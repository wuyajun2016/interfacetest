package com.dfire.testcase.appchange;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.alibaba.dubbo.common.json.JSONArray;
import com.dfire.wechat.util.BeanProvider;
import com.dfire.wechat.util.SignUtilForApp;
import com.dfire.wechat.util.SignUtils;
import com.google.gson.Gson;
import com.twodfire.util.JsonUtil;

import net.sf.json.JSONObject;


public class FoodDairyTestData {
	
	
	    
			    
		@DataProvider(name = "foodDairyTest")
		public static Object [][] foodDairyTest() throws Exception {
			
			String description = "case1:没有入参，检查返回200且有数据";
		    Map<String, Object> query = new HashMap<String, Object>();
			query.put("appKey", "100011"); 
			query.put("format", "json"); 
			query.put("uid", "15068129031"); //怎么生成
			query.put("timestamp", String.valueOf(System.currentTimeMillis()));
			query.put("token", "XIANMAOTEST");//怎么生成


			return new Object[][]{
					{description, query, 200, 1, 100},

			};
		}
		
		
		/**
		 * 生成一个app中的签名
		 * @return
		 */
		public static String gensign(){
			
			SignUtilForApp sign = new SignUtilForApp();
			String[] appKey = new String[]{"100011"};
	        String[] uid = new String[]{"15068129031"};
	        String[] equipmentId = new String[]{"8C5678A7F3F44919933128C3EEACDABF"};
	        String[] timestamp = new String[]{"1446801341607"};
	        String[] sign1 = new String[]{"7F2EEB98A37C7F8CEF7AD715F6AE8562"};
	        Map<String, String[]> paramsMap = new HashMap();
	        paramsMap.put("appKey", appKey);
	        paramsMap.put("uid", uid);
	        paramsMap.put("equipmentId", equipmentId);
	        paramsMap.put("timestamp", timestamp);
	        paramsMap.put("sign", sign1);
			
			return sign.getSign(paramsMap, "ZGViZGI3MDQwMTdiYTcwZTdiM2E0ZDA5OGJlMGE4NzA=");
			
		}
		
}
