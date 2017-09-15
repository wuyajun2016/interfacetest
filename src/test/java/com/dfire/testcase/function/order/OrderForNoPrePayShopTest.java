package com.dfire.testcase.function.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.dfire.testcase.function.bean.BaseParamBean;
import com.dfire.testcase.function.boss.BossController;
import com.dfire.testcase.function.boss.ForceConfig;
import com.dfire.testcase.function.dishes.OrderDishesForNoPrePayShopTest;
import com.dfire.testcase.function.dishes.OrderDishesForNoPrePayShopTestData;
import com.dfire.testcase.function.service.DataService;
import com.dfire.testcase.function.util.api.SeatPrePayAPI;
import com.dfire.testcase.function.util.base.SeatNoPrePayUtil;
import com.dfire.testcase.function.util.base.ShopNoPrePayUtil;
import com.dfire.testcase.function.util.base.WechatBaseUtil;
import com.dfire.wechat.util.BeanProvider;
import com.dfire.wechat.util.CartIncludeSuitForm;
import com.dfire.wechat.util.CommonConstants;
import com.dfire.wechat.util.HttpRequestEx;
import com.dfire.wechat.util.Response;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class OrderForNoPrePayShopTest {
	
	
	private static final Logger logger = Logger.getLogger(OrderForNoPrePayShopTest.class);
	private String host = CommonConstants.DEFAULT_HOST;
	private HttpRequestEx httpRequestForBossAPI;
	private HttpRequestEx httpRequest;	
	
	private String entityId = CommonConstants.entityId;
	private String seatCode = CommonConstants.seatCode;
	private String orderId = "";
	
	private String repeat = "false";	
	private String recommend = "false";
	private String signKey = CommonConstants.signKey;
	
	private String queueId = "";
	private String xtoken = "";
	private String customerRegisterId = CommonConstants.UID;
	
	private Gson gson = new Gson();	
	private BaseParamBean baseParam = new BaseParamBean();	
	
	private String xtokenOther = "";
	private String unionIdOther = CommonConstants.unionIDOther;
	private BaseParamBean baseParamOther = new BaseParamBean();	
	
	private Map<String, String> otherParameter = new HashMap<String, String>();
	private boolean https = CommonConstants.HTTPS;
	
	@BeforeClass(alwaysRun = true)
	public void setup(){
		
		logger.info("setup in OrderForNoPrePaySeatTest");
		httpRequest = new HttpRequestEx(host, https);
		httpRequestForBossAPI = new HttpRequestEx(CommonConstants.BOSS_API_HOST, https);
		
		// 获取 token
		xtoken = WechatBaseUtil.getToken(httpRequest, entityId, CommonConstants.unionID);
		xtokenOther = WechatBaseUtil.getToken(httpRequest, entityId, unionIdOther);
		
		// 构造参数		
		baseParam.setXtoken(xtoken);
		baseParam.setEntityId(entityId);
		baseParam.setSeatCode(seatCode);
		baseParam.setOrderId(orderId);
		
		baseParamOther.setXtoken(xtokenOther);
		baseParamOther.setEntityId(entityId);
		baseParamOther.setSeatCode(seatCode);
		baseParamOther.setOrderId(orderId);
		
		otherParameter.put("queueId", queueId);		
		otherParameter.put("repeat", repeat);
		otherParameter.put("recommend", recommend);
		
		otherParameter.put("signKey", signKey);
		otherParameter.put("customerRegisterId", customerRegisterId);		
		otherParameter.put("menuId", CommonConstants.menuId);
		
		
		// parameters for scanning seat code 
		otherParameter.put("people", "6");
		otherParameter.put("peopleCount", "6");
		otherParameter.put("memo", "");
		otherParameter.put("oldPeople", "");
		
		otherParameter.put("page", "");
		otherParameter.put("pageSize", "");
		otherParameter.put("isPreCart", "false");
		
		// 用于提交订单
		otherParameter.put("isPrePay", "false");
		otherParameter.put("cartTime", Long.toString(System.currentTimeMillis()));
		
		// scan shop code
		ShopNoPrePayUtil.scanCode(httpRequest, baseParam, otherParameter);
		
		// click to enter  (pre conditions)
		ShopNoPrePayUtil.clickToEnter(httpRequest, baseParam, otherParameter);
	}
	
	
	@AfterClass(alwaysRun = true)
	public void tearDown(){
		logger.info("tearDown in OrderForNoPrePayShopTest");
		httpRequest.ShutDown();
		httpRequestForBossAPI.ShutDown();
	}
	
	
	///////////////////////////////// 用户下单但未进行支付       ////////////////////////////////////////////////////////////
	
	@Test(description = "单个用户操作, 将普通菜或者套菜加入购物车, 之后扫桌码, 再次进行加普通菜或者套菜", dataProvider = "orderForOneCustomer",
			dataProviderClass = OrderForNoPrePayShopTestData.class, groups = {"module", "all"})
	public void orderForOneCustomer(String description, List<CartIncludeSuitForm> cartSuitListForShop, 
			List<CartIncludeSuitForm> cartSuitListForSeat, List<CartIncludeSuitForm> cartSuitListForTotal){
			
		logger.info(description);
		String people = "0";
		
		try{
		
			//////////////////////// 前置操作, 包括数据准备      //////////////////////////////////////
			
			// 收银非预付款下单需要 5min 超时失效, 该操作为了使得后续操作不受前次操作下单影响
			logger.info("等待之前的订单失效");
			Thread.sleep(5*60*1000);
			logger.info("等待动作已完成");
			
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			WechatBaseUtil.clearCart(httpRequest, baseParam);
			WechatBaseUtil.clearCartForShop(httpRequest, baseParam, otherParameter);
			
			
			
			
			/////////////////  扫店码加菜开始     ///////////////////////////////////////////////////
			/////////////////////////    业务操作         ////////////////////////////////////////
			
			// 模拟业务操作, 获取购物车内菜品数据
			ShopNoPrePayUtil.getUserCart(httpRequest, baseParam, otherParameter);
					
			
			//加菜到购物车
			for(CartIncludeSuitForm cartSuitForm : cartSuitListForShop){
				ShopNoPrePayUtil.modifyCart(httpRequest, baseParam, gson.toJson(cartSuitForm));
			}

			
			
			/////////////////////////    数据校验      ///////////////////////////////////////////

			boolean result1 = DataService.DataVerified(httpRequest, baseParam, otherParameter, people, cartSuitListForShop);
			Assert.assertEquals(result1, true);
			
			/////////////////  扫店码加菜结束     ///////////////////////////////////////////////////
			
			
						
			
			
			/////////////////  扫桌码加菜开始       ///////////////////////////////////////////////////
			
			// 构建 HTTP head 确保为同一个 session, 通过 entityId 以及 token 来确保为同一个 session
			Map<String, String> header = new HashMap<String, String>();
			header.put("Cookie", "entity_id=" + entityId + ";token=" + xtoken);
			
			// scan seat code
			SeatNoPrePayUtil.scanCode(httpRequest, baseParam, otherParameter, header);
			
			// click to enter  (pre conditions)
			SeatPrePayAPI.clickToEnter(httpRequest, baseParam, otherParameter);
			
			/////////////////////////    业务操作         ////////////////////////////////////////
			
			// 修改人数和备注信息
			WechatBaseUtil.modifyPeople(httpRequest, baseParam, otherParameter);
			
			// 添加必选商品
			WechatBaseUtil.addRequiredItem(httpRequest, baseParam, otherParameter);			
			
			//将菜加入购物车
			for(CartIncludeSuitForm cartSuitForm : cartSuitListForSeat){
				WechatBaseUtil.addDishToCarts(httpRequest,baseParam, gson.toJson(cartSuitForm));
			}
			
			
			/////////////////////////    数据校验      ///////////////////////////////////////////
			boolean result2 = DataService.DataVerified(httpRequest, baseParam, otherParameter.get("people"), cartSuitListForTotal);
			Assert.assertEquals(result2, true);

			/////////////////  扫桌码加菜结束      ///////////////////////////////////////////////////
			
			
			
			
			
			
			/////////////////  下单开始      ///////////////////////////////////////////////////			
			// 获取购物车数据
			Response cartData = WechatBaseUtil.listCartData(httpRequest, baseParam);
			JsonObject checkJsonObject = new JsonParser().parse(cartData.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(checkJsonObject.get("code").getAsInt(), 1, "获取购物车数据失败");
			String cartTime = checkJsonObject.get("data").getAsJsonObject().get("cartTime").getAsString();
			otherParameter.put("cartTime", cartTime);
			
			
			//提交订单
			Response orderResult = WechatBaseUtil.submitOrder(httpRequest, baseParam, otherParameter);
			JsonObject orderObject = new JsonParser().parse(orderResult.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(orderObject.get("code").getAsInt(), 1, "提交订单失败");
			
			
			// 获取订单列表
			Response responseFromGetOrder = WechatBaseUtil.getOrder(httpRequest, baseParam);
			String orderIdsFormJson = BeanProvider.getOrderIdsFormListJson(responseFromGetOrder);
			
			// 检查订单变化  (可能多人提交订单)
			Response orderChange = WechatBaseUtil.checkOrderChange(httpRequest, baseParam, orderIdsFormJson);
			JsonObject orderChangeObject = new JsonParser().parse(orderChange.getResponseStr()).getAsJsonObject();			
			
			if(1 == orderChangeObject.get("code").getAsInt())
				logger.info("订单未发生变化");
			else
				logger.info("订单发生了变化");
			
			/////////////////  下单结束      ///////////////////////////////////////////////////
			
			
			
		}catch(Exception e){
			
			logger.info(e.toString());
			
		}finally{
			
			////////////////////////后置操作         ////////////////////////////////////////////
						
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			WechatBaseUtil.clearCart(httpRequest, baseParam);
			WechatBaseUtil.clearCartForShop(httpRequest, baseParam, otherParameter);
			
		}
	
	}
	
	
	
	
	@Test(description = "多个用户操作, 将普通菜或者套菜加入购物车, 之后扫桌码, 再次进行加普通菜或者套菜", dataProvider = "orderForMultiCustomer",
			dataProviderClass = OrderForNoPrePayShopTestData.class, groups = {"module", "all"})
	public void orderForMultiCustomer(String description, List<CartIncludeSuitForm> cartSuitListForShop, List<CartIncludeSuitForm> cartSuitListForSeatOne,
			List<CartIncludeSuitForm> cartSuitListForSeatTwo, List<CartIncludeSuitForm> cartSuitListForTotal, boolean multi){
			
		logger.info(description);
		String people = "0";
		
		try{
		
			//////////////////////// 前置操作, 包括数据准备      //////////////////////////////////////
			
			// 收银非预付款下单需要 5min 超时失效, 该操作为了使得后续操作不受前次操作下单影响
			logger.info("等待之前的订单失效");
			Thread.sleep(5*60*1000);
			logger.info("等待动作已完成");
			
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			WechatBaseUtil.clearCart(httpRequest, baseParam);
			WechatBaseUtil.clearCartForShop(httpRequest, baseParam, otherParameter);
			
			
			
			
			/////////////////  扫店码加菜开始     ///////////////////////////////////////////////////
			/////////////////////////    业务操作         ////////////////////////////////////////
			
			// 模拟业务操作, 获取购物车内菜品数据
			ShopNoPrePayUtil.getUserCart(httpRequest, baseParam, otherParameter);
					
			
			//加菜到购物车
			for(CartIncludeSuitForm cartSuitForm : cartSuitListForShop){
				ShopNoPrePayUtil.modifyCart(httpRequest, baseParam, gson.toJson(cartSuitForm));
			}

			
			
			/////////////////////////    数据校验      ///////////////////////////////////////////

			boolean result1 = DataService.DataVerified(httpRequest, baseParam, otherParameter, people, cartSuitListForShop);
			Assert.assertEquals(result1, true);
			
			/////////////////  扫店码加菜结束     ///////////////////////////////////////////////////
			
			
						
			
			
			/////////////////  扫桌码加菜开始       ///////////////////////////////////////////////////
			
			// 构建 HTTP head 确保为同一个 session, 通过 entityId 以及 token 来确保为同一个 session
			Map<String, String> header = new HashMap<String, String>();
			header.put("Cookie", "entity_id=" + entityId + ";token=" + xtoken);
			
			// scan seat code
			SeatNoPrePayUtil.scanCode(httpRequest, baseParam, otherParameter, header);
			
			// click to enter  (pre conditions)
			SeatPrePayAPI.clickToEnter(httpRequest, baseParam, otherParameter);
			
			/////////////////////////    业务操作         ////////////////////////////////////////
			
			// 修改人数和备注信息
			WechatBaseUtil.modifyPeople(httpRequest, baseParam, otherParameter);
			
			// 添加必选商品
			WechatBaseUtil.addRequiredItem(httpRequest, baseParam, otherParameter);			
			
			
			//用户 A 将菜加入购物车
			for(CartIncludeSuitForm cartSuitForm : cartSuitListForSeatOne){
				WechatBaseUtil.addDishToCarts(httpRequest,baseParam, gson.toJson(cartSuitForm));
			}
			
			
			//客户 B 将菜加入购物车
			for(CartIncludeSuitForm cartSuitForm : cartSuitListForSeatTwo){
				WechatBaseUtil.addDishToCarts(httpRequest,baseParamOther, gson.toJson(cartSuitForm));
			}
			
			/////////////////////////    数据校验      ///////////////////////////////////////////
			boolean result2 = DataService.DataVerified(httpRequest, baseParam, otherParameter.get("people"), cartSuitListForTotal);
			Assert.assertEquals(result2, true);

			/////////////////  扫桌码加菜结束      ///////////////////////////////////////////////////
			
			
			
			
			
			
			/////////////////  下单开始      ///////////////////////////////////////////////////			
			// 获取用户 A 购物车数据
			Response cartData = WechatBaseUtil.listCartData(httpRequest, baseParam);
			JsonObject checkJsonObject = new JsonParser().parse(cartData.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(checkJsonObject.get("code").getAsInt(), 1, "获取购物车数据失败");
			String cartTime = checkJsonObject.get("data").getAsJsonObject().get("cartTime").getAsString();
			otherParameter.put("cartTime", cartTime);
			
			
			//提交订单
			Response orderResult = WechatBaseUtil.submitOrder(httpRequest, baseParam, otherParameter);
			JsonObject orderObject = new JsonParser().parse(orderResult.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(orderObject.get("code").getAsInt(), 1, "提交订单失败");
			
			if(multi){
				
				// 获取用户 B 购物车数据
				Response cartDataB = WechatBaseUtil.listCartData(httpRequest, baseParamOther);
				JsonObject checkJsonObjectB = new JsonParser().parse(cartDataB.getResponseStr()).getAsJsonObject();			
				Assert.assertEquals(checkJsonObjectB.get("code").getAsInt(), 1, "获取购物车数据失败");
				String cartTimeB = checkJsonObjectB.get("data").getAsJsonObject().get("cartTime").getAsString();
				otherParameter.put("cartTime", cartTimeB);
				
				// 第二个顾客提交订单
				Response orderResult2 = WechatBaseUtil.submitOrder(httpRequest, baseParamOther, otherParameter);
				JsonObject orderObject2 = new JsonParser().parse(orderResult2.getResponseStr()).getAsJsonObject();			
				Assert.assertEquals(orderObject2.get("code").getAsInt(), 0, "再次提交订单验证异常");
			}
			
			// 获取订单列表
			Response responseFromGetOrder = WechatBaseUtil.getOrder(httpRequest, baseParam);
			String orderIdsFormJson = BeanProvider.getOrderIdsFormListJson(responseFromGetOrder);
			
			// 检查订单变化  (可能多人提交订单)
			Response orderChange = WechatBaseUtil.checkOrderChange(httpRequest, baseParam, orderIdsFormJson);
			JsonObject orderChangeObject = new JsonParser().parse(orderChange.getResponseStr()).getAsJsonObject();			
			
			if(1 == orderChangeObject.get("code").getAsInt())
				logger.info("订单未发生变化");
			else
				logger.info("订单发生了变化");
			
			/////////////////  下单结束      ///////////////////////////////////////////////////
			
			
			
		}catch(Exception e){
			
			logger.info(e.toString());
			
		}finally{
			
			////////////////////////后置操作         ////////////////////////////////////////////
						
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			WechatBaseUtil.clearCart(httpRequest, baseParam);
			WechatBaseUtil.clearCartForShop(httpRequest, baseParam, otherParameter);
			
		}
	
	}
		

}
