package com.dfire.qa.meal.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dfire.qa.meal.bean.Response;
import com.dfire.qa.meal.constant.BossContents;
import com.dfire.qa.meal.enums.Environment;
import com.dfire.qa.meal.httpclient.HTTPClientService;
import com.dfire.qa.meal.logger.LoggerMarkers;
import com.dfire.qa.meal.logger.MealLoggerFactory;
import com.dfire.qa.meal.props.BossProperties;
import com.dfire.qa.meal.props.HostProperties;
import com.dfire.qa.meal.service.IBossIntegralService;
import com.dfire.qa.meal.utils.HTTPRequestUtil;
import com.dfire.qa.meal.utils.MD5Utils;
import com.dfire.qa.meal.utils.PathForHTTP;

@Service
public class BossIntegralServiceImpl implements IBossIntegralService{

	
	@Resource
	private HostProperties hostProperties;
	
	@Resource
	private BossProperties bossProperties;
	
	@Resource
	private HTTPClientService httpClientService;
	
	
	
	@Override
	public Response getActivityDetail(String entityId, String activityId, Environment env) throws Exception {
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.GETACTIVITYDETAIL);
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
		
		Response response = null;						
		Map<String, String> bossPros = bossProperties.getBossProperties(env);
		
		
		// 设置 HTTP 请求  header
		Map<String, String> httpHeader = new HashMap<String, String>();		
		httpHeader.put("version", bossPros.get("version"));
		httpHeader.put("sessionId", bossPros.get("sessionId"));
		httpHeader.put("isTest", bossPros.get("isTest"));
		
		
		// 设置 HTTP 请求 body
	
		Map<String, String> params = new HashMap<String, String>();
		params.put("activity_id", activityId);
		
		params.put("app_key", bossPros.get("appKey"));
		params.put("app_version", bossPros.get("appVersion"));
		params.put("device_id", bossPros.get("deviceId"));

		params.put("format", bossPros.get("format"));
		params.put("timeStamp", Long.toString(System.currentTimeMillis()));
		params.put("entityId", entityId);  // 用于设置白名单

		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), params);
		params.put("sign", sign);
		
		// 构造表单形式的    body 内容
		StringBuilder bodyBuilder = new StringBuilder();
		
		for(Map.Entry<String, String> elementEntry : params.entrySet()){
			bodyBuilder.append("&" + elementEntry.getKey() + "=").append(elementEntry.getValue());
		}
		
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForGetActivityDetail(), null, protocol);
		response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
	}

	
	
	
	@Override
	public Response getActivityList(String entityId, Integer page, Integer pageSize, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.GETACTIVITYLIST);
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
		
		Response response = null;						
		Map<String, String> bossPros = bossProperties.getBossProperties(env);
		
		
		// 设置 HTTP 请求  header
		Map<String, String> httpHeader = new HashMap<String, String>();		
		httpHeader.put("version", bossPros.get("version"));
		httpHeader.put("sessionId", bossPros.get("sessionId"));
		httpHeader.put("isTest", bossPros.get("isTest"));
		
		
		// 设置 HTTP 请求 body
	
		Map<String, String> params = new HashMap<String, String>();
		params.put("page", Integer.toString(page));
		params.put("page_size", Integer.toString(pageSize));
		
		params.put("app_key", bossPros.get("appKey"));
		params.put("app_version", bossPros.get("appVersion"));
		params.put("device_id", bossPros.get("deviceId"));

		params.put("format", bossPros.get("format"));
		params.put("timeStamp", Long.toString(System.currentTimeMillis()));
		params.put("entityId", entityId);  // 用于设置白名单

		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), params);
		params.put("sign", sign);
		
		// 构造表单形式的    body 内容
		StringBuilder bodyBuilder = new StringBuilder();
		
		for(Map.Entry<String, String> elementEntry : params.entrySet()){
			bodyBuilder.append("&" + elementEntry.getKey() + "=").append(elementEntry.getValue());
		}
		
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForGetActivityList(), null, protocol);
		response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}

	
	
	
	
	@Override
	public Response getCommonMenuList(String entityId, String entityListStr, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.GETMENULIST);
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
		
		Response response = null;						
		Map<String, String> bossPros = bossProperties.getBossProperties(env);
		
		
		// 设置 HTTP 请求  header
		Map<String, String> httpHeader = new HashMap<String, String>();		
		httpHeader.put("version", bossPros.get("version"));
		httpHeader.put("sessionId", bossPros.get("sessionId"));
		httpHeader.put("isTest", bossPros.get("isTest"));
		
		
		// 设置 HTTP 请求 body
	
		Map<String, String> params = new HashMap<String, String>();
		
		params.put("app_key", bossPros.get("appKey"));
		params.put("app_version", bossPros.get("appVersion"));
		params.put("device_id", bossPros.get("deviceId"));

		params.put("format", bossPros.get("format"));
		params.put("timeStamp", Long.toString(System.currentTimeMillis()));
		params.put("entityId", entityId);  // 用于设置白名单

		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), params);
		params.put("sign", sign);
		
		// 构造表单形式的    body 内容
		StringBuilder bodyBuilder = new StringBuilder();
		
		for(Map.Entry<String, String> elementEntry : params.entrySet()){
			bodyBuilder.append("&" + elementEntry.getKey() + "=").append(elementEntry.getValue());
		}
		
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForQueryCommonMenu(), null, protocol);
		response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
	}

	
	
	
	@Override
	public Response saveActivity(String entityId, String activityInfoReqJson, String activityPromotionsJson, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.GETACTIVITYLIST);
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
		
		Response response = null;						
		Map<String, String> bossPros = bossProperties.getBossProperties(env);
		
		
		// 设置 HTTP 请求  header
		Map<String, String> httpHeader = new HashMap<String, String>();		
		httpHeader.put("version", bossPros.get("version"));
		httpHeader.put("sessionId", bossPros.get("sessionId"));
		httpHeader.put("isTest", bossPros.get("isTest"));
		
		
		// 设置 HTTP 请求 body
	
		Map<String, String> params = new HashMap<String, String>();
		params.put("activity_info_req", activityInfoReqJson);
		params.put("activity_promotions", activityPromotionsJson);
		
		params.put("app_key", bossPros.get("appKey"));
		params.put("app_version", bossPros.get("appVersion"));
		params.put("device_id", bossPros.get("deviceId"));

		params.put("format", bossPros.get("format"));
		params.put("timeStamp", Long.toString(System.currentTimeMillis()));
		params.put("entityId", entityId);  // 用于设置白名单

		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), params);
		params.put("sign", sign);
		
		// 构造表单形式的    body 内容
		StringBuilder bodyBuilder = new StringBuilder();
		
		for(Map.Entry<String, String> elementEntry : params.entrySet()){
			bodyBuilder.append("&" + elementEntry.getKey() + "=").append(elementEntry.getValue());
		}
		
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForSaveActivity(), null, protocol);
		response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
	}
	
	

}
