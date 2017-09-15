package com.dfire.qa.meal.service;

import java.util.Map;

import com.dfire.qa.meal.bean.BaseParamBean;
import com.dfire.qa.meal.bean.Response;
import com.dfire.qa.meal.enums.Environment;

public interface IMenuService {
	
	/**
	 * 店码获取菜单列表<br/>
	 */
	public Response getMenusByShopCode(BaseParamBean baseParamBean, Map<String, String> otherParameter, Environment env) throws Exception;
	

}
