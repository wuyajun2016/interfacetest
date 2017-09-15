package com.dfire.qa.meal.service;

import com.dfire.qa.meal.bean.BaseParamBean;
import com.dfire.qa.meal.bean.Response;
import com.dfire.qa.meal.enums.Environment;

public interface IPrivilegeService {
	
	
	/**
	 * 获取店铺所有优惠方案列表<br/>
	 */
	public Response getPrivilegeList(BaseParamBean baseParamBean, Integer useRange, Environment env) throws Exception;

}
