package com.dfire.wechat.db;

public enum WaitingOrderDetailStatus {
	
	DEFAULT("default status", 0), PENDING_AUDIT("pending to check order", 2), CANCELED("order is cancled", 3), CONFIREMED("the order is confirmed", 4), 
	ARRIVED("arrived shop", 5), TAKE_OUT_IN_OPERATION("the take out is in operation", 6), TAKE_OUT_ARRIVED("take out is arrived", 7), 
	TAKE_OUT_FINISH("the take out is finished", 8), TIME_OUT("time out", -1);
	
	private String description;
	private int index;
	
	private WaitingOrderDetailStatus(String description, int index){
		this.description = description;
		this.index = index;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	public void setIndex(int index){
		this.index = index;
	}
	
	public int getIndex(){
		return this.index;
	}

}
