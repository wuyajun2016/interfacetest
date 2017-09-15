package com.dfire.testcase.function.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.dfire.testcase.function.bean.BaseParamBean;
import com.dfire.testcase.function.util.base.SeatNoPrePayUtil;
import com.dfire.testcase.function.util.base.ShopNoPrePayUtil;
import com.dfire.testcase.function.util.base.WechatBaseUtil;
import com.dfire.wechat.util.CartIncludeSuitForm;
import com.dfire.wechat.util.HttpRequestEx;
import com.dfire.wechat.util.Response;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class DataService {
	
	private static final Logger logger = Logger.getLogger(DataService.class);
	
	// 店码数据验证
	public static boolean DataVerified(HttpRequestEx httpRequest, BaseParamBean baseParam, Map<String, String> otherParameter,
			String people, List<CartIncludeSuitForm> httpJsonForCartSuitList){
		
		boolean result = false;
		
		try{
			// 获取虚拟购物车数据及用餐人数
			Response response = ShopNoPrePayUtil.getUserCart(httpRequest, baseParam, otherParameter);
			JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(resp.get("code").getAsInt(), 1);
			
			// 构造验证参数
			logger.info("数据校验开始");
			
			Map<String, Map<String, CartIncludeSuitForm>> cartSuitMap = new HashMap<String, Map<String,CartIncludeSuitForm>>();
			
			for(CartIncludeSuitForm cartSuit : httpJsonForCartSuitList){
				
				if(cartSuitMap.keySet().contains(cartSuit.getUid())){
					cartSuitMap.get(cartSuit.getUid()).put(cartSuit.getMenuId(), cartSuit);
				}
				else{
					Map<String, CartIncludeSuitForm> menuMap = new HashMap<String, CartIncludeSuitForm>();
					menuMap.put(cartSuit.getMenuId(), cartSuit);
					cartSuitMap.put(cartSuit.getUid(), menuMap);
				}
				
			}
									
			
			// 参数验证
			result = SeatNoPrePayUtil.MenusValidate(response, people, cartSuitMap);
			Assert.assertEquals(result, true);
			logger.info("数据校验成功");
			
			return result;
		}catch(Exception e){
			logger.info("数据校验失败");
			logger.info(e.toString());
		}
		
		return result;
		
	}
		
		
	// 桌码数据验证
	public static boolean DataVerified(HttpRequestEx httpRequest, BaseParamBean baseParam, 
			String people, List<CartIncludeSuitForm> httpJsonForCartSuitList){
		
		boolean result = false;
		
		try{
			// 获取虚拟购物车数据及用餐人数
			Response response = WechatBaseUtil.listCartData(httpRequest, baseParam);
			JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(resp.get("code").getAsInt(), 1);
			
			// 构造验证参数
			logger.info("数据校验开始");
			
			Map<String, Map<String, CartIncludeSuitForm>> cartSuitMap = new HashMap<String, Map<String,CartIncludeSuitForm>>();
			
			for(CartIncludeSuitForm cartSuit : httpJsonForCartSuitList){
				
				if(cartSuitMap.keySet().contains(cartSuit.getUid())){
					cartSuitMap.get(cartSuit.getUid()).put(cartSuit.getMenuId(), cartSuit);
				}
				else{
					Map<String, CartIncludeSuitForm> menuMap = new HashMap<String, CartIncludeSuitForm>();
					menuMap.put(cartSuit.getMenuId(), cartSuit);
					cartSuitMap.put(cartSuit.getUid(), menuMap);
				}
				
			}
									
			
			// 参数验证
			result = SeatNoPrePayUtil.MenusValidate(response, people, cartSuitMap);
			Assert.assertEquals(result, true);
			logger.info("数据校验成功");
			
			return result;
		}catch(Exception e){
			logger.info("数据校验失败");
			logger.info(e.toString());
		}
		
		return result;
		
	}
	
	
	/**
	 * 进行菜单验证， 待验证菜单数据填写于 Map 容器中
	 * Map<String, Map<String, CartIncludeSuitForm>>, 第一个参数表示 用户 ID, 一个用户对应一个购物车
	 * 单个用户的购物车数据放置于   Map<String, CartIncludeSuitForm>  中，其中第一个参数表示菜单 ID, 一个菜单 ID 对应相应的菜单数据
	 * @param response
	 * @param expectedParameter
	 * @return
	 */
    public static boolean MenusValidate(Response response, String people, Map<String, Map<String, CartIncludeSuitForm>> expectedCartSuit){
    	
    	logger.info("菜单验证开始");
    	boolean flag = false;
    	
    	try{
    		JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();
    		
    		// 就餐人数验证
			resp.get("data").getAsJsonObject().get("people").getAsString().equalsIgnoreCase(people);
			
			// 获取购物车数组
			JsonArray cartsArray = resp.get("data").getAsJsonObject().get("userCarts").getAsJsonArray();
			
			// 获取用户 ID
			Set<String> uidSet = expectedCartSuit.keySet();
			
			for(JsonElement cart : cartsArray){
				
				// 用户信息确认
				JsonObject customer = cart.getAsJsonObject().get("customerVo").getAsJsonObject();
				boolean customerExist = uidSet.contains(customer.get("id").getAsString());
				Assert.assertTrue(customerExist);
				
				// 获取菜单详情列表
				JsonArray menuArray = cart.getAsJsonObject().get("cartVoList").getAsJsonArray();
				
				// 获取特定用户的购物车中的 menuID 集合
				Set<String> menuIdList = expectedCartSuit.get(customer.get("id").getAsString()).keySet();
				Map<String, CartIncludeSuitForm> originMenuList = expectedCartSuit.get(customer.get("id").getAsString());
				
				// 对具体的菜品进行验证, 该菜可能为 普通菜 或者为 套菜 
				for(JsonElement menuElement : menuArray){
					
					JsonObject menuJsonObject = menuElement.getAsJsonObject();
					
					// 判断该菜是否为套菜
					if(menuJsonObject.has("childCartVos")){
						
						// 验证父菜
						Assert.assertTrue(menuIdList.contains(menuJsonObject.get("menuId").getAsString()), "接口返回的套菜中父菜的 menuId 在预期的数据中不存在");
						
						// 获取具体的菜品, 该菜为套菜，包含子菜
						CartIncludeSuitForm cartSuitForm = originMenuList.get(menuJsonObject.get("menuId").getAsString());
						
						// 验证父菜数据
						Assert.assertEquals(menuJsonObject.get("kind").getAsInt(), 2, "父菜数据中的 kind 验证失败");
						Assert.assertEquals(menuJsonObject.get("num").getAsInt(), cartSuitForm.getNum().intValue(), "父菜数据中的 num 验证失败");
						menuJsonObject.get("name").getAsString().equalsIgnoreCase(cartSuitForm.getMenuName());
						
						// 获取原始子菜信息
						Map<String, CartIncludeSuitForm> childSuitMap = new HashMap<String, CartIncludeSuitForm>();
						for(CartIncludeSuitForm childSuit : cartSuitForm.getChildCartVos()){
							childSuitMap.put(childSuit.getMenuId(), childSuit);
						}
						Set<String> childMenuIdSet = childSuitMap.keySet();
						
						// 子菜数据验证
						for(JsonElement childElement : menuJsonObject.get("childCartVos").getAsJsonArray()){
							
							JsonObject childJsonObject = childElement.getAsJsonObject();
							
							// 表明该菜为普通菜
							Assert.assertTrue(childMenuIdSet.contains(childJsonObject.get("menuId").getAsString()), "接口返回的子菜的 menuId 在预期的数据中不存在");
							
							// 获取具体的菜品
							CartIncludeSuitForm childCartSuitForm = childSuitMap.get(childJsonObject.get("menuId").getAsString());
							
							// 验证菜品数据
							Assert.assertEquals(childJsonObject.get("kind").getAsInt(), 1, "子菜数据中的 kind 验证失败");
							Assert.assertEquals(childJsonObject.get("num").getAsInt(), childCartSuitForm.getNum().intValue(), "子菜数据中的 num 验证失败");
							childJsonObject.get("name").getAsString().equalsIgnoreCase(childCartSuitForm.getMenuName());
						}
						
					}else{
						// 表明该菜为普通菜
						Assert.assertTrue(menuIdList.contains(menuJsonObject.get("menuId").getAsString()), "接口返回的普通菜的 menuId 在预期数据中不存在");
						
						// 获取具体的菜品
						CartIncludeSuitForm cartSuitForm = originMenuList.get(menuJsonObject.get("menuId").getAsString());
						
						// 验证菜品数据
						Assert.assertEquals(menuJsonObject.get("kind").getAsInt(), 1,  "普通菜的 kind 验证失败");
						Assert.assertEquals(menuJsonObject.get("num").getAsInt(), cartSuitForm.getNum().intValue(), "普通菜的 num 验证失败");
						menuJsonObject.get("name").getAsString().equalsIgnoreCase(cartSuitForm.getMenuName());
						
					}
										
					
				}
				
				
			}
			
    		flag = true;
    		logger.info("菜单验证成功");
 
    	}catch(Exception e){
    		logger.info("菜单验证失败");
    		logger.info(e.toString());
    	}
    	
    	return flag;
    }
	

}
