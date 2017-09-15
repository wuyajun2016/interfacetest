package com.dfire.qa.meal.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dfire.qa.meal.bean.BaseParamBean;
import com.dfire.qa.meal.bean.Response;
import com.dfire.qa.meal.constant.Constants;
import com.dfire.qa.meal.enums.Environment;
import com.dfire.qa.meal.httpclient.HTTPClientService;
import com.dfire.qa.meal.logger.LoggerMarkers;
import com.dfire.qa.meal.logger.MealLoggerFactory;
import com.dfire.qa.meal.props.AuthProperties;
import com.dfire.qa.meal.props.HostProperties;
import com.dfire.qa.meal.service.IOAuthService;
import com.dfire.qa.meal.utils.HTTPRequestUtil;
import com.dfire.qa.meal.utils.MD5Utils;
import com.dfire.qa.meal.utils.PathForHTTP;



@Service
public class OAuthServiceImpl implements IOAuthService{

	@Resource
	private HostProperties hostProperties;
	
	@Resource
	private AuthProperties authProperties;
	
	@Resource
	private HTTPClientService httpClientService;

	
	
	
	@Override
	public Response seatCodeOAuth(BaseParamBean baseParamBean, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.SEATTYPE);
		
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
		
		Response response = null;
		
		Map<String, String> query = new HashMap<String, String>();
		query.put("xtoken", baseParamBean.getXtoken());	

		List<String> path = new ArrayList<String>();
		for(String element : PathForHTTP.getPathForOAuth())
			path.add(element);
		path.add(baseParamBean.getEntityId());
		path.add(baseParamBean.getSeatCode());
		path.add(MD5Utils.generatorKey(baseParamBean.getEntityId() + baseParamBean.getSeatCode()));
		
		
		// 计算 URL 并发送 GET 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("serverURL"), path, query, protocol);
		response = httpClientService.doGet(url, null);

		return response;
		
	}




	@Override
	public Response globalCodeOAuth(BaseParamBean baseParamBean, String consumerCode, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.SEATTYPE);
		
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
		
		Response response = null;
		
		Map<String, String> query = new HashMap<String, String>();
		query.put("xtoken", baseParamBean.getXtoken());	

		List<String> path = new ArrayList<String>();
		for(String element : PathForHTTP.getPathForOAuthConsumerCode())
			path.add(element);
		path.add(consumerCode);
	
		
		// 计算 URL 并发送 GET 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("serverURL"), path, query, protocol);
		response = httpClientService.doGet(url, null);
		
		return response;
	}




	@Override
	public Response entityIdOAuth(BaseParamBean baseParamBean, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.SEATTYPE);
		
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
		
		Response response = null;
		
		Map<String, String> query = new HashMap<String, String>();
		query.put("xtoken", baseParamBean.getXtoken());	

		List<String> path = new ArrayList<String>();
		for(String element : PathForHTTP.getPathForOAuthEntityId())
			path.add(element);
		path.add(baseParamBean.getEntityId());
		path.add(MD5Utils.generatorKey(baseParamBean.getEntityId()));
	
		
		// 计算 URL 并发送 GET 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("serverURL"), path, query, protocol);
		response = httpClientService.doGet(url, null);
		
		return response;
	}




	@Override
	public Response menuCodeOAuth(BaseParamBean baseParamBean, String menuId, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.SEATTYPE);
		
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
		
		Response response = null;
		
		Map<String, String> query = new HashMap<String, String>();
		query.put("xtoken", baseParamBean.getXtoken());	

		List<String> path = new ArrayList<String>();
		for(String element : PathForHTTP.getPathForOAuthMenuCode())
			path.add(element);
		path.add(baseParamBean.getEntityId());
		path.add(menuId);
		path.add(MD5Utils.generatorKey(baseParamBean.getEntityId() + menuId));
	
		
		// 计算 URL 并发送 GET 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("serverURL"), path, query, protocol);
		response = httpClientService.doGet(url, null);
		
		return response;
		
	}

	
	

}
