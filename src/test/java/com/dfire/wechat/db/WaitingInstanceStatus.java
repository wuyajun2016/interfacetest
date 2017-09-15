package com.dfire.wechat.db;

public enum WaitingInstanceStatus {
	
	STATUS_NO_OPERATION("no operation", 0), STATUS_ARRIVE_SHOP("pending audit", 1), STATUS_TIMEOUT("time out", 2), 
	STATUS_NO_WORK("fail to place an order", 3), STATUS_WORK("success to place an order", 9);

	private String description;
	private int index;
	
	private WaitingInstanceStatus(String description, int index){
		this.description = description;
		this.index = index;
	}
	
	public void setDescription(String description){
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
