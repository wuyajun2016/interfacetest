/************************* 版权声明 **********************************
 * 版权所有：Copyright (c) 2011, ${year} 黄晓峰
 *
 * 工程名称：	钻木餐饮系统
 * 创建者： 黄晓峰 创建日期： ${date}
 * 创建记录：	创建类结构。
 *
 **/

package com.dfire.qa.meal.vo.cash;

import com.twodfire.remote.specdetail.domain.BaseSpecDetail;


/**
 * 规格明细设置.
 * @author <a href="mailto:xiaofenguser@163.com">黄晓峰</a>
 * @version $Revision$
 */
public class SpecDetail extends BaseSpecDetail{
	/**
	 * <code>序列ID</code>.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * <code>按比例调价</code>.
	 */
	public static final Short PRICE_MODE_SCALE = 1;
	/**
	 * <code>按加价调价</code>.
	 */
	public static final Short PRICE_MODE_ADD = 2;
}
