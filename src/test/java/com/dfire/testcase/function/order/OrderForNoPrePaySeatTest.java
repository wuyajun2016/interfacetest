package com.dfire.testcase.function.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.dfire.testcase.function.bean.BaseParamBean;
import com.dfire.testcase.function.bean.CardPayBean;
import com.dfire.testcase.function.service.CashService;
import com.dfire.testcase.function.service.DataService;
import com.dfire.testcase.function.thread.ServiceBillThreadV2;
import com.dfire.testcase.function.util.api.SeatNoPrePayAPI;
import com.dfire.testcase.function.util.api.SeatPrePayAPI;
import com.dfire.testcase.function.util.base.SeatNoPrePayUtil;
import com.dfire.testcase.function.util.base.WechatBaseUtil;
import com.dfire.wechat.util.BeanProvider;
import com.dfire.wechat.util.CartIncludeSuitForm;
import com.dfire.wechat.util.CommonConstants;
import com.dfire.wechat.util.HttpRequestEx;
import com.dfire.wechat.util.Response;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class OrderForNoPrePaySeatTest {
	
	private static final Logger logger = Logger.getLogger(OrderForNoPrePaySeatTest.class);
	private HttpRequestEx httpRequest;	
	private String host = CommonConstants.DEFAULT_HOST;	
	
	private String entityId = CommonConstants.entityId;
	private String seatCode = CommonConstants.seatCode;
	private String orderId = "";
	
	private String repeat = "false";	
	private String recommend = "false";
	private String menuId = CommonConstants.menuId;
	
	private String people = "6";
	private String memo = "";
	private String oldPeople = "";
	
	private String page = "";
	private String pageSize = "";
	private String isPreCart = "false";
	
	private String xtoken = "";
	
	private BaseParamBean baseParam = new BaseParamBean();
	private Map<String, String> otherParameter = new HashMap<String, String>();
	
	private String xtokenOther = "";
	private String unionIdOther = CommonConstants.unionIDOther;
	private BaseParamBean baseParamOther = new BaseParamBean();	
	
	private Gson gson = new Gson();
	private boolean https = CommonConstants.HTTPS;
	private List<String> menuIdList = new ArrayList<String>();
	
	// 用于收银动作变量
	private String cashHost = CommonConstants.cashHost;
	private String cashHeartHost = CommonConstants.cashHeartHost;
		
	private HttpRequestEx cashRequest;	
	private HttpRequestEx cashHeartRequest;	
	
	private CashService cashService = new CashService();
	
	@BeforeClass(alwaysRun = true)
	public void setup() throws Exception{
		
		logger.info("setup in OrderForNoPrePaySeatTest");
		
		httpRequest = new HttpRequestEx(host, https);
		cashRequest = new HttpRequestEx(cashHost, https);
		cashHeartRequest = new HttpRequestEx(cashHeartHost, https);
		
		// 获取 token
		xtoken = WechatBaseUtil.getToken(httpRequest, entityId, CommonConstants.unionID);
		xtokenOther = WechatBaseUtil.getToken(httpRequest, entityId, unionIdOther);
		
		baseParam.setEntityId(entityId);
		baseParam.setOrderId(orderId);
		baseParam.setSeatCode(seatCode);
		baseParam.setXtoken(xtoken);
		
		baseParamOther.setXtoken(xtokenOther);
		baseParamOther.setEntityId(entityId);
		baseParamOther.setSeatCode(seatCode);
		baseParamOther.setOrderId(orderId);
				
		
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
		
		
		// 用于收银动作初始化----收银登陆
		cashService.setCashRequest(cashRequest);
		cashService.setCashInitBean(entityId);
		cashService.setCashExtBean();
		String sessionID = cashService.loginwithpassword(cashRequest, baseParam, CommonConstants.appSecret);
		cashService.cashextBean.setSessionId(sessionID);
		
		// scan code
		SeatNoPrePayUtil.scanCode(httpRequest, baseParam, otherParameter);
		SeatNoPrePayUtil.scanCode(httpRequest, baseParamOther, otherParameter);
		
		// click to enter  (pre conditions)
		SeatPrePayAPI.clickToEnter(httpRequest, baseParam, otherParameter);
		SeatPrePayAPI.clickToEnter(httpRequest, baseParamOther, otherParameter);
				
		
	}
	
	
	@AfterClass(alwaysRun = true)
	public void tearDown(){
		
		logger.info("tearDown in OrderForNoPrePaySeatTest");
		
		// 清除购物车中的所有菜 (测试需要, 非业务流程)
		WechatBaseUtil.clearCart(httpRequest, baseParam);
		
		httpRequest.ShutDown();
	}
	
	
	
	///////////////////////////////// 用户下单但未进行支付       ////////////////////////////////////////////////////////////
	
	@Test(description = "单个用户点菜下单", dataProvider = "orderForOneCustomer",
			dataProviderClass = OrderForNoPrePaySeatTestData.class, groups = {"module", "all"})
	public void orderForOneCustomer(String description, List<CartIncludeSuitForm> cartSuitList){
		
		
		logger.info(description);
		
		try{
			
			////////////////////////前置操作, 包括数据准备      //////////////////////////////////////
				
			// 收银非预付款下单需要 5min 超时失效, 该操作为了使得后续操作不受前次操作下单影响			
			logger.info("等待之前的订单失效");
			Thread.sleep(5*60*1000);
			logger.info("等待动作已完成");
			
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
			
			
			////////////////////////   下单流程      /////////////////////////////////////////////
			// 获取购物车数据
			Response cartData = WechatBaseUtil.listCartData(httpRequest, baseParam);
			JsonObject checkJsonObject = new JsonParser().parse(cartData.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(checkJsonObject.get("code").getAsInt(), 1, "获取购物车数据失败");
			String cartTime = checkJsonObject.get("data").getAsJsonObject().get("cartTime").getAsString();
			otherParameter.put("cartTime", cartTime);
			
			//提交订单
			Response orderResult = WechatBaseUtil.submitOrder(httpRequest, baseParam, otherParameter);
			JsonObject orderObject = new JsonParser().parse(orderResult.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(orderObject.get("code").getAsInt(), 1, "提交订单失败");
			
			// 获取订单列表
			Response responseFromGetOrder = WechatBaseUtil.getOrder(httpRequest, baseParam);
			String orderIdsFormJson = BeanProvider.getOrderIdsFormListJson(responseFromGetOrder);
			
			// 检查订单变化  (可能多人提交订单)
			Response orderChange = WechatBaseUtil.checkOrderChange(httpRequest, baseParam, orderIdsFormJson);
			JsonObject orderChangeObject = new JsonParser().parse(orderChange.getResponseStr()).getAsJsonObject();			
			
			if(1 == orderChangeObject.get("code").getAsInt())
				logger.info("订单未发生变化");
			else
				logger.info("订单发生了变化");
			
		}catch(Exception e){
			
			logger.error(e.toString());
			
		}finally{
			
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			WechatBaseUtil.clearCart(httpRequest, baseParam);
		}
		
		
	}
	
	
	
	
	/**
	 * 参数 multi 是布尔值，为 true 表明是多人同时下单, 为 false 表明单人下单
	 * @param description
	 * @param cartSuitListOne
	 * @param cartSuitListTwo
	 * @param cartSuitListTotal
	 * @param multi
	 */
	@Test(description = "多个用户点菜下单", dataProvider = "orderForMultiCustomer",
			dataProviderClass = OrderForNoPrePaySeatTestData.class, groups = {"module", "all"})
	public void orderForMultiCustomer(String description, List<CartIncludeSuitForm> cartSuitListOne, 
			List<CartIncludeSuitForm> cartSuitListTwo, List<CartIncludeSuitForm> cartSuitListTotal, boolean multi){
		
		
		logger.info(description);
		
		try{
			
			////////////////////////前置操作, 包括数据准备      //////////////////////////////////////
				
			// 收银非预付款下单需要 5min 超时失效, 该操作为了使得后续操作不受前次操作下单影响
			logger.info("等待之前的订单失效");
			Thread.sleep(5*60*1000);
			logger.info("等待动作已完成");
			
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
			
			//客户  A 将菜加入购物车
			for(CartIncludeSuitForm cartSuitForm : cartSuitListOne){
				WechatBaseUtil.addDishToCarts(httpRequest,baseParam, gson.toJson(cartSuitForm));
			}
			
			//客户  B 将菜加入购物车
			for(CartIncludeSuitForm cartSuitForm : cartSuitListTwo){
				WechatBaseUtil.addDishToCarts(httpRequest,baseParamOther, gson.toJson(cartSuitForm));
			}
			
			/////////////////////////    数据校验      ///////////////////////////////////////////
			boolean result1 = DataService.DataVerified(httpRequest, baseParam, people, cartSuitListTotal);
			Assert.assertEquals(result1, true);
			
			
			///////////////////////  下单流程    ///////////////////////////////////////////////////
			// 获取购物车数据
			Response cartData = WechatBaseUtil.listCartData(httpRequest, baseParam);
			JsonObject checkJsonObject = new JsonParser().parse(cartData.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(checkJsonObject.get("code").getAsInt(), 1, "获取购物车数据失败");
			String cartTime = checkJsonObject.get("data").getAsJsonObject().get("cartTime").getAsString();
			otherParameter.put("cartTime", cartTime);
			
			//提交订单
			Response orderResult = WechatBaseUtil.submitOrder(httpRequest, baseParam, otherParameter);
			JsonObject orderObject = new JsonParser().parse(orderResult.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(orderObject.get("code").getAsInt(), 1, "提交订单失败");
			
			if(multi){
				// 第二个顾客提交订单
				Response orderResult2 = WechatBaseUtil.submitOrder(httpRequest, baseParamOther, otherParameter);
				JsonObject orderObject2 = new JsonParser().parse(orderResult2.getResponseStr()).getAsJsonObject();			
				Assert.assertEquals(orderObject2.get("code").getAsInt(), 0, "再次提交订单验证异常");
			}
			
			// 获取订单列表
			Response responseFromGetOrder = WechatBaseUtil.getOrder(httpRequest, baseParam);
			String orderIdsFormJson = BeanProvider.getOrderIdsFormListJson(responseFromGetOrder);
			
			// 检查订单变化  (可能多人提交订单)
			Response orderChange = WechatBaseUtil.checkOrderChange(httpRequest, baseParam, orderIdsFormJson);
			JsonObject orderChangeObject = new JsonParser().parse(orderChange.getResponseStr()).getAsJsonObject();			
			
			if(1 == orderChangeObject.get("code").getAsInt())
				logger.info("订单未发生变化");
			else
				logger.info("订单发生了变化");
		
		}catch(Exception e){
			
			logger.error(e.toString());
			
		}finally{
			
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			WechatBaseUtil.clearCart(httpRequest, baseParam);
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	//////////////////////////////     用户下单并进行支付         ////////////////////////////////////////////////
	
	@Test(description="收银审核同意", dataProvider = "orderApprovalTest",
			dataProviderClass = OrderForNoPrePaySeatTestData.class, groups = {"moduleNo", "allNo"})
	public void orderApprovalTest(String description, List<CartIncludeSuitForm> cartSuitList){
		
		logger.info(description);
		
		try{
			
			////////////////////////前置操作, 包括数据准备      //////////////////////////////////////
					
			// 收银非预付款下单需要 5min 超时失效, 该操作为了使得后续操作不受前次操作下单影响			
//			logger.info("等待之前的订单失效");
//			Thread.sleep(5*60*1000);
//			logger.info("等待动作已完成");
			
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			WechatBaseUtil.clearCart(httpRequest, baseParam);
			
			// 发送收银心跳
			cashService.heart(cashHeartRequest, baseParam);
			
			
			/////////////////////////    业务操作         ////////////////////////////////////////
			
			// 加菜
			SeatNoPrePayUtil.addMenuToCart(httpRequest, baseParam, otherParameter, cartSuitList);
						
			// 数据校验
			boolean result1 = DataService.DataVerified(httpRequest, baseParam, people, cartSuitList);
			Assert.assertEquals(result1, true);
			
			// 下单
			SeatNoPrePayUtil.placeOrder(httpRequest, baseParam, otherParameter);
			
			
			/////////////////////////   收银动作        ///////////////////////////////////////////
			
			//收银审核
			Map<String, Object> cashReturnMap = cashService.approveOrder(cashRequest, baseParam.getEntityId(), CommonConstants.appSecret);
			
			
			/////////////////////////   业务动作        //////////////////////////////////////////////
			//刷新已下单的页面 (收银已经审核)
			Response refreshOrder = WechatBaseUtil.getOrder(httpRequest, baseParam);
			JsonObject refreshOrderObject = new JsonParser().parse(refreshOrder.getResponseStr()).getAsJsonObject();	
			Assert.assertEquals(refreshOrderObject.get("code").getAsInt(), 1, "刷新已下单的页面失败");
			
			//查询付款详情
			Response getBillInfo = SeatNoPrePayUtil.getBillInfo(httpRequest,baseParam);
			JsonObject getBillInfoObject = new JsonParser().parse(refreshOrder.getResponseStr()).getAsJsonObject();	
			Assert.assertEquals(getBillInfoObject.get("code").getAsInt(), 1, "查询付款详情失败");
			
			
			//启动一个线程计算账单
			ServiceBillThreadV2 serviceBillThread = new ServiceBillThreadV2(cashService);
			Thread t = new Thread(serviceBillThread);
		    t.start(); 
		    
		    //去支付
			baseParam.setCardId("");
			Boolean toBillPageFlag = SeatNoPrePayAPI.intoBillPage(httpRequest, baseParam,"true", null);
			Assert.assertTrue(toBillPageFlag,"进入账单页面失败");
			
			//等待支付 获取支付类型
			Boolean getPayTypeFlag = SeatNoPrePayAPI.getPayType(httpRequest, baseParam, null);
			Assert.assertTrue(getPayTypeFlag,"获取支付方式失败");
					
			//会员卡支付
			CardPayBean cardPayBean = new CardPayBean();
			Boolean cardPayFlag = SeatNoPrePayAPI.cardPay(httpRequest,baseParam, cardPayBean);
			Assert.assertTrue(cardPayFlag,"会员卡支付失败");
					
			Thread.sleep(2000);
			cashService.settleAccounts(cashRequest, baseParam, CommonConstants.appSecret);
			
			
			
		}catch(Exception e){
			
			logger.error(e.toString());
			
		}
		
	}
	
	
	@Test(description="收银超时未处理")
	public void orderOvertime(){
		//提交订单
		
		//设置订单超时时间
		
		//等待订单超时
		
		//获取订单
		
		//校验订单状态
		
	}
	
	
	@Test(description="收银超时未处理再次提交")
	public void orderRepeatSubmit(){
		//提交订单
		//设置订单超时时间
				
		//等待订单超时
				
		//获取订单
				
		//校验订单状态
		
		//再次提交订单
		
		//获取订单
	}
	
	@Test(description="收银拒绝")
	public void orderRefuse(){
		//提交订单
				
		//收银拒绝
		
		//校验订单状态
		
		//获取订单
	}
	
	@Test(description="重复下单")
	public void orderRepeat(){
		//提交订单
		
		//提交订单
		
		//校验订单状态
		
		//获取订单
	}
	

}
