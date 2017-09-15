package com.dfire.qa.meal.vo.boss;

public class CustomPrivilege {
	
	private Integer selectedStatus;
	private String title;
	private Integer cellType;
	private Boolean hasAddFooter;
	private String content;
	
	
	private String customPrivilegeId;
	private String lastVer;
	
	public CustomPrivilege(){
		this.selectedStatus = 0;
		this.title = "自定义券";
		this.cellType = 3;
		this.hasAddFooter = false;
		this.content = "这是自定义券";
	}
	
	
	/**
	 * 用于在自定义列表中删除自定义特权<br/>
	 */
	public CustomPrivilege(String customPrivilegeId, String title, String content, Integer selectedStatus){
		
		this.customPrivilegeId = customPrivilegeId;
		this.title = title;
		this.content = content;
		
		this.selectedStatus = selectedStatus;
		this.cellType = 3;
	    this.lastVer = "0";
	    
	    this.hasAddFooter = false;
	}
	
	public Integer getSelectedStatus() {
		return selectedStatus;
	}
	public void setSelectedStatus(Integer selectedStatus) {
		this.selectedStatus = selectedStatus;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getCellType() {
		return cellType;
	}
	public void setCellType(Integer cellType) {
		this.cellType = cellType;
	}
	public Boolean getHasAddFooter() {
		return hasAddFooter;
	}
	public void setHasAddFooter(Boolean hasAddFooter) {
		this.hasAddFooter = hasAddFooter;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCustomPrivilegeId() {
		return customPrivilegeId;
	}
	public void setCustomPrivilegeId(String customPrivilegeId) {
		this.customPrivilegeId = customPrivilegeId;
	}
	

}
