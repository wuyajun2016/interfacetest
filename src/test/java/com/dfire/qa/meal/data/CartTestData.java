package com.dfire.qa.meal.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;









import org.testng.annotations.DataProvider;

import com.dfire.qa.meal.bean.MenuGet;
import com.dfire.qa.meal.vo.boss.ForceConfig;
import com.google.gson.Gson;

public class CartTestData extends BaseTestData{
	


	@DataProvider(name = "showCartPrivilegeTest")
	public static Object [][] showCartPrivilegeTest(){
		
		List<String> menuIdList = new ArrayList<String>();
		
		String description1 = "description: token 正常, entityId 正常, seat_code 正常, order_id 为空, 返回具体的购物车信息";
		Map<String, String> query1 = new HashMap<String, String>();
		query1.put("xtoken", token);
		query1.put("entity_id", entityId);
		query1.put("menu_id_list", gson.toJson(menuIdList));		

		return new Object[][]{
				{description1, query1, 200, 1, 200},
				
		};
	}
	
	
	@DataProvider(name = "addMenuTest")
	public static Object [][] addMenuTest(){
				
	
		String description1 = "description: 一份普通菜";
		MenuGet menuGet = new MenuGet();
		menuGet.setNormalMenu(true);
		menuGet.setNormalMenuNo(1);
		
		String description2 = "description: 一份套菜";
		MenuGet menuGet2 = new MenuGet();
		menuGet2.setSuitMenu(true);
		menuGet2.setSuitMenuNo(1);
		
		String description3 = "description: 两份普通菜";
		MenuGet menuGet3 = new MenuGet();
		menuGet3.setNormalMenu(true);
		menuGet3.setNormalMenuNo(2);
		
		
		String description4 = "description: 两份套菜";
		MenuGet menuGet4 = new MenuGet();
		menuGet4.setSuitMenu(true);
		menuGet4.setSuitMenuNo(2);
		
		
		String description5 = "description: 一份普通菜和一份套菜";
		MenuGet menuGet5 = new MenuGet();
		menuGet5.setNormalMenu(true);
		menuGet5.setNormalMenuNo(1);
		menuGet5.setSuitMenu(true);
		menuGet5.setSuitMenuNo(1);
		
				
		String description6 = "description: 两份普通菜和两份套菜";
		MenuGet menuGet6 = new MenuGet();
		menuGet6.setNormalMenu(true);
		menuGet6.setNormalMenuNo(2);
		menuGet6.setSuitMenu(true);
		menuGet6.setSuitMenuNo(2);
		
		
		
		// 用例1 ： 选择包含规格, 做法, 配料的普通菜加入购物车
		// 用例2 ： 起点份数不同
		
		return new Object[][]{
				
				{description1, menuGet, 200, 1},
				{description2, menuGet2, 200, 1},
				
				{description3, menuGet3, 200, 1},
				{description4, menuGet4, 200, 1},
				
				{description5, menuGet5, 200, 1},
				{description6, menuGet6, 200, 1},

		};
	}
	
	
	
	@DataProvider(name = "addMenuTest2")
	public static Object [][] addMenuTest2(){
		
		String description1 = "description: 用参人数为 6, 选择单个普通菜, 加入购物车, 清空购物车, 重新加菜";
		MenuGet menuGet = new MenuGet();
		menuGet.setNormalMenu(true);
		menuGet.setNormalMenuNo(1);		

		return new Object[][]{
				{description1, menuGet},
				
		};
	}
	
	
	
	@DataProvider(name = "addMenuTest3")
	public static Object [][] addMenuTest3(){
		
		String description1 = "用参人数为 6, 选择单个普通菜, 加入购物车, 将该份菜份数减为 0, 重新加菜";
		MenuGet menuGet = new MenuGet();
		menuGet.setNormalMenu(true);
		menuGet.setNormalMenuNo(1);		

		return new Object[][]{
				{description1, menuGet},
				
		};
	}
	
	
	
	@DataProvider(name = "addMenuTest4")
	public static Object [][] addMenuTest4(){
		
		String description1 = "掌柜端打开添加必选菜开关, 添加套菜到购物车";
				
		// 必选菜	
	    Integer forceType1 = 1;   // 强制类型（0:指定数量,1:与用餐人数相同）
	    Integer forceNum1 = 1;    //强制点菜数量（当强制类型为"与用餐人数相同"时无视此数量）
	    String menuIdForForce1 = "";   // menuId
		Integer menuType1 = 0;    // 商品类型（0:普通菜,1:套菜,2:加料菜）
		
		ForceConfig forceConfig1 = new ForceConfig();
		forceConfig1.setForceType(forceType1);
		forceConfig1.setForceNum(forceNum1);
		forceConfig1.setMenuType(menuType1);
		forceConfig1.setMenuId(menuIdForForce1);
		
		MenuGet menuGet1 = new MenuGet();
		menuGet1.setNormalMenu(true);
		menuGet1.setNormalMenuNo(1);		

		MenuGet menuGet2 = new MenuGet();
		menuGet2.setSuitMenu(true);
		menuGet2.setSuitMenuNo(1);	
		
		return new Object[][]{
				{description1, menuGet1, forceConfig1, menuGet2},
				
		};
	}
	
	
	
	@DataProvider(name = "addMenuTest5")
	public static Object [][] addMenuTest5(){
		
		String description1 = "用参人数为 6, 选择单个普通菜, 加入购物车, 将该份菜份数减为 0, 重新加菜";
		MenuGet menuGet1 = new MenuGet();
		menuGet1.setNormalMenu(true);
		menuGet1.setNormalMenuNo(1);	
		
		MenuGet menuGet2 = new MenuGet();
		menuGet2.setSuitMenu(true);
		menuGet2.setSuitMenuNo(1);	

		return new Object[][]{
				{description1, menuGet1, menuGet2},
				
		};
	}
	
	
	
	
	@DataProvider(name = "clickToEnterest")
	public static Object [][] clickToEnterest(){
		
		
		String description1 = "description: token 正常, entityId 正常, seat_code 正常, order_id 为空, 返回具体的购物车信息";
		
		String repeat = "false";
		String recommend = "false";
		String orderId = "";
		
		return new Object[][]{
				{description1, entityId, seatCode, repeat, recommend, orderId},
				
		};
	}
	
	@DataProvider(name = "dishCartsTest")
	public static Object [][] dishCartsTest(){
		
		String description1 = "用参人数为 6, 选择多个普通菜加入购物车";
		
		// 菜品的规格
		MenuGet menuGet = new MenuGet();
		menuGet.setNormalMenu(true);
		menuGet.setNormalMenuNo(1);	
		
		return new Object[][]{
				{description1, menuGet},

		};
	}
	
	
	
	@DataProvider(name = "dishOrderTest")
	public static Object [][] dishOrderTest(){
		
		String description1 = "用参人数为 6, 选择单个普通菜加入购物车, 扫码时该桌已下单，进行菜单验证";
		
		// 菜品的规格
		MenuGet menuGet = new MenuGet();
		menuGet.setNormalMenu(true);
		menuGet.setNormalMenuNo(1);	

		return new Object[][]{
				{description1, menuGet},

		};
	}
	
	
	
}
