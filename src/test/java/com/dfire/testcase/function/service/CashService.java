package com.dfire.testcase.function.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.Reporter;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.dfire.sdk.sign.SignGenerator;
import com.dfire.test.util.StringUtil;
import com.dfire.testcase.function.bean.BaseParamBean;
import com.dfire.testcase.function.bean.CashInitBean;
import com.dfire.testcase.function.bean.cash.CashExtBean;
import com.dfire.testcase.function.bean.cash.CashWaitingOrderVO;
import com.dfire.testcase.function.bean.cash.CloudMessage;
import com.dfire.testcase.function.bean.cash.CustomerPayOrder;
import com.dfire.testcase.function.bean.cash.Instance;
import com.dfire.testcase.function.bean.cash.Menu;
import com.dfire.testcase.function.bean.cash.NumberUtils;
import com.dfire.testcase.function.bean.cash.Order;
import com.dfire.testcase.function.bean.cash.OrderInfoVo;
import com.dfire.testcase.function.bean.cash.Pay;
import com.dfire.testcase.function.bean.cash.PayOrder;
import com.dfire.testcase.function.bean.cash.ServiceBillVO;
import com.dfire.testcase.function.bean.cash.TotalPay;
import com.dfire.testcase.function.bean.cash.TotalPayInfoVo;
import com.dfire.testcase.function.bean.cash.WaitingInstance;
import com.dfire.testcase.function.bean.cash.WaitingOrder;
import com.dfire.testcase.function.bean.cash.base.Base;
import com.dfire.testcase.function.util.base.CashUtil;
import com.dfire.testcase.function.util.cash.SignUtil;
import com.dfire.wechat.util.DateUtil;
import com.dfire.wechat.util.HttpRequestEx;
import com.dfire.wechat.util.PropertiesUtil;
import com.dfire.wechat.util.Response;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


/**
 * 用于模拟收银机行为
 * @author ljw
 *
 */
public class CashService {
	
	private static final Logger logger = Logger.getLogger(CashService.class);
	
	public HttpRequestEx cashRequest;
	public CashInitBean cashinitBean = new CashInitBean();
	public CashExtBean cashextBean = new CashExtBean();
	PropertiesUtil propertyUtil = PropertiesUtil.getPropertiesUtil();
	
	
	public static Gson gson = new Gson();
	
	public String entityId;

	public TotalPayInfoVo totalPayInfoVo = new TotalPayInfoVo();
	public List<Instance> instances = new ArrayList<Instance>();
	public ServiceBillVO serviceBillVO = new ServiceBillVO();
	public TotalPay totalPay = new TotalPay();
	public List<OrderInfoVo> orderInfoVos = new ArrayList<OrderInfoVo>();
	public Order order = new Order();
	
	
	/**
	 * 生成订单和instance
	 * @param entityId
	 * @param waitingOrderId
	 * @param appSecret
	 * @return 
	 * @throws Exception
	 */
	public void generateOrderAndUpload(HttpRequestEx cashRequestEx,String msgId,String entityId, String waitingOrderId,CashWaitingOrderVO cashWaitingOrderVO, String appSecret) throws Exception{
		
		logger.info("上传订单");
		this.entityId=entityId;
		
		initTotalPayInfoVo();
		
		//create totalpay
		totalPay = createTotalpay();
		totalPayInfoVo.setTotalPay(totalPay);
		
		//create order
		order = createOrder(cashWaitingOrderVO.getWaitingOrder(), totalPay);
		
		//create instance
		instances = createInstances(cashWaitingOrderVO.getWaitingInstances(), order.getId(), cashWaitingOrderVO.getWaitingOrder().getBatchMsg());
		
		orderInfoVos.add(new OrderInfoVo(order, instances, order.getSeatId()));
		
//		serviceBillVO = createServiceBillVO(instances, totalPayInfoVo);
//		//创建totalPayInfoVo
//		totalPayInfoVo = createTotalPayInfoVo(totalPay, order, instances, serviceBillVO);
		
		totalPayInfoVo.setOrderInfoVos(orderInfoVos);
		
		//上传订单
		uploadData(cashRequestEx,totalPayInfoVo, entityId, appSecret);
		
		//更新消息
		updateMessage(cashRequestEx,msgId, entityId, waitingOrderId, "2", appSecret);
	}


	

	private void initTotalPayInfoVo() {
		
		if (totalPayInfoVo==null) {
			totalPayInfoVo = new TotalPayInfoVo();
		}
		if (totalPayInfoVo.getTotalPay()==null) {
			TotalPay totalPay = new TotalPay();
			totalPayInfoVo.setTotalPay(totalPay);
		}
	}




	/**
	 * 更新消息
	 * @param msgId
	 * @param entityId
	 * @param waitingOrderId
	 * @param status
	 * @param appSecret
	 */
	public void updateMessage(HttpRequestEx httpRequest,String msgId, String entityId,String businessId,String status, String appSecret) {
		Map<String, String> paraMap = new HashMap<String, String>();
		paraMap.clear();
		buildCashBaseParam(paraMap);
		paraMap.put("id", msgId);
		paraMap.put("result_message", "模拟收银机");
		paraMap.put("timestamp",  String.valueOf(System.currentTimeMillis()));
		paraMap.put("modify_user", "模拟收银机");
		paraMap.put("status", status);
		paraMap.put("business_id", businessId);
		paraMap.put("device_id", "CashDesk");
		paraMap.put("entity_id", entityId);
		
		String sign = getSign(appSecret, paraMap);
		paraMap.put("sign", sign);
		Response response4 = CashUtil.updateMessage(httpRequest,paraMap,cashextBean.getSessionId());
		Assert.assertTrue(Response.isSuccess(response4),"更新消息失败！");
	}

	
	/**
	 * 获取waitingorder
	 * @param entityId
	 * @param waitingOrderId
	 * @param appSecret
	 * @return
	 * @throws Exception
	 */
	public CashWaitingOrderVO getWaitingOrderByWaitingOrderId2(HttpRequestEx httpRequest,String entityId, String waitingOrderId, String appSecret) throws Exception {
        CashWaitingOrderVO cashWaitingOrderVO = null;
        try {
            Map<String, String> params = new HashMap<String, String>();
            buildCashBaseParam(params);
            params.put("entity_id", entityId);
            params.put("waiting_order_id", waitingOrderId);
            params.put("timestamp", String.valueOf(System.currentTimeMillis()));
            
            String sign = getSign(appSecret,params);
            params.put("sign", sign);
            Response response = CashUtil.getWaitingOrder(httpRequest, params, cashextBean.getSessionId());
            if (Response.isSuccess(response)) {
            	cashWaitingOrderVO = JSONObject.parseObject(JSONPath.read(response.getResponseStr(), "$.data").toString(), CashWaitingOrderVO.class);
			}
            
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return cashWaitingOrderVO;
    }

	/**
	 * 创建totalpay
	 *	
	 * @return
	 */
	private TotalPay createTotalpay() {
		TotalPay totalPay = new TotalPay();
		totalPay.initDefault(entityId);
		return totalPay;
	}
	
	/**
	 * 创建order
	 * @param waitingOrder
	 * @param totalPay
	 * @return
	 */
	private Order createOrder(WaitingOrder waitingOrder, TotalPay totalPay) {
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
			System.out.println("orderId:"+order.getId());
			return order;
		}

		
	/**
	 * 上传订单
	 * @param totalPay
	 * @param order
	 * @param macIp2 
	 * @param entityId 
	 * @param instances
	 */
	private void uploadData(HttpRequestEx httpRequest,TotalPayInfoVo totalPayInfoVo, String entityId,String appSecret) {
			Map<String, String> paramsMap = new HashMap<String, String>();
			buildCashBaseParam(paramsMap);
	        paramsMap.put("total_pay_info_vo", gson.toJson(totalPayInfoVo));
	        paramsMap.put("entity_id", entityId);
	        paramsMap.put("mac", cashextBean.getMacIp());
	        paramsMap.put("method", "scanCode");
	        paramsMap.put("sign", getSign(appSecret, paramsMap));
	        Response response = CashUtil.uploadData(httpRequest, paramsMap ,cashextBean.getSessionId());
	        Assert.assertTrue(Response.isSuccess(response),"上传数据不成功！");
	}


	/**
	 * 
	 * @param totalPay
	 * @param order
	 * @param instances
	 * @param serviceBillVO 
	 * @return
	 */
	public TotalPayInfoVo createTotalPayInfoVo(TotalPay totalPay, Order order,List<Instance> instances, ServiceBillVO serviceBillVO) {
		List<OrderInfoVo> orderInfoVos = new ArrayList<OrderInfoVo>();
		orderInfoVos.add(new OrderInfoVo(order, instances, order.getSeatId()));
		
		totalPayInfoVo.setTotalPay(totalPay);
		totalPayInfoVo.setPays(null);
		totalPayInfoVo.setOrderInfoVos(orderInfoVos);
		totalPayInfoVo.setServiceBillVO(serviceBillVO);
		return totalPayInfoVo;
	}
		
	private ServiceBillVO createServiceBillVO(List<Instance> instances,TotalPayInfoVo totalPayInfoVo) {
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
		serviceBillVO.setFinalAmount(fee);// 最终金额
		serviceBillVO.setAgioAmount(fee);// 折后金额
		serviceBillVO.setOriginAmount(fee);// 原始金额
		return serviceBillVO;
	}

	/**
	 * waitinginstance转instance
	 * 
	 * @param waitingInstances
	 * @param orderId
	 * @param batchMsg
	 * @return
	 */
	private List<Instance> createInstances(List<WaitingInstance> waitingInstances, String orderId,String batchMsg) {
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
	 * 
	 * @param orderId
	 * @param waitingInstance
	 * @param status
	 * @param batchMsg
	 * @return
	 */
	private Instance createInstance(String orderId,WaitingInstance waitingInstance, Short status, String batchMsg) {
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
	 * 收银审核同意
	 * @param appSecret 
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("serial")
	public Map<String, Object> approveOrder(HttpRequestEx cashRequest, String entityId, String appSecret) throws Exception{
		
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		try {
			//获取消息
			Map<String, String> parMap = new HashMap<String, String>();
			
			buildCashBaseParam(parMap);
			parMap.put("device_id", "CashDesk");
			parMap.put("status", "3");//0:新消息； 1：自动审核；2：已处理；3：自动审核，未处理
			parMap.put("start_time", DateUtil.dateToStamp("1970-01-18 10:46:33"));
			
			parMap.put("page_index", "1");
			parMap.put("entity_id", entityId);
			parMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
			
			String sign = getSign(appSecret, parMap);
			parMap.put("sign", sign);
			
			Response response = CashUtil.getMessageList(cashRequest, parMap, cashextBean.getSessionId());
			Assert.assertTrue(Response.isSuccess(response),"获取消息失败");
			
			//获取返回值	
			String json = String.valueOf(JSONPath.read(response.getResponseStr(),"$.data[ty=102][0]"));//102:订单审核消息
			Assert.assertNotNull(json,"未获取到订单审核消息！");
			
			CloudMessage msg = JSONObject.parseObject(json,CloudMessage.class);
			JSONObject jsonObject = JSONObject.parseObject(msg.getCot());
			String waitingOrderId = jsonObject.getString("waitingOrderId");
			
			String msgId = msg.getId();
			Assert.assertNotNull(msgId,"待审核消息为空");
			Assert.assertNotNull(waitingOrderId,"waitingorderId为空");
			
			//获取waitingorder
			CashWaitingOrderVO cashWaitingOrderVO =  getWaitingOrderByWaitingOrderId2(cashRequest,entityId, waitingOrderId, appSecret);
			
			//生成订单并上传
			generateOrderAndUpload(cashRequest,msgId, entityId, waitingOrderId,cashWaitingOrderVO, appSecret);
			
			returnMap.put("cashWaitingOrderVO", cashWaitingOrderVO);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new Exception("收银审核失败！"){};
			
		}
		
		return returnMap;
		
	}
	

	
	public String getSign(String secret, Map<String, String> parMap) {
		return SignGenerator.client(secret, parMap);
	}

	

	/**
	 * 构造收银机的基本参数
	 * @param paraMap 
	 * @param entityId
	 */
	public void buildCashBaseParam(Map<String, String> paraMap) {
		
		paraMap.put("s_osv", cashinitBean.getS_osv());
		paraMap.put("s_br", cashinitBean.getS_br());
		paraMap.put("s_apv", cashinitBean.getS_apv());
		
		paraMap.put("s_eid", cashinitBean.getS_eid());
		paraMap.put("s_did", cashinitBean.getS_did());
		paraMap.put("s_os", cashinitBean.getS_os());
		
		paraMap.put("s_net", cashinitBean.getS_net());
		paraMap.put("appKey", cashinitBean.getApp_key());
		paraMap.put("format", cashinitBean.getFormat());
		
		paraMap.put("s_sc", cashinitBean.getS_sc());
		paraMap.put("app_key", cashinitBean.getApp_key());
		
	}

	/**
	 * 非预付款
	 * 收银结账清台
	 * @param cashRequest
	 * @param baseParam 
	 * @param cashMap
	 * @param appSecret
	 */
	public void settleAccounts(HttpRequestEx cashRequest,BaseParamBean baseParam, String appSecret){
		
		logger.info("收银结账开始！");
		
		//获取消息
		Map<String, String> parMap = new HashMap<String, String>();
		buildCashBaseParam(parMap);
		
		parMap.put("device_id", "CashDesk");
		parMap.put("status", "3");//0:新消息； 1：自动审核；2：已处理；3：自动审核，未处理
		parMap.put("start_time", DateUtil.dateToStamp("1970-01-18 10:46:33"));
		
		parMap.put("page_index", "1");
		parMap.put("entity_id", entityId);
		parMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
		
		String sign = getSign(appSecret, parMap);
		parMap.put("sign", sign);
		
		Response response = CashUtil.getMessageList(cashRequest, parMap,cashextBean.getSessionId());
		Assert.assertTrue(Response.isSuccess(response),"获取消息失败");
		
		//获取返回值	
		String orderId = baseParam.getOrderId();
		String json = String.valueOf(JSONPath.read(response.getResponseStr(), "$.data[b_id='"+orderId+"'][0]"));//获取特定json
		
		logger.info("获取付款消息:"+json);
		
		CloudMessage msg = JSONObject.parseObject(json,CloudMessage.class);
		String msgId = msg.getId();
		PayOrder payOrder = JSONObject.parseObject(msg.getCot(), PayOrder.class);
		
		//处理支付消息
		handlePayMsg(cashRequest, msgId,payOrder, appSecret);
		
	
	}


	public void handlePayMsg(HttpRequestEx cashRequest,String msgId, PayOrder payOrder,String appSecret) {
		
		//根据orderid查询  List<Order> orders 
		List<OrderInfoVo> orders = totalPayInfoVo.getOrderInfoVos();
		
		//更新pays
		updatePays(payOrder);
		
		//更新order
		updateOrderInfo(orders);
		
		//更新totalpay
		updateTotalPay(totalPayInfoVo.getTotalPay());
		
		//更新支付消息
		updateMessage(cashRequest, msgId, entityId,"","2", appSecret);
		
		uploadData(cashRequest, totalPayInfoVo, entityId, appSecret);
	}




	/**
	 * 更新order
	 * 
	 * @param orders
	 */
	private void updateOrderInfo(List<OrderInfoVo> orders) {
		
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
	 * updateTotalpays
	 * @param totalPays
	 */
	private void updateTotalPay(TotalPay totalPay) {
		totalPay.setStatus(TotalPay.STATUS_END);
		totalPay.setOperator("模拟收银机");
		totalPay.setOperateDate(System.currentTimeMillis());
		totalPay.setCurrDate(new Date());
		totalPay.setOverStatus(TotalPay.SHIFT_STATUS_COMMON);

	}


	public void updatePays(PayOrder payOrder) {
		
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
	

	
	// 给账单中的订单赋值（多单）
	public void updateOrders(List<Order> orderList) {
		
		for (Order order : orderList) {
			
			order.setStatus(Order.STATUS_END);
			order.setEndTime(new Date().getTime());
			
			// 取消会员卡关联
			order.setRelatedCardId(null);
			
			// 将预充值信息清空
			order.setPrePay(0d);
			order.setCurrDate(new Date());
			
		}
		
	}
	
//	public void setTotalPay(TotalPay totalPay, ServiceBill totalServiceBill) {
//		totalPay.setSourceAmount(totalServiceBill.getOriginReceivablesAmount());
//		totalPay.setDiscountAmount(totalServiceBill.getAgioReceivablesAmount());
//		totalPay.setResultAmount(totalServiceBill.getFinalAmount());
//		totalPay.setInvoiceCode("发票号");
//		totalPay.setInvoiceMemo("发票抬头");
//		totalPay.setStatus(TotalPay.STATUS_END);
//		totalPay.setOperator("操作id");
//		totalPay.setOperateDate(new Date().getTime()); // 结账时间
//		totalPay.setCurrDate(new Date());
//		totalPay.setOverStatus(TotalPay.SHIFT_STATUS_COMMON);
//	}

	
	
	///////////////////////////////////////////////
	/////////////////预付款
	///////////////////
	
	
	/**
	 * 店码预付款结账
	 * @param cashRequest
	 * @param cashMap
	 * @param baseParamBean 
	 * @param appSecret
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	public void preSettleAccounts(HttpRequestEx cashRequest,Map<String, String> cashMap,BaseParamBean baseParamBean, String appSecret) throws JsonParseException, JsonMappingException, IOException{
		
		logger.info("收银结账开始！");
		
		String entityId = cashMap.get("entity_id");
		cashinitBean.setS_eid(entityId);
		
		//获取消息
		Map<String, String> parMap = new HashMap<String, String>();
		buildCashBaseParam(parMap);
		
		parMap.put("device_id", "CashDesk");
		parMap.put("status", "3");//0:新消息； 1：自动审核；2：已处理；3：自动审核，未处理
		parMap.put("start_time", DateUtil.dateToStamp("1970-01-18 10:46:33"));
		
		parMap.put("page_index", "1");
		parMap.put("entity_id", entityId);
		parMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
		
		String sign = getSign(appSecret, parMap);
		parMap.put("sign", sign);
		
		Response response = CashUtil.getMessageList(cashRequest, parMap,cashextBean.getSessionId());
		Assert.assertTrue(Response.isSuccess(response),"获取消息失败");
		
		//获取返回值	
		String orderId = baseParamBean.getOrderId();
		String json = String.valueOf(JSONPath.read(response.getResponseStr(), "$.data[b_id='"+orderId+"'][0]"));//获取特定json		
		Assert.assertNotNull(json,"支付成功消息获取失败！");
		
		logger.info("获取预付款消息:"+json);
		
		CloudMessage msg = JSONObject.parseObject(json,CloudMessage.class);
		ObjectMapper objectMapper = new ObjectMapper();
		
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		CustomerPayOrder customerPayOrder = (CustomerPayOrder) objectMapper.readValue(msg.getCot(),CustomerPayOrder.class);
		
		//处理支付消息
		preEndPay(cashRequest,entityId, msg,customerPayOrder, appSecret);
		
	
	}




	private void preEndPay(HttpRequestEx cashRequest, String entityId,CloudMessage msg, 
			CustomerPayOrder customerPayOrder, String appSecret) {
		
		List<PayOrder> payOrders = customerPayOrder.getPayOrders();
		
		@SuppressWarnings("unchecked")
		List<WaitingInstance> waitingInstances = customerPayOrder.getCustomerOrder().getWaitingInstances();
		this.entityId=entityId;
		
		initTotalPayInfoVo();
		
		//设置totalpay
		TotalPay totalPay = createTotalpay();
		totalPayInfoVo.setTotalPay(totalPay);
		
		//设置instances
		List<Instance> instances = createInstances(waitingInstances, customerPayOrder.getCustomerOrder().getWaitingOrder().getId(),
				customerPayOrder.getCustomerOrder().getWaitingOrder().getBatchMsg());
		
		//设置pays
		for (PayOrder payOrder : payOrders) {
			
			updatePays(payOrder);
			
			
		}
		
		
		//设置orders
		Order order = createOrder(customerPayOrder.getCustomerOrder().getWaitingOrder(), totalPay);		
		order.setStatus(Order.STATUS_END);
		
		//设置serviceBillVo
		ServiceBillVO serviceBillVO = createServiceBillVO(instances, totalPayInfoVo);
		
		//创建totalPayInfoVo
		totalPayInfoVo = createTotalPayInfoVo(totalPay,order, instances,serviceBillVO);
		
		//上传订单
		uploadData(cashRequest,totalPayInfoVo, entityId, appSecret);
		
		
		updateMessage(cashRequest, msg.getId(), entityId,"","2", appSecret);
		
		
	}

	
	/**
	 * 桌码预付款
	 * @param cashRequest
	 * @param cashMap
	 * @param baseParamBean
	 * @param appSecret
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public void preSeatSettleAccounts(HttpRequestEx cashRequest,Map<String, String> cashMap,BaseParamBean baseParamBean, String appSecret) throws JsonParseException, JsonMappingException, IOException{
		
		logger.info("收银结账开始！");
		String entityId = cashMap.get("entity_id");
		
		//获取消息
		Map<String, String> parMap = new HashMap<String, String>();
		buildCashBaseParam(parMap);
		
		parMap.put("device_id", "CashDesk");
		parMap.put("status", "3");//0:新消息； 1：自动审核；2：已处理；3：自动审核，未处理
		parMap.put("start_time", DateUtil.dateToStamp("1970-01-18 10:46:33"));
		
		parMap.put("page_index", "1");
		parMap.put("entity_id", entityId);
		parMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
		
		String sign = getSign(appSecret, parMap);
		parMap.put("sign", sign);
		
		Response response = CashUtil.getMessageList(cashRequest, parMap,cashextBean.getSessionId());
		Assert.assertTrue(Response.isSuccess(response),"获取消息失败");
		
		//获取返回值	
		String orderId = baseParamBean.getOrderId();
		String json = String.valueOf(JSONPath.read(response.getResponseStr(), 
				"$.data[ty=123][0]"));//获取特定json 有时候获取不到因为b_id不等于orderId
		
		logger.info("获取预付款消息:"+json);
		
		CloudMessage msg = JSONObject.parseObject(json,CloudMessage.class);
		ObjectMapper objectMapper = new ObjectMapper();
		
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		CustomerPayOrder customerPayOrder = (CustomerPayOrder) objectMapper.readValue(msg.getCot(),CustomerPayOrder.class);
		
		//处理支付消息
		preEndPay(cashRequest,entityId, msg,customerPayOrder, appSecret);
		
	
	}


	/**
	 * 收银登陆
	 * @throws Exception 
	 */
	public String loginwithpassword(HttpRequestEx cashRequest,BaseParamBean baseParamBean, String appSecret) throws Exception {
		
		String sessionId = "";
		
		try {
			
			//收银机登录
			Map<String, String> parMap = new HashMap<String, String>();
			buildCashBaseParam(parMap);
			
			parMap.put("user_name", "ADMIN");
			parMap.put("client_type", cashextBean.getClient_type());
			parMap.put("uniq_no", cashextBean.getUniq_no());
			
			parMap.put("brand", cashextBean.getBrand());
			parMap.put("mac", cashextBean.getMacIp());
			parMap.put("entity_id", baseParamBean.getEntityId());
			
			parMap.put("password", cashextBean.getPassword());
			String sign = getSign(appSecret, parMap);
			parMap.put("sign", sign);
			
			Response response = CashUtil.loginWithEncryptedPassword(cashRequest, parMap);
			Reporter.log("返回消息："+response);
			
			Assert.assertTrue(Response.isSuccess(response),"收银登录获取消息失败！");
			sessionId = JSONPath.read(response.getResponseStr(), "$.data").toString();
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new Exception("收银登录失败！");
			
		}
		
		return sessionId;
		
	}

	
	/**
	 * 发送收银机心跳
	 * @param cashRequest
	 * @param baseParamBean
	 * @param cashMap
	 * @param appSecret
	 * @throws Exception 
	 */
	public void heart(HttpRequestEx cashRequest, BaseParamBean baseParamBean) throws Exception {
		
		Map<String, String> parMap = new HashMap<String, String>();
		
		parMap.put("entityid", baseParamBean.getEntityId());
		parMap.put("apptime", String.valueOf(System.currentTimeMillis()));
		 
		String sign = SignUtil.heartSign(parMap, propertyUtil.getValue("heart.appsecret"));
		parMap.put("sign", sign);
		parMap.put("appversion",propertyUtil.getValue("s_apv"));
		
		parMap.put("heart_appkey",propertyUtil.getValue("heart.appkey"));
		
	    Response response = CashUtil.heart(cashRequest, parMap);
	    
	    if (response.getStatus() == 200) {
	    	
			JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();
			Assert.assertNotNull(resp.get("entityid"),"收银心跳返回有误！");
			
		}else {
			
			throw new Exception("收银心跳接口调用失败");
		}
	    
	}

	/**
	 * 获取消息
	 * @param status
	 * @return
	 */
	public String getMessages(String status){
		//获取消息
		Map<String, String> parMap = new HashMap<String, String>();
		buildCashBaseParam(parMap);
		parMap.put("device_id", "CashDesk");
		parMap.put("status", status);//0:新消息； 1：自动审核；2：已处理；3：自动审核，未处理
		parMap.put("start_time", DateUtil.dateToStamp("1970-01-18 10:46:33"));
		parMap.put("page_index", "1");
		parMap.put("entity_id", entityId);
		parMap.put("timestamp", String.valueOf(System.currentTimeMillis()));
		String sign = getSign(cashextBean.getAppsecret(), parMap);
		parMap.put("sign", sign);
		Response response = CashUtil.getMessageList(cashRequest, parMap, cashextBean.getSessionId());
		Assert.assertTrue(Response.isSuccess(response),"获取消息失败");
		return response.getResponseStr();
//		String json = String.valueOf(JSONPath.read(response.getResponseStr(),"$.data[ty=123][0]"));//获取特定json 有时候获取不到因为b_id不等于orderId
//		logger.info("获取预付款消息:"+json);
//		CloudMessage msg = JSONObject.parseObject(json,CloudMessage.class);
		
	}

	public void calculateServiceBill(String msgId){
		//创建ServiceBillVo
		serviceBillVO = createServiceBillVO(instances, totalPayInfoVo);
		//创建totalPayInfoVo
		totalPayInfoVo = createTotalPayInfoVo(totalPay, order, instances, serviceBillVO);
		//上传订单
		uploadData(cashRequest,totalPayInfoVo, entityId, cashextBean.getAppsecret());
		//更新消息
		updateMessage(cashRequest,msgId, entityId, "", "2", cashextBean.getAppsecret());
	}
	
	
	/**
	 * 收银机初始化
	 * @param entityId
	 */
	public void setCashInitBean(String entityId) {
		
		cashinitBean.setApp_key(propertyUtil.getValue("app_key"));   // 
		cashinitBean.setAppKey(propertyUtil.getValue("appKey"));     // 
		cashinitBean.setFormat(propertyUtil.getValue("format"));     // 
		
		cashinitBean.setS_apv(propertyUtil.getValue("s_apv"));
		cashinitBean.setS_br(propertyUtil.getValue("s_br"));
		cashinitBean.setS_did(propertyUtil.getValue("s_did"));
		
		cashinitBean.setS_eid(entityId);     // 对应店铺的 entityId 
		cashinitBean.setS_net(propertyUtil.getValue("s_net"));
		cashinitBean.setS_os(propertyUtil.getValue("s_os"));
		
		cashinitBean.setS_osv(propertyUtil.getValue("s_osv"));
		cashinitBean.setS_sc(propertyUtil.getValue("s_sc"));
		
	}




	public void setCashExtBean() {
		
		cashextBean.setMacIp(propertyUtil.getValue("mac"));
		cashextBean.setClient_type(propertyUtil.getValue("client_type"));
		
		cashextBean.setUniq_no(propertyUtil.getValue("uniq_no"));
		cashextBean.setBrand(propertyUtil.getValue("brand"));
		
		cashextBean.setPassword(propertyUtil.getValue("password"));
		cashextBean.setAppsecret(propertyUtil.getValue("appSecret"));
		
	}
	


	public HttpRequestEx getCashRequest() {
		return cashRequest;
	}


	public void setCashRequest(HttpRequestEx cashRequest) {
		this.cashRequest = cashRequest;
	}


}
