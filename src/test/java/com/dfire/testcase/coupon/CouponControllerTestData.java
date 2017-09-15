package com.dfire.testcase.coupon;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;

public class CouponControllerTestData {
	
		// 薯条内网测试店  token : c4c576cdfbc65a5c1c1355414dc3e260, entity_id : 99001331
		// 问荆内网测试店  token:  54452c7d6322ff78056424b22666bd1b ,entity_id : 99927792   
		// 问荆内网测试店2 token : 1b60096f549717236f500663093062cf ,entity_id : 99927836

		private static final String[] xtoken = {"c4c576cdfbc65a5c1c1355414dc3e260", 
												"54452c7d6322ff78056424b22666bd1b", 
												"1b60096f549717236f500663093062cf"};
		// uid[0] 为 无效 id, 与 token 无对应关系
		private static final String[] uid = {"7539759878350350545fgf56", "b379d59ed79c4991a05fb2eb2a7b1c2c", "b379d59ed79c4991a05fb2eb2a7b1c2c"};
		
		@DataProvider(name = "getCouponListTest")
		public static Object [][] getCouponListTest(){
			
			String description1 = "description: token 正常, entityId 正常, seat_code 正常, order_id 为空, 返回各个字段的count数目";
			Map<String, String> query1 = new HashMap<String, String>();
			query1.put("xtoken", xtoken[1]);
			query1.put("entity_id", "99927792");
			query1.put("seat_code", "B1");
			query1.put("order_id", "");
			

			String description2 = "description: token 正常, entity_id 正常, seat_code 为空, order_id 为空,  返回各个字段的count数目";
			Map<String, String> query2 = new HashMap<String, String>();
			query2.put("xtoken", xtoken[1]);
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
			query4.put("xtoken", xtoken[1]);
			query4.put("entity_id", "9090");
			query4.put("seat_code", "B1");
			query4.put("order_id", "");
			

			String description5 = "description: token 正常, entityId 正常, seat_code 非法无效, order_id 为空, message 提示：“会员已拥有该卡”";
			Map<String, String> query5 = new HashMap<String, String>();
			query5.put("xtoken", xtoken[1]);
			query5.put("entity_id", "99927792");
			query5.put("seat_code", "7h8ugh8949");
			query5.put("order_id", "");
			
			return new Object[][]{
					{description1, query1, 200, 1, 200},
					{description2, query2, 200, 1, 400},
					{description3, query3, 200, -1, 400},
					{description4, query4, 200, 9999, 400},
					{description5, query5, 200, 9999, 400},


			};
		}

}
