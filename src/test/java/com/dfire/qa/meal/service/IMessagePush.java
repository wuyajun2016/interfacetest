package com.dfire.qa.meal.service;

import com.dfire.qa.meal.bean.DingdingMsg;

public interface IMessagePush {
	
	
	/**
	 * 发发消息到钉钉群<br/>
	 */
	public Boolean pushMsgToDingding(String URL, DingdingMsg dingdingMsg) throws Exception;

}
