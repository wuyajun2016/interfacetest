package com.dfire.testcase.bean;

import java.util.HashMap;
import java.util.Map;

public class PayType {
	
	// 店铺ID
	private String entity_id;
	// 桌位码
	private String seat_code;
	// 会员卡ID
	private String card_id;
	
	
	// 红包 ID 
	private String coupon_id;
	// 红包 code
	private String code_id;
	// 商铺 ID
	private String shop_id;
	
	
	// 签名
	private String sign;
	// 优惠平台优惠ID
	private String promotion_id;
	// 优惠平台优惠券用户ID
	private String promotion_customer_id;
	
	
	// 订单 ID
	private String order_id;
	// 预付款订单ID
	private String waiting_order_id;
	// 总金额
	private String total_fee;
	
	
	// 原始金额
	private String origin_fee;
	// 服务费
	private String service_fee;
	// 优惠金额
	private String discount_fee;
	
	
	
	// 应付金额
	private String need_fee;
	// 已支付金额
	private String paid_fee;
	// 赞助抵扣金额
	private String deduct_fee;
	
	
	
	// 优惠抵扣金额
	private String promotion_fee;
	// ???
	private String csrf_token;
	// 礼品列表
	private String gift_forms_string;
	
	
	/**
	 * 返回 Map &lt; String, String &gt; 形式的 bean
	 * @return
	 */
	public Map<String, String> mapBean(){
		
		Map<String, String> bean = new HashMap<String, String>(); 
		bean.put("entity_id", this.entity_id);
		bean.put("seat_code", this.seat_code);
		bean.put("card_id", this.card_id);
		
		bean.put("coupon_id", this.code_id);
		bean.put("code_id", this.code_id);
		bean.put("shop_id", this.shop_id);
		
		bean.put("sign", this.sign);
		bean.put("promotion_id", this.promotion_id);
		bean.put("promotion_customer_id", this.promotion_customer_id);
		
		bean.put("order_id", this.order_id);
		bean.put("waiting_order_id", this.waiting_order_id);		
		bean.put("total_fee", this.total_fee);
		
		bean.put("origin_fee", this.origin_fee);		
		bean.put("service_fee", this.service_fee);
		bean.put("discount_fee", this.discount_fee);
		
		bean.put("need_fee", this.need_fee);		
		bean.put("paid_fee", this.paid_fee);
		bean.put("deduct_fee", this.deduct_fee);
		
		bean.put("promotion_fee", this.promotion_fee);		
		bean.put("csrf_token", this.csrf_token);
		bean.put("gift_forms_string", this.gift_forms_string);
		
		return bean;
	}
	
	public String getEntity_id() {
		return entity_id;
	}
	public void setEntity_id(String entity_id) {
		this.entity_id = entity_id;
	}
	public String getSeat_code() {
		return seat_code;
	}
	public void setSeat_code(String seat_code) {
		this.seat_code = seat_code;
	}
	public String getCard_id() {
		return card_id;
	}
	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}
	public String getCoupon_id() {
		return coupon_id;
	}
	public void setCoupon_id(String coupon_id) {
		this.coupon_id = coupon_id;
	}
	public String getCode_id() {
		return code_id;
	}
	public void setCode_id(String code_id) {
		this.code_id = code_id;
	}
	public String getShop_id() {
		return shop_id;
	}
	public void setShop_id(String shop_id) {
		this.shop_id = shop_id;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getPromotion_id() {
		return promotion_id;
	}
	public void setPromotion_id(String promotion_id) {
		this.promotion_id = promotion_id;
	}
	public String getPromotion_customer_id() {
		return promotion_customer_id;
	}
	public void setPromotion_customer_id(String promotion_customer_id) {
		this.promotion_customer_id = promotion_customer_id;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getWaiting_order_id() {
		return waiting_order_id;
	}
	public void setWaiting_order_id(String waiting_order_id) {
		this.waiting_order_id = waiting_order_id;
	}
	public String getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}
	public String getOrigin_fee() {
		return origin_fee;
	}
	public void setOrigin_fee(String origin_fee) {
		this.origin_fee = origin_fee;
	}
	public String getService_fee() {
		return service_fee;
	}
	public void setService_fee(String service_fee) {
		this.service_fee = service_fee;
	}
	public String getDiscount_fee() {
		return discount_fee;
	}
	public void setDiscount_fee(String discount_fee) {
		this.discount_fee = discount_fee;
	}
	public String getNeed_fee() {
		return need_fee;
	}
	public void setNeed_fee(String need_fee) {
		this.need_fee = need_fee;
	}
	public String getPaid_fee() {
		return paid_fee;
	}
	public void setPaid_fee(String paid_fee) {
		this.paid_fee = paid_fee;
	}
	public String getDeduct_fee() {
		return deduct_fee;
	}
	public void setDeduct_fee(String deduct_fee) {
		this.deduct_fee = deduct_fee;
	}
	public String getPromotion_fee() {
		return promotion_fee;
	}
	public void setPromotion_fee(String promotion_fee) {
		this.promotion_fee = promotion_fee;
	}
	public String getCsrf_token() {
		return csrf_token;
	}
	public void setCsrf_token(String csrf_token) {
		this.csrf_token = csrf_token;
	}
	public String getGift_forms_string() {
		return gift_forms_string;
	}
	public void setGift_forms_string(String gift_forms_string) {
		this.gift_forms_string = gift_forms_string;
	}

}
