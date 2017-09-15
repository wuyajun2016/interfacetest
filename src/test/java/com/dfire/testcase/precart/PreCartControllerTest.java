package com.dfire.testcase.precart;

import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.dfire.shop.redis.RedisManipulate;
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
import com.twodfire.share.result.Result;

public class PreCartControllerTest extends AbstractTestBase{
	
	private static final Logger logger = Logger.getLogger(CartControllerTest.class);
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
		
		logger.info("setup in PreCartControllerTest");
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
		logger.info("tearDown in PreCartControllerTest");
		httpRequest.ShutDown();
		
		// clean menu data from mysql
		WeChatUtils.deleteMenuFromDB(menuId);
	}
	
	
	/**
	 * 该接口涉及账单计算,原有的账单计算已经被废弃,正在开发新的账单中心,因此该接口暂时不覆盖
	 * @param description
	 * @param query
	 * @param httpBody
	 * @param responseStatus
	 * @param resultCode
	 * @param jobStatus
	 */
	@Test(description = "modify cart test", dataProvider = "modifyCartTest",
			dataProviderClass = PreCartControllerTestData.class, groups = {"smoke", "all"})
	public void modifyCartTest(String description, Map<String, String> query, String httpBody, 
			                   int responseStatus, int resultCode, int jobStatus){
		
		try{
			Response response = httpRequest.post(PathForPost.getPathForPreCart(), query, httpBody); 
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
	

	
	@Test(description = "get menus test", dataProvider = "getMenusTest",
			dataProviderClass = PreCartControllerTestData.class, groups = {"smoke", "all"})
	public void getMenusTest(String description, Map<String, String> query,  
			                 int responseStatus, int resultCode, int jobStatus){
		
		try{
			Response response = httpRequest.get(PathForPost.getPathForGetMenus(), query); 
			Assert.assertEquals(response.getStatus(), responseStatus);
			
			if(responseStatus == 200){
				JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
				Assert.assertEquals(resp.get("code").getAsInt(), resultCode);
				
				if(jobStatus == 200){
					Assert.assertNotNull(resp.get("data").getAsJsonObject().get("kind_menus").getAsJsonArray().get(0));
				}
			}
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}
	
	
	@Test(description = "get user cart test", dataProvider = "getUserCartTest",
			dataProviderClass = PreCartControllerTestData.class, groups = {"smoke", "all"})
	public void getUserCartTest(String description, Map<String, String> query,  
			                 int responseStatus, int resultCode, int jobStatus){
		
		try{
			Response response = httpRequest.get(PathForPost.getPathForGetUserCart(), query); 
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
	
	
	
	@Test(description = "import user cart test", dataProvider = "importUserCartTest",
			dataProviderClass = PreCartControllerTestData.class, groups = {"smoke", "all"})
	public void importUserCartTest(String description, Map<String, String> query,  
			                 int responseStatus, int resultCode, int jobStatus){
		
		try{
			Response response = httpRequest.post(PathForPost.getPathForImportUserCart(), query); 
			Assert.assertEquals(response.getStatus(), responseStatus);
			
			if(responseStatus == 200){
				JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
				Assert.assertEquals(resp.get("code").getAsInt(), resultCode);
				
				if(jobStatus == 200){
					Assert.assertNotNull(resp.get("data"));
				}
			}
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}
	
	
	
	@Test(description = "modify people and memo test", dataProvider = "modifyPeopleAndMemoTest",
			dataProviderClass = PreCartControllerTestData.class, groups = {"smoke", "all"})
	public void modifyPeopleAndMemoTest(String description, Map<String, String> query,  
			                 int responseStatus, int resultCode, int jobStatus){
		
		try{
			Response response = httpRequest.post(PathForPost.getPathForModifyPeopleAndMemo(), query); 
			Assert.assertEquals(response.getStatus(), responseStatus);
			
			if(responseStatus == 200){
				JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
				Assert.assertEquals(resp.get("code").getAsInt(), resultCode);
				
				if(jobStatus == 200){
					Assert.assertNotNull(resp.get("data"));
				}
			}
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}

}
