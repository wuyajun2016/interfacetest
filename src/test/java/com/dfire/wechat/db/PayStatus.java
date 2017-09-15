package com.dfire.wechat.db;

public enum PayStatus {
	
	PAY_STATUS_NO_USE("no front money", 0), PAY_STATUS_NO("not paid", 1), PAY_STATUS_ALREADY("already paid", 2);
	
	private String description;
	private int index;
	
	private PayStatus(String description, int index){
		this.description = description;
		this.index = index;
	}

	public void setDescritpion(String description){
		this.description = description;
	}
	
	public void setIndex(int index){
		this.index = index;
	}
	
	public String getDescription(){
		return description;
	}
	
	public int getIndex(){
		return index;
	}
}
