package com.dfire.testcase.prepay;

import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.DataProvider;
import com.dfire.wechat.util.BeanProvider;


public class PrePayControllerTestData {
	
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
				
				private static final String menuId = PrePayControllerTest.menuId;
				
				@DataProvider(name = "getPrePayBillTest")
				public static Object [][] getPrePayBillTest(){
					
					
					String description1 = "description: token 正常, entityId 正常, seat_code 正常, cartSuitFormList 正常, message: 返回购物车中的菜品";
					Map<String, String> query1 = new HashMap<String, String>();
					query1.put("xtoken", xtoken[1]);
					query1.put("entity_id", "99927792");		
					query1.put("seat_code", "B1");
					
					String cartSuitFormList1 = BeanProvider.getCartSuitListJson(12.0, 1, menuId, uid[1]);
					
					
					
					String description2 = "description: token 正常, entity_id 正常, seat_code 正常, cartSuitFormList 为 空,  message: 参数不正确";
					Map<String, String> query2 = new HashMap<String, String>();
					query2.put("xtoken", xtoken[1]);
					query2.put("entity_id", "99927792");
					query2.put("seat_code", "B1");
					
					String cartSuitFormList2 = "";

					
					
					
					String description3 = "description: token 非法, entity_id 正常, seat_code 正常, cartSuitFormList 正常, message: 请先授权";
					Map<String, String> query3 = new HashMap<String, String>();
					query3.put("xtoken", "577fhfhjgkeurg");
					query3.put("entity_id", "99927792");
					query3.put("seat_code", "B1");
					
					String cartSuitFormList3 = BeanProvider.getCartSuitListJson(12.0, 1, menuId, uid[1]);

					
					
					
					String description4 = "description: token 正常, entityId 非法, seat_code 正常, cartSuitFormList 正常,"
							+ " message: 购物车过期, 应该返回 entity_id 无效";
					Map<String, String> query4 = new HashMap<String, String>();
					query4.put("xtoken", xtoken[1]);
					query4.put("entity_id", "9090");
					query4.put("seat_code", "B1");
					
					String cartSuitFormList4 = BeanProvider.getCartSuitListJson(12.0, 1, menuId, uid[1]);

										
					
					
					String description5 = "description: token 正常, entityId 正常, seat_code 非法无效, cartSuitFormList 正常, "
							+ "message: 购物车过期, 应该提示 seat_code 无效";
					Map<String, String> query5 = new HashMap<String, String>();
					query5.put("xtoken", xtoken[1]);
					query5.put("entity_id", "99927792");
					query5.put("seat_code", "A4");
					
					String cartSuitFormList5 = BeanProvider.getCartSuitListJson(12.0, 1, menuId, uid[1]);

					
					
					// entityId 包含特殊字符, 但是未被 Spring 框架拦截
					String description6 = "description: token 正常, entityId 包含非法字符, seat_code 正常, cartSuitFormList 正常, message: 应该提示 entity_id 不能为非法字符";
					Map<String, String> query6 = new HashMap<String, String>();
					query6.put("xtoken", xtoken[1]);
					query6.put("entity_id", "9090……&&*");
					query6.put("seat_code", "B1");
					
					String cartSuitFormList6 = BeanProvider.getCartSuitListJson(12.0, 1, menuId, uid[1]);

					
										
					// URL 中包含特殊字符会被 Spring 框架拦截
					String description7 = "description: token 正常, entityId 正常, seat_code 包含非法字符, cartSuitFormList 为空, message: 应该提示seat_code 不能包含非法字符";
					Map<String, String> query7 = new HashMap<String, String>();
					query7.put("xtoken", xtoken[1]);
					query7.put("entity_id", "99927792");
					query7.put("seat_code", "B1&*^");
					
					String cartSuitFormList7 = BeanProvider.getCartSuitListJson(12.0, 1, menuId, uid[1]);

					
					// orderId 不起作用?
					String description8 = "description: token 正常, entityId 正常, seat_code 正常, cartSuitFormList 正常, orderId 为无效值, message: 返回购物车菜品";
					Map<String, String> query8 = new HashMap<String, String>();
					query8.put("xtoken", xtoken[1]);
					query8.put("entity_id", "99927792");		
					query8.put("seat_code", "B1");
					query8.put("order_id", BeanProvider.getUUIDName());
					
					String cartSuitFormList8 = BeanProvider.getCartSuitListJson(12.0, 1, menuId, uid[1]);
					
					
					// 该用例需要重新考虑
					String description9 = "description: token 正常, entityId 正常, seat_code 正常, cartSuitFormList 正常, orderId 为空, "
							+ "waiting_order_id 无效值, message: 请求失败";
					Map<String, String> query9 = new HashMap<String, String>();
					query9.put("xtoken", xtoken[1]);
					query9.put("entity_id", "99927792");		
					query9.put("seat_code", "B1");
					query9.put("waiting_order_id", BeanProvider.getUUIDName());
					
					String cartSuitFormList9 = BeanProvider.getCartSuitListJson(12.0, 1, menuId, uid[1]);
					
					
					return new Object[][]{
							{description1, query1, cartSuitFormList1, 200, 2, 200},
							{description2, query2, cartSuitFormList2, 200, 0, 400},
							{description3, query3, cartSuitFormList3, 200, -1, 400},
							{description4, query4, cartSuitFormList4, 200, 6, 400},
							{description5, query5, cartSuitFormList5, 200, 5, 400},
							{description6, query6, cartSuitFormList6, 200, 6, 400},
//							{description7, query7, cartSuitFormList7, 200, 6, 400},
       						{description8, query8, cartSuitFormList8, 200, 2, 200},
							{description9, query9, cartSuitFormList9, 200, 9999, 400},
				};
			}
				
				
				
				
				@DataProvider(name = "confirmPrePayOrderTest")
				public static Object [][] confirmPrePayOrderTest(){
					
					
					String description1 = "description: token 正常, entityId 正常, seat_code 正常, cartSuitFormList 正常, message: 返回购物车中的菜品";
					Map<String, String> query1 = new HashMap<String, String>();
					query1.put("xtoken", xtoken[1]);
					query1.put("entity_id", "99927792");		
					query1.put("seat_code", "B1");
					
					String cartSuitFormList1 = BeanProvider.getCartSuitListJson(12.0, 1, menuId, uid[1]);
					
					
					
					String description2 = "description: token 正常, entity_id 正常, seat_code 正常, cartSuitFormList 为 空,  message: 参数不正确";
					Map<String, String> query2 = new HashMap<String, String>();
					query2.put("xtoken", xtoken[1]);
					query2.put("entity_id", "99927792");
					query2.put("seat_code", "B1");
					
					String cartSuitFormList2 = "";

					
					
					
					String description3 = "description: token 非法, entity_id 正常, seat_code 正常, cartSuitFormList 正常, message: 请先授权";
					Map<String, String> query3 = new HashMap<String, String>();
					query3.put("xtoken", "577fhfhjgkeurg");
					query3.put("entity_id", "99927792");
					query3.put("seat_code", "B1");
					
					String cartSuitFormList3 = BeanProvider.getCartSuitListJson(12.0, 1, menuId, uid[1]);

					
					
					
					String description4 = "description: token 正常, entityId 非法, seat_code 正常, cartSuitFormList 正常,"
							+ " message: 购物车过期, 应该返回 entity_id 无效";
					Map<String, String> query4 = new HashMap<String, String>();
					query4.put("xtoken", xtoken[1]);
					query4.put("entity_id", "9090");
					query4.put("seat_code", "B1");
					
					String cartSuitFormList4 = BeanProvider.getCartSuitListJson(12.0, 1, menuId, uid[1]);

										
					
					
					String description5 = "description: token 正常, entityId 正常, seat_code 非法无效, cartSuitFormList 正常, "
							+ "message: 购物车过期, 应该提示 seat_code 无效";
					Map<String, String> query5 = new HashMap<String, String>();
					query5.put("xtoken", xtoken[1]);
					query5.put("entity_id", "99927792");
					query5.put("seat_code", "A4");
					
					String cartSuitFormList5 = BeanProvider.getCartSuitListJson(12.0, 1, menuId, uid[1]);

					
					
					// entityId 包含特殊字符, 但是未被 Spring 框架拦截
					String description6 = "description: token 正常, entityId 包含非法字符, seat_code 正常, cartSuitFormList 正常, message: 应该提示 entity_id 不能为非法字符";
					Map<String, String> query6 = new HashMap<String, String>();
					query6.put("xtoken", xtoken[1]);
					query6.put("entity_id", "9090……&&*");
					query6.put("seat_code", "B1");
					
					String cartSuitFormList6 = BeanProvider.getCartSuitListJson(12.0, 1, menuId, uid[1]);

					
										
					// URL 中包含特殊字符会被 Spring 框架拦截
					String description7 = "description: token 正常, entityId 正常, seat_code 包含非法字符, cartSuitFormList 为空, message: 应该提示seat_code 不能包含非法字符";
					Map<String, String> query7 = new HashMap<String, String>();
					query7.put("xtoken", xtoken[1]);
					query7.put("entity_id", "99927792");
					query7.put("seat_code", "B1&*^");
					
					String cartSuitFormList7 = BeanProvider.getCartSuitListJson(12.0, 1, menuId, uid[1]);

					
					// orderId 不起作用?
					String description8 = "description: token 正常, entityId 正常, seat_code 正常, cartSuitFormList 正常, orderId 为无效值, message: 返回购物车菜品";
					Map<String, String> query8 = new HashMap<String, String>();
					query8.put("xtoken", xtoken[1]);
					query8.put("entity_id", "99927792");		
					query8.put("seat_code", "B1");
					query8.put("order_id", BeanProvider.getUUIDName());
					
					String cartSuitFormList8 = BeanProvider.getCartSuitListJson(12.0, 1, menuId, uid[1]);
					
					
					// 该用例需要重新考虑
					String description9 = "description: token 正常, entityId 正常, seat_code 正常, cartSuitFormList 正常, orderId 为空, "
							+ "waiting_order_id 无效值, message: 请求失败";
					Map<String, String> query9 = new HashMap<String, String>();
					query9.put("xtoken", xtoken[1]);
					query9.put("entity_id", "99927792");		
					query9.put("seat_code", "B1");
					query9.put("waiting_order_id", BeanProvider.getUUIDName());
					
					String cartSuitFormList9 = BeanProvider.getCartSuitListJson(12.0, 1, menuId, uid[1]);
					
					
					return new Object[][]{
							{description1, query1, cartSuitFormList1, 200, 2, 200},
							{description2, query2, cartSuitFormList2, 200, 0, 400},
							{description3, query3, cartSuitFormList3, 200, -1, 400},
							{description4, query4, cartSuitFormList4, 200, 6, 400},
							{description5, query5, cartSuitFormList5, 200, 5, 400},
							{description6, query6, cartSuitFormList6, 200, 6, 400},
//							{description7, query7, cartSuitFormList7, 200, 6, 400},
       						{description8, query8, cartSuitFormList8, 200, 2, 200},
							{description9, query9, cartSuitFormList9, 200, 9999, 400},
				};
			}
				
				
				@DataProvider(name = "deletePrepayOrderTest")
				public static Object [][] deletePrepayOrderTest(){
					
					// 该用例需要日常环境的正常的waiting_order_id
					String description1 = "description: token 正常, waiting_order_id 正常, message: 返回购物车中的菜品";
					Map<String, String> query1 = new HashMap<String, String>();
					query1.put("xtoken", xtoken[1]);
					query1.put("waiting_order_id", "9992779275768");		
					
					
					
					String description2 = "description: token 正常, waiting_order_id 无效, message: 请求失败, 应该提示 waiting_order_id 无效";
					Map<String, String> query2 = new HashMap<String, String>();
					query2.put("xtoken", xtoken[1]);
					query2.put("waiting_order_id", "999277928684685");
					
					
					
					String description3 = "description: token 非法, waiting_order_id 正常, message: 请先授权";
					Map<String, String> query3 = new HashMap<String, String>();
					query3.put("xtoken", "577fhfhjgkeurg");
					query3.put("waiting_order_id", "99927792");
					
					
					// URL 中的特殊字符由 Spring 自动拦截
					String description4 = "description: token 正常, waiting_order_id 包含特殊字符, message: ";
					Map<String, String> query4 = new HashMap<String, String>();
					query4.put("xtoken", xtoken[1]);
					query4.put("waiting_order_id", "9090%&%&^");									
					
								
					
					return new Object[][]{
//							{description1, query1, 200, 2, 200},
							{description2, query2, 200, 999, 400},
							{description3, query3, 200, -1, 400},
//							{description4, query4, 200, 6, 400},

				};
			}
				
				
				
				@DataProvider(name = "getPrepayOrderTest")
				public static Object [][] getPrepayOrderTest(){
					
					// 该用例需要日常环境的正常的waiting_order_id
					String description1 = "description: token 正常, entity_id 正常, seat_code 正常, message: 返回预付订单内容";
					Map<String, String> query1 = new HashMap<String, String>();
					query1.put("xtoken", xtoken[1]);
					query1.put("entity_id", "99927792");
					query1.put("seat_code", "B1");
					
					
					
					String description2 = "description: token 正常, entity_id 无效, seat_code 正常, message: 请求失败, 应该提示 entity_id 无效";
					Map<String, String> query2 = new HashMap<String, String>();
					query2.put("xtoken", xtoken[1]);
					query2.put("entity_id", "9992");
					query2.put("seat_code", "B1");
					
					
					
					String description3 = "description: token 非法, entity_id 正常, seat_code 正常, message: 请先授权";
					Map<String, String> query3 = new HashMap<String, String>();
					query3.put("xtoken", "577fhfhjgkeurg");
					query3.put("entity_id", "99927792");
					query3.put("seat_code", "B1");
					
					
					// URL 中的特殊字符由 Spring 自动拦截
					String description4 = "description: token 正常, entity_id 包含特殊字符, seat_message: ";
					Map<String, String> query4 = new HashMap<String, String>();
					query4.put("xtoken", xtoken[1]);
					query4.put("entity_id", "9090%&%&^");	
					query4.put("seat_code", "B1");
					
						
					String description5 = "description: token 正常, entity_id 正常, seat_code 无效, message: 返回空的预账单, 应该提示 seat_code 无效";
					Map<String, String> query5 = new HashMap<String, String>();
					query5.put("xtoken", xtoken[1]);
					query5.put("entity_id", "99927792");
					query5.put("seat_code", "B19");
					
					return new Object[][]{
							{description1, query1, 200, 1, 200},
							{description2, query2, 200, 999, 400},
							{description3, query3, 200, -1, 400},
//					    	{description4, query4, 200, 6, 400},
							{description5, query5, 200, 6, 400},

				};
			}
				
				
				
				@DataProvider(name = "getBillInfoTest")
				public static Object [][] getBillInfoTest(){
					String orderDone[] = {"9992779256788385015678cca8200042",      // seat_code : B1
										  "999277925678d22701567939a9cc001d",};    // seat_code : 101
					
					String preOrder[] = {"9992779256788385015678c937ca003d"};  // 即  waiting_order_id  seat_code : B1
					
					// 该用例需要日常环境的正常的 waiting_order_id
					String description1 = "description: token 正常, entity_id 正常, seat_code 正常, order_id 正常, message: 返回正确预订单内容";
					Map<String, String> query1 = new HashMap<String, String>();
					query1.put("xtoken", xtoken[1]);
					query1.put("entity_id", "99927792");
					query1.put("seat_code", "B1");
					query1.put("order_id", preOrder[0]);
					
					
					
					String description2 = "description: token 正常, entity_id 无效, seat_code 正常, order_id 正常, "
							+ "message: 返回正确预订单内容, 应该提示 entity_id 无效";
					Map<String, String> query2 = new HashMap<String, String>();
					query2.put("xtoken", xtoken[1]);
					query2.put("entity_id", "9992");
					query2.put("seat_code", "B1");
					query2.put("order_id", preOrder[0]);
					
					
					
					String description3 = "description: token 非法, entity_id 正常, seat_code 正常, order_id 正常, message: 请先授权";
					Map<String, String> query3 = new HashMap<String, String>();
					query3.put("xtoken", "577fhfhjgkeurg");
					query3.put("entity_id", "99927792");
					query3.put("seat_code", "B1");
					query3.put("order_id", preOrder[0]);
					
					
					// URL 中的特殊字符由 Spring 自动拦截, 该用例可忽略
					String description4 = "description: token 正常, entity_id 包含特殊字符, seat_code 正常, order_id 正常 , Spring 框架抛出非法参数异常";
					Map<String, String> query4 = new HashMap<String, String>();
					query4.put("xtoken", xtoken[1]);
					query4.put("entity_id", "9090%&%&^");	
					query4.put("seat_code", "B1");
					query4.put("order_id", preOrder[0]);
					
						
					String description5 = "description: token 正常, entity_id 正常, seat_code 无效,  order_id 正常, message: 返回正确预订单内容, 应该提示 seat_code 无效";
					Map<String, String> query5 = new HashMap<String, String>();
					query5.put("xtoken", xtoken[1]);
					query5.put("entity_id", "99927792");
					query5.put("seat_code", "B19");
					query5.put("order_id", preOrder[0]);
					
					// URL 中的特殊字符由 Spring 自动拦截, 该用例可忽略
					String description6 = "description: token 正常, entity_id 正常, seat_code 包含特殊字符,  order_id 正常, Spring 框架抛出非法参数异常";
					Map<String, String> query6 = new HashMap<String, String>();
					query6.put("xtoken", xtoken[1]);
					query6.put("entity_id", "99927792");
					query6.put("seat_code", "B1&^&*");
					query6.put("order_id", preOrder[0]);
					
					
					// 该用例需要日常环境的正常的 waiting_order_id
					String description7 = "description: token 正常, entity_id 正常, seat_code 正常, order_id 为已结账订单, message: 返回正确预订单内容";
					Map<String, String> query7 = new HashMap<String, String>();
					query7.put("xtoken", xtoken[1]);
					query7.put("entity_id", "99927792");
					query7.put("seat_code", "B1");
					query7.put("order_id", orderDone[0]);
					
					return new Object[][]{
							{description1, query1, 200, 1, 200},
							{description2, query2, 200, 999, 400},
							{description3, query3, 200, -1, 400},
//					    	{description4, query4, 200, 6, 400},
							{description5, query5, 200, 6, 400},
//							{description6, query6, 200, 6, 400},
							{description7, query7, 200, 1, 400},

				};
			}
				
				
				@DataProvider(name = "lockPayWaitingOrderTest")
				public static Object [][] lockPayWaitingOrderTest(){
					
					String orderInvalid[] = {"99927575575798hhgt5015678cca8200042", 
							                 "999275$%$^%^^&^&78cca8200042"};
					
					String orderDone[] = {"9992779256788385015678cca8200042",      // seat_code : B1
										  "999277925678d22701567939a9cc001d",};    // seat_code : 101
					
					String preOrder[] = {"9992779256788385015678c937ca003d",   // 即  waiting_order_id  seat_code : B1
							             "99927792567c943a01567ccc77cb0014",   // 即  waiting_order_id  seat_code : B2
							             "99927792567c97ea01567d880d24003f" }; // 即  waiting_order_id  seat_code : B2
					
					// 该用例需要日常环境的正常的 waiting_order_id
					String description1 = "description: token 正常, waiting_order_id 正常, pay_money 正常, "
							+ "message: 请求失败, 提示 java.lang.NumberFormatException: null, 应该返回正常的等待订单信息";
					Map<String, String> query1 = new HashMap<String, String>();
					query1.put("xtoken", xtoken[1]);					
					query1.put("waiting_order_id", preOrder[2]);
					query1.put("pay_money", "20.0");
					
					
					
					String description2 = "description: token 正常, waiting_order_id 无效, pay_money 正常, message:订单获取失败 ";
					Map<String, String> query2 = new HashMap<String, String>();
					query2.put("xtoken", xtoken[1]);
					query2.put("waiting_order_id", orderInvalid[0]);
					query2.put("pay_money", "26");
					
					
					
					String description3 = "description: token 非法, waiting_order_id 正常, pay_money 正常, message: 请先授权";
					Map<String, String> query3 = new HashMap<String, String>();
					query3.put("xtoken", "577fhfhjgkeurg");
					query3.put("waiting_order_id", preOrder[1]);
					query3.put("pay_money", "46");
					
					
					
					String description4 = "description: token 正常, waiting_order_id 正常, pay_money 与实际账单不吻合, message: 请求失败, 应该指明具体失败原因";
					Map<String, String> query4 = new HashMap<String, String>();
					query4.put("xtoken", xtoken[1]);
					query4.put("waiting_order_id", preOrder[1]);
					query4.put("pay_money", "90");
					
					
					//  URL 中的特殊字符由 Spring 自动拦截, 该用例可忽略
					String description5 = "description: token 正常, waiting_order_id 包含非法字符, pay_money 正常, message:";
					Map<String, String> query5 = new HashMap<String, String>();
					query5.put("xtoken", xtoken[1]);
					query5.put("waiting_order_id", orderInvalid[1]);
					query5.put("pay_money", "46");
					
					// URL 中的特殊字符由 Spring 自动拦截, 该用例可忽略
					String description6 = "description: token 正常, waiting_order_id 正常, pay_money 包含特殊字符 ";
					Map<String, String> query6 = new HashMap<String, String>();
					query6.put("xtoken", xtoken[1]);
					query6.put("waiting_order_id", preOrder[1]);
					query6.put("pay_moneny", "46&^&*");
					
					
					// 该用例需要日常环境的正常的 waiting_order_id
					String description7 = "description: token 正常, waiting_order_id 正常, pay_moneny 为负数,  message: 请求失败, 应该返回具体原因";
					Map<String, String> query7 = new HashMap<String, String>();
					query7.put("xtoken", xtoken[1]);
					query7.put("waiting_order_id", preOrder[1]);
					query7.put("pay_moneny", "-46");

					
					return new Object[][]{
							{description1, query1, 200, 1, 200},
							{description2, query2, 200, 1001, 400},
							{description3, query3, 200, -1, 400},
					    	{description4, query4, 200, 6, 400},
//						    {description5, query5, 200, 6, 400},
							{description6, query6, 200, 6, 400},
							{description7, query7, 200, 1, 400},

				};
			}
				

}
