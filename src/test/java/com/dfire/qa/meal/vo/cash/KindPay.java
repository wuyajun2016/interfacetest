/************************* 版权声明 **********************************
 * 版权所有：Copyright (c) 2011, ${year} 黄晓峰
 *
 * 工程名称：	钻木餐饮系统
 * 创建者： 黄晓峰 创建日期： ${date}
 * 创建记录：	创建类结构。
 *
 **/

package com.dfire.qa.meal.vo.cash;


/**
 * 付款类型.
 * @author <a href="mailto:xiaofenguser@163.com">黄晓峰</a>
 * @version $Revision$
 */
public class KindPay extends BaseKindPay{
	/**
	 * <code>序列ID</code>.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * <code>现金</code>.
	 */
	public static final Integer KIND_CASH = 0;
	
	/**
	 * <code>银行卡</code>.
	 */
	public static final Integer CARD = 1;

	/**
	 * <code>挂账</code>.
	 */
	public static final Integer KIND_CREDIT_ACCOUNT = 2;
	
	/**
	 * <code>优惠券</code>.
	 */
	public static final Integer KIND_VOUCHER= 3;
	
	/**
	 * <code>免单</code>.
	 */
	public static final Integer KIND_FREE_BILL = 4;
	
	/**
	 * <code>储值卡</code>.
	 */
	public static final Integer KIND_SAVING_CARD = 5;
	
	/**
	 * <code>第三方支付</code>.
	 */
	public static final Integer THIRDPART_PAY = 6;
	
	/**
	 * <code>本地优惠券支付</code>.
	 */
	public static final Integer LOCAL_COUPON = 9;
	/**
	 * <code>红包支付对应的字段</code>.
	 */
	public static final Short TYPE_COUPON = 10;
	/**
	 * <code>闪惠支付对应的字段</code>.
	 */
	public static final Short TYPE_SHANHUI = 11;
	/**
	 * <code>闪惠折扣支付对应的字段</code>.
	 */
	public static final Short TYPE_SHANHUI_DISCOUNT = 12;
	/**
	 * <code>优惠券支付对应的字段</code>.
	 */
	public static final Short TYPE_DISCOUNT_COUPON = 8;
	/**
	 * <code>第三方支付类型，支付宝扫码</code>
	 */
	public static final Short THIRD_TYPE_ALIPAY = 1;
	/**
	 * <code>第三方支付类型，支付宝折扣</code>
	 */
	public static final Short THIRD_TYPE_ALIPAY_DISCOUNT = 1001;
	/**
	 * <code>第三方支付类型，微信扫码</code>
	 */
	public static  final Short THIRD_TYPE_WEIXIN = 9;
	/**
	 * <code>第三方支付类型，园区卡</code>
	 */
	public static  final Short THIRD_TYPE_PARK_CARD = 13;
	/**
	 * <code>代金券，与上面的优惠券区分开</code>
	 */
	public static final Integer KIND_VOUCHERS = 10;

//	public static final Integer KIND_WEIXIN = 7;
	
	public KindPay() {
		super();
	}

	public KindPay(String name, Short isCharge) {
		super();
		setName(name);
		setIsCharge(isCharge);
	}

	/**
	 * 是否是第三方代金券
	 * @return
     */
	public boolean isVouchers(){
		if(KIND_VOUCHERS.equals(getKind())){
			return true;
		}
		return false;
	}

	/**
	 * 是否第三方
	 *
	 * @return
	 */
	public boolean isThirdOnline() {
		Short thirdType = getThirdType();
		if (thirdType == null) {
			return false;
		}
		if (1 == thirdType || 9 == thirdType) {
			return true;
		}
		return false;
	}
}
