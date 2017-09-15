package com.dfire.qa.meal.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;








import com.dfire.qa.meal.bean.Response;
import com.dfire.qa.meal.vo.boss.TakeOut;
import com.dfire.qa.meal.vo.boss.TakeOutDeliveryMan;
import com.dfire.qa.meal.vo.boss.TakeOutTime;
import com.dfire.qa.meal.vo.cart.CartIncludeSuitForm;
import com.dfire.qa.meal.vo.cart.CartsCreate;
import com.dfire.qa.meal.vo.menu.MenuForm;
import com.dfire.qa.meal.vo.order.OrderIdsForm;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class BeanProvider {
	
	private static Gson gson = new Gson();

	
	/**
	 * people 一般为 6, isPrePay 一般为 false
	 */
	public static Map<String, String> getOtherParameter(String people, String menuId, String isPrePay){
		
		Map<String, String> otherParameter = new HashMap<String, String>();
		otherParameter.put("people", people);
		otherParameter.put("memo", "");
		otherParameter.put("oldPeople", "");
		
		otherParameter.put("repeat", "false");
		otherParameter.put("recommend", "false");
		
		otherParameter.put("page", "");
		otherParameter.put("pageSize", "");
		otherParameter.put("isPreCart", "false");
		
		otherParameter.put("menuId", menuId);
		otherParameter.put("menuIdList", ""); // 该参数为空
		otherParameter.put("memo_labels", "");
		
		// 用于提交订单
		otherParameter.put("isPrePay", isPrePay);
		otherParameter.put("cartTime", Long.toString(System.currentTimeMillis()));
		
		return otherParameter;
		
	}
	
	
	/**
	 * 获取 掌柜端--客户端设置--外卖配送人员设置 参数
	 * @return
	 */
	public static TakeOutDeliveryMan getTakeOutDeliveryManBean(){
		
		TakeOutDeliveryMan takeOutDeliveryMan = new TakeOutDeliveryMan();
		
		takeOutDeliveryMan.setPhone("15158119632");
		takeOutDeliveryMan.setIdCard("111111111111111111");
		takeOutDeliveryMan.setLastVer(0);
		
		takeOutDeliveryMan.setOpTime((long)0);
		takeOutDeliveryMan.setIsValid(0);
		takeOutDeliveryMan.setSex(1);
		
		takeOutDeliveryMan.setCreateTime((long)0);
		takeOutDeliveryMan.setName("abc");

		return takeOutDeliveryMan;
	}
	
	
	
	/**
	 * 获取 掌柜端--客户端设置--外卖配送时间设置 参数
	 * @return
	 */
	public static TakeOutTime getTakeOutTimeBean(){
		
		TakeOutTime takeOutTime = new TakeOutTime();
		
		takeOutTime.setOpTime((long)0);
		takeOutTime.setBeginTime((long)600);
		takeOutTime.setEndTime((long)0);
		
		takeOutTime.setLastVer(0);
		takeOutTime.setIsValid(0);
		takeOutTime.setNum(100);
		
		takeOutTime.setCreateTime((long)0);
		

		
		
		return takeOutTime;
	}
	
	
	
	/**
	 * 获取 掌柜端--客户端设置--外卖设置 参数
	 * @return
	 */
	public static TakeOut getTakeOutSet(){
		
		TakeOut takeOut = new TakeOut();
		takeOut.setOutRange("五公里以内");
		takeOut.setOpTime(0);
		takeOut.setReserveTomorrowFlag(0);
		
		takeOut.setLatitude("30.12");
		takeOut.setIsValid(0);
		takeOut.setOutFee(0);
		
		takeOut.setMapAddress("杭州滨江");
		takeOut.setPickupFlag(0);
		takeOut.setOutFeeMode(3);
		
		takeOut.setOrderAheadOfTime((long)60);
		takeOut.setDeliveryTime((long)60);
		takeOut.setLongitude("120.13");
		
		takeOut.setCashSupVer2Flag(0);
		takeOut.setStartPrice(1500);
		takeOut.setLastVer(0);
		
		takeOut.setDeliveryMenList(new ArrayList<String>());
		takeOut.setTakeOutTimeList(new ArrayList<String>());
		takeOut.setCreateTime((long)0);
		
		takeOut.setIsOut(1);
		takeOut.setMaxRange(0);
		
		return takeOut;
	}
	
	
	/**
	 * 用于创建购物车时传入的  bean 
	 */
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
	 * the uid name is began with entityId
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
				
				String specId = menu.getAsJsonObject().get("specId").getAsString();
				String makeId = menu.getAsJsonObject().get("makeId").getAsString();
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
	
	
}
