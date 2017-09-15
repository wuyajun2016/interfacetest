package com.dfire.qa.meal.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.testng.Assert;












import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.dfire.qa.meal.bean.BaseParamBean;
import com.dfire.qa.meal.bean.Response;
import com.dfire.qa.meal.constant.Constants;
import com.dfire.qa.meal.enums.Environment;
import com.dfire.qa.meal.enums.MessageStatus;
import com.dfire.qa.meal.httpclient.HTTPClientService;
import com.dfire.qa.meal.httpclient.HttpRequestEx;
import com.dfire.qa.meal.logger.LoggerMarkers;
import com.dfire.qa.meal.logger.MealLoggerFactory;
import com.dfire.qa.meal.props.CashProperties;
import com.dfire.qa.meal.props.HostProperties;
import com.dfire.qa.meal.service.ICashDeskService;
import com.dfire.qa.meal.utils.CashDeskUtil;
import com.dfire.qa.meal.utils.CommonUtil;
import com.dfire.qa.meal.utils.HTTPRequestUtil;
import com.dfire.qa.meal.utils.PathForHTTP;
import com.dfire.qa.meal.vo.cash.CashWaitingOrderVO;
import com.dfire.qa.meal.vo.cash.CloudMessage;
import com.dfire.qa.meal.vo.cash.MenuBalance;
import com.dfire.qa.meal.vo.cash.PayOrder;
import com.dfire.qa.meal.vo.cash.TotalPayInfoVo;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Service
public class CashDeskServiceImpl implements ICashDeskService{

	@Resource
	private HostProperties hostProperties;
	
	@Resource
	private CashProperties cashProperties;
	
	@Resource
	private HTTPClientService httpClientService;
	
	private Gson gson = new Gson();
	
	@Override
	public String loginWithPassword(BaseParamBean baseParamBean, Environment env) throws Exception {

		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.CashDesk.LOGINCASHDESK);		
				
		Map<String, String> query = cashProperties.getCashBasePara(env);
		
		
		// 构造表单形式的 body 内容
		Map<String, String> cashPts = cashProperties.getClientBasePara(env);
		String sign = CashDeskUtil.getSignForLoginCashDask(cashProperties, env);
		
		Map<String, String> paramOfBody = new HashMap<String, String>();
		paramOfBody.putAll(cashPts);
		paramOfBody.put("sign", sign);
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("cashURL"), PathForHTTP.getPathForLoginCashDesk(), query, protocol);
		Response response = httpClientService.doPostForCashWithForm(url, null, paramOfBody);
		
		 // 对返回参数进行验证处理
		Assert.assertTrue(Response.isSuccess(response), Constants.CashDesk.LOGINCASHDESKFAIL);
		String sessionId = JSONPath.read(response.getResponseStr(), "$.data").toString();
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.CashDesk.LOGINCASHDESKSUC);
		
		return sessionId;
		
	}



	@Override
	public Boolean sendHeartBeat(BaseParamBean baseParamBean, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.CashDesk.SENDHEARTBEAT);
		
		
		// 构造表单形式的 body 内容
		Map<String, String> cashBasePara = cashProperties.getCashBasePara(env);
		Map<String, String> otherBasePara = cashProperties.getOtherBasePara(env);
		String appTime = String.valueOf(System.currentTimeMillis());
		
		Map<String, String> paramOfBody = new HashMap<String, String>();
		paramOfBody.put("entityid", cashBasePara.get("s_eid"));
		paramOfBody.put("apptime", appTime);
		
		
		String sign = CashDeskUtil.getSignForSendHeartBeat(paramOfBody, otherBasePara.get("heart.appsecret"));
		
		paramOfBody.put("appversion", cashBasePara.get("s_apv"));		
		paramOfBody.put("sign", sign);
		
		// 计算 URL 并发送 POST 请求
		List<String> path = HTTPRequestUtil.addPathOfVarible(PathForHTTP.getPathForSendHeartBeat(), otherBasePara.get("heart.appkey"));		
		HttpRequestEx httpRequestEx = new HttpRequestEx(hostProperties.getHostProperties(env).get("cashHeartURL"), false);
		Response response = httpRequestEx.postWithHeaders(path, null, null, paramOfBody);
		
		// 关闭 HTTP 连接
		httpRequestEx.ShutDown();
		
		
		 // 对返回参数进行验证处理
		JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();
		if(200 != resp.get("code").getAsInt())
			throw new Exception(Constants.CashDesk.SENDHEARTBEATFAIL);

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.CashDesk.SENDHEARTBEATSUC);
		
		return true;
	}



	@Override
	public Response getMessageList(BaseParamBean baseParamBean, String sessionId, Environment env) throws Exception {
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.CashDesk.GETMESSAGELIST);
		
		
		// 待处理消息类型
		List<MessageStatus> msgStatus = new ArrayList<MessageStatus>();		
		msgStatus.add(MessageStatus.NEW_MESSAGE);
		msgStatus.add(MessageStatus.AUTO_CHECK_NOT_HANDLE);
		msgStatus.add(MessageStatus.AUTO_CHECK);
		
		
		Response response = null;
		int loopOutSide = 0;
		
		// 遍历执行所有消息类型
		for(MessageStatus element: msgStatus){
			
			// 构造请求参数
			Map<String, String> cashBasePara = cashProperties.getCashBasePara(env);
			Map<String, String> deviceParameter = new HashMap<String, String>();
			deviceParameter.put("device_id", "CashDesk");
			deviceParameter.put("status", element.getStatus());   //0:新消息； 1：自动审核；2：已处理；3：自动审核，未处理
			deviceParameter.put("start_time", CommonUtil.dateToStamp("1970-01-18 10:46:33"));
			
			deviceParameter.put("page_index", "1");
			deviceParameter.put("entity_id", cashBasePara.get("s_eid"));
			deviceParameter.put("timestamp", String.valueOf(System.currentTimeMillis()));
			
			
			// 计算 sign
			String sign = CashDeskUtil.getSignForDpush(cashProperties, env, deviceParameter);
			deviceParameter.put("sign", sign);
			
			
			// 构造 HTTP body
			String bodyWithForm = CashDeskUtil.getBodyWithForm(deviceParameter);
				
			
			// 构造 HTTP header
			Map<String, String> header = new HashMap<String, String>();
			header.put("sessionId", sessionId);
			
			
			// 计算 URL 并发送 POST 请求
			String protocol = (Environment.publish == env) ? "https" : "http";
			String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("cashURL"), PathForHTTP.getPathForGetMessageList(), cashBasePara, protocol);
			response = httpClientService.doPostWithForm(url, header, bodyWithForm);	
			
			// 确保对 Dpush 消息进行了消费
			int loop = 7;
			for (   ; loop > 0 ; loop--) {
				
				// 对返回参数进行验证处理
				Assert.assertTrue(Response.isSuccess(response), Constants.CashDesk.GETMESSAGELISTFAIL);				 
				JsonObject messageFromDpush = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();
				
				JsonElement dataElement = messageFromDpush.get("data");			
				
				if(null != dataElement && !("null".equalsIgnoreCase(dataElement.toString()))&& (messageFromDpush.get("data").getAsJsonArray().size()) > 0)
					break;
				
				
				Thread.sleep(3000);//休息3s再试
				response = httpClientService.doPostWithForm(url, header, bodyWithForm);
				
				
			}
			
			if(loop > 0){
				
				MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, "Dpush 消息状态为 ：" + element.getStatus());
				
				break;
			}
			
						
			if( (++loopOutSide) > 2){
				
				
				MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.CashDesk.GETMESSAGELISTFAIL);
				
				throw new Exception(Constants.CashDesk.GETMESSAGELISTFAIL);
			}
			
		
		}
		
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.CashDesk.GETMESSAGELISTSUC);
		
		return response;
		
	}



	@Override
	public CashWaitingOrderVO getWaitingOrderVo(BaseParamBean baseParamBean, Response responseFromGetMessageList, String sessionId, Environment env) throws Exception {
				
		//获取返回值	
		String orderApproedMsgJson = String.valueOf(JSONPath.read(responseFromGetMessageList.getResponseStr(),"$.data[ty=102][0]"));//102:订单审核消息
		Assert.assertNotNull(orderApproedMsgJson, Constants.CashDesk.ERRORGETMESSAGE);
		
		//对订单审核消息进行校验
		Assert.assertNotNull(orderApproedMsgJson, Constants.CashDesk.ERRORGETMESSAGE);
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.CashDesk.WAITINGORDERVO);
		
		CloudMessage msg = JSONObject.parseObject(orderApproedMsgJson, CloudMessage.class);
		String waitingOrderId = JSONObject.parseObject(msg.getCot()).getString("waitingOrderId");		
		String msgId = msg.getId();
		
		Assert.assertNotNull(msgId,Constants.CashDesk.CHECKMSGEMPTY);
		Assert.assertNotNull(waitingOrderId, Constants.CashDesk.CHECKMSGFORWAITING);
		
		// 构造请求参数
		Map<String, String> cashBasePara = cashProperties.getCashBasePara(env);
		Map<String, String> deviceParameter = new HashMap<String, String>();
				
		deviceParameter.put("waiting_order_id", waitingOrderId);
		deviceParameter.put("entity_id", cashBasePara.get("s_eid"));
		deviceParameter.put("timestamp", String.valueOf(System.currentTimeMillis()));
		
		
		// 计算 sign
		String sign = CashDeskUtil.getSignForDpush(cashProperties, env, deviceParameter);
		deviceParameter.put("sign", sign);
		
		
		// 构造 HTTP body
		String bodyWithForm = CashDeskUtil.getBodyWithForm(deviceParameter);
			
		
		// 构造 HTTP header
		Map<String, String> header = new HashMap<String, String>();
		header.put("sessionId", sessionId);
		
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("cashURL"), PathForHTTP.getPathForGetWaitingOrder(), cashBasePara, protocol);
		Response response = httpClientService.doPostWithForm(url, header, bodyWithForm);
		
		Assert.assertTrue(Response.isSuccess(response), Constants.CashDesk.WAITINGORDERVOFAIL);	
		CashWaitingOrderVO cashWaitingOrderVO = JSONObject.parseObject(JSONPath.read(response.getResponseStr(), "$.data").toString(), CashWaitingOrderVO.class);
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.CashDesk.WAITINGORDERVOSUC);
		
		return cashWaitingOrderVO;
		
		
	}



	@Override
	public Boolean uploadTotalPayInfoVo(TotalPayInfoVo totalPayInfoVo, String entityId, String sessionId, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.CashDesk.TOTALPAYINFOVO);

		
		// 构造请求参数
		Map<String, String> cashBasePara = cashProperties.getCashBasePara(env);
		Map<String, String> otherBasePara = cashProperties.getOtherBasePara(env);
		Map<String, String> deviceParameter = new HashMap<String, String>();
				
		deviceParameter.put("total_pay_info_vo", gson.toJson(totalPayInfoVo));
		deviceParameter.put("entity_id", cashBasePara.get("s_eid"));
		deviceParameter.put("mac", otherBasePara.get("macIp"));
		deviceParameter.put("method", "scanCode");
		
		
		// 计算 sign
		String sign = CashDeskUtil.getSignForDpush(cashProperties, env, deviceParameter);
		deviceParameter.put("sign", sign);
		
		
		// 构造 HTTP body
		String bodyWithForm = CashDeskUtil.getBodyWithForm(deviceParameter);
		
		
		// 构造 HTTP header
		Map<String, String> header = new HashMap<String, String>();
		header.put("sessionId", sessionId);
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("cashURL"), PathForHTTP.getPathForUploadTotalPay(), cashBasePara, protocol);
		Response response = httpClientService.doPostWithForm(url, header, bodyWithForm);
		
		
		// 数据校验
		Assert.assertTrue(Response.isSuccess(response), Constants.CashDesk.TOTALPAYINFOVOFAIL);	
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.CashDesk.TOTALPAYINFOVOSUC);
		
		return true;
	}



	@Override
	public Boolean updateMessage(String msgId, String bussinessId, String sessionId, String status, Environment env) throws Exception {


		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.CashDesk.UPDATEMESSAGE);						
		
		// 构造请求参数
		Map<String, String> cashBasePara = cashProperties.getCashBasePara(env);
		Map<String, String> deviceParameter = new HashMap<String, String>();
				
		deviceParameter.put("id", msgId);
		deviceParameter.put("result_message", "模拟收银机");
		deviceParameter.put("timestamp", String.valueOf(System.currentTimeMillis()));
		
		deviceParameter.put("modify_user", "模拟收银机");
		deviceParameter.put("status", status);
		deviceParameter.put("business_id", bussinessId);
		
		deviceParameter.put("device_id", "CashDesk");
		deviceParameter.put("entity_id", cashBasePara.get("s_eid"));
		
		
		// 计算 sign
		String sign = CashDeskUtil.getSignForDpush(cashProperties, env, deviceParameter);
		deviceParameter.put("sign", sign);
		
		
		// 构造 HTTP body
		String bodyWithForm = CashDeskUtil.getBodyWithForm(deviceParameter);
		
		
		// 构造 HTTP header
		Map<String, String> header = new HashMap<String, String>();
		header.put("sessionId", sessionId);
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("cashURL"), PathForHTTP.getPathForUpdateMessage(), cashBasePara, protocol);
		Response response = httpClientService.doPostWithForm(url, header, bodyWithForm);
		
		
		// 数据校验
		Assert.assertTrue(Response.isSuccess(response), Constants.CashDesk.UPDATEMESSAGEFAIL);	
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.CashDesk.UPDATEMESSAGESUC);
		
		return true;
		
	}



	@Override
	public Map<String, Object> approveOrder(BaseParamBean baseParamBean, String sessionId, Environment env) throws Exception {

		
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.CashDesk.APPROVERORDER);
		
		
		// 从 Dpush 获取 waitingOrder 信息   MessageStatus.NEW_MESSAGE
		Response responseFromGetMessageList = getMessageList(baseParamBean, sessionId, MessageStatus.NEW_MESSAGE, env);
		
//		// debug
//		getAllMsgList(baseParamBean, sessionId, env);
		
		// 获取 订单 并构造订单信息
		CashWaitingOrderVO cashWaitingOrderVO = getWaitingOrderVo(baseParamBean, responseFromGetMessageList, sessionId, env);
		
		
		// 构造订单信息并进行上传
				
		TotalPayInfoVo totalPayInfoVo = CashDeskUtil.getTotalPayInfo(cashWaitingOrderVO, baseParamBean.getEntityId());
				
		Boolean uploadResult = uploadTotalPayInfoVo(totalPayInfoVo, baseParamBean.getEntityId(), sessionId, env);
		Assert.assertTrue(uploadResult, Constants.CashDesk.APPROVERORDERFAIL);
		
		// 更新  Dpush 消息
		Map<String, String> infoForUpdateMessage = CashDeskUtil.getInfoForUpdateMessage(responseFromGetMessageList);
		String status = "2";
		Boolean updateMsgResult = updateMessage(infoForUpdateMessage.get("msgId"), infoForUpdateMessage.get("waitingOrderId"), sessionId, status, env);
		
		
		// 数据校验
		Assert.assertTrue(updateMsgResult, Constants.CashDesk.APPROVERORDERFAIL);	
		

		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("getMessageList", responseFromGetMessageList);
		resultMap.put("cashWaitingOrderVo", cashWaitingOrderVO);
		
		resultMap.put("uploadResult", uploadResult);
		resultMap.put("updateMsgResult", updateMsgResult);
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.CashDesk.APPROVERORDERSUC);
		
		return resultMap;
		
	}



	@Override
	public Boolean settleOrder(BaseParamBean baseParamBean, String sessionId, CashWaitingOrderVO cashWaitingOrderVO, Environment env) throws Exception {


		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.CashDesk.SETTLEORDER);
		
		
		// 从 Dpush 获取  Order 信息  MessageStatus.AUTO_CHECK_NOT_HANDLE
		Response responseFromGetMessageList = getMessageList(baseParamBean, sessionId, MessageStatus.AUTO_CHECK_NOT_HANDLE, env);
		
//		// debug
//		getAllMsgList(baseParamBean, sessionId, env);
		
		
		// 从 response 中获取 订单信息
		String orderSettledjson = String.valueOf(JSONPath.read(responseFromGetMessageList.getResponseStr(), 
				"$.data[b_id='" + baseParamBean.getOrderId() + "'][0]"));   //获取特定json
		
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, orderSettledjson);
		
		
		CloudMessage msg = JSONObject.parseObject(orderSettledjson, CloudMessage.class);
		String msgId = msg.getId();
		PayOrder payOrder = JSONObject.parseObject(msg.getCot(), PayOrder.class);
		
		
		// 更新  totalPayInfoVo 信息
		TotalPayInfoVo totalPayInfoVo = CashDeskUtil.updateTotalPayInfoVo(cashWaitingOrderVO, baseParamBean.getEntityId(), payOrder);
		
				
		// 更新  Dpush 消息
		String status = "2";
		Boolean updateMsgResult = updateMessage(msgId, "", sessionId, status, env);
		Assert.assertTrue(updateMsgResult, Constants.CashDesk.APPROVERORDERFAIL);
		
		// 构造订单信息并进行上传
		Boolean uploadResult = uploadTotalPayInfoVo(totalPayInfoVo, baseParamBean.getEntityId(), sessionId, env);
		Assert.assertTrue(uploadResult, Constants.CashDesk.APPROVERORDERFAIL);
		
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.CashDesk.SETTLEORDERSUC);
		
		
		return true;
		
	}

	
	
	
	@Override
	public Response getMessageList(BaseParamBean baseParamBean, String sessionId, MessageStatus messageStatus, Environment env) throws Exception {

		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.CashDesk.GETMESSAGELISTFOREXCEPTION);
				
		Response response = null;
				
		// 构造请求参数
		Map<String, String> cashBasePara = cashProperties.getCashBasePara(env);
		Map<String, String> deviceParameter = new HashMap<String, String>();
		deviceParameter.put("device_id", "CashDesk");
		deviceParameter.put("status", messageStatus.getStatus());   //0:新消息； 1：自动审核；2：已处理；3：自动审核，未处理
		deviceParameter.put("start_time", CommonUtil.dateToStamp("1970-01-18 10:46:33"));
		
		deviceParameter.put("page_index", "1");
		deviceParameter.put("entity_id", cashBasePara.get("s_eid"));
		deviceParameter.put("timestamp", String.valueOf(System.currentTimeMillis()));
		
		
		// 计算 sign
		String sign = CashDeskUtil.getSignForDpush(cashProperties, env, deviceParameter);
		deviceParameter.put("sign", sign);
		
		
		// 构造 HTTP body
		String bodyWithForm = CashDeskUtil.getBodyWithForm(deviceParameter);
			
		
		// 构造 HTTP header
		Map<String, String> header = new HashMap<String, String>();
		header.put("sessionId", sessionId);
		
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("cashURL"), PathForHTTP.getPathForGetMessageList(), cashBasePara, protocol);
		response = httpClientService.doPostWithForm(url, header, bodyWithForm);	
		
		// 确保对 Dpush 消息进行了消费
		int loop = 7;
		for (   ; loop > 0 ; loop--) {
			
			// 对返回参数进行验证处理
			Assert.assertTrue(Response.isSuccess(response), Constants.CashDesk.GETMESSAGELISTFAILFOREXCEPTION);				 
			JsonObject messageFromDpush = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();
			
			JsonElement dataElement = messageFromDpush.get("data");			
			
			if(null != dataElement && !("null".equalsIgnoreCase(dataElement.toString()))&& (messageFromDpush.get("data").getAsJsonArray().size()) > 0)
				break;
			
			
			Thread.sleep(3000);//休息3s再试
			response = httpClientService.doPostWithForm(url, header, bodyWithForm);
			
			
		}
		
		if(loop < 1){
			
			MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.CashDesk.GETMESSAGELISTFAILFOREXCEPTION);
			
			return null;
		}
		
						
			
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, "Dpush 消息状态为 ：" + messageStatus.getStatus());
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.CashDesk.GETMESSAGELISTSUCFOREXCEPTION);
		
		return response;
		
	}
	
	
	
	@Override
	public Response getAllMsgList(BaseParamBean baseParamBean, String sessionId, Environment env) throws Exception {
		
		Response newMsg = getMessageList(baseParamBean, sessionId, MessageStatus.NEW_MESSAGE, env);
		
		Response autoCheck = getMessageList(baseParamBean, sessionId, MessageStatus.AUTO_CHECK, env);
		
		Response Checked = getMessageList(baseParamBean, sessionId, MessageStatus.CHECKED, env);
		
		Response autoCheckedNoHandle = getMessageList(baseParamBean, sessionId, MessageStatus.AUTO_CHECK_NOT_HANDLE, env);
		
		return newMsg;
		
	}
	
	

	@Override
	public void clearDpushMessage(BaseParamBean baseParamBean, String sessionId, Environment env) throws Exception {

		
		//////////////////  处理状态为 0 (新消息) 的消息        /////////////////////////////////////
		Response newMessageResponse = getMessageList(baseParamBean, sessionId, MessageStatus.NEW_MESSAGE, env);
		
		
	}



	@Override
	public Response menuBalance(BaseParamBean baseParamBean, String menuId, String sessionId, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.CashDesk.MENUBALANCE);						
		
		// 构造请求参数
		Map<String, String> cashBasePara = cashProperties.getCashBasePara(env);
		Map<String, String> deviceParameter = new HashMap<String, String>();
		
		List<MenuBalance> menuBalances = new ArrayList<MenuBalance>();
		MenuBalance menuBalance = new MenuBalance(menuId, baseParamBean.getEntityId());
		menuBalances.add(menuBalance);
		
		deviceParameter.put("menu_balance_list", gson.toJson(menuBalances));
		deviceParameter.put("timestamp", String.valueOf(System.currentTimeMillis()));	
		
		// 计算 sign
		String sign = CashDeskUtil.getSignForDpush(cashProperties, env, deviceParameter);
		deviceParameter.put("sign", sign);
		
		
		// 构造 HTTP body
		String bodyWithForm = CashDeskUtil.getBodyWithForm(deviceParameter);
		
		
		// 构造 HTTP header
		Map<String, String> header = new HashMap<String, String>();
		header.put("sessionId", sessionId);
				
				
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("cashURL"), PathForHTTP.getPathForMenuBalance(), cashBasePara, protocol);
		Response response = httpClientService.doPostWithForm(url, header, bodyWithForm);
		
		return response;
	}



	@Override
	public Response clearMenuBalance(BaseParamBean baseParamBean, String menuId, String sessionId, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.CashDesk.CLEANMENUBALANCE);						
		
		// 构造请求参数
		Map<String, String> cashBasePara = cashProperties.getCashBasePara(env);
		Map<String, String> deviceParameter = new HashMap<String, String>();
		
		List<String> menuIdList = new ArrayList<String>();
		menuIdList.add(menuId);
		
		deviceParameter.put("menu_id_list", gson.toJson(menuIdList));
		deviceParameter.put("timestamp", String.valueOf(System.currentTimeMillis()));	
		deviceParameter.put("entity_id", baseParamBean.getEntityId());
		
		// 计算 sign
		String sign = CashDeskUtil.getSignForDpush(cashProperties, env, deviceParameter);
		deviceParameter.put("sign", sign);
		
		
		// 构造 HTTP body
		String bodyWithForm = CashDeskUtil.getBodyWithForm(deviceParameter);
		
		
		// 构造 HTTP header
		Map<String, String> header = new HashMap<String, String>();
		header.put("sessionId", sessionId);

		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("cashURL"), PathForHTTP.getPathForCleanMenuBalance(), cashBasePara, protocol);
		Response response = httpClientService.doPostWithForm(url, header, bodyWithForm);
		
		return response;
		
	}








	
	
	
	
	
	
	
	

}
