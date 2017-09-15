package com.dfire.testcase.function.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.dfire.wechat.util.BeanProvider;
import com.dfire.wechat.util.CartIncludeSuitForm;
import com.dfire.wechat.util.CommonConstants;

public class OrderForNoPrePaySeatTestData {
	
	
	private static String uid = CommonConstants.UID;
	
	@DataProvider(name = "orderForOneCustomer")
	public static Object [][] orderForOneCustomer(){
		
		
		String description1 = "用参人数为 6, 选择单个普通菜 '水果沙拉' 加入购物车";
		
		// 菜品的规格
		double menuNumber1 = 1;
		int kindType = 1;
		String makeId = ""; 
		String SpecDetailed = "";
		String menuName = "水果沙拉";
		String menuId = CommonConstants.menuId;
		
		CartIncludeSuitForm cartIncludeSuitForm = BeanProvider.getCartSuit(menuNumber1, kindType, makeId, SpecDetailed, menuName, menuId, uid);
		List<CartIncludeSuitForm> cartList = new ArrayList<CartIncludeSuitForm>();
		cartList.add(cartIncludeSuitForm);
		
		return new Object[][]{
				
				{description1, cartList},

		};
	}

	
	
	@DataProvider(name = "orderForMultiCustomer")
	public static Object [][] orderForMultiCustomer(){
		
						
		///////////////////////  菜品资源    ///////////////////////////////////////////////////////////////////////////////////////////////
				
		// 普通菜----'水果沙拉'
		double menuNumber1 = 1;
		int kindType = 1;
		String makeId = ""; 
		String SpecDetailed = "";
		String menuName = "水果沙拉";
		String menuId = CommonConstants.menuId;
		
		CartIncludeSuitForm cartSuit1 = BeanProvider.getCartSuit(menuNumber1, kindType, makeId, SpecDetailed, menuName, menuId, CommonConstants.UID);
		CartIncludeSuitForm cartSuit2 = BeanProvider.getCartSuit(menuNumber1, kindType, makeId, SpecDetailed, menuName, menuId, CommonConstants.UIDOther);
		
		
		// 普通菜----'干锅包心菜'
		
		double menuNumber2 = 1;
		int kindType2 = 1;
		String makeId2 = "";
		String SpecDetailed2 = "9992924957f00a320157f53f02e0001a";
		String menuName2 = "干锅包心菜";
		String menuId2 = CommonConstants.cabbageMenuId;
		String suitMenuId2 = "999292495828239201582e5fd4d50117";
		
		CartIncludeSuitForm cabageCartSuit1 = BeanProvider.getCartSuitWithDetail(menuNumber2, kindType2, makeId2, SpecDetailed2, menuName2, menuId2, CommonConstants.UID, suitMenuId2);
		CartIncludeSuitForm cabageCartSuit2 = BeanProvider.getCartSuitWithDetail(menuNumber2, kindType2, makeId2, SpecDetailed2, menuName2, menuId2, CommonConstants.UIDOther, suitMenuId2);
		
		
		
		
		// 套菜----'套菜2'(包含普通菜  '热牛排' 和 普通菜 '干锅包心菜')
		int numForHotSteak = 2;
		int numForCabage = 1;
		
		CartIncludeSuitForm cartSuitWithChild1 = BeanProvider.getCartSuitWithChild(numForHotSteak, numForCabage, CommonConstants.UID);		
		CartIncludeSuitForm cartSuitWithChild2 = BeanProvider.getCartSuitWithChild2(numForHotSteak, numForCabage, CommonConstants.UID);
		
		CartIncludeSuitForm cartSuitWithChild11 = BeanProvider.getCartSuitWithChild(numForHotSteak, numForCabage, CommonConstants.UIDOther);		
		CartIncludeSuitForm cartSuitWithChild22 = BeanProvider.getCartSuitWithChild2(numForHotSteak, numForCabage, CommonConstants.UIDOther);
		
		// 东坡肉
		int numberForPeanut = 1;
		int numberForPork = 1;
		CartIncludeSuitForm cartSuitWithIntegrations = BeanProvider.getCartSuitWithIntegrations(numberForPeanut, numberForPork);
		
		
		/////////////////////// 用参人数为 6, 用户 A 选择单个普通菜, 用户 B 选择单个普通菜加入购物车       /////////////////////////////////////////////////////////
		
		String description1 = "用参人数为 6, 用户 A 选择单个'水果沙拉'加入购物车， 用户 B 选择单个'面酱黄瓜'加入购物车";
		
		
		List<CartIncludeSuitForm> cartSuitList11 = new ArrayList<CartIncludeSuitForm>();
		cartSuitList11.add(cartSuit1);
		
		List<CartIncludeSuitForm> cartSuitList12 = new ArrayList<CartIncludeSuitForm>();
		cartSuitList12.add(cabageCartSuit2);
		
		List<CartIncludeSuitForm> cartSuitList13 = new ArrayList<CartIncludeSuitForm>();
		cartSuitList13.add(cartSuit1);
		cartSuitList13.add(cabageCartSuit2);

		
		return new Object[][]{
				
				{description1, cartSuitList11, cartSuitList12, cartSuitList13, false},
				{description1, cartSuitList11, cartSuitList12, cartSuitList13, true},

		};
	}

	
	
	
	@DataProvider(name = "orderApprovalTest")
	public static Object [][] orderApprovalTest(){
		
		
		String description1 = "用参人数为 6, 选择单个普通菜加入购物车";
		
		// 菜品的规格
		double menuNumber1 = 1;
		int kindType = 1;
		String makeId = ""; 
		String SpecDetailed = "";
		String menuName = "水果沙拉";
		String menuId = CommonConstants.menuId;
		String uid = CommonConstants.UID;
		
		CartIncludeSuitForm cartIncludeSuitForm = BeanProvider.getCartSuit(menuNumber1, kindType, makeId, SpecDetailed, menuName, menuId, uid);
		List<CartIncludeSuitForm> cartList = new ArrayList<CartIncludeSuitForm>();
		cartList.add(cartIncludeSuitForm);
		
		return new Object[][]{
				{description1, cartList},

		};
	}


}
