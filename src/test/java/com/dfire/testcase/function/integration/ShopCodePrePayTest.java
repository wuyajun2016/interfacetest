package com.dfire.testcase.function.integration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.uncommons.reportng.HTMLReporter;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.dfire.testcase.function.bean.BaseParamBean;
import com.dfire.testcase.function.bean.CardPayBean;
import com.dfire.testcase.function.service.cash.CashService;
import com.dfire.testcase.function.util.api.ShopPrePayAPI;
import com.dfire.testcase.function.util.base.WechatBaseUtil;
import com.dfire.wechat.util.CartIncludeSuitForm;
import com.dfire.wechat.util.CommonConstants;
import com.dfire.wechat.util.HttpRequestEx;
import com.dfire.wechat.util.PropertiesUtil;


/**
 * 扫店码预付款流程
 * @author pidan
 *
 */
@Listeners(HTMLReporter.class)
public class ShopCodePrePayTest{
	
	private static final Logger logger = Logger.getLogger(ShopCodePrePayTest.class);
	private PropertiesUtil propertyUtil = PropertiesUtil.getPropertiesUtil();
	
	//从配置文件获取参数
	private String host = propertyUtil.getValue("DEFAULT_HOST");
	private String cashHost = propertyUtil.getValue("CASH_HOST");
	private String cashHeartHost = propertyUtil.getValue("CASH_HEART_HOST");
	
	private String uid = propertyUtil.getValue("uid");
	private String appSecret = propertyUtil.getValue("appSecret");
	
	
	private HttpRequestEx httpRequest;	
	private HttpRequestEx cashRequest;	
	private HttpRequestEx cashHeartRequest;	
	
	
	private boolean https = CommonConstants.HTTPS;
	
	private BaseParamBean baseParamBean = new BaseParamBean();
	private CashService cashService = new CashService();
	private Map<String, String> cashMap = new HashMap<String, String>();
				
	@BeforeClass(alwaysRun = true)
	public void setup() throws Exception{
		httpRequest = new HttpRequestEx(host, https);
		cashRequest = new HttpRequestEx(cashHost, https);
		cashHeartRequest = new HttpRequestEx(cashHeartHost, false);
		
		String entityId = propertyUtil.getValue("entityId");
		String seatCode = "";
		String orderId = "";
		String unionId = propertyUtil.getValue("unionid");
		String xtoken = WechatBaseUtil.getToken(httpRequest, entityId, unionId);
		
		
		baseParamBean.setEntityId(entityId);
		baseParamBean.setOrderId(orderId);
		baseParamBean.setSeatCode(seatCode);
		baseParamBean.setCardId("");
		baseParamBean.setXtoken(xtoken);
		
		//收银机登录
		cashMap.put("entity_id", baseParamBean.getEntityId());
		cashService.setCashInitBean();
		cashService.setCashExtBean();
		String sessionID = cashService.loginwithpassword(cashRequest,baseParamBean, cashMap, appSecret);
		cashService.cashextBean.setSessionId(sessionID);
		
	}
	
	
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod() throws InterruptedException{
		//批量执行后order的值也许不为空，置为空
		String orderId = "";
		baseParamBean.setOrderId(orderId);
		
		ShopPrePayAPI.commonMap.clear();
		Thread.sleep(2000);
	}
	
	@AfterClass(alwaysRun = true)
	public void after(){
		httpRequest.ShutDown();
		cashHeartRequest.ShutDown();
		cashRequest.ShutDown();
	}
	
	
	@Test(invocationCount=1,threadPoolSize=1,groups={"flowNo"})
	public void apiTest() throws Exception{
		logger.info("ShopCodePrePayTest start!");
		Map<String, String> parMap = new HashMap<String, String>();
		String heartAppSecret = propertyUtil.getValue("heart.appsecret");
		//收银心跳
		cashService.heart(cashHeartRequest,baseParamBean, cashMap, heartAppSecret );
				
		//扫店码
		Boolean scancodeflag = ShopPrePayAPI.scanCode(httpRequest,baseParamBean);
		Assert.assertTrue(scancodeflag,"扫码失败");
		
		//点击进入
		String repeat = "false";	//菜单是否去重
		String recommend = "false"; //是否展示推荐菜
		parMap.put("repeat", repeat);
		parMap.put("recommend", recommend);
		Boolean clEnFlag = ShopPrePayAPI.clickToEnter(httpRequest,baseParamBean, parMap);
		Assert.assertTrue(clEnFlag,"点击进入流程失败");
		
		//如果是空桌
		String people_count="2";
		String memo="";
		String isPreCart = "true";
		String page= "1";
		String pageSize = "12";
		String oldpeople = "";
		parMap.put("people", people_count);
		parMap.put("memo", memo);
		parMap.put("oldpeople", oldpeople);
		parMap.put("page", page);
		parMap.put("pageSize", pageSize);
		parMap.put("isPreCart", isPreCart);
		
		
		// 用于提交订单
		parMap.put("isPrePay", "false");
		parMap.put("cartTime", "");
		
		//开始点菜
		Boolean intoMenusFlag = ShopPrePayAPI.startChooseDish(httpRequest, baseParamBean,parMap);
		Assert.assertTrue(intoMenusFlag,"进入点菜流程失败");
		
		//点菜
		//获取menuId
		String menusJson = ShopPrePayAPI.commonMap.get("listMenu_data").toString();
		String menuId = (String) JSONPath.read(menusJson, "$.kind_menus[0].menus[0].id");
		logger.info("menuid:"+menuId);
		Assert.assertNotNull(menuId,"菜单ID为空！");
		Double num = 1d;//菜的数量
		int kindType = 1;//菜的类型
		parMap.put("menuId", menuId);
		Boolean chooseDishFlag = ShopPrePayAPI.chooseDish(httpRequest, baseParamBean, parMap);
		Assert.assertTrue(chooseDishFlag,"获取菜规格和做法失败");
		
		//将菜加入购物车
		JSONObject menusObject  = (JSONObject) ShopPrePayAPI.commonMap.get("menuspec_data");
		String makeId = (String) JSONPath.read(menusObject.toString(), "$.makeDataList[0].makeId");
		String specId = (String) JSONPath.read(menusObject.toString(), "$.specDataList[0].specItemId");
		CartIncludeSuitForm cartIncludeSuitForm = new CartIncludeSuitForm();
		cartIncludeSuitForm.setMakeId(makeId);
		cartIncludeSuitForm.setMenuId(menuId);
		cartIncludeSuitForm.setSpecDetailId(specId);
		cartIncludeSuitForm.setNum(num);
		cartIncludeSuitForm.setKindType(kindType);
		cartIncludeSuitForm.setUid(uid);//给账单接口用
		Boolean addToCartFlag = ShopPrePayAPI.addDishToCarts(httpRequest, baseParamBean,cartIncludeSuitForm);
		Assert.assertTrue(addToCartFlag,"加入购物车失败！");
		
		
		//进入购物车
		List<CartIncludeSuitForm> cartList= new ArrayList<CartIncludeSuitForm>();
		cartList.add(cartIncludeSuitForm);
		Map<String, String> intoCartMap = new HashMap<String, String>();
		intoCartMap.put("page", page);
		intoCartMap.put("isPreCart", isPreCart);
		intoCartMap.put("pageSize", pageSize);
		Boolean intoCartFlag = ShopPrePayAPI.intoCart(httpRequest,baseParamBean,intoCartMap);
		Assert.assertTrue(intoCartFlag,"进入购物车失败");
		
		//下单 去“账单” 页面，这点和非预付款不一样
		List<CartIncludeSuitForm> cartListTeForms = new ArrayList<CartIncludeSuitForm>();
		for (CartIncludeSuitForm objForm:cartList) {
			objForm.setMakeId(objForm.getMakeId()==null?"":objForm.getMakeId());
			objForm.setSpecDetailId(objForm.getSpecDetailId()==null?"":objForm.getSpecDetailId());
			objForm.setNum(objForm.getNum());
			cartListTeForms.add(objForm);
		}
		//必须位false 否则提示orderID不能为空
		Boolean orderFlag = ShopPrePayAPI.intoBillPage(httpRequest, baseParamBean,"false", cartList);
		Assert.assertTrue(orderFlag,"拉取账单失败");
		//等待支付 获取支付类型
		parMap.put("peopleCount", people_count);
		Boolean payTypeFlag = ShopPrePayAPI.getPayType(httpRequest, baseParamBean, parMap, cartList);
		Assert.assertTrue(payTypeFlag,"获取支付类型失败");
		
		//支付
		//会员卡支付
		CardPayBean cardPayBean = new CardPayBean();
		Boolean cardPayFlag = ShopPrePayAPI.cardPay(httpRequest, baseParamBean,cardPayBean);
		Assert.assertTrue(cardPayFlag, "会员卡支付失败");
				
		cashService.preSettleAccounts(cashRequest, cashMap,baseParamBean, appSecret);
		logger.info("ShopCodePrePayTest end!");
		
	}


	
	@Test(description = "单个用户点菜下单", dataProvider = "placeOrderWithPrePayTest",
		dataProviderClass = ShopCodePrePayTestData.class, invocationCount=1,threadPoolSize=1,groups={"flowTemp"})
	public void placeOrderWithPrePayTest() {
		
		logger.info("ShopCodePrePayTest start!");
		
		try{
			
			Map<String, String> parMap = new HashMap<String, String>();
			String heartAppSecret = propertyUtil.getValue("heart.appsecret");
			
			
			//收银心跳
			cashService.heart(cashHeartRequest,baseParamBean, cashMap, heartAppSecret );
					
			//扫店码
			Boolean scancodeflag = ShopPrePayAPI.scanCode(httpRequest,baseParamBean);
			Assert.assertTrue(scancodeflag,"扫码失败");
			
			//点击进入
			String repeat = "false";	//菜单是否去重
			String recommend = "false"; //是否展示推荐菜
			parMap.put("repeat", repeat);
			parMap.put("recommend", recommend);
			Boolean clEnFlag = ShopPrePayAPI.clickToEnter(httpRequest,baseParamBean, parMap);
			Assert.assertTrue(clEnFlag,"点击进入流程失败");
			
			//如果是空桌
			String people_count="2";
			String memo="";
			String isPreCart = "true";
			String page= "1";
			String pageSize = "12";
			String oldpeople = "";
			parMap.put("people", people_count);
			parMap.put("memo", memo);
			parMap.put("oldpeople", oldpeople);
			parMap.put("page", page);
			parMap.put("pageSize", pageSize);
			parMap.put("isPreCart", isPreCart);
			
			
			// 用于提交订单
			parMap.put("isPrePay", "false");
			parMap.put("cartTime", "");
			
			//开始点菜
			Boolean intoMenusFlag = ShopPrePayAPI.startChooseDish(httpRequest, baseParamBean,parMap);
			Assert.assertTrue(intoMenusFlag,"进入点菜流程失败");
			
			//点菜
			//获取menuId
			String menusJson = ShopPrePayAPI.commonMap.get("listMenu_data").toString();
			String menuId = (String) JSONPath.read(menusJson, "$.kind_menus[0].menus[0].id");
			logger.info("menuid:"+menuId);
			Assert.assertNotNull(menuId,"菜单ID为空！");
			Double num = 1d;//菜的数量
			int kindType = 1;//菜的类型
			parMap.put("menuId", menuId);
			Boolean chooseDishFlag = ShopPrePayAPI.chooseDish(httpRequest, baseParamBean, parMap);
			Assert.assertTrue(chooseDishFlag,"获取菜规格和做法失败");
			
			//将菜加入购物车
			JSONObject menusObject  = (JSONObject) ShopPrePayAPI.commonMap.get("menuspec_data");
			String makeId = (String) JSONPath.read(menusObject.toString(), "$.makeDataList[0].makeId");
			String specId = (String) JSONPath.read(menusObject.toString(), "$.specDataList[0].specItemId");
			CartIncludeSuitForm cartIncludeSuitForm = new CartIncludeSuitForm();
			cartIncludeSuitForm.setMakeId(makeId);
			cartIncludeSuitForm.setMenuId(menuId);
			cartIncludeSuitForm.setSpecDetailId(specId);
			cartIncludeSuitForm.setNum(num);
			cartIncludeSuitForm.setKindType(kindType);
			cartIncludeSuitForm.setUid(uid);//给账单接口用
			Boolean addToCartFlag = ShopPrePayAPI.addDishToCarts(httpRequest, baseParamBean,cartIncludeSuitForm);
			Assert.assertTrue(addToCartFlag,"加入购物车失败！");
			
			
			//进入购物车
			List<CartIncludeSuitForm> cartList= new ArrayList<CartIncludeSuitForm>();
			cartList.add(cartIncludeSuitForm);
			Map<String, String> intoCartMap = new HashMap<String, String>();
			intoCartMap.put("page", page);
			intoCartMap.put("isPreCart", isPreCart);
			intoCartMap.put("pageSize", pageSize);
			Boolean intoCartFlag = ShopPrePayAPI.intoCart(httpRequest,baseParamBean,intoCartMap);
			Assert.assertTrue(intoCartFlag,"进入购物车失败");
			
			//下单 去“账单” 页面，这点和非预付款不一样
			List<CartIncludeSuitForm> cartListTeForms = new ArrayList<CartIncludeSuitForm>();
			for (CartIncludeSuitForm objForm:cartList) {
				objForm.setMakeId(objForm.getMakeId()==null?"":objForm.getMakeId());
				objForm.setSpecDetailId(objForm.getSpecDetailId()==null?"":objForm.getSpecDetailId());
				objForm.setNum(objForm.getNum());
				cartListTeForms.add(objForm);
			}
			//必须位false 否则提示orderID不能为空
			Boolean orderFlag = ShopPrePayAPI.intoBillPage(httpRequest, baseParamBean,"false", cartList);
			Assert.assertTrue(orderFlag,"拉取账单失败");
			//等待支付 获取支付类型
			parMap.put("peopleCount", people_count);
			Boolean payTypeFlag = ShopPrePayAPI.getPayType(httpRequest, baseParamBean, parMap, cartList);
			Assert.assertTrue(payTypeFlag,"获取支付类型失败");
			
			//支付
			//会员卡支付
			CardPayBean cardPayBean = new CardPayBean();
			Boolean cardPayFlag = ShopPrePayAPI.cardPay(httpRequest, baseParamBean,cardPayBean);
			Assert.assertTrue(cardPayFlag, "会员卡支付失败");
					
			cashService.preSettleAccounts(cashRequest, cashMap,baseParamBean, appSecret);
			
		}catch(Exception e){
			
			logger.info("出现异常" + e.toString());
			
		}
		
		logger.info("ShopCodePrePayTest end!");
		
	}


	


}
