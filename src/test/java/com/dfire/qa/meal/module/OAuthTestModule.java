package com.dfire.qa.meal.module;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.dfire.qa.meal.api.BaseTestController;
import com.dfire.qa.meal.bean.BaseParamBean;
import com.dfire.qa.meal.bean.MenuGet;
import com.dfire.qa.meal.bean.Response;
import com.dfire.qa.meal.data.OAuthModuleTestData;
import com.dfire.qa.meal.httpclient.HTTPClientService;
import com.dfire.qa.meal.props.HostProperties;
import com.dfire.qa.meal.service.ICartService;
import com.dfire.qa.meal.service.IOAuthService;
import com.dfire.qa.meal.service.IQRCodeService;
import com.dfire.qa.meal.utils.PathForHTTP;
import com.dfire.qa.meal.vo.cart.CartIncludeSuitForm;

public class OAuthTestModule extends BaseTestController{
	
	
	@Resource
	private HTTPClientService httpClientService;
	
	
	@Resource
	IOAuthService oauthService;
	
	
	private String menuId = null;
	
	@BeforeClass(alwaysRun = true)
	public void setup() throws Exception{
		
		baseParamBean = commonService.getBaseParam(environment);
		MenuGet menuGet = new MenuGet();
		menuGet.setNormalMenu(true);
		menuGet.setNormalMenuNo(1);
		List<CartIncludeSuitForm> cartSuitList = commonService.getMenu(baseParamBean, menuGet, otherParameter, environment);
		menuId = cartSuitList.get(0).getMenuId();
				
	}
	

	@Test(description = "oAuth test", dataProvider = "oAuthTest",
			dataProviderClass = OAuthModuleTestData.class, groups = {"smoke", "all"})
	public void oAuthTest(String description, BaseParamBean baseParam, int responseStatus) throws Exception{
		
		Response response = oauthService.seatCodeOAuth(baseParam, environment); 
		Assert.assertEquals(response.getStatus(), responseStatus);
					
	}
	
	
	/**
	 * 目前暂时还未获取到有效的 global code，因此测试用例还缺乏数据<br/>
	 */
	@Test(description = "oAuth consumer code test", dataProvider = "oAuthConsumerCodeTest",
			dataProviderClass = OAuthModuleTestData.class, groups = {"smoke", "all"})
	public void oAuthConsumerCodeTest(String description,BaseParamBean baseParam, String consumerCode, int responseStatus) throws Exception{
		
		
		Response response = oauthService.globalCodeOAuth(baseParam, consumerCode, environment); 
		Assert.assertEquals(response.getStatus(), responseStatus);
					
		
	}
	
	
	
	@Test(description = "oAuth entityId test", dataProvider = "oAuthEntityIdTest",
			dataProviderClass = OAuthModuleTestData.class, groups = {"smoke", "all"})
	public void oAuthEntityIdTest(String description, BaseParamBean baseParam, int responseStatus) throws Exception{
			
		
		Response response = oauthService.entityIdOAuth(baseParam, environment); 
		Assert.assertEquals(response.getStatus(), responseStatus);
						
		
	}
	
	
	
	@Test(description = "oAuth menu code test", dataProvider = "oAuthMenuCodeTest",
			dataProviderClass = OAuthModuleTestData.class, groups = {"smoke", "all"})
	public void oAuthMenuCodeTest(String description,BaseParamBean baseBean, Boolean correctMenu, int responseStatus) throws Exception{
		
		String correctMenuId = correctMenu ? menuId : "890868";
		
		Response response = oauthService.menuCodeOAuth(baseBean, correctMenuId, environment); 
		Assert.assertEquals(response.getStatus(), responseStatus);
			
		
	}
	
	
	
	/**
	 * 该接口考虑到需要微信端返回 code, 暂不覆盖<br/>
	 */
	/*
	@Test(description = "oAuth call back test", dataProvider = "oAuthCallbackTest",
			dataProviderClass = OAuthModuleTestData.class, groups = {"smoke", "all"})
	public void oAuthCallbackTest(String description, Map<String, String> query, String user_agent, int responseStatus) throws Exception{
		

		List<String> path = PathForHTTP.getPathForOAuthCallback();
		
		Response response = httpRequest.get(path, query, user_agent); 
		Assert.assertEquals(response.getStatus(), responseStatus);
			
			
		
	}
	*/
	
	
	
	/**
	 * 该接口涉及到第三方支付，流程较为复杂，暂不覆盖<br/>
	 */
	/*
	@Test(description = "order dishes in aliShop test", dataProvider = "aliShopOrderTest",
			dataProviderClass = OAuthModuleTestData.class, groups = {"smoke", "all"})
	public void aliShopOrderTest(String description, Map<String, String> query, String user_agent, int responseStatus) throws Exception{

		List<String> path = PathForHTTP.getPathForaliShop();
		
		Response response = httpRequest.get(path, query, user_agent); 
		Assert.assertEquals(response.getStatus(), responseStatus);
			
	
	}
	*/
	
	

}
