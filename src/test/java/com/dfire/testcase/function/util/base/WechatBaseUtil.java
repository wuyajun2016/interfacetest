package com.dfire.testcase.function.util.base;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.testng.Assert;

import com.dfire.testcase.function.bean.BaseParamBean;
import com.dfire.testcase.function.bean.CardPayBean;
import com.dfire.testcase.function.bean.CardPayBeanV2;
import com.dfire.testcase.function.bean.PayTypeRequestForm;
import com.dfire.testcase.function.bean.TradeBillBean;
import com.dfire.wechat.util.BeanProvider;
import com.dfire.wechat.util.HttpRequestEx;
import com.dfire.wechat.util.MD5Utils;
import com.dfire.wechat.util.PathForPost;
import com.dfire.wechat.util.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class WechatBaseUtil {

	private static final Logger logger = Logger.getLogger(WechatBaseUtil.class);

	public static ObjectMapper mapper = new ObjectMapper();  
    
	
	/**
	 * 获取用户 token
	 * 该 token用于验证用户的有效性，在后续的请求中都需要带上
	 * @param httpRequest
	 * @param UUID
	 * @return
	 */
	public static String getToken(HttpRequestEx httpRequest, String entityId, String unionId) {

		String xtoken = null;
		
		try {
			
			logger.info("获取用户 token");
			
			String signKey = MD5Utils.generatorKey(entityId + unionId);
			
			Map<String, String> query = new HashMap<String, String>();
			query.put("entity_id", entityId);
			query.put("unionid", unionId);
			query.put("sign", signKey);
			
			Response response = httpRequest.get(PathForPost.getPathForGetToken(), query);
			JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();	
			Assert.assertEquals(resp.get("success").getAsString(), "true");
			
			Assert.assertEquals(resp.get("model").getAsJsonObject().get("success").getAsString(), "true");
			xtoken = resp.get("model").getAsJsonObject().get("model").getAsString();
					
		} catch (Exception e) {
			
			logger.error("fail to get token ");
			logger.info(e.toString());
		}
		
		return xtoken;
	}
	
	/**
	 * 桌位码扫码
	 * 扫桌码：/s/{entityId}/{seatCode}/{signKey}  
	 */
	public static Response oauthQRCodeForSeat(HttpRequestEx httpRequest, BaseParamBean baseParamBean, Map<String, String> otherParameter){
		
		Response response = null;
		try{
			List<String> path = new ArrayList<String>();
			path.add("s");
			path.add(baseParamBean.getEntityId());
			path.add(baseParamBean.getSeatCode());
			path.add(otherParameter.get("signKey"));
			
			response = httpRequest.get(path, null); 
		}catch(Exception e){
			logger.info(e.toString());
		}
		
		return response;
	}
	
	/**
	 * 桌位码扫码
	 * 扫桌码：/s/{entityId}/{seatCode}/{signKey}  
	 */
	public static Response oauthQRCodeForSeat(HttpRequestEx httpRequest, BaseParamBean baseParamBean, 
			Map<String, String> otherParameter, Map<String, String> header){
		
		Response response = null;
		try{
			List<String> path = new ArrayList<String>();
			path.add("s");
			path.add(baseParamBean.getEntityId());
			path.add(baseParamBean.getSeatCode());
			path.add(otherParameter.get("signKey"));
			
			response = httpRequest.get(path, null, header); 
		}catch(Exception e){
			logger.info(e.toString());
		}
		
		return response;
	}
	/**
	 * 店码扫码
	 * 扫店码：/s/{entityId}/{signKey}  
	 */
	public static Response oauthQRCodeForShop(HttpRequestEx httpRequest, BaseParamBean baseParamBean, Map<String, String> otherParameter){
		
		Response response = null;
		try{
			List<String> path = new ArrayList<String>();
			path.add("s");
			path.add(baseParamBean.getEntityId());
			path.add(otherParameter.get("signKey"));
			
			response = httpRequest.get(path, null); 
		}catch(Exception e){
			logger.info(e.toString());
		}
		
		return response;
	}

	/**
	 * 店铺初始化
	 * 对应 URL: GET /shop/v1/get_state
	 */
	public static Response getInitDataForShop(HttpRequestEx httpRequest, BaseParamBean baseParamBean) {

		Response response = null;
		try {
			logger.info("店铺初始化");
			Map<String, String> query = new HashMap<String, String>();
			query.put("xtoken", baseParamBean.getXtoken());
			query.put("entity_id", baseParamBean.getEntityId());
			query.put("seat_code", baseParamBean.getSeatCode());
			
			response = httpRequest.get(PathForPost.getPathForLoadState(), query);
			
			return response;
		} catch (Exception e) {
			logger.info(e.toString());
		}
		return response;
	}
	
	
	/**
	 * 首页会员等级信息详情
	 * 对应 URL: GET /shop_member/v1/get_person_info
	 */
	public static Response getPersonInfo(HttpRequestEx httpRequest, BaseParamBean baseParamBean) {

		Response response = null;
		try {
			logger.info("首页会员等级信息详情");
			Map<String, String> query = new HashMap<String, String>();
			query.put("xtoken", baseParamBean.getXtoken());
			query.put("entity_id", baseParamBean.getEntityId());
			
			response = httpRequest.get(PathForPost.getPathForGetPersonInfo(), query);
			
			return response;
		} catch (Exception e) {
			logger.info(e.toString());
		}
		return response;
	}
	
	
	
	/**
	 * 查找用户个人信息
	 * 对应 URL: GET /users/v1/get_user_info
	 */
	public static Response getUserInfo(HttpRequestEx httpRequest, BaseParamBean baseParamBean) {

		Response response = null;
		try {
			logger.info("查找用户个人信息");
			Map<String, String> query = new HashMap<String, String>();
			query.put("xtoken", baseParamBean.getXtoken());
			
			response = httpRequest.get(PathForPost.getPathForGetUserInfo(), query);
			
			return response;
		} catch (Exception e) {
			logger.info(e.toString());
		}
		return response;
	}
	
	
	/**
	 * 店铺分享URL的信息（包括图片、文案）接口
	 * 对应 URL：GET /share/v1/info
	 */
	public static Response shareForShop(HttpRequestEx httpRequest, BaseParamBean baseParamBean) {

		Response response = null;
		try {
			logger.info("店铺分享URL的信息（包括图片、文案）接口");
			
			// 请求路径
			List<String> path = new ArrayList<String>();
			path.add("shop/v1");
			path.add(baseParamBean.getEntityId());
			path.add("share");
						
			Map<String, String> query = new HashMap<String, String>();
			query.put("xtoken", baseParamBean.getXtoken());
			
			response = httpRequest.get(path, query); 
			
			return response;
		} catch (Exception e) {
			logger.info(e.toString());
		}
		return response;
	}
	
	
	/**
	 * 创建购物车 create own cart , the data is in redis with keyword "order_cart"
	 * 需要参数：xtoken, entityId, seatCode, orderId
	 * 对应的 URL：/carts/v1/create
	 */
	public static Response createCart(HttpRequestEx httpRequest, BaseParamBean baseParamBean) {
		Response response = null;
		try {

			logger.info("创建购物车");
			Map<String, String> query = new HashMap<String, String>();
			query.put("xtoken", baseParamBean.getXtoken());
			query.put("entity_id", baseParamBean.getEntityId());
			query.put("seat_code", baseParamBean.getSeatCode());
			query.put("order_id", baseParamBean.getOrderId());

			int peopleCount = -1;
			String cartsCreateJson = BeanProvider.getCartsCreateJson(baseParamBean.getEntityId(),
					baseParamBean.getSeatCode(), baseParamBean.getOrderId(), peopleCount);

			response = httpRequest.post(PathForPost.getPathForCreateOwnCart(), query, cartsCreateJson);
			return response;
		} catch (Exception e) {
			logger.info(e.toString());
		}
		return response;
	}

	/**
	 * 获取虚拟购物车数据及用餐人数 get info from cart with list format, the data is in redis
	 * with keyword "order_cart"
	 * 对应的URL：/carts/v1/list
	 */
	public static Response listCartData(HttpRequestEx httpRequest, BaseParamBean baseParamBean) {
		Response response = null;
		try {
			logger.info("获取虚拟购物车数据及用餐人数");
			Map<String, String> query = new HashMap<String, String>();
			query.put("xtoken", baseParamBean.getXtoken());
			query.put("entity_id", baseParamBean.getEntityId());
			query.put("seat_code", baseParamBean.getSeatCode());
			query.put("order_id", baseParamBean.getOrderId());

			response = httpRequest.get(PathForPost.getPathForGetCartData(), query);
			
			return response;
		} catch (Exception e) {
			logger.info(e.toString());
		}
		return response;
	}

	/**
	 * 优惠载体文字显示栏标题 show privilege title
	 * 请求的 URL ：/privilege/v1/title
	 */
	public static Response showPrivilegeTitle(HttpRequestEx httpRequest, BaseParamBean baseParamBean) {
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
	 * 获取菜单列表 list menus
	 * 请求 URL：/menus/v1/list
	 */
	public static Response listMenus(HttpRequestEx httpRequest, BaseParamBean baseParamBean, Map<String, String> otherParameter) {
		Response response = null;
		try {
			logger.info("获取店铺菜单列表");
			Map<String, String> query = new HashMap<String, String>();
			query.put("xtoken", baseParamBean.getXtoken());
			query.put("entity_id", baseParamBean.getEntityId());
			query.put("repeat", otherParameter.get("repeat"));
			query.put("recommend", otherParameter.get("recommend"));
			
			response = httpRequest.get(PathForPost.getPathForListMenus(), query);
			
			return response;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	
	
	/**
	 * 获取弹窗消息
	 * 请求 URL：/privilege/v1/popup_content
	 */
	public static Response popupMessage(HttpRequestEx httpRequest, BaseParamBean baseParamBean) {
		Response response = null;
		try {
			logger.info("获取弹窗消息");
			Map<String, String> query = new HashMap<String, String>();
			query.put("xtoken", baseParamBean.getXtoken());
			query.put("entity_id", baseParamBean.getEntityId());
			
			response = httpRequest.get(PathForPost.getPathForGetPopupMessage(), query);
			
			return response;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	

	
	/**
	 * 购物车页面展示可用优惠信息
	 * 请求的 URL ：/privilege/v1/cart/list
	 */
	public static Response cartPrivilege(HttpRequestEx httpRequest, BaseParamBean baseParamBean, Map<String, String> otherParameter) {
		Response response = null;
		try {
			logger.info("购物车页面展示可用优惠信息");
			Map<String, String> query = new HashMap<String, String>();
			query.put("xtoken", baseParamBean.getXtoken());
			query.put("entity_id", baseParamBean.getEntityId());
			query.put("menu_id_list", otherParameter.get("menuIdList"));

			response = httpRequest.get(PathForPost.getPathForShowTitle(), query);
			
			return response;
		} catch (Exception e) {
			logger.info(e.toString());
		}
		return response;
	}
	
	
	/**
	 * 添加必选商品 add required item
	 * 请求 URL ：
	 */
	public static Response addRequiredItem(HttpRequestEx httpRequest, BaseParamBean baseParamBean, Map<String, String> otherParameter) {
		Response response = null;
		try {
			logger.info("添加必选商品");
			Map<String, String> query = new HashMap<String, String>();
			query.put("xtoken", baseParamBean.getXtoken());
			query.put("entity_id", baseParamBean.getEntityId());
			query.put("seat_code", baseParamBean.getSeatCode());

			query.put("order_id", baseParamBean.getOrderId());
			query.put("people", otherParameter.get("people"));

			response = httpRequest.post(PathForPost.getPathForForceMenu(), query);
			
			return response;
		} catch (Exception e) {
			logger.info(e.toString());
		}
		return response;
	}

	/**
	 * 展示购物车页面展示可用优惠信息, 原先传入参数  List<String> menuIdList 
	 * menu_id_list 参数暂时置为空
	 */
	public static Response listCartsPrivilege(HttpRequestEx httpRequest, BaseParamBean baseParamBean, Map<String, String> otherParameter){
		Response response = null;
		try{
			logger.info("展示购物车页面可用优惠信息");
	        
			Map<String, String> query = new HashMap<String, String>();
			query.put("xtoken",baseParamBean.getXtoken());
			query.put("entity_id", baseParamBean.getEntityId());
			query.put("menu_id_list", "");	
			
			response = httpRequest.get(PathForPost.getPathForListCartsPrivilege(), query); 
			
			return response;
		}catch(Exception e){
			logger.info(e.toString());
		}
		return response;
	}
	
	
	/**
	 * 获取推荐菜 get recommend menus fron the cart
	 * 
	 * @param httpRequest
	 * @param entityId
	 * @param seatCode
	 * @param page
	 * @param pageSize
	 * @param orderId
	 * @param isPreCart
	 * @return
	 */
	public static Response getRecommendMenus(HttpRequestEx httpRequest, BaseParamBean baseParamBean, Map<String, String> otherParameter) {
		Response response = null;
		try {
			
			logger.info("获取推荐菜列表");
			Map<String, String> query = new HashMap<String, String>();
			
			query.put("xtoken", baseParamBean.getXtoken());
			query.put("entity_id", baseParamBean.getEntityId());
			query.put("seat_code", baseParamBean.getSeatCode());
			query.put("order_id", baseParamBean.getOrderId());
			
			query.put("page", otherParameter.get("page"));
			query.put("page_size", otherParameter.get("pageSize"));
			query.put("is_pre_cart", otherParameter.get("isPreCart")); // 是否是预点菜

			response = httpRequest.get(PathForPost.getPathForRecommendMenu(), query);
			
			return response;
		} catch (Exception e) {
			logger.info(e.toString());
		}
		return response;
	}

	/**
	 * 获取菜单详情只包括规格做法 get menu spec
	 * 需要参数：xtoken, entityId, menuId
	 * @param httpRequest
	 * @param entityId
	 * @param menuId
	 * @return
	 */
	public static Response getMenuSpec(HttpRequestEx httpRequest, BaseParamBean baseParamBean, Map<String, String> otherParameter) {
		Response response = null;
		try {
			logger.info("获取菜单详情包括规格做法");
			List<String> path = new ArrayList<String>();
			path.add("menus/v1/get_menu_spec");
			path.add(baseParamBean.getEntityId());
			path.add(otherParameter.get("menuId") + "/");

			Map<String, String> query = new HashMap<String, String>();
			query.put("xtoken", baseParamBean.getXtoken());

			response = httpRequest.get(path, query);
						
			return response;
		} catch (Exception e) {
			logger.info(e.toString());
		}
		return response;
	}

	/**
	 * 
	 * 添加菜品到个人购物车, 其中 httpBodyJson 是 CartIncludeSuitForm 的 Json 形式 <br/>
	 * 需要参数：xtoken, entityId, seatCode, orderId(默认为空) <br/>
	 * <span style="color: red;">请求的新 URL</span> ： /carts/v1/async_modify 或者    /carts/v1/modify ，二者是同一个接口，只是为了区分加的是普通菜还是套菜</br>
	 * <span style="color: blue;">请求的旧的URL</span>： /carts/v1/modify_own
	 */
	public static Response addDishToCarts(HttpRequestEx httpRequest, BaseParamBean baseParamBean, String httpBodyJson) {
		Response response = null;
		try {
			logger.info("添加菜品到个人购物车");
			Map<String, String> query = new HashMap<String, String>();
			query.put("xtoken", baseParamBean.getXtoken());
			query.put("entity_id", baseParamBean.getEntityId());
			query.put("seat_code", baseParamBean.getSeatCode());
			query.put("order_id", baseParamBean.getOrderId());

			response = httpRequest.post(PathForPost.getPathForModifyCart(), query, httpBodyJson);
			
			return response;
		} catch (Exception e) {
			logger.info(e.toString());
		}
		return response;
	}

	// ///////////////////////////////////////////// for order dishes
	// //////////////////////////////////////////////////////////////

	/**
	 * modify people and memo <br/>
	 * 修改人数和备注信息  <br/>
	 * 需要参数：xtoken, entityId, seatCode, orderId, memo, people, old_people <br/>
	 * 请求 URL : /carts/v1/modify_people_memo
	 */
	public static Response modifyPeople(HttpRequestEx httpRequest, BaseParamBean baseParamBean, Map<String, String> otherParameter) {
		
		Response response = null;
		
		try {
			
			logger.info("修改人数和备注信息");
			Map<String, String> query = new HashMap<String, String>();
			
			query.put("xtoken", baseParamBean.getXtoken());
			query.put("entity_id", baseParamBean.getEntityId());
			query.put("seat_code", baseParamBean.getSeatCode());
			query.put("order_id", baseParamBean.getOrderId());
			
			query.put("memo", otherParameter.get("memo"));
			query.put("people", otherParameter.get("people"));
			query.put("old_people", otherParameter.get("oldPeople"));
			query.put("memo_labels", otherParameter.get("memoLabels"));

			response = httpRequest.post(PathForPost.getPathForModifyInfo(), query);
			
			return response;
		} catch (Exception e) {
			logger.info(e.toString());
		}
		return response;
	}
	
	
	/**
	 * 18、获取购物车数量 
	 * 需要参数：xtoken, entityId, seatCode, orderId
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
	
	

	/**
	 * 删除一起点购物车的所有菜, 该接口针对扫桌码
	 * 需要参数：xtoken, entityId, seatCode, orderId
	 */
	public static Response clearCart(HttpRequestEx httpRequest, BaseParamBean baseParamBean) {
		Response response = null;
		try {

			logger.info("删除一起点购物车的所有菜, 即清空购物车, 该接口针对扫桌码 ");
			Map<String, String> query = new HashMap<String, String>();
			query.put("xtoken", baseParamBean.getXtoken());
			
			// 构造表单形式的 body 内容
			StringBuilder bodyBuilder = new StringBuilder();
			bodyBuilder.append("entity_id=").append(baseParamBean.getEntityId()).append("&seat_code=").
			append(baseParamBean.getSeatCode()).append("&order_id=").append(baseParamBean.getOrderId());

			response = httpRequest.postWithFormType(PathForPost.getPathForClearCart(), query, bodyBuilder.toString());
			
			return response;
		} catch (Exception e) {
			logger.info(e.toString());
		}
		return response;
	}
	
	
	/**
	 * 删除一起点购物车的所有菜, 该接口针对扫店码
	 * 需要参数：xtoken, entityId, seatCode, orderId
	 * @param httpRequest
	 * @param entityId
	 * @param seatCode
	 * @param orderId
	 * @return
	 */
	public static Boolean clearCartForShop(HttpRequestEx httpRequest, BaseParamBean baseParamBean, Map<String, String> otherParameter) {
		
		Boolean result = false;
		
		try {

			logger.info("删除一起点购物车的所有菜, 即清空购物车, 该接口针对扫店码, 目前通过重新扫码清空购物车数据 ");
			
			
			// 下述两个动作会清空扫店码时添加的菜品
			// scan code
			ShopNoPrePayUtil.scanCode(httpRequest, baseParamBean, otherParameter);
			
			// click to enter  (pre conditions)
			ShopNoPrePayUtil.clickToEnter(httpRequest, baseParamBean, otherParameter);
			
			result = true;						
			
		} catch (Exception e) {
			logger.info(e.toString());
		}
		
		return result;
	}
	
	
	/**
	 * 在购物车详情页,修改整桌的购物车(主要是菜品的数目) 
	 * orderId 可为空，httpBodyJson 是  CartIncludeSuitForm  的 Json 形式
	 * 需要的参数：xtoken, entityId, seatCode, orderId
	 */
	public static Response modifyDishNumber(HttpRequestEx httpRequest, BaseParamBean baseParamBean, String httpBodyJson) {
		Response response = null;
		try {

			logger.info("在购物车详情页,修改整桌的购物车(主要是菜品的数目) ");
			Map<String, String> query = new HashMap<String, String>();
			query.put("xtoken", baseParamBean.getXtoken());
			query.put("entity_id", baseParamBean.getEntityId());
			query.put("seat_code", baseParamBean.getSeatCode());			
			query.put("order_id", baseParamBean.getOrderId());

			response = httpRequest.post(PathForPost.getPathForModifyCart(), query, httpBodyJson);
			
			return response;
		} catch (Exception e) {
			logger.info(e.toString());
		}
		return response;
	}
	
	
	/**
	 * 下单前订单提醒(只对堂食-待迁移)
	 * orderId 可为空<br/>
	 * 需要的参数：xtoken, entityId, seatCode, orderId, people
	 */
	public static Response orderRemind(HttpRequestEx httpRequest, BaseParamBean baseParamBean, Map<String, String> otherParameter) {
		Response response = null;
		try {

			logger.info("下单前订单提醒(只对堂食-待迁移) ");
			Map<String, String> query = new HashMap<String, String>();
			query.put("xtoken", baseParamBean.getXtoken());
			query.put("entity_id", baseParamBean.getEntityId());
			
			query.put("seat_code", baseParamBean.getSeatCode());			
			query.put("order_id", baseParamBean.getOrderId());
			query.put("people", otherParameter.get("people"));

			response = httpRequest.post(PathForPost.getPathForModifyCart(), query);
			
			return response;
		} catch (Exception e) {
			logger.info(e.toString());
		}
		return response;
	}
	
	
	/**
	 * 提交订单
	 * orderId 可为空，<br/>
	 * 需要参数：xtoken, entityId, seatCode, orderId; <br/>peopleCount, memo, isPrePay, cartTime <br/>
	 * 对应 URL ：orders/v1/confirm
	 */
	public static Response submitOrder(HttpRequestEx httpRequest, BaseParamBean baseParamBean, Map<String, String> otherParameter){
		Response response = null;
		try{
			logger.info("提交订单");
			Map<String, String> query = new HashMap<String, String>();
			
			query.put("xtoken", baseParamBean.getXtoken());
			
			query.put("entity_id", baseParamBean.getEntityId());			
			query.put("seat_code", baseParamBean.getSeatCode());			
			query.put("order_id", baseParamBean.getOrderId());
			
			query.put("memo", otherParameter.get("memo"));
			query.put("people_count", otherParameter.get("people"));
			query.put("is_prepay", otherParameter.get("isPrePay"));
			query.put("cart_time", otherParameter.get("cartTime"));
			
//			StringBuilder bodyBuilder = new StringBuilder();
//			bodyBuilder.append("entityId=").append(baseParamBean.getEntityId()).append("&seat_code=").append(baseParamBean.getSeatCode()).
//			append("&order_id=").append(baseParamBean.getOrderId()).append("&people_count=").append(otherParameter.get("peopleCount")).
//			append("&memo=").append(otherParameter.get("memo")).append("&is_prepay=").append(otherParameter.get("isPrePay")).
//			append("&cart_time=").append(otherParameter.get("cartTime"));
//			
//			response = httpRequest.post(PathForPost.getPathForConfirmOrder(), query, null, bodyBuilder.toString()); 
			
			response = httpRequest.post(PathForPost.getPathForConfirmOrder(), query); 
			
			return response;
		}catch(Exception e){
			logger.info(e.toString());
		}
		return response;
	}
	

	/**
	 * 下单前检查订单
	 * orderId 可为空，httpBodyJson 是  List< CartIncludeSuitForm >  的 Json 形式<br/>
	 * 需要参数：xtoken, entityId, seatCode, orderId(默认为空)<br/>
	 * 对应的 URL ：orders/v1/confirm_check<br/>
	 * 如果要检查订单是否发生了更新,请使用接口 {@link #checkOrderChange(HttpRequestEx, BaseParamBean, String)}
	 */
	@Deprecated
	public static Response checkOrder(HttpRequestEx httpRequest, BaseParamBean baseParamBean, String httpBodyJson){
		
		Response response = null;
		
		try{
			logger.info("下单前检查订单");
			Map<String, String> query = new HashMap<String, String>();
			
			query.put("xtoken", baseParamBean.getXtoken());
			query.put("entity_id", baseParamBean.getEntityId());
			query.put("seat_code", baseParamBean.getSeatCode());	
			query.put("order_id", "");
			
			response = httpRequest.post(PathForPost.getPathForCheckOrder(), query, httpBodyJson); 
			return response;
			
		}catch(Exception e){
			logger.info(e.toString());
		}
		
		return response;
	}
	
	
	
	/**
	 * 获取订单列表<br/>
	 * 需要参数：xtoken, entityId, seatCode, orderId<br/>
	 * orderId 可为空<br/>
	 * 请求 URL：orders/v1/get_order<br/>
	 */
	public static Response getOrder(HttpRequestEx httpRequest, BaseParamBean baseParamBean){
		
		Response response = null;
		
		try{
			
			logger.info("获取订单列表");
			Map<String, String> query = new HashMap<String, String>();
			
			query.put("xtoken", baseParamBean.getXtoken());
			query.put("entity_id", baseParamBean.getEntityId());
			query.put("seat_code", baseParamBean.getSeatCode());	
			query.put("order_id", baseParamBean.getOrderId());
			
			response = httpRequest.get(PathForPost.getPathForGetOrder(), query); 
			
			return response;
		}catch(Exception e){
			logger.info(e.toString());
		}
		
		return response;
	}
	

	
	/**
	 * 检查订单变化 <br/>
	 * orderId 可为空, orderIdsFormJson 是封装了  orderIdsForm 的 Json 形式 <br/>
	 * 需要参数：xtoken, entityId, seatCode, orderId<br/>
	 * 请求  URL ：orders/v1/check_order_change  
	 */
	public static Response checkOrderChange(HttpRequestEx httpRequest, BaseParamBean baseParamBean, String orderIdsFormJson){

		Response response = null;
		
		try{
			
			logger.info("检查订单变化  (可能多人提交订单) ");
			Map<String, String> query = new HashMap<String, String>();
			
			query.put("xtoken", baseParamBean.getXtoken());
			query.put("entity_id", baseParamBean.getEntityId());
			query.put("seat_code", baseParamBean.getSeatCode());	
			query.put("order_id", baseParamBean.getOrderId());
			
			response = httpRequest.post(PathForPost.getPathForCheckOrderChange(), query, orderIdsFormJson); 
			
			return response;
			
		}catch(Exception e){
			logger.info(e.toString());
		}
		
		return response;
	}
	

	// ///////////////////////////////////////////// for pay    //////////////////////////////////////////////////////////////	

	/**
	 * 查询订单是否允许评价
	 * 需要参数：xtoken, entityId, order_ids[]
	 * 请求 URL 为: evaluation/v1/play/comments
	 */
	public static Response createWaiterComments(HttpRequestEx httpRequest, BaseParamBean baseParamBean, Map<String, String> otherParameter){

		Response response = null;
		
		try{
			
			logger.info("查询订单是否允许评价");
			
//			String order_idStr = "";			
//			for(String orderId : order_ids)
//				order_idStr = order_idStr + orderId + ",";
			
			Map<String, String> query = new HashMap<String, String>();
			query.put("xtoken", baseParamBean.getXtoken());
			query.put("entity_id", baseParamBean.getEntityId());
			query.put("order_ids[]", otherParameter.get("orderIds"));	
			
			response = httpRequest.get(PathForPost.getPathForPlayComments(), query); 
			
			return response;
			
		}catch(Exception e){
			logger.info(e.toString());
		}
		
		return response;
	}
	
	
	/**
	 * 获取点赞活动开关 <br/>
	 * 不需要填写 orderId <br/>
	 * 需要参数：xtoken, entityId, seatCode, orderId <br/>
	 * 请求 URL 为: activity/v1/activity_switch
	 */
	public static Response getActivitySwitch(HttpRequestEx httpRequest, BaseParamBean baseParamBean){

		Response response = null;
		
		try{
			logger.info("获取点赞活动开关");		
			
			Map<String, String> query = new HashMap<String, String>();
			query.put("xtoken", baseParamBean.getXtoken());
			query.put("entity_id", baseParamBean.getEntityId());
			
			query.put("seat_code", baseParamBean.getSeatCode());
			query.put("order_id", baseParamBean.getOrderId());	
			
			response = httpRequest.get(PathForPost.getPathForGetActivitySwitch(), query); 
			
			return response;
			
		}catch(Exception e){
			
			logger.info(e.toString());
			
		}
		
		
		return response;
		
	}
	
	
	
	/**
	 * 查询订单是否允许评价 <br/>
	 * 需要填写 orderId 列表 <br/>
	 * 请求 URL 为: /evaluation/v1/play/comments
	 */
	public static Response getOrderComments(HttpRequestEx httpRequest, BaseParamBean baseParamBean, Map<String, String> otherParameter){

		Response response = null;
		
		try{
			logger.info("获取点赞活动开关");		
			
			Map<String, String> query = new HashMap<String, String>();
			query.put("xtoken", baseParamBean.getXtoken());
			query.put("entity_id", baseParamBean.getEntityId());
						
			query.put("order_ids[]", otherParameter.get("orderList"));	
			
			response = httpRequest.get(PathForPost.getPathForGetOrderComments(), query); 
			
			return response;
			
		}catch(Exception e){
			
			logger.info(e.toString());
			
		}
		
		
		return response;
		
	}
	
	
	/**
	 * 获取优惠方案及支付订单信息<br/>
	 * 该接口存疑<br/>
	 * 请求 URL 为: pay/v1/get_trade_bill<br/>
	 * 后续接口请使用：{@link #getTradeBillV2(HttpRequestEx, BaseParamBean, Map)}
	 * @author hedan
	 */
	@Deprecated
	public static Response getTradeBill(HttpRequestEx httpRequest, BaseParamBean baseParamBean, Map<String, Object> parMap){

		Response response = null;
		
		try{
			logger.info("获取优惠方案及支付订单信息");			
			Map<String, String> query = new HashMap<String, String>();
			query.put("xtoken", baseParamBean.getXtoken());
			query.put("entity_id", baseParamBean.getEntityId());
			query.put("seat_code", baseParamBean.getSeatCode());

			Map<String,Object> httpBody = new HashMap<String, Object>();
			httpBody.put("flag", parMap.get("flag"));
			httpBody.put("selected", parMap.get("selected"));
			httpBody.put("query_bill_form", parMap.get("query_bill_form"));
			httpBody.put("cart_form", parMap.get("cart_form"));
			
			logger.info("httpBody:" + mapper.writeValueAsString(httpBody));
			response = httpRequest.post(PathForPost.getPathForGetTradeBill(), query, mapper.writeValueAsString(httpBody)); 
			return response;
			
		}catch(Exception e){
			e.printStackTrace();
			logger.info(e.toString());
		}
		
		return response;
	}
	
	
	/**
	 * 获取优惠方案及支付订单信息<br/>
	 * 未下单的订单 from_cart = 1 <br/>
	 * 请求 URL 为: pay/v2/get_trade_bill
	 */
	public static Response getTradeBillV2(HttpRequestEx httpRequest, BaseParamBean baseParamBean, Map<String, Object> parMap){

		Response response = null;
		
		try{
			logger.info("获取优惠方案及支付订单信息");			
			Map<String, String> query = new HashMap<String, String>();
			query.put("xtoken", baseParamBean.getXtoken());
			query.put("entity_id", baseParamBean.getEntityId());
			query.put("seat_code", baseParamBean.getSeatCode());

			Map<String,Object> httpBody = new HashMap<String, Object>();
			httpBody.put("flag", parMap.get("flag"));
			httpBody.put("selected", parMap.get("selected"));
			httpBody.put("query_bill_form", parMap.get("query_bill_form"));
			
			// 下述三个参数目前非必填
//			httpBody.put("cart_form", parMap.get("cart_form"));
//			httpBody.put("cart_time", parMap.get("cartTime"));
//			httpBody.put("from_cart", parMap.get("fromCart"));
			
			logger.info("httpBody:" + mapper.writeValueAsString(httpBody));
			
			
			response = httpRequest.post(PathForPost.getPathForGetTradeBillV2(), query, mapper.writeValueAsString(httpBody)); 
			return response;
			
		}catch(Exception e){
			e.printStackTrace();
			logger.info(e.toString());
		}
		
		return response;
	}
	
	/**
	 * 获取优惠方案及支付订单信息<br/>
	 * 未下单的订单 from_cart = 1 <br/>
	 * 请求 URL 为: pay/v2/get_trade_bill
	 */
	public static Response getTradeBillV2Test(HttpRequestEx httpRequest, BaseParamBean baseParamBean, String httpBodyJson){

		Response response = null;
		
		try{
			logger.info("获取优惠方案及支付订单信息");			
			Map<String, String> query = new HashMap<String, String>();
			query.put("xtoken", baseParamBean.getXtoken());
			query.put("entity_id", baseParamBean.getEntityId());
			query.put("seat_code", baseParamBean.getSeatCode());
			
			logger.info("httpBody:" + httpBodyJson);			
			
			response = httpRequest.post(PathForPost.getPathForGetTradeBillV2(), query, httpBodyJson); 
			return response;
			
		}catch(Exception e){
			e.printStackTrace();
			logger.info(e.toString());
		}
		
		return response;
	}
	
	
	
	/**
	 * 预付款下单确认, 下单检测购物车与云购物车是否一致
	 * cartSuitFormListJson 是  List&lt;CartIncludeSuitForm&gt; 的 Json 形式
	 * 需要参数：xtoken, entityId, seatCode, orderId, peopleCount, memo, cardId
	 * 请求 URL 为:prepay/v1/confirm_prepay
	 */
	public static Response confirmPrePay(HttpRequestEx httpRequest, String cartSuitFormListJson, 
			BaseParamBean baseParamBean, Map<String, String> otherParameter){
		
		Response response = null;
		
		try{
			
			logger.info("预付款下单确认, 下单检测购物车与云购物车是否一致");						
			Map<String, String> query = new HashMap<String, String>();
			
			query.put("xtoken", baseParamBean.getXtoken());
			query.put("entity_id", baseParamBean.getEntityId());
			query.put("seat_code", baseParamBean.getSeatCode());
			query.put("order_id", baseParamBean.getOrderId());
			
			query.put("people_count", otherParameter.get("peopleCount"));
			query.put("memo", otherParameter.get("memo"));
			query.put("card_id", otherParameter.get("cardId"));
			
			response = httpRequest.post(PathForPost.getPathForConfirmPrePay(), query, cartSuitFormListJson); 
			
			return response;
			
		}catch(Exception e){
			
			logger.info(e.toString());
			
		}
		
		return response;
	}
	
	
	/**
	 * 店码获取菜单列表
	 * 需要参数：xtoken, entityId, recommend, repeat
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
	 * 5、获取用户购物车的数据接口（不含菜单列表）
	 * 需要参数：xtoken, entityId, queueId
	 * @param httpRequest
	 * @param entityId
	 * @param queueId
	 * @return
	 */
	public static Response getUserCart(HttpRequestEx httpRequest, BaseParamBean baseParamBean, Map<String, String> otherParameter) {
		
		Response response = null;
		
		try {

			logger.info("获取用户购物车的数据接口（不含菜单列表）");
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
	 *  9、修改购物车菜单的接口   
	 *  需要参数：xtoken, entityId
	 * @param httpRequest
	 * @param entityId
	 * @param cartsSuitFormJson
	 * @return
	 */
	public static Response modifyCart(HttpRequestEx httpRequest, BaseParamBean baseParamBean, String cartsSuitFormJson) {
		
		Response response = null;
		
		try {

			logger.info("修改购物车菜单的接口 ");
			Map<String, String> query = new HashMap<String, String>();
			
			query.put("xtoken", baseParamBean.getXtoken());
			query.put("entity_id", baseParamBean.getEntityId());
			
			response = httpRequest.post(PathForPost.getPathForPreCart(), query, cartsSuitFormJson);
			
		} catch (Exception e) {
			logger.info(e.toString());
		}
		
		return response;
	}
	
	
	/**
	 * 获取支付类型
	 * cartSuitFormListJson 是  List&lt;CartIncludeSuitForm&gt; 的 Json 形式<br/>
	 * 需要参数：xtoken<br/>
	 * 请求 URL 为: pay/v1/get_pay_type
	 */
	public static Response getPayType(HttpRequestEx httpRequest, BaseParamBean baseParamBean, PayTypeRequestForm payTypeForm){
		
		Response response = null;
		
		try{
			logger.info("获取支付类型");			
			Map<String, String> query = new HashMap<String, String>();
			query.put("xtoken", baseParamBean.getXtoken());
			query.put("entity_id", baseParamBean.getEntityId());
			query.put("seat_code", baseParamBean.getSeatCode());

			response = httpRequest.post(PathForPost.getPathForGetPayType(), query, mapper.writeValueAsString(payTypeForm)); 
			
			return response;
			
		}catch(Exception e){
			logger.info(e.toString());
		}
		
		return response;
	}
	
	
	/**
	 * 获取支付类型
	 * cartSuitFormListJson 是  List&lt;CartIncludeSuitForm&gt; 的 Json 形式<br/>
	 * 需要参数：xtoken<br/>
	 * 该接口为正式接口, 到时候重构可以覆盖接口 {@link com.dfire.testcase.function.util.base.WechatBaseUtil#getPayType(HttpRequestEx, BaseParamBean, PayTypeRequestForm)}<br>
	 * 请求 URL 为: pay/v1/get_pay_type
	 */
	public static Response getPayTypeTest(HttpRequestEx httpRequest, BaseParamBean baseParamBean, PayTypeRequestForm payTypeForm){
		
		Response response = null;
		Gson gson = new Gson();
		
		try{
			logger.info("获取支付类型");			
			Map<String, String> query = new HashMap<String, String>();
			query.put("xtoken", baseParamBean.getXtoken());
			query.put("entity_id", baseParamBean.getEntityId());
			query.put("seat_code", baseParamBean.getSeatCode());

			response = httpRequest.post(PathForPost.getPathForGetPayType(), query, gson.toJson(payTypeForm)); 
			
			return response;
			
		}catch(Exception e){
			logger.info(e.toString());
		}
		
		return response;
	}
	
	/**
	 * 获取优惠方案及支付订单信息
	 * 该接口存疑
	 * 请求 URL 为: pay/v2/get_trade_bill
	 * @author ljw
	 */
	public static Response getTradeBillForPay(HttpRequestEx httpRequest, BaseParamBean baseParamBean, TradeBillBean tradeBillBean){

		Response response = null;
		
		try{
			logger.info("获取优惠方案及支付订单信息");			
			Map<String, String> query = new HashMap<String, String>();
			query.put("xtoken", baseParamBean.getXtoken());
			query.put("entity_id", baseParamBean.getEntityId());
			query.put("seat_code", baseParamBean.getSeatCode());

			
			logger.info("httpBody:" + tradeBillBean.toJson());
			response = httpRequest.post(PathForPost.getPathForGetTradeBillV2(), query, tradeBillBean.toJson()); 
			return response;
			
		}catch(Exception e){
			e.printStackTrace();
			logger.info(e.toString());
		}
		
		return response;
	}
	
	
	/**
	 * 会员卡支付<br/>
	 * 请求 URL 为:pay/v1/card_pay<br/>
	 * 该请求方式暂时保留, 以兼容原先代码 <br/>
	 * @param httpRequest
	 * @param baseParamBean
	 * @param cardPayBean
	 * @return
	 */
	@Deprecated
	public static Response cardPay(HttpRequestEx httpRequest, BaseParamBean baseParamBean, CardPayBean cardPayBean) {
		
		Response response = null;
		
		try {
			
			logger.info("会员卡支付");
			Map<String, String> query = new HashMap<String, String>();
			query.put("xtoken", baseParamBean.getXtoken());
			
			response = httpRequest.postWithHeader(PathForPost.getPathForCardPay(), query, cardPayBean.mapBean());
			return response;
			
		} catch (Exception e) {
			logger.info(e.toString());
		}
		
		return response;
	}
	
	
	/**
	 * 会员卡支付<br/>
	 * 请求 URL 为:pay/v1/card_pay<br/>
	 * 推荐请求方式
	 */
	public static Response cardPayV2(HttpRequestEx httpRequest, BaseParamBean baseParamBean, CardPayBeanV2 cardPayBean) {
		
		Response response = null;
		
		try {
			
			logger.info("会员卡支付");
			Map<String, String> query = new HashMap<String, String>();
			query.put("xtoken", baseParamBean.getXtoken());
			
			StringBuilder httpContent = new StringBuilder();
			httpContent.append("entity_id=").append(cardPayBean.getEntity_id()).append("&card_id=").append(cardPayBean.getCard_id()).
			append("&order_id=").append(cardPayBean.getOrder_id()).append("&waiting_order_id=").append(cardPayBean.getWaiting_order_id()).
			append("&need_fee=").append(cardPayBean.getNeed_fee()).append("&origin_fee=").append(cardPayBean.getOrigin_fee()).
			append("&snapshot_id=").append(cardPayBean.getSnapshot_id());
			
			response = httpRequest.postWithFormType(PathForPost.getPathForCardPay(), query, httpContent.toString());
			
			return response;
			
		} catch (Exception e) {
			logger.info(e.toString());
		}
		
		return response;
	}
	
	
	
	/**
	 * 查询支付账单<br/>
	 * 请求 URL 为: query_pay/bill
	 * @param httpRequest
	 * @param baseParamBean
	 * @param otherParameter
	 * @return
	 */
	public static Response queryPayBill(HttpRequestEx httpRequest, BaseParamBean baseParamBean, Map<String, String> otherParameter) {
		
		Response response = null;
		
		try {
			logger.info("发票打印开关取得");
			Map<String, String> query = new HashMap<String, String>();
			
			query.put("xtoken", baseParamBean.getXtoken());
			query.put("entity_id", baseParamBean.getEntityId());
			
			query.put("card_id", otherParameter.get("cardId"));
			query.put("out_trade_no", otherParameter.get("outTradeNo"));
			query.put("pay_type", otherParameter.get("payType"));
			query.put("snapshot_id", otherParameter.get("snapshotId"));
			
			response = httpRequest.get(PathForPost.getPathForGetPrepayBill(), query);
			return response;
			
		} catch (Exception e) {
			logger.info(e.toString());
		}
		
		return response;
	}

	
	
	/**
	 * 结账后优惠券赠送<br/>
	 * 请求 URL 为: /privilege/v1/get_give_coupon<br/>
	 * @param httpRequest
	 * @param baseParamBean
	 * @return
	 */
	public static Response getCouponOrder(HttpRequestEx httpRequest, BaseParamBean baseParamBean,  Map<String, String> otherParameter) {
		
		Response response = null;
		
		try {
			
			logger.info("结账后优惠券赠送");
			Map<String, String> query = new HashMap<String, String>();
			
			query.put("xtoken", baseParamBean.getXtoken());
			query.put("entity_id", baseParamBean.getEntityId());
			query.put("order_id", baseParamBean.getOrderId());
			query.put("snapshot_id", otherParameter.get("snapshotId"));
			
			response = httpRequest.get(PathForPost.getPathForGetGiveCoupon(), query);
			return response;
			
		} catch (Exception e) {
			logger.info(e.toString());
		}
		
		return response;

	}
	
	
	/**
	 * 查询支付账单
	 * 请求 URL 为: orders/v1/get_query_shop_tax
	 * @param httpRequest
	 * @param baseParamBean
	 * @return
	 */
	public static Response queryShopTax(HttpRequestEx httpRequest, BaseParamBean baseParamBean) {
		
		Response response = null;
		
		try {
			
			logger.info("查询支付账单");
			Map<String, String> query = new HashMap<String, String>();
			
			query.put("xtoken", baseParamBean.getXtoken());
			query.put("entity_id", baseParamBean.getEntityId());
			query.put("order_id", baseParamBean.getOrderId());
			
			response = httpRequest.get(PathForPost.getPathForGetShopTax(), query);
			return response;
			
		} catch (Exception e) {
			logger.info(e.toString());
		}
		
		return response;

	}
	
}
