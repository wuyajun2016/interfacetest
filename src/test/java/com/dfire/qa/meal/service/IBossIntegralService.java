package com.dfire.qa.meal.service;

import com.dfire.qa.meal.bean.Response;
import com.dfire.qa.meal.enums.Environment;

public interface IBossIntegralService {
	
	
	/**
	 * 获取抽奖活动详情<br/>
	 */
	public Response getActivityDetail(String entityId, String activityId, Environment env) throws Exception;

	
	
	/**
	 * 一元购活动列表页<br/>
	 */
	public Response getActivityList(String entityId, Integer page, Integer pageSize, Environment env) throws Exception;
	
	
	
    /**
     * 获取菜列表<br/>
     * entityListStr 为空是单店菜信息，不为空为连锁菜信息<br/>
     */
	public Response getCommonMenuList(String entityId, String entityListStr, Environment env) throws Exception;
	
	
	
	/**
	 * 抽奖活动保存/发布设置（根据活动状态）<br/>
	 */
	public Response saveActivity(String entityId, String activityInfoReqJson, String activityPromotionsJson, Environment env) throws Exception;
	
	
	
}
