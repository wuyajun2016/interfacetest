package com.dfire.testcase.function.util.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.dfire.testcase.function.bean.BaseParamBean;
import com.dfire.testcase.function.bean.BillRequestForm;
import com.dfire.testcase.function.bean.CardPayBeanV2;
import com.dfire.testcase.function.bean.CreateSnapshotForm;
import com.dfire.testcase.function.bean.PayTypeRequestForm;
import com.dfire.testcase.function.bean.QueryTradeBillForm;
import com.dfire.wechat.util.BeanProvider;
import com.dfire.wechat.util.CartIncludeSuitForm;
import com.dfire.wechat.util.HttpRequestEx;
import com.dfire.wechat.util.Response;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class SeatPrePayUtil extends WechatBaseUtil{
	
	private static final Logger logger = Logger.getLogger(WechatBaseUtil.class);
	
	protected static final String[] xtoken = {
			"c4c576cdfbc65a5c1c1355414dc3e260",
			"4491c3560597a8c89123929219856a39",
			"1b60096f549717236f500663093062cf" };
	
	/**
	 * 扫桌码, 需要验证店铺数据 <br/>
	 * 该接口已被废弃，对应接口可参考：{@link com.dfire.testcase.function.util.base.SeatNoPrePayUtil#scanCode(HttpRequestEx, BaseParamBean, Map)  扫桌码非预付款的 扫码 动作}
	 */
	@Deprecated
	public static Response scanCode(HttpRequestEx httpRequest, BaseParamBean baseParamBean, Map<String, String> otherParameter){
		
		Response response = null;
		
		try{
						
			// 二维码扫码入口
			Response responseTemp = oauthQRCodeForSeat(httpRequest, baseParamBean, otherParameter);
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
	  * 预付款扫桌码下单及支付<br/>
	  * 包含请求: 获取购物车中菜单列表, 获取账单, 下单, 获取支付类型, 进行会员卡支付
	  */
	 public static Map<String, Response> placeAndPayForOrder(HttpRequestEx httpRequest, BaseParamBean baseParamBean, 
			 Map<String, String> otherParameter, List<CartIncludeSuitForm> cartSuitList ){

		 Map<String, Response> response = new HashMap<String, Response>();
		 Gson gson = new Gson();
		
		try{
			
			/////////////////////////////  获取账单        ////////////////////////////////////////////////////////
			
			
			// 获取购物车数据以获取其中的 cart_time 字段用于后续数据填充
			Response cartData = WechatBaseUtil.listCartData(httpRequest, baseParamBean);
			JsonObject checkJsonObject = new JsonParser().parse(cartData.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(checkJsonObject.get("code").getAsInt(), 1, "获取购物车数据失败");
			String cartTime = checkJsonObject.get("data").getAsJsonObject().get("cartTime").getAsString();
			otherParameter.put("cartTime", cartTime);
			
			
			QueryTradeBillForm queryTradeBillForm = new QueryTradeBillForm();
			queryTradeBillForm.setEntityId(baseParamBean.getEntityId());
			queryTradeBillForm.setOrderId(baseParamBean.getOrderId());
			queryTradeBillForm.setSeatCode(baseParamBean.getSeatCode());
			queryTradeBillForm.setSource(1);
			queryTradeBillForm.setCardId("");
			queryTradeBillForm.setSign("");
			queryTradeBillForm.setPromotionId("");
			queryTradeBillForm.setPromotionCustomerId("");
			
			queryTradeBillForm.setFrom_cart(1);
			
			BillRequestForm billRequestForm = new BillRequestForm();
			billRequestForm.setQuery_bill_form(queryTradeBillForm);       // 具体账单参数
			billRequestForm.setFlag(false);                               // 是否选中赞助礼品
			billRequestForm.setSelected(true);                            // 重新拉取账单    
			billRequestForm.setFrom_cart(1);
			billRequestForm.setCart_time(Long.getLong(cartTime));
			

			Response getTradeBill = WechatBaseUtil.getTradeBillV2Test(httpRequest, baseParamBean, gson.toJson(billRequestForm));
			JsonObject getTradeBillJsonObject = new JsonParser().parse(getTradeBill.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(getTradeBillJsonObject.get("code").getAsInt(), 1, "获取账单失败");
			
			JsonObject data = getTradeBillJsonObject.get("data").getAsJsonObject();
			JsonObject payOrder = data.get("pay_order").getAsJsonObject();

						
			
			
			//////////////////////////    提交订单            /////////////////////////////////////////////////////////
			
			
			// 提交订单
			Response orderResult = WechatBaseUtil.submitOrder(httpRequest, baseParamBean, otherParameter);
			JsonObject orderObject = new JsonParser().parse(orderResult.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(orderObject.get("code").getAsInt(), 1, "提交订单失败");
			String waitingOrderId = orderObject.get("data").getAsJsonObject().get("waitingOrderId").getAsString();
			
			
			
			
			
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
			createSnapshotForm.setWaitingOrderId(waitingOrderId);
			
			payTypeForm.setCreateSnapshotForm(createSnapshotForm);
			
			// just for test 
//			HttpRequestEx httpRequestTestEx = new HttpRequestEx("10.1.66.32:8080", false);
			Response getPayType = WechatBaseUtil.getPayTypeTest(httpRequest, baseParamBean, payTypeForm);
			
			JsonObject getPayTypeJsonObject = new JsonParser().parse(getPayType.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(getPayTypeJsonObject.get("code").getAsInt(), 1, "获取支付类型失败");
			
			JsonObject dataForPayType = getPayTypeJsonObject.get("data").getAsJsonObject();
			JsonArray cardsForPayType = dataForPayType.get("cards").getAsJsonArray();
			
			
			
			//////////////////////////////  会员卡支付        //////////////////////////////////////////////////
			
			CardPayBeanV2 cardPayBean = new CardPayBeanV2();
			
			cardPayBean.setEntity_id(baseParamBean.getEntityId());
			cardPayBean.setNeed_fee(payOrder.get("needFee").getAsInt());
			
			cardPayBean.setOrder_id("");			
			cardPayBean.setWaiting_order_id(waitingOrderId);
			
			cardPayBean.setSnapshot_id(dataForPayType.get("snapshotId").getAsString());
			cardPayBean.setOrigin_fee(payOrder.get("originPrice").getAsInt());			
			cardPayBean.setCard_id(cardsForPayType.get(0).getAsJsonObject().get("id").getAsString());
			
//			HttpRequestEx httpRequesttest = new HttpRequestEx("10.1.66.81:8080", false);
			Response cardPay = WechatBaseUtil.cardPayV2(httpRequest, baseParamBean, cardPayBean);
			JsonObject cardPayJsonObject = new JsonParser().parse(cardPay.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(cardPayJsonObject.get("code").getAsInt(), 1, "会员卡支付失败");
			
			
			//////////////////////////   数据封装       ////////////////////////////////////////////////
			response.put("getTradeBill", getTradeBill);
			response.put("waitingOrder", orderResult);
			response.put("getPayType", getPayType);			
			response.put("cardPay", cardPay);						
			
		}catch(Exception e){
			
			logger.info("出现异常");
			e.printStackTrace();
		}
		
		return response;
   }
	 
	 

	
}












