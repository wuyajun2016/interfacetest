/************************* 版权声明 **********************************
 * 版权所有：Copyright (c) 2011, ${year} 黄晓峰
 *
 * 工程名称：	钻木餐饮系统
 * 创建者： 黄晓峰 创建日期： ${date}
 * 创建记录：	创建类结构。
 *
 **/

package com.dfire.qa.meal.vo.cash.base;

import java.awt.Cursor;

/**
 * 基础点菜明细.
 * 
 * @author <a href="mailto:xiaofenguser@163.com">黄晓峰</a>
 * @version $Revision$
 */
public abstract class BaseWaitingInstance extends Base {
	/**
	 * <code>序列ID</code>.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * <code>表名</code>.
	 */
	public static final String TABLE_NAME = "WAITINGINSTANCE";
	/**
	 * <code>订单ID对应的字段</code>.
	 */
	public static final String ORDERID = "ORDERID";
	/**
	 * <code>菜名对应的字段</code>.
	 */
	public static final String NAME = "NAME";
	/**
	 * <code>菜品ID对应的字段</code>.
	 */
	public static final String MENUID = "MENUID";
	/**
	 * <code>做法ID对应的字段</code>.
	 */
	public static final String MAKEID = "MAKEID";
	/**
	 * <code>做法对应的字段</code>.
	 */
	public static final String MAKENAME = "MAKENAME";
	/**
	 * <code>做法调价对应的字段</code>.
	 */
	public static final String MAKEPRICE = "MAKEPRICE";
	/**
	 * <code>做法调价模式对应的字段</code>.
	 */
	public static final String MAKEPRICEMODE = "MAKEPRICEMODE";
	/**
	 * <code>规格名对应的字段</code>.
	 */
	public static final String SPECDETAILNAME = "SPECDETAILNAME";
	/**
	 * <code>规格明细ID对应的字段</code>.
	 */
	public static final String SPECDETAILID = "SPECDETAILID";
	/**
	 * <code>规格调价模式对应的字段</code>.
	 */
	public static final String SPECPRICEMODE = "SPECPRICEMODE";
	/**
	 * <code>规格调价对应的字段</code>.
	 */
	public static final String SPECDETAILPRICE = "SPECDETAILPRICE";
	/**
	 * <code>点菜数量对应的字段</code>.
	 */
	public static final String NUM = "NUM";
	/**
	 * <code>结账数量对应的字段</code>.
	 */
	public static final String ACCOUNTNUM = "ACCOUNTNUM";
	/**
	 * <code>点菜单位对应的字段</code>.
	 */
	public static final String UNIT = "UNIT";
	/**
	 * <code>结账单位对应的字段</code>.
	 */
	public static final String ACCOUNTUNIT = "ACCOUNTUNIT";
	/**
	 * <code>说明对应的字段</code>.
	 */
	public static final String MEMO = "MEMO";
	/**
	 * <code>单价对应的字段</code>.
	 */
	public static final String PRICE = "PRICE";
	/**
	 * <code>金额对应的字段</code>.
	 */
	public static final String FEE = "FEE";
	/**
	 * <code>可否打折对应的字段</code>.
	 */
	public static final String ISRATIO = "ISRATIO";
	/**
	 * <code>菜类ID对应的字段</code>.
	 */
	public static final String KINDMENUID = "KINDMENUID";
	/**
	 * <code>口味说明对应的字段</code>.
	 */
	public static final String TASTE = "TASTE";
	/**
	 * <code>原始单价对应的字段</code>.
	 */
	public static final String ORIGINALPRICE = "ORIGINALPRICE";
	/**
	 * <code>会员价对应的字段</code>.
	 */
	public static final String MEMBERPRICE = "MEMBERPRICE";
	/**
	 * <code>折扣率对应的字段</code>.
	 */
	public static final String RATIO = "RATIO";
	/**
	 * <code>折后金额对应的字段</code>.
	 */
	public static final String RATIOFEE = "RATIOFEE";
	/**
	 * <code>套菜可换菜原始ID对应的字段</code>.
	 */
	public static final String CHILDID = "CHILDID";
	/**
	 * <code>服务费收取方式对应的字段</code>.
	 */
	public static final String SERVICEFEEMODE = "SERVICEFEEMODE";
	/**
	 * <code>服务费收取数值对应的字段</code>.
	 */
	public static final String SERVICEFEE = "SERVICEFEE";
	/**
	 * <code>退菜是否需要权限对应的字段</code>.
	 */
	public static final String ISBACKAUTH = "ISBACKAUTH";
	
	public static final String PRICEMODE = "PRICEMODE";
	/**
	 * <code>菜系类型</code>.
	 */
	public static final String KIND="KIND";
	
	public static final String STATUS = "STATUS";

	public static final String TYPE = "TYPE";
	/**
	 * <code>订单ID</code>.
	 */
	private String orderId;
	/**
	 * <code>菜名</code>.
	 */
	private String name;
	/**
	 * <code>菜品ID</code>.
	 */
	private String menuId;
	/**
	 * <code>做法ID</code>.
	 */
	private String makeId;
	/**
	 * <code>做法</code>.
	 */
	private String makeName;
	/**
	 * <code>做法调价</code>.
	 */
	private Double makePrice;
	/**
	 * <code>做法调价模式</code>.
	 */
	private Short makePriceMode;
	/**
	 * <code>规格名</code>.
	 */
	private String specDetailName;
	/**
	 * <code>规格明细ID</code>.
	 */
	private String specDetailId;
	/**
	 * <code>规格调价模式</code>.
	 */
	private Short specPriceMode;
	/**
	 * <code>规格调价</code>.
	 */
	private Double specDetailPrice;
	/**
	 * <code>点菜数量</code>.
	 */
	private Double num;
	/**
	 * <code>结账数量</code>.
	 */
	private Double accountNum;
	/**
	 * <code>点菜单位</code>.
	 */
	private String unit;
	/**
	 * <code>结账单位</code>.
	 */
	private String accountUnit;
	/**
	 * <code>说明</code>.
	 */
	private String memo;
	/**
	 * <code>单价</code>.
	 */
	private Double price;
	/**
	 * <code>金额</code>.
	 */
	private Double fee;
	/**
	 * <code>可否打折</code>.
	 */
	private Short isRatio;
	/**
	 * <code>菜类ID</code>.
	 */
	private String kindMenuId;
	/**
	 * <code>口味说明</code>.
	 */
	private String taste;
	/**
	 * <code>原始单价</code>.
	 */
	private Double originalPrice;
	/**
	 * <code>会员价</code>.
	 */
	private Double memberPrice;
	/**
	 * <code>折扣率</code>.
	 */
	private Double ratio;
	/**
	 * <code>折后金额</code>.
	 */
	private Double ratioFee;
	/**
	 * <code>套菜可换菜原始ID</code>.
	 */
	private String childId;
	/**
	 * <code>服务费收取方式</code>.
	 */
	private Short serviceFeeMode;
	/**
	 * <code>服务费收取数值</code>.
	 */
	private Double serviceFee;
	/**
	 * <code>退菜是否需要权限</code>.
	 */
	private Short isBackAuth;
	
	private Short priceMode;
	
	private Short kind;

	/**
	 * <code>菜肴类型</code>.
	 */
	private Short type;
	
	/**
	 * <code>菜肴的状态,火点菜加菜时可删除和待审核,不插入数据库</code>. 因收银本地也要显示沽清的菜，要保存
	 */
	private Short status;

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	/**
	 * 得到订单ID.
	 * 
	 * @return 订单ID.
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * 设置订单ID.
	 * 
	 * @param orderId
	 *            订单ID.
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	/**
	 * 得到点菜类型.
	 * 
	 * @return 点菜类型.
	 */
	public Short getKind() {
		return kind;
	}

	/**
	 * 设置点菜类型.
	 * 
	 * @param kind
	 *            点菜类型.
	 */
	public void setKind(Short kind) {
		this.kind = kind;
	}

	/**
	 * 得到套菜计价模式.
	 * 
	 * @return 套菜计价模式.
	 */
	public Short getPriceMode() {
		return priceMode;
	}

	/**
	 * 设置套菜计价模式.
	 * 
	 * @param priceMode
	 *            套菜计价模式.
	 */
	public void setPriceMode(Short priceMode) {
		this.priceMode = priceMode;
	}

	/**
	 * 得到菜名.
	 * 
	 * @return 菜名.
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置菜名.
	 * 
	 * @param name
	 *            菜名.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 得到菜品ID.
	 * 
	 * @return 菜品ID.
	 */
	public String getMenuId() {
		return menuId;
	}

	/**
	 * 设置菜品ID.
	 * 
	 * @param menuId
	 *            菜品ID.
	 */
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	/**
	 * 得到做法ID.
	 * 
	 * @return 做法ID.
	 */
	public String getMakeId() {
		return makeId;
	}

	/**
	 * 设置做法ID.
	 * 
	 * @param makeId
	 *            做法ID.
	 */
	public void setMakeId(String makeId) {
		this.makeId = makeId;
	}

	/**
	 * 得到做法.
	 * 
	 * @return 做法.
	 */
	public String getMakeName() {
		return makeName;
	}

	/**
	 * 设置做法.
	 * 
	 * @param makeName
	 *            做法.
	 */
	public void setMakeName(String makeName) {
		this.makeName = makeName;
	}

	/**
	 * 得到做法调价.
	 * 
	 * @return 做法调价.
	 */
	public Double getMakePrice() {
		return makePrice;
	}

	/**
	 * 设置做法调价.
	 * 
	 * @param makePrice
	 *            做法调价.
	 */
	public void setMakePrice(Double makePrice) {
		this.makePrice = makePrice;
	}

	/**
	 * 得到点菜数量.
	 * 
	 * @return 点菜数量.
	 */
	public Double getNum() {
		return num;
	}

	/**
	 * 设置点菜数量.
	 * 
	 * @param num
	 *            点菜数量.
	 */
	public void setNum(Double num) {
		this.num = num;
	}

	/**
	 * 得到结账数量.
	 * 
	 * @return 结账数量.
	 */
	public Double getAccountNum() {
		return accountNum;
	}

	/**
	 * 设置结账数量.
	 * 
	 * @param accountNum
	 *            结账数量.
	 */
	public void setAccountNum(Double accountNum) {
		this.accountNum = accountNum;
	}

	/**
	 * 得到单价.
	 * 
	 * @return 单价.
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * 设置单价.
	 * 
	 * @param price
	 *            单价.
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * 得到可否打折.
	 * 
	 * @return 可否打折.
	 */
	public Short getIsRatio() {
		return isRatio;
	}

	/**
	 * 设置可否打折.
	 * 
	 * @param isRatio
	 *            可否打折.
	 */
	public void setIsRatio(Short isRatio) {
		this.isRatio = isRatio;
	}

	/**
	 * 得到折扣率.
	 * 
	 * @return 折扣率.
	 */
	public Double getRatio() {
		return ratio;
	}

	/**
	 * 设置折扣率.
	 * 
	 * @param ratio
	 *            折扣率.
	 */
	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}

	/**
	 * 得到金额.
	 * 
	 * @return 金额.
	 */
	public Double getFee() {
		return fee;
	}

	/**
	 * 设置金额.
	 * 
	 * @param fee
	 *            金额.
	 */
	public void setFee(Double fee) {
		this.fee = fee;
	}

	/**
	 * 得到折后金额.
	 * 
	 * @return 折后金额.
	 */
	public Double getRatioFee() {
		return ratioFee;
	}

	/**
	 * 设置折后金额.
	 * 
	 * @param ratioFee
	 *            折后金额.
	 */
	public void setRatioFee(Double ratioFee) {
		this.ratioFee = ratioFee;
	}

	/**
	 * 得到说明.
	 * 
	 * @return 说明.
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * 设置说明.
	 * 
	 * @param memo
	 *            说明.
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * 得到规格明细ID.
	 * 
	 * @return 规格明细ID.
	 */
	public String getSpecDetailId() {
		return specDetailId;
	}

	/**
	 * 设置规格明细ID.
	 * 
	 * @param specDetailId
	 *            规格明细ID.
	 */
	public void setSpecDetailId(String specDetailId) {
		this.specDetailId = specDetailId;
	}

	/**
	 * 得到规格名.
	 * 
	 * @return 规格名.
	 */
	public String getSpecDetailName() {
		return specDetailName;
	}

	/**
	 * 设置规格名.
	 * 
	 * @param specDetailName
	 *            规格名.
	 */
	public void setSpecDetailName(String specDetailName) {
		this.specDetailName = specDetailName;
	}

	/**
	 * 得到规格调价.
	 * 
	 * @return 规格调价.
	 */
	public Double getSpecDetailPrice() {
		return specDetailPrice;
	}

	/**
	 * 设置规格调价.
	 * 
	 * @param specDetailPrice
	 *            规格调价.
	 */
	public void setSpecDetailPrice(Double specDetailPrice) {
		this.specDetailPrice = specDetailPrice;
	}

	/**
	 * 得到点菜单位.
	 * 
	 * @return 点菜单位.
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * 设置点菜单位.
	 * 
	 * @param unit
	 *            点菜单位.
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * 得到结账单位.
	 * 
	 * @return 结账单位.
	 */
	public String getAccountUnit() {
		return accountUnit;
	}

	/**
	 * 设置结账单位.
	 * 
	 * @param accountUnit
	 *            结账单位.
	 */
	public void setAccountUnit(String accountUnit) {
		this.accountUnit = accountUnit;
	}

	/**
	 * 得到做法调价模式.
	 * 
	 * @return 做法调价模式.
	 */
	public Short getMakePriceMode() {
		return makePriceMode;
	}

	/**
	 * 设置做法调价模式.
	 * 
	 * @param makePriceMode
	 *            做法调价模式.
	 */
	public void setMakePriceMode(Short makePriceMode) {
		this.makePriceMode = makePriceMode;
	}

	/**
	 * 得到菜类ID.
	 * 
	 * @return 菜类ID.
	 */
	public String getKindMenuId() {
		return kindMenuId;
	}

	/**
	 * 设置菜类ID.
	 * 
	 * @param kindMenuId
	 *            菜类ID.
	 */
	public void setKindMenuId(String kindMenuId) {
		this.kindMenuId = kindMenuId;
	}

	/**
	 * 得到原始单价.
	 * 
	 * @return 原始单价.
	 */
	public Double getOriginalPrice() {
		return originalPrice;
	}

	/**
	 * 设置原始单价.
	 * 
	 * @param originalPrice
	 *            原始单价.
	 */
	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}

	/**
	 * 得到口味说明.
	 * 
	 * @return 口味说明.
	 */
	public String getTaste() {
		return taste;
	}

	/**
	 * 设置口味说明.
	 * 
	 * @param taste
	 *            口味说明.
	 */
	public void setTaste(String taste) {
		this.taste = taste;
	}

	/**
	 * 得到会员价.
	 * 
	 * @return 会员价.
	 */
	public Double getMemberPrice() {
		return memberPrice;
	}

	/**
	 * 设置会员价.
	 * 
	 * @param memberPrice
	 *            会员价.
	 */
	public void setMemberPrice(Double memberPrice) {
		this.memberPrice = memberPrice;
	}

	/**
	 * 得到规格调价模式.
	 * 
	 * @return 规格调价模式.
	 */
	public Short getSpecPriceMode() {
		return specPriceMode;
	}

	/**
	 * 设置规格调价模式.
	 * 
	 * @param specPriceMode
	 *            规格调价模式.
	 */
	public void setSpecPriceMode(Short specPriceMode) {
		this.specPriceMode = specPriceMode;
	}

	/**
	 * 得到套菜可换菜原始ID.
	 * @return 套菜可换菜原始ID.
	 */
	public String getChildId() {
		return childId;
	}

	/**
	 * 设置套菜可换菜原始ID.
	 * @param childId 套菜可换菜原始ID.
	 */
	public void setChildId(String childId) {
		this.childId = childId;
	}
	
	/**
	 * 得到服务费收取方式.
	 * @return 服务费收取方式.
	 */
	public Short getServiceFeeMode() {
		return serviceFeeMode;
	}

	/**
	 * 设置服务费收取方式.
	 * @param serviceFeeMode 服务费收取方式.
	 */
	public void setServiceFeeMode(Short serviceFeeMode) {
		this.serviceFeeMode = serviceFeeMode;
	}
	
	/**
	 * 得到服务费收取数值.
	 * @return 服务费收取数值.
	 */
	public Double getServiceFee() {
		return serviceFee;
	}

	/**
	 * 设置服务费收取数值.
	 * @param serviceFee 服务费收取数值.
	 */
	public void setServiceFee(Double serviceFee) {
		this.serviceFee = serviceFee;
	}
	/**
	 * 得到退菜是否需要权限.
	 * @return 退菜是否需要权限.
	 */
	public Short getIsBackAuth() {
		return isBackAuth;
	}

	/**
	 * 设置退菜是否需要权限.
	 * @param isBackAuth 退菜是否需要权限.
	 */
	public void setIsBackAuth(Short isBackAuth) {
		this.isBackAuth = isBackAuth;
	}
	/**
	 * 得到菜肴类型.
	 * @return 菜肴类型.
	 */
	public Short getType() {
		return type;
	}
	/**
	 * 设置菜肴类型.
	 * @param type 菜肴类型.
	 */
	public void setType(Short type) {
		this.type = type;
	}

}
