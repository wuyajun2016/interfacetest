package com.dfire.testcase.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import redis.clients.jedis.Jedis;

import com.dfire.shop.redis.RedisManipulate;
import com.dfire.shop.redis.RedisUtils;
import com.dfire.testcase.shop.CartControllerTest;
import com.dfire.testcase.shop.CartControllerUtils;
import com.dfire.wechat.db.DataPrepared;
import com.dfire.wechat.db.WeChatUtils;
import com.dfire.wechat.util.BeanProvider;
import com.dfire.wechat.util.CommonConstants;
import com.dfire.wechat.util.HttpRequestEx;
import com.dfire.wechat.util.PathForPost;
import com.dfire.wechat.util.Response;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class OrderControllerTest {
	
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
	
	// child menu
	public static String menuIdChild = BeanProvider.getUUIDName();  
	public static String orderId = BeanProvider.getUUIDName();
	private static String kindMenuIdChild = BeanProvider.getUUIDName();  
	
	// parent menu
	public static String menuIdParent = BeanProvider.getUUIDName();  
	private static String kindMenuIdParent = BeanProvider.getUUIDName();  
	
	// for create own cart without child cartSuit
	private static String uid = "b379d59ed79c4991a05fb2eb2a7b1c2c";  // 与 使用的token 响对应
	public static String seatCode = "AK";
	public static String orderId1 = BeanProvider.getUUIDName();
	public static String entityId_cart = "99927792";
	private static String people_count = "6";
	private static String memo = "test_for_order_controller_in_weixin-meal";
	
	// set kindType to kind_normal with value 1
	private static String cartSuit = BeanProvider.getCartSuitJson(1, 1, menuIdChild, uid);
	public static String cartSuitList = BeanProvider.getCartSuitListJson(1, 1, menuIdChild, uid);
	public static String cartSuitListWithMultiDish = BeanProvider.getCartSuitListJson(1, 1, menuIdChild, uid, 2);
	
	// for create another cart with child cartSuit
	public static String seatCode2 = "AG";
	public static String orderId2 = BeanProvider.getUUIDName();
	
	// set kindType to kind_suit with value 2
	private static String cartSuit2 = BeanProvider.getCartSuitJson(1, 2, menuIdParent, menuIdChild, uid, true);
	public static String cartSuitList2 = BeanProvider.getCartSuitListJson(1, 2, menuIdParent, menuIdChild, uid, true);
	
	// define primary key for table suit_menu_detail and suit_menu_change
	public static String menuIdPri = BeanProvider.getUUIDName();
	private static String childMenuIdPri = BeanProvider.getUUIDName();
	
	private static String waitingOrderDetailIDForOrder1 = orderId1;
	private static String waitingOrderDetailIDForOrder2 = orderId2;
	
	private static String waitingInstanceInfoIDForOrder1 = BeanProvider.getUUIDName();
	private static String waitingInstanceInfoIDForOrder2 = BeanProvider.getUUIDName();
	
	///////////////////////////   prepared data for check order change test method ////////////////////////////////
	public static String menuIdForOrder = DataPrepared.menuId;
	public static String kindMenuIdForOrder = BeanProvider.getUUIDName();
	
	
	// get json of OrderIdsForm List 
	public static String orderIdsFormListJson1 = BeanProvider.getOrderIdsFormListJson(DataPrepared.orderDetailIdList.get(1), uid, menuIdForOrder, 
			DataPrepared.orderMenuFormStatus.get(1));
	public static String orderIdsFormListJson2 = BeanProvider.getOrderIdsFormListJson(DataPrepared.orderDetailIdList.get(2), uid, menuIdForOrder, 
			DataPrepared.orderMenuFormStatus.get(2));
	
	public static String seatCodeInOrderTable = "AB"; // the seatCode is equal to the seat code in class DataPrepared
	
	@BeforeClass(alwaysRun = true)
	public void setup(){
		
		logger.info("setup in OrderControllerTest");
		httpRequest = new HttpRequestEx(host, https);
		RedisManipulate.connectToRedis();
		
		// set cache for wenjing store
		RedisManipulate.setCache(tokenKey1, value1);
		RedisManipulate.setCache(sessionKey1, sessionBody1);
		
		RedisManipulate.setCache(tokenKey2, value2);
		RedisManipulate.setCache(sessionKey2, sessionBody2);
		
		logger.info("set token info successfully");
		
		
		// clear cart cache
		Jedis jedis = RedisUtils.createJedis("10.1.6.20", 7091, 0);  // connect to cart redis
		RedisUtils.deleteCache(jedis, "order_cart:99927792AK");
		RedisUtils.deleteCache(jedis, "order_cart:99927792AG");
		
		logger.info("clear order cart cache successfully");
		
		// prepare menu data into database
		WeChatUtils.insertMenuIntoDB(menuIdChild, entityId, kindMenuIdChild); // child dish ---> menu table
		WeChatUtils.insertMenuIntoDB(menuIdParent, entityId, kindMenuIdParent);// parent dish ---> menu table

		WeChatUtils.insertSuitMenuIntoDB(menuIdPri,menuIdParent,entityId);   // parent dish ---> suit_menu_detail table
		WeChatUtils.insertChildMenuIntoDB(childMenuIdPri, menuIdChild, menuIdPri, entityId);   // child dish ---> suit_menu_change table

		// create own cart without child cartSuit and add dishes into corresponding cart
		CartControllerUtils.createCart(httpRequest, entityId_cart, seatCode, orderId1, people_count, memo);
		CartControllerUtils.modifyCart(httpRequest, entityId_cart, seatCode, orderId1, cartSuit);
		
		// create another own cart with child cartSuit and add dishes into corresponding cart
		CartControllerUtils.createCart(httpRequest, entityId_cart, seatCode2, orderId2, people_count, memo);
		CartControllerUtils.modifyCart(httpRequest, entityId_cart, seatCode2, orderId2, cartSuit2);
		
		// prepare order data for orderId1 
		WeChatUtils.insertOrderDetailIntoDB(orderId1, waitingOrderDetailIDForOrder1, entityId, (int)(System.currentTimeMillis()/1000)); // insert data into table orderdetail		
		WeChatUtils.insertWaitingOrderDetailIntoDB(waitingOrderDetailIDForOrder1, orderId1, entityId); // insert data into table waiting order detail 
		WeChatUtils.insertWaitingInstanceInfoIntoDB(waitingInstanceInfoIDForOrder1, waitingOrderDetailIDForOrder1, menuIdChild, entityId);  // insert data into table waiting instance info 
		
		// prepare order data for orderId2 
		WeChatUtils.insertOrderDetailIntoDB(orderId2, waitingOrderDetailIDForOrder2, entityId, (int)(System.currentTimeMillis()/1000)); // insert data into table orderdetail		
		WeChatUtils.insertWaitingOrderDetailIntoDB(waitingOrderDetailIDForOrder2, orderId2, entityId); // insert data into table waiting order detail 
		WeChatUtils.insertWaitingInstanceInfoIntoDB(waitingInstanceInfoIDForOrder2, waitingOrderDetailIDForOrder2, menuIdParent, entityId);  // insert data into table waiting instance info 
		
		
		
		/////////////////////////////   prepared data for check order change test method /////////////////////////////////////////////
		DataPrepared.preparedDataForCheckOrderChange(entityId);
		
		
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown(){
		
		logger.info("tearDown in OrderControllerTest");
		httpRequest.ShutDown();
		
		// clean menu data from mysql
		WeChatUtils.deleteMenuFromDB(menuIdChild);
		WeChatUtils.deleteMenuFromDB(menuIdParent);
		
		WeChatUtils.deleteSuitMenuFromDB(menuIdPri);        // clean parent menu in table suit_menu_detail 
		WeChatUtils.deleteChildMenuFromDB(childMenuIdPri);  // clean child menu in table suit_menu_change
		
		// clean order data 
		WeChatUtils.deleteOrderDetailFromDB(orderId1);
		WeChatUtils.deleteWaitingOrderDetailFromDB(waitingOrderDetailIDForOrder1);
		WeChatUtils.deleteWaitingInstanceFromDB(waitingInstanceInfoIDForOrder1);
		
		WeChatUtils.deleteOrderDetailFromDB(orderId2);
		WeChatUtils.deleteWaitingOrderDetailFromDB(waitingOrderDetailIDForOrder2);
		WeChatUtils.deleteWaitingInstanceFromDB(waitingInstanceInfoIDForOrder2);
		
		logger.info("clear menu and order data from db successfully");
		
		/////////////////////////////   delete data for check order change test method //////////////////////////////////////////////////
		DataPrepared.deleteDataForCheckOrderChange();

		
	}
	
	

	@Test(description = "lock pay order test", dataProvider = "lockOrderTest",
			dataProviderClass = OrderControllerTestData.class, groups = {"smoke", "all"})
	public void lockPayOrderTest(String description, Map<String, String> query, 
			                   int responseStatus, int resultCode, int jobStatus){
		
		try{
			Response response = httpRequest.post(PathForPost.getPathForLockOrder(), query); 
			Assert.assertEquals(response.getStatus(), responseStatus);
			
			if(responseStatus == 200){
				JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
				Assert.assertEquals(resp.get("code").getAsInt(), resultCode);
				
//				if(jobStatus == 200){
//					Assert.assertNotNull(resp.get("data").getAsJsonObject().get("seatId"));
//				}
			}
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}
	

	
	
	@Test(description = "unlock pay order test", dataProvider = "unlockPayOrderTest",
			dataProviderClass = OrderControllerTestData.class, groups = {"smoke", "all"})
	public void unlockPayOrderTest(String description, Map<String, String> query, 
			                   int responseStatus, int resultCode, int jobStatus){
		
		try{
			Response response = httpRequest.post(PathForPost.getPathForUnLockPayOrder(), query); 
			Assert.assertEquals(response.getStatus(), responseStatus);
			
			if(responseStatus == 200){
				JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
				Assert.assertEquals(resp.get("code").getAsInt(), resultCode);
				
			}
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}
	
	

	
	@Test(description = "check order test", dataProvider = "checkOrderTest",
			dataProviderClass = OrderControllerTestData.class, groups = {"smoke", "all"})
	public void checkOrderTest(String description, Map<String, String> query, String httpBody, 
			                   int responseStatus, int resultCode, int jobStatus){
		
		try{
			
			Response response = httpRequest.post(PathForPost.getPathForCheckOrder(), query, httpBody); 
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
	
	
	@Test(description = "check order change test", dataProvider = "checkOrderChangeTest",
			dataProviderClass = OrderControllerTestData.class, groups = {"smoke", "all"})
	public void checkOrderChangeTest(String description, Map<String, String> query, String httpBody, 
			                   int responseStatus, int resultCode, int jobStatus){
		
		try{
			
			Response response = httpRequest.post(PathForPost.getPathForCheckOrderChange(), query, httpBody); 
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

	
	
	@Test(description = "confirm order test", dataProvider = "confirmOrderTest",
			dataProviderClass = OrderControllerTestData.class, groups = {"smoke", "all"})
	public void confirmOrderTest(String description, Map<String, String> query, String httpBody, 
			                   int responseStatus, int resultCode, int jobStatus){
		
		try{
			
			Response response = httpRequest.post(PathForPost.getPathForConfirmOrder(), query, httpBody); 
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
	
	
	@Test(description = "modify memo test", dataProvider = "modifyMemoTest",
			dataProviderClass = OrderControllerTestData.class, groups = {"smoke", "all"})
	public void modifyMemoTest(String description, Map<String, String> query, 
			                   int responseStatus, int resultCode, int jobStatus){
		
		try{
			
			Response response = httpRequest.post(PathForPost.getPathForModifyMemo(), query); 
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
	

	@Test(description = "get order test", dataProvider = "getOrderTest",
			dataProviderClass = OrderControllerTestData.class, groups = {"smoke", "all"})
	public void getOrderTest(String description, Map<String, String> query, int responseStatus, int resultCode, 
			boolean clientOrderExist, boolean waitingOrderExist){
		
		try{
			
			Response response = httpRequest.get(PathForPost.getPathForGetOrder(), query); 
			Assert.assertEquals(response.getStatus(), responseStatus);
			
			if(responseStatus == 200){
				JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
				Assert.assertEquals(resp.get("code").getAsInt(), resultCode);
				
				
				// for clientOrder
				boolean clientOrder = false;
				if( null != resp.get("data").getAsJsonObject() && null != resp.get("data").getAsJsonObject().get("clientOrderVos"))
					clientOrder = true;
				Assert.assertEquals(clientOrderExist, clientOrder);
				
				if(clientOrderExist){
					
					JsonElement clientOrderResult = resp.get("data").getAsJsonObject().get("clientOrderVos").
							getAsJsonArray().get(0).getAsJsonObject().get("seatCode");
					Assert.assertNotNull(clientOrderResult);
				}
				
				// for waitingOrder
				boolean waitingOrder = false;
				if( null != resp.get("data").getAsJsonObject() && null != resp.get("data").getAsJsonObject().get("waitingOrderVos"))
					waitingOrder = true;
				Assert.assertEquals(waitingOrderExist, waitingOrder);
				
				if(waitingOrderExist){
					
					JsonElement waitingOrderResult = resp.get("data").getAsJsonObject().get("waitingOrderVos").
							getAsJsonArray().get(0).getAsJsonObject().get("seatCode");
					Assert.assertNotNull(waitingOrderResult);
				}

				
			}
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}
	
	// turtle-soa  querySeat  throw nullpointer exception
	@Test(description = "get koubei order test", dataProvider = "getKoubeiOrderTest",
			dataProviderClass = OrderControllerTestData.class, groups = {"smoke", "all"})
	public void getKoubeiOrderTest(String description, Map<String, String> query, 
			                   int responseStatus, int resultCode, int jobStatus){
		
		try{
			
			Response response = httpRequest.get(PathForPost.getPathForGetKoubeiOrder(), query); 
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
	
	
	
	
	@Test(description = "get order history test", dataProvider = "getOrderHistoryTest",
			dataProviderClass = OrderControllerTestData.class, groups = {"smoke", "all"})
	public void getOrderHistoryTest(String description, Map<String, String> query, 
			                   int responseStatus, int resultCode, int jobStatus){
		
		try{
			
			Response response = httpRequest.get(PathForPost.getPathForGetOrderHistory(), query); 
			Assert.assertEquals(response.getStatus(), responseStatus);
			
			if(responseStatus == 200){
				JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
				Assert.assertEquals(resp.get("code").getAsInt(), resultCode);
				
				if(jobStatus == 200){
					Assert.assertNotNull(resp.get("data").getAsJsonArray().get(0).getAsJsonObject().get("orderId"));
				}
			}
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}
	
	
	// 对应对的 order 方法有bug
	@Test(description = "reissued pre order test", dataProvider = "reIssuedPreOrderTest",
			dataProviderClass = OrderControllerTestData.class, groups = {"smoke", "all"})
	public void reIssuedPreOrderTest(String description, Map<String, String> query, 
			                   int responseStatus, int resultCode, int jobStatus){
		
		try{
			
			Response response = httpRequest.post(PathForPost.getPathForReIssuedPreOrder(), query); 
			Assert.assertEquals(response.getStatus(), responseStatus);
			
			if(responseStatus == 200){
				JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
				Assert.assertEquals(resp.get("code").getAsInt(), resultCode);
				
				if(jobStatus == 200){
					Assert.assertNotNull(resp.get("data").getAsJsonArray().get(0).getAsJsonObject().get("orderId"));
				}
			}
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}
	
	
}
