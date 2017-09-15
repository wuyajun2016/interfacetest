package com.dfire.testcase.function.boss;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.dfire.wechat.util.CommonConstants;
import com.google.gson.Gson;

public class BossConfig {
	
	private static final Logger logger = Logger.getLogger(BossConfig.class);
	private Gson gson = new Gson();
	
	
	

	/**
	 * 传入的参数中包含了需要更新的设置属性<br/>
	 */
	public String BasicSet(Map<String, String> targetConfigMap){
		
		String configForBoss = null;
		
		try{
						
			List<String> configList = new ArrayList<String>();    
			for(Map.Entry<String, String> entry : targetConfigMap.entrySet()){
				configList.add(entry.getKey() + "|" + entry.getValue());
			}
			
			configForBoss = gson.toJson(configList);
			
		}catch(Exception e){
			logger.error(e.toString());
		}
		
		return configForBoss;
	}
	


	/**
	 * 掌柜端菜单属性设置，该方法设置的字段为传输中的 menu_str 字段
	 * @param menuId
	 * @param kindMenuId
	 * @param menuName
	 * @param tagOfMenu
	 * @param menuPrice
	 * @return
	 */
	public String MenuSet(String menuId, String kindMenuId, String menuName, String tagOfMenu, double menuPrice, boolean onSelf){
		
		String menuConfig = null;
		
		try{
			
			double memberPrice = menuPrice;
			Menu menu = new Menu(menuId, tagOfMenu, memberPrice, menuName, menuPrice, kindMenuId, onSelf);
			
			menuConfig = gson.toJson(menu);
			
		}catch(Exception e){
			logger.error(e.toString());
		}
		
		return menuConfig;
		
	}
	
	
	/**
	 * 掌柜端菜单属性设置，该方法设置的字段为传输中的 menu_prop_str 字段
	 * @param entityId
	 * @param menuId
	 * @return
	 */
	public String MenuPropSet(String entityId, String menuId, boolean onSelf){
		
		String menuPropConfig = null;
		
		try{
			
			MenuProp menuProp = new MenuProp(entityId, menuId, onSelf);
			
			menuPropConfig = gson.toJson(menuProp);
			
		}catch(Exception e){
			logger.error(e.toString());
		}
		
		return menuPropConfig;
		
		
	}
	

}
