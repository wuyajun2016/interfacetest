package com.dfire.testcase.function.util.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.alibaba.fastjson.JSON;
import com.dfire.testcase.function.bean.BaseParamBean;
import com.dfire.testcase.function.bean.CardPayBean;
import com.dfire.testcase.function.bean.CardPayBeanV2;
import com.dfire.testcase.function.bean.CreateSnapshotForm;
import com.dfire.testcase.function.bean.PayTypeRequestForm;
import com.dfire.testcase.function.bean.QueryTradeBillForm;
import com.dfire.wechat.util.BeanProvider;
import com.dfire.wechat.util.CartIncludeSuitForm;
import com.dfire.wechat.util.CommonConstants;
import com.dfire.wechat.util.HttpRequestEx;
import com.dfire.wechat.util.PathForPost;
import com.dfire.wechat.util.Response;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class SeatNoPrePayUtil extends WechatBaseUtil{
	
	private static final Logger logger = Logger.getLogger(SeatNoPrePayUtil.class);
	private static Gson gson = new Gson();
	
	
	/**
	 * 获取付款信息
	 */
	public static Response getBillInfo(HttpRequestEx httpRequest, BaseParamBean baseParamBean) {
		
		Response response = null;
		try {
			
			logger.info("获取付款信息");
			Map<String, String> param = new HashMap<String, String>();
			param.put("xtoken", baseParamBean.getXtoken());
			param.put("entity_id", baseParamBean.getEntityId());
			param.put("seat_code", baseParamBean.getSeatCode());
			param.put("order_id", baseParamBean.getOrderId());
			
			response = httpRequest.post(PathForPost.getPathForGetBillInfo(), param);
			
			return response;
			
		} catch (Exception e) {
			logger.info(e.toString());
		}
		
		return response;
	}
	
	/**
	 * 获取优惠方案及支付订单信息
	 * 该接口存疑
	 * 请求 URL ：pay/v2/get_trade_bill
	 */
	public static Response getTradeBill(HttpRequestEx httpRequest,BaseParamBean baseParamBean,Map<String, Object> parMap){

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
			
			logger.info("httpBody:"+mapper.writeValueAsString(httpBody));
			response = httpRequest.post(PathForPost.getPathForGetTradeBillV2(), query,mapper.writeValueAsString(httpBody)); 
			return response;
			
		}catch(Exception e){
			e.printStackTrace();
			logger.info(e.toString());
		}
		
		return response;
	}
	
/////////////////////////////////////  模块方法      //////////////////////////////////////////////////////////////////////
		
	/**
	 * 扫桌码, 需要验证店铺数据
	 * 需要参数：entityId, seatCode, signKey
	 */
	public static Response scanCode(HttpRequestEx httpRequest, BaseParamBean baseParamBean, Map<String, String> otherParameter){
		
		Response response = null;
		
		try{
			
			// 二维码扫码入口
			Response responseTemp = oauthQRCodeForSeat(httpRequest, baseParamBean, otherParameter);
			Assert.assertEquals(responseTemp.getStatus(), 200);
			
			// 获取初始化数据
			response = getInitDataForShop(httpRequest, baseParamBean);
			Assert.assertEquals(response.getStatus(), 200);
			
			// 首页会员等级信息详情
			Response responsePersonInfo = getPersonInfo(httpRequest, baseParamBean);
			Assert.assertEquals(responsePersonInfo.getStatus(), 200);
						
			// 查找用户个人信息 
			Response responseUserInfo = getUserInfo(httpRequest, baseParamBean);
			Assert.assertEquals(responseUserInfo.getStatus(), 200);
			
			// 店铺分享URL的信息（包括图片、文案）接口
			Response responseForShare = shareForShop(httpRequest, baseParamBean);
			Assert.assertEquals(responseForShare.getStatus(), 200);
			
		}catch(Exception e){
			logger.info(e.toString());
		}
		
		return response;
	}
	
	/**
	 * 扫桌码, 需要验证店铺数据, 该接口针对扫店码之后扫桌码，带有 cookie
	 * 需要参数：entityId, seatCode, signKey
	 */
	public static Response scanCode(HttpRequestEx httpRequest, BaseParamBean baseParamBean, Map<String, String> otherParameter, Map<String, String> header){
		
		Response response = null;
		
		try{
			
			// 二维码扫码入口
			Response responseTemp = oauthQRCodeForSeat(httpRequest, baseParamBean, otherParameter, header);
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
	 * <b>点击进入</b>，进入菜单列表页
	 * Map 中 的 key 为：newCart, cartList, priTitle, menuList <br/>
	 * <b>原始参数</b> ：entityId, seatCode, repeat, recommend  (orderID  :  "") <br/>
	 * <span style="color: red;"><B>该函数已更新<B></span>
	 */
	public static Map<String, Response> clickToEnter(HttpRequestEx httpRequest, BaseParamBean baseParamBean, Map<String, String> otherParameter){
		
		Map<String, Response> response = new HashMap<String, Response>();
		
		try{
			
			// prepare new cart
			Response newCart = WechatBaseUtil.createCart(httpRequest, baseParamBean);			
			
			// get info of carts with list format
			Response cartList = WechatBaseUtil.listCartData(httpRequest, baseParamBean);
			
			// show privilege title
			Response priTitle = WechatBaseUtil.showPrivilegeTitle(httpRequest, baseParamBean);
			
			// list menus
			Response menuList = WechatBaseUtil.listMenus(httpRequest, baseParamBean, otherParameter);
			
			// pop up message
			Response popupMessage = WechatBaseUtil.popupMessage(httpRequest, baseParamBean);
			
			// cart privilege
			Response cartPrivilege = WechatBaseUtil.cartPrivilege(httpRequest, baseParamBean, otherParameter);
			
			response.put("newCart", newCart);
			response.put("cartList", cartList);
			response.put("priTitle", priTitle);
			
			response.put("menuList", menuList);
			response.put("popUpMessage", popupMessage);
			response.put("cartPrivilege", cartPrivilege);
			
		}catch(Exception e){
			logger.info(e.toString());
		}
		
		return response;
	}
	
	 /**
	  * 非预付款扫桌码加菜<br/>
	  * 包含请求: 创建购物车, 修改人数, 添加必选菜, 加菜(包括普通菜和套菜)
	  */
	 public static Map<String, Response> addMenuToCart(HttpRequestEx httpRequest, BaseParamBean baseParamBean, 
			 Map<String, String> otherParameter, List<CartIncludeSuitForm> cartSuitList){

    	Map<String, Response> response = new HashMap<String, Response>();
		
		try{
			
			// 重新创建购物车, 以免之前创建的购物车失效
			Response newCart = WechatBaseUtil.createCart(httpRequest, baseParamBean);			
			
			// 修改人数和备注信息
			Response modifyPeople = WechatBaseUtil.modifyPeople(httpRequest, baseParamBean, otherParameter);
			
			// 添加必选商品
			Response addForceMenu = WechatBaseUtil.addRequiredItem(httpRequest, baseParamBean, otherParameter);			
			
			//将菜加入购物车
			for(CartIncludeSuitForm cartSuitForm : cartSuitList){
				WechatBaseUtil.addDishToCarts(httpRequest, baseParamBean, gson.toJson(cartSuitForm));
			}
			
			response.put("newCart", newCart);
			response.put("modifyPeople", modifyPeople);			
			response.put("addForceMenu", addForceMenu);						
			
		}catch(Exception e){
			logger.info(e.toString());
		}
		
		return response;
    }
	 
	 
	 /**
	  * 非预付款扫桌码下单<br/>
	  * 包含请求: 获取购物车数据, 提交订单, 获取订单列表, 检查订单变化
	  */
	 public static Map<String, Response> placeOrder(HttpRequestEx httpRequest, BaseParamBean baseParamBean, Map<String, String> otherParameter){

    	Map<String, Response> response = new HashMap<String, Response>();
		
		try{
			
			// 获取购物车数据
			Response cartData = WechatBaseUtil.listCartData(httpRequest, baseParamBean);
			JsonObject checkJsonObject = new JsonParser().parse(cartData.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(checkJsonObject.get("code").getAsInt(), 1, "获取购物车数据失败");
			String cartTime = checkJsonObject.get("data").getAsJsonObject().get("cartTime").getAsString();
			otherParameter.put("cartTime", cartTime);
			
			//提交订单
			Response orderResult = WechatBaseUtil.submitOrder(httpRequest, baseParamBean, otherParameter);
			JsonObject orderObject = new JsonParser().parse(orderResult.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(orderObject.get("code").getAsInt(), 1, "提交订单失败");
			
			// 获取订单列表
			Response responseFromGetOrder = WechatBaseUtil.getOrder(httpRequest, baseParamBean);
			String orderIdsFormJson = BeanProvider.getOrderIdsFormListJson(responseFromGetOrder);
			
			// 检查订单变化  (可能多人提交订单)
			Response orderChange = WechatBaseUtil.checkOrderChange(httpRequest, baseParamBean, orderIdsFormJson);
			JsonObject orderChangeObject = new JsonParser().parse(orderChange.getResponseStr()).getAsJsonObject();			
			
			if(1 == orderChangeObject.get("code").getAsInt())
				logger.info("订单未发生变化");
			else
				logger.info("订单发生了变化");
			
			response.put("cartData", cartData);
			response.put("orderResult", orderResult);	
			
			response.put("responseFromGetOrder", responseFromGetOrder);	
			response.put("orderChange", orderChange);
			
		}catch(Exception e){
			logger.info(e.toString());
		}
		
		return response;
    }
	 
	
	 
	 /**
	  * 非预付款扫桌码刷新获取最新订单<br/>
	  * 下单并且收银审核同意后刷新页面<br/>
	  * 包含请求: 获取订单列表, 获取点赞活动开关, 查询订单是否允许评价, 检查订单变化
	  */
	 public static Map<String, Response> refreshToGetPayDetail(HttpRequestEx httpRequest, BaseParamBean baseParamBean, Map<String, String> otherParameter ){

		logger.info("开始刷新订单页面");
		
    	Map<String, Response> response = new HashMap<String, Response>();
		
		try{
			
			// 获取订单列表
			Response getOrder = WechatBaseUtil.getOrder(httpRequest, baseParamBean);	
			String orderIdsFormJson = BeanProvider.getOrderIdsFormListJson(getOrder);
			JsonObject getOrderJsonObject = new JsonParser().parse(getOrder.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(getOrderJsonObject.get("code").getAsInt(), 1, "获取订单列表失败");

			// 获取点赞活动开关[目前暂时不支持点赞]
			Response getActivitySwitch = WechatBaseUtil.getActivitySwitch(httpRequest, baseParamBean);			
			JsonObject getActivitySwitchJsonObject = new JsonParser().parse(getActivitySwitch.getResponseStr()).getAsJsonObject();			
//			Assert.assertEquals(getActivitySwitchJsonObject.get("code").getAsInt(), 1, "获取订单列表失败");
			
			
//			// 查询订单是否允许评价, 该接口需要调试？？？
//			Response getOrderComments = WechatBaseUtil.getOrderComments(httpRequest, baseParamBean, otherParameter);			
//			JsonObject getOrderCommentsJsonObject = new JsonParser().parse(getOrderComments.getResponseStr()).getAsJsonObject();			
//			Assert.assertEquals(getOrderCommentsJsonObject.get("code").getAsInt(), 1, "查询订单是否允许评价失败");
			
			
			// 检查订单变化  (可能多人提交订单)
			Response orderChange = WechatBaseUtil.checkOrderChange(httpRequest, baseParamBean, orderIdsFormJson);
			JsonObject orderChangeObject = new JsonParser().parse(orderChange.getResponseStr()).getAsJsonObject();			
			
			if(1 == orderChangeObject.get("code").getAsInt())
				logger.info("订单未发生变化");
			else
				logger.info("订单发生了变化");
			
			response.put("getOrder", getOrder);
			response.put("getActivitySwitch", getActivitySwitch);			
//			response.put("getOrderComments", getOrderComments);	
			response.put("orderChange", orderChange);
			
		}catch(Exception e){
			
			logger.info(e.toString());
		}
		
		logger.info("订单页面刷新成功");
		
		return response;
    }
	 
	 
	 
	 /**
	  * 非预付款扫桌码支付订单<br/>
	  * 页面已刷新,支付订单<br/>
	  * 包含请求: 获取账单,获取支付类型, 进行会员卡支付
	  */
	 public static Map<String, Response> payForOrder(HttpRequestEx httpRequest, BaseParamBean baseParamBean, List<CartIncludeSuitForm> cartSuitList ){

    	Map<String, Response> response = new HashMap<String, Response>();
		
		try{
			
			/////////////////////////////  获取账单        ////////////////////////////////////////////////////////
			QueryTradeBillForm queryTradeBillForm = new QueryTradeBillForm();
			queryTradeBillForm.setEntityId(baseParamBean.getEntityId());
			queryTradeBillForm.setOrderId(baseParamBean.getOrderId());
			queryTradeBillForm.setSeatCode(baseParamBean.getSeatCode());
			queryTradeBillForm.setSource(1);
			queryTradeBillForm.setCardId("");
			queryTradeBillForm.setSign("");
			queryTradeBillForm.setPromotionId("");
			queryTradeBillForm.setPromotionCustomerId("");
			
			Map<String, Object> parMap = new HashMap<String, Object>();
			parMap.put("query_bill_form", queryTradeBillForm);   // 具体账单参数
			parMap.put("selected", "false");  // 是否选中赞助礼品
			parMap.put("flag", "false");	     // 重新拉取账单
//			parMap.put("flag", "true");	     // 重新拉取账单 ,如果修改为 true，会在 Dpush 端产生一个消息，相当于重新拉取账单，否则默认已经拉取了账单。就不会在 Dpush 端产生消息
			
			// 下述三个参数目前非必填
//			parMap.put("cart_form", gson.toJson(cartSuitList));
//			parMap.put("cart_time", "true");	
//			parMap.put("from_cart", "true");	
			
			Response getTradeBill = WechatBaseUtil.getTradeBillV2(httpRequest, baseParamBean, parMap);
			JsonObject getTradeBillJsonObject = new JsonParser().parse(getTradeBill.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(getTradeBillJsonObject.get("code").getAsInt(), 1, "获取账单失败");
			
			JsonObject data = getTradeBillJsonObject.get("data").getAsJsonObject();
			JsonObject payOrder = data.get("pay_order").getAsJsonObject();

			
			//////////////////////////     获取支付类型           ////////////////////////////////////////////////////////
			// 获取初始化数据
			Response shopState = WechatBaseUtil.getInitDataForShop(httpRequest, baseParamBean);
			JsonObject shopStateJsonObject = new JsonParser().parse(shopState.getResponseStr()).getAsJsonObject();				
			Assert.assertEquals(shopStateJsonObject.get("code").getAsInt(), 1, "获取 shopId 失败");
			
			JsonObject shopObject = shopStateJsonObject.get("data").getAsJsonObject().get("shop").getAsJsonObject();
			String shopId = shopObject.get("shop_id").getAsString();
			
			
			PayTypeRequestForm payTypeForm = new PayTypeRequestForm();
			
			CreateSnapshotForm createSnapshotForm = new CreateSnapshotForm();
			createSnapshotForm.setSource(1);
			createSnapshotForm.setShopId(shopId);
			
			createSnapshotForm.setSeatCode(baseParamBean.getSeatCode());
			createSnapshotForm.setOrderId(baseParamBean.getOrderId());
			createSnapshotForm.setEntityId(baseParamBean.getEntityId());
			
			createSnapshotForm.setTotalFee(payOrder.get("fee").getAsInt());   // /100
			createSnapshotForm.setOriginFee(payOrder.get("originPrice").getAsInt());  // /100
			createSnapshotForm.setServiceFee(payOrder.get("originServiceCharge").getAsInt());
			
			createSnapshotForm.setDiscountFee(payOrder.get("discountFee").getAsInt());   // /100
			createSnapshotForm.setNeedFee(payOrder.get("needFee").getAsInt());  // /100
			createSnapshotForm.setPaidFee(payOrder.get("paidFee").getAsInt());
			
			createSnapshotForm.setDeductFee(payOrder.get("deductFee").getAsInt());
			createSnapshotForm.setCsrfToken(data.get("csrfToken").getAsString());
			
			payTypeForm.setCreateSnapshotForm(createSnapshotForm);
			
			Response getPayType = WechatBaseUtil.getPayType(httpRequest, baseParamBean, payTypeForm);
			JsonObject getPayTypeJsonObject = new JsonParser().parse(getPayType.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(getPayTypeJsonObject.get("code").getAsInt(), 1, "获取支付类型失败");
			
			JsonObject dataForPayType = getPayTypeJsonObject.get("data").getAsJsonObject();
			JsonArray cardsForPayType = dataForPayType.get("cards").getAsJsonArray();
			
			
			
			//////////////////////////////  会员卡支付        //////////////////////////////////////////////////
			
			CardPayBeanV2 cardPayBean = new CardPayBeanV2();
			
			cardPayBean.setEntity_id(baseParamBean.getEntityId());
			cardPayBean.setNeed_fee(payOrder.get("needFee").getAsInt());
			
			cardPayBean.setOrder_id(payOrder.get("orderId").getAsString());			
			cardPayBean.setWaiting_order_id("");
			
			cardPayBean.setSnapshot_id(dataForPayType.get("snapshotId").getAsString());
			cardPayBean.setOrigin_fee(payOrder.get("originPrice").getAsInt());			
			cardPayBean.setCard_id(cardsForPayType.get(0).getAsJsonObject().get("id").getAsString());
			
//			HttpRequestEx httpRequesttest = new HttpRequestEx("10.1.66.81:8080", false);
			Response cardPay = WechatBaseUtil.cardPayV2(httpRequest, baseParamBean, cardPayBean);
			JsonObject cardPayJsonObject = new JsonParser().parse(cardPay.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(cardPayJsonObject.get("code").getAsInt(), 1, "会员卡支付失败");
			
			
			//////////////////////////   数据封装       ////////////////////////////////////////////////
			response.put("getTradeBill", getTradeBill);
			response.put("getPayType", getPayType);			
			response.put("cardPay", cardPay);						
			
		}catch(Exception e){
			
			logger.info(e.toString());
		}
		
		return response;
    }
	 
	 
	 
	 
	/**
	 * 数据校验
	 */
	public static boolean DataVerified(HttpRequestEx httpRequest, BaseParamBean baseParam, String people, List<CartIncludeSuitForm> httpJsonForCartSuitList){
		
		boolean result = false;
		
		try{
			// 获取虚拟购物车数据及用餐人数
			Response response = WechatBaseUtil.listCartData(httpRequest, baseParam);
			JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(resp.get("code").getAsInt(), 1);
			
			// 构造验证参数
			logger.info("数据校验开始");
			
			// uid---menuId---MenuData
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
				Assert.assertTrue(customerExist, "用户：" + customer.get("id").getAsString() + " 不存在于： " + uidSet.toString());
				
				// 获取菜单详情列表
				JsonArray menuArray = cart.getAsJsonObject().get("cartVoList").getAsJsonArray();
				
				// 获取特定用户的购物车中的 menuID 集合
				Set<String> menuIdList = expectedCartSuit.get(customer.get("id").getAsString()).keySet();
				Map<String, CartIncludeSuitForm> originMenuList = expectedCartSuit.get(customer.get("id").getAsString());
				
				// 统计单个用户实际对应的菜品数目
				int actualMenuCount = 0;  
				
				// 对具体的菜品进行验证, 该菜可能为 普通菜 或者为 套菜 
				for(JsonElement menuElement : menuArray){
					
					++actualMenuCount;  // 统计菜品数目
					
					// 获取实际菜品数据
					JsonObject menuJsonObject = menuElement.getAsJsonObject();
//					String kindMenuName = menuJsonObject.get("kindMenuName").getAsString();
					String menuName = menuJsonObject.get("name").getAsString();
					String menuId = menuJsonObject.get("menuId").getAsString();
					
					int num = menuJsonObject.get("num").getAsInt();
					int kind = menuJsonObject.get("kind").getAsInt();
					
					
					// 获取具体的菜品, 该菜为期望的菜品
					CartIncludeSuitForm cartSuitForm = originMenuList.get(menuJsonObject.get("menuId").getAsString());
					
					
					// 对菜品数据进行验证
					Assert.assertTrue(menuIdList.contains(menuJsonObject.get("menuId").getAsString()), "接口返回的菜品中 menuId 在预期的数据中不存在");
//					Assert.assertEquals(kindMenuName, cartSuitForm.getKindMenuName(), "kindMenuName 不吻合");
					Assert.assertEquals(menuName, cartSuitForm.getMenuName());
					
					Assert.assertEquals(num, cartSuitForm.getNum().intValue());
					Assert.assertEquals(kind, cartSuitForm.getKindType());
										
					
				}
				
				
				// 验证单个用户的菜品数目是否一致
				int expectMenuCount = expectedCartSuit.get(customer.get("id").getAsString()).keySet().size();
				Assert.assertEquals(actualMenuCount, expectMenuCount, 
						"uid 为  " + customer.get("id").getAsString() + " 的实际菜品数目为：" + actualMenuCount + ", 期望的菜品数目为：" + expectMenuCount);
				
				
			}
			
			
    		flag = true;
    		logger.info("菜单验证成功");
 
    	}catch(Exception e){
    		
    		logger.info("菜单验证失败, 原因是 ： " + e.toString());
    		logger.info(e.toString());
    	}
    	
    	return flag;
    }

    
   
	
}

