package com.dfire.qa.meal.props;

import java.util.Map;

import com.dfire.qa.meal.enums.Environment;

public class ShopProperties {
	
	private String env;
	
	private Map<String, String> dailyShopParaMap;
	
	private Map<String, String> devShopParaMap;
	
	private Map<String, String> preShopParaMap;
	
	private Map<String, String> publishShopParaMap;
	
	
	/**
	 * 获取当前环境下的店铺配置
	 */
	public Map<String, String> getShopProperties() throws Exception{
		
		if(null == env || env.isEmpty())
			return null;
		
		if(Environment.daily.getLiteral().equalsIgnoreCase(env)){
			return getDailyShopParaMap();
			
		}else if(Environment.publish.getLiteral().equalsIgnoreCase(env)){
			return getPublishShopParaMap();
			
		}else if(Environment.dev.getLiteral().equalsIgnoreCase(env)){
			return getDevShopParaMap();
			
		}else if(Environment.prePublish.getLiteral().equalsIgnoreCase(env)){
			return getPreShopParaMap();
			
		}else{
			return null;
		}
	}
	
	
	/**
	 * 获取特定环境下的店铺配置
	 */
	public Map<String, String> getShopProperties(Environment env) throws Exception{
		
		if(Environment.daily == env){
			return getDailyShopParaMap();
			
		}else if(Environment.publish == env){
			return getPublishShopParaMap();
			
		}else if(Environment.dev == env){
			return getDevShopParaMap();
			
		}else if(Environment.prePublish == env){
			return getPreShopParaMap();
		}else{
			return null;
		}
	}


	public Map<String, String> getDailyShopParaMap() {
		return dailyShopParaMap;
	}


	public void setDailyShopParaMap(Map<String, String> dailyShopParaMap) {
		this.dailyShopParaMap = dailyShopParaMap;
	}


	public Map<String, String> getPublishShopParaMap() {
		return publishShopParaMap;
	}


	public void setPublishShopParaMap(Map<String, String> publishShopParaMap) {
		this.publishShopParaMap = publishShopParaMap;
	}


	public Map<String, String> getDevShopParaMap() {
		return devShopParaMap;
	}


	public void setDevShopParaMap(Map<String, String> devShopParaMap) {
		this.devShopParaMap = devShopParaMap;
	}


	public String getEnv() {
		return env;
	}


	public void setEnv(String env) {
		this.env = env;
	}


	public Map<String, String> getPreShopParaMap() {
		return preShopParaMap;
	}


	public void setPreShopParaMap(Map<String, String> preShopParaMap) {
		this.preShopParaMap = preShopParaMap;
	}

}
