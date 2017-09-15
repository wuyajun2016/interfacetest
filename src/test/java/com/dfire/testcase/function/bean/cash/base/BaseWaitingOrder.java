/************************* 版权声明 **********************************
 * 版权所有：Copyright (c) 2011, ${year} 黄晓峰
 *
 * 工程名称：	钻木餐饮系统
 * 创建者： 黄晓峰 创建日期： ${date}
 * 创建记录：	创建类结构。
 *
 **/

package com.dfire.testcase.function.bean.cash.base;


/**
 * 基础待下单数据.
 * @author <a href="mailto:xiaofenguser@163.com">黄晓峰</a>
 * @version $Revision$
 */
public abstract class BaseWaitingOrder extends Base{
	/**
	 * <code>序列ID</code>.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * <code>订单来源</code>.
	 */
	private Short orderFrom;
	/**
	 * <code>订单类型</code>.
	 */
	private Short kind;
	/**
	 * <code>淘宝订单号</code>.
	 */
	private String code;
	/**
	 * <code>桌号</code>.
	 */
	private String seatCode;
	/**
	 * <code>人数</code>.
	 */
	private Integer peopleCount;
	/**
	 * <code>手机号码</code>.
	 */
	private String mobile;
	/**
	 * <code>联系方式</code>.
	 */
	private String tel;
	/**
	 * <code>联系人</code>.
	 */
	private String name;
	/**
	 * <code>预定日期</code>.
	 */
	private Long reserveDate;
	/**
	 * <code>订单总价</code>.
	 */
	private Double totalPrice;
	/**
	 * <code>实际总价</code>.
	 */
	private Double realPrice;
	/**
	 * <code>说明</code>.
	 */
	private String memo;
	/**
	 * <code>订单ID</code>.
	 */
	private String orderId;
	/**
	 * <code>商户名称</code>.
	 */
	private String shopName;
	/**
	 * <code>送货地址</code>.
	 */
	private String address;
	/**
	 * <code>外送费</code>.
	 */
	private Double outFee;
	/**
	 * <code>支付方式</code>.
	 */
	private Short payMode;
	/**
	 * <code>预付金额</code>.
	 */
	private Double advancePay;
	/**
	 * <code>支付状态</code>.
	 */
	private Short payStatus;
	/**
	 * <code>座位类型ID</code>.
	 */
	private String reserveSeatId;
	/**
	 * <code>时段ID</code>.
	 */
	private String reserveTimeId;
	/**
	 * <code>送货人</code>.
	 */
	private String sender;
	/**
	 * <code>送货人ID</code>.
	 */
	private String senderId;
	/**
	 * <code>状态</code>.
	 */
	private Short status;
	/**
	 * <code>审核状态</code>.
	 */
	private Short auditStatus;
	/**
	 * <code>错误信息</code>.
	 */
	private String errorMessage;
	/**
	 * <code>客户ID</code>.
	 */
	private String customerRegisterId;
	/**
	 * <code>预付订桌金额</code>.
	 */
	private Double advanceSeatPay;
	/**
	 * <code>批次信息</code>.
	 */
	private String batchMsg;
	
	/**
	 * 得到淘宝订单号.
	 * @return 淘宝订单号.
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 设置淘宝订单号.
	 * @param code 淘宝订单号.
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 得到桌号.
	 * @return 桌号.
	 */
	public String getSeatCode() {
		return seatCode;
	}

	/**
	 * 设置桌号.
	 * @param seatCode 桌号.
	 */
	public void setSeatCode(String seatCode) {
		this.seatCode = seatCode;
	}
	/**
	 * 得到人数.
	 * @return 人数.
	 */
	public Integer getPeopleCount() {
		return peopleCount;
	}

	/**
	 * 设置人数.
	 * @param peopleCount 人数.
	 */
	public void setPeopleCount(Integer peopleCount) {
		this.peopleCount = peopleCount;
	}
	
	
	/**
	 * 得到备注.
	 * @return 备注.
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * 设置备注.
	 * @param memo 备注.
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * 得到手机号码.
	 * @return 手机号码.
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * 设置手机号码.
	 * @param mobile 手机号码.
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * 得到订单ID.
	 * @return 订单ID.
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * 设置订单ID.
	 * @param orderId 订单ID.
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	/**
	 * 得到处理状态.
	 * @return 处理状态.
	 */
	public Short getStatus() {
		return status;
	}

	/**
	 * 设置处理状态.
	 * @param status 处理状态.
	 */
	public void setStatus(Short status) {
		this.status = status;
	}
	/**
	 * 得到错误信息.
	 * @return 错误信息.
	 */
	public String getErrorMsg() {
		return errorMessage;
	}

	/**
	 * 设置错误信息.
	 * @param errorMessage 错误信息.
	 */
	public void setErrorMsg(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Short getOrderKind() {
		return kind;
	}

	public void setOrderKind(Short kind) {
		this.kind = kind;
	}

	/**
	 * 得到订单来源.
	 * @return 订单来源.
	 */
	public Short getOrderFrom() {
		return orderFrom;
	}

	/**
	 * 设置订单来源.
	 * @param orderFrom 订单来源.
	 */
	public void setOrderFrom(Short orderFrom) {
		this.orderFrom = orderFrom;
	}

	/**
	 * 得到姓名.
	 * @return 姓名.
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置姓名.
	 * @param name 姓名.
	 */
	public void setName(String name) {
		this.name = name;
	}

	public Short getKind() {
		return kind;
	}

	public void setKind(Short kind) {
		this.kind = kind;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Long getReserveDate() {
		return reserveDate;
	}

	public void setReserveDate(Long reserveDate) {
		this.reserveDate = reserveDate;
	}

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Double getRealPrice() {
		return realPrice;
	}

	public void setRealPrice(Double realPrice) {
		this.realPrice = realPrice;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Double getOutFee() {
		return outFee;
	}

	public void setOutFee(Double outFee) {
		this.outFee = outFee;
	}

	public Short getPayMode() {
		return payMode;
	}

	public void setPayMode(Short payMode) {
		this.payMode = payMode;
	}

	public Double getAdvancePay() {
		return advancePay;
	}

	public void setAdvancePay(Double advancePay) {
		this.advancePay = advancePay;
	}

	public Short getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Short payStatus) {
		this.payStatus = payStatus;
	}

	public String getReserveSeatId() {
		return reserveSeatId;
	}

	public void setReserveSeatId(String reserveSeatId) {
		this.reserveSeatId = reserveSeatId;
	}

	public String getReserveTimeId() {
		return reserveTimeId;
	}

	public void setReserveTimeId(String reserveTimeId) {
		this.reserveTimeId = reserveTimeId;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public Short getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Short auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getCustomerRegisterId() {
		return customerRegisterId;
	}

	public void setCustomerRegisterId(String customerRegisterId) {
		this.customerRegisterId = customerRegisterId;
	}

	public Double getAdvanceSeatPay() {
		return advanceSeatPay;
	}

	public void setAdvanceSeatPay(Double advanceSeatPay) {
		this.advanceSeatPay = advanceSeatPay;
	}
	/**
	 * 得到批次信息.
	 * @return 批次信息.
	 */
	public String getBatchMsg() {
		return batchMsg;
	}

	/**
	 * 设置批次信息.
	 * @param batchMsg 批次信息.
	 */
	public void setBatchMsg(String batchMsg) {
		this.batchMsg = batchMsg;
	}


}
