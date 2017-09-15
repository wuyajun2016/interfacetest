package com.dfire.qa.meal.data;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.dfire.qa.meal.bean.MenuGet;
import com.dfire.qa.meal.utils.MenuUtil;
import com.dfire.qa.meal.vo.cart.CartIncludeSuitForm;

public class PayTestData extends BaseTestData{
	
	
	@DataProvider(name = "placeOrderWithNoPrePaySeatCode")
	public static Object [][] placeOrderWithNoPrePaySeatCode(){
		

		String description1 = "用餐人数为 6, 选择单个普通菜加入购物车";
//		
//		CartIncludeSuitForm cartSuit1 = MenuUtil.getNormalMenu(normal1MenuId, normal1MenuName, 1, uid);
//		List<CartIncludeSuitForm> cartSuitList1 = new ArrayList<CartIncludeSuitForm>();
//		cartSuitList1.add(cartSuit1);
		
		MenuGet menuGet = new MenuGet();
		menuGet.setNormalMenu(true);
		menuGet.setNormalMenuNo(1);
		
		return new Object[][]{
				
				{description1, menuGet},

		};
	}
	
	
	
	@DataProvider(name = "placeOrderWithPrePaySeatCode")
	public static Object [][] placeOrderWithPrePaySeatCode(){
		

		String description1 = "用餐人数为 6, 选择单个普通菜加入购物车";
		
		MenuGet menuGet = new MenuGet();
		menuGet.setNormalMenu(true);
		menuGet.setNormalMenuNo(1);
		
		return new Object[][]{
				
				{description1, menuGet},

		};
	}


}
