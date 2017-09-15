package com.dfire.testcase.function.order;

import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dfire.sdk.sign.SignGenerator;
import com.dfire.sdk.util.MD5Util;
import com.dfire.testcase.function.bean.QueryTradeBillForm;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class Test {

	public static void main(String[] args) throws JsonProcessingException {
		String value = MD5Util.encode("2f80fa408a0b427ea7b2a28f33358468");
		System.out.println(value);
	}

}
