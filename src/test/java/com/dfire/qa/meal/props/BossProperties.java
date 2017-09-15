package com.dfire.qa.meal.props;

import java.util.Map;

import com.dfire.qa.meal.enums.Environment;

public class BossProperties {
	
	private String env;
	
	private Map<String, String> bossParaMap;
	
	private Map<String, String> dailyBossConfigMap;
	
	private Map<String, String> devBossConfigMap;
	
	private Map<String, String> preBossConfigMap;
	
	private Map<String, String> publishBossConfigMap;
	
	
	/**
	 * 获取当前环境下的掌柜端配置
	 */
	public Map<String, String> getBossProperties() throws Exception{
				
		return getBossParaMap();
	}
	
	
	/**
	 * 获取特定环境下的掌柜端配置
	 */
	public Map<String, String> getBossProperties(Environment env) throws Exception{
		
		return getBossParaMap();
	}



	public String getEnv() {
		return env;
	}


	public void setEnv(String env) {
		this.env = env;
	}


	public Map<String, String> getBossParaMap() {
		return bossParaMap;
	}


	public void setBossParaMap(Map<String, String> bossParaMap) {
		this.bossParaMap = bossParaMap;
	}


	public Map<String, String> getDailyBossConfigMap() {
		return dailyBossConfigMap;
	}


	public void setDailyBossConfigMap(Map<String, String> dailyBossConfigMap) {
		this.dailyBossConfigMap = dailyBossConfigMap;
	}


	public Map<String, String> getDevBossConfigMap() {
		return devBossConfigMap;
	}


	public void setDevBossConfigMap(Map<String, String> devBossConfigMap) {
		this.devBossConfigMap = devBossConfigMap;
	}


	public Map<String, String> getPreBossConfigMap() {
		return preBossConfigMap;
	}


	public void setPreBossConfigMap(Map<String, String> preBossConfigMap) {
		this.preBossConfigMap = preBossConfigMap;
	}


	public Map<String, String> getPublishBossConfigMap() {
		return publishBossConfigMap;
	}


	public void setPublishBossConfigMap(Map<String, String> publishBossConfigMap) {
		this.publishBossConfigMap = publishBossConfigMap;
	}


}
