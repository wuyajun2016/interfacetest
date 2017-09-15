package com.dfire.qa.meal.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;









import org.testng.annotations.DataProvider;

import com.dfire.qa.meal.bean.MenuGet;
import com.dfire.qa.meal.vo.menu.MenuList;
import com.dfire.qa.meal.vo.menu.MenuSpec;
import com.dfire.qa.meal.vo.menu.MenuSpecChild;
import com.dfire.testcase.function.util.base.WechatBaseUtil;
import com.dfire.wechat.util.BeanProvider;
import com.dfire.wechat.util.CartIncludeSuitForm;
import com.dfire.wechat.util.CommonConstants;
import com.dfire.wechat.util.HttpRequestEx;
import com.google.gson.Gson;

public class MenuTestData extends BaseTestData{
	


	
	@DataProvider(name = "clickToEnterest")
	public static Object [][] clickToEnterest(){
		
		
		String description1 = "description: token 正常, entityId 正常, seat_code 正常, order_id 为空, 返回具体的购物车信息";
		String entityId = CommonConstants.entityId;
		String seatCode = CommonConstants.seatCode;
		
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
		MenuGet menuGet = new MenuGet();
		menuGet.setNormalMenu(true);
		menuGet.setNormalMenuNo(1);

		return new Object[][]{
				{description1, menuGet},

		};
	}
	
	
	
	

	
	
	
}
