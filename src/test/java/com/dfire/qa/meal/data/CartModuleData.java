package com.dfire.qa.meal.data;


import java.util.ArrayList;
import java.util.List;



import org.testng.annotations.DataProvider;

import com.dfire.qa.meal.bean.BaseParamBean;
import com.dfire.qa.meal.bean.MenuGet;


public class CartModuleData extends BaseTestData{
	
	
	@DataProvider(name = "createOwnCartTest")
	public static Object [][] createOwnCartTest(){
				
		String description1 = "description: 正常用例";
		BaseParamBean baseParamBean1 = new BaseParamBean();
		baseParamBean1.setEntityId(entityId);
		baseParamBean1.setSeatCode(seatCode);
		baseParamBean1.setXtoken(token);
		
		return new Object[][]{
				{description1, baseParamBean1, 200, 1},

		};
	}
	
	
	
	@DataProvider(name = "listCartDataTest")
	public static Object [][] listCartDataTest(){
		
		String description1 = "description: 正常用例";
		BaseParamBean baseParamBean1 = new BaseParamBean();
		baseParamBean1.setEntityId(entityId);
		baseParamBean1.setSeatCode(seatCode);
		baseParamBean1.setXtoken(token);
				
		
		return new Object[][]{
				{description1, baseParamBean1, 200, 1, 200},
				
		};
	}
	
	
	@DataProvider(name = "showPopupMessageTest")
	public static Object [][] showPopupMessageTest(){
		
		String description1 = "description: 正常用例";
		BaseParamBean baseParamBean1 = new BaseParamBean();
		baseParamBean1.setEntityId(entityId);		
		baseParamBean1.setXtoken(token);			
		
		return new Object[][]{
				{description1, baseParamBean1, 200, 1},
				
		};
	}
	
	
	
	@DataProvider(name = "addRequiredItemTest")
	public static Object [][] addRequiredItemTest(){
		
		String description1 = "description: 正常用例";				
		
		return new Object[][]{
				{description1, 200, 1},
				
		};
	}
	
	
	
	
	
	@DataProvider(name = "showCartPrivilegeTest")
	public static Object [][] showCartPrivilegeTest(){
		
		String description1 = "description: 正常用例";
		BaseParamBean baseParamBean1 = new BaseParamBean();
		baseParamBean1.setEntityId(entityId);		
		baseParamBean1.setXtoken(token);
		
		List<String> menuIdList1 = new ArrayList<String>();
		
		
		return new Object[][]{
				{description1, baseParamBean1, menuIdList1, 200, 1},
				
		};
	}
	
	
	
	@DataProvider(name = "getUserCartTest")
	public static Object [][] getUserCartTest(){
		
		String description1 = "description: 正常用例";
		BaseParamBean baseParamBean1 = new BaseParamBean();
		baseParamBean1.setEntityId(entityId);		
		baseParamBean1.setXtoken(token);
		
	
		return new Object[][]{
				{description1, baseParamBean1, 200, 1},
				
		};
	}
	
	
	
	@DataProvider(name = "modifyCartTest")
	public static Object [][] modifyCartTest(){
		
		String description1 = "description: 正常用例";
		BaseParamBean baseParamBean1 = new BaseParamBean();
		baseParamBean1.setEntityId(entityId);		
		baseParamBean1.setXtoken(token);
		
		MenuGet menuGet = new MenuGet();
		menuGet.setNormalMenu(true);
		menuGet.setNormalMenuNo(1);
		
		return new Object[][]{
				{description1, baseParamBean1, menuGet, 200, 1},
				
		};
	}
	
	
	
	@DataProvider(name = "getCartCountTest")
	public static Object [][] getCartCountTest(){
		
		String description1 = "description: 正常用例";
		BaseParamBean baseParamBean1 = new BaseParamBean();
		baseParamBean1.setEntityId(entityId);
		baseParamBean1.setSeatCode(seatCode);
		baseParamBean1.setOrderId("");
		baseParamBean1.setXtoken(token);
		
		MenuGet menuGet = new MenuGet();
		menuGet.setNormalMenu(true);
		menuGet.setNormalMenuNo(1);
		
		return new Object[][]{
				{description1, baseParamBean1, menuGet, 200, 1},
				
		};
	}

}
