package com.dfire.qa.meal.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dfire.qa.meal.bean.BaseParamBean;
import com.dfire.qa.meal.bean.MenuGet;
import com.dfire.qa.meal.bean.Response;
import com.dfire.qa.meal.bean.ResultMap;
import com.dfire.qa.meal.constant.Constants;
import com.dfire.qa.meal.enums.Environment;
import com.dfire.qa.meal.logger.LoggerMarkers;
import com.dfire.qa.meal.logger.MealLoggerFactory;
import com.dfire.qa.meal.props.AuthProperties;
import com.dfire.qa.meal.service.IBossService;
import com.dfire.qa.meal.service.ICartService;
import com.dfire.qa.meal.service.ICashDeskService;
import com.dfire.qa.meal.service.ICommonService;
import com.dfire.qa.meal.service.IIntegrationService;
import com.dfire.qa.meal.service.IOrderService;
import com.dfire.qa.meal.service.IPayService;
import com.dfire.qa.meal.service.IQRCodeService;
import com.dfire.qa.meal.vo.cart.CartIncludeSuitForm;
import com.dfire.qa.meal.vo.cash.CashWaitingOrderVO;
import com.dfire.soa.order.bo.Order;
import com.dfire.soa.order.service.IGetOrderService;
import com.dfire.soa.order.test.ITestOrderService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.twodfire.share.result.Result;

@Service
public class IntegrationServiceImpl implements IIntegrationService{

	
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
	
	
    @Resource
    ITestOrderService orderTestService;
    
    
    @Resource
    IGetOrderService getOrderService;
    
    
    
	
	@Override
	public ResultMap payNoPrePaySeat(BaseParamBean baseParamBean, Map<String, String> otherParameter, MenuGet menuGet, Environment env, Boolean pay) throws Exception {

		MealLoggerFactory.INTEGRATION.info(LoggerMarkers.INTEGRATION, Constants.Pay.PAYBEGIN);
		
		
//		// 清理订单, 结账清台
//		List<String> seatCodes = new ArrayList<String>();
//		seatCodes.add(baseParamBean.getSeatCode());
//		cleanOrder(baseParamBean.getEntityId(), seatCodes);
		
		
		
		// 扫桌码
		qrCodeService.scanCode(baseParamBean, env);		
										
		// 执行 “点击进入” 动作
		cartService.clickToEnter(baseParamBean, otherParameter, env);
		
		
		List<CartIncludeSuitForm> temp = null;
		String orderId = null;
		try{
			
			
			////////////////////////   前置操作, 包括数据准备      //////////////////////////////////////				
			
			// 登陆收银机
			String sessionId = cashDeskService.loginWithPassword(baseParamBean, env);
			
			
			// 收银机发送心跳
			cashDeskService.sendHeartBeat(baseParamBean, env);
			
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			cartService.clearCart(baseParamBean, env);
			
			
			
			/////////////////////////    加菜到购物车         ////////////////////////////////////////
			List<CartIncludeSuitForm> cartSuitList = commonService.getMenu(baseParamBean, menuGet, otherParameter, env);
			cartService.addMenuToCart(baseParamBean, otherParameter, cartSuitList, env);
			temp = cartSuitList;
			
			
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
			
		    // 判断是否仅仅进行下单
		    orderId = orderVos.get("orderId").getAsString();
			if(!pay){
				ResultMap resultMap = new ResultMap(cartSuitList);
				resultMap.put("id", orderVos.get("orderId").getAsString());
				return resultMap;
			}
		    
			////////////////////////   支付订单流程      /////////////////////////////////////////////
			payService.payForOrder(baseParamBean, cartSuitList, env);
			
			
			
			////////////////////////   收银结账流程      /////////////////////////////////////////////
			Thread.sleep(2000);
			cashDeskService.settleOrder(baseParamBean, sessionId, cashWaitingOrderVO, env);
			
			
			
			MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, "扫桌码非预付款下单支付(结账清台) 成功");
					
		
		}catch(Exception exception){
			
			
			MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, "扫桌码非预付款下单支付(结账清台) 失败, 原因是 ： " + exception.toString());
			return new ResultMap("007", "扫桌码非预付款下单支付(结账清台) 失败");
																	
		}finally{
			
			// 清除购物车中的所有菜 (测试需要, 非业务流程)			
			cartService.clearCart(baseParamBean, env);		
			
		}
		
		ResultMap resultMap = new ResultMap(temp);
		resultMap.put("id", orderId);
		return resultMap;
	}


	@Deprecated
	@Override
	public Boolean cleanOrder(String entityId, List<String> seatCodes) throws Exception {
		
		
		List<Order> orders = getOrderService.getBySeatCodes(entityId, seatCodes);

		List<String> orderIdList = new ArrayList<String>();
		for(Order order : orders){
			orderIdList.add(order.getId());
		}
		
		Result<Integer> endOrder = orderTestService.endOrder(entityId, orderIdList);
		if(!endOrder.getModel().equals(1))
			return false;
		
		return true;
	}

}
