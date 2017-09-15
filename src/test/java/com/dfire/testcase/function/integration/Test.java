package com.dfire.testcase.function.integration;

import java.util.List;

import org.testng.Assert;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.dfire.testcase.function.bean.cash.CustomerPayOrder;
import com.dfire.testcase.function.bean.cash.WaitingInstance;
import com.google.gson.Gson;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Gson gson = new Gson();
		String json = "{\"record\":0,\"data\":[{\"id\":\"e84548e924fc493faa080ad819dec8af\",\"sb_id\":\"b3aa512b56e4486f8c5531096924ecaa\",\"su\":0,\"ty\":122,\"et\":1481540505,\"e_id\":null,\"tl\":\"火小二/淘点点网上支付\",\"cot\":\"{\"cardEntityId\":\"99928345\",\"cardId\":\"9992834557bd80c30157bda6626c000c\",\"code\":\"99928345zZvcEj3MQk69qp6R3P8fUZ\",\"customerRegisterId\":\"2f80fa408a0b427ea7b2a28f33358468\",\"fee\":12,\"memo\":\"new_card_pay\",\"orderId\":\"9992834558d87d7e0158f27ad70a06b0\",\"pay\":12,\"type\":3,\"waitingPayId\":\"9992834558d8810f0158f27af8380714\"}\",\"b_id\":\"9992834558d87d7e0158f27ad70a06b0\",\"s_id\":\"2f80fa408a0b427ea7b2a28f33358468\",\"sc\":\"103\",\"rm\":\"\",\"mu\":\"\",\"ct\":1481536905086,\"mt\":1481536905773}],\"code\":1}";
		
		if(null==JSONPath.read(json,"$.data[ty=121][0]")){
			System.out.println("ssss");
		}
		System.out.println(json);
//		CustomerPayOrder customerPayOrder = gson.fromJson(cot,CustomerPayOrder.class);
//
//		List<WaitingInstance> waitingInstances = customerPayOrder.getCustomerOrder().getWaitingInstances();
		
	}

}
