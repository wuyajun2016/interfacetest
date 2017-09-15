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


public class TimeLineTestData {
	
	
	    
			    
		@DataProvider(name = "timeLineTest")
		public static Object [][] timeLineTest() throws Exception {
			
			String description = "case1:当前页第1页，一页10条，检查返回200有数据且倒叙排列";
		    Map<String, Object> query = new HashMap<String, Object>();
			query.put("appKey", "100011"); 
			query.put("format", "json"); 
			query.put("uid", "15068129031"); 	
			query.put("timestamp", String.valueOf(System.currentTimeMillis()));
			query.put("token", "XIANMAOTEST");
			query.put("current_page","1");//当前页1
			query.put("page_size","10");//一页10条
			
			String description2 = "case2:当前页第100页，一页10条，假设数据共2页，检查返回200且数据为空";
		    Map<String, Object> query2 = new HashMap<String, Object>();
		    query2.put("appKey", "100011"); 
		    query2.put("format", "json"); 
		    query2.put("uid", "15068129031"); //怎么生成	
		    query2.put("timestamp", String.valueOf(System.currentTimeMillis()));
			query2.put("token", "XIANMAOTEST");
		    query2.put("current_page","100");//当前页10
		    query2.put("page_size","10");//一页10条
		
			String description3 = "case3:不传参数，返回最多10条";
		    Map<String, Object> query3 = new HashMap<String, Object>();
		    query3.put("appKey", "100011"); 
		    query3.put("format", "json"); 
		    query3.put("uid", "15068129031"); //怎么生成	
		    query3.put("timestamp", String.valueOf(System.currentTimeMillis()));
		    query3.put("token", "XIANMAOTEST");
			


			
			return new Object[][]{
					{description, query, 200, 1, 100},
					{description2, query2, 200, 1, 200},
					{description3, query3, 200, 1, 300},

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
