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
 * 食记火会员信息
 * @author Administrator
 *
 */
public class FireMemberTest {
	
	private static final Logger logger = Logger.getLogger(FireMemberTest.class);
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

	
	@Test(description = "fire Member Test", dataProvider = "fireMemberTest",
			dataProviderClass = FireMemberTestData.class, groups = {"smoke", "all"})
	public void getCartDataTest(String description, Map<String, String> query, int responseStatus, int resultCode, int jobStatus)throws Exception{
		
		try{
			Response response = httpRequest.get(PathForPost.getFireMember(),query); 
			Assert.assertEquals(response.getStatus(), responseStatus);
			JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(resp.get("code").getAsInt(), resultCode);
			Assert.assertTrue(resp.get("data").getAsJsonObject().get("fireGrade").getAsInt()>1);
			
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}
	
	
	
	
	
	
	
}
