/************************* 版权声明 **********************************
 * 版权所有：Copyright (c) 2011, ${year} 黄晓峰
 *
 * 工程名称：	钻木餐饮系统
 * 创建者： 黄晓峰 创建日期： ${date}
 * 创建记录：	创建类结构。
 *
 **/

package com.dfire.testcase.function.bean.cash;

import com.dfire.testcase.function.bean.cash.base.Base;
import com.dfire.testcase.function.bean.cash.base.BasePay;


/**
 * 付款信息.
 * @author <a href="mailto:xiaofenguser@163.com">黄晓峰</a>
 * @version $Revision$
 */
public class Pay extends BasePay{
	/**
	 * <code>序列ID</code>.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * <code>预付类型ID</code>.
	 */
	public static final String RESERVE_PAY_KIND_ID = "1";
	/**
	 * <code>网上支付类型ID</code>.
	 */
	public static final String NET_PAY_KIND_ID = "2";
	/**
	 * <code>网上支付</code>.
	 */
	public static final String NET_PAY_NAME = "网络支付";
	
	/**
	 * <code>微信支付type</code>.
	 */
	public static final int WIN_XIN_PAY_TYPE = 9;
	
	/**
	 * <code>红包支付type</code>.
	 */
	public static final int RED_COUPON_PAY_TYPE = 10;
	
	/**
	 * 更新付款信息.
	 */
	public void updatePay() {
		this.setFee(NumberUtils.round(this.getPay() - this.getCharge()));
		this.setPayTime(System.currentTimeMillis());
		setIsDealed(Base.FALSE);
	}

}
