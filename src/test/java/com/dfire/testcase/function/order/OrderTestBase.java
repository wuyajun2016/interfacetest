package com.dfire.testcase.function.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.dfire.testcase.function.bean.BaseParamBean;
import com.dfire.testcase.function.bean.CardPayBean;
import com.dfire.testcase.function.bean.cash.CartCompareBean;
import com.dfire.testcase.function.bean.cash.CashWaitingOrderVO;
import com.dfire.testcase.function.service.cash.CashService;
import com.dfire.testcase.function.thread.ServiceBillThread;
import com.dfire.testcase.function.util.api.SeatNoPrePayAPI;
import com.dfire.testcase.function.util.api.ShopPrePayAPI;
import com.dfire.testcase.function.util.base.WechatBaseUtil;
import com.dfire.wechat.util.CartIncludeSuitForm;
import com.dfire.wechat.util.HttpRequestEx;
import com.dfire.wechat.util.PropertiesUtil;
import com.dfire.wechat.util.Response;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;

public class OrderTestBase {
	private static final Logger logger = Logger.getLogger(OrderTestBase.class);
	public HttpRequestEx httpRequest;	
	public HttpRequestEx cashRequest;	
	public HttpRequestEx cashHeartRequest;	
	public BaseParamBean baseParam = new BaseParamBean();
	PropertiesUtil propertyUtil = PropertiesUtil.getPropertiesUtil();
	
	public String host = propertyUtil.getValue("DEFAULT_HOST");
	public String cashHost = propertyUtil.getValue("CASH_HOST");
	public String cashHeartHost = propertyUtil.getValue("CASH_HEART_HOST");
	
	String uid;
	
	Map<String, String> parMap = new HashMap<String, String>();
	Map<String, String> cashMap = new HashMap<String, String>();
	
//	//从配置文件获取参数
//	String pidansessionKey = propertyUtil.getValue("pidansessionKey");
//	String pidansessionValue =  propertyUtil.getValue("pidansessionValue");
//	String pidantokenKey = propertyUtil.getValue("pidantokenKey");
//	String pidantokenBody = propertyUtil.getValue("pidantokenBody");	
	
	String appSecret = propertyUtil.getValue("appSecret");
	
	List<CartIncludeSuitForm> cartList = new ArrayList<CartIncludeSuitForm>();
	public static Gson gson = new Gson();
	CashService cashService = new CashService();
	
	public boolean verifyWaitingOrder(CashWaitingOrderVO cashWaitingOrderVO,List<CartIncludeSuitForm> cartList) {
		String cashjson = JSONObject.toJSONString(cashWaitingOrderVO.getWaitingInstances());
		System.out.println("cashjson:"+cashjson);
		
		String cartjson = JSONObject.toJSONString(cartList);
		System.out.println("cartjson:"+cartjson);
		
		//将小二的bean转为待比较的bean
		List<CartCompareBean> cartVos = JSONArray.parseArray(cartjson, CartCompareBean.class);
		
		//将收银的bean转为待比较的bean
		List<CartCompareBean> cashVos = JSONArray.parseArray(cashjson, CartCompareBean.class);
		
		if (cashVos.containsAll(cartVos)&&cartVos.containsAll(cashVos)) {
			return true;
		}else {
			System.err.println("与收银不一致");
			System.err.println("收银："+JSONObject.toJSONString(cashVos));
			System.err.println("小二："+JSONObject.toJSONString(cartVos));
			return false;
		}
	}


	public void createOrder() {
		logger.info("创建订单开始！");
		//扫码
		Assert.assertTrue(SeatNoPrePayAPI.scanCode(httpRequest, baseParam),"扫码流程有误！");
		//点击进入
		Assert.assertTrue(SeatNoPrePayAPI.clickToEnter(httpRequest, baseParam, parMap),"点击进入有误！");
		//开始点菜
		Assert.assertTrue(SeatNoPrePayAPI.startChooseDish(httpRequest, baseParam, parMap),"开始点菜有误");
		
		String menusJson = ShopPrePayAPI.commonMap.get("listMenu_data").toString();
		String menuId = (String) JSONPath.read(menusJson, "$.kindMenusVos[1].menus[0].id");
		Assert.assertNotNull(menuId,"获取menuId有误");
		System.out.println("menuId:"+menuId);
		parMap.put("menuId", menuId);
		//点菜
		Assert.assertTrue(SeatNoPrePayAPI.chooseDish(httpRequest, baseParam, parMap),"选择菜有误！");
		
		//加入购物车
		Double num = 1d;//菜的数量
		int kindType = 1;//菜的类型
		JSONObject menusObject  = (JSONObject) ShopPrePayAPI.commonMap.get("menuspec_data");
		String makeId = (String) JSONPath.read(menusObject.toString(), "$.makeDataList[0].makeId");
		String specId = (String) JSONPath.read(menusObject.toString(), "$.specDataList[0].specItemId");
		CartIncludeSuitForm cartIncludeSuitForm = new CartIncludeSuitForm();
		cartIncludeSuitForm.setMakeId(makeId);
		cartIncludeSuitForm.setMenuId(menuId);
		cartIncludeSuitForm.setSpecDetailId(specId);
		cartIncludeSuitForm.setNum(num);
		cartIncludeSuitForm.setKindType(kindType);
		cartIncludeSuitForm.setUid(uid);//给账单接口用
		
		SeatNoPrePayAPI.addDishToCarts(httpRequest, baseParam, cartIncludeSuitForm);
		cartList.add(cartIncludeSuitForm);
		
		parMap.put("page", "1");
		parMap.put("isPreCart", "false");
		parMap.put("pageSize", "5");
		
		SeatNoPrePayAPI.intoCart(httpRequest,baseParam,parMap,cartList);
		
		logger.info("创建订单结束！");
		
	}
	

	/**
	 * payorder
	 * @throws InterruptedException
	 * @throws JsonProcessingException
	 */
	public void payOrder() throws InterruptedException, JsonProcessingException {
		Thread.sleep(5000);
		//刷新已下单的页面 (收银已经审核)
		Response response1 = WechatBaseUtil.getOrder(httpRequest,baseParam);
		String orderId = (String) JSONPath.read(response1.getResponseStr(), "$.data.orderVos[0].orderId");
		Assert.assertNotNull(orderId,"订单ID为空！");
		logger.info("getorder orderId:"+orderId);
		baseParam.setOrderId(orderId);
				
		//启动一个线程计算账单
		ServiceBillThread serviceBillThread = new ServiceBillThread(cashService);
		Thread t = new Thread(serviceBillThread);
		t.start();
		//去支付
		baseParam.setCardId("");
		Assert.assertTrue(SeatNoPrePayAPI.intoBillPage(httpRequest,baseParam, "true", null), "获取账单失败");

		// 等待支付 获取支付类型
		Assert.assertTrue(SeatNoPrePayAPI.getPayType(httpRequest,baseParam, parMap, cartList), "获取支付方式失败");

		// 会员卡支付
		CardPayBean cardPayBean = new CardPayBean();
		Assert.assertTrue(SeatNoPrePayAPI.cardPay(httpRequest,baseParam, cardPayBean), "会员卡支付失败");
	}
	
	
}
