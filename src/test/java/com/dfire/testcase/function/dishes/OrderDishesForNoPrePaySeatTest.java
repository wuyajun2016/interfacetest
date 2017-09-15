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
import com.dfire.testcase.function.service.DataService;
import com.dfire.testcase.function.util.api.SeatPrePayAPI;
import com.dfire.testcase.function.util.base.SeatNoPrePayUtil;
import com.dfire.testcase.function.util.base.WechatBaseUtil;
import com.dfire.wechat.util.CartIncludeSuitForm;
import com.dfire.wechat.util.CommonConstants;
import com.dfire.wechat.util.HttpRequestEx;
import com.dfire.wechat.util.PathForPost;
import com.dfire.wechat.util.Response;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class OrderDishesForNoPrePaySeatTest {
	
	private static final Logger logger = Logger.getLogger(OrderDishesForNoPrePaySeatTest.class);
	private String host = CommonConstants.DEFAULT_HOST;
	private HttpRequestEx httpRequest;	
	private HttpRequestEx httpRequestForBossAPI;
	
	private String entityId = CommonConstants.entityId;
	private String seatCode = CommonConstants.seatCode;
	private String orderId = "";
	
	private String repeat = "false";	
	private String recommend = "false";
	private String signKey = CommonConstants.signKey;
	
	
	private String xtoken = "";
	private String unionId = CommonConstants.unionID;
	private Gson gson = new Gson();
	
	private String xtokenOther = "";
	private String unionIdOther = CommonConstants.unionIDOther;
	private BaseParamBean baseParamOther = new BaseParamBean();	
	
	private BaseParamBean baseParam = new BaseParamBean();	
	private Map<String, String> otherParameter = new HashMap<String, String>();
	private boolean https = CommonConstants.HTTPS;
	
	private BossController bossController = new BossController();
	private List<String> menuIdList = new ArrayList<String>();
	
	@BeforeClass(alwaysRun = true)
	public void setup(){
		
		logger.info("setup in OrderDishesForNoPrePaySeatTest");
		httpRequest = new HttpRequestEx(host, https);
		httpRequestForBossAPI = new HttpRequestEx(CommonConstants.BOSS_API_HOST, https);
		
		// 获取 token
		xtoken = WechatBaseUtil.getToken(httpRequest, entityId, unionId);
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
				
		otherParameter.put("people", "6");
		otherParameter.put("memo", "");
		otherParameter.put("oldPeople", "");
		
		otherParameter.put("repeat", repeat);
		otherParameter.put("recommend", recommend);
		otherParameter.put("signKey", signKey);
		
		otherParameter.put("page", "");
		otherParameter.put("pageSize", "");
		otherParameter.put("isPreCart", "false");
		
		otherParameter.put("menuId", CommonConstants.menuId);
		otherParameter.put("menuIdList", gson.toJson(menuIdList)); // 该参数为空
		otherParameter.put("memo_labels", "");
		
		// 用于提交订单
		otherParameter.put("isPrePay", "false");
		otherParameter.put("cartTime", Long.toString(System.currentTimeMillis()));
		
		// scan code
		SeatNoPrePayUtil.scanCode(httpRequest, baseParam, otherParameter);
		SeatNoPrePayUtil.scanCode(httpRequest, baseParamOther, otherParameter);
		
		// click to enter  (pre conditions)
		SeatPrePayAPI.clickToEnter(httpRequest, baseParam, otherParameter);
		SeatPrePayAPI.clickToEnter(httpRequest, baseParamOther, otherParameter);
		
		// 掌柜端移除必选菜
		bossController.removeForceMenu(httpRequestForBossAPI, baseParam.getEntityId(), CommonConstants.cokeMenuId);
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown(){
		
		logger.info("tearDown in OrderDishesForNoPrePaySeatTest");
		
		// 掌柜端移除必选菜
		bossController.removeForceMenu(httpRequestForBossAPI, baseParam.getEntityId(), CommonConstants.cokeMenuId);		
		logger.info("remove force menu success ");
		
		httpRequest.ShutDown();
		httpRequestForBossAPI.ShutDown();
		
		
		
	}
	
	
	// 修改人数和备注信息
	@Test(description = "modify people memo", dataProvider = "modifyPeopleTest",
			dataProviderClass = OrderDishesForNoPrePaySeatTestData.class, groups = {"smoke", "all"})
	public void modifyPeopleTest(String description, Map<String, String> query, int responseStatus, int resultCode, int jobStatus){
		
		try{
			
			Response response = httpRequest.post(PathForPost.getPathForModifyInfo(), query); 
			Assert.assertEquals(response.getStatus(), responseStatus, "修改人数和备注信息接口返回状态验证失败");
			
			if(responseStatus == 200){
				JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
				Assert.assertEquals(resp.get("code").getAsInt(), resultCode, "修改人数和备注信息接口返回 code 验证失败");
								
			}
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}


	
	// 添加必选商品
	@Test(description = "add required item ", dataProvider = "addRequiredItemTest",
			dataProviderClass = OrderDishesForNoPrePaySeatTestData.class, groups = {"smoke", "all"})
	public void addRequiredItemTest(String description, Map<String, String> query, int responseStatus, int resultCode, int jobStatus){
		
		try{
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			WechatBaseUtil.clearCart(httpRequest, baseParam);
			
			// 修改人数和备注信息
			WechatBaseUtil.modifyPeople(httpRequest, baseParam, otherParameter);
			
			Response response = httpRequest.post(PathForPost.getPathForForceMenu(), query); 
			Assert.assertEquals(response.getStatus(), responseStatus, " 添加必选商品接口返回状态验证失败");
			
			if(responseStatus == 200){
				JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
				Assert.assertEquals(resp.get("code").getAsInt(), resultCode, " 添加必选商品接口返回 code 验证失败");
				
			}			
			
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			WechatBaseUtil.clearCart(httpRequest, baseParam);
			
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}
	
	
	// 获取购物车推荐菜列表
	@Test(description = "get recommend menus", dataProvider = "getRecommendMenusTest",
			dataProviderClass = OrderDishesForNoPrePaySeatTestData.class, groups = {"smoke", "all"})
	public void getRecommendMenusTest(String description, Map<String, String> query, int responseStatus, int resultCode, int jobStatus){
		
		try{
			
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			WechatBaseUtil.clearCart(httpRequest, baseParam);
			
			// 修改人数和备注信息
			WechatBaseUtil.modifyPeople(httpRequest, baseParam, otherParameter);
			
			// 添加必选商品
			WechatBaseUtil.addRequiredItem(httpRequest, baseParam, otherParameter);
			
			// 获取虚拟购物车数据及用餐人数
			WechatBaseUtil.listCartData(httpRequest, baseParam);
			
			Response response = httpRequest.get(PathForPost.getPathForRecommendMenu(), query); 
			Assert.assertEquals(response.getStatus(), responseStatus, "获取购物车推荐菜列表接口返回状态验证失败");
			
			if(responseStatus == 200){
				JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
				Assert.assertEquals(resp.get("code").getAsInt(), resultCode, "获取购物车推荐菜列表接口返回 code 验证失败");
				
			}
			
			
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			WechatBaseUtil.clearCart(httpRequest, baseParam);						
						
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}
		
	// 获取菜单详情只包括规格做法
	@Test(description = "get menu spec", dataProvider = "getMenuSpecTest",
			dataProviderClass = OrderDishesForNoPrePaySeatTestData.class, groups = {"smoke", "all"})
	public void getMenuSpecTest(String description, String entityIdForMenuSpec, String menuId, Map<String, String> query,
			int responseStatus, int resultCode, int jobStatus){
		
		try{
			
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			WechatBaseUtil.clearCart(httpRequest,baseParam);
			
			// 修改人数和备注信息
			WechatBaseUtil.modifyPeople(httpRequest, baseParam, otherParameter);
			
			// 添加必选商品
			WechatBaseUtil.addRequiredItem(httpRequest, baseParam, otherParameter);
			
			// 获取虚拟购物车数据及用餐人数
			WechatBaseUtil.listCartData(httpRequest, baseParam);
			
			// 获取购物车推荐菜列表			
			WechatBaseUtil.getRecommendMenus(httpRequest, baseParam, otherParameter);
			
			List<String> path = new ArrayList<String>();
			path.add("menus/v1/get_menu_spec");
			path.add(entityIdForMenuSpec);
			path.add(menuId + "/");
			
			Response response = httpRequest.get(path, query); 
			Assert.assertEquals(response.getStatus(), responseStatus, "获取菜单详情只包括规格做法接口返回状态验证失败");
			
			if(responseStatus == 200){
				JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
				Assert.assertEquals(resp.get("code").getAsInt(), resultCode, "获取菜单详情只包括规格做法接口返回 code 验证失败");
				
			}
			
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			WechatBaseUtil.clearCart(httpRequest, baseParam);
			
			
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}	
	
	
	// 添加菜品到个人购物车
	@Test(description = "add dish to own cart", dataProvider = "addToCartsTest",
			dataProviderClass = OrderDishesForNoPrePaySeatTestData.class, groups = {"smoke", "all"})
	public void addToCartsTest(String description, Map<String, String> query, String httpBody,
			int responseStatus, int resultCode, int jobStatus){
		
		try{
			
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			WechatBaseUtil.clearCart(httpRequest, baseParam);
			
			
			// 修改人数和备注信息
			WechatBaseUtil.modifyPeople(httpRequest, baseParam, otherParameter);
			
			// 添加必选商品
			WechatBaseUtil.addRequiredItem(httpRequest, baseParam, otherParameter);
			
			// 获取虚拟购物车数据及用餐人数
			WechatBaseUtil.listCartData(httpRequest, baseParam);
			
			// 获取购物车推荐菜列表
			WechatBaseUtil.getRecommendMenus(httpRequest, baseParam, otherParameter);
			
			//获取菜单详情只包括规格做法
			WechatBaseUtil.getMenuSpec(httpRequest,baseParam, otherParameter);
			
			// 添加菜品到个人购物车
			Response response = httpRequest.post(PathForPost.getPathForModifyCart(), query, httpBody); 
			Assert.assertEquals(response.getStatus(), responseStatus, "添加菜品到个人购物车接口返回状态验证失败");
			
			if(responseStatus == 200){
				JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
				Assert.assertEquals(resp.get("code").getAsInt(), resultCode, "添加菜品到个人购物车接口返回 code 验证失败");
				
			}
			
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			WechatBaseUtil.clearCart(httpRequest, baseParam);
			
			
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}	
		

	
	
	
	
	
	
/////////////////////////////////   业务测试用例       //////////////////////////////////////////////////////////////////////


	@Test(description = "将普通菜或者套菜加入购物车", dataProvider = "addDishToCartTest",
			dataProviderClass = OrderDishesForNoPrePaySeatTestData.class, groups = {"module", "all"})
	public void addDishToCartTest(String description, List<CartIncludeSuitForm> cartSuitList, String cartSuitListJson){
		
		logger.info(description);
		String people = "6";
		
		try{
			
			//////////////////////// 前置操作, 包括数据准备      //////////////////////////////////////
			
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			WechatBaseUtil.clearCart(httpRequest, baseParam);
			
			
			
			/////////////////////////    业务操作         ////////////////////////////////////////
			// 修改人数和备注信息
			WechatBaseUtil.modifyPeople(httpRequest, baseParam, otherParameter);
			
			// 添加必选商品
			WechatBaseUtil.addRequiredItem(httpRequest, baseParam, otherParameter);			
			
			//将菜加入购物车
			for(CartIncludeSuitForm cartSuitForm : cartSuitList){
				WechatBaseUtil.addDishToCarts(httpRequest,baseParam, gson.toJson(cartSuitForm));
			}
		
			
			/////////////////////////    数据校验      ///////////////////////////////////////////
			boolean result1 = DataService.DataVerified(httpRequest, baseParam, people, cartSuitList);
			Assert.assertEquals(result1, true);
			
			
			////////////////////////   后置操作         ////////////////////////////////////////////
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			WechatBaseUtil.clearCart(httpRequest, baseParam);
			
		}catch(Exception e){
			
			logger.info(e.toString());
			
		}finally{
			
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			WechatBaseUtil.clearCart(httpRequest, baseParam);
		}
	
	}

	
	@Test(description = "用参人数为 6, 选择单个普通菜, 加入购物车, 清空购物车, 重新加菜", dataProvider = "addDishToCartTest2",
			dataProviderClass = OrderDishesForNoPrePaySeatTestData.class, groups = {"module", "all"})
	public void addDishToCartTest2(String description, CartIncludeSuitForm httpJsonForCartSuit, 
			CartIncludeSuitForm httpJsonForCartSuitAgain, String cartSuitJson, String cartSuitJsonAgain){
		
		logger.info(description);
		String people = "6";
		
		try{
			
			//////////////////////// 前置操作, 包括数据准备      //////////////////////////////////////
			
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			WechatBaseUtil.clearCart(httpRequest, baseParam);
			
			
			
			/////////////////////////    业务操作         ////////////////////////////////////////
			// 修改人数和备注信息
			WechatBaseUtil.modifyPeople(httpRequest, baseParam, otherParameter);
			
			// 添加必选商品
			WechatBaseUtil.addRequiredItem(httpRequest, baseParam, otherParameter);			
			
			//获取菜单详情只包括规格做法
			WechatBaseUtil.addDishToCarts(httpRequest,baseParam, gson.toJson(httpJsonForCartSuit));
		
			
			/////////////////////////    数据校验      ///////////////////////////////////////////
			
			List<CartIncludeSuitForm> cartSuitList1 = new ArrayList<CartIncludeSuitForm>();
			cartSuitList1.add(httpJsonForCartSuit);
			boolean result1 = DataService.DataVerified(httpRequest, baseParam, people, cartSuitList1);
			Assert.assertEquals(result1, true, "数据检验失败");
					
			
			////////////////////////   测试操作             ////////////////////////////////////////////
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			WechatBaseUtil.clearCart(httpRequest, baseParam);
			
			
			/////////////////////////    业务操作(重新加菜)   ////////////////////////////////////////
			// 修改人数和备注信息
			WechatBaseUtil.modifyPeople(httpRequest, baseParam, otherParameter);
			
			// 添加必选商品
			WechatBaseUtil.addRequiredItem(httpRequest, baseParam, otherParameter);			
			
			//获取菜单详情只包括规格做法
			WechatBaseUtil.addDishToCarts(httpRequest,baseParam, gson.toJson(httpJsonForCartSuitAgain));
			
			
			
			/////////////////////////    数据校验      ///////////////////////////////////////////
			
			List<CartIncludeSuitForm> cartSuitList2 = new ArrayList<CartIncludeSuitForm>();
			cartSuitList2.add(httpJsonForCartSuitAgain);
			boolean result3 = SeatNoPrePayUtil.DataVerified(httpRequest, baseParam, people, cartSuitList2);
			Assert.assertEquals(result3, true, "数据检验失败");
			
			
			////////////////////////   后置操作         ////////////////////////////////////////////
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			WechatBaseUtil.clearCart(httpRequest, baseParam);
			
			
		}catch(Exception e){
			logger.info(e.toString());
		}finally{
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			WechatBaseUtil.clearCart(httpRequest, baseParam);
		}
	
	}

	
	
	@Test(description = "用参人数为 6, 选择单个普通菜, 加入购物车, 将该份菜份数减为 0, 重新加菜", dataProvider = "addDishToCartTest3",
			dataProviderClass = OrderDishesForNoPrePaySeatTestData.class, groups = {"module", "all"})
	public void addDishToCartTest3(String description, CartIncludeSuitForm httpJsonForCartSuit, 
			CartIncludeSuitForm httpJsonForCartSuitOther, String cartSuitJson, String cartSuitJsonOther){
		
		logger.info(description);
		String people = "6";
		
		try{
			
			//////////////////////// 前置操作, 包括数据准备      //////////////////////////////////////
			
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			WechatBaseUtil.clearCart(httpRequest, baseParam);
			
			
			
			/////////////////////////    业务操作         ////////////////////////////////////////
			// 修改人数和备注信息
			WechatBaseUtil.modifyPeople(httpRequest, baseParam, otherParameter);
			
			// 添加必选商品
			WechatBaseUtil.addRequiredItem(httpRequest, baseParam, otherParameter);			
			
			//获取菜单详情只包括规格做法
			WechatBaseUtil.addDishToCarts(httpRequest,baseParam, gson.toJson(httpJsonForCartSuit));
		
			
			/////////////////////////    数据校验      ///////////////////////////////////////////
			
			List<CartIncludeSuitForm> cartSuitList1 = new ArrayList<CartIncludeSuitForm>();
			cartSuitList1.add(httpJsonForCartSuit);
			boolean result1 = DataService.DataVerified(httpRequest, baseParam, people, cartSuitList1);
			Assert.assertEquals(result1, true, "数据检验失败");
					
			
			////////////////////////   测试操作             ////////////////////////////////////////////
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			httpJsonForCartSuit.setNum((double)0);
			WechatBaseUtil.modifyDishNumber(httpRequest, baseParam, gson.toJson(httpJsonForCartSuit));
						
			
			
			/////////////////////////    数据校验      ///////////////////////////////////////////
			
			List<CartIncludeSuitForm> cartSuitList2 = new ArrayList<CartIncludeSuitForm>();
			cartSuitList2.add(httpJsonForCartSuit);
			boolean result2 = DataService.DataVerified(httpRequest, baseParam, people, cartSuitList2);
			Assert.assertEquals(result2, true, "数据检验失败");
						
						
			
			/////////////////////////    业务操作(重新加菜)   ////////////////////////////////////////
			// 修改人数和备注信息
			WechatBaseUtil.modifyPeople(httpRequest, baseParam, otherParameter);
			
			// 添加必选商品
			WechatBaseUtil.addRequiredItem(httpRequest, baseParam, otherParameter);			
			
			//获取菜单详情只包括规格做法
			WechatBaseUtil.addDishToCarts(httpRequest,baseParam, gson.toJson(httpJsonForCartSuitOther));
			
			
			
			/////////////////////////    数据校验      ///////////////////////////////////////////
			
			List<CartIncludeSuitForm> cartSuitList3 = new ArrayList<CartIncludeSuitForm>();
			cartSuitList3.add(httpJsonForCartSuitOther);
			boolean result3 = DataService.DataVerified(httpRequest, baseParam, people, cartSuitList3);
			Assert.assertEquals(result3, true, "数据检验失败");
			
			
			////////////////////////   后置操作         ////////////////////////////////////////////
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			WechatBaseUtil.clearCart(httpRequest, baseParam);
			
			
		}catch(Exception e){
			
			logger.info(e.toString());
			
		}finally{
			
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			WechatBaseUtil.clearCart(httpRequest, baseParam);
		}
	
	}

	
	@Test(description = "掌柜端打开添加必选菜开关, 添加普通菜到购物车", dataProvider = "addDishToCartTest4",
			dataProviderClass = OrderDishesForNoPrePaySeatTestData.class, groups = {"prepared", "allNo"})
	public void addDishToCartTest4(String description, List<CartIncludeSuitForm> cartSuitList, 
			List<CartIncludeSuitForm> cartSuitListWithRequiredDish, ForceConfig forceConfig, 
			String cartListJson, String cartList2Json, String forceConfigJson){
		
		logger.info(description);
		String people = "6";		
		
		try{
			
			//////////////////////// 前置操作, 包括数据准备      //////////////////////////////////////
			
			// 掌柜端设置添加必选菜			
			bossController.saveForceMenu(httpRequestForBossAPI, baseParam.getEntityId(), forceConfig);  // 设置可口可乐味必选菜
			Thread.sleep(30*1000);	//  等待该设置生效		
			
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			WechatBaseUtil.clearCart(httpRequest, baseParam);   // 该接口对必选菜无效
						
			
			/////////////////////////    业务操作         ////////////////////////////////////////
			// 修改人数和备注信息
			WechatBaseUtil.modifyPeople(httpRequest, baseParam, otherParameter);
			
			// 添加必选商品
			otherParameter.put("people", "");  // 避免干扰默认必选菜的行为
			WechatBaseUtil.addRequiredItem(httpRequest, baseParam, otherParameter);	
			otherParameter.put("people", people);  // 恢复原始数据
			
			//将菜加入购物车
			for(CartIncludeSuitForm cartSuitForm : cartSuitList){
				WechatBaseUtil.addDishToCarts(httpRequest,baseParam, gson.toJson(cartSuitForm));
			}
		
			
			/////////////////////////    数据校验      ///////////////////////////////////////////
			boolean result1 = DataService.DataVerified(httpRequest, baseParam, people, cartSuitListWithRequiredDish);
			Assert.assertEquals(result1, true, "数据检验失败");
			
			
			////////////////////////   后置操作         ////////////////////////////////////////////
			
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			WechatBaseUtil.clearCart(httpRequest, baseParam);
						
			
		}catch(Exception e){
			
			logger.info(e.toString());
			
		}finally{
			
			// 掌柜端移除必选菜
			bossController.removeForceMenu(httpRequestForBossAPI, baseParam.getEntityId(), CommonConstants.cokeMenuId);
			try{
				
				Thread.sleep(30*1000); // 等待掌柜端设置生效
				
			}catch(Exception e){
				logger.error(e.toString());
			}
			
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			WechatBaseUtil.clearCart(httpRequest, baseParam);
		}
	
	}
	
	
	@Test(description = "用参人数为 6, 点菜未达起点份数；点菜超过累加份数", dataProvider = "illegalTest",
			dataProviderClass = OrderDishesForNoPrePaySeatTestData.class, groups = {"illegal", "all"})
	public void illegalTest(String description, CartIncludeSuitForm httpJsonForCartSuit){
		
		logger.info(description);
		String people = "6";
		
		try{
			
			//////////////////////// 前置操作, 包括数据准备      //////////////////////////////////////
			
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			WechatBaseUtil.clearCart(httpRequest, baseParam);
			
			
			
			/////////////////////////    业务操作         ////////////////////////////////////////
			// 修改人数和备注信息
			WechatBaseUtil.modifyPeople(httpRequest, baseParam, otherParameter);
			
			// 添加必选商品
			WechatBaseUtil.addRequiredItem(httpRequest, baseParam, otherParameter);			
			
			//获取菜单详情只包括规格做法
			WechatBaseUtil.addDishToCarts(httpRequest,baseParam, gson.toJson(httpJsonForCartSuit));
		
			
			/////////////////////////    数据校验      ///////////////////////////////////////////
			// 获取虚拟购物车数据及用餐人数
			Response response = WechatBaseUtil.listCartData(httpRequest, baseParam);
			JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(resp.get("code").getAsInt(), 1);
			
			// 构造验证参数
			Map<String, CartIncludeSuitForm> menuMap = new HashMap<String, CartIncludeSuitForm>();
			menuMap.put(httpJsonForCartSuit.getMenuId(), httpJsonForCartSuit);
			Map<String, Map<String, CartIncludeSuitForm>> cartSuitMap = new HashMap<String, Map<String,CartIncludeSuitForm>>();
			cartSuitMap.put(CommonConstants.UID, menuMap);
			
			// 参数验证
			boolean result = SeatNoPrePayUtil.MenusValidate(response, people, cartSuitMap);
			Assert.assertEquals(result, true, "数据校验失败");
			
			////////////////////////   后置操作         ////////////////////////////////////////////
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			WechatBaseUtil.clearCart(httpRequest, baseParam);
			
		}catch(Exception e){
			
			logger.info(e.toString());
			
		}finally{
			
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			WechatBaseUtil.clearCart(httpRequest, baseParam);
						
		}
	
		
	}
	
	
	
	@Test(description = "多人将普通菜或者套菜加入购物车", dataProvider = "addDishToCartWithMultiCustomerTest",
			dataProviderClass = OrderDishesForNoPrePaySeatTestData.class, groups = {"module", "all"})
	public void addDishToCartWithMultiCustomerTest(String description, List<CartIncludeSuitForm> cartSuitListOne, 
			List<CartIncludeSuitForm> cartSuitListTwo, List<CartIncludeSuitForm> cartSuitListTotal){
		
		logger.info(description);
		String people = "6";
		
		try{
			
			//////////////////////// 前置操作, 包括数据准备      //////////////////////////////////////
			
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			WechatBaseUtil.clearCart(httpRequest, baseParam);
			
			Response response = WechatBaseUtil.listCartData(httpRequest, baseParam);
			JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(resp.get("data").getAsJsonObject().get("userCarts").getAsJsonArray().size(), 0, "桌码  " + seatCode + " 中存在冗余菜品数据");
			Assert.assertEquals(resp.get("data").getAsJsonObject().get("kindUserCarts").getAsJsonArray().size(), 0, "桌码  " + seatCode + " 中存在冗余菜品数据");
			
			
			/////////////////////////    业务操作         ////////////////////////////////////////
			// 修改人数和备注信息
			WechatBaseUtil.modifyPeople(httpRequest, baseParam, otherParameter);
			
			// 添加必选商品
			WechatBaseUtil.addRequiredItem(httpRequest, baseParam, otherParameter);			
			
			//客户 One 将菜加入购物车
			for(CartIncludeSuitForm cartSuitForm : cartSuitListOne){
				WechatBaseUtil.addDishToCarts(httpRequest,baseParam, gson.toJson(cartSuitForm));
			}
		
			//客户 Two 将菜加入购物车
			for(CartIncludeSuitForm cartSuitForm : cartSuitListTwo){
				WechatBaseUtil.addDishToCarts(httpRequest,baseParamOther, gson.toJson(cartSuitForm));
			}
			
			/////////////////////////    数据校验      ///////////////////////////////////////////
			boolean result1 = DataService.DataVerified(httpRequest, baseParam, people, cartSuitListTotal);
			Assert.assertEquals(result1, true);
			
			
			////////////////////////   后置操作         ////////////////////////////////////////////
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			WechatBaseUtil.clearCart(httpRequest, baseParam);
			
		}catch(Exception e){
			logger.info(e.toString());
		}finally{
			
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			WechatBaseUtil.clearCart(httpRequest, baseParam);
		}
	
	}
	
}
