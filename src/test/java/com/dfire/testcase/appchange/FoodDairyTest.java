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
 * 获取食记页信息
 * @author Administrator
 *
 */
public class FoodDairyTest {
	
	private static final Logger logger = Logger.getLogger(FoodDairyTest.class);
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

	
	@Test(description = "food Dairy Test", dataProvider = "foodDairyTest",
			dataProviderClass = FoodDairyTestData.class, groups = {"smoke", "all"})
	public void getCartDataTest(String description, Map<String, String> query, int responseStatus, int resultCode, int jobStatus)throws Exception{
		
		try{
			Response response = httpRequest.get(PathForPost.getFoodDairy(),query); 
			Assert.assertEquals(response.getStatus(), responseStatus);
			JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(resp.get("code").getAsInt(), resultCode);
			//存在扫码下单的店铺
			Assert.assertTrue(resp.get("data").getAsJsonObject().get("shopInfoList").getAsJsonArray().size()!=0);
			//存在个人消费数据
			Assert.assertTrue(resp.get("data").getAsJsonObject().get("mealRecordList").getAsJsonArray().size()!=0);
			

		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}
	
	
	
	
	
	
	
}
