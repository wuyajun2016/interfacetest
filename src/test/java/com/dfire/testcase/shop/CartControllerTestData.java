package com.dfire.testcase.shop;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.dfire.wechat.util.BeanProvider;
import com.google.gson.Gson;

/**
 * 在进行测试用例的设计时可以通过缓存中的数据进行用例设计
 * @author ljw
 *
 */
public class CartControllerTestData {
	
		// 薯条内网测试店  token : c4c576cdfbc65a5c1c1355414dc3e260, entity_id : 99001331
		// 问荆内网测试店  token:  54452c7d6322ff78056424b22666bd1b ,entity_id : 99927792   
		// 问荆内网测试店2 token : 1b60096f549717236f500663093062cf ,entity_id : 99927836

		private static final String[] xtoken = {"c4c576cdfbc65a5c1c1355414dc3e260", 
												"54452c7d6322ff78056424b22666bd1b", 
												"1b60096f549717236f500663093062cf"};
		// uid[0] 为 无效 id, 与 token 无对应关系
		private static final String[] uid = {"7539759878350350545fgf56", "b379d59ed79c4991a05fb2eb2a7b1c2c", "b379d59ed79c4991a05fb2eb2a7b1c2c"};
		
		@DataProvider(name = "getCartCountTest")
		public static Object [][] getCartCountTest(){
			
			String description1 = "description: token 正常, entityId 正常, seat_code 正常, order_id 为空, 返回各个字段的count数目";
			Map<String, String> query1 = new HashMap<String, String>();
			query1.put("xtoken", xtoken[2]);
			query1.put("entity_id", "99927792");
			query1.put("seat_code", "B1");
			query1.put("order_id", "");
			

			String description2 = "description: token 正常, entity_id 正常, seat_code 为空, order_id 为空,  返回各个字段的count数目";
			Map<String, String> query2 = new HashMap<String, String>();
			query2.put("xtoken", xtoken[2]);
			query2.put("entity_id", "99927792");
			query2.put("seat_code", "");
			query2.put("order_id", "");
			
			String description3 = "description: token 非法, entity_id 正常, seat_code 正常, order_id 为空";
			Map<String, String> query3 = new HashMap<String, String>();
			query3.put("xtoken", "577fhfhjgkeurg");
			query3.put("entity_id", "99927792");
			query3.put("seat_code", "B1");
			query3.put("order_id", "");
			
		
			String description4 = "description: token 正常, entityId 非法, seat_code 正常, order_id 为空, 返回各个字段的count数目";
			Map<String, String> query4 = new HashMap<String, String>();
			query4.put("xtoken", xtoken[2]);
			query4.put("entity_id", "9090");
			query4.put("seat_code", "B1");
			query4.put("order_id", "");
			

			String description5 = "description: token 正常, entityId 正常, seat_code 非法无效, order_id 为空, message 提示：“会员已拥有该卡”";
			Map<String, String> query5 = new HashMap<String, String>();
			query5.put("xtoken", xtoken[2]);
			query5.put("entity_id", "99927792");
			query5.put("seat_code", "7h8ugh8949");
			query5.put("order_id", "");
			
			return new Object[][]{
					{description1, query1, 200, 1, 200},
					{description2, query2, 200, 1, 400},
					{description3, query3, 200, -1, 400},
					{description4, query4, 200, 1, 400},
					{description5, query5, 200, 1, 400},


			};
		}
		
		@DataProvider(name = "createOwnCartTest")
		public static Object [][] createOwnCartTest(){
			
			String description1 = "description: token 正常, entityId 正常, seat_code 正常, order_id 为空, message: 请求成功";
			Map<String, String> query1 = new HashMap<String, String>();
			query1.put("xtoken", xtoken[1]);
			query1.put("entity_id", "99927792");
			query1.put("seat_code", "B1");
			query1.put("order_id", "");
			

			String description2 = "description: token 正常, entity_id 正常, seat_code 为空, order_id 为空,  message: 请求成功";
			Map<String, String> query2 = new HashMap<String, String>();
			query2.put("xtoken", xtoken[1]);
			query2.put("entity_id", "99927792");
			query2.put("seat_code", "");
			query2.put("order_id", "");
			
			String description3 = "description: token 非法, entity_id 正常, seat_code 正常, order_id 为空, message: 请先授权";
			Map<String, String> query3 = new HashMap<String, String>();
			query3.put("xtoken", "577fhfhjgkeurg");
			query3.put("entity_id", "99927792");
			query3.put("seat_code", "B1");
			query3.put("order_id", "");
			
		
			// 需要查看 购物车 缓存判断非法 entityId 可否创建虚拟的购物车   ????????
			String description4 = "description: token 正常, entityId 非法, seat_code 正常, order_id 为空, message: 请求成功";
			Map<String, String> query4 = new HashMap<String, String>();
			query4.put("xtoken", xtoken[1]);
			query4.put("entity_id", "9090");
			query4.put("seat_code", "B1");
			query4.put("order_id", "");
			
			// 需要查看 购物车 缓存判断非法 seat_code 可否创建虚拟的购物车   ????????
			String description5 = "description: token 正常, entityId 正常, seat_code 非法无效, order_id 为空, message: 请求成功";
			Map<String, String> query5 = new HashMap<String, String>();
			query5.put("xtoken", xtoken[1]);
			query5.put("entity_id", "99927792");
			query5.put("seat_code", "7h8ugh8949");
			query5.put("order_id", "");
			
			return new Object[][]{
					{description1, query1, 200, 1, 400},
					{description2, query2, 200, 1, 400},
					{description3, query3, 200, -1, 400},
					{description4, query4, 200, 1, 400},
					{description5, query5, 200, 1, 400},


			};
		}
		
		
		@DataProvider(name = "getCartDataTest")
		public static Object [][] getCartDataTest(){
			
			String description1 = "description: token 正常, entityId 正常, seat_code 正常, order_id 为空, 返回具体的购物车信息";
			Map<String, String> query1 = new HashMap<String, String>();
			query1.put("xtoken", xtoken[1]);
			query1.put("entity_id", "99927792");
			query1.put("seat_code", "B1");
			query1.put("order_id", "");
			

			String description2 = "description: token 正常, entity_id 正常, seat_code 为空, order_id 为空,  message: 参数错误!";
			Map<String, String> query2 = new HashMap<String, String>();
			query2.put("xtoken", xtoken[1]);
			query2.put("entity_id", "99927792");
			query2.put("seat_code", "");
			query2.put("order_id", "");
			
			String description3 = "description: token 非法, entity_id 正常, seat_code 正常, order_id 为空, message: 请先授权";
			Map<String, String> query3 = new HashMap<String, String>();
			query3.put("xtoken", "577fhfhjgkeurg");
			query3.put("entity_id", "99927792");
			query3.put("seat_code", "B1");
			query3.put("order_id", "");
			
		
			String description4 = "description: token 正常, entityId 非法, seat_code 正常, order_id 为空, message: 购物车过期";
			Map<String, String> query4 = new HashMap<String, String>();
			query4.put("xtoken", xtoken[1]);
			query4.put("entity_id", "9090");
			query4.put("seat_code", "B1");
			query4.put("order_id", "");
			
			
			String description5 = "description: token 正常, entityId 正常, seat_code 非法无效, order_id 为空, message: 购物车过期";
			Map<String, String> query5 = new HashMap<String, String>();
			query5.put("xtoken", xtoken[1]);
			query5.put("entity_id", "99927792");
			query5.put("seat_code", "7h8ugh8949");
			query5.put("order_id", "");
			
			String description6 = "description: token 正常, entityId 包含非法字符, seat_code 正常, order_id 为空, message: 应该提示 entity_id 不能为非法字符";
			Map<String, String> query6 = new HashMap<String, String>();
			query6.put("xtoken", xtoken[1]);
			query6.put("entity_id", "9090……&&*");
			query6.put("seat_code", "B1");
			query6.put("order_id", "");
			
			
			String description7 = "description: token 正常, entityId 正常, seat_code 包含非法字符, order_id 为空, message: seat_code 不能为非法字符";
			Map<String, String> query7 = new HashMap<String, String>();
			query7.put("xtoken", xtoken[1]);
			query7.put("entity_id", "99927792");
			query7.put("seat_code", "7h8$%^%^49");
			query7.put("order_id", "");
			
			return new Object[][]{
					{description1, query1, 200, 1, 200},
					{description2, query2, 200, 0, 400},
					{description3, query3, 200, -1, 400},
					{description4, query4, 200, 5, 400},
					{description5, query5, 200, 5, 400},
					{description6, query6, 200, 6, 400},
					{description5, query5, 200, 6, 400},

			};
		}

		
		@DataProvider(name = "modifyCartTest")
		public static Object [][] modifyCartTest(){
			
			String description1 = "description: token 正常, entity_id 正常, seat_code 正常, order_id 正常, 普通菜, 返回具体的修改信息";
			Map<String, String> query1 = new HashMap<String, String>();
			query1.put("xtoken", xtoken[1]);
			query1.put("entity_id", "99927792");
			query1.put("seat_code", CartControllerTest.seatCode1);
			query1.put("order_id", CartControllerTest.orderId1);			
			String cartSuit1 = CartControllerTest.cartSuit;

			
			String description2 = "description: token 正常, entity_id 正常, seat_code 正常, order_id 正常, 套菜(带有子菜), 返回具体的修改信息";
			Map<String, String> query2 = new HashMap<String, String>();
			query2.put("xtoken", xtoken[1]);
			query2.put("entity_id", "99927792");
			query2.put("seat_code", CartControllerTest.seatCode2);
			query2.put("order_id", CartControllerTest.orderId2);
			String cartSuit2 = CartControllerTest.cartSuit2;
			
			
			String description3 = "description: token 非法, entity_id 正常, seat_code 正常, order_id 正常, 普通菜, message: 请先授权";
			Map<String, String> query3 = new HashMap<String, String>();
			query3.put("xtoken", "577fhfhjgkeurg");
			query3.put("entity_id", "99927792");
			query3.put("seat_code", CartControllerTest.seatCode1);
			query3.put("order_id",  CartControllerTest.orderId1);
			String cartSuit3 = CartControllerTest.cartSuit;
			
		
			String description4 = "description: token 正常, entityId 非法, seat_code 正常, order_id 正常, 普通菜, message:请求失败, 应该提示 entity_id 无效 ";
			Map<String, String> query4 = new HashMap<String, String>();
			query4.put("xtoken", xtoken[1]);
			query4.put("entity_id", "9090");
			query4.put("seat_code", CartControllerTest.seatCode1);
			query4.put("order_id", CartControllerTest.orderId1);
			String cartSuit4 = CartControllerTest.cartSuit;
			
			
			String description5 = "description: token 正常, entityId 正常, seat_code 无效, order_id 正常, 普通菜, message: 购物车过期, 应该提示 seat_code 无效";
			Map<String, String> query5 = new HashMap<String, String>();
			query5.put("xtoken", xtoken[1]);
			query5.put("entity_id", "99927792");
			query5.put("seat_code", "DFGHJ");
			query5.put("order_id", CartControllerTest.orderId1);
			String cartSuit5 = CartControllerTest.cartSuit;
			
			
			String description6 = "description: token 正常, entityId 包含非法字符, seat_code 正常, order_id 正常, 普通菜, message: 应该提示 entity_id 不能为非法字符";
			Map<String, String> query6 = new HashMap<String, String>();
			query6.put("xtoken", xtoken[1]);
			query6.put("entity_id", "9090……&&*");
			query6.put("seat_code", CartControllerTest.seatCode1);
			query6.put("order_id", CartControllerTest.orderId1);
			String cartSuit6 = CartControllerTest.cartSuit;
			
			
			String description7 = "description: token 正常, entityId 正常, seat_code 包含非法字符, order_id 正常, 普通菜, message: seat_code 不能为非法字符";
			Map<String, String> query7 = new HashMap<String, String>();
			query7.put("xtoken", xtoken[1]);
			query7.put("entity_id", "99927792");
			query7.put("seat_code", "7h8$%^%^49");
			query7.put("order_id", CartControllerTest.orderId1);
			String cartSuit7 = CartControllerTest.cartSuit;
			
			return new Object[][]{
					{description1, query1, cartSuit1, 200, 1, 200},
					{description2, query2, cartSuit2, 200, 1, 200},
					{description3, query3, cartSuit3, 200, -1, 400},
					{description4, query4, cartSuit4, 200, 999, 400},
					{description5, query5, cartSuit5, 200, 5, 400},
					{description6, query6, cartSuit6, 200, 6, 400},
					{description5, query5, cartSuit7, 200, 6, 400},

			};
		}
		
		@DataProvider(name = "clearCartTest")
		public static Object [][] clearCartTest(){
			
			String description1 = "description: token 正常, entityId 正常, seat_code 正常, order_id 为空, message: 请求成功";
			Map<String, String> query1 = new HashMap<String, String>();
			query1.put("xtoken", xtoken[1]);
			query1.put("entity_id", "99927792");
			query1.put("seat_code", "A4");
			query1.put("order_id", "");			

			String description2 = "description: token 正常, entity_id 正常, seat_code 为空, order_id 为空,  message: 参数错误!";
			Map<String, String> query2 = new HashMap<String, String>();
			query2.put("xtoken", xtoken[1]);
			query2.put("entity_id", "99927792");
			query2.put("seat_code", "");
			query2.put("order_id", "");
			
			String description3 = "description: token 非法, entity_id 正常, seat_code 正常, order_id 为空, message: 请先授权";
			Map<String, String> query3 = new HashMap<String, String>();
			query3.put("xtoken", "577fhfhjgkeurg");
			query3.put("entity_id", "99927792");
			query3.put("seat_code", "A4");
			query3.put("order_id", "");
			
		
			String description4 = "description: token 正常, entityId 非法, seat_code 正常, order_id 为空, message: 购物车过期";
			Map<String, String> query4 = new HashMap<String, String>();
			query4.put("xtoken", xtoken[1]);
			query4.put("entity_id", "9090");
			query4.put("seat_code", "A4");
			query4.put("order_id", "");
			
			
			String description5 = "description: token 正常, entityId 正常, seat_code 非法无效, order_id 为空, message: 购物车过期";
			Map<String, String> query5 = new HashMap<String, String>();
			query5.put("xtoken", xtoken[1]);
			query5.put("entity_id", "99927792");
			query5.put("seat_code", "7h8ugh8949");
			query5.put("order_id", "");
			
			String description6 = "description: token 正常, entityId 包含非法字符, seat_code 正常, order_id 为空, message: 应该提示 entity_id 不能为非法字符";
			Map<String, String> query6 = new HashMap<String, String>();
			query6.put("xtoken", xtoken[1]);
			query6.put("entity_id", "9090……&&*");
			query6.put("seat_code", "A4");
			query6.put("order_id", "");
			
			// 该非法参数在对 URL进行校验时会进行字符非法的判断
			String description7 = "description: token 正常, entityId 正常, seat_code 包含非法字符, order_id 为空, message: seat_code 不能为非法字符";
			Map<String, String> query7 = new HashMap<String, String>();
			query7.put("xtoken", xtoken[1]);
			query7.put("entity_id", "99927792");
			query7.put("seat_code", "7h8$%^%^49");
			query7.put("order_id", "");
			
			return new Object[][]{
					{description1, query1, 200, 1, 400},
					{description2, query2, 200, 0, 400},
					{description3, query3, 200, -1, 400},
					{description4, query4, 200, 5, 400},
					{description5, query5, 200, 5, 400},
					{description6, query6, 200, 6, 400},
//					{description7, query7, 200, 6, 400},

			};
		}
		
		/**
		 * 创建购物车时从 session 中获取的 customerID, 利用该 customerID 创建购物车,
		 * 因此在执行 clearOwnCartTest时, customer_register_id 需要与创建购物车时使用的 customerID 保持一致
		 * @return
		 */
		@DataProvider(name = "clearOwnCartTest")
		public static Object [][] clearOwnCartTest(){
			
			String description1 = "description: customerId 正常, token 正常, entityId 正常, seat_code 正常, order_id 为空, message: 请求成功";
			Map<String, String> query1 = new HashMap<String, String>();
			query1.put("xtoken", xtoken[1]);
			query1.put("entity_id", "99927792");
			query1.put("seat_code", "A4");
			query1.put("order_id", "");		
			query1.put("customer_register_id", uid[1]);

			String description2 = "description: customerId 正常, token 正常, entity_id 正常, seat_code 为空, order_id 为空,  message: 参数错误!";
			Map<String, String> query2 = new HashMap<String, String>();
			query2.put("xtoken", xtoken[1]);
			query2.put("entity_id", "99927792");
			query2.put("seat_code", "");
			query2.put("order_id", "");
			query2.put("customer_register_id", uid[1]);
			
			String description3 = "description: customerId 正常, token 非法, entity_id 正常, seat_code 正常, order_id 为空, message: 请先授权";
			Map<String, String> query3 = new HashMap<String, String>();
			query3.put("xtoken", "577fhfhjgkeurg");
			query3.put("entity_id", "99927792");
			query3.put("seat_code", "A4");
			query3.put("order_id", "");
			query3.put("customer_register_id", uid[1]);
		
			String description4 = "description: customerId 正常, token 正常, entityId 非法, seat_code 正常, order_id 为空, message: 购物车过期";
			Map<String, String> query4 = new HashMap<String, String>();
			query4.put("xtoken", xtoken[1]);
			query4.put("entity_id", "9090");
			query4.put("seat_code", "A4");
			query4.put("order_id", "");
			query4.put("customer_register_id", uid[1]);
			
			String description5 = "description: customerId 正常, token 正常, entityId 正常, seat_code 非法无效, order_id 为空, message: 购物车过期";
			Map<String, String> query5 = new HashMap<String, String>();
			query5.put("xtoken", xtoken[1]);
			query5.put("entity_id", "99927792");
			query5.put("seat_code", "7h8ugh8949");
			query5.put("order_id", "");
			query5.put("customer_register_id", uid[1]);
			
			String description6 = "description: customerId 与token不对应, token 正常, entityId 包含非法字符, seat_code 正常, order_id 为空, message: 应该提示 customerId 无效";
			Map<String, String> query6 = new HashMap<String, String>();
			query6.put("xtoken", xtoken[1]);
			query6.put("entity_id", "99927792");
			query6.put("seat_code", "A4");
			query6.put("order_id", "");
			query6.put("customer_register_id", uid[0]);
			
			return new Object[][]{
					{description1, query1, 200, 1, 200},
					{description2, query2, 200, 0, 400},
					{description3, query3, 200, -1, 400},
					{description4, query4, 200, 5, 400},
					{description5, query5, 200, 5, 400},
					{description6, query6, 200, 6, 400},

			};
		}
		
		
		@DataProvider(name = "modifyInfoTest")
		public static Object [][] modifyInfoTest(){
			
			String description1 = "description: token 正常, entityId 正常, seat_code 正常, people 正常, memo 正常, message: 请求成功";
			Map<String, String> query1 = new HashMap<String, String>();
			query1.put("xtoken", xtoken[1]);
			query1.put("entity_id", "99927792");
			query1.put("seat_code", "A4");
			query1.put("people", "8");		
			query1.put("memo", "备注");

			String description2 = "description: token 正常, entity_id 正常, people 为负数, memo 正常,  message: 请求失败";
			Map<String, String> query2 = new HashMap<String, String>();
			query2.put("xtoken", xtoken[1]);
			query2.put("entity_id", "99927792");
			query2.put("seat_code", "");
			query2.put("people", "-1");
			query2.put("memo", "备注");
			
			String description3 = "description: token 非法, entity_id 正常, people 正常, memo 正常, message: 请先授权";
			Map<String, String> query3 = new HashMap<String, String>();
			query3.put("xtoken", "577fhfhjgkeurg");
			query3.put("entity_id", "99927792");
			query3.put("seat_code", "A4");
			query3.put("people", "8");
			query3.put("memo", "备注");
		
			String description4 = "description: token 正常, entityId 非法, people 正常, memo 正常, message: 请求失败";
			Map<String, String> query4 = new HashMap<String, String>();
			query4.put("xtoken", xtoken[1]);
			query4.put("entity_id", "9090");
			query4.put("seat_code", "A4");
			query4.put("people", "8");
			query4.put("memo", "备注");
			
			String description5 = "description: token 正常, entityId 正常, seat_code 非法无效, people 正常, memo 正常, message: 请求失败";
			Map<String, String> query5 = new HashMap<String, String>();
			query5.put("xtoken", xtoken[1]);
			query5.put("entity_id", "99927792");
			query5.put("seat_code", "7h8ugh8949");
			query5.put("people", "9");
			query5.put("memo", "备注");
			
			String description6 = "description: token 正常, entityId 正常, seat_code 正常, people 数据过大, memo 正常, "
					+ "message: 请求失败, 建议 message 修改为 people 数值过大";
			Map<String, String> query6 = new HashMap<String, String>();
			query6.put("xtoken", xtoken[1]);
			query6.put("entity_id", "99927792");
			query6.put("seat_code", "A4");
			query6.put("people", "999999999999999999999999999999");
			query6.put("memo", "备注");
			
			return new Object[][]{
					{description1, query1, 200, 1, 200},
					{description2, query2, 200, 0, 400},
					{description3, query3, 200, -1, 400},
					{description4, query4, 200, 0, 400},
					{description5, query5, 200, 0, 400},
					{description6, query6, 200, 999, 400},

			};
		}
		
		
		
		@DataProvider(name = "getRecommendMenuListTest")
		public static Object [][] getRecommendMenuListTest(){
			
			String description1 = "description: token 正常, entityId 正常, seat_code 正常, people 正常, message: 获取智能推荐菜列表失败";
			Map<String, String> query1 = new HashMap<String, String>();
			query1.put("xtoken", xtoken[1]);
			query1.put("entity_id", "99927792");
			query1.put("seat_code", "A4");
			query1.put("people", "8");		

			String description2 = "description: token 正常, entity_id 正常, people 为负数, message: 请求失败";
			Map<String, String> query2 = new HashMap<String, String>();
			query2.put("xtoken", xtoken[1]);
			query2.put("entity_id", "99927792");
			query2.put("seat_code", "");
			query2.put("people", "-1");
			
			String description3 = "description: token 非法, entity_id 正常, people 正常, message: 请先授权";
			Map<String, String> query3 = new HashMap<String, String>();
			query3.put("xtoken", "577fhfhjgkeurg");
			query3.put("entity_id", "99927792");
			query3.put("seat_code", "A4");
			query3.put("people", "8");
		
			String description4 = "description: token 正常, entityId 非法, people 正常, message: 请求失败";
			Map<String, String> query4 = new HashMap<String, String>();
			query4.put("xtoken", xtoken[1]);
			query4.put("entity_id", "9090");
			query4.put("seat_code", "A4");
			query4.put("people", "8");
			
			String description5 = "description: token 正常, entityId 正常, seat_code 非法无效, people 正常, message: 请求失败";
			Map<String, String> query5 = new HashMap<String, String>();
			query5.put("xtoken", xtoken[1]);
			query5.put("entity_id", "99927792");
			query5.put("seat_code", "7h8ugh8949");
			query5.put("people", "9");

			
			String description6 = "description: token 正常, entityId 正常, seat_code 正常, people 数据过大, "
					+ "message: 请求失败, 建议 message 修改为 people 数值过大";
			Map<String, String> query6 = new HashMap<String, String>();
			query6.put("xtoken", xtoken[1]);
			query6.put("entity_id", "99927792");
			query6.put("seat_code", "A4");
			query6.put("people", "999999999999999999999999999999");

			
			return new Object[][]{
					{description1, query1, 200, 0, 400},
//					{description2, query2, 200, 0, 400},
//					{description3, query3, 200, -1, 400},
//					{description4, query4, 200, 0, 400},
//					{description5, query5, 200, 0, 400},
//					{description6, query6, 200, 999, 400},

			};
		}
		
		
		@DataProvider(name = "getRecommendMenuByIdTest")
		public static Object [][] getRecommendMenuByIdTest(){
			
			String description1 = "description: token 正常, entityId 正常, seat_code 正常, people 正常, message: 请求失败";
			Map<String, String> query1 = new HashMap<String, String>();
			query1.put("xtoken", xtoken[1]);
			query1.put("entity_id", "99927792");
			query1.put("seat_code", "A4");
			query1.put("label_id", "8");		
			query1.put("page", "8");
			query1.put("total_page", "18");

			String description2 = "description: token 正常, entity_id 正常, people 为负数, message: 请求失败";
			Map<String, String> query2 = new HashMap<String, String>();
			query2.put("xtoken", xtoken[1]);
			query2.put("entity_id", "99927792");
			query2.put("seat_code", "");
			query2.put("people", "-1");
			
			String description3 = "description: token 非法, entity_id 正常, people 正常, message: 请先授权";
			Map<String, String> query3 = new HashMap<String, String>();
			query3.put("xtoken", "577fhfhjgkeurg");
			query3.put("entity_id", "99927792");
			query3.put("seat_code", "A4");
			query3.put("people", "8");
		
			String description4 = "description: token 正常, entityId 非法, people 正常, message: 请求失败";
			Map<String, String> query4 = new HashMap<String, String>();
			query4.put("xtoken", xtoken[1]);
			query4.put("entity_id", "9090");
			query4.put("seat_code", "A4");
			query4.put("people", "8");
			
			String description5 = "description: token 正常, entityId 正常, seat_code 非法无效, people 正常, message: 请求失败";
			Map<String, String> query5 = new HashMap<String, String>();
			query5.put("xtoken", xtoken[1]);
			query5.put("entity_id", "99927792");
			query5.put("seat_code", "7h8ugh8949");
			query5.put("people", "9");

			
			String description6 = "description: token 正常, entityId 正常, seat_code 正常, people 数据过大, "
					+ "message: 请求失败, 建议 message 修改为 people 数值过大";
			Map<String, String> query6 = new HashMap<String, String>();
			query6.put("xtoken", xtoken[1]);
			query6.put("entity_id", "99927792");
			query6.put("seat_code", "A4");
			query6.put("people", "999999999999999999999999999999");

			
			return new Object[][]{
					{description1, query1, 200, 9999, 400},
//					{description2, query2, 200, 0, 400},
//					{description3, query3, 200, -1, 400},
//					{description4, query4, 200, 0, 400},
//					{description5, query5, 200, 0, 400},
//					{description6, query6, 200, 999, 400},

			};
		}
}
