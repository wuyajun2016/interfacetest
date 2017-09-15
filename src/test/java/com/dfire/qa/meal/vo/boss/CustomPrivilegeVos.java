package com.dfire.qa.meal.vo.boss;

import java.util.ArrayList;
import java.util.List;

public class CustomPrivilegeVos {
	
	private List<CustomPrivilege> customPrivilegeDetailVos;

	public CustomPrivilegeVos(String customPrivilegeId){
		
		CustomPrivilege customPrivilege = new CustomPrivilege();
		customPrivilege.setCustomPrivilegeId(customPrivilegeId);
		
		List<CustomPrivilege> privilegeList = new ArrayList<CustomPrivilege>();
		privilegeList.add(customPrivilege);
		
		this.customPrivilegeDetailVos = privilegeList;
		
	}
	
	public List<CustomPrivilege> getCustomPrivilegeDetailVos() {
		return customPrivilegeDetailVos;
	}

	public void setCustomPrivilegeDetailVos(List<CustomPrivilege> customPrivilegeDetailVos) {
		this.customPrivilegeDetailVos = customPrivilegeDetailVos;
	}
	
	
	
	

}
