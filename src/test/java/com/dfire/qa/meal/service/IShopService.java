package com.dfire.qa.meal.service;


import com.dfire.qa.meal.bean.BaseParamBean;
import com.dfire.qa.meal.bean.Response;
import com.dfire.qa.meal.enums.Environment;

public interface IShopService {
	
	
	/**
	 * 通过业务类型获取附近的店接口<br/>
	 */
	public Response getNearbyShops(BaseParamBean baseParamBean, String bussinessType, String bussinessId, Environment env) throws Exception;
	
	
	
	/**
	 * 获取排队桌位类型<br/>
	 */
	public Response getSeatType(BaseParamBean baseParamBean, Environment env) throws Exception;
	
	
	
	/**
	 * 获取店铺相关信息<br/>
	 */
	public Response getShopState(BaseParamBean baseParamBean, Environment env) throws Exception;
	
	
	
	/**
	 * 获取店铺基本信息<br/>
	 */
	public Response getShopBaseInfo(BaseParamBean baseParamBean, Environment env) throws Exception;
	
	
	
	
	
	/**
	 * 获取店铺生活圈信息<br/>
	 */
	public Response getShopMoment(BaseParamBean baseParamBean, Environment env) throws Exception;
	
	
	
	
	/**
	 * 获取店铺所有信息（包括店铺详情和生活圈信息）接口<br/>
	 */
	public Response getShopAllInfo(BaseParamBean baseParamBean, Environment env) throws Exception;
	
	
	
	
	/**
	 * 店铺分享URL的信息（包括图片、文案）<br/>
	 */
	public Response getShopShare(BaseParamBean baseParamBean, Environment env) throws Exception;
	
	

}
