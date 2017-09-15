package com.dfire.testcase.querypay;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.dfire.testcase.prepay.PrePayControllerTest;
import com.dfire.wechat.util.BeanProvider;

public class QueryPayControllerTestData {
	
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
	
	@DataProvider(name = "getPrepayBillTest")
	public static Object [][] getPrepayBillTest(){
		
			// order 与  out_trade_no 一一对应, 对应的 entity_id 均为 "99927792"
			String order[] = {"9992779256788385015678cca8200042",    //  未支付
					          "999277925682ea4901568c31b7c0014a",    // pay_type : 会员卡  已支付
					          "999277925682f3b501568ce2ccd0029d"};   // pay_type : 微信   未支付
			
			String out_trade_no[] = {"9992779218GYaxJbkqQ8oe9gWJd8tY", 
									 "999277926BifhO2skAr8RBq1RDTa5T", 
									 "999277925uUWXWsjApoag8Zaajrn0G"};
		    
			// 1:微信,2:支付宝,4:会员卡
			String description1 = "description: token 正常, entity_id 正常, out_trade_no 正常, pay_type 正常, 已支付, message: 返回购物车中的菜品";
			Map<String, String> query1 = new HashMap<String, String>();
			query1.put("xtoken", xtoken[1]);
			query1.put("entity_id", "99927792");		
			query1.put("out_trade_no", out_trade_no[1]);
			query1.put("pay_type", "4");
			
			
			
			
			String description2 = "description: token 正常, entity_id 正常, out_trade_no 正常, pay_type 正常, 未支付,  message: 查询电子支付发生异常";
			Map<String, String> query2 = new HashMap<String, String>();
			query2.put("xtoken", xtoken[1]);
			query2.put("entity_id", "99927792");
			query2.put("out_trade_no", out_trade_no[0]);
			query2.put("pay_type", "4");
				
			
			
			String description3 = "description: token 非法, entity_id 正常, out_trade_no 正常, pay_type 正常, 已支付, message: 请先授权";
			Map<String, String> query3 = new HashMap<String, String>();
			query3.put("xtoken", "577fhfhjgkeurg");
			query3.put("entity_id", "99927792");
			query3.put("out_trade_no", out_trade_no[1]);
			query3.put("pay_type", "4");
	
					
			
			String description4 = "description: token 正常, entityId 非法, out_trade_no 正常, pay_type 正常, message: 应该返回 entity_id 无效";
			Map<String, String> query4 = new HashMap<String, String>();
			query4.put("xtoken", xtoken[1]);
			query4.put("entity_id", "9090");
			query4.put("out_trade_no", out_trade_no[1]);
			query4.put("pay_type", "4");
								
			
			
			String description5 = "description: token 正常, entityId 正常, out_trade_no 正常, pay_type 为支付宝 但是实际为微信,  "
					+ "但是包含 card_id, 未支付, message: 查询电子支付发生异常";
			Map<String, String> query5 = new HashMap<String, String>();
			query5.put("xtoken", xtoken[1]);
			query5.put("entity_id", "99927792");
			query5.put("out_trade_no", out_trade_no[2]);
			query5.put("pay_type", "2");
			query5.put("card_id", "0000000236");

		
			
			// entityId 包含特殊字符, 但是未被 Spring 框架拦截
			String description6 = "description: token 正常, entityId 包含非法字符, out_trade_no 正常, pay_type 正常, 已支付, message: 应该提示 entity_id 不能为非法字符";
			Map<String, String> query6 = new HashMap<String, String>();
			query6.put("xtoken", xtoken[1]);
			query6.put("entity_id", "9090……&&*");
			query6.put("out_trade_no", out_trade_no[1]);
			query6.put("pay_type", "4");
			
	
			
								
			// URL 中包含特殊字符会被 Spring 框架拦截, 该用例可忽略
			String description7 = "description: token 正常, entityId 正常, out_trade_no 正常包含非法字符, pay_type 正常, 已支付, message: 应该提示out_trade_no 不能包含非法字符";
			Map<String, String> query7 = new HashMap<String, String>();
			query7.put("xtoken", xtoken[1]);
			query7.put("entity_id", "99927792");
			query7.put("out_trade_no", "9992$^$%^%hO2skAr8RBq1RDTa5T");
			query7.put("pay_type", "4");
			
	
			
			// orderId 不起作用?
			String description8 = "description: token 正常, entityId 正常, out_trade_no 正常, pay_type 无效, 已支付, message: 返回正常数据, 但是应该返回 pay_type 无效";
			Map<String, String> query8 = new HashMap<String, String>();
			query8.put("xtoken", xtoken[1]);
			query8.put("entity_id", "99927792");		
			query8.put("out_trade_no", out_trade_no[1]);
			query8.put("pay_type", "40");
			
			
			
			// 该用例需要重新考虑
			String description9 = "description: token 正常, entityId 正常, ut_trade_no 正常, pay_type 无效, 已支付, message: 返回正常数据, 但是应该返回 pay_type 为负数";
			Map<String, String> query9 = new HashMap<String, String>();
			query9.put("xtoken", xtoken[1]);
			query9.put("entity_id", "99927792");		
			query9.put("out_trade_no", out_trade_no[1]);
			query9.put("pay_type", "-4");
			
			
			
			return new Object[][]{
					{description1, query1, 200, 1, 200},
					{description2, query2, 200, 3, 400},
					{description3, query3, 200, -1, 400},
					{description4, query4, 200, 6, 400},
					{description5, query5, 200, 3, 200},
					{description6, query6, 200, 6, 400},
//    			{description7, query7, 200, 6, 400},
					{description8, query8, 200, 2, 200},
					{description9, query9, 200, 9999, 400},
		};
	}
		
	
	

}
