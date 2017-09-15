/************************* 版权声明 **********************************
 * 版权所有：Copyright (c) 2011, ${year} 黄晓峰
 *
 * 工程名称：	钻木餐饮系统
 * 创建者： 黄晓峰 创建日期： ${date}
 * 创建记录：	创建类结构。
 *
 **/

package com.dfire.qa.meal.vo.cash;

import java.util.Date;

import com.dfire.qa.meal.vo.cash.base.Base;
import com.dfire.qa.meal.vo.cash.base.BaseTotalPay;
import com.dfire.test.util.StringUtil;


/**
 * 账单.
 * @author <a href="mailto:xiaofenguser@163.com">黄晓峰</a>
 * @version $Revision$
 */
public class TotalPay extends BaseTotalPay {
	/**
	 * <code>序列ID</code>.
	 */
	private static final long serialVersionUID = 1L;
	
	/** <code>初始</code>. */
	public static final Short STATUS_COMMON = (short) 1;

	/** <code>结账完毕</code>. */
	public static final Short STATUS_END = (short) 2;

	/** <code>反结账</code>. */
	public static final Short STATUS_CANCELED = (short) 3;

	/** <code>未交班状态</code>. */
	public static final Short SHIFT_STATUS_COMMON = (short) 1;

	/** <code>已交班状态</code>. */
	public static final Short SHIFT_STATUS_SHIFTTED = (short) 2;
	
	/**
	 * <code>默认折扣率</code>.
	 */
	public static final Double DEFAULT_AGIO_RATE = 100d;
	
	public void initDefault(String entityId) {
		
		initBaseData(entityId);
		setInvoice(0d);
		
		setPrintnum1(0);		
		setPrintnum2(0);
		
		setCurrDate(new Date());
		setSourceAmount(0d);
		setDiscountAmount(0d);
		
		setResultAmount(0d);
		setRecieveAmount(0d);
		setOperateDate(System.currentTimeMillis());
		
		setIsFullRatio(Base.TRUE);
		setRatio(100d); // 折扣率
		
		setOverStatus(SHIFT_STATUS_COMMON); // 交接班状态
		setStatus(STATUS_COMMON);
		
		setIsMinConsumeRatio(Base.FALSE);
		setIsServiceFeeRatio(Base.FALSE);
		
		setIsHide(Base.FALSE);
		setIsInvoice(Base.FALSE);
		
		setCouponDiscount(0d);
		
	}
	


	/**
	 * 获取发票抬头，发票抬头中包含了电子发票的电话号码
	 */
	public String getInvoiceTitle() {
		String invoiceTitle = getInvoiceMemo();
		if (StringUtil.isNotEmpty(invoiceTitle)) {
			if (invoiceTitle.contains(",")) {
				String[] invoices = invoiceTitle.split(",");
				return invoices[0];
			} else {
				return invoiceTitle;
			}
		}
		return null;
	}

	/**
	 * 获取发票抬头，发票抬头中包含了电子发票的电话号码
	 */
	public String getPhoneNumber() {
		String invoiceTitle = getInvoiceMemo();
		if (StringUtil.isNotEmpty(invoiceTitle)) {
			if (invoiceTitle.contains(",")) {
				String[] invoices = invoiceTitle.split(",");
				if (invoices.length > 1) {
					return invoices[1];
				} else {
					return null;
				}
			} else {
				return null;
			}
		}
		return null;
	}
}
