package com.dfire.testcase.function.util.api;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.dfire.testcase.function.bean.BaseParamBean;
import com.dfire.testcase.function.bean.CreateSnapshotForm;
import com.dfire.testcase.function.bean.PayTypeRequestForm;
import com.dfire.testcase.function.util.base.SeatPrePayUtil;
import com.dfire.wechat.util.CartIncludeSuitForm;
import com.dfire.wechat.util.HttpRequestEx;
import com.dfire.wechat.util.Response;
import com.google.gson.Gson;

public class SeatPrePayAPI extends WechatBaseAPI{
	
	private static final Logger logger = Logger.getLogger(SeatPrePayAPI.class);
	private static Gson gson = new Gson();
	
	/**
	 * 扫码
	 * @param httpRequest
	 * @param entityId
	 * @param seatCode
	 * @return 
	 */
//	public static Boolean scanCode(HttpRequestEx httpRequest, String entityId,String seatCode) {
//		Boolean flag = false;
//		Response response = SeatUtil.getInitDataForShop(httpRequest, entityId, seatCode);
//		
//		//TODO /shop/v1/${entityId}/share?xtoken=${xtoken}  店铺分享URL的信息（包括图片、文案）
//		
//		if (isSuccess(response)) {
//			flag = true;
//			//将返回结果放进公共参数map，供后面接口使用
//			logger.info("存储公共参数：getState_data："+JSONPath.read(response.getResponseStr(), "$.data").toString());
//			commonMap.put("getState_data",JSONPath.read(response.getResponseStr(), "$.data"));
//		}
//		return flag;
//	}


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
	public static boolean clickToEnter(HttpRequestEx httpRequest, BaseParamBean baseParam,Map<String, String> parMap){
		boolean flag = false;
		String entityId = baseParam.getEntityId();
		String seatCode = baseParam.getSeatCode();
		String orderId = baseParam.getOrderId();
		try{
			logger.info("业务流程：点击进入");	
			// prepare new cart
			Response response1 = SeatPrePayUtil.createCart(httpRequest, baseParam);			
			if (!isSuccess(response1)){
				logger.info("创建购物车失败");
				return flag;
			}
			
			// list menus
			Response response2 = SeatPrePayUtil.listMenus(httpRequest, baseParam, parMap);
			if (!isSuccess(response2)){
				logger.info("查询菜单失败");
				return flag;
			}
			
			
			// get info of carts with list format
			Response response3 = SeatPrePayUtil.popupMessage(httpRequest, baseParam);
			if (!isSuccess(response3)){
				logger.info("获取弹窗消息");
				return flag;
			}
						
			// get info of carts with list format
			Response response4 = SeatPrePayUtil.listCartData(httpRequest, baseParam);
			if (!isSuccess(response4)){
				logger.info("查询购物车失败");
				return flag;
			}
			
			// show privilege title
			Response response5 = SeatPrePayUtil.showPrivilegeTitle(httpRequest, baseParam);
			if (!isSuccess(response5)){
				logger.info("优惠载体文字显示栏标题失败");
				return flag;
			}

			// get cart privilege
			Response response6 = SeatPrePayUtil.cartPrivilege(httpRequest, baseParam, parMap);
			if (!isSuccess(response6)){
				logger.info("购物车页面展示可用优惠信息");
				return flag;
			}
			
			flag = true;
			logger.info("存储公共参数：listMenu_data："+ JSONPath.read(response4.getResponseStr(), "$.data").toString());
			commonMap.put("listMenu_data", JSONPath.read(response4.getResponseStr(), "$.data"));			
			return flag;
			
		}catch(Exception e){
			logger.info(e.toString());
		}
		return false;
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
	 * @param cartList 
	 * @return
	 */
	public static boolean startChooseDish(HttpRequestEx httpRequest, BaseParamBean baseParam,Map<String, String> parMap){
		boolean flag = false;
		try{
			logger.info("桌码预付款进入点菜");
			// modify people and memo 修改人数和备注
			Response response1 = SeatPrePayUtil.modifyPeople(httpRequest, baseParam, parMap);
			if (!isSuccess(response1)){
				logger.info("修改人数和备注失败");
				return flag;
			}
			// add required item 添加必选商品
			Response response2 = SeatPrePayUtil.addRequiredItem(httpRequest, baseParam,parMap);
			if (!isSuccess(response2)){
				logger.info("添加必选商品失败");
				return flag;
			}
			
			//获取虚拟购物车数据及用餐人数
			Response response3 = SeatPrePayUtil.listCartData(httpRequest, baseParam);
			if (!isSuccess(response3)){
				logger.info("获取虚拟购物车数据及用餐人数失败");
				return flag;
			}
			// get recommend menus 获取购物车推荐菜列表
			Response response4 = SeatPrePayUtil.getRecommendMenus(httpRequest, baseParam,parMap);
			if (!isSuccess(response4)){
				logger.info("获取购物车推荐菜列表失败");
				return flag;
			}
				
			flag = true;
			return flag;
		}catch(Exception e){
			logger.info(e.toString());
		}
		return flag;
	}


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
		Response response1 = SeatPrePayUtil.getMenuSpec(httpRequest, baseParam,parMap);
		
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
	public static Boolean addDishToCarts(HttpRequestEx httpRequest,BaseParamBean baseParam, CartIncludeSuitForm cartIncludeSuitForm) {
		logger.info("店码预付款加入购物车！");
		Boolean flag = false;
		// add dish to the carts
		Response response1  = SeatPrePayUtil.addDishToCarts(httpRequest, baseParam, gson.toJson(cartIncludeSuitForm));
		if (isSuccess(response1)) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 进入购物车
	 * @param httpRequest
	 * @param intoCartMap 
	 * @param cartList2 
	 * @param cartList 
	 * @return
	 */
	public static Boolean intoCart(HttpRequestEx httpRequest,BaseParamBean baseParam,List<String> menuIdList,Map<String, String> intoCartMap, List<CartIncludeSuitForm> cartList) {
		Boolean flag = false;
		//创建购物车
		Response response1 = SeatPrePayUtil.createCart(httpRequest, baseParam);
		if(!isSuccess(response1)){
			logger.info("创建购物车失败！");
			return flag;
		}
		//获取虚拟购物车数据及用餐人数
		Response response2 = SeatPrePayUtil.listCartData(httpRequest, baseParam);
		cartList.clear();
		if(isSuccess(response2)){
			extractListCartData(cartList, response2);
		}
		
		//购物车页面展示可用优惠信息
		Response response3 = SeatPrePayUtil.listCartsPrivilege(httpRequest, baseParam, intoCartMap);
		if(!isSuccess(response3)){
			logger.info("购物车页面展示可用优惠信息失败！");
			return flag;
		}
		//获取推荐菜
		Response response4 = SeatPrePayUtil.getRecommendMenus(httpRequest, baseParam,intoCartMap);
		if(!isSuccess(response4)){
			logger.info("获取推荐菜失败！");
			return flag;
		}
		//下单前检查
		Response response5 = SeatPrePayUtil.checkOrder(httpRequest,baseParam, gson.toJson(cartList));
		if(!isSuccess(response5)){
			logger.info("下单前检查失败！");
			return flag;
		}
		//返回 2 需要进行调试
		
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
	 */
	public static Boolean submitOrder(HttpRequestEx httpRequest,BaseParamBean baseParam,Map<String, String> parMap, List<CartIncludeSuitForm> cartList) {
		Boolean flag = false;
		//提交订单	
		Response response1 =SeatPrePayUtil.submitOrder(httpRequest,baseParam, parMap);
		
		if (isSuccess(response1)) {
			flag = true;
		}
		return flag;
	}


	/**
	 * 收银审核以后刷新已下单的页面
	 * @param httpRequest
	 * @param entityId
	 * @param seatCode
	 * @param orderId
	 * @return
	 */
//	public static Boolean refreshOrder(HttpRequestEx httpRequest,String entityId, String seatCode, String orderId) {
//		
//		Boolean flag = false;
//		//获取订单
//		Response response1 = SeatUtil.getOrder(httpRequest, entityId, seatCode, orderId);
//		if(!isSuccess(response1)){
//			return flag;
//		}
//	//	String orderIdsFormJson = BeanProvider.getOrderIdsFormListJson(orderResponse);
//		
//		//查询订单是否允许评价
//		// TODO /evaluation/v1/play/comments?xtoken=${xtoken}&order_ids[]=${orderId}&entity_id=${entityId}
//		List<String> menuIdList = new ArrayList<String>();
//		Response response2 = SeatUtil.createWaiterComments(httpRequest, entityId, menuIdList);
//		if(!isSuccess(response2)){
//			return flag;
//		}
//		
//		//获取点赞活动开关
//		// TODO  /activity/v1/activity_switch?xtoken=${xtoken}&entity_id=${entityId}&order_id=${orderId}&seat_code=${seatCode}
//		String orderIdsFormJson = "";
//		Response response3 = SeatUtil.getActivitySwitch(httpRequest, entityId, seatCode, orderIdsFormJson);
//		if(!isSuccess(response3)){
//			return flag;
//		}
//		//检查订单变化
//		Response response4 = SeatUtil.checkOrderChange(httpRequest, entityId, seatCode, orderId, orderIdsFormJson);
//		if(!isSuccess(response4)){
//			return flag;
//		}
//		flag = true;
//		return flag;
//	}



//	public static Boolean intoHomePage(HttpRequestEx httpRequest,String entityId, String seatCode, String orderId) {
//		Boolean flag = false;
//		//获取购物车数量
//		Response response = SeatUtil.getCartCount(httpRequest, entityId, seatCode, orderId);
//		if (isSuccess(response)) {
//			flag = true;
//		}
//		return flag;
//	}


	/**
	 * 去已下单的页面
	 * @param httpRequest
	 * @param entityId
	 * @param seatCode
	 * @param orderId
	 * @return
	 */
	public static Boolean intoUnderOrderPage(HttpRequestEx httpRequest,String entityId, String seatCode, String orderId) {
		//预付款 查看已下单的菜/prepay/v1/get_prepay_order?xtoken=4491c3560597a8c89123929219856a39&t=1476953336785&g_entityId=99928345 HTTP/1.1
		//TODO 
		
		//查询订单是否允许评价 /evaluation/v1/play/comments?xtoken=4491c3560597a8c89123929219856a39&t=1476953337012&g_entityId=99928345&order_ids%5B%5D=9992834557e130bf0157e130bf220000&entity_id=99928345 HTTP/1.1
		//TODO 
		
		//获取点赞活动开关  /activity/v1/activity_switch?xtoken=4491c3560597a8c89123929219856a39&t=1476953337013&g_entityId=99928345&entity_id=99928345&order_id=9992834557e130bf0157e130bf220000&seat_code=B1 HTTP/1.1
		//TODO  
		
		//检查订单的变化	/orders/v1/check_order_change?entity_id=99928345&seat_code=B1&order_id=9992834557e130bf0157e130bf220000&xtoken=4491c3560597a8c89123929219856a39&t=1476953337030&g_entityId=99928345 HTTP/1.1
		//TODO  
		
		//可以分离出去
		//查看支付信息
		//TODO /prepay/v1/get_bill_info?xtoken=4491c3560597a8c89123929219856a39&t=1476953693867&g_entityId=99928345 HTTP/1.1
		return null;
	}


	public static Boolean intoMenusPage(HttpRequestEx httpRequest,String entityId, String seatCode, String orderId) {
		boolean flag = false;
		try{
			logger.info("非空桌进入菜单页面");
			//create cart
			
			//list menus
			
			//list cart
			
			///privilege/v1/title
			
			
		}catch(Exception e){
			logger.info(e.toString());
		}
		return flag;
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
		
		String waitingOrderId = (String) JSONPath.read(response.getResponseStr().toString(), "$.data.waitingOrder.id");
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
		createSnapshotForm.setWaitingOrderId(waitingOrderId);
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



	
}
