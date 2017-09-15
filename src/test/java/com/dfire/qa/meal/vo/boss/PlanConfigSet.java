package com.dfire.qa.meal.vo.boss;

public class PlanConfigSet {
	
	private String labelId;
    private String maxNumber;
    private String maxSwitch;
    
    private String minNumber;
    private String minSwitch;
    
    public PlanConfigSet(String labelId){
    	
    	this.labelId = labelId;
    	this.maxNumber = "3";
    	this.maxSwitch = "1";
    	
    	this.minNumber = "1";
    	this.minSwitch = "1";
    	
    }
    
	public String getLabelId() {
		return labelId;
	}
	public void setLabelId(String labelId) {
		this.labelId = labelId;
	}
	public String getMaxNumber() {
		return maxNumber;
	}
	public void setMaxNumber(String maxNumber) {
		this.maxNumber = maxNumber;
	}
	public String getMaxSwitch() {
		return maxSwitch;
	}
	public void setMaxSwitch(String maxSwitch) {
		this.maxSwitch = maxSwitch;
	}
	public String getMinNumber() {
		return minNumber;
	}
	public void setMinNumber(String minNumber) {
		this.minNumber = minNumber;
	}
	public String getMinSwitch() {
		return minSwitch;
	}
	public void setMinSwitch(String minSwitch) {
		this.minSwitch = minSwitch;
	}
    
    

}
