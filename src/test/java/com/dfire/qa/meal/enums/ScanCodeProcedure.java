package com.dfire.qa.meal.enums;

public enum ScanCodeProcedure {
	
	PREPAYSEAT("扫桌码预付款"), NOPREPAYSEAT("扫桌码非预付款"), PREPAYSHOP("扫店码预付款"), NOPREPAYSHOP("扫店码非预付款");
	
	private String description;
	
	private ScanCodeProcedure(String description){
		this.description = description;
	}
	
	public String showDescription(){
		return this.description;
	}


}
