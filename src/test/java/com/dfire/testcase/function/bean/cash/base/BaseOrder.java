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
 * 基础订单.
 * @author <a href="mailto:xiaofenguser@163.com">黄晓峰</a>
 * @version $Revision$
 */
public abstract class BaseOrder extends Base{
	/**
	 * <code>序列ID</code>.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * <code>表名</code>.
	 */
	public static final String TABLE_NAME = "ORDERINFO";
	/**
	 * <code>开单时间对应的字段</code>.
	 */
	public static final String OPENTIME = "OPENTIME";
	/**
	 * <code>单号对应的字段</code>.
	 */
	public static final String CODE = "CODE";
	/**
	 * <code>菜谱ID对应的字段</code>.
	 */
	public static final String BOOKID = "BOOKID";
	/**
	 * <code>关联预订ID对应的字段</code>.
	 */
	public static final String RESERVEID = "RESERVEID";
	/**
	 * <code>座位ID对应的字段</code>.
	 */
	public static final String SEATID = "SEATID";
	/**
	 * <code>账单ID对应的字段</code>.
	 */
	public static final String TOTALPAYID = "TOTALPAYID";
	/**
	 * <code>服务员ID对应的字段</code>.
	 */
	public static final String WORKERID = "WORKERID";
	/**
	 * <code>结单时间对应的字段</code>.
	 */
	public static final String ENDTIME = "ENDTIME";
	/**
	 * <code>就餐人数对应的字段</code>.
	 */
	public static final String PEOPLECOUNT = "PEOPLECOUNT";
	/**
	 * <code>开单类型对应的字段</code>.
	 */
	public static final String ORDERKIND = "ORDERKIND";
	/**
	 * <code>当前状态对应的字段</code>.
	 */
	public static final String STATUS = "STATUS";
	/**
	 * <code>附加费用方案ID对应的字段</code>.
	 */
	public static final String FEEPLANID = "FEEPLANID";
	/**
	 * <code>是否进行厨打对应的字段</code>.
	 */
	public static final String ISPRINT = "ISPRINT";
	/**
	 * <code>待叫标志对应的字段</code>.
	 */
	public static final String ISWAIT = "ISWAIT";
	/**
	 * <code>整单说明对应的字段</code>.
	 */
	public static final String MEMO = "MEMO";
	/**
	 * <code>是否隐藏对应的字段</code>.
	 */
	public static final String ISHIDE = "ISHIDE";
	/**
	 * <code>营业日期对应的字段</code>.
	 */
	public static final String CURRDATE = "CURRDATE";
	/**
	 * <code>内单号(用于跟踪)对应的字段</code>.
	 */
	public static final String INNERCODE = "INNERCODE";
	/**
	 * <code>菜肴分时段Id对应的字段</code>.
	 */
	public static final String MENUTIMEID = "MENUTIMEID";
	/**
	 * <code>操作人对应的字段</code>.
	 */
	public static final String OPUSERID = "OPUSERID";
	/**
	 * <code>区域ID对应的字段</code>.
	 */
	public static final String AREAID = "AREAID";
	/**
	 * <code>外卖联系人字段</code>.
	 */
	public static final String NAME = "NAME";
	/**
	 * <code>手机字段</code>.
	 */
	public static final String MOBILE = "MOBILE";
	/**
	 * <code>联系电话字段</code>.
	 */
	public static final String TEL = "TEL";
	/**
	 * <code>是否预约外送时间字段</code>.
	 */
	public static final String ISAUTOCOMMIT = "ISAUTOCOMMIT";
	/**
	 * <code>外送时间字段</code>.
	 */
	public static final String SENDTIME  = "SENDTIME";
	/**
	 * <code>外送地址字段</code>.
	 */
	public static final String ADDRESS  = "ADDRESS";
	/**
	 * <code>支付方式字段</code>.
	 */
	public static final String PAYMODE  = "PAYMODE";
	/**
	 * <code>外送费字段</code>.
	 */
	public static final String OUTFEE  = "OUTFEE";
	/**
	 * <code>送货人ID字段</code>.
	 */
	public static final String SENDERID  = "SENDERID";
	/**
	 * <code>客户ID字段</code>.
	 */
	public static final String CUSTOMERREGISTERID  = "CUSTOMERREGISTERID";
	/**
	 * <code>外卖来源单ID字段</code>.
	 */
	public static final String WAITINGORDERID  = "WAITINGORDERID";
	/**
	 * <code>送货状态字段</code>.
	 */
	public static final String SENDSTATUS  = "SENDSTATUS";
	/**
	 * <code>订单来源对应的字段</code>.
	 */
	public static final String ORDERFROM = "ORDERFROM";
	/**
	 * <code>时段Id对应的字段</code>.
	 */
	public static final String RESERVETIMEID = "RESERVETIMEID";
	/**
	 * <code>串码对应的字段</code>.
	 */
	public static final String GLOBALCODE = "GLOBALCODE";
	/**
	 * <code>短串码对应的字段</code>.
	 */
	public static final String SIMPLECODE = "SIMPLECODE";
	/**
	 * <code>分单时候原始单id对应的字段</code>.
	 */
	public static final String ORIGNID = "ORIGNID";
	/**
	 * <code>餐标对应的字段</code>.
	 */
	public static final String SEATMARK="SEATMARK";
	/**
	 * <code>是否限时用餐对应的字段</code>.
	 */
	public static final String ISLIMITTIME="ISLIMITTIME";
	/**
	 * <code>扫码地址对应的字段</code>.
	 */
	public static final String SCANURL="SCANURL";
	/**
	 * <code>配送员姓名对应的字段</code>.
	 */
	public static final String SENDERNAME="SENDERNAME";
	/**
	 * <code>配送员电话方式对应的字段</code>.
	 */
	public static final String SENDERMOBILE="SENDERMOBILE";
	/**
	 * <code>是否第三方支付对应的字段</code>.
	 */
	public static final String ISTHIRDSHIPPING="ISTHIRDSHIPPING";
	
	/**
	 * <code>开单时间</code>.
	 */
	private Long openTime;
	/**
	 * <code>单号</code>.
	 */
	private Integer code;
	/**
	 * <code>菜谱ID</code>.
	 */
	private String bookId;
	/**
	 * <code>关联预订ID</code>.
	 */
	private String reserveId;
	/**
	 * <code>座位ID</code>.
	 */
	private String seatId;
	/**
	 * <code>账单ID</code>.
	 */
	private String totalpayId;
	/**
	 * <code>服务员ID</code>.
	 */
	private String workerId;
	/**
	 * <code>结单时间</code>.
	 */
	private Long endTime;
	/**
	 * <code>就餐人数</code>.
	 */
	private Integer peopleCount;
	/**
	 * <code>开单类型</code>.
	 */
	private Short orderKind;
	/**
	 * <code>当前状态</code>.
	 */
	private Short status;
	/**
	 * <code>附加费用方案ID</code>.
	 */
	private String feePlanId;
	/**
	 * <code>是否进行厨打</code>.
	 */
	private Short isPrint;
	/**
	 * <code>待叫标志</code>.
	 */
	private Short isWait;
	/**
	 * <code>整单说明</code>.
	 */
	private String memo;
	/**
	 * <code>是否隐藏</code>.
	 */
	private Short isHide;
	/**
	 * <code>营业日期</code>.
	 */
	private java.util.Date currDate;
	/**
	 * <code>内单号(用于跟踪)</code>.
	 */
	private String innerCode;
	/**
	 * <code>菜肴分时段Id</code>.
	 */
	private String menuTimeId;
	/**
	 * <code>操作人</code>.
	 */
	private String opUserId;
	/**
	 * <code>区域ID</code>.
	 */
	private String areaId;
	/**
	 * <code>外卖联系人</code>.
	 */
	private String name;
	/**
	 * <code>手机</code>.
	 */
	private String mobile;
	/**
	 * <code>电话</code>.
	 */
	private String tel;
	/**
	 * <code>是否预约外送时间</code>.
	 */
	private Short IsAutoCommit;
	/**
	 * <code>外送时间</code>.
	 */
	private Long sendTime;
	/**
	 * <code>外送地址</code>.
	 */
	private String address;
	/**
	 * <code>支付方式</code>.
	 */
	private Short payMode;
	/**
	 * <code>外送费</code>.
	 */
	private Double outFee;
	/**
	 * <code>送货人ID</code>.
	 */
	private String senderId;
	/**
	 * <code>客户ID</code>.
	 */
	private String customerRegisterId;
	/**
	 * <code>外卖来源单ID</code>.
	 */
	private String waitingOrderId;
	/**
	 * <code>送货状态</code>.
	 */
	private Short sendStatus;
	/**
	 * <code>订单来源</code>.
	 */
	private Short orderFrom;
	/**
	 * <code>时段Id</code>.
	 */
	private String reserveTimeId;
	/**
	 * <code>串码对应的字段</code>.
	 */
	private String globalCode;
	/**
	 * <code>串码对应的字段</code>.
	 */
	private String simpleCode;
	/**
	 * <code>分单时原始单ID对应的字段</code>.
	 */
	private String orignId;
	
	/**
	 * 餐标对应的字段
	 */
	private String seatMark;
	
	/**
	 * 是否限时用餐对应的字段
	 */
	private Short isLimitTime;
	
	/**
	 * 扫码地址
	 */
	private String scanUrl;
	
	/**
	 * 会员卡id
	 */
	private String relatedCardId;
	/**
	 * 预充值金额
	 */
	private Double prePay;
	/**
	 * 配送员姓名
	 */
	private String senderName;
	/**
	 * 配送员电话
	 */
	private String senderMobile;
	/**
     * 是否第三方配送
     */
    private Short isThirdShipping;
    
	/**
	 * 得到开单时间.
	 * @return 开单时间.
	 */
	public Long getOpenTime() {
		return openTime;
	}

	/**
	 * 设置开单时间.
	 * @param openTime 开单时间.
	 */
	public void setOpenTime(Long openTime) {
		this.openTime = openTime;
	}
	/**
	 * 得到单号.
	 * @return 单号.
	 */
	public Integer getCode() {
		return code;
	}

	/**
	 * 设置单号.
	 * @param code 单号.
	 */
	public void setCode(Integer code) {
		this.code = code;
	}
	/**
	 * 得到菜谱ID.
	 * @return 菜谱ID.
	 */
	public String getBookId() {
		return bookId;
	}

	/**
	 * 设置菜谱ID.
	 * @param bookId 菜谱ID.
	 */
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	/**
	 * 得到关联预订ID.
	 * @return 关联预订ID.
	 */
	public String getReserveId() {
		return reserveId;
	}

	/**
	 * 设置关联预订ID.
	 * @param reserveId 关联预订ID.
	 */
	public void setReserveId(String reserveId) {
		this.reserveId = reserveId;
	}
	/**
	 * 得到座位ID.
	 * @return 座位ID.
	 */
	public String getSeatId() {
		return seatId;
	}

	/**
	 * 设置座位ID.
	 * @param seatId 座位ID.
	 */
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	/**
	 * 得到账单ID.
	 * @return 账单ID.
	 */
	public String getTotalpayId() {
		return totalpayId;
	}

	/**
	 * 设置账单ID.
	 * @param totalpayId 账单ID.
	 */
	public void setTotalpayId(String totalpayId) {
		this.totalpayId = totalpayId;
	}
	/**
	 * 得到服务员ID.
	 * @return 服务员ID.
	 */
	public String getWorkerId() {
		return workerId;
	}

	/**
	 * 设置服务员ID.
	 * @param workerId 服务员ID.
	 */
	public void setWorkerId(String workerId) {
		this.workerId = workerId;
	}
	/**
	 * 得到结单时间.
	 * @return 结单时间.
	 */
	public Long getEndTime() {
		return endTime;
	}

	/**
	 * 设置结单时间.
	 * @param endTime 结单时间.
	 */
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
	/**
	 * 得到就餐人数.
	 * @return 就餐人数.
	 */
	public Integer getPeopleCount() {
		return peopleCount;
	}

	/**
	 * 设置就餐人数.
	 * @param peopleCount 就餐人数.
	 */
	public void setPeopleCount(Integer peopleCount) {
		this.peopleCount = peopleCount;
	}
	/**
	 * 得到开单类型.
	 * @return 开单类型.
	 */
	public Short getOrderKind() {
		return orderKind;
	}

	/**
	 * 设置开单类型.
	 * @param orderKind 开单类型.
	 */
	public void setOrderKind(Short orderKind) {
		this.orderKind = orderKind;
	}
	/**
	 * 得到当前状态.
	 * @return 当前状态.
	 */
	public Short getStatus() {
		return status;
	}

	/**
	 * 设置当前状态.
	 * @param status 当前状态.
	 */
	public void setStatus(Short status) {
		this.status = status;
	}
	/**
	 * 得到附加费用方案ID.
	 * @return 附加费用方案ID.
	 */
	public String getFeePlanId() {
		return feePlanId;
	}

	/**
	 * 设置附加费用方案ID.
	 * @param feePlanId 附加费用方案ID.
	 */
	public void setFeePlanId(String feePlanId) {
		this.feePlanId = feePlanId;
	}
	/**
	 * 得到是否进行厨打.
	 * @return 是否进行厨打.
	 */
	public Short getIsPrint() {
		return isPrint;
	}

	/**
	 * 设置是否进行厨打.
	 * @param isPrint 是否进行厨打.
	 */
	public void setIsPrint(Short isPrint) {
		this.isPrint = isPrint;
	}
	/**
	 * 得到待叫标志.
	 * @return 待叫标志.
	 */
	public Short getIsWait() {
		return isWait;
	}

	/**
	 * 设置待叫标志.
	 * @param isWait 待叫标志.
	 */
	public void setIsWait(Short isWait) {
		this.isWait = isWait;
	}
	/**
	 * 得到整单说明.
	 * @return 整单说明.
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * 设置整单说明.
	 * @param memo 整单说明.
	 */
	public void setMemo(String memo) {
		this.memo = memo;
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
	 * 得到营业日期.
	 * @return 营业日期.
	 */
	public java.util.Date getCurrDate() {
		return currDate;
	}

	/**
	 * 设置营业日期.
	 * @param currDate 营业日期.
	 */
	public void setCurrDate(java.util.Date currDate) {
		this.currDate = currDate;
	}
	/**
	 * 得到内单号(用于跟踪).
	 * @return 内单号(用于跟踪).
	 */
	public String getInnerCode() {
		return innerCode;
	}

	/**
	 * 设置内单号(用于跟踪).
	 * @param innerCode 内单号(用于跟踪).
	 */
	public void setInnerCode(String innerCode) {
		this.innerCode = innerCode;
	}
	/**
	 * 得到菜肴分时段Id.
	 * @return 菜肴分时段Id.
	 */
	public String getMenuTimeId() {
		return menuTimeId;
	}

	/**
	 * 设置菜肴分时段Id.
	 * @param menuTimeId 菜肴分时段Id.
	 */
	public void setMenuTimeId(String menuTimeId) {
		this.menuTimeId = menuTimeId;
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
	 * 得到区域ID.
	 * @return 区域ID.
	 */
	public String getAreaId() {
		return areaId;
	}

	/**
	 * 设置区域ID.
	 * @param areaId 区域ID.
	 */
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public Short getIsAutoCommit() {
		return IsAutoCommit;
	}

	public void setIsAutoCommit(Short isAutoCommit) {
		IsAutoCommit = isAutoCommit;
	}

	public Long getSendTime() {
		return sendTime;
	}

	public void setSendTime(Long sendTime) {
		this.sendTime = sendTime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Short getPayMode() {
		return payMode;
	}

	public void setPayMode(Short payMode) {
		this.payMode = payMode;
	}

	public Double getOutFee() {
		return outFee;
	}

	public void setOutFee(Double outFee) {
		this.outFee = outFee;
	}

	public String getSenderId() {
		return senderId;
	}

	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}

	public String getCustomerRegisterId() {
		return customerRegisterId;
	}

	public void setCustomerRegisterId(String customerRegisterId) {
		this.customerRegisterId = customerRegisterId;
	}

	public String getWaitingOrderId() {
		return waitingOrderId;
	}

	public void setWaitingOrderId(String waitingOrderId) {
		this.waitingOrderId = waitingOrderId;
	}

	public Short getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(Short sendStatus) {
		this.sendStatus = sendStatus;
	}

	public Short getOrderFrom() {
		return orderFrom;
	}

	public void setOrderFrom(Short orderFrom) {
		this.orderFrom = orderFrom;
	}

	public String getReserveTimeId() {
		return reserveTimeId;
	}

	public void setReserveTimeId(String reserveTimeId) {
		this.reserveTimeId = reserveTimeId;
	}

	public String getGlobalCode() {
		return globalCode;
	}

	public void setGlobalCode(String globalCode) {
		this.globalCode = globalCode;
	}

	public String getSimpleCode() {
		return simpleCode;
	}

	public void setSimpleCode(String simpleCode) {
		this.simpleCode = simpleCode;
	}

	/**
	 * 得到分单时原始单ID.
	 * @return orignId 原始单ID.
	 */
	public String getOrignId() {
		return orignId;
	}

	/**
	 * 设置分单时原始单ID.
	 * @param orignId 原始单ID.
	 */
	public void setOrignId(String orignId) {
		this.orignId = orignId;
	}

	public void setSeatMark(String seatMark) {
		this.seatMark = seatMark;
	}
	
	public String getSeatMark() {
		return seatMark;
	}
	
	public void setIsLimitTime(Short isLimitTime) {
		this.isLimitTime = isLimitTime;
	}
	
	public Short getIsLimitTime() {
		return isLimitTime;
	}
	
	/**
	 * 得到扫码地址
	 * @return 扫码地址
	 */
	public String getScanUrl() {
		return scanUrl;
	}

	/**
	 * 设置扫码地址
	 * @param scanUrl 扫码地址
	 */
	public void setScanUrl(String scanUrl) {
		this.scanUrl = scanUrl;
	}

	/**
	 * 得到关联的会员卡id
	 * @return
	 */
	public String getRelatedCardId() {
		return relatedCardId;
	}
	/**
	 * 设置会员卡id
	 * @param relatedCardId
	 */
	public void setRelatedCardId(String relatedCardId) {
		this.relatedCardId = relatedCardId;
	}
	/**
	 * 
	 * @return
	 */
	public Double getPrePay() {
		return prePay;
	}

	public void setPrePay(Double prePay) {
		this.prePay = prePay;
	}
	/**
	 * 获得配送员姓名
	 * @return
	 */
	public String getSenderName() {
		if (senderName == null) return "";
		return senderName;
	}
	/**
	 * 设置配送员姓名
	 * @param senderName
	 */
	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
	/**
	 * 获得配送员电话
	 * @return
	 */
	public String getSenderMobile() {
		if (senderMobile == null) return "";
		return senderMobile;
	}
	/**
	 * 设置配送员电话
	 * @param senderMobile
	 */
	public void setSenderMobile(String senderMobile) {
		this.senderMobile = senderMobile;
	}
	/**
	 * 获得是否第三方配送
	 * @return
	 */
	public boolean getIsThirdDelivery() {
		short isThird = getIsThirdShipping();
		if (isThird == 1) {
			return true;
		}
		return false;
	}

	public Short getIsThirdShipping() {
		return isThirdShipping!=null?isThirdShipping:0;
	}

	/**
	 * 设置是否第三方配送
	 * @return
	 */
	public void setIsThirdShipping(Short isThirdShipping) {
		this.isThirdShipping = isThirdShipping;
	}


}
