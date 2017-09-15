package com.dfire.qa.meal.service.impl;

import java.util.HashMap;
import java.util.Map;

import javassist.expr.NewArray;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.testng.Assert;

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
import com.dfire.qa.meal.utils.BeanProvider;
import com.dfire.qa.meal.utils.HTTPRequestUtil;
import com.dfire.qa.meal.utils.PathForHTTP;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Service
public class OrderServiceImpl implements IOrderService{

	@Resource
	private HostProperties hostProperties;
	
	@Resource
	private HTTPClientService httpClientService;
	
	@Resource
	private ICartService  cartService;

	private Gson gson = new Gson();
	
	
	@Override
	public Response submitOrder(BaseParamBean baseParamBean,Map<String, String> otherParameter, Environment env) throws Exception {
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.SUBMITORDER);
		
		Response response = null;
		
		Map<String, String> query = new HashMap<String, String>();
		
		query.put("xtoken", baseParamBean.getXtoken());		
		query.put("entity_id", baseParamBean.getEntityId());			
		query.put("seat_code", baseParamBean.getSeatCode());			
		query.put("order_id", baseParamBean.getOrderId());
		
		query.put("memo", otherParameter.get("memo"));
		query.put("people_count", otherParameter.get("people"));
		query.put("is_prepay", otherParameter.get("isPrePay"));
		query.put("cart_time", otherParameter.get("cartTime"));

		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("serverURL"), PathForHTTP.getPathForConfirmOrder(), query, protocol);
		response = httpClientService.doPostWithForm(url, null, "");
		
		return response;
	}
	
	

	@Override
	public Response getOrder(BaseParamBean baseParamBean, Environment env) throws Exception {
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.GETORDER);
		
		Response response = null;
			
		Map<String, String> query = new HashMap<String, String>();
		
		query.put("xtoken", baseParamBean.getXtoken());
		query.put("entity_id", baseParamBean.getEntityId());
		query.put("seat_code", baseParamBean.getSeatCode());	
		query.put("order_id", baseParamBean.getOrderId());
		
		// 计算 URL 并发送 GET 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("serverURL"), PathForHTTP.getPathForGetOrder(), query, protocol);
		response = httpClientService.doGet(url, null);
				
		return response;
	}
	
	
	

	@Override
	public Response checkOrderChange(BaseParamBean baseParamBean, String orderIdsFormJson, Environment env) throws Exception {
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.CHECKORDERCHANGE);
		
		Response response = null;		
			
		Map<String, String> query = new HashMap<String, String>();
		
		query.put("xtoken", baseParamBean.getXtoken());
		query.put("entity_id", baseParamBean.getEntityId());
		query.put("seat_code", baseParamBean.getSeatCode());	
		query.put("order_id", baseParamBean.getOrderId());
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("serverURL"), PathForHTTP.getPathForCheckOrderChange(), query, protocol);
		response = httpClientService.doPost(url, null, orderIdsFormJson);				
		
		return response;
	}



	@Override
	public Map<String, Response> placeOrder(BaseParamBean baseParamBean, Map<String, String> otherParameter, Environment env) throws Exception {
		
				
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.PLACEORDER);
		
		// 获取购物车数据
		Response cartData = cartService.listCartData(baseParamBean, env);
		JsonObject checkJsonObject = new JsonParser().parse(cartData.getResponseStr()).getAsJsonObject();	
		
		if(1 != checkJsonObject.get("code").getAsInt()){
//			throw new Exception(Constants.Error.EXPECTED + "1, " + Constants.Error.ACTUAL + 
//					checkJsonObject.get("code").getAsInt() + ", " + Constants.Order.PLACEORDER + Constants.Message.GETCARTDATAERROR);
			throw new Exception(gson.toJson(new Response(200, Constants.Message.GETCARTDATAERROR)));
		}

		String cartTime = checkJsonObject.get("data").getAsJsonObject().get("cartTime").getAsString();
		otherParameter.put("cartTime", cartTime);
		
		
		//提交订单
		Response orderResult = submitOrder(baseParamBean, otherParameter, env);
		JsonObject orderObject = new JsonParser().parse(orderResult.getResponseStr()).getAsJsonObject();	
		if(1 != orderObject.get("code").getAsInt()){
//			throw new Exception(Constants.Error.EXPECTED + "1, " + Constants.Error.ACTUAL + 
//					orderObject.get("code").getAsInt() + ", " + Constants.Order.PLACEORDER + Constants.Message.SUBMITORDERERROR);
			throw new Exception(gson.toJson(new Response(200, Constants.Message.SUBMITORDERERROR)));
		}
		
		
		
		// 获取订单列表
		Response responseFromGetOrder = getOrder(baseParamBean, env);
		String orderIdsFormJson = BeanProvider.getOrderIdsFormListJson(responseFromGetOrder);
		
		
		// 检查订单变化  (可能多人提交订单)
		Response orderChange = checkOrderChange(baseParamBean, orderIdsFormJson, env);
		JsonObject orderChangeObject = new JsonParser().parse(orderChange.getResponseStr()).getAsJsonObject();			
		
		
		if(1 == orderChangeObject.get("code").getAsInt())
			MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Order.ORDERNOTCHANGE);
		else
			MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Order.ORDERCHANGE);
		
		
		Map<String, Response> response = new HashMap<String, Response>();
		response.put("cartData", cartData);
		response.put("orderResult", orderResult);	
		
		response.put("responseFromGetOrder", responseFromGetOrder);	
		response.put("orderChange", orderChange);
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.PLACEORDERSUC);
		
		return response;
	}



	@Override
	public Response getShopOrderList(BaseParamBean baseParamBean, Integer page, Integer pageSize, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.GETSHOPORDERLIST);
		
		Response response = null;		
			
		Map<String, String> query = new HashMap<String, String>();
		
		query.put("xtoken", baseParamBean.getXtoken());
		query.put("entity_id", baseParamBean.getEntityId());
		query.put("page", Integer.toString(page));	
		query.put("page_size", Integer.toString(pageSize));
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("serverURL"), PathForHTTP.getPathForGetShopOrderList(), query, protocol);
		response = httpClientService.doGet(url, null);				
		
		return response;
	}
	
	
	

}
