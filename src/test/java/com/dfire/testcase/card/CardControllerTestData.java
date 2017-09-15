package com.dfire.testcase.card;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;

import com.dfire.testcase.function.util.base.WechatBaseUtil;
import com.dfire.wechat.util.BeanProvider;
import com.dfire.wechat.util.CommonConstants;
import com.dfire.wechat.util.HttpRequestEx;

public class CardControllerTestData {

private static final Logger logger = Logger.getLogger(CardControllerTestData.class);
	
	// 薯条内网测试店  token : c4c576cdfbc65a5c1c1355414dc3e260, entity_id : 99001331
	// 问荆内网测试店  token : f63928b42228b3d9b7da6ff06461606f ,entity_id : 99927792   
	// 问荆内网测试店2  token :  ,entity_id : 99927836
	// 普洱提供 token : 05a17071a19397ab43cfcd7ff7dde32c    貌似不起作用
	private static final String[] xtoken = {"c4c576cdfbc65a5c1c1355414dc3e260", "54452c7d6322ff78056424b22666bd1b", 
		"05a17071a19397ab43cfcd7ff7dde32c", "1b60096f549717236f500663093062cf"};
	
	private static String token;

	static{
		
		HttpRequestEx httpRequest = new HttpRequestEx(CommonConstants.DEFAULT_HOST, CommonConstants.HTTPS);
		token = WechatBaseUtil.getToken(httpRequest, CommonConstants.entityId, CommonConstants.unionID);
		httpRequest.ShutDown();
		
	}
	
	@DataProvider(name = "ApplyCardTest")
	public static Object [][] ApplyCardTest(){
		
		String description1 = "前置条件: token 正常, entityId 正常, card_id 为自动生成的 UUID, message 提示：“会员已拥有该卡”";
		Map<String, String> query1 = new HashMap<String, String>();
		query1.put("xtoken", token);
		query1.put("card_id", BeanProvider.getUUIDName());
		query1.put("entity_id", "99927792");
		

		String description2 = "前置条件: token 正常, entity_id 正常, card_id 字段不存在, message 提示：“请求失败”";
		Map<String, String> query2 = new HashMap<String, String>();
		query2.put("xtoken", token);
		query2.put("entity_id", "99927792");
		
		String description3 = "前置条件: token 非法, entity_id 正常, card_id 为自动生成的 UUID, message 提示：“请先授权”";
		Map<String, String> query3 = new HashMap<String, String>();
		query3.put("xtoken", "577fhfhjgkeurg");
		query3.put("entity_id", "99927792");
		query3.put("card_id", BeanProvider.getUUIDName());
		
		// 该用例存疑？？？？？？？？？？？？？？？？？？？？？？？
		String description4 = "前置条件: token 正常, entityId 非法, card_id 为自动生成的 UUID, message 提示：“会员已拥有该卡”";
		Map<String, String> query4 = new HashMap<String, String>();
		query4.put("xtoken", token);
		query4.put("entity_id", "9090");
		query4.put("card_id", BeanProvider.getUUIDName());
		
		// 
		String description5 = "前置条件: token 正常, entityId 正常, card_id 为自动生成的 UUID, message 提示：“会员已拥有该卡”";
		Map<String, String> query5 = new HashMap<String, String>();
		query5.put("xtoken", token);
		query5.put("entity_id", "99001331");
		query5.put("card_id", BeanProvider.getUUIDName());
		
		return new Object[][]{
				
				{description1, query1, 200, 1, 400},
				{description2, query2, 200, 9999, 400},
				{description3, query3, 200, -1, 400},
				
				{description4, query4, 200, -1, 400},
				{description5, query5, 200, 1, 400},


		};
	}
	
	
	@DataProvider(name = "CardListTest")
	public static Object [][] CardListTest(){
		
		String description1 = "description: token 正常, entityId 正常并且对应存在 card, message 提示：“请求成功”";
		Map<String, String> query1 = new HashMap<String, String>();
		query1.put("xtoken", xtoken[1]);
		query1.put("entity_id", "99001331");
		

		String description2 = "description: token 正常, entity_id 正常 但是对应不存在card,  message 提示：“请求成功”";
		Map<String, String> query2 = new HashMap<String, String>();
		query2.put("xtoken", xtoken[1]);
		query2.put("entity_id", "99927792");
		
		
		String description3 = "description: token 非法, entity_id 正常并且对应存在 card,   message 提示：“请先授权”";
		Map<String, String> query3 = new HashMap<String, String>();
		query3.put("xtoken", "577fhfhjgkeurg");
		query3.put("entity_id", "99001331");
		

		String description4 = "description: token 正常, entityId 字段不存在, message 提示：“请求失败”";
		Map<String, String> query4 = new HashMap<String, String>();
		query4.put("xtoken", xtoken[1]);
		

		String description5 = "description: token 正常, entityId 非法, message 提示：“请求成功”, 但是在 data 当中没有数据";
		Map<String, String> query5 = new HashMap<String, String>();
		query5.put("xtoken", xtoken[1]);
		query5.put("entity_id", "990");
		
		return new Object[][]{
				{description1, query1, 200, 1, 200},
				{description2, query2, 200, 1, 400},
				{description3, query3, 200, -1, 400},
				{description4, query4, 200, 9999, 400},
				{description5, query5, 200, 1, 400},


		};
	}
	
	
	@DataProvider(name = "DeleteCardTest")
	public static Object [][] DeleteCardTest(){
		
		String description1 = "description: token 正常, cardId 无效, message 提示：“没有对应的Card记录. cardId:3434” ";
		Map<String, String> query1 = new HashMap<String, String>();
		query1.put("xtoken", xtoken[1]);
		String cardId1 = "3434";
		

		// 该用例需要考虑到在 会员缓存中写入相关的数据，，需要了解具体是在那个缓存的数据库中写入数据
		String description2 = "description: token 正常, cardId 有效,  message 提示：“请求成功”";
		Map<String, String> query2 = new HashMap<String, String>();
		query2.put("xtoken", xtoken[3]);
		String cardId2 = "123455677889";
		
		
		String description3 = "description: token 非法, cardId 有效,   message 提示：“请先授权”";
		Map<String, String> query3 = new HashMap<String, String>();
		query3.put("xtoken", "577fhfhjgkeurg");
		String cardId3 = "123455677889";
		

		String description4 = "description: token 正常, cardId 为null, message 提示：“没有对应的Card记录. cardId:null”";
		Map<String, String> query4 = new HashMap<String, String>();
		query4.put("xtoken", xtoken[1]);
		String cardId4 = null;

		// 在 URL 中的字符带有特殊字符时会直接抛出异常, 此用例可忽略
		String description5 = "description: token 正常, cardId 非法, message 提示：“请求成功”";
		Map<String, String> query5 = new HashMap<String, String>();
		query5.put("xtoken", xtoken[1]);
		String cardId5 = "yut#$%^&";
		
		return new Object[][]{
				{description1, query1, cardId1, 200, 1000, 400},
//				{description2, query2, cardId2, 200, 1, 400},
				{description3, query3, cardId3, 200, -1, 400},
    			{description4, query4, cardId4, 200, 1000, 400},
//				{description5, query5, cardId5, 200, 1000, 400},


		};
	}

}
