package com.dfire.testcase.function.bean;

import java.util.HashMap;
import java.util.Map;

public class CardPayBean {
		// 店铺ID
		private String entity_id;
		// 会员卡ID
		private String card_id;
		// 订单 ID
		private String order_id;
		// 预付款订单ID
		private String waiting_order_id;
		// 应付金额
		private String need_fee;
		// 原始金额
		private String origin_fee;
		// 快照ID
		private String snapshot_id;
		
		
		/**
		 * 返回 Map &lt; String, String &gt; 形式的 bean
		 * @return
		 */
		public Map<String, String> mapBean(){
			
			Map<String, String> bean = new HashMap<String, String>(); 
			bean.put("entity_id", this.entity_id);
			bean.put("card_id", this.card_id);
			bean.put("order_id", this.order_id);
			bean.put("waiting_order_id", this.waiting_order_id);		
			bean.put("origin_fee", this.origin_fee);		
			bean.put("need_fee", this.need_fee);		
			bean.put("snapshot_id", this.snapshot_id);		
			
			return bean;
		}


		public String getEntity_id() {
			return entity_id;
		}


		public void setEntity_id(String entity_id) {
			this.entity_id = entity_id;
		}


		public String getCard_id() {
			return card_id;
		}


		public void setCard_id(String card_id) {
			this.card_id = card_id;
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





		public String getSnapshot_id() {
			return snapshot_id;
		}


		public void setSnapshot_id(String snapshot_id) {
			this.snapshot_id = snapshot_id;
		}


		public String getOrigin_fee() {
			return origin_fee;
		}


		public void setOrigin_fee(String origin_fee) {
			this.origin_fee = origin_fee;
		}


		public String getNeed_fee() {
			return need_fee;
		}


		public void setNeed_fee(String need_fee) {
			this.need_fee = need_fee;
		}



		
		
}
