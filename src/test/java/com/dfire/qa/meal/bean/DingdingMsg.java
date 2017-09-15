package com.dfire.qa.meal.bean;

public class DingdingMsg {
	
	private String msgtype;
	private DingdingText text;
	private DingdingCall at;
	
	
	public DingdingMsg(String msgtype, DingdingText text, DingdingCall dingdingCall){
		this.msgtype = msgtype;
		this.text = text;
		this.at = dingdingCall;
	}
	
	
	public String getMsgtype() {
		return msgtype;
	}
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}
	public DingdingText getText() {
		return text;
	}
	public void setText(DingdingText text) {
		this.text = text;
	}
	public DingdingCall getAt() {
		return at;
	}
	public void setAt(DingdingCall at) {
		this.at = at;
	}

}
