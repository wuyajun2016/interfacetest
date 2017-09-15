package com.dfire.qa.meal.api;

import javax.annotation.Resource;


import org.testng.annotations.Test;

import com.dfire.qa.meal.service.IManageService;

public class ManageController extends BaseTestController{
	
	
	@Resource
	IManageService manageService;
	
	
	@Test(description = "获取特定注解类下的方法")
	public void getMethodName() throws Exception{
		
		manageService.getAllMethods();
		
	}

}
