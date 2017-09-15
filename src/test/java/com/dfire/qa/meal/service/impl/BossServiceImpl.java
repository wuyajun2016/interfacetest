package com.dfire.qa.meal.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javassist.expr.NewArray;

import javax.annotation.Resource;

import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.springframework.stereotype.Service;
import org.testng.Assert;

import com.alibaba.dubbo.rpc.cluster.configurator.absent.AbsentConfigurator;
import com.alibaba.fastjson.JSONPath;
import com.dfire.qa.meal.bean.BaseParamBean;
import com.dfire.qa.meal.bean.Response;
import com.dfire.qa.meal.constant.BossContents;
import com.dfire.qa.meal.constant.Constants;
import com.dfire.qa.meal.enums.Environment;
import com.dfire.qa.meal.enums.FileType;
import com.dfire.qa.meal.enums.Privilege;
import com.dfire.qa.meal.enums.ScanCodeProcedure;
import com.dfire.qa.meal.httpclient.HTTPClientService;
import com.dfire.qa.meal.logger.LoggerMarkers;
import com.dfire.qa.meal.logger.MealLoggerFactory;
import com.dfire.qa.meal.props.BossProperties;
import com.dfire.qa.meal.props.HostProperties;
import com.dfire.qa.meal.service.IBossService;
import com.dfire.qa.meal.utils.BeanProvider;
import com.dfire.qa.meal.utils.HTTPRequestUtil;
import com.dfire.qa.meal.utils.MD5Utils;
import com.dfire.qa.meal.utils.PathForHTTP;
import com.dfire.qa.meal.vo.boss.BaseBossBean;
import com.dfire.qa.meal.vo.boss.BossConfig;
import com.dfire.qa.meal.vo.boss.CustomPrivilege;
import com.dfire.qa.meal.vo.boss.ForceConfig;
import com.dfire.qa.meal.vo.cart.CartIncludeSuitForm;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Service
public class BossServiceImpl implements IBossService{

	@Resource
	private HostProperties hostProperties;
	
	@Resource
	private BossProperties bossProperties;
	
	@Resource
	private HTTPClientService httpClientService;
	
	
	private Gson gson = new Gson();

	
	
	@Override
	public Response saveForceMenu(ForceConfig forceConfig, Environment env) throws Exception {
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Boss.SAVEFORCEMENU);
		
		
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
		BaseBossBean baseParam = new BaseBossBean();				
		baseParam.setForceConfig(forceConfig);		
		baseParam.setAppKey(bossPros.get("appKey"));
		
		baseParam.setAppVersion(bossPros.get("appVersion"));
		baseParam.setDeviceId(bossPros.get("deviceId"));
		
		baseParam.setFormat(bossPros.get("format"));
		baseParam.setSessionKey(bossPros.get("sessionKey"));
		
		baseParam.setTimeStamp(Long.toString(System.currentTimeMillis()));
		baseParam.setEntityId(forceConfig.getEntityId());
		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), baseParam.getBodyForSaveForceMenu());
		baseParam.setSign(sign);
		
		// 构造表单形式的    body 内容
		StringBuilder bodyBuilder = new StringBuilder();
		bodyBuilder.append("entityId=").append(baseParam.getEntityId()).append("&force_config=").append(gson.toJson(baseParam.getForceConfig())).
		append("&sign=").append(baseParam.getSign()).append("&session_key=").append(baseParam.getSessionKey()).append("&app_key=").
		append(baseParam.getAppKey()).append("&app_version=").append(baseParam.getAppVersion()).append("&device_id=").append(baseParam.getDeviceId()).
		append("&format=").append(baseParam.getFormat()).append("&timestamp=").append(baseParam.getTimeStamp());
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForSaveForceMenu(), null, protocol);
		response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}
	
	



	@Override
	public Response removeForceMenu(String entityId, String configId, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Boss.REMOVEFORCEMENU);
		
		
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
		
		params.put("config_id", configId);
		params.put("app_key", bossPros.get("appKey"));
		params.put("app_version", bossPros.get("appVersion"));
		params.put("device_id", bossPros.get("deviceId"));

		params.put("format", bossPros.get("format"));
		params.put("sessionKey", bossPros.get("sessionKey"));
		params.put("timeStamp", Long.toString(System.currentTimeMillis()));
		params.put("entityId", entityId);  // 用于设置白名单
		
//		params.put("version_code", "5061201");

		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), params);
		params.put("sign", sign);
		
		// 构造表单形式的    body 内容
		StringBuilder bodyBuilder = new StringBuilder();
		
		for(Map.Entry<String, String> elementEntry : params.entrySet()){
			bodyBuilder.append("&" + elementEntry.getKey() + "=").append(elementEntry.getValue());
		}
				
		
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForRemoveForceMenu(), null, protocol);
		response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}




	@Override
	public Response queryForceMenuList(String entityId, Environment env) throws Exception {
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Boss.QUERYFORCEMENULIST);
		
		
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
		BaseBossBean baseParam = new BaseBossBean();					
		baseParam.setAppKey(bossPros.get("appKey"));
		
		baseParam.setAppVersion(bossPros.get("appVersion"));
		baseParam.setDeviceId(bossPros.get("deviceId"));
		
		baseParam.setFormat(bossPros.get("format"));
		baseParam.setSessionKey(bossPros.get("sessionKey"));
		
		baseParam.setTimeStamp(Long.toString(System.currentTimeMillis()));
		baseParam.setEntityId(entityId);
		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), baseParam.getBodyForQueryForceMenu());
		baseParam.setSign(sign);
		
		// 构造表单形式的    body 内容
		StringBuilder bodyBuilder = new StringBuilder();		
		bodyBuilder.append("entityId=").append(baseParam.getEntityId()).append("&sign=").append(baseParam.getSign()).
		append("&session_key=").append(baseParam.getSessionKey()).append("&app_key=").append(baseParam.getAppKey()).
		append("&app_version=").append(baseParam.getAppVersion()).append("&device_id=").append(baseParam.getDeviceId()).
		append("&format=").append(baseParam.getFormat()).append("&timestamp=").append(baseParam.getTimeStamp());
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForSaveForceMenu(), null, protocol);
		response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}





	@Override
	public Response prePaySwitch(String entityId, ScanCodeProcedure scanCodeProcedure, boolean switchConfig, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Boss.PREPAYSWITCH);
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
		
		
		// 显示扫码类型
		if(switchConfig){
			
			MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, scanCodeProcedure.showDescription() + Constants.Boss.OPEN);
			
		}else{
			
			MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, scanCodeProcedure.showDescription() + Constants.Boss.CLOSE);
		}
		
		
		// 构造配置数据
		Map<String, String> bossPros = bossProperties.getBossProperties(env);				
		String value = switchConfig ? ("1") : ("0");
		
		Map<String, String> targetConfigMap = new HashMap<String, String>();
		targetConfigMap.put(bossPros.get("prePayConfigForSeatCode"), value);
		
		BossConfig bossConfig = new BossConfig();
		String configSettingString = bossConfig.BasicSet(targetConfigMap);
		
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////
		Response response = null;						
		
		
		
		// 设置 HTTP 请求  header
		Map<String, String> httpHeader = new HashMap<String, String>();		
		httpHeader.put("version", bossPros.get("version"));
		httpHeader.put("sessionId", bossPros.get("sessionId"));
		httpHeader.put("isTest", bossPros.get("isTest"));
		
		
		// 设置 HTTP 请求 body
		BaseBossBean baseParam = new BaseBossBean();
		baseParam.setIds_str(configSettingString);
		
		baseParam.setConfigId(bossPros.get("prePayConfigForSeatCode"));	
		baseParam.setAppKey(bossPros.get("appKey"));
		
		baseParam.setAppVersion(bossPros.get("appVersion"));
		baseParam.setDeviceId(bossPros.get("deviceId"));
		
		baseParam.setFormat(bossPros.get("format"));
		baseParam.setSessionKey(bossPros.get("sessionKey"));
		
		baseParam.setTimeStamp(Long.toString(System.currentTimeMillis()));
		baseParam.setEntityId(entityId);
		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), baseParam.getBodyForPrePaySwitchWithSeatCode());
		baseParam.setSign(sign);
		
		StringBuilder bodyBuilder = new StringBuilder();
		bodyBuilder.append("entityId=").append(baseParam.getEntityId()).append("&sign=").append(baseParam.getSign()).
		append("&ids_str=").append(baseParam.getIds_str()).append("&app_key=").append(baseParam.getAppKey()).
		append("&app_version=").append(baseParam.getAppVersion()).append("&device_id=").append(baseParam.getDeviceId()).
		append("&format=").append(baseParam.getFormat()).append("&timestamp=").append(baseParam.getTimeStamp());

		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForPrePaySwitch(), null, protocol);
		response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}
	
	
	
	@Override
	public Response getTakeOutDeliveryMan(String entityId, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.GETTAKEOUTDELIVERYMAN);
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
									
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////

		
		// 构造配置数据, 设置 HTTP 请求  header
		Map<String, String> bossPros = bossProperties.getBossProperties(env);	
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
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForGetTakeOutDeliveryMan(), null, protocol);
		Response response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}
	
	
	@Override
	public Response deleteTakeOutDeliveryMan(String entityId, String id, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.DELETETAKEOUTDELIVERYMAN);
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
									
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////

		
		// 构造配置数据, 设置 HTTP 请求  header
		Map<String, String> bossPros = bossProperties.getBossProperties(env);	
		Map<String, String> httpHeader = new HashMap<String, String>();		
		httpHeader.put("version", bossPros.get("version"));
		httpHeader.put("sessionId", bossPros.get("sessionId"));
		httpHeader.put("isTest", bossPros.get("isTest"));
		
		
		// 设置 HTTP 请求 body
		Map<String, String> params = new HashMap<String, String>();
		
		params.put("id", id);
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
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForDeleteTakeOutDeliveryMan(), null, protocol);
		Response response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}
	
	
	
	
	@Override
	public Boolean deleteAllTakeOutDeliveryMan(String entityId, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.DELETEALLTAKEOUTDELIVERYMAN);
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
									
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////

		
		Response timesConfig = getTakeOutDeliveryMan(entityId, env);
		JsonObject resp = new JsonParser().parse(timesConfig.getResponseStr()).getAsJsonObject();	
		
		
		for(JsonElement element : resp.get("data").getAsJsonArray()){
			
			deleteTakeOutDeliveryMan(entityId, element.getAsJsonObject().get("id").getAsString(), env);	
			
		}
		
		
		Response timesConfigCheck = getTakeOutDeliveryMan(entityId, env);
		JsonObject respCheck = new JsonParser().parse(timesConfigCheck.getResponseStr()).getAsJsonObject();
		
		if(respCheck.get("data").getAsJsonArray().size() > 0)
			throw new Exception(" fail to delete" + BossContents.InfraClient.TAKEOUTDELIVERYMAN);
		
		return true;
		
	}
	
	
	
	
	
	@Override
	public Response takeOutDeliveryMan(String entityId, String takeoutDeliveryManJson, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.TAKEOUTDELIVERYMAN);
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
									
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////

		
		// 构造配置数据, 设置 HTTP 请求  header
		Map<String, String> bossPros = bossProperties.getBossProperties(env);	
		Map<String, String> httpHeader = new HashMap<String, String>();		
		httpHeader.put("version", bossPros.get("version"));
		httpHeader.put("sessionId", bossPros.get("sessionId"));
		httpHeader.put("isTest", bossPros.get("isTest"));
		
		
		// 设置 HTTP 请求 body
		BaseBossBean baseParam = new BaseBossBean();
		
		baseParam.setDelivery_man_json(takeoutDeliveryManJson);
		baseParam.setAppKey(bossPros.get("appKey"));
		
		baseParam.setAppVersion(bossPros.get("appVersion"));
		baseParam.setDeviceId(bossPros.get("deviceId"));
		
		baseParam.setFormat(bossPros.get("format"));
		baseParam.setSessionKey(bossPros.get("sessionKey"));
		
		baseParam.setTimeStamp(Long.toString(System.currentTimeMillis()));
		baseParam.setEntityId(entityId);
		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), baseParam.getBodyForTakeOutDeliveryMan());
		baseParam.setSign(sign);
		
		StringBuilder bodyBuilder = new StringBuilder();
		bodyBuilder.append("entityId=").append(baseParam.getEntityId()).append("&sign=").append(baseParam.getSign()).
		append("&delivery_man_json=").append(baseParam.getDelivery_man_json()).append("&app_key=").append(baseParam.getAppKey()).
		append("&app_version=").append(baseParam.getAppVersion()).append("&device_id=").append(baseParam.getDeviceId()).
		append("&format=").append(baseParam.getFormat()).append("&timestamp=").append(baseParam.getTimeStamp());
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForTakeOutDeliveryMan(), null, protocol);
		Response response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}
	
	
	
	@Override
	public Response takeOutTimeConfig(String entityId, String takeoutTimeJson, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.TAKEOUTTIME);
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
									
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////

		
		// 构造配置数据, 设置 HTTP 请求  header
		Map<String, String> bossPros = bossProperties.getBossProperties(env);	
		Map<String, String> httpHeader = new HashMap<String, String>();		
		httpHeader.put("version", bossPros.get("version"));
		httpHeader.put("sessionId", bossPros.get("sessionId"));
		httpHeader.put("isTest", bossPros.get("isTest"));
		
		
		// 设置 HTTP 请求 body
		BaseBossBean baseParam = new BaseBossBean();
		
		baseParam.setTime_json(takeoutTimeJson);
		baseParam.setAppKey(bossPros.get("appKey"));
		
		baseParam.setAppVersion(bossPros.get("appVersion"));
		baseParam.setDeviceId(bossPros.get("deviceId"));
		
		baseParam.setFormat(bossPros.get("format"));
		baseParam.setSessionKey(bossPros.get("sessionKey"));
		
		baseParam.setTimeStamp(Long.toString(System.currentTimeMillis()));
		baseParam.setEntityId(entityId);
		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), baseParam.getBodyForTakeOutTimeConfig());
		baseParam.setSign(sign);
		
		StringBuilder bodyBuilder = new StringBuilder();
		bodyBuilder.append("entityId=").append(baseParam.getEntityId()).append("&sign=").append(baseParam.getSign()).
		append("&time_json=").append(baseParam.getTime_json()).append("&app_key=").append(baseParam.getAppKey()).
		append("&app_version=").append(baseParam.getAppVersion()).append("&device_id=").append(baseParam.getDeviceId()).
		append("&format=").append(baseParam.getFormat()).append("&timestamp=").append(baseParam.getTimeStamp());
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForTakeOutTime(), null, protocol);
		Response response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}
	
	
	@Override
	public Response getTakeOutTimeConfig(String entityId, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.GETTAKEOUTTIMECONFIG);
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
									
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////

		
		// 构造配置数据, 设置 HTTP 请求  header
		Map<String, String> bossPros = bossProperties.getBossProperties(env);	
		Map<String, String> httpHeader = new HashMap<String, String>();		
		httpHeader.put("version", bossPros.get("version"));
		httpHeader.put("sessionId", bossPros.get("sessionId"));
		httpHeader.put("isTest", bossPros.get("isTest"));
		
		
		// 设置 HTTP 请求 body		
		Map<String, String> params = new HashMap<String, String>();
		
		params.put("entityType", "0");
		
		params.put("app_key", bossPros.get("appKey"));
		params.put("app_version", bossPros.get("appVersion"));
		params.put("device_id", bossPros.get("deviceId"));

		params.put("format", bossPros.get("format"));
		params.put("timeStamp", Long.toString(System.currentTimeMillis()));
		params.put("entityId", entityId);  // 用于设置白名单
		params.put("session_key", bossPros.get("sessionKey"));

		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), params);
		params.put("sign", sign);
		
		StringBuilder bodyBuilder = new StringBuilder();
		
		for(Map.Entry<String, String> elementEntry : params.entrySet()){
			bodyBuilder.append("&" + elementEntry.getKey() + "=").append(elementEntry.getValue());
		}
		
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForGetTakeOutConfig(), null, protocol);
		Response response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}
	
	
	
	@Override
	public Response deleteTakeOutTimeConfig(String entityId, String id, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.DELETETAKEOUTTIME);
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
									
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////
		
		// 构造配置数据, 设置 HTTP 请求  header
		Map<String, String> bossPros = bossProperties.getBossProperties(env);	
		Map<String, String> httpHeader = new HashMap<String, String>();		
		httpHeader.put("version", bossPros.get("version"));
		httpHeader.put("sessionId", bossPros.get("sessionId"));
		httpHeader.put("isTest", bossPros.get("isTest"));
		
		
		// 设置 HTTP 请求 body
		Map<String, String> params = new HashMap<String, String>();
		
		params.put("id", id);
		params.put("app_key", bossPros.get("appKey"));
		params.put("app_version", bossPros.get("appVersion"));
		params.put("device_id", bossPros.get("deviceId"));

		params.put("format", bossPros.get("format"));
		params.put("timeStamp", Long.toString(System.currentTimeMillis()));
		params.put("entityId", entityId);  // 用于设置白名单
		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), params);
		params.put("sign", sign);
		
		StringBuilder bodyBuilder = new StringBuilder();
		
		for(Map.Entry<String, String> elementEntry : params.entrySet()){
			bodyBuilder.append("&" + elementEntry.getKey() + "=").append(elementEntry.getValue());
		}
		
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForDeleteTakeOutConfig(), null, protocol);
		Response response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}
	
	@Override
	public Boolean deleteAllTakeOutTimeConfig(String entityId, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.DELETEALLTAKEOUTCONFIG);
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
									
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////

		Response timesConfig = getTakeOutTimeConfig(entityId, env);
		JsonObject resp = new JsonParser().parse(timesConfig.getResponseStr()).getAsJsonObject();	
		
		
		for(JsonElement element : resp.get("data").getAsJsonArray()){
			
			deleteTakeOutTimeConfig(entityId, element.getAsJsonObject().get("id").getAsString(), env);	
			
		}
		
	
		Response timesConfigCheck = getTakeOutTimeConfig(entityId, env);
		JsonObject respCheck = new JsonParser().parse(timesConfigCheck.getResponseStr()).getAsJsonObject();
		if(respCheck.get("data").getAsJsonArray().size() > 0 )
			throw new Exception("fail to delete " + BossContents.InfraClient.DELETEALLTAKEOUTCONFIG);
		
		return true;
		
	}
	
	
	@Override
	public Response takeOutSetConfig(String entityId, String takeoutSetJson, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.TAKEOUT);
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
									
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////

		
		// 构造配置数据, 设置 HTTP 请求  header
		Map<String, String> bossPros = bossProperties.getBossProperties(env);	
		Map<String, String> httpHeader = new HashMap<String, String>();		
		httpHeader.put("version", bossPros.get("version"));
		httpHeader.put("sessionId", bossPros.get("sessionId"));
		httpHeader.put("isTest", bossPros.get("isTest"));
		
		
		// 设置 HTTP 请求 body
		BaseBossBean baseParam = new BaseBossBean();
		
		baseParam.setSettings_json(takeoutSetJson);
		baseParam.setAppKey(bossPros.get("appKey"));
		
		baseParam.setAppVersion(bossPros.get("appVersion"));
		baseParam.setDeviceId(bossPros.get("deviceId"));
		
		baseParam.setFormat(bossPros.get("format"));
		baseParam.setSessionKey(bossPros.get("sessionKey"));
		
		baseParam.setTimeStamp(Long.toString(System.currentTimeMillis()));
		baseParam.setEntityId(entityId);
		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), baseParam.getBodyForTakeOutConfig());
		baseParam.setSign(sign);
		
		StringBuilder bodyBuilder = new StringBuilder();
		bodyBuilder.append("entityId=").append(baseParam.getEntityId()).append("&sign=").append(baseParam.getSign()).
		append("&settings_json=").append(baseParam.getSettings_json()).append("&app_key=").append(baseParam.getAppKey()).
		append("&app_version=").append(baseParam.getAppVersion()).append("&device_id=").append(baseParam.getDeviceId()).
		append("&format=").append(baseParam.getFormat()).append("&timestamp=").append(baseParam.getTimeStamp());
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForTakeOutConfig(), null, protocol);
		Response response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}

	
	
	
	@Override
	public Response saveForceMenu(String entityId,  ForceConfig forceConfig, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.SAVEFORCEMENU);
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
									
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////
		
		// 构造配置数据, 设置 HTTP 请求  header
		Map<String, String> bossPros = bossProperties.getBossProperties(env);	
		Map<String, String> httpHeader = new HashMap<String, String>();		
		httpHeader.put("version", bossPros.get("version"));
		httpHeader.put("sessionId", bossPros.get("sessionId"));
		httpHeader.put("isTest", bossPros.get("isTest"));
		
		
		// 设置 HTTP 请求 body
		Map<String, String> params = new HashMap<String, String>();
		
		params.put("force_config", gson.toJson(forceConfig));
		params.put("app_key", bossPros.get("appKey"));
		params.put("app_version", bossPros.get("appVersion"));
		params.put("device_id", bossPros.get("deviceId"));

		params.put("format", bossPros.get("format"));
		params.put("timeStamp", Long.toString(System.currentTimeMillis()));
		params.put("entityId", entityId);  // 用于设置白名单
		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), params);
		params.put("sign", sign);
		
		StringBuilder bodyBuilder = new StringBuilder();
		
		for(Map.Entry<String, String> elementEntry : params.entrySet()){
			bodyBuilder.append("&" + elementEntry.getKey() + "=").append(elementEntry.getValue());
		}
		
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForSaveForceMenu(), null, protocol);
		Response response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}
	
	
	
	
	
	@Override
	public Response getForceMenuList(String entityId, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.GETFORCEMENULIST);
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
									
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////
		
		// 构造配置数据, 设置 HTTP 请求  header
		Map<String, String> bossPros = bossProperties.getBossProperties(env);	
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
		
		StringBuilder bodyBuilder = new StringBuilder();
		
		for(Map.Entry<String, String> elementEntry : params.entrySet()){
			bodyBuilder.append("&" + elementEntry.getKey() + "=").append(elementEntry.getValue());
		}
		
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForGetForceMenuList(), null, protocol);
		Response response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}
	
	
	
	@Override
	public Response getAllForceMenuList(String entityId, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.GETALLFORCEMENULIST);
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
									
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////
		
		// 构造配置数据, 设置 HTTP 请求  header
		Map<String, String> bossPros = bossProperties.getBossProperties(env);	
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
		
		StringBuilder bodyBuilder = new StringBuilder();
		
		for(Map.Entry<String, String> elementEntry : params.entrySet()){
			bodyBuilder.append("&" + elementEntry.getKey() + "=").append(elementEntry.getValue());
		}
		
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForGetAllForceMenuList(), null, protocol);
		Response response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}
	
	
	
	
	
	
	@Override
	public Response deleteForceMenu(String entityId, String configId, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.DELETEFORCEMENU);
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
									
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////
		
		// 构造配置数据, 设置 HTTP 请求  header
		Map<String, String> bossPros = bossProperties.getBossProperties(env);	
		Map<String, String> httpHeader = new HashMap<String, String>();		
		httpHeader.put("version", bossPros.get("version"));
		httpHeader.put("sessionId", bossPros.get("sessionId"));
		httpHeader.put("isTest", bossPros.get("isTest"));
		
		
		// 设置 HTTP 请求 body
		Map<String, String> params = new HashMap<String, String>();
		
		params.put("config_id", configId);
		params.put("app_key", bossPros.get("appKey"));
		params.put("app_version", bossPros.get("appVersion"));
		params.put("device_id", bossPros.get("deviceId"));

		params.put("format", bossPros.get("format"));
		params.put("timeStamp", Long.toString(System.currentTimeMillis()));
		params.put("entityId", entityId);  // 用于设置白名单
		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), params);
		params.put("sign", sign);
		
		StringBuilder bodyBuilder = new StringBuilder();
		
		for(Map.Entry<String, String> elementEntry : params.entrySet()){
			bodyBuilder.append("&" + elementEntry.getKey() + "=").append(elementEntry.getValue());
		}
		
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForRemoveForceMenu(), null, protocol);
		Response response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}
	
	
	
	@Override
	public Boolean deleteAllForceMenu(String entityId, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.DELETEALLFORCEMENU);						
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////
		
		Response forceMenu = getForceMenuList(entityId, env);
		JsonObject forceMenuJsonObject = new JsonParser().parse(forceMenu.getResponseStr()).getAsJsonObject();
		
		if(!forceMenuJsonObject.has("data") || (forceMenuJsonObject.get("data").getAsJsonArray().size() < 1))
			return true;
		
		
		for(JsonElement element : forceMenuJsonObject.get("data").getAsJsonArray()){
			String configId = element.getAsJsonObject().get("forceConfigVo").getAsJsonObject().get("configId").getAsString();
			deleteForceMenu(entityId, configId, env);
		}
		
		return true;
		
	}
	
	
	

	@Override
	public Response batchSetConfig(String entityId, Map<String, String> configMap, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.BATCHSET);
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
									
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////

		
		// 构造配置数据, 设置 HTTP 请求  header
		Map<String, String> bossPros = bossProperties.getBossProperties(env);	
		Map<String, String> httpHeader = new HashMap<String, String>();		
		httpHeader.put("version", bossPros.get("version"));
		httpHeader.put("sessionId", bossPros.get("sessionId"));
		httpHeader.put("isTest", bossPros.get("isTest"));
		
		
		// 设置 HTTP 请求 body
		BaseBossBean baseParam = new BaseBossBean();
		
		baseParam.setConfig_map_json(gson.toJson(configMap));
		baseParam.setAppKey(bossPros.get("appKey"));
		
		baseParam.setAppVersion(bossPros.get("appVersion"));
		baseParam.setDeviceId(bossPros.get("deviceId"));
		
		baseParam.setFormat(bossPros.get("format"));
		baseParam.setSessionKey(bossPros.get("sessionKey"));
		
		baseParam.setTimeStamp(Long.toString(System.currentTimeMillis()));
		baseParam.setEntityId(entityId);
		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), baseParam.getBodyForBatchSetConfig());
		baseParam.setSign(sign);
		
		StringBuilder bodyBuilder = new StringBuilder();
		bodyBuilder.append("entityId=").append(baseParam.getEntityId()).append("&sign=").append(baseParam.getSign()).
		append("&config_map_json=").append(baseParam.getConfig_map_json()).append("&app_key=").append(baseParam.getAppKey()).
		append("&app_version=").append(baseParam.getAppVersion()).append("&device_id=").append(baseParam.getDeviceId()).
		append("&format=").append(baseParam.getFormat()).append("&timestamp=").append(baseParam.getTimeStamp());
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForBatchSetting(), null, protocol);
		Response response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}
	
	
	@Override
	public Response listConfigInfraSwitch(String entityId, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Boss.PREPAYSWITCH);		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}

		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////
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
		params.put("kind_config_code", "SYS_CONFIG");
		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), params);
		params.put("sign", sign);
		
		// 构造表单形式的    body 内容
		StringBuilder bodyBuilder = new StringBuilder();
		
		for(Map.Entry<String, String> elementEntry : params.entrySet()){
			bodyBuilder.append("&" + elementEntry.getKey() + "=").append(elementEntry.getValue());
		}
		

		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForListBossConfig(), null, protocol);
		response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}
	
	
	@Override
	public Response infraSwitchWithClient(String entityId, String config, boolean switchConfig, String description, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Boss.INFRASWITCH);
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.COMMON + description);
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
		
		
		// 构造配置数据
		Map<String, String> bossPros = bossProperties.getBossProperties(env);				
		String value = switchConfig ? ("1") : ("0");
		
		Map<String, String> targetConfigMap = new HashMap<String, String>();
		targetConfigMap.put(config, value);
		
		BossConfig bossConfig = new BossConfig();
		String configSettingString = bossConfig.BasicSet(targetConfigMap);
		
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////
		Response response = null;						
		
		
		
		// 设置 HTTP 请求  header
		Map<String, String> httpHeader = new HashMap<String, String>();		
		httpHeader.put("version", bossPros.get("version"));
		httpHeader.put("sessionId", bossPros.get("sessionId"));
		httpHeader.put("isTest", bossPros.get("isTest"));
		
		
		// 设置 HTTP 请求 body
		BaseBossBean baseParam = new BaseBossBean();
		baseParam.setIds_str(configSettingString);
		
		baseParam.setConfigId(config);	
		baseParam.setAppKey(bossPros.get("appKey"));
		
		baseParam.setAppVersion(bossPros.get("appVersion"));
		baseParam.setDeviceId(bossPros.get("deviceId"));
		
		baseParam.setFormat(bossPros.get("format"));
		baseParam.setSessionKey(bossPros.get("sessionKey"));
		
		baseParam.setTimeStamp(Long.toString(System.currentTimeMillis()));
		baseParam.setEntityId(entityId);
		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), baseParam.getBodyForPrePaySwitchWithSeatCode());
		baseParam.setSign(sign);
		
		StringBuilder bodyBuilder = new StringBuilder();
		bodyBuilder.append("entityId=").append(baseParam.getEntityId()).append("&sign=").append(baseParam.getSign()).
		append("&ids_str=").append(baseParam.getIds_str()).append("&app_key=").append(baseParam.getAppKey()).
		append("&app_version=").append(baseParam.getAppVersion()).append("&device_id=").append(baseParam.getDeviceId()).
		append("&format=").append(baseParam.getFormat()).append("&timestamp=").append(baseParam.getTimeStamp());

		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForBaseWithClientSwitch(), null, protocol);
		response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}
	
	
	
	
	
	@Override
	public Response uploadFile(String entityId, FileType fileType, String fileName, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.FILEUPLOAD + " " + fileType.getDescription());
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
									
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////

		
		// 构造配置数据, 设置 HTTP 请求  header
		Map<String, String> bossPros = bossProperties.getBossProperties(env);	
		Map<String, String> httpHeader = new HashMap<String, String>();		
		httpHeader.put("version", bossPros.get("version"));
		httpHeader.put("sessionId", bossPros.get("sessionId"));
		httpHeader.put("isTest", bossPros.get("isTest"));
		
		
		// 设置 HTTP 请求 body
		BaseBossBean baseParam = new BaseBossBean();
		
		baseParam.setDomain("skin");;
		baseParam.setAppKey(bossPros.get("appKey"));
		
		baseParam.setAppVersion(bossPros.get("appVersion"));
		baseParam.setDeviceId(bossPros.get("deviceId"));
		
		baseParam.setFormat(bossPros.get("format"));
		baseParam.setSessionKey(bossPros.get("sessionKey"));
		
		baseParam.setTimeStamp(Long.toString(System.currentTimeMillis()));
		baseParam.setEntityId(entityId);
		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), baseParam.getBodyForUploadFile());
		baseParam.setSign(sign);
	
		
		//////////////////////  file entity     ///////////////////////////////////////
		
		String filePath = ClassLoader.getSystemResource("").getPath() + "file/" + fileName;
		File file = new File(filePath);
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, "file path is : " + filePath);
		
		
		String boundary = "-------------" + System.currentTimeMillis();
		MultipartEntityBuilder multipartEntity = MultipartEntityBuilder.create();
		multipartEntity.setBoundary(boundary);
		
		multipartEntity.addBinaryBody("file", file);
		multipartEntity.addTextBody("entityId", baseParam.getEntityId());
		multipartEntity.addTextBody("sign", baseParam.getSign());
		
		multipartEntity.addTextBody("app_key", baseParam.getAppKey());
		multipartEntity.addTextBody("app_version", baseParam.getAppVersion());
		multipartEntity.addTextBody("device_id", baseParam.getDeviceId());
		
		multipartEntity.addTextBody("domain", baseParam.getDomain());
		multipartEntity.addTextBody("format", baseParam.getFormat());
		multipartEntity.addTextBody("timestamp", baseParam.getTimeStamp());
		HttpEntity httpEntity = multipartEntity.build();
	
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		List<String> pathList = new ArrayList<String>();
		if(fileType == FileType.image)
			pathList =  PathForHTTP.getPathForImageUpload();
		
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), pathList, null, protocol);
		Response response = httpClientService.doPostFile(url, httpHeader, httpEntity, boundary);
		
		return response;
		
	}
	
	
	
	
	@Override
	public Response imageSetConfig(String entityId, String addImageSetJson, String deleteImageJson, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.TAKEOUT);
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
									
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////

		
		// 构造配置数据, 设置 HTTP 请求  header
		Map<String, String> bossPros = bossProperties.getBossProperties(env);	
		Map<String, String> httpHeader = new HashMap<String, String>();		
		httpHeader.put("version", bossPros.get("version"));
		httpHeader.put("sessionId", bossPros.get("sessionId"));
		httpHeader.put("isTest", bossPros.get("isTest"));
		
		
		// 设置 HTTP 请求 body
		BaseBossBean baseParam = new BaseBossBean();
		
		baseParam.setDel_item_ids(deleteImageJson);  // 需要被删除的图片 ID
		baseParam.setItem_exts(addImageSetJson);     // 需要被添加的图片 ID
		baseParam.setAppKey(bossPros.get("appKey"));
		
		baseParam.setAppVersion(bossPros.get("appVersion"));
		baseParam.setDeviceId(bossPros.get("deviceId"));
		
		baseParam.setFormat(bossPros.get("format"));
		baseParam.setSessionKey(bossPros.get("sessionKey"));
		
		baseParam.setTimeStamp(Long.toString(System.currentTimeMillis()));
		baseParam.setEntityId(entityId);
		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), baseParam.getBodyForImageSetConfig());
		baseParam.setSign(sign);
		
		StringBuilder bodyBuilder = new StringBuilder();
		bodyBuilder.append("entityId=").append(baseParam.getEntityId()).append("&sign=").append(baseParam.getSign()).
		append("&item_exts=").append(baseParam.getItem_exts()).append("&app_key=").append(baseParam.getAppKey()).
		append("&app_version=").append(baseParam.getAppVersion()).append("&device_id=").append(baseParam.getDeviceId()).
		append("&format=").append(baseParam.getFormat()).append("&timestamp=").append(baseParam.getTimeStamp()).
		append("&del_item_ids=").append(baseParam.getDel_item_ids());
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForImageSetConfig(), null, protocol);
		Response response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}
	
	
	
	
	
	@Override
	public Response queryImageSet(String entityId, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.TAKEOUT);
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
									
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////

		
		// 构造配置数据, 设置 HTTP 请求  header
		Map<String, String> bossPros = bossProperties.getBossProperties(env);	
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
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForQueryImageSet(), null, protocol);
		Response response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}
	
	
	
	
	
	@Override
	public Response colorStyleConfig(String entityId, String styleJson, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.TAKEOUT);
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
									
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////

		
		// 构造配置数据, 设置 HTTP 请求  header
		Map<String, String> bossPros = bossProperties.getBossProperties(env);	
		Map<String, String> httpHeader = new HashMap<String, String>();		
		httpHeader.put("version", bossPros.get("version"));
		httpHeader.put("sessionId", bossPros.get("sessionId"));
		httpHeader.put("isTest", bossPros.get("isTest"));
		
		
		// 设置 HTTP 请求 body
		BaseBossBean baseParam = new BaseBossBean();
		
		baseParam.setStyle(styleJson);
		baseParam.setAppKey(bossPros.get("appKey"));
		
		baseParam.setAppVersion(bossPros.get("appVersion"));
		baseParam.setDeviceId(bossPros.get("deviceId"));
		
		baseParam.setFormat(bossPros.get("format"));
		baseParam.setSessionKey(bossPros.get("sessionKey"));
		
		baseParam.setTimeStamp(Long.toString(System.currentTimeMillis()));
		baseParam.setEntityId(entityId);
		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), baseParam.getBodyForColorStyleConfig());
		baseParam.setSign(sign);
		
		StringBuilder bodyBuilder = new StringBuilder();
		bodyBuilder.append("entityId=").append(baseParam.getEntityId()).append("&sign=").append(baseParam.getSign()).
		append("&style=").append(baseParam.getStyle()).append("&app_key=").append(baseParam.getAppKey()).
		append("&app_version=").append(baseParam.getAppVersion()).append("&device_id=").append(baseParam.getDeviceId()).
		append("&format=").append(baseParam.getFormat()).append("&timestamp=").append(baseParam.getTimeStamp());
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForColorStyleConfig(), null, protocol);
		Response response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}
	
	
	
	
	@Override
	public Response menuRepeatConfig(String entityId, Integer status, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.TAKEOUT);
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
									
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////

		
		// 构造配置数据, 设置 HTTP 请求  header
		Map<String, String> bossPros = bossProperties.getBossProperties(env);	
		Map<String, String> httpHeader = new HashMap<String, String>();		
		httpHeader.put("version", bossPros.get("version"));
		httpHeader.put("sessionId", bossPros.get("sessionId"));
		httpHeader.put("isTest", bossPros.get("isTest"));
		
		
		// 设置 HTTP 请求 body
		BaseBossBean baseParam = new BaseBossBean();
		
		baseParam.setStatus(status);
		baseParam.setAppKey(bossPros.get("appKey"));
		
		baseParam.setAppVersion(bossPros.get("appVersion"));
		baseParam.setDeviceId(bossPros.get("deviceId"));
		
		baseParam.setFormat(bossPros.get("format"));
		baseParam.setSessionKey(bossPros.get("sessionKey"));
		
		baseParam.setTimeStamp(Long.toString(System.currentTimeMillis()));
		baseParam.setEntityId(entityId);
		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), baseParam.getBodyForMenuRepeatRemaindConfig());
		baseParam.setSign(sign);
		
		StringBuilder bodyBuilder = new StringBuilder();
		bodyBuilder.append("entityId=").append(baseParam.getEntityId()).append("&sign=").append(baseParam.getSign()).
		append("&status=").append(baseParam.getStatus()).append("&app_key=").append(baseParam.getAppKey()).
		append("&app_version=").append(baseParam.getAppVersion()).append("&device_id=").append(baseParam.getDeviceId()).
		append("&format=").append(baseParam.getFormat()).append("&timestamp=").append(baseParam.getTimeStamp());
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForMenuRepeatRemaindConfig(), null, protocol);
		Response response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}
	
	
	
	@Override
	public Response menuRepeatAddMenuConfig(String entityId, String menuListJson, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.MENUREPEATADDMENU);
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
									
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////

		
		// 构造配置数据, 设置 HTTP 请求  header
		Map<String, String> bossPros = bossProperties.getBossProperties(env);	
		Map<String, String> httpHeader = new HashMap<String, String>();		
		httpHeader.put("version", bossPros.get("version"));
		httpHeader.put("sessionId", bossPros.get("sessionId"));
		httpHeader.put("isTest", bossPros.get("isTest"));
		
		
		// 设置 HTTP 请求 body
		BaseBossBean baseParam = new BaseBossBean();
		
		baseParam.setIds_str(menuListJson);
		baseParam.setAppKey(bossPros.get("appKey"));
		
		baseParam.setAppVersion(bossPros.get("appVersion"));
		baseParam.setDeviceId(bossPros.get("deviceId"));
		
		baseParam.setFormat(bossPros.get("format"));
		baseParam.setSessionKey(bossPros.get("sessionKey"));
		
		baseParam.setTimeStamp(Long.toString(System.currentTimeMillis()));
		baseParam.setEntityId(entityId);
		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), baseParam.getBodyForMenuRepeatAddMenuConfig());
		baseParam.setSign(sign);
		
		StringBuilder bodyBuilder = new StringBuilder();
		bodyBuilder.append("entityId=").append(baseParam.getEntityId()).append("&sign=").append(baseParam.getSign()).
		append("&ids_str=").append(baseParam.getIds_str()).append("&app_key=").append(baseParam.getAppKey()).
		append("&app_version=").append(baseParam.getAppVersion()).append("&device_id=").append(baseParam.getDeviceId()).
		append("&format=").append(baseParam.getFormat()).append("&timestamp=").append(baseParam.getTimeStamp());
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForMenuRepeatAddMenuConfig(), null, protocol);
		Response response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}
	
	
	@Override
	public Response menuRepeatDeleteMenuConfig(String entityId, String menuId, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.MENUREPEATDELETEMENU);
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
									
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////

		
		// 构造配置数据, 设置 HTTP 请求  header
		Map<String, String> bossPros = bossProperties.getBossProperties(env);	
		Map<String, String> httpHeader = new HashMap<String, String>();		
		httpHeader.put("version", bossPros.get("version"));
		httpHeader.put("sessionId", bossPros.get("sessionId"));
		httpHeader.put("isTest", bossPros.get("isTest"));
		
		
		// 设置 HTTP 请求 body
		BaseBossBean baseParam = new BaseBossBean();
		
		baseParam.setMenu_id(menuId);
		baseParam.setAppKey(bossPros.get("appKey"));
		
		baseParam.setAppVersion(bossPros.get("appVersion"));
		baseParam.setDeviceId(bossPros.get("deviceId"));
		
		baseParam.setFormat(bossPros.get("format"));
		baseParam.setSessionKey(bossPros.get("sessionKey"));
		
		baseParam.setTimeStamp(Long.toString(System.currentTimeMillis()));
		baseParam.setEntityId(entityId);
		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), baseParam.getBodyForMenuRepeatDeleteMenuConfig());
		baseParam.setSign(sign);
		
		StringBuilder bodyBuilder = new StringBuilder();
		bodyBuilder.append("entityId=").append(baseParam.getEntityId()).append("&sign=").append(baseParam.getSign()).
		append("&menu_id=").append(baseParam.getMenu_id()).append("&app_key=").append(baseParam.getAppKey()).
		append("&app_version=").append(baseParam.getAppVersion()).append("&device_id=").append(baseParam.getDeviceId()).
		append("&format=").append(baseParam.getFormat()).append("&timestamp=").append(baseParam.getTimeStamp());
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForMenuRepeatDeleteMenuConfig(), null, protocol);
		Response response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}
	
	
	
	
	@Override
	public Response saveCouponConfig(String entityId, String couponJson, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.MENUREPEATDELETEMENU);
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
									
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////

		
		// 构造配置数据, 设置 HTTP 请求  header
		Map<String, String> bossPros = bossProperties.getBossProperties(env);	
		Map<String, String> httpHeader = new HashMap<String, String>();		
		httpHeader.put("version", bossPros.get("version"));
		httpHeader.put("sessionId", bossPros.get("sessionId"));
		httpHeader.put("isTest", bossPros.get("isTest"));
		
		
		// 设置 HTTP 请求 body
		BaseBossBean baseParam = new BaseBossBean();
		
		baseParam.setCoupon_promotion_str(couponJson);
		baseParam.setAppKey(bossPros.get("appKey"));
		
		baseParam.setAppVersion(bossPros.get("appVersion"));
		baseParam.setDeviceId(bossPros.get("deviceId"));
		
		baseParam.setFormat(bossPros.get("format"));
		baseParam.setSessionKey(bossPros.get("sessionKey"));
		
		baseParam.setTimeStamp(Long.toString(System.currentTimeMillis()));
		baseParam.setEntityId(entityId);
		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), baseParam.getBodyForSaveCouponConfig());
		baseParam.setSign(sign);
		
		StringBuilder bodyBuilder = new StringBuilder();
		bodyBuilder.append("entityId=").append(baseParam.getEntityId()).append("&sign=").append(baseParam.getSign()).
		append("&coupon_promotion_str=").append(baseParam.getCoupon_promotion_str()).append("&app_key=").append(baseParam.getAppKey()).
		append("&app_version=").append(baseParam.getAppVersion()).append("&device_id=").append(baseParam.getDeviceId()).
		append("&format=").append(baseParam.getFormat()).append("&timestamp=").append(baseParam.getTimeStamp());
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForSaveCouponConfig(), null, protocol);
		Response response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}
	
	
	
	
	public Response listCoupon(String entityId, Environment env) throws Exception{

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.LISTCOUPON);
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
									
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////

		
		// 构造配置数据, 设置 HTTP 请求  header
		Map<String, String> bossPros = bossProperties.getBossProperties(env);	
		Map<String, String> httpHeader = new HashMap<String, String>();		
		httpHeader.put("version", bossPros.get("version"));
		httpHeader.put("sessionId", bossPros.get("sessionId"));
		httpHeader.put("isTest", bossPros.get("isTest"));
		
		
		// 设置 HTTP 请求 body
		Map<String, String> params = new HashMap<String, String>();
		params.put("app_key", bossPros.get("appKey"));
		params.put("app_version", bossPros.get("appVersion"));
		params.put("device_id", bossPros.get("deviceId"));
		params.put("coupon_types_str", "0,1,20,21,22,23");
		
		params.put("format", bossPros.get("format"));
		params.put("timeStamp", Long.toString(System.currentTimeMillis()));
		params.put("entityId", entityId);  // 用于设置白名单
		params.put("entity_id", entityId); // 用于本身请求的参数
		
		params.put("session_key", bossPros.get("sessionKey"));
		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), params);
		params.put("sign", sign);
		
		StringBuilder bodyBuilder = new StringBuilder();
		for(Map.Entry<String, String> elementEntry : params.entrySet()){
			bodyBuilder.append("&" + elementEntry.getKey() + "=").append(elementEntry.getValue());
		}
		
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForListCoupon(), null, protocol);
		Response response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
	}
	
	
	@Override
	public JsonObject getCoupon(String entityId, String couponId, Environment env) throws Exception{
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.GETCOUPON);
		
		Response couponList = listCoupon(entityId, env);
		
		JsonArray couponListJsonObject = new JsonParser().parse(couponList.getResponseStr()).getAsJsonObject().get("data").getAsJsonArray();
		
		for(JsonElement element : couponListJsonObject){
			
			if(element.getAsJsonObject().get("id").getAsString().equalsIgnoreCase(couponId))
				return element.getAsJsonObject();
		}
		
		return null;
	}
	
	
	@Override
	public Response deleteCouponConfig(String entityId, String couponId, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.MENUREPEATDELETEMENU);
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
									
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////

		
		// 构造配置数据, 设置 HTTP 请求  header
		Map<String, String> bossPros = bossProperties.getBossProperties(env);	
		Map<String, String> httpHeader = new HashMap<String, String>();		
		httpHeader.put("version", bossPros.get("version"));
		httpHeader.put("sessionId", bossPros.get("sessionId"));
		httpHeader.put("isTest", bossPros.get("isTest"));
		
		
		// 设置 HTTP 请求 body
		BaseBossBean baseParam = new BaseBossBean();
		
		baseParam.setCoupon_promotion_id(couponId);
		baseParam.setAppKey(bossPros.get("appKey"));
		
		baseParam.setAppVersion(bossPros.get("appVersion"));
		baseParam.setDeviceId(bossPros.get("deviceId"));
		
		baseParam.setFormat(bossPros.get("format"));
		baseParam.setSessionKey(bossPros.get("sessionKey"));
		
		baseParam.setTimeStamp(Long.toString(System.currentTimeMillis()));
		baseParam.setEntityId(entityId);
		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), baseParam.getBodyForDeleteCouponConfig());
		baseParam.setSign(sign);
		
		StringBuilder bodyBuilder = new StringBuilder();
		bodyBuilder.append("entityId=").append(baseParam.getEntityId()).append("&sign=").append(baseParam.getSign()).
		append("&coupon_promotion_id=").append(baseParam.getCoupon_promotion_id()).append("&app_key=").append(baseParam.getAppKey()).
		append("&app_version=").append(baseParam.getAppVersion()).append("&device_id=").append(baseParam.getDeviceId()).
		append("&format=").append(baseParam.getFormat()).append("&timestamp=").append(baseParam.getTimeStamp());
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForDeleteCouponConfig(), null, protocol);
		Response response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}
	
	
	
	@Override
	public Response savePromotion(String entityId, String promotionJson, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.MENUREPEATDELETEMENU);
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
									
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////

		
		// 构造配置数据, 设置 HTTP 请求  header
		Map<String, String> bossPros = bossProperties.getBossProperties(env);	
		Map<String, String> httpHeader = new HashMap<String, String>();		
		httpHeader.put("version", bossPros.get("version"));
		httpHeader.put("sessionId", bossPros.get("sessionId"));
		httpHeader.put("isTest", bossPros.get("isTest"));
		
		
		// 设置 HTTP 请求 body
		BaseBossBean baseParam = new BaseBossBean();
		
		baseParam.setEntityType("0");
		baseParam.setSales_promotion_str(promotionJson);
		baseParam.setAppKey(bossPros.get("appKey"));
		
		baseParam.setAppVersion(bossPros.get("appVersion"));
		baseParam.setDeviceId(bossPros.get("deviceId"));
		
		baseParam.setFormat(bossPros.get("format"));
		baseParam.setSessionKey(bossPros.get("sessionKey"));
		
		baseParam.setTimeStamp(Long.toString(System.currentTimeMillis()));
		baseParam.setEntityId(entityId);
		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), baseParam.getBodyForSavePromotion());
		baseParam.setSign(sign);
		
		StringBuilder bodyBuilder = new StringBuilder();
		bodyBuilder.append("entityId=").append(baseParam.getEntityId()).append("&sign=").append(baseParam.getSign()).
		append("&sales_promotion_str=").append(baseParam.getSales_promotion_str()).append("&app_key=").append(baseParam.getAppKey()).
		append("&app_version=").append(baseParam.getAppVersion()).append("&device_id=").append(baseParam.getDeviceId()).
		append("&format=").append(baseParam.getFormat()).append("&timestamp=").append(baseParam.getTimeStamp()).
		append("&entityType=").append(baseParam.getEntityType());
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForSavePromotion(), null, protocol);
		Response response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}
	
	
	@Override
	public Response getPromotionList(String entityId, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.MENUREPEATDELETEMENU);
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
									
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////

		
		// 构造配置数据, 设置 HTTP 请求  header
		Map<String, String> bossPros = bossProperties.getBossProperties(env);	
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
		params.put("entityType", "0");    // 单店
		
		params.put("page", "1");
		params.put("page_size", "20");
		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), params);
		params.put("sign", sign);
		
		StringBuilder bodyBuilder = new StringBuilder();
		for(Map.Entry<String, String> elementEntry : params.entrySet()){
			bodyBuilder.append("&" + elementEntry.getKey() + "=").append(elementEntry.getValue());
		}
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForGetPromotionList(), null, protocol);
		Response response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}
	
	
	@Override
	public JsonObject getPromotion(String entityId, String promotionId, Environment env) throws Exception{
		
		Response promotionList = getPromotionList(entityId, env);
		JsonObject promotionJsonObject = new JsonParser().parse(promotionList.getResponseStr()).getAsJsonObject().get("data").getAsJsonObject();
		
		for(JsonElement element : promotionJsonObject.get("salesPromotionVoList").getAsJsonArray()){
			if(element.getAsJsonObject().get("id").getAsString().equalsIgnoreCase(promotionId))
				return element.getAsJsonObject();
		}
		
		return null;
	}
	
	
	@Override
	public Response deletePromotion(String entityId, String promotionId, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.MENUREPEATDELETEMENU);
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
									
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////

		
		// 构造配置数据, 设置 HTTP 请求  header
		Map<String, String> bossPros = bossProperties.getBossProperties(env);	
		Map<String, String> httpHeader = new HashMap<String, String>();		
		httpHeader.put("version", bossPros.get("version"));
		httpHeader.put("sessionId", bossPros.get("sessionId"));
		httpHeader.put("isTest", bossPros.get("isTest"));
		
		
		// 设置 HTTP 请求 body
		BaseBossBean baseParam = new BaseBossBean();
		
		baseParam.setCoupon_promotion_id(promotionId);
		baseParam.setAppKey(bossPros.get("appKey"));
		
		baseParam.setAppVersion(bossPros.get("appVersion"));
		baseParam.setDeviceId(bossPros.get("deviceId"));
		
		baseParam.setFormat(bossPros.get("format"));
		baseParam.setSessionKey(bossPros.get("sessionKey"));
		
		baseParam.setTimeStamp(Long.toString(System.currentTimeMillis()));
		baseParam.setEntityId(entityId);
		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), baseParam.getBodyForDeleteCouponConfig());
		baseParam.setSign(sign);
		
		StringBuilder bodyBuilder = new StringBuilder();
		bodyBuilder.append("entityId=").append(baseParam.getEntityId()).append("&sign=").append(baseParam.getSign()).
		append("&coupon_promotion_id=").append(baseParam.getCoupon_promotion_id()).append("&app_key=").append(baseParam.getAppKey()).
		append("&app_version=").append(baseParam.getAppVersion()).append("&device_id=").append(baseParam.getDeviceId()).
		append("&format=").append(baseParam.getFormat()).append("&timestamp=").append(baseParam.getTimeStamp());
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForDeleteCouponConfig(), null, protocol);
		Response response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}
	
	
	
	
	
	@Override
	public Response savePrivilege(String entityId, Privilege privilege, String privilegeJson, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.SAVEPRIVILEGE);
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
									
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////

		
		// 构造配置数据, 设置 HTTP 请求  header
		Map<String, String> bossPros = bossProperties.getBossProperties(env);	
		Map<String, String> httpHeader = new HashMap<String, String>();		
		httpHeader.put("version", bossPros.get("version"));
		httpHeader.put("sessionId", bossPros.get("sessionId"));
		httpHeader.put("isTest", bossPros.get("isTest"));
		
		
		// 设置 HTTP 请求 body
		BaseBossBean baseParam = new BaseBossBean();
		
		baseParam.setEntityType("0");
		baseParam.setCustomer_level(0);
		baseParam.setPrivilege(privilege, privilegeJson);
		baseParam.setAppKey(bossPros.get("appKey"));
		
		baseParam.setAppVersion(bossPros.get("appVersion"));
		baseParam.setDeviceId(bossPros.get("deviceId"));
		
		baseParam.setFormat(bossPros.get("format"));
		baseParam.setSessionKey(bossPros.get("sessionKey"));
		
		baseParam.setTimeStamp(Long.toString(System.currentTimeMillis()));
		baseParam.setEntityId(entityId);
		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), baseParam.getBodyForSavePrivilege(privilege));
		baseParam.setSign(sign);
		
		StringBuilder bodyBuilder = new StringBuilder();
		bodyBuilder.append("entityId=").append(baseParam.getEntityId()).append("&sign=").append(baseParam.getSign()).
		append("&" + privilege.getField() + "=").append(baseParam.getPrivilege(privilege)).append("&app_key=").append(baseParam.getAppKey()).
		append("&app_version=").append(baseParam.getAppVersion()).append("&device_id=").append(baseParam.getDeviceId()).
		append("&format=").append(baseParam.getFormat()).append("&timestamp=").append(baseParam.getTimeStamp()).
		append("&entityType=").append(baseParam.getEntityType()).append("&customer_level=").append(baseParam.getCustomer_level());
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPrivilegePath(privilege), null, protocol);
		Response response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}
	
	
	
	
	@Override
	public Response addCardPrivilege(String entityId, String cardPrivilegeJson, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.SAVEPRIVILEGE);
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
									
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////

		
		// 构造配置数据, 设置 HTTP 请求  header
		Map<String, String> bossPros = bossProperties.getBossProperties(env);	
		Map<String, String> httpHeader = new HashMap<String, String>();		
		httpHeader.put("version", bossPros.get("version"));
		httpHeader.put("sessionId", bossPros.get("sessionId"));
		httpHeader.put("isTest", bossPros.get("isTest"));
		
		
		// 设置 HTTP 请求 body
		BaseBossBean baseParam = new BaseBossBean();
		
		baseParam.setKind_card_str(cardPrivilegeJson);
		baseParam.setAppKey(bossPros.get("appKey"));
		
		baseParam.setAppVersion(bossPros.get("appVersion"));
		baseParam.setDeviceId(bossPros.get("deviceId"));
		
		baseParam.setFormat(bossPros.get("format"));
		baseParam.setSessionKey(bossPros.get("sessionKey"));
		
		baseParam.setTimeStamp(Long.toString(System.currentTimeMillis()));
		baseParam.setEntityId(entityId);
		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), baseParam.getBodyForSaveCardPrivilege());
		baseParam.setSign(sign);
		
		StringBuilder bodyBuilder = new StringBuilder();
		bodyBuilder.append("entityId=").append(baseParam.getEntityId()).append("&sign=").append(baseParam.getSign()).
		append("&kind_card_str=").append(baseParam.getKind_card_str()).append("&app_key=").append(baseParam.getAppKey()).
		append("&app_version=").append(baseParam.getAppVersion()).append("&device_id=").append(baseParam.getDeviceId()).
		append("&format=").append(baseParam.getFormat()).append("&timestamp=").append(baseParam.getTimeStamp());
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForAddCardPrivilege(), null, protocol);
		Response response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}
	
	
	
	@Override
	public Response deletePrivilege(String entityId, String privilegeId, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.DELETEPRIVILEGE);
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
									
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////

		
		// 构造配置数据, 设置 HTTP 请求  header
		Map<String, String> bossPros = bossProperties.getBossProperties(env);	
		Map<String, String> httpHeader = new HashMap<String, String>();		
		httpHeader.put("version", bossPros.get("version"));
		httpHeader.put("sessionId", bossPros.get("sessionId"));
		httpHeader.put("isTest", bossPros.get("isTest"));
		
		
		// 设置 HTTP 请求 body
		BaseBossBean baseParam = new BaseBossBean();
		
		baseParam.setLast_ver(0);
		
//		baseParam.setEntityType("0");
		baseParam.setCustomer_right_id(privilegeId);
		baseParam.setAppKey(bossPros.get("appKey"));
		
		baseParam.setAppVersion(bossPros.get("appVersion"));
		baseParam.setDeviceId(bossPros.get("deviceId"));
		
		baseParam.setFormat(bossPros.get("format"));
		baseParam.setSessionKey(bossPros.get("sessionKey"));
		
		baseParam.setTimeStamp(Long.toString(System.currentTimeMillis()));
		baseParam.setEntityId(entityId);
		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), baseParam.getBodyForDeletePrivilege());
		baseParam.setSign(sign);
		
		StringBuilder bodyBuilder = new StringBuilder();
		bodyBuilder.append("entityId=").append(baseParam.getEntityId()).append("&sign=").append(baseParam.getSign()).
		append("&customer_right_id=").append(baseParam.getCustomer_right_id()).append("&app_key=").append(baseParam.getAppKey()).
		append("&app_version=").append(baseParam.getAppVersion()).append("&device_id=").append(baseParam.getDeviceId()).
		append("&format=").append(baseParam.getFormat()).append("&timestamp=").append(baseParam.getTimeStamp()).
		append("&last_ver=").append(baseParam.getLast_ver());
//		append("&entityType=").append(baseParam.getEntityType());
		
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForDeletePrivilege(), null, protocol);
		Response response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}
	
	
	@Override
	public Response deleteCardPrivilege(String entityId, String cardPrivilegeId, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.DELETEPRIVILEGE);
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
									
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////

		
		// 构造配置数据, 设置 HTTP 请求  header
		Map<String, String> bossPros = bossProperties.getBossProperties(env);	
		Map<String, String> httpHeader = new HashMap<String, String>();		
		httpHeader.put("version", bossPros.get("version"));
		httpHeader.put("sessionId", bossPros.get("sessionId"));
		httpHeader.put("isTest", bossPros.get("isTest"));
		
		
		// 设置 HTTP 请求 body
		BaseBossBean baseParam = new BaseBossBean();
				
		baseParam.setId(cardPrivilegeId);
		baseParam.setAppKey(bossPros.get("appKey"));
		
		baseParam.setAppVersion(bossPros.get("appVersion"));
		baseParam.setDeviceId(bossPros.get("deviceId"));
		
		baseParam.setFormat(bossPros.get("format"));
		baseParam.setSessionKey(bossPros.get("sessionKey"));
		
		baseParam.setTimeStamp(Long.toString(System.currentTimeMillis()));
		baseParam.setEntityId(entityId);
		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), baseParam.getBodyForDeleteCardPrivilege());
		baseParam.setSign(sign);
		
		StringBuilder bodyBuilder = new StringBuilder();
		bodyBuilder.append("entityId=").append(baseParam.getEntityId()).append("&sign=").append(baseParam.getSign()).
		append("&id=").append(baseParam.getId()).append("&app_key=").append(baseParam.getAppKey()).
		append("&app_version=").append(baseParam.getAppVersion()).append("&device_id=").append(baseParam.getDeviceId()).
		append("&format=").append(baseParam.getFormat()).append("&timestamp=").append(baseParam.getTimeStamp());
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForDeleteCardPrivilege(), null, protocol);
		Response response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}
	
	
	
	@Override
	public Response deleteCustomPrivilege(String entityId, String privilegeId, String interestId, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.DELETECUSTOMPRIVILEGE);
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
									
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////

		
		// 构造配置数据, 设置 HTTP 请求  header
		Map<String, String> bossPros = bossProperties.getBossProperties(env);	
		Map<String, String> httpHeader = new HashMap<String, String>();		
		httpHeader.put("version", bossPros.get("version"));
		httpHeader.put("sessionId", bossPros.get("sessionId"));
		httpHeader.put("isTest", bossPros.get("isTest"));
		
		
		// 设置 HTTP 请求 body
		BaseBossBean baseParam = new BaseBossBean();
		
		baseParam.setLast_version(0);
//		baseParam.setEntityType("0");
		
		baseParam.setRight_interest_Id(interestId);
		baseParam.setCustomer_right_id(privilegeId);
		baseParam.setAppKey(bossPros.get("appKey"));
		
		baseParam.setAppVersion(bossPros.get("appVersion"));
		baseParam.setDeviceId(bossPros.get("deviceId"));
		
		baseParam.setFormat(bossPros.get("format"));
		baseParam.setSessionKey(bossPros.get("sessionKey"));
		
		baseParam.setTimeStamp(Long.toString(System.currentTimeMillis()));
		baseParam.setEntityId(entityId);
		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), baseParam.getBodyForDeleteCustomPrivilege());
		baseParam.setSign(sign);
		
		StringBuilder bodyBuilder = new StringBuilder();
		bodyBuilder.append("entityId=").append(baseParam.getEntityId()).append("&sign=").append(baseParam.getSign()).
		append("&customer_right_id=").append(baseParam.getCustomer_right_id()).append("&app_key=").append(baseParam.getAppKey()).
		append("&app_version=").append(baseParam.getAppVersion()).append("&device_id=").append(baseParam.getDeviceId()).
		append("&format=").append(baseParam.getFormat()).append("&timestamp=").append(baseParam.getTimeStamp()).
		append("&right_interest_Id=").append(baseParam.getRight_interest_Id()).
		append("&last_version=").append(baseParam.getLast_version());
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForDeleteCustomPrivilege(), null, protocol);
		Response response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}
	
	
	
	@Override
	public Response addCustomPrivilege(String entityId, String customPrivilegeJson, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.ADDCTUOMPRIVILEGE);
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
									
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////

		
		// 构造配置数据, 设置 HTTP 请求  header
		Map<String, String> bossPros = bossProperties.getBossProperties(env);	
		Map<String, String> httpHeader = new HashMap<String, String>();		
		httpHeader.put("version", bossPros.get("version"));
		httpHeader.put("sessionId", bossPros.get("sessionId"));
		httpHeader.put("isTest", bossPros.get("isTest"));
		
		
		// 设置 HTTP 请求 body
		BaseBossBean baseParam = new BaseBossBean();
		
		baseParam.setEntityType("0");  // 表示单店
		baseParam.setCustom_privilege_str(customPrivilegeJson);
		baseParam.setAppKey(bossPros.get("appKey"));
		
		baseParam.setAppVersion(bossPros.get("appVersion"));
		baseParam.setDeviceId(bossPros.get("deviceId"));
		
		baseParam.setFormat(bossPros.get("format"));
		baseParam.setSessionKey(bossPros.get("sessionKey"));
		
		baseParam.setTimeStamp(Long.toString(System.currentTimeMillis()));
		baseParam.setEntityId(entityId);
		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), baseParam.getBodyForAddCustomPrivilege());
		baseParam.setSign(sign);
		
		StringBuilder bodyBuilder = new StringBuilder();
		bodyBuilder.append("entityId=").append(baseParam.getEntityId()).append("&sign=").append(baseParam.getSign()).
		append("&custom_privilege_str=").append(baseParam.getCustom_privilege_str()).append("&app_key=").append(baseParam.getAppKey()).
		append("&app_version=").append(baseParam.getAppVersion()).append("&device_id=").append(baseParam.getDeviceId()).
		append("&format=").append(baseParam.getFormat()).append("&timestamp=").append(baseParam.getTimeStamp()).
		append("&entityType=").append(baseParam.getEntityType());
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForAddCustomPrivilege(), null, protocol);
		Response response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}
	
	
	
	@Override
	public Response deleteAllCustomPrivilege(String entityId, String customPrivilegeJson, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.DELETEALLCUSTOMPRIVILEGE);
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
									
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////

		
		// 构造配置数据, 设置 HTTP 请求  header
		Map<String, String> bossPros = bossProperties.getBossProperties(env);	
		Map<String, String> httpHeader = new HashMap<String, String>();		
		httpHeader.put("version", bossPros.get("version"));
		httpHeader.put("sessionId", bossPros.get("sessionId"));
		httpHeader.put("isTest", bossPros.get("isTest"));
		
		
		// 设置 HTTP 请求 body
		BaseBossBean baseParam = new BaseBossBean();
				
		baseParam.setEntityType("0"); // 表示单店
		
		baseParam.setCustom_privilege_str(customPrivilegeJson);
		baseParam.setAppKey(bossPros.get("appKey"));
		
		baseParam.setAppVersion(bossPros.get("appVersion"));
		baseParam.setDeviceId(bossPros.get("deviceId"));
		
		baseParam.setFormat(bossPros.get("format"));
		baseParam.setSessionKey(bossPros.get("sessionKey"));
		
		baseParam.setTimeStamp(Long.toString(System.currentTimeMillis()));
		baseParam.setEntityId(entityId);
		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), baseParam.getBodyForAddCustomPrivilege());
		baseParam.setSign(sign);
		
		StringBuilder bodyBuilder = new StringBuilder();
		bodyBuilder.append("entityId=").append(baseParam.getEntityId()).append("&sign=").append(baseParam.getSign()).
		append("&custom_privilege_str=").append(baseParam.getCustom_privilege_str()).append("&app_key=").append(baseParam.getAppKey()).
		append("&app_version=").append(baseParam.getAppVersion()).append("&device_id=").append(baseParam.getDeviceId()).
		append("&format=").append(baseParam.getFormat()).append("&timestamp=").append(baseParam.getTimeStamp()).
		append("&entityType=").append(baseParam.getEntityType());
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForAllDeleteCustomPrivilege(), null, protocol);
		Response response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}
	
	
	
	@Override
	public Response getAllPrivilegeDetail(String entityId, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.GETALLPRIVILEGEDETAIL);
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
									
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////

		
		// 构造配置数据, 设置 HTTP 请求  header
		Map<String, String> bossPros = bossProperties.getBossProperties(env);	
		Map<String, String> httpHeader = new HashMap<String, String>();		
		httpHeader.put("version", bossPros.get("version"));
		httpHeader.put("sessionId", bossPros.get("sessionId"));
		httpHeader.put("isTest", bossPros.get("isTest"));
		
		
		// 设置 HTTP 请求 body
		BaseBossBean baseParam = new BaseBossBean();
				
		baseParam.setAppKey(bossPros.get("appKey"));
		
		baseParam.setAppVersion(bossPros.get("appVersion"));
		baseParam.setDeviceId(bossPros.get("deviceId"));
		
		baseParam.setFormat(bossPros.get("format"));
		baseParam.setSessionKey(bossPros.get("sessionKey"));
		
		baseParam.setTimeStamp(Long.toString(System.currentTimeMillis()));
		baseParam.setEntityId(entityId);
		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), baseParam.getBodyFoGetAllPrivilegeDetail());
		baseParam.setSign(sign);
		
		StringBuilder bodyBuilder = new StringBuilder();
		bodyBuilder.append("entityId=").append(baseParam.getEntityId()).append("&sign=").append(baseParam.getSign()).
		append("&app_key=").append(baseParam.getAppKey()).append("&app_version=").append(baseParam.getAppVersion()).
		append("&device_id=").append(baseParam.getDeviceId()).append("&format=").append(baseParam.getFormat()).
		append("&timestamp=").append(baseParam.getTimeStamp());
		
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForGetAllPrivilegeDetail(), null, protocol);
		Response response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}
	
	
	
	@Override
	public Response getAllCardPrivilegeDetail(String entityId, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.GETALLCARDPRIVILEGEDETAIL);
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
									
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////

		
		// 构造配置数据, 设置 HTTP 请求  header
		Map<String, String> bossPros = bossProperties.getBossProperties(env);	
		Map<String, String> httpHeader = new HashMap<String, String>();		
		httpHeader.put("version", bossPros.get("version"));
		httpHeader.put("sessionId", bossPros.get("sessionId"));
		httpHeader.put("isTest", bossPros.get("isTest"));
		
		
		// 设置 HTTP 请求 body
		BaseBossBean baseParam = new BaseBossBean();
				
		baseParam.setAppKey(bossPros.get("appKey"));
		
		baseParam.setAppVersion(bossPros.get("appVersion"));
		baseParam.setDeviceId(bossPros.get("deviceId"));
		
		baseParam.setFormat(bossPros.get("format"));
		baseParam.setSessionKey(bossPros.get("sessionKey"));
		
		baseParam.setTimeStamp(Long.toString(System.currentTimeMillis()));
		baseParam.setEntityId(entityId);
		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), baseParam.getBodyFoGetAllPrivilegeDetail());
		baseParam.setSign(sign);
		
		StringBuilder bodyBuilder = new StringBuilder();
		bodyBuilder.append("entityId=").append(baseParam.getEntityId()).append("&sign=").append(baseParam.getSign()).
		append("&app_key=").append(baseParam.getAppKey()).append("&app_version=").append(baseParam.getAppVersion()).
		append("&device_id=").append(baseParam.getDeviceId()).append("&format=").append(baseParam.getFormat()).
		append("&timestamp=").append(baseParam.getTimeStamp());
		
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForGetAllCardPrivilegeDetail(), null, protocol);
		Response response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}
	
	
	@Override
	public Response getCustomPrivilegeList(String entityId, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.GETALLCARDPRIVILEGEDETAIL);
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
									
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////

		
		// 构造配置数据, 设置 HTTP 请求  header
		Map<String, String> bossPros = bossProperties.getBossProperties(env);	
		Map<String, String> httpHeader = new HashMap<String, String>();		
		httpHeader.put("version", bossPros.get("version"));
		httpHeader.put("sessionId", bossPros.get("sessionId"));
		httpHeader.put("isTest", bossPros.get("isTest"));
		
		
		// 设置 HTTP 请求 body
		BaseBossBean baseParam = new BaseBossBean();
			
		baseParam.setCustomer_level(0);
		baseParam.setAppKey(bossPros.get("appKey"));
		
		baseParam.setAppVersion(bossPros.get("appVersion"));
		baseParam.setDeviceId(bossPros.get("deviceId"));
		
		baseParam.setFormat(bossPros.get("format"));
		baseParam.setSessionKey(bossPros.get("sessionKey"));
		
		baseParam.setTimeStamp(Long.toString(System.currentTimeMillis()));
		baseParam.setEntityId(entityId);
		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), baseParam.getBodyFoGetCustomPrivilegeList());
		baseParam.setSign(sign);
		
		StringBuilder bodyBuilder = new StringBuilder();
		bodyBuilder.append("entityId=").append(baseParam.getEntityId()).append("&sign=").append(baseParam.getSign()).
		append("&app_key=").append(baseParam.getAppKey()).append("&app_version=").append(baseParam.getAppVersion()).
		append("&device_id=").append(baseParam.getDeviceId()).append("&format=").append(baseParam.getFormat()).
		append("&timestamp=").append(baseParam.getTimeStamp()).append("&customer_level=").append("0");
		
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForGetCustomPrivilegeList(), null, protocol);
		Response response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}
	
	
	@Override
	public Boolean deleteAllPrivilege(String entityId, CustomPrivilege customPrivilege, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.DELETEALLPRIVILEGEDETAIL);
		
		Response privilegeDetail = getAllPrivilegeDetail(entityId, env);
		if(privilegeDetail.getStatus() != 200){
			MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, "获取会员等级特权接口返回失败");
			return false;
		}
		
		
		JsonObject resp = new JsonParser().parse(privilegeDetail.getResponseStr()).getAsJsonObject();	
		if(resp.get("code").getAsInt() != 1){
			MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, "获取会员等级特权接口返回 code 验证失败");
			return false;
		}
		
		
		
		
		// 删除所有等级的特权
		for(JsonElement element : resp.get("data").getAsJsonObject().get("customerPrivilegeVos").getAsJsonArray()){
			
			
			if(element.getAsJsonObject().has("birthdayPrivilegeVo")){
				// 删除生日特权
				String customerRightId = element.getAsJsonObject().get("birthdayPrivilegeVo").
						getAsJsonObject().get("customerRightId").getAsString();
				deletePrivilege(entityId, customerRightId, env);
				 
			}
			
			if(element.getAsJsonObject().has("memoryPrivilegeVo")){
				// 删除纪念日特权
				String customerRightId = element.getAsJsonObject().get("memoryPrivilegeVo").
						getAsJsonObject().get("customerRightId").getAsString();
				deletePrivilege(entityId, customerRightId, env);
			}
			
			if(element.getAsJsonObject().has("couponPrivilegeVo")){
				// 赠券特权
				String customerRightId = element.getAsJsonObject().get("couponPrivilegeVo").
						getAsJsonObject().get("customerRightId").getAsString();
				deletePrivilege(entityId, customerRightId, env);
				
			}
			
			if(element.getAsJsonObject().has("customPrivilegeVo")){
				// 自定义特权
				String customerRightId = element.getAsJsonObject().get("customPrivilegeVo").
						getAsJsonObject().get("customerRightId").getAsString();
				JsonArray customPrivilegeDetailVos = element.getAsJsonObject().get("customPrivilegeVo").
						getAsJsonObject().get("customPrivilegeDetailVos").getAsJsonArray();
				
				for(JsonElement customElement : customPrivilegeDetailVos){
					
					String rightInterestId = customElement.getAsJsonObject().get("rightInterestId").getAsString();
//					String customPrivilegeId = customElement.getAsJsonObject().get("customPrivilegeId").getAsString();
					deleteCustomPrivilege(entityId, customerRightId, rightInterestId, env);
					
					
				}
				
				 
			}
			
			if(element.getAsJsonObject().has("cardPrivilegeVo")){
				// 特权卡
				String customerRightId = element.getAsJsonObject().get("cardPrivilegeVo").
						getAsJsonObject().get("customerRightId").getAsString();
				deletePrivilege(entityId, customerRightId, env);
			}
		}
		
		
		
		Response customPrivilegeDetail = getCustomPrivilegeList(entityId, env);
		JsonArray customDetailResp = new JsonParser().parse(customPrivilegeDetail.getResponseStr()).getAsJsonObject().get("data").getAsJsonArray();
		
		// 删除自定义列表中的自定义特权
		for(JsonElement element : customDetailResp){
			
			String customPrivilegeId = element.getAsJsonObject().get("customPrivilegeId").getAsString();
			String title = element.getAsJsonObject().get("title").getAsString();
			String content = element.getAsJsonObject().get("content").getAsString();
			Integer selectedStatus = element.getAsJsonObject().get("selectedStatus").getAsInt();
			
			CustomPrivilege customPrivilegeBean = new CustomPrivilege(customPrivilegeId, title, content, selectedStatus);
			deleteAllCustomPrivilege(entityId, gson.toJson(customPrivilegeBean), env);
			
		}
		
		
		
		// 删除特权卡库中的特权卡
		Response cardPrivilegeDetail = getAllCardPrivilegeDetail(entityId, env);
		JsonObject cardResp = new JsonParser().parse(cardPrivilegeDetail.getResponseStr()).getAsJsonObject();
		
		for(JsonElement element : cardResp.get("data").getAsJsonArray()){
			String id = element.getAsJsonObject().get("id").getAsString();
			deleteCardPrivilege(entityId, id, env);
		}
		
		
		return true;
		
	}
	
	
	
	
	@Override
	public Response queryKindCards(String entityId, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.QUERYKINDCARDS);
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
									
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////

		
		// 构造配置数据, 设置 HTTP 请求  header
		Map<String, String> bossPros = bossProperties.getBossProperties(env);	
		Map<String, String> httpHeader = new HashMap<String, String>();		
		httpHeader.put("version", bossPros.get("version"));
		httpHeader.put("sessionId", bossPros.get("sessionId"));
		httpHeader.put("isTest", bossPros.get("isTest"));
		
		
		// 设置 HTTP 请求 body
		BaseBossBean baseParam = new BaseBossBean();
			
		baseParam.setAppKey(bossPros.get("appKey"));
		
		baseParam.setAppVersion(bossPros.get("appVersion"));
		baseParam.setDeviceId(bossPros.get("deviceId"));
		
		baseParam.setFormat(bossPros.get("format"));
		baseParam.setSessionKey(bossPros.get("sessionKey"));
		
		baseParam.setTimeStamp(Long.toString(System.currentTimeMillis()));
		baseParam.setEntityId(entityId);
		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), baseParam.getBodyForQueryKindCards());
		baseParam.setSign(sign);
		
		StringBuilder bodyBuilder = new StringBuilder();
		bodyBuilder.append("entityId=").append(baseParam.getEntityId()).append("&sign=").append(baseParam.getSign()).
		append("&app_key=").append(baseParam.getAppKey()).append("&app_version=").append(baseParam.getAppVersion()).
		append("&device_id=").append(baseParam.getDeviceId()).append("&format=").append(baseParam.getFormat()).
		append("&timestamp=").append(baseParam.getTimeStamp());
		
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForQueryKindCards(), null, protocol);
		Response response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}
	
	
	
	@Override
	public Response saveRechargeRule(String entityId, String rechargeJson, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.QUERYKINDCARDS);
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
									
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////

		
		// 构造配置数据, 设置 HTTP 请求  header
		Map<String, String> bossPros = bossProperties.getBossProperties(env);	
		Map<String, String> httpHeader = new HashMap<String, String>();		
		httpHeader.put("version", bossPros.get("version"));
		httpHeader.put("sessionId", bossPros.get("sessionId"));
		httpHeader.put("isTest", bossPros.get("isTest"));
		
		
		// 设置 HTTP 请求 body
		BaseBossBean baseParam = new BaseBossBean();
			
		baseParam.setMoney_rule_str(rechargeJson);
		baseParam.setAppKey(bossPros.get("appKey"));
		
		baseParam.setAppVersion(bossPros.get("appVersion"));
		baseParam.setDeviceId(bossPros.get("deviceId"));
		
		baseParam.setFormat(bossPros.get("format"));
		baseParam.setSessionKey(bossPros.get("sessionKey"));
		
		baseParam.setTimeStamp(Long.toString(System.currentTimeMillis()));
		baseParam.setEntityId(entityId);
		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), baseParam.getBodyForSaveRechargeRule());
		baseParam.setSign(sign);
		
		StringBuilder bodyBuilder = new StringBuilder();
		bodyBuilder.append("entityId=").append(baseParam.getEntityId()).append("&sign=").append(baseParam.getSign()).
		append("&app_key=").append(baseParam.getAppKey()).append("&app_version=").append(baseParam.getAppVersion()).
		append("&device_id=").append(baseParam.getDeviceId()).append("&format=").append(baseParam.getFormat()).
		append("&timestamp=").append(baseParam.getTimeStamp()).append("&money_rule_str=").append(baseParam.getMoney_rule_str());
		
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForSaveRechargeRule(), null, protocol);
		Response response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}
	
	
	
	@Override
	public Response deleteAllRechargeRule(String entityId, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.DELETEKINDCARDS);
		
		// 获取充值优惠 id, 构建参数
		Response querySwitch = queryKindCards(entityId, env);
		JsonObject queryResp = new JsonParser().parse(querySwitch.getResponseStr()).getAsJsonObject();
		
		List<String> idList = new ArrayList<String>();
		for(JsonElement element : queryResp.get("data").getAsJsonArray()){
			
			JsonArray recharges = element.getAsJsonObject().get("moneyRules").getAsJsonArray();
			for(JsonElement recharge : recharges){
				
				String id = recharge.getAsJsonObject().get("id").getAsString();
				idList.add(id);
				
			}
			
		}
		
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
									
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////

		
		// 构造配置数据, 设置 HTTP 请求  header
		Map<String, String> bossPros = bossProperties.getBossProperties(env);	
		Map<String, String> httpHeader = new HashMap<String, String>();		
		httpHeader.put("version", bossPros.get("version"));
		httpHeader.put("sessionId", bossPros.get("sessionId"));
		httpHeader.put("isTest", bossPros.get("isTest"));
		
		
		// 设置 HTTP 请求 body
		BaseBossBean baseParam = new BaseBossBean();
			
		baseParam.setIds_str(gson.toJson(idList));
		baseParam.setAppKey(bossPros.get("appKey"));
		
		baseParam.setAppVersion(bossPros.get("appVersion"));
		baseParam.setDeviceId(bossPros.get("deviceId"));
		
		baseParam.setFormat(bossPros.get("format"));
		baseParam.setSessionKey(bossPros.get("sessionKey"));
		
		baseParam.setTimeStamp(Long.toString(System.currentTimeMillis()));
		baseParam.setEntityId(entityId);
		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), baseParam.getBodyForDeleteAllRecharge());
		baseParam.setSign(sign);
		
		StringBuilder bodyBuilder = new StringBuilder();
		bodyBuilder.append("entityId=").append(baseParam.getEntityId()).append("&sign=").append(baseParam.getSign()).
		append("&app_key=").append(baseParam.getAppKey()).append("&app_version=").append(baseParam.getAppVersion()).
		append("&device_id=").append(baseParam.getDeviceId()).append("&format=").append(baseParam.getFormat()).
		append("&timestamp=").append(baseParam.getTimeStamp()).append("&ids_str=").append(baseParam.getIds_str());
		
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForDeleteAllRecharge(), null, protocol);
		Response response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}
	
	
	
	
	
	@Override
	public Response selfRecharge(String entityId, String selfRechargeJson, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.RECHARGERULES);
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
									
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////

		
		// 构造配置数据, 设置 HTTP 请求  header
		Map<String, String> bossPros = bossProperties.getBossProperties(env);	
		Map<String, String> httpHeader = new HashMap<String, String>();		
		httpHeader.put("version", bossPros.get("version"));
		httpHeader.put("sessionId", bossPros.get("sessionId"));
		httpHeader.put("isTest", bossPros.get("isTest"));
		
		
		// 设置 HTTP 请求 body
		BaseBossBean baseParam = new BaseBossBean();
			
		baseParam.setSelf_recharge_str_list(selfRechargeJson);
		baseParam.setAppKey(bossPros.get("appKey"));
		
		baseParam.setAppVersion(bossPros.get("appVersion"));
		baseParam.setDeviceId(bossPros.get("deviceId"));
		
		baseParam.setFormat(bossPros.get("format"));
		baseParam.setSessionKey(bossPros.get("sessionKey"));
		
		baseParam.setTimeStamp(Long.toString(System.currentTimeMillis()));
		baseParam.setEntityId(entityId);
		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), baseParam.getBodyForSelfRecharge());
		baseParam.setSign(sign);
		
		StringBuilder bodyBuilder = new StringBuilder();
		bodyBuilder.append("entityId=").append(baseParam.getEntityId()).append("&sign=").append(baseParam.getSign()).
		append("&app_key=").append(baseParam.getAppKey()).append("&app_version=").append(baseParam.getAppVersion()).
		append("&device_id=").append(baseParam.getDeviceId()).append("&format=").append(baseParam.getFormat()).
		append("&timestamp=").append(baseParam.getTimeStamp()).append("&self_recharge_str_list=").append(baseParam.getSelf_recharge_str_list());
		
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForSelfRecharge(), null, protocol);
		Response response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}
	
	
	
	
	@Override
	public Response saveGift(String entityId, String giftJson, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.RECHARGERULES);
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
									
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////

		
		// 构造配置数据, 设置 HTTP 请求  header
		Map<String, String> bossPros = bossProperties.getBossProperties(env);	
		Map<String, String> httpHeader = new HashMap<String, String>();		
		httpHeader.put("version", bossPros.get("version"));
		httpHeader.put("sessionId", bossPros.get("sessionId"));
		httpHeader.put("isTest", bossPros.get("isTest"));
		
		
		// 设置 HTTP 请求 body
		BaseBossBean baseParam = new BaseBossBean();
			
		baseParam.setGift_str(giftJson);
		baseParam.setAppKey(bossPros.get("appKey"));
		
		baseParam.setAppVersion(bossPros.get("appVersion"));
		baseParam.setDeviceId(bossPros.get("deviceId"));
		
		baseParam.setFormat(bossPros.get("format"));
		baseParam.setSessionKey(bossPros.get("sessionKey"));
		
		baseParam.setTimeStamp(Long.toString(System.currentTimeMillis()));
		baseParam.setEntityId(entityId);
		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), baseParam.getBodyForSaveGift());
		baseParam.setSign(sign);
		
		StringBuilder bodyBuilder = new StringBuilder();
		bodyBuilder.append("entityId=").append(baseParam.getEntityId()).append("&sign=").append(baseParam.getSign()).
		append("&app_key=").append(baseParam.getAppKey()).append("&app_version=").append(baseParam.getAppVersion()).
		append("&device_id=").append(baseParam.getDeviceId()).append("&format=").append(baseParam.getFormat()).
		append("&timestamp=").append(baseParam.getTimeStamp()).append("&gift_str=").append(baseParam.getGift_str());
		
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForSaveGift(), null, protocol);
		Response response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}
	
	
	
	
	@Override
	public Response getGiftList(String entityId, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.GETGIFT);
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
									
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////

		
		// 构造配置数据, 设置 HTTP 请求  header
		Map<String, String> bossPros = bossProperties.getBossProperties(env);	
		Map<String, String> httpHeader = new HashMap<String, String>();		
		httpHeader.put("version", bossPros.get("version"));
		httpHeader.put("sessionId", bossPros.get("sessionId"));
		httpHeader.put("isTest", bossPros.get("isTest"));
		
		
		// 设置 HTTP 请求 body
		BaseBossBean baseParam = new BaseBossBean();
			
		baseParam.setAppKey(bossPros.get("appKey"));
		
		baseParam.setAppVersion(bossPros.get("appVersion"));
		baseParam.setDeviceId(bossPros.get("deviceId"));
		
		baseParam.setFormat(bossPros.get("format"));
		baseParam.setSessionKey(bossPros.get("sessionKey"));
		
		baseParam.setTimeStamp(Long.toString(System.currentTimeMillis()));
		baseParam.setEntityId(entityId);
		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), baseParam.getBodyForQueryKindCards());
		baseParam.setSign(sign);
		
		StringBuilder bodyBuilder = new StringBuilder();
		bodyBuilder.append("entityId=").append(baseParam.getEntityId()).append("&sign=").append(baseParam.getSign()).
		append("&app_key=").append(baseParam.getAppKey()).append("&app_version=").append(baseParam.getAppVersion()).
		append("&device_id=").append(baseParam.getDeviceId()).append("&format=").append(baseParam.getFormat()).
		append("&timestamp=").append(baseParam.getTimeStamp());
		
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForGetGiftList(), null, protocol);
		Response response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}
	
	
	
	
	@Override
	public Response deleteAllGift(String entityId, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.DELETEALLGIFT);
		
		// 获取充值优惠 id, 构建参数
		Response giftSwitch = getGiftList(entityId, env);
		JsonObject queryResp = new JsonParser().parse(giftSwitch.getResponseStr()).getAsJsonObject();
		
		List<String> idList = new ArrayList<String>();
		for(JsonElement element : queryResp.get("data").getAsJsonArray()){
			
			JsonArray recharges = element.getAsJsonObject().get("gifts").getAsJsonArray();
			for(JsonElement recharge : recharges){
				
				String id = recharge.getAsJsonObject().get("id").getAsString();
				idList.add(id);
				
			}
			
		}
		
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
									
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////

		
		// 构造配置数据, 设置 HTTP 请求  header
		Map<String, String> bossPros = bossProperties.getBossProperties(env);	
		Map<String, String> httpHeader = new HashMap<String, String>();		
		httpHeader.put("version", bossPros.get("version"));
		httpHeader.put("sessionId", bossPros.get("sessionId"));
		httpHeader.put("isTest", bossPros.get("isTest"));
		
		
		// 设置 HTTP 请求 body
		BaseBossBean baseParam = new BaseBossBean();
			
		baseParam.setIds_str(gson.toJson(idList));
		baseParam.setAppKey(bossPros.get("appKey"));
		
		baseParam.setAppVersion(bossPros.get("appVersion"));
		baseParam.setDeviceId(bossPros.get("deviceId"));
		
		baseParam.setFormat(bossPros.get("format"));
		baseParam.setSessionKey(bossPros.get("sessionKey"));
		
		baseParam.setTimeStamp(Long.toString(System.currentTimeMillis()));
		baseParam.setEntityId(entityId);
		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), baseParam.getBodyForDeleteAllRecharge());
		baseParam.setSign(sign);
		
		StringBuilder bodyBuilder = new StringBuilder();
		bodyBuilder.append("entityId=").append(baseParam.getEntityId()).append("&sign=").append(baseParam.getSign()).
		append("&app_key=").append(baseParam.getAppKey()).append("&app_version=").append(baseParam.getAppVersion()).
		append("&device_id=").append(baseParam.getDeviceId()).append("&format=").append(baseParam.getFormat()).
		append("&timestamp=").append(baseParam.getTimeStamp()).append("&ids_str=").append(baseParam.getIds_str());
		
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForDeleteAllGift(), null, protocol);
		Response response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}
	
	
	
	@Override
	public Response saveGiftConvert(String entityId, String giftConvertJson, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.SAVEGIFTCONVERT);
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
									
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////

		
		// 构造配置数据, 设置 HTTP 请求  header
		Map<String, String> bossPros = bossProperties.getBossProperties(env);	
		Map<String, String> httpHeader = new HashMap<String, String>();		
		httpHeader.put("version", bossPros.get("version"));
		httpHeader.put("sessionId", bossPros.get("sessionId"));
		httpHeader.put("isTest", bossPros.get("isTest"));
		
		
		// 设置 HTTP 请求 body
		BaseBossBean baseParam = new BaseBossBean();
			
		baseParam.setGift_convert_str(giftConvertJson);
		baseParam.setAppKey(bossPros.get("appKey"));
		
		baseParam.setAppVersion(bossPros.get("appVersion"));
		baseParam.setDeviceId(bossPros.get("deviceId"));
		
		baseParam.setFormat(bossPros.get("format"));
		baseParam.setSessionKey(bossPros.get("sessionKey"));
		
		baseParam.setTimeStamp(Long.toString(System.currentTimeMillis()));
		baseParam.setEntityId(entityId);
		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), baseParam.getBodyForSaveGiftConvert());
		baseParam.setSign(sign);
		
		StringBuilder bodyBuilder = new StringBuilder();
		bodyBuilder.append("entityId=").append(baseParam.getEntityId()).append("&sign=").append(baseParam.getSign()).
		append("&app_key=").append(baseParam.getAppKey()).append("&app_version=").append(baseParam.getAppVersion()).
		append("&device_id=").append(baseParam.getDeviceId()).append("&format=").append(baseParam.getFormat()).
		append("&timestamp=").append(baseParam.getTimeStamp()).append("&gift_convert_str=").append(baseParam.getGift_convert_str());
		
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForSaveGiftConvert(), null, protocol);
		Response response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}
	
	
	
	@Override
	public Response getGiftConvertList(String entityId, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.GETGIFTCONVERT);
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
									
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////

		
		// 构造配置数据, 设置 HTTP 请求  header
		Map<String, String> bossPros = bossProperties.getBossProperties(env);	
		Map<String, String> httpHeader = new HashMap<String, String>();		
		httpHeader.put("version", bossPros.get("version"));
		httpHeader.put("sessionId", bossPros.get("sessionId"));
		httpHeader.put("isTest", bossPros.get("isTest"));
		
		
		// 设置 HTTP 请求 body
		BaseBossBean baseParam = new BaseBossBean();
			
		baseParam.setAppKey(bossPros.get("appKey"));
		
		baseParam.setAppVersion(bossPros.get("appVersion"));
		baseParam.setDeviceId(bossPros.get("deviceId"));
		
		baseParam.setFormat(bossPros.get("format"));
		baseParam.setSessionKey(bossPros.get("sessionKey"));
		
		baseParam.setTimeStamp(Long.toString(System.currentTimeMillis()));
		baseParam.setEntityId(entityId);
		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), baseParam.getBodyForQueryKindCards());
		baseParam.setSign(sign);
		
		StringBuilder bodyBuilder = new StringBuilder();
		bodyBuilder.append("entityId=").append(baseParam.getEntityId()).append("&sign=").append(baseParam.getSign()).
		append("&app_key=").append(baseParam.getAppKey()).append("&app_version=").append(baseParam.getAppVersion()).
		append("&device_id=").append(baseParam.getDeviceId()).append("&format=").append(baseParam.getFormat()).
		append("&timestamp=").append(baseParam.getTimeStamp());
		
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForGetGiftConvertList(), null, protocol);
		Response response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}
	
	
	
	
	@Override
	public Response updateMenuLabel(String entityId, String menuLabelJson, String menuId, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.UPDATEMENULABEL);
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
									
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////

		
		// 构造配置数据, 设置 HTTP 请求  header
		Map<String, String> bossPros = bossProperties.getBossProperties(env);	
		Map<String, String> httpHeader = new HashMap<String, String>();		
		httpHeader.put("version", bossPros.get("version"));
		httpHeader.put("sessionId", bossPros.get("sessionId"));
		httpHeader.put("isTest", bossPros.get("isTest"));
		
		
		// 设置 HTTP 请求 body
		BaseBossBean baseParam = new BaseBossBean();
			
		baseParam.setMenu_id(menuId);
		baseParam.setMenu_label_str(menuLabelJson);
		baseParam.setAppKey(bossPros.get("appKey"));
		
		baseParam.setAppVersion(bossPros.get("appVersion"));
		baseParam.setDeviceId(bossPros.get("deviceId"));
		
		baseParam.setFormat(bossPros.get("format"));
		baseParam.setSessionKey(bossPros.get("sessionKey"));
		
		baseParam.setTimeStamp(Long.toString(System.currentTimeMillis()));
		baseParam.setEntityId(entityId);
		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), baseParam.getBodyForUpdateMenuLabel());
		baseParam.setSign(sign);
		
		StringBuilder bodyBuilder = new StringBuilder();
		bodyBuilder.append("entityId=").append(baseParam.getEntityId()).append("&sign=").append(baseParam.getSign()).
		append("&app_key=").append(baseParam.getAppKey()).append("&app_version=").append(baseParam.getAppVersion()).
		append("&device_id=").append(baseParam.getDeviceId()).append("&format=").append(baseParam.getFormat()).
		append("&timestamp=").append(baseParam.getTimeStamp()).append("&menu_label_str=").append(baseParam.getMenu_label_str()).
		append("&menu_id=").append(baseParam.getMenu_id());
		
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForUpdateMenuLabel(), null, protocol);
		Response response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}
	
	

	@Override
	public Response saveEntityConfig(String entityId, Integer trunOn, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.MENURECOMMAND);
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
									
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////

		
		// 构造配置数据, 设置 HTTP 请求  header
		Map<String, String> bossPros = bossProperties.getBossProperties(env);	
		Map<String, String> httpHeader = new HashMap<String, String>();		
		httpHeader.put("version", bossPros.get("version"));
		httpHeader.put("sessionId", bossPros.get("sessionId"));
		httpHeader.put("isTest", bossPros.get("isTest"));
		
		
		// 设置 HTTP 请求 body
		BaseBossBean baseParam = new BaseBossBean();
			
		baseParam.setIs_turn_on(trunOn);
		baseParam.setAppKey(bossPros.get("appKey"));
		
		baseParam.setAppVersion(bossPros.get("appVersion"));
		baseParam.setDeviceId(bossPros.get("deviceId"));
		
		baseParam.setFormat(bossPros.get("format"));
		baseParam.setSessionKey(bossPros.get("sessionKey"));
		
		baseParam.setTimeStamp(Long.toString(System.currentTimeMillis()));
		baseParam.setEntityId(entityId);
		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), baseParam.getBodyForSaveEntityConfig());
		baseParam.setSign(sign);
		
		StringBuilder bodyBuilder = new StringBuilder();
		bodyBuilder.append("entityId=").append(baseParam.getEntityId()).append("&sign=").append(baseParam.getSign()).
		append("&app_key=").append(baseParam.getAppKey()).append("&app_version=").append(baseParam.getAppVersion()).
		append("&device_id=").append(baseParam.getDeviceId()).append("&format=").append(baseParam.getFormat()).
		append("&timestamp=").append(baseParam.getTimeStamp()).append("&is_turn_on=").append(baseParam.getIs_turn_on());
		
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForSaveEntityConfig(), null, protocol);
		Response response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}
	
	
	
	
	@Override
	public Response queryPlanConfig(String entityId, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.UPDATEMENULABEL);
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
									
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////

		
		// 构造配置数据, 设置 HTTP 请求  header
		Map<String, String> bossPros = bossProperties.getBossProperties(env);	
		Map<String, String> httpHeader = new HashMap<String, String>();		
		httpHeader.put("version", bossPros.get("version"));
		httpHeader.put("sessionId", bossPros.get("sessionId"));
		httpHeader.put("isTest", bossPros.get("isTest"));
		
		
		// 设置 HTTP 请求 body
		BaseBossBean baseParam = new BaseBossBean();
			
		baseParam.setAppKey(bossPros.get("appKey"));
		
		baseParam.setAppVersion(bossPros.get("appVersion"));
		baseParam.setDeviceId(bossPros.get("deviceId"));
		
		baseParam.setFormat(bossPros.get("format"));
		baseParam.setSessionKey(bossPros.get("sessionKey"));
		
		baseParam.setTimeStamp(Long.toString(System.currentTimeMillis()));
		baseParam.setEntityId(entityId);
		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), baseParam.getBodyForQueryKindCards());
		baseParam.setSign(sign);
		
		StringBuilder bodyBuilder = new StringBuilder();
		bodyBuilder.append("entityId=").append(baseParam.getEntityId()).append("&sign=").append(baseParam.getSign()).
		append("&app_key=").append(baseParam.getAppKey()).append("&app_version=").append(baseParam.getAppVersion()).
		append("&device_id=").append(baseParam.getDeviceId()).append("&format=").append(baseParam.getFormat()).
		append("&timestamp=").append(baseParam.getTimeStamp());
		
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForQueryAllConfig(), null, protocol);
		Response response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}
	
	
	@Override
	public Response savePlanConfig(String entityId, String planConfigJson, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.MENURECOMMAND);
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
									
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////

		
		// 构造配置数据, 设置 HTTP 请求  header
		Map<String, String> bossPros = bossProperties.getBossProperties(env);	
		Map<String, String> httpHeader = new HashMap<String, String>();		
		httpHeader.put("version", bossPros.get("version"));
		httpHeader.put("sessionId", bossPros.get("sessionId"));
		httpHeader.put("isTest", bossPros.get("isTest"));
		
		
		// 设置 HTTP 请求 body
		BaseBossBean baseParam = new BaseBossBean();
			
		baseParam.setPlan_config_json(planConfigJson);
		baseParam.setAppKey(bossPros.get("appKey"));
		
		baseParam.setAppVersion(bossPros.get("appVersion"));
		baseParam.setDeviceId(bossPros.get("deviceId"));
		
		baseParam.setFormat(bossPros.get("format"));
		baseParam.setSessionKey(bossPros.get("sessionKey"));
		
		baseParam.setTimeStamp(Long.toString(System.currentTimeMillis()));
		baseParam.setEntityId(entityId);
		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), baseParam.getBodyForSavePlanConfig());
		baseParam.setSign(sign);
		
		StringBuilder bodyBuilder = new StringBuilder();
		bodyBuilder.append("entityId=").append(baseParam.getEntityId()).append("&sign=").append(baseParam.getSign()).
		append("&app_key=").append(baseParam.getAppKey()).append("&app_version=").append(baseParam.getAppVersion()).
		append("&device_id=").append(baseParam.getDeviceId()).append("&format=").append(baseParam.getFormat()).
		append("&timestamp=").append(baseParam.getTimeStamp()).append("&plan_config_json=").append(baseParam.getPlan_config_json());
		
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForSavePlanConfig(), null, protocol);
		Response response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}
	
	
	
	
	@Override
	public Response saveTemplateConfig(String entityId, Integer flag, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.MENUTEMPLATE);
		
		
		// 设置 HTTPS 相关信息
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
									
		
		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////

		
		// 构造配置数据, 设置 HTTP 请求  header
		Map<String, String> bossPros = bossProperties.getBossProperties(env);	
		Map<String, String> httpHeader = new HashMap<String, String>();		
		httpHeader.put("version", bossPros.get("version"));
		httpHeader.put("sessionId", bossPros.get("sessionId"));
		httpHeader.put("isTest", bossPros.get("isTest"));
		
		
		// 设置 HTTP 请求 body

		Map<String, String> params = new HashMap<String, String>();
		params.put("is_template_on", Integer.toString(flag));
		params.put("app_key", bossPros.get("appKey"));
		params.put("app_version", bossPros.get("appVersion"));
		
		params.put("device_id", bossPros.get("deviceId"));
		params.put("format", bossPros.get("format"));
		params.put("session_key", bossPros.get("sessionKey"));

		params.put("timeStamp", Long.toString(System.currentTimeMillis()));
		params.put("entityId", entityId);
		params.put("entity_id", entityId);
		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), params);
		params.put("sign", sign);
		
		StringBuilder bodyBuilder = new StringBuilder();
		
		int loop = 0;
		for(Map.Entry<String, String> elemeEntry : params.entrySet()){
			
			loop++;
			if(loop == 1)
				bodyBuilder.append(elemeEntry.getKey() + "=").append(elemeEntry.getValue());
			else
				bodyBuilder.append("&" + elemeEntry.getKey() + "=").append(elemeEntry.getValue());
		}

	
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForSaveTemplateConfig(), null, protocol);
		Response response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}
	
	
	
	
	@Override
	public Response queryMenuTemplate(String entityId, Environment env) throws Exception {
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.QUERYMENUTEMPLATE);
		
		
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
		BaseBossBean baseParam = new BaseBossBean();					
		baseParam.setAppKey(bossPros.get("appKey"));
		
		baseParam.setAppVersion(bossPros.get("appVersion"));
		baseParam.setDeviceId(bossPros.get("deviceId"));
		
		baseParam.setFormat(bossPros.get("format"));
		baseParam.setSessionKey(bossPros.get("sessionKey"));
		
		baseParam.setTimeStamp(Long.toString(System.currentTimeMillis()));
		baseParam.setEntityId(entityId);
		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), baseParam.getBodyForQueryForceMenu());
		baseParam.setSign(sign);
		
		// 构造表单形式的    body 内容
		StringBuilder bodyBuilder = new StringBuilder();		
		bodyBuilder.append("entityId=").append(baseParam.getEntityId()).append("&sign=").append(baseParam.getSign()).
		append("&session_key=").append(baseParam.getSessionKey()).append("&app_key=").append(baseParam.getAppKey()).
		append("&app_version=").append(baseParam.getAppVersion()).append("&device_id=").append(baseParam.getDeviceId()).
		append("&format=").append(baseParam.getFormat()).append("&timestamp=").append(baseParam.getTimeStamp());
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForQueryMenuTemplate(), null, protocol);
		response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}

	
	
	@Override
	public Response modifyTemplateConfig(String entityId, String templateName, String coverPath, String shopTemplateId, Environment env) throws Exception {
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.UPDATEMENUTEMPLATE);
		
		
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
		params.put("sort_code", "0");
		params.put("is_hidden", "0");
		params.put("cpi", "5000");
		
		params.put("template_name", templateName);
		params.put("cover_path", coverPath);
		params.put("shop_template_id", shopTemplateId);
		
		params.put("app_key", bossPros.get("appKey"));
		params.put("app_version", bossPros.get("appVersion"));
		params.put("device_id", bossPros.get("deviceId"));

		params.put("format", bossPros.get("format"));
		params.put("timeStamp", Long.toString(System.currentTimeMillis()));
		params.put("entityId", entityId);  // 用于设置白名单
		params.put("entity_id", entityId); // 用于本身请求的参数
		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), params);
		params.put("sign", sign);
		
		// 构造表单形式的    body 内容
		StringBuilder bodyBuilder = new StringBuilder();
		
		for(Map.Entry<String, String> elementEntry : params.entrySet()){
			bodyBuilder.append("&" + elementEntry.getKey() + "=").append(elementEntry.getValue());
		}
		
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForModifyMenuTemplate(), null, protocol);
		response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
		
	}





	@Override
	public Response menuConfigUpdate(BaseParamBean baseParamBean, Object menuObject, Boolean onSelf, Environment env) throws Exception {
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, BossContents.InfraClient.MENUONSELF);
		
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
		BossConfig bossConfig = new BossConfig();	
		
		String menuId = JSONPath.read(menuObject.toString(), "$.id").toString();
		String kindMenuId = JSONPath.read(menuObject.toString(), "$.kindMenuId").toString();
		String menuName = JSONPath.read(menuObject.toString(), "$.name").toString();
		Double price = Double.valueOf(JSONPath.read(menuObject.toString(), "$.price").toString());
		String menuTag = ""; // JSONPath.read(menuObject.toString(), "$.specialtagString").toString();
		
		String menuStr = bossConfig.MenuSet(menuId, kindMenuId, menuName, menuTag, price, onSelf);	
		String menuPropStr = bossConfig.MenuPropSet(baseParamBean.getEntityId(), menuId, onSelf);
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("menu_str", menuStr);
		params.put("menu_prop_str", menuPropStr);
		
		params.put("app_key", bossPros.get("appKey"));
		params.put("app_version", bossPros.get("appVersion"));
		params.put("device_id", bossPros.get("deviceId"));

		params.put("format", bossPros.get("format"));
		params.put("timeStamp", Long.toString(System.currentTimeMillis()));
		params.put("entityId", baseParamBean.getEntityId());  // 用于设置白名单
		params.put("entity_id", baseParamBean.getEntityId()); // 用于本身请求的参数
		
		String sign = MD5Utils.generateSignForBossAPI(bossPros.get("secret"), params);
		params.put("sign", sign);
		
		// 构造表单形式的    body 内容
		StringBuilder bodyBuilder = new StringBuilder();
		
		for(Map.Entry<String, String> elementEntry : params.entrySet()){
			bodyBuilder.append("&" + elementEntry.getKey() + "=").append(elementEntry.getValue());
		}
		
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("bossURL"), PathForHTTP.getPathForMenuConfigUpdate(), null, protocol);
		response = httpClientService.doPostWithForm(url, httpHeader, bodyBuilder.toString());
		
		return response;
	}
	
	
	
	
}
