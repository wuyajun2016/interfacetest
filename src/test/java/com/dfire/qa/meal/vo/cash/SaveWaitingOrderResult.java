package com.dfire.qa.meal.vo.cash;

import java.util.List;

/**
 * 描述:保存waitingorder返回对象. 日期:2015-05-26 作者:坚果
 */
public class SaveWaitingOrderResult {

	private Short cloudTaskProcessResult;

	private List<String> instanceIds;
	/**
	 * 判断是否加菜
	 */
	private boolean isAdd;

	private String orderId;
	
	private String errorMsg;

	private String totalPayId;//预付款单据自动结账完毕功能添加

	public SaveWaitingOrderResult(Short cloudTaskProcessResult, List<String> instanceIds) {
		super();
		this.cloudTaskProcessResult = cloudTaskProcessResult;
		this.instanceIds = instanceIds;
	}

	public SaveWaitingOrderResult(Short cloudTaskProcessResult, List<String> instanceIds, boolean isAdd) {
		super();
		this.cloudTaskProcessResult = cloudTaskProcessResult;
		this.instanceIds = instanceIds;
		this.isAdd = isAdd;
	}
	public Short getCloudTaskProcessResult() {
		return cloudTaskProcessResult;
	}

	public void setCloudTaskProcessResult(Short cloudTaskProcessResult) {
		this.cloudTaskProcessResult = cloudTaskProcessResult;
	}

	public List<String> getInstanceIds() {
		return instanceIds;
	}

	public void setInstanceIds(List<String> instanceIds) {
		this.instanceIds = instanceIds;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setAdd(boolean isAdd) {
		this.isAdd = isAdd;
	}

	public boolean isAdd() {
		return isAdd;
	}
	
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}

	public String getTotalPayId() {
		return totalPayId;
	}

	public void setTotalPayId(String totalPayId) {
		this.totalPayId = totalPayId;
	}
}
