package com.dfire.qa.meal.vo.boss;

public class BaseCoupon {
		
	private Integer isPublished = 1; 
	private Long  startDate = System.currentTimeMillis();
	private Long endDate = System.currentTimeMillis() + (long)27647990;
	
	private Boolean brandCoupon = false;
	private Integer isSpecificWeekDay = 0;
	private Integer discountAll = 0;
	
	private Integer effectiveHour = 0;
	private Integer orderLimit = 0;
	private Integer useCondition = 0;
	
	private Integer useRangeType = 1;
	private Integer expireDays = 30;
	private Integer permitAutoClaim = 1;
	
	private Integer discountType = 0;
	private Integer leastPrice = 0;
	
	private Integer memberGift = 0;
	private Integer usedNum = 0;

	
	private Integer couponCount = 0;
	private Integer hasNoCouponDate = 0;
	private Integer expireType = 0;
	
	private Integer isSpecificTime = 0;
	
	private Integer isAutoSend = 1;

	
	private Integer selectedFlag = 0;   
	private Integer couponStatus = 0;
	private Integer deliveredNum = 0;
	
	
	
	public Integer getIsPublished() {
		return isPublished;
	}
	public void setIsPublished(Integer isPublished) {
		this.isPublished = isPublished;
	}
	public Long getStartDate() {
		return startDate;
	}
	public void setStartDate(Long startDate) {
		this.startDate = startDate;
	}
	public Long getEndDate() {
		return endDate;
	}
	public void setEndDate(Long endDate) {
		this.endDate = endDate;
	}
	public Boolean getBrandCoupon() {
		return brandCoupon;
	}
	public void setBrandCoupon(Boolean brandCoupon) {
		this.brandCoupon = brandCoupon;
	}
	public Integer getIsSpecificWeekDay() {
		return isSpecificWeekDay;
	}
	public void setIsSpecificWeekDay(Integer isSpecificWeekDay) {
		this.isSpecificWeekDay = isSpecificWeekDay;
	}
	public Integer getDiscountAll() {
		return discountAll;
	}
	public void setDiscountAll(Integer discountAll) {
		this.discountAll = discountAll;
	}
	public Integer getEffectiveHour() {
		return effectiveHour;
	}
	public void setEffectiveHour(Integer effectiveHour) {
		this.effectiveHour = effectiveHour;
	}
	public Integer getOrderLimit() {
		return orderLimit;
	}
	public void setOrderLimit(Integer orderLimit) {
		this.orderLimit = orderLimit;
	}
	public Integer getUseCondition() {
		return useCondition;
	}
	public void setUseCondition(Integer useCondition) {
		this.useCondition = useCondition;
	}
	public Integer getUseRangeType() {
		return useRangeType;
	}
	public void setUseRangeType(Integer useRangeType) {
		this.useRangeType = useRangeType;
	}
	public Integer getExpireDays() {
		return expireDays;
	}
	public void setExpireDays(Integer expireDays) {
		this.expireDays = expireDays;
	}
	public Integer getPermitAutoClaim() {
		return permitAutoClaim;
	}
	public void setPermitAutoClaim(Integer permitAutoClaim) {
		this.permitAutoClaim = permitAutoClaim;
	}
	public Integer getDiscountType() {
		return discountType;
	}
	public void setDiscountType(Integer discountType) {
		this.discountType = discountType;
	}
	public Integer getLeastPrice() {
		return leastPrice;
	}
	public void setLeastPrice(Integer leastPrice) {
		this.leastPrice = leastPrice;
	}
	public Integer getMemberGift() {
		return memberGift;
	}
	public void setMemberGift(Integer memberGift) {
		this.memberGift = memberGift;
	}
	public Integer getUsedNum() {
		return usedNum;
	}
	public void setUsedNum(Integer usedNum) {
		this.usedNum = usedNum;
	}
	public Integer getCouponCount() {
		return couponCount;
	}
	public void setCouponCount(Integer couponCount) {
		this.couponCount = couponCount;
	}
	public Integer getHasNoCouponDate() {
		return hasNoCouponDate;
	}
	public void setHasNoCouponDate(Integer hasNoCouponDate) {
		this.hasNoCouponDate = hasNoCouponDate;
	}
	public Integer getExpireType() {
		return expireType;
	}
	public void setExpireType(Integer expireType) {
		this.expireType = expireType;
	}
	public Integer getIsSpecificTime() {
		return isSpecificTime;
	}
	public void setIsSpecificTime(Integer isSpecificTime) {
		this.isSpecificTime = isSpecificTime;
	}

	public Integer getIsAutoSend() {
		return isAutoSend;
	}
	public void setIsAutoSend(Integer isAutoSend) {
		this.isAutoSend = isAutoSend;
	}
	public Integer getSelectedFlag() {
		return selectedFlag;
	}
	public void setSelectedFlag(Integer selectedFlag) {
		this.selectedFlag = selectedFlag;
	}
	public Integer getCouponStatus() {
		return couponStatus;
	}
	public void setCouponStatus(Integer couponStatus) {
		this.couponStatus = couponStatus;
	}
	public Integer getDeliveredNum() {
		return deliveredNum;
	}
	public void setDeliveredNum(Integer deliveredNum) {
		this.deliveredNum = deliveredNum;
	}
	
	
	


}
