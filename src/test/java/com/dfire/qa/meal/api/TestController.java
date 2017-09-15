package com.dfire.qa.meal.api;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.dfire.qa.meal.bean.Response;
import com.dfire.qa.meal.data.CartTestData;
import com.dfire.qa.meal.enums.Environment;
import com.dfire.qa.meal.httpclient.HTTPClientService;
import com.dfire.qa.meal.logger.LoggerMarkers;
import com.dfire.qa.meal.logger.MealLoggerFactory;
import com.dfire.qa.meal.props.HostProperties;
import com.dfire.qa.meal.service.ICartService;
import com.dfire.qa.meal.service.IQRCodeService;
import com.dfire.qa.meal.utils.CommonUtil;
import com.dfire.qa.meal.utils.HTTPRequestUtil;
import com.dfire.qa.meal.utils.PathForHTTP;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class TestController extends BaseTestController{
	
	@Resource
	HostProperties hostProperties;
	
	@Resource
	HTTPClientService httpClientService;
	
	@Resource
	IQRCodeService qrCodeService;
	
	@Resource
	ICartService cartService;
		


	
	@Test(description = "test ")
	public void javaTest() throws Exception{
		
		List<String> oneList = new ArrayList<String>();
		oneList.add("apple");
		oneList.add("pea");
		oneList.add("banana");
		
		List<String> twoList = new ArrayList<String>();
		twoList.add("tea");
		twoList.add("bottle");
		twoList.add("pen");
		
		List<String> combineList = new ArrayList<String>();
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, "combine is : " + combineList);
		
		combineList.addAll(oneList.subList(0, oneList.size()-1));
		
		combineList.addAll(twoList.subList(0, 1));
		
		
		UUID uuid = UUID.randomUUID();
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, "the uuid is : " + uuid.toString());
		
		String password = "q";
		String encodeString = CommonUtil.encodeWithAlgorithm(password.toUpperCase(), "MD5");
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, "encoded string is :" + encodeString );
		

		
	}
	

}
