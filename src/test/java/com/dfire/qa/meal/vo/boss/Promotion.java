package com.dfire.qa.meal.vo.boss;

public class Promotion {
	
	private String discountCondition;
    private String title;
    private String discountType;
    
    private Boolean isBrand;
    private String discountRate;
    private Integer discountActivity;
    
    private Integer discountAll;
    private Integer isSpecificTime;
    private Integer maxReduceAmount;
    
    private Integer hasCanNotUseDate;
    private Integer useRangeType;
    private Integer totalSaveMoney;
    
    private Integer isSpecificDate;
    private Integer reduceAmount;
    private Boolean memberGift;
    
    private Long specificEndDate;
    private Integer totalOrderNum;
    private Integer reduceCondition;
    
    private Integer activityStatus;
    private Integer totalOrderMoney;
    private Integer reduceActivity;
    
    private Integer discountLeastPrice;
    private Integer isSpecificWeekDay;
    private Integer reduceLeastPrice;
    
    private Long specificStartDate;

    
    
    
    public Promotion(){
    	
    	this.discountCondition = "0";
    	this.title = "大促";
    	this.discountType = "0";
    	
    	this.isBrand = false;
    	this.discountRate = "99";
    	this.discountActivity = 1;
    	
    	this.discountAll = 0;
    	this.isSpecificTime = 0;
    	this.maxReduceAmount = 0;
    	
    	this.hasCanNotUseDate = 0;
    	this.useRangeType = 1;
    	this.totalSaveMoney = 0;
    	
    	this.isSpecificDate = 1;
    	this.reduceAmount = 0;
    	this.memberGift = false;
    	
    	this.totalOrderNum = 0;
    	this.reduceCondition = 0;
    	this.activityStatus = 0;
    	
    	this.totalOrderMoney = 0;
    	this.reduceActivity = 0;
    	this.discountLeastPrice = 0;
    	
    	this.isSpecificWeekDay = 0;
    	this.reduceLeastPrice = 0;
    	
    	this.specificStartDate = System.currentTimeMillis();
    	this.specificEndDate = System.currentTimeMillis() + (long)27647990;
    }
    
    
    
    
	public String getDiscountCondition() {
		return discountCondition;
	}

	public void setDiscountCondition(String discountCondition) {
		this.discountCondition = discountCondition;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDiscountType() {
		return discountType;
	}

	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}

	public Boolean getIsBrand() {
		return isBrand;
	}

	public void setIsBrand(Boolean isBrand) {
		this.isBrand = isBrand;
	}

	public String getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(String discountRate) {
		this.discountRate = discountRate;
	}

	public Integer getDiscountActivity() {
		return discountActivity;
	}

	public void setDiscountActivity(Integer discountActivity) {
		this.discountActivity = discountActivity;
	}

	public Integer getDiscountAll() {
		return discountAll;
	}

	public void setDiscountAll(Integer discountAll) {
		this.discountAll = discountAll;
	}

	public Integer getIsSpecificTime() {
		return isSpecificTime;
	}

	public void setIsSpecificTime(Integer isSpecificTime) {
		this.isSpecificTime = isSpecificTime;
	}

	public Integer getMaxReduceAmount() {
		return maxReduceAmount;
	}

	public void setMaxReduceAmount(Integer maxReduceAmount) {
		this.maxReduceAmount = maxReduceAmount;
	}

	public Integer getHasCanNotUseDate() {
		return hasCanNotUseDate;
	}

	public void setHasCanNotUseDate(Integer hasCanNotUseDate) {
		this.hasCanNotUseDate = hasCanNotUseDate;
	}

	public Integer getUseRangeType() {
		return useRangeType;
	}

	public void setUseRangeType(Integer useRangeType) {
		this.useRangeType = useRangeType;
	}

	public Integer getTotalSaveMoney() {
		return totalSaveMoney;
	}

	public void setTotalSaveMoney(Integer totalSaveMoney) {
		this.totalSaveMoney = totalSaveMoney;
	}

	public Integer getIsSpecificDate() {
		return isSpecificDate;
	}

	public void setIsSpecificDate(Integer isSpecificDate) {
		this.isSpecificDate = isSpecificDate;
	}

	public Integer getReduceAmount() {
		return reduceAmount;
	}

	public void setReduceAmount(Integer reduceAmount) {
		this.reduceAmount = reduceAmount;
	}

	public Boolean getMemberGift() {
		return memberGift;
	}

	public void setMemberGift(Boolean memberGift) {
		this.memberGift = memberGift;
	}

	public Long getSpecificEndDate() {
		return specificEndDate;
	}

	public void setSpecificEndDate(Long specificEndDate) {
		this.specificEndDate = specificEndDate;
	}

	public Integer getTotalOrderNum() {
		return totalOrderNum;
	}

	public void setTotalOrderNum(Integer totalOrderNum) {
		this.totalOrderNum = totalOrderNum;
	}

	public Integer getReduceCondition() {
		return reduceCondition;
	}

	public void setReduceCondition(Integer reduceCondition) {
		this.reduceCondition = reduceCondition;
	}

	public Integer getActivityStatus() {
		return activityStatus;
	}

	public void setActivityStatus(Integer activityStatus) {
		this.activityStatus = activityStatus;
	}

	public Integer getTotalOrderMoney() {
		return totalOrderMoney;
	}

	public void setTotalOrderMoney(Integer totalOrderMoney) {
		this.totalOrderMoney = totalOrderMoney;
	}

	public Integer getReduceActivity() {
		return reduceActivity;
	}

	public void setReduceActivity(Integer reduceActivity) {
		this.reduceActivity = reduceActivity;
	}

	public Integer getDiscountLeastPrice() {
		return discountLeastPrice;
	}

	public void setDiscountLeastPrice(Integer discountLeastPrice) {
		this.discountLeastPrice = discountLeastPrice;
	}

	public Integer getIsSpecificWeekDay() {
		return isSpecificWeekDay;
	}

	public void setIsSpecificWeekDay(Integer isSpecificWeekDay) {
		this.isSpecificWeekDay = isSpecificWeekDay;
	}

	public Integer getReduceLeastPrice() {
		return reduceLeastPrice;
	}

	public void setReduceLeastPrice(Integer reduceLeastPrice) {
		this.reduceLeastPrice = reduceLeastPrice;
	}

	public Long getSpecificStartDate() {
		return specificStartDate;
	}

	public void setSpecificStartDate(Long specificStartDate) {
		this.specificStartDate = specificStartDate;
	}

}
