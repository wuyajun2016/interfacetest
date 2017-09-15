package com.dfire.wechat.db;

public enum WaitingInstanceKind {
	
	KIND_NORMAL("normal dish", 1), KIND_SUIT("suit dish", 2), KIND_SELF("normal dish for self", 3), 
	KIND_SELF_SUIT("suit dish for self", 4), KIND_ADDITION("addition", 5);
	
	private String description;
	private int index;
	
	private WaitingInstanceKind(String description, int index){
		this.description = description;
		this.index = index;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void setIndex(int index){
		this.index = index;
	}
	
	public int getIndex(){
		return index;
	}

}
