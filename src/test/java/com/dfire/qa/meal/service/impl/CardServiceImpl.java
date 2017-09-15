package com.dfire.qa.meal.service.impl;

import java.util.HashMap;
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
import com.dfire.qa.meal.props.HostProperties;
import com.dfire.qa.meal.service.ICardService;
import com.dfire.qa.meal.utils.HTTPRequestUtil;
import com.dfire.qa.meal.utils.PathForHTTP;
import com.google.gson.Gson;


@Service
public class CardServiceImpl implements ICardService{

	
	@Resource
	HostProperties hostProperties;
	
	@Resource
	HTTPClientService httpClientService;
	
	
	@Override
	public Response getCardsList(BaseParamBean baseParamBean, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.GETCARDSLIST);
		
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
		
		Response response = null;
		
		Map<String, String> query = new HashMap<String, String>();
		query.put("xtoken", baseParamBean.getXtoken());
		query.put("entity_id", baseParamBean.getEntityId());
		
		
		// 计算 URL 并发送 GET 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("serverURL"), PathForHTTP.getPathForGetCardsList(), query, protocol);
		response = httpClientService.doGet(url, null);
					
		
		return response;
	}

	
	@Override
	public Response getCardDetail(BaseParamBean baseParamBean, String cardId, String kindCardId, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.GETCARDDETAIL);
		
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
		
		Response response = null;
		
		Map<String, String> query = new HashMap<String, String>();
		query.put("xtoken", baseParamBean.getXtoken());
		query.put("entity_id", baseParamBean.getEntityId());
		query.put("card_id", cardId);
		query.put("kind_card_id", kindCardId);
		
		
		// 计算 URL 并发送 GET 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("serverURL"), PathForHTTP.getPathForGetCardDetail(), query, protocol);
		response = httpClientService.doGet(url, null);
					
		
		return response;
	}

	
	@Override
	public Response getCardsPayment(BaseParamBean baseParamBean, String cardId, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.GETCARDSPAYMENT);
		
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
		
		Response response = null;
		
		Map<String, String> query = new HashMap<String, String>();
		query.put("xtoken", baseParamBean.getXtoken());
		query.put("entity_id", baseParamBean.getEntityId());
		query.put("card_id", cardId);
		
		
		// 计算 URL 并发送 GET 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("serverURL"), PathForHTTP.getPathForGetCardsPayment(), query, protocol);
		response = httpClientService.doGet(url, null);
					
		
		return response;
	}


	@Override
	public Response getAllMyCardList(BaseParamBean baseParamBean, Integer page, Integer pageSize, Environment env) throws Exception {
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.GETALLMYCARD);
		
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
		
		Response response = null;
		
		Map<String, String> query = new HashMap<String, String>();
		query.put("xtoken", baseParamBean.getXtoken());
//		query.put("entity_id", baseParamBean.getEntityId()); // 如果加了 entityId 表示只获取本店会员卡，而忽略其他店的会员卡
		query.put("page", Integer.toString(page));
		query.put("page_size", Integer.toString(pageSize));
		query.put("loading", "false");
		query.put("end", "false");
		
		// 计算 URL 并发送 GET 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("serverURL"), PathForHTTP.getPathForGetAllMyCard(), query, protocol);
		response = httpClientService.doGet(url, null);
					
		
		return response;
	}
	
	
	
	@Override
	public Response getAllMyCouponList(BaseParamBean baseParamBean, Integer page, Integer pageSize, Environment env) throws Exception {
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.GETALLMYCOUPON);
		
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
		
		Response response = null;
		
		Map<String, String> query = new HashMap<String, String>();
		query.put("xtoken", baseParamBean.getXtoken());
		query.put("page", Integer.toString(page));
		query.put("page_size", Integer.toString(pageSize));
		query.put("loading", "false");
		query.put("end", "false");
		
		// 计算 URL 并发送 GET 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("serverURL"), PathForHTTP.getPathForGetAllMyCoupon(), query, protocol);
		response = httpClientService.doGet(url, null);
					
		
		return response;
	}
	

}
