package com.dfire.qa.meal.service;

import java.util.Map;

import com.dfire.qa.meal.bean.BaseParamBean;
import com.dfire.qa.meal.bean.Response;
import com.dfire.qa.meal.enums.Environment;

public interface IOrderService {
	
	
	
	/**
	 * 提交订单
	 * orderId 可为空，<br/>
	 * 需要参数：xtoken, entityId, seatCode, orderId; <br/>peopleCount, memo, isPrePay, cartTime <br/>
	 * 对应 URL ：orders/v1/confirm
	 */
	public Response submitOrder(BaseParamBean baseParamBean, Map<String, String> otherParameter, Environment env) throws Exception;
	
	
	
	/**
	 * 获取订单列表<br/>
	 * 需要参数：xtoken, entityId, seatCode, orderId<br/>
	 * orderId 可为空<br/>
	 * 请求 URL：orders/v1/get_order<br/>
	 */
	public Response getOrder(BaseParamBean baseParamBean, Environment env) throws Exception;
	
	
	
	/**
	 * 检查订单变化 <br/>
	 * orderId 可为空, orderIdsFormJson 是封装了  orderIdsForm 的 Json 形式 <br/>
	 * 需要参数：xtoken, entityId, seatCode, orderId<br/>
	 * 请求  URL ：orders/v1/check_order_change  
	 */
	public Response checkOrderChange(BaseParamBean baseParamBean, String orderIdsFormJson, Environment env) throws Exception;
	
	
	
	 /**
	  * 非预付款扫桌码下单<br/>
	  * 包含请求: 获取购物车数据, 提交订单, 获取订单列表, 检查订单变化
	  */
	 public Map<String, Response> placeOrder(BaseParamBean baseParamBean, Map<String, String> otherParameter, Environment env) throws Exception;


	 
	 /**
	  * 获取我在本店的订单列表<br/>
	  */
	 public Response getShopOrderList(BaseParamBean baseParamBean, Integer page, Integer pageSize, Environment env) throws Exception;

}
