package com.dfire.qa.meal.vo.boss;

import java.util.List;

public class PrivilegeVo {
	
	private List<PrivilegePromotionVo> privilegePromotionVos;
	private Integer cellType;
	private Boolean hasAddFooter;
	private Integer lastVersion;
	
	public PrivilegeVo(List<PrivilegePromotionVo> privilegePromotionVos){
		
		this.privilegePromotionVos = privilegePromotionVos;
		this.cellType = 3;
		this.hasAddFooter = false;
		this.lastVersion = 0;
		
	}
	
	
	public List<PrivilegePromotionVo> getPrivilegePromotionVos() {
		return privilegePromotionVos;
	}
	public void setPrivilegePromotionVos(List<PrivilegePromotionVo> privilegePromotionVos) {
		this.privilegePromotionVos = privilegePromotionVos;
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
	public Integer getLastVersion() {
		return lastVersion;
	}
	public void setLastVersion(Integer lastVersion) {
		this.lastVersion = lastVersion;
	}

}
