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
 * 热门搜索
 * @author Administrator
 *
 */
public class HotSearchTest {
	
	private static final Logger logger = Logger.getLogger(HotSearchTest.class);
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

	
	@Test(description = "hot Search test", dataProvider = "hotSearchTest",
			dataProviderClass = HotSearchTestData.class, groups = {"smoke", "all"})
	public void getCartDataTest(String description, Map<String, String> query, int responseStatus, int resultCode, int jobStatus)throws Exception{
		
		try{
			Response response = httpRequest.get(PathForPost.getHotSearch(),query); 
			Assert.assertEquals(response.getStatus(), responseStatus);
			JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
			if(jobStatus==100){
				Assert.assertEquals(resp.get("code").getAsInt(), resultCode);
				Assert.assertTrue(resp.get("data").getAsJsonArray().size()==0);
//				for(int i=0;i<resp.get("data").getAsJsonArray().size();i++){
//					if(resp.get("data").getAsJsonArray()){
//						Assert.assertEquals("实际数据一致", "实际数据一致");
//					}else{
//						Assert.assertEquals("实际数据不一致", "实际数据一致");
//						break;
//					}
//				}
			}
			else if(jobStatus==200){
				Assert.assertEquals(resp.get("code").getAsInt(), resultCode);
				Assert.assertTrue(resp.get("data").getAsJsonArray().size()!=0);
				System.out.println("-----------"+resp.get("data").getAsJsonArray());
			}
			//非上海，北京，杭州返回空
			else if(jobStatus==300){
				Assert.assertEquals(resp.get("code").getAsInt(), resultCode);
				Assert.assertTrue(resp.get("data").getAsJsonArray().size()==0);
			}
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}
	
	
	
	
	
	
	
}
