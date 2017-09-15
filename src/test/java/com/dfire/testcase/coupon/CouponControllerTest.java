package com.dfire.testcase.coupon;

import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.dfire.shop.redis.RedisManipulate;
import com.dfire.wechat.util.CommonConstants;
import com.dfire.wechat.util.HttpRequestEx;
import com.dfire.wechat.util.PathForPost;
import com.dfire.wechat.util.Response;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import redis.clients.jedis.Jedis;

public class CouponControllerTest {
	
	private static final Logger logger = Logger.getLogger(CouponControllerTest.class);
	private String host = CommonConstants.DEFAULT_HOST;
	private HttpRequestEx httpRequest;	
	private boolean https = CommonConstants.HTTPS;
	
	private String tokenKey1 = CommonConstants.tokenKey1;
	private String value1 =  CommonConstants.value1;
	private String sessionKey1 = CommonConstants.sessionKey1;
	private String sessionBody1 = CommonConstants.sessionBody1;
	
	private String tokenKey2 = CommonConstants.tokenKey2;
	private String value2 = CommonConstants.value2;
	private String sessionKey2 = CommonConstants.sessionKey2;
	private String sessionBody2 = CommonConstants.sessionBody2;
	
	@BeforeClass(alwaysRun = true)
	public void setup(){
		
		logger.info("setup in CartControllerTest");
		httpRequest = new HttpRequestEx(host, https);
		RedisManipulate.connectToRedis();
		
		// set cache for wenjing store
		RedisManipulate.setCache(tokenKey1, value1);
		RedisManipulate.setCache(sessionKey1, sessionBody1);

		RedisManipulate.setCache(tokenKey2, value2);
		RedisManipulate.setCache(sessionKey2, sessionBody2);
		
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown(){
		logger.info("tearDown in CartControllerTest");
		httpRequest.ShutDown();

	}
	
	/**
	 * 由于获取红包列表的功能旧的工程已经被废弃，新的工程正在开发，因此该接口暂不覆盖
	 * @param description
	 * @param query
	 * @param responseStatus
	 * @param resultCode
	 * @param jobStatus
	 */
	@Test(description = "get Coupon List test", dataProvider = "getCouponListTest",
			dataProviderClass = CouponControllerTestData.class, groups = {"smoke", "all"})
	public void getCouponListTest(String description, Map<String, String> query, int responseStatus, int resultCode, int jobStatus){
		
		try{
			Response response = httpRequest.get(PathForPost.getPathForGetCouponList(), query); 
			Assert.assertEquals(response.getStatus(), responseStatus);
			
			if(responseStatus == 200){
				JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
				Assert.assertEquals(resp.get("code").getAsInt(), resultCode);
				
				if(jobStatus == 200){
					Assert.assertNotNull(resp.get("data").getAsJsonObject().get("seatId"));
				}
			}
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}
	

}
