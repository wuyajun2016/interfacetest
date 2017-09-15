package com.dfire.testcase.shop;

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

public class CartControllerTest {
	
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
	private static String uid = "b379d59ed79c4991a05fb2eb2a7b1c2c";  // 与 使用的token 响对应
	
	public static String menuIdChild = BeanProvider.getUUIDName();
	private static String kindMenuIdChild = BeanProvider.getUUIDName();
	
	public static String menuIdParent = BeanProvider.getUUIDName();
	public static String menuIdPri = BeanProvider.getUUIDName();
	private static String kindMenuIdParent = BeanProvider.getUUIDName();
	
	public static String seatCode1 = "A4";
	public static String orderId1 = BeanProvider.getUUIDName();
	
	public static String orderId2 = BeanProvider.getUUIDName();
	public static String seatCode2 = "AJ";
	
	public static String people_count = "6";
	public static String memo = "this_memo_cart_controller_test";
	
	// set kindType to kind_normal with value 1
	public static String cartSuit = BeanProvider.getCartSuitJson(1, 1, menuIdChild, uid);
	
	// set kindType to kind_suit with value 2
	public static String cartSuit2 = BeanProvider.getCartSuitJson(1, 2, menuIdParent, menuIdChild, uid, true);

	private static String childMenuIdPri = BeanProvider.getUUIDName();
	
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
		
		// clear cart cache
		Jedis jedis = RedisUtils.createJedis("10.1.6.20", 7091, 0);  // connect to cart redis
		RedisUtils.deleteCache(jedis, "order_cart:99927792A4");
		RedisUtils.deleteCache(jedis, "order_cart:99927792AJ");
		
		
		// create own cart without child cartSuit and add dishes into corresponding cart
		CartControllerUtils.createCart(httpRequest, entityId, seatCode1, orderId1, people_count, memo);
		
		// create another own cart with child cartSuit and add dishes into corresponding cart
		CartControllerUtils.createCart(httpRequest, entityId, seatCode2, orderId2, people_count, memo);

		
		// prepare menu data into database
		WeChatUtils.insertMenuIntoDB(menuIdChild, entityId, kindMenuIdChild);  // child dish ---> menu table
		
		WeChatUtils.insertMenuWithChildIntoDB(menuIdParent, entityId, kindMenuIdParent);  // parent dish ---> menu table
		WeChatUtils.insertSuitMenuIntoDB(menuIdPri,menuIdParent,entityId);   // parent dish ---> suit_menu_detail table
		WeChatUtils.insertChildMenuIntoDB(childMenuIdPri, menuIdChild, menuIdPri, entityId);   // child dish ---> suit_menu_change table
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown(){
		logger.info("tearDown in CartControllerTest");
		httpRequest.ShutDown();
		
		// clean menu data from mysql
		WeChatUtils.deleteMenuFromDB(menuIdChild);    // clean child menu in table menu
		
		WeChatUtils.deleteMenuFromDB(menuIdParent);   // clean parent menu in table menu
		WeChatUtils.deleteSuitMenuFromDB(menuIdPri);  // clean parent menu in table suit_menu_detail 
		WeChatUtils.deleteChildMenuFromDB(childMenuIdPri);  // clean child menu in table suit_menu_change
		
		logger.info("clear menu from db successfully");
		
 	}
	
	@Test(description = "get cart count test", dataProvider = "getCartCountTest",
			dataProviderClass = CartControllerTestData.class, groups = {"smoke", "all"})
	public void getCartCountTest(String description, Map<String, String> query, int responseStatus, int resultCode, int jobStatus){
		
		try{
			Response response = httpRequest.get(PathForPost.getPathForGetCartCount(), query); 
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
	

	@Test(description = "create own cart test", dataProvider = "createOwnCartTest",
			dataProviderClass = CartControllerTestData.class, groups = {"smoke", "all"})
	public void createOwnCartTest(String description, Map<String, String> query, int responseStatus, int resultCode, int jobStatus){
		
		try{
			Response response = httpRequest.post(PathForPost.getPathForCreateOwnCart(), query); 
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
	
	

	@Test(description = "get cart data test", dataProvider = "getCartDataTest",
			dataProviderClass = CartControllerTestData.class, groups = {"smoke", "all"})
	public void getCartDataTest(String description, Map<String, String> query, int responseStatus, int resultCode, int jobStatus){
		
		try{
			// prepare new cart
			CartControllerUtils.createCart(httpRequest, "99927792", "B1", "");
			
			Response response = httpRequest.get(PathForPost.getPathForGetCartData(), query); 
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
	
	/**
	 * 该接口比较复杂 , 涉及多张表及多处缓存, 注意需要预埋比较多的数据
	 */
	@Test(description = "modify cart test", dataProvider = "modifyCartTest",
			dataProviderClass = CartControllerTestData.class, groups = {"smoke", "all"})
	public void modifyCartTest(String description, Map<String, String> query, String httpBody, int responseStatus, int resultCode, int jobStatus){
		
		try{			
			Response response = httpRequest.post(PathForPost.getPathForModifyCart(), query, httpBody); 
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
	
	
	@Test(description = "clear cart test", dataProvider = "clearCartTest",
			dataProviderClass = CartControllerTestData.class, groups = {"smoke", "all"})
	public void clearCartTest(String description, Map<String, String> query, int responseStatus, int resultCode, int jobStatus){
		
		try{
			// prepare new cart
			CartControllerUtils.createCart(httpRequest, "99927792", "A4", orderId1);
			
			Response response = httpRequest.post(PathForPost.getPathForClearCart(), query); 
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
	
	
	@Test(description = "clear own cart test", dataProvider = "clearOwnCartTest",
			dataProviderClass = CartControllerTestData.class, groups = {"smoke", "all"})
	public void clearOwnCartTest(String description, Map<String, String> query, int responseStatus, int resultCode, int jobStatus){
		
		try{
			// prepare new cart
			CartControllerUtils.createCart(httpRequest, "99927792", "A4", orderId1);
			
			Response response = httpRequest.post(PathForPost.getPathForClearOwnCart(), query); 
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
	
	
	@Test(description = "modify info test", dataProvider = "modifyInfoTest",
			dataProviderClass = CartControllerTestData.class, groups = {"smoke", "all"})
	public void modifyInfoTest(String description, Map<String, String> query, int responseStatus, int resultCode, int jobStatus){
		
		try{
			// prepare new cart
			CartControllerUtils.createCart(httpRequest, "99927792", "A4", orderId1);
			
			Response response = httpRequest.post(PathForPost.getPathForModifyInfo(), query); 
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
	
	/**
	 * 该方法由于推荐功能降级，测试用例进行有限覆盖
	 * @param description
	 * @param query
	 * @param responseStatus
	 * @param resultCode
	 * @param jobStatus
	 */
	@Test(description = "get recommend menu list test", dataProvider = "getRecommendMenuListTest",
			dataProviderClass = CartControllerTestData.class, groups = {"smoke", "all"})
	public void getRecommendMenuListTest(String description, Map<String, String> query, int responseStatus, int resultCode, int jobStatus){
		
		try{
			// prepare new cart
			CartControllerUtils.createCart(httpRequest, "99927792", "A4", orderId1);
			
			Response response = httpRequest.get(PathForPost.getPathForGetRecommendMenuList(), query); 
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
	
	
	/**
	 * 该方法由于推荐功能降级，测试用例进行有限覆盖
	 * @param description
	 * @param query
	 * @param responseStatus
	 * @param resultCode
	 * @param jobStatus
	 */
	@Test(description = "get recommend menu by ID test", dataProvider = "getRecommendMenuByIdTest",
			dataProviderClass = CartControllerTestData.class, groups = {"smoke", "all"})
	public void getRecommendMenuByIdTest(String description, Map<String, String> query, int responseStatus, int resultCode, int jobStatus){
		
		try{
			// prepare new cart
			CartControllerUtils.createCart(httpRequest, "99927792", "A4", orderId1);
			
			Response response = httpRequest.get(PathForPost.getPathForGetRecommendMenuById(), query); 
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
	
	
//	@Test(description = " my test")
//	public void myTest(){
//		
//		try{
//			Map<String, String> data = new TreeMap<>();
//			String result = data.get(null);
//			logger.info("OK");
//		}catch(Exception e){
//			logger.info(e.toString());
//		}
//		
//	}
	
}
