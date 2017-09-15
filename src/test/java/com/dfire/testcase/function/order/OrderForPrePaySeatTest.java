package com.dfire.testcase.function.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.dfire.testcase.bean.BasicSetting;
import com.dfire.testcase.function.bean.BaseParamBean;
import com.dfire.testcase.function.bean.TradeBillBean;
import com.dfire.testcase.function.boss.BossConfig;
import com.dfire.testcase.function.boss.BossController;
import com.dfire.testcase.function.service.DataService;
import com.dfire.testcase.function.util.api.SeatNoPrePayAPI;
import com.dfire.testcase.function.util.api.SeatPrePayAPI;
import com.dfire.testcase.function.util.base.SeatNoPrePayUtil;
import com.dfire.testcase.function.util.base.WechatBaseUtil;
import com.dfire.wechat.util.BeanProvider;
import com.dfire.wechat.util.CartIncludeSuitForm;
import com.dfire.wechat.util.CommonConstants;
import com.dfire.wechat.util.HttpRequestEx;
import com.dfire.wechat.util.Response;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class OrderForPrePaySeatTest {
	
	private static final Logger logger = Logger.getLogger(OrderForPrePaySeatTest.class);
	private HttpRequestEx httpRequest;	
	private HttpRequestEx httpRequestForBossAPI;
	private String host = CommonConstants.DEFAULT_HOST;	
	
	private String entityId = CommonConstants.entityId;
	private String seatCode = CommonConstants.seatCode;
	private String orderId = "";
	
	private String repeat = "false";	
	private String recommend = "false";
	private String menuId = CommonConstants.menuId;
	
	private String people = "6";
	private String memo = "";
	private String oldPeople = "";
	
	private String page = "";
	private String pageSize = "";
	private String isPreCart = "false";
	
	private String cardId = "";
	
	private String xtoken = "";
	private String unionId = CommonConstants.unionID;
	
	private String xtokenOther = "";
	private String unionIdOther = CommonConstants.unionIDOther;
	private BaseParamBean baseParamOther = new BaseParamBean();	
	
	private BaseParamBean baseParam = new BaseParamBean();
	private Map<String, String> otherParameter = new HashMap<String, String>();
	
	private Gson gson = new Gson();
	private boolean https = CommonConstants.HTTPS;
	private BossController bossController = new BossController();
	
	@BeforeClass(alwaysRun = true)
	public void setup(){
		
		logger.info("setup in OrderForPrePaySeatTest");
		
		httpRequest = new HttpRequestEx(host, https);		
		httpRequestForBossAPI = new HttpRequestEx(CommonConstants.BOSS_API_HOST, https);
		
		// 获取 token
		xtoken = WechatBaseUtil.getToken(httpRequest, entityId, unionId);
		xtokenOther = WechatBaseUtil.getToken(httpRequest, entityId, unionIdOther);
		
		baseParam.setEntityId(entityId);
		baseParam.setOrderId(orderId);
		baseParam.setSeatCode(seatCode);
		baseParam.setXtoken(xtoken);
		
		baseParamOther.setXtoken(xtokenOther);
		baseParamOther.setEntityId(entityId);
		baseParamOther.setSeatCode(seatCode);
		baseParamOther.setOrderId(orderId);
		
		otherParameter.put("repeat", repeat);
		otherParameter.put("recommend", recommend);		
		otherParameter.put("menuId", menuId);
		
		otherParameter.put("people", people);
		otherParameter.put("memo", memo);		
		otherParameter.put("oldPeople", oldPeople);
		
		otherParameter.put("page", page);
		otherParameter.put("pageSize", pageSize);
		otherParameter.put("isPreCart", isPreCart);
		
		otherParameter.put("peopleCount", people);
		otherParameter.put("cardId", cardId);
		
		// 用于提交订单
		otherParameter.put("isPrePay", "false");
		otherParameter.put("cartTime", Long.toString(System.currentTimeMillis()));
		
		
		// scan code
		SeatNoPrePayUtil.scanCode(httpRequest, baseParam, otherParameter);
		SeatNoPrePayUtil.scanCode(httpRequest, baseParamOther, otherParameter);
		
		// click to enter  (pre conditions)
		SeatPrePayAPI.clickToEnter(httpRequest, baseParam, otherParameter);
		SeatPrePayAPI.clickToEnter(httpRequest, baseParamOther, otherParameter);
		
		// 清除购物车中的所有菜 (测试需要, 非业务流程)
		WechatBaseUtil.clearCart(httpRequest, baseParam);
		
	}
	
	
	@AfterClass(alwaysRun = true)
	public void tearDown(){
		
		logger.info("tearDown in OrderForPrePaySeatTest");
		
		// 关闭 扫桌码 预付款 开关
		boolean result = bossController.basicConfigForBoss(httpRequestForBossAPI, entityId, 
				BasicSetting.noPrePaySeat, CommonConstants.prePayConfigForSeatCode, false);
		Assert.assertEquals(result, true, "关闭 扫桌码 预付款 开关失败");
					
		// 清除购物车中的所有菜 (测试需要, 非业务流程)
		WechatBaseUtil.clearCart(httpRequest, baseParam);
		
		httpRequest.ShutDown();
		httpRequestForBossAPI.ShutDown();
	}
	
	
	
	
	///////////////////////////////// 用户下单但未进行支付       ////////////////////////////////////////////////////////////
		
	@Test(description = "单个用户点菜下单", dataProvider = "orderForOneCustomer",
			dataProviderClass = OrderForPrePaySeatTestData.class, groups = {"moduleNo", "allNo"})
	public void orderForOneCustomer(String description, List<CartIncludeSuitForm> cartSuitList, TradeBillBean tradeBillBean){
	
	
		logger.info(description);
		
		try{
		
			////////////////////////前置操作, 包括数据准备      //////////////////////////////////////
			// 开启 扫桌码 预付款 开关
			boolean result = bossController.basicConfigForBoss(httpRequestForBossAPI, entityId, 
					BasicSetting.prePaySeat, CommonConstants.prePayConfigForSeatCode, true);
			Assert.assertEquals(result, true, "开启 扫桌码 预付款 开关失败");
			
			// 收银非预付款下单需要 5min 超时失效, 该操作为了使得后续操作不受前次操作下单影响			
//			logger.info("等待之前的订单失效");
//			Thread.sleep(5*60*1000);
//			logger.info("等待动作已完成");
			
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			WechatBaseUtil.clearCart(httpRequest, baseParam);
			
			
			
			/////////////////////////    业务操作         ////////////////////////////////////////
			// 修改人数和备注信息
			WechatBaseUtil.modifyPeople(httpRequest, baseParam, otherParameter);
			
			// 添加必选商品
			WechatBaseUtil.addRequiredItem(httpRequest, baseParam, otherParameter);			
			
			//将菜加入购物车
			for(CartIncludeSuitForm cartSuitForm : cartSuitList){
				WechatBaseUtil.addDishToCarts(httpRequest,baseParam, gson.toJson(cartSuitForm));
			}
			
			
			/////////////////////////    数据校验      ///////////////////////////////////////////
			boolean result1 = DataService.DataVerified(httpRequest, baseParam, people, cartSuitList);
			Assert.assertEquals(result1, true);
			
			
			////////////////////////   下单流程      /////////////////////////////////////////////
			// 下单前检查订单
			Response checkResult = WechatBaseUtil.checkOrder(httpRequest, baseParam, gson.toJson(cartSuitList));
			JsonObject checkJsonObject = new JsonParser().parse(checkResult.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(checkJsonObject.get("code").getAsInt(), 1, "下单前检查订单失败");
			
			
			// 获取优惠方案及支付订单信息
			Response getTradeBill = WechatBaseUtil.getTradeBillForPay(httpRequest, baseParam, tradeBillBean);
			JsonObject getTradeBillJsonObject = new JsonParser().parse(getTradeBill.getResponseStr()).getAsJsonObject();
			Assert.assertEquals(getTradeBillJsonObject.get("code").getAsInt(), 1, "获取优惠方案及支付订单信息失败");
			
			
			
			////////////////////////   支付流程      /////////////////////////////////////////////
			// 预付款下单确认, 下单检测购物车与云购物车是否一致(目前该接口需要开启收银机,鉴于云收银正在开发, 因此目前该支付动作暂时不做)
			Response confirmPrePay = WechatBaseUtil.confirmPrePay(httpRequest, gson.toJson(cartSuitList), baseParam, otherParameter);
			JsonObject confirmPrePayJsonObject = new JsonParser().parse(confirmPrePay.getResponseStr()).getAsJsonObject();
			Assert.assertEquals(confirmPrePayJsonObject.get("code").getAsInt(), 1, "预付款下单确认失败");
			
			
//			// 获取支付类型
//			Response getPayType = WechatBaseUtil.getPayType(httpRequest, baseParam, otherParameter);
//			JsonObject getPayTypeJsonObject = new JsonParser().parse(getPayType.getResponseStr()).getAsJsonObject();
//			Assert.assertEquals(getPayTypeJsonObject.get("code").getAsInt(), 1, "获取支付类型失败");
			
		}catch(Exception e){
			
			e.printStackTrace();
		
		}finally{
		
			// 关闭 扫桌码 预付款 开关
			boolean result = bossController.basicConfigForBoss(httpRequestForBossAPI, entityId, 
					BasicSetting.noPrePaySeat, CommonConstants.prePayConfigForSeatCode, false);
			Assert.assertEquals(result, true, "关闭 扫桌码 预付款 开关失败");
			
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			WechatBaseUtil.clearCart(httpRequest, baseParam);
		}


	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@Test(description="收银审核同意", dataProvider = "addDishToCartTest2",
			dataProviderClass = OrderForPrePaySeatTestData.class, groups = {"moduleNo", "allNo"})
	public void orderApprovalTest(String description, List<CartIncludeSuitForm> cartSuitList){
		
		logger.info(description);
		
		// 下单前检查订单
		WechatBaseUtil.checkOrder(httpRequest, baseParam, gson.toJson(cartSuitList));
		
		//提交订单
		WechatBaseUtil.submitOrder(httpRequest, baseParam, otherParameter);
		
		// 获取订单列表
		Response responseFromGetOrder = WechatBaseUtil.getOrder(httpRequest, baseParam);
		String orderIdsFormJson = BeanProvider.getOrderIdsFormListJson(responseFromGetOrder);
		
		// 检查订单变化  (可能多人提交订单)
		WechatBaseUtil.checkOrderChange(httpRequest, baseParam, orderIdsFormJson);
		
		//从Dpush上调用获取订单接口
		
		//与提交订单做校验
		
		//调用审核同意
		
	}
	
	
	@Test(description="收银超时未处理")
	public void orderOvertime(){
		//提交订单
		
		//设置订单超时时间
		
		//等待订单超时
		
		//获取订单
		
		//校验订单状态
		
	}
	
	
	@Test(description="收银超时未处理再次提交")
	public void orderRepeatSubmit(){
		//提交订单
		//设置订单超时时间
				
		//等待订单超时
				
		//获取订单
				
		//校验订单状态
		
		//再次提交订单
		
		//获取订单
	}
	
	@Test(description="收银拒绝")
	public void orderRefuse(){
		//提交订单
				
		//收银拒绝
		
		//校验订单状态
		
		//获取订单
	}
	
	@Test(description="重复下单")
	public void orderRepeat(){
		//提交订单
		
		//提交订单
		
		//校验订单状态
		
		//获取订单
	}
	
	

}
