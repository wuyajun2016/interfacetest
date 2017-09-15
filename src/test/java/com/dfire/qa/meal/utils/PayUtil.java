package com.dfire.qa.meal.utils;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import com.alibaba.fastjson.JSONObject;
import com.dfire.qa.meal.bean.Response;
import com.dfire.soa.consumer.vo.CardPayVo;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class PayUtil {
	
	
	public static List<CardPayVo> getCardPayVoFromBill(Response getTradeBill) throws Exception{
		
		List<CardPayVo> cardPayoList = new ArrayList<CardPayVo>();
		
		JsonObject getTradeBillJsonObject = new JsonParser().parse(getTradeBill.getResponseStr()).getAsJsonObject();			
		Assert.assertEquals(getTradeBillJsonObject.get("code").getAsInt(), 1, "获取账单失败");
		
		JsonObject data = getTradeBillJsonObject.get("data").getAsJsonObject();		
		JsonArray cardPayArray = data.get("cardPay").getAsJsonArray();
		
		for(JsonElement cardPay : cardPayArray){
			
			String cardPayJson = cardPay.toString();
			
			CardPayVo cardPayVo = JSONObject.parseObject(cardPayJson, CardPayVo.class);
			
			cardPayoList.add(cardPayVo);
		}
		
		return cardPayoList;
	}
	
	
	
	

}
