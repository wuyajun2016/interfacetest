package com.dfire.testcase.function.integration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.uncommons.reportng.HTMLReporter;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.dfire.testcase.function.bean.BaseParamBean;
import com.dfire.testcase.function.bean.CardPayBean;
import com.dfire.testcase.function.order.OrderForNoPrePaySeatTestData;
import com.dfire.testcase.function.service.DataService;
import com.dfire.testcase.function.service.cash.CashService;
import com.dfire.testcase.function.thread.ServiceBillThread;
import com.dfire.testcase.function.util.api.SeatNoPrePayAPI;
import com.dfire.testcase.function.util.api.SeatPrePayAPI;
import com.dfire.testcase.function.util.base.SeatNoPrePayUtil;
import com.dfire.testcase.function.util.base.WechatBaseUtil;
import com.dfire.wechat.util.CartIncludeSuitForm;
import com.dfire.wechat.util.CommonConstants;
import com.dfire.wechat.util.HttpRequestEx;
import com.dfire.wechat.util.PropertiesUtil;
import com.dfire.wechat.util.Response;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


/**
 * 扫桌码非预付款流程
 * @author pidan
 *
 */
@Test
@Listeners(HTMLReporter.class)
public class SeatCodeNoPrePayTest{
	
	private static final Logger logger = Logger.getLogger(SeatCodeNoPrePayTest.class);
	PropertiesUtil propertyUtil = PropertiesUtil.getPropertiesUtil();
	
	public String host = propertyUtil.getValue("DEFAULT_HOST");
	public String cashHost = propertyUtil.getValue("CASH_HOST");
	public String cashHeartHost = propertyUtil.getValue("CASH_HEART_HOST");
	
	String uid = propertyUtil.getValue("uid");
	String appSecret = propertyUtil.getValue("appSecret");
	
	private HttpRequestEx httpRequest;	
	private HttpRequestEx cashRequest;	
	private HttpRequestEx cashHeartRequest;	
	
	BaseParamBean baseParamBean = new BaseParamBean();
	CashService cashService = new CashService();

	Map<String, String> cashMap = new HashMap<String, String>();
	private List<String> menuIdList = new ArrayList<String>();
	
	private Gson gson = new Gson();
	private boolean https = CommonConstants.HTTPS;
	private Map<String, String> otherParameter = new HashMap<String, String>();
	
	private String repeat = "false";	
	private String recommend = "false";
	private String menuId = CommonConstants.menuId;
	
	private String people = "6";
	private String memo = "";
	private String oldPeople = "";
	
	private String page = "";
	private String pageSize = "";
	private String isPreCart = "false";
	
	@BeforeClass(alwaysRun = true)
	public void setup() throws Exception{
		
		logger.info("SeatCodeNoPrePayTest!");
		
		host = propertyUtil.getValue("DEFAULT_HOST");
		httpRequest = new HttpRequestEx(host, https);
		cashRequest = new HttpRequestEx(cashHost, https);
		cashHeartRequest = new HttpRequestEx(cashHeartHost, false);
		
		String entityId = propertyUtil.getValue("entityId");
		String seatCode = propertyUtil.getValue("seatCode");
		
		seatCode = "110";
		uid = propertyUtil.getValue("uid");
		
		String orderId = "";
		String unionId = propertyUtil.getValue("unionid");
		String xtoken = WechatBaseUtil.getToken(httpRequest, entityId, unionId);//获取token
		
		baseParamBean.setEntityId(entityId);
		baseParamBean.setOrderId(orderId);
		baseParamBean.setSeatCode(seatCode);
		baseParamBean.setXtoken(xtoken);
		
		otherParameter.put("repeat", repeat);
		otherParameter.put("recommend", recommend);		
		otherParameter.put("menuId", menuId);
		
		otherParameter.put("peopleCount", people);
		otherParameter.put("memo", memo);		
		otherParameter.put("oldPeople", oldPeople);
		
		otherParameter.put("page", page);
		otherParameter.put("pageSize", pageSize);
		otherParameter.put("isPreCart", isPreCart);
		
		otherParameter.put("people", people);
		otherParameter.put("menuIdList", gson.toJson(menuIdList)); // 该参数为空
		otherParameter.put("memo_labels", "");
		
		// 用于提交订单
		otherParameter.put("isPrePay", "false");
		otherParameter.put("cartTime", "");
		
		//收银机登录
		cashService.setCashRequest(cashRequest);
		cashMap.put("entity_id", baseParamBean.getEntityId());
		cashService.setCashInitBean();
		cashService.setCashExtBean();
		String sessionID = cashService.loginwithpassword(cashRequest,baseParamBean, cashMap, appSecret);
		cashService.cashextBean.setSessionId(sessionID);
	}
	
	
	@Test(invocationCount=1,threadPoolSize=1,groups={"flowNo"})
	public void apiTest() throws Exception{
		String heartAppSecret = propertyUtil.getValue("heart.appsecret");
		//收银心跳
		cashService.heart(cashHeartRequest,baseParamBean, cashMap, heartAppSecret );
		
		//购物车
		List<CartIncludeSuitForm> cartList= new ArrayList<CartIncludeSuitForm>();
		Map<String, String> parMap = new HashMap<String, String>();
		
		String repeat = "false";
		String recommend = "false";
		
		//扫码
		Boolean scancodeflag = SeatNoPrePayAPI.scanCode(httpRequest,baseParamBean);
		Assert.assertTrue(scancodeflag,"扫码失败");
		
		//点击进入
		parMap.put("repeat", repeat);
		parMap.put("recommend", recommend);
		Boolean clEnFlag = SeatNoPrePayAPI.clickToEnter(httpRequest,baseParamBean, parMap);
		Assert.assertTrue(clEnFlag,"点击进入流程失败");
		
		String people_count="2";
		String memo="";
		String isPreCart = "false";
		String page= "1";
		String pageSize = "12";
		String oldPeople = "";
		parMap.put("people", people_count);
		parMap.put("memo", memo);
		parMap.put("oldPeople", oldPeople);
		parMap.put("page", page);
		parMap.put("pageSize", pageSize);
		parMap.put("isPreCart", isPreCart);
		
		// 用于提交订单
		parMap.put("isPrePay", "false");
		parMap.put("cartTime", "");
		
		//开始点菜
		Boolean intoMenusFlag = SeatNoPrePayAPI.startChooseDish(httpRequest, baseParamBean,parMap);
		Assert.assertTrue(intoMenusFlag,"进入点菜流程失败");
		
		//点菜
		//获取menuId
		String menusJson = SeatNoPrePayAPI.commonMap.get("listMenu_data").toString();
		String menuId = (String) JSONPath.read(menusJson, "$.kindMenusVos[1].menus[0].id");
		Assert.assertNotNull(menuId,"菜单ID为空！");
		Double num = 1d;//菜的数量
		System.out.println("menuId:"+menuId);
		parMap.put("menuId", menuId);
		Boolean chooseDishFlag = SeatNoPrePayAPI.chooseDish(httpRequest, baseParamBean, parMap);
		Assert.assertTrue(chooseDishFlag,"获取菜规格和做法失败");
		
		//将菜加入购物车
		JSONObject menusObject  = (JSONObject) SeatNoPrePayAPI.commonMap.get("menuspec_data");
		String makeId = (String) JSONPath.read(menusObject.toString(), "$.makeDataList[0].makeId");
		String specId = (String) JSONPath.read(menusObject.toString(), "$.specDataList[0].specItemId");
		CartIncludeSuitForm cartIncludeSuitForm = new CartIncludeSuitForm();
		cartIncludeSuitForm.setMakeId(makeId);
		cartIncludeSuitForm.setSpecDetailId(specId);
		cartIncludeSuitForm.setMenuId(menuId);
		cartIncludeSuitForm.setNum(num);
		cartIncludeSuitForm.setUid(uid);
		cartList.add(cartIncludeSuitForm);
		Boolean addToCartFlag = SeatNoPrePayAPI.addDishToCarts(httpRequest, baseParamBean,cartIncludeSuitForm);
		Assert.assertTrue(addToCartFlag,"加入购物车失败！");
		
		//点菜 点一个套餐
		//获取menuId
//		String menuId2 = (String) JSONPath.read(menusJson, "$.kindMenusVos[name='套餐1'][0].menus[0].id");//选择一个套餐
//		Assert.assertNotNull(menuId2,"套餐ID为空！");
//		num = 1d;//菜的数量
//		parMap.put("menuId2", menuId2);
//		chooseDishFlag = SeatNoPrePayAPI.chooseDish(httpRequest,baseParamBean,parMap);
//		Assert.assertTrue(chooseDishFlag,"获取菜规格和做法失败");
//	
//		//将菜加入购物车
//		menusObject  = (JSONObject) SeatNoPrePayAPI.commonMap.get("menuspec_data");
//		makeId = (String) JSONPath.read(menusObject.toString(), "$.makeDataList[0].makeId");
//		specId = (String) JSONPath.read(menusObject.toString(), "$.specDataList[0].specItemId");
//		CartIncludeSuitForm cartIncludeSuitForm2 = new CartIncludeSuitForm();
//		cartIncludeSuitForm2.setMakeId(makeId);
//		cartIncludeSuitForm2.setSpecDetailId(specId);
//		cartIncludeSuitForm2.setMenuId(menuId2);
//		cartIncludeSuitForm2.setNum(num);
//		cartIncludeSuitForm2.setUid(uid);
//		cartList.add(cartIncludeSuitForm2);
//		addToCartFlag = SeatNoPrePayAPI.addDishToCarts(httpRequest, baseParamBean,cartIncludeSuitForm2);
//		Assert.assertTrue(addToCartFlag,"加入购物车失败！");
		
		//进入购物车
		Map<String, String> intoCartMap = new HashMap<String, String>();
		intoCartMap.put("page", page);
		intoCartMap.put("isPreCart", isPreCart);
		intoCartMap.put("pageSize", pageSize);
		Boolean intoCartFlag = SeatNoPrePayAPI.intoCart(httpRequest,baseParamBean,intoCartMap,cartList);
		Assert.assertTrue(intoCartFlag,"进入购物车失败");
		
		//下单
		// 获取特定用户的购物车数据
		Response cartData = WechatBaseUtil.listCartData(httpRequest, baseParamBean);
		JsonObject checkJsonObject = new JsonParser().parse(cartData.getResponseStr()).getAsJsonObject();			
		Assert.assertEquals(checkJsonObject.get("code").getAsInt(), 1, "获取购物车数据失败");
		String cartTime = checkJsonObject.get("data").getAsJsonObject().get("cartTime").getAsString();
		parMap.put("cartTime", cartTime);
		
		String peopleCount = "2";
		parMap.put("peopleCount", peopleCount);
		Boolean sumbmitOrderFlag = SeatNoPrePayAPI.submitOrder(httpRequest, baseParamBean, parMap,cartList);
		Assert.assertTrue(sumbmitOrderFlag,"提交订单失败");
		
		//收银审核
		Map<String, Object> cashreturnMap = cashService.approveOrder(cashRequest, cashMap,baseParamBean.getEntityId(), appSecret);
		
		//刷新已下单的页面 (收银已经审核)
		Boolean refreshOrderFlag = SeatNoPrePayAPI.refreshOrder(httpRequest, baseParamBean);
		Assert.assertTrue(refreshOrderFlag,"刷新已下单的页面");
		
		//二期再做
		//查询付款详情
		Boolean getPayInfoFlag = SeatNoPrePayAPI.getPayDetail(httpRequest,baseParamBean);
		Assert.assertTrue(getPayInfoFlag,"查询付款详情失败");
		
		//启动一个线程计算账单
		ServiceBillThread serviceBillThread = new ServiceBillThread(cashService);
		Thread t = new Thread(serviceBillThread);
	    t.start(); 
		//去支付
		baseParamBean.setCardId("");
		Boolean toBillPageFlag = SeatNoPrePayAPI.intoBillPage(httpRequest, baseParamBean,"true", null);
		Assert.assertTrue(toBillPageFlag,"进入账单页面失败");
		
		//等待支付 获取支付类型
		Boolean getPayTypeFlag = SeatNoPrePayAPI.getPayType(httpRequest,baseParamBean, parMap, cartList);
		Assert.assertTrue(getPayTypeFlag,"获取支付方式失败");
				
		//会员卡支付
		CardPayBean cardPayBean = new CardPayBean();
		Boolean cardPayFlag = SeatNoPrePayAPI.cardPay(httpRequest,baseParamBean, cardPayBean);
		Assert.assertTrue(cardPayFlag,"会员卡支付失败");
				
		Thread.sleep(2000);
		cashService.settleAccounts(cashRequest, baseParamBean, intoCartMap, appSecret);
		
	}

	
	@Test(invocationCount=1,threadPoolSize=1,groups={"flowNo"})
	public void apiTestOther() throws Exception{
		String heartAppSecret = propertyUtil.getValue("heart.appsecret");
		//收银心跳
		cashService.heart(cashHeartRequest,baseParamBean, cashMap, heartAppSecret );
		
		//购物车
		List<CartIncludeSuitForm> cartList= new ArrayList<CartIncludeSuitForm>();
		Map<String, String> parMap = new HashMap<String, String>();
		
		String repeat = "false";
		String recommend = "false";
		
		//扫码
		Boolean scancodeflag = SeatNoPrePayAPI.scanCode(httpRequest,baseParamBean);
		Assert.assertTrue(scancodeflag,"扫码失败");
		
		//点击进入
		parMap.put("repeat", repeat);
		parMap.put("recommend", recommend);
		Boolean clEnFlag = SeatNoPrePayAPI.clickToEnter(httpRequest,baseParamBean, parMap);
		Assert.assertTrue(clEnFlag,"点击进入流程失败");
		
		String people_count="2";
		String memo="";
		String isPreCart = "false";
		String page= "1";
		String pageSize = "12";
		String oldPeople = "";
		parMap.put("people", people_count);
		parMap.put("memo", memo);
		parMap.put("oldPeople", oldPeople);
		parMap.put("page", page);
		parMap.put("pageSize", pageSize);
		parMap.put("isPreCart", isPreCart);
		
		// 用于提交订单
		parMap.put("isPrePay", "false");
		parMap.put("cartTime", "");
		
		//开始点菜
		Boolean intoMenusFlag = SeatNoPrePayAPI.startChooseDish(httpRequest, baseParamBean,parMap);
		Assert.assertTrue(intoMenusFlag,"进入点菜流程失败");
		
		//点菜
		//获取menuId		
		String menuId = CommonConstants.juice;
		Assert.assertNotNull(menuId,"菜单ID为空！");
		Double num = 1d;//菜的数量
		System.out.println("menuId:"+menuId);
		parMap.put("menuId", menuId);
		Boolean chooseDishFlag = SeatNoPrePayAPI.chooseDish(httpRequest, baseParamBean, parMap);
		Assert.assertTrue(chooseDishFlag,"获取菜规格和做法失败");
		
		//将菜加入购物车
		String makeId = "";
		String specId = "";
		CartIncludeSuitForm cartIncludeSuitForm = new CartIncludeSuitForm();
		cartIncludeSuitForm.setMakeId(makeId);
		cartIncludeSuitForm.setSpecDetailId(specId);
		cartIncludeSuitForm.setMenuId(menuId);
		cartIncludeSuitForm.setNum(num);
		cartIncludeSuitForm.setUid(uid);
		cartList.add(cartIncludeSuitForm);
		Boolean addToCartFlag = SeatNoPrePayAPI.addDishToCarts(httpRequest, baseParamBean,cartIncludeSuitForm);
		Assert.assertTrue(addToCartFlag,"加入购物车失败！");
		

		
		//进入购物车
		Map<String, String> intoCartMap = new HashMap<String, String>();
		intoCartMap.put("page", page);
		intoCartMap.put("isPreCart", isPreCart);
		intoCartMap.put("pageSize", pageSize);
		Boolean intoCartFlag = SeatNoPrePayAPI.intoCart(httpRequest,baseParamBean,intoCartMap,cartList);
		Assert.assertTrue(intoCartFlag,"进入购物车失败");
		
		//下单
		// 获取特定用户的购物车数据
		Response cartData = WechatBaseUtil.listCartData(httpRequest, baseParamBean);
		JsonObject checkJsonObject = new JsonParser().parse(cartData.getResponseStr()).getAsJsonObject();			
		Assert.assertEquals(checkJsonObject.get("code").getAsInt(), 1, "获取购物车数据失败");
		String cartTime = checkJsonObject.get("data").getAsJsonObject().get("cartTime").getAsString();
		parMap.put("cartTime", cartTime);
		
		String peopleCount = "2";
		parMap.put("peopleCount", peopleCount);
		Boolean sumbmitOrderFlag = SeatNoPrePayAPI.submitOrder(httpRequest, baseParamBean, parMap,cartList);
		Assert.assertTrue(sumbmitOrderFlag,"提交订单失败");
		
		//收银审核
		Map<String, Object> cashreturnMap = cashService.approveOrder(cashRequest, cashMap,baseParamBean.getEntityId(), appSecret);
		
		//刷新已下单的页面 (收银已经审核)
		Boolean refreshOrderFlag = SeatNoPrePayAPI.refreshOrder(httpRequest, baseParamBean);
		Assert.assertTrue(refreshOrderFlag,"刷新已下单的页面");
		
		//二期再做
		//查询付款详情
		Boolean getPayInfoFlag = SeatNoPrePayAPI.getPayDetail(httpRequest,baseParamBean);
		Assert.assertTrue(getPayInfoFlag,"查询付款详情失败");
		
		//启动一个线程计算账单
		ServiceBillThread serviceBillThread = new ServiceBillThread(cashService);
		Thread t = new Thread(serviceBillThread);
	    t.start(); 
		//去支付
		baseParamBean.setCardId("");
		Boolean toBillPageFlag = SeatNoPrePayAPI.intoBillPage(httpRequest, baseParamBean,"true", null);
		Assert.assertTrue(toBillPageFlag,"进入账单页面失败");
		
		//等待支付 获取支付类型
		Boolean getPayTypeFlag = SeatNoPrePayAPI.getPayType(httpRequest,baseParamBean, parMap, cartList);
		Assert.assertTrue(getPayTypeFlag,"获取支付方式失败");
				
		//会员卡支付
		CardPayBean cardPayBean = new CardPayBean();
		Boolean cardPayFlag = SeatNoPrePayAPI.cardPay(httpRequest,baseParamBean, cardPayBean);
		Assert.assertTrue(cardPayFlag,"会员卡支付失败");
				
		Thread.sleep(2000);
		cashService.settleAccounts(cashRequest, baseParamBean, intoCartMap, appSecret);
		
	}

	
	/**
	 * 非预付款扫桌码下单，收银审核同意并进行结账<br/>
	 * @throws Exception
	 */
	@Test(description = "单个用户点菜下单", dataProvider = "placeOrderWithCashTest",
		dataProviderClass = SeatCodeNoPrePayTestData.class, invocationCount=1, threadPoolSize=1, groups={"flow"})
	public void placeOrderWithCashTest(String description, List<CartIncludeSuitForm> cartSuitList) throws Exception{
		
		String heartAppSecret = propertyUtil.getValue("heart.appsecret");
		
		//收银心跳
		cashService.heart(cashHeartRequest,baseParamBean, cashMap, heartAppSecret );

		
		/////////////////////////////////////   业务代码       /////////////////////////////////////////
		
		// scan code
		SeatNoPrePayUtil.scanCode(httpRequest, baseParamBean, otherParameter);
								
		// click to enter  (pre conditions)
		SeatPrePayAPI.clickToEnter(httpRequest, baseParamBean, otherParameter);
		
		// add menu
		SeatNoPrePayUtil.addMenuToCart(httpRequest, baseParamBean, otherParameter, cartSuitList);
		
		// place order
		SeatNoPrePayUtil.placeOrder(httpRequest, baseParamBean, otherParameter);
				
		//收银审核
		Map<String, Object> cashreturnMap = cashService.approveOrder(cashRequest, cashMap, baseParamBean.getEntityId(), appSecret);
		
		
		//刷新已下单的页面 (收银已经审核)
		Map<String, Response> refreshOrder = SeatNoPrePayUtil.refreshToGetPayDetail(httpRequest, baseParamBean, otherParameter);
		
		
		//启动一个线程计算账单
//		ServiceBillThread serviceBillThread = new ServiceBillThread(cashService);
//		Thread t = new Thread(serviceBillThread);
//	    t.start(); 
	    
	    
		//会员卡支付
		JsonObject orderVos = new JsonParser().parse(refreshOrder.get("getOrder").getResponseStr()).
				getAsJsonObject().get("data").getAsJsonObject().get("orderVos").getAsJsonArray().get(0).getAsJsonObject();
	    baseParamBean.setOrderId(orderVos.get("orderId").getAsString());
		SeatNoPrePayUtil.payForOrder(httpRequest, baseParamBean, cartSuitList);
	    	    
		
				
		Thread.sleep(2000);
		cashService.settleAccounts(cashRequest, baseParamBean, null, appSecret);
		
		
	}
	
	
	@AfterClass
	public void after(){
		httpRequest.ShutDown();
	}
	
	
	

}
