package com.dfire.qa.meal.enums;

public enum MessageStatus {
	
	NEW_MESSAGE("0"), AUTO_CHECK("1"), CHECKED("2"), AUTO_CHECK_NOT_HANDLE("3");
	
	
	/**
	 * 代表 Dpush 上消息的状态类型,  具体如下所述 ,  0:新消息； 1：自动审核；2：已处理；3：自动审核，未处理
	 */
	private String status;  
	
	private MessageStatus(String status){
		
		this.setStatus(status);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
