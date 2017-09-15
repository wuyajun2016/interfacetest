package com.dfire.qa.meal.api;


import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.aop.ThrowsAdvice;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONPath;
import com.dfire.qa.meal.bean.MenuGet;
import com.dfire.qa.meal.bean.Response;
import com.dfire.qa.meal.data.MenuTestData;
import com.dfire.qa.meal.httpclient.HTTPClientService;
import com.dfire.qa.meal.logger.LoggerMarkers;
import com.dfire.qa.meal.logger.MealLoggerFactory;
import com.dfire.qa.meal.props.HostProperties;
import com.dfire.qa.meal.service.IBossService;
import com.dfire.qa.meal.service.ICartService;
import com.dfire.qa.meal.service.ICashDeskService;
import com.dfire.qa.meal.service.ICommonService;
import com.dfire.qa.meal.service.IOrderService;
import com.dfire.qa.meal.service.IQRCodeService;
import com.dfire.qa.meal.utils.CommonUtil;
import com.dfire.qa.meal.vo.boss.BossConfig;
import com.dfire.qa.meal.vo.cart.CartIncludeSuitForm;
import com.dfire.qa.meal.vo.menu.MenuSpec;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MenuTestController extends BaseTestController{
	
	
	@Resource
	ICommonService commonService;
	
	@Resource
	private IQRCodeService qrCodeService;
	
	@Resource
	private ICartService cartService;
	
	@Resource
	IOrderService orderService;
	
	@Resource
	ICashDeskService cashDeskService;
	
	
	@Resource
	IBossService bossService;
	
	
	@BeforeClass(alwaysRun = true)
	public void Setup() throws Exception{
		
		baseParamBean = commonService.getBaseParam(environment);
		
	}
	
	
	/**
	 * 菜品沽清, 验证菜单数据<br/>
	 */
	@Test(description = "菜品沽清,获取菜单列表，进行菜单验证", groups = {"smoke", "all"})
	public void dishSoldOutTest() throws Exception{
		
	
		Response menuResponse = cartService.listMenus(baseParamBean, otherParameter, environment);
		@SuppressWarnings("unchecked")
		List<Object> menuObjects = (List<Object>)JSONPath.read(menuResponse.getResponseStr(), "$.data.kindMenusVos[0:-1].menus[0:-1].id");
		
		int menuLength = 0;
		for(Object menuListObject : menuObjects){
			@SuppressWarnings("unchecked")
			List<Object> menus = (List<Object>)menuListObject;
			menuLength += menus.size();
		}
		
		
		MenuGet menuGet = new MenuGet();
		menuGet.setNormalMenu(true);
		menuGet.setNormalMenuNo(1);	
		
		List<CartIncludeSuitForm> menuList = commonService.getMenu(baseParamBean, menuGet, otherParameter, environment);
		String menuId = menuList.get(0).getMenuId();		
		String sessionId = cashDeskService.loginWithPassword(baseParamBean, environment);
		
		try{
			
			// 设置商品沽清
			cashDeskService.menuBalance(baseParamBean, menuId, sessionId, environment);
			
			// 等待沽清设置生效
			Thread.sleep(30*1000);
			Response menuResponse2 = cartService.listMenus(baseParamBean, otherParameter, environment);
			@SuppressWarnings("unchecked")
			List<Object> menuObjects2 = (List<Object>)JSONPath.read(menuResponse2.getResponseStr(), "$.data.kindMenusVos[0:-1].menus[0:-1].id");
			
			int menuLength2 = 0;
			for(Object menuListObject : menuObjects2){
				@SuppressWarnings("unchecked")
				List<Object> menus = (List<Object>)menuListObject;
				menuLength2 += menus.size();
				
				for(Object elementObject : menus){
					if(menuId.equalsIgnoreCase(elementObject.toString()))
						throw new Exception("菜品未沽清");
				}
			}
			
			if(menuLength != (menuLength2 + 1))
				throw new Exception("沽清后菜品数目不匹配");
			
			// 取消商品沽清
			cashDeskService.clearMenuBalance(baseParamBean, menuId, sessionId, environment);
		
		
		}catch(Exception e){
			
			throw new Exception(e);
			
		}finally{
			
			cashDeskService.clearMenuBalance(baseParamBean, menuId, sessionId, environment);
			
		}
		
		
	}
	
	

	@Test(description = "菜品下架,获取菜单列表，进行菜单验证", groups = {"smoke", "all"})
	public void dishGroundTest() throws Exception{
				
		MenuGet menuGet = new MenuGet();
		menuGet.setNormalMenu(true);
		menuGet.setNormalMenuNo(1);
		
		List<CartIncludeSuitForm> menuList = commonService.getMenu(baseParamBean, menuGet, otherParameter, environment);
		String menuId = menuList.get(0).getMenuId();	
		
		Response menuResponse = cartService.listMenus(baseParamBean, otherParameter, environment);
		@SuppressWarnings("unchecked")
		List<Object> menuObjects = (List<Object>)JSONPath.read(menuResponse.getResponseStr(), "$.data.kindMenusVos[0:-1].menus[0:-1]");
		
		int menuLength = 0;
		Object targetMenuObject = null;
		
		for(Object menuListObject : menuObjects){
			@SuppressWarnings("unchecked")
			List<Object> menus = (List<Object>)menuListObject;
			menuLength += menus.size();
			
			for(Object element : menus){
				if(menuId.equalsIgnoreCase(JSONPath.read(element.toString(), "$.id").toString()))
						targetMenuObject = element;
			}
		}
		
				
		
		try{
						
			// 设置商品下架			
			bossService.menuConfigUpdate(baseParamBean, targetMenuObject, false, environment);
			
			
			// 等待下架设置生效
			Thread.sleep(30*1000);
			Response menuResponse2 = cartService.listMenus(baseParamBean, otherParameter, environment);
			@SuppressWarnings("unchecked")
			List<Object> menuObjects2 = (List<Object>)JSONPath.read(menuResponse2.getResponseStr(), "$.data.kindMenusVos[0:-1].menus[0:-1].id");
			
			int menuLength2 = 0;
			for(Object menuListObject : menuObjects2){
				@SuppressWarnings("unchecked")
				List<Object> menus = (List<Object>)menuListObject;
				menuLength2 += menus.size();
				
				for(Object elementObject : menus){
					if(menuId.equalsIgnoreCase(elementObject.toString()))
						throw new Exception("菜品未下架");
				}
			}
			
			if(menuLength != (menuLength2 + 1))
				throw new Exception("下架后菜品数目不匹配");
			
			
			// 设置商品上架
			bossService.menuConfigUpdate(baseParamBean, targetMenuObject, true, environment);
			
			
		}catch(Exception e){
			
			throw new Exception(e);
						
		}finally{
			
			// 恢复设置商品上架
			bossService.menuConfigUpdate(baseParamBean, targetMenuObject, true, environment);
			
			// 等待上架设置生效
			Thread.sleep(30*1000);
			
			Response menuResponse3 = cartService.listMenus(baseParamBean, otherParameter, environment);
			@SuppressWarnings("unchecked")
			List<Object> menuObjects3 = (List<Object>)JSONPath.read(menuResponse3.getResponseStr(), "$.data.kindMenusVos[0:-1].menus[0:-1]");
			
			int menuLength3 = 0;

			for(Object menuListObject : menuObjects3){
				@SuppressWarnings("unchecked")
				List<Object> menus = (List<Object>)menuListObject;
				menuLength3 += menus.size();

			}
			
			if(menuLength != menuLength3)
				throw new Exception("上架设置未生效");
			
		}
		
	}
	

	
	
	
	
	
	@Test(description = "扫码时该桌已有菜加入购物车，进行菜单验证", dataProvider = "dishCartsTest",
			dataProviderClass = MenuTestData.class, groups = {"smoke", "all"})
	public void dishCartsTest(String description, MenuGet menuGet) throws Exception{
		
		
		//////////////////////// 前置操作, 包括数据准备      //////////////////////////////////////
					
		// 清除购物车中的所有菜 (测试需要, 非业务流程)
		cartService.clearCart(baseParamBean, environment);

		
		try{
			/////////////////////////    业务操作         ////////////////////////////////////////
			// 扫桌码
			qrCodeService.scanCode(baseParamBean, environment);		
											
			// 执行 “点击进入” 动作
			cartService.clickToEnter(baseParamBean, otherParameter, environment);
			
			
			/////////////////////////    加菜到购物车         ////////////////////////////////////////
			List<CartIncludeSuitForm> cartSuitList = commonService.getMenu(baseParamBean, menuGet, otherParameter, environment);
			cartService.addMenuToCart(baseParamBean, otherParameter, cartSuitList, environment);
			
			
			// 验证菜单
			if(!commonService.menuCompare(cartSuitList, baseParamBean, 1, environment))
				throw new Exception("验证菜单失败");
			
			/////////////////////////    测试操作         ////////////////////////////////////////
			
			// 扫桌码
			qrCodeService.scanCode(baseParamBean, environment);		
											
			// 执行 “点击进入” 动作
			cartService.clickToEnter(baseParamBean, otherParameter, environment);
			
			
			// 验证菜单
			if(!commonService.menuCompare(cartSuitList, baseParamBean, 1, environment))
				throw new Exception("验证菜单失败");
			
		}catch(Exception e){
			
			MealLoggerFactory.LOG_FILES_LOGGER.error(LoggerMarkers.LOGFILE, e.toString());
			
			////////////////////////后置操作         ////////////////////////////////////////////
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			cartService.clearCart(baseParamBean, environment);
			
		}
		
	
		
	}
	
	
	
	@Test(description = "扫码时该桌已下单，进行菜单验证", dataProvider = "dishOrderTest",
			dataProviderClass = MenuTestData.class, groups = {"smoke", "all"})
	public void dishOrderTest(String description, MenuGet menuGet) throws Exception{
		
			
		////////////////////////前置操作, 包括数据准备      //////////////////////////////////////
		
		// 收银非预付款下单需要 5min 超时失效, 该操作为了使得后续操作不受前次操作下单影响			
		logger.info("等待之前的订单失效");
		Thread.sleep(5*60*1000);
		logger.info("等待动作已完成");
		
		// 清除购物车中的所有菜 (测试需要, 非业务流程)
		cartService.clearCart(baseParamBean, environment);
		
		
		/////////////////////////    业务操作         ////////////////////////////////////////
		// 扫桌码
		qrCodeService.scanCode(baseParamBean, environment);		
										
		// 执行 “点击进入” 动作
		cartService.clickToEnter(baseParamBean, otherParameter, environment);			
		
		/////////////////////////    加菜到购物车         ////////////////////////////////////////
		List<CartIncludeSuitForm> cartSuitList = commonService.getMenu(baseParamBean, menuGet, otherParameter, environment);
		cartService.addMenuToCart(baseParamBean, otherParameter, cartSuitList, environment);
		
		
		orderService.placeOrder(baseParamBean, otherParameter, environment);
		
		/////////////////////////    测试操作         ////////////////////////////////////////
		// 扫桌码
		qrCodeService.scanCode(baseParamBean, environment);		
										
		// 执行 “点击进入” 动作
		cartService.clickToEnter(baseParamBean, otherParameter, environment);			
					
		
		// 菜单验证
		commonService.menuCompare(cartSuitList, baseParamBean, 2, environment);

		
		////////////////////////   后置操作         ////////////////////////////////////////////
		// 清除购物车中的所有菜 (测试需要, 非业务流程)
		cartService.clearCart(baseParamBean, environment);

		
	}
	
	

	
	
	
	/**
	 * 添加必选商品<br/>
	 * 需要在 掌柜端 设置必选菜, 然后添加必选商品<br/>
	 * 必选商品只能通过重新扫码进行删除, clearCart 无法删除购物车中的必选商品<br/>
	 */
	@Deprecated
	@Test(description = "add required item ", dataProvider = "addRequiredItemTest",
			dataProviderClass = MenuTestData.class, groups = {"smokeNo", "allNo"})
	public void addRequiredItemTest(String description, Map<String, String> query, int responseStatus, int resultCode, int jobStatus){
		
		try{
			
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			cartService.clearCart(baseParamBean, environment);
			
			// 修改人数和备注信息
			cartService.modifyPeople(baseParamBean, query, environment);
			
			Response response = cartService.addRequiredItem(baseParamBean, query, environment); 
			Assert.assertEquals(response.getStatus(), responseStatus, " 添加必选商品接口返回状态验证失败");
			
			if(responseStatus == 200){
				JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
				Assert.assertEquals(resp.get("code").getAsInt(), resultCode, " 添加必选商品接口返回 code 验证失败");
				
			}			
			
			// 清除购物车中的所有菜 (测试需要, 非业务流程)
			cartService.clearCart(baseParamBean, environment);
			
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}
	

}
