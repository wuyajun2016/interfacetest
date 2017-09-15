package com.dfire.testcase.function.util.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.dfire.sdk.sign.SignGenerator;
import com.dfire.wechat.util.CommonConstants;
import com.dfire.wechat.util.DateUtil;
import com.dfire.wechat.util.HttpRequestEx;
import com.dfire.wechat.util.Response;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CashUtil {

	private static final Logger logger = Logger.getLogger(CashUtil.class);

	/**
	 * 从dpush获取消息
	 * @param httpRequest
	 * @param parMap
	 * @param sessionId 
	 * @return
	 */
	public static Response getMessageList(HttpRequestEx httpRequest, Map<String, String> parMap, String sessionId) {
		Response response = null;
		try {
			logger.info("从dpush获取消息");
			Map<String, String> query = new HashMap<String, String>();
			setBuildParam(parMap, query);
			
			Map<String, String> param = new HashMap<String, String>();
			param.put("device_id", parMap.get("device_id"));
			param.put("status", parMap.get("status"));
			param.put("start_time", parMap.get("start_time"));
			param.put("page_index", parMap.get("page_index"));
			param.put("entity_id", parMap.get("entity_id"));
			param.put("timestamp", parMap.get("timestamp"));
			param.put("sign", parMap.get("sign"));
			
			List<String> pathList = new ArrayList<String>();
			pathList.add("dpush-api/dpush/v1/get_message_list");
			
			Map<String, String> headerMap = new HashMap<String, String>();
			logger.info("sessionId:"+sessionId);
			headerMap.put("sessionId", sessionId);
			response = httpRequest.postWithHeaders(pathList,headerMap,query, param);
			
			if (Response.isSuccess(response)) {
				
//				if (null==JSONPath.read(response.getResponseStr(),"$.data")) {
//					Thread.sleep(1000);//休息1s再试
//					response = httpRequest.postWithHeaders(pathList,headerMap,query, param);
//				}
				 
				JsonObject messageFromDpushJsonObject = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();
				int number = messageFromDpushJsonObject.get("data").getAsJsonArray().size();
				
				int loop = 10;
				for (; number < 1 && loop > 0 ; loop--) {
					Thread.sleep(5000);//休息1s再试
					response = httpRequest.postWithHeaders(pathList,headerMap,query, param);
				}
				
			}
			
			
			
			return response;
		} catch (Exception e) {
			logger.info(e.toString());
		}
		return response;
	}

	/**
	 * 获取waitingorder
	 * @param httpRequest
	 * @param parMap
	 * @return
	 */
	public static Response getWaitingOrder(HttpRequestEx httpRequest, Map<String, String> parMap,String sessionId) {
		
		Response response = null;
		
		try {
			
			logger.info("获取待审核订单");
			
			Map<String, String> query = new HashMap<String, String>();
			setBuildParam(parMap, query);
			
			Map<String, String> param = new HashMap<String, String>();
			param.put("waiting_order_id", parMap.get("waiting_order_id"));
			param.put("entity_id", parMap.get("entity_id"));
			param.put("sign", parMap.get("sign"));
			
			List<String> pathList = new ArrayList<String>();
			pathList.add("cash-api/getOrder/v1/get_waiting_order_2_cash");
			
			Map<String, String> headerMap = new HashMap<String, String>();
			headerMap.put("sessionId", sessionId);
			response = httpRequest.postWithHeaders(pathList,headerMap,query, param);
			
//			Map<String, String> headerMap = new HashMap<String, String>();
//			headerMap.put("sessionId", sessionId);
//			response = cashRequest.postWithHeaders(pathList,headerMap,query, param);
			
			return response;
			
		} catch (Exception e) {
			
			logger.info(e.toString());
			
		}
		
		return response;
		
	}

	/**
	 * 更新消息状态
	 * @param cashRequest
	 * @param parMap
	 * @return
	 */
	public static Response updateMessage(HttpRequestEx cashRequest,Map<String, String> parMap,String sessionId) {
		Response response = null;
		try {
			logger.info("审核订单通过");
			Map<String, String> query = new HashMap<String, String>();
			setBuildParam(parMap, query);
			
			Map<String, String> param = new HashMap<String, String>();
			param.put("sign", parMap.get("sign"));
			param.put("id", parMap.get("id"));
			param.put("result_message", parMap.get("result_message"));
			param.put("timestamp", parMap.get("timestamp"));
			param.put("modify_user", parMap.get("modify_user"));
			param.put("status", parMap.get("status"));
			param.put("business_id", parMap.get("business_id"));
			param.put("device_id", parMap.get("device_id"));
			param.put("entity_id", parMap.get("entity_id"));
			
			List<String> pathList = new ArrayList<String>();
			pathList.add("dpush-api/dpush/v1/update_message");
			Map<String, String> headerMap = new HashMap<String, String>();
			headerMap.put("sessionId", sessionId);
			response = cashRequest.postWithHeaders(pathList,headerMap,query, param);
			
			return response;
		} catch (Exception e) {
			logger.info(e.toString());
		}
		return null;
	}

	/**
	 * 拒绝
	 * @param cashRequest
	 * @param parMap
	 * @return
	 */
	public static Response refuseOrder(HttpRequestEx cashRequest,Map<String, String> parMap) {
		Response response = null;
		try {
			logger.info("拒绝订单");
			Map<String, String> query = new HashMap<String, String>();
			setBuildParam(parMap, query);
			
			Map<String, String> param = new HashMap<String, String>();
			param.put("waiting_order_id", parMap.get("waiting_order_id"));
			param.put("instanceId", parMap.get("instanceId"));
			
			List<String> pathList = new ArrayList<String>();
			pathList.add("cash-api/order/v1/rejected_order");
			response = cashRequest.postWithHeader(pathList,query, param);
			
			return response;
		} catch (Exception e) {
			logger.info(e.toString());
		}
		
		return null;
	}
	
	/**
	 * 上传数据到交易中心
	 * @param cashRequest
	 * @param parMap
	 * @param sessionId 
	 * @return
	 */
	public static Response uploadData(HttpRequestEx cashRequest,Map<String, String> parMap, String sessionId) {
		Response response = null;
		try {
			logger.info("上传数据");
			Map<String, String> query = new HashMap<String, String>();
			setBuildParam(parMap, query);
			
			Map<String, String> param = new HashMap<String, String>();
			param.put("method",parMap.get("method"));
			param.put("total_pay_info_vo", parMap.get("total_pay_info_vo"));
			param.put("entity_id", parMap.get("entity_id"));
			param.put("mac", parMap.get("mac"));
			param.put("sign", parMap.get("sign"));
			
			List<String> pathList = new ArrayList<String>();
			pathList.add("cash-api/cashierorder/v2/uploadOrder");
			Map<String, String> headerMap = new HashMap<String, String>();
			headerMap.put("sessionId", sessionId);
			response = cashRequest.postWithHeaders(pathList,headerMap,query, param);
			
			return response;
		} catch (Exception e) {
			logger.info(e.toString());
		}
		return null;
	}

	/**
	 * 构建基本参数
	 * @param parMap
	 * @param query
	 */
	public static void setBuildParam(Map<String, String> parMap,Map<String, String> query) {
		query.put("s_osv", parMap.get("s_osv"));
		query.put("s_br", parMap.get("s_br"));
		query.put("s_apv", parMap.get("s_apv"));
		query.put("s_eid", parMap.get("s_eid"));
		query.put("s_did", parMap.get("s_did"));
		query.put("s_os", parMap.get("s_os"));
		query.put("s_net", parMap.get("s_net"));
		query.put("appKey", parMap.get("appKey"));
		query.put("format", parMap.get("format"));
		query.put("s_sc", parMap.get("s_sc"));
		query.put("app_key", parMap.get("app_key"));
	}
	
	
	public static void main(String arg[]) {
		String cashHost = "api.2dfire.com";
		HttpRequestEx cashRequest = new HttpRequestEx(cashHost, CommonConstants.HTTPS);
		Map<String, String> cashMap = new HashMap<String, String>();
		//获取消息
		cashMap.put("s_osv", "19");
		cashMap.put("s_br", "rk3188");
		cashMap.put("s_apv", "5.5.51-debug");
		cashMap.put("s_eid", "99928345");
		cashMap.put("s_did", "b44c31cc95573704f8e753de9577b2e9");
		cashMap.put("s_os", "android");
		cashMap.put("s_net", "1");
		cashMap.put("appKey", "200002");
		cashMap.put("format", "json");
		cashMap.put("s_sc", "1360x720");
		cashMap.put("app_key", "200002");
		
		cashMap.put("device_id", "CashDesk");
		cashMap.put("status", "3");//0:新消息； 1：自动审核；2：已处理；3：自动审核，未处理
		cashMap.put("start_time", DateUtil.dateToStamp("1970-01-18 10:46:33"));
		cashMap.put("page_index", "1");
		cashMap.put("entity_id", "00067404");
		cashMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
		String sign = getSign("9562e96263f74981a104720e3278763b", cashMap);
		cashMap.put("sign", sign);
		
		String sessionId = "9uV6L6K4IgyfuwKoCV3PUCXnWt3V3x3GVA8bX1SoOq2sLaiH95Mffp51Xk7a9neqX4ZujlzeRY80";
		String orderId = "0006740458d8760b0158e311934307b3";
		
		Response response = CashUtil.getMessageList(cashRequest, cashMap, sessionId);
		Assert.assertTrue(Response.isSuccess(response),"获取消息失败");
		
		JSONObject jsonObject =  JSONObject.parseObject(JSONPath.read(response.getResponseStr(), "$.data[b_id='"+orderId+"'][0]").toString()) ;
//		String waitingOrderId = jsonObject.getString("waitingOrderId");
		System.out.println(jsonObject.getString("id"));
	}

	public static String getSign(String secret, Map<String, String> cashMap) {
		return SignGenerator.client(secret, cashMap);
	}

	public static Response loginWithEncryptedPassword(HttpRequestEx httpRequest, Map<String, String> parMap) {
		Response response = null;
		try {
			logger.info("收银机登录");
			Map<String, String> query = new HashMap<String, String>();
			setBuildParam(parMap, query);
			
			Map<String, String> param = new HashMap<String, String>();
			param.put("sign", parMap.get("sign"));
			param.put("user_name", parMap.get("user_name"));
			param.put("client_type", parMap.get("client_type"));
			param.put("uniq_no", parMap.get("uniq_no"));
			param.put("brand", parMap.get("brand"));
			param.put("mac", parMap.get("mac"));
			param.put("entity_id", parMap.get("entity_id"));
			param.put("password", parMap.get("password"));
			
			List<String> pathList = new ArrayList<String>();
			pathList.add("cash-api/auth/v1/loginWithEncryptedPassword");
			response = httpRequest.postWithHeader(pathList, query, param);
			return response;
		} catch (Exception e) {
			logger.info(e.toString());
		}
		return response;
	}

	
	/**
	 * 发送收银心跳
	 * @param cashRequest
	 * @param parMap
	 * @return
	 */
	public static Response heart(HttpRequestEx cashRequest,Map<String, String> parMap) {
		
		Response response = null;
		
		try {
			
			logger.info("收银机心跳");
			
			Map<String, String> param = new HashMap<String, String>();
			param.put("entityid", parMap.get("entityid"));
			param.put("apptime", parMap.get("apptime"));
			param.put("appversion",parMap.get("appversion"));
			param.put("sign",parMap.get("sign"));
			
			List<String> pathList = new ArrayList<String>();
			pathList.add("heart/new/"+parMap.get("heart_appkey"));
			response = cashRequest.postWithHeaders(pathList, null,null, param);
			
			return response;
			
		} catch (Exception e) {
			
			logger.info(e.toString());
			
		}
		
		return response;
		
	}
	
	
	
}
