package com.dfire.testcase.function.integration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.util.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.uncommons.reportng.HTMLReporter;

import com.alibaba.dubbo.remoting.http.HttpServer;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.dfire.testcase.function.bean.BaseParamBean;
import com.dfire.testcase.function.util.api.ShopNoPrePayAPI;
import com.dfire.wechat.util.CartIncludeSuitForm;
import com.dfire.wechat.util.CommonConstants;
import com.dfire.wechat.util.HttpRequestEx;
import com.dfire.wechat.util.PropertiesUtil;


/**
 * 扫店码非预付款流程
 * @author pidan
 *
 */
@Listeners(HTMLReporter.class)
public class ShopCodeNoPayTest{
	private static final Logger logger = Logger.getLogger(ShopCodeNoPayTest.class);
	PropertiesUtil propertyUtil = PropertiesUtil.getPropertiesUtil();
	BaseParamBean baseParamBean = new BaseParamBean();
	
	public String host = propertyUtil.getValue("DEFAULT_HOST");
	public HttpRequestEx httpRequest;	
	private boolean https = CommonConstants.HTTPS;
	
	@BeforeClass(alwaysRun = true)
	public void setup(){
		logger.info("扫店码非预付款 start!");
		httpRequest = new HttpRequestEx(host, https);

		String entityId = propertyUtil.getValue("entityId");
		String seatCode = "";
		String xtoken = propertyUtil.getValue("token").trim();
		String orderId = "";
		
		baseParamBean.setEntityId(entityId);
		baseParamBean.setOrderId(orderId);
		baseParamBean.setSeatCode(seatCode);
		baseParamBean.setXtoken(xtoken);
	}
	
	
	
	@Test(groups={"flowNo"})
	public void apiTest(){
		List<CartIncludeSuitForm> cartList= new ArrayList<CartIncludeSuitForm>();
		Map<String, String> parMap = new HashMap<String, String>();
		//扫店码
		Boolean scancodeflag = ShopNoPrePayAPI.scanCode(httpRequest,baseParamBean);
		Assert.isTrue(scancodeflag,"扫码失败");
		
		//点击进入
		String repeat = "false";
		String recommend = "false";                          
		parMap.put("repeat", repeat);
		parMap.put("recommend", recommend);
		parMap.put("queueId", "");
		Boolean clEnFlag = ShopNoPrePayAPI.clickToEnter(httpRequest, baseParamBean, parMap);
		Assert.isTrue(clEnFlag,"点击进入流程失败");
		
		//点菜
		//获取menuId
		String menusJson = ShopNoPrePayAPI.commonMap.get("listMenu_data").toString();
		String menuId = (String) JSONPath.read(menusJson, "$.kind_menus[0].menus[0].id");
		Double num = 1d;//菜的数量
		int kindType = 1;//菜的类型
		parMap.put("menuId", menuId);
		Boolean chooseDishFlag = ShopNoPrePayAPI.chooseDish(httpRequest, baseParamBean,parMap);
		Assert.isTrue(chooseDishFlag,"获取菜规格和做法失败");
		
		//将菜加入购物车
		JSONObject menusObject  = (JSONObject) ShopNoPrePayAPI.commonMap.get("menuspec_data");
		String makeId = (String) JSONPath.read(menusObject.toString(), "$.makeDataList[0].makeId");
		String specId = (String) JSONPath.read(menusObject.toString(), "$.specDataList[0].specItemId");
		CartIncludeSuitForm cartIncludeSuitForm = new CartIncludeSuitForm();
		cartIncludeSuitForm.setMakeId(makeId);
		cartIncludeSuitForm.setMenuId(menuId);
		cartIncludeSuitForm.setSpecDetailId(specId);
		cartIncludeSuitForm.setNum(num);
		cartIncludeSuitForm.setKindType(kindType);
		Boolean addToCartFlag = ShopNoPrePayAPI.addDishToCarts(httpRequest, baseParamBean,cartIncludeSuitForm);
		Assert.isTrue(addToCartFlag,"加入购物车失败！");
		
		cartList.add(cartIncludeSuitForm);
		
		
		//进入购物车
		String isPreCart = "true";
		String page= "1";
		String pageSize = "12";
		
		Map<String, String> intoCartMap = new HashMap<String, String>();
		intoCartMap.put("page", page);
		intoCartMap.put("isPreCart", isPreCart);
		intoCartMap.put("pageSize", pageSize);
		intoCartMap.put("queueId", "");
		Boolean intoCartFlag = ShopNoPrePayAPI.intoCart(httpRequest,baseParamBean,intoCartMap);
		Assert.isTrue(intoCartFlag,"进入购物车失败");
		
		//非预付款
		//扫桌码
//		seatCode = propertyUtil.getValue("seatCode");
//		Boolean seatcodeflag = ShopNoPrePayAPI.scanCode(httpRequest,entityId,seatCode);
//		Assert.isTrue(seatcodeflag,"扫桌码失败");
//		
//		//点击进入 进入店铺主页
//		Boolean clicBoolean = ShopNoPrePayAPI.intoHomePage(httpRequest,entityId,seatCode, repeat, recommend, orderId);
//		Assert.isTrue(clicBoolean,"点击进入店铺主页流程失败"); 
//		
//		//后面都为扫码流程
//		//选择去下单 到购物车 其实是扫桌码的购物车
//		String peopleCount = "10";
//		Boolean toSeatCodeCartFlag = SeatNoPrePayAPI.intoCart(httpRequest, entityId, seatCode, orderId, menuIdList, intoCartMap, cartList);
//		Assert.isTrue(toSeatCodeCartFlag,"去下单失败");
//		
//		//提交订单
//		Boolean refreshOrderFlag = ShopNoPrePayAPI.submitOrder(httpRequest, entityId, seatCode, orderId, peopleCount, cartList);
//		Assert.isTrue(refreshOrderFlag,"提交订单");
		
		//去支付
		//TODO 
		
		//等待支付
		//TODO
		
		//支付
		//TODO 
		
				
	}
	
	@AfterClass
	public void after(){
		httpRequest.ShutDown();
	}

}
