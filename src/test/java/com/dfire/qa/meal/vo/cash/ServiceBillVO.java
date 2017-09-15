package com.dfire.qa.meal.vo.cash;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 帐单显示VO.
 * 
 * @author <a href="mailto:zxh1000@163.com">张向华</a>.
 * @version $Revision$.
 */
public class ServiceBillVO implements Serializable {
	/**
	 * <code>[serialVersionUID]</code>.
	 */
	private static final long serialVersionUID = -6366073665533386270L;
	/**
	 * id(只在向钻木上传数据的时候使用，保存的是totalpayid)
	 */
	private String id;
	/**
	 * <code>原始消费金额</code>.
	 */
	private Double originAmount;
	/**
	 * <code>原始服务费金额</code>.
	 */
	private Double originServiceCharge;

	/**
	 * <code>原始最低消费</code>.
	 */
	private Double originLeastAmount;
	/**
	 * <code>折后消费金额</code>.
	 */
	private Double agioAmount;
	/**
	 * <code>折后服务费</code>.
	 */
	private Double agioServiceCharge;
	/**
	 * <code>折后最低消费</code>.
	 */
	private Double agioLeastAmount;

	/**
	 * <code>原始应收金额</code>.
	 */
	private Double originReceivablesAmount = 0d;

	/**
	 * <code>折后应收金额</code>.
	 */
	private Double agioReceivablesAmount = 0d;

	/**
	 * <code>最终应收金额</code>.
	 */
	private Double finalAmount;

	/**
	 * <code>原始总金额</code>.
	 */
	private Double originTotal;

	/**
	 * <code>折后总金额</code>.
	 */
	private Double agioTotal;

	/**
	 * <code>预付金额</code>.
	 */
	private Double reserveAmount;
	
	/**
	 * <code>外送费</code>.
	 */
	private Double outFee;

	/**
	 * <code>是否使用收银优惠</code>.
	 */
	private Short useCashPromotion;
	/**
	 * <code>操作时间</code>.
	 */
	private long opTime;

	
	/**
	 * 得到预付金额.
	 * @return 预付金额.
	 */
	public Double getReserveAmount() {
		return reserveAmount;
	}

	/**
	 * 设置预付金额.
	 * @param reserveAmount 预付金额.
	 */
	public void setReserveAmount(Double reserveAmount) {
		this.reserveAmount = reserveAmount;
	}

	/**
	 * 得到折扣金额.
	 * 
	 * @return 折扣金额.
	 */
	public Double getDiscountAmount() {
		return new BigDecimal(getOriginReceivablesAmount() - getFinalAmount())
				.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 得到原始消费金额.
	 * 
	 * @return 原始消费金额.
	 */
	public Double getOriginAmount() {
		return originAmount;
	}

	/**
	 * 设置原始消费金额.
	 * 
	 * @param originAmount
	 *            原始消费金额.
	 */
	public void setOriginAmount(Double originAmount) {
		this.originAmount = originAmount;
	}

	/**
	 * 得到原始服务费金额.
	 * 
	 * @return 原始服务费金额.
	 */
	public Double getOriginServiceCharge() {
		return originServiceCharge;
	}

	/**
	 * 设置原始服务费金额.
	 * 
	 * @param originServiceCharge
	 *            原始服务费金额.
	 */
	public void setOriginServiceCharge(Double originServiceCharge) {
		this.originServiceCharge = originServiceCharge;
	}

	/**
	 * 得到原始最低消费.
	 * 
	 * @return 原始最低消费.
	 */
	public Double getOriginLeastAmount() {
		return originLeastAmount;
	}

	/**
	 * 设置原始最低消费.
	 * 
	 * @param originLeastAmount
	 *            原始最低消费.
	 */
	public void setOriginLeastAmount(Double originLeastAmount) {
		this.originLeastAmount = originLeastAmount;
	}

	/**
	 * 得到折后消费金额.
	 * 
	 * @return 折后消费金额.
	 */
	public Double getAgioAmount() {
		return agioAmount;
	}

	/**
	 * 设置折后消费金额.
	 * 
	 * @param agioAmount
	 *            折后消费金额.
	 */
	public void setAgioAmount(Double agioAmount) {
		this.agioAmount = agioAmount;
	}

	/**
	 * 得到折后服务费.
	 * 
	 * @return 折后服务费.
	 */
	public Double getAgioServiceCharge() {
		return agioServiceCharge;
	}

	/**
	 * 设置折后服务费.
	 * 
	 * @param agioServiceCharge
	 *            折后服务费.
	 */
	public void setAgioServiceCharge(Double agioServiceCharge) {
		this.agioServiceCharge = agioServiceCharge;
	}

	/**
	 * 得到折后最低消费.
	 * 
	 * @return 折后最低消费.
	 */
	public Double getAgioLeastAmount() {
		return agioLeastAmount;
	}

	/**
	 * 设置折后最低消费.
	 * 
	 * @param agioLeastAmount
	 *            折后最低消费.
	 */
	public void setAgioLeastAmount(Double agioLeastAmount) {
		this.agioLeastAmount = agioLeastAmount;
	}

	/**
	 * 得到原始应收金额.
	 * 
	 * @return 原始应收金额.
	 */
	public Double getOriginReceivablesAmount() {
		return originReceivablesAmount;
	}

	/**
	 * 设置原始应收金额.
	 * 
	 * @param originReceivablesAmount
	 *            原始应收金额.
	 */
	public void setOriginReceivablesAmount(Double originReceivablesAmount) {
		this.originReceivablesAmount = originReceivablesAmount;
	}

	/**
	 * 得到折后应收金额.
	 * 
	 * @return 折后应收金额.
	 */
	public Double getAgioReceivablesAmount() {
		return agioReceivablesAmount;
	}

	/**
	 * 设置折后应收金额.
	 * 
	 * @param agioReceivablesAmount
	 *            折后应收金额.
	 */
	public void setAgioReceivablesAmount(Double agioReceivablesAmount) {
		this.agioReceivablesAmount = agioReceivablesAmount;
	}

	/**
	 * 得到最终应收金额.
	 * 
	 * @return 最终应收金额.
	 */
	public Double getFinalAmount() {
		return finalAmount;
	}

	/**
	 * 设置最终应收金额.
	 * 
	 * @param finalAmount
	 *            最终应收金额.
	 */
	public void setFinalAmount(Double finalAmount) {
		this.finalAmount = finalAmount;
	}

	/**
	 * 得到原始总金额.
	 * 
	 * @return 原始总金额.
	 */
	public Double getOriginTotal() {
		return originTotal;
	}

	/**
	 * 设置原始总金额.
	 * 
	 * @param originTotal
	 *            原始总金额.
	 */
	public void setOriginTotal(Double originTotal) {
		this.originTotal = originTotal;
	}

	/**
	 * 得到折后总金额.
	 * 
	 * @return 折后总金额.
	 */
	public Double getAgioTotal() {
		return agioTotal;
	}

	/**
	 * 设置折后总金额.
	 * 
	 * @param agioTotal
	 *            折后总金额.
	 */
	public void setAgioTotal(Double agioTotal) {
		this.agioTotal = agioTotal;
	}

	/**
	 * 得到外送费.
	 * 
	 * @return 外送费.
	 */
	public Double getOutFee() {
		return outFee;
	}

	/**
	 * 设置外送费.
	 * 
	 * @param outFee
	 *            外送费.
	 */
	public void setOutFee(Double outFee) {
		this.outFee = outFee;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 得到是否使用收银优惠.
	 *
	 * @return 外送费.
	 */
	public Short getUseCashPromotion() {
		return useCashPromotion;
	}
	/**
	 * 设置是否使用收银优惠.
	 *
	 * @param useCashPromotion
	 *            是否使用收银优惠.
	 */
	public void setUseCashPromotion(Short useCashPromotion) {
		this.useCashPromotion = useCashPromotion;
	}
	/**
	 * 得到操作时间.
	 *
	 * @return opTime 操作时间.
	 */
	public long getOpTime() {
		return opTime;
	}
	/**
	 * 设置操作时间.
	 *
	 * @param opTime
	 *            操作时间.
	 */
	public void setOpTime(long opTime) {
		this.opTime = opTime;
	}

	
}
