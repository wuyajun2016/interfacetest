package com.dfire.testcase.function.util.base;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.dfire.testcase.function.bean.BaseParamBean;
import com.dfire.wechat.util.CommonConstants;
import com.dfire.wechat.util.HttpRequestEx;
import com.dfire.wechat.util.PathForPost;
import com.dfire.wechat.util.Response;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ShopNoPrePayUtil extends WechatBaseUtil{
	
	private static final Logger logger = Logger.getLogger(ShopNoPrePayUtil.class);
	private static final String[] xtoken = {
			"c4c576cdfbc65a5c1c1355414dc3e260",
			"4491c3560597a8c89123929219856a39",
			"1b60096f549717236f500663093062cf" };
	private static Gson gson = new Gson();

	
/////////////////////////////////  scan code ////////////////////////////////////////
	// 1、店铺初始化    getInitDataForShop    在父类中已存在
	
	// 2、 店铺分享URL的信息（包括图片、文案）接口   shareForShop  在父类中已存在
	
////////////////////////////////  click to enter  ///////////////////////////////////
	// 3、创建购物车  createCart  在父类中已存在
	

	/**
	 * 4、获取用户购物车的数据接口（含菜单列表）
	 * recommend, 以及  repeat 是 String 类型的 Boolean
	 * @param httpRequest
	 * @param entityId
	 * @param seatCode
	 * @param orderId
	 * @return
	 */
	public static Response getMenus(HttpRequestEx httpRequest, BaseParamBean baseParamBean, Map<String, String> otherParameter) {
		
		Response response = null;
		
		try {

			logger.info("获取用户购物车的数据接口（含菜单列表）");
			Map<String, String> query = new HashMap<String, String>();
			
			query.put("xtoken", baseParamBean.getXtoken());
			query.put("entity_id", baseParamBean.getEntityId());
			
			query.put("recommend", otherParameter.get("recommend"));
			query.put("repeat", otherParameter.get("repeat"));

			response = httpRequest.get(PathForPost.getPathForGetMenus(), query);
			
			return response;
			
		} catch (Exception e) {
			logger.info(e.toString());
		}
		
		return response;
	}
	
	
	/**
	 * 5、获取用户购物车的数据接口（不含菜单列表）, 获取购物车内菜品数据
	 * 需要参数：xtoken, entityId, queueId
	 * @param httpRequest
	 * @param entityId
	 * @param queueId
	 * @return
	 */
	public static Response getUserCart(HttpRequestEx httpRequest, BaseParamBean baseParamBean, Map<String, String> otherParameter) {
		
		Response response = null;
		
		try {

			logger.info("获取用户购物车的数据接口（不含菜单列表）, 获取购物车内菜品数据");
			Map<String, String> query = new HashMap<String, String>();
			
			query.put("xtoken", baseParamBean.getXtoken());
			query.put("entity_id", baseParamBean.getEntityId());
			query.put("queue_id", otherParameter.get("queueId"));

			response = httpRequest.get(PathForPost.getPathForGetUserCart(), query);
			return response;
			
		} catch (Exception e) {
			logger.info(e.toString());
		}
		
		return response;
	}

	
	/**
	 * 6、优惠载体文字显示栏标题
	 * 需要参数：xtoken, entityId
	 * code 校验建议 规定为 1，目前校验是 0
	 * @param httpRequest
	 * @param entityId
	 * @param queueId
	 * @return
	 */
	public static Response getPrivilegeTitle(HttpRequestEx httpRequest, BaseParamBean baseParamBean) {
		
		Response response = null;
		
		try {

			logger.info("优惠载体文字显示栏标题");
			Map<String, String> query = new HashMap<String, String>();
			
			query.put("xtoken", baseParamBean.getXtoken());
			query.put("entity_id", baseParamBean.getEntityId());

			response = httpRequest.get(PathForPost.getPathForShowTitle(), query);						
			
			return response;
			
		} catch (Exception e) {
			logger.info(e.toString());
		}
		
		return response;
	}
	
	
	/**
	 * 进入点菜<br/>
	 * 需要参数：entityId, seatCode, orderId, recommend, repeat, queueId<br/>
	 * 该方法可与预付款扫店码共用<br/>
	 * @param httpRequest
	 * @param baseParamBean
	 * @param otherParameter
	 * @return
	 */
	public static Boolean clickToEnter(HttpRequestEx httpRequest, BaseParamBean baseParamBean, Map<String, String> otherParameter){
		
		boolean flag = false;
		
		try{
			
			logger.info("进入点菜");
			
			// prepare new cart
			WechatBaseUtil.createCart(httpRequest, baseParamBean);
			
			// get menus from user cart 
			ShopNoPrePayUtil.getMenus(httpRequest, baseParamBean, otherParameter);
			
			// get user cart
			ShopNoPrePayUtil.getUserCart(httpRequest, baseParamBean, otherParameter);
			
			// get privilege title
			ShopNoPrePayUtil.getPrivilegeTitle(httpRequest, baseParamBean);
			
			flag = true;
			return flag;
			
		}catch(Exception e){
			logger.info(e.toString());
		}
		
		return flag;
		
	}
	
//////////////////////////////////////////////////  for carts  //////////////////////////////////////////////////////	
	
	// 7、 获取用户购物车的数据接口（不含菜单列表）    getUserCart   已经在上文定义
	
	// 8、获取菜单详情包括规格做法      getMenuSpec  在父类中已经存在
	
	
	/**
	 *  9、修改购物车菜单的接口, 即加菜到购物车
	 *  需要参数：xtoken, entityId
	 * @param httpRequest
	 * @param entityId
	 * @param cartsSuitFormJson
	 * @return
	 */
	public static Response modifyCart(HttpRequestEx httpRequest, BaseParamBean baseParamBean, String cartsSuitFormJson) {
		
		Response response = null;
		
		try {

			logger.info("修改购物车菜单的接口, 即加菜到购物车");
			Map<String, String> query = new HashMap<String, String>();
			
			query.put("xtoken", baseParamBean.getXtoken());
			query.put("entity_id", baseParamBean.getEntityId());

			response = httpRequest.post(PathForPost.getPathForPreCart(), query, cartsSuitFormJson);
			
			return response;
			
		} catch (Exception e) {
			logger.info(e.toString());
		}
		
		return response;
	}
	
	
	// 10、 获取菜单详情包括规格做法      getMenuSpec  在父类中已经存在
	
	// 11、修改购物车菜单的接口         modifyCart  已经在上文定义
	
	// 12、获取用户购物车的数据接口（不含菜单列表）    getUserCart   已经在上文定义
	
	// 13、创建购物车    createCart   在父类中已经存在
	
	// 14、展示购物车页面可用优惠信息    listCartsPrivilege   在父类中已经定义
	
	// 15、获取推荐菜列表     getRecommendMenus   在父类中已经定义
	
	
	/**
	 * 非预付款扫店码加菜, 模块方法
	 * 需要参数：entityId, menuId, queueId
	 * @param httpRequest
	 * @param entityId
	 * @param menuId
	 * @param queueId
	 * @param cartsSuitFormJson
	 * @return
	 */
	public static Boolean cartsFromShop(HttpRequestEx httpRequest,  BaseParamBean baseParamBean, Map<String, String> otherParameter, String cartsSuitFormJson){
		
		boolean flag = false;
		try{
			logger.info("非预付款扫店码加菜");
			
			// get user cart
			ShopNoPrePayUtil.getUserCart(httpRequest, baseParamBean, otherParameter);
				
			// get menu spec
			WechatBaseUtil.getMenuSpec(httpRequest, baseParamBean, otherParameter);
			
			// modify cart
			modifyCart(httpRequest, baseParamBean, cartsSuitFormJson);
			
			// get user cart 
			getUserCart(httpRequest, baseParamBean, otherParameter);
						
//			// prepare new cart
//			WechatBaseUtil.createCart(httpRequest, entityId, seatCode, orderId);			
			
			flag = true;
			return flag;
		}catch(Exception e){
			logger.info(e.toString());
		}
		return flag;
		
	}

/////////////////////////////////////   scan seat code  ////////////////////////////////////////
	
	// 16、店铺初始化     getInitDataForShop  在父类中已经定义
	
	// 17、店铺分享URL的信息（包括图片、文案）接口     shareForShop  在父类中已经定义
	

	/**
	 * 18、获取购物车数量 
	 * 需要参数：xtoken, entityId, seatCode, orderId
	 * @param httpRequest
	 * @param entityId
	 * @param seatCode
	 * @param orderId
	 * @return
	 */
	public static Response getCartCount(HttpRequestEx httpRequest, BaseParamBean baseParamBean) {
		
		Response response = null;
		
		try {

			logger.info("获取购物车数量 ");
			Map<String, String> query = new HashMap<String, String>();
			
			query.put("xtoken", baseParamBean.getXtoken());
			query.put("entity_id", baseParamBean.getEntityId());
			query.put("seat_code", baseParamBean.getSeatCode());
			query.put("order_id", baseParamBean.getOrderId());

			response = httpRequest.get(PathForPost.getPathForGetCartCount(), query);
			
			return response;
			
		} catch (Exception e) {
			logger.info(e.toString());
		}
		
		return response;
	}
	
////////////////////////////////   for pay   /////////////////////////////////////////////////
	
	// 19、 获取虚拟购物车数据及用餐人数     listCartData   已在父类中定义
	
	// 20、展示购物车页面可用优惠信息      listCartsPrivilege   已在父类中定义
	
	// 21、 获取推荐菜列表     getRecommendMenus  已在父类中定义
	
	// 22、提交订单  submitOrder   已在父类中定义
	
	// 23、获取订单列表     getOrder   已在父类中定义
	
	// 24、检查订单变化     checkOrderChange   已在父类中定义
	
	// 25、查询订单是否允许评价     createWaiterComments  已在父类中定义
	
	// 26、获取点赞活动开关     getActivitySwitch   已在父类中定义 
	
	// 27、获取优惠方案及支付订单信息    getTradeBill  已在父类中定义
	
	
	
	
/////////////////////////////////////////    模块封装方法       ///////////////////////////////////////////
	
	/**
	 * 扫店码, 需要验证店铺数据<br/>
	 * 需要参数：entityId, seatCode, signKey<br/>
	 * 该方法可与预付款扫店码共用
	 */
	public static Response scanCode(HttpRequestEx httpRequest, BaseParamBean baseParamBean, Map<String, String> otherParameter){
		
		Response response = null;
		
		try{						
						
			// 二维码扫码入口
			Response responseTemp = oauthQRCodeForShop(httpRequest, baseParamBean, otherParameter);
			Assert.assertEquals(responseTemp.getStatus(), 200);
			
			// 获取初始化数据
			response = getInitDataForShop(httpRequest, baseParamBean);
			Assert.assertEquals(response.getStatus(), 200);
			
			// 店铺分享URL的信息（包括图片、文案）接口
			Response responseForShare = shareForShop(httpRequest, baseParamBean);
			Assert.assertEquals(responseForShare.getStatus(), 200);
			
		}catch(Exception e){
			logger.info(e.toString());
		}
		
		return response;
	}
	
	
	/**
	 * 点击进入，进入菜单列表页
	 * Map 中 的 key 为：newCart, cartList, priTitle, menuList
	 * 原始参数 ：entityId, seatCode, repeat, recommend, queue_id(可以为空)  (orderID  :  "")
	 */
	public static Map<String, Response> clickToEnterForModule(HttpRequestEx httpRequest, BaseParamBean baseParamBean, Map<String, String> otherParameter){
		
		Map<String, Response> response = new HashMap<String, Response>();
		
		try{
			
			// prepare new cart
			Response newCart = WechatBaseUtil.createCart(httpRequest, baseParamBean);
			
			// get menus from user cart 
			Response menuList = ShopNoPrePayUtil.getMenus(httpRequest, baseParamBean, otherParameter);
			
			// get user cart
			Response cartList = ShopNoPrePayUtil.getUserCart(httpRequest, baseParamBean, otherParameter);
			
			// get privilege title
			Response priTitle = ShopNoPrePayUtil.getPrivilegeTitle(httpRequest, baseParamBean);
			
			response.put("newCart", newCart);
			response.put("menuList", menuList);
			response.put("cartList", cartList);
			response.put("priTitle", priTitle);
			
			
		}catch(Exception e){
			logger.info(e.toString());
		}
		
		return response;
	}
	
}
