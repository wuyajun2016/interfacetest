package com.dfire.qa.meal.vo.boss;

import java.util.ArrayList;
import java.util.List;

public class RespShopCouponPromotionVo {
	
	
	private Integer isPublished;
	private Long  startDate;
	private Long endDate;
	
	private Boolean brandCoupon;
	private Integer isSpecificWeekDay;
	private Integer discountAll;
	
	private Integer effectiveHour;
	private String bgImg;
	private Integer autoSendLimit;
	
	private String couponShareStr;
	private Integer orderLimit;
	private Integer useCondition;
	
	private Integer useRangeType;
	private Integer expireDays;
	private Integer permitAutoClaim;
	
	private Integer discountType;
	private Integer personLimit;
	private Integer leastPrice;
	
	private Integer memberGift;
	private Integer usedNum;
	private Integer dailyLimit;
	
	private Integer couponCount;
	private Integer hasNoCouponDate;
	private Integer expireType;
	
	private Integer isSpecificTime;
	private Integer couponType;
	private Integer discountRate;
	
	private String couponTypeStr;
	private Integer isAutoSend;
	private Integer totalNum;
	
	private Integer selectedFlag;
	   
	private Integer couponStatus;
	private Integer deliveredNum;
	private Integer amount;
	
	
	
	private String id;
	private List<Long> canNotUseDate;
	private String sychrKouBei;
	private String plateEntityId;
	private List<String> dayOfWeeks;
	private List<String> exceptMenus;
	
	
	public RespShopCouponPromotionVo(String id, String entityId){
		
		this.isPublished = 1;
		this.startDate = System.currentTimeMillis();
		this.endDate = System.currentTimeMillis() + (long)27647990;
		
		this.brandCoupon = false;
		this.isSpecificWeekDay = 0;
		this.discountAll = 0;
		
		this.effectiveHour = 0;
		// 优惠券图片有 30 张, URL 只是最后面的图片名称不同, 具体范围为: bg1.png~bg30.png
		this.bgImg = "http://rest3.zm1717.com/upload_files/boss-app/image/logout/coupon/background/bg14.png";
		this.autoSendLimit = 0;
		
		this.couponShareStr = "8.8折优惠券";
		this.orderLimit = 0;
		this.useCondition = 0;
		
		this.useRangeType = 1;
		this.expireDays = 0;
		this.permitAutoClaim = 0;
		
		this.discountType = 0;
		this.personLimit = 100;
		this.leastPrice = 0;
		
		this.memberGift = 1;
		this.usedNum = 0;
		this.dailyLimit = 100;
		
		this.couponCount = 0;
		this.hasNoCouponDate = 0;
		this.expireType = 0;
		
		this.isSpecificTime = 0;
		this.couponType = 1;
		this.discountRate = 88;
		
		this.couponTypeStr = "全场折扣券";
		this.isAutoSend = 0;
		this.totalNum = 1000;
		
		this.selectedFlag = 0;
		this.couponStatus = 1;
		this.deliveredNum = 0;
		
		this.amount = 0;
		
		
		
		this.id = id;
		this.canNotUseDate = new ArrayList<Long>();
		this.sychrKouBei = "0";
		this.plateEntityId = entityId;
		this.dayOfWeeks = new ArrayList<String>();
		this.exceptMenus = new ArrayList<String>();
		
		
	}
	
	public Integer getIsPublished() {
		return isPublished;
	}
	public void setIsPublished(Integer isPublished) {
		this.isPublished = isPublished;
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
	public String getBgImg() {
		return bgImg;
	}
	public void setBgImg(String bgImg) {
		this.bgImg = bgImg;
	}
	public Integer getEffectiveHour() {
		return effectiveHour;
	}
	public void setEffectiveHour(Integer effectiveHour) {
		this.effectiveHour = effectiveHour;
	}
	public Integer getAutoSendLimit() {
		return autoSendLimit;
	}
	public void setAutoSendLimit(Integer autoSendLimit) {
		this.autoSendLimit = autoSendLimit;
	}
	public Integer getOrderLimit() {
		return orderLimit;
	}
	public void setOrderLimit(Integer orderLimit) {
		this.orderLimit = orderLimit;
	}
	public String getCouponShareStr() {
		return couponShareStr;
	}
	public void setCouponShareStr(String couponShareStr) {
		this.couponShareStr = couponShareStr;
	}
	public Long getStartDate() {
		return startDate;
	}
	public void setStartDate(Long startDate) {
		this.startDate = startDate;
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
	public Integer getPersonLimit() {
		return personLimit;
	}
	public void setPersonLimit(Integer personLimit) {
		this.personLimit = personLimit;
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
	public Integer getDailyLimit() {
		return dailyLimit;
	}
	public void setDailyLimit(Integer dailyLimit) {
		this.dailyLimit = dailyLimit;
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
	public Integer getCouponType() {
		return couponType;
	}
	public void setCouponType(Integer couponType) {
		this.couponType = couponType;
	}
	public Integer getDiscountRate() {
		return discountRate;
	}
	public void setDiscountRate(Integer discountRate) {
		this.discountRate = discountRate;
	}
	public String getCouponTypeStr() {
		return couponTypeStr;
	}
	public void setCouponTypeStr(String couponTypeStr) {
		this.couponTypeStr = couponTypeStr;
	}
	public Integer getIsAutoSend() {
		return isAutoSend;
	}
	public void setIsAutoSend(Integer isAutoSend) {
		this.isAutoSend = isAutoSend;
	}
	public Integer getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}
	public Integer getSelectedFlag() {
		return selectedFlag;
	}
	public void setSelectedFlag(Integer selectedFlag) {
		this.selectedFlag = selectedFlag;
	}
	public Integer getDeliveredNum() {
		return deliveredNum;
	}
	public void setDeliveredNum(Integer deliveredNum) {
		this.deliveredNum = deliveredNum;
	}
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getCouponStatus() {
		return couponStatus;
	}
	public void setCouponStatus(Integer couponStatus) {
		this.couponStatus = couponStatus;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<Long> getCanNotUseDate() {
		return canNotUseDate;
	}
	public void setCanNotUseDate(List<Long> canNotUseDate) {
		this.canNotUseDate = canNotUseDate;
	}
	public String getSychrKouBei() {
		return sychrKouBei;
	}
	public void setSychrKouBei(String sychrKouBei) {
		this.sychrKouBei = sychrKouBei;
	}
	public String getPlateEntityId() {
		return plateEntityId;
	}
	public void setPlateEntityId(String plateEntityId) {
		this.plateEntityId = plateEntityId;
	}
	public List<String> getDayOfWeeks() {
		return dayOfWeeks;
	}
	public void setDayOfWeeks(List<String> dayOfWeeks) {
		this.dayOfWeeks = dayOfWeeks;
	}
	public List<String> getExceptMenus() {
		return exceptMenus;
	}
	public void setExceptMenus(List<String> exceptMenus) {
		this.exceptMenus = exceptMenus;
	}
	

}
