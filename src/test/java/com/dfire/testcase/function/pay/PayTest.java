package com.dfire.testcase.function.pay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.dfire.testcase.bean.TradeBill;
import com.dfire.testcase.function.bean.BaseParamBean;
import com.dfire.testcase.function.bean.QueryTradeBillForm;
import com.dfire.testcase.function.util.api.SeatPrePayAPI;
import com.dfire.testcase.function.util.base.WechatBaseUtil;
import com.dfire.wechat.util.BeanProvider;
import com.dfire.wechat.util.CartIncludeSuitForm;
import com.dfire.wechat.util.CommonConstants;
import com.dfire.wechat.util.HttpRequestEx;
import com.dfire.wechat.util.PathForPost;
import com.dfire.wechat.util.Response;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class PayTest {
	
	private static final Logger logger = Logger.getLogger(PayTest.class);
	private String host = CommonConstants.DEFAULT_HOST;
	private HttpRequestEx httpRequest;	
	
	private String entityId = CommonConstants.entityId;
	private String seatCode = CommonConstants.seatCode;
	private String repeat = "false";
	
	private String recommend = "false";
	private String orderId = "";
	private String people = "6";
	
	private String menuId = CommonConstants.menuId;
	private String memo = "";
	private String oldPeople = "";
	
	private String page = "";
	private String pageSize = "";
	private String isPreCart = "false";
	
	private String peopleCount = "";
	private String cardId = "";
	private String orderIdForGetOrder = null;
	
	private BaseParamBean baseParam = new BaseParamBean();
	private Map<String, String> otherParameter = new HashMap<String, String>();
	
	private String xtoken = "";
	private Gson gson = new Gson();
	private boolean https = CommonConstants.HTTPS;
	
	@BeforeClass(alwaysRun = true)
	public void setup(){
		
		logger.info("setup in PayTest");
		httpRequest = new HttpRequestEx(host, https);
		
		// 获取 token
		xtoken = WechatBaseUtil.getToken(httpRequest, entityId, CommonConstants.unionID);
			
		baseParam.setXtoken(xtoken);
		baseParam.setEntityId(entityId);
		baseParam.setSeatCode(seatCode);
		baseParam.setOrderId(orderId);
		
		otherParameter.put("repeat", repeat);
		otherParameter.put("recommend", recommend);				
		otherParameter.put("menuId", CommonConstants.menuId);
		
		otherParameter.put("people", people);
		otherParameter.put("memo", memo);		
		otherParameter.put("oldPeople", oldPeople);
		
		otherParameter.put("page", page);
		otherParameter.put("pageSize", pageSize);
		otherParameter.put("isPreCart", isPreCart);
		
		// 用于提交订单
		otherParameter.put("isPrePay", "false");
		otherParameter.put("cartTime", Long.toString(System.currentTimeMillis()));
		
		// scan code (pre conditions)
		SeatPrePayAPI.clickToEnter(httpRequest, baseParam, otherParameter);
		
		// order dishes
		CartIncludeSuitForm cartSuit = BeanProvider.getCartSuit(1, 1, "", "", "水果沙拉", menuId, "");
		SeatPrePayAPI.startChooseDish(httpRequest, baseParam, otherParameter);
		SeatPrePayAPI.chooseDish(httpRequest, baseParam, otherParameter);
		WechatBaseUtil.addDishToCarts(httpRequest, baseParam, gson.toJson(cartSuit));
		
		// list carts(巡回请求)
		WechatBaseUtil.listCartData(httpRequest, baseParam);
		
		// add dish to cart and submit order,check order
		
		List<CartIncludeSuitForm> cartList = new ArrayList<CartIncludeSuitForm>();
		cartList.add(cartSuit);
		WechatBaseUtil.submitOrder(httpRequest, baseParam, otherParameter);
		
		// loop to get order
		Response responseFromGetOrder = WechatBaseUtil.getOrder(httpRequest, baseParam);
		JsonObject resp = new JsonParser().parse(responseFromGetOrder.getResponseStr()).getAsJsonObject();
		orderIdForGetOrder = resp.get("data").getAsJsonObject().get("waitingOrderVos").getAsJsonArray().get(0).
				getAsJsonObject().get("waitingOrderItems").getAsJsonObject().get("waitingOrderId").getAsString();
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown(){
		logger.info("tearDown in PayTest");
		httpRequest.ShutDown();
	}
	
	
	// 查询订单是否允许评价
	@Test(description = "judge orders Comments", dataProvider = "isCreateWaiterCommentsTest",
			dataProviderClass = PayTestData.class, groups = {"smoke", "all"})
	public void isCreateWaiterCommentsTest(String description, Map<String, String> query, int responseStatus, int resultCode, int jobStatus){
		
		try{
			
			Response response = httpRequest.get(PathForPost.getPathForPlayComments(), query); 
			Assert.assertEquals(response.getStatus(), responseStatus);
			
			if(responseStatus == 200){
				JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
				Assert.assertEquals(resp.get("code").getAsInt(), resultCode);
								
			}
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}	
	
	
	// 获取点赞活动开关
	@Test(description = "get activity switch", dataProvider = "getActivitySwitchTest",
			dataProviderClass = PayTestData.class, groups = {"smoke", "all"})
	public void getActivitySwitchTest(String description, Map<String, String> query, 
			List<String> menuIdList, int responseStatus, int resultCode, int jobStatus){
		
		try{
			
			// 填充获取的订单 Id
			query.put("order_id", orderIdForGetOrder);
			
			// play comments
			String order_idStr = "";			
			for(String orderId : menuIdList)
				order_idStr = order_idStr + orderId + ",";
			Map<String, String> otherParameter = new HashMap<String, String>();
			otherParameter.put("orderIds", order_idStr);
			WechatBaseUtil.createWaiterComments(httpRequest, baseParam, otherParameter);
			
			Response response = httpRequest.get(PathForPost.getPathForGetActivitySwitch(), query); 
			Assert.assertEquals(response.getStatus(), responseStatus);
			
			if(responseStatus == 200){
				JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
				Assert.assertEquals(resp.get("code").getAsInt(), resultCode);
								
			}
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}	
	
	

	/**
	 * 获取优惠方案及支付订单信息
	 * 该接口存疑
	 */
	@Test(description = "get trade bill", dataProvider = "getTradeBillTest",
			dataProviderClass = PayTestData.class, groups = {"smoke", "all"})
	public void getTradeBillTest(String description, Map<String, String> query, 
			Map<String, String> httpBody, int responseStatus, int resultCode, int jobStatus){
		
		try{
			
			// 数据准备
			BaseParamBean baseParam = new BaseParamBean();	
			baseParam.setEntityId(entityId);
			baseParam.setSeatCode(seatCode);
			baseParam.setOrderId(orderIdForGetOrder);
			
			Map<String, String> otherParameter = new HashMap<String, String>();
			otherParameter.put("orderIds", menuId);
			
			// play comments			
			WechatBaseUtil.createWaiterComments(httpRequest, baseParam, otherParameter);			
			
			// get activity switch
			
			WechatBaseUtil.getActivitySwitch(httpRequest, baseParam);
			
			// construct trade bill with form
			String tradeBillBody = BeanProvider.getHTTPBodyWithForm(httpBody);
			
//			// 填充获取的订单 Id
//			query.put("order_id", orderIdForGetOrder);
			Response response = httpRequest.post(PathForPost.getPathForGetTradeBill(), query, tradeBillBody); 
			Assert.assertEquals(response.getStatus(), responseStatus);
			
			if(responseStatus == 200){
				JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
				Assert.assertEquals(resp.get("code").getAsInt(), resultCode);								
			}
			
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}

	
	
	/**
	 * 预付款下单确认, 下单检测购物车与云购物车是否一致
	 */
	@Test(description = "confirm pre pay ", dataProvider = "confirmPrePayTest",
			dataProviderClass = PayTestData.class, groups = {"smoke", "all"})
	public void confirmPrePayTest(String description, Map<String, String> query, String cartSuitFormListJson, 
			TradeBill tradeBill, int responseStatus, int resultCode, int jobStatus){
		
		try{
					
			// 数据准备
			BaseParamBean baseParam = new BaseParamBean();	
			baseParam.setXtoken(xtoken);
			baseParam.setEntityId(entityId);
			baseParam.setSeatCode(seatCode);
			baseParam.setOrderId(orderIdForGetOrder);
			
			Map<String, String> otherParameter = new HashMap<String, String>();
			otherParameter.put("orderIds", menuId);
			
			// play comments
			WechatBaseUtil.createWaiterComments(httpRequest, baseParam, otherParameter);			
			
			// get activity switch
			WechatBaseUtil.getActivitySwitch(httpRequest, baseParam);
			
			// get trade bill
			QueryTradeBillForm queryTradeBillForm = new QueryTradeBillForm();
			queryTradeBillForm.setEntityId(baseParam.getEntityId());
			queryTradeBillForm.setOrderId(baseParam.getOrderId());
			queryTradeBillForm.setSeatCode(baseParam.getSeatCode());
			queryTradeBillForm.setSource(1);
			queryTradeBillForm.setCardId(baseParam.getCardId());
			queryTradeBillForm.setSign("");
			queryTradeBillForm.setPromotionId("");
			queryTradeBillForm.setPromotionCustomerId("");
			
			
			
			Map<String, Object> otherParameter1 = new HashMap<String, Object>();
			otherParameter1.put("flag", "false");
			otherParameter1.put("selected", "false");
			otherParameter1.put("query_bill_form", queryTradeBillForm);
			otherParameter1.put("cart_form", cartSuitFormListJson);
			otherParameter1.put("fromCart", "1");
			
			// 获取购物车数据
			Response cartData = WechatBaseUtil.listCartData(httpRequest, baseParam);
			JsonObject checkJsonObject = new JsonParser().parse(cartData.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(checkJsonObject.get("code").getAsInt(), 1, "获取购物车数据失败");
			String cartTime = checkJsonObject.get("data").getAsJsonObject().get("cartTime").getAsString();
			otherParameter1.put("cartTime", cartTime);
			
			WechatBaseUtil.getTradeBillV2(httpRequest, baseParam, otherParameter1);
			
			Response response = httpRequest.post(PathForPost.getPathForConfirmPrePay(), query, cartSuitFormListJson); 
			Assert.assertEquals(response.getStatus(), responseStatus);
			
			if(responseStatus == 200){
				JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
				Assert.assertEquals(resp.get("code").getAsInt(), resultCode);								
			}
			
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}
		
		


	/**
	 * 获取支付类型
	 * 该接口存疑
	 */
	@Test(description = "get pay type", dataProvider = "getPayTypeTest",
			dataProviderClass = PayTestData.class, groups = {"smoke", "all"})
	public void getPayTypeTest(String description, Map<String, String> query, String httpBodyForConfirmPrePay, 
			String cartSuitFormListJson, TradeBill tradeBill, int responseStatus, int resultCode, int jobStatus){
		
		try{
					
			// 数据准备
			BaseParamBean baseParam = new BaseParamBean();	
			baseParam.setXtoken(xtoken);
			baseParam.setEntityId(entityId);
			baseParam.setSeatCode(seatCode);
			baseParam.setOrderId(orderIdForGetOrder);
			
			Map<String, String> otherParameter = new HashMap<String, String>();
			otherParameter.put("orderIds", menuId);
			otherParameter.put("peopleCount", peopleCount);
			otherParameter.put("memo", memo);
			otherParameter.put("cardId", cardId);
			
			// play comments
			WechatBaseUtil.createWaiterComments(httpRequest, baseParam, otherParameter);			
			
			// get activity switch
			WechatBaseUtil.getActivitySwitch(httpRequest, baseParam);
			
			// get trade bill
			QueryTradeBillForm queryTradeBillForm = new QueryTradeBillForm();
			queryTradeBillForm.setEntityId(baseParam.getEntityId());
			queryTradeBillForm.setOrderId(baseParam.getOrderId());
			queryTradeBillForm.setSeatCode(baseParam.getSeatCode());
			queryTradeBillForm.setSource(1);
			queryTradeBillForm.setCardId(baseParam.getCardId());
			queryTradeBillForm.setSign("");
			queryTradeBillForm.setPromotionId("");
			queryTradeBillForm.setPromotionCustomerId("");
			
			Map<String, Object> otherParameter1 = new HashMap<String, Object>();
			otherParameter1.put("flag", "false");
			otherParameter1.put("selected", "false");
			otherParameter1.put("query_bill_form", queryTradeBillForm);
			otherParameter1.put("cart_form", cartSuitFormListJson);
			
			otherParameter1.put("from_cart", "1");
			
			// 获取购物车数据
			Response cartData = WechatBaseUtil.listCartData(httpRequest, baseParam);
			JsonObject checkJsonObject = new JsonParser().parse(cartData.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(checkJsonObject.get("code").getAsInt(), 1, "获取购物车数据失败");
			String cartTime = checkJsonObject.get("data").getAsJsonObject().get("cartTime").getAsString();
			otherParameter1.put("cartTime", cartTime);
			
			WechatBaseUtil.getTradeBillV2(httpRequest, baseParam, otherParameter1);
			
			// confirm prepay
			baseParam.setOrderId(orderId);
			WechatBaseUtil.confirmPrePay(httpRequest, cartSuitFormListJson, baseParam, otherParameter);
			
			// get pay type
			Response response = httpRequest.post(PathForPost.getPathForGetPayType(), query, httpBodyForConfirmPrePay); 
			Assert.assertEquals(response.getStatus(), responseStatus);
			
			if(responseStatus == 200){
				JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
				Assert.assertEquals(resp.get("code").getAsInt(), resultCode);								
			}
			
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}
	
	
}
