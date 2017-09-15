package com.dfire.qa.meal.api;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.dfire.qa.meal.bean.Response;
import com.dfire.qa.meal.data.QRCodeTestData;
import com.dfire.qa.meal.enums.Environment;
import com.dfire.qa.meal.httpclient.HTTPClientService;
import com.dfire.qa.meal.logger.LoggerMarkers;
import com.dfire.qa.meal.logger.MealLoggerFactory;
import com.dfire.qa.meal.props.AuthProperties;
import com.dfire.qa.meal.props.HostProperties;
import com.dfire.qa.meal.props.ShopProperties;
import com.dfire.qa.meal.service.ICommonService;
import com.dfire.qa.meal.service.IQRCodeService;
import com.dfire.qa.meal.utils.CommonUtil;
import com.dfire.qa.meal.utils.HTTPRequestUtil;
import com.dfire.qa.meal.utils.PathForHTTP;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class QRCodeTestController extends BaseTestController{
		
	@Resource
	private ShopProperties shopProperties;
	
	@Resource
	private HostProperties hostProperties;
	
	@Resource
	private HTTPClientService httpClientService;
	
	@Resource
	private ICommonService commonService;
	
	@Resource
	private IQRCodeService qrCodeService;
	
	@BeforeClass(alwaysRun = true)
	public void setup(){
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, "setup in QRCodeTestController");				

	}
	
	@AfterClass(alwaysRun = true)
	public void tearDown(){
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, "tearDown in QRCodeTestController");	
				
	}
		
	
	
	/**
	 * 二维码扫码入口  
	 *扫桌码：/s/{entityId}/{seatCode}/{signKey}  
	 *扫店码：/s/{entityId}/{signKey}  
	 *扫菜码：/m/{entityId}/{menuId}/{signKey}  
	 *扫消费码：/g/{globalCode}  
	 *扫外卖码：/t/{entityId}/{signKey}  
	 *对应 weixin-meal 工程控制器：OAuthController  
	 */
	@Test(description = "scan QRcode test", dataProvider = "oauthQRCodeTest",
			dataProviderClass = QRCodeTestData.class, groups = {"smoke", "all"})
	public void oauthQRCodeTest(String description, List<String> path, int responseStatus) throws Exception{
		
			Response response = qrCodeService.scanQRCode(null, path, environment);
			Assert.assertEquals(response.getStatus(), responseStatus, "二维码扫码返回状态验证未通过");		
		
	}
	

	/**
	 * 获取初始化数据
	 * 对应 URL: GET /shop/v1/get_state
	 * 对于四种流程均相同
	 */
	@Test(description = "获取初始化数据", dataProvider = "getInitDataForShopTest",
			dataProviderClass = QRCodeTestData.class, groups = {"smoke", "all"})
	public void getInitDataForShopTest(String description, Map<String, String> query, int responseStatus, int resultCode, int jobStatus) throws Exception{
		
		
		if (Environment.publish == environment){
			httpClientService.setHttps();
		}
		
		// 计算 URL 并发送 Get 请求
		String protocol = (Environment.publish == environment) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(environment).get("serverURL"), PathForHTTP.getPathForLoadState(), query, protocol);
		Response response = httpClientService.doGet(url, null); 		
		Assert.assertEquals(response.getStatus(), responseStatus);
		
		if(responseStatus == 200){
			JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(resp.get("code").getAsInt(), resultCode);
			
			if(jobStatus == 200){
				
				Map<String, String> shopPra = shopProperties.getShopProperties();
				Assert.assertNotNull(resp.get("data").getAsJsonObject().get("shop").getAsJsonObject().get("shop_name"), "店铺名称为空");					
				Assert.assertNotNull(resp.get("data").getAsJsonObject().get("shop").getAsJsonObject().get("shop_id"), "店铺 id 为空");
				Assert.assertNotNull(resp.get("data").getAsJsonObject().get("shop").getAsJsonObject().get("seat_name"), "桌位名称为空");
				Assert.assertNotNull(resp.get("data").getAsJsonObject().get("shop").getAsJsonObject().get("seat_id"), "桌位 id 为空");
				
				
//				String nameString = CommonUtil.convertStringEncode(shopPra.get("shopName")); 
//				Assert.assertEquals(resp.get("data").getAsJsonObject().get("shop").getAsJsonObject().get("shop_name").getAsString(), shopPra.get("shopName"), "店铺名称不吻合");
				Assert.assertEquals(resp.get("data").getAsJsonObject().get("shop").getAsJsonObject().get("shop_id").getAsString(), shopPra.get("shopId"), "店铺 id 不吻合");
//				Assert.assertEquals(resp.get("data").getAsJsonObject().get("shop").getAsJsonObject().get("seat_name").getAsString(), shopPra.get("seatName"), "桌位名称不吻合");
				Assert.assertEquals(resp.get("data").getAsJsonObject().get("shop").getAsJsonObject().get("seat_id").getAsString(), shopPra.get("seatId"), "桌位 id 不吻合");
			}
		}
		
		
	}
	
	
	/**
	 * 店铺分享URL的信息（包括图片、文案）接口
	 * GET /share/v1/info
	 * 对于四种流程均相同
	 */
	@Test(description = "店铺分享URL的信息（包括图片、文案）接口", dataProvider = "shareForShopTest",
			dataProviderClass = QRCodeTestData.class, groups = {"smoke", "all"})
	public void shareForShopTest(String description, Map<String, String> query, int responseStatus, int resultCode, int jobStatus) throws Exception{
		
		Map<String, String> shopPra = shopProperties.getShopProperties();
		if (Environment.publish == environment){
			httpClientService.setHttps();
		}
		
		
		List<String> path = new ArrayList<String>();
		path.add("shop/v1");
		path.add((String)shopPra.get("entityIdForWJ"));
		path.add("share");
		
		// 计算 URL 并发送 Get 请求
		String protocol = (Environment.publish == environment) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(environment).get("serverURL"), path, query, protocol);
		Response response = httpClientService.doGet(url, null);
		Assert.assertEquals(response.getStatus(), responseStatus, "店铺分享URL的信息接口返回状态验证未通过");
		
		if(responseStatus == 200){
			JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(resp.get("code").getAsInt(), resultCode);
			
			if(jobStatus == 200){
				Assert.assertNotNull(resp.get("data").getAsJsonObject().get("moment").getAsJsonObject().get("title"), "title 字段不存在");
				Assert.assertNotNull(resp.get("data").getAsJsonObject().get("friend").getAsJsonObject().get("memo"), "memo 字段不存在");
			}
		}

		
	}
	
	
}
