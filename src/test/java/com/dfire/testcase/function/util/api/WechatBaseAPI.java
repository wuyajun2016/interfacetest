package com.dfire.testcase.function.util.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.dfire.testcase.bean.MenuForm;
import com.dfire.testcase.bean.OrderIdsForm;
import com.dfire.testcase.function.bean.BaseParamBean;
import com.dfire.testcase.function.bean.CardPayBean;
import com.dfire.testcase.function.bean.CreateSnapshotForm;
import com.dfire.testcase.function.bean.PayTypeRequestForm;
import com.dfire.testcase.function.bean.QueryTradeBillForm;
import com.dfire.testcase.function.util.base.SeatPrePayUtil;
import com.dfire.testcase.function.util.base.WechatBaseUtil;
import com.dfire.wechat.util.CartIncludeSuitForm;
import com.dfire.wechat.util.HttpRequestEx;
import com.dfire.wechat.util.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.twodfire.util.StringUtil;

public class WechatBaseAPI {
	
	private static final Logger logger = Logger.getLogger(WechatBaseAPI.class);
	public  static Gson gson = new Gson();
	
	public static Map<String, Object> commonMap = new HashMap<String, Object>();
	public static ObjectMapper mapper = new ObjectMapper();  
    

	/**
	 * 扫码
	 * @param httpRequest
	 * @param entityId
	 * @param seatCode
	 * @return 
	 */
	public static Boolean scanCode(HttpRequestEx httpRequest, BaseParamBean baseParam) {
		Boolean flag = false;
		//店铺初始化
		Response response = WechatBaseUtil.getInitDataForShop(httpRequest, baseParam);
		if (!isSuccess(response)){
			logger.error("店铺初始化失败");
			return flag;
		}
		//店铺分享URL的信息（包括图片、文案） TODO /shop/v1/${entityId}/share?xtoken=${xtoken}  
		Response response2 = WechatBaseUtil.shareForShop(httpRequest,baseParam);
		if (!isSuccess(response2)) {
			logger.error("店铺分享url信息失败");
			return flag;
		}
		flag = true;
		//将返回结果放进公共参数map，供后面接口使用
		logger.info("存储公共参数：getState_data："+JSONPath.read(response.getResponseStr(), "$.data").toString());
		commonMap.put("getState_data",JSONPath.read(response.getResponseStr(), "$.data"));
	
		return flag;
	}


	/**
	 * “点击进入”
	 * click to enter
	 * @param httpRequest
	 * @param entityId
	 * @param seatCode
	 * @param repeat
	 * @param recommend
	 * @param orderId
	 * @return
	 */
	public static boolean clickToEnter(HttpRequestEx httpRequest, BaseParamBean baseParam, Map<String, String> paramMap){
		boolean flag = false;
		try{
			logger.info("业务流程：点击进入");	
			// prepare new cart
			Response response1 = WechatBaseUtil.createCart(httpRequest,baseParam);			
			if (!isSuccess(response1)){
				logger.error("创建购物车失败！");
				return flag;
			}
		
			// get info of carts with list format
			Response response2 = WechatBaseUtil.listCartData(httpRequest, baseParam);
			if (!isSuccess(response2)){
				logger.error("购物车列表失败！");
				return flag;
			}
			// show privilege title
			Response response3 = WechatBaseUtil.showPrivilegeTitle(httpRequest, baseParam);
			if (!isSuccess(response3)){
				logger.error("优惠载体标题失败！");
				return flag;
			}
			// list menus
			Response response4 = WechatBaseUtil.listMenus(httpRequest, baseParam, paramMap);
			if (!isSuccess(response4)){
				logger.error("获取菜单失败！");
				return flag;
			}
				
			flag = true;
			//将返回结果放进公共参数map，供后面接口使用
			logger.info("存储公共参数：listMenu_data："+JSONPath.read(response4.getResponseStr(), "$.data").toString());
			commonMap.put("listMenu_data",JSONPath.read(response4.getResponseStr(), "$.data"));
			return flag;
		}catch(Exception e){
			logger.info(e.toString());
		}
		return flag;
	}
	

	
	/**
	 * 
	 *  点击“开始点菜”进入菜单页面, 
	 * 其中 httpBodyJson 是  CartIncludeSuitForm 的 Json 形式
	 * @param httpRequest
	 * @param entityId
	 * @param seatCode
	 * @param people
	 * @param memo
	 * @param orderId
	 * @param oldPeople
	 * @param page
	 * @param pageSize
	 * @param isPreCart
	 * @return
	 */
//	public static boolean startChooseDish(HttpRequestEx httpRequest, BaseParamBean baseParam, String people, String memo, String oldPeople, String page, String pageSize, String isPreCart){
//		boolean flag = false;
//		String entityId = baseParam.getEntityId();
//		String seatCode = baseParam.getSeatCode();
//		String orderId = baseParam.getOrderId();
//		try{
//			logger.info("进入点菜");
//			
//			// modify people and memo
//			Response response1 = WechatBaseUtil.modifyPeople(httpRequest, baseParam,, people, memo,orderId, oldPeople);
//			if (!isSuccess(response1)) {
//				logger.error("修改人数失败");
//				return flag;
//			}
//			// add required item
////			Response response2 = WechatBaseUtil.addRequiredItem(httpRequest, entityId, seatCode, people, orderId);
////			if (!isSuccess(response2)) {
////				logger.error("添加必选商品失败");
////				return flag;
////			}
//			Response response3 = WechatBaseUtil.listCartData(httpRequest, entityId,seatCode, orderId);
//			if (!isSuccess(response3)) {
//				logger.error("查询购物车数据失败");
//				return flag;
//			}
//			// get recommend menus
//			Response response4 = WechatBaseUtil.getRecommendMenus(httpRequest, entityId, seatCode, page, pageSize, orderId, isPreCart);
//			if (!isSuccess(response4)) {
//				logger.error("查询推荐菜失败");
//				return flag;
//			}
//
//			flag = true;
//			
//		}catch(Exception e){
//			logger.info(e.toString());
//		}
//		return flag;
//	}


	/**
	 * 选择菜
	 * @param httpRequest
	 * @param entityId
	 * @param seatCode
	 * @param menuId
	 * @param num
	 * @param kindType
	 * @return
	 */
	public static Boolean chooseDish(HttpRequestEx httpRequest, BaseParamBean baseParam, Map<String, String> parMap) {
		logger.info("点菜");
		Boolean flag = false;
		// get menu Spec 
		Response response1 = WechatBaseUtil.getMenuSpec(httpRequest, baseParam, parMap);
		if (isSuccess(response1)) {
			flag = true;
			// 将返回结果放进公共参数map，供后面接口使用
			logger.info("存储公共参数：menuspec_data："+ JSONPath.read(response1.getResponseStr(), "$.data").toString());
			commonMap.put("menuspec_data",JSONPath.read(response1.getResponseStr(), "$.data"));
		}
		
		return flag;
	}	
	
	/**
	 * 加入购物车
	 * @param httpRequest
	 * @param entityId
	 * @param seatCode
	 * @param cartIncludeSuitForm
	 * @return
	 */
	public static Boolean addDishToCarts(HttpRequestEx httpRequest, BaseParamBean baseParam, CartIncludeSuitForm cartIncludeSuitForm) {
		logger.info("加入购物车！");
		Boolean flag = false;
		// add dish to the carts
		Response response1  = WechatBaseUtil.addDishToCarts(httpRequest, baseParam, gson.toJson(cartIncludeSuitForm));
		if (isSuccess(response1)) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 进入购物车
	 * @param httpRequest
	 * @param intoCartMap 
	 * @return
	 */
	public static Boolean intoCart(HttpRequestEx httpRequest,BaseParamBean baseParam,Map<String, String> intoCartMap, List<CartIncludeSuitForm> cartList) {
		Boolean flag = false;
		//创建购物车
		Response response1 = WechatBaseUtil.createCart(httpRequest,baseParam);
		if (!isSuccess(response1)) {
			logger.error("创建购物车失败！");
			return flag;
		}
		//获取虚拟购物车数据及用餐人数
		Response response2 = WechatBaseUtil.listCartData(httpRequest,baseParam);
		if (!isSuccess(response2)) {
			logger.error("获取虚拟购物车数据及用餐人数失败！");
			return flag;
		}
		cartList.clear();
		if(isSuccess(response2)){
			extractListCartData(cartList, response2);
		}
		//购物车页面展示可用优惠信息
		Response response3 = WechatBaseUtil.listCartsPrivilege(httpRequest,baseParam, intoCartMap);
		if (!isSuccess(response3)) {
			logger.error("购物车页面展示可用优惠信息失败！");
			return flag;
		}
		//获取推荐菜
		Response response4 = WechatBaseUtil.getRecommendMenus(httpRequest, baseParam, intoCartMap);
		if (!isSuccess(response4)) {
			logger.error("获取推荐菜失败！");
			return flag;
		}	

		flag = true;
		return flag;
	}


	/**
	 * 提交订单
	 * @param httpRequest
	 * @param entityId
	 * @param seatCode
	 * @param orderId
	 * @param peopleCount
	 * @param cartList
	 * @return
	 * @throws InterruptedException 
	 */
	public static Boolean submitOrder(HttpRequestEx httpRequest,BaseParamBean baseParam,Map<String, String> parMap, List<CartIncludeSuitForm> cartList)  {
		Boolean flag = false;
		//创建购物车
		//提交订单	
		Response response1 = WechatBaseUtil.submitOrder(httpRequest, baseParam, parMap);
		if (isSuccess(response1)) {
			flag = true;
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}//休息5s等待收银审核
		return flag;
	}


	/**
//	 * 收银审核以后刷新已下单的页面
//	 * @param httpRequest
//	 * @param entityId
//	 * @param seatCode
//	 * @param orderId
//	 * @return
//	 */
//	public static Boolean refreshOrder(HttpRequestEx httpRequest,String entityId, String seatCode, String orderId) {
//		
//		Boolean flag = false;
//		//获取订单
//		Response response1 = WechatBaseUtil.getOrder(httpRequest, entityId, seatCode, orderId);
//		if(!isSuccess(response1)){
//			return flag;
//		}
//		//将返回数据放入公共参数
//		logger.info("getOrder_data:"+JSONPath.read(response1.getResponseStr(), "$.data").toString());
//		commonMap.put("getOrder_data",JSONPath.read(response1.getResponseStr(), "$.data"));
//		
//		String orderJson = SeatNoPrePayAPI.commonMap.get("getOrder_data").toString();
//		List<String> orderIds = new ArrayList<String>();
//		List<OrderIdsForm> orderIdsForms = new ArrayList<OrderIdsForm>();
//		orderId = (String) JSONPath.read(orderJson, "$.orderVos[0].orderId");
//		//从返回中提取参数
//		extractRreshParam(orderJson, orderIds, orderIdsForms);
//		//查询订单是否允许评价
//		Response response2 = WechatBaseUtil.createWaiterComments(httpRequest, entityId, orderIds);
//		if(!isSuccess(response2)){
//			return flag;
//		}
//		//获取点赞活动开关
//		Response response3 = WechatBaseUtil.getActivitySwitch(httpRequest, entityId, seatCode, orderId);
//		if(!isSuccess(response3)){
//			return flag;
//		}
//		//检查订单变化
//		Response response4 = WechatBaseUtil.checkOrderChange(httpRequest, entityId, seatCode, orderId, gson.toJson(orderIdsForms));
//		if(!isSuccess(response4)){
//			return flag;
//		}
//			
//		flag = true;
//		return flag;
//	}


	public static void extractRreshParam(String orderJson,List<String> orderIds, List<OrderIdsForm> orderIdsForms) {
		JSONArray orderArray = (JSONArray) JSONPath.read(orderJson, "$.orderVos");
		for (Object orderObj : orderArray) {
			OrderIdsForm orderIdsForm = new OrderIdsForm();
			List<MenuForm> order = new ArrayList<MenuForm>();
			
			String orderIdstr = (String) JSONPath.read(orderObj.toString(), "$.orderId");
			orderIdsForm.setFoodStatus(JSONPath.read(orderObj.toString(),"$.foodStatus").toString());
			orderIdsForm.setId((String) JSONPath.read(orderObj.toString(), "$.orderId"));
			orderIds.add(orderIdstr);
			
			JSONArray orderItemArray = (JSONArray) JSONPath.read(orderObj.toString(), "$.orderItems");
			for (Object itemobj : orderItemArray) {
 				String uid = JSONPath.read(itemobj.toString(),"$.uid").toString();
				JSONArray menuArray = (JSONArray) JSONPath.read(itemobj.toString(), "$.orderMenus");
				for (Object menuobj : menuArray) {
					MenuForm menuForm = new MenuForm();
					menuForm = JSONObject.parseObject(menuobj.toString(),MenuForm.class);
//					menuForm.setMakeId(JSONPath.read(menuobj.toString(),"$.makeId").toString());
//					menuForm.setMenuId(JSONPath.read(menuobj.toString(),"$.menuId").toString());
//					menuForm.setNum(String.valueOf(JSONPath.read(menuobj.toString(),"$.num")));
//					menuForm.setSpecId(JSONPath.read(menuobj.toString(),"$.specId").toString());
//					menuForm.setStatus(Integer.valueOf(JSONPath.read(menuobj.toString(),"$.status").toString()));
					menuForm.setUid(uid);
					order.add(menuForm);
				}
				
			}
			orderIdsForm.setOrder(order);
			orderIdsForms.add(orderIdsForm);
		}
	}



//	/**
//	 * 去支付
//	 * @param httpRequest
//	 * @param entityId
//	 * @param seatCode
//	 * @param orderId
//	 * @return
//	 */
//	public static Boolean intoBillPage(HttpRequestEx httpRequest, BaseParamBean baseParam,List<CartIncludeSuitForm> carts) {
//		Boolean flag = false;
//		//调用返回 999 
//		TradeBill tradeBill = new TradeBill();
//		tradeBill.setEntity_id(baseParam.getEntityId());
//		tradeBill.setSeat_code(baseParam.getSeatCode());
//		tradeBill.setOrder_id(baseParam.getOrderId());
//		tradeBill.setCart_forms_string(gson.toJson(carts));
//		Response response = WechatBaseUtil.getTradeBill(httpRequest, tradeBill);
//		if (isSuccess(response)) {
//			flag = true;
//		}
//		return flag;
//	}
	

	
	public static Boolean clearCart(HttpRequestEx httpRequestEx,BaseParamBean baseParam) {
		Boolean flag = false;
		
		Response response = WechatBaseUtil.clearCart(httpRequestEx, baseParam);
		if (isSuccess(response)) {
			flag = true;
		}
		return flag;
	}
	



	public static Boolean isSuccess(Response response){
		Boolean flag = false;
		if (response.getStatus() == 200) {
			JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();
			if (resp.get("code").getAsInt()==1) {
				flag = true;
			}
		}
		return flag;
	}
	
	public static void extractListCartData(List<CartIncludeSuitForm> cartList,
			Response response2) {
		JSONArray userCartArray = (JSONArray) JSONPath.read(response2.getResponseStr(), "$.data.userCarts");
		for (Object object : userCartArray) {
			String uid = String.valueOf(JSONPath.read(object.toString(),"$.customerVo.id")) ;
			JSONArray cartVoList = (JSONArray) JSONPath.read(object.toString(),"$.cartVoList") ;
			for (Object cartVo : cartVoList) {
				CartIncludeSuitForm cartIncludeSuitForm = JSONObject.parseObject(cartVo.toString(), CartIncludeSuitForm.class);
				cartIncludeSuitForm.setUid(uid);
				cartList.add(cartIncludeSuitForm);
			}
		}
	}


	public static void BeanToSimple(CartIncludeSuitForm sour,CartIncludeSuitForm target) {
		if (StringUtil.isNotEmpty(sour.getUid())) {
			target.setUid(sour.getUid());
		}
		if (StringUtil.isNotEmpty(sour.getMakeId())) {
			target.setMakeId(sour.getMakeId());
		}
		if (StringUtil.isNotEmpty(sour.getMenuId())) {
			target.setMenuId(sour.getMenuId());
		}
		if (StringUtil.isNotEmpty(sour.getSpecDetailId())) {
			target.setSpecDetailId(sour.getSpecDetailId());
		}
		if (sour.getNum()>0) {
			target.setNum(sour.getNum());
		}
	}
	
//////////////////////////////////////////////////for carts and order //////////////////////////////////////////////////////////////////

	/**
	 * 订单管理, 包含下单前订单检查, 提交订单, 获取订单列表, 检查订单变化 其中 获取订单列表以及点单检查两个请求为巡回请求
	 */
//	public static Response submitAndCheckOrder(HttpRequestEx httpRequest,
//			String entityId, String seatCode, String orderId, String menuId,
//			String people) {
//		boolean flag = false;
//		Response response = null;
//
//		try {
//			logger.info("订单管理");
//
//			// list privilege of carts
//			List<String> menuIdList = new ArrayList<String>();
//			menuIdList.add(menuId);
//		    WechatBaseUtil.listCartsPrivilege(httpRequest, entityId, menuIdList);
//
//			// check order
//			String cartsListJson = BeanProvider.getCartSuitListJson();
//			WechatBaseUtil.checkOrder(httpRequest, entityId, seatCode, "",
//					cartsListJson);
//
//			// submit order
//			WechatBaseUtil.submitOrder(httpRequest, entityId, seatCode, people,
//					"", cartsListJson);
//
//			// get order list
//			Response responseFromGetOrder = WechatBaseUtil.getOrder(
//					httpRequest, entityId, seatCode, "");
//			String orderIdsFormJson = BeanProvider
//					.getOrderIdsFormListJson(responseFromGetOrder);
//
//			// check order change
//			Response responseFromCheckOrderChange = WechatBaseUtil
//					.checkOrderChange(httpRequest, entityId, seatCode, orderId,
//							orderIdsFormJson);
//			// Assert.assertEquals(responseFromCheckOrderChange.getStatus(), 1);
//
//			flag = true;
//			return responseFromCheckOrderChange;
//		} catch (Exception e) {
//			logger.info(e.toString());
//		}
//		return response;
//	}
	
	/**
	 * 去支付
	 * @param httpRequest
	 * @param entityId
	 * @param seatCode
	 * @param orderId
	 * @return
	 * @throws JsonProcessingException 
	 */
	public static Boolean intoBillPage(HttpRequestEx httpRequest, BaseParamBean baseParam, String flag,List<CartIncludeSuitForm> carts) throws JsonProcessingException {
		QueryTradeBillForm queryTradeBillForm = new QueryTradeBillForm();
		queryTradeBillForm.setEntityId(baseParam.getEntityId());
		queryTradeBillForm.setOrderId(baseParam.getOrderId());
		queryTradeBillForm.setSeatCode(baseParam.getSeatCode());
		queryTradeBillForm.setSource(1);
		queryTradeBillForm.setWaitingOrderId("");
		queryTradeBillForm.setCardId(baseParam.getCardId());
		queryTradeBillForm.setCouponId(0);
		queryTradeBillForm.setCodeId(0);
		queryTradeBillForm.setSign("");
		queryTradeBillForm.setShopId("");
		queryTradeBillForm.setPromotionId("");
		queryTradeBillForm.setPromotionCustomerId("");
		
		Map<String, Object> parMap = new HashMap<String, Object>();
		parMap.put("query_bill_form", queryTradeBillForm);
		parMap.put("selected", "true");
		parMap.put("flag", flag);
		parMap.put("cart_form", carts);
		
		parMap.put("from_cart", "1");
		
		// 获取购物车数据
		Response cartData = WechatBaseUtil.listCartData(httpRequest, baseParam);
		JsonObject checkJsonObject = new JsonParser().parse(cartData.getResponseStr()).getAsJsonObject();			
		Assert.assertEquals(checkJsonObject.get("code").getAsInt(), 1, "获取购物车数据失败");
		String cartTime = checkJsonObject.get("data").getAsJsonObject().get("cartTime").getAsString();
		parMap.put("cartTime", cartTime);
		
		Response response = WechatBaseUtil.getTradeBillV2(httpRequest,baseParam,parMap);
		if (isSuccess(response)) {
			commonMap.put("tradebill_data", JSONPath.read(response.getResponseStr(), "$.data"));
			return true;
		}
		return false;
	}
	
	

	public static Boolean getPayType(HttpRequestEx httpRequest,BaseParamBean baseParam,Map<String, String> parMap,List<CartIncludeSuitForm> cartList) {
		Boolean flag = false;
		JSONObject payOrder = (JSONObject) JSONPath.read(commonMap.get("tradebill_data").toString(), "$.pay_order");
		
		Response response = SeatPrePayUtil.confirmPrePay(httpRequest, gson.toJson(cartList),baseParam, parMap);
		if (!isSuccess(response)) {
			return flag;
		}
		logger.info("存储公共参数：confirmPre_data："+ JSONPath.read(response.getResponseStr(), "$.data").toString());
		commonMap.put("confirmPre_data", JSONPath.read(response.getResponseStr(), "$.data"));
		
		String shopId = (String) JSONPath.read(commonMap.get("getState_data").toString(), "$.shop.shop_id");
		String csrfToken = (String) JSONPath.read(commonMap.get("tradebill_data").toString(), "$.csrfToken");

		PayTypeRequestForm payTypeForm = new PayTypeRequestForm();
		CreateSnapshotForm createSnapshotForm = new CreateSnapshotForm();
		createSnapshotForm.setSource(1);
		createSnapshotForm.setShopId(shopId);
		createSnapshotForm.setSeatCode(baseParam.getSeatCode());
		createSnapshotForm.setOrderId(baseParam.getOrderId());
		createSnapshotForm.setEntityId(baseParam.getEntityId());
		createSnapshotForm.setTotalFee(payOrder.getIntValue("fee"));
		createSnapshotForm.setOriginFee(payOrder.getIntValue("originPrice"));
		createSnapshotForm.setServiceFee(payOrder.getIntValue("originServiceCharge"));
		createSnapshotForm.setDiscountFee(payOrder.getIntValue("discountFee"));
		createSnapshotForm.setNeedFee(payOrder.getIntValue("needFee"));
		createSnapshotForm.setPaidFee(payOrder.getIntValue("paidFee"));
		createSnapshotForm.setDeductFee(payOrder.getIntValue("deductFee"));
		createSnapshotForm.setCsrfToken(csrfToken);
		payTypeForm.setCreateSnapshotForm(createSnapshotForm);
		Response response2 = SeatPrePayUtil.getPayType(httpRequest,baseParam, payTypeForm);
		if (!isSuccess(response2)) {
			return flag;
		}
		logger.info("存储公共参数：payType_data："+ JSONPath.read(response2.getResponseStr(), "$.data").toString());
		commonMap.put("payType_data", JSONPath.read(response2.getResponseStr(), "$.data"));
		
		flag = true;
		return flag;
	}


	public static Boolean cardPay(HttpRequestEx httpRequest,BaseParamBean baseParam,CardPayBean cardPayBean) {
		Boolean flag = false;
		String entityId = baseParam.getEntityId();
		
		JSONObject payOrder = (JSONObject) JSONPath.read(commonMap.get("tradebill_data").toString(), "$.pay_order");
		String waitingOrderId = (String) JSONPath.read(commonMap.get("confirmPre_data").toString(), "$.waitingOrder.id");
		String snapshotId = (String) JSONPath.read(commonMap.get("payType_data").toString(), "$.snapshotId");
		String cardId = (String) JSONPath.read(commonMap.get("payType_data").toString(), "$.cards[0].id");
		
		cardPayBean.setEntity_id(entityId);
		cardPayBean.setNeed_fee(String.valueOf(payOrder.getIntValue("needFee")));
		cardPayBean.setOrder_id("");
		cardPayBean.setWaiting_order_id(waitingOrderId);
		cardPayBean.setSnapshot_id(snapshotId);
		cardPayBean.setOrigin_fee(String.valueOf(payOrder.getIntValue("originPrice")));
		cardPayBean.setCard_id(cardId);
		Response response1 = SeatPrePayUtil.cardPay(httpRequest,baseParam,cardPayBean);
		if (!isSuccess(response1)) {
			return flag;
		}
		String outTradeNo = (String) JSONPath.read(response1.getResponseStr(), "$.data.outTradeNo");
		
		String payType = "4" ;//会员卡支付
		Map<String,String> parMap = new HashMap<String, String>();
		parMap.put("outTradeNo", outTradeNo);
		parMap.put("payType", payType);
		parMap.put("snapshotId", snapshotId);
		Response response2 = SeatPrePayUtil.queryPayBill(httpRequest,baseParam,parMap);
		if (!isSuccess(response2)) {
			return flag;
		}
		//预付款返回orderID
		String orderId = (String) JSONPath.read(response2.getResponseStr(), "$.data.orderId");
		baseParam.setOrderId(orderId);
		Response response3 = SeatPrePayUtil.queryShopTax(httpRequest,baseParam);
		if (!isSuccess(response3)) {
			return flag;
		}
		flag = true;
		return flag;
	}

}
