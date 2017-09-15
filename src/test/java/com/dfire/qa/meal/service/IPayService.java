package com.dfire.qa.meal.service;

import java.util.List;
import java.util.Map;

import com.dfire.qa.meal.bean.BaseParamBean;
import com.dfire.qa.meal.bean.Response;
import com.dfire.qa.meal.enums.Environment;
import com.dfire.qa.meal.vo.cart.CartIncludeSuitForm;
import com.dfire.qa.meal.vo.pay.BillRequestForm;
import com.dfire.qa.meal.vo.pay.CardPayBeanV2;
import com.dfire.qa.meal.vo.pay.PayTypeRequestForm;
import com.dfire.tp.client.bill.request.BillNormalRequest;



public interface IPayService {
	
	
	/**
	 * 获取点赞活动开关 <br/>
	 * 不需要填写 orderId <br/>
	 * 需要参数：xtoken, entityId, seatCode, orderId <br/>
	 * 请求 URL 为: activity/v1/activity_switch
	 */
	public  Response getActivitySwitch(BaseParamBean baseParamBean, Environment env) throws Exception;
	
	
	/**
	 * 查询订单是否允许评价<br/>
	 * 请求 URL ： evaluation/v1/play/comments <br/>
	 */
	public Response getOrderIfComments(BaseParamBean baseParamBean, String order, Environment env) throws Exception;
	
	
	
	
	/**
	 * 获取后付款账单信息(即先下单后付款)<br/>
	 * 订单审核同意后产生 orderId 时该接口才能正常返回<br/>
	 * 请求 URL ： bill/v1/get_normal_trade_bill<br/>
	 */
	public Response getNormalTradeBill(BaseParamBean baseParamBean, BillNormalRequest billNormalRequest, Environment env) throws Exception;
	
	
	
	
	/**
	 * 获取优惠方案及支付订单信息<br/>
	 * 未下单的订单 from_cart = 1 <br/>
	 * 请求 URL 为: pay/v2/get_trade_bill
	 */
	public Response getTradeBillV2(BaseParamBean baseParamBean, Map<String, Object> otherParameter, Environment env) throws Exception;
	
	
	
	/**
	 * 获取支付类型<br/>
	 * cartSuitFormListJson 是  List&lt;CartIncludeSuitForm&gt; 的 Json 形式<br/>
	 * 需要参数：xtoken<br/>
	 * 如果采用会员卡支付方式付款, 该接口调用成功后已经进行了相应的支付
	 * 请求 URL 为: pay/v1/get_pay_type
	 */
	public Response getPayType(BaseParamBean baseParamBean, PayTypeRequestForm payTypeForm, Environment env) throws Exception;
	
	
	
	/**
	 * 会员卡支付<br/>
	 * 请求 URL 为:pay/v1/card_pay<br/>
	 * 推荐请求方式
	 */
	@Deprecated
	public Response cardPayV2(BaseParamBean baseParamBean, CardPayBeanV2 cardPayBean, Environment env) throws Exception;
	
	
	
	 /**
	  * 非预付款扫桌码刷新获取最新订单<br/>
	  * 下单并且收银审核同意后刷新页面<br/>
	  * 包含请求: 获取订单列表, 获取点赞活动开关, 查询订单是否允许评价, 检查订单变化
	  */
	 public Map<String, Response> refreshToGetPayDetail(BaseParamBean baseParamBean, Map<String, String> otherParameter, Environment env) throws Exception;

	
	 
	 /**
	  * 非预付款扫桌码支付订单<br/>
	  * 页面已刷新,支付订单<br/>
	  * 包含请求: 获取账单,获取支付类型, 进行会员卡支付
	  */
	 public Map<String, Response> payForOrder(BaseParamBean baseParamBean, List<CartIncludeSuitForm> cartSuitList, Environment env) throws Exception;

	

}
