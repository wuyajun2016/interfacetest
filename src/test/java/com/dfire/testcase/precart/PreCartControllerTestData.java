package com.dfire.testcase.precart;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.dfire.testcase.shop.CartControllerTest;
import com.dfire.wechat.util.BeanProvider;

public class PreCartControllerTestData {
	
			// 薯条内网测试店  token : c4c576cdfbc65a5c1c1355414dc3e260, entity_id : 99001331
			// 问荆内网测试店  token:  54452c7d6322ff78056424b22666bd1b ,entity_id : 99927792   
			// 问荆内网测试店2 token : 1b60096f549717236f500663093062cf ,entity_id : 99927836

			private static final String[] xtoken = {"c4c576cdfbc65a5c1c1355414dc3e260", 
													"54452c7d6322ff78056424b22666bd1b", 
													"1b60096f549717236f500663093062cf"};
			// uid[0] 为 无效 id, 与 token 无对应关系
			private static final String[] uid = { "7539759878350350545fgf56", 
				                                  "b379d59ed79c4991a05fb2eb2a7b1c2c", 
				                                  "b379d59ed79c4991a05fb2eb2a7b1c2c"};
			
			@DataProvider(name = "modifyCartTest")
			public static Object [][] modifyCartTest(){
				
				String description1 = "description: token 正常, entityId 正常, cartSuit 正常(普通菜), 返回具体的购物车信息";
				Map<String, String> query1 = new HashMap<String, String>();
				query1.put("xtoken", xtoken[1]);
				query1.put("entity_id", "99927792");		
				String cartSuit1 = BeanProvider.getCartSuitJson(60, 1, CartControllerTest.menuIdChild, uid[1]);

				
				String description2 = "description: token 正常, entity_id 正常, seat_code 为空, order_id 为空,  message: 参数错误!";
				Map<String, String> query2 = new HashMap<String, String>();
				query2.put("xtoken", xtoken[1]);
				query2.put("entity_id", "99927792");

				
				String description3 = "description: token 非法, entity_id 正常, seat_code 正常, order_id 为空, message: 请先授权";
				Map<String, String> query3 = new HashMap<String, String>();
				query3.put("xtoken", "577fhfhjgkeurg");
				query3.put("entity_id", "99927792");

				
			
				String description4 = "description: token 正常, entityId 非法, seat_code 正常, order_id 为空, message: 购物车过期";
				Map<String, String> query4 = new HashMap<String, String>();
				query4.put("xtoken", xtoken[1]);
				query4.put("entity_id", "9090");

				
				
				String description5 = "description: token 正常, entityId 正常, seat_code 非法无效, order_id 为空, message: 购物车过期";
				Map<String, String> query5 = new HashMap<String, String>();
				query5.put("xtoken", xtoken[1]);
				query5.put("entity_id", "99927792");

				
				String description6 = "description: token 正常, entityId 包含非法字符, seat_code 正常, order_id 为空, message: 应该提示 entity_id 不能为非法字符";
				Map<String, String> query6 = new HashMap<String, String>();
				query6.put("xtoken", xtoken[1]);
				query6.put("entity_id", "9090……&&*");

				
				
				String description7 = "description: token 正常, entityId 正常, seat_code 包含非法字符, order_id 为空, message: seat_code 不能为非法字符";
				Map<String, String> query7 = new HashMap<String, String>();
				query7.put("xtoken", xtoken[1]);
				query7.put("entity_id", "99927792");

				
				return new Object[][]{
						{description1, query1, cartSuit1, 200, 1, 200},
//						{description2, query2, cartSuit2, 200, 0, 400},
//						{description3, query3, cartSuit3, 200, -1, 400},
//						{description4, query4, cartSuit4, 200, 5, 400},
//						{description5, query5, cartSuit5, 200, 5, 400},
//						{description6, query6, cartSuit6, 200, 6, 400},
//						{description5, query5, cartSuit7, 200, 6, 400},
			};
		}
			

			
			@DataProvider(name = "getMenusTest")
			public static Object [][] getMenusTest(){
				
				String description1 = "description: token 正常, entityId 正常";
				Map<String, String> query1 = new HashMap<String, String>();
				query1.put("xtoken", xtoken[1]);
				query1.put("entity_id", "99927792");		
				

				
				String description2 = "description: token 正常, entity_id 无效, message: 返回的字段包含空数据";
				Map<String, String> query2 = new HashMap<String, String>();
				query2.put("xtoken", xtoken[1]);
				query2.put("entity_id", "9992");

				
				String description3 = "description: token 非法, entity_id 正常, message: 请先授权";
				Map<String, String> query3 = new HashMap<String, String>();
				query3.put("xtoken", "577fhfhjgkeurg");
				query3.put("entity_id", "99927792");

				
				// URL 中包含特殊字符, 被 Spring 框架过滤, 该用例可忽略
				String description4 = "description: token 正常, entityId 包含特殊字符, message: 购物车过期";
				Map<String, String> query4 = new HashMap<String, String>();
				query4.put("xtoken", xtoken[1]);
				query4.put("entity_id", "9090%^%^");


				
				return new Object[][]{
						{description1, query1, 200, 1, 200},
						{description2, query2, 200, 1, 400},
						{description3, query3, 200, -1, 400},
//					    {description4, query4, 200, 5, 400},

			};
		}
			
			
			
			@DataProvider(name = "getUserCartTest")
			public static Object [][] getUserCartTest(){
				
				String description1 = "description: token 正常, entityId 正常, queue_id 正常, 接口返回正常菜单内容";
				Map<String, String> query1 = new HashMap<String, String>();
				query1.put("xtoken", xtoken[1]);
				query1.put("entity_id", "99927792");		
				query1.put("queue_id", "");
				
				
				String description2 = "description: token 正常, entity_id 正常, queue_id 无效, 接口返回正常";
				Map<String, String> query2 = new HashMap<String, String>();
				query2.put("xtoken", xtoken[1]);
				query2.put("entity_id", "99927792");
				query2.put("queue_id", BeanProvider.getUUIDName());

				
				String description3 = "description: token 非法, entity_id 正常, queue_id 正常, message: 请先授权";
				Map<String, String> query3 = new HashMap<String, String>();
				query3.put("xtoken", "577fhfhjgkeurg");
				query3.put("entity_id", "99927792");
				query3.put("queue_id", "");


							
				String description4 = "description: token 正常, entityId 非法, queue_id 正常, message: 应该提示 entityId 非法";
				Map<String, String> query4 = new HashMap<String, String>();
				query4.put("xtoken", xtoken[1]);
				query4.put("entity_id", "9090");
				query4.put("queue_id", "");
				
				
				String description5 = "description: token 正常, entityId 正常, 缺少字段 queue_id";
				Map<String, String> query5 = new HashMap<String, String>();
				query5.put("xtoken", xtoken[1]);
				query5.put("entity_id", "99927792");

				
				String description6 = "description: token 正常, entityId 包含非法字符, queue_id 正常, "
						+ "message 应该提示 entityId 包含非法字符";
				Map<String, String> query6 = new HashMap<String, String>();
				query6.put("xtoken", xtoken[1]);
				query6.put("entity_id", "9090……&&*");
				query6.put("queue_id", "");
							
				
				String description7 = "description: token 正常, 缺少字段  entityId 以及 queue_id, message 应该提示请求成功, 但是没有返回数据 ";
				Map<String, String> query7 = new HashMap<String, String>();
				query7.put("xtoken", xtoken[1]);

				
				return new Object[][]{
						{description1, query1, 200, 1, 200},
						{description2, query2, 200, 1, 200},
						{description3, query3, 200, -1, 400},
						{description4, query4, 200, 5, 400},
						{description5, query5, 200, 1, 200},
						{description6, query6, 200, 6, 400},
						{description7, query7, 200, 4, 400},
			};
		}
			
			
			
			@DataProvider(name = "importUserCartTest")
			public static Object [][] importUserCartTest(){
				
				String description1 = "description: token 正常, entityId 正常, source_type 为排队, seat_code 正常, order 正常, message: todo暂不支持";
				Map<String, String> query1 = new HashMap<String, String>();
				query1.put("xtoken", xtoken[1]);
				query1.put("source_type", "1");  // source_type 取值为 1,2
				query1.put("entity_id", "99927792");		
				query1.put("seat_code", "B1");
				query1.put("order", "");
				
				
				String description2 = "description: token 正常, entity_id 正常, source_type 为个人, seat_code 正常, order 正常, 接口返回正常";
				Map<String, String> query2 = new HashMap<String, String>();
				query2.put("xtoken", xtoken[1]);
				query2.put("source_type", "2");  // source_type 取值为 1,2
				query2.put("entity_id", "99927792");
				query2.put("seat_code", "B1");
				query2.put("order", "");

				
				String description3 = "description: token 非法, entity_id 正常, source_type 为个人, seat_code 正常, order 正常, message: 请先授权";
				Map<String, String> query3 = new HashMap<String, String>();
				query3.put("xtoken", "577fhfhjgkeurg");
				query3.put("source_type", "2");  // source_type 取值为 1,2
				query3.put("entity_id", "99927792");
				query3.put("seat_code", "B1");
				query3.put("order", "");

							
				String description4 = "description: token 正常, entityId 非法, source_type 为个人, seat_code 正常, order 正常, message: 应该提示 entityId 非法";
				Map<String, String> query4 = new HashMap<String, String>();
				query4.put("xtoken", xtoken[1]);
				query4.put("source_type", "2");  // source_type 取值为 1,2
				query4.put("entity_id", "9090");
				query4.put("seat_code", "B1");
				query4.put("order", "");

				
				
				String description5 = "description: token 正常, entityId 正常, source_type 为个人, 无 seat_code 及 order, "
						+ "message: 参数错误! 后两个字段为非必填字段,但缺少两个字段时接口返回失败";
				Map<String, String> query5 = new HashMap<String, String>();
				query5.put("xtoken", xtoken[1]);
				query5.put("source_type", "2");  // source_type 取值为 1,2
				query5.put("entity_id", "99927792");

				
				String description6 = "description: token 正常, entityId 包含非法字符, source_type 为个人, seat_code 正常, order 正常, "
						+ "message: 获取购物车数量失败, 应该提示 entityId 包含非法字符";
				Map<String, String> query6 = new HashMap<String, String>();
				query6.put("xtoken", xtoken[1]);
				query6.put("source_type", "2");  // source_type 取值为 1,2
				query6.put("entity_id", "9090……&&*");
				query6.put("seat_code", "B1");
				query6.put("order", "");

				
				
				String description7 = "description: token 正常, entityId 正常, 缺少字段 source_type, seat_code 正常, order 正常, message: 请求失败";
				Map<String, String> query7 = new HashMap<String, String>();
				query7.put("xtoken", xtoken[1]);
				query7.put("entity_id", "99927792");		
				query7.put("seat_code", "B1");
				query7.put("order", "");

				
				return new Object[][]{
						{description1, query1, 200, 0, 400},
						{description2, query2, 200, 1, 200},
						{description3, query3, 200, -1, 400},
						{description4, query4, 200, 5, 400},
						{description5, query5, 200, 1, 400},
						{description6, query6, 200, 6, 400},
						{description7, query7, 200, 9999, 400},
			};
		}
			
			
			
			
			@DataProvider(name = "modifyPeopleAndMemoTest")
			public static Object [][] modifyPeopleAndMemoTest(){
				
				String description1 = "description: token 正常, entityId 正常, people 正常, memo 正常";
				Map<String, String> query1 = new HashMap<String, String>();
				query1.put("xtoken", xtoken[1]);
				query1.put("entity_id", "99927792");		
				query1.put("people", "12");
				query1.put("memo", "备注");
				
				
				String description2 = "description: token 正常, entity_id 正常, people 为负数, memo 正常, message 应该提示  people 不能为负数";
				Map<String, String> query2 = new HashMap<String, String>();
				query2.put("xtoken", xtoken[1]);
				query2.put("entity_id", "99927792");
				query2.put("people", "-12");
				query2.put("memo", "备注");

				
				String description3 = "description: token 非法, entity_id 正常, people 正常, memo 正常, message: 请先授权";
				Map<String, String> query3 = new HashMap<String, String>();
				query3.put("xtoken", "577fhfhjgkeurg");
				query3.put("entity_id", "99927792");
				query3.put("people", "12");
				query3.put("memo", "备注");

							
				String description4 = "description: token 正常, entityId 非法, people 正常, memo 正常, message: 应该提示 entityId 非法";
				Map<String, String> query4 = new HashMap<String, String>();
				query4.put("xtoken", xtoken[1]);
				query4.put("entity_id", "9090");
				query4.put("people", "12");
				query4.put("memo", "备注");

				
				
				String description5 = "description: token 正常, entityId 正常, 无 people 及 memo";
				Map<String, String> query5 = new HashMap<String, String>();
				query5.put("xtoken", xtoken[1]);
				query5.put("entity_id", "99927792");

				
				String description6 = "description: token 正常, entityId 包含非法字符,  people 正常, memo 正常, message: 应该提示 entityId 包含非法字符";
				Map<String, String> query6 = new HashMap<String, String>();
				query6.put("xtoken", xtoken[1]);
				query6.put("entity_id", "9090……&&*");
				query6.put("people", "12");
				query6.put("memo", "备注");

				
				
				String description7 = "description: token 正常, 无 字段entityId 及 people 及 memo";
				Map<String, String> query7 = new HashMap<String, String>();
				query7.put("xtoken", xtoken[1]);

				
				return new Object[][]{
						{description1, query1, 200, 1, 200},
						{description2, query2, 200, 0, 400},
						{description3, query3, 200, -1, 400},
						{description4, query4, 200, 5, 400},
						{description5, query5, 200, 1, 200},
						{description6, query6, 200, 6, 400},
						{description7, query7, 200, 9999, 400},
			};
		}
}
