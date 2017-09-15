package com.dfire.qa.meal.vo.boss;

import com.dfire.qa.meal.utils.CommonConstants;

public class MenuInfo {
	
	private  Boolean doubleUnitMenu;
    private Integer showTop;
    private Integer isInclude;
    
    private Integer isRatio;
    private Integer chain;
    private Integer isPrint;
    
    private Integer acridLevel;
    private Integer isSelf;
    private Integer recommendLevel;
    
    private Integer type;
    private Integer sortCode;
    
	private Boolean hasSpecAndMake;
    
    private Boolean orClick;
    private Boolean isSearch;
    private Integer memberPrice;
    
    private String menuName;
    private String menuId;
    private String menuPrice;
    
    private Integer ratio;
    private Integer lastVer;
    private Integer isAdditional;
    
    private Integer price;
    
    public MenuInfo(){
    	
		 this.doubleUnitMenu = false;
	     this.showTop = 0;
	     this.isInclude = 0;
	     this.isRatio = 0;
	     
	     this.chain = 0;
	     this.isPrint = 0;
	     this.acridLevel = 0;
	     this.isSelf = 0;
	     
	     this.recommendLevel = 0;
	     this.type = 0;
	     this.sortCode = 0;
	     this.hasSpecAndMake = false;
	     
	     this.orClick = true;
	     this.isSearch = false;
	     this.memberPrice = 0;
	     this.menuName = "水果沙拉";
	     
	     this.menuId = CommonConstants.fruitSaladMenuId;
	     this.menuPrice = "10";
	     this.ratio = 0;
	     this.lastVer = 0;
	     
	     this.isAdditional = 0;
	     this.price = 0;
    }
    
    
    public Boolean getDoubleUnitMenu() {
		return doubleUnitMenu;
	}


	public void setDoubleUnitMenu(Boolean doubleUnitMenu) {
		this.doubleUnitMenu = doubleUnitMenu;
	}


	public Integer getShowTop() {
		return showTop;
	}


	public void setShowTop(Integer showTop) {
		this.showTop = showTop;
	}


	public Integer getIsInclude() {
		return isInclude;
	}


	public void setIsInclude(Integer isInclude) {
		this.isInclude = isInclude;
	}


	public Integer getIsRatio() {
		return isRatio;
	}


	public void setIsRatio(Integer isRatio) {
		this.isRatio = isRatio;
	}


	public Integer getChain() {
		return chain;
	}


	public void setChain(Integer chain) {
		this.chain = chain;
	}


	public Integer getIsPrint() {
		return isPrint;
	}


	public void setIsPrint(Integer isPrint) {
		this.isPrint = isPrint;
	}


	public Integer getAcridLevel() {
		return acridLevel;
	}


	public void setAcridLevel(Integer acridLevel) {
		this.acridLevel = acridLevel;
	}


	public Integer getIsSelf() {
		return isSelf;
	}


	public void setIsSelf(Integer isSelf) {
		this.isSelf = isSelf;
	}


	public Integer getRecommendLevel() {
		return recommendLevel;
	}


	public void setRecommendLevel(Integer recommendLevel) {
		this.recommendLevel = recommendLevel;
	}


	public Integer getType() {
		return type;
	}


	public void setType(Integer type) {
		this.type = type;
	}


	public Integer getSortCode() {
		return sortCode;
	}


	public void setSortCode(Integer sortCode) {
		this.sortCode = sortCode;
	}


	public Boolean getHasSpecAndMake() {
		return hasSpecAndMake;
	}


	public void setHasSpecAndMake(Boolean hasSpecAndMake) {
		this.hasSpecAndMake = hasSpecAndMake;
	}


	public Boolean getOrClick() {
		return orClick;
	}


	public void setOrClick(Boolean orClick) {
		this.orClick = orClick;
	}


	public Boolean getIsSearch() {
		return isSearch;
	}


	public void setIsSearch(Boolean isSearch) {
		this.isSearch = isSearch;
	}


	public Integer getMemberPrice() {
		return memberPrice;
	}


	public void setMemberPrice(Integer memberPrice) {
		this.memberPrice = memberPrice;
	}


	public String getMenuName() {
		return menuName;
	}


	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}


	public String getMenuId() {
		return menuId;
	}


	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}


	public String getMenuPrice() {
		return menuPrice;
	}


	public void setMenuPrice(String menuPrice) {
		this.menuPrice = menuPrice;
	}


	public Integer getRatio() {
		return ratio;
	}


	public void setRatio(Integer ratio) {
		this.ratio = ratio;
	}


	public Integer getLastVer() {
		return lastVer;
	}


	public void setLastVer(Integer lastVer) {
		this.lastVer = lastVer;
	}


	public Integer getIsAdditional() {
		return isAdditional;
	}


	public void setIsAdditional(Integer isAdditional) {
		this.isAdditional = isAdditional;
	}


	public Integer getPrice() {
		return price;
	}


	public void setPrice(Integer price) {
		this.price = price;
	}



    
    


}
