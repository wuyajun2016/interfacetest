package com.dfire.qa.meal.module;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.aop.ThrowsAdvice;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.dfire.qa.meal.api.BaseTestController;
import com.dfire.qa.meal.bean.BaseParamBean;
import com.dfire.qa.meal.bean.MenuGet;
import com.dfire.qa.meal.bean.Response;
import com.dfire.qa.meal.data.MenuModuleData;
import com.dfire.qa.meal.httpclient.HTTPClientService;
import com.dfire.qa.meal.logger.LoggerMarkers;
import com.dfire.qa.meal.logger.MealLoggerFactory;
import com.dfire.qa.meal.props.HostProperties;
import com.dfire.qa.meal.service.ICartService;
import com.dfire.qa.meal.service.IMenuService;
import com.dfire.qa.meal.service.IQRCodeService;
import com.dfire.qa.meal.vo.cart.CartIncludeSuitForm;
import com.dfire.qa.meal.vo.menu.MenuSpec;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MenuTestModule extends BaseTestController{
	
	
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
	
	private String menuId = null;
	
	@BeforeClass(alwaysRun = true)
	public void Setup() throws Exception{
		baseParamBean = commonService.getBaseParam(environment);
		
		MenuGet menuGet = new MenuGet();
		menuGet.setNormalMenu(true);
		menuGet.setNormalMenuNo(1);
		List<CartIncludeSuitForm> cartSuitList = commonService.getMenu(baseParamBean, menuGet, otherParameter, environment);
		menuId = cartSuitList.get(0).getMenuId();
	}
	
	
	
	@Test(description = "list menus spec with no prepay seat", dataProvider = "listMenusSpecTest",
			dataProviderClass = MenuModuleData.class, groups = {"test"})
	public void listMenuSpecTest(String description, MenuSpec menuSpec, int responseStatus, int resultCode) throws Exception{
								
		menuSpec.setMenu_id(menuId);
		
		// 计算 URL 并发送 POST 请求
		Response response = cartService.getSuitMenuSpec(baseParamBean, menuSpec, environment);
		Assert.assertEquals(response.getStatus(), responseStatus, "获取店铺菜单详情接口返回状态验证未通过");
		
		if(responseStatus == 200){
			JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(resp.get("code").getAsInt(), resultCode, "获取店铺菜单列表接口返回 code 验证未通过");
			
		}
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, "the response is : " + response.getResponseStr());
		
		
	}
	
	
	
	

	@Test(description = "list menus with no prepay seat", dataProvider = "listMenusTest",
			dataProviderClass = MenuModuleData.class, groups = {"smoke", "all"})
	public void listMenusTest(String description, int responseStatus, int resultCode, int jobStatus) throws Exception{
					
		
		// 计算 URL 并发送  GET 请求
		Response response = cartService.listMenus(baseParamBean, otherParameter, environment);
		Assert.assertEquals(response.getStatus(), responseStatus, "获取店铺菜单列表接口返回状态验证未通过");
		
		if(responseStatus == 200){
			JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(resp.get("code").getAsInt(), resultCode, "获取店铺菜单列表接口返回 code 验证未通过");
			
//			if(jobStatus == 200){
//				Assert.assertNotNull(resp.get("data").getAsJsonObject().get("kindMenusVos").
//						getAsJsonArray().get(0).getAsJsonObject().get("menus"), "获取店铺菜单列表接口返回数据不存在 menus 字段");
//				
//				// 菜单列表数据校验
//				CommonUtil.compareMenuList(response, true, menuProperties.getMenuProperties(environment));
//			}
			
		}
		
		
	}
	
	
	
	/**
	 * 修改人数和备注信息<br/>
	 */
	@Test(description = "modify people memo", dataProvider = "modifyPeopleTest",
			dataProviderClass = MenuModuleData.class, groups = {"smoke", "all"})
	public void modifyPeopleTest(String description, int responseStatus, int resultCode, int jobStatus) throws Exception{
		
		
		Response response = cartService.modifyPeople(baseParamBean, otherParameter, environment); 
		Assert.assertEquals(response.getStatus(), responseStatus, "修改人数和备注信息接口返回状态验证失败");
		
		if(responseStatus == 200){
			JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(resp.get("code").getAsInt(), resultCode, "修改人数和备注信息接口返回 code 验证失败");
							
		}
			
		
	}
	
	
	
	
	/**
	 * 添加必选商品<br/>
	 */
	@Test(description = "add required item ", dataProvider = "addRequiredItemTest",
			dataProviderClass = MenuModuleData.class, groups = {"smoke", "all"})
	public void addRequiredItemTest(String description, int responseStatus, int resultCode) throws Exception{
		
		
		try{
			
			cartService.createCart(baseParamBean, environment);
			
			Response response = cartService.addRequiredItem(baseParamBean, otherParameter, environment); 
			Assert.assertEquals(response.getStatus(), responseStatus, " 添加必选商品接口返回状态验证失败");
			
			if(responseStatus == 200){
				JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
				Assert.assertEquals(resp.get("code").getAsInt(), resultCode, " 添加必选商品接口返回 code 验证失败");				
			}	
			
		}catch(Exception e){
			
			throw new Exception(e);
			
		}finally{
			
			// 扫桌码清理购物车中存在的必选菜
			qrCodeService.scanCode(baseParamBean, environment);	
			
		}
		
			
	
	}
		
		
	
	/**
	 * 获取推荐菜<br/>
	 */
	@Test(description = "get recommend menus", dataProvider = "getRecommendMenusTest",
			dataProviderClass = MenuModuleData.class, groups = {"smoke", "all"})
	public void getRecommendMenusTest(String description, int responseStatus, int resultCode, int jobStatus) throws Exception{
		
		
		Response response = cartService.getRecommendMenus(baseParamBean, otherParameter, environment); 
		Assert.assertEquals(response.getStatus(), responseStatus, "获取推荐菜接口返回状态验证失败");
		
		if(responseStatus == 200){
			JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(resp.get("code").getAsInt(), resultCode, "获取推荐菜接口返回 code 验证失败");
							
		}
			
		
	}
	
	
	
	/**
	 * 获取用户购物车的数据接口（含菜单列表）<br/>
	 * 扫店码获取菜单列表<br/>
	 */
	@Test(description = "get menus by shop code", dataProvider = "getMenusTest",
			dataProviderClass = MenuModuleData.class, groups = {"smokeNo", "allNo", "need to wait"})
	public void getMenusTest(String description, BaseParamBean baseParam, int responseStatus, int resultCode, int jobStatus) throws Exception{
		
					
		Response response = menuService.getMenusByShopCode(baseParam, otherParameter, environment); 
		Assert.assertEquals(response.getStatus(), responseStatus, "获取用户购物车的数据接口（含菜单列表） 返回状态验证失败");
		
		if(responseStatus == 200){
			JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(resp.get("code").getAsInt(), resultCode, "获取用户购物车的数据接口（含菜单列表）返回  code 验证失败");
			
			if(jobStatus == 200){
				Assert.assertNotNull(resp.get("data").getAsJsonObject().get("kind_menus").
						getAsJsonArray().get(0).getAsJsonObject().get("menus"), "获取用户购物车的数据接口（含菜单列表）返回 字段  menus 为空");
		
			}
			
		}
		
		
	}
	

}
