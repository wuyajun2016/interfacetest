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
import com.dfire.qa.meal.service.IShopService;
import com.dfire.qa.meal.utils.HTTPRequestUtil;
import com.dfire.qa.meal.utils.PathForHTTP;
import com.dfire.soa.consumer.param.NearbyShop;
import com.google.gson.Gson;


@Service
public class ShopServiceImpl implements IShopService{


	@Resource
	private HostProperties hostProperties;
	
	@Resource
	private AuthProperties authProperties;
	
	@Resource
	private HTTPClientService httpClientService;
	
	
	private Gson gson = new Gson();
	
	@Override
	public Response getNearbyShops(BaseParamBean baseParamBean, String bussinessType, String bussinessId, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.NEARBYSHOP);
		
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
		
		Response response = null;
		
		Map<String, String> query = new HashMap<String, String>();
		query.put("xtoken", baseParamBean.getXtoken());	
		query.put("business_type", bussinessType);
		query.put("business_id", bussinessId);
		
		NearbyShop nearbyShop = new NearbyShop();
		nearbyShop.setLatitude("30.29824");
		nearbyShop.setLongitude("120.1298");
		nearbyShop.setPage(1);
		nearbyShop.setPageSize(20);
		
		// 计算 URL 并发送 GET 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("serverURL"), PathForHTTP.getPathForGetNearbyShop(), query, protocol);
		response = httpClientService.doPost(url, null, gson.toJson(nearbyShop));
					
		
		return response;
	}

	
	
	
	@Override
	public Response getSeatType(BaseParamBean baseParamBean, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.SEATTYPE);
		
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
		
		Response response = null;
		
		Map<String, String> query = new HashMap<String, String>();
		query.put("xtoken", baseParamBean.getXtoken());	
		query.put("entity_id", baseParamBean.getEntityId());
		
		
		// 计算 URL 并发送 GET 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("serverURL"), PathForHTTP.getPathForGetSeatType(), query, protocol);
		response = httpClientService.doGet(url, null);

		return response;
		
	}




	@Override
	public Response getShopState(BaseParamBean baseParamBean, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.GETSTATE);
		
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
		
		Response response = null;
		
		Map<String, String> query = new HashMap<String, String>();
		query.put("xtoken", baseParamBean.getXtoken());	
		query.put("entity_id", baseParamBean.getEntityId());
		query.put("seat_code", baseParamBean.getSeatCode());
		
		
		// 计算 URL 并发送 GET 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("serverURL"), PathForHTTP.getPathForLoadState(), query, protocol);
		response = httpClientService.doGet(url, null);

		return response;
	}




	@Override
	public Response getShopBaseInfo(BaseParamBean baseParamBean, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.BASEINFO);
		
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
		
		Response response = null;
		
		Map<String, String> query = new HashMap<String, String>();
		query.put("xtoken", baseParamBean.getXtoken());	
		
		List<String> path = new ArrayList<String>();
		for(String element : PathForHTTP.getPathForBaseInfo())
			path.add(element);
		path.add(baseParamBean.getEntityId());
		
		// 计算 URL 并发送 GET 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("serverURL"), path, query, protocol);
		response = httpClientService.doGet(url, null);

		return response;
	}




	@Override
	public Response getShopMoment(BaseParamBean baseParamBean, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.BASEINFO);
		
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
		
		Response response = null;
		
		Map<String, String> query = new HashMap<String, String>();
		query.put("xtoken", baseParamBean.getXtoken());	
		
		List<String> path = new ArrayList<String>();
		for(String element : PathForHTTP.getPathForBaseInfo())
			path.add(element);
		path.add(baseParamBean.getEntityId());
		path.add("moment");
		
		// 计算 URL 并发送 GET 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("serverURL"), path, query, protocol);
		response = httpClientService.doGet(url, null);

		return response;
	}




	@Override
	public Response getShopAllInfo(BaseParamBean baseParamBean, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.BASEINFO);
		
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
		
		Response response = null;
		
		Map<String, String> query = new HashMap<String, String>();
		query.put("xtoken", baseParamBean.getXtoken());	
		
		List<String> path = new ArrayList<String>();
		for(String element : PathForHTTP.getPathForBaseInfo())
			path.add(element);
		path.add(baseParamBean.getEntityId());
		path.add("all_info");
		
		// 计算 URL 并发送 GET 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("serverURL"), path, query, protocol);
		response = httpClientService.doGet(url, null);

		return response;
	}




	@Override
	public Response getShopShare(BaseParamBean baseParamBean, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.BASEINFO);
		
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
		
		Response response = null;
		
		Map<String, String> query = new HashMap<String, String>();
		query.put("xtoken", baseParamBean.getXtoken());	
		
		List<String> path = new ArrayList<String>();
		for(String element : PathForHTTP.getPathForBaseInfo())
			path.add(element);
		path.add(baseParamBean.getEntityId());
		path.add("share");
		
		
		// 计算 URL 并发送 GET 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("serverURL"), path, query, protocol);
		response = httpClientService.doGet(url, null);

		return response;
	}
	
	
	

}
