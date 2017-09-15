package com.dfire.qa.meal.module;

import java.util.List;

import javax.annotation.Resource;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.dfire.qa.meal.api.BaseTestController;
import com.dfire.qa.meal.bean.BaseParamBean;
import com.dfire.qa.meal.bean.MenuGet;
import com.dfire.qa.meal.bean.Response;
import com.dfire.qa.meal.data.CartModuleData;
import com.dfire.qa.meal.httpclient.HTTPClientService;
import com.dfire.qa.meal.props.HostProperties;
import com.dfire.qa.meal.service.ICartService;
import com.dfire.qa.meal.service.IMenuService;
import com.dfire.qa.meal.service.IQRCodeService;
import com.dfire.qa.meal.vo.cart.CartIncludeSuitForm;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CartTestModule extends BaseTestController{

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
	
	private Gson gson = new Gson();
	
	
	@BeforeClass(alwaysRun = true)
	public void Setup() throws Exception{
		
		baseParamBean = commonService.getBaseParam(environment);
		
	}
	
	
	@Test(description = "create own cart test with no prepay seat", dataProvider = "createOwnCartTest",
			dataProviderClass = CartModuleData.class, groups = {"smoke", "all"})
	public void createOwnCartTest(String description, BaseParamBean baseParam, int responseStatus, int resultCode) throws Exception{
		

		Response response = cartService.createCart(baseParam, environment);
		Assert.assertEquals(response.getStatus(), responseStatus, "创建个人购物车接口返回状态验证未通过");
		
		if(responseStatus == 200){
			JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(resp.get("code").getAsInt(), resultCode, "创建个人购物车接口返回 code 验证未通过");
			
		}

		
	}
	
	
	
	@Test(description = "get cart data test with no prepay seat", dataProvider = "listCartDataTest",
			dataProviderClass = CartModuleData.class, groups = {"smoke", "all"})
	public void listCartDataTest(String description, BaseParamBean baseParam, int responseStatus, int resultCode, int jobStatus) throws Exception{
			
			
		cartService.createCart(baseParam, environment);
				
		Response response = cartService.listCartData(baseParam, environment);			
		Assert.assertEquals(response.getStatus(), responseStatus, "获取购物车数据接口返回状态验证未通过");
		
		if(responseStatus == 200){
			JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(resp.get("code").getAsInt(), resultCode, "获取购物车数据接口返回 code 验证未通过");
			
			if(jobStatus == 200){
				Assert.assertNotNull(resp.get("data").getAsJsonObject().get("kindUserCarts"), "获取购物车数据接口返回字段中不存在 kindUserCarts 字段");
			}
		}
		
		
	}
	
	
	
	// 获取弹窗消息
	@Test(description = "show popup message with no prepay seat", dataProvider = "showPopupMessageTest",
			dataProviderClass = CartModuleData.class, groups = {"smoke", "all"})
	public void showPopupMessageTest(String description, BaseParamBean baseParam, int responseStatus, int resultCode) throws Exception{
		
			
		Response response = cartService.popupMessage(baseParam, environment);
		Assert.assertEquals(response.getStatus(), responseStatus, "获取弹窗消息接口返回状态验证未通过");
		
		if(responseStatus == 200){
			JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(resp.get("code").getAsInt(), resultCode, "获取弹窗消息接口返回 code 验证未通过");
							
		}
		
		
	}
	
	
	
	
	/**
	 * 添加必选菜<br/>
	 */
	@Test(description = "add required item", dataProvider = "addRequiredItemTest",
			dataProviderClass = CartModuleData.class, groups = {"smoke", "all"})
	public void addRequiredItemTest(String description, int responseStatus, int resultCode) throws Exception{
		
		cartService.createCart(baseParamBean, environment);
		Response response = cartService.addRequiredItem(baseParamBean, otherParameter, environment);
		Assert.assertEquals(response.getStatus(), responseStatus, "添加必选菜接口返回状态验证未通过");
		
		if(responseStatus == 200){
			JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(resp.get("code").getAsInt(), resultCode, "添加必选菜接口返回 code 验证未通过");
							
		}
		
		
	}
		
		
	
	
	
	/**
	 * 购物车页面展示可用优惠信息<br/>
	 */
	@Test(description = "show privilege title with no prepay seat", dataProvider = "showCartPrivilegeTest",
			dataProviderClass = CartModuleData.class, groups = {"smoke", "all"})
	public void showCartPrivilegeTest(String description, BaseParamBean baseParam, List<String> menuIdList, int responseStatus, int resultCode) throws Exception{
		

		Response response = cartService.cartPrivilege(baseParamBean, menuIdList, environment);		
		Assert.assertEquals(response.getStatus(), responseStatus, "优惠载体文字显示栏标题接口返回状态验证未通过");
		
		if(responseStatus == 200){
			JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(resp.get("code").getAsInt(), resultCode, "优惠载体文字显示栏标题接口返回 code 验证未通过");
							
		}
		
		
	}
	
	
	
	/**	
	 * 获取用户购物车的数据接口（不含菜单列表）<br/>
	 * 扫店码,源自 PreCartController<br/>
	 */
	@Test(description = "get user cart", dataProvider = "getUserCartTest",
			dataProviderClass = CartModuleData.class, groups = {"shopCode", "smoke", "all"})
	public void getUserCartTest(String description, BaseParamBean baseParam, int responseStatus, int resultCode) throws Exception{
		
					
		// prepare new cart
		cartService.createCart(baseParam, environment);
						
		// get user cart
		Response response = cartService.getUserCart(baseParam, otherParameter, environment); 
		Assert.assertEquals(response.getStatus(), responseStatus, "获取用户购物车的数据接口（不含菜单列表）返回状态验证失败");
		
		if(responseStatus == 200){
			JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(resp.get("code").getAsInt(), resultCode, "获取用户购物车的数据接口（不含菜单列表）返回 code 验证失败");
	
		}
	}
	
	
	
	/**
	 * 修改个人购物车商品<br/>
	 * 扫店码,源自 PreCartController<br/>
	 */
	@Test(description = "modify cart ", dataProvider = "modifyCartTest",
			dataProviderClass = CartModuleData.class, groups = {"shopCode", "smoke", "all"})
	public void modifyCartTest(String description, BaseParamBean baseParam, MenuGet menuGet, int responseStatus, int resultCode) throws Exception{
		
								
		cartService.createCart(baseParam, environment);
		List<CartIncludeSuitForm> menuList = commonService.getMenu(baseParam, menuGet, otherParameter, environment);		
		Response response = cartService.addDishtoCartWithShop(baseParam, gson.toJson(menuList.get(0)), environment); 
		
		Assert.assertEquals(response.getStatus(), responseStatus);		
		if(responseStatus == 200){
			JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(resp.get("code").getAsInt(), resultCode);				
		}
		
		
		// 菜单数据验证
		
		
					
		
	}
	
	
	
	
	/**
	 * 获取购物车内商品数量<br/>
	 */
	@Test(description = "get cart count", dataProvider = "getCartCountTest",
			dataProviderClass = CartModuleData.class, groups = {"smoke", "all"})
	public void getCartCountTest(String description, BaseParamBean baseParam, MenuGet menuGet, int responseStatus, int resultCode) throws Exception{
		
								
		cartService.createCart(baseParam, environment);
		List<CartIncludeSuitForm> menuList = commonService.getMenu(baseParam, menuGet, otherParameter, environment);
		
		cartService.addDishToCarts(baseParam, gson.toJson(menuList.get(0)), environment);
		
		// get cart count
		Response response = cartService.getMenuCount(baseParam, environment); 
		Assert.assertEquals(response.getStatus(), responseStatus);
		
		if(responseStatus == 200){
			JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(resp.get("code").getAsInt(), resultCode);				
			Assert.assertTrue(1 == resp.get("data").getAsJsonObject().get("currentUserCount").getAsInt());
		}
			
		
		
	}
	
	
	
	
	
	
	
	
	
	
}
