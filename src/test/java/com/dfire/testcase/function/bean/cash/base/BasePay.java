/************************* 版权声明 **********************************
 * 版权所有：Copyright (c) 2011, ${year} 黄晓峰
 *
 * 工程名称：	钻木餐饮系统
 * 创建者： 黄晓峰 创建日期： ${date}
 * 创建记录：	创建类结构。
 *
 **/

package com.dfire.testcase.function.bean.cash.base;

import java.awt.Cursor;

/**
 * 基础付款信息.
 * @author <a href="mailto:xiaofenguser@163.com">黄晓峰</a>
 * @version $Revision$
 */
public abstract class BasePay extends Base{
	/**
	 * <code>序列ID</code>.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * <code>表名</code>.
	 */
	public static final String TABLE_NAME = "PAY";
	/**
	 * <code>付款类型对应的字段</code>.
	 */
	public static final String KINDPAYID = "KINDPAYID";	
	/**
	 * <code>在线支付ID</code>.
	 */
	public static final String ONLINEBILLID = "ONLINEBILLID";
	/**
	 * <code>实收额对应的字段</code>.
	 */
	public static final String FEE = "FEE";
	/**
	 * <code>收银员对应的字段</code>.
	 */
	public static final String OPERATOR = "OPERATOR";
	/**
	 * <code>收银时间对应的字段</code>.
	 */
	public static final String PAYTIME = "PAYTIME";
	/**
	 * <code>现收金额对应的字段</code>.
	 */
	public static final String PAY = "PAY";
	/**
	 * <code>找零对应的字段</code>.
	 */
	public static final String CHARGE = "CHARGE";
	/**
	 * <code>相关付款信息对应的字段</code>.
	 */
	public static final String TOTALPAYID = "TOTALPAYID";
	/**
	 * <code>会员卡ID对应的字段</code>.
	 */
	public static final String CARDID = "CARDID";
	/**
	 * <code>处理标识(对挂账收款的支持)对应的字段</code>.
	 */
	public static final String ISDEALED = "ISDEALED";
	/**
	 * <code>会员卡系统Id对应的字段</code>.
	 */
	public static final String CARDENTITYID = "CARDENTITYID";
	/**
	 * <code>操作人对应的字段</code>.
	 */
	public static final String OPUSERID = "OPUSERID"; 
	/**
	 * <code>网上支付类型对应的字段</code>.
	 */
	public static final String TYPE = "TYPE";
	/**
	 * <code>网上支付相关外部流水对应的字段</code>.
	 */
	public static final String CODE = "CODE";
	/**
	 * <code>网上支付相关Id对应的字段</code>.
	 */
	public static final String WAITINGPAYID = "WAITINGPAYID";
	
	/**
	 * <code>优惠券支付时记录优惠券的名字</code>.
	 */
	public static final String TYPENAME = "TYPENAME";
	/**
	 *<code>代金券面额</code>.
	 */
	public static final String COUPONFEE = "COUPONFEE";
	/**
	 *<code>代金券实际售价额</code>.
	 */
	public static final String COUPONCOST = "COUPONCOST";
	/**
	 *<code>代金券张数</code>.
	 */
	public static final String COUPONNUM = "COUPONNUM";
	/**
	 * <code>付款类型</code>.
	 */
	private String kindPayId;	
	/**
	 * <code>在线支付ID</code>.
	 */
	private String onlineBillId;
	/**
	 * <code>实收额</code>.
	 */
	private Double fee;
	/**
	 * <code>收银员</code>.
	 */
	private String operator;
	/**
	 * <code>收银时间</code>.
	 */
	private Long payTime;
	/**
	 * <code>现收金额</code>.
	 */
	private Double pay;
	/**
	 * <code>找零</code>.
	 */
	private Double charge;
	/**
	 * <code>相关付款信息</code>.
	 */
	private String totalPayId;
	/**
	 * <code>会员卡ID</code>.
	 */
	private String cardId;
	/**
	 * <code>处理标识(对挂账收款的支持)</code>.
	 */
	private Short isDealed;
	/**
	 * <code>会员卡系统Id</code>.
	 */
	private String cardEntityId;
	/**
	 * <code>操作人</code>.
	 */
	private String opUserId;

	/**
	 * <code>网上支付类型</code>.
	 */
	private int type;
	/**
	 * <code>网上支付相关外部流水</code>.
	 */
	private String code;
	/**
	 * <code>网上支付相关Id</code>.
	 */
	private String waitingPayId;
	
	/**
	 * <code>优惠券名字</code>.
	 */
	private String typeName;
	/**
	 *code>第三方代金券面额</code>.
	 */
	public Double couponFee ;
	/**
	 *<code>第三方代金券实际售价额</code>.
	 */
	public Double couponCost ;
	/**
	 *<code>第三方代金券张数</code>.
	 */
	public Integer couponNum;
	/**
	 * 得到付款类型.
	 * @return 付款类型.
	 */
	public String getKindPayId() {
		return kindPayId;
	}

	/**
	 * 设置付款类型.
	 * @param kindPayId 付款类型.
	 */
	public void setKindPayId(String kindPayId) {
		this.kindPayId = kindPayId;
	}
	
	public String getOnlineBillId() {
		return onlineBillId;
	}

	public void setOnlineBillId(String onlineBillId) {
		this.onlineBillId = onlineBillId;
	}

	/**
	 * 得到实收额.
	 * @return 实收额.
	 */
	public Double getFee() {
		return fee;
	}

	/**
	 * 设置实收额.
	 * @param fee 实收额.
	 */
	public void setFee(Double fee) {
		this.fee = fee;
	}
	/**
	 * 得到收银员.
	 * @return 收银员.
	 */
	public String getOperator() {
		return operator;
	}

	/**
	 * 设置收银员.
	 * @param operator 收银员.
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}
	/**
	 * 得到收银时间.
	 * @return 收银时间.
	 */
	public Long getPayTime() {
		return payTime;
	}

	/**
	 * 设置收银时间.
	 * @param payTime 收银时间.
	 */
	public void setPayTime(Long payTime) {
		this.payTime = payTime;
	}
	/**
	 * 得到现收金额.
	 * @return 现收金额.
	 */
	public Double getPay() {
		return pay;
	}

	/**
	 * 设置现收金额.
	 * @param pay 现收金额.
	 */
	public void setPay(Double pay) {
		this.pay = pay;
	}
	/**
	 * 得到找零.
	 * @return 找零.
	 */
	public Double getCharge() {
		return charge;
	}

	/**
	 * 设置找零.
	 * @param charge 找零.
	 */
	public void setCharge(Double charge) {
		this.charge = charge;
	}
	/**
	 * 得到相关付款信息.
	 * @return 相关付款信息.
	 */
	public String getTotalPayId() {
		return totalPayId;
	}

	/**
	 * 设置相关付款信息.
	 * @param totalPayId 相关付款信息.
	 */
	public void setTotalPayId(String totalPayId) {
		this.totalPayId = totalPayId;
	}
	/**
	 * 得到会员卡ID.
	 * @return 会员卡ID.
	 */
	public String getCardId() {
		return cardId;
	}

	/**
	 * 设置会员卡ID.
	 * @param cardId 会员卡ID.
	 */
	public void setCardId(String cardId) {
		this.cardId = cardId;
	}
	/**
	 * 得到处理标识(对挂账收款的支持).
	 * @return 处理标识(对挂账收款的支持).
	 */
	public Short getIsDealed() {
		return isDealed;
	}

	/**
	 * 设置处理标识(对挂账收款的支持).
	 * @param isDealed 处理标识(对挂账收款的支持).
	 */
	public void setIsDealed(Short isDealed) {
		this.isDealed = isDealed;
	}
	/**
	 * 得到会员卡系统Id.
	 * @return 会员卡系统Id.
	 */
	public String getCardEntityId() {
		return cardEntityId;
	}

	/**
	 * 设置会员卡系统Id.
	 * @param cardEntityId 会员卡系统Id.
	 */
	public void setCardEntityId(String cardEntityId) {
		this.cardEntityId = cardEntityId;
	}
	/**
	 * 得到操作人.
	 * @return 操作人.
	 */
	public String getOpUserId() {
		return opUserId;
	}

	/**
	 * 设置操作人.
	 * @param opUserId 操作人.
	 */
	public void setOpUserId(String opUserId) {
		this.opUserId = opUserId;
	}
	
	/**
	 * 得到网上支付类型.
	 * @return 网上支付类型.
	 */
	public int getType() {
		return type;
	}
	/**
	 * 设置网上支付类型.
	 * @param type 网上支付类型.
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * 得到网上支付相关外部流水.
	 * @return 网上支付相关外部流水.
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置网上支付相关外部流水.
	 * @param code 网上支付相关外部流水.
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 得到网上支付相关Id.
	 * @return 网上支付相关Id.
	 */
	public String getWaitingPayId() {
		return waitingPayId;
	}
	/**
	 * 设置网上支付相关Id.
	 * @param waitingPayId 网上支付相关Id.
	 */
	public void setWaitingPayId(String waitingPayId) {
		this.waitingPayId = waitingPayId;
	}
	
	/**
	 * 得到优惠券名字
	 * @return
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * 设置优惠券名字
	 * @param typeName
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * 获取代金券张数
	 * @return
     */
	public Integer getCouponNum() {
		return couponNum;
	}

	/**
	 * 设置代金券张数
	 * @param couponNum
     */
	public void setCouponNum(Integer couponNum) {
		this.couponNum = couponNum;
	}
	/**
	 * 获取代金券实际售价额
	 * @return
	 */
	public Double getCouponCost() {
		return couponCost;
	}
	/**
	 * 设置代金券实际售价额
	 * @return
	 */
	public void setCouponCost(Double couponCost) {
		this.couponCost = couponCost;
	}
	/**
	 * 获取代金券面额
	 * @return
	 */
	public Double getCouponFee() {
		return couponFee;
	}
	/**
	 * 设置代金券面额
	 * @return
	 */
	public void setCouponFee(Double couponFee) {
		this.couponFee = couponFee;
	}
}
