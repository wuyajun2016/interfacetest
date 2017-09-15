/**
 * 
 */
package com.dfire.qa.meal.vo.cash;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * 待处理订单详细信息.
 * 
 * <pre>
 * 适用于以下几种类型.
 * 101：火小二扫单点菜 
 * 102：火小二扫桌点菜
 * 131：火小二预订
 * 132：火小二外卖  
 * 1101，点菜宝同意火小二点菜
 * 1102，点菜宝同意火小二扫桌点菜
 * </pre>
 * 
 * @author <a href="mailto:rain999@gmail.com">黄晓峰</a>.
 * @version $Revision$.
 */
public class CustomerOrder<T extends WaitingOrder, V extends WaitingInstance> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 382428480412220199L;
	private static final List<Integer> TYPE = Arrays.asList(
			CloudConstants.Type.PO_ADD_INSTANCE_BY_SCAN_ORDER,
			CloudConstants.Type.PO_ADD_INSTANCE_BY_SCAN_SEAT,
			CloudConstants.Type.PO_RESERVE, CloudConstants.Type.PO_TAKEOUT,
			CloudConstants.Type.WAITER_PO_ADD_INSTANCE_BY_ORDER,
			CloudConstants.Type.WAITER_PO_ADD_INSTANCE_BY_SEAT);
	/**
	 * <code>待处理订单</code>.
	 */
	private T waitingOrder;
	/**
	 * <code>待处理订单菜肴</code>.
	 */
	private List<V> waitingInstances;
	
	/**
	 * <code>操作人Id</code>.
	 */
	private String operatorId;

	/**
	 * 请求人ID.
	 */
	private String customerRegisterId;

	private boolean isPrintOrder;// 下单是否自动打印订单
	private String printerIp;
	private Integer charNum;
	private String memberId;
	private String entityId;
	private boolean isAcceptEvaluation;//是否接受评价
	public CustomerOrder() {
		super();
	}

	public CustomerOrder(T waitingOrder, List<V> waitingInstances) {
		super();
		this.waitingOrder = waitingOrder;
		this.waitingInstances = waitingInstances;
	}

	public CustomerOrder(T waitingOrder, List<V> waitingInstances, String customerRegisterId) {
		super();
		this.waitingOrder = waitingOrder;
		this.waitingInstances = waitingInstances;
		this.customerRegisterId = customerRegisterId;
	}

	public CustomerOrder(T waitingOrder, List<V> waitingInstances, String operatorId, String customerRegisterId) {
		super();
		this.waitingOrder = waitingOrder;
		this.waitingInstances = waitingInstances;
		this.operatorId = operatorId;
		this.customerRegisterId = customerRegisterId;
	}

	public CustomerOrder(T waitingOrder, List<V> waitingInstances, String operatorId, String customerRegisterId, boolean isPrintOrder, String printerIp, Integer charNum) {
		super();
		this.waitingOrder = waitingOrder;
		this.waitingInstances = waitingInstances;
		this.operatorId = operatorId;
		this.customerRegisterId = customerRegisterId;
		this.isPrintOrder = isPrintOrder;
		this.printerIp = printerIp;
		this.charNum = charNum;
	}

	public String getCustomerRegisterId() {
		return customerRegisterId;
	}

	public void setCustomerRegisterId(String customerRegisterId) {
		this.customerRegisterId = customerRegisterId;
	}

	/**
	 * 得到待处理订单.
	 * 
	 * @return 待处理订单.
	 */
	public T getWaitingOrder() {
		return waitingOrder;
	}

	/**
	 * 设置待处理订单.
	 * 
	 * @param waitingOrder
	 *            待处理订单.
	 */
	public void setWaitingOrder(T waitingOrder) {
		this.waitingOrder = waitingOrder;
	}

	/**
	 * 得到待处理订单菜肴.
	 * 
	 * @return 待处理订单菜肴.
	 */
	public List<V> getWaitingInstances() {
		return waitingInstances;
	}

	/**
	 * 设置待处理订单菜肴.
	 * 
	 * @param waitingInstances
	 *            待处理订单菜肴.
	 */
	public void setWaitingInstances(List<V> waitingInstances) {
		this.waitingInstances = waitingInstances;
	}

	public List<Integer> types() {
		return TYPE;
	}
	/**
	 * 得到操作人Id.
	 * 
	 * @return 操作人Id.
	 */
	public String getOperatorId() {
		return operatorId;
	}
	/**
	 * 设置操作人Id.
	 * 
	 * @param operatorId
	 *            操作人Id.
	 */
	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public boolean isPrintOrder() {
		return isPrintOrder;
	}

	public void setPrintOrder(boolean isPrintOrder) {
		this.isPrintOrder = isPrintOrder;
	}

	public String getPrinterIp() {
		return printerIp;
	}

	public void setPrinterIp(String printerIp) {
		this.printerIp = printerIp;
	}

	public Integer getCharNum() {
		return charNum;
	}

	public void setCharNum(Integer charNum) {
		this.charNum = charNum;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public boolean isAcceptEvaluation() {
		return isAcceptEvaluation;
	}

	public void setAcceptEvaluation(boolean isAcceptEvaluation) {
		this.isAcceptEvaluation = isAcceptEvaluation;
	}
	
}
