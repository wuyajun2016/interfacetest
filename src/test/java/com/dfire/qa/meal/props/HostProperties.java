package com.dfire.qa.meal.props;

import java.util.Map;

import com.dfire.qa.meal.enums.Environment;

public class HostProperties {
	
	private String env;
	
	private Map<String, String> dailyHostParaMap;
	
	private Map<String, String> devHostParaMap;
	
	private Map<String, String> preHostParaMap;
	
	private Map<String, String> publishHostParaMap;
	
	
	/**
	 * 返回当前环境的主机配置
	 */
	public Map<String, String> getHostProperties() throws Exception{
		
		if(null == env || env.isEmpty())
			return null;
		
		if(Environment.daily.getLiteral().equalsIgnoreCase(env)){
			return getDailyHostParaMap();
			
		}else if(Environment.publish.getLiteral().equalsIgnoreCase(env)){
			return getPublishHostParaMap();
			
		}else if(Environment.dev.getLiteral().equalsIgnoreCase(env)){
			return getDevHostParaMap();
			
		}else if(Environment.prePublish.getLiteral().equalsIgnoreCase(env)){
			return getPreHostParaMap();
			
		}else{
			return null;
		}
	}
	

	/**
	 * 返回指定环境的主机配置
	 */
	public Map<String, String> getHostProperties(Environment env) throws Exception{
		
		if(Environment.daily == env){
			return getDailyHostParaMap();
			
		}else if(Environment.publish == env){
			return getPublishHostParaMap();
			
		}else if(Environment.dev == env){
			return getDevHostParaMap();
			
		}else if(Environment.prePublish == env){
			return getPreHostParaMap();
			
		}else{
			return null;
		}
	}


	public Map<String, String> getDailyHostParaMap() {
		return dailyHostParaMap;
	}


	public void setDailyHostParaMap(Map<String, String> dailyHostParaMap) {
		this.dailyHostParaMap = dailyHostParaMap;
	}


	public Map<String, String> getPublishHostParaMap() {
		return publishHostParaMap;
	}


	public void setPublishHostParaMap(Map<String, String> publishHostParaMap) {
		this.publishHostParaMap = publishHostParaMap;
	}


	public Map<String, String> getDevHostParaMap() {
		return devHostParaMap;
	}


	public void setDevHostParaMap(Map<String, String> devHostParaMap) {
		this.devHostParaMap = devHostParaMap;
	}


	public String getEnv() {
		return env;
	}


	public void setEnv(String env) {
		this.env = env;
	}


	public Map<String, String> getPreHostParaMap() {
		return preHostParaMap;
	}


	public void setPreHostParaMap(Map<String, String> preHostParaMap) {
		this.preHostParaMap = preHostParaMap;
	}


	

}
