/************************* 版权声明 **********************************
 * 版权所有：Copyright (c) 2011, ${year} 黄晓峰
 *
 * 工程名称：	钻木餐饮系统
 * 创建者： 黄晓峰 创建日期： ${date}
 * 创建记录：	创建类结构。
 *
 **/

package com.dfire.qa.meal.vo.cash;

import java.util.Map;

import com.dfire.qa.meal.vo.cash.base.BaseMenu;





/**
 * 菜肴.
 * @author <a href="mailto:xiaofenguser@163.com">黄晓峰</a>
 * @version $Revision$
 */
public class Menu extends BaseMenu{
	/**
	 * <code>序列ID</code>.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * <code>固定价</code>.
	 */
	public static final Short PRICE_MODE_FIX = 1;
	
	/**
	 * <code>浮动价</code>.
	 */
	public static final Short PRICE_MODE_TOTAL = 2;

	/** 普通菜. */
	public static final Short TYPE_NORMAL = 0;

	/** 套菜. */
	public static final Short TYPE_SUIT = 1;
	/**
	 * 得到显示的价格.
	 * @param menuTimePriceMap 菜肴分时价格.
	 * @return 显示价格.
	 */
	public Double getShowPrice(Map<String, Double> menuTimePriceMap) 
	{
		if (menuTimePriceMap != null && menuTimePriceMap.containsKey(getId())) 
		{
			return menuTimePriceMap.get(getId());
		}
		else
		{
			return getPrice();
		}
	}
	
	
	/**
	 * 用于加料数量的显示，只做临时使用，不保存在数据库中
	 */
	private Double num;
	
	public void setNum(Double num) {
		this.num = num;
	}
	
	public Double getNum() {
		return num;
	}
}
