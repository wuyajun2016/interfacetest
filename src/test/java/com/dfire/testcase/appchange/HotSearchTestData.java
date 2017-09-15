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


public class HotSearchTestData {
	    
		@DataProvider(name = "hotSearchTest")
		public static Object [][] hotSearchTest() throws Exception {
			
			String description = "case1:经纬度为空时候，即获取不到城市，此时热门搜索返空";
		    Map<String, Object> query = new HashMap<String, Object>();
			query.put("appKey", "100011"); 
			query.put("format", "json"); 
			query.put("uid", "15068129031"); 
			query.put("timestamp", String.valueOf(System.currentTimeMillis()));
			query.put("token", "XIANMAOTEST");
			
			
			String description2 = "case2:有经纬时候，获取到对应城市（在北京、上海、杭州内），热门搜索返回对应固定值";
			Map<String, Object> query2 = new HashMap<String, Object>();
			query2.put("appKey", "100011"); 
			query2.put("format", "json"); 
			query2.put("uid", "15068129031"); 
			query2.put("timestamp", String.valueOf(System.currentTimeMillis()));
			query2.put("token", "XIANMAOTEST");
			query2.put("latitude", "120.140707");//二维火坐标
			query2.put("longitude", "30.301946");
			query2.put("city_name", "杭州");//可以有多个叫法
			
			String description3 = "case3:有经纬时候，获取到对应城市（在北京、上海、杭州外），热门搜索返回空";
			Map<String, Object> query3 = new HashMap<String, Object>();
			query3.put("appKey", "100011"); 
			query3.put("format", "json"); 
			query3.put("uid", "15068129031"); 
			query3.put("timestamp", String.valueOf(System.currentTimeMillis()));
			query3.put("token", "XIANMAOTEST");
			query3.put("latitude", "121.567034");//宁波坐标
			query3.put("longitude", "29.835323");
			query3.put("city_name", "宁波");
			
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
	        String[] timestamp = new String[]{"1504001666251"};
	        String[] sign1 = new String[]{"e0f4b00212c4eb4a9a60b6367d173c40"};
	        Map<String, String[]> paramsMap = new HashMap();
	        paramsMap.put("appKey", appKey);
	        paramsMap.put("uid", uid);
	        paramsMap.put("equipmentId", equipmentId);
	        paramsMap.put("timestamp", timestamp);
	        paramsMap.put("sign", sign1);
			
			return sign.getSign(paramsMap, "ODVjOGExYjI1OWYzN2Q1OGYxZGRmYTU2NTA5MzlhZmY=");
			
		}
		
}
