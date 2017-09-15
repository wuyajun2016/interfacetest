package com.dfire.qa.meal.vo.boss;

import java.util.ArrayList;
import java.util.List;

public class RechargeRule {
	
	private Integer giftDegree;
	private List<String> giveCouponList;
	private Integer condition;
	
	private String kindCardId;
	private Integer giveCouponCount;
	private String kindCardName;
	
	private Integer rule;
	
	public RechargeRule(String kindCardId, String kindCardName){
		
		this.giftDegree = 10;
		this.giveCouponList = new ArrayList<String>();
		this.condition = 1000;
		
		this.kindCardId = kindCardId;
		this.giveCouponCount = 0;
		
		this.kindCardName = kindCardName;
		this.rule = 0;
		
	}

	public Integer getGiftDegree() {
		return giftDegree;
	}

	public void setGiftDegree(Integer giftDegree) {
		this.giftDegree = giftDegree;
	}

	public List<String> getGiveCouponList() {
		return giveCouponList;
	}

	public void setGiveCouponList(List<String> giveCouponList) {
		this.giveCouponList = giveCouponList;
	}

	public Integer getCondition() {
		return condition;
	}

	public void setCondition(Integer condition) {
		this.condition = condition;
	}

	public String getKindCardId() {
		return kindCardId;
	}

	public void setKindCardId(String kindCardId) {
		this.kindCardId = kindCardId;
	}

	public Integer getGiveCouponCount() {
		return giveCouponCount;
	}

	public void setGiveCouponCount(Integer giveCouponCount) {
		this.giveCouponCount = giveCouponCount;
	}

	public String getKindCardName() {
		return kindCardName;
	}

	public void setKindCardName(String kindCardName) {
		this.kindCardName = kindCardName;
	}

	public Integer getRule() {
		return rule;
	}

	public void setRule(Integer rule) {
		this.rule = rule;
	}
	

}
