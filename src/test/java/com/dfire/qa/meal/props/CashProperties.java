package com.dfire.qa.meal.props;

import java.util.Map;

import com.dfire.qa.meal.enums.Environment;
import com.dfire.qa.meal.utils.CommonUtil;

public class CashProperties {
	
	
	private String env;
	
	
	// 收银基本参数
	private Map<String, String> dailyCashBasePara;
	
	private Map<String, String> devCashBasePara;
	
	private Map<String, String> preCashBasePara;
	
	private Map<String, String> publishCashBasePara;
	
	
	// 用户及店铺基本参数
	private Map<String, String> dailyClientBasePara;
	
	private Map<String, String> devClientBasePara;
	
	private Map<String, String> preClientBasePara;
	
	private Map<String, String> publishClientBasePara;
	
	
	// 其他参数
	private Map<String, String> dailyOtherBasePara;
	
	private Map<String, String> devOtherBasePara;
	
	private Map<String, String> preOtherBasePara;
	
	private Map<String, String> publishOtherBasePara;

	
	
	//////////////    获取收银基本参数       ///////////////////////////////
	public Map<String, String> getCashBasePara(){
		if(null == env || env.isEmpty())
			return null;
		
		if(Environment.daily.getLiteral().equalsIgnoreCase(env)){
			return getDailyCashBasePara();
			
		}else if(Environment.publish.getLiteral().equalsIgnoreCase(env)){
			return getPublishCashBasePara();
			
		}else if(Environment.dev.getLiteral().equalsIgnoreCase(env)){
			return getDevCashBasePara();
			
		}else if(Environment.prePublish.getLiteral().equalsIgnoreCase(env)){
			return getPreCashBasePara();
			
		}else{
			return null;
		}
	}

	public Map<String, String> getCashBasePara(Environment env){
		
		if(Environment.daily == env){
			return getDailyCashBasePara();
			
		}else if(Environment.publish == env){
			return getPublishCashBasePara();
			
		}else if(Environment.dev == env){
			return getDevCashBasePara();
			
		}else if(Environment.prePublish == env){
			return getPreCashBasePara();
			
		}else{
			return null;
		}
	}
	
	
	
	////////////   获取用户及店铺基本参数            //////////////////////////
	public Map<String, String> getClientBasePara(){
		
		if(null == env || env.isEmpty())
			return null;
		
		if(Environment.daily.getLiteral().equalsIgnoreCase(env)){
			return getDailyClientBasePara();
			
		}else if(Environment.publish.getLiteral().equalsIgnoreCase(env)){
			return getPublishClientBasePara();
			
		}else if(Environment.dev.getLiteral().equalsIgnoreCase(env)){
			return getDevClientBasePara();
			
		}else if(Environment.prePublish.getLiteral().equalsIgnoreCase(env)){
			return getPreClientBasePara();
			
		}else{
			return null;
		}
	}
	
	public Map<String, String> getClientBasePara(Environment env){
		
		if(Environment.daily == env){
			
			if(getDailyClientBasePara().get("flag").contains("false")){
				String password = getDailyClientBasePara().get("password");
				String encodeString = CommonUtil.encodeWithAlgorithm(password.toUpperCase(), "MD5");
				getDailyClientBasePara().put("password", encodeString);
				getDailyClientBasePara().put("flag", "true");
			}
			
			return getDailyClientBasePara();
			
		}else if(Environment.publish == env){
			
			if(getPublishClientBasePara().get("flag").contains("false")){
				String password = getPublishClientBasePara().get("password");
				String encodeString = CommonUtil.encodeWithAlgorithm(password.toUpperCase(), "MD5");
				getPublishClientBasePara().put("password", encodeString);
				getPublishClientBasePara().put("flag", "true");
			}
			
			return getPublishClientBasePara();
			
		}else if(Environment.dev == env){
			
			if(getDevClientBasePara().get("flag").contains("false")){
				String password = getDevClientBasePara().get("password");
				String encodeString = CommonUtil.encodeWithAlgorithm(password.toUpperCase(), "MD5");
				getDevClientBasePara().put("password", encodeString);
				getDevClientBasePara().put("flag", "true");
			}
			
			return getDevClientBasePara();
			
		}else if(Environment.prePublish == env){
			
			if(getPreClientBasePara().get("flag").contains("false")){
				String password = getPreClientBasePara().get("password");
				String encodeString = CommonUtil.encodeWithAlgorithm(password.toUpperCase(), "MD5");
				getPreClientBasePara().put("password", encodeString);
				getPreClientBasePara().put("flag", "true");
			}
			
			return getPreClientBasePara();
			
		}else{
			return null;
		}
	}
	
	
	
	/////////////   获取用户及店铺基本参数              //////////////////////////
	
	public Map<String, String> getOtherBasePara(){
		
		if(null == env || env.isEmpty())
			return null;
		
		if(Environment.daily.getLiteral().equalsIgnoreCase(env)){
			return getDailyOtherBasePara();
			
		}else if(Environment.publish.getLiteral().equalsIgnoreCase(env)){
			return getPublishOtherBasePara();
			
		}else if(Environment.dev.getLiteral().equalsIgnoreCase(env)){
			return getDevOtherBasePara();
			
		}else if(Environment.prePublish.getLiteral().equalsIgnoreCase(env)){
			return getPreOtherBasePara();
			
		}else{
			return null;
		}
	}
	

	public Map<String, String> getOtherBasePara(Environment env){
		
		if(Environment.daily == env){
			return getDailyOtherBasePara();
			
		}else if(Environment.publish == env){
			return getPublishOtherBasePara();
			
		}else if(Environment.dev == env){
			return getDevOtherBasePara();
			
		}else if(Environment.prePublish == env){
			return getPreOtherBasePara();
			
		}else{
			return null;
		}
	}
	
	
	
	
	public Map<String, String> getDailyCashBasePara() {
		return dailyCashBasePara;
	}

	public void setDailyCashBasePara(Map<String, String> dailyCashBasePara) {
		this.dailyCashBasePara = dailyCashBasePara;
	}

	public Map<String, String> getPublishCashBasePara() {
		return publishCashBasePara;
	}

	public void setPublishCashBasePara(Map<String, String> publishCashBasePara) {
		this.publishCashBasePara = publishCashBasePara;
	}

	public Map<String, String> getDailyClientBasePara() {
		return dailyClientBasePara;
	}

	public void setDailyClientBasePara(Map<String, String> dailyClientBasePara) {
		this.dailyClientBasePara = dailyClientBasePara;
	}

	public Map<String, String> getPublishClientBasePara() {
		return publishClientBasePara;
	}

	public void setPublishClientBasePara(Map<String, String> publishClientBasePara) {
		this.publishClientBasePara = publishClientBasePara;
	}

	public Map<String, String> getDailyOtherBasePara() {
		return dailyOtherBasePara;
	}

	public void setDailyOtherBasePara(Map<String, String> dailyOtherBasePara) {
		this.dailyOtherBasePara = dailyOtherBasePara;
	}

	public Map<String, String> getPublishOtherBasePara() {
		return publishOtherBasePara;
	}

	public void setPublishOtherBasePara(Map<String, String> publishOtherBasePara) {
		this.publishOtherBasePara = publishOtherBasePara;
	}



	public String getEnv() {
		return env;
	}



	public void setEnv(String env) {
		this.env = env;
	}



	public Map<String, String> getDevCashBasePara() {
		return devCashBasePara;
	}



	public void setDevCashBasePara(Map<String, String> devCashBasePara) {
		this.devCashBasePara = devCashBasePara;
	}



	public Map<String, String> getDevClientBasePara() {
		return devClientBasePara;
	}



	public void setDevClientBasePara(Map<String, String> devClientBasePara) {
		this.devClientBasePara = devClientBasePara;
	}



	public Map<String, String> getDevOtherBasePara() {
		return devOtherBasePara;
	}



	public void setDevOtherBasePara(Map<String, String> devOtherBasePara) {
		this.devOtherBasePara = devOtherBasePara;
	}

	public Map<String, String> getPreCashBasePara() {
		return preCashBasePara;
	}

	public void setPreCashBasePara(Map<String, String> preCashBasePara) {
		this.preCashBasePara = preCashBasePara;
	}

	public Map<String, String> getPreClientBasePara() {
		return preClientBasePara;
	}

	public void setPreClientBasePara(Map<String, String> preClientBasePara) {
		this.preClientBasePara = preClientBasePara;
	}

	public Map<String, String> getPreOtherBasePara() {
		return preOtherBasePara;
	}

	public void setPreOtherBasePara(Map<String, String> preOtherBasePara) {
		this.preOtherBasePara = preOtherBasePara;
	}
	
	
	


	

}
