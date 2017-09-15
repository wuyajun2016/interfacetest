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
 * 基础账单.
 * @author <a href="mailto:xiaofenguser@163.com">黄晓峰</a>
 * @version $Revision$
 */
public abstract class BaseTotalPay extends Base{
	/**
	 * <code>序列ID</code>.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * <code>表名</code>.
	 */
	public static final String TABLE_NAME = "TOTALPAY";
	/**
	 * <code>发生日期对应的字段</code>.
	 */
	public static final String CURRDATE = "CURRDATE";
	/**
	 * <code>原始费用对应的字段</code>.
	 */
	public static final String SOURCEAMOUNT = "SOURCEAMOUNT";
	/**
	 * <code>折后费用对应的字段</code>.
	 */
	public static final String DISCOUNTAMOUNT = "DISCOUNTAMOUNT";
	/**
	 * <code>应付总额对应的字段</code>.
	 */
	public static final String RESULTAMOUNT = "RESULTAMOUNT";
	/**
	 * <code>实收总额对应的字段</code>.
	 */
	public static final String RECIEVEAMOUNT = "RECIEVEAMOUNT";
	/**
	 * <code>采用的打折方案对应的字段</code>.
	 */
	public static final String DISCOUNTPLANID = "DISCOUNTPLANID";
	/**
	 * <code>收银员对应的字段</code>.
	 */
	public static final String OPERATOR = "OPERATOR";
	/**
	 * <code>结账时间对应的字段</code>.
	 */
	public static final String OPERATEDATE = "OPERATEDATE";
	/**
	 * <code>会员卡ID对应的字段</code>.
	 */
	public static final String CARDID = "CARDID";
	/**
	 * <code>会员卡号对应的字段</code>.
	 */
	public static final String CARD = "CARD";
	/**
	 * <code>是否整体打折对应的字段</code>.
	 */
	public static final String ISFULLRATIO = "ISFULLRATIO";
	/**
	 * <code>折扣率对应的字段</code>.
	 */
	public static final String RATIO = "RATIO";
	/**
	 * <code>交接班状态对应的字段</code>.
	 */
	public static final String OVERSTATUS = "OVERSTATUS";
	/**
	 * <code>是否隐藏对应的字段</code>.
	 */
	public static final String ISHIDE = "ISHIDE";
	/**
	 * <code>开发票额对应的字段</code>.
	 */
	public static final String INVOICE = "INVOICE";
	/**
	 * <code>客户联打印次数对应的字段</code>.
	 */
	public static final String PRINTNUM1 = "PRINTNUM1";
	/**
	 * <code>财务联打印次数对应的字段</code>.
	 */
	public static final String PRINTNUM2 = "PRINTNUM2";
	/**
	 * <code>状态对应的字段</code>.
	 */
	public static final String STATUS = "STATUS";
	/**
	 * <code>最低消费是否打折对应的字段</code>.
	 */
	public static final String ISMINCONSUMERATIO = "ISMINCONSUMERATIO";
	/**
	 * <code>服务费是否打折对应的字段</code>.
	 */
	public static final String ISSERVICEFEERATIO = "ISSERVICEFEERATIO";
	/**
	 * <code>会员卡系统Id对应的字段</code>.
	 */
	public static final String CARDENTITYID = "CARDENTITYID";
	/**
	 * <code>操作人对应的字段</code>.
	 */
	public static final String OPUSERID = "OPUSERID";
	/**
	 * <code>外送费对应的字段</code>.
	 */
	public static final String OUTFEE = "OUTFEE";
	/**
	 * <code>发票号对应的字段</code>.
	 */
	public static final String INVOICECODE = "INVOICECODE";
	/**
	 * <code>发票抬头对应的字段</code>.
	 */
	public static final String INVOICEMEMO = "INVOICEMEMO";
	/**
	 * <code>是否需要开发票对应的字段</code>.
	 */
	public static final String ISINVOICE = "ISINVOICE";
	/**
	 * <code>代金券的优惠金额</code>.
	 */
	public static final String COUPONDISCOUNT = "COUPONDISCOUNT";

	/**
	 * <code>是否是用收银优惠</code>.
	 */
	public static final String ISUSECASHPROMOTION = "ISUSECASHPROMOTION";
	/**
	 * <code>发生日期</code>.
	 */
	private java.util.Date currDate;
	/**
	 * <code>原始费用</code>.
	 */
	private Double sourceAmount;
	/**
	 * <code>折后费用</code>.
	 */
	private Double discountAmount;
	/**
	 * <code>应付总额</code>.
	 */
	private Double resultAmount;
	/**
	 * <code>实收总额</code>.
	 */
	private Double recieveAmount;
	/**
	 * <code>采用的打折方案</code>.
	 */
	private String discountPlanId;
	/**
	 * <code>收银员</code>.
	 */
	private String operator;
	/**
	 * <code>结账时间</code>.
	 */
	private Long operateDate;
	/**
	 * <code>会员卡ID</code>.
	 */
	private String cardId;
	/**
	 * <code>会员卡号</code>.
	 */
	private String card;
	/**
	 * <code>是否整体打折</code>.
	 */
	private Short isFullRatio;
	/**
	 * <code>折扣率</code>.
	 */
	private Double ratio;
	/**
	 * <code>交接班状态</code>.
	 */
	private Short overStatus;
	/**
	 * <code>是否隐藏</code>.
	 */
	private Short isHide;
	/**
	 * <code>开发票额</code>.
	 */
	private Double invoice;
	/**
	 * <code>客户联打印次数</code>.
	 */
	private Integer printnum1;
	/**
	 * <code>财务联打印次数</code>.
	 */
	private Integer printnum2;
	/**
	 * <code>状态</code>.
	 */
	private Short status;
	/**
	 * <code>最低消费是否打折</code>.
	 */
	private Short isMinConsumeRatio;
	/**
	 * <code>服务费是否打折</code>.
	 */
	private Short isServiceFeeRatio;
	/**
	 * <code>会员卡系统Id</code>.
	 */
	private String cardEntityId;
	/**
	 * <code>操作人</code>.
	 */
	private String opUserId;
	/**
	 * <code>外送费</code>.
	 */
	private Double outFee;
	/**
	 * <code>发票号</code>.
	 */
	private String invoiceCode;
	/**
	 * <code>发票抬头</code>.
	 */
	private String invoiceMemo;

	/**
	 * <code>是否需要开发票/code>.
	 */
	private Short isInvoice;

	/**
	 * <code>代金券的优惠金额</code>.
	 */
	private Double couponDiscount;

	/**
	 * <code>是否是用收银优惠</code>.
	 */
	private Short isUseCashPromotion;

	/**
	 * 得到发生日期.
	 * @return 发生日期.
	 */
	public java.util.Date getCurrDate() {
		return currDate;
	}

	/**
	 * 设置发生日期.
	 * @param currDate 发生日期.
	 */
	public void setCurrDate(java.util.Date currDate) {
		this.currDate = currDate;
	}
	/**
	 * 得到原始费用.
	 * @return 原始费用.
	 */
	public Double getSourceAmount() {
		return sourceAmount;
	}

	/**
	 * 设置原始费用.
	 * @param sourceAmount 原始费用.
	 */
	public void setSourceAmount(Double sourceAmount) {
		this.sourceAmount = sourceAmount;
	}
	/**
	 * 得到折后费用.
	 * @return 折后费用.
	 */
	public Double getDiscountAmount() {
		return discountAmount;
	}

	/**
	 * 设置折后费用.
	 * @param discountAmount 折后费用.
	 */
	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}
	/**
	 * 得到应付总额.
	 * @return 应付总额.
	 */
	public Double getResultAmount() {
		return resultAmount;
	}

	/**
	 * 设置应付总额.
	 * @param resultAmount 应付总额.
	 */
	public void setResultAmount(Double resultAmount) {
		this.resultAmount = resultAmount;
	}
	/**
	 * 得到实收总额.
	 * @return 实收总额.
	 */
	public Double getRecieveAmount() {
		return recieveAmount;
	}

	/**
	 * 设置实收总额.
	 * @param recieveAmount 实收总额.
	 */
	public void setRecieveAmount(Double recieveAmount) {
		this.recieveAmount = recieveAmount;
	}
	/**
	 * 得到采用的打折方案.
	 * @return 采用的打折方案.
	 */
	public String getDiscountPlanId() {
		return discountPlanId;
	}

	/**
	 * 设置采用的打折方案.
	 * @param discountPlanId 采用的打折方案.
	 */
	public void setDiscountPlanId(String discountPlanId) {
		this.discountPlanId = discountPlanId;
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
	 * 得到结账时间.
	 * @return 结账时间.
	 */
	public Long getOperateDate() {
		return operateDate;
	}

	/**
	 * 设置结账时间.
	 * @param operateDate 结账时间.
	 */
	public void setOperateDate(Long operateDate) {
		this.operateDate = operateDate;
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
	 * 得到会员卡号.
	 * @return 会员卡号.
	 */
	public String getCard() {
		return card;
	}

	/**
	 * 设置会员卡号.
	 * @param card 会员卡号.
	 */
	public void setCard(String card) {
		this.card = card;
	}
	/**
	 * 得到是否整体打折.
	 * @return 是否整体打折.
	 */
	public Short getIsFullRatio() {
		return isFullRatio;
	}

	/**
	 * 设置是否整体打折.
	 * @param isFullRatio 是否整体打折.
	 */
	public void setIsFullRatio(Short isFullRatio) {
		this.isFullRatio = isFullRatio;
	}
	/**
	 * 得到折扣率.
	 * @return 折扣率.
	 */
	public Double getRatio() {
		return ratio;
	}

	/**
	 * 设置折扣率.
	 * @param ratio 折扣率.
	 */
	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}
	/**
	 * 得到交接班状态.
	 * @return 交接班状态.
	 */
	public Short getOverStatus() {
		return overStatus;
	}

	/**
	 * 设置交接班状态.
	 * @param overStatus 交接班状态.
	 */
	public void setOverStatus(Short overStatus) {
		this.overStatus = overStatus;
	}
	/**
	 * 得到是否隐藏.
	 * @return 是否隐藏.
	 */
	public Short getIsHide() {
		return isHide;
	}

	/**
	 * 设置是否隐藏.
	 * @param isHide 是否隐藏.
	 */
	public void setIsHide(Short isHide) {
		this.isHide = isHide;
	}
	/**
	 * 得到开发票额.
	 * @return 开发票额.
	 */
	public Double getInvoice() {
		return invoice;
	}

	/**
	 * 设置开发票额.
	 * @param invoice 开发票额.
	 */
	public void setInvoice(Double invoice) {
		this.invoice = invoice;
	}

	/**
	 * 得到客户联打印次数.
	 *
	 * @return 客户联打印次数.
	 */
	public Integer getPrintnum1() {
		if (printnum1 == null) {//java.lang.NullPointerException
			return 0;
		}
		return printnum1;
	}

	/**
	 * 设置客户联打印次数.
	 * @param printnum1 客户联打印次数.
	 */
	public void setPrintnum1(Integer printnum1) {
		this.printnum1 = printnum1;
	}
	/**
	 * 得到财务联打印次数.
	 * @return 财务联打印次数.
	 */
	public Integer getPrintnum2() {
		if (printnum2 == null) {//java.lang.NullPointerException
			return 0;
		}
		return printnum2;
	}

	/**
	 * 设置财务联打印次数.
	 * @param printnum2 财务联打印次数.
	 */
	public void setPrintnum2(Integer printnum2) {
		this.printnum2 = printnum2;
	}
	/**
	 * 得到状态.
	 * @return 状态.
	 */
	public Short getStatus() {
		return status;
	}

	/**
	 * 设置状态.
	 * @param status 状态.
	 */
	public void setStatus(Short status) {
		this.status = status;
	}
	/**
	 * 得到最低消费是否打折.
	 * @return 最低消费是否打折.
	 */
	public Short getIsMinConsumeRatio() {
		return isMinConsumeRatio;
	}

	/**
	 * 设置最低消费是否打折.
	 * @param isMinConsumeRatio 最低消费是否打折.
	 */
	public void setIsMinConsumeRatio(Short isMinConsumeRatio) {
		this.isMinConsumeRatio = isMinConsumeRatio;
	}
	/**
	 * 得到服务费是否打折.
	 * @return 服务费是否打折.
	 */
	public Short getIsServiceFeeRatio() {
		return isServiceFeeRatio;
	}

	/**
	 * 设置服务费是否打折.
	 * @param isServiceFeeRatio 服务费是否打折.
	 */
	public void setIsServiceFeeRatio(Short isServiceFeeRatio) {
		this.isServiceFeeRatio = isServiceFeeRatio;
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
	 * 得到外送费.
	 * @return 外送费.
	 */
	public Double getOutFee() {
		return outFee;
	}
	
	/**
	 * 设置外送费.
	 * @param outFee 外送费.
	 */
	public void setOutFee(Double outFee) {
		this.outFee = outFee;
	}

	/**
	 * 得到发票号.
	 * @return 发票号.
	 */
	public String getInvoiceCode() {
		return invoiceCode;
	}

	
	/**
	 * 设置发票号.
	 * @param invoiceCode 发票号.
	 */
	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}
	/**
	 * 得到发票抬头.
	 * @return 发票抬头.
	 */
	public String getInvoiceMemo() {
		return invoiceMemo;
	}
	
	/**
	 * 设置发票抬头.
	 * @param invoiceMemo 发票抬头.
	 */
	public void setInvoiceMemo(String invoiceMemo) {
		this.invoiceMemo = invoiceMemo;
	}

	public Short getIsInvoice() {
		return isInvoice;
	}

	public void setIsInvoice(Short isInvoice) {
		this.isInvoice = isInvoice;
	}
	/**
	 * 获取代金券的优惠金额
	 * @return
     */
	public Double getCouponDiscount() {
		return couponDiscount;
	}

	/**
	 * 设置代金券的优惠金额
	 * @param couponDiscount
     */
	public void setCouponDiscount(Double couponDiscount) {
		this.couponDiscount = couponDiscount;
	}
	/**
	 * 获取是否是用收银优惠
	 * @return
	 */
	public Short getIsUseCashPromotion() {
		return isUseCashPromotion;
	}
	/**
	 * 设置是否是用收银优惠
	 * @param isUseCashPromotion
	 */
	public void setIsUseCashPromotion(Short isUseCashPromotion) {
		this.isUseCashPromotion = isUseCashPromotion;
	}
	
	
}
