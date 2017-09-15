package com.dfire.qa.meal.api;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.dfire.qa.meal.bean.MenuGet;
import com.dfire.qa.meal.bean.Response;
import com.dfire.qa.meal.constant.Constants;
import com.dfire.qa.meal.data.PayTestData;
import com.dfire.qa.meal.enums.Environment;
import com.dfire.qa.meal.enums.ScanCodeProcedure;
import com.dfire.qa.meal.logger.LoggerMarkers;
import com.dfire.qa.meal.logger.MealLoggerFactory;
import com.dfire.qa.meal.props.AuthProperties;
import com.dfire.qa.meal.service.IBossService;
import com.dfire.qa.meal.service.ICartService;
import com.dfire.qa.meal.service.ICashDeskService;
import com.dfire.qa.meal.service.ICommonService;
import com.dfire.qa.meal.service.IOrderService;
import com.dfire.qa.meal.service.IPayService;
import com.dfire.qa.meal.service.IQRCodeService;
import com.dfire.qa.meal.utils.HTTPRequestUtil;

import com.dfire.qa.meal.vo.cart.CartIncludeSuitForm;
import com.dfire.qa.meal.vo.cash.CashWaitingOrderVO;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class PayTestController extends BaseTestController{
	
	@Resource
	ICommonService commonService;
	
	@Resource
	IQRCodeService qrCodeService;
	
	@Resource
	ICartService cartService;
	
	@Resource
	IOrderService orderService;
	
	@Resource
	IPayService payService;
	
	@Resource
	IBossService bossService;
	
	@Resource
	ICashDeskService cashDeskService;
	
	
	@Resource
	AuthProperties authProperties;
	
	
	@BeforeClass(alwaysRun = true)
	public void Setup() throws Exception{
		baseParamBean = commonService.getBaseParam(environment);
	}
	
	@Test(description = "扫桌码非预付款", dataProvider = "placeOrderWithNoPrePaySeatCode",
			dataProviderClass = PayTestData.class, groups = {"smoke", "all"})
	public void placeOrderWithNoPrePaySeatCode(String description, MenuGet menuGet) throws Exception{		
		
		MealLoggerFactory.INTEGRATION.info(LoggerMarkers.INTEGRATION, Constants.Pay.PAYBEGIN);
		
		Environment env = environment;				
		
		// 扫桌码
		qrCodeService.scanCode(baseParamBean, env);		
										
		// 执行 “点击进入” 动作
		cartService.clickToEnter(baseParamBean, otherParameter, env);
		
				
		try{
			
			
			////////////////////////   前置操作, 包括数据准备      //////////////////////////////////////				
			
			// 登陆收银机
			String sessionId = cashDeskService.loginWithPassword(baseParamBean, env);
			
			
			// 收银机发送心跳
			cashDeskService.sendHeartBeat(baseParamBean, env);
			
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			cartService.clearCart(baseParamBean, env);
			
			
			
			/////////////////////////    加菜到购物车         ////////////////////////////////////////
			List<CartIncludeSuitForm> cartSuitList = commonService.getMenu(baseParamBean, menuGet, otherParameter, environment);
			cartService.addMenuToCart(baseParamBean, otherParameter, cartSuitList, env);

			
			
			/////////////////////////    数据校验      ///////////////////////////////////////////
//			boolean result1 = commonService.DataVerified(baseParamBean, otherParameter.get("people"), cartSuitList, env);
//			Assert.assertEquals(result1, true);
	
			

			////////////////////////    下单流程      /////////////////////////////////////////////
			orderService.placeOrder(baseParamBean, otherParameter, env);
			
			
			
			////////////////////////   收银审核同意流程      /////////////////////////////////////////////
			Map<String, Object> approveOrderResult = cashDeskService.approveOrder(baseParamBean, sessionId, env);
			CashWaitingOrderVO cashWaitingOrderVO = (CashWaitingOrderVO)(approveOrderResult.get("cashWaitingOrderVo"));
			
			
//				///////////////////////   启动一个线程消费 Dpush 的计算账单消息          /////////////////////////////////////////
//				ServiceBillThread serviceBillThread = new ServiceBillThread(cashDeskService, cashWaitingOrderVO, baseParamBean, sessionId, env);
//				Thread t = new Thread(serviceBillThread);
//			    t.start(); 
		    
		    
			////////////////////////   刷新获取流程      /////////////////////////////////////////////				
			Map<String, Response> refreshOrder = payService.refreshToGetPayDetail(baseParamBean, otherParameter, env);
			
			//填充数据，准备会员卡支付
			JsonObject orderVos = new JsonParser().parse(refreshOrder.get("getOrder").getResponseStr()).
					getAsJsonObject().get("data").getAsJsonObject().get("orderVos").getAsJsonArray().get(0).getAsJsonObject();
		    baseParamBean.setOrderId(orderVos.get("orderId").getAsString());
			
		    
		    
			////////////////////////   支付订单流程      /////////////////////////////////////////////
			payService.payForOrder(baseParamBean, cartSuitList, env);
			
			
			
			////////////////////////   收银结账流程      /////////////////////////////////////////////
			Thread.sleep(2000);
			cashDeskService.settleOrder(baseParamBean, sessionId, cashWaitingOrderVO, env);
			
			
			
			MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, "扫桌码非预付款下单支付(结账清台) 成功");
					
		
		}catch(Exception exception){
			
			
			MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, "扫桌码非预付款下单支付(结账清台) 失败, 原因是：" + exception.toString());				
																	
		}finally{
			
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			cartService.clearCart(baseParamBean, env);		
			
		}
		
					
		
		
		
	}

	
	
	@Test(description = "扫桌码预付款", dataProvider = "placeOrderWithPrePaySeatCode",
			dataProviderClass = PayTestData.class, groups = {"smokeNo", "allNo"})
	public void placeOrderWithPrePaySeatCode(String description, MenuGet menuGet) throws Exception{		
		
		MealLoggerFactory.INTEGRATION.info(LoggerMarkers.INTEGRATION, Constants.Pay.PAYBEGIN);		
		
		// 开启桌码预付款开关
		Response openPrePay = bossService.prePaySwitch(baseParamBean.getEntityId(), ScanCodeProcedure.PREPAYSEAT, true, environment);
		Map<String, Response> openPrePayMap = new HashMap<String, Response>();
		openPrePayMap.put("prePay", openPrePay);
		HTTPRequestUtil.verifyResponse(openPrePayMap, "prePay");	
		
				
		// 扫桌码
		qrCodeService.scanCode(baseParamBean, environment);		
										
		// 执行 “点击进入” 动作
		cartService.clickToEnter(baseParamBean, otherParameter, environment);
		

		try{
			
			
			////////////////////////   前置操作, 包括数据准备      //////////////////////////////////////											
			
			
			// 登陆收银机
			String sessionId = cashDeskService.loginWithPassword(baseParamBean, environment);
			
			
			// 收银机发送心跳
			cashDeskService.sendHeartBeat(baseParamBean, environment);
			
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			cartService.clearCart(baseParamBean, environment);
			
			
			
			/////////////////////////    加菜到购物车         ////////////////////////////////////////			
			List<CartIncludeSuitForm> cartSuitList = commonService.getMenu(baseParamBean, menuGet, otherParameter, environment);
			cartService.addMenuToCart(baseParamBean, otherParameter, cartSuitList, environment);

			
			
			/////////////////////////    数据校验      ///////////////////////////////////////////
			boolean result1 = commonService.DataVerified(baseParamBean, otherParameter.get("people"), cartSuitList, environment);
			Assert.assertEquals(result1, true);
	
			

			////////////////////////    下单流程      /////////////////////////////////////////////
			Map<String, Response> orderResponseMap = orderService.placeOrder(baseParamBean, otherParameter, environment);
			HTTPRequestUtil.verifyResponse(orderResponseMap, "orderResult");												
						    
		    
			////////////////////////   刷新获取流程      /////////////////////////////////////////////				
			Map<String, Response> refreshOrder = payService.refreshToGetPayDetail(baseParamBean, otherParameter, environment);
			
			//填充数据，准备会员卡支付
			JsonObject orderVos = new JsonParser().parse(refreshOrder.get("getOrder").getResponseStr()).
					getAsJsonObject().get("data").getAsJsonObject().get("orderVos").getAsJsonArray().get(0).getAsJsonObject();
		    baseParamBean.setOrderId(orderVos.get("orderId").getAsString());
			
		    
		    
			////////////////////////   支付订单流程      /////////////////////////////////////////////
			payService.payForOrder(baseParamBean, cartSuitList, environment);
			
			
			
			////////////////////////   收银结账流程      /////////////////////////////////////////////
			Thread.sleep(2000);
//				cashDeskService.settleOrder(baseParamBean, sessionId, cashWaitingOrderVO, env);
			
			
			
			MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, "扫桌码预付款(结账清台) 成功");
					
		
		}catch(Exception exception){
							
			MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, "扫桌码预付款(结账清台) 失败");				
																	
		}finally{
			
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			cartService.clearCart(baseParamBean, environment);	
									
		}
				
		
		// 关闭桌码预付款开关
		Response closePrePay = bossService.prePaySwitch(baseParamBean.getEntityId(), ScanCodeProcedure.PREPAYSEAT, false, environment);
		Map<String, Response> closePrePayMap = new HashMap<String, Response>();
		closePrePayMap.put("prePay", closePrePay);
		HTTPRequestUtil.verifyResponse(closePrePayMap, "prePay");	
	
		
		
		
	}
	

}
