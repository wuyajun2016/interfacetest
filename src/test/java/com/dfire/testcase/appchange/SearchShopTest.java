package com.dfire.testcase.appchange;

import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.List;
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
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.twodfire.util.JsonUtil;

import net.sf.json.JSONObject;

/**
 * 找店页&搜索结果
 * @author Administrator
 *
 */
public class SearchShopTest {
	
	private static final Logger logger = Logger.getLogger(SearchShopTest.class);
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

	
	@Test(description = "search shop test", dataProvider = "searchShopTest",
			dataProviderClass = SearchShopTestData.class, groups = {"smoke", "all"})
	public void getCartDataTest(String description, Map<String,String> param, Map<String,String> body,int responseStatus, int resultCode, int jobStatus,Map<String,String> checkdata)throws Exception{
		
		try{
//			Gson gson = new Gson();
//			NearByShopBean nb = gson.fromJson(body.toString(), NearByShopBean.class);
			Response response = httpRequest.post_john(PathForPost.getSearchShop(), param, body);
			Assert.assertEquals(response.getStatus(), responseStatus);
			
			JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();		
			if(jobStatus == 100){
				Assert.assertEquals(resp.get("code").getAsInt(), resultCode);
				Assert.assertEquals(resp.getAsJsonArray("data").size(), 0);
			}
			else if(jobStatus==200){
				Assert.assertEquals(resp.get("code").getAsInt(), resultCode);
				Assert.assertTrue(resp.getAsJsonArray("data").size()>0);
			}
			else if(jobStatus==300){
				Assert.assertEquals(resp.get("code").getAsInt(), resultCode);
				Assert.assertEquals(resp.get("errorCode").getAsString(), checkdata.get("requirednull"));
			}
			else if(jobStatus==400){
				Assert.assertEquals(resp.get("code").getAsInt(), resultCode);
//				System.out.println("店铺名称为："+resp.get("data").getAsJsonArray().get(0).getAsJsonObject().get("name").getAsString());
				Assert.assertEquals(resp.get("data").getAsJsonArray().get(0).getAsJsonObject().get("name").getAsString(), checkdata.get("shopname"));	
			}
			//可能有点问题，传入的typecontent不存在也返回数据了
			else if(jobStatus==500){
				Assert.assertEquals(resp.get("code").getAsInt(), resultCode);
				Assert.assertTrue(resp.getAsJsonArray("data").size()==10);
			}
			else if(jobStatus==600){
				Assert.assertEquals(resp.get("code").getAsInt(), resultCode);

			}
			
		}catch(Exception e){
			logger.info(e.toString());
			Assert.assertEquals("断言抛出了异常直接失败", "没有异常");
		}
		
	}
	
	
	
	
	
	
	
}
