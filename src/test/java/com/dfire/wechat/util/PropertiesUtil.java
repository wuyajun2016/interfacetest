package com.dfire.wechat.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.apache.log4j.Logger;


public class PropertiesUtil {

	private static final Logger logger = Logger.getLogger(PropertiesUtil.class);
	private static PropertiesUtil propertiesUtil;
	private Properties properties;
	
	private PropertiesUtil(){
				
		try {
			
			properties = new Properties();
			
//			String resourcePath = ClassLoader.getSystemResource("").getPath();
//			InputStream in = new FileInputStream(resourcePath + "config.properties" );
//			logger.info("the resource path is : " + resourcePath + "config.properities");
			
			InputStream in = PropertiesUtil.class.getClass().getResourceAsStream("/config.properties");
//			InputStreamReader inputStreamReader = new InputStreamReader(in, "UTF-8");  // convert byte encoding to chacarter encoding
//			InputStream in = Object.class.getResourceAsStream("/config.properties");
//			logger.info("the inputStream is : " + in);
			BufferedReader bf = new BufferedReader(new InputStreamReader(in)); 
			
			
				
			properties.load(bf);
//			properties.load(inputStreamReader);
//			properties.load(in);
			
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	
	public static PropertiesUtil getPropertiesUtil(){
		if (propertiesUtil==null) {
			propertiesUtil = new PropertiesUtil();
		}
		return propertiesUtil;
		
	}
	
	
	public String getValue(String key){
		return (String) properties.get(key);
	}
	
	
}
