package com.dfire.qa.meal.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import java.util.UUID;

import org.testng.Assert;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.dfire.qa.meal.bean.Response;
import com.dfire.qa.meal.constant.Constants;
import com.dfire.qa.meal.enums.Environment;
import com.dfire.qa.meal.logger.LoggerMarkers;
import com.dfire.qa.meal.logger.MealLoggerFactory;
import com.dfire.qa.meal.props.CashProperties;
import com.dfire.qa.meal.vo.cash.CashWaitingOrderVO;
import com.dfire.qa.meal.vo.cash.CloudMessage;
import com.dfire.qa.meal.vo.cash.Instance;
import com.dfire.qa.meal.vo.cash.Menu;
import com.dfire.qa.meal.vo.cash.NumberUtils;
import com.dfire.qa.meal.vo.cash.Order;
import com.dfire.qa.meal.vo.cash.OrderInfoVo;
import com.dfire.qa.meal.vo.cash.Pay;
import com.dfire.qa.meal.vo.cash.PayOrder;
import com.dfire.qa.meal.vo.cash.ServiceBillVO;
import com.dfire.qa.meal.vo.cash.TotalPay;
import com.dfire.qa.meal.vo.cash.TotalPayInfoVo;
import com.dfire.qa.meal.vo.cash.WaitingInstance;
import com.dfire.qa.meal.vo.cash.WaitingOrder;
import com.dfire.qa.meal.vo.cash.base.Base;
import com.dfire.sdk.sign.SignGenerator;
import com.dfire.test.util.StringUtil;
import com.google.common.base.FinalizablePhantomReference;

public class CashDeskUtil {
	
	
	/**
	 * 获取登陆收银所需的 sign 
	 */
	public static String getSignForLoginCashDask(CashProperties cashProperties, Environment env) throws Exception{
		
		String sign = null;
		
		// 获取特定环境下的收银参数
		Map<String, String> cashBasePara = cashProperties.getCashBasePara(env);
		Map<String, String> clientBasePara = cashProperties.getClientBasePara(env);
		Map<String, String> otherBasePara = cashProperties.getOtherBasePara(env);
		
		// 构造计算 sign 的参数集
		Map<String, String> parameter = new HashMap<String, String>();
		parameter.putAll(cashBasePara);
		parameter.putAll(clientBasePara);
		
		sign = SignGenerator.client(otherBasePara.get("appSecret"), parameter);
		
		return sign;
	}
	
	
	
	/**
	 * 获取收银机发送心跳所需 sign 
	 */
	public static String getSignForSendHeartBeat(Map<String, String> parameter, String appSecret) throws Exception{
		
		String sign = null;		
				
		sign = SignForCashDeskUtil.heartSign(parameter, appSecret);
			
		return sign;
	}
	
	
	/**
	 * 生成获取 Dpush 所需的 sign
	 */
	public static String getSignForDpush(CashProperties cashProperties, Environment env, final Map<String, String> deviceParameter) throws Exception{
		
		String sign = null;
		
		// 获取特定环境下的收银参数
		Map<String, String> cashBasePara = cashProperties.getCashBasePara(env);
		Map<String, String> otherBasePara = cashProperties.getOtherBasePara(env);
		
		// 构造另外参数
		// 构造计算 sign 的参数集
		Map<String, String> parameter = new HashMap<String, String>();
		parameter.putAll(cashBasePara);
		parameter.putAll(deviceParameter);
		
		sign = SignGenerator.client(otherBasePara.get("appSecret"), parameter);
		
		return sign;
	}
	
	
	
	/**
	 * 构造表单形式的 HTTP body
	 */
	public static String getBodyWithForm(Map<String, String> body) throws Exception{
		
		StringBuilder bodyWithForm = new StringBuilder();
		
		for(Map.Entry<String, String> entry : body.entrySet()){
			bodyWithForm.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
		}
		
		return bodyWithForm.toString();
	}
	
	
	
	/**
	 * 构造 totalPayInfoVo 用于后续的订单上传
	 */
	public static TotalPayInfoVo  getTotalPayInfo(CashWaitingOrderVO cashWaitingOrderVO, String entityId) throws Exception{
		
		// 构造 TotalPay 以及 TotalPayInfoVo对象
		TotalPay totalPay = new TotalPay();
		totalPay.initDefault(entityId);
		
		TotalPayInfoVo totalPayInfoVo = new TotalPayInfoVo();
		totalPayInfoVo.setTotalPay(totalPay);
		
		
		// 构造 Order 以及 Instance
		Order order = getOrder(cashWaitingOrderVO.getWaitingOrder(), totalPay, entityId);
		List<Instance> instances = createInstances(cashWaitingOrderVO.getWaitingInstances(), 
				order.getId(), cashWaitingOrderVO.getWaitingOrder().getBatchMsg()); 
		
		
		// 构造 ServiceBillVO 对象
		ServiceBillVO serviceBillVO = new ServiceBillVO();
		serviceBillVO = createServiceBillVO(instances, totalPayInfoVo);
		
		
		// 构造 OrderInfVo
		List<OrderInfoVo> orderInfoVos = new ArrayList<OrderInfoVo>();
		orderInfoVos.add(new OrderInfoVo(order, instances, order.getSeatId()));
		
		
		// 构造 Pay List 
		List<Pay> pays = getPayList(instances);
		
		
		// 设置  TotalPayInfoVo 参数
		totalPayInfoVo.setTotalPay(totalPay);
		totalPayInfoVo.setPays(pays);
		
		totalPayInfoVo.setOrderInfoVos(orderInfoVos);
		totalPayInfoVo.setServiceBillVO(serviceBillVO);
		
		return totalPayInfoVo;
		
	}
	
	
	
	/**
	 * 更新 totalPayInfoVo , 用于收银结账
	 */
	public static TotalPayInfoVo updateTotalPayInfoVo(CashWaitingOrderVO cashWaitingOrderVO, String entityId, PayOrder payOrder) throws Exception{
		
		TotalPayInfoVo totalPayInfoVo = getTotalPayInfo(cashWaitingOrderVO, entityId);
		
		
		// 更新 Orders
		updateOrderInfo(totalPayInfoVo);
		
		
		// 更新 pays
		updatePays(payOrder, totalPayInfoVo);
		
		
		// 更新 totalPay
		updateTotalPay(totalPayInfoVo);
		
		
		return totalPayInfoVo;
		
	}
	
	
	
	/**
	 * 根据 getMessageList 方法返回的 response 解析出 msgId 以及 waitingOrderId 用于后续处理
	 */
	public static Map<String, String> getInfoForUpdateMessage(Response responseFromGetMessageList) throws Exception{
		
		//获取返回值	
		String orderApproedMsgJson = String.valueOf(JSONPath.read(responseFromGetMessageList.getResponseStr(),"$.data[ty=102][0]"));//102:订单审核消息
		Assert.assertNotNull(orderApproedMsgJson, Constants.CashDesk.ERRORGETMESSAGE);
		
		//对订单审核消息进行校验			
		Assert.assertNotNull(orderApproedMsgJson, Constants.CashDesk.ERRORGETMESSAGE);
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.CashDesk.SUCGETMESSAGE);
		
		CloudMessage msg = JSONObject.parseObject(orderApproedMsgJson, CloudMessage.class);
		String waitingOrderId = JSONObject.parseObject(msg.getCot()).getString("waitingOrderId");		
		String msgId = msg.getId();
		
		Assert.assertNotNull(msgId,Constants.CashDesk.CHECKMSGEMPTY);
		Assert.assertNotNull(waitingOrderId, Constants.CashDesk.CHECKMSGFORWAITING);
		
		Map<String, String> infoVo = new HashMap<String, String>();
		infoVo.put("msgId", msgId);
		infoVo.put("waitingOrderId", waitingOrderId);
		
		return infoVo;
		
	}
	
	
	
	
	/**
	 * 构建 TotalPayInfoVo 对象
	 */
	private static List<Pay> getPayList(List<Instance> instances) {
				
		List<Pay> pays = new ArrayList<Pay>();
		
		for(Instance instance : instances){
			
			Pay payElement = new Pay();
			payElement.setFee(instance.getFee());
			
			// add by another
			payElement.setPay(instance.getFee());
			payElement.setCharge(0d);
			
			payElement.setCouponFee(0d);
			payElement.setCouponCost(0d);
			
			payElement.setCouponNum(0);
			payElement.setOperator("模拟收银机");
			payElement.setOpUserId("模拟收银机");
			
			pays.add(payElement);
		}		
		
		return pays;
		
	}
	
	
	/**
	 * 构造 Order 对象
	 */
	private static Order getOrder(WaitingOrder waitingOrder, TotalPay totalPay, String entityId) throws Exception{
		
		Order order = new Order();
		order.initBaseData(entityId);
		
		order.setLastVer(1);
		order.setId(waitingOrder.getId());
		order.setOrderKind(Order.KIND_NORMAL);
		
		order.setOpUserId("操作人id");
		order.setIsWait(Base.FALSE);
		order.setIsPrint(Base.TRUE);
		
		if (StringUtil.isEmpty(order.getWorkerId())) {
			order.setWorkerId("操作人id");
		}
		
		order.setOpenTime(System.currentTimeMillis());
		order.setPeopleCount(waitingOrder.getPeopleCount());
		order.setName(waitingOrder.getName()==null?"":waitingOrder.getName());
		
		order.setMemo(waitingOrder.getMemo());
		order.setPayMode(waitingOrder.getPayMode());
		order.setSendTime(waitingOrder.getReserveDate());
		
		order.setAddress(waitingOrder.getAddress());
		order.setPayMode(waitingOrder.getPayMode());
		order.setOutFee(waitingOrder.getOutFee());
		
		order.setSenderId(waitingOrder.getSenderId());
		order.setEndTime(null);
		order.setMobile(waitingOrder.getMobile());
		
		order.setCustomerRegisterId(waitingOrder.getCustomerRegisterId());
		order.setTel(waitingOrder.getTel());
		order.setStatus(Order.STATUS_COMMON);
		
		order.setSendStatus(Order.SENDSTATUS_NO);
		order.setOrderFrom(waitingOrder.getOrderFrom());
		order.setSeatId(waitingOrder.getSeatCode());
		
		// 服务费方案
		// queryFeePlan(order);
		order.setIsHide(Base.FALSE);
		
		// Date bizDate = configService.getCurrentBizDate();
		// order.setCurrDate(bizDate);
		
		order.setWaitingOrderId(waitingOrder.getId());		
		order.setCode(1);
		order.setTotalpayId(totalPay.getId());
		
		order.setReserveTimeId(waitingOrder.getReserveTimeId());
				
		return order;
		
	}
	
	
	
	
	/**
	 * 将 waitingInstance 转为 instance
	 */
	private static List<Instance> createInstances(List<WaitingInstance> waitingInstances, String orderId, String batchMsg) throws Exception{
		
		List<Instance> instances = new ArrayList<Instance>();
		
		if (waitingInstances == null) {
			return null;
		}
		
		for (WaitingInstance waitingInstance : waitingInstances) {
			instances.add(createInstance(orderId, waitingInstance, (short) 2,
					batchMsg));
		}
		
		return instances;
		
	}
	
	
	
	
	/**
	 * 创建instance，设置基础施加
	 */
	private static Instance createInstance(String orderId,WaitingInstance waitingInstance, Short status, String batchMsg) throws Exception{
		
		String menuId = waitingInstance.getMenuId();
		Menu menu = new Menu();
		Instance instance = new Instance();
		
		instance.initDefault();
		instance.setOrderId(orderId);
		instance.setParentId(waitingInstance.getParentId());// 设置子菜parentId
		
		instance.setMakeId(waitingInstance.getMakeId());
		String specDetailId = waitingInstance.getSpecDetailId();
		
		if (StringUtil.isNotEmpty(specDetailId)) {
			instance.setSpecDetailId(specDetailId);
		}
		
		instance.setMenuId(menuId);
		instance.setNum(waitingInstance.getNum());
		instance.setUnit(menu.getBuyAccount());
		
		instance.setAccountNum(waitingInstance.getAccountNum());
		instance.setAccountUnit(menu.getAccount());
		instance.setPrice(menu.getPrice());
		
		instance.setOriginalPrice(menu.getPrice());
		instance.setIsRatio(menu.getIsRatio() == null ? 0 : menu.getIsRatio());
		instance.setKindMenuId(menu.getKindMenuId());
		
		instance.setChildId(waitingInstance.getChildId());
		instance.setName(menu.getName() == null ? "" : menu.getName());
		instance.setKind(waitingInstance.getKind());
		
		instance.setMemo(waitingInstance.getMemo());
		
		if (waitingInstance.getPriceMode() != null) {
			instance.setPriceMode(waitingInstance.getPriceMode());
		}
		
		
		instance.setTaste(waitingInstance.getTaste());
		instance.setWorkerId("-1");
		instance.setStatus(status);

		
		if (waitingInstance.getIsWait() != null) {
			instance.setIsWait(waitingInstance.getIsWait());
		}
		
		
		if (menu.getMemberPrice() == null) {
			instance.setMemberPrice(menu.getPrice());
		} else {
			instance.setMemberPrice(menu.getMemberPrice());
		}

		instance.setWaitingInstanceId(waitingInstance.getId());
		instance.setHitPrice(waitingInstance.getHitPrice());
		instance.setType(waitingInstance.getType());
		
		instance.setBatchMsg(batchMsg);
		instance.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		instance.setFee(waitingInstance.getFee());
		
		return instance;
		
	}
	
	

	
	/**
	 * 构造 ServiceBillVO
	 */
	private static ServiceBillVO createServiceBillVO(List<Instance> instances,TotalPayInfoVo totalPayInfoVo) throws Exception{
		
		ServiceBillVO serviceBillVO = new ServiceBillVO();
		serviceBillVO.setId(totalPayInfoVo.getTotalPay().getId());
		
		double fee = 0;
		for (Instance instance : instances) {
			
			if (instance == null) {
				continue;
			}
			
			if (instance.getFee() == null) {
				continue;
			}
			
			fee += instance.getFee();
			
		}
		
		serviceBillVO.setFinalAmount(fee);                // 最终应收金额
		serviceBillVO.setAgioAmount(fee);                 // 折后消费金额
		
		serviceBillVO.setOriginAmount(fee);               // 原始消费金额		
		serviceBillVO.setOriginTotal(fee);                // 原始总金额
		
		serviceBillVO.setOriginReceivablesAmount(fee);    // 原始应收金额
		serviceBillVO.setAgioReceivablesAmount(fee);      // 折后应收金额
		
		serviceBillVO.setAgioTotal(fee);                  // 折后总金额
		
		return serviceBillVO;
		
	}
	
	
	
	
	/**
	 * 更新 Pays, 用于收银结账
	 */
	public static void updatePays(PayOrder payOrder, TotalPayInfoVo totalPayInfoVo) {
			
			List<Pay> pays = totalPayInfoVo.getPays();
			
			Pay pay = new Pay();
			pay.setKindPayId(Pay.NET_PAY_KIND_ID);// 网络支付
			pay.setPay(payOrder.getFee());
			
			pay.setCharge(0d);
			pay.setTotalPayId(totalPayInfoVo.getTotalPay().getId());
			
			if (payOrder.getPay() == null) {// 兼容小二老版本
				
				pay.setFee(NumberUtils.round(payOrder.getFee()));
				
			} else {
				
				pay.setFee(payOrder.getPay());
				
			}
			
			pay.setOperator("模拟收银机");
			pay.setOpUserId("模拟收银机");
			
			pay.setPayTime(System.currentTimeMillis());
			pay.setIsDealed(Base.FALSE);
			
			pay.setCode(payOrder.getCode());
			pay.setType(payOrder.getType());
			
			pay.setCardEntityId(payOrder.getCardEntityId());
			pay.setCardId(payOrder.getCardId());
			
			pay.setCouponFee(0d);
			pay.setCouponCost(0d);
			
			pay.setCouponNum(0);
			pay.setWaitingPayId(payOrder.getWaitingPayId());
			
			if (pays == null) {
				
				pays = new ArrayList<Pay>();
				
			}
			
			pays.add(pay);
			
		}

	
	
	/**
	 * 更新 order, 用于收银结账
	 */
	private static void updateOrderInfo(TotalPayInfoVo totalPayInfoVo) {
		
		
		List<OrderInfoVo> orders = totalPayInfoVo.getOrderInfoVos();
		
		for (OrderInfoVo orderInfoVo : orders) {
			
           // code = order.getCode().toString();
			Order order = orderInfoVo.getOrder();
            order.setStatus(Order.STATUS_END);
            
            order.setEndTime(System.currentTimeMillis());
            //取消会员卡关联
            order.setRelatedCardId(null);
            
            //将预充值信息清空
            order.setPrePay(0d);
            order.setCurrDate(new Date());
            
            order.setLastVer(1+order.getLastVer());
        }
		
	}
	
	
	/**
	 * 更新 totalPay, 用于收银结账
	 */
	private static void updateTotalPay(TotalPayInfoVo totalPayInfoVo) {
		
		TotalPay totalPay = totalPayInfoVo.getTotalPay();
		
		totalPay.setStatus(TotalPay.STATUS_END);
		totalPay.setOperator("模拟收银机");
		
		totalPay.setOperateDate(System.currentTimeMillis());
		totalPay.setCurrDate(new Date());
		
		totalPay.setOverStatus(TotalPay.SHIFT_STATUS_COMMON);

	}

	
}
