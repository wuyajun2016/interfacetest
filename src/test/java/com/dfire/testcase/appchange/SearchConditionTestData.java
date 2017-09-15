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


public class SearchConditionTestData {
	
	
	    
			    
		@DataProvider(name = "searchConditionTest")
		public static Object [][] searchConditionTest() throws Exception {
			
			//这块是公共的
			Map<String, Object> typename = new HashMap<String, Object>();
			typename.put("meishi", "美食类型");
			typename.put("waimai", "外卖");
			typename.put("quhao", "远程取号");
			typename.put("gengduo", "更多");
			typename.put("qiyeka", "企业专属");
			Map<String, Object> meishi_sort = new HashMap<String, Object>();
			meishi_sort.put("huoguo", "火锅");
			//下面是用例
			String description = "case1:获取所有筛选条件,没有入参,检查返回类型和下拉筛选条件包含某几个（不全部检查）";
		    Map<String, Object> query = new HashMap<String, Object>();
			query.put("appKey", "100011"); 
			query.put("format", "json"); 
			query.put("uid", "15068129031"); //没有企业卡的账号
			query.put("timestamp", String.valueOf(System.currentTimeMillis()));
//			query.put("sign", gensign());
			query.put("token", "XIANMAOTEST");

			
			String description2 = "case2:当存在企业卡时候（由于条件创建较为麻烦，暂且放置）";
		    Map<String, Object> query2 = new HashMap<String, Object>();
		    query2.put("appKey", "100011"); 
		    query2.put("format", "json"); 
		    query2.put("uid", "15971145133"); //有企业卡的账号
		    query2.put("timestamp", String.valueOf(System.currentTimeMillis()));
		    query2.put("token", "XIANMAOTEST");
			
			
			
			return new Object[][]{
					{description, query, 200, 1, 100,typename,meishi_sort},
					{description2, query2, 200, 1, 200,typename,meishi_sort},

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
