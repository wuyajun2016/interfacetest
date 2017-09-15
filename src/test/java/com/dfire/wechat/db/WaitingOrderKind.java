package com.dfire.wechat.db;

public enum WaitingOrderKind {
	
	KIND_RESERVE("order table", 1), KIND_TAKE_OUT("take-out", 2), KIND_SCAN_CODE("scan code to add dish", 3), 
	KIND_ORDER_SETA_CODE("open table with scanning seat code", 4), KIND_ORDER_PRE_ORDER("place a pre order with scanning code", 5), 
	KIND_ORDER_PRE_ORDER_SHOP("place a pre order with scanning shop code", 6);
	
	private String description;
	private int kindType;
	
	private WaitingOrderKind(String description, int kindType){
		this.description = description;
		this.kindType = kindType;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public void setIndex(int kindType){
		this.kindType = kindType;
	}
	
	public String getDescription(){
		return description;
	}
	
	public int getIndex(){
		return kindType;
	}

}
