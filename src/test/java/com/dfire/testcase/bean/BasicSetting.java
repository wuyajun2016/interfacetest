package com.dfire.testcase.bean;

public enum BasicSetting {
	
	prePaySeat("扫桌码预付款"), noPrePaySeat("扫桌码非预付款"), prePayShop("扫店码预付款"), noPrePayShop("扫店码非预付款");
	
	private String description;
	
	private BasicSetting(String description){
		this.description = description;
	}
	
	public String showDescription(){
		return this.description;
	}

}
