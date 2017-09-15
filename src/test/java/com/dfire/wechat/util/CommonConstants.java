package com.dfire.wechat.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.apache.log4j.Logger;

import bsh.This;

public class CommonConstants {
	private static final Logger logger = Logger.getLogger(CommonConstants.class);
	
	/**测试代码中用到的常量定义*/
	public static final String resourcePath = null;
	public static final String DEFAULT_CONFIG = "config.properties";
	public static final String DEFAULT_HOST;
	
	public static final String BOSS_API_HOST;

	public static final String JDBCURL;
	public static final String JDBCURL_QRCODE;
	public static final String JDBCURL_ORDER;
	public static final String JDBCURL_SEAT;

	public static final boolean isWhitelist;

	
	public static final String HEADER_HOST;
	public static final boolean HTTPS;

	public static final boolean SUB_DOMAIN;
	public static final boolean WITHOUT_HOST;
	
	public static final String jettyPort;
	
	// redis configuration 
	public static final String redisHost;
	public static final String redisDB;
	public static final String cartIndex;
	
	// token and session in redis
	public static final String tokenKey1;
	public static final String value1;
	
	public static final String sessionKey1;
	public static final String sessionBody1;
	
	
	public static final String tokenKey2;
	public static final String value2;
	
	public static final String sessionKey2;
	public static final String sessionBody2;
	
	public static final String unionID;
	public static final String unionIDOther;
	
	public static final String menuId;
	public static final String UID;
	public static final String UIDOther;
	
	// 店铺 ID
	public static final String entityId;
	public static final String signKey;
	
	public static final String entityIdForPiDan;
	// 桌位码 code
	public static final String seatCode;
	public static final String seatCodeForBuss;
	
	// 店铺数据
	public static String shopName;
	public static final String shopId;
	public static final String seatName;
	public static final String seatId;
	
	// 菜单 ID  
	public static final String suit2MenuId;
	public static final String hotSteakMenuId;
	public static final String cabbageMenuId;
	public static final String fruitSaladMenuId;
	
	public static final String cucumberMenuId;
	public static final String cokeMenuId;
	public static final String suit3MenuId;
	
	public static final String sugarMeatId;
	
	public static final String peanutMenuId;
	public static final String kindMenuIdForPeanut;
	
	public static final String DongpoPorkMenuId;
	public static final String DongpoPorkMakeId;
	public static final String DongpoPorkSpecDetailId;
	
	// boss basic config
	public static final String prePayConfigForSeatCode;
	public static final String prePayConfigForShop;
	
	public static final String prePayConfigForSeatCodeWithPiDan;
	public static final String prePayConfigForShopWithPiDan;
	
	public static String menuConfigForMenuName;
	public static String menuConfigForTagOfMenu;
	
	public static final String menuConfigForMenuId;
	public static final String menuConfigForKindMenuId;
	
	public static final String menuConfigForMenuPrice;
	
	// for cash
	public static final String cashHost;
	public static final String cashHeartHost;
	public static final String appSecret;
	
	public static final String juice;
	
	
	
	
	static{
		Properties properties = new Properties();
//		resourcePath = ClassLoader.getSystemResource("").getPath();
//		logger.info("the resources path is: " + resourcePath);

		try {
			//获取该环境下的配置项
//			InputStream is = new FileInputStream(resourcePath + DEFAULT_CONFIG );
			InputStream is = CommonConstants.class.getClass().getResourceAsStream("/config.properties");
			InputStreamReader inputStreamReader = new InputStreamReader(is, "UTF-8");  // convert byte encoding to chacarter encoding
//			logger.info("parse config file as: " + (resourcePath + DEFAULT_CONFIG ));
			try {
				properties.load(inputStreamReader);
			} finally {
				is.close();
				inputStreamReader.close();
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
		
		DEFAULT_HOST = (String)properties.get("DEFAULT_HOST");
		BOSS_API_HOST = (String)properties.get("BOSS_API");
		
		JDBCURL = (String)properties.get("jdbc_url");
		JDBCURL_QRCODE = (String)properties.get("jdbc_url_qrcode");
		JDBCURL_ORDER = (String)properties.get("jdbc_url_order");
		JDBCURL_SEAT = (String)properties.get("jdbc_url_seat");
		
		jettyPort = (String)properties.get("jettyPort");
		
		redisHost = (String)properties.get("cache.redis.server");
		redisDB = (String)properties.get("cache.redis.database");
		cartIndex = (String)properties.get("cartcache.redis.database");
		
		
		// token and session in redis
		tokenKey1 = (String)properties.get("wenjingTokenKey1");
		value1 = (String)properties.get("wenjingValue1");
		
		sessionKey1 = (String)properties.get("wenjingSessionKey1");
		sessionBody1 = (String)properties.get("wenjingSessionBody1");
		
		
		tokenKey2 = (String)properties.get("wenjingTokenKey2");
		value2 = (String)properties.get("wenjingValue2");
		
		sessionKey2 = (String)properties.get("wenjingSessionKey2");
		sessionBody2 = (String)properties.get("wenjingSessionBody2");
		
		unionID = (String)properties.get("unionIDForWJ");
		unionIDOther = (String)properties.get("unionid");
		
		menuId = (String)properties.get("menuIdForWJ");
		UID = (String)properties.get("uidForWJ");
		UIDOther = (String)properties.get("uid");
		
		entityId = (String)properties.get("entityIdForWJ");
		signKey = (String)properties.get("signKeyForWJ");
		seatCode =  (String)properties.get("seatCodeForWJ");
		seatCodeForBuss =  (String)properties.get("seatCodeForBuss");
		
		
		entityIdForPiDan = (String)properties.get("entityId");
		
		
		// boss basic config
		prePayConfigForSeatCode =  (String)properties.get("prePayConfigForSeatCode");
		prePayConfigForShop =  (String)properties.get("prePayConfigForShop");
		
		prePayConfigForSeatCodeWithPiDan =  (String)properties.get("bossConfigForPrePaySeat");
		prePayConfigForShopWithPiDan =  (String)properties.get("bossConfigForPrePayShop");
		
				
		menuConfigForMenuId = (String)properties.get("menuIdForBoss");
		menuConfigForKindMenuId = (String)properties.get("kindMenuIdForBoss");
		
		menuConfigForMenuPrice = (String)properties.get("menuPrice");
		
		
		// 店铺数据
		try{
			
			// utf-8 is the encoding type of platform
			shopName = new String(properties.getProperty("shopName").getBytes("ISO-8859-1"), "UTF-8");
			menuConfigForMenuName = new String(properties.getProperty("menuName").getBytes("ISO-8859-1"), "UTF-8");
			menuConfigForTagOfMenu = new String(properties.getProperty("tagOfMenu").getBytes("ISO-8859-1"), "UTF-8");
			
		}catch(Exception e){
			logger.error(e.toString());
		}
		
		shopId = (String)properties.get("shopId");
		seatName =  (String)properties.get("seatName");
		seatId =  (String)properties.get("seatId");
		
		// 菜单ID
		suit2MenuId = (String)properties.get("suit2MenuId");
		hotSteakMenuId = (String)properties.get("hotSteakMenuId");
		cabbageMenuId =  (String)properties.get("cabbageMenuId");
		fruitSaladMenuId =  (String)properties.get("fruitSaladMenuId");
		
		cucumberMenuId = (String)properties.get("cucumberMenuId");
		cokeMenuId =  (String)properties.get("cokeMenuId");
		suit3MenuId =  (String)properties.get("suit3MenuId");
		
		sugarMeatId =  (String)properties.get("sugarMeatId");
		
		// 东坡肉菜品及其配料 ID
		peanutMenuId = (String)properties.get("peanutMenuId");
		kindMenuIdForPeanut = (String)properties.get("kindMenuIdForPeanut");
		
		DongpoPorkMenuId =  (String)properties.get("DongpoPorkMenuId");
		DongpoPorkMakeId =  (String)properties.get("DongpoPorkMakeId");
		DongpoPorkSpecDetailId =  (String)properties.get("DongpoPorkSpecDetailId");
		
		
		// for cash 
		cashHost =  (String)properties.get("CASH_HOST");
		cashHeartHost =  (String)properties.get("CASH_HEART_HOST");
		appSecret = (String)properties.get("appSecret");
				
				
		String whitelist = (String)properties.get("WHITELIST");
		if (whitelist!=null) {
			if (whitelist.equalsIgnoreCase("true")) {
				isWhitelist = true;
			}else {
				isWhitelist = false;
			}
		}else {
			isWhitelist = false;
		}

	
		//是否用https形式进行请求--------------xianmao  String https = (String)properties.get("https");
		String https = (String)properties.get("http");
		if (https!=null) {
			if (https.equalsIgnoreCase("true")) {
				HTTPS = true;
			}else {
				HTTPS = false;
			}
		}else {
			HTTPS = false;
		}
		
		
		//是否用子域名形式进行请求
		String sub_domain = (String)properties.get("SUB_DOMAIN");
		if (sub_domain!=null) {
			if (sub_domain.equalsIgnoreCase("true")) {
				SUB_DOMAIN = true;
			}else {
				SUB_DOMAIN = false;
			}
		}else {
			SUB_DOMAIN = false;
		}
		//请求中是否带host头
		String without_host = (String)properties.get("WITHOUT_HOST");
		if (without_host!=null) {
			if (without_host.equalsIgnoreCase("true")) {
				WITHOUT_HOST = true;
			}else {
				WITHOUT_HOST = false;
			}
		}else {
			WITHOUT_HOST = false;
		}
		HEADER_HOST = (String)properties.get("HEADER_HOST");
		
		
		// for pidan menu
		juice = (HTTPS) ? "00067404598af8070159ab95a6934d38":"9992834557daa8370157daf80be70014";
	
	}
	
	
	// just for test
	public static void main(String argus[]){
		
		CommonConstants constants = new CommonConstants();
		Properties pros = new Properties();

		try {
			
			//获取该环境下的配置项
			InputStream stream = new FileInputStream(resourcePath + DEFAULT_CONFIG );
//			InputStreamReader inputStreamReader = new InputStreamReader(constants.getClass().getClassLoader().getResourceAsStream("config.properties"), "UTF-8");
			InputStreamReader inputStreamReader = new InputStreamReader(stream, "UTF-8");
//			BufferedReader buffer = new BufferedReader(inputStreamReader);
			
			logger.info("parse config file as: " + (resourcePath + DEFAULT_CONFIG ));
			
			try {
				pros.load(inputStreamReader);
			} finally {
				stream.close();
				inputStreamReader.close();
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
		try {
			String name = new String(pros.getProperty("shopName").getBytes("ISO-8859-1"), "UTF-8");
			logger.info("the name is : " + name);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		String shopName = pros.get("shopName").toString();
		String seatId = (String)pros.get("seatId");
		
		logger.info("the shopName is : " + shopName);
		logger.info("the seatId is : " + seatId);
		
	}
	

}
