package com.dfire.qa.meal.vo.boss;

public class CardGift {
	
	private Long mobile;
	private Integer quantity;
	private Long hash;
	
	private Integer lastVer;
	private Integer price;
	private Long opTime;
	
	private Integer isValid;
	private Integer type;
	private Integer cardFee;
	
	private String name;
	private Long createTime;
	
	public CardGift(){
		this.mobile = (long)0;
		this.quantity = 10;
		this.hash = (long)0;  // 貌似不严格校验
		
		this.lastVer = 0;
		this.price = 20;
		this.opTime = (long)0;
		
		this.isValid = 0;
		this.type = 1;
		this.cardFee = 0;
		
		this.name = "20元";
		this.createTime = (long)0;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Long getHash() {
		return hash;
	}

	public void setHash(Long hash) {
		this.hash = hash;
	}

	public Integer getLastVer() {
		return lastVer;
	}

	public void setLastVer(Integer lastVer) {
		this.lastVer = lastVer;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Long getOpTime() {
		return opTime;
	}

	public void setOpTime(Long opTime) {
		this.opTime = opTime;
	}

	public Integer getIsValid() {
		return isValid;
	}

	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getCardFee() {
		return cardFee;
	}

	public void setCardFee(Integer cardFee) {
		this.cardFee = cardFee;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

}
