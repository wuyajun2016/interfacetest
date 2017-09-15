/************************* 版权声明 **********************************
 * 版权所有：Copyright (c) 2008, 2010 黄晓峰
 *
 * 工程名称：	com.zmsoft.core
 * 创建者：	Administrator 创建日期： 2010-11-16
 * 创建记录：	创建类结构。
 *
 * ************************* 变更记录 ********************************
 * 修改者： 
 * 修改日期：
 * 修改记录：
 *
 * 
 * ......************************* To Do List*****************************
 * 
 *
 * Suberversion 信息
 * ID:			$Id$
 * 源代码URL：	$HeadURL$
 * 最后修改者：	$LastChangedBy$
 * 最后修改日期：	$LastChangedDate$
 * 最新版本：		$LastChangedRevision$
 **/


package com.dfire.testcase.function.bean.cash;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;


/**
 * 数据格式化工具.
 * @author <a href="mailto:xiaofenguser@163.com">黄晓峰</a>
 * @version $Revision$
 */
public final class FormatUtil {
	private static final NumberFormat FLOAT_FORMAT = new DecimalFormat("#.00");
	private static final NumberFormat NUMBER_FORMAT = new DecimalFormat("#");
	private static final NumberFormat NUMBER_FORMAT2 = new DecimalFormat("#,##0.00");
	private static final NumberFormat NUMBER_FORMAT3 = new DecimalFormat("#,##0.0");
	private static final NumberFormat NUMBER_FORMAT4 = new DecimalFormat("#,##0.0#");
	
	/**
	 * 格式化浮点型数据.
	 * @param f 原数据.
	 * @return 格式化后的结果.
	 */
	public static String format(Float f) {
		if (f == null) {
			return "0";
		}
		return FLOAT_FORMAT.format(f);
	}
	
	/**
	 * 格式化浮点型数据.
	 * @param f 原数据.
	 * @return 格式化后的结果.
	 */
	public static String format(Double d) {
		if (d == null) {
			return "0";
		}
		return FLOAT_FORMAT.format(d);
	}

	/**
	 * @param i
	 * @return
	 */
	public static String format(Integer i) {
		if (i == null) {
			return "0";
		}
		return NUMBER_FORMAT.format(i);
	}
	/**
	 * @param l
	 * @return
	 */
	public static String format(Long l) {
		if (l == null) {
			return "0";
		}
		return NUMBER_FORMAT.format(l);
	}
	/**
	 * 格式化浮点型数据.
	 * @param f 原数据.
	 * @return 格式化后的结果.
	 */
	public static String format2(Double d) {
		if (d == null) {
			return "0";
		}
		return NUMBER_FORMAT2.format(d);
	}
	/**
	 * 格式化浮点型数据.
	 * @param f 原数据.
	 * @return 格式化后的结果.
	 */
	public static String format3(Double m) {
		if (m == null) {
			return "0";
		}
		return NUMBER_FORMAT3.format(m);
	}
	/**
	 * 格式化浮点型数据.
	 * @param f 原数据.
	 * @return 格式化后的结果.
	 */
	public static String format4(Double m) {
		if (m == null) {
			return "0";
		}
		return NUMBER_FORMAT4.format(m);
	}
	
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}

	/**
	 * double减法运算
     * @return
     */
	public static double sub(double v1,double v2){
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}
}
