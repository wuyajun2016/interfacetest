package com.dfire.qa.meal.vo.boss;



public class PrivilegePromotionVo {

	private Integer sendCount;
	private RespShopCouponPromotionVo respShopCouponPromotionVo;
	private Integer cycleType;
	
	public PrivilegePromotionVo(RespShopCouponPromotionVo respShopCouponPromotionVo){
		this.sendCount = 1;
		this.cycleType = 0;
		this.respShopCouponPromotionVo = respShopCouponPromotionVo;
				
	}
	public Integer getSendCount() {
		return sendCount;
	}
	public void setSendCount(Integer sendCount) {
		this.sendCount = sendCount;
	}

	public Integer getCycleType() {
		return cycleType;
	}
	public void setCycleType(Integer cycleType) {
		this.cycleType = cycleType;
	}
	public RespShopCouponPromotionVo getRespShopCouponPromotionVo() {
		return respShopCouponPromotionVo;
	}
	public void setRespShopCouponPromotionVo(RespShopCouponPromotionVo respShopCouponPromotionVo) {
		this.respShopCouponPromotionVo = respShopCouponPromotionVo;
	}
}
