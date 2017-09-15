package com.dfire.qa.meal.enums;

public enum Privilege {

	BIRTH("生日祝福", "birth_privilege_str"), MEMORY("纪念日祝福", "memory_privilege_str"), 
	COUPON("添加赠券", "coupon_privilege_str"), CUSTOMPRIVILEGE("自定义权限", "custom_privilege_str"),
	CARDPRIVILEGE("特权卡", "card_privilege_str");
	
	private String description;
	private String field;
	
	private Privilege(String description, String field){
		this.setDescription(description);
		this.setField(field);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
}
