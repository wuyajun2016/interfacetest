package com.dfire.qa.meal.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.dfire.qa.meal.props.AuthProperties;
import com.dfire.qa.meal.props.HostProperties;
import com.dfire.qa.meal.service.IQRCodeService;
import com.dfire.qa.meal.utils.HTTPRequestUtil;
import com.dfire.qa.meal.utils.MD5Utils;
import com.dfire.qa.meal.utils.PathForHTTP;

@Service
public class QRCodeServiceImpl implements IQRCodeService{
	
	@Resource
	private HostProperties hostProperties;
	
	@Resource
	private AuthProperties authProperties;
	
	@Resource
	private HTTPClientService httpClientService;
	
	
	@Override
	public Response scanQRCode(Map<String, String> header, List<String> URIPath, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.QRCODEUNIVERSAL);
		
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
		
		Response response = null;		
		
		// 计算 URL 并发送 Get 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("serverURL"), URIPath, null, protocol);
		response = httpClientService.doGet(url, header); 
		
		
		return response;
	}
	
	
	@Override
	public Response oauthQRCodeForSeat(BaseParamBean baseParamBean, Map<String, String> header, Environment env)  throws Exception{

		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.QRCODESEAT);
		
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
		
		Response response = null;

		List<String> path = new ArrayList<String>();
		path.add("s");
		path.add(baseParamBean.getEntityId());
		path.add(baseParamBean.getSeatCode());
		path.add(MD5Utils.generatorKey(baseParamBean.getEntityId() + baseParamBean.getSeatCode()));
		
		// 计算 URL 并发送 Get 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("serverURL"), path, null, protocol);
		response = httpClientService.doGet(url, header); 
		
		
		return response;

	}


	@Override
	public Response oauthQRCodeForShop(BaseParamBean baseParamBean, Environment env) throws Exception {
		
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.QRCODESHOP);
		
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
		
		
		Response response = null;
		
		List<String> path = new ArrayList<String>();
		path.add("s");
		path.add(baseParamBean.getEntityId());
		path.add(MD5Utils.generatorKey(baseParamBean.getEntityId()));
		
		// 计算 URL 并发送 Get 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("serverURL"), path, null, protocol);
		response = httpClientService.doGet(url, null); 
		
		return response;
	}


	@Override
	public Response getInitDataForShop(BaseParamBean baseParamBean, Environment env) throws Exception {
		
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.SHOPINIT);
		
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
				
		Response response = null;
	
		
		Map<String, String> query = new HashMap<String, String>();
		query.put("xtoken", baseParamBean.getXtoken());
		query.put("entity_id", baseParamBean.getEntityId());
		query.put("seat_code", baseParamBean.getSeatCode());
		
		// 计算 URL 并发送 Get 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("serverURL"), PathForHTTP.getPathForLoadState(), query, protocol);
		response = httpClientService.doGet(url, null); 		
		
		return response;
		
	}


	@Override
	public Response getPersonInfo(BaseParamBean baseParamBean, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.PERSONALINFO);
		
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
		
		Response response = null;

		
		Map<String, String> query = new HashMap<String, String>();
		query.put("xtoken", baseParamBean.getXtoken());
		query.put("entity_id", baseParamBean.getEntityId());
		
		// 计算 URL 并发送 Get 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("serverURL"), PathForHTTP.getPathForGetPersonInfo(), query, protocol);
		response = httpClientService.doGet(url, null); 		
		
		return response;

	}


	@Override
	public Response getUserInfo(BaseParamBean baseParamBean, Environment env) throws Exception {


		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.USERINFO);
		
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
		
		Response response = null;

		Map<String, String> query = new HashMap<String, String>();
		query.put("xtoken", baseParamBean.getXtoken());
		
		// 计算 URL 并发送 Get 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("serverURL"), PathForHTTP.getPathForGetUserInfo(), query, protocol);
		response = httpClientService.doGet(url, null); 	
		
		return response;

	}

	
	
	
	@Override
	public Response shareForShop(BaseParamBean baseParamBean, Environment env) throws Exception {
		
				
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.SHAREFORSHOP);
		
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
		
		Response response = null;
		
		// 请求路径
		List<String> path = new ArrayList<String>();
		path.add("shop/v1");
		path.add(baseParamBean.getEntityId());
		path.add("share");
					
		Map<String, String> query = new HashMap<String, String>();
		query.put("xtoken", baseParamBean.getXtoken());
		
		// 计算 URL 并发送 Get 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("serverURL"), path, query, protocol);
		response = httpClientService.doGet(url, null);
			
		return response;

	}
	
	
	
	
	@Override
	public Map<String, Response> scanCode(BaseParamBean baseParamBean, Environment env) throws Exception {
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.SCANSEATCODE);
		
		// 二维码扫桌码入口
		Response scanSeatCode = oauthQRCodeForSeat(baseParamBean, null, env); 
		if(200 != scanSeatCode.getStatus())
			throw new Exception(Constants.Message.SCANSEATCODEERROR);
		
		
		// 获取初始化数据
		Response initForShop = getInitDataForShop(baseParamBean, env);
		if(200 != initForShop.getStatus())
			throw new Exception(Constants.Message.GETSHOPINITINFOERROR);
		
		
		// 首页会员等级信息详情
		Response personInfo = getPersonInfo(baseParamBean, env);
		if(200 != personInfo.getStatus())
			throw new Exception(Constants.Message.GETMEMBERINFOERROR);

					
		// 查找用户个人信息 
		Response userInfo = getUserInfo(baseParamBean, env);
		if(200 != userInfo.getStatus())
			throw new Exception(Constants.Message.GETUSERINFOERROR);
		
		
		// 店铺分享URL的信息（包括图片、文案）接口
		Response shareForShop = shareForShop(baseParamBean, env);
		if(200 != shareForShop.getStatus())
			throw new Exception(Constants.Message.GETSHAREOFSHOPINFOERROR);
		
		
		Map<String, Response> response = new HashMap<String, Response>();
		response.put("scanSeatCode", scanSeatCode);
		response.put("initForShop", initForShop);
		
		response.put("personInfo", personInfo);		
		response.put("userInfo", userInfo);
		response.put("shareForShop", shareForShop);
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.SCANSEATCODESUC);
		
		return response;
	}


	@Override
	public Map<String, Response> scanShopCode(BaseParamBean baseParamBean, Environment env) throws Exception {
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.SCANSHOPCODE);
		
		Response scanShopCode = oauthQRCodeForShop(baseParamBean, env);
		if(200 != scanShopCode.getStatus())
			throw new Exception(Constants.Message.SCANSHOPCODEERROR);
		
		
		// 获取初始化数据
		Response initForShop = getInitDataForShop(baseParamBean, env);
		if(200 != initForShop.getStatus())
			throw new Exception(Constants.Message.GETSHOPINITINFOERROR);
		
		// 店铺分享URL的信息（包括图片、文案）接口
		Response shareForShop = shareForShop(baseParamBean, env);
		if(200 != shareForShop.getStatus())
			throw new Exception(Constants.Message.GETSHAREOFSHOPINFOERROR);
		
		Map<String, Response> response = new HashMap<String, Response>();
		response.put("scanShopCode", scanShopCode);
		response.put("initForShop", initForShop);
		response.put("shareForShop", shareForShop);
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.SCANSHOPCODESUC);
		
		return response;
	}


	


	

}
