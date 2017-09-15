package com.dfire.qa.meal.service;

import com.dfire.qa.meal.bean.BaseParamBean;
import com.dfire.qa.meal.bean.Response;
import com.dfire.qa.meal.enums.Environment;



public interface IOAuthService {
	
	
	/**
	 * 桌位码扫码入口<br/>
	 */
	public Response seatCodeOAuth(BaseParamBean baseParamBean, Environment env) throws Exception;
	
	
	
	/**
	 * 消费码扫码入口<br/>
	 */
	public Response globalCodeOAuth(BaseParamBean baseParamBean, String consumerCode, Environment env) throws Exception;
	
	
	
	/**
	 * 店铺扫码入口<br/>
	 */
	public Response entityIdOAuth(BaseParamBean baseParamBean, Environment env) throws Exception;
	
	
	
	/**
	 * 菜码扫码入口<br/>
	 */
	public Response menuCodeOAuth(BaseParamBean baseParamBean, String menuId, Environment env) throws Exception;

}
