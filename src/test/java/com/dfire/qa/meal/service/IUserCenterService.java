package com.dfire.qa.meal.service;

import com.dfire.qa.meal.bean.BaseParamBean;
import com.dfire.qa.meal.bean.Response;
import com.dfire.qa.meal.enums.Environment;

public interface IUserCenterService {
	
	
	
	/**
	 * 商户会员中心<br/>
	 */
	public Response getMyDashboard(BaseParamBean baseParamBean, Environment env) throws Exception;
	
	
	
	/**
	 * 二维火会员中心<br/>
	 */
	public Response getMyUserCenter(BaseParamBean baseParamBean, Environment env) throws Exception;

	
}
