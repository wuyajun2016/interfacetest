package com.dfire.qa.meal.props;

import java.util.Map;

import com.dfire.qa.meal.enums.Environment;


public class AuthProperties {
	
	
	private String env;
	
	
	/**
	 * 日常认证配置
	 */
	private Map<String, String> dailyAuthParaMap;
	
	
	/**
	 * 项目环境认证配置
	 */
	private Map<String, String> devAuthParaMap;
	
	
	/**
	 * 预发环境认证配置
	 */
	private Map<String, String> preAuthParaMap;
	
	
	
	/**
	 * 线上认证配置
	 */
	private Map<String, String> publishAuthParaMap;
	
	
	
	/**
	 * 获取当前环境认证属性
	 */
	public Map<String, String> getAuthProperties() throws Exception{
				
		if(null == env || env.isEmpty())
			return null;
		
		if(Environment.daily.getLiteral().equalsIgnoreCase(env)){
			
			return getDailyAuthParaMap();
			
		}else if(Environment.publish.getLiteral().equalsIgnoreCase(env)){
			
			return getPublishAuthParaMap();
			
		}else if(Environment.dev.getLiteral().equalsIgnoreCase(env)){
			
			return getDevAuthParaMap();
			
		}else if(Environment.prePublish.getLiteral().equalsIgnoreCase(env)){
			
			return getPreAuthParaMap();
			
		}else{
			return null;
		}

	}
	
	
	
	/**
	 * 获取特定环境认证属性
	 */
	public Map<String, String> getAuthProperties(Environment env) throws Exception{
				
		
		if(Environment.daily == env){
			
			return getDailyAuthParaMap();
			
		}else if(Environment.publish == env){
			
			return getPublishAuthParaMap();
			
		}else if(Environment.dev == env){
			
			return getDevAuthParaMap();
			
		}else if(Environment.prePublish == env){
			
			return getPreAuthParaMap();
			
		}else{
			return null;
		}

	}




	public Map<String, String> getDailyAuthParaMap() {
		return dailyAuthParaMap;
	}




	public void setDailyAuthParaMap(Map<String, String> dailyAuthParaMap) {
		this.dailyAuthParaMap = dailyAuthParaMap;
	}




	public Map<String, String> getPublishAuthParaMap() {
		return publishAuthParaMap;
	}




	public void setPublishAuthParaMap(Map<String, String> publishAuthParaMap) {
		this.publishAuthParaMap = publishAuthParaMap;
	}




	public String getEnv() {
		return env;
	}




	public void setEnv(String env) {
		this.env = env;
	}




	public Map<String, String> getDevAuthParaMap() {
		return devAuthParaMap;
	}




	public void setDevAuthParaMap(Map<String, String> devAuthParaMap) {
		this.devAuthParaMap = devAuthParaMap;
	}



	public Map<String, String> getPreAuthParaMap() {
		return preAuthParaMap;
	}



	public void setPreAuthParaMap(Map<String, String> preAuthParaMap) {
		this.preAuthParaMap = preAuthParaMap;
	}

}
