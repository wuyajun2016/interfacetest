package com.dfire.qa.meal.vo.boss;

public class SelfRecharge {
	
	private String kindCardId;
	
	private Integer isSelfRecharge;

	
	public SelfRecharge(String kindCardId, Integer isSelfRecharge){
		this.kindCardId = kindCardId;
		this.isSelfRecharge = isSelfRecharge;
	}
	
	public String getKindCardId() {
		return kindCardId;
	}

	public void setKindCardId(String kindCardId) {
		this.kindCardId = kindCardId;
	}

	public Integer getIsSelfRecharge() {
		return isSelfRecharge;
	}

	public void setIsSelfRecharge(Integer isSelfRecharge) {
		this.isSelfRecharge = isSelfRecharge;
	}
	
	

}
