package com.dfire.testcase.function.bean.cash;

import java.util.List;

public class OrderInfoVo {
	private Order order;
	
	private List<Instance> instances;
	
	private String seatCode;
	
	public OrderInfoVo() {
		// TODO Auto-generated constructor stub
	}
	
	public OrderInfoVo(Order order,List<Instance> instances,String seatCode){
		this.order = order;
		this.instances = instances;
		this.seatCode = seatCode;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public List<Instance> getInstances() {
		return instances;
	}

	public void setInstances(List<Instance> instances) {
		this.instances = instances;
	}

	public String getSeatCode() {
		return seatCode;
	}

	public void setSeatCode(String seatCode) {
		this.seatCode = seatCode;
	}
}
