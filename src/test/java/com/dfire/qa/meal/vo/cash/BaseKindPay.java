/************************* 版权声明 **********************************
 * 版权所有：Copyright (c) 2011, ${year} 黄晓峰
 *
 * 工程名称：	钻木餐饮系统
 * 创建者： 黄晓峰 创建日期： ${date}
 * 创建记录：	创建类结构。
 *
 **/

package com.dfire.qa.meal.vo.cash;

import com.dfire.qa.meal.vo.cash.base.Base;



/**
 * 基础付款类型.
 * @author <a href="mailto:xiaofenguser@163.com">黄晓峰</a>
 * @version $Revision$
 */
public abstract class BaseKindPay extends Base{
	/**
	 * <code>序列ID</code>.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * <code>表名</code>.
	 */
	public static final String TABLE_NAME = "KINDPAY";
	/**
	 * <code>顺序码对应的字段</code>.
	 */
	public static final String SORTCODE = "SORTCODE";
	/**
	 * <code>编码对应的字段</code>.
	 */
	public static final String CODE = "CODE";
	/**
	 * <code>名称对应的字段</code>.
	 */
	public static final String NAME = "NAME";
	/**
	 * <code>备注对应的字段</code>.
	 */
	public static final String MEMO = "MEMO";
	/**
	 * <code>是否需要找零对应的字段</code>.
	 */
	public static final String ISCHARGE = "ISCHARGE";
	/**
	 * <code>是否需要从卡上扣款对应的字段</code>.
	 */
	public static final String ISCARD = "ISCARD";
	/**
	 * <code>是否计入销售额对应的字段</code>.
	 */
	public static final String ISINCLUDE = "ISINCLUDE";
	/**
	 * <code>是否挂账模式对应的字段</code>.
	 */
	public static final String ISSIGNBILL = "ISSIGNBILL";
	/**
	 * <code>第三方系统连接地址对应的字段</code>.
	 */
	public static final String THIRDPARTURL = "THIRDPARTURL";
	/**
	 * <code>第三方系统类型对应的字段</code>.
	 */
	public static final String THIRDPARTKIND = "THIRDPARTKIND";
	/**
	 * <code>是否第三方结算对应的字段</code>.
	 */
	public static final String ISTHIRDPART = "ISTHIRDPART";
	/**
	 * <code>是否积分对应的字段</code>.
	 */
	public static final String ISDEGREE = "ISDEGREE";
	/**
	 * <code>类型对应的字段</code>.
	 */
	public static final String KIND = "KIND";
	
	/**
	 * <code>是否打开钱箱对应的字段</code>.
	 */
	public static final String ISOPENCASHDRAWER = "ISOPENCASHDRAWER";
	
	public static final String THIRDTYPE = "THIRDTYPE";
	
	public static final String ISRETURN = "ISRETURN";
	/**
	 * <code>第三方支付是否显示对应的字段</code>.
	 */
	public static final String ISSHOW = "ISSHOW";
	/**
	 * <code>顺序码</code>.
	 */
	private Integer sortCode;
	/**
	 * <code>编码</code>.
	 */
	private String code;
	/**
	 * <code>名称</code>.
	 */
	private String name;
	/**
	 * <code>备注</code>.
	 */
	private String memo;
	/**
	 * <code>是否需要找零</code>.
	 */
	private Short isCharge;
	/**
	 * <code>是否需要从卡上扣款</code>.
	 */
	private Short isCard;
	/**
	 * <code>是否计入销售额</code>.
	 */
	private Short isInclude;
	/**
	 * <code>是否挂账模式</code>.
	 */
	private Short isSignBill;
	/**
	 * <code>第三方系统连接地址</code>.
	 */
	private String thirdPartUrl;
	/**
	 * <code>第三方系统类型</code>.
	 */
	private String thirdPartKind;
	/**
	 * <code>是否第三方结算</code>.
	 */
	private Short isThirdPart;
	/**
	 * <code>是否积分</code>.
	 */
	private Short isDegree;
	/**
	 * <code>类型</code>.
	 */
	private Integer kind;
	
	/**
	 * <code>是否打开钱箱</code>.
	 */
	private Short isOpenCashDrawer;
	
	/**
	 * 第三方支付类型
	 */
	private Short thirdType;

	/**
	 * 第三方支付类型是否支持退款 
	 */
	private Short isReturn;

	/**
	 * <code>第三方支付是否显示对应的字段</code>.
	 */
	private Short isShow ;

	public Short getThirdType() {
		return thirdType;
	}

	public void setThirdType(Short thirdType) {
		this.thirdType = thirdType;
	}

	public Short getIsReturn() {
		return isReturn;
	}

	public void setIsReturn(Short isReturn) {
		this.isReturn = isReturn;
	}

	/**
	 * 得到顺序码.
	 * @return 顺序码.
	 */
	public Integer getSortCode() {
		return sortCode;
	}

	/**
	 * 设置顺序码.
	 * @param sortCode 顺序码.
	 */
	public void setSortCode(Integer sortCode) {
		this.sortCode = sortCode;
	}
	/**
	 * 得到编码.
	 * @return 编码.
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 设置编码.
	 * @param code 编码.
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 得到名称.
	 * @return 名称.
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置名称.
	 * @param name 名称.
	 */
	public void setName(String name) {
		this.name = name;
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
	 * 得到是否需要找零.
	 * @return 是否需要找零.
	 */
	public Short getIsCharge() {
		return isCharge;
	}

	/**
	 * 设置是否需要找零.
	 * @param isCharge 是否需要找零.
	 */
	public void setIsCharge(Short isCharge) {
		this.isCharge = isCharge;
	}
	/**
	 * 得到是否需要从卡上扣款.
	 * @return 是否需要从卡上扣款.
	 */
	public Short getIsCard() {
		return isCard;
	}

	/**
	 * 设置是否需要从卡上扣款.
	 * @param isCard 是否需要从卡上扣款.
	 */
	public void setIsCard(Short isCard) {
		this.isCard = isCard;
	}
	/**
	 * 得到是否计入销售额.
	 * @return 是否计入销售额.
	 */
	public Short getIsInclude() {
		return isInclude;
	}

	/**
	 * 设置是否计入销售额.
	 * @param isInclude 是否计入销售额.
	 */
	public void setIsInclude(Short isInclude) {
		this.isInclude = isInclude;
	}
	/**
	 * 得到是否挂账模式.
	 * @return 是否挂账模式.
	 */
	public Short getIsSignBill() {
		return isSignBill;
	}

	/**
	 * 设置是否挂账模式.
	 * @param isSignBill 是否挂账模式.
	 */
	public void setIsSignBill(Short isSignBill) {
		this.isSignBill = isSignBill;
	}
	/**
	 * 得到第三方系统连接地址.
	 * @return 第三方系统连接地址.
	 */
	public String getThirdPartUrl() {
		return thirdPartUrl;
	}

	/**
	 * 设置第三方系统连接地址.
	 * @param thirdPartUrl 第三方系统连接地址.
	 */
	public void setThirdPartUrl(String thirdPartUrl) {
		this.thirdPartUrl = thirdPartUrl;
	}
	/**
	 * 得到第三方系统类型.
	 * @return 第三方系统类型.
	 */
	public String getThirdPartKind() {
		return thirdPartKind;
	}

	/**
	 * 设置第三方系统类型.
	 * @param thirdPartKind 第三方系统类型.
	 */
	public void setThirdPartKind(String thirdPartKind) {
		this.thirdPartKind = thirdPartKind;
	}
	/**
	 * 得到是否第三方结算.
	 * @return 是否第三方结算.
	 */
	public Short getIsThirdPart() {
		return isThirdPart;
	}

	/**
	 * 设置是否第三方结算.
	 * @param isThirdPart 是否第三方结算.
	 */
	public void setIsThirdPart(Short isThirdPart) {
		this.isThirdPart = isThirdPart;
	}
	/**
	 * 得到是否积分.
	 * @return 是否积分.
	 */
	public Short getIsDegree() {
		return isDegree;
	}

	/**
	 * 设置是否积分.
	 * @param isDegree 是否积分.
	 */
	public void setIsDegree(Short isDegree) {
		this.isDegree = isDegree;
	}
	/**
	 * 得到类型.
	 * @return 类型.
	 */
	public Integer getKind() {
		return kind;
	}

	/**
	 * 设置类型.
	 * @param kind 类型.
	 */
	public void setKind(Integer kind) {
		this.kind = kind;
	}
	
	/**
	 * 得到是否打开钱箱
	 * @return
	 */
	public Short getIsOpenCashDrawer() {
		return isOpenCashDrawer;
	}

	/**
	 * 设置是否打开钱箱
	 * @param isOpenCashDrawer
	 */
	public void setIsOpenCashDrawer(Short isOpenCashDrawer) {
		this.isOpenCashDrawer = isOpenCashDrawer;
	}
	/**
	 * 得到是否显示第三方支付
	 * @return
	 */
	public void setIsShow(Short isShow) {
		this.isShow = isShow;
	}
	/**
	 * 设置是否显示第三方支付
	 * @return
	 */
	public Short getIsShow() {
		return isShow;
	}

//	/** {@inheritDoc} */
//	protected void processContentValues(ContentValues contentValues) {
//		put(contentValues, SORTCODE, sortCode);
//		put(contentValues, CODE, code);
//		put(contentValues, NAME, name);
//		put(contentValues, MEMO, memo);
//		put(contentValues, ISCHARGE, isCharge);
//		put(contentValues, ISCARD, isCard);
//		put(contentValues, ISINCLUDE, isInclude);
//		put(contentValues, ISSIGNBILL, isSignBill);
//		put(contentValues, THIRDPARTURL, thirdPartUrl);
//		put(contentValues, THIRDPARTKIND, thirdPartKind);
//		put(contentValues, ISTHIRDPART, isThirdPart);
//		put(contentValues, ISDEGREE, isDegree);
//		put(contentValues, KIND, kind);
//		put(contentValues, ISOPENCASHDRAWER, isOpenCashDrawer);
//		put(contentValues, THIRDTYPE, thirdType);
//		put(contentValues, ISRETURN, isReturn);
//		put(contentValues, ISSHOW, isShow);
//	}

	/** {@inheritDoc} */
//	@Override
//	public String getTableName() {
//		return TABLE_NAME;
//	}
	/**
	 * 初始化对象.
	 * @param cursor 游标对象.
	 */
//	protected void doInit(Cursor cursor) {
//		super.doInit(cursor);
//		sortCode = cursor.getInt(cursor.getColumnIndex(SORTCODE));
//		code = cursor.getString(cursor.getColumnIndex(CODE));
//		name = cursor.getString(cursor.getColumnIndex(NAME));
//		memo = cursor.getString(cursor.getColumnIndex(MEMO));
//		isCharge = cursor.getShort(cursor.getColumnIndex(ISCHARGE));
//		isCard = cursor.getShort(cursor.getColumnIndex(ISCARD));
//		isInclude = cursor.getShort(cursor.getColumnIndex(ISINCLUDE));
//		isSignBill = cursor.getShort(cursor.getColumnIndex(ISSIGNBILL));
//		thirdPartUrl = cursor.getString(cursor.getColumnIndex(THIRDPARTURL));
//		thirdPartKind = cursor.getString(cursor.getColumnIndex(THIRDPARTKIND));
//		isThirdPart = cursor.getShort(cursor.getColumnIndex(ISTHIRDPART));
//		isDegree = cursor.getShort(cursor.getColumnIndex(ISDEGREE));
//		kind = cursor.getInt(cursor.getColumnIndex(KIND));
//		isOpenCashDrawer = cursor.getShort(cursor.getColumnIndex(ISOPENCASHDRAWER));
//		thirdType = cursor.getShort(cursor.getColumnIndex(THIRDTYPE));
//		isReturn = cursor.getShort(cursor.getColumnIndex(ISRETURN));
//		isShow = cursor.getShort(cursor.getColumnIndex(ISSHOW));
//	}
}
