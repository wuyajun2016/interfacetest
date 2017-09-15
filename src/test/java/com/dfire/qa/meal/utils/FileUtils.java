package com.dfire.qa.meal.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Properties;

import com.dfire.qa.meal.enums.Environment;
import com.dfire.qa.meal.logger.LoggerMarkers;
import com.dfire.qa.meal.logger.MealLoggerFactory;


public class FileUtils {
	
	
	/**
	 * 保存配置文件
	 * @return
	 */
	public static Boolean saveProprityFile(String path, String userName, Environment env, String propertyInfo) throws Exception{
		
		try {
			
			// 判断目录存在与否
			File file = new File(path);
			if(!file.exists())
				file.mkdir();
						
			
			// 获取输入流
			InputStream is = new ByteArrayInputStream(propertyInfo.getBytes());			
			
			int len = 0;
			byte[] tempArray = new byte[1024];            
           
            // 创建输出流
         	OutputStream os = new FileOutputStream(path +  userName + "-" + env.getLiteral() + ".properties");
         	
         	while((len = is.read(tempArray)) > 0){
         		
         		os.write(tempArray, 0, len);
         	}
         	
         	// 释放资源
         	is.close();
         	os.close();
         	
		} catch (IOException ioException) {
			
			MealLoggerFactory.LOG_FILES_LOGGER.error(LoggerMarkers.LOGFILE, ioException.toString());
			return false;
			
		}catch (Exception exception) {
			
			MealLoggerFactory.LOG_FILES_LOGGER.error(LoggerMarkers.LOGFILE, exception.toString());
			return false;
		}
		
		return true;
	}
	
	
	
	
	/**
	 * 读取配置文件, 获取配置文件对象
	 * @return
	 * @throws Exception
	 */
	public static Properties getProperties(String path) throws Exception{
		
		Properties properties = new Properties();
		
		try {
			
			InputStream is = new FileInputStream(path);
			InputStreamReader inputStreamReader = new InputStreamReader(is, "UTF-8");  // convert byte encoding to chacarter encoding

			try {
				
				properties.load(inputStreamReader);
				
			} finally {
				is.close();
				inputStreamReader.close();
			}
			
		} catch (Throwable t) {
			
			t.printStackTrace();
		}
		
		return properties;
	}

}
