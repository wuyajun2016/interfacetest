package com.dfire.qa.meal.utils;

import com.dfire.qa.meal.bean.Response;
import com.dfire.qa.meal.constant.Constants;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MessageUtil {
	
	
	private static Gson gson = new Gson();
	
	/**
	 * 获取拼装的错误信息<br/>
	 * 需要注意传入的参数是 Json 形式
	 */
	public static String getErrorMessage(String responseOfJson) throws Exception{
		
		Response response = gson.fromJson(responseOfJson, Response.class);
		
		// 校验 status 字段
		if(200 != response.getStatus()){
			return "_" + response.getStatus() + "_" + Constants.Message.CASEFAIL + response.getResponseStr();
		}
		
		
		JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();
		
		
		// 处理不包含 code 的情况
		if( !(resp.has("code"))){
			
			return "_" + response.getStatus() + "_" + Constants.Message.CASEFAIL + response.getResponseStr();
			
		}
		
		// 处理包含 code 的情况
		if(1 != resp.get("code").getAsInt()){
			
			if(resp.has("message"))
				return "_" + "200_" + Constants.Message.CASEFAIL + resp.get("message").getAsString();
			else 
				return "_" + "200_" + Constants.Message.CASEFAIL + "[no message find]";
			
			
		}
		
		return null;
	}
	

}
