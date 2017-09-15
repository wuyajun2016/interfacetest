package com.dfire.qa.meal.vo.boss;

public class MenuAttribute {
	
	private String menu_weight;
	private String spec_weight;
	private String label_info;
	
	
	public MenuAttribute(){
		this.menu_weight = "1";
		this.spec_weight = "{}";
		this.label_info = "6";
	}
	
	
	public String getMenu_weight() {
		return menu_weight;
	}
	public void setMenu_weight(String menu_weight) {
		this.menu_weight = menu_weight;
	}
	public String getSpec_weight() {
		return spec_weight;
	}
	public void setSpec_weight(String spec_weight) {
		this.spec_weight = spec_weight;
	}
	public String getLabel_info() {
		return label_info;
	}
	public void setLabel_info(String label_info) {
		this.label_info = label_info;
	}
	
	
	

}
