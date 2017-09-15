package com.dfire.qa.meal.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.testng.Assert;

import com.alibaba.fastjson.JSONObject;
import com.dfire.qa.meal.bean.BaseParamBean;
import com.dfire.qa.meal.bean.Response;
import com.dfire.qa.meal.constant.Constants;
import com.dfire.qa.meal.enums.Environment;
import com.dfire.qa.meal.httpclient.HTTPClientService;
import com.dfire.qa.meal.logger.LoggerMarkers;
import com.dfire.qa.meal.logger.MealLoggerFactory;
import com.dfire.qa.meal.props.HostProperties;
import com.dfire.qa.meal.service.ICartService;
import com.dfire.qa.meal.service.IOrderService;
import com.dfire.qa.meal.service.IPayService;
import com.dfire.qa.meal.service.IQRCodeService;
import com.dfire.qa.meal.utils.BeanProvider;
import com.dfire.qa.meal.utils.HTTPRequestUtil;
import com.dfire.qa.meal.utils.PathForHTTP;
import com.dfire.qa.meal.utils.PayUtil;
import com.dfire.qa.meal.vo.cart.CartIncludeSuitForm;
import com.dfire.qa.meal.vo.cash.CloudMessage;
import com.dfire.qa.meal.vo.pay.BillRequestForm;
import com.dfire.qa.meal.vo.pay.CardPayBeanV2;
import com.dfire.qa.meal.vo.pay.CreateSnapshotForm;
import com.dfire.qa.meal.vo.pay.PayTypeRequestForm;
import com.dfire.qa.meal.vo.pay.QueryTradeBillForm;
import com.dfire.soa.consumer.vo.CardPayVo;
import com.dfire.tp.client.bill.request.BillNormalRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Service
public class PayServiceImpl implements IPayService{

	@Resource
	private HostProperties hostProperties;
	
	@Resource
	private HTTPClientService httpClientService;
	
	@Resource
	private IQRCodeService qrCodeService;
	
	@Resource
	private ICartService  cartService;
	
	@Resource
	private IOrderService orderService;
	
	private Gson gson = new Gson();
	
	private ObjectMapper mapper = new ObjectMapper();
	
	@Override
	public Response getActivitySwitch(BaseParamBean baseParamBean, Environment env) throws Exception {
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.ACTIVITYSWITCH);
		
		Response response = null;	
		
		Map<String, String> query = new HashMap<String, String>();
		query.put("xtoken", baseParamBean.getXtoken());
		query.put("entity_id", baseParamBean.getEntityId());
		
		query.put("seat_code", baseParamBean.getSeatCode());
		query.put("order_id", baseParamBean.getOrderId());	
		
		// 计算 URL 并发送 GET 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("serverURL"), PathForHTTP.getPathForGetActivitySwitch(), query, protocol);
		response = httpClientService.doGet(url, null);
		
		return response;
	}

	
	
	@Override
	public Response getOrderIfComments(BaseParamBean baseParamBean, String order, Environment env) throws Exception{

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.ACTIVITYSWITCH);
		
		Response response = null;	
		
		Map<String, String> query = new HashMap<String, String>();
		query.put("xtoken", baseParamBean.getXtoken());
		query.put("entity_id", baseParamBean.getEntityId());
		query.put("order_ids[]", order);	
		
		// 计算 URL 并发送 GET 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("serverURL"), PathForHTTP.getPathForPlayComments(), query, protocol);
		response = httpClientService.doGet(url, null);
		
		return response;
	}
	
	
	
	@Override
	public Response getNormalTradeBill(BaseParamBean baseParamBean, BillNormalRequest billNormalRequest, Environment env) throws Exception{
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.ACTIVITYSWITCH);
		
		Response response = null;	
		
		Map<String, String> query = new HashMap<String, String>();
		query.put("xtoken", baseParamBean.getXtoken());
		query.put("entity_id", baseParamBean.getEntityId());
		query.put("seat_code", baseParamBean.getSeatCode());	
		
		// 计算 URL 并发送 GET 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("serverURL"), PathForHTTP.getPathForGetNormalTradeBill(), query, protocol);
		response = httpClientService.doPost(url, null, gson.toJson(billNormalRequest));
		
		return response;
	}
	
	
	
	@Override
	public Response getTradeBillV2(BaseParamBean baseParamBean,  Map<String, Object> otherParameter, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.TRADEBILL);
		
		Response response = null;		
					
		Map<String, String> query = new HashMap<String, String>();
		query.put("xtoken", baseParamBean.getXtoken());
		query.put("entity_id", baseParamBean.getEntityId());
		query.put("seat_code", baseParamBean.getSeatCode());

		Map<String,Object> httpBody = new HashMap<String, Object>();
		httpBody.put("flag", otherParameter.get("flag"));
		httpBody.put("selected", otherParameter.get("selected"));
		httpBody.put("query_bill_form", otherParameter.get("query_bill_form"));
		httpBody.put("cardSelected", otherParameter.get("cardSelected"));  
		httpBody.put("firstLoading", otherParameter.get("firstLoading"));   
			
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		
		// hostProperties.getHostProperties(env).get("serverURL")
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("serverURL"), PathForHTTP.getPathForGetTradeBillV2(), query, protocol);
		response = httpClientService.doPost(url, null, mapper.writeValueAsString(httpBody));

					
		return response;
		
	}

	
		
	
	@Override
	public Response getPayType(BaseParamBean baseParamBean, PayTypeRequestForm payTypeForm, Environment env) throws Exception {

		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.PAYTYPE);
		
		Response response = null;
		
		Map<String, String> query = new HashMap<String, String>();
		query.put("xtoken", baseParamBean.getXtoken());
		query.put("entity_id", baseParamBean.getEntityId());
		query.put("seat_code", baseParamBean.getSeatCode());
		
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("serverURL"), PathForHTTP.getPathForGetPayType(), query, protocol);
		response = httpClientService.doPost(url, null, mapper.writeValueAsString(payTypeForm));	
		
		return response;
		
	}

	
	
	
	@Override
	@Deprecated
	public Response cardPayV2(BaseParamBean baseParamBean, CardPayBeanV2 cardPayBean, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.CARDPAY);
		
		Response response = null;
		
		Map<String, String> query = new HashMap<String, String>();
		query.put("xtoken", baseParamBean.getXtoken());
		
		StringBuilder httpContent = new StringBuilder();
		httpContent.append("entity_id=").append(cardPayBean.getEntity_id()).append("&card_id=").append(cardPayBean.getCard_id()).
		append("&order_id=").append(cardPayBean.getOrder_id()).append("&waiting_order_id=").append(cardPayBean.getWaiting_order_id()).
		append("&need_fee=").append(cardPayBean.getNeed_fee()).append("&origin_fee=").append(cardPayBean.getOrigin_fee()).
		append("&snapshot_id=").append(cardPayBean.getSnapshot_id());
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("serverURL"), PathForHTTP.getPathForCardPay(), query, protocol);
		response = httpClientService.doPostWithForm(url, null, mapper.writeValueAsString(httpContent));	
					
		return response;
		
	}



	@Override
	public Map<String, Response> refreshToGetPayDetail(BaseParamBean baseParamBean, Map<String, String> otherParameter, Environment env) throws Exception {

		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Pay.REFRESHTOGETPAYDETAIL);
		
   	
		// 获取订单列表
		Response getOrder = orderService.getOrder(baseParamBean, env);	
		String orderIdsFormJson = BeanProvider.getOrderIdsFormListJson(getOrder);
		JsonObject getOrderJsonObject = new JsonParser().parse(getOrder.getResponseStr()).getAsJsonObject();	
		
		if(1 != getOrderJsonObject.get("code").getAsInt()){
			throw new Exception(Constants.Error.EXPECTED + "1, " + Constants.Error.ACTUAL + 
					getOrderJsonObject.get("code").getAsInt() + ", " + Constants.Order.REFRESHORDER + Constants.Order.GETORDERLISTFAIL);
		}
		

		// 获取点赞活动开关[目前暂时不支持点赞]
		Response getActivitySwitch = getActivitySwitch(baseParamBean, env);			
		JsonObject getActivitySwitchJsonObject = new JsonParser().parse(getActivitySwitch.getResponseStr()).getAsJsonObject();			
//			Assert.assertEquals(getActivitySwitchJsonObject.get("code").getAsInt(), 1, "获取订单列表失败");
		
		
//			// 查询订单是否允许评价, 该接口需要调试？？？
//			Response getOrderComments = WechatBaseUtil.getOrderComments(httpRequest, baseParamBean, otherParameter);			
//			JsonObject getOrderCommentsJsonObject = new JsonParser().parse(getOrderComments.getResponseStr()).getAsJsonObject();			
//			Assert.assertEquals(getOrderCommentsJsonObject.get("code").getAsInt(), 1, "查询订单是否允许评价失败");
		
		
		// 检查订单变化  (可能多人提交订单)
		Response orderChange = orderService.checkOrderChange(baseParamBean, orderIdsFormJson, env);
		JsonObject orderChangeObject = new JsonParser().parse(orderChange.getResponseStr()).getAsJsonObject();			
		
		
		if(1 == orderChangeObject.get("code").getAsInt())
			MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Order.ORDERNOTCHANGE);
		else
			MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Order.ORDERCHANGE);
		
		
		Map<String, Response> response = new HashMap<String, Response>();
		response.put("getOrder", getOrder);
		response.put("getActivitySwitch", getActivitySwitch);	
		
//			response.put("getOrderComments", getOrderComments);	
		response.put("orderChange", orderChange);
					
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Pay.REFRESHSUC);
		
		return response;
		
	}
	
	



	@Override
	public Map<String, Response> payForOrder(BaseParamBean baseParamBean, List<CartIncludeSuitForm> cartSuitList, Environment env) throws Exception {

		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Pay.PAYPROCEDURE);				
			
		/////////////////////////////  获取账单        ////////////////////////////////////////////////////////
		QueryTradeBillForm queryTradeBillForm = new QueryTradeBillForm();
		queryTradeBillForm.setEntityId(baseParamBean.getEntityId());
		queryTradeBillForm.setOrderId(baseParamBean.getOrderId());
		queryTradeBillForm.setSeatCode(baseParamBean.getSeatCode());
		queryTradeBillForm.setSource(1);
		queryTradeBillForm.setCardId("");
		queryTradeBillForm.setSign("");
		
//		// 后续可以采用 类 的方式进行组织
//		BillRequestForm billRequestForm = new BillRequestForm();
//		billRequestForm.setQueryTradeBillForm(queryTradeBillForm);   // 具体账单参数
//		billRequestForm.setSelected(false);                          // 是否选中赞助礼品
//		billRequestForm.setFlag(false);                              // 重新拉取账单, 如果修改为 true，会在 Dpush 端产生一个消息，相当于重新拉取账单，否则默认已经拉取了账单。就不会在 Dpush 端产生消息
//		billRequestForm.setCardSelected(true);                       // 是否选中会员卡支付
//		billRequestForm.setFirstLoading(1);                          // 1: 第一次进入账单   2：非第一次进入账单
		
		/**
		 * 之前版本： 将 flag 字段设置为 true 时会重新拉取账单, 重新拉取账单 , 如果修改为 true，会在 Dpush 端产生一个消息，相当于重新拉取账单，否则默认已经拉取了账单。就不会在 Dpush 端产生消息
		 * 现在版本：现在为了区分  本地收银  与  云收银  增加该字段 firstLoading， 该字段为 1 时仍然会在 Dpush 产生消息，相当于再次拉取账单,为了避免再次拉取账单需要将该字段设置为非 1，另外需要将该店放入灰度发布中
		 */
		Map<String, Object> parMap = new HashMap<String, Object>();
		parMap.put("query_bill_form", queryTradeBillForm);   
		parMap.put("selected", "false");    
		parMap.put("flag", "false");	       
		parMap.put("cardSelected", "true");  
		parMap.put("firstLoading", 2);   
		
		
		Response getTradeBill = getTradeBillV2(baseParamBean, parMap, env);
		JsonObject getTradeBillJsonObject = new JsonParser().parse(getTradeBill.getResponseStr()).getAsJsonObject();	
		
		if(1 != getTradeBillJsonObject.get("code").getAsInt()){
//			throw new Exception(Constants.Error.EXPECTED + "1, " + Constants.Error.ACTUAL + 
//					getTradeBillJsonObject.get("code").getAsInt() + ", " + Constants.Pay.PAYACTION +  Constants.Pay.GETTRADEBILLERROR);
			throw new Exception(gson.toJson(new Response(200, Constants.Pay.GETTRADEBILLERROR)));
		}
		
		
		JsonObject data = getTradeBillJsonObject.get("data").getAsJsonObject();
		JsonObject payOrder = data.get("pay_order").getAsJsonObject();
		JsonArray cardPayArray = data.get("cardPay").getAsJsonArray();
		
		// 获取 CardPayVo list 用于后续获取支付类型接口
		List<CardPayVo> cardPayList = PayUtil.getCardPayVoFromBill(getTradeBill);
		

		
		//////////////////////////     获取支付类型并进行支付        ////////////////////////////////////////////////////////
		// 获取初始化数据
		Response shopState = qrCodeService.getInitDataForShop(baseParamBean, env);
		JsonObject shopStateJsonObject = new JsonParser().parse(shopState.getResponseStr()).getAsJsonObject();				
		Assert.assertEquals(shopStateJsonObject.get("code").getAsInt(), 1, "获取 shopId 失败");
		
		JsonObject shopObject = shopStateJsonObject.get("data").getAsJsonObject().get("shop").getAsJsonObject();
		String shopId = shopObject.get("shop_id").getAsString();
		
		
		PayTypeRequestForm payTypeForm = new PayTypeRequestForm();
		
		CreateSnapshotForm createSnapshotForm = new CreateSnapshotForm();
		createSnapshotForm.setSource(1);
		createSnapshotForm.setShopId(shopId);
		
		createSnapshotForm.setSeatCode(baseParamBean.getSeatCode());
		createSnapshotForm.setOrderId(baseParamBean.getOrderId());
		createSnapshotForm.setEntityId(baseParamBean.getEntityId());
		
		createSnapshotForm.setTotalFee(payOrder.get("fee").getAsInt());   // /100
		createSnapshotForm.setOriginFee(payOrder.get("originPrice").getAsInt());  // /100
		createSnapshotForm.setServiceFee(payOrder.get("originServiceCharge").getAsInt());
		
		createSnapshotForm.setDiscountFee(payOrder.get("discountFee").getAsInt());   // /100
		createSnapshotForm.setNeedFee(payOrder.get("needFee").getAsInt());  // /100, 如果在 ServiceBill 消费账单信息阶段未上传账单信息那么拉取账单后该字段为 0, 该动作为真实动作
//		createSnapshotForm.setNeedFee(payOrder.get("fee").getAsInt());  // /100, 如果在 ServiceBill 消费账单信息阶段未上传账单信息那么拉取账单后该字段为 0,此处采取简略做法直接进行设置,该动作为简略动作
		createSnapshotForm.setPaidFee(payOrder.get("paidFee").getAsInt());
		
		createSnapshotForm.setDeductFee(payOrder.get("deductFee").getAsInt());
		createSnapshotForm.setCsrfToken(data.get("csrfToken").getAsString());
		
		payTypeForm.setCreateSnapshotForm(createSnapshotForm);
		payTypeForm.setCardPay(cardPayList);
		
		Response getPayType = getPayType(baseParamBean, payTypeForm, env);
		JsonObject getPayTypeJsonObject = new JsonParser().parse(getPayType.getResponseStr()).getAsJsonObject();
		
		if(1 != getPayTypeJsonObject.get("code").getAsInt()){
			
			throw new Exception(gson.toJson(new Response(200,  Constants.Pay.GETPAYTYPEERROR)));
		}
		
		
//		JsonObject dataForPayType = getPayTypeJsonObject.get("data").getAsJsonObject();
		
		
		
		//////////////////////////////  会员卡支付        //////////////////////////////////////////////////
		
//		CardPayBeanV2 cardPayBean = new CardPayBeanV2();
//		
//		cardPayBean.setEntity_id(baseParamBean.getEntityId());
//		cardPayBean.setNeed_fee(payOrder.get("fee").getAsInt());
//		
//		cardPayBean.setOrder_id(payOrder.get("orderId").getAsString());			
//		cardPayBean.setWaiting_order_id("");
//		
//		cardPayBean.setSnapshot_id(dataForPayType.get("snapshotId").getAsString());
//		cardPayBean.setOrigin_fee(payOrder.get("originPrice").getAsInt());			
//		cardPayBean.setCard_id(cardPayArray.get(0).getAsJsonObject().get("cardId").getAsString());
//		
////			HttpRequestEx httpRequesttest = new HttpRequestEx("10.1.66.81:8080", false);
//		Response cardPay = cardPayV2(baseParamBean, cardPayBean, env);
//		JsonObject cardPayJsonObject = new JsonParser().parse(cardPay.getResponseStr()).getAsJsonObject();			
//		Assert.assertEquals(cardPayJsonObject.get("code").getAsInt(), 1, "会员卡支付失败");
//		
//		
		//////////////////////////   数据封装       ////////////////////////////////////////////////
		Map<String, Response> response = new HashMap<String, Response>();
		response.put("getTradeBill", getTradeBill);
		response.put("getPayType", getPayType);									
					
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Pay.PAYPROCEDURESUC);	
		
		return response;
		
	}
	
	
	
	
	
	
	

}
