package com.dfire.testcase.function.boss;

import java.util.ArrayList;
import java.util.List;

public class PathForBoss {
		
	private static List<String> pathForSaveForceMenu;
	private static List<String> pathForRemoveForceMenu;
	private static List<String> pathForGetForceMenuList;
	
	
	private static List<String> pathForSetEntityConfig;
	private static List<String> pathForSetAutoGetCoupon;
	
	private static List<String> pathForPrePaySwitch;
	private static List<String> pathForMenuConfigUpdate;
	
	static{
		
		// 添加必选菜
		pathForSaveForceMenu = new ArrayList<String>();
		pathForSaveForceMenu.add("menu/v1/save_force_menu");
		
		
		// 移除必选菜
		pathForRemoveForceMenu = new ArrayList<String>();
		pathForRemoveForceMenu.add("menu/v1/remove_force_menu");
		
		// 获取必选商品列表
		pathForGetForceMenuList = new ArrayList<String>();
		pathForGetForceMenuList.add("menu/v1/query_force_menu_list");
		
		// 批量设置店铺维度的配置
		pathForSetEntityConfig = new ArrayList<String>();
		pathForSetEntityConfig.add("boss/v1/batch_set_entity_config");
		
		
		// 设置自动领券开关
		pathForSetAutoGetCoupon = new ArrayList<String>();
		pathForSetAutoGetCoupon.add("promotion/v1/set_auto_send_and_coupon");
		
		
		// 设置预付款开关
		pathForPrePaySwitch = new ArrayList<String>();
		pathForPrePaySwitch.add("config_set/v1/save");
		
		
		// 设置商品基本属性
		pathForMenuConfigUpdate = new ArrayList<String>();
		pathForMenuConfigUpdate.add("menu/v1/save_or_update_menu");
		
	}
	
	
	public static List<String> getPathForRemoveForceMenu() {
		return pathForRemoveForceMenu;
	}

	public static void setPathForRemoveForceMenu(
			List<String> pathForRemoveForceMenu) {
		PathForBoss.pathForRemoveForceMenu = pathForRemoveForceMenu;
	}

	public static List<String> getPathForSaveForceMenu() {
		return pathForSaveForceMenu;
	}

	public static void setPathForSaveForceMenu(List<String> pathForSaveForceMenu) {
		PathForBoss.pathForSaveForceMenu = pathForSaveForceMenu;
	}

	public static List<String> getPathForGetForceMenuList() {
		return pathForGetForceMenuList;
	}

	public static void setPathForGetForceMenuList(
			List<String> pathForGetForceMenuList) {
		PathForBoss.pathForGetForceMenuList = pathForGetForceMenuList;
	}

	public static List<String> getPathForSetEntityConfig() {
		return pathForSetEntityConfig;
	}

	public static void setPathForSetEntityConfig(
			List<String> pathForSetEntityConfig) {
		PathForBoss.pathForSetEntityConfig = pathForSetEntityConfig;
	}

	public static List<String> getPathForSetAutoGetCoupon() {
		return pathForSetAutoGetCoupon;
	}

	public static void setPathForSetAutoGetCoupon(
			List<String> pathForSetAutoGetCoupon) {
		PathForBoss.pathForSetAutoGetCoupon = pathForSetAutoGetCoupon;
	}

	public static List<String> getPathForPrePaySwitch() {
		return pathForPrePaySwitch;
	}

	public static void setPathForPrePaySwitch(List<String> pathForPrePaySwitch) {
		PathForBoss.pathForPrePaySwitch = pathForPrePaySwitch;
	}

	public static List<String> getPathForMenuConfigUpdate() {
		return pathForMenuConfigUpdate;
	}

	public static void setPathForMenuConfigUpdate(
			List<String> pathForMenuConfigUpdate) {
		PathForBoss.pathForMenuConfigUpdate = pathForMenuConfigUpdate;
	}

	

	
	

}
