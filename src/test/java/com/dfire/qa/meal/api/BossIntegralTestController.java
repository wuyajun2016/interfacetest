package com.dfire.qa.meal.api;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.dfire.qa.meal.bean.DingdingCall;
import com.dfire.qa.meal.bean.DingdingMsg;
import com.dfire.qa.meal.bean.DingdingText;
import com.dfire.qa.meal.bean.MenuGet;
import com.dfire.qa.meal.bean.Response;
import com.dfire.qa.meal.data.BossTestData;
import com.dfire.qa.meal.httpclient.HTTPClientService;
import com.dfire.qa.meal.service.IBossService;
import com.dfire.qa.meal.utils.CommonUtil;

public class BossIntegralTestController extends BaseTestController{
	
	
	@Resource
	IBossService bossService;
	
	
	@Resource
	HTTPClientService httpClientService;
	
	
	@BeforeClass(alwaysRun = true)
	public void Setup() throws Exception{
		
		baseParamBean = commonService.getBaseParam(environment);
		
	}
	
	
	
	
	@AfterClass(alwaysRun = true)
	public void TearDown() throws Exception{
		
	}
	
	
	
	@Test(description = "顾客端设置---基础设置", dataProvider = "infraSwitch",
			dataProviderClass = BossTestData.class, groups = {"smoke", "all"})
	public void dingdingPush() throws Exception{
			
				
		
	}
	
	
	
	

}
