/************************* 版权声明 **********************************
 * 版权所有：Copyright (c) 2011, ${year} 黄晓峰
 *
 * 工程名称：	钻木餐饮系统
 * 创建者： 黄晓峰 创建日期： ${date}
 * 创建记录：	创建类结构。
 *
 **/

package com.dfire.testcase.function.bean.cash;

import com.twodfire.remote.menumake.domain.BaseMenuMake;


/**
 * 菜肴烧法.
 * @author <a href="mailto:xiaofenguser@163.com">黄晓峰</a>
 * @version $Revision$
 */
public class MenuMake extends BaseMenuMake{
	/**
	 * <code>序列ID</code>.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * <code>加价模式-不加价</code>.
	 */
	public static final Short MAKEPRICE_NONE = 0;  
	/**
	 * <code>做法加价模式：一次性加价</code>.
	 */
	public static final Short MAKEPRICE_TOTAL= 1;
	
	/**
	 * <code>做法加价模式：每点菜单位加价</code>.
	 */
	public static final Short MAKEPRICE_PERBUYACCOUNT = 2;

	/**
	 * <code>做法加价模式：每结账单位加价</code>.
	 */
	public static final Short MAKEPRICE_PERUNIT = 3;
}
