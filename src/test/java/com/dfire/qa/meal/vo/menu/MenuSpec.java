package com.dfire.qa.meal.vo.menu;


public class MenuSpec {
	
	private String entity_id;
	
	private String menu_id;
	
	private Integer is_include;
	
	private MenuList data;
	
	public MenuSpec(String entityId, String menuId, Integer isIncludeed, MenuList data){
		
		this.entity_id = entityId;
		this.menu_id = menuId;
		
		this.is_include = isIncludeed;
		this.setData(data);
		
	}
	
	public String getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}

	public Integer getIs_include() {
		return is_include;
	}

	public void setIs_include(Integer is_include) {
		this.is_include = is_include;
	}

	

	public String getEntity_id() {
		return entity_id;
	}

	public void setEntity_id(String entity_id) {
		this.entity_id = entity_id;
	}

	public MenuList getData() {
		return data;
	}

	public void setData(MenuList data) {
		this.data = data;
	}





	
	

}
