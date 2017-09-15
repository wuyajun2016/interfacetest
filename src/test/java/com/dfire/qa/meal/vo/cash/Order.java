/************************* 版权声明 **********************************
 * 版权所有：Copyright (c) 2011, ${year} 黄晓峰
 *
 * 工程名称：	钻木餐饮系统
 * 创建者： 黄晓峰 创建日期： ${date}
 * 创建记录：	创建类结构。
 *
 **/

package com.dfire.qa.meal.vo.cash;

import com.dfire.qa.meal.vo.cash.base.BaseOrder;
import com.dfire.test.util.StringUtil;


/**
 * 订单.
 * @author <a href="mailto:xiaofenguser@163.com">黄晓峰</a>
 * @version $Revision$
 */
public class Order extends BaseOrder{
	
	/**
	 * <code>序列ID</code>.
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * <code>正常状态</code>.
	 */
	public final static Short STATUS_COMMON = (short)1;
	/**
	 * <code>并单状态</code>.
	 */
	public final static Short STATUS_MERGE = (short)2;
	/**
	 * <code>撤消状态</code>.
	 */
	public final static Short STATUS_CANCEL = (short)3;
	
	/**
	 * <code>结账状态</code>.
	 */
	public final static Short STATUS_END = (short)4;
	/**
	 * <code>排队状态</code>.
	 */
	public final static Short STATUS_QUEUE = (short)9;
	/**
	 * <code>下单状态</code>.
	 */
	public final static Short STATUS_CONFIRMED = (short)10;
	/**
	 * <code>未下单状态</code>.
	 */
	public final static Short STATUS_UNCONFIRMED = (short)11;
	
	/**
	 * <code>发送状态:0未送货</code>.
	 */
	public final static Short SENDSTATUS_NO=(short)0;
	
	/**
	 * <code>发送状态:1已送货</code>.
	 */
	public final static Short SENDSTATUS_DELIEVE=(short)1;

	/**
	 * <code>发送状态:2已送达</code>.
	 */
	public final static Short SENDSTATUS_DELIVERED=(short)2;
	/**
	 * <code>正常开单</code>.
	 */
	public final static Short KIND_NORMAL = (short)1;
	/**
	 * <code>预定开单</code>.
	 */
	public final static Short KIND_BOOK = (short)2;
	/**
	 * <code>队列开单</code>.
	 */
	public final static Short KIND_QUEEN = (short)3;
	/**
	 * <code>外卖开单</code>.
	 */
	public final static Short KIND_SALE = (short)4;
	
	private String instances;
	
	private Short isPrinted;
	/*
	 * <code>订单原状态（在反结账时候记录，以便更新order缓存）</code>.
	 */
	private Short oldStatus;
	
	public String getInstances() {
		return instances;
	}
	/**
	 * 设置[instances].
	 * @param instances [instances].
	 */
	public void setInstances(String instances) {
		this.instances = instances;
	}
	/**
	 * 得到[isPrinted].
	 * @return [isPrinted].
	 */
	public Short getIsPrinted() {
		return isPrinted;
	}
	/**
	 * 设置[isPrinted].
	 * @param isPrinted [isPrinted].
	 */
	public void setIsPrinted(Short isPrinted) {
		this.isPrinted = isPrinted;
	}
	//下单状态
	public boolean getIsConfirmed()
	{
		return StringUtil.isNotEmpty(getInnerCode());
	}
	/**
	 * 得到[oldStatus].
	 * @return [oldStatus].
	 */
	public Short getOldStatus() {
		return oldStatus;
	}
	/**
	 * 设置oldStatus
	 * @param oldStatus
	 */
	public void setOldStatus(Short oldStatus) {
		this.oldStatus = oldStatus;
	}

}
