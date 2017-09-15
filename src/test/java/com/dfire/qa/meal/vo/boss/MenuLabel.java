package com.dfire.qa.meal.vo.boss;

public class MenuLabel {
	
	private String tagSource;
    private String recommendLevelString;
    private String showTop;
    
    private String specialTagString;
    private MenuAttribute menuAttribute;
    private String recommendLevel;
    
    private String acridLevelString;
    private String acridLevel;
    
    
    public MenuLabel(){
    	
    	this.tagSource = "0";
    	this.recommendLevelString = "不设定";
    	this.showTop = "0";
    	
    	this.specialTagString = "不设定";
    	this.menuAttribute = new MenuAttribute();
    	this.recommendLevel = "0";
    	
    	this.acridLevelString = "不辣";
    	this.acridLevel = "0";
    }
    
    
    
	public String getTagSource() {
		return tagSource;
	}
	public void setTagSource(String tagSource) {
		this.tagSource = tagSource;
	}
	public String getRecommendLevelString() {
		return recommendLevelString;
	}
	public void setRecommendLevelString(String recommendLevelString) {
		this.recommendLevelString = recommendLevelString;
	}
	public String getShowTop() {
		return showTop;
	}
	public void setShowTop(String showTop) {
		this.showTop = showTop;
	}
	public String getSpecialTagString() {
		return specialTagString;
	}
	public void setSpecialTagString(String specialTagString) {
		this.specialTagString = specialTagString;
	}
	public MenuAttribute getMenuAttribute() {
		return menuAttribute;
	}
	public void setMenuAttribute(MenuAttribute menuAttribute) {
		this.menuAttribute = menuAttribute;
	}
	public String getRecommendLevel() {
		return recommendLevel;
	}
	public void setRecommendLevel(String recommendLevel) {
		this.recommendLevel = recommendLevel;
	}
	public String getAcridLevelString() {
		return acridLevelString;
	}
	public void setAcridLevelString(String acridLevelString) {
		this.acridLevelString = acridLevelString;
	}
	public String getAcridLevel() {
		return acridLevel;
	}
	public void setAcridLevel(String acridLevel) {
		this.acridLevel = acridLevel;
	}

}
