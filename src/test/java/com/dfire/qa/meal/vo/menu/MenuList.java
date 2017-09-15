package com.dfire.qa.meal.vo.menu;

import java.util.List;

public class MenuList {
	
	private List<MenuSpecChild> menulist;
	
	public MenuList(List<MenuSpecChild> menuList){
		
		this.menulist = menuList;
		
	}
	

	public List<MenuSpecChild> getMenulist() {
		return menulist;
	}

	public void setMenulist(List<MenuSpecChild> menulist) {
		this.menulist = menulist;
	}

}
