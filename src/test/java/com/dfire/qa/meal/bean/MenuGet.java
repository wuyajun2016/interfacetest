package com.dfire.qa.meal.bean;

public class MenuGet {
	
	/**
	 * 是普通菜就为 true, 否则为 false<br/>
	 */
	private Boolean normalMenu = false;
	
	
	/**
	 * 普通菜数目, 如果 normalMenu 为 false, 那么该字段不起作用<br/>
	 */
	private Integer normalMenuNo;
	
	
	/**
	 * 是套菜就为 true, 否则为 false<br/>
	 */
	private Boolean suitMenu = false;
	
	
	/**
	 * 套菜数目, 如果 suitMenu 为 false, 那么该字段不起作用<br/>
	 */
	private Integer suitMenuNo;
	
	
	

	public Boolean getNormalMenu() {
		return normalMenu;
	}

	public void setNormalMenu(Boolean normalMenu) {
		this.normalMenu = normalMenu;
	}

	public Integer getNormalMenuNo() {
		return normalMenuNo;
	}

	public void setNormalMenuNo(Integer normalMenuNo) {
		this.normalMenuNo = normalMenuNo;
	}

	public Boolean getSuitMenu() {
		return suitMenu;
	}

	public void setSuitMenu(Boolean suitMenu) {
		this.suitMenu = suitMenu;
	}

	public Integer getSuitMenuNo() {
		return suitMenuNo;
	}

	public void setSuitMenuNo(Integer suitMenuNo) {
		this.suitMenuNo = suitMenuNo;
	}

}
