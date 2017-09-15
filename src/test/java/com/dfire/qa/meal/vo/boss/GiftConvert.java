package com.dfire.qa.meal.vo.boss;


public class GiftConvert {
	
	private Integer giftCanConvert;
	private Integer giftNumbs;
	private Integer convertLimit;
	
	public GiftConvert(){
		
		this.giftCanConvert = 1;
		this.giftNumbs = 10;
		this.convertLimit = 1200;
		
	}
	
	public void reset(){
		
		this.giftCanConvert = 0;
		this.giftNumbs = 0;
		this.convertLimit = 0;
		
	}
	
	
	public Integer getGiftCanConvert() {
		return giftCanConvert;
	}
	public void setGiftCanConvert(Integer giftCanConvert) {
		this.giftCanConvert = giftCanConvert;
	}
	public Integer getGiftNumbs() {
		return giftNumbs;
	}
	public void setGiftNumbs(Integer giftNumbs) {
		this.giftNumbs = giftNumbs;
	}
	public Integer getConvertLimit() {
		return convertLimit;
	}
	public void setConvertLimit(Integer convertLimit) {
		this.convertLimit = convertLimit;
	}
	
	

}
