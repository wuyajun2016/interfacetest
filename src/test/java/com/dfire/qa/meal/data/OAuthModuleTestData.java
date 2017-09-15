package com.dfire.qa.meal.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.dfire.qa.meal.bean.BaseParamBean;
import com.dfire.wechat.util.BeanProvider;
import com.dfire.wechat.util.MD5Utils;

public class OAuthModuleTestData extends BaseTestData{
	
	

	@DataProvider(name = "oAuthTest")
	public static Object [][] oAuthTest(){
		
		
		String description1 = "description: 正常用例";
		BaseParamBean baseParamBean1 = new BaseParamBean();
		baseParamBean1.setXtoken(token);
		baseParamBean1.setEntityId(entityId);
		baseParamBean1.setSeatCode(seatCode);
		


		String description2 = "description: signKey 为空";
		
		
	
		String description3 = "description: token 非法";
		BaseParamBean baseParamBean3 = new BaseParamBean();
		baseParamBean3.setXtoken("577fhfhjgkeurg");
		baseParamBean3.setEntityId(entityId);
		baseParamBean3.setSeatCode(seatCode);
		

		
		String description4 = "description: entityId 非法";
		BaseParamBean baseParamBean4 = new BaseParamBean();
		baseParamBean4.setXtoken(token);
		baseParamBean4.setEntityId("9090");
		baseParamBean4.setSeatCode(seatCode);
		
		

		String description5 = "description: seat_code 非法无效";
		BaseParamBean baseParamBean5 = new BaseParamBean();
		baseParamBean5.setXtoken(token);
		baseParamBean5.setEntityId(entityId);
		baseParamBean5.setSeatCode("7h8ugh8949");
		

		
		return new Object[][]{
				
				{description1, baseParamBean1, 200},
//				{description2, baseParamBean2, 200},
				{description3, baseParamBean3, 200},
				
				{description4, baseParamBean4, 200},
				{description5, baseParamBean5, 200},



		};
	}
	
			
			
	@DataProvider(name = "oAuthConsumerCodeTest")
	public static Object [][] oAuthConsumerCodeTest(){
		
		String description1 = "description: 消费码正常";
		BaseParamBean baseParamBean1 = new BaseParamBean();
		baseParamBean1.setXtoken(token);	
		String consumerCode1 = "8a669efd6d2fb3a8ebca2356c57a1ea2"; 

				
		String description2 = "description: 消费码 无效";
		BaseParamBean baseParamBean2 = new BaseParamBean();
		baseParamBean2.setXtoken(token);		
		String consumerCode2 = "201708051050043501331811"; 
		
	
		String description3 = "description: token 非法";
		BaseParamBean baseParamBean3 = new BaseParamBean();
		baseParamBean3.setXtoken("577fhfhjgkeurg");
		String consumerCode3 = "201608051050043501331811"; 
		
		
	
//		url4.add("201608051050043501331811");   
		// 该用例无效
		String description4 = "description: 消费码 包含特殊字符";
		BaseParamBean baseParamBean4 = new BaseParamBean();
		baseParamBean4.setXtoken(token);
		String consumerCode4 = "20160805105004350133*&^1"; 
		
	
		return new Object[][]{
				
				{description1, baseParamBean1, consumerCode1, 200},
				{description2, baseParamBean2, consumerCode2, 200},
				
				{description3, baseParamBean3, consumerCode3, 200},
//				{description4, baseParamBean4, consumerCode4, 200},


		};
	}
			
			
			
	@DataProvider(name = "oAuthEntityIdTest")
	public static Object [][] oAuthEntityIdTest(){
		
		String description1 = "description: 正常用例";
		BaseParamBean baseParamBean1 = new BaseParamBean();
		baseParamBean1.setXtoken(token);	
		baseParamBean1.setEntityId(entityId);		

		
		String description2 = "description: token 非法";		
		BaseParamBean baseParamBean2 = new BaseParamBean();
		baseParamBean2.setXtoken("577fhfhjgkeurg");	
		baseParamBean2.setEntityId(entityId);
		
				
		
		String description3 = "description: entityId 非法";
		BaseParamBean baseParamBean3 = new BaseParamBean();
		baseParamBean3.setXtoken(token);	
		baseParamBean3.setEntityId("9090");
			
		
		
		return new Object[][]{
				
				{description1, baseParamBean1, 200},				
				{description2, baseParamBean2, 200},
				{description3, baseParamBean3, 200},
				

		};
	}
			
			
			
	@DataProvider(name = "oAuthMenuCodeTest")
	public static Object [][] oAuthMenuCodeTest(){
		
		String description1 = "description: 正常用例";
		BaseParamBean baseParamBean1 = new BaseParamBean();
		baseParamBean1.setXtoken(token);	
		baseParamBean1.setEntityId(entityId);
		Boolean correctMenu1 = true;
		

		
		String description3 = "description: token 非法";
		BaseParamBean baseParamBean2 = new BaseParamBean();
		baseParamBean2.setXtoken("577fhfhjgkeurg");	
		baseParamBean2.setEntityId(entityId);
		Boolean correctMenu2 = true;
	
		
		
		String description4 = "description: entityId 无效";
		BaseParamBean baseParamBean3 = new BaseParamBean();
		baseParamBean3.setXtoken(token);	
		baseParamBean3.setEntityId("9090");
		Boolean correctMenu3 = true;
		
				

		String description5 = "description:  menuId 无效";
		BaseParamBean baseParamBean4 = new BaseParamBean();
		baseParamBean4.setXtoken(token);	
		baseParamBean4.setEntityId(entityId);
		Boolean correctMenu4 = false;

		
		/**
		 * 代码覆盖到即可, 无须对页面进行校验, 该用例破坏代码通用性,可后需考虑
		 */
		String description6 = "description: signKey 无效";
		BaseParamBean baseParamBean6 = new BaseParamBean();
		baseParamBean6.setXtoken(token);	
		baseParamBean6.setEntityId(entityId);
		Boolean correctMenu6 = false;
		
		
		return new Object[][]{
				
				{description1, baseParamBean1, correctMenu1, 200},				
				{description3, baseParamBean2, correctMenu2, 200},
				{description4, baseParamBean3, correctMenu3, 200},
				
				{description5, baseParamBean4, correctMenu4, 200},				
//				{description6, baseParamBean6, correctMenu6, 200},


		};
	}
	
	
	/**
	@DataProvider(name = "oAuthCallbackTest")
	public static Object [][] oAuthCallbackTest(){
		
		String description1 = "description: 正常用例";

		String description2 = "description:  qr_code 为空";
				
		String description3 = "description: entityId 无效";
		
		String description4 = "description: menuId 无效";
		
		// 代码覆盖到即可, 无须对页面进行校验
		String description5 = "description:signKey 无效";
		

		return new Object[][]{
				{description1, 200},
				{description2, 200},
				{description3, 200},
				{description4, 200},
				{description5, 200},


		};
	}
	*/
	
			
	
	/**
	@DataProvider(name = "aliShopOrderTest")
	public static Object [][] aliShopOrderTest(){
		
		String description1 = "description: merchant_pid 无效, shop_id 无效";
		Map<String, String> query1 = new HashMap<String, String>();
		query1.put("xtoken", xtoken[1]);
		query1.put("merchant_pid", BeanProvider.getUUIDName());
		query1.put("shop_id", BeanProvider.getUUIDName());
		
	

		String description2 = "description: token 正常, merchant_pid 无效, shop_id 无效, user_agent 为支付宝, 出现错误页面";
		Map<String, String> query2 = new HashMap<String, String>();
		query2.put("xtoken", xtoken[1]);
		query2.put("merchant_pid", BeanProvider.getUUIDName());
		query2.put("shop_id", BeanProvider.getUUIDName());
		
		
		return new Object[][]{
				{description1, query1, 200},
				{description2, query2, 200},


		};
	}
	*/
			

}
