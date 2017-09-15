package com.dfire.qa.meal.service;

import com.dfire.qa.meal.bean.BaseParamBean;
import com.dfire.qa.meal.bean.Response;
import com.dfire.qa.meal.enums.Environment;

public interface IIntegralMallService {
	
	
	/**
	 * 积分商城首页<br/>
	 */
	public Response getIntegralHomeList(BaseParamBean baseParamBean, Environment env) throws Exception;

}
