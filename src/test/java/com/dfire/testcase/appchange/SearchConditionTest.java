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
 * 获取筛选条件
 * @author Administrator
 *
 */
public class SearchConditionTest {
	
	private static final Logger logger = Logger.getLogger(SearchConditionTest.class);
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

	
	@Test(description = "sear chCondition Test", dataProvider = "searchConditionTest",
			dataProviderClass = SearchConditionTestData.class, groups = {"smoke", "all"})
	public void getCartDataTest(String description, Map<String, String> query, int responseStatus, int resultCode, int jobStatus,Map<String,String> typename,Map<String,String> meishi_sort){
		
		try{
			Response response = httpRequest.get(PathForPost.getSearchConditon(),query);
			Assert.assertEquals(response.getStatus(), responseStatus);
			JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(resp.get("code").getAsInt(), resultCode);
			//没有企业卡
			if(jobStatus==100){
				//美食类型
				Assert.assertEquals(resp.get("data").getAsJsonArray().get(0).getAsJsonObject().get("typeName").getAsString(),typename.get("meishi"));
				//外卖
				Assert.assertEquals(resp.get("data").getAsJsonArray().get(1).getAsJsonObject().get("typeName").getAsString(),typename.get("waimai"));
				//远程取号
				Assert.assertEquals(resp.get("data").getAsJsonArray().get(2).getAsJsonObject().get("typeName").getAsString(),typename.get("quhao"));
				//更多
				Assert.assertEquals(resp.get("data").getAsJsonArray().get(3).getAsJsonObject().get("typeName").getAsString(),typename.get("gengduo"));
				//美食类型下的子搜索：火锅（随便校验一个就好）
				Assert.assertEquals(resp.get("data").getAsJsonArray().get(0).getAsJsonObject().get("typeContents").getAsJsonArray().get(0).getAsJsonObject().get("name").getAsString(),meishi_sort.get("huoguo"));
				//更多下的子搜索（会变化，校验下不为空即可）
				Assert.assertNotNull(resp.get("data").getAsJsonArray().get(3).getAsJsonObject().get("typeContents").getAsJsonArray().get(0).getAsJsonObject().get("name").getAsString());
			}
			//有企业卡
			else if(jobStatus==200){
				//美食类型
				Assert.assertEquals(resp.get("data").getAsJsonArray().get(0).getAsJsonObject().get("typeName").getAsString(),typename.get("meishi"));
				//外卖
				Assert.assertEquals(resp.get("data").getAsJsonArray().get(1).getAsJsonObject().get("typeName").getAsString(),typename.get("waimai"));
				//远程取号-变成企业卡
				Assert.assertEquals(resp.get("data").getAsJsonArray().get(2).getAsJsonObject().get("typeName").getAsString(),typename.get("qiyeka"));
				//更多
				Assert.assertEquals(resp.get("data").getAsJsonArray().get(3).getAsJsonObject().get("typeName").getAsString(),typename.get("gengduo"));
				//美食类型下的子搜索：火锅（随便校验一个就好）
				Assert.assertEquals(resp.get("data").getAsJsonArray().get(0).getAsJsonObject().get("typeContents").getAsJsonArray().get(0).getAsJsonObject().get("name").getAsString(),meishi_sort.get("huoguo"));
				//更多下的子搜索（会变化，校验下不为空即可）
				Assert.assertNotNull(resp.get("data").getAsJsonArray().get(3).getAsJsonObject().get("typeContents").getAsJsonArray().get(0).getAsJsonObject().get("name").getAsString());
			}
			
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}
	
	
	
	
	
	
	
}
