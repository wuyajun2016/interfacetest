package com.dfire.qa.meal.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;










import com.alibaba.fastjson.JSONPath;
import com.dfire.qa.meal.bean.BaseParamBean;
import com.dfire.qa.meal.bean.MenuGet;
import com.dfire.qa.meal.bean.Response;
import com.dfire.qa.meal.data.CartTestData;
import com.dfire.qa.meal.enums.Environment;
import com.dfire.qa.meal.httpclient.HTTPClientService;
import com.dfire.qa.meal.logger.LoggerMarkers;
import com.dfire.qa.meal.logger.MealLoggerFactory;
import com.dfire.qa.meal.props.HostProperties;
import com.dfire.qa.meal.service.IBossService;
import com.dfire.qa.meal.service.ICartService;
import com.dfire.qa.meal.service.ICommonService;
import com.dfire.qa.meal.service.IQRCodeService;
import com.dfire.qa.meal.utils.HTTPRequestUtil;
import com.dfire.qa.meal.utils.PathForHTTP;
import com.dfire.qa.meal.vo.boss.ForceConfig;
import com.dfire.qa.meal.vo.cart.CartIncludeSuitForm;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CartTestController extends BaseTestController{

	
	@Resource
	private HostProperties hostProperties;
	
	@Resource
	private HTTPClientService httpClientService;
	
	@Resource
	IQRCodeService qrCodeService;
	
	@Resource
	private ICartService cartService;
		
	
	@Resource
	private ICommonService commonService;
	
	@Resource
	private IBossService bossService;
	
	
	private Gson gson = new Gson();


	@BeforeClass(alwaysRun = true)
	public void Setup() throws Exception{
		
		baseParamBean = commonService.getBaseParam(environment);
		
		// 扫桌码
		qrCodeService.scanCode(baseParamBean, environment);		
										
		// 执行 “点击进入” 动作
		cartService.clickToEnter(baseParamBean, otherParameter, environment);
		
	}
	

	
		
		
	/**
	 * 该接口已废弃
	 */
	@Deprecated
	// 购物车页面展示可用优惠信息
	@Test(description = "show cart privilege with no prepay with seat", dataProvider = "showCartPrivilegeTest",
			dataProviderClass = CartTestData.class, groups = {"smokeNo", "allNo"})
	public void showCartPrivilegeTest(String description, Map<String, String> query, int responseStatus, int resultCode, int jobStatus) throws Exception{
		
		
		// prepare new cart
		cartService.createCart(baseParamBean, environment);		
		
		// get info of carts with list format
		cartService.listCartData(baseParamBean, environment);
		
		// 计算 URL 并发送  GET 请求
		String protocol = (Environment.publish == environment) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(environment).get("serverURL"), PathForHTTP.getPathForShowTitle(), query, protocol);
		Response response = httpClientService.doGet(url, null);		
		Assert.assertEquals(response.getStatus(), responseStatus, " 购物车页面展示可用优惠信息接口返回状态验证未通过");
		
		if(responseStatus == 200){
			JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(resp.get("code").getAsInt(), resultCode, " 购物车页面展示可用优惠信息接口返回 code 验证未通过");
							
		}
		
		
	}
	
	
	
	
	/**
	 * 加菜到购物车<br/>
	 */
	@Test(description = "add menu with no prepay with seat", dataProvider = "addMenuTest",
			dataProviderClass = CartTestData.class, groups = {"smoke", "all"})
	public void addMenuTest(String description, MenuGet menuGet, int responseStatus, int resultCode) throws Exception{
		
		cartService.clearCart(baseParamBean, environment);
				
		// prepare new cart
		cartService.createCart(baseParamBean, environment);		
		
	
		List<CartIncludeSuitForm> targetMenu = commonService.getMenu(baseParamBean, menuGet, otherParameter, environment);
		for(CartIncludeSuitForm cartMenu : targetMenu){
			
			Response response = cartService.addDishToCarts(baseParamBean, gson.toJson(cartMenu), environment);	
			Assert.assertEquals(response.getStatus(), responseStatus, " 加菜到购物车接口返回状态验证未通过");
					
			if(responseStatus == 200){
				JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
				Assert.assertEquals(resp.get("code").getAsInt(), resultCode, " 加菜到购物车接口返回 code 验证未通过");								
			}
		
		}
				
		commonService.menuCompare(targetMenu, baseParamBean, 1, environment);
		
		cartService.clearCart(baseParamBean, environment);
		
	}
	
	
	
	
	@Test(description = "用参人数为 6, 选择单个普通菜, 加入购物车, 清空购物车, 重新加菜", dataProvider = "addMenuTest2",
			dataProviderClass = CartTestData.class, groups = {"smoke", "all"})
	public void addMenuTest2(String description, MenuGet menuGet) throws Exception{
		
		cartService.clearCart(baseParamBean, environment);

		cartService.createCart(baseParamBean, environment);	
		
		/////////////////////////    加菜到购物车         ////////////////////////////////////////
		List<CartIncludeSuitForm> cartSuitList = commonService.getMenu(baseParamBean, menuGet, otherParameter, environment);
		cartService.addMenuToCart(baseParamBean, otherParameter, cartSuitList, environment);
		
		commonService.menuCompare(cartSuitList, baseParamBean, 1, environment);
		
		
		cartService.clearCart(baseParamBean, environment);
		cartService.addMenuToCart(baseParamBean, otherParameter, cartSuitList, environment);
		commonService.menuCompare(cartSuitList, baseParamBean, 1, environment);
		
		cartService.clearCart(baseParamBean, environment);
		
	}
	
	
	
	@Test(description = "用参人数为 6, 选择单个普通菜, 加入购物车, 将该份菜份数减为 0, 重新加菜", dataProvider = "addMenuTest3",
			dataProviderClass = CartTestData.class, groups = {"smoke", "all"})
	public void addMenuTest3(String description, MenuGet menuGet) throws Exception{
		
		cartService.clearCart(baseParamBean, environment);

		cartService.createCart(baseParamBean, environment);	
		
		/////////////////////////    加菜到购物车         ////////////////////////////////////////
		List<CartIncludeSuitForm> cartSuitList = commonService.getMenu(baseParamBean, menuGet, otherParameter, environment);
		cartService.addMenuToCart(baseParamBean, otherParameter, cartSuitList, environment);
		
		commonService.menuCompare(cartSuitList, baseParamBean, 1, environment);
		
		Response response = cartService.listCartData(baseParamBean, environment);
		String index = JSONPath.read(response.getResponseStr(), "$.data.kindUserCarts[0].cartVoList[0].index").toString();
		
		Map<String, Double> menuMap = new HashMap<String, Double>();
		for(CartIncludeSuitForm element : cartSuitList){
			menuMap.put(element.getMenuId(), element.getNum());
			element.setNum((double)0);
		}
		
		cartSuitList.get(0).setIndex(index);
		cartService.addMenuToCart(baseParamBean, otherParameter, cartSuitList, environment);
		commonService.menuCompare(cartSuitList, baseParamBean, 1, environment);
		
		
		for(CartIncludeSuitForm element : cartSuitList){			
			element.setNum(menuMap.get(element.getMenuId()));
		}
		cartService.addMenuToCart(baseParamBean, otherParameter, cartSuitList, environment);
		commonService.menuCompare(cartSuitList, baseParamBean, 1, environment);
		
		cartService.clearCart(baseParamBean, environment);
		
	}
	
	
	
	
	/**
	 * 必选菜只在新开单或者新开桌的时候才会加入,对于已经开桌并且含有未结订单的桌位不会再加入必选菜<br/>
	 */
	@Test(description = "掌柜端打开添加必选菜开关, 添加普通菜到购物车", dataProvider = "addMenuTest4",
			dataProviderClass = CartTestData.class, groups = {"smokeNo", "allNo"})
	public void addMenuTest4(String description, MenuGet menuGet, ForceConfig forceConfig, MenuGet menuGet2) throws Exception{
		
		commonService.clearCartData(baseParamBean, otherParameter, environment);
			
		// 掌柜端设置添加必选菜
		List<CartIncludeSuitForm> cartSuitList = commonService.getMenu(baseParamBean, menuGet, otherParameter, environment);
		forceConfig.setMenuId(cartSuitList.get(0).getMenuId());
		bossService.saveForceMenu(baseParamBean.getEntityId(), forceConfig, environment);
		
		try{
			
			cartService.createCart(baseParamBean, environment);
						
			// 触发 加必选菜 动作
			cartService.modifyPeople(baseParamBean, otherParameter, environment);
						
			List<CartIncludeSuitForm> cartSuitList2 = commonService.getMenu(baseParamBean, menuGet2, otherParameter, environment);
			cartService.addMenuToCart(baseParamBean, otherParameter, cartSuitList2, environment);
						
			
			cartSuitList.get(0).setNum(Double.valueOf(otherParameter.get("people")));
			List<CartIncludeSuitForm> totalMenus = new ArrayList<CartIncludeSuitForm>();
			totalMenus.addAll(cartSuitList);
			totalMenus.addAll(cartSuitList2);			
			commonService.menuCompare(totalMenus, baseParamBean, 1, environment);			
			
		}catch(Exception e){
			
			throw new Exception(e);
			
		}finally{
			
			// 掌柜端移除必选菜
			bossService.deleteAllForceMenu(baseParamBean.getEntityId(), environment);			

			commonService.clearCartData(baseParamBean, otherParameter, environment);
		}
	}
	
	
	
	/**
	 * 只有添加的菜的 uid 以及执行请求时的 token 是其他人的才算他人点餐<br/>
	 */
	@Test(description = "多人将普通菜或者套菜加入购物车", dataProvider = "addMenuTest5",
			dataProviderClass = CartTestData.class, groups = {"smoke", "all"})
	public void addMenuTest5(String description, MenuGet menuGet1, MenuGet menuGet2) throws Exception{
		
		cartService.clearCart(baseParamBean, environment);

		try{
			
			/////////////////////////    用户1 加菜到购物车         ////////////////////////////////////////
			List<CartIncludeSuitForm> cartSuitList = commonService.getMenu(baseParamBean, menuGet1, otherParameter, environment);
			cartService.addMenuToCart(baseParamBean, otherParameter, cartSuitList, environment);
			
			
			/////////////////////////    用户2 加菜到购物车         ////////////////////////////////////////
			BaseParamBean otherBaseParam = new BaseParamBean();
			otherBaseParam.setEntityId(baseParamBean.getEntityId());
			otherBaseParam.setSeatCode(baseParamBean.getSeatCode());
			otherBaseParam.setXtoken(commonService.getToken(baseParamBean.getEntityId(), "oSqRbuEM0bV8HwzZo1sLIjVk9tUo", environment));
			
			List<CartIncludeSuitForm> cartSuitList2 = commonService.getMenu(otherBaseParam, menuGet2, otherParameter, environment);	
			cartSuitList2.get(0).setUid("2f80fa408a0b427ea7b2a28f33358468");
			cartService.addMenuToCart(otherBaseParam, otherParameter, cartSuitList2, environment);
			
			List<CartIncludeSuitForm> cartSuitListAll = new ArrayList<CartIncludeSuitForm>();
			cartSuitListAll.addAll(cartSuitList);
			cartSuitListAll.addAll(cartSuitList2);
			
			commonService.menuCompare(cartSuitListAll, baseParamBean, 1, environment);
			
		}catch(Exception e){
			
			throw new Exception(e);
			
		}finally{
			
			cartService.clearCart(baseParamBean, environment);
			
		}
		
		
		
	}
		
		
		
		
		
		
	
}
