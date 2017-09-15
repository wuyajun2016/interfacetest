/************************* 版权声明 **********************************
 * 版权所有：Copyright (c) 2011, ${year} 黄晓峰
 *
 * 工程名称：	钻木餐饮系统
 * 创建者： 黄晓峰 创建日期： ${date}
 * 创建记录：	创建类结构。
 *
 **/

package com.dfire.testcase.function.bean.cash;

import com.dfire.testcase.function.bean.cash.base.BaseWaitingOrder;

/**
 * 待下单数据.
 * 
 * @author <a href="mailto:xiaofenguser@163.com">黄晓峰</a>
 * @version $Revision$
 */
public class WaitingOrder extends BaseWaitingOrder {
	/**
	 * <code>序列ID</code>.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * <code>无审核状态</code>.
	 */
	public static final short AUDIT_STATUS_NO = 0;
	/**
	 * <code>审核状态：下单审核</code>.
	 */
	public static final short AUDIT_STATUS_NEW = 1;

	/**
	 * <code>审核状态: 取消审核</code>.
	 */
	public static final short AUDIT_STATUS_CANCEL = 2;

	public static final short RESERVESTATUS_START = 1;

	public static final short RESERVESTATUS_CANCEL = 3;

	public static final short STATUS_WAIT_CONFIRM = 2;
	public static final short STATUS_CONFIRMED = 4;// 生效未下单
	public static final short STATUS_CANCEL = 3;
	// public static final short STATUS_NORMAL=4;
	public static final short STATUS_ORDER = 5;// 生效已下单
	public static final short STATUS_DELIVER = 6;// 送货中
	public static final short STATUS_END = 7;// 完结

	/**
	 * 在线支付
	 */
	public static final short PAYMODE_ONLINE = 1;
	/**
	 * 货到付款
	 */
	public static final short PAYMODE_COD = 2;

	/**
	 * 订单类型--预定
	 */
	public static final short KIND_RESERVE = 1;
	/**
	 * 订单类型--外卖
	 */
	public static final short KIND_TAKEOUT = 2;
	
	/**
	 * 扫码加菜
	 */
	public static final short KIND_SCAN_CODE = 3;

	/**
	 * 扫桌位直接开桌
	 */
	public static final short KIND_ORDER_SETA_CODE = 4;
	
	/**
	 * 订单来源，taobao点点
	 */
	public static final short ORDER_FROM_TAOBAO = 1;
	
	/**
	 * 订单来源，卡包
	 */
	public static final short ORDER_FROM_CARD = 2;
	/**
	 * 订单来源，服务生
	 */
	public static final short ORDER_FROM_WAITER = 3;
	/**
     * 微信
     */
    public static final short ORDER_FROM_WEIXIN=40;


	/**
	 * <code>取消订单原因</code>.
	 */
	private String cancelMemo;

	/**
	 * <code>消息Id</code>.
	 */
	private String messageId;

	private Short reserveFrom;

	/**
	 * 座位
	 */
	private String seatName;
	
	/**
     * 美团显示ID
     */
    private String viewId;

    /**
     * 是否开发票
     */
    private Boolean hasInvoiced;

    /**
     * 发票抬头
     */
    private String invoiceTitle;

    /**

     * 是否第三方配送
     */
    private Boolean isThirdShipping;

    /**
     * 配送员姓名
     */
    private String courierName;

    /**
     * 配送员电话
     */
    private String courierPhone;

    /**
     * 当天流水
     */
    private String daySeq;

    /**
     * 其它额外信息,比如优化活动
     */
    private String extras;
    
    /**
     * 附加信息
     */
    private String ext;

	/**
	 * 得到取消订单原因.
	 * 
	 * @return 取消订单原因.
	 */
	public String getCancelMemo() {
		return cancelMemo;
	}

	/**
	 * 设置取消订单原因.
	 * 
	 * @param cancelMemo
	 *            取消订单原因.
	 */
	public void setCancelMemo(String cancelMemo) {
		this.cancelMemo = cancelMemo;
	}

	/**
	 * 得到[reserveFrom].
	 * 
	 * @return [reserveFrom].
	 */
	public Short getReserveFrom() {
		return reserveFrom;
	}

	/**
	 * 设置[reserveFrom].
	 * 
	 * @param reserveFrom
	 *            [reserveFrom].
	 */
	public void setReserveFrom(Short reserveFrom) {
		this.reserveFrom = reserveFrom;
	}

	/**
	 * 得到[messageId].
	 * 
	 * @return [messageId].
	 */
	public String getMessageId() {
		return messageId;
	}

	/**
	 * 设置[messageId].
	 * 
	 * @param messageId
	 *            [messageId].
	 */
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getSeatName() {
		return seatName;
	}

	public void setSeatName(String seatName) {
		this.seatName = seatName;
	}
	
	public Boolean getHasInvoiced() {
        return hasInvoiced;
    }

    public void setHasInvoiced(Boolean hasInvoiced) {
        this.hasInvoiced = hasInvoiced;
    }

    public String getInvoiceTitle() {
        return invoiceTitle;
    }

    public void setInvoiceTitle(String invoiceTitle) {
        this.invoiceTitle = invoiceTitle;
    }

    public Boolean getIsThirdShipping() {
       return isThirdShipping;
    }

    public void setIsThirdShipping(Boolean thirdShipping) {
        isThirdShipping = thirdShipping;
    }

    public String getDaySeq() {
        return daySeq != null ? daySeq : "";
    }

    public void setDaySeq(String daySeq) {
        this.daySeq = daySeq;
    }

    public String getExtras() {
        return extras;
    }

    public void setExtras(String extras) {
        this.extras = extras;
    }

    public String getViewId() {
        return viewId;
    }

    public void setViewId(String viewId) {
        this.viewId = viewId;
    }

    public String getCourierName() {
        return courierName != null ? courierName : "";
    }

    public void setCourierName(String courierName) {
        this.courierName = courierName;
    }

    public String getCourierPhone() {
        return courierPhone != null ? courierPhone : "";
    }

    public void setCourierPhone(String courierPhone) {
        this.courierPhone = courierPhone;
    }
    
    public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}
}
