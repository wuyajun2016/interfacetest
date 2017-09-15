package com.dfire.testcase.function.boss;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.alibaba.dubbo.remoting.http.HttpServer;
import com.dfire.testcase.bean.BasicSetting;
import com.dfire.wechat.util.CommonConstants;
import com.dfire.wechat.util.HttpRequestEx;
import com.dfire.wechat.util.MD5Utils;
import com.dfire.wechat.util.Response;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class BossControllerTest {
	
	private static final Logger logger = Logger.getLogger(BossControllerTest.class);
	private String host = CommonConstants.BOSS_API_HOST;
	private HttpRequestEx httpRequest;	
	
	private String entityId = CommonConstants.entityId;
	private String configId = "99929249586bcef30158715df1520022"; // 对应的菜是 可乐
	private String sign = "4bc6ffb96d7279bd2761063e75004e69";	
	
	private String appKey = "200006";
	private String appVersion = "5.5.52";
	private String deviceId = "1E8543AEED1E43BEBAB6AE46A2BAC605";
	
	private String format = "json";
	// 在 HTTP 请求的 header 中 设置为 test时 session 相关参数可忽略
	private String sessionKey = "100008999292499992924957e6050a0157f53eeb9f0013";  
	private String timeStamp = Long.toString(System.currentTimeMillis());

	private String menuId = CommonConstants.cokeMenuId; // 对应的菜菜是可乐
	private Integer menuType = 0;
	private Integer forceType = 0;
	private Integer forceNum = 1;
	
	private String secret = "BoivJgAlmBUO05yoxD6RU/SZ/nhLvpXT40v2ceqKJ1s=";
	private BaseBossBean baseParam = new BaseBossBean();
	private ForceConfig forceConfig = new ForceConfig();
	private Map<String, String> httpHeader = new HashMap<String, String>();

	private boolean https = CommonConstants.HTTPS;
	
	@BeforeClass(alwaysRun = true)
	public void setup(){
				
		logger.info("setup in BossControllerTest");
		httpRequest = new HttpRequestEx(host, https);
		
		// 设置 HTTP body 中的具体某个参数
		forceConfig.setMenuId(menuId);
		forceConfig.setMenuType(menuType);
		forceConfig.setForceType(forceType);
		forceConfig.setForceNum(forceNum);
		
		
		// 设置 HTTP 请求 body
		baseParam.setConfigId(configId);
		baseParam.setForceConfig(forceConfig);
		
		baseParam.setAppKey(appKey);
		baseParam.setAppVersion(appVersion);
		baseParam.setDeviceId(deviceId);
		
		baseParam.setFormat(format);
		baseParam.setSessionKey(sessionKey);
		baseParam.setTimeStamp(timeStamp);
		baseParam.setEntityId(entityId);
        
		
		
		// 设置 HTTP 请求  header
		httpHeader.put("version", "sso");
		httpHeader.put("sessionId", "123");
		httpHeader.put("isTest", "true");

	}
	
	
	@AfterClass(alwaysRun = true)
	public void tearDown(){
		
		logger.info("tearDown in BossControllerTest");
		
		httpRequest.ShutDown();
	}
	
	
	
	// 保存必选商品设置
	@Test(description = "save the force menu ")
	public void saveForceMenuTest(){
		
		try{
			
			sign = MD5Utils.generateSignForBossAPI(secret, baseParam.getBodyForSaveForceMenu());
			baseParam.setSign(sign);
			Response response = RequestForBoss.saveForceMenu(httpRequest, httpHeader, baseParam); 
			
			Assert.assertEquals(response.getStatus(), 200);
			JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(resp.get("code").getAsInt(), 1);

			
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}


	
	// 删除必选商品设置
	@Test(description = "remove the force menu ")
	public void removeForceMenuTest(){
		
		try{
			sign = MD5Utils.generateSignForBossAPI(secret, baseParam.getBodyForRemoveForceMenu());
			baseParam.setSign(sign);
			Response response = RequestForBoss.removeForceMenu(httpRequest, httpHeader, baseParam); 
			
			Assert.assertEquals(response.getStatus(), 200);
			JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(resp.get("code").getAsInt(), 1);
			
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}
	
	
	// 查询必选商品设置
	@Test(description = "query the force menu ")
	public void queryForceMenuTest(){
		
		try{
			
			sign = MD5Utils.generateSignForBossAPI(secret, baseParam.getBodyForQueryForceMenu());
			baseParam.setSign(sign);
			Response response = RequestForBoss.queryForceMenuList(httpRequest, httpHeader, baseParam); 
			
			Assert.assertEquals(response.getStatus(), 200);
			JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(resp.get("code").getAsInt(), 1);
			
			
			
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}
	

	

	@Test(description = "test for method in the BossController ")
	public void methodTest(){
		
		try{
			
			BossController bossController = new BossController();
			Boolean flag = bossController.removeForceMenu(httpRequest, entityId, menuId);
			logger.info("remove force menu successfully");
			
			
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}
	
	
	@Test(description = "test for prepay switch")
	public void prePayTest() throws Exception{
		
		BossController bossController = new BossController();
		Boolean flag = bossController.basicConfigForBoss(httpRequest, "00067404", BasicSetting.prePaySeat, "e90157d6a6819b38788203d132f12acb", false);
		logger.info("switch prePay successfully");
	}
	
	
}
