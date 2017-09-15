package com.dfire.qa.meal.utils;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.apache.log4j.Logger;


public class CommonConstants {
	
	private static final Logger logger = Logger.getLogger(CommonConstants.class);
	
	/**测试代码中用到的常量定义*/
	public static final String resourcePath = null;
	public static final String DEFAULT_CONFIG = "config.properties";

	public static final boolean isWhitelist;

	public static final String DEFAULT_HOST;
	public static final String HEADER_HOST;
	public static final boolean HTTPS;

	public static final boolean SUB_DOMAIN;
	public static final boolean WITHOUT_HOST;
	
	public static final String unionID;
	public static final String unionIDOther;
	
	public static final String menuId;
	public static final String UID;
	public static final String UIDOther;
	
	// 店铺 ID
	public static final String entityId;	
	public static final String currentEnv;
	
	// 桌位码 code
	public static final String seatCode;
	public static final String seatCodeForBuss;
	
	// 店铺数据
	public static String shopName;
	public static final String shopId;
	public static final String seatName;
	public static final String seatId;

	
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
	
	public static final String juice;
	
	////////////////////   boss-client infra set     ////////////////////////////////

	public static final String showMsg;
	public static final String showMsgNoUp;
	
	public static final String showMsgCold;
	public static final String showMsgTakeOut;
	public static final String prePaySeat;
	
	public static final String prePayShop;
	
	public static final String menuCodeButtonFlag;
	
	
	//////////////////  boss-client style set     //////////////////////////////////
	public static final String backGroundStyle;
	public static final String iconStyle;
	
	
	/////////////////   boss-member-privilege set   ////////////////////////////////
	
	public static final String couponName;
	public static final String fruitSaladMenuId;
	
	static{
		Properties properties = new Properties();
		String resourcePath = ClassLoader.getSystemResource("").getPath();
		logger.info("the resources path is: " + resourcePath);

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
		currentEnv = (String)properties.get("currentEnv");
		
		unionID = (String)properties.get("unionIDForWJ");
		unionIDOther = (String)properties.get("unionid");
		
		menuId = (String)properties.get("menuIdForWJ");
		UID = (String)properties.get("uidForWJ");
		UIDOther = (String)properties.get("uid");
		
		entityId = (String)properties.get("entityIdForWJ");
		seatCode =  (String)properties.get("seatCodeForWJ");
		seatCodeForBuss =  (String)properties.get("seatCodeForBuss");
			
		
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
			
		
		//////////////////////   boss-client infra set    ////////////////////////////////
		showMsg = (String)properties.get("showMsg");
		showMsgNoUp = (String)properties.get("showMsgNoUp");
		
		showMsgCold = (String)properties.get("showMsgCold");
		showMsgTakeOut = (String)properties.get("showMsgTakeOut");
		prePaySeat = (String)properties.get("prePaySeat");
		
		prePayShop = (String)properties.get("prePayShop");

		menuCodeButtonFlag = (String)properties.get("menuCodeButtonFlag");
		
		
		////////////////////////   boss-client style set       //////////////////////
		backGroundStyle = (String)properties.get("backGroundStyle");
		iconStyle = (String)properties.get("iconStyle");
		
		
		////////////////////////  boss-member-privilege        ///////////////////////
		
		couponName = (String)properties.get("couponName");
		fruitSaladMenuId = (String)properties.get("fruitSaladMenuId");
		
		
		
		

		
		
		
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

	
		//是否用https形式进行请求
		String https = (String)properties.get("https");
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
