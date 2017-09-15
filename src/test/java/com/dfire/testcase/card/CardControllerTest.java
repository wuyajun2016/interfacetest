package com.dfire.testcase.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.dfire.wechat.util.CommonConstants;
import com.dfire.wechat.util.HttpRequestEx;
import com.dfire.wechat.util.PathForPost;
import com.dfire.wechat.util.Response;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CardControllerTest {

	private static final Logger logger = Logger.getLogger(CardControllerTest.class);
	private String host = CommonConstants.DEFAULT_HOST;
	private boolean https = CommonConstants.HTTPS;
	private HttpRequestEx httpRequest;	
	
	@BeforeClass(alwaysRun = true)
	public void setup(){
		
		logger.info("setup in CardControllerTest");
		httpRequest = new HttpRequestEx(host, https);

	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown(){
		logger.info("tearDown in CardControllerTest");
		httpRequest.ShutDown();
	}
	
	@Test(description = "apply card test", dataProvider = "ApplyCardTest",
			dataProviderClass = CardControllerTestData.class, groups = {"smoke", "all"})
	public void applyCardTest(String description, Map<String, String> query, int responseStatus, int resultCode, int jobStatus){
		
		try{
			Response response = httpRequest.post(PathForPost.getPathForApplyCard(), query); 
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
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}
	
	
	@Test(description = "card list test", dataProvider = "CardListTest",
			dataProviderClass = CardControllerTestData.class, groups = {"smoke", "all"})
	public void cardListTest(String description, Map<String, String> query, int responseStatus, int resultCode, int jobStatus){
		
		try{
			Response response = httpRequest.get(PathForPost.getPathForCardList(), query); 
			Assert.assertEquals(response.getStatus(), responseStatus);
			
			if(responseStatus == 200){
				JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
				Assert.assertEquals(resp.get("code").getAsInt(), resultCode);
				
				if(jobStatus == 200){
					Assert.assertNotNull(resp.get("data").getAsJsonArray().get(0).getAsJsonObject().get("id"));
				}
			}
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}
	
	@Test(description = "delete card test", dataProvider = "DeleteCardTest",
			dataProviderClass = CardControllerTestData.class, groups = {"smoke", "all"})
	public void deleteCardTest(String description,  Map<String, String> query, String cardId, int responseStatus, int resultCode, int jobStatus){
		
		try{
			List<String> path = new ArrayList<String>();
			for(String element:PathForPost.getPathForDeleteCard())
				path.add(element);
			path.add(cardId);
			
			Response response = httpRequest.post(path, query); 
			Assert.assertEquals(response.getStatus(), responseStatus);
			
			if(responseStatus == 200){
				JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
				Assert.assertEquals(resp.get("code").getAsInt(), resultCode);
				
				if(jobStatus == 200){
					Assert.assertNotNull(resp.get("data").getAsJsonObject().get("shop").getAsJsonObject().get("shop_name"));
				}
			}
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}

}
