package com.dfire.testcase.function.integration;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.dfire.wechat.util.BeanProvider;
import com.dfire.wechat.util.CartIncludeSuitForm;
import com.dfire.wechat.util.CommonConstants;

public class SeatCodeNoPrePayTestData {
	
	private static String uid = CommonConstants.UIDOther;
	
	@DataProvider(name = "placeOrderWithCashTest")
	public static Object [][] placeOrderWithCashTest(){
		
		
		String description1 = "用参人数为 6, 选择单个普通菜 '水果沙拉' 加入购物车";
		
		// 菜品的规格
		double menuNumber1 = 1;
		int kindType = 1;
		String makeId = ""; 
		String SpecDetailed = "";
		String menuName = "橙汁";
		String menuId = CommonConstants.juice;// "00067404574691cc01576b071a9c00c3";// "00067404574690b801576b0720e00426";
		String index = "";//"44354406402140433b0162add34543d1";
		
		CartIncludeSuitForm cartIncludeSuitForm = BeanProvider.getCartSuit(menuNumber1, kindType, makeId, SpecDetailed, menuName, menuId, uid, index);
		List<CartIncludeSuitForm> cartList = new ArrayList<CartIncludeSuitForm>();
		cartList.add(cartIncludeSuitForm);
		
		return new Object[][]{
				
				{description1, cartList},

		};
	}


}
