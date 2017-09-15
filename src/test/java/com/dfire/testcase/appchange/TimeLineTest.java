package com.dfire.testcase.appchange;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import redis.clients.jedis.Jedis;

import com.alibaba.fastjson.JSON;
import com.dfire.shop.redis.RedisManipulate;
import com.dfire.shop.redis.RedisUtils;
import com.dfire.wechat.db.WeChatUtils;
import com.dfire.wechat.util.BeanProvider;
import com.dfire.wechat.util.CommonConstants;
import com.dfire.wechat.util.HttpRequestEx;
import com.dfire.wechat.util.PathForPost;
import com.dfire.wechat.util.Response;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * 获取时间线信息
 * @author Administrator
 *
 */
public class TimeLineTest {
	
	private static final Logger logger = Logger.getLogger(TimeLineTest.class);
	private String host = CommonConstants.DEFAULT_HOST;
	private boolean https = CommonConstants.HTTPS;
	private HttpRequestEx httpRequest;	
	
	@BeforeClass(alwaysRun = true)
	public void setup(){
		
		logger.info("setup in SearchShopTest");
		httpRequest = new HttpRequestEx(host, https);
		
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown(){
		logger.info("tearDown in SearchShopTest");
		httpRequest.ShutDown();
		
		
 	}

	
	@Test(description = "time Line Test", dataProvider = "timeLineTest",
			dataProviderClass = TimeLineTestData.class, groups = {"smoke", "all"})
	public void getCartDataTest(String description, Map<String, String> query, int responseStatus, int resultCode, int jobStatus)throws Exception{
		
		try{
			Response response = httpRequest.get(PathForPost.getTimeLine(),query); 
			Assert.assertEquals(response.getStatus(), responseStatus);
			JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();		
			if(jobStatus == 100){
				Assert.assertEquals(resp.get("code").getAsInt(), resultCode);
				Assert.assertNotNull(resp.get("data"));
				if(resp.get("data")!=null){
					Long d1 = resp.get("data").getAsJsonArray().get(0).getAsJsonObject().get("timeToShop").getAsLong();
					Long d2 = resp.get("data").getAsJsonArray().get(1).getAsJsonObject().get("timeToShop").getAsLong();
					Long d3 = resp.get("data").getAsJsonArray().get(2).getAsJsonObject().get("timeToShop").getAsLong();
					//抽取最前面3条记录，校验倒叙排列
					Assert.assertTrue(d1>d2);
					Assert.assertTrue(d2>d3);
				}
				
			}
			else if(jobStatus == 200){
				Assert.assertEquals(resp.get("code").getAsInt(), resultCode);
				Assert.assertEquals(resp.getAsJsonArray("data").size(), 0);
				
			}
			else if(jobStatus ==300){
				Assert.assertEquals(resp.get("code").getAsInt(), resultCode);
				Assert.assertNotNull(resp.get("data"));
				//判断下不传参时候默认最多10条
				Assert.assertTrue(resp.getAsJsonArray("data").size()<=10);
				
			}
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}
	
	
	
	
	
	
	
}
