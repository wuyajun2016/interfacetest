package com.dfire.testcase.function.integration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.uncommons.reportng.HTMLReporter;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.dfire.testcase.bean.BasicSetting;
import com.dfire.testcase.function.bean.BaseParamBean;
import com.dfire.testcase.function.bean.CardPayBean;
import com.dfire.testcase.function.boss.BossController;
import com.dfire.testcase.function.service.cash.CashService;
import com.dfire.testcase.function.util.api.SeatPrePayAPI;
import com.dfire.testcase.function.util.base.SeatNoPrePayUtil;
import com.dfire.testcase.function.util.base.SeatPrePayUtil;
import com.dfire.testcase.function.util.base.WechatBaseUtil;
import com.dfire.wechat.util.CartIncludeSuitForm;
import com.dfire.wechat.util.CommonConstants;
import com.dfire.wechat.util.HttpRequestEx;
import com.dfire.wechat.util.PropertiesUtil;
import com.dfire.wechat.util.Response;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


/**
 * 扫桌码预付款流程
 * @author pidan
 *
 */
@Listeners(HTMLReporter.class)
public class SeatCodePrePayTest{
	
	private static final Logger logger = Logger.getLogger(SeatCodePrePayTest.class);
	private PropertiesUtil propertyUtil = PropertiesUtil.getPropertiesUtil();
	
	private String host = propertyUtil.getValue("DEFAULT_HOST");
	private String cashHost = propertyUtil.getValue("CASH_HOST");
	private String cashHeartHost = propertyUtil.getValue("CASH_HEART_HOST");
	
	private String appSecret = propertyUtil.getValue("appSecret");
	
	private BaseParamBean baseParamBean = new BaseParamBean();
	private CashService cashService = new CashService();
	private Map<String, String> cashMap = new HashMap<String, String>();
	
	private HttpRequestEx httpRequest;	
	private HttpRequestEx httpRequestForBossAPI;
	
	private HttpRequestEx cashRequest;	
	private HttpRequestEx cashHeartRequest;	
	
	private boolean https = CommonConstants.HTTPS;
	
	private Map<String, String> otherParameter = new HashMap<String, String>();
	
	private String repeat = "false";	
	private String recommend = "false";
	
	private String people = "6";
	private String memo = "";
	private String oldPeople = "";
	
	private String page = "";
	private String pageSize = "";
	private String isPreCart = "false";
	
	private BossController bossController = new BossController();
	
	@BeforeClass(alwaysRun = true)
	public void setup() throws Exception{
		
		logger.info("ScanSeatCodePrePayTest start!");
		
		httpRequest = new HttpRequestEx(host, https);
		httpRequestForBossAPI = new HttpRequestEx(CommonConstants.BOSS_API_HOST, https);
		
		cashRequest = new HttpRequestEx(cashHost, https);
		cashHeartRequest = new HttpRequestEx(cashHeartHost, false);
		
		String entityId = propertyUtil.getValue("entityId");
		String seatCode = propertyUtil.getValue("seatCode");
		String orderId = "";
		String unionId = propertyUtil.getValue("unionid");
		String xtoken = WechatBaseUtil.getToken(httpRequest, entityId, unionId);//获取token
		
		baseParamBean.setEntityId(entityId);
		baseParamBean.setOrderId(orderId);
		baseParamBean.setSeatCode(seatCode);
		baseParamBean.setXtoken(xtoken);
		baseParamBean.setCardId("");
		
		otherParameter.put("repeat", repeat);
		otherParameter.put("recommend", recommend);		
		
		otherParameter.put("peopleCount", people);
		otherParameter.put("memo", memo);		
		otherParameter.put("oldPeople", oldPeople);
		
		otherParameter.put("page", page);
		otherParameter.put("pageSize", pageSize);
		otherParameter.put("isPreCart", isPreCart);
		
		otherParameter.put("people", people);
		otherParameter.put("menuIdList", ""); // 该参数为空
		otherParameter.put("memo_labels", "");
		
		// 用于提交订单
		otherParameter.put("isPrePay", "true");   // 预付款情况下需要修改为 true
		otherParameter.put("cartTime", "");
		
		//收银机登录
		cashMap.put("entity_id", baseParamBean.getEntityId());
		cashService.setCashInitBean();
		cashService.setCashExtBean();
		String sessionID = cashService.loginwithpassword(cashRequest,baseParamBean, cashMap, appSecret);
		cashService.cashextBean.setSessionId(sessionID);
		
	}
	
	
	@AfterClass(alwaysRun = true)
	public void after(){
		httpRequest.ShutDown();
	}
	
	
	/**
	 * @author pidan
	 * 原始代码, 予以保留参考
	 * @throws Exception
	 */
	@Test(groups = {"flowNo"})
	public void scanEmptySeat() throws Exception{
		logger.info("桌码预付款扫空桌测试开始!");
		//收银心跳
		String heartAppSecret = propertyUtil.getValue("heart.appsecret");
		cashService.heart(cashHeartRequest,baseParamBean, cashMap, heartAppSecret);
		
		List<CartIncludeSuitForm> cartList= new ArrayList<CartIncludeSuitForm>();
		Map<String, String> parMap = new HashMap<String, String>();
		//扫码
		boolean scancodeflag = SeatPrePayAPI.scanCode(httpRequest,baseParamBean);
		Assert.assertEquals(scancodeflag, true, "扫码失败");
		
		//点击进入
		String repeat = "false";	//菜单是否去重
		String recommend = "false"; //是否展示推荐菜
		parMap.put("repeat", repeat);
		parMap.put("recommend", recommend);
		boolean clEnFlag = SeatPrePayAPI.clickToEnter(httpRequest,baseParamBean,parMap);
		Assert.assertEquals(clEnFlag, true, "点击进入流程失败");
		
		//如果是空桌
		String memo=""; //备注
		String isPreCart = "false";//是否是预点菜
		String page= "1"; //当前页
		String pageSize = "12"; //一页数量
		String oldpeople = "";//修改前人数
		String peopleCount="2";
		parMap.put("memo", memo);
		parMap.put("isPreCart", isPreCart);
		parMap.put("page", page);
		parMap.put("pageSize", pageSize);
		parMap.put("oldPeople", oldpeople);
		parMap.put("people", peopleCount);
		//开始点菜											
		boolean intoMenusFlag = SeatPrePayAPI.startChooseDish(httpRequest, baseParamBean, parMap);
		Assert.assertEquals(intoMenusFlag, true, "进入点菜流程失败");
		
		//点菜
		//获取menuId
		String menusJson = SeatPrePayAPI.commonMap.get("listMenu_data").toString();
		String menuId = (String) JSONPath.read(menusJson, "$.kindMenusVos[0].menus[0].id");
		Assert.assertNotNull(menuId,"获取菜单ID失败！");
		boolean chooseDishFlag = SeatPrePayAPI.chooseDish(httpRequest, baseParamBean, parMap);
		Assert.assertEquals(chooseDishFlag, true, "获取菜规格和做法失败");
		
		//将菜加入购物车
		Double num = 1d;//菜的数量
		int kindType = 1;//菜的类型  TODO 不明来源
		JSONObject menusObject  = (JSONObject) SeatPrePayAPI.commonMap.get("menuspec_data");
		String makeId = (String) JSONPath.read(menusObject.toString(), "$.makeDataList[0].makeId");
		String specId = (String) JSONPath.read(menusObject.toString(), "$.specDataList[0].specItemId");
		CartIncludeSuitForm cartIncludeSuitForm = new CartIncludeSuitForm();
		cartIncludeSuitForm.setMakeId(makeId);
		cartIncludeSuitForm.setMenuId(menuId);
		cartIncludeSuitForm.setSpecDetailId(specId);
		cartIncludeSuitForm.setNum(num);
		cartIncludeSuitForm.setKindType(kindType);
		boolean addToCartFlag = SeatPrePayAPI.addDishToCarts(httpRequest, baseParamBean,cartIncludeSuitForm);
		Assert.assertEquals(addToCartFlag, true, "加入购物车失败！");
		
		//进入购物车
		cartList.add(cartIncludeSuitForm);
		List<String> menuIdList = null;
		Map<String, String> intoCartMap = new HashMap<String, String>();
		intoCartMap.put("page", page);
		intoCartMap.put("isPreCart", isPreCart);
		intoCartMap.put("pageSize", pageSize);
		boolean intoCartFlag = SeatPrePayAPI.intoCart(httpRequest,baseParamBean,menuIdList,intoCartMap,cartList);
		Assert.assertEquals(intoCartFlag, true, "进入购物车失败");
		
		//下单 去“账单” 页面，这点和非预付款不一样
		String flag = "false";//是否重新拉取账单
		boolean intoPageFlag = SeatPrePayAPI.intoBillPage(httpRequest,baseParamBean,flag,cartList);
		Assert.assertEquals(intoPageFlag,"进入账单页面失败");
		
		//等待支付 获取支付类型
		parMap.put("peopleCount", peopleCount);
		boolean getPayTypeFlag = SeatPrePayAPI.getPayType(httpRequest, baseParamBean, parMap,cartList);
		Assert.assertEquals(getPayTypeFlag, true, "获取支付方式失败");
		
		//会员卡支付
		CardPayBean cardPayBean = new CardPayBean();
		boolean cardPayFlag = SeatPrePayAPI.cardPay(httpRequest,baseParamBean,cardPayBean);
		Assert.assertEquals(cardPayFlag, true, "会员卡支付失败");
		
		cashMap.put("entity_id", baseParamBean.getEntityId());
		cashService.preSettleAccounts(cashRequest, cashMap,baseParamBean, appSecret);
		logger.info("桌码预付款扫空桌测试完成!");
	}
	
	
	

	
	@Test(description = "扫桌码预付款, 单个用户点菜下单", dataProvider = "placeOrderForSeatWithPrePay", 
			dataProviderClass = SeatCodePrePayTestData.class, groups = {"flowNo"})
	public void placeOrderForSeatWithPrePay(String description, List<CartIncludeSuitForm> cartSuitList) {
		
		logger.info("桌码预付款扫空桌测试开始!");
		
		try{
			
			////////////////////////////   预处理动作        //////////////////////////////////////////////////////////
			
			// 开启桌码预付款功能
			boolean result = bossController.basicConfigForBoss(httpRequestForBossAPI, CommonConstants.entityIdForPiDan, 
					BasicSetting.prePaySeat, CommonConstants.prePayConfigForSeatCodeWithPiDan, true);
			Assert.assertEquals(result, true, "开启 扫桌码 预付款 开关失败");
			
			//收银心跳
			String heartAppSecret = propertyUtil.getValue("heart.appsecret");
			cashService.heart(cashHeartRequest, baseParamBean, cashMap, heartAppSecret);
			
						
			
			///////////////////////////    业务代码            ////////////////////////////////////////////////////////////
			// scan code
			SeatNoPrePayUtil.scanCode(httpRequest, baseParamBean, otherParameter);
									
			// click to enter  (pre conditions)
			SeatPrePayAPI.clickToEnter(httpRequest, baseParamBean, otherParameter);
			
			// add menu
			SeatNoPrePayUtil.addMenuToCart(httpRequest, baseParamBean, otherParameter, cartSuitList);
			
			// place order
			Map<String, Response> payAndOrderResult= SeatPrePayUtil.placeAndPayForOrder(httpRequest, baseParamBean, otherParameter, cartSuitList);						
			
			
			
			
			///////////////////////////////   后续清理动作            //////////////////////////////////////////////////////
						
			//  收银结账清台
			cashMap.put("entity_id", baseParamBean.getEntityId());
			JsonObject orderObject = new JsonParser().parse(payAndOrderResult.get("waitingOrder").getResponseStr()).getAsJsonObject();
			baseParamBean.setWaitingOrderId(orderObject.get("data").getAsJsonObject().get("waitingOrderId").getAsString());
			cashService.preSettleAccounts(cashRequest, cashMap, baseParamBean, appSecret);
		
			// 关闭桌码预付款开关
			boolean result2 = bossController.basicConfigForBoss(httpRequestForBossAPI, CommonConstants.entityIdForPiDan, 
					BasicSetting.noPrePaySeat, CommonConstants.prePayConfigForSeatCodeWithPiDan, false);
			Assert.assertEquals(result2, true, "开启 扫桌码 预付款 开关失败");
			
			
			// 清理购物车
			WechatBaseUtil.clearCart(httpRequest, baseParamBean);
			
		}catch(Exception e){
			
			logger.info("出现异常：" + e.toString());
			e.printStackTrace();
			
		}finally{
			
			// 关闭桌码的预付款开关，并进行检查		
			boolean result2 = bossController.basicConfigForBoss(httpRequestForBossAPI, CommonConstants.entityIdForPiDan, 
					BasicSetting.noPrePaySeat, CommonConstants.prePayConfigForSeatCodeWithPiDan, false);
			Assert.assertEquals(result2, true, "开启 扫桌码 预付款 开关失败");
			
			
			// 清理购物车
			WechatBaseUtil.clearCart(httpRequest, baseParamBean);
			
		}
		
		
		logger.info("桌码预付款扫空桌测试完成!");
		
		
	}
	
	


}
