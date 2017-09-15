/*************************
 * 版权声明 **********************************
 * 版权所有：Copyright (c) 2000, 2007 浙江钻木电子科技有限公司
 * <p/>
 * 工程名称：	com.zmsoft.eatery
 * 创建者：	黄晓峰 创建日期： 2007-10-23
 * 创建记录：	创建类结构。
 * <p/>
 * ************************* 变更记录 ********************************
 * 修改者：
 * 修改日期：
 * 修改记录：
 * <p/>
 * <p/>
 * ......************************* To Do List*****************************
 * <p/>
 * <p/>
 * Suberversion 信息
 * ID:			$Id$
 * 源代码URL：	$HeadURL$
 * 最后修改者：	$LastChangedBy$
 * 最后修改日期：	$LastChangedDate$
 * 最新版本：		$LastChangedRevision$
 **/

package com.dfire.qa.meal.vo.cash;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 数字相关的工具类.
 *
 * @author <a href="mailto:xiaofenguser@163.com">黄晓峰</a>
 * @version $Revision$
 */
public final class NumberUtils {

    private static final DecimalFormat NF = new DecimalFormat("#0.###");
    private static final DecimalFormat NF2 = new DecimalFormat("#,##0.00");
    private static final DecimalFormat NF3 = new DecimalFormat("#,##0.0");
    private static final DecimalFormat NF4 = new DecimalFormat("0.00");
    private static final DecimalFormat NF5 = new DecimalFormat("#.0");
    private static final Pattern PATTERN_NUMBER = Pattern.compile("^\\d*[.]?\\d*$");

    private static final int PERCENT = 100;
    private static final double FLOOR = 0.5;

    public static final double ZERO = 10E-6;

    private static Double MAX_PRICE = 100000d, MAX_NUM = 10000d;

    /**
     * 私有构造.
     */
    private NumberUtils() {

    }

    /**
     * 将数字转为字符串，格式为：有小数位显示小数位，无小数位或者小数位为0不显示小数位。
     * <pre><code>
     *     1000.01 解析为 "1000.01"
     *     1000.011 解析为 "1000.011"
     *     1000.00 解析为 "1000"
     *     1000 解析为 "1000"
     * </code></pre>
     * @param number double
     * @return String
     */
    public static String format(Double number) {
        return NF.format(number);
    }

    /**
     * 将数字转为字符串，格式为：显示2位小数，并且整数部分采用三位分节法，即 1,000.00的形式。
     * <pre><code>
     *     100.01 解析为 "100.01"
     *     1000.01 解析为 "1,000.01"
     *     1000.001 解析为 "1,000.00"
     *     1000.009 解析为 "1,000.01"
     *     1000.00 解析为 "1,000.00"
     *     1000 解析为 "1,000.00"
     * </code></pre>
     * @param number
     * @return
     */
    public static String format2(Double number) {
        return NF2.format(number);
    }

    /**
     * 将数字转为字符串，格式为：显示1位小数，并且整数部分采用三位分节法，即 1,000.0的形式。
     * @param number double
     * @return String
     * @see #format2(Double)
     */
    public static String format3(Double number) {
        return NF3.format(number);
    }

    /**
     * 将数字转为字符串，格式为：显示2位小数，即 1000.00的形式。
     * @param number double
     * @return String
     */
    public static String format4(Double number) {
        return NF4.format(number);
    }

    /**
     * 将数字转为字符串，格式为：显示1位小数，即 1000.0的形式。
     * @param number
     * @return
     */
    public static String format5(Double number) {
        return NF5.format(number);
    }

    /**
     * 将字符串解析为double，会自动去除掉逗号分隔符。
     * @param numberStr 数字字符串
     * @return Double
     */
    public static Double parse(String numberStr) {
        if (numberStr == null) {
            return null;
        }
        return Double.parseDouble(numberStr.replaceAll(",", ""));
    }
    /**
     * 处理数字的四舍五入. 四对后两位小数进行处理.
     *
     * @param number
     *            要处理的数字.
     * @return 处理结果.
     */
    public static Double round(Double number) {
        return Math.floor(number * PERCENT + FLOOR) / PERCENT;
    }
    /**
     * 处理到整数位的四舍五入.
     *
     * @param number
     *            要处理的数字.
     * @return 处理结果.
     */
    public static Double roundLong(Double number) {
        return Math.floor(number + FLOOR);
    }

    /**
     * 把一个浮点型数据转成有意义的数值,如果是null则转成0.
     *
     * @param source
     *            源浮点型数据.
     * @return 转换后的结果.
     */
    public static double nullToZero(Double source) {
        return source == null ? 0 : source;
    }

    /**
     * 把一个Short型的数据转成有意义的数值,如果是null则转成0.
     *
     * @param source
     *            源Short型数据.
     * @return 转换后的结果.
     */
    public static Double nullToZero(Short source) {
        if (source == null) {
            return 0d;
        }
        return source.doubleValue();
    }

    /**
     * 把一个Integer型的数据转成有意义的数值,如果是null则转成0.
     *
     * @param source
     *            源Short型数据.
     * @return 转换后的结果.
     */
    public static Double nullToZero(Integer source) {
        if (source == null) {
            return 0d;
        }
        return source.doubleValue();
    }

    /**
     * 对数字的求和.
     * @param a 被加数.
     * @param b 加数.
     * @return 结果.
     */
    public static Double add(Double a, Double b) {
        return nullToZero(a) + nullToZero(b);
    }

    /**
     * 判断double型数字是否为零(包括正负数).
     * @param d 数.
     * @return 结果.
     */
    public static boolean isZero(double d) {
        return Math.abs(d) < ZERO;
    }

    /**
     * 是否是数字.
     * @param source 源数字.
     * @return true, 是正常数字.
     */
    public static boolean isNumber(String source) {
        if (source == null || source.length() == 0) {
            return true;
        }
        Matcher m = PATTERN_NUMBER.matcher(source);
        if (!m.find()) {
            return false;
        }
        String first = null;
        int split = source.indexOf('.');
        if (split == -1) {
            first = source;
        } else {
            first = source.substring(0, split);
        }
        if (first == null || first.length() == 0) {
            return true;
        }
        if (first.length() > 1 && first.startsWith("0")) {
            return false;
        }
        return true;
    }

    /**
     * 价格是否过大.
     * @param price 价格.
     * @return true, 是正常数字.
     */
    public static boolean isPriceOverLarge(Double price) {
        if (price != null) {
            return price > MAX_PRICE;
        }
        return false;
    }

    /**
     * 数量是否过大.
     * @param num 价格.
     * @return true, 是正常数字.
     */
    public static boolean isNumOverLarge(Double num) {
        if (num != null) {
            return num > MAX_NUM;
        }
        return false;
    }

    public static Double getMaxNum() {
        return MAX_NUM;
    }

    public static Double getMaxPrice() {
        return MAX_PRICE;
    }

    /**
     * 检查整数位掩码
     * @param status
     * @param bit
     * @return
     */
    public static boolean checkBitMask(short status, int bit) {
        int mask = 1 << bit;
        return (status & mask) > 0;
    }

    /**
     * 设置整数位掩码
     * @param status
     * @param bit
     * @return
     */
    public static int setBitMask(short status, int bit) {
        return status | (1 << bit);
    }

    /**
     * 翻转整数位掩码
     * @param status
     * @param bit
     * @return
     */
    public static int flipBitMask(short status, int bit) {
        int mask = 1 << bit;
        if ((status & mask) > 0) {
            return status & (~mask);
        } else {
            return status | mask;
        }
    }

    /**
     * 清除整数位掩码
     * @param status
     * @param bit
     * @return
     */
    public static int clearBitMask(short status, int bit) {
        return status & (~(1 << bit));
    }

	/**
	 * 将单位“元”转为“分”
	 * @return
	 */
	public static Integer  yuanToFen(Double num){
		if(num == null){
			num = 0d;
		}
		Double num_yuan = round(num);//保留两位小数
		NumberFormat format = NumberFormat.getInstance();
		// 默认情况下GroupingUsed属性为true,每三位数为一个分组，用英文半角逗号隔开，例如9,333,333
		format.setGroupingUsed(false);
		// 设置返回数的小数部分所允许的最大位数
		format.setMaximumFractionDigits(0);
		return Integer.valueOf(format.format(num_yuan * 100.0));//num_yuan * 100.0的结果小数点后全是0
	}

    /**
     * 对于小数点后为0的double类型，只输出整数部分
     * @param num
     * @return
     */
    public static String dTIIfNoPoint(Double num){
        double _num =  nullToZero(num);
        _num =  round(_num);
        if(Math.round(_num) - _num == 0){
            return String.valueOf((long)_num);
        }
        return String.valueOf(_num);
    }

    /**
     * 将单位“分”转为“元”
     * @param num
     * @return
     */
    public static Double fenToYuan(Integer num){
        Integer _num = (num == null ? 0 : num);
        double tmp =  BigDecimal.valueOf(Long.valueOf(_num)).divide(new BigDecimal(100)).doubleValue();
        return round(tmp);
    }

    public static boolean isEqualDouble(Double a, Double b) {
        BigDecimal data1 = new BigDecimal(a);
        BigDecimal data2 = new BigDecimal(b);
        return data1.compareTo(data2) == 0;
    }
}
