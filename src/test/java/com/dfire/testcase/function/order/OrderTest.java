package com.dfire.testcase.function.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.dfire.testcase.function.bean.cash.CashWaitingOrderVO;
import com.dfire.testcase.function.util.base.WechatBaseUtil;
import com.dfire.wechat.util.CommonConstants;
import com.dfire.wechat.util.HttpRequestEx;
import com.dfire.wechat.util.Response;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class OrderTest extends OrderTestBase{
	
	private static final Logger logger = Logger.getLogger(OrderTest.class);
	
	
	@BeforeClass(alwaysRun = true)
	public void setup() throws Exception{
		logger.info("OrderTest start!");
		//初始化
		httpRequest = new HttpRequestEx(host, CommonConstants.HTTPS);
		cashRequest = new HttpRequestEx(cashHost, CommonConstants.HTTPS);
		cashHeartRequest = new HttpRequestEx(cashHeartHost, false);
		 
		//从配置文件获取参数
		String entityId = propertyUtil.getValue("entityId");
		String seatCode = propertyUtil.getValue("seatCode");

		uid = propertyUtil.getValue("uid");
		String orderId = "";
		String unionId = propertyUtil.getValue("unionid");
		String xtoken = WechatBaseUtil.getToken(httpRequest, entityId, unionId);//获取token
		
		baseParam.setEntityId(entityId);
		baseParam.setOrderId(orderId);
		baseParam.setSeatCode(seatCode);
		baseParam.setXtoken(xtoken);
		//非预付款下单
		
		//收银机登录
		cashService.setCashRequest(cashRequest);
		cashMap.put("entity_id", baseParam.getEntityId());
		cashService.setCashInitBean();
		cashService.setCashExtBean();
		String sessionID = cashService.loginwithpassword(cashRequest,baseParam, cashMap, appSecret);
		cashService.cashextBean.setSessionId(sessionID);
		
	}
	
	@BeforeMethod(alwaysRun = true)
	public void setupOrder(){
		logger.info("before method!");
		
		List<String> menuIdList = new ArrayList<String>();
		
		parMap.put("recommend", "");
		parMap.put("repeat", "false");
		
		parMap.put("people", "2");
		parMap.put("memo", "2");
		parMap.put("oldPeople", "2");
		parMap.put("page", "1");
		parMap.put("pageSize", "5");
		parMap.put("isPreCart", "false");
		
		parMap.put("menuIdList", gson.toJson(menuIdList)); // 该参数为空
		parMap.put("memo_labels", "");
		
		// 用于提交订单
		parMap.put("isPrePay", "false");
		parMap.put("cartTime", "");
	}

	
	@Test(description="小二下单校验菜单和收银获取到的是否一致",invocationCount=1,threadPoolSize=1,groups={"module"})
	public void orderApprovalTest() throws Exception{
		logger.info("提交订单测试开始！");
		String heartAppSecret = propertyUtil.getValue("heart.appsecret");
		//收银心跳
		cashService.heart(cashHeartRequest,baseParam, cashMap, heartAppSecret );
		
		//创建一个订单
		createOrder();
		//提交订单
		parMap.put("peopleCount", "3");
		parMap.put("memo", "approve!");
		
		// 获取特定用户的购物车数据
		Response cartData = WechatBaseUtil.listCartData(httpRequest, baseParam);
		JsonObject checkJsonObject = new JsonParser().parse(cartData.getResponseStr()).getAsJsonObject();			
		Assert.assertEquals(checkJsonObject.get("code").getAsInt(), 1, "获取购物车数据失败");
		String cartTime = checkJsonObject.get("data").getAsJsonObject().get("cartTime").getAsString();
		parMap.put("cartTime", cartTime);
					
		Response response = WechatBaseUtil.submitOrder(httpRequest, baseParam, parMap);
		Assert.assertTrue(Response.isSuccess(response),"提交订单返回结果有误！");
		logger.info(response.getResponseStr());
		
		Thread.sleep(1000);
		//收银审核同意
		Map<String, Object> cashreturnMap = cashService.approveOrder(cashRequest, cashMap,baseParam.getEntityId(), appSecret);
		CashWaitingOrderVO cashWaitingOrderVO = (CashWaitingOrderVO) cashreturnMap.get("cashWaitingOrderVO");
		//校验waitingorder
		Assert.assertTrue(verifyWaitingOrder(cashWaitingOrderVO,cartList),"菜单与收银不一致");
		//支付
		payOrder();
		//收银结账
		Thread.sleep(2000);
		cashService.settleAccounts(cashRequest, baseParam,cashMap, appSecret);
		logger.info("提交订单测试完成！");
	}

	
	@AfterClass(alwaysRun = true)
	public void tearDown(){
		logger.info("tearDown in OrderTest");
		httpRequest.ShutDown();
		cashRequest.ShutDown();
	}
	
	
}
