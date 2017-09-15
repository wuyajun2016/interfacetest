package com.dfire.qa.meal.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dfire.qa.meal.bean.DingdingMsg;
import com.dfire.qa.meal.bean.Response;
import com.dfire.qa.meal.constant.BossContents;
import com.dfire.qa.meal.enums.Environment;
import com.dfire.qa.meal.httpclient.HTTPClientService;
import com.dfire.qa.meal.logger.LoggerMarkers;
import com.dfire.qa.meal.logger.MealLoggerFactory;
import com.dfire.qa.meal.service.IMessagePush;
import com.dfire.qa.meal.utils.HTTPRequestUtil;
import com.dfire.qa.meal.utils.MD5Utils;
import com.dfire.qa.meal.utils.PathForHTTP;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Service
public class MessagePush implements IMessagePush{

	
	@Resource
	private HTTPClientService httpClientService;
	
	
	private Gson gson = new Gson();
	
	
	@Override
	public Boolean pushMsgToDingding(String URL, DingdingMsg dingdingMsg) throws Exception {
				
		// 设置 HTTPS 相关信息
		httpClientService.setHttps();

		///////////////////////   发送请求进行相应设置        /////////////////////////////////////////////////////////////

		// 计算 URL 并发送 POST 请求
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, "the body is : " + gson.toJson(dingdingMsg));
		Response response = httpClientService.doPost(URL, null, gson.toJson(dingdingMsg));
		JsonObject msgPush = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();
		if(msgPush.get("errcode").getAsInt() != 0)
			return false;
		
		return true;
	}

}
