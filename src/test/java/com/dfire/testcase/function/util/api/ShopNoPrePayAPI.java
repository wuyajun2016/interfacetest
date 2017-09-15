package com.dfire.testcase.function.util.api;

import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONPath;
import com.dfire.testcase.function.bean.BaseParamBean;
import com.dfire.testcase.function.util.base.ShopNoPrePayUtil;
import com.dfire.wechat.util.CartIncludeSuitForm;
import com.dfire.wechat.util.HttpRequestEx;
import com.dfire.wechat.util.Response;
import com.google.gson.Gson;

public class ShopNoPrePayAPI extends WechatBaseAPI{
	private static final Logger logger = Logger.getLogger(ShopNoPrePayAPI.class);
	private static Gson gson = new Gson();
	
	/**
	 * 店码非预付款 “点击进入”
	 * @param httpRequest
	 * @param entityId
	 * @param seatCode
	 * @param repeat
	 * @param recommend
	 * @param orderId
	 * @return
	 */
	public static boolean clickToEnter(HttpRequestEx httpRequest, BaseParamBean baseParam, Map<String,String> parMap){
		Boolean flag = false;
		try{
			logger.info("业务流程：点击进入");	
			// prepare new cart
			Response response1 = ShopNoPrePayUtil.createCart(httpRequest, baseParam);			
			if (!isSuccess(response1)) {
				logger.error("创建购物车失败！");
				return flag;
			}
			
			//获取菜单
			Response response2 = ShopNoPrePayUtil.getMenus(httpRequest, baseParam,parMap);
			if (!isSuccess(response2)) {
				logger.error("获取菜单失败！");
				return flag;
			}
			//将返回参数存入map 供后面接口使用
			logger.info("存储参数:listMenu_data="+gson.toJson( JSONPath.read(response2.getResponseStr(), "$.data")));
			commonMap.put("listMenu_data", JSONPath.read(response2.getResponseStr(), "$.data"));
			
			//获取用户购物车
			Response response3 = ShopNoPrePayUtil.getUserCart(httpRequest, baseParam,parMap);
			if (!isSuccess(response3)) {
				logger.error("获取用户购物车失败!");
				return flag;
			}
			// show privilege title
			Response response4 = ShopNoPrePayUtil.showPrivilegeTitle(httpRequest, baseParam);
			if (!isSuccess(response4)) {
				logger.error("优惠载体文字标题失败！");
				return flag;
			}
			flag = true;
			return flag;
		}catch(Exception e){
			logger.info(e.toString());
		}
		return flag;
	}
	
	
	public static Boolean addDishToCarts(HttpRequestEx httpRequest,BaseParamBean baseParam,CartIncludeSuitForm cartIncludeSuitForm) {
		logger.info("加入购物车！");
		Boolean flag = false;
		// add dish to the carts
		Response response = ShopNoPrePayUtil.modifyCart(httpRequest, baseParam, gson.toJson(cartIncludeSuitForm));
		if (!isSuccess(response)) {
			return flag;
		}
		flag = true;
		return flag;
	}

	/**
	 * 
	 * @param httpRequest
	 * @param entityId
	 * @param seatCode
	 * @param orderId
	 * @param menuIdList
	 * @param intoCartMap
	 * @return
	 */
	public static Boolean intoCart(HttpRequestEx httpRequest,BaseParamBean baseParam, Map<String, String> intoCartMap) {
		Boolean flag = false;
		//创建购物车
		Response response1 = ShopNoPrePayUtil.createCart(httpRequest,baseParam);
		if (!isSuccess(response1)) {
			logger.error("创建购物车失败！");
			return flag;
		}
		
		Response response2 = ShopNoPrePayUtil.getUserCart(httpRequest, baseParam, intoCartMap);
		if (!isSuccess(response2)) {
			logger.error("获取用户购物车失败！");
			return flag;
		}
		//购物车页面展示可用优惠信息
		Response response3 = ShopNoPrePayUtil.listCartsPrivilege(httpRequest, baseParam, intoCartMap);
		if (!isSuccess(response3)) {
			logger.error("展示购物车可用优惠信息失败！");
			return flag;
		}		
		
		//获取推荐菜
		Response response4 = ShopNoPrePayUtil.getRecommendMenus(httpRequest, baseParam, intoCartMap);
		if (!isSuccess(response4)) {
			logger.error("获取推荐菜失败");
			return flag;
		}
		flag = true;
		return flag;
	}


	/**
	 * 进入店铺主页
	 * @param httpRequest
	 * @param entityId
	 * @param seatCode
	 * @param repeat
	 * @param recommend
	 * @param orderId
	 * @return
	 */
//	public static Boolean intoHomePage(HttpRequestEx httpRequest,
//			String entityId, String seatCode, String repeat, String recommend,
//			String orderId) {
//		Boolean flag = false;
//		//进入店铺home
//		Response response = ShopNoPrePayUtil.getCartCount(httpRequest, entityId, seatCode, orderId);
//		if (isSuccess(response)) {
//			return true;
//		}
//		return flag;
//	}


	
}
