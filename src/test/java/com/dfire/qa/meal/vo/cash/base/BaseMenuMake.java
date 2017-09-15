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
 * 基础菜肴烧法.
 * @author <a href="mailto:xiaofenguser@163.com">黄晓峰</a>
 * @version $Revision$
 */
public abstract class BaseMenuMake extends Base{
	/**
	 * <code>序列ID</code>.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * <code>表名</code>.
	 */
	public static final String TABLE_NAME = "MENUMAKE";
	/**
	 * <code>菜肴ID对应的字段</code>.
	 */
	public static final String MENUID = "MENUID";
	/**
	 * <code>烧法ID对应的字段</code>.
	 */
	public static final String MAKEID = "MAKEID";
	/**
	 * <code>烧法加价对应的字段</code>.
	 */
	public static final String MAKEPRICE = "MAKEPRICE";
	/**
	 * <code>调价模式对应的字段</code>.
	 */
	public static final String MAKEPRICEMODE = "MAKEPRICEMODE";
	/**
	 * <code>顺序码对应的字段</code>.
	 */
	public static final String SORTCODE = "SORTCODE";
	/**
	 * <code>菜肴ID</code>.
	 */
	private String menuId;
	/**
	 * <code>烧法ID</code>.
	 */
	private String makeId;
	/**
	 * <code>烧法加价</code>.
	 */
	private Double makePrice;
	/**
	 * <code>调价模式</code>.
	 */
	private Short makePriceMode;
	/**
	 * <code>顺序码</code>.
	 */
	private Integer sortCode;
	
	/**
	 * 得到菜肴ID.
	 * @return 菜肴ID.
	 */
	public String getMenuId() {
		return menuId;
	}

	/**
	 * 设置菜肴ID.
	 * @param menuId 菜肴ID.
	 */
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	/**
	 * 得到烧法ID.
	 * @return 烧法ID.
	 */
	public String getMakeId() {
		return makeId;
	}

	/**
	 * 设置烧法ID.
	 * @param makeId 烧法ID.
	 */
	public void setMakeId(String makeId) {
		this.makeId = makeId;
	}
	/**
	 * 得到烧法加价.
	 * @return 烧法加价.
	 */
	public Double getMakePrice() {
		return makePrice;
	}

	/**
	 * 设置烧法加价.
	 * @param makePrice 烧法加价.
	 */
	public void setMakePrice(Double makePrice) {
		this.makePrice = makePrice;
	}
	/**
	 * 得到调价模式.
	 * @return 调价模式.
	 */
	public Short getMakePriceMode() {
		return makePriceMode;
	}

	/**
	 * 设置调价模式.
	 * @param makePriceMode 调价模式.
	 */
	public void setMakePriceMode(Short makePriceMode) {
		this.makePriceMode = makePriceMode;
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
}
