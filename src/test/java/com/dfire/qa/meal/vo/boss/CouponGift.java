package com.dfire.qa.meal.vo.boss;

public class CouponGift {
	
	private  Integer quantity;
    private Long hash;
    private String productId;
    
    private Integer mobile;
    private Integer type;
    private Integer price;
    
    private Integer lastVer;
    private Long createTime;
    private Long opTime;
    
    private Integer isValid;
    private Integer cardFee;
    private String name;
    
    
    public CouponGift(String productId, String name){
    	
    	this.quantity = 10;
    	this.hash = (long)0;
    	this.productId = productId;
    	
	    this.mobile = 0;
	    this.type = 3;
	    this.price = 0;
	    
	    this.lastVer = 0;
	    this.createTime = (long)0;
	    this.opTime = (long)0;
	    
	    this.isValid = 0;
	    this.cardFee = 0;
	    this.name = name;
	    
    	
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

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Integer getMobile() {
		return mobile;
	}

	public void setMobile(Integer mobile) {
		this.mobile = mobile;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getLastVer() {
		return lastVer;
	}

	public void setLastVer(Integer lastVer) {
		this.lastVer = lastVer;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
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

}
