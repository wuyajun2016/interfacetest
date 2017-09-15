package com.dfire.wechat.util;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Response {
	
	private int status;
	private String responseStr;
	
	public Response(int responseStatus, String responseStr){
		this.status = responseStatus;
		this.responseStr = responseStr;
	}

	public int getStatus() {
		return status;
	}

	public String getResponseStr() {
		return responseStr;
	}

	
	public static Boolean isSuccess(Response response){
		Boolean flag = false;
		if (response.getStatus() == 200) {
			JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();
			if (resp.get("code").getAsInt()==1) {
				flag = true;
			}
		}
		return flag;
	}
}
