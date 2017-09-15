package com.dfire.qa.meal.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;







import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.testng.Assert;






















import com.alibaba.fastjson.JSONPath;
import com.dfire.qa.meal.bean.BaseParamBean;
import com.dfire.qa.meal.bean.MenuGet;
import com.dfire.qa.meal.bean.Response;
import com.dfire.qa.meal.constant.Constants;
import com.dfire.qa.meal.enums.Environment;
import com.dfire.qa.meal.httpclient.HTTPClientService;
import com.dfire.qa.meal.logger.LoggerMarkers;
import com.dfire.qa.meal.logger.MealLoggerFactory;
import com.dfire.qa.meal.props.AuthProperties;
import com.dfire.qa.meal.props.CashProperties;
import com.dfire.qa.meal.props.HostProperties;
import com.dfire.qa.meal.props.ShopProperties;
import com.dfire.qa.meal.service.ICartService;
import com.dfire.qa.meal.service.ICommonService;
import com.dfire.qa.meal.service.IOrderService;
import com.dfire.qa.meal.utils.CommonUtil;
import com.dfire.qa.meal.utils.HTTPRequestUtil;
import com.dfire.qa.meal.utils.MD5Utils;
import com.dfire.qa.meal.utils.PathForHTTP;
import com.dfire.qa.meal.vo.cart.CartIncludeSuitForm;
import com.dfire.qa.meal.vo.menu.MenuList;
import com.dfire.qa.meal.vo.menu.MenuSpec;
import com.dfire.qa.meal.vo.menu.MenuSpecChild;
import com.google.common.base.FinalizablePhantomReference;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Service
public class CommonServiceImpl implements ICommonService{
	
	@Resource
	private HostProperties hostProperties;
	
	@Resource
	private AuthProperties authProperties;
	
	@Resource
	private HTTPClientService httpClientService;
	
	@Resource
	private ICartService cartService;
		
	@Resource
	private CashProperties cashProperties;
	
	@Resource
	private ShopProperties shopProperties;
	
	
	@Resource
	private IOrderService orderService;
	
	private Gson gson = new Gson();
	
	
	
	/**
	 * 从配置文件中获取 entityId, 以及 seatCode
	 */	
	public BaseParamBean getBaseParam(Environment env) throws Exception{
		
		Map<String, String> shopPra = shopProperties.getShopProperties();
		
		// 构造参数
		BaseParamBean baseParamBean = new BaseParamBean();
		baseParamBean.setEntityId((String)shopPra.get("entityIdForWJ"));
		baseParamBean.setSeatCode((String)shopPra.get("seatCodeForWJ"));
		baseParamBean.setOrderId("");
		baseParamBean.setXtoken(getToken((String)shopPra.get("entityIdForWJ"), env));
		
		return baseParamBean;
	}
	
	
	
	/**
	 * 获取请求 token, 该token 在每次请求时必须带上
	 */
	public String getToken(String entityId, Environment env) throws Exception {
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Common.GETTOKEN);
		
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
		
		String unionId = authProperties.getAuthProperties(env).get(entityId);
//		String unionId = (String)properties.get("unionID");
		if( null == unionId || unionId.isEmpty()){
			MealLoggerFactory.LOG_FILES_LOGGER.error(LoggerMarkers.LOGFILE, Constants.Error.GETTOKENERROR);
			throw new Exception(Constants.Error.GETTOKENERROR);
		}
		String signKey = MD5Utils.generatorKey(entityId + unionId);
		
		Map<String, String> query = new HashMap<String, String>();
		query.put("entity_id", entityId);
		query.put("unionid", unionId);
		query.put("sign", signKey);
		
		// 计算 URL 并发送 Get 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("serverURL"), PathForHTTP.getPathForGetToken(), query, protocol);
		Response response = httpClientService.doGet(url, null);
		
		JsonObject resp = new JsonParser().parse(response.getResponseStr().trim()).getAsJsonObject();	
		Assert.assertEquals(resp.get("success").getAsString(), "true");
		
		Assert.assertEquals(resp.get("model").getAsJsonObject().get("success").getAsString(), "true");
		String xtoken = resp.get("model").getAsJsonObject().get("model").getAsString();
		
		
		return xtoken;
	}

	
	@Override
	public String getToken(String entityId, String unionId, Environment env) throws Exception{
		

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Common.GETTOKEN);
		
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
		
		
		if( null == unionId || unionId.isEmpty()){
			MealLoggerFactory.LOG_FILES_LOGGER.error(LoggerMarkers.LOGFILE, Constants.Error.GETTOKENERROR);
			throw new Exception(Constants.Error.GETTOKENERROR);
		}
		String signKey = MD5Utils.generatorKey(entityId + unionId);
		
		Map<String, String> query = new HashMap<String, String>();
		query.put("entity_id", entityId);
		query.put("unionid", unionId);
		query.put("sign", signKey);
		
		// 计算 URL 并发送 Get 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("serverURL"), PathForHTTP.getPathForGetToken(), query, protocol);
		Response response = httpClientService.doGet(url, null);
		
		JsonObject resp = new JsonParser().parse(response.getResponseStr().trim()).getAsJsonObject();	
		Assert.assertEquals(resp.get("success").getAsString(), "true");
		
		Assert.assertEquals(resp.get("model").getAsJsonObject().get("success").getAsString(), "true");
		String xtoken = resp.get("model").getAsJsonObject().get("model").getAsString();
		
		
		return xtoken;
		
	}
	

	@Override
	public boolean DataVerified(BaseParamBean baseParam, Map<String, String> otherParameter, String people,
			List<CartIncludeSuitForm> httpJsonForCartSuitList, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Common.SHOPCODEVERIFIED);
		
		boolean result = false;
		
		try{
			// 获取虚拟购物车数据及用餐人数
			Response response = cartService.getUserCart(baseParam, otherParameter, env);
			JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(resp.get("code").getAsInt(), 1);
			
			// 构造验证参数						
			Map<String, Map<String, CartIncludeSuitForm>> cartSuitMap = new HashMap<String, Map<String,CartIncludeSuitForm>>();
			
			for(CartIncludeSuitForm cartSuit : httpJsonForCartSuitList){
				
				if(cartSuitMap.keySet().contains(cartSuit.getUid())){
					cartSuitMap.get(cartSuit.getUid()).put(cartSuit.getMenuId(), cartSuit);
				}
				else{
					Map<String, CartIncludeSuitForm> menuMap = new HashMap<String, CartIncludeSuitForm>();
					menuMap.put(cartSuit.getMenuId(), cartSuit);
					cartSuitMap.put(cartSuit.getUid(), menuMap);
				}
				
			}
									
			
			// 参数验证
			result = MenusValidate(response, people, cartSuitMap, env);
			Assert.assertEquals(result, true);
			MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Common.SHOPVERYSUC);
			
			return result;
			
		}catch(Exception e){
			
			MealLoggerFactory.LOG_FILES_LOGGER.error(LoggerMarkers.LOGFILE, Constants.Common.SHOPVERYFAIL);
			
		}
		
		return result;
		
	}

	
	

	@Override
	public boolean DataVerified(BaseParamBean baseParam, String people, List<CartIncludeSuitForm> httpJsonForCartSuitList, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Common.SEATCODEVERIFIED);
		
		boolean result = false;
		
		try{
			// 获取虚拟购物车数据及用餐人数
			Response response = cartService.listCartData(baseParam, env);
			JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(resp.get("code").getAsInt(), 1);
			
			// 构造验证参数						
			Map<String, Map<String, CartIncludeSuitForm>> cartSuitMap = new HashMap<String, Map<String,CartIncludeSuitForm>>();
			
			for(CartIncludeSuitForm cartSuit : httpJsonForCartSuitList){
				
				if(cartSuitMap.keySet().contains(cartSuit.getUid())){
					cartSuitMap.get(cartSuit.getUid()).put(cartSuit.getMenuId(), cartSuit);
				}
				else{
					Map<String, CartIncludeSuitForm> menuMap = new HashMap<String, CartIncludeSuitForm>();
					menuMap.put(cartSuit.getMenuId(), cartSuit);
					cartSuitMap.put(cartSuit.getUid(), menuMap);
				}
				
			}
									
			
			// 参数验证
			result = MenusValidate(response, people, cartSuitMap, env);
			Assert.assertEquals(result, true);
			MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Common.SEATVERYSUC);
			
			return result;
			
		}catch(Exception e){
			
			MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Common.SEATVERYFAIL);
		}
		
		return result;
	}

	
	

	@Override
	public boolean MenusValidate(Response response, String people, Map<String, Map<String, CartIncludeSuitForm>> expectedCartSuit, Environment env) throws Exception {
		
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Common.MENUSVERIFIED);
		
    	boolean flag = false;
    	
    	try{
    		
    		JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();
    		
    		// 就餐人数验证
			resp.get("data").getAsJsonObject().get("people").getAsString().equalsIgnoreCase(people);
			
			// 获取购物车数组
			JsonArray cartsArray = resp.get("data").getAsJsonObject().get("userCarts").getAsJsonArray();
			
			// 获取用户 ID
			Set<String> uidSet = expectedCartSuit.keySet();
			
			for(JsonElement cart : cartsArray){
				
				// 用户信息确认
				JsonObject customer = cart.getAsJsonObject().get("customerVo").getAsJsonObject();
				boolean customerExist = uidSet.contains(customer.get("id").getAsString());
				if(true != customerExist){
					throw new Exception(gson.toJson(new Response(200, Constants.Menu.UIDVERIFY)));
				}
				
				// 获取菜单详情列表
				JsonArray menuArray = cart.getAsJsonObject().get("cartVoList").getAsJsonArray();
				
				// 获取特定用户的购物车中的 menuID 集合
				Set<String> menuIdList = expectedCartSuit.get(customer.get("id").getAsString()).keySet();
				Map<String, CartIncludeSuitForm> originMenuList = expectedCartSuit.get(customer.get("id").getAsString());
				
				// 对具体的菜品进行验证, 该菜可能为 普通菜 或者为 套菜 
				for(JsonElement menuElement : menuArray){
					
					JsonObject menuJsonObject = menuElement.getAsJsonObject();
					
					// 判断该菜是否为套菜
					if(2 == menuJsonObject.get("kind").getAsInt()){
						
						// 验证父菜
						if(true != menuIdList.contains(menuJsonObject.get("menuId").getAsString())){

							throw new Exception(gson.toJson(new Response(200, Constants.Menu.PARENTMENU)));
						}
						
						
						// 获取具体的菜品, 该菜为套菜，包含子菜
						CartIncludeSuitForm cartSuitForm = originMenuList.get(menuJsonObject.get("menuId").getAsString());
						
						
						
						if(cartSuitForm.getNum().intValue() != menuJsonObject.get("num").getAsInt()){

							throw new Exception(gson.toJson(new Response(200, Constants.Menu.PARENTMENUNUM)));
						}
						
						menuJsonObject.get("name").getAsString().equalsIgnoreCase(cartSuitForm.getMenuName());
												
						
					}else{
						
						// 表明该菜为普通菜
						if(true != menuIdList.contains(menuJsonObject.get("menuId").getAsString())){

							throw new Exception(gson.toJson(new Response(200, Constants.Menu.NORMALMENU)));
						}
						
						// 获取具体的菜品
						CartIncludeSuitForm cartSuitForm = originMenuList.get(menuJsonObject.get("menuId").getAsString());
						
						// 验证菜品数据
						if(1 != menuJsonObject.get("kind").getAsInt()){

							throw new Exception(gson.toJson(new Response(200, Constants.Menu.NORMALMENUKIND)));
						}
						
						if(cartSuitForm.getNum().intValue() != menuJsonObject.get("num").getAsInt()){

							throw new Exception(gson.toJson(new Response(200, Constants.Menu.NORMALMENUNUM)));
						}
						
						menuJsonObject.get("name").getAsString().equalsIgnoreCase(cartSuitForm.getMenuName());
						
					}
										
					
				}
				
				
			}
			
    		flag = true;
    		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Common.MENUSVERYSUC);
 
    	}catch(Exception e){
    		
    		MealLoggerFactory.LOG_FILES_LOGGER.error(LoggerMarkers.LOGFILE, Constants.Common.MENUSVERYFAIL);
    		
    	}
    	
    	return flag;
	}



	@Override
	public List<CartIncludeSuitForm> getMenu(BaseParamBean baseParam, MenuGet menuGet, Map<String, String> otherParameter, Environment env) throws Exception {
		
		
		// 获取所有菜品列表
		Response menuListResponse = cartService.listMenus(baseParam, otherParameter, env);
		JsonObject menuListJsonObject = new JsonParser().parse(menuListResponse.getResponseStr()).getAsJsonObject();
		if(menuListResponse.getStatus() != 200 || menuListJsonObject.get("code").getAsInt() != 1)
			throw new Exception("fail to list menu");
		
		
		// 保存菜品 menuId
		List<String> normalMenuIdList = new ArrayList<String>();
		List<String> suitMenuIdList = new ArrayList<String>();
		
		Map<String, String> normalMenuMap = new HashMap<String, String>();
		Map<String, String> suitMenuMap = new HashMap<String, String>();
		
		
		// 将普通菜和套菜的 menuId 进行分类
		for(JsonElement kindMenuElement : menuListJsonObject.get("data").getAsJsonObject().get("kindMenusVos").getAsJsonArray()){
			JsonArray kindMeunJsonArray = kindMenuElement.getAsJsonObject().get("menus").getAsJsonArray();
			
			for(JsonElement menuElement : kindMeunJsonArray){
				if(0 == menuElement.getAsJsonObject().get("isInclude").getAsInt()){
					normalMenuIdList.add(menuElement.getAsJsonObject().get("id").getAsString());
					normalMenuMap.put(menuElement.getAsJsonObject().get("id").getAsString(), menuElement.getAsJsonObject().get("name").getAsString());
				}
				else{ 
					suitMenuIdList.add(menuElement.getAsJsonObject().get("id").getAsString());
					suitMenuMap.put(menuElement.getAsJsonObject().get("id").getAsString(), menuElement.getAsJsonObject().get("name").getAsString());
				}
			}
		}
		
		
		
		
		// 构造参数
		MenuSpecChild menuSpecChild = new MenuSpecChild("", null, null, null);
		List<MenuSpecChild> menuSpecChilds = new ArrayList<MenuSpecChild>();
		menuSpecChilds.add(menuSpecChild);
		MenuList menuList = new MenuList(menuSpecChilds);
	
		
		
		// 构造普通菜bean
		List<CartIncludeSuitForm> normalCartItem = new ArrayList<CartIncludeSuitForm>();
		if(menuGet.getNormalMenu() && menuGet.getNormalMenuNo() > 0){
			
			int toIndex = menuGet.getNormalMenuNo();
			List<String> normalMenuIdChoosenList = normalMenuIdList.subList(0, (toIndex > normalMenuIdList.size()) ? normalMenuIdList.size() : toIndex);
			
			for(String menuId : normalMenuIdChoosenList){
				
				Response menuItemResponse = cartService.getNormalMenuSpec(baseParam, menuId, env);
				JsonObject menuItemJsonObject = new JsonParser().parse(menuItemResponse.getResponseStr()).getAsJsonObject().
						get("data").getAsJsonObject().get("menuDetailWithPrivilegeVo").getAsJsonObject();
				
				CartIncludeSuitForm normalMenu = new CartIncludeSuitForm();
				if(menuItemJsonObject.get("makeDataList").getAsJsonArray().size() > 0)
					normalMenu.setMakeId(menuItemJsonObject.get("makeDataList").getAsJsonArray().get(0).getAsJsonObject().get("makeId").getAsString());
				
				if(menuItemJsonObject.get("specDataList").getAsJsonArray().size() > 0)
					normalMenu.setSpecDetailId(menuItemJsonObject.get("specDataList").getAsJsonArray().get(0).getAsJsonObject().get("specItemId").getAsString());
				
				
				// 添加配料
				if(menuItemJsonObject.get("additionKindMenuList").getAsJsonArray().size() > 0){
					
					JsonObject additionMenu = menuItemJsonObject.get("additionKindMenuList").getAsJsonArray().get(0).getAsJsonObject();
					CartIncludeSuitForm childMenu = new CartIncludeSuitForm();
					
					childMenu.setKindMenuId(additionMenu.get("kindMenuId").getAsString());
					childMenu.setKindMenuName(additionMenu.get("kindMenuName").getAsString());
					childMenu.setNum((double)1);
					childMenu.setKindType(5);
					
					childMenu.setMenuId(additionMenu.get("additionMenuList").getAsJsonArray().get(0).getAsJsonObject().get("menuId").getAsString());
					childMenu.setMenuName(additionMenu.get("additionMenuList").getAsJsonArray().get(0).getAsJsonObject().get("menuName").getAsString());
					
					List<CartIncludeSuitForm> childCartVos = new ArrayList<CartIncludeSuitForm>();
					childCartVos.add(childMenu);
					
					normalMenu.setChildCartVos(childCartVos);
					
				}
				
				normalMenu.setUid(authProperties.getAuthProperties(env).get("uid"));
				normalMenu.setKindType(1);
				
				Integer numberInteger = (menuItemJsonObject.get("stepLength").getAsInt() > menuItemJsonObject.get("recommendLevel").getAsInt()) ? menuItemJsonObject.get("stepLength").getAsInt() : menuItemJsonObject.get("recommendLevel").getAsInt();
				Double number = (numberInteger > 0 )? numberInteger.doubleValue() : (double)1.0;
				normalMenu.setNum(number);
				
				normalMenu.setMenuId(menuId);
				normalMenu.setMenuName(normalMenuMap.get(menuId));
				normalMenu.setIndex(CommonUtil.getUUID());  // meun 的唯一标识
				
				normalCartItem.add(normalMenu);	
			}
			
		}
		
		
		
		// 构造套菜 bean
		List<CartIncludeSuitForm> suitCartItem = new ArrayList<CartIncludeSuitForm>();
		if(menuGet.getSuitMenu() && menuGet.getSuitMenuNo() > 0){
			int toIndex = menuGet.getSuitMenuNo();
			List<String> suitMenuIdChoosenList = suitMenuIdList.subList(0, (toIndex > suitMenuIdList.size()) ? suitMenuIdList.size() : toIndex);
			
			for(String menuId : suitMenuIdChoosenList){
				
				MenuSpec menuSpec = new MenuSpec(baseParam.getEntityId(), menuId, 1, menuList);
				Response menuItemResponse = cartService.getSuitMenuSpec(baseParam, menuSpec, env);
				JsonObject menuItemJsonObject = new JsonParser().parse(menuItemResponse.getResponseStr()).getAsJsonObject().
						get("data").getAsJsonObject().get("suitHitMenuVo").getAsJsonObject();
				
				CartIncludeSuitForm suitMenu = new CartIncludeSuitForm();
				
				// 加子菜
				List<CartIncludeSuitForm> childCartVos = new ArrayList<CartIncludeSuitForm>();
				for(JsonElement childKindMenuElement : menuItemJsonObject.get("suitMenuGroupVos").getAsJsonArray()){
					
					JsonObject childMenuJsonObject = childKindMenuElement.getAsJsonObject().get("menus").getAsJsonArray().get(0).getAsJsonObject();
		
					CartIncludeSuitForm childMenu = new CartIncludeSuitForm();
					childMenu.setMenuId(childMenuJsonObject.get("id").getAsString());
					childMenu.setName(childMenuJsonObject.get("name").getAsString());
					
					if(childMenuJsonObject.get("hasMake").getAsInt() != 0)
						childMenu.setMakeId(childMenuJsonObject.get("makeId").getAsString());
					if(childMenuJsonObject.get("hasSpec").getAsInt() != 0)
						childMenu.setSpecDetailId(childMenuJsonObject.get("specDetailId").getAsString());
					
					
					childMenu.setSuitMenuDetailId(childKindMenuElement.getAsJsonObject().get("suitMenuDetailId").getAsString());
					childMenu.setKindType(5);
					childMenu.setId(childMenuJsonObject.get("id").getAsString());
					childMenu.setNum(childKindMenuElement.getAsJsonObject().get("num").getAsDouble());
					childMenu.setIndex(CommonUtil.getUUID());
					
					childCartVos.add(childMenu);
						
					
				}
				
				suitMenu.setChildCartVos(childCartVos);
				suitMenu.setName(suitMenuMap.get(menuId));
				suitMenu.setMenuId(menuId);
				
				suitMenu.setKindType(2);
				suitMenu.setNum((double)1);
				suitMenu.setUid(authProperties.getAuthProperties(env).get("uid"));
				
				suitMenu.setIndex(CommonUtil.getUUID());
				
				
				suitCartItem.add(suitMenu);
			}
		}
		
		
	
		return chooseMenu(normalCartItem, suitCartItem);

	}



	@Override
	public List<CartIncludeSuitForm> chooseMenu(List<CartIncludeSuitForm> normalMenu, List<CartIncludeSuitForm> suitMenu) throws Exception {
		
		List<CartIncludeSuitForm> result = new ArrayList<CartIncludeSuitForm>();
	
		result.addAll(normalMenu);
		result.addAll(suitMenu);

		return (result.isEmpty() ? null : result);
	}
	
	
	

	@Override
	public  Boolean menuCompare(List<CartIncludeSuitForm> cartSuitList, BaseParamBean baseParam, Integer stage, Environment env) throws Exception{
		
		Response response = null;
		String path = null;
		
		if(1 == stage){
			response = cartService.listCartData(baseParam, env);
			path = "$.data.kindUserCarts[0:-1].cartVoList[0:-1]";
			
		}else if(2 == stage){
			response = orderService.getOrder(baseParam, env);
			path = "$.data.waitingOrderVos[0:0].waitingOrderItems.orderMenus[0:-1]"; // 只获取当前订单
			
		}else if(3 == stage){
			response = orderService.getOrder(baseParam, env);
			path = "$.data.orderVos[0:-1].orderItems.orderMenus[0:-1]";
		}
		
		
		@SuppressWarnings("unchecked")
		List<Object> menuList = (List<Object>)JSONPath.read(response.getResponseStr(), path);
		
		// 去除数目为 0 的假菜
		List<CartIncludeSuitForm> actualCartSuitList = new ArrayList<CartIncludeSuitForm>();
		for(CartIncludeSuitForm element : cartSuitList){
			if(element.getNum().intValue() > 0)
				actualCartSuitList.add(element);
		}
		
		List<Object> actualMenuList = getActualMenuList(menuList);
		if(actualCartSuitList.size() != actualMenuList.size())
			throw new Exception("菜品种类数量不匹配");
		
		for(CartIncludeSuitForm element : actualCartSuitList){
			
			Boolean hit = false;
			for(Object elementObject : actualMenuList){
				
				String menuId = (String)JSONPath.read(elementObject.toString(), ".menuId");	

				if(menuId.equalsIgnoreCase(element.getMenuId())){
					hit = true;
					String name = (String)JSONPath.read(elementObject.toString(), ".name");
					
					BigDecimal num = new JsonParser().parse(elementObject.toString()).getAsJsonObject().get("num").getAsBigDecimal();
					
					String menuName = ((null == element.getMenuName()) || element.getMenuName().isEmpty())? element.getName() : element.getMenuName();
					if(!menuName.equalsIgnoreCase(name))
						throw new Exception("菜名不匹配");
					
					if( 0 != element.getNum().compareTo(num.doubleValue()))
						throw new Exception("同一种菜品数量不匹配");
					
					break;
				}
			}
			
			if(!hit)
				throw new Exception(element.getMenuName() + " " + element.getName() + " " + element.getMenuId() + " 未匹配");
		}
		
		return true;
	}



	@Override
	public List<Object> getActualMenuList(List<Object> menuList) throws Exception{
		
		List<Object> actualMenuList = new ArrayList<Object>();
		
		for(Object elementObject : menuList){
			
			@SuppressWarnings("unchecked")
			List<Object> elementList = (List<Object>) elementObject;
			
			for(Object menuItem : elementList){
				actualMenuList.add(menuItem);
			}
			
		}		
		
		return actualMenuList;
	}
	
	
	
	@Override
	public Boolean clearCartData(BaseParamBean baseParamBean, Map<String, String> otherParameter, Environment env) throws Exception {
		
		cartService.clearCart(baseParamBean, env);
		
		Response cartData = cartService.listCartData(baseParamBean, env);
		JsonObject checkJsonObject = new JsonParser().parse(cartData.getResponseStr()).getAsJsonObject();	
		int cartNumber = checkJsonObject.get("data").getAsJsonObject().get("kindUserCarts").getAsJsonArray().size();
		if(cartNumber < 1)
			return true;
		
		// 消除必选菜
		orderService.placeOrder(baseParamBean, otherParameter, env);
		
		return true;
		
	}
	
	
	
	
}

	
	
	



