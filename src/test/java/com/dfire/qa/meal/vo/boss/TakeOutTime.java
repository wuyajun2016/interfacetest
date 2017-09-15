package com.dfire.qa.meal.vo.boss;

public class TakeOutTime {
	
	private Long opTime;
	private Long hash;
	private Long beginTime;
	
	private Long endTime;
	private Integer lastVer;
	private Integer isValid;
	
	private Integer num;
	private Long createTime;
	
	
	public Long getOpTime() {
		return opTime;
	}
	public void setOpTime(Long opTime) {
		this.opTime = opTime;
	}
	public Long getHash() {
		return hash;
	}
	public void setHash(Long hash) {
		this.hash = hash;
	}
	public Long getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Long beginTime) {
		this.beginTime = beginTime;
	}
	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
	public Integer getLastVer() {
		return lastVer;
	}
	public void setLastVer(Integer lastVer) {
		this.lastVer = lastVer;
	}
	public Integer getIsValid() {
		return isValid;
	}
	public void setIsValid(Integer isValid) {
		this.isValid = isValid;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

}
