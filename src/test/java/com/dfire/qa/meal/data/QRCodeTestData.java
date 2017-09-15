package com.dfire.qa.meal.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.dfire.qa.meal.utils.CommonConstants;
import com.dfire.qa.meal.utils.MD5Utils;
import com.dfire.testcase.function.util.base.WechatBaseUtil;
import com.dfire.wechat.util.BeanProvider;
import com.dfire.wechat.util.HttpRequestEx;



public class QRCodeTestData extends BaseTestData{
	

	
	@DataProvider(name = "oauthQRCodeTest")
	public static Object [][] oauthQRCodeTest(){
				
		////////////////////////////////////////   桌码用例        ///////////////////////////////////////////////////////////////
		// 扫桌码，参数正常
		String description1 = "description: entityId 正常, seatCode正常, signKey正常, userAgent为微信, message 提示：";	
		List<String> path1 = new ArrayList<String>();
		path1.add("s");
		path1.add(entityId);
		path1.add(seatCode);
		path1.add(signKey);
		
		
		
		// 扫桌码，桌码无效, is_valid 为 0
		String description2 = "description: entityId 正常, seatCode无效, signKey正常, userAgent为微信, message 提示：";	
		List<String> path2 = new ArrayList<String>();
		path2.add("s");
		path2.add(entityId);
		path2.add(seatCode);
		path2.add(signKey);
		
		
		
		// 扫桌码，桌码 code 发生更改
		String description3 = "description: entityId 正常, seatCode 发生更改, signKey正常, userAgent为微信, message 提示: ";	
		List<String> path3 = new ArrayList<String>();
		path3.add("s");
		path3.add(entityId);
		path3.add(seatCode);
		path3.add(signKey);
		
		
		////////////////////////////////////////   店码用例           ////////////////////////////////////////////////////////////////////		
		// 扫店码，参数正常
		String description4 = "description: 店码, entityId 正常, signKey正常, userAgent为微信, message 提示：";	
		List<String> path4 = new ArrayList<String>();
		path4.add("s");
		path4.add(entityId);
		path4.add(signKey);
		
		
		
		///////////////////////////////////////  外卖码用例         //////////////////////////////////////////////////////////////////////
		// 扫外卖码，参数正常
		String description5 = "description: 外卖码, entityId 正常, signKey正常, userAgent为微信, message 提示： ";	
		List<String> path5 = new ArrayList<String>();
		path5.add("t");
		path5.add(entityId);
		path5.add(signKey);
		
		
		
		return new Object[][]{
				
				{description1, path1, 200},
//				{description2, path2, 200},
//				{description3, path3, 200},
//				
				{description4, path4, 200},
				{description5, path5, 200},

		};
	}

	
	@DataProvider(name = "getInitDataForShopTest")
	public static Object [][] getInitDataForShopTest(){
					
		// 正常情况
		String description1 = "description: entityId 正常, seatCode正常, signKey正常, userAgent为微信, message 提示： ";	
		Map<String, String> query1 = new HashMap<String, String>();
		query1.put("xtoken", token);
		query1.put("entity_id", entityId);
		query1.put("seat_code", seatCode);
		
		
		return new Object[][]{
				{description1, query1, 200, 1, 200},

		};
	}
	
	
	@DataProvider(name = "shareForShopTest")
	public static Object [][] shareForShopTest(){				
	
		// 正常情况
		String description1 = "description: entityId 正常, seatCode正常, signKey正常, userAgent为微信, message 提示： ";	
		Map<String, String> query1 = new HashMap<String, String>();
		query1.put("xtoken", token);
		
		return new Object[][]{
				{description1, query1, 200, 1, 200},

		};
	}
	
	
	
	
}
