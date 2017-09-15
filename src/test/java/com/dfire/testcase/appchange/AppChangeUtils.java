package com.dfire.testcase.appchange;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.dfire.qa.meal.bean.BaseParamBean;
import com.dfire.wechat.util.CartIncludeSuitForm;
import com.dfire.wechat.util.HttpRequestEx;
import com.dfire.wechat.util.PathForPost;


public class AppChangeUtils {
	private static final Logger logger = Logger.getLogger(AppChangeUtils.class);
	private static final String[] xtoken = {"c4c576cdfbc65a5c1c1355414dc3e260", 
											"54452c7d6322ff78056424b22666bd1b", 
											"1b60096f549717236f500663093062cf"};
	
	//登录专用
	public static String logining(){
		
		try{
//			logger.info("数据准备：登陆获取token");
//			Map<String, String> query = new HashMap<String, String>();
//			query.put("xtoken", "MTRhODI2MDY0ZjgyNTVlZmMyYTJkZGMzMzYzOGY1MDg=");
//			query.put("entity_id", "00026863");
//			query.put("seat_code", "");
//			query.put("order_id", "");
//			
//			Map<String, String> query2 = new HashMap<String, String>();
//			query2.put("xtoken", "MTRhODI2MDY0ZjgyNTVlZmMyYTJkZGMzMzYzOGY1MDg");
//			query2.put("entity_id", "00026863");
//			query2.put("seat_code", "");
//			query2.put("order_id", "");
//			
//			httpRequest.post(PathForPost.getPathForCreateOwnCart(), query2);
		}catch(Exception e){
			logger.info(e.toString());
		}
		
		return null;
		
	}
	
	
	//将菜品放入购物车
	public static boolean putDishToShoppingCart(HttpRequestEx httpRequest){
		boolean flag = false;
		try{

			logger.info("数据准备：将菜品放入购物车");
			Map<String, String> query = new HashMap<String, String>();
			query.put("xtoken", "MTRhODI2MDY0ZjgyNTVlZmMyYTJkZGMzMzYzOGY1MDg=");
			query.put("entity_id", "00026863");
			query.put("seat_code", "");
			query.put("order_id", "");
			
			Map<String, String> query2 = new HashMap<String, String>();
			query2.put("xtoken", "MTRhODI2MDY0ZjgyNTVlZmMyYTJkZGMzMzYzOGY1MDg");
			query2.put("entity_id", "00026863");
			query2.put("seat_code", "");
			query2.put("order_id", "");
			
			httpRequest.post(PathForPost.getPathForCreateOwnCart(), query2);
			httpRequest.get(PathForPost.getPathForGetUserCart(), query);
			
			flag = true;
			return flag;
		}catch(Exception e){
			logger.info(e.toString());
		}
		return false;
	}
	
	// create own cart , the data is in redis with keyword "order_cart"
	public static boolean createCart(HttpRequestEx httpRequest, String entity_id, String seat_id, String order_id){
		boolean flag = false;
		try{

			logger.info("创建购物车");
			Map<String, String> query = new HashMap<String, String>();
			query.put("xtoken", xtoken[1]);
			query.put("entity_id", entity_id);
			query.put("seat_code", seat_id);
			query.put("order_id", order_id);
			
			httpRequest.post(PathForPost.getPathForCreateOwnCart(), query);
			
			flag = true;
			return flag;
		}catch(Exception e){
			logger.info(e.toString());
		}
		return false;
	}
	
	
	public static boolean createCart(HttpRequestEx httpRequest, String entity_id, String seat_id, 
			                         String order_id, String people_count, String memo){
		
		boolean flag = false;
		try{
			String description1 = "创建个人购物车";
			Map<String, String> query = new HashMap<String, String>();
			query.put("xtoken", xtoken[1]);
			query.put("entity_id", entity_id);
			query.put("seat_code", seat_id);
			
			query.put("order_id", order_id);
			query.put("people_count", people_count);
			query.put("memo", memo);
			
			httpRequest.post(PathForPost.getPathForCreateOwnCart(), query);
			
			flag = true;
			return flag;
		}catch(Exception e){
			logger.info(e.toString());
		}
		return false;
	}
	
	
	
	// add dish into cart
	public static boolean modifyCart(HttpRequestEx httpRequest, String entity_id, String seat_code, 
			String order_id, String cartIncludeSuitForm){
		boolean flag = false;
		try{
			String description1 = "添加菜品";
			Map<String, String> query = new HashMap<String, String>();
			query.put("xtoken", xtoken[1]);
			query.put("entity_id", entity_id);
			query.put("seat_code", seat_code);
			query.put("order_id", order_id);
			
			httpRequest.post(PathForPost.getPathForModifyCart(), query, cartIncludeSuitForm);
			
			flag = true;
			return flag;
		}catch(Exception e){
			logger.info(e.toString());
		}
		return false;
	}
	
	
	
	
	
	
}