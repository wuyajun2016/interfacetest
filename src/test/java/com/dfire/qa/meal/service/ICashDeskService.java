package com.dfire.qa.meal.service;


import java.util.Map;

import com.dfire.qa.meal.bean.BaseParamBean;
import com.dfire.qa.meal.bean.Response;
import com.dfire.qa.meal.enums.Environment;
import com.dfire.qa.meal.enums.MessageStatus;
import com.dfire.qa.meal.vo.cash.CashWaitingOrderVO;
import com.dfire.qa.meal.vo.cash.TotalPayInfoVo;



public interface ICashDeskService {
	
	
	
	/**
	 * 登陆收银机, 返回 sessionId <br/>
	 */
	public String loginWithPassword(BaseParamBean baseParamBean, Environment env) throws Exception;
	
	
	
	/**
	 * 发送收银机心跳, 不管线上还是线下, 使用的都是 HTTP 
	 */
	public Boolean sendHeartBeat(BaseParamBean baseParamBean, Environment env) throws Exception;
	
	
	
	/**
	 * 从 Dpush 获取消息列表, 返回数据为 json 形式 <br/>
	 * 该接口主要用于流程处理, 获取特定 Dpush 消息
	 */
	public Response getMessageList(BaseParamBean baseParamBean, String sessionId, Environment env) throws Exception;
	
	
	
	
	/**
	 * 从 Dpush 获取消息列表, 返回数据为 json 形式 <br/>
	 * 该接口主要用于异常处理, 获取特定状态的所有 Dpush 消息
	 */
	public Response getMessageList(BaseParamBean baseParamBean, String sessionId, MessageStatus messageStatus, Environment env) throws Exception;
	
	
	/**
	 * 从 Dpush 获取各种类型消息<br/>
	 */
	@Deprecated
	public Response getAllMsgList(BaseParamBean baseParamBean, String sessionId, Environment env) throws Exception;
	
	
	/**
	 * 获取 waitingOrder信息，构造 waitingOrder 类 <br/>
	 * orderApprovedMsgJson 是订单审核通过后从 Dpush 返回的订单审核消息
	 */
	public CashWaitingOrderVO getWaitingOrderVo(BaseParamBean baseParamBean, Response responseFromGetMessageList, String sessionId, Environment env) throws Exception;

	
	
	/**
	 * 上传 TotalPayInfoVo 信息
	 */
	public Boolean uploadTotalPayInfoVo(TotalPayInfoVo totalPayInfoVo, String entityId, String sessionId, Environment env) throws Exception;
	
	
	
	/**
	 * 更新 Dpush 消息
	 */
	public Boolean updateMessage(String msgId, String bussinessId, String sessionId, String status, Environment env) throws Exception;
	
	
	
	/**
	 * 非预付款情况下审核订单使之通过
	 */
	public Map<String, Object> approveOrder(BaseParamBean baseParamBean, String sessionId, Environment env) throws Exception;
	
	
	
	/**
	 * 收银结账清台, 注意此处的 baseParamBean 需要包含 orderId
	 */
	public Boolean settleOrder(BaseParamBean baseParamBean, String sessionId, CashWaitingOrderVO cashWaitingOrderVO, Environment env) throws Exception;
	
	
	
	/**
	 * 获取特定店铺的所有类型 Dpush 消息, 并将之置为  已处理   状态
	 */
	public void clearDpushMessage(BaseParamBean baseParamBean, String sessionId, Environment env) throws Exception;
	
	
	/**
	 * 设置菜品沽清<br/>
	 */
	public Response menuBalance(BaseParamBean baseParamBean, String menuId, String sessionId, Environment env) throws Exception;
	
	
	/**
	 * 删除沽清菜品, 使之处于非沽清状态<br/>
	 */
	public Response clearMenuBalance(BaseParamBean baseParamBean, String menuId, String sessionId, Environment env) throws Exception;
	
	
}
