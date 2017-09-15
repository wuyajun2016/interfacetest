package com.dfire.testcase.shop;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.dfire.wechat.util.CartIncludeSuitForm;
import com.dfire.wechat.util.HttpRequestEx;
import com.dfire.wechat.util.PathForPost;


public class CartControllerUtils {
	private static final Logger logger = Logger.getLogger(CartControllerUtils.class);
	private static final String[] xtoken = {"c4c576cdfbc65a5c1c1355414dc3e260", 
											"54452c7d6322ff78056424b22666bd1b", 
											"1b60096f549717236f500663093062cf"};
	
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
	
	
	// get info from cart with list format, the data is in redis with keyword "order_cart"
	public static boolean listCartData(HttpRequestEx httpRequest, String entity_id, String seat_id, String order_id){
		boolean flag = false;
		try{
			logger.info("获取虚拟购物车数据及用餐人数");
			Map<String, String> query = new HashMap<String, String>();
			query.put("xtoken", xtoken[1]);
			query.put("entity_id", entity_id);
			query.put("seat_code", seat_id);
			query.put("order_id", order_id);
			
			httpRequest.get(PathForPost.getPathForGetCartData(), query);
			
			flag = true;
			return flag;
		}catch(Exception e){
			logger.info(e.toString());
		}
		return false;
	}
		
	// show privilege title
	public static boolean showPrivilegeTitle(HttpRequestEx httpRequest, String entity_id){
		boolean flag = false;
		try{
			logger.info("优惠载体文字显示栏标题");
			Map<String, String> query = new HashMap<String, String>();
			query.put("xtoken", xtoken[1]);
			query.put("entity_id", entity_id);
			
			httpRequest.get(PathForPost.getPathForShowTitle(), query);
			
			flag = true;
			return flag;
		}catch(Exception e){
			logger.info(e.toString());
		}
		return false;
	}
	
	// list menus
	public static boolean listMenus(HttpRequestEx httpRequest, String entity_id, String repeat, String recommend){
		boolean flag = false;
		try{
			logger.info("获取店铺菜单列表");
			Map<String, String> query = new HashMap<String, String>();
			query.put("xtoken", xtoken[1]);
			query.put("entity_id", entity_id);
			query.put("repeat", repeat);		
			query.put("recommend", recommend);
			httpRequest.get(PathForPost.getPathForListMenus(), query);
			
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