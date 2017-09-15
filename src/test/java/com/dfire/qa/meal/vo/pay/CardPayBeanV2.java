package com.dfire.qa.meal.vo.pay;

public class CardPayBeanV2 {
		
	// 店铺ID
	private String entity_id;
	
	// 会员卡ID
	private String card_id;
	
	// 订单 ID
	private String order_id;
	
	// 预付款订单ID
	private String waiting_order_id;
	
	// 应付金额
	private Integer need_fee;
	
	// 原始金额
	private Integer origin_fee;
	
	// 快照ID
	private String snapshot_id;
	
	

	public String getSnapshot_id() {
		return snapshot_id;
	}

	public void setSnapshot_id(String snapshot_id) {
		this.snapshot_id = snapshot_id;
	}

	public Integer getOrigin_fee() {
		return origin_fee;
	}

	public void setOrigin_fee(Integer origin_fee) {
		this.origin_fee = origin_fee;
	}

	public Integer getNeed_fee() {
		return need_fee;
	}

	public void setNeed_fee(Integer need_fee) {
		this.need_fee = need_fee;
	}

	public String getWaiting_order_id() {
		return waiting_order_id;
	}

	public void setWaiting_order_id(String waiting_order_id) {
		this.waiting_order_id = waiting_order_id;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getCard_id() {
		return card_id;
	}

	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}

	public String getEntity_id() {
		return entity_id;
	}

	public void setEntity_id(String entity_id) {
		this.entity_id = entity_id;
	}
	
			

}
