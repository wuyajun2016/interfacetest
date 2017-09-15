package com.dfire.qa.meal.module;


import javax.annotation.Resource;


import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.dfire.qa.meal.api.BaseTestController;
import com.dfire.qa.meal.bean.BaseParamBean;
import com.dfire.qa.meal.bean.Response;
import com.dfire.qa.meal.data.ShopInfoModuleTestData;
import com.dfire.qa.meal.httpclient.HTTPClientService;
import com.dfire.qa.meal.props.HostProperties;
import com.dfire.qa.meal.service.ICartService;
import com.dfire.qa.meal.service.IQRCodeService;
import com.dfire.qa.meal.service.IShopService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class ShopInfoTestModule extends BaseTestController{
	
	@Resource
	IShopService shopService;
	
	@Resource
	private HostProperties hostProperties;
	
	@Resource
	private HTTPClientService httpClientService;
	
	@Resource
	IQRCodeService qrCodeService;
	
	@Resource
	private ICartService cartService;
	
	@BeforeClass(alwaysRun = true)
	public void setup() throws Exception{
		
		baseParamBean = commonService.getBaseParam(environment);

	}
	
	
	
	
	@Test(description = "load state test", dataProvider = "LoadStateTest",
			dataProviderClass = ShopInfoModuleTestData.class, groups = {"smoke", "all"})
	public void loadStateTest(String description, BaseParamBean baseParam, int responseStatus, int resultCode, int jobStatus) throws Exception{
		

		Response response = shopService.getShopState(baseParam, environment); 
		Assert.assertEquals(response.getStatus(), responseStatus);
		
		if(responseStatus == 200){
			JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(resp.get("code").getAsInt(), resultCode);
			
			if(jobStatus == 200){
				Assert.assertNotNull(resp.get("data").getAsJsonObject().get("shop").getAsJsonObject().get("shop_name"));
				Assert.assertNotNull(resp.get("data").getAsJsonObject().get("shop").getAsJsonObject().get("shop_id"));
				Assert.assertNotNull(resp.get("data").getAsJsonObject().get("shop").getAsJsonObject().get("seat_name"));
				Assert.assertNotNull(resp.get("data").getAsJsonObject().get("shop").getAsJsonObject().get("seat_id"));
			}
		}
		
		
	}


	@Test(description = "base info test", dataProvider = "baseInfoTest",
			dataProviderClass = ShopInfoModuleTestData.class, groups = {"smoke", "all"})
	public void baseInfoTest(String description,BaseParamBean baseParam, int responseStatus, int resultCode, int jobStatus) throws Exception{
		

		Response response = shopService.getShopBaseInfo(baseParam, environment); 
		Assert.assertEquals(response.getStatus(), responseStatus);
		
		if(responseStatus == 200){
			JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(resp.get("code").getAsInt(), resultCode);
			
			if(jobStatus == 200){
				Assert.assertNotNull(resp.get("data").getAsJsonObject().get("shopImgPaths").getAsJsonArray().get(0));
			}
		}
		
		
	}
	
	
	@Test(description = "moment test", dataProvider = "momentTest",
			dataProviderClass = ShopInfoModuleTestData.class, groups = {"smoke", "all"})
	public void momentTest(String description, BaseParamBean baseParam, int responseStatus, int resultCode, int jobStatus) throws Exception{
		
		
		Response response = shopService.getShopMoment(baseParam, environment); 
		Assert.assertEquals(response.getStatus(), responseStatus);
		
		if(responseStatus == 200){
			JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(resp.get("code").getAsInt(), resultCode);
			
			if(jobStatus == 200){
				Assert.assertNotNull(resp.get("data").getAsJsonObject().get("shopImgPaths").getAsJsonArray());
				Assert.assertNotNull(resp.get("data").getAsJsonObject().get("shopImgPaths").getAsJsonArray());
				Assert.assertNotNull(resp.get("data").getAsJsonObject().get("shopImgPaths").getAsJsonArray());

			}
		}
		
		
	}
	
	
	
	
	@Test(description = "all Info test", dataProvider = "allInfoTest",
			dataProviderClass = ShopInfoModuleTestData.class, groups = {"smoke", "all"})
	public void allInfoTest(String description, BaseParamBean baseParam, int responseStatus, int resultCode, int jobStatus) throws Exception{
		
		
		Response response = shopService.getShopAllInfo(baseParam, environment); 
		Assert.assertEquals(response.getStatus(), responseStatus);
		
		if(responseStatus == 200){
			JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(resp.get("code").getAsInt(), resultCode);
			
			if(jobStatus == 200){
				Assert.assertNotNull(resp.get("data").getAsJsonObject().get("shopImgPaths").getAsJsonArray());

			}
		}
		
		
	}
	
	
	
	
	@Test(description = "shop share test", dataProvider = "shopShareTest",
			dataProviderClass = ShopInfoModuleTestData.class, groups = {"smoke", "all"})
	public void shopShareTest(String description, BaseParamBean baseParam, int responseStatus, int resultCode, int jobStatus) throws Exception{
		
		
		Response response = shopService.getShopShare(baseParam, environment); 
		Assert.assertEquals(response.getStatus(), responseStatus);
		
		if(responseStatus == 200){
			JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(resp.get("code").getAsInt(), resultCode);
			
			if(jobStatus == 200){
				Assert.assertNotNull(resp.get("data").getAsJsonObject().get("moment").getAsJsonObject().get("title"));
				Assert.assertNotNull(resp.get("data").getAsJsonObject().get("moment").getAsJsonObject().get("shopUrl"));
				Assert.assertNotNull(resp.get("data").getAsJsonObject().get("moment").getAsJsonObject().get("imgUrl"));
				
			}
		}
		
		
	}
}
