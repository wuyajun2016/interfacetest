package com.dfire.qa.meal.vo.pay;

public class TradeBill {
	
	// 购物车
	private String cart_forms_string;
	// 店铺ID
	private String entity_id;
	// 桌位ID
	private String seat_code;
	
	
	// 订单 ID
	private String order_id;
	// 预付款订单ID
	private String waiting_order_id;
	// 会员卡 ID
	private String card_id;
	
	
	// 红包 ID
	private String coupon_id;
	// 红包 code
	private String code_id;
	// 优惠平台优惠ID
	private String promotion_id;
	
	
	
	// 优惠平台优惠券用户ID
	private String promotion_customer_id;
	// 签名
	private String sign;
	// 是否重新拉取账单
	private String flag;
	// 是否选中赞助礼品
	private String gift_forms_string;

	// 是否选中赞助礼品 字段修改了 
	private String selected;

	public String getCart_forms_string() {
		return cart_forms_string;
	}

	public void setCart_forms_string(String cart_forms_string) {
		this.cart_forms_string = cart_forms_string;
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

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getGift_forms_string() {
		return gift_forms_string;
	}

	public void setGift_forms_string(String gift_forms_string) {
		this.gift_forms_string = gift_forms_string;
	}

	public String getSelected() {
		return selected;
	}

	public void setSelected(String selected) {
		this.selected = selected;
	}
	
	

}
