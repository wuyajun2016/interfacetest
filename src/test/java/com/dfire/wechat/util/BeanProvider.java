package com.dfire.wechat.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.dfire.soa.order.bo.Cart;
import com.dfire.testcase.bean.CartsCreate;
import com.dfire.testcase.bean.MenuForm;
import com.dfire.testcase.bean.OrderIdsForm;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class BeanProvider {
	
	private static final Logger logger = Logger.getLogger(BeanProvider.class);
	private static Gson gson = new Gson();

	/**
	 * the uid name is began with entityId
	 * @param number
	 * @param entityId
	 * @return
	 */
	public static List<String> getUUIDNameListWithEntityIDPrefix(int number, String entityId){
		List<String> uuidList = new ArrayList<String>();
		for(int i = 0; i < number; i++){
			String uuid = UuidUtils.generate(entityId);
			uuidList.add(uuid);
		}
		return uuidList;
	}
	
	
	
	/**
	 * get UUIDName
	 * @return
	 */
	public static String getUUIDName(){
		UUID uuid = UUID.randomUUID();
		String [] uuidArray = uuid.toString().split("-");  // avoid character '-' in case the String is too long
		String id = uuidArray[0] + uuidArray[1] + uuidArray[2] + uuidArray[3] + uuidArray[4];  // shop id
		return id;
	}
	/**
	 * get UUID number
	 * @param number
	 * @return
	 */
	public static List<String> getUUIDNameList(int number){
		List<String> uuidList = new ArrayList<String>();
		for(int i = 0; i < number; i++){
			UUID uuid = UUID.randomUUID();
			String [] uuidArray = uuid.toString().split("-");  // avoid character '-' in case the String is too long
			String id = uuidArray[0] + uuidArray[1] + uuidArray[2] + uuidArray[3] + uuidArray[4];  // shop id
			uuidList.add(id);
		}
		return uuidList;
	}
	
	/**
	 * @param menuNumber  范围不能超过 99
	 * @param kindType 1.普通菜  2.套菜  5.加料菜（只能在子child出现）
	 * @param menuId 菜单 ID
	 * @param uid 单人点餐时 uid 与 customerID 是一致的
	 * @return
	 */
	public static CartIncludeSuitForm getCartSuit(double menuNumber, int kindType, String menuId, String uid){
		CartIncludeSuitForm cartSuit = new CartIncludeSuitForm();
		cartSuit.setNum(menuNumber);
		cartSuit.setKindType(kindType);
		
		cartSuit.setMakeId("test_makeId_1");
		cartSuit.setSpecDetailId("test_SpecId_2");
		cartSuit.setMenuName("test_for_cart");
		
		cartSuit.setUid(uid);
		cartSuit.setMenuId(menuId);
		cartSuit.setChildCartVos(null);
		
		return cartSuit;
	}
	
	
	
	/**
	 * get cat suit form list with json
	 */
	public static String getCartSuitListJson(double menuNumber, int kindType, String menuId, String uid){
		
		CartIncludeSuitForm cartSuit = new CartIncludeSuitForm();
		cartSuit.setNum(menuNumber);
		cartSuit.setKindType(kindType);
		
		cartSuit.setMakeId("test_makeId");
		cartSuit.setSpecDetailId("test_for_SpecId");
		cartSuit.setMenuName("test_for_menu");
		
		cartSuit.setUid(uid);
		cartSuit.setMenuId(menuId);
		cartSuit.setChildCartVos(null);

		
		List<CartIncludeSuitForm> cartSuitFormList = new ArrayList<CartIncludeSuitForm>();
		cartSuitFormList.add(cartSuit);
		
		return gson.toJson(cartSuitFormList);
	}
	
	public static String getCartSuitListJson(double menuNumber, int kindType, String menuId, String uid, int dishNumber){
		
		CartIncludeSuitForm cartSuit = new CartIncludeSuitForm();
		cartSuit.setNum(menuNumber);
		cartSuit.setKindType(kindType);
		
		cartSuit.setMakeId("test_makeId");
		cartSuit.setSpecDetailId("test_for_SpecId");
		cartSuit.setMenuName("test_for_menu");
		
		cartSuit.setUid(uid);
		cartSuit.setMenuId(menuId);
		cartSuit.setChildCartVos(null);

		
		List<CartIncludeSuitForm> cartSuitFormList = new ArrayList<CartIncludeSuitForm>();
		for(int i=0; i<dishNumber; ++i){
			cartSuitFormList.add(cartSuit);
		}
		
		return gson.toJson(cartSuitFormList);
	}
	
	/**
	 * include child cartSuit
	 */
	public static String getCartSuitListJson(double menuNumber, int kindType, String menuIdParent, String menuIdChild, String uid, boolean flag){
		
		CartIncludeSuitForm cartSuit = new CartIncludeSuitForm();
		cartSuit.setNum(menuNumber);
		cartSuit.setKindType(kindType);  // suit (套菜)
		
		cartSuit.setMakeId("test_makeId");
		cartSuit.setSpecDetailId("test_for_SpecId");
		cartSuit.setMenuName("test_for_menu");
		
		cartSuit.setUid(uid);
		cartSuit.setMenuId(menuIdParent);
		
		// include child cartSuit
		CartIncludeSuitForm cartSuitChild = new CartIncludeSuitForm();
		List<CartIncludeSuitForm> cartSuitFormList = new ArrayList<CartIncludeSuitForm>();
		if(true == flag){		
			cartSuitChild.setNum(menuNumber);
			cartSuitChild.setKindType(1); // normal (普通菜)
			
			cartSuitChild.setMakeId("test_makeId");
			cartSuitChild.setSpecDetailId("test_for_SpecId");
			cartSuitChild.setMenuName("test_for_menu");
			
			cartSuitChild.setUid(uid);
			cartSuitChild.setMenuId(menuIdChild);
			cartSuitChild.setChildCartVos(null);
			// add cartSuit to list
			cartSuitFormList.add(cartSuitChild);
		}
		
		if(true == flag)
			cartSuit.setChildCartVos(cartSuitFormList);
		else
			cartSuit.setChildCartVos(null);

		
		List<CartIncludeSuitForm> cartSuitFormListForHTTP = new ArrayList<CartIncludeSuitForm>();
		cartSuitFormListForHTTP.add(cartSuit);
		
		return gson.toJson(cartSuitFormListForHTTP);
	}
	
	
	
	/**
	 * 无菜的子菜, 测试特例
	 * get cat suit form with json
	 */
	public static String getCartSuitJson(double menuNumber, int kindType, String menuId, String uid){
		CartIncludeSuitForm cartSuit = new CartIncludeSuitForm();
		cartSuit.setNum(menuNumber);
		cartSuit.setKindType(kindType);
		
		cartSuit.setMakeId("test_makeId");
		cartSuit.setSpecDetailId("test_for_SpecId");
		cartSuit.setMenuName("test_for_menu");
		
		cartSuit.setUid(uid);
		cartSuit.setMenuId(menuId);
		cartSuit.setChildCartVos(null);
		
		return gson.toJson(cartSuit);
	}
	
	/**
	 *无子菜的菜, 返回 Json 
	 */
	public static String getCartSuitJson(double menuNumber, int kindType, String makeId, 
			String SpecDetailed, String menuName, String menuId, String uid){
		CartIncludeSuitForm cartSuit = new CartIncludeSuitForm();
		cartSuit.setNum(menuNumber);
		cartSuit.setKindType(kindType);
		
		cartSuit.setMakeId(makeId);
		cartSuit.setSpecDetailId(SpecDetailed);
		cartSuit.setMenuName(menuName);
		
		cartSuit.setUid(uid);
		cartSuit.setMenuId(menuId);
		cartSuit.setChildCartVos(null);
		
		return gson.toJson(cartSuit);
	}
	
	
	/**
	 *无子菜的菜, 返回 CartIncludeSuitForm 类对象
	 */
	public static CartIncludeSuitForm getCartSuit(double menuNumber, int kindType, String makeId, 
			String SpecDetailed, String menuName, String menuId, String uid){
		
		CartIncludeSuitForm cartSuit = new CartIncludeSuitForm();
		cartSuit.setNum(menuNumber);
		cartSuit.setKindType(kindType);
		
		cartSuit.setMakeId(makeId);
		cartSuit.setSpecDetailId(SpecDetailed);
		cartSuit.setMenuName(menuName);
		
		cartSuit.setUid(uid);
		cartSuit.setMenuId(menuId);
		cartSuit.setChildCartVos(null);
		
		return cartSuit;
	}
	
	/**
	 *无子菜的菜, 返回 CartIncludeSuitForm 类对象
	 */
	public static CartIncludeSuitForm getCartSuit(double menuNumber, int kindType, String makeId, 
			String SpecDetailed, String menuName, String menuId, String uid, String index){
		
		CartIncludeSuitForm cartSuit = new CartIncludeSuitForm();
		cartSuit.setNum(menuNumber);
		cartSuit.setKindType(kindType);
		
		cartSuit.setMakeId(makeId);
		cartSuit.setSpecDetailId(SpecDetailed);
		cartSuit.setMenuName(menuName);
		
		cartSuit.setUid(uid);
		cartSuit.setMenuId(menuId);
		cartSuit.setChildCartVos(null);
		
		cartSuit.setIndex(index);
		
		return cartSuit;
	}
	
	
	/**
	 *无子菜的菜, 返回 CartIncludeSuitForm 类对象
	 */
	public static CartIncludeSuitForm getCartSuitWithDetail(double menuNumber, int kindType, String makeId, 
			String SpecDetailed, String menuName, String menuId, String uid, String suitMenuDetailId){
		
		CartIncludeSuitForm cartSuit = new CartIncludeSuitForm();
		cartSuit.setNum(menuNumber);
		cartSuit.setKindType(kindType);
		
		cartSuit.setMakeId(makeId);
		cartSuit.setSpecDetailId(SpecDetailed);
		cartSuit.setMenuName(menuName);
		
		cartSuit.setUid(uid);
		cartSuit.setMenuId(menuId);
		cartSuit.setChildCartVos(null);
		
		cartSuit.setSuitMenuDetailId(suitMenuDetailId);
		
		return cartSuit;
	}
	
	
	
	/**
	 *构造套菜, 返回参数为 Json 形式
	 */
	public static String getCartSuitWithChildJson(CartIncludeSuitForm parentCartSuit, List<CartIncludeSuitForm> clildCartSuit){
				
		parentCartSuit.setChildCartVos(clildCartSuit);
		
		return gson.toJson(parentCartSuit);
	}
	
	
	
	public static String getCartSuitListJson(List<CartIncludeSuitForm> cartSuitList){
				
		return gson.toJson(cartSuitList);
	}
	
	/**
	 * 获取某个特定菜的 Json 字符串
	 */
	public static String getCartSuitListJson(){
		// 菜品的规格
		double menuNumber = 1;
		int kindType = 1;
		String makeId = ""; 
		String SpecDetailed = "";
		String menuName = "水果沙拉";
		String menuId = CommonConstants.menuId;
		String uid = "b379d59ed79c4991a05fb2eb2a7b1c2c";
		
		CartIncludeSuitForm cartSuit = new CartIncludeSuitForm();
		cartSuit.setSpecDetailId(SpecDetailed);
		cartSuit.setMakeId(makeId);
		cartSuit.setMenuId(menuId);
		cartSuit.setNum(menuNumber);
		cartSuit.setUid(uid);
		
		List<CartIncludeSuitForm> listCarts = new ArrayList<CartIncludeSuitForm>();
		listCarts.add(cartSuit);
		return gson.toJson(listCarts);
	}
	
	
	
	public static String getCartsCreateJson(String entityId, String seatCode, String orderId, int peopleCount){
		
		CartsCreate cartsCreate = new CartsCreate();
		cartsCreate.setEntity_id(entityId);
		cartsCreate.setSeat_code(seatCode);
		
		cartsCreate.setOrder_id(orderId);
		
		if(peopleCount > 0)
			cartsCreate.setPeople_count(peopleCount);
		
		return gson.toJson(cartsCreate);
	}
	
	/**
	 * include child cartSuit
	 */
	public static String getCartSuitJson(double menuNumber, int kindType, String menuIdParent, String menuIdChild, String uid, boolean flag){
		CartIncludeSuitForm cartSuit = new CartIncludeSuitForm();
		cartSuit.setNum(menuNumber);
		cartSuit.setKindType(kindType);  // suit(套菜)
		
		cartSuit.setMakeId("test_makeId");
		cartSuit.setSpecDetailId("test_for_SpecId");
		cartSuit.setMenuName("test_for_menu");
		
		cartSuit.setUid(uid);
		cartSuit.setMenuId(menuIdParent);
		
		// child cartSuit
		CartIncludeSuitForm cartSuitChild = new CartIncludeSuitForm();
		List<CartIncludeSuitForm> cartSuitFormList = new ArrayList<CartIncludeSuitForm>();
		if(true == flag){		
			cartSuitChild.setNum(menuNumber);
			cartSuitChild.setKindType(1);  // normal(正常菜) 
			
			cartSuitChild.setMakeId("test_makeId");
			cartSuitChild.setSpecDetailId("test_for_SpecId");
			cartSuitChild.setMenuName("test_for_menu");
			
			cartSuitChild.setUid(uid);
			cartSuitChild.setMenuId(menuIdChild);
			cartSuitChild.setChildCartVos(null);
			// add cartSuit to list
			cartSuitFormList.add(cartSuitChild);
		}
		
		if(true == flag)
			cartSuit.setChildCartVos(cartSuitFormList);
		else
			cartSuit.setChildCartVos(null);
		
		return gson.toJson(cartSuit);
	}
	

	
	
	/**
	 * child carts is null
	 */
	public static Cart createCart(String entityId, String menuId){
		Cart cart = new Cart();
		cart.setAccountNum(12.0);
		cart.setAccountUnit("份");
		cart.setAddPrice(12.0);
		
		cart.setBoxNum(12);
		cart.setBoxPrice(12.0);
		cart.setChildCarts(null);
		
		cart.setEntityId(entityId);
		cart.setMenuId(menuId);
		cart.setMakeName("makeName");
		
		return cart;
	}
	
	/**
	 * with child carts
	 */
	public static Cart createCartWithChildCarts(String entityId, String menuId){
		Cart cart = new Cart();
		cart.setAccountNum(16.0);
		cart.setAccountUnit("份");
		cart.setAddPrice(18.0);
		
		cart.setBoxNum(19);
		cart.setBoxPrice(10.0);
		
		List<Cart> cartList = new ArrayList<Cart>();
		cartList.add(createCart(entityId, menuId));
		cart.setChildCarts(cartList);
		
		cart.setEntityId(entityId);
		cart.setMenuId(menuId);
		cart.setMakeName("makeName");
		
		return cart;
	}
	
	
	/**
	 * 获取测试用的 orderIdsFormList, 形式为 json
	 */
	public static String getOrderIdsFormListJson(String orderId, String uid, String menuId, int menuFormStatus){
		
		// menuForm List
		String makeId = "makeId";
		String specId = "specId";
		String num = "8";
		
		MenuForm menuForm = new MenuForm();
		menuForm.setUid(uid);
		menuForm.setMenuId(menuId);
		menuForm.setMakeId(makeId);
		menuForm.setSpecId(specId);
		menuForm.setNum(num);
		menuForm.setStatus(menuFormStatus);
		
		List<MenuForm> menuFormList = new ArrayList<MenuForm>();
		menuFormList.add(menuForm);
		
		// orderIdsForm List
		OrderIdsForm orderIdsForm = new OrderIdsForm();
		orderIdsForm.setId(orderId);
		orderIdsForm.setOrder(menuFormList);
		
		List<OrderIdsForm> orderIdsFormList = new ArrayList<OrderIdsForm>();
		orderIdsFormList.add(orderIdsForm);
		
		return gson.toJson(orderIdsFormList);
	}
	
	
	/**
	 * 获取特 orderIdsFormList 的   Json 形式
	 * @return
	 */
	public static String getOrderIdsFormListJson(Response responseFromGetOrder){
		
		JsonObject resp = new JsonParser().parse(responseFromGetOrder.getResponseStr()).getAsJsonObject();	
		JsonArray waitingOrders = resp.get("data").getAsJsonObject().get("waitingOrderVos").getAsJsonArray();
		
		List<OrderIdsForm> orderIdsFormList = new ArrayList<OrderIdsForm>();
		for(JsonElement waitOrder : waitingOrders){
			
			// 获取订单ID 及 UID
			String waitingOrder = waitOrder.getAsJsonObject().get("waitingOrderItems").getAsJsonObject().get("waitingOrderId").getAsString();
			String uid = waitOrder.getAsJsonObject().get("waitingOrderItems").getAsJsonObject().get("uid").getAsString();
			JsonArray orderMenus = waitOrder.getAsJsonObject().get("waitingOrderItems").getAsJsonObject().get("orderMenus").getAsJsonArray();
			
			String foodStatus = waitOrder.getAsJsonObject().get("foodStatus").getAsString();
					
			List<MenuForm> menuFormList = new ArrayList<MenuForm>();
			for(JsonElement menu : orderMenus){
				
				String specId = menu.getAsJsonObject().get("spec").getAsString();
				String makeId = menu.getAsJsonObject().get("make").getAsString();
				String num = menu.getAsJsonObject().get("num").getAsString();
				Integer status = menu.getAsJsonObject().get("status").getAsInt();
				String menuId = menu.getAsJsonObject().get("menuId").getAsString();
				
//				if("" == specId)
//					specId = null;
//				if("" == makeId)
//					makeId = null;
				
				MenuForm menuForm = new MenuForm();
				menuForm.setUid(uid);
				menuForm.setMenuId(menuId);
				menuForm.setMakeId(makeId);
				menuForm.setSpecId(specId);
				menuForm.setNum(num);
				menuForm.setStatus(status);
				
				menuFormList.add(menuForm);
			}
			
			//设置一张订单对应的订单 ID 以及对应的菜
			OrderIdsForm orderIdsForm = new OrderIdsForm();
			orderIdsForm.setId(waitingOrder);
			orderIdsForm.setOrder(menuFormList);
			orderIdsForm.setFoodStatus(foodStatus);
			
			// 将该订单加到订单列表中
			orderIdsFormList.add(orderIdsForm);
		}
		
		return gson.toJson(orderIdsFormList);
	}
	
	
	/**
	 * 转换形成 "card_id=&code_id=&coupon_id=&sign=&gift_forms_string=true" 的字符串
	 * @param httpBody
	 * @return
	 */
	public static String getHTTPBodyWithForm(Map<String, String> httpBody){
		
		StringBuilder body = new StringBuilder();
		
		for(String key : httpBody.keySet()){
			body.append(key).append("=").append(httpBody.get(key)).append("&");
		}
		
		return body.toString();
	}
	
	
	/**
	 * 构建 日常测试店中的"套菜2" 的 CartIncludeSuitForm 类对象 形式数据
	 * @return
	 */
	public static CartIncludeSuitForm getCartSuitWithChild(int numForHotSteak, int numForCabage, String uid){
		
		if((numForHotSteak + numForCabage) != 3){
			logger.info("the total number is not equal to 3");
			return null;
		}
			
		// hotSteak cartSuit
		CartIncludeSuitForm hotSteakSuit = new CartIncludeSuitForm();
		hotSteakSuit.setNum((double)numForHotSteak);
		hotSteakSuit.setSuitMenuDetailId("999292495828239201582e5fd4d50117");
		
		hotSteakSuit.setMakeId("");
		hotSteakSuit.setSpecDetailId("");
		hotSteakSuit.setMenuName("热牛排");
		
		hotSteakSuit.setMenuId(CommonConstants.hotSteakMenuId);
		hotSteakSuit.setKindType(1);
		hotSteakSuit.setChildCartVos(null);
		hotSteakSuit.setUid(uid);
		
		// cabbage cartSuit
		CartIncludeSuitForm cabageCartSuit = new CartIncludeSuitForm();
		cabageCartSuit.setNum((double)numForCabage);
		cabageCartSuit.setSuitMenuDetailId("999292495828239201582e5fd4d50117");
		
		cabageCartSuit.setMakeId("");
		cabageCartSuit.setSpecDetailId("9992924957f00a320157f53f02e0001a");
		cabageCartSuit.setMenuName("干锅包心菜");
		
		cabageCartSuit.setMenuId(CommonConstants.cabbageMenuId);
		cabageCartSuit.setChildCartVos(null);
		cabageCartSuit.setKindType(1);
		cabageCartSuit.setUid(uid);
		
		// add to cartSuit list 
		List<CartIncludeSuitForm> cartSuitFormList = new ArrayList<CartIncludeSuitForm>();
		cartSuitFormList.add(hotSteakSuit);
		cartSuitFormList.add(cabageCartSuit);
		
		// parent cartSuit
		CartIncludeSuitForm cartSuit = new CartIncludeSuitForm();
		cartSuit.setNum((double)1);
		cartSuit.setKindType(2);
		
		cartSuit.setMakeId("");
		cartSuit.setSpecDetailId("");
		cartSuit.setMenuName("套菜2");
		
		cartSuit.setMenuId(CommonConstants.suit2MenuId);
		cartSuit.setChildCartVos(cartSuitFormList);
		cartSuit.setUid(uid);
		
		return cartSuit;
	}
	

	/**
	 * 构建 日常测试店中的"套菜3" 的 CartIncludeSuitForm 类对象 形式数据
	 * @return
	 */
	public static CartIncludeSuitForm getCartSuitWithChild2(int numOne, int numTwo, String uid){
		
		if((numOne + numTwo) != 3){
			logger.info("the total number is not equal to 3");
			return null;
		}
			
		// hotSteak cartSuit
		CartIncludeSuitForm hotSteakSuit = new CartIncludeSuitForm();
		hotSteakSuit.setNum((double)numOne);
		hotSteakSuit.setSuitMenuDetailId("9992924958282392015843217637019c");
		
		hotSteakSuit.setMakeId("");
		hotSteakSuit.setSpecDetailId("");
		hotSteakSuit.setMenuName("面酱黄瓜");
		
		hotSteakSuit.setMenuId(CommonConstants.cucumberMenuId);
		hotSteakSuit.setKindType(1);
		hotSteakSuit.setChildCartVos(null);
		hotSteakSuit.setUid(uid);
		
		
		// cabbage cartSuit
		CartIncludeSuitForm cabageCartSuit = new CartIncludeSuitForm();
		cabageCartSuit.setNum((double)numTwo);
		cabageCartSuit.setSuitMenuDetailId("9992924958282392015843217637019c");
		
		cabageCartSuit.setMakeId("");
		cabageCartSuit.setSpecDetailId("");
		cabageCartSuit.setMenuName("可乐");
		
		cabageCartSuit.setMenuId(CommonConstants.cokeMenuId);
		cabageCartSuit.setChildCartVos(null);
		cabageCartSuit.setKindType(1);
		cabageCartSuit.setUid(uid);
		
		
		// add to cartSuit list 
		List<CartIncludeSuitForm> cartSuitFormList = new ArrayList<CartIncludeSuitForm>();
		cartSuitFormList.add(hotSteakSuit);
		cartSuitFormList.add(cabageCartSuit);
		
		// parent cartSuit
		CartIncludeSuitForm cartSuit = new CartIncludeSuitForm();
		cartSuit.setNum((double)1);
		cartSuit.setKindType(2);
		
		cartSuit.setMakeId("");
		cartSuit.setSpecDetailId("");
		cartSuit.setMenuName("套菜3");
		
		cartSuit.setMenuId(CommonConstants.suit3MenuId);
		cartSuit.setChildCartVos(cartSuitFormList);
		cartSuit.setUid(uid);
		
		
		return cartSuit;
	}
	
	
	/**
	 * 构建 日常测试店中的"东坡肉" 的 CartIncludeSuitForm 类对象 形式数据
	 * @return
	 */
	public static CartIncludeSuitForm getCartSuitWithIntegrations(int numberForPeanutSuit, int numberForPorkSuit){
		
		
			
		// hotSteak cartSuit
		CartIncludeSuitForm peanutSuit = new CartIncludeSuitForm();
		
		peanutSuit.setNum((double)numberForPeanutSuit);
		peanutSuit.setKindMenuId(CommonConstants.kindMenuIdForPeanut);
		peanutSuit.setKindMenuName("小菜");
		
		peanutSuit.setMakeId("");
		peanutSuit.setSpecDetailId("");
		peanutSuit.setMenuName("花生");
		
		peanutSuit.setMenuId(CommonConstants.peanutMenuId);
		peanutSuit.setKindType(5);
		peanutSuit.setChildCartVos(null);
		peanutSuit.setUid(CommonConstants.UID);
		
		
		
		// add to cartSuit list 
		List<CartIncludeSuitForm> cartSuitFormList = new ArrayList<CartIncludeSuitForm>();
		cartSuitFormList.add(peanutSuit);
		
		// parent cartSuit
		CartIncludeSuitForm cartSuit = new CartIncludeSuitForm();
		cartSuit.setNum((double)numberForPorkSuit);
		cartSuit.setKindType(1);
		
		cartSuit.setMakeId(CommonConstants.DongpoPorkMakeId);
		cartSuit.setSpecDetailId(CommonConstants.DongpoPorkSpecDetailId);
		cartSuit.setMenuName("东坡肉");
		
		cartSuit.setMenuId(CommonConstants.DongpoPorkMenuId);
		cartSuit.setChildCartVos(cartSuitFormList);
		cartSuit.setUid(CommonConstants.UID);
		
		return cartSuit;
	}
}
