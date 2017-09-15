package com.dfire.qa.meal.module;


import java.util.List;

import javax.annotation.Resource;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.dfire.qa.meal.api.BaseTestController;
import com.dfire.qa.meal.bean.MenuGet;
import com.dfire.qa.meal.bean.Response;
import com.dfire.qa.meal.constant.Constants;
import com.dfire.qa.meal.data.OrderModuleData;
import com.dfire.qa.meal.httpclient.HTTPClientService;
import com.dfire.qa.meal.props.HostProperties;
import com.dfire.qa.meal.service.ICartService;
import com.dfire.qa.meal.service.ICommonService;
import com.dfire.qa.meal.service.IMenuService;
import com.dfire.qa.meal.service.IOrderService;
import com.dfire.qa.meal.service.IQRCodeService;
import com.dfire.qa.meal.vo.cart.CartIncludeSuitForm;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class OrderTestModule extends BaseTestController{
	
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
	ICommonService commonService;
	
	
	Gson gson = new Gson();
	
	
	@BeforeClass(alwaysRun = true)
	public void Setup() throws Exception{
		
		baseParamBean = commonService.getBaseParam(environment);		
		
					
	}
	
	
	
	
	/**
	 * 提交订单
	 */
	@Test(description = "submit order test", dataProvider = "submitOrderTest",
			dataProviderClass = OrderModuleData.class, groups = {"smoke", "all"})
	public void submitOrderTest(String description, MenuGet menuGet, int responseStatus, int resultCode, int jobStatus) throws Exception{
				
		// 清除购物车中的所有菜 (测试需要, 非业务流程)
		cartService.clearCart(baseParamBean, environment);
		
				
		// 加菜
		List<CartIncludeSuitForm> cartSuitList = commonService.getMenu(baseParamBean, menuGet, otherParameter, environment);
		cartService.addMenuToCart(baseParamBean, otherParameter, cartSuitList, environment);
		
		// 获取购物车数据
		Response cartData = cartService.listCartData(baseParamBean, environment);
		JsonObject checkJsonObject = new JsonParser().parse(cartData.getResponseStr()).getAsJsonObject();	

		String cartTime = checkJsonObject.get("data").getAsJsonObject().get("cartTime").getAsString();
		otherParameter.put("cartTime", cartTime);
		
		
		//提交订单
		Response orderResult = orderService.submitOrder(baseParamBean, otherParameter, environment);
		JsonObject orderObject = new JsonParser().parse(orderResult.getResponseStr()).getAsJsonObject();	
		if(1 != orderObject.get("code").getAsInt()){
			throw new Exception(Constants.Message.SUBMITORDERERROR);
		}
						
		if(responseStatus == 200){
			JsonObject resp = new JsonParser().parse(orderResult.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(resp.get("code").getAsInt(), resultCode);
			Assert.assertNotNull(resp.get("data").getAsJsonObject().get("waitingOrderId").getAsString());
			
		}
		
		commonService.menuCompare(cartSuitList, baseParamBean, 2, environment);
		
		// 清除购物车中的所有菜 (测试需要, 非业务流程)
		cartService.clearCart(baseParamBean, environment);
		
	}
	
	
	
	
	
	/**
	 * 获取订单列表<br/>
	 */
	@Test(description = "get order test", dataProvider = "getOrderTest",
			dataProviderClass = OrderModuleData.class, groups = {"smoke", "all"})
	public void getOrderTest(String description, MenuGet menuGet, int responseStatus, int resultCode, int jobStatus) throws Exception{
			
		
		// 清除购物车中的所有菜 (测试需要, 非业务流程)
		cartService.clearCart(baseParamBean, environment);
				
				
		// 加菜
		List<CartIncludeSuitForm> cartSuitList = commonService.getMenu(baseParamBean, menuGet, otherParameter, environment);
		cartService.addMenuToCart(baseParamBean, otherParameter, cartSuitList, environment);
		
		// 获取购物车数据
		Response cartData = cartService.listCartData(baseParamBean, environment);
		JsonObject checkJsonObject = new JsonParser().parse(cartData.getResponseStr()).getAsJsonObject();	

		String cartTime = checkJsonObject.get("data").getAsJsonObject().get("cartTime").getAsString();
		otherParameter.put("cartTime", cartTime);
		
		
		//提交订单
		orderService.submitOrder(baseParamBean, otherParameter, environment);
		
		Response response = orderService.getOrder(baseParamBean, environment); 
		Assert.assertEquals(response.getStatus(), responseStatus);
		
		if(responseStatus == 200){
			JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(resp.get("code").getAsInt(), resultCode);
			
		}
		
		commonService.menuCompare(cartSuitList, baseParamBean, 2, environment);
		
		// 清除购物车中的所有菜 (测试需要, 非业务流程)
		cartService.clearCart(baseParamBean, environment);
		
		
	}
	
	
	
	
	
	/**
	 * 检查订单变化<br/>
	 */
	@Deprecated
	@Test(description = "check order change", dataProvider = "checkOrderChangeTest",
			dataProviderClass = OrderModuleData.class, groups = {"No", "No"})
	public void checkOrderChangeTest(String description, MenuGet menuGet, int responseStatus, int resultCode) throws Exception{
		
		/*
		// 清除购物车中的所有菜 (测试需要, 非业务流程)
		cartService.clearCart(baseParamBean, environment);
		
		
		// 加菜
		List<CartIncludeSuitForm> cartSuitList = commonService.getMenu(baseParamBean, menuGet, otherParameter, environment);
		cartService.addMenuToCart(baseParamBean, otherParameter, cartSuitList, environment);
		
		// 获取购物车数据
		Response cartData = cartService.listCartData(baseParamBean, environment);
		JsonObject checkJsonObject = new JsonParser().parse(cartData.getResponseStr()).getAsJsonObject();	

		String cartTime = checkJsonObject.get("data").getAsJsonObject().get("cartTime").getAsString();
		otherParameter.put("cartTime", cartTime);
		
		
		//提交订单
		orderService.submitOrder(baseParamBean, otherParameter, environment);
		
		Response responseFromGetOrder = orderService.getOrder(baseParamBean, environment); 	
		String orderIdsFormJson = BeanProvider.getOrderIdsFormListJson(responseFromGetOrder);
		
		// check order change
		Response response = orderService.checkOrderChange(baseParamBean, orderIdsFormJson, environment); 
		Assert.assertEquals(response.getStatus(), responseStatus);
		
		if(responseStatus == 200){
			JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(resp.get("code").getAsInt(), resultCode);
			
		}
		
		// 清除购物车中的所有菜 (测试需要, 非业务流程)
		cartService.clearCart(baseParamBean, environment);
		*/
		
	}
	
	
	
	
	/**
	 * 订单确认<br/>
	 */
	@Deprecated
	@Test(description = "check order test", dataProvider = "checkOrderTest",
			dataProviderClass = OrderModuleData.class, groups = {"No", "No"})
	public void checkOrderTest(String description, MenuGet menuGet, int responseStatus, int resultCode, int jobStatus) throws Exception{
		
		/*
		Response response = httpRequest.post(PathForHTTP.getPathForCheckOrder(), query, httpBody); 
		Assert.assertEquals(response.getStatus(), responseStatus);
		
		if(responseStatus == 200){
			JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(resp.get("code").getAsInt(), resultCode);
			
			if(jobStatus == 200){
				Assert.assertNotNull(resp.get("data"));
			}
		}
		*/
		
		
	}
	
		
	
		
	

		


}
