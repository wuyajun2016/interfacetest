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
import com.google.gson.JsonObject;
import com.twodfire.util.JsonUtil;

import net.sf.json.JSONObject;


public class SearchShopTestData {
	
			    
		@DataProvider(name = "searchShopTest")
		public static Object [][] searchShopTest(){
			
			//这部分参数放在请求头中（公共的）
			Map<String, String> param = new HashMap<String, String>();
			param.put("appKey", "100011"); 
			param.put("timestamp", String.valueOf(System.currentTimeMillis()));
			param.put("uid","15068129031");
			param.put("format", "json");
			param.put("token", "XIANMAOTEST");
			//校验数据
			Map<String, String> checkdata = new HashMap<String, String>();
			checkdata.put("requirednull", "ERR_000072");
			checkdata.put("shopname", "山楂酱内网测试新店");
			
			//以下是用例
			String description = "case1:附近没有搜索到店铺(经纬度用拉萨的),返回空";
		    Map<String, Object> query = new HashMap<String, Object>();
		    Map<String, String> bod = new HashMap<String, String>();
		    nearbyShopConditionBean ncvo = new nearbyShopConditionBean();
		    ncvo.setPage(1);//当前页
			ncvo.setPageSize(10);//一页10条
			ncvo.setLatitude("91.141484");//经度
			ncvo.setLongitude("29.676616");//维度
			query.put("nearbyShopConditionParam", ncvo);
			JSONObject query_json = JSONObject.fromObject(query); //将附件搜索条件转换成json串
			bod.put("nearby_shop", query_json.toString());

			
			String description2 = "case2:只传必填参数(page和pagesize),检查返回code200且有数据";
			Map<String, Object> query2 = new HashMap<String, Object>();
		    Map<String, String> bod2 = new HashMap<String, String>();
		    nearbyShopConditionBean ncvo2 = new nearbyShopConditionBean();
		    ncvo2.setPage(1);//当前页
		    ncvo2.setPageSize(10);//一页10条
			query2.put("nearbyShopConditionParam", ncvo2);
			JSONObject query_json2 = JSONObject.fromObject(query2); //将附件搜索条件转换成json串
			bod2.put("nearby_shop", query_json2.toString());
			
			String description3 = "case3:必填参数(nearbyShopConditionParam)不传，检查无法请求成功";
			Map<String, Object> query3 = new HashMap<String, Object>();
		    Map<String, String> bod3 = new HashMap<String, String>();
		    nearbyShopConditionBean ncvo3 = new nearbyShopConditionBean();
//			query3.put("nearbyShopConditionParam", ncvo3);
			JSONObject query_json3 = JSONObject.fromObject(query3); //将附件搜索条件转换成json串
			bod3.put("nearby_shop", query_json3.toString());
		
			String description4 = "case4:在输入框输入店名进行搜索，检查返回code200且数据包含店名";
			Map<String, Object> query4 = new HashMap<String, Object>();
		    Map<String, String> bod4 = new HashMap<String, String>();
		    nearbyShopConditionBean ncvo4 = new nearbyShopConditionBean();
		    ncvo4.setPage(1);//当前页
		    ncvo4.setPageSize(10);//一页10条
			ncvo4.setLatitude("30.302095");//经度教工路552
			ncvo4.setLongitude("120.140653");//维度
		    ncvo4.setSearchContent(checkdata.get("shopname"));
			query4.put("nearbyShopConditionParam", ncvo4);
			JSONObject query_json4 = JSONObject.fromObject(query4); //将附件搜索条件转换成json串
			bod4.put("nearby_shop", query_json4.toString());
		
			String description5 = "case5:只组合多个筛选条件进行搜索，检查返回200且有数据----待确认";
			Map<String, Object> query5 = new HashMap<String, Object>();
		    Map<String, String> bod5 = new HashMap<String, String>();
		    nearbyShopConditionBean ncvo5 = new nearbyShopConditionBean();
		    AdditionKeyBean advo5 = new AdditionKeyBean();
		       advo5.setId("5");  
		       ArrayList<String> addtion_array5 = new ArrayList<String>();
		       addtion_array5.add("5.1");
		       addtion_array5.add("5.3");
//		       addtion_array5.add("22");
//		       addtion_array5.add("21");
//		       addtion_array5.add("23");
//		       addtion_array5.add("24");
//		       addtion_array5.add("15");
//		       addtion_array5.add("20");
//		       addtion_array5.add("2");
		       advo5.setTypeContents(addtion_array5);
		       ArrayList<AdditionKeyBean> adt = new ArrayList<AdditionKeyBean>();
		       adt.add(advo5);//转换 对象 为 数组对象
		    ncvo5.setAdditionKey(adt); //美食类型以及下面的筛选条件
		    ncvo5.setPage(3);//当前页
		    ncvo5.setPageSize(300);//一页10条
		    ncvo5.setLatitude("30.302095");//经度教工路552
		    ncvo5.setLongitude("120.140653");//维度
			query5.put("nearbyShopConditionParam", ncvo5);
			JSONObject query_json5 = JSONObject.fromObject(query5); //将附件搜索条件转换成json串
			bod5.put("nearby_shop", query_json5.toString());
			
			
			String description6 = "case6:组合输入框(商圈)和筛选条件进行搜索，检查返回code200且有数据";
			Map<String, Object> query6 = new HashMap<String, Object>();
		    Map<String, String> bod6 = new HashMap<String, String>();
		    nearbyShopConditionBean ncvo6 = new nearbyShopConditionBean(); //筛选条件1
		    nearbyShopBusinessBean nbvo6 = new nearbyShopBusinessBean();//筛选条件2
		    //+筛选条件1:搜索附近可使用企业卡店铺
		    nbvo6.setBusinessId("");     
		    nbvo6.setBusinessType("company_card_id");
			query6.put("nearbyShopBusinessParam", nbvo6);
		    //+筛选条件2：搜索出附件可使用企业卡的店铺后，再加上下面这些条件去筛选一遍
		    AdditionKeyBean advo6 = new AdditionKeyBean();
		       advo6.setId("2");
		       ArrayList<String> addtion_array6 = new ArrayList<String>();
		       addtion_array6.add("5");
		       advo6.setTypeContents(addtion_array6);
//		       ArrayList<AdditionKeyBean> adt6 = new ArrayList<AdditionKeyBean>();
//		       adt6.add(advo6);//转换 对象 为 数组对象
		    AdditionKeyBean advo6_2 = new AdditionKeyBean();
		       advo6_2.setId("3");
		       ArrayList<String> addtion_array6_2 = new ArrayList<String>();
		       addtion_array6_2.add("3");
		       addtion_array6_2.add("4");
		       advo6_2.setTypeContents(addtion_array6_2);
//		       ArrayList<AdditionKeyBean> adt6_2 = new ArrayList<AdditionKeyBean>();
//		       adt6_2.add(advo6_2);//转换 对象 为 数组对象
		       ArrayList<AdditionKeyBean> adtall = new ArrayList<AdditionKeyBean>(); 
		       adtall.add(advo6);
		       adtall.add(advo6_2);	       
		    ncvo6.setAdditionKey(adtall); //美食类型以及下面的筛选条件
		    ncvo6.setPage(1);//当前页
		    ncvo6.setPageSize(10);//一页10条
		    ncvo6.setLatitude("30.302095");//经度教工路552
		    ncvo6.setLongitude("120.140653");//维度
			query6.put("nearbyShopConditionParam", ncvo6);
			JSONObject query_json6 = JSONObject.fromObject(query6); //将附件搜索条件转换成json串
			bod6.put("nearby_shop", query_json6.toString());




			
			return new Object[][]{
//					{description, param,bod, 200, 1, 100,checkdata},
//					{description2, param,bod2, 200, 1, 200,checkdata},
//					{description3, param,bod3, 200, 0, 300,checkdata},  //必填参数填，返回resultcode应该是0
//					{description4, param,bod4, 200, 1, 400,checkdata},
					{description5, param,bod5, 200, 1, 500,checkdata},
//					{description6, param,bod6, 200, 1, 600,checkdata},


			};
		}
		
		
		/**
		 * 生成一个app中的签名
		 * @return
		 */
		public static String gensign(){
			
			SignUtilForApp sign = new SignUtilForApp();
		    String[] singarray= new String[3];
		    Map<String, String[]> singmap = new HashMap<String, String[]>();
		    singarray[0] = "100011";
			singmap.put("appKey", singarray);
			
			return sign.getSign(singmap, "");
			
		}
		
		
}
