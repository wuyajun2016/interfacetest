package com.dfire.qa.meal.enums;

public enum Module {
	
	addDish("加菜动作"), prePay("掌柜端预付款设置");
	
	private String description;
	
	private Module(String description){
		this.setDescription(description);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
