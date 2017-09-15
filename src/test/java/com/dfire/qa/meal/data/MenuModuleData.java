package com.dfire.qa.meal.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.dfire.qa.meal.bean.BaseParamBean;
import com.dfire.qa.meal.vo.menu.MenuList;
import com.dfire.qa.meal.vo.menu.MenuSpec;
import com.dfire.qa.meal.vo.menu.MenuSpecChild;

public class MenuModuleData extends BaseTestData{
	
	
	@DataProvider(name = "listMenusSpecTest")
	public static Object [][] listMenusSpecTest(){
	
		String description1 = "description: 显示菜单详情";
		MenuSpecChild menuSpecChild = new MenuSpecChild("", null, null, null);
		List<MenuSpecChild> menuSpecChilds = new ArrayList<MenuSpecChild>();
		menuSpecChilds.add(menuSpecChild);
		
		MenuList menuList = new MenuList(menuSpecChilds);		
		MenuSpec menuSpec = new MenuSpec(entityId, "", 0, menuList);
		
		return new Object[][]{
				{description1, menuSpec, 200, 1},
				
		};
	}
	
	
	
	
	@DataProvider(name = "listMenusTest")
	public static Object [][] listMenusTest(){
				
		String description1 = "description: 显示菜单列表";
		
		return new Object[][]{
				{description1, 200, 1, 200},
				
		};
	}
	
	
	
	@DataProvider(name = "modifyPeopleTest")
	public static Object [][] modifyPeopleTest(){
		
		String description1 = "description: 修改点餐人数";
		
		return new Object[][]{
				{description1, 200, 1, 200},

		};
	}
	
	
	
	
	@DataProvider(name = "addRequiredItemTest")
	public static Object [][] addRequiredItemTest(){
		
		String description1 = "description: 添加必选菜";
		
		return new Object[][]{
				{description1, 200, 1},

		};
	}
	
	
	
	@DataProvider(name = "getRecommendMenusTest")
	public static Object [][] getRecommendMenusTest(){
		
		String description1 = "description: 获取推荐菜";
		
		return new Object[][]{
				{description1, 200, 1, 200},

		};
	}
	
	
	
	@DataProvider(name = "getMenusTest")
	public static Object [][] getMenusTest(){
		
		String description1 = "description: 正常用例";
		BaseParamBean baseParamBean1 = new BaseParamBean();
		baseParamBean1.setEntityId(entityId);		
		baseParamBean1.setXtoken(token);
		
	
		return new Object[][]{
				{description1, baseParamBean1, 200, 1, 200},
				
		};
	}
	
	
	
	
	

}
