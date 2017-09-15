package com.dfire.qa.meal.service;

import com.dfire.qa.meal.bean.BaseParamBean;
import com.dfire.qa.meal.bean.Response;
import com.dfire.qa.meal.enums.Environment;

public interface ICardService {
	
	
	/**
	 * 获取我在本店的会员卡列表<br/>
	 */
	public Response getCardsList(BaseParamBean baseParamBean, Environment env) throws Exception;
	
	
	
	/**
	 * 会员卡详情查询<br/>
	 */
	public Response getCardDetail(BaseParamBean baseParamBean, String cardId, String kindCardId, Environment env) throws Exception;
	
	
	
	/**
	 * 会员卡支付<br/>
	 */
	public Response getCardsPayment(BaseParamBean baseParamBean, String cardId, Environment env) throws Exception;
	
	
	/**
	 * 获取包含我所有会员卡的列表<br/>
	 */
	public Response getAllMyCardList(BaseParamBean baseParamBean, Integer page, Integer pageSize, Environment env) throws Exception;
	
	
	/**
	 * 获取包含我所有优惠券的列表<br/>
	 */
	public Response getAllMyCouponList(BaseParamBean baseParamBean, Integer page, Integer pageSize, Environment env) throws Exception;

}
