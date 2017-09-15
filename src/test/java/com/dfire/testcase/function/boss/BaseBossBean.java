package com.dfire.testcase.function.boss;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class BaseBossBean {
	
	// just for test
	private String entityId;
	
	
	// real parameter
	private ForceConfig forceConfig;
	private String configId;
	private String sign;
	
	private String appKey;
	private String appVersion;
	private String deviceId;
	
	private String format;
	private String sessionKey;	
	private String timeStamp;
	
	// config string for basic setting
	private String ids_str;
	
	
	// config string for menu setting
	private String menu_str;
	private String menu_prop_str;
	
	
	private Gson gson = new Gson();
	
	
	/**
	 * 用于计算 sign
	 * 针对设置必选菜
	 * @return
	 */
	public Map<String, String> getBodyForSaveForceMenu(){
		
		Map<String, String> saveForMenuMap = new HashMap<String, String>();
		saveForMenuMap.put("app_key", getAppKey());
		saveForMenuMap.put("app_version", getAppVersion());
		saveForMenuMap.put("device_id", getDeviceId());
		
		saveForMenuMap.put("force_config", gson.toJson(getForceConfig()));
		saveForMenuMap.put("format", getFormat());
		saveForMenuMap.put("session_key", getSessionKey());
		
		saveForMenuMap.put("timestamp", getTimeStamp());
		saveForMenuMap.put("entityId", getEntityId());
		
		return saveForMenuMap;
	}
	
	/**
	 * 用于计算 sign
	 * 用于移除必选菜
	 * @return
	 */
	public Map<String, String> getBodyForRemoveForceMenu(){
		
		Map<String, String> saveForMenuMap = new HashMap<String, String>();
		saveForMenuMap.put("app_key", getAppKey());
		saveForMenuMap.put("app_version", getAppVersion());
		saveForMenuMap.put("device_id", getDeviceId());
		
		saveForMenuMap.put("config_id", getConfigId());
		saveForMenuMap.put("format", getFormat());
		saveForMenuMap.put("session_key", getSessionKey());
		
		saveForMenuMap.put("timestamp", getTimeStamp());
		saveForMenuMap.put("entityId", getEntityId());
		
		return saveForMenuMap;
	}
	
	
	/**
	 * 用于计算 sign
	 * 用于查询必选菜
	 * @return
	 */
	public Map<String, String> getBodyForQueryForceMenu(){
		
		Map<String, String> saveForMenuMap = new HashMap<String, String>();
		saveForMenuMap.put("app_key", getAppKey());
		saveForMenuMap.put("app_version", getAppVersion());
		saveForMenuMap.put("device_id", getDeviceId());
		
		saveForMenuMap.put("format", getFormat());
		saveForMenuMap.put("session_key", getSessionKey());
		
		saveForMenuMap.put("timestamp", getTimeStamp());
		saveForMenuMap.put("entityId", getEntityId());
		
		return saveForMenuMap;
	}
	
	
	/**
	 * 用于计算 sign
	 * 用于开启或者关闭扫桌码预付款开关
	 * @return
	 */
	public Map<String, String> getBodyForPrePaySwitchWithSeatCode(){
		
		Map<String, String> saveForMenuMap = new HashMap<String, String>();
		saveForMenuMap.put("app_key", getAppKey());
		saveForMenuMap.put("app_version", getAppVersion());
		saveForMenuMap.put("device_id", getDeviceId());
		
		saveForMenuMap.put("format", getFormat());
		saveForMenuMap.put("ids_str", getIds_str());
		
		saveForMenuMap.put("timestamp", getTimeStamp());
		saveForMenuMap.put("entityId", getEntityId());
		
		return saveForMenuMap;
	}
	
	
	
	/**
	 * 用于计算 sign
	 * 用于设置掌柜端商品的基本属性
	 * @return
	 */
	public Map<String, String> getBodyForMenuConfigUpdate(){
		
		Map<String, String> saveForMenuMap = new HashMap<String, String>();
		saveForMenuMap.put("app_key", getAppKey());
		saveForMenuMap.put("app_version", getAppVersion());
		
		saveForMenuMap.put("device_id", getDeviceId());		
		saveForMenuMap.put("format", getFormat());
		
		saveForMenuMap.put("menu_str", getMenu_str());
		saveForMenuMap.put("menu_prop_str", getMenu_prop_str());
		
		saveForMenuMap.put("timestamp", getTimeStamp());
		saveForMenuMap.put("entityId", getEntityId());
		
		return saveForMenuMap;
		
	}
	
	
	
	public String getConfigId() {
		return configId;
	}
	
	public void setConfigId(String configId) {
		this.configId = configId;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getAppVersion() {
		return appVersion;
	}
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getSessionKey() {
		return sessionKey;
	}
	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public ForceConfig getForceConfig() {
		return forceConfig;
	}

	public void setForceConfig(ForceConfig forceConfig) {
		this.forceConfig = forceConfig;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getIds_str() {
		return ids_str;
	}

	public void setIds_str(String ids_str) {
		this.ids_str = ids_str;
	}

	public String getMenu_str() {
		return menu_str;
	}

	public void setMenu_str(String menu_str) {
		this.menu_str = menu_str;
	}

	public String getMenu_prop_str() {
		return menu_prop_str;
	}

	public void setMenu_prop_str(String menu_prop_str) {
		this.menu_prop_str = menu_prop_str;
	}

}
