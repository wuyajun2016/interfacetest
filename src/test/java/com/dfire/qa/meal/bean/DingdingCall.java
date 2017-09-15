package com.dfire.qa.meal.bean;

import java.util.List;

public class DingdingCall {
	
	private List<String> atMobiles;
	private Boolean isAtAll;
	
	
	public DingdingCall(List<String> mobiles, Boolean isAtAll){
		
		this.atMobiles = mobiles;
		this.isAtAll = isAtAll;
	}
	
	public List<String> getAtMobiles() {
		return atMobiles;
	}
	public void setAtMobiles(List<String> atMobiles) {
		this.atMobiles = atMobiles;
	}
	public Boolean getIsAtAll() {
		return isAtAll;
	}
	public void setIsAtAll(Boolean isAtAll) {
		this.isAtAll = isAtAll;
	}

}
