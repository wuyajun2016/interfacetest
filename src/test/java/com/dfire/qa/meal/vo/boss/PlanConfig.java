package com.dfire.qa.meal.vo.boss;

import java.util.ArrayList;
import java.util.List;

public class PlanConfig {
	
	private String planId;
	
	private Integer recommendType;
	
	private List<PlanConfigSet> settingList;

	public PlanConfig(String planId, List<String> labelIdList){
		
		this.planId = planId;
		this.recommendType = 0;
		this.settingList = new ArrayList<PlanConfigSet>();
		
		for(String labelId : labelIdList){
			PlanConfigSet planConfigSet = new PlanConfigSet(labelId);
			settingList.add(planConfigSet);
		}
		
		
	}
	
	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

	public Integer getRecommendType() {
		return recommendType;
	}

	public void setRecommendType(Integer recommendType) {
		this.recommendType = recommendType;
	}

	public List<PlanConfigSet> getSettingList() {
		return settingList;
	}

	public void setSettingList(List<PlanConfigSet> settingList) {
		this.settingList = settingList;
	}
	
	

}
