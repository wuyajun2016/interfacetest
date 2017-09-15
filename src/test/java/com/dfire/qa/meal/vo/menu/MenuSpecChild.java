package com.dfire.qa.meal.vo.menu;

public class MenuSpecChild {
	
	private  String menuId;
    private Integer menuNum;
    
    private String specDetailId;
    private String makeDetailId;
    
    public MenuSpecChild(String menuId, Integer menuNum, String specDetailId, String makeDetailId){
    	
    	this.menuId = menuId;
    	this.menuNum = menuNum;
    	
    	this.specDetailId = specDetailId;
    	this.makeDetailId = makeDetailId;
    }
    
	public String getMenuId() {
		return menuId;
	}
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	public Integer getMenuNum() {
		return menuNum;
	}
	public void setMenuNum(Integer menuNum) {
		this.menuNum = menuNum;
	}
	public String getSpecDetailId() {
		return specDetailId;
	}
	public void setSpecDetailId(String specDetailId) {
		this.specDetailId = specDetailId;
	}
	public String getMakeDetailId() {
		return makeDetailId;
	}
	public void setMakeDetailId(String makeDetailId) {
		this.makeDetailId = makeDetailId;
	}
    
    

}
