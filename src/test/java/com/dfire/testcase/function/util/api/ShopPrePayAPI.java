package com.dfire.testcase.function.util.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.dfire.testcase.function.bean.BaseParamBean;
import com.dfire.testcase.function.bean.CreateSnapshotForm;
import com.dfire.testcase.function.bean.PayTypeRequestForm;
import com.dfire.testcase.function.bean.QueryTradeBillForm;
import com.dfire.testcase.function.util.base.SeatPrePayUtil;
import com.dfire.testcase.function.util.base.ShopPrePayUtil;
import com.dfire.testcase.function.util.base.WechatBaseUtil;
import com.dfire.wechat.util.CartIncludeSuitForm;
import com.dfire.wechat.util.HttpRequestEx;
import com.dfire.wechat.util.Response;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ShopPrePayAPI extends WechatBaseAPI{
	private static final Logger logger = Logger.getLogger(ShopPrePayAPI.class);
	private static Gson gson = new Gson();
	
	/**
	 * 店码非预付款 “点击进入”
	 * @param httpRequest
	 * @param entityId
	 * @param seatCode
	 * @param repeat
	 * @param recommend
	 * @param orderId
	 * @return
	 */
	public static boolean clickToEnter(HttpRequestEx httpRequest, BaseParamBean baseParam, Map<String, String> parMap){
		try{
			Boolean flag = false;
			logger.info("业务流程：店码预付款点击进入");	
			Response response1 = ShopPrePayUtil.getMenus(httpRequest, baseParam, parMap);
			if (!isSuccess(response1)) {
				return flag;
			}
			logger.info("存储公共参数:"+JSONPath.read(response1.getResponseStr(), "$.data").toString());
			commonMap.put("listMenu_data", JSONPath.read(response1.getResponseStr(), "$.data"));
			
			Response response2 = ShopPrePayUtil.showPrivilegeTitle(httpRequest, baseParam);
			if (!isSuccess(response2)) {
				return flag;
			}			
			String queueId = "";
			Response response3 = ShopPrePayUtil.getUserCart(httpRequest, baseParam, parMap);	
			if (!isSuccess(response3)) {
				logger.error("获取用户购物车的数据接口（不含菜单列表）失败");
				return flag;
			}
			flag = true ;
			return flag;
		}catch(Exception e){
			logger.info(e.toString());
		}
		return false;
	}
	
	/**
	 * 点击“开始点菜” 进入菜单
	 * @param httpRequest
	 * @param entityId
	 * @param seatCode
	 * @param people
	 * @param memo
	 * @param orderId
	 * @param oldPeople
	 * @param page
	 * @param pageSize
	 * @param isPreCart
	 * @return
	 */
	public static boolean startChooseDish(HttpRequestEx httpRequest, BaseParamBean baseParam,Map<String, String> parMap){
		boolean flag = false;
		try{
			logger.info("进入点菜");
			
			// modify people and memo
			Response response1 = ShopPrePayUtil.preModifyPeopleAndMemo(httpRequest, baseParam,parMap);
			if (!isSuccess(response1)) {
				return flag;
			}
			// get user cart
			String queueId = "";
			Response response2 = ShopPrePayUtil.getUserCart(httpRequest, baseParam, parMap);
			if (!isSuccess(response2)) {
				return flag;
			}
			// get recommend menus
			Response response3 = ShopPrePayUtil.getRecommendMenus(httpRequest, baseParam,parMap);
			if (!isSuccess(response3)) {
				return flag;
			}
			flag = true;
			return flag;
		}catch(Exception e){
			logger.info(e.toString());
		}
		return false;
	}
	
	public static Boolean addDishToCarts(HttpRequestEx httpRequest, BaseParamBean baseParam,CartIncludeSuitForm cartIncludeSuitForm) {
		logger.info("加入购物车！");
		Boolean flag = false;
		
		// add dish to the carts
		Response response = ShopPrePayUtil.modifyCart(httpRequest, baseParam, gson.toJson(cartIncludeSuitForm));
		if (!isSuccess(response)) {
			return flag;
		}
		flag = true;
		return flag;
	}

	public static Boolean intoCart(HttpRequestEx httpRequest,BaseParamBean baseParam, Map<String, String> intoCartMap) {
		Boolean flag = false ;
		//购物车页面展示可用优惠信息
		Response response1 = ShopPrePayUtil.listCartsPrivilege(httpRequest, baseParam, intoCartMap);
		if (!isSuccess(response1)) {
			return flag;
		}		
		
		//获取推荐菜
		Response response2 = ShopPrePayUtil.getRecommendMenus(httpRequest, baseParam, intoCartMap);
		if (!isSuccess(response2)) {
			return flag;
		}
		flag = true;
		return flag;
	}


	/**
	 * 进入店铺主页
	 * @param httpRequest
	 * @param entityId
	 * @param seatCode
	 * @param repeat
	 * @param recommend
	 * @param orderId
	 * @return
	 */
//	public static Boolean intoHomePage(HttpRequestEx httpRequest,
//			String entityId, String seatCode, String repeat, String recommend,
//			String orderId) {
//		Boolean flag = false;
//		//进入店铺home
//		//TODO /carts/v1/count?xtoken=4491c3560597a8c89123929219856a39&t=1476863895769&g_entityId=99928345&entity_id=99928345&seat_code=B1&order_id= HTTP/1.1
//		Response response = ShopUtil.getCartCount(httpRequest, entityId, seatCode, orderId);
//		if (!isSuccess(response)) {
//			return flag;
//		}
//		return flag;
//	}


	public static Boolean intoBillPage(HttpRequestEx httpRequest, BaseParamBean baseParam,String flag,List<CartIncludeSuitForm> cartList) {
		QueryTradeBillForm queryTradeBillForm = new QueryTradeBillForm();
		queryTradeBillForm.setEntityId(baseParam.getEntityId());
		queryTradeBillForm.setOrderId(baseParam.getOrderId());
		queryTradeBillForm.setSeatCode(baseParam.getSeatCode());
		queryTradeBillForm.setSource(1);
		queryTradeBillForm.setCardId(baseParam.getCardId());
		queryTradeBillForm.setSign("");
		queryTradeBillForm.setPromotionId("");
		queryTradeBillForm.setPromotionCustomerId("");
		
		Map<String, Object> parMap = new HashMap<String, Object>();
		parMap.put("query_bill_form",queryTradeBillForm);
		parMap.put("selected", "true");
		parMap.put("flag", flag);
		parMap.put("cart_form", cartList);

		parMap.put("from_cart", "1");
		
		// 获取购物车数据
//		Response cartData = WechatBaseUtil.listCartData(httpRequest, baseParam);
//		JsonObject checkJsonObject = new JsonParser().parse(cartData.getResponseStr()).getAsJsonObject();			
//		Assert.assertEquals(checkJsonObject.get("code").getAsInt(), 1, "获取购物车数据失败");
//		String cartTime = checkJsonObject.get("data").getAsJsonObject().get("cartTime").getAsString();
		parMap.put("cartTime", "");
		
		Response response = WechatBaseUtil.getTradeBillV2(httpRequest,baseParam,parMap);
		if (isSuccess(response)) {
			logger.info("存储公共参数tradebill_data:"+JSONPath.read(response.getResponseStr(), "$.data"));
			ShopPrePayAPI.commonMap.put("tradebill_data", JSONPath.read(response.getResponseStr(), "$.data"));
			return true;
		}
		return false;
	}
	
	
	public static Boolean getPayType(HttpRequestEx httpRequest,BaseParamBean baseParam,Map<String, String> parMap,List<CartIncludeSuitForm> cartList) {
		Boolean flag = false;
		JSONObject payOrder = (JSONObject) JSONPath.read(commonMap.get("tradebill_data").toString(), "$.pay_order");
		
		Response response = SeatPrePayUtil.confirmPrePay(httpRequest, gson.toJson(cartList),baseParam, parMap);
		if (!isSuccess(response)) {
			return flag;
		}
		logger.info("存储公共参数：confirmPre_data："+ JSONPath.read(response.getResponseStr(), "$.data").toString());
		commonMap.put("confirmPre_data", JSONPath.read(response.getResponseStr(), "$.data"));
		
		String waitingOrderId = (String) JSONPath.read(response.getResponseStr().toString(), "$.data.waitingOrder.id");
		String shopId = (String) JSONPath.read(commonMap.get("getState_data").toString(), "$.shop.shop_id");
		String csrfToken = (String) JSONPath.read(commonMap.get("tradebill_data").toString(), "$.csrfToken");

		PayTypeRequestForm payTypeForm = new PayTypeRequestForm();
		CreateSnapshotForm createSnapshotForm = new CreateSnapshotForm();
		createSnapshotForm.setSource(1);
		createSnapshotForm.setShopId(shopId);
		createSnapshotForm.setSeatCode(baseParam.getSeatCode());
		createSnapshotForm.setOrderId(baseParam.getOrderId());
		createSnapshotForm.setEntityId(baseParam.getEntityId());
		createSnapshotForm.setTotalFee(payOrder.getIntValue("fee"));
		createSnapshotForm.setOriginFee(payOrder.getIntValue("originPrice"));
		createSnapshotForm.setServiceFee(payOrder.getIntValue("originServiceCharge"));
		createSnapshotForm.setDiscountFee(payOrder.getIntValue("discountFee"));
		createSnapshotForm.setNeedFee(payOrder.getIntValue("needFee"));
		createSnapshotForm.setPaidFee(payOrder.getIntValue("paidFee"));
		createSnapshotForm.setDeductFee(payOrder.getIntValue("deductFee"));
		createSnapshotForm.setCsrfToken(csrfToken);
		createSnapshotForm.setWaitingOrderId(waitingOrderId);
		payTypeForm.setCreateSnapshotForm(createSnapshotForm);
		Response response2 = SeatPrePayUtil.getPayType(httpRequest,baseParam, payTypeForm);
		if (!isSuccess(response2)) {
			return flag;
		}
		logger.info("存储公共参数：payType_data："+ JSONPath.read(response2.getResponseStr(), "$.data").toString());
		commonMap.put("payType_data", JSONPath.read(response2.getResponseStr(), "$.data"));
		
		flag = true;
		return flag;
	}
}
