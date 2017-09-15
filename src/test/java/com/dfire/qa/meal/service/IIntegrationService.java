package com.dfire.qa.meal.service;

import java.util.List;
import java.util.Map;

import com.dfire.qa.meal.bean.BaseParamBean;
import com.dfire.qa.meal.bean.MenuGet;
import com.dfire.qa.meal.bean.ResultMap;
import com.dfire.qa.meal.enums.Environment;

public interface IIntegrationService {
	
	
	/**
	 * 扫桌码非预付款整体流程<br/>
	 * pay 为 true 时表示进行支付并进行结账清台, 否则仅仅进行下单<br/>
	 */
	public ResultMap payNoPrePaySeat(BaseParamBean baseParamBean, Map<String, String> otherParameter, MenuGet menuGet, Environment env, Boolean pay) throws Exception;
	
	
	/**
	 * 清理订单(通过 order-soa 进行结账清台)<br/>
	 * 该接口只是对订单状态进行设置, 不能解决问题<br/>
	 */
	@Deprecated
	public Boolean cleanOrder(String entityId, List<String> seatCodes) throws Exception;

}
