package com.dfire.testcase.function.dishes;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.alibaba.dubbo.remoting.http.HttpServer;
import com.dfire.testcase.function.bean.BaseParamBean;
import com.dfire.testcase.function.boss.BossController;
import com.dfire.testcase.function.boss.ForceConfig;
import com.dfire.testcase.function.order.OrderForNoPrePayShopTestData;
import com.dfire.testcase.function.service.DataService;
import com.dfire.testcase.function.util.api.SeatPrePayAPI;
import com.dfire.testcase.function.util.base.SeatNoPrePayUtil;
import com.dfire.testcase.function.util.base.ShopNoPrePayUtil;
import com.dfire.testcase.function.util.base.WechatBaseUtil;
import com.dfire.wechat.util.BeanProvider;
import com.dfire.wechat.util.CartIncludeSuitForm;
import com.dfire.wechat.util.CommonConstants;
import com.dfire.wechat.util.HttpRequestEx;
import com.dfire.wechat.util.Response;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class OrderDishesForNoPrePayShopTest {
	
	private static final Logger logger = Logger.getLogger(OrderDishesForNoPrePayShopTest.class);
	private String host = CommonConstants.DEFAULT_HOST;
	private HttpRequestEx httpRequestForBossAPI;
	private HttpRequestEx httpRequest;	
	
	private String entityId = CommonConstants.entityId;
	private String seatCode = CommonConstants.seatCode;
	private String orderId = "";
	
	private String repeat = "false";	
	private String recommend = "false";
	private String signKey = CommonConstants.signKey;
	
	private String queueId = "";
	private String xtoken = "";
	private String customerRegisterId = CommonConstants.UID;
	
	private Gson gson = new Gson();	
	private BaseParamBean baseParam = new BaseParamBean();	
	
	private String xtokenOther = "";
	private String unionIdOther = CommonConstants.unionIDOther;
	private BaseParamBean baseParamOther = new BaseParamBean();	
	
	private Map<String, String> otherParameter = new HashMap<String, String>();
	private boolean https = CommonConstants.HTTPS;
	private List<String> menuIdList = new ArrayList<String>();
	
	@BeforeClass(alwaysRun = true)
	public void setup(){
		
		logger.info("setup in OrderDishesForNoPrePaySeatTest");
		httpRequest = new HttpRequestEx(host, https);
		httpRequestForBossAPI = new HttpRequestEx(CommonConstants.BOSS_API_HOST, https);
		
		// 获取 token
		xtoken = WechatBaseUtil.getToken(httpRequest, entityId, CommonConstants.unionID);
		xtokenOther = WechatBaseUtil.getToken(httpRequest, entityId, unionIdOther);
		
		// 构造参数		
		baseParam.setXtoken(xtoken);
		baseParam.setEntityId(entityId);
		baseParam.setSeatCode(seatCode);
		baseParam.setOrderId(orderId);
				
		baseParamOther.setXtoken(xtokenOther);
		baseParamOther.setEntityId(entityId);
		baseParamOther.setSeatCode(seatCode);
		baseParamOther.setOrderId(orderId);
		
		otherParameter.put("queueId", queueId);		
		otherParameter.put("repeat", repeat);
		otherParameter.put("recommend", recommend);
		
		otherParameter.put("signKey", signKey);
		otherParameter.put("customerRegisterId", customerRegisterId);		
		otherParameter.put("menuId", CommonConstants.menuId);
		
		
		// parameters for scanning seat code 
		otherParameter.put("people", "6");
		otherParameter.put("memo", "");
		otherParameter.put("oldPeople", "");
		
		otherParameter.put("page", "");
		otherParameter.put("pageSize", "");
		otherParameter.put("isPreCart", "false");
		
		otherParameter.put("peopleCount", "6");
		otherParameter.put("menuIdList", gson.toJson(menuIdList)); // 该参数为空
		otherParameter.put("memo_labels", "");
		
		// 用于提交订单
		otherParameter.put("isPrePay", "false");
		otherParameter.put("cartTime", Long.toString(System.currentTimeMillis()));
		
		// scan shop code
		ShopNoPrePayUtil.scanCode(httpRequest, baseParam, otherParameter);
		
		// click to enter  (pre conditions)
		ShopNoPrePayUtil.clickToEnter(httpRequest, baseParam, otherParameter);
	}
	
	
	@AfterClass(alwaysRun = true)
	public void tearDown(){
		logger.info("tearDown in OrderDishesForNoPrePayShopTest");
		httpRequest.ShutDown();
		httpRequestForBossAPI.ShutDown();
	}
	
	
/////////////////////////////////   业务测试用例       //////////////////////////////////////////////////////////////////////


	@Test(description = "将普通菜或者套菜加入购物车", dataProvider = "addDishToCartTest",
	dataProviderClass = OrderDishesForNoPrePayShopTestData.class, groups = {"module", "all"})
	public void addDishToCartTest(String description, List<CartIncludeSuitForm> cartSuitList, String cartSuitListJson){
	
		logger.info(description);
		String people = "0";
		
		try{
		
			//////////////////////// 前置操作, 包括数据准备      //////////////////////////////////////
			
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			WechatBaseUtil.clearCartForShop(httpRequest, baseParam, otherParameter);
			
			
			
			/////////////////////////    业务操作         ////////////////////////////////////////
			
			// 模拟业务操作, 获取购物车内菜品数据
			ShopNoPrePayUtil.getUserCart(httpRequest, baseParam, otherParameter);
					
			
			//加菜到购物车
			for(CartIncludeSuitForm cartSuitForm : cartSuitList){
				ShopNoPrePayUtil.modifyCart(httpRequest, baseParam, gson.toJson(cartSuitForm));
			}

			
			
			/////////////////////////    数据校验      ///////////////////////////////////////////

			boolean result1 = DataService.DataVerified(httpRequest, baseParam, otherParameter, people, cartSuitList);
			Assert.assertEquals(result1, true);
			
			
			////////////////////////   后置操作         ////////////////////////////////////////////
			
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			WechatBaseUtil.clearCartForShop(httpRequest, baseParam, otherParameter);
		
			
		}catch(Exception e){
			
			logger.info(e.toString());
			
		}finally{
			
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			WechatBaseUtil.clearCartForShop(httpRequest, baseParam, otherParameter);
			
		}
	
	}
	
	
	@Test(description = "选择单个普通菜, 加入购物车, 清空购物车, 重新加菜", dataProvider = "addDishToCartTest2",
			dataProviderClass = OrderDishesForNoPrePayShopTestData.class, groups = {"module", "all"})
	public void addDishToCartTest2(String description, List<CartIncludeSuitForm> cartSuitListOne, 
			List<CartIncludeSuitForm> cartSuitListTwo, String cartList1Json, String cartList2Json){
		
		logger.info(description);
		String people = "0";
		
		try{
			
			//////////////////////// 前置操作, 包括数据准备      //////////////////////////////////////
						
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			WechatBaseUtil.clearCartForShop(httpRequest, baseParam, otherParameter);
			
			
			
			/////////////////////////    业务操作         ////////////////////////////////////////
			
			// 获取购物车内的菜品数据
			ShopNoPrePayUtil.getUserCart(httpRequest, baseParam, otherParameter);
					
			
			//加菜到购物车
			for(CartIncludeSuitForm cartSuitForm : cartSuitListOne){
				ShopNoPrePayUtil.modifyCart(httpRequest, baseParam, gson.toJson(cartSuitForm));
			}
			
			
			
			/////////////////////////    数据校验      ///////////////////////////////////////////
			
			boolean result1 = DataService.DataVerified(httpRequest, baseParam, otherParameter, people, cartSuitListOne);
			Assert.assertEquals(result1, true);
			
			
			////////////////////////   测试操作         ////////////////////////////////////////////
			
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			WechatBaseUtil.clearCartForShop(httpRequest, baseParam, otherParameter);
			
			
			/////////////////////////    业务操作         ////////////////////////////////////////
						
			// 模拟业务操作, 获取购物车内菜品数据
			ShopNoPrePayUtil.getUserCart(httpRequest, baseParam, otherParameter);
					
			
			//加菜到购物车
			for(CartIncludeSuitForm cartSuitForm : cartSuitListTwo){
				ShopNoPrePayUtil.modifyCart(httpRequest, baseParam, gson.toJson(cartSuitForm));
			}
			
			
			/////////////////////////    数据校验      ///////////////////////////////////////////
						
			boolean result2 = DataService.DataVerified(httpRequest, baseParam, otherParameter, people, cartSuitListTwo);
			Assert.assertEquals(result2, true);


			////////////////////////后置操作         ////////////////////////////////////////////
						
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			WechatBaseUtil.clearCartForShop(httpRequest, baseParam, otherParameter);
			
		}catch(Exception e){
			
			logger.info(e.toString());
			
		}finally{
			
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			WechatBaseUtil.clearCartForShop(httpRequest, baseParam, otherParameter);
			
		}
	
	}


	/**
	 * 在扫店码后扫桌码前, 必选菜不起作用
	 * @param description
	 * @param cartSuitList
	 * @param cartSuitListWithRequiredDish
	 * @param forceConfig
	 */
	@Test(description = "掌柜端打开添加必选菜开关, 添加普通菜到购物车", dataProvider = "addDishToCartTest4",
			dataProviderClass = OrderDishesForNoPrePayShopTestData.class, groups = {"moduleNo", "allNo"})
	public void addDishToCartTest4(String description, List<CartIncludeSuitForm> cartSuitList, 
			List<CartIncludeSuitForm> cartSuitListWithRequiredDish, ForceConfig forceConfig, 
			String cartList1Json, String cartList2Json, String forceconFigJson){
		
		logger.info(description);
		String people = "0";  // 扫店码加菜阶段无法修改人数, 默认为 0
		
		BossController bossController = new BossController();
		
		try{
			
			//////////////////////// 前置操作, 包括数据准备      //////////////////////////////////////
			
			// 掌柜端设置添加必选菜			
			bossController.saveForceMenu(httpRequestForBossAPI, baseParam.getEntityId(), forceConfig);  // 设置可口可乐味必选菜
			Thread.sleep(30*1000);  // 等待掌柜端设置生效
			
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			WechatBaseUtil.clearCartForShop(httpRequest, baseParam, otherParameter);
						
			
			/////////////////////////    业务操作         ////////////////////////////////////////
			// 修改人数和备注信息
			WechatBaseUtil.modifyPeople(httpRequest, baseParam, otherParameter);
			
			// 添加必选商品
			WechatBaseUtil.addRequiredItem(httpRequest, baseParam, otherParameter);			
			
			//将菜加入购物车
			for(CartIncludeSuitForm cartSuitForm : cartSuitList){
				ShopNoPrePayUtil.modifyCart(httpRequest, baseParam, gson.toJson(cartSuitForm));
			}
		
			
			/////////////////////////    数据校验      ///////////////////////////////////////////
			boolean result1 = DataService.DataVerified(httpRequest, baseParam, otherParameter, people, cartSuitListWithRequiredDish);
			Assert.assertEquals(result1, true);
			
			
			////////////////////////   后置操作         ////////////////////////////////////////////
			
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			WechatBaseUtil.clearCartForShop(httpRequest, baseParam, otherParameter);
						
			
		}catch(Exception e){
			
			logger.info(e.toString());
			
		}finally{
			
			// 掌柜端移除必选菜
			bossController.removeForceMenu(httpRequestForBossAPI, baseParam.getEntityId(), CommonConstants.cokeMenuId);
			try{
				
				Thread.sleep(30*1000);  // 等待掌柜端设置生效			
			
				// 清除购物车中的所有菜 (测试需要, 非业务流程)
				WechatBaseUtil.clearCartForShop(httpRequest, baseParam, otherParameter);
				
				///////////////   通过下单的方式清除必选菜     ///////////////////////////////////////////////////////				
				Thread.sleep(5*60*1000); // 使得之前的订单失效, 避免对现在的下单动作产生影响
				
				Response cartData = WechatBaseUtil.listCartData(httpRequest, baseParam);
				JsonObject checkJsonObject = new JsonParser().parse(cartData.getResponseStr()).getAsJsonObject();			
				Assert.assertEquals(checkJsonObject.get("code").getAsInt(), 1, "获取购物车数据失败");
				String cartTime = checkJsonObject.get("data").getAsJsonObject().get("cartTime").getAsString();
				otherParameter.put("cartTime", cartTime);
								
				//提交订单
				Response orderResult = WechatBaseUtil.submitOrder(httpRequest, baseParam, otherParameter);
				JsonObject orderObject = new JsonParser().parse(orderResult.getResponseStr()).getAsJsonObject();			
				Assert.assertEquals(orderObject.get("code").getAsInt(), 1, "提交订单失败");
			}catch(Exception e){
				logger.error(e.toString());
			}
		}
	
	}
	
	
	
	@Test(description = "将普通菜或者套菜加入购物车, 之后扫桌码, 再次进行加普通菜或者套菜", dataProvider = "addDishToCartWithSeatCodeTest",
			dataProviderClass = OrderDishesForNoPrePayShopTestData.class, groups = {"module", "all"})
	public void addDishToCartWithSeatCodeTest(String description, List<CartIncludeSuitForm> cartSuitListForShop, 
			List<CartIncludeSuitForm> cartSuitListForSeat, List<CartIncludeSuitForm> cartSuitListForTotal, String cartSuitListJsonForTotal){
			
		logger.info(description);
		String people = "0";
		
		try{
		
			//////////////////////// 前置操作, 包括数据准备      //////////////////////////////////////
			
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			WechatBaseUtil.clearCart(httpRequest, baseParam);
			WechatBaseUtil.clearCartForShop(httpRequest, baseParam, otherParameter);
			
			
			/////////////////  扫店码加菜开始     ///////////////////////////////////////////////////
			/////////////////////////    业务操作         ////////////////////////////////////////
			
			// 模拟业务操作, 获取购物车内菜品数据
			ShopNoPrePayUtil.getUserCart(httpRequest, baseParam, otherParameter);
					
			
			//加菜到购物车
			for(CartIncludeSuitForm cartSuitForm : cartSuitListForShop){
				ShopNoPrePayUtil.modifyCart(httpRequest, baseParam, gson.toJson(cartSuitForm));
			}

			
			
			/////////////////////////    数据校验      ///////////////////////////////////////////

			boolean result1 = DataService.DataVerified(httpRequest, baseParam, otherParameter, people, cartSuitListForShop);
			Assert.assertEquals(result1, true);
			
			/////////////////  扫店码加菜结束     ///////////////////////////////////////////////////
			
			
			
			
			
			
			
			/////////////////  扫桌码加菜开始       ///////////////////////////////////////////////////
			
			// 构建 HTTP head 确保为同一个 session, 通过 entityId 以及 token 来确保为同一个 session
			Map<String, String> header = new HashMap<String, String>();
			header.put("Cookie", "entity_id=" + entityId + ";token=" + xtoken);
			
			// scan seat code
			SeatNoPrePayUtil.scanCode(httpRequest, baseParam, otherParameter, header);
			
			// click to enter  (pre conditions)
			SeatPrePayAPI.clickToEnter(httpRequest, baseParam, otherParameter);
			
			/////////////////////////    业务操作         ////////////////////////////////////////
			
			// 修改人数和备注信息
			WechatBaseUtil.modifyPeople(httpRequest, baseParam, otherParameter);
			
			// 添加必选商品
			WechatBaseUtil.addRequiredItem(httpRequest, baseParam, otherParameter);			
			
			//将菜加入购物车
			for(CartIncludeSuitForm cartSuitForm : cartSuitListForSeat){
				WechatBaseUtil.addDishToCarts(httpRequest,baseParam, gson.toJson(cartSuitForm));
			}
			
			
			/////////////////////////    数据校验      ///////////////////////////////////////////
			boolean result2 = DataService.DataVerified(httpRequest, baseParam, otherParameter.get("people"), cartSuitListForTotal);
			Assert.assertEquals(result2, true);

			/////////////////  扫桌码加菜结束      ///////////////////////////////////////////////////
			
			
			
			////////////////////////   后置操作         ////////////////////////////////////////////
			
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			WechatBaseUtil.clearCart(httpRequest, baseParam);
			WechatBaseUtil.clearCartForShop(httpRequest, baseParam, otherParameter);
		
			
		}catch(Exception e){
			
			logger.info(e.toString());
			
		}finally{
			
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			WechatBaseUtil.clearCart(httpRequest, baseParam);
			WechatBaseUtil.clearCartForShop(httpRequest, baseParam, otherParameter);
		}
	
	}
	
	
	@Test(description = "多个用户操作, 将普通菜或者套菜加入购物车, 之后扫桌码, 再次进行加普通菜或者套菜", dataProvider = "orderDishForMultiCustomer",
			dataProviderClass = OrderDishesForNoPrePayShopTestData.class, groups = {"module", "all"})
	public void orderForMultiCustomer(String description, List<CartIncludeSuitForm> cartSuitListForShop, List<CartIncludeSuitForm> cartSuitListForSeatOne,
			List<CartIncludeSuitForm> cartSuitListForSeatTwo, List<CartIncludeSuitForm> cartSuitListForTotal){
			
		logger.info(description);
		String people = "0";
		
		try{
		
			//////////////////////// 前置操作, 包括数据准备      //////////////////////////////////////
			
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			WechatBaseUtil.clearCart(httpRequest, baseParam);
			WechatBaseUtil.clearCartForShop(httpRequest, baseParam, otherParameter);
			
			
			
			
			/////////////////  扫店码加菜开始     ///////////////////////////////////////////////////
			/////////////////////////    业务操作         ////////////////////////////////////////
			
			// 模拟业务操作, 获取购物车内菜品数据
			ShopNoPrePayUtil.getUserCart(httpRequest, baseParam, otherParameter);
					
			
			//加菜到购物车
			for(CartIncludeSuitForm cartSuitForm : cartSuitListForShop){
				ShopNoPrePayUtil.modifyCart(httpRequest, baseParam, gson.toJson(cartSuitForm));
			}

			
			
			/////////////////////////    数据校验      ///////////////////////////////////////////

			boolean result1 = DataService.DataVerified(httpRequest, baseParam, otherParameter, people, cartSuitListForShop);
			Assert.assertEquals(result1, true);
			
			/////////////////  扫店码加菜结束     ///////////////////////////////////////////////////
			
			
						
			
			
			/////////////////  扫桌码加菜开始       ///////////////////////////////////////////////////
			
			// 构建 HTTP head 确保为同一个 session, 通过 entityId 以及 token 来确保为同一个 session
			Map<String, String> header = new HashMap<String, String>();
			header.put("Cookie", "entity_id=" + entityId + ";token=" + xtoken);
			
			// scan seat code
			SeatNoPrePayUtil.scanCode(httpRequest, baseParam, otherParameter, header);
			
			// click to enter  (pre conditions)
			SeatPrePayAPI.clickToEnter(httpRequest, baseParam, otherParameter);
			
			/////////////////////////    业务操作         ////////////////////////////////////////
			
			// 修改人数和备注信息
			WechatBaseUtil.modifyPeople(httpRequest, baseParam, otherParameter);
			
			// 添加必选商品
			WechatBaseUtil.addRequiredItem(httpRequest, baseParam, otherParameter);			
			
			
			//用户 A 将菜加入购物车
			for(CartIncludeSuitForm cartSuitForm : cartSuitListForSeatOne){
				WechatBaseUtil.addDishToCarts(httpRequest,baseParam, gson.toJson(cartSuitForm));
			}
			
			
			//客户 B 将菜加入购物车
			for(CartIncludeSuitForm cartSuitForm : cartSuitListForSeatTwo){
				WechatBaseUtil.addDishToCarts(httpRequest, baseParamOther, gson.toJson(cartSuitForm));
			}
			
			/////////////////////////    数据校验      ///////////////////////////////////////////
			boolean result2 = DataService.DataVerified(httpRequest, baseParam, otherParameter.get("people"), cartSuitListForTotal);
			Assert.assertEquals(result2, true);

			/////////////////  扫桌码加菜结束      ///////////////////////////////////////////////////
			
			
			
			
		}catch(Exception e){
			
			logger.info(e.toString());
			
		}finally{
			
			////////////////////////后置操作         ////////////////////////////////////////////
						
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			WechatBaseUtil.clearCart(httpRequest, baseParam);
			WechatBaseUtil.clearCartForShop(httpRequest, baseParam, otherParameter);
			
		}
	
	}
		
	
	
	
	@Test
	public void clearCartsTest(){
		
		// 清除购物车中的所有菜 (测试需要, 非业务流程)
		WechatBaseUtil.clearCartForShop(httpRequest, baseParam, otherParameter);
	}
	
	
}
