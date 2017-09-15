package com.dfire.testcase.function.order;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.dfire.wechat.util.BeanProvider;
import com.dfire.wechat.util.CartIncludeSuitForm;
import com.dfire.wechat.util.CommonConstants;
import com.google.gson.Gson;

public class OrderForNoPrePayShopTestData {
	
	private static Gson gson = new Gson();
	private static String uid = CommonConstants.UID;
	
	@DataProvider(name = "orderForOneCustomer")
	public static Object [][] orderForOneCustomer(){
		
		//////////////////////   基础菜品数据          //////////////////////////////////////////////////
		// 一份水果沙拉, 普通菜
		double menuNumber1 = 1;
		double menuNumber11 = 6;
		int kindType = 1;
		String makeId = ""; 
		String SpecDetailed = "";
		String menuName = "水果沙拉";
		String menuId = CommonConstants.menuId;
		String uid = CommonConstants.UID;
		
		CartIncludeSuitForm cartSuit1 = BeanProvider.getCartSuit(menuNumber1, kindType, makeId, SpecDetailed, menuName, menuId, uid);
		CartIncludeSuitForm cartSuit11 = BeanProvider.getCartSuit(menuNumber11, kindType, makeId, SpecDetailed, menuName, menuId, uid);
		
		// 六份水果沙拉, 普通菜
		double menuNumber2 = 6;
		CartIncludeSuitForm cartSuit2 = BeanProvider.getCartSuit(menuNumber2, kindType, makeId, SpecDetailed, menuName, menuId, uid);
		
		
		// 一份干锅包心菜, 普通菜
		CartIncludeSuitForm cabageCartSuit = new CartIncludeSuitForm();
		cabageCartSuit.setNum((double)1);
		cabageCartSuit.setSuitMenuDetailId("999292495828239201582e5fd4d50117");
		
		cabageCartSuit.setMakeId("");
		cabageCartSuit.setSpecDetailId("9992924957f00a320157f53f02e0001a");
		cabageCartSuit.setMenuName("干锅包心菜");
		
		cabageCartSuit.setMenuId(CommonConstants.cabbageMenuId);
		cabageCartSuit.setChildCartVos(null);
		cabageCartSuit.setKindType(1);
		
		cabageCartSuit.setUid(CommonConstants.UID);
		
		
		int numForHotSteak = 2;
		int numForCabage = 1;
		// 套菜 2
		CartIncludeSuitForm cartSuitWithChildOne = BeanProvider.getCartSuitWithChild(numForHotSteak, numForCabage, uid);
		// 套菜 3
		CartIncludeSuitForm cartSuitWithChildTwo = BeanProvider.getCartSuitWithChild2(numForHotSteak, numForCabage, uid);
		
		
		
		
		///////////////////////  非预付款扫店码, 选择单个普通菜加入购物车, 之后扫桌码, 再加入 6 个普通菜       //////////////////////////////////////////////////////////////////////////
		
		String description1 = " 非预付款扫店码, 选择单个普通菜加入购物车, 之后扫桌码, 再加入 6 个普通菜";
				
		List<CartIncludeSuitForm> cartSuitListForShop1 = new ArrayList<CartIncludeSuitForm>();
		cartSuitListForShop1.add(cartSuit1);
		
		List<CartIncludeSuitForm> cartSuitListForSeat1 = new ArrayList<CartIncludeSuitForm>();
		cartSuitListForSeat1.add(cartSuit2);
		
		// 同样的菜会进行覆盖
		List<CartIncludeSuitForm> cartSuitListForTotal1 = new ArrayList<CartIncludeSuitForm>();
//		cartSuitListForTotal1.add(cartSuit1);
		cartSuitListForTotal1.add(cartSuit2);
		
		
        ///////////////////////  非预付款扫店码, 选择 6 个普通菜加入购物车, 之后扫桌码, 再加入 1 个普通菜       //////////////////////////////////////////////////////////////////////////
		
		String description2 = " 非预付款扫店码, 选择 6 个普通菜加入购物车, 之后扫桌码, 再加入 1 个普通菜";
		
		List<CartIncludeSuitForm> cartSuitListForShop2 = new ArrayList<CartIncludeSuitForm>();
		cartSuitListForShop2.add(cartSuit11);
		
		List<CartIncludeSuitForm> cartSuitListForSeat2 = new ArrayList<CartIncludeSuitForm>();
		cartSuitListForSeat2.add(cabageCartSuit);
		
		List<CartIncludeSuitForm> cartSuitListForTotal2 = new ArrayList<CartIncludeSuitForm>();
		cartSuitListForTotal2.add(cartSuit11);
		cartSuitListForTotal2.add(cabageCartSuit);
		
		
				
		
		///////////////////////  非预付款扫店码, 选择 单个套菜加入购物车, 之后扫桌码, 再加入 单个普通菜       //////////////////////////////////////////////////////////////////////////
		
		String description3 = "非预付款扫店码, 选择 单个套菜加入购物车, 之后扫桌码, 再加入 单个普通菜";
		
		List<CartIncludeSuitForm> cartSuitListForShop3 = new ArrayList<CartIncludeSuitForm>();
		cartSuitListForShop3.add(cartSuitWithChildOne);
		
		List<CartIncludeSuitForm> cartSuitListForSeat3 = new ArrayList<CartIncludeSuitForm>();
		cartSuitListForSeat3.add(cartSuit1);
		
		List<CartIncludeSuitForm> cartSuitListForTotal3 = new ArrayList<CartIncludeSuitForm>();
		cartSuitListForTotal3.add(cartSuitWithChildOne);
		cartSuitListForTotal3.add(cartSuit1);
		
		
		
		///////////////////////  非预付款扫店码, 选择 2 个套菜加入购物车, 之后扫桌码, 再加入 单个普通菜       //////////////////////////////////////////////////////////////////////////
		
		String description4 = "非预付款扫店码, 选择 2 个套菜加入购物车, 之后扫桌码, 再加入 单个普通菜";
		
		List<CartIncludeSuitForm> cartSuitListForShop4 = new ArrayList<CartIncludeSuitForm>();
		cartSuitListForShop4.add(cartSuitWithChildOne);
		cartSuitListForShop4.add(cartSuitWithChildTwo);
		
		List<CartIncludeSuitForm> cartSuitListForSeat4 = new ArrayList<CartIncludeSuitForm>();
		cartSuitListForSeat4.add(cartSuit1);
		
		List<CartIncludeSuitForm> cartSuitListForTotal4 = new ArrayList<CartIncludeSuitForm>();
		cartSuitListForTotal4.add(cartSuitWithChildOne);
		cartSuitListForTotal4.add(cartSuitWithChildTwo);
		cartSuitListForTotal4.add(cartSuit1);
		
		
		
		///////////////////////  非预付款扫店码, 选择 2 个普通菜加入购物车, 之后扫桌码, 再加入 2 个套菜       //////////////////////////////////////////////////////////////////////////
				
		String description5 = "非预付款扫店码, 选择 2 个普通菜加入购物车, 之后扫桌码, 再加入 2 个套菜 ";
		
		List<CartIncludeSuitForm> cartSuitListForShop5 = new ArrayList<CartIncludeSuitForm>();
		cartSuitListForShop5.add(cartSuit1);
		cartSuitListForShop5.add(cabageCartSuit);
		
		List<CartIncludeSuitForm> cartSuitListForSeat5 = new ArrayList<CartIncludeSuitForm>();
		cartSuitListForSeat5.add(cartSuitWithChildOne);
		cartSuitListForSeat5.add(cartSuitWithChildTwo);
		
		List<CartIncludeSuitForm> cartSuitListForTotal5 = new ArrayList<CartIncludeSuitForm>();
		cartSuitListForTotal5.add(cartSuit1);
		cartSuitListForTotal5.add(cabageCartSuit);
		cartSuitListForTotal5.add(cartSuitWithChildOne);
		cartSuitListForTotal5.add(cartSuitWithChildTwo);

		
		///////////////////////  非预付款扫店码, 选择 单个普通菜加入购物车, 之后扫桌码, 再加入 单个套菜       //////////////////////////////////////////////////////////////////////////
				
		String description6 = "非预付款扫店码, 选择 单个普通菜加入购物车, 之后扫桌码, 再加入 单个套菜";
		
		List<CartIncludeSuitForm> cartSuitListForShop6 = new ArrayList<CartIncludeSuitForm>();
		cartSuitListForShop6.add(cartSuit1);
		
		List<CartIncludeSuitForm> cartSuitListForSeat6 = new ArrayList<CartIncludeSuitForm>();
		cartSuitListForSeat6.add(cartSuitWithChildOne);
		
		List<CartIncludeSuitForm> cartSuitListForTotal6 = new ArrayList<CartIncludeSuitForm>();
		cartSuitListForTotal6.add(cartSuit1);
		cartSuitListForTotal6.add(cartSuitWithChildOne);
		
		
		
		return new Object[][]{
				
				{description1, cartSuitListForShop1, cartSuitListForSeat1, cartSuitListForTotal1},
//				{description2, cartSuitListForShop2, cartSuitListForSeat2, cartSuitListForTotal2},
//				{description3, cartSuitListForShop3, cartSuitListForSeat3, cartSuitListForTotal3},
//				
//				{description4, cartSuitListForShop4, cartSuitListForSeat4, cartSuitListForTotal4},
//				{description5, cartSuitListForShop5, cartSuitListForSeat5, cartSuitListForTotal5},
//				{description6, cartSuitListForShop6, cartSuitListForSeat6, cartSuitListForTotal6)},
				

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
		
		List<CartIncludeSuitForm> cartSuitList10 = new ArrayList<CartIncludeSuitForm>();
		cartSuitList10.add(cartSuitWithChild1);
		
		List<CartIncludeSuitForm> cartSuitList11 = new ArrayList<CartIncludeSuitForm>();
		cartSuitList11.add(cartSuit1);
		
		List<CartIncludeSuitForm> cartSuitList12 = new ArrayList<CartIncludeSuitForm>();
		cartSuitList12.add(cabageCartSuit2);
		
		List<CartIncludeSuitForm> cartSuitList13 = new ArrayList<CartIncludeSuitForm>();
		cartSuitList13.add(cartSuitWithChild1);
		cartSuitList13.add(cartSuit1);
		cartSuitList13.add(cabageCartSuit2);

		
		return new Object[][]{
				
				{description1, cartSuitList10, cartSuitList11, cartSuitList12, cartSuitList13, false},
				{description1, cartSuitList10, cartSuitList11, cartSuitList12, cartSuitList13, true},

		};
	}
	
	
	
	

}
