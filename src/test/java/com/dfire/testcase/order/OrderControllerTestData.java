package com.dfire.testcase.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.dfire.testcase.shop.CartControllerTest;
import com.dfire.wechat.db.DataPrepared;
import com.dfire.wechat.util.BeanProvider;

public class OrderControllerTestData {
	
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
				
				private static final String[] orderIDUnpaid = {"999277925682f3b501569154d1f0058c",    // from wechat, seat_code: B2, price:0
																"99927792569188b7015691eb22280037",   // from wechat, seat_code: 105, price:3.0
																"999277925692534c01569770bc590205"};  // from wechat, seat_code: 103, price:0.0  unCheck
				
				private static final String[] orderIDPaid = {"999277925682f3b501569159b6900595"}; // from wechat, seat_code:106, price:0
				
				/**
				 * 支付前先锁定订单，支付后由微信推送pay解锁 回调接口：pay/native_url
				 * @return
				 */
				@DataProvider(name = "lockOrderTest")
				public static Object [][] lockOrderTest(){
					
					String description1 = "description: token 正常, order_id 正常, pay_moneny正常, 未付款, 应该进行消息提示";
					Map<String, String> query1 = new HashMap<String, String>();
					query1.put("xtoken", xtoken[1]);
					query1.put("order_id", orderIDUnpaid[0]);		
					query1.put("pay_money", "0.0");

					
					String description2 = "description: token 正常, order_id 正常, pay_moneny 不正确但是合法, "
							+ "message: 账单总金额为0.00元, 该次支付金额为12.00元,支付超额";
					Map<String, String> query2 = new HashMap<String, String>();
					query2.put("xtoken", xtoken[1]);
					query2.put("order_id", orderIDUnpaid[0]);		
					query2.put("pay_money", "12.0");

					
					String description3 = "description: token 非法, order_id 正常, pay_moneny 正常, message: 请先授权";
					Map<String, String> query3 = new HashMap<String, String>();
					query3.put("xtoken", "577fhfhjgkeurg");
					query3.put("order_id", orderIDUnpaid[0]);		
					query3.put("pay_money", "0.0");

					
				
					String description4 = "description: token 正常, order_id 正常, pay_moneny 为负数, message:payMoney订单号不能为空, 应该提示 pay_moneny 本能为负数";
					Map<String, String> query4 = new HashMap<String, String>();
					query4.put("xtoken", xtoken[1]);
					query4.put("order_id", orderIDUnpaid[0]);		
					query4.put("pay_money", "-90.0");

					
					// URL 中包含特殊字符会被 Spring 框架拦截, 该用例可忽略
					String description5 = "description: token 正常, order_id 正常, pay_moneny 包含特殊字符";
					Map<String, String> query5 = new HashMap<String, String>();
					query5.put("xtoken", xtoken[1]);
					query5.put("order_id", orderIDUnpaid[0]);		
					query5.put("pay_money", "0.0^&*");

					// URL 中包含特殊字符会被 Spring 框架拦截, 该用例可忽略
					String description6 = "description: token 正常, order_id 包含特殊字符, pay_moneny 正常";
					Map<String, String> query6 = new HashMap<String, String>();
					query6.put("xtoken", xtoken[1]);
					query6.put("order_id", "999277925682%^&%^154d1f0058c");		
					query6.put("pay_money", "0.0");

					
					
					String description7 = "description: token 正常,  order_id 正常, pay_moneny 正常, 已支付, message:返回正常, 应该进行有效的消息提示";
					Map<String, String> query7 = new HashMap<String, String>();
					query7.put("xtoken", xtoken[1]);
					query7.put("order_id", orderIDPaid[0]);		
					query7.put("pay_money", "0.0");

					
					String description8 = "description: token 正常,  order_id 正常, pay_moneny 正常, 未支付, pay_moneny 小于实际值, "
							+ "message:返回正常, 应该提示 pay_moneny 小于实际值";
					Map<String, String> query8 = new HashMap<String, String>();
					query8.put("xtoken", xtoken[1]);
					query8.put("order_id", orderIDUnpaid[1]);		
					query8.put("pay_money", "1.0");
					
					
					return new Object[][]{
							{description1, query1, 200, 1, 200},
							{description2, query2, 200, 1003, 400},
							{description3, query3, 200, -1, 400},
							{description4, query4, 200, 5, 400},
////						{description5, query5, 200, 5, 400},
////						{description6, query6, 200, 6, 400},
							{description7, query7, 200, 6, 400},
							{description8, query8, 200, 6, 400},
				};
			}
				
				
				
				@DataProvider(name = "unlockPayOrderTest")
				public static Object [][] unlockPayOrderTest(){
					
					String description1 = "description: token 正常, order_id 正常, 未付款, 应该进行消息提示";
					Map<String, String> query1 = new HashMap<String, String>();
					query1.put("xtoken", xtoken[1]);
					query1.put("order_id", orderIDUnpaid[0]);		

					
					String description2 = "description: token 非法, order_id 正常, 未付款, message: 请先授权";
					Map<String, String> query2 = new HashMap<String, String>();
					query2.put("xtoken", "577fhfhjgkeurg");
					query2.put("order_id", orderIDUnpaid[0]);		


					// URL 中包含特殊字符会被 Spring 框架拦截, 该用例可忽略
					String description3 = "description: token 正常, order_id 包含特殊字符";
					Map<String, String> query3 = new HashMap<String, String>();
					query3.put("xtoken", xtoken[1]);
					query3.put("order_id", "999277925682%^&%^154d1f0058c");		

					
					
					String description4 = "description: token 正常,  order_id 正常, 已支付, message:返回正常, 应该进行有效的消息提示";
					Map<String, String> query4 = new HashMap<String, String>();
					query4.put("xtoken", xtoken[1]);
					query4.put("order_id", orderIDPaid[0]);		

									
					
					return new Object[][]{
							{description1, query1, 200, 1, 200},
							{description2, query2, 200, -1, 400},
//							{description3, query3, 200, 6, 400},
							{description4, query4, 200, 5, 400},

				};
			}
				
				
				
				@DataProvider(name = "checkOrderTest")
				public static Object [][] checkOrderTest(){
					
					String description1 = "description: token 正常, entity_id 正常, seat_code 正常, order_id 正常, 普通菜, 请求成功, 应该将 data 字段改为  message 字段";
					Map<String, String> query1 = new HashMap<String, String>();
					query1.put("xtoken", xtoken[1]);
					query1.put("entity_id", OrderControllerTest.entityId_cart);	
					query1.put("seat_code", OrderControllerTest.seatCode);	
					query1.put("order_id", OrderControllerTest.orderId1);		
					String cartSuit1 = OrderControllerTest.cartSuitList;
					
					
					String description2 = "description: token 正常, entity_id 正常, seat_code 正常, order_id 正常, 套菜, 请求成功, 应该将 data 字段改为  message 字段";
					Map<String, String> query2 = new HashMap<String, String>();
					query2.put("xtoken", xtoken[1]);
					query2.put("entity_id", OrderControllerTest.entityId_cart);	
					query2.put("seat_code", OrderControllerTest.seatCode2);	
					query2.put("order_id", OrderControllerTest.orderId2);		
					String cartSuit2 = OrderControllerTest.cartSuitList2;	


					String description3 = "description: token 正常, entity_id 正常, seat_code 正常, order_id 正常, 普通菜(包含两个菜), 请求成功, 应该返回 message: 购物车数据发生变化";
					Map<String, String> query3 = new HashMap<String, String>();
					query3.put("xtoken", xtoken[1]);
					query3.put("entity_id", OrderControllerTest.entityId_cart);	
					query3.put("seat_code", OrderControllerTest.seatCode);	
					query3.put("order_id", OrderControllerTest.orderId1);		
					String cartSuit3 = OrderControllerTest.cartSuitListWithMultiDish;		

					
					
					String description4 = "description: token 正常, entity_id 无效, seat_code 无效, order_id 无效, 普通菜, 提示购物车过期";
					Map<String, String> query4 = new HashMap<String, String>();
					query4.put("xtoken", xtoken[1]);
					query4.put("entity_id", "907979");	
					query4.put("seat_code", "ERT");	
					query4.put("order_id", "123");		
					String cartSuit4 = OrderControllerTest.cartSuitList;

									
					String description5 = "description: token 无效, entity_id 正常, seat_code 正常, order_id 正常, 普通菜, 请求成功, 应该将 data 字段改为  message 字段";
					Map<String, String> query5 = new HashMap<String, String>();
					query5.put("xtoken", "87468575");
					query5.put("entity_id", OrderControllerTest.entityId_cart);	
					query5.put("seat_code", OrderControllerTest.seatCode);	
					query5.put("order_id", OrderControllerTest.orderId1);		
					String cartSuit5 = OrderControllerTest.cartSuitList;
					
					
					return new Object[][]{
							{description1, query1, cartSuit1, 200, 1, 200},
							{description2, query2, cartSuit2, 200, 1, 200},
							{description3, query3, cartSuit3, 200, 6, 400},
							{description4, query4, cartSuit4, 200, 5, 400},
							{description5, query5, cartSuit5, 200, -1, 400},

				};
			}
				
				
				
				@DataProvider(name = "checkOrderChangeTest")
				public static Object [][] checkOrderChangeTest(){
					
					String description1 = "description: token 正常, entity_id 正常, seat_code 正常, order_id 正常, " + DataPrepared.descriptionList.get(0);
					Map<String, String> query1 = new HashMap<String, String>();
					query1.put("xtoken", xtoken[1]);
					query1.put("entity_id", "99927792");	
					query1.put("seat_code", "AB");	
					query1.put("order_id", DataPrepared.orderDetailIdList.get(1));	
					String orderIdsFormList1 = OrderControllerTest.orderIdsFormListJson1;

					
					String description2 = "description: token 正常, entity_id 正常, seat_code 正常, order_id 正常, " + DataPrepared.descriptionList.get(1);
					Map<String, String> query2 = new HashMap<String, String>();
					query2.put("xtoken", xtoken[1]);
					query2.put("entity_id","99927792");	
					query2.put("seat_code", "AB");	
					query2.put("order_id", DataPrepared.orderDetailIdList.get(2));	
					String orderIdsFormList2 = OrderControllerTest.orderIdsFormListJson2;


					// URL 中包含特殊字符会被 Spring 框架拦截, 该用例可忽略
					String description3 = "description: token 正常, entity_id 正常, seat_code 正常, order_id 正常, 缺少 orderIdsForms 参数";
					Map<String, String> query3 = new HashMap<String, String>();
					query3.put("xtoken", xtoken[1]);
					query3.put("entity_id", "99927792");	
					query3.put("seat_code", "AB");	
					query3.put("order_id", DataPrepared.orderDetailIdList.get(1));		

					
					
					String description4 = "description: token 非法,  entity_id 正常, seat_code 正常, order_id 正常, 消息提示:请先授权";
					Map<String, String> query4 = new HashMap<String, String>();
					query4.put("xtoken", "uehruih9uh");
					query4.put("entity_id", "99927792");	
					query4.put("seat_code", "AB");	
					query4.put("order_id", DataPrepared.orderDetailIdList.get(1));	
					String orderIdsFormList4 = OrderControllerTest.orderIdsFormListJson1;	

									
					
					return new Object[][]{
							{description1, query1, orderIdsFormList1, 200, 1, 400},
							{description2, query2, orderIdsFormList2, 200, 1, 400},
							{description3, query3, "", 200, 2, 400},
							{description4, query4, orderIdsFormList4, 200, -1, 400},

				};
			}
				
				
				
				@DataProvider(name = "confirmOrderTest")
				public static Object [][] confirmOrderTest(){
					
					String description1 = "description: token 正常, entity_id 正常, seat_code 正常, order_id 正常, 普通菜, 请求成功, 应该返回具体的 message 字段";
					Map<String, String> query1 = new HashMap<String, String>();
					query1.put("xtoken", xtoken[1]);
					query1.put("entity_id", OrderControllerTest.entityId_cart);	
					query1.put("seat_code", OrderControllerTest.seatCode);	
					query1.put("order_id", OrderControllerTest.orderId1);		
					String cartSuit1 = OrderControllerTest.cartSuitList;
					
					
					String description2 = "description: token 正常, entity_id 正常, seat_code 正常, order_id 正常, 套菜, 请求成功, 应该返回具体的  message 字段";
					Map<String, String> query2 = new HashMap<String, String>();
					query2.put("xtoken", xtoken[1]);
					query2.put("entity_id", OrderControllerTest.entityId_cart);	
					query2.put("seat_code", OrderControllerTest.seatCode2);	
					query2.put("order_id", OrderControllerTest.orderId2);		
					String cartSuit2 = OrderControllerTest.cartSuitList2;	


					String description3 = "description: token 正常, entity_id 正常, seat_code 正常, order_id 正常, 普通菜(包含两个菜), 请求成功, 应该返回 message: 购物车数据发生变化";
					Map<String, String> query3 = new HashMap<String, String>();
					query3.put("xtoken", xtoken[1]);
					query3.put("entity_id", OrderControllerTest.entityId_cart);	
					query3.put("seat_code", OrderControllerTest.seatCode);	
					query3.put("order_id", OrderControllerTest.orderId1);		
					String cartSuit3 = OrderControllerTest.cartSuitListWithMultiDish;		

					
					
					String description4 = "description: token 正常, entity_id 无效, seat_code 无效, order_id 无效, 普通菜, 应该提示刷新购物车失败";
					Map<String, String> query4 = new HashMap<String, String>();
					query4.put("xtoken", xtoken[1]);
					query4.put("entity_id", "907979");	
					query4.put("seat_code", "ERT");	
					query4.put("order_id", "123");		
					String cartSuit4 = OrderControllerTest.cartSuitList;

									
					
					String description5 = "description: token 无效, entity_id 正常, seat_code 正常, order_id 正常, 普通菜, 请求成功, 应该将 data 字段改为  message 字段";
					Map<String, String> query5 = new HashMap<String, String>();
					query5.put("xtoken", "87468575");
					query5.put("entity_id", OrderControllerTest.entityId_cart);	
					query5.put("seat_code", OrderControllerTest.seatCode);	
					query5.put("order_id", OrderControllerTest.orderId1);		
					String cartSuit5 = OrderControllerTest.cartSuitList;
					
					
					
					return new Object[][]{
							{description1, query1, cartSuit1, 200, 1, 400},
							{description2, query2, cartSuit2, 200, 1, 400},
							{description3, query3, cartSuit3, 200, 2, 200},
							{description4, query4, cartSuit4, 200, 2, 400},
							{description5, query5, cartSuit5, 200, -1, 400},

				};
			}
			
				
				
				
				@DataProvider(name = "modifyMemoTest")
				public static Object [][] modifyMemoTest(){
					
										
					String description1 = "description: token 正常, entity_id 正常, seat_code 正常, order_id 正常, memo 正常, 普通菜, 请求成功, 应该返回具体的 message 字段";
					Map<String, String> query1 = new HashMap<String, String>();
					query1.put("xtoken", xtoken[1]);
					query1.put("entity_id", OrderControllerTest.entityId_cart);	
					query1.put("seat_code", OrderControllerTest.seatCode);	
					query1.put("order_id", OrderControllerTest.orderId1);		
					query1.put("memo", "test_for_memo");
					
					
					
					
					String description2 = "description: token 正常, entity_id 正常, seat_code 正常, order_id 正常, memo 正常, 套菜, 请求成功, 应该返回具体的  message 字段";
					Map<String, String> query2 = new HashMap<String, String>();
					query2.put("xtoken", xtoken[1]);
					query2.put("entity_id", OrderControllerTest.entityId_cart);	
					query2.put("seat_code", OrderControllerTest.seatCode2);	
					query2.put("order_id", OrderControllerTest.orderId2);		
					query2.put("memo", "test_for_memo");	


					// URL 中包含特殊字符, 已由 Spring 框架拦截, 该用例可忽略
					String description3 = "description: token 正常, entity_id 正常, seat_code 正常, order_id 正常, memo 包含特殊字符, 普通菜, 请求成功, 应该返回 message: 购物车数据发生变化";
					Map<String, String> query3 = new HashMap<String, String>();
					query3.put("xtoken", xtoken[1]);
					query3.put("entity_id", OrderControllerTest.entityId_cart);	
					query3.put("seat_code", OrderControllerTest.seatCode);	
					query3.put("order_id", OrderControllerTest.orderId1);		
					query3.put("memo", "test_for_memo$%^%");		

					
					
					String description4 = "description: token 正常, entity_id 无效, seat_code 无效, order_id 无效, 普通菜, code=5, 提示：购物车过期, 应该提示具体哪些请求参数非法";
					Map<String, String> query4 = new HashMap<String, String>();
					query4.put("xtoken", xtoken[1]);
					query4.put("entity_id", "907979");	
					query4.put("seat_code", "ERT");	
					query4.put("order_id", "123");	
					query4.put("memo", "test_for_memo");	
					

									
					
					String description5 = "description: token 无效, entity_id 正常, seat_code 正常, order_id 正常, memo 正常, 普通菜";
					Map<String, String> query5 = new HashMap<String, String>();
					query5.put("xtoken", "87468575");
					query5.put("entity_id", OrderControllerTest.entityId_cart);	
					query5.put("seat_code", OrderControllerTest.seatCode);	
					query5.put("order_id", OrderControllerTest.orderId1);		
					
					
					
					
					return new Object[][]{
							{description1, query1, 200, 1, 400},
							{description2, query2, 200, 1, 400},
//							{description3, query3, 200, 2, 200},
							{description4, query4, 200, 9, 400},
							{description5, query5, 200, -1, 400},

				};
			}
			
				
				
				
				@DataProvider(name = "getOrderTest")
				public static Object [][] getOrderTest(){
					
										
					String description1 = "description: token 正常, entity_id 正常, seat_code 正常, order_id 正常, memo 正常, 普通菜, 请求成功, 应该返回具体的 message 字段";
					Map<String, String> query1 = new HashMap<String, String>();
					query1.put("xtoken", xtoken[1]);
					query1.put("entity_id", OrderControllerTest.entityId_cart);	
					query1.put("seat_code", OrderControllerTest.seatCode);	
					query1.put("order_id", OrderControllerTest.orderId1);		
					query1.put("memo", "test_for_memo");
					
					
					
					
					String description2 = "description: token 正常, entity_id 正常, seat_code 正常, order_id 正常, memo 正常, 套菜, 请求成功, 应该返回具体的  message 字段";
					Map<String, String> query2 = new HashMap<String, String>();
					query2.put("xtoken", xtoken[1]);
					query2.put("entity_id", OrderControllerTest.entityId_cart);	
					query2.put("seat_code", OrderControllerTest.seatCode2);	
					query2.put("order_id", OrderControllerTest.orderId2);		
					query2.put("memo", "test_for_memo");	


					// URL 中包含特殊字符, 已由 Spring 框架拦截, 该用例可忽略
					String description3 = "description: token 正常, entity_id 正常, seat_code 正常, order_id 正常, memo 包含特殊字符, 普通菜, 请求成功, 应该返回 message: 购物车数据发生变化";
					Map<String, String> query3 = new HashMap<String, String>();
					query3.put("xtoken", xtoken[1]);
					query3.put("entity_id", OrderControllerTest.entityId_cart);	
					query3.put("seat_code", OrderControllerTest.seatCode);	
					query3.put("order_id", OrderControllerTest.orderId1);		
					query3.put("memo", "test_for_memo$%^%");		

					
					
					String description4 = "description: token 正常, entity_id 无效, seat_code 无效, order_id 无效, 普通菜, code=5, 提示：购物车过期, 应该提示具体哪些请求参数非法";
					Map<String, String> query4 = new HashMap<String, String>();
					query4.put("xtoken", xtoken[1]);
					query4.put("entity_id", "907979");	
					query4.put("seat_code", "ERT");	
					query4.put("order_id", "123");	
					query4.put("memo", "test_for_memo");	
					

									
					
					String description5 = "description: token 无效, entity_id 正常, seat_code 正常, order_id 正常, memo 正常, 普通菜";
					Map<String, String> query5 = new HashMap<String, String>();
					query5.put("xtoken", "87468575");
					query5.put("entity_id", OrderControllerTest.entityId_cart);	
					query5.put("seat_code", OrderControllerTest.seatCode);	
					query5.put("order_id", OrderControllerTest.orderId1);		
					
					
					String description6 = "description: token 有效, entity_id 正常, seat_code 与  DataPrepared 中的 seatCode 保持一致";
					Map<String, String> query6 = new HashMap<String, String>();
					query6.put("xtoken", xtoken[1]);
					query6.put("entity_id", OrderControllerTest.entityId_cart);	
					query6.put("seat_code", OrderControllerTest.seatCodeInOrderTable);	
					query6.put("order_id", DataPrepared.orderDetailIdList.get(1));
					
					
					String description7 = "description: token 有效, entity_id 正常, seat_code 与  DataPrepared 中的 seatCode 保持一致";
					Map<String, String> query7 = new HashMap<String, String>();
					query7.put("xtoken", xtoken[1]);
					query7.put("entity_id", OrderControllerTest.entityId_cart);	
					query7.put("seat_code", OrderControllerTest.seatCodeInOrderTable);	
					query7.put("order_id", DataPrepared.orderDetailIdList.get(2));
					
					
					return new Object[][]{
							{description1, query1, 200, 1, false, false},
							{description2, query2, 200, 1, false, false},
//							{description3, query3, 200, 2, false, flase},
							{description4, query4, 200, 9, false, false},
							{description5, query5, 200, -1,  false, false},
							{description6, query6, 200, 1,  false, true},
							{description7, query7, 200, 1,  true, false},

				};
			}
				
				
				@DataProvider(name = "getKoubeiOrderTest")
				public static Object [][] getKoubeiOrderTest(){
					
					List<String> orderList =  DataPrepared.orderDetailIdList;
					
					String description1 = "description: token 正常, entity_id 正常, order_id 正常, ";
					Map<String, String> query1 = new HashMap<String, String>();
					query1.put("xtoken", xtoken[1]);
					query1.put("entity_id", OrderControllerTest.entityId_cart);	
					query1.put("order_id",  orderList.get(1));		

					
					
					
					
					String description2 = "description: token 正常, entity_id 正常, order_id 正常, ";
					Map<String, String> query2 = new HashMap<String, String>();
					query2.put("xtoken", xtoken[1]);
					query2.put("entity_id", OrderControllerTest.entityId_cart);	
					query2.put("order_id", orderList.get(2));		



					// URL 中包含特殊字符, 已由 Spring 框架拦截, 该用例可忽略
					String description3 = "description: token 正常, entity_id 正常, order_id 正常, ";
					Map<String, String> query3 = new HashMap<String, String>();
					query3.put("xtoken", xtoken[1]);
					query3.put("entity_id", OrderControllerTest.entityId_cart);	
					query3.put("order_id", orderList.get(3));		
	

					
					
					String description4 = "description: token 正常, entity_id 无效, sorder_id 无效,";
					Map<String, String> query4 = new HashMap<String, String>();
					query4.put("xtoken", xtoken[1]);
					query4.put("entity_id", "907979");	
					query4.put("order_id", "123");	

					

									
					
					String description5 = "description: token 无效, entity_id 正常, order_id 正常,";
					Map<String, String> query5 = new HashMap<String, String>();
					query5.put("xtoken", "87468575");
					query5.put("entity_id", OrderControllerTest.entityId_cart);	
					query5.put("order_id", OrderControllerTest.orderId1);		
					
					
					
					
					return new Object[][]{
							{description1, query1, 200, 1, 200},
//							{description2, query2, 200, 1, 400},
////							{description3, query3, 200, 2, 200},
//							{description4, query4, 200, 9, 400},
//							{description5, query5, 200, -1, 400},

				};
			}
			
			
				
				
				@DataProvider(name = "getOrderHistoryTest")
				public static Object [][] getOrderHistoryTest(){
					
					String description1 = "description: token 正常, entity_id 正常, page 正常, page_size 正常";
					Map<String, String> query1 = new HashMap<String, String>();
					query1.put("xtoken", xtoken[1]);
					query1.put("entity_id", OrderControllerTest.entityId_cart);	
					query1.put("page",  "");
					query1.put("page_size",  "");	
				
					
					
					String description2 = "description: token 正常, entity_id 正常, page 过大, page_size 正常";
					Map<String, String> query2 = new HashMap<String, String>();
					query2.put("xtoken", xtoken[1]);
					query2.put("entity_id", OrderControllerTest.entityId_cart);	
					query2.put("page", "1000");	
					query2.put("page_size", "");


					
					String description3 = "description: token 正常, entity_id 正常, page 正常, page_size 过大";
					Map<String, String> query3 = new HashMap<String, String>();
					query3.put("xtoken", xtoken[1]);
					query3.put("entity_id", OrderControllerTest.entityId_cart);	
					query3.put("page", "");	
					query3.put("page_size", "1000");
	
					
					
					String description4 = "description: token 正常, entity_id 正常, page 过大, page_size 过大, message 提示：历史订单不存在, 应该显示最近的所有订单";
					Map<String, String> query4 = new HashMap<String, String>();
					query4.put("xtoken", xtoken[1]);
					query4.put("entity_id", OrderControllerTest.entityId_cart);	
					query4.put("page", "10000");	
					query4.put("page_size", "100000");

														
					
					String description5 = "description: token 无效, entity_id 正常, page 正常, page_size 正常, message 请先授权";
					Map<String, String> query5 = new HashMap<String, String>();
					query5.put("xtoken", "87468575");
					query5.put("entity_id", OrderControllerTest.entityId_cart);	
					query5.put("page", "");	
					query5.put("page_size", "");
					
					
					
					String description6 = "description: token 正常, entity_id 无效, page 正常, page_size 正常 , message提示：历史订单不存在";
					Map<String, String> query6 = new HashMap<String, String>();
					query6.put("xtoken", xtoken[1]);
					query6.put("entity_id", "123");	
					query6.put("page", "");	
					query6.put("page_size", "");
					
					return new Object[][]{
							{description1, query1, 200, 1, 200},
							{description2, query2, 200, 1, 200},
							{description3, query3, 200, 1, 200},
							{description4, query4, 200, 2, 400},
							{description5, query5, 200, -1, 400},
							{description6, query6, 200, 1, 400},

				};
			}
				
				
				@DataProvider(name = "reIssuedPreOrderTest")
				public static Object [][] reIssuedPreOrderTest(){
					
					List<String> orderList =  DataPrepared.orderDetailIdList;
					
					String description1 = "description: token 正常, waiting_order_id 正常, waitingOrder Kind: pre order, Status : time out ";
					Map<String, String> query1 = new HashMap<String, String>();
					query1.put("xtoken", xtoken[1]);
					query1.put("waiting_order_id", DataPrepared.waitingOrderDetailIdList.get(11));	
									
					
					
					String description2 = "description: token 正常, waiting_order_id 正常, waitingOrder kind : pre order, Status : pending audit ";
					Map<String, String> query2 = new HashMap<String, String>();
					query2.put("xtoken", xtoken[1]);
					query2.put("waiting_order_id", DataPrepared.waitingOrderDetailIdList.get(12));	


					
					String description3 = "description: token 正常, waiting_order_id 正常, waitingOrder kind : scan code, Status : time out";
					Map<String, String> query3 = new HashMap<String, String>();
					query3.put("xtoken", xtoken[1]);
					query3.put("waiting_order_id", DataPrepared.waitingOrderDetailIdList.get(13));	
	

					
					
					String description4 = "description: token 正常, waiting_order_id 为空";
					Map<String, String> query4 = new HashMap<String, String>();
					query4.put("xtoken", xtoken[1]);
					query4.put("waiting_order_id", "");	
					
									
					
					String description5 = "description: token 正常, waiting_order_id 无效";
					Map<String, String> query5 = new HashMap<String, String>();
					query5.put("xtoken", "87468575");
					query5.put("waiting_order_id", "123");	
					
					
					
					String description6 = "description: token 无效, waiting_order_id 正常";
					Map<String, String> query6 = new HashMap<String, String>();
					query6.put("xtoken", "123");
					query6.put("waiting_order_id", DataPrepared.waitingOrderDetailIdList.get(13));	
					
					
					return new Object[][]{
							{description1, query1, 200, 1, 400},
							{description2, query2, 200, 1, 400},
							{description3, query3, 200, 1, 200},
//							{description4, query4, 200, 2, 400},
//							{description5, query5, 200, -1, 400},
//							{description6, query6, 200, 1, 400},

				};
			}

}
