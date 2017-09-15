package com.dfire.qa.meal.vo.boss;

import java.util.ArrayList;
import java.util.List;

import com.dfire.qa.meal.logger.LoggerMarkers;
import com.dfire.qa.meal.logger.MealLoggerFactory;

public class Coupon extends BaseCoupon{
	

	private String bgImg;
	private Integer autoSendLimit;	
	private String couponShareStr;

	private Integer personLimit;
	private Integer dailyLimit;
	private Integer discountRate;
	private Integer couponType;

	
	private String couponTypeStr;
	private Integer totalNum;
	private Integer amount;
	
	private List<MenuInfo> menuInfoList = new ArrayList<MenuInfo>();
	
	
	
	public Coupon(Integer flag){
		
		if(1 == flag){
			// 全场现金券
			this.bgImg = "http://rest3.zm1717.com/upload_files/boss-app/image/logout/coupon/background/bg5.png";
			this.autoSendLimit = 10;	
			this.couponShareStr = "100元现金券";
			
			this.personLimit = 1;
			this.dailyLimit = 1;
			
	
			this.couponType = 0;
			this.discountRate = 0;		
			this.couponTypeStr = "全场现金券";
	
			this.totalNum = 1000;	
			this.amount = 100;
			
		}else if(2 == flag){
			//全场折扣券    优惠券图片有 30 张, URL 只是最后面的图片名称不同, 具体范围为: bg1.png~bg30.png
			this.bgImg = "http://rest3.zm1717.com/upload_files/boss-app/image/logout/coupon/background/bg13.png";
			this.autoSendLimit = 20;	
			this.couponShareStr = "9.0折优惠券";
			
			this.personLimit = 1;
			this.dailyLimit = 1;
			
	
			this.couponType = 1;
			this.discountRate = 90;		
			this.couponTypeStr = "全场折扣券";
	
			this.totalNum = 80;	
			this.amount = 0;
			
		}else if(3 == flag){
			
			// 单品现金券
			this.bgImg = "http://rest3.zm1717.com/upload_files/boss-app/image/logout/coupon/background/bg30.png";
			this.autoSendLimit = 10;	
			this.couponShareStr = "5元现金券((null))";
			
			this.personLimit = 100;
			this.dailyLimit = 100;
			
			this.menuInfoList.add(new MenuInfo());
	
			this.couponType = 20;
			this.discountRate = 0;		
			this.couponTypeStr = "单品现金券";
	
			this.totalNum = 500;	
			this.amount = 5;
			
		}else if(4 == flag){
			
			// 单品折扣券
			this.bgImg = "http://rest3.zm1717.com/upload_files/boss-app/image/logout/coupon/background/bg21.png";
			this.autoSendLimit = 10;	
			this.couponShareStr = "9.0折折扣券((null))";
			
			this.personLimit = 100;
			this.dailyLimit = 100;
			
			this.menuInfoList.add(new MenuInfo());
	
			this.couponType = 21;
			this.discountRate = 90;		
			this.couponTypeStr = "单品折扣券";
	
			this.totalNum = 500;	
			this.amount = 0;
			
		}else if(5 == flag){
			
			// 单品特价券
			this.bgImg = "http://rest3.zm1717.com/upload_files/boss-app/image/logout/coupon/background/bg5.png";
			this.autoSendLimit = 1;	
			this.couponShareStr = "2元特价券((null))";
			
			this.personLimit = 1;
			this.dailyLimit = 1;
			
			this.menuInfoList.add(new MenuInfo());
	
			this.couponType = 22;
			this.discountRate = 0;		
			this.couponTypeStr = "单品特价券";
	
			this.totalNum = 1000;	
			this.amount = 2;
			
		}else if(6 == flag){
			
			// 单品兑换券
			this.bgImg = "http://rest3.zm1717.com/upload_files/boss-app/image/logout/coupon/background/bg19.png";
			this.autoSendLimit = 0;	
			this.couponShareStr = "5元兑换券((null))";
			
			this.personLimit = 1;
			this.dailyLimit = 1;
			
			this.menuInfoList.add(new MenuInfo());
	
			this.couponType = 23;
			this.discountRate = 0;		
			this.couponTypeStr = "单品兑换券";
	
			this.totalNum = 1000;	
			this.amount = 5;
			
		}else{
			MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, "there is no coupon match");
		}
		
	}
	
	
	

	
	public String getBgImg() {
		return bgImg;
	}
	public void setBgImg(String bgImg) {
		this.bgImg = bgImg;
	}
	public Integer getAutoSendLimit() {
		return autoSendLimit;
	}
	public void setAutoSendLimit(Integer autoSendLimit) {
		this.autoSendLimit = autoSendLimit;
	}
	public String getCouponShareStr() {
		return couponShareStr;
	}
	public void setCouponShareStr(String couponShareStr) {
		this.couponShareStr = couponShareStr;
	}
	
	
	public Integer getPersonLimit() {
		return personLimit;
	}
	public void setPersonLimit(Integer personLimit) {
		this.personLimit = personLimit;
	}
	
	
	public Integer getDailyLimit() {
		return dailyLimit;
	}
	public void setDailyLimit(Integer dailyLimit) {
		this.dailyLimit = dailyLimit;
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
	
	public Integer getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(Integer totalNum) {
		this.totalNum = totalNum;
	}

	
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}



	public List<MenuInfo> getMenuInfoList() {
		return menuInfoList;
	}



	public void setMenuInfoList(List<MenuInfo> menuInfoList) {
		this.menuInfoList = menuInfoList;
	}

}
