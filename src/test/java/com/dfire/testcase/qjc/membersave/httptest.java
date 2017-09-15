package com.dfire.testcase.qjc.membersave;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.dfire.wechat.db.WeChatUtils;
import com.dfire.wechat.util.CommonConstants;
import com.dfire.wechat.util.HttpRequestEx;
import com.dfire.wechat.util.PathForPost;
import com.dfire.wechat.util.Response;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * http get test
 * @author Administrator
 *
 */
public class httptest {
	
	private HttpRequestEx httpRequest;
	private static final Logger logger = Logger.getLogger(httptest.class);
	private boolean https = CommonConstants.HTTPS;
	private String host = "www.kuaidi100.com"; 
//	private String host = "api.l.whereask.com"; 

	
	@BeforeClass(alwaysRun = true)
	public void setup(){
//		WeChatUtils.insertMenuIntoDB("", "", "");  //操作数据库数据准备
		System.out.println("------------------初始化数据");
	}
	
	@Test(priority = 1 , description = "qjc membercosumcardlist_data_setup", dataProvider = "memberConsumCardListTest",
			dataProviderClass = qjc_CardCosumeTestData.class, groups = {"smoke", "all"})
	public void getPayAndFee(String description, Map<String, String> query, 
			                   int responseStatus, int resultCode, int jobStatus,String abc)throws Exception{
		
		try{
			Map<String,String> query123 = new HashMap<String,String>(); 
			query123.put("type", "shunfeng");
			query123.put("postid", "615219134887");
			httpRequest = new HttpRequestEx(host, https);
			Response response1 = httpRequest.get(PathForPost.getPathFortest(),query123);
			JsonObject resp = new JsonParser().parse(response1.getResponseStr()).getAsJsonObject();//将Json字符串转换成JsonObject对象
			System.out.println("返回为--------------------"+response1);
			System.out.println("返回为2--------------------"+resp.getAsJsonArray("data").get(0).getAsJsonObject().get("context"));
			System.out.println(abc);
			assertEquals(resp.getAsJsonArray("data").get(0).getAsJsonObject().get("context").getAsString(), "交易成功");
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}
	
//	@Test(priority = 1 , description = "qjc membercosumcardlist_data_setup", dataProvider = "memberConsumCardListTest",
//			dataProviderClass = qjc_CardCosumeTestData.class, groups = {"smoke", "all"})
//	public void test2(String description, Map<String, String> query, 
//			                   int responseStatus, int resultCode, int jobStatus,String abc)throws Exception{
//		
//		try{
//			Map<String,String> query123 = new HashMap<String,String>(); 
//			query123.put("entity_id", "99932390");
//			query123.put("seat_code", "101");
//			query123.put("order_id", "");
//			query123.put("xtoken", "1D1D6B440E7FB4DB888F72CEF5E65ED25709DC35AFAA5E4DD3AEB134C49CBE7B");
//			httpRequest = new HttpRequestEx(host, https);
//			Response response1 = httpRequest.get(PathForPost.getPathFortest(),query123);
////			JsonObject resp = new JsonParser().parse(response1.getResponseStr()).getAsJsonObject();//将Json字符串转换成JsonObject对象
//			System.out.println("返回为--------------------"+response1);
////			System.out.println("返回为2--------------------"+resp.getAsJsonArray("data").get(0).getAsJsonObject().get("context"));
////			System.out.println(abc);
////			assertEquals(resp.getAsJsonArray("data").get(0).getAsJsonObject().get("context").getAsString(), "交易成功");
//		}catch(Exception e){
//			logger.info(e.toString());
//		}
//		
//	}

	
}
