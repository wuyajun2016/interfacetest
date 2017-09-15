package com.dfire.qa.meal.module;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONPath;
import com.dfire.qa.meal.api.BaseTestController;
import com.dfire.qa.meal.bean.MenuGet;
import com.dfire.qa.meal.bean.Response;
import com.dfire.qa.meal.data.PayModuleData;
import com.dfire.qa.meal.httpclient.HTTPClientService;
import com.dfire.qa.meal.props.HostProperties;
import com.dfire.qa.meal.service.ICartService;
import com.dfire.qa.meal.service.ICashDeskService;
import com.dfire.qa.meal.service.IMenuService;
import com.dfire.qa.meal.service.IOrderService;
import com.dfire.qa.meal.service.IPayService;
import com.dfire.qa.meal.service.IQRCodeService;
import com.dfire.qa.meal.vo.cart.CartIncludeSuitForm;
import com.dfire.qa.meal.vo.pay.QueryTradeBillForm;
import com.dfire.testcase.bean.TradeBill;
import com.dfire.testcase.function.pay.PayTestData;
import com.dfire.tp.client.bill.request.BillNormalRequest;
import com.dfire.tp.client.bill.request.param.OrderParam;
import com.dfire.tp.client.bill.request.param.PayParam;
import com.dfire.tp.client.bill.request.param.PromotionParam;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class PayTestMoudle extends BaseTestController{
	
	
	@Resource
	private HostProperties hostProperties;
	
	@Resource
	private HTTPClientService httpClientService;
	
	@Resource
	IQRCodeService qrCodeService;
	
	@Resource
	private ICartService cartService;
		
	@Resource
	IMenuService menuService;
	
	
	@Resource
	IOrderService orderService;
	
	@Resource
	IPayService payService;
	
	@Resource
	ICashDeskService cashDeskService;
	
	@BeforeClass(alwaysRun = true)
	public void Setup() throws Exception{
		
		baseParamBean = commonService.getBaseParam(environment);
		
	}
	
	
		
		
	/**
	 * 获取点赞活动开关<br/>
	 * 默认不支持点赞<br/>
	 */
	@Test(description = "get activity switch", dataProvider = "getActivitySwitchTest",
			dataProviderClass = PayModuleData.class, groups = {"smoke", "all"})
	public void getActivitySwitchTest(String description, int responseStatus, int resultCode) throws Exception{
		
		
		Response response = payService.getActivitySwitch(baseParamBean, environment); 
		Assert.assertEquals(response.getStatus(), responseStatus);
		
		if(responseStatus == 200){
			JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(resp.get("code").getAsInt(), resultCode);
							
		}		
		
	}	
	
	
	
	/**
	 * 查询订单是否允许评价<br/>
	 */
	@Test(description = "judge orders Comments", dataProvider = "isCreateWaiterCommentsTest",
			dataProviderClass = PayModuleData.class, groups = {"smoke", "all"})
	public void isCreateWaiterCommentsTest(String description, String order, int responseStatus, int resultCode) throws Exception{
		
					
		Response response = payService.getOrderIfComments(baseParamBean, order, environment); 
		Assert.assertEquals(response.getStatus(), responseStatus);
		
		if(responseStatus == 200){
			JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(resp.get("code").getAsInt(), resultCode);
							
		}
				
	}	
	
	
	
	
	/**
	 * 获取后付款账单信息(即先下单后付款)<br/>
	 */
	@Test(description = "get normal trade bill", dataProvider = "getNormalTradeBillTest",
			dataProviderClass = PayModuleData.class, groups = {"smoke", "all"})
	public void getNormalTradeBillTest(String description, MenuGet menuGet, int responseStatus, int resultCode) throws Exception{
		

		
		qrCodeService.scanCode(baseParamBean, environment);														
		cartService.clickToEnter(baseParamBean, otherParameter, environment);	
		
		
		try{
			
			// 登陆收银机
			String sessionId = cashDeskService.loginWithPassword(baseParamBean, environment);
											
			// 收银机发送心跳
			cashDeskService.sendHeartBeat(baseParamBean, environment);
			
			
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			cartService.clearCart(baseParamBean, environment);
			
			/////////////////////////    加菜到购物车         ////////////////////////////////////////
			List<CartIncludeSuitForm> cartSuitList = commonService.getMenu(baseParamBean, menuGet, otherParameter, environment);
			cartService.addMenuToCart(baseParamBean, otherParameter, cartSuitList, environment);
	
			////////////////////////    下单流程      /////////////////////////////////////////////
			Map<String, Response> orderResultMap = orderService.placeOrder(baseParamBean, otherParameter, environment);
			
			////////////////////////收银审核同意流程      /////////////////////////////////////////////
			Map<String, Object> approveOrderResult = cashDeskService.approveOrder(baseParamBean, sessionId, environment);
			Response responseFromGetOrder = orderService.getOrder(baseParamBean, environment);
			
			BillNormalRequest billNormalRequest = new BillNormalRequest();
			OrderParam orderParam = new OrderParam();
			orderParam.setCartTime(Long.parseLong(JSONPath.read(orderResultMap.get("cartData").getResponseStr(), "$.data.cartTime").toString()));
			orderParam.setOrderId(JSONPath.read(responseFromGetOrder.getResponseStr(), ".data.orderVos[0].orderId").toString());
			orderParam.setRecomputeFlag(false);
						
			PayParam payParam = new PayParam();
			payParam.setGiftPaySelected(true);
			payParam.setCardPaySelected(true);
			payParam.setMemberPointsSelected(false);
			
			PromotionParam promotionParam = new PromotionParam();
			promotionParam.setKouBeiSelected(false);
			
			billNormalRequest.setOrderParam(orderParam);
			billNormalRequest.setPayParam(payParam);
			billNormalRequest.setPromotionParam(promotionParam);
			
			Response response = payService.getNormalTradeBill(baseParamBean, billNormalRequest, environment); 
			Assert.assertEquals(response.getStatus(), responseStatus);
			
			
			if(responseStatus == 200){
				
				JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
				Assert.assertEquals(resp.get("code").getAsInt(), resultCode);	
				
				
				// 数据校验
				Double menuNum = cartSuitList.get(0).getNum();
				Double num = Double.valueOf(JSONPath.read(response.getResponseStr(), "$.data.orderParam.instances[0].num").toString());
				Assert.assertTrue(0 == menuNum.compareTo(num));
				
			}			
			
		}catch(Exception e){
			
			throw new Exception(e);
			
		}finally{
			
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			cartService.clearCart(baseParamBean, environment);
			
		}
					
		
	}
	
	
	
	
	/**
	 * 获取优惠方案及支付订单信息<br/>
	 * 该接口需要订单审核同意才能拉取账单<br/>
	 */
	@Test(description = "get trade bill", dataProvider = "getTradeBillTest",
			dataProviderClass = PayModuleData.class, groups = {"smoke", "all"})
	public void getTradeBillTest(String description, MenuGet menuGet, int responseStatus, int resultCode) throws Exception{
		
								
		qrCodeService.scanCode(baseParamBean, environment);														
		cartService.clickToEnter(baseParamBean, otherParameter, environment);	
		
		
		try{
			
			// 登陆收银机
			String sessionId = cashDeskService.loginWithPassword(baseParamBean, environment);
											
			// 收银机发送心跳
			cashDeskService.sendHeartBeat(baseParamBean, environment);
			
			
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			cartService.clearCart(baseParamBean, environment);
			
			/////////////////////////    加菜到购物车         ////////////////////////////////////////
			List<CartIncludeSuitForm> cartSuitList = commonService.getMenu(baseParamBean, menuGet, otherParameter, environment);
			cartService.addMenuToCart(baseParamBean, otherParameter, cartSuitList, environment);
	
			////////////////////////    下单流程      /////////////////////////////////////////////
			Map<String, Response> orderResultMap = orderService.placeOrder(baseParamBean, otherParameter, environment);
			
			////////////////////////收银审核同意流程      /////////////////////////////////////////////
			Map<String, Object> approveOrderResult = cashDeskService.approveOrder(baseParamBean, sessionId, environment);
			Response responseFromGetOrder = orderService.getOrder(baseParamBean, environment);
			
			
			/////////////////////////////  获取账单        ////////////////////////////////////////////////////////
			QueryTradeBillForm queryTradeBillForm = new QueryTradeBillForm();
			queryTradeBillForm.setEntityId(baseParamBean.getEntityId());
			queryTradeBillForm.setOrderId(JSONPath.read(responseFromGetOrder.getResponseStr(), ".data.orderVos[0].orderId").toString());
			queryTradeBillForm.setSeatCode(baseParamBean.getSeatCode());
			queryTradeBillForm.setSource(1);
			queryTradeBillForm.setCardId("");
			queryTradeBillForm.setSign("");
			
			//// 后续可以采用 类 的方式进行组织
			//BillRequestForm billRequestForm = new BillRequestForm();
			//billRequestForm.setQueryTradeBillForm(queryTradeBillForm);   // 具体账单参数
			//billRequestForm.setSelected(false);                          // 是否选中赞助礼品
			//billRequestForm.setFlag(false);                              // 重新拉取账单, 如果修改为 true，会在 Dpush 端产生一个消息，相当于重新拉取账单，否则默认已经拉取了账单。就不会在 Dpush 端产生消息
			//billRequestForm.setCardSelected(true);                       // 是否选中会员卡支付
			//billRequestForm.setFirstLoading(1);                          // 1: 第一次进入账单   2：非第一次进入账单
			
			/**
			* 之前版本： 将 flag 字段设置为 true 时会重新拉取账单, 重新拉取账单 , 如果修改为 true，会在 Dpush 端产生一个消息，相当于重新拉取账单，否则默认已经拉取了账单。就不会在 Dpush 端产生消息
			* 现在版本：现在为了区分  本地收银  与  云收银  增加该字段 firstLoading， 该字段为 1 时仍然会在 Dpush 产生消息，相当于再次拉取账单,为了避免再次拉取账单需要将该字段设置为非 1，另外需要将该店放入灰度发布中
			*/
			Map<String, Object> parMap = new HashMap<String, Object>();
			parMap.put("query_bill_form", queryTradeBillForm);   
			parMap.put("selected", "false");    
			parMap.put("flag", "false");	       
			parMap.put("cardSelected", "true");  
			parMap.put("firstLoading", 2);   
			
			
			Response getTradeBill = payService.getTradeBillV2(baseParamBean, parMap, environment);
			
			
			if(responseStatus == 200){
				
				JsonObject resp = new JsonParser().parse(getTradeBill.getResponseStr()).getAsJsonObject();			
				Assert.assertEquals(resp.get("code").getAsInt(), resultCode);	
				
				
				// 数据校验
				Double menuNum = cartSuitList.get(0).getNum();
				Double num = Double.valueOf(JSONPath.read(getTradeBill.getResponseStr(), "$.data.pay_order.menus[0].num").toString());
				Assert.assertTrue(0 == menuNum.compareTo(num));
				
				String orderIdSource = queryTradeBillForm.getOrderId();
				String orderIdActual = JSONPath.read(getTradeBill.getResponseStr(), "$.data.pay_order.orderId").toString();
				Assert.assertTrue(orderIdSource.equalsIgnoreCase(orderIdActual));
				
			}		
			
		}catch(Exception e){
			
			throw new Exception(e);
			
		}finally{
			
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			cartService.clearCart(baseParamBean, environment);
			
		}
					
		
	}
	
	
	
	
	/**
	 * 获取支付类型<br/>
	 */
	@Test(description = "get pay type", dataProvider = "getPayTypeTest",
			dataProviderClass = PayModuleData.class, groups = {"smoke", "all"})
	public void getPayTypeTest(String description, MenuGet menuGet, int responseStatus, int resultCode) throws Exception{
		
	}

	
	
	/**
	 * 预付款下单确认, 下单检测购物车与云购物车是否一致<br/>
	 */
	@Test(description = "confirm pre pay ", dataProvider = "confirmPrePayTest",
			dataProviderClass = PayModuleData.class, groups = {"smokeNo", "allNo"})
	public void confirmPrePayTest(String description, MenuGet menuGet, int responseStatus, int resultCode) throws Exception{
		
	}	
		
		
		

}
