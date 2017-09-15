package com.dfire.qa.meal.data;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.dfire.qa.meal.bean.MenuGet;


public class PayModuleData extends BaseTestData{
	
	
	@DataProvider(name = "getActivitySwitchTest")
	public static Object [][] getActivitySwitchTest(){
		
		String description1 = "获取点赞活动开关";		
		
		return new Object[][]{
				{description1, 200, 0},

		};
	}
	
	
	
	@DataProvider(name = "isCreateWaiterCommentsTest")
	public static Object [][] isCreateWaiterCommentsTest(){
		
		String description1 = "查询订单是否允许评价";		
		String order1 = "123";
		
		return new Object[][]{
				{description1, order1, 200, 1},

		};
	}
	
	
	
	@DataProvider(name = "getNormalTradeBillTest")
	public static Object [][] getNormalTradeBillTest(){
		
		String description1 = "获取后付款账单信息(即先下单后付款)";		
		MenuGet menuGet = new MenuGet();
		menuGet.setNormalMenu(true);
		menuGet.setNormalMenuNo(1);
				
		return new Object[][]{
				{description1, menuGet, 200, 1},

		};
	}
	
	
	
	
	@DataProvider(name = "getTradeBillTest")
	public static Object [][] getTradeBillTest(){
		
		String description1 = "获取优惠方案及支付订单信息";		
		MenuGet menuGet = new MenuGet();
		menuGet.setNormalMenu(true);
		menuGet.setNormalMenuNo(1);
				
		return new Object[][]{
				{description1, menuGet, 200, 1},

		};
	}
	

}
