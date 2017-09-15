package com.dfire.testcase.prepay;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.dfire.shop.redis.RedisManipulate;
import com.dfire.soa.wechat.service.ICardServiceForBusiness;
import com.dfire.testcase.precart.PreCartControllerTestData;
import com.dfire.testcase.shop.CartControllerTest;
import com.dfire.wechat.db.WeChatUtils;
import com.dfire.wechat.util.AbstractTestBase;
import com.dfire.wechat.util.BeanProvider;
import com.dfire.wechat.util.CommonConstants;
import com.dfire.wechat.util.HttpRequestEx;
import com.dfire.wechat.util.PathForPost;
import com.dfire.wechat.util.Response;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class PrePayControllerTest extends AbstractTestBase {
	
	private static final Logger logger = Logger.getLogger(PrePayControllerTest.class);
	private String host = CommonConstants.DEFAULT_HOST;
	private boolean https = CommonConstants.HTTPS;
	private HttpRequestEx httpRequest;	
	
	private String tokenKey1 = CommonConstants.tokenKey1;
	private String value1 =  CommonConstants.value1;
	private String sessionKey1 =  CommonConstants.sessionKey1;
	private String sessionBody1 =  CommonConstants.sessionBody1;
	
	private String tokenKey2 = CommonConstants.tokenKey2;
	private String value2 =  CommonConstants.value2;
	private String sessionKey2 =  CommonConstants.sessionKey2;
	private String sessionBody2 =  CommonConstants.sessionBody2;
	
	private String entityId = "99927792";
	public static String menuId = BeanProvider.getUUIDName();
	public static String orderId = BeanProvider.getUUIDName();
	private static String kindMenuId = BeanProvider.getUUIDName();
	
	private String seatCode = "B1";
	private String orderId1 = "";
	private String customerRegisterId = "b379d59ed79c4991a05fb2eb2a7b1c2c";
	private short orderFrom = (short) 1;

	
	@BeforeClass(alwaysRun = true)
	public void setup(){
		
		logger.info("setup in PrePayControllerTest");
		httpRequest = new HttpRequestEx(host, https);
		RedisManipulate.connectToRedis();
		
		// set cache for wenjing store
		RedisManipulate.setCache(tokenKey1, value1);
		RedisManipulate.setCache(sessionKey1, sessionBody1);
		
		RedisManipulate.setCache(tokenKey2, value2);
		RedisManipulate.setCache(sessionKey2, sessionBody2);
		
		// prepare menu data into database
		WeChatUtils.insertMenuIntoDB(menuId, entityId, kindMenuId);
		
		// create personal cart
//		Result<Integer> result1 = cloudCartService.joinTable(entityId, seatCode, orderId1, customerRegisterId, orderFrom, 6, "test");
//		Result<CloudCartVo> result2 = cloudCartService.modifyCloudCart(entityId, seatCode, orderId1, 
//				customerRegisterId, BeanProvider.createCart(entityId, kindMenuId));
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown(){
		logger.info("tearDown in PrePayControllerTest");
		httpRequest.ShutDown();
		
		// clean menu data from mysql
		WeChatUtils.deleteMenuFromDB(menuId);
	}
	

	
	@Test(description = "get pre pay bill test", dataProvider = "getPrePayBillTest",
			dataProviderClass = PrePayControllerTestData.class, groups = {"smoke", "all"})
	public void getPrePayBillTest(String description, Map<String, String> query, String httpBody, 
			                   int responseStatus, int resultCode, int jobStatus){
		
		try{
			Response response = httpRequest.post(PathForPost.getPathForGetPrePayBill(), query, httpBody); 
			Assert.assertEquals(response.getStatus(), responseStatus);
			
			if(responseStatus == 200){
				JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
				Assert.assertEquals(resp.get("code").getAsInt(), resultCode);
				
				if(jobStatus == 200){
					Assert.assertNotNull(resp.get("data").getAsJsonObject().get("owner"));
				}
			}
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}
	
	
	@Test(description = "confirm prepay order test", dataProvider = "confirmPrePayOrderTest",
			dataProviderClass = PrePayControllerTestData.class, groups = {"smoke", "all"})
	public void confirmPrePayOrderTest(String description, Map<String, String> query, String httpBody, 
			                   int responseStatus, int resultCode, int jobStatus){
		
		try{
			Response response = httpRequest.post(PathForPost.getPathForConfirmPrepayOrder(), query, httpBody); 
			Assert.assertEquals(response.getStatus(), responseStatus);
			
			if(responseStatus == 200){
				JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
				Assert.assertEquals(resp.get("code").getAsInt(), resultCode);
				
				if(jobStatus == 200){
					Assert.assertNotNull(resp.get("data").getAsJsonObject().get("owner"));
				}
			}
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}
	
	
	
	@Test(description = "delete prepay order test", dataProvider = "deletePrepayOrderTest",
			dataProviderClass = PrePayControllerTestData.class, groups = {"smoke", "all"})
	public void deletePrepayOrderTest(String description, Map<String, String> query, 
			                   int responseStatus, int resultCode, int jobStatus){
		
		try{
			Response response = httpRequest.post(PathForPost.getPathForDeletePrepayOrder(), query); 
			Assert.assertEquals(response.getStatus(), responseStatus);
			
			if(responseStatus == 200){
				JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
				Assert.assertEquals(resp.get("code").getAsInt(), resultCode);
				
				if(jobStatus == 200){
					Assert.assertNotNull(resp.get("data").getAsJsonObject().get("owner"));
				}
			}
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}
	
	
	@Test(description = "get prepay order test", dataProvider = "getPrepayOrderTest",
			dataProviderClass = PrePayControllerTestData.class, groups = {"smoke", "all"})
	public void getPrepayOrderTest(String description, Map<String, String> query, 
			                   int responseStatus, int resultCode, int jobStatus){
		
		try{
			Response response = httpRequest.post(PathForPost.getPathForGetPrepayOrder(), query); 
			Assert.assertEquals(response.getStatus(), responseStatus);
			
			if(responseStatus == 200){
				JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
				Assert.assertEquals(resp.get("code").getAsInt(), resultCode);
				
				if(jobStatus == 200){
					Assert.assertNotNull(resp.get("data").getAsJsonObject().get("orderVos"));
				}
			}
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}
	
	
	
	@Test(description = "get bill info test", dataProvider = "getBillInfoTest",
			dataProviderClass = PrePayControllerTestData.class, groups = {"smoke", "all"})
	public void getBillInfoTest(String description, Map<String, String> query, 
			                   int responseStatus, int resultCode, int jobStatus){
		
		try{
			Response response = httpRequest.post(PathForPost.getPathForGetBillInfo(), query); 
			Assert.assertEquals(response.getStatus(), responseStatus);
			
			if(responseStatus == 200){
				JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
				Assert.assertEquals(resp.get("code").getAsInt(), resultCode);
				
				if(jobStatus == 200){
					Assert.assertNotNull(resp.get("data").getAsJsonArray().get(0));
					
				}
			}
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}
	
	
	@Test(description = "lock pay waiting order test", dataProvider = "lockPayWaitingOrderTest",
			dataProviderClass = PrePayControllerTestData.class, groups = {"smoke", "all"})
	public void lockPayWaitingOrderTest(String description, Map<String, String> query, 
			                   int responseStatus, int resultCode, int jobStatus){
		
		try{
			Response response = httpRequest.post(PathForPost.getPathForLockPayWaitingOrder(), query); 
			Assert.assertEquals(response.getStatus(), responseStatus);
			
			if(responseStatus == 200){
				JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
				Assert.assertEquals(resp.get("code").getAsInt(), resultCode);
				
				if(jobStatus == 200){
					Assert.assertNotNull(resp.get("data").getAsJsonArray().get(0));
					
				}
			}
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}

}
