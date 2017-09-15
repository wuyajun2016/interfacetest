package com.dfire.testcase.function.boss;

import java.util.Map;

import org.apache.log4j.Logger;


import com.dfire.testcase.function.util.base.WechatBaseUtil;
import com.dfire.wechat.util.HttpRequestEx;
import com.dfire.wechat.util.Response;
import com.google.gson.Gson;

public class RequestForBoss {
	
	private static final Logger logger = Logger.getLogger(WechatBaseUtil.class);
	private static Gson gson = new Gson();

	

	/**
	 * 保存必选商品设置
	 * 保存必选商品设置：/menu/v1/save_force_menu  
	 */
	public static Response saveForceMenu(HttpRequestEx httpRequest, Map<String, String> httpHeader, BaseBossBean baseParamBean){
		
		Response response = null;
		try{
			// 构造表单形式的    body 内容
			StringBuilder bodyBuilder = new StringBuilder();
			bodyBuilder.append("entityId=").append(baseParamBean.getEntityId()).append("&force_config=").append(gson.toJson(baseParamBean.getForceConfig())).
			append("&sign=").append(baseParamBean.getSign()).append("&session_key=").append(baseParamBean.getSessionKey()).append("&app_key=").
			append(baseParamBean.getAppKey()).append("&app_version=").append(baseParamBean.getAppVersion()).append("&device_id=").append(baseParamBean.getDeviceId()).
			append("&format=").append(baseParamBean.getFormat()).append("&timestamp=").append(baseParamBean.getTimeStamp());
			
			response = httpRequest.post(PathForBoss.getPathForSaveForceMenu(), null, httpHeader, bodyBuilder.toString()); 
		}catch(Exception e){
			logger.info(e.toString());
		}
		
		return response;
	}
	
	
	/**
	 * 删除必选商品设置
	 * 删除必选商品设置：/menu/v1/remove_force_menu  
	 */
	public static Response removeForceMenu(HttpRequestEx httpRequest, Map<String, String> httpHeader, BaseBossBean baseParamBean){
		
		Response response = null;
		try{
			// 构造表单形式的    body 内容
			StringBuilder bodyBuilder = new StringBuilder();
			bodyBuilder.append("entityId=").append(baseParamBean.getEntityId()).append("&config_id=").append(baseParamBean.getConfigId()).
			append("&sign=").append(baseParamBean.getSign()).append("&session_key=").append(baseParamBean.getSessionKey()).append("&app_key=").
			append(baseParamBean.getAppKey()).append("&app_version=").append(baseParamBean.getAppVersion()).append("&device_id=").append(baseParamBean.getDeviceId()).
			append("&format=").append(baseParamBean.getFormat()).append("&timestamp=").append(baseParamBean.getTimeStamp());
			
			response = httpRequest.post(PathForBoss.getPathForRemoveForceMenu(), null, httpHeader, bodyBuilder.toString()); 
		}catch(Exception e){
			logger.info(e.toString());
		}
		
		return response;
	}
	
	
	/**
	 * 获取必选商品列表
	 * 获取必选商品列表：/menu/v1/query_force_menu_list  
	 */
	public static Response queryForceMenuList(HttpRequestEx httpRequest, Map<String, String> httpHeader, BaseBossBean baseParamBean ){
		
		Response response = null;
		try{
			// 构造表单形式的    body 内容
			StringBuilder bodyBuilder = new StringBuilder();
			bodyBuilder.append("entityId=").append(baseParamBean.getEntityId()).append("&sign=").append(baseParamBean.getSign()).
			append("&session_key=").append(baseParamBean.getSessionKey()).append("&app_key=").append(baseParamBean.getAppKey()).
			append("&app_version=").append(baseParamBean.getAppVersion()).append("&device_id=").append(baseParamBean.getDeviceId()).
			append("&format=").append(baseParamBean.getFormat()).append("&timestamp=").append(baseParamBean.getTimeStamp());
			
			
			response = httpRequest.post(PathForBoss.getPathForGetForceMenuList(), null, httpHeader, bodyBuilder.toString()); 
		}catch(Exception e){
			logger.info(e.toString());
		}
		
		return response;
	}
	
	
	
	/**
	 * 设置 掌柜端 扫桌位码 或者 扫店码 时的  预付款  开关
	 * 对应的 URL：/boss-api/config_set/v1/save  
	 */
	public static Response prePaySwitch(HttpRequestEx httpRequest, Map<String, String> httpHeader, BaseBossBean baseParamBean ){
		
		Response response = null;
		try{
			// 构造表单形式的    body 内容
			StringBuilder bodyBuilder = new StringBuilder();
			bodyBuilder.append("entityId=").append(baseParamBean.getEntityId()).append("&sign=").append(baseParamBean.getSign()).
			append("&ids_str=").append(baseParamBean.getIds_str()).append("&app_key=").append(baseParamBean.getAppKey()).
			append("&app_version=").append(baseParamBean.getAppVersion()).append("&device_id=").append(baseParamBean.getDeviceId()).
			append("&format=").append(baseParamBean.getFormat()).append("&timestamp=").append(baseParamBean.getTimeStamp());
			
			
			response = httpRequest.post(PathForBoss.getPathForPrePaySwitch(), null, httpHeader, bodyBuilder.toString()); 
			
		}catch(Exception e){
			logger.info(e.toString());
		}
		
		return response;
	}
	
	
	
	/**
	 * 设置 掌柜端 商品的基本属性设置开关
	 * 对应的 URL：/boss-api/menu/v1/save_or_update_menu  
	 */
	public static Response menuConfigUpdate(HttpRequestEx httpRequest, Map<String, String> httpHeader, BaseBossBean baseParamBean ){
		
		Response response = null;
		try{
			// 构造表单形式的    body 内容
			StringBuilder bodyBuilder = new StringBuilder();
			bodyBuilder.append("entityId=").append(baseParamBean.getEntityId()).append("&sign=").append(baseParamBean.getSign()).
			append("&menu_str=").append(baseParamBean.getMenu_str()).append("&app_key=").append(baseParamBean.getAppKey()).
			append("&app_version=").append(baseParamBean.getAppVersion()).append("&device_id=").append(baseParamBean.getDeviceId()).
			append("&format=").append(baseParamBean.getFormat()).append("&timestamp=").append(baseParamBean.getTimeStamp()).
			append("&menu_prop_str=").append(baseParamBean.getMenu_prop_str());
			
			
			response = httpRequest.post(PathForBoss.getPathForMenuConfigUpdate(), null, httpHeader, bodyBuilder.toString()); 
			
		}catch(Exception e){
			logger.info(e.toString());
		}
		
		return response;
	}
	
	
	
	/**
	 * 获取必选商品列表
	 * 获取必选商品列表：/menu/v1/batch_set_entity_config  
	 * 该接口有待完善
	 */
	public static Response setEntityConfig(HttpRequestEx httpRequest,  Map<String, String> httpHeader, BaseBossBean baseParamBean){
		
		Response response = null;
		try{
			// 构造表单形式的    body 内容
			StringBuilder bodyBuilder = new StringBuilder();
			bodyBuilder.append("config_map_json=").append(gson.toJson(baseParamBean.getForceConfig()));
			
			response = httpRequest.post(PathForBoss.getPathForSetEntityConfig(), null, httpHeader, bodyBuilder.toString()); 
		}catch(Exception e){
			logger.info(e.toString());
		}
		
		return response;
	}
	
	
	/**
	 * 设置自动领券开关
	 * 设置自动领券开关：/promotion/v1/set_auto_send_and_coupon
	 * 该接口有待完善
	 */
	public static Response setAutoGetCoupon(HttpRequestEx httpRequest, Map<String, String> httpHeader, BaseBossBean baseParamBean){
		
		Response response = null;
		try{
			// 构造表单形式的    body 内容
			StringBuilder bodyBuilder = new StringBuilder();
			bodyBuilder.append("is_auto_send=").append(gson.toJson(baseParamBean.getForceConfig()));
			
			response = httpRequest.post(PathForBoss.getPathForSetAutoGetCoupon(), null, httpHeader, bodyBuilder.toString()); 
		}catch(Exception e){
			logger.info(e.toString());
		}
		
		return response;
	}
	
	
	

}
