package com.dfire.qa.meal.vo.cash;

public class MenuBalance {
	
	private Long createTime;
    private String id;
    private int isValid;
    
    private int lastVer;
    private Long opTime;
    private int balanceMode;
    
    private int balanceNum;
    private int calMode;
    private String menuId;
    
    private int uploadStatus;
    private String entityId;
    private Boolean noLimit;
    
    private Boolean over;
    private String tableName;
    
    
    public MenuBalance(String menuId, String entityId){
    	this.createTime = System.currentTimeMillis();
    	this.id = "";
    	this.isValid = 1;
    	
    	this.lastVer = 5;
    	this.opTime = System.currentTimeMillis();
    	this.balanceMode = 1;
    	
    	this.balanceNum = 0;
    	this.calMode = 2;
    	this.menuId = menuId;
    	
    	this.uploadStatus = 1;
    	this.entityId = entityId;
    	this.noLimit = false;
    	
    	this.over = true;
    	this.tableName = "MENUBALANCE";  	
    	
    }
    
    
    
    
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getIsValid() {
		return isValid;
	}
	public void setIsValid(int isValid) {
		this.isValid = isValid;
	}
	public int getLastVer() {
		return lastVer;
	}
	public void setLastVer(int lastVer) {
		this.lastVer = lastVer;
	}
	public Long getOpTime() {
		return opTime;
	}
	public void setOpTime(Long opTime) {
		this.opTime = opTime;
	}
	public int getBalanceMode() {
		return balanceMode;
	}
	public void setBalanceMode(int balanceMode) {
		this.balanceMode = balanceMode;
	}
	public int getBalanceNum() {
		return balanceNum;
	}
	public void setBalanceNum(int balanceNum) {
		this.balanceNum = balanceNum;
	}
	public int getCalMode() {
		return calMode;
	}
	public void setCalMode(int calMode) {
		this.calMode = calMode;
	}
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public int getUploadStatus() {
		return uploadStatus;
	}
	public void setUploadStatus(int uploadStatus) {
		this.uploadStatus = uploadStatus;
	}
	public String getEntityId() {
		return entityId;
	}
	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}
	public Boolean getNoLimit() {
		return noLimit;
	}
	public void setNoLimit(Boolean noLimit) {
		this.noLimit = noLimit;
	}
	public Boolean getOver() {
		return over;
	}
	public void setOver(Boolean over) {
		this.over = over;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

    
    
}
