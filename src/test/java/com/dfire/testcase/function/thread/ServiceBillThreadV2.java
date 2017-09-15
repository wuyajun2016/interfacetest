package com.dfire.testcase.function.thread;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.dfire.testcase.function.bean.cash.CloudMessage;
import com.dfire.testcase.function.service.CashService;

public class ServiceBillThreadV2 implements Runnable{
	
	private static final Logger logger = Logger.getLogger(ServiceBillThread.class);
	
	private CashService cashService;
	
	public ServiceBillThreadV2(CashService cashService){
		
		this.cashService = cashService;
		
	}
	
	public void run() {
		
		//获取账单消息 121 
		String status = "3";//自动审核未处理消息
		String json;
		
		//提取拉账单消息 
		do {
			String messages = cashService.getMessages(status);
			json = String.valueOf(JSONPath.read(messages,"$.data[ty=121][0]"));//121:拉取账单消息
		} 
		while 
			(json==null||"null".equals(json));
		
		Assert.assertNotNull(json,"非预付款获取账单消息失败！");
		logger.info("拉账单消息:"+json);
		
		CloudMessage msg = JSONObject.parseObject(json, CloudMessage.class);
		
		//计算账单
		cashService.calculateServiceBill(msg.getId());
		
	}


}
