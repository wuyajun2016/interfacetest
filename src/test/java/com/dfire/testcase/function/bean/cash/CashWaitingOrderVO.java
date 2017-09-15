package com.dfire.testcase.function.bean.cash;

/**
 *
 */
import java.io.Serializable;
import java.util.List;

/**
 * 待处理订单详细信息.
 */
public class CashWaitingOrderVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private WaitingOrder waitingOrder;
	/**
	 * 预付款折扣卡信息
	 */
	private CardDto cardDto;
	/**
	 * <code>待处理订单菜肴</code>.
	 */
	private List<WaitingInstance> waitingInstances;

	private List<PayOrder> payOrders;

	public WaitingOrder getWaitingOrder() {
		return waitingOrder;
	}

	public CardDto getCardDto() {
		return cardDto;
	}

	public List<PayOrder> getPayOrders() {
		return payOrders;
	}

	public void setWaitingOrder(WaitingOrder waitingOrder) {
		this.waitingOrder = waitingOrder;
	}

	public void setCardDto(CardDto cardDto) {
		this.cardDto = cardDto;
	}

	public void setPayOrders(List<PayOrder> payOrders) {
		this.payOrders = payOrders;
	}

	public List<WaitingInstance> getWaitingInstances() {
		return waitingInstances;
	}

	public void setWaitingInstances(List<WaitingInstance> waitingInstances) {
		this.waitingInstances = waitingInstances;
	}

	public List<WaitingInstance> createWaitingInstances() {
		// if (getCashWaitingInstanceVo() == null) {
		// return null;
		// }
		// List<WaitingInstance> waitingInstances = new
		// ArrayList<WaitingInstance>();
		// for (CashWaitingInstanceVo cashWaitingInstance :
		// getCashWaitingInstanceVo()) {
		// waitingInstances.add(cashWaitingInstance.createWaitingInstance());
		// }
		return waitingInstances;
	}

}
