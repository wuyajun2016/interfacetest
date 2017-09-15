package com.dfire.testcase.function.util.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.dfire.testcase.bean.OrderIdsForm;
import com.dfire.testcase.function.bean.BaseParamBean;
import com.dfire.testcase.function.bean.CardPayBean;
import com.dfire.testcase.function.bean.CreateSnapshotForm;
import com.dfire.testcase.function.bean.PayTypeRequestForm;
import com.dfire.testcase.function.bean.QueryTradeBillForm;
import com.dfire.testcase.function.util.base.SeatNoPrePayUtil;
import com.dfire.testcase.function.util.base.WechatBaseUtil;
import com.dfire.wechat.util.BeanProvider;
import com.dfire.wechat.util.CartIncludeSuitForm;
import com.dfire.wechat.util.HttpRequestEx;
import com.dfire.wechat.util.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class SeatNoPrePayAPI extends WechatBaseAPI{
	
	private static final Logger logger = Logger.getLogger(SeatNoPrePayAPI.class);
	
	public static boolean startChooseDish(HttpRequestEx httpRequest, BaseParamBean baseParam, Map<String, String> parMap){
		boolean flag = false;
		try{
			logger.info("进入点菜");
			
			// modify people and memo
			Response response1 = WechatBaseUtil.modifyPeople(httpRequest, baseParam,parMap);
			if (!isSuccess(response1)) {
				logger.error("修改人数失败");
				return flag;
			}
			 //add required item 扫桌码
//			Response response2 = WechatBaseUtil.addRequiredItem(httpRequest, baseParam, parMap);
//			if (!isSuccess(response2)) {
//				logger.error("添加必选商品失败");
//				return flag;
//			}
			Response response3 = WechatBaseUtil.listCartData(httpRequest, baseParam);
			if (!isSuccess(response3)) {
				logger.error("查询购物车数据失败");
				return flag;
			}
			// get recommend menus
			Response response4 = WechatBaseUtil.getRecommendMenus(httpRequest, baseParam, parMap);
			if (!isSuccess(response4)) {
				logger.error("查询推荐菜失败");
				return flag;
			}

			flag = true;
			
		}catch(Exception e){
			logger.info(e.toString());
		}
		return flag;
	}

	
	/**
	 * 收银审核以后刷新已下单的页面
	 * @param httpRequest
	 * @param entityId
	 * @param seatCode
	 * @param orderId
	 * @return
	 */
	public static Boolean refreshOrder(HttpRequestEx httpRequest,BaseParamBean baseParamBean) {
		
		Boolean flag = false;
		//获取订单
		Response response1 = WechatBaseUtil.getOrder(httpRequest,baseParamBean);
		if(!isSuccess(response1)){
			return flag;
		}
		//将返回数据放入公共参数
		logger.info("getOrder_data:"+JSONPath.read(response1.getResponseStr(), "$.data").toString());
		commonMap.put("getOrder_data",JSONPath.read(response1.getResponseStr(), "$.data"));
		
		String orderJson = SeatNoPrePayAPI.commonMap.get("getOrder_data").toString();
		
		List<String> orderIds = new ArrayList<String>();
		List<OrderIdsForm> orderIdsForms = new ArrayList<OrderIdsForm>();
		
		String orderId = (String) JSONPath.read(orderJson, "$.orderVos[0].orderId");
		Assert.assertNotNull(orderId,"订单ID为空！");
		baseParamBean.setOrderId(orderId.trim());
		orderIds.add(orderId);
		//从返回中提取参数
		extractRreshParam(orderJson, orderIds, orderIdsForms);
		
		Map<String, String> parMap = new HashMap<String, String>();
		parMap.put("orderIds", orderId);
		//查询订单是否允许评价
		Response response2 = WechatBaseUtil.createWaiterComments(httpRequest, baseParamBean, parMap);
		if(!isSuccess(response2)){
			return flag;
		}
		//获取点赞活动开关  接口弃用
//		Response response3 = WechatBaseUtil.getActivitySwitch(httpRequest, baseParamBean);
//		if(!isSuccess(response3)){
//			return flag;
//		}
			
		flag = true;
		return flag;
	}

	public static Boolean getPayDetail(HttpRequestEx httpRequest, BaseParamBean baseParamBean) {
		Boolean flag = false;
		Response response = SeatNoPrePayUtil.getBillInfo(httpRequest,baseParamBean);
		if (isSuccess(response)) {
			flag = true;
		}
		return flag;
	}

	public static Boolean getPayType(HttpRequestEx httpRequest,BaseParamBean baseParam,Map<String, String> parMap) {
		Boolean flag = false;
		JSONObject payOrder = (JSONObject) JSONPath.read(commonMap.get("tradebill_data").toString(), "$.pay_order");
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
		payTypeForm.setCreateSnapshotForm(createSnapshotForm);

		Response response1 = SeatNoPrePayUtil.getPayType(httpRequest, baseParam, payTypeForm);
		if (!isSuccess(response1)) {
			return flag;
		}
		logger.info("存储公共参数：payType_data："+ JSONPath.read(response1.getResponseStr(), "$.data").toString());
		commonMap.put("payType_data", JSONPath.read(response1.getResponseStr(), "$.data"));
		
		Map<String, String> otherParameter = new HashMap<String, String>();
		otherParameter.put("order_ids", "");
		Response response2 = SeatNoPrePayUtil.createWaiterComments(httpRequest, baseParam, otherParameter);
		if (!isSuccess(response2)) {
			return flag;
		}
		flag = true;
		return flag;
	}

//	public static Boolean cardPay(HttpRequestEx httpRequest, BaseParamBean baseParam) {
//		// TODO http://api.l.whereask.com/pay/v1/card_pay?xtoken=4491c3560597a8c89123929219856a39&t=1477032623066&g_entityId=99928345
//		CardPayBean cardPay = new CardPayBean();
//		Response response1 = SeatNoPrePayUtil.cardPay(httpRequest, baseParam, cardPay);
//		if (isSuccess(response1)) {
//			return true;
//		}
//		// TODO http://api.l.whereask.com/query_pay/bill?xtoken=4491c3560597a8c89123929219856a39&t=1477032623558&g_entityId=99928345&entity_id=99928345&out_trade_no=999283458SoRZRpPQWsafXoQS2OtxC&card_id=9992834557bd80c30157bda6626c000c&pay_type=4&snapshot_id=9992834531whCVBzQqW9YR2KkloXSq
//		return null;
//		
//	}
	
	public static Boolean cardPay(HttpRequestEx httpRequest,BaseParamBean baseParam,CardPayBean cardPayBean) {
		Boolean flag = false;
		String entityId = baseParam.getEntityId();
		
		JSONObject payOrder = (JSONObject) JSONPath.read(commonMap.get("tradebill_data").toString(), "$.pay_order");
		String snapshotId = (String) JSONPath.read(commonMap.get("payType_data").toString(), "$.snapshotId");
		String cardId = (String) JSONPath.read(commonMap.get("payType_data").toString(), "$.cards[0].id");
		
		cardPayBean.setEntity_id(entityId);
		cardPayBean.setNeed_fee(String.valueOf(payOrder.getIntValue("needFee")));
		cardPayBean.setOrder_id(baseParam.getOrderId());
		cardPayBean.setWaiting_order_id("");
		cardPayBean.setSnapshot_id(snapshotId);
		cardPayBean.setOrigin_fee(String.valueOf(payOrder.getIntValue("originPrice")));
		cardPayBean.setCard_id(cardId);
		Response response1 = SeatNoPrePayUtil.cardPay(httpRequest,baseParam,cardPayBean);
		if (!isSuccess(response1)) {
			return flag;
		}
		String outTradeNo = (String) JSONPath.read(response1.getResponseStr(), "$.data.outTradeNo");
		
		String payType = "4" ;//会员卡支付
		Map<String,String> parMap = new HashMap<String, String>();
		parMap.put("outTradeNo", outTradeNo);
		parMap.put("payType", payType);
		parMap.put("snapshotId", snapshotId);
		Response response2 = SeatNoPrePayUtil.queryPayBill(httpRequest,baseParam,parMap);
		if (!isSuccess(response2)) {
			return flag;
		}
		
		Response response3 = SeatNoPrePayUtil.queryShopTax(httpRequest,baseParam);
		if (!isSuccess(response3)) {
			return flag;
		}
		flag = true;
		return flag;
	}

	
	/**
	 * 去支付
	 * @param httpRequest
	 * @param entityId
	 * @param seatCode
	 * @param orderId
	 * @return
	 * @throws JsonProcessingException 
	 */
	public static Boolean intoBillPage(HttpRequestEx httpRequest, BaseParamBean baseParam, String flag, List<CartIncludeSuitForm> carts) 
			throws JsonProcessingException {
		
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
		
		Response response = SeatNoPrePayUtil.getTradeBill(httpRequest,baseParam,parMap);
		if (isSuccess(response)) {
			commonMap.put("tradebill_data", JSONPath.read(response.getResponseStr(), "$.data"));
			return true;
		}
		return false;
	}
	
	/**
	 * 非预付款扫桌码下单动作, 包括下单、获取订单列表、检查订单变化等
	 * @param httpRequest
	 * @param baseParam
	 * @param otherParameter
	 * @return
	 */
	public static boolean submitOrder(HttpRequestEx httpRequest, BaseParamBean baseParam, Map<String, String> otherParameter){
		
		boolean result = false;
		logger.info("非预款扫桌码下单开始");
		
		try{
			// 获取特定用户的购物车数据
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
			
			result = true;
			
		}catch(Exception e){
			
			logger.error(e.toString());
			
		}
		
		logger.info("非预款扫桌码下单结束");
		return result;
	}
	
	
}
