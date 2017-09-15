/**
 * 
 */
package com.dfire.qa.meal.vo.cash;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * 火小二/淘点点支付.
 * <pre>
 * 适用于以下几种类型.
 * 122：火小二/淘点点网上支付
 * </pre>
 * @author <a href="mailto:rain999@gmail.com">黄晓峰</a>.
 * @version $Revision$.
 */
public class PayOrder implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	private static final List<Integer> TYPE = Arrays.asList(CloudConstants.Type.PO_NET_PAY);
	/**
	 * 类型:会员卡，现金，银行卡.
	 * 必须.
	 */
	private Short type;
	/**
	 * 订单ID.
	 * 必须.
	 */
	private String orderId;
	/**
	 * 请求人ID.
	 * 必须.
	 */
	private String customerRegisterId;

	/**
	 * 支付金额.
	 * 必须.
	 */
	private Double fee;
	/**
	 * 会员卡ID.
	 * 刷卡时用，非必须.
	 */
	private String cardId;
	/**
	 * 会员卡实体.
	 * 刷卡时用，非必须.
	 */
	private String cardEntityId;
	/**
	 * 与前系统兼容用.
	 * 新系统可为空.
	 */
	private String waitingPayId;

	/**
	 * 银行卡卡号.
	 * 刷银行卡时用，非必须.
	 */
	private String code;
	/**
	 * 备用，暂时不用.
	 */
	private String memo;

	/**
	 * 账单金额.
	 * 必须.
	 */
	private Double pay;
	
	public Double getFee() {
		return fee;
	}
	public void setFee(Double fee) {
		this.fee = fee;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Short getType() {
		return type;
	}
	public void setType(Short type) {
		this.type = type;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getCardId() {
		return cardId;
	}
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	public String getCardEntityId() {
		return cardEntityId;
	}
	public void setCardEntityId(String cardEntityId) {
		this.cardEntityId = cardEntityId;
	}
	public String getCustomerRegisterId() {
		return customerRegisterId;
	}
	public void setCustomerRegisterId(String customerRegisterId) {
		this.customerRegisterId = customerRegisterId;
	}
	public String getWaitingPayId() {
		return waitingPayId;
	}
	public void setWaitingPayId(String waitingPayId) {
		this.waitingPayId = waitingPayId;
	}

	public void setPay(Double pay) {
		this.pay = pay;
	}

	public Double getPay() {
		return pay;
	}


}
