package com.dfire.qa.meal.service;

import com.dfire.qa.meal.bean.BaseParamBean;
import com.dfire.qa.meal.bean.Response;
import com.dfire.qa.meal.enums.Environment;

public interface ITakeoutMenuService {
	
	
	/**
	 * 获取店铺菜单列表<br/>
	 */
	public Response getTakeoutMenuList(BaseParamBean baseParamBean, Environment env) throws Exception;

}
