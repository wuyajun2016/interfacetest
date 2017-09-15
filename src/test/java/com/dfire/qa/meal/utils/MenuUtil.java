package com.dfire.qa.meal.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.dfire.qa.meal.vo.cart.CartIncludeSuitForm;


public class MenuUtil {
	
	
	
	/**
	 * 获取普通菜<br/>
	 */
	public static CartIncludeSuitForm getNormalMenu(String menuId, String menuName, double menuNumber, String uid){

				
		CartIncludeSuitForm cartSuit = new CartIncludeSuitForm();
		cartSuit.setNum(menuNumber);
		cartSuit.setKindType(1);
		
		cartSuit.setMakeId("");
		cartSuit.setSpecDetailId("");
		cartSuit.setMenuName(menuName);
		
		cartSuit.setUid(uid);
		cartSuit.setMenuId(menuId);
		cartSuit.setChildCartVos(null);
		
		return cartSuit;
		
	}
	
	

	/**
	 * 获取包含规格, 配菜的普通菜
	 */
	public static CartIncludeSuitForm getNormalMenu(Properties properties){

				
		// hotSteak cartSuit
		CartIncludeSuitForm peanutSuit = new CartIncludeSuitForm();
		
		peanutSuit.setNum((double)1);
		peanutSuit.setKindMenuId((String)properties.get("kindMenuIdForSide"));
		peanutSuit.setKindMenuName((String)properties.get("kindMenuNameForSide"));
		
		peanutSuit.setMakeId("");
		peanutSuit.setSpecDetailId("");
		peanutSuit.setMenuName((String)properties.get("sideMenuName"));
		
		peanutSuit.setMenuId((String)properties.getProperty("sideMenuId"));
		peanutSuit.setKindType(5);
		peanutSuit.setChildCartVos(null);
		peanutSuit.setUid((String)properties.get("uid"));
		
		
		
		// add to cartSuit list 
		List<CartIncludeSuitForm> cartSuitFormList = new ArrayList<CartIncludeSuitForm>();
		cartSuitFormList.add(peanutSuit);
		
		// parent cartSuit
		CartIncludeSuitForm cartSuit = new CartIncludeSuitForm();
		cartSuit.setNum((double)1);
		cartSuit.setKindType(1);
		
		cartSuit.setMakeId((String)properties.getProperty("normal3MakeId"));
		cartSuit.setSpecDetailId((String)properties.getProperty("normal3SpecDetailId"));
		cartSuit.setMenuName((String)properties.getProperty("normal3MenuName"));
		
		cartSuit.setMenuId((String)properties.getProperty("normal3MenuId"));
		cartSuit.setChildCartVos(cartSuitFormList);
		cartSuit.setUid((String)properties.getProperty("uid"));
		
		return cartSuit;
		
	}
	
	
	
	/**
	 * 获取套菜
	 */
	public static CartIncludeSuitForm getSuitMenu(Map<String, String> menuIdMap){
		
		CartIncludeSuitForm child1 = getNormalMenu(menuIdMap.get("child1MenuId"), menuIdMap.get("child1Name"), 1, menuIdMap.get("uid"));
		
		CartIncludeSuitForm child2 = getNormalMenu(menuIdMap.get("child2MenuId"), menuIdMap.get("child2Name"), 2, menuIdMap.get("uid"));
		
		// add to cartSuit list 
		List<CartIncludeSuitForm> cartSuitFormList = new ArrayList<CartIncludeSuitForm>();
		cartSuitFormList.add(child1);
		cartSuitFormList.add(child2);
		
		// parent cartSuit
		CartIncludeSuitForm cartSuit = new CartIncludeSuitForm();
		cartSuit.setNum((double)1);
		cartSuit.setKindType(2);
		
		cartSuit.setMakeId("");
		cartSuit.setSpecDetailId("");
		cartSuit.setMenuName(menuIdMap.get("suitMenuName"));
		
		cartSuit.setMenuId(menuIdMap.get("suitMenuId"));
		cartSuit.setChildCartVos(cartSuitFormList);
		cartSuit.setUid(menuIdMap.get("uid"));
		return cartSuit;
		
	}
	
	
	

}
