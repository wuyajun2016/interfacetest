package com.dfire.qa.meal.vo.boss;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class BossConfig {
	
	
	private Gson gson = new Gson();
	
	

	/**
	 * 传入的参数中包含了需要更新的设置属性<br/>
	 */
	public String BasicSet(Map<String, String> targetConfigMap) throws Exception{
		
		String configForBoss = null;
				
						
		List<String> configList = new ArrayList<String>();    
		for(Map.Entry<String, String> entry : targetConfigMap.entrySet()){
			configList.add(entry.getKey() + "|" + entry.getValue());
		}
		
		configForBoss = gson.toJson(configList);
					
		
		return configForBoss;
	}
	


	/**
	 * 掌柜端菜单属性设置，该方法设置的字段为传输中的 menu_str 字段
	 */
	public String MenuSet(String menuId, String kindMenuId, String menuName, String tagOfMenu, double menuPrice, boolean onSelf) throws Exception {
		
		
		String menuConfig = null;
		
		
		double memberPrice = menuPrice;
		Menu menu = new Menu(menuId, tagOfMenu, memberPrice, menuName, menuPrice, kindMenuId, onSelf);
		
		menuConfig = gson.toJson(menu);
					
		
		return menuConfig;
		
	}
	
	
	/**
	 * 掌柜端菜单属性设置，该方法设置的字段为传输中的 menu_prop_str 字段
	 */
	public String MenuPropSet(String entityId, String menuId, boolean onSelf) throws Exception {
		
		
		
		String menuPropConfig = null;
			
			
		MenuProp menuProp = new MenuProp(entityId, menuId, onSelf);
		
		menuPropConfig = gson.toJson(menuProp);
					
		
		return menuPropConfig;
		
		
	}
	

}
