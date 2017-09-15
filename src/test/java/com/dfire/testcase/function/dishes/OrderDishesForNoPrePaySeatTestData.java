package com.dfire.testcase.function.dishes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.dfire.testcase.function.boss.ForceConfig;
import com.dfire.testcase.function.util.base.WechatBaseUtil;
import com.dfire.wechat.util.BeanProvider;
import com.dfire.wechat.util.CartIncludeSuitForm;
import com.dfire.wechat.util.CommonConstants;
import com.dfire.wechat.util.HttpRequestEx;
import com.google.gson.Gson;

public class OrderDishesForNoPrePaySeatTestData {
	
	private static String entityId = CommonConstants.entityId;
	private static String seatCode = CommonConstants.seatCode;
	
	private static String token;
	private static Gson gson = new Gson();
	private static boolean https = CommonConstants.HTTPS;
	
	private static String uid = CommonConstants.UID;
	
	static{
		
		HttpRequestEx httpRequest = new HttpRequestEx(CommonConstants.DEFAULT_HOST, https);
		token = WechatBaseUtil.getToken(httpRequest, CommonConstants.entityId, CommonConstants.unionID);
		httpRequest.ShutDown();
		
	}
	
	
	@DataProvider(name = "modifyPeopleTest")
	public static Object [][] modifyPeopleTest(){
		
		String description1 = "description: token 正常, entityId 正常, people 正常, memo 正常";
		Map<String, String> query1 = new HashMap<String, String>();
		query1.put("xtoken", token);
		query1.put("entity_id", entityId);
		query1.put("seat_code", seatCode);	
		query1.put("people", "12");
		query1.put("memo", "备注");
		
		return new Object[][]{
				{description1, query1, 200, 1, 200},

		};
	}

	
	@DataProvider(name = "addRequiredItemTest")
	public static Object [][] addRequiredItemTest(){
		
		String description1 = "description: token 正常, entityId 正常, people 正常, memo 正常";
		Map<String, String> query1 = new HashMap<String, String>();
		query1.put("xtoken", token);
		query1.put("entity_id", entityId);
		query1.put("seat_code", seatCode);	
		query1.put("people", "12");
		query1.put("order_id", "");
		
		return new Object[][]{
				{description1, query1, 200, 1, 200},

		};
	}
	
	
	@DataProvider(name = "getRecommendMenusTest")
	public static Object [][] getRecommendMenusTest(){
		
		String description1 = "description: token 正常, entityId 正常, people 正常, memo 正常";
		Map<String, String> query1 = new HashMap<String, String>();
		query1.put("xtoken", token);
		query1.put("entity_id", entityId);
		query1.put("seat_code", seatCode);	
		query1.put("is_pre_cart", "false");
		query1.put("order_id", "");
		
		return new Object[][]{
				{description1, query1, 200, 1, 200},

		};
	}
	
	@DataProvider(name = "getMenuSpecTest")
	public static Object [][] getMenuSpecTest(){
		
		String description1 = "description: token 正常, entityId 正常, people 正常, memo 正常";
		String entityIdForMenuSpec = entityId;
		String menuId = "999277925600e4a301562626dc790272"; // 日常环境 menuId
		
		Map<String, String> query1 = new HashMap<String, String>();
		query1.put("xtoken", token);
		
		return new Object[][]{
				{description1, entityIdForMenuSpec, menuId, query1, 200, 1, 200},

		};
	}
	
	
	@DataProvider(name = "addToCartsTest")
	public static Object [][] addToCartsTest(){
		
		String description1 = "description: token 正常, entityId 正常, people 正常, memo 正常";
		Map<String, String> query1 = new HashMap<String, String>();
		query1.put("xtoken", token);
		query1.put("entity_id", entityId);
		query1.put("seat_code", seatCode);	
		query1.put("order_id", "");
		
		// 菜品的规格
		double menuNumber = 1;
		int kindType = 1;
		String makeId = ""; 
		String SpecDetailed = "";
		String menuName = "水果沙拉";
		String menuId = CommonConstants.menuId;
		String uid = "";
		String cartSuit = BeanProvider.getCartSuitJson(menuNumber, kindType, makeId, SpecDetailed, menuName, menuId, uid);
		
		return new Object[][]{
				{description1, query1, cartSuit, 200, 1, 200},

		};
	}
	
	
	@DataProvider(name = "addDishToCartTest")
	public static Object [][] addDishToCartTest(){
		
		
		///////////////////////  选择单个普通菜, 数目为 1, 加入购物车       //////////////////////////////////////////////////////////////////////////
		
		String description1 = "用参人数为 6, 选择单个普通菜加入购物车";
		
		// 菜品的规格
		double menuNumber1 = 1;
		int kindType = 1;
		String makeId = ""; 
		String SpecDetailed = "";
		String menuName = "水果沙拉";
		String menuId = CommonConstants.menuId;
		String uid = CommonConstants.UID;
		CartIncludeSuitForm cartSuit1 = BeanProvider.getCartSuit(menuNumber1, kindType, makeId, SpecDetailed, menuName, menuId, uid);
		List<CartIncludeSuitForm> cartSuitList1 = new ArrayList<CartIncludeSuitForm>();
		cartSuitList1.add(cartSuit1);
		
		
		
		
		///////////////////////  选择单个普通菜, 数目为6, 加入购物车       //////////////////////////////////////////////////////////////////////////
		
		String description2 = "用参人数为 6, 选择单个普通菜, 起点份数为6, 加入购物车";
			
		// 菜品的规格
		double menuNumber2 = 6;
		CartIncludeSuitForm cartSuit2 = BeanProvider.getCartSuit(menuNumber2, kindType, makeId, SpecDetailed, menuName, menuId, uid);
		List<CartIncludeSuitForm> cartSuitList2 = new ArrayList<CartIncludeSuitForm>();
		cartSuitList2.add(cartSuit2);
		

		
		
		///////////////////////  选择多个普通菜, 加入购物车       //////////////////////////////////////////////////////////////////////////
		
		String description3 = "用参人数为 6, 选择多个普通菜加入购物车";
		
		// cabbage cartSuit
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
		
		List<CartIncludeSuitForm> cartSuitList3 = new ArrayList<CartIncludeSuitForm>();
		cartSuitList3.add(cartSuit1);
		cartSuitList3.add(cabageCartSuit);
		
		
		
		///////////////////////  选择单个套菜, 加入购物车       //////////////////////////////////////////////////////////////////////////
		
		String description4 = "用参人数为 6, 选择单个套菜加入购物车";
		
		int numForHotSteak = 2;
		int numForCabage = 1;
		CartIncludeSuitForm cartSuitWithChild4 = BeanProvider.getCartSuitWithChild(numForHotSteak, numForCabage, uid);
		List<CartIncludeSuitForm> cartSuitList4 = new ArrayList<CartIncludeSuitForm>();
		cartSuitList4.add(cartSuitWithChild4);
		
		///////////////////////  选择多个套菜, 加入购物车       //////////////////////////////////////////////////////////////////////////
		
		String description5 = "用参人数为 6, 选择多个套菜加入购物车";
		
		CartIncludeSuitForm cartSuitWithChildOne = BeanProvider.getCartSuitWithChild(numForHotSteak, numForCabage, uid);				
		CartIncludeSuitForm cartSuitWithChildTwo = BeanProvider.getCartSuitWithChild2(numForHotSteak, numForCabage, uid);
		List<CartIncludeSuitForm> cartSuitList5 = new ArrayList<CartIncludeSuitForm>();
		cartSuitList5.add(cartSuitWithChildOne);
		cartSuitList5.add(cartSuitWithChildTwo);
		
		
		///////////////////////  选择单个普通菜, 以及单个套菜加入购物车       //////////////////////////////////////////////////////////////////////////
		
		String description6 = "选择单个普通菜, 以及单个套菜加入购物";
		List<CartIncludeSuitForm> cartSuitList6 = new ArrayList<CartIncludeSuitForm>();
		cartSuitList6.add(cartSuit1);
		cartSuitList6.add(cartSuitWithChildTwo);
		
		
		///////////////////////  选择多个普通菜及多个套菜加入购物车       //////////////////////////////////////////////////////////////////////////
				
		String description7 = "选择多个普通菜及多个套菜加入购物车 ";
		List<CartIncludeSuitForm> cartSuitList7 = new ArrayList<CartIncludeSuitForm>();
		cartSuitList7.add(cartSuit1);
		cartSuitList7.add(cabageCartSuit);
		cartSuitList7.add(cartSuitWithChildOne);
		cartSuitList7.add(cartSuitWithChildTwo);
		
		
		
		///////////////////////  选择包含规格, 做法, 配料的普通菜加入购物车       //////////////////////////////////////////////////////////////////////////
				
		String description8 = "选择包含规格, 做法, 配料的普通菜加入购物车 ";
		int numberForPeanut = 1;
		int numberForPork = 1;
		CartIncludeSuitForm cartSuitWithIntegrations = BeanProvider.getCartSuitWithIntegrations(numberForPeanut, numberForPork);
		List<CartIncludeSuitForm> cartSuitList8 = new ArrayList<CartIncludeSuitForm>();
		cartSuitList8.add(cartSuitWithIntegrations);

		
		
		return new Object[][]{
				
				{description1, cartSuitList1, gson.toJson(cartSuitList1)},
				{description2, cartSuitList2, gson.toJson(cartSuitList2)},
				{description3, cartSuitList3, gson.toJson(cartSuitList3)},
				
				{description4, cartSuitList4, gson.toJson(cartSuitList4)},
				{description5, cartSuitList5, gson.toJson(cartSuitList5)},
				{description6, cartSuitList6, gson.toJson(cartSuitList6)},
				
				{description7, cartSuitList7, gson.toJson(cartSuitList7)},
				{description8, cartSuitList8, gson.toJson(cartSuitList8)},

		};
	}
	

	
	@DataProvider(name = "addDishToCartTest2")
	public static Object [][] addDishToCartTest2(){
		
		String description = "用参人数为 6, 选择单个普通菜, 加入购物车, 清空购物车, 重新加菜";
		
		// 菜品的规格
		double menuNumber = 1;
		int kindType = 1;
		String makeId = ""; 
		String SpecDetailed = "";
		String menuName = "水果沙拉";
		String menuId = CommonConstants.menuId;
		String uid = CommonConstants.UID;
		CartIncludeSuitForm cartSuit1 = BeanProvider.getCartSuit(menuNumber, kindType, makeId, SpecDetailed, menuName, menuId, uid);
		
		double menuNumber2 = 3;
		CartIncludeSuitForm cartSuit2 = BeanProvider.getCartSuit(menuNumber2, kindType, makeId, SpecDetailed, menuName, menuId, uid);
		
		return new Object[][]{
				{description, cartSuit1, cartSuit2, gson.toJson(cartSuit1), gson.toJson(cartSuit2)},

		};
	}
	
	
	
	@DataProvider(name = "addDishToCartTest3")
	public static Object [][] addDishToCartTest3(){
		
		String description = "用参人数为 6, 选择单个普通菜, 加入购物车, 将该份菜份数减为 0, 重新加菜";
		
		// 菜品的规格
		double menuNumber = 2;
		int kindType = 1;
		String makeId = ""; 
		String SpecDetailed = "";
		String menuName = "水果沙拉";
		String menuId = CommonConstants.menuId;
		String uid = CommonConstants.UID;
		CartIncludeSuitForm cartSuit = BeanProvider.getCartSuit(menuNumber, kindType, makeId, SpecDetailed, menuName, menuId, uid);
		
		// cabbage cartSuit
		CartIncludeSuitForm cabageCartSuit = new CartIncludeSuitForm();
		cabageCartSuit.setUid(CommonConstants.UID);
		cabageCartSuit.setNum((double)1);
		cabageCartSuit.setSuitMenuDetailId("999292495828239201582e5fd4d50117");
		
		cabageCartSuit.setMakeId("");
		cabageCartSuit.setSpecDetailId("9992924957f00a320157f53f02e0001a");
		cabageCartSuit.setMenuName("干锅包心菜");
		
		cabageCartSuit.setMenuId(CommonConstants.cabbageMenuId);
		cabageCartSuit.setChildCartVos(null);
		cabageCartSuit.setKindType(1);
		
		return new Object[][]{
				{description, cartSuit, cabageCartSuit, gson.toJson(cartSuit), gson.toJson(cabageCartSuit)},

		};
	}
	
	
	
	@DataProvider(name = "addDishToCartTest4")
	public static Object [][] addDishToCartTest4(){
		
			
		
		// 菜品1(水果沙拉), 添加的菜
		double menuNumber = 1;
		int kindType = 1;
		String makeId = ""; 
		String SpecDetailed = "";
		String menuName = "水果沙拉";
		String menuId = CommonConstants.menuId;
		String uid = CommonConstants.UID;
		
		CartIncludeSuitForm cartSuit = BeanProvider.getCartSuit(menuNumber, kindType, makeId, SpecDetailed, menuName, menuId, uid);
		List<CartIncludeSuitForm> cartList = new ArrayList<CartIncludeSuitForm>();
		cartList.add(cartSuit);
		
		
		
		
		String uidForForceMenu = "consumer_system";  // 必选菜的 uid 以及 name 均为 "consumer_system"
		String forceMenuId = CommonConstants.cokeMenuId;
		
		///////////////////////////////////////////  必选菜, 指定数量为 1, 普通菜   /////////////////////////////////////////////////////////////////////////////
		
		String description1 = "用参人数为 6, 掌柜端开启添加必选菜(指定数量为 1, 普通菜),选择单个普通菜, 加入购物车, 之后在掌柜端移除必选菜选项";
		
		// 必选菜	
	    Integer forceType1 = 0;   // 强制类型（0:指定数量,1:与用餐人数相同）
	    Integer forceNum1 = 1;    //强制点菜数量（当强制类型为"与用餐人数相同"时无视此数量）
	    String menuIdForForce1 = forceMenuId;   // 菜单 ID 为 "可乐"
		Integer menuType1 = 0;    // 商品类型（0:普通菜,1:套菜,2:加料菜）
		
		
		CartIncludeSuitForm cartSuit1 = BeanProvider.getCartSuit(forceNum1, kindType, makeId, SpecDetailed, "可乐", forceMenuId, uidForForceMenu);
		List<CartIncludeSuitForm> cartList1 = new ArrayList<CartIncludeSuitForm>();
		cartList1.add(cartSuit);
		cartList1.add(cartSuit1);
		
		ForceConfig forceConfig1 = new ForceConfig();
		forceConfig1.setForceType(forceType1);
		forceConfig1.setForceNum(forceNum1);
		forceConfig1.setMenuType(menuType1);
		forceConfig1.setMenuId(menuIdForForce1);
		
		
		
		
		/////////////////////////////////////////////  必选菜, 指定数量为6, 普通菜      ///////////////////////////////////////////////////////////////////////
		
		String description2 = "用参人数为 6, 掌柜端开启添加必选菜(指定数量为6, 普通菜),选择单个普通菜, 加入购物车, 之后在掌柜端移除必选菜选项";
		
		// 必选菜	
	    Integer forceType2 = 0;   // 强制类型（0:指定数量,1:与用餐人数相同）
	    Integer forceNum2 = 6;    //强制点菜数量（当强制类型为"与用餐人数相同"时无视此数量）
	    String menuIdForForce2 = forceMenuId;   // 菜单 ID 为 "可乐"
		Integer menuType2 = 0;    // 商品类型（0:普通菜,1:套菜,2:加料菜）
		
		
		CartIncludeSuitForm cartSuit2 = BeanProvider.getCartSuit(forceNum2, kindType, makeId, SpecDetailed, "可乐", forceMenuId, uidForForceMenu);
		List<CartIncludeSuitForm> cartList2 = new ArrayList<CartIncludeSuitForm>();
		cartList2.add(cartSuit);
		cartList2.add(cartSuit2);
		
		ForceConfig forceConfig2 = new ForceConfig();
		forceConfig2.setForceType(forceType2);
		forceConfig2.setForceNum(forceNum2);
		forceConfig2.setMenuType(menuType2);
		forceConfig2.setMenuId(menuIdForForce2);
		
		
		
		
		///////////////////////////////////////////  必选菜, 指定数量为 与用参人数相同, 用参人数为 6, 普通菜      ///////////////////////////////////////////////////////////////////////
		
		String description3 = "用参人数为 6, 掌柜端开启添加必选菜(指定数量为6, 普通菜 ),选择单个普通菜, 加入购物车, 之后在掌柜端移除必选菜选项";
		
		// 必选菜	
		Integer forceType3 = 1;   // 强制类型（0:指定数量,1:与用餐人数相同）
		Integer forceNum3 = 3;    //强制点菜数量（当强制类型为"与用餐人数相同"时无视此数量）
		String menuIdForForce3 = forceMenuId;   // 菜单 ID 为 "可乐"
		Integer menuType3 = 0;    // 商品类型（0:普通菜,1:套菜,2:加料菜）
		
		Integer forceNumOfPeople = 6;  // 该参数需要与对应测试代码中的人数对应
		
		CartIncludeSuitForm cartSuit3 = BeanProvider.getCartSuit(forceNumOfPeople, kindType, makeId, SpecDetailed, "可乐", forceMenuId, uidForForceMenu);
		List<CartIncludeSuitForm> cartList3 = new ArrayList<CartIncludeSuitForm>();
		cartList3.add(cartSuit);
		cartList3.add(cartSuit3);
		
		ForceConfig forceConfig3 = new ForceConfig();
		forceConfig3.setForceType(forceType3);
		forceConfig3.setForceNum(forceNum3);
		forceConfig3.setMenuType(menuType3);
		forceConfig3.setMenuId(menuIdForForce3);
		
		
		
		return new Object[][]{
				
				{description1, cartList, cartList1, forceConfig1, gson.toJson(cartList), gson.toJson(cartList1), gson.toJson(forceConfig1)},
//				{description2, cartList, cartList2, forceConfig2},
//				{description3, cartList, cartList3, forceConfig3},
				
		};
	}
	
	
	
	@DataProvider(name = "illegalTest")
	public static Object [][] illeagalTest(){
		
		String description1 = "用参人数为 6, 选择普通菜，但是未达起点份数，将其加入购物车";
		
		// 菜品的规格
		double menuNumber = 1;
		int kindType = 1;
		String makeId = ""; 
		String SpecDetailed = "";
		String menuName = "糖酥里脊";
		String menuId = CommonConstants.sugarMeatId;
		String uid = "";
		CartIncludeSuitForm cartSuit1 = BeanProvider.getCartSuit(menuNumber, kindType, makeId, SpecDetailed, menuName, menuId, uid);
		
		return new Object[][]{
				{description1, cartSuit1},

		};
	}
	
	
	
	@DataProvider(name = "addDishToCartWithMultiCustomerTest")
	public static Object [][] addDishToCartWithMultiCustomerTest(){
		
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
		
		
		
		
		/////////////////////// 用参人数为 6, 用参人数为 6, 用户 A 选择单个普通菜, 用户 B 选择单个套菜加入购物车       ///////////////////////////////////////
		
		String description2 = "用参人数为 6, 用户 A 选择单个'水果沙拉'加入购物车， 用户 B 选择单个'套菜2' 加入购物车";
			
		List<CartIncludeSuitForm> cartSuitList21 = new ArrayList<CartIncludeSuitForm>();
		cartSuitList21.add(cartSuit1);
		
		List<CartIncludeSuitForm> cartSuitList22 = new ArrayList<CartIncludeSuitForm>();
		cartSuitList22.add(cartSuitWithChild11);
		
		List<CartIncludeSuitForm> cartSuitList23 = new ArrayList<CartIncludeSuitForm>();
		cartSuitList23.add(cartSuit1);
		cartSuitList23.add(cartSuitWithChild11);
		

		
		
		/////////////////////// 用参人数为 6, 用户 A 选择单个套菜加入购物车， 用户 B 选择单个套菜加入购物车       ///////////////////////////////////////////
		
		String description3 = "用参人数为 6, 用户 A 选择单个'套菜2'加入购物车， 用户 B 选择单个'套菜3' 加入购物车";
		
		List<CartIncludeSuitForm> cartSuitList31 = new ArrayList<CartIncludeSuitForm>();
		cartSuitList31.add(cartSuitWithChild1);
		
		List<CartIncludeSuitForm> cartSuitList32 = new ArrayList<CartIncludeSuitForm>();
		cartSuitList32.add(cartSuitWithChild22);
		
		List<CartIncludeSuitForm> cartSuitList33 = new ArrayList<CartIncludeSuitForm>();
		cartSuitList33.add(cartSuitWithChild1);
		cartSuitList33.add(cartSuitWithChild22);
		
		
		
		/////////////////////// 用参人数为 6, 用户 A 选择 多份普通菜加入购物车,  用户 B 选择 多份套菜入购物车       ///////////////////////////////////////
		
		String description4 = "用参人数为 6, 用户 A 选择 '水果沙拉' 以及 '干锅包心菜' 加入购物车,  用户 B 选择 '套菜2'  以及  '套菜3' 加入购物车";
		
		List<CartIncludeSuitForm> cartSuitList41 = new ArrayList<CartIncludeSuitForm>();
		cartSuitList41.add(cartSuit1);
		cartSuitList41.add(cabageCartSuit1);
		
		List<CartIncludeSuitForm> cartSuitList42 = new ArrayList<CartIncludeSuitForm>();
		cartSuitList42.add(cartSuitWithChild11);
		cartSuitList42.add(cartSuitWithChild22);
		
		List<CartIncludeSuitForm> cartSuitList43 = new ArrayList<CartIncludeSuitForm>();
		cartSuitList43.add(cartSuit1);
		cartSuitList43.add(cabageCartSuit1);
		cartSuitList43.add(cartSuitWithChild11);
		cartSuitList43.add(cartSuitWithChild22);
	
		
		
		
		///////////////////////  用户 A 选择单个包含规格, 做法, 配料的普通菜加入购物车, 用户 B 选择单个套菜       ////////////////////////////////////////////
				
		String description5 = "用参人数为 6, 用户 A 选择 '东坡肉' 加入购物车,  用户 B 选择 '套菜2' 加入购物车 ";
		
		List<CartIncludeSuitForm> cartSuitList51 = new ArrayList<CartIncludeSuitForm>();
		cartSuitList51.add(cartSuitWithIntegrations);
		
		List<CartIncludeSuitForm> cartSuitList52 = new ArrayList<CartIncludeSuitForm>();
		cartSuitList52.add(cartSuitWithChild11);
		
		List<CartIncludeSuitForm> cartSuitList53 = new ArrayList<CartIncludeSuitForm>();
		cartSuitList53.add(cartSuitWithIntegrations);
		cartSuitList53.add(cartSuitWithChild11);

		
		
		return new Object[][]{
				
				{description1, cartSuitList11, cartSuitList12, cartSuitList13},
				{description2, cartSuitList21, cartSuitList22, cartSuitList23},
				{description3, cartSuitList31, cartSuitList32, cartSuitList33},
				
				{description4, cartSuitList41, cartSuitList42, cartSuitList43},
				{description5, cartSuitList51, cartSuitList52, cartSuitList53},


		};
	}
	
	
}
