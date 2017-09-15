package com.dfire.testcase.function.pay;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.dfire.testcase.bean.PayType;
import com.dfire.testcase.bean.TradeBill;
import com.dfire.wechat.util.BeanProvider;
import com.dfire.wechat.util.CartIncludeSuitForm;
import com.dfire.wechat.util.CommonConstants;
import com.google.gson.Gson;

public class PayTestData {
	
	private static String entityId = "99927792";
	private static String seatCode = "B2";
	
	private static String token = "54452c7d6322ff78056424b22666bd1b";
	private static String menuId = CommonConstants.menuId;
	
	private static String peopleCount = "12";
	private static Gson gson = new Gson();
	
	@DataProvider(name = "isCreateWaiterCommentsTest")
	public static Object [][] isCreateWaiterCommentsTest(){
		
		String description1 = "description: token 正常, entityId 正常, people 正常, memo 正常";
		
       
		Map<String, String> query1 = new HashMap<String, String>();
		query1.put("xtoken", token);
		query1.put("entity_id", entityId);
		query1.put("order_ids[]", "");	
		
		return new Object[][]{
				{description1, query1, 200, 1, 200},

		};
	}
	
	
	@DataProvider(name = "getActivitySwitchTest")
	public static Object [][] getActivitySwitchTest(){
		
		String description1 = "description: token 正常, entityId 正常, people 正常, memo 正常";
		
       
		Map<String, String> query1 = new HashMap<String, String>();
		query1.put("xtoken", token);
		query1.put("entity_id", entityId);
		query1.put("seat_code", seatCode);	
		query1.put("order_id", "");
		
		List<String> menuIdList = new ArrayList<String>();
		menuIdList.add(menuId);
		
		return new Object[][]{
				{description1, query1, menuIdList, 200, 1, 200},

		};
	}
	
	@DataProvider(name = "getTradeBillTest")
	public static Object [][] getTradeBillTest(){
		
		String description1 = "description: token 正常, entityId 正常, people 正常, memo 正常";
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
		String cartSuitList = BeanProvider.getCartSuitListJson(listCarts);
       
		Map<String, String> query1 = new HashMap<String, String>();
		query1.put("xtoken", token);
		query1.put("entity_id", entityId);
		query1.put("seat_code", seatCode);
		
		query1.put("order_id", "");		
		query1.put("flag", "true");
		query1.put("card_id", "");
		
		
		Map<String,String> httpBody = new HashMap<String, String>();

		httpBody.put("code_id", "");		
		httpBody.put("coupon_id", "");
		httpBody.put("sign", "");
		
		httpBody.put("gift_forms_string", "true");		
		httpBody.put("promotion_customer_id", "");
		httpBody.put("promotion_id", "");
		
		httpBody.put("waiting_order_id", "");
		httpBody.put("cart_forms_string", cartSuitList);
		
		return new Object[][]{
				{description1, query1, httpBody, 200, 1, 200},

		};
	}
	
	
	@DataProvider(name = "confirmPrePayTest")
	public static Object [][] confirmPrePayTest(){
		
		String description1 = "description: token 正常, entityId 正常, people 正常, memo 正常";
		
       
		Map<String, String> query1 = new HashMap<String, String>();
		query1.put("xtoken", token);
		query1.put("entity_id", entityId);
		query1.put("seat_code", seatCode);
		
		query1.put("order_id", "");		
		query1.put("people_count", "");
		query1.put("memo", "");
		
		query1.put("card_id", "");
		
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
		String cartSuitList = BeanProvider.getCartSuitListJson(listCarts);
		
		
		// construct the body of trade bill
		TradeBill tradeBill = new TradeBill();
		tradeBill.setEntity_id(entityId);
		tradeBill.setSeat_code(seatCode);
		tradeBill.setOrder_id("");
		
		tradeBill.setFlag("true");
		tradeBill.setCard_id("");
		tradeBill.setCode_id("");
		
		tradeBill.setCoupon_id("");
		tradeBill.setSign("");
		tradeBill.setGift_forms_string("true");
		
		tradeBill.setPromotion_customer_id("");
		tradeBill.setPromotion_id("");
		tradeBill.setWaiting_order_id("");
		tradeBill.setCart_forms_string(cartSuitList);
		
		return new Object[][]{
				{description1, query1, cartSuitList, tradeBill, 200, 1, 200},

		};
	}
	
	
	

	
	/**
	 * 该方法部分数据还未填写
	 * @return
	 */
	@DataProvider(name = "getPayTypeTest")
	public static Object [][] getPayTypeTest(){
		
		
		String description1 = "description: token 正常, entityId 正常, people 正常, memo 正常";		
       
		Map<String, String> query1 = new HashMap<String, String>();
		query1.put("xtoken", token);
		
		PayType payType = new PayType();
		payType.setEntity_id(entityId);
		payType.setSeat_code(seatCode);
		payType.setCard_id("");
		
		payType.setCoupon_id("");
		payType.setCode_id("");
		payType.setShop_id("");  // 该参数需要填写
		
		payType.setSign("");
		payType.setPromotion_id("");
		payType.setPromotion_customer_id("");
		
		payType.setOrder_id("");
		payType.setWaiting_order_id(""); // 该参数需要填写
		payType.setTotal_fee("0");
		
		payType.setOrigin_fee("0");  // 所选菜的价格
		payType.setService_fee("");
		payType.setDiscount_fee("");
		
		payType.setNeed_fee("0");  // 所选菜的价格
		payType.setPaid_fee("");
		payType.setDeduct_fee("");
		
		payType.setPromotion_fee("");
		payType.setCsrf_token("");  //  该参数需要填写
		payType.setGift_forms_string("");
		
		
		String httpBodyForGetPayType = BeanProvider.getHTTPBodyWithForm(payType.mapBean());
		
		
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
		String cartSuitList = BeanProvider.getCartSuitListJson(listCarts);
		
		
		// construct the body of trade bill
		TradeBill tradeBill = new TradeBill();
		tradeBill.setEntity_id(entityId);
		tradeBill.setSeat_code(seatCode);
		tradeBill.setOrder_id("");
		
		tradeBill.setFlag("true");
		tradeBill.setCard_id("");
		tradeBill.setCode_id("");
		
		tradeBill.setCoupon_id("");
		tradeBill.setSign("");
		tradeBill.setGift_forms_string("true");
		
		tradeBill.setPromotion_customer_id("");
		tradeBill.setPromotion_id("");
		tradeBill.setWaiting_order_id("");
		tradeBill.setCart_forms_string(cartSuitList);		
				
		
		return new Object[][]{
				{description1, query1, httpBodyForGetPayType, cartSuitList, tradeBill, 200, 1, 200},

		};
	}
	
	
	
}
