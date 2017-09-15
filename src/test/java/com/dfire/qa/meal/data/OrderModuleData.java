package com.dfire.qa.meal.data;

import org.testng.annotations.DataProvider;

import com.dfire.qa.meal.bean.MenuGet;

public class OrderModuleData extends BaseTestData{

	
	
	@DataProvider(name = "submitOrderTest")
	public static Object [][] submitOrderTest(){
		
		String description1 = "用参人数为 6, 选择多个普通菜加入购物车";
		MenuGet menuGet = new MenuGet();
		menuGet.setNormalMenu(true);
		menuGet.setNormalMenuNo(1);
		
		return new Object[][]{
				{description1, menuGet, 200, 1, 200},

		};
	}
	
	
	
	@DataProvider(name = "getOrderTest")
	public static Object [][] getOrderTest(){
		
		String description1 = "用参人数为 6, 选择多个普通菜加入购物车";
		MenuGet menuGet = new MenuGet();
		menuGet.setNormalMenu(true);
		menuGet.setNormalMenuNo(1);
		
		return new Object[][]{
				{description1, menuGet, 200, 1, 200},

		};
	}
	
	
	
	@DataProvider(name = "checkOrderChangeTest")
	public static Object [][] checkOrderChangeTest(){
		
		String description1 = "用参人数为 6, 选择多个普通菜加入购物车";
		MenuGet menuGet = new MenuGet();
		menuGet.setNormalMenu(true);
		menuGet.setNormalMenuNo(1);
		
		return new Object[][]{
				{description1, menuGet, 200, 1},

		};
	}
	
	
}
