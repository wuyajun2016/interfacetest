package com.dfire.testcase.function.order;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.DataProvider;

import com.dfire.testcase.function.bean.QueryTradeBillForm;
import com.dfire.testcase.function.bean.TradeBillBean;
import com.dfire.wechat.util.BeanProvider;
import com.dfire.wechat.util.CartIncludeSuitForm;
import com.dfire.wechat.util.CommonConstants;
import com.google.gson.Gson;

public class OrderForPrePaySeatTestData {
	
	private static final String entityId = CommonConstants.entityId;
	private static final String seatCode = CommonConstants.seatCode;
	
	private static String uid = CommonConstants.UID;
	private static Gson gson = new Gson();
	
	@DataProvider(name = "orderForOneCustomer")
	public static Object [][] orderForOneCustomer(){
		
		
		String description1 = "用参人数为 6, 选择单个普通菜 '水果沙拉' 加入购物车";
		
		// 菜品的规格
		double menuNumber1 = 1;
		int kindType = 1;
		String makeId = ""; 
		String SpecDetailed = "";
		String menuName = "水果沙拉";
		String menuId = CommonConstants.menuId;
		
		CartIncludeSuitForm cartIncludeSuitForm = BeanProvider.getCartSuit(menuNumber1, kindType, makeId, SpecDetailed, menuName, menuId, uid);
		List<CartIncludeSuitForm> cartList = new ArrayList<CartIncludeSuitForm>();
		cartList.add(cartIncludeSuitForm);
		
		// 构造 支付账单中的 HTTP body 操作
		
		QueryTradeBillForm queryBillForm = new QueryTradeBillForm();
		queryBillForm.setEntityId(entityId);
		queryBillForm.setOrderId("");
		queryBillForm.setWaitingOrderId("");
		
		queryBillForm.setSeatCode(seatCode);
		queryBillForm.setSource(1);
		queryBillForm.setCart_forms_string(gson.toJson(cartList));
		
		queryBillForm.setGift_forms_string(true);
		
		
		TradeBillBean tradeBillBean = new TradeBillBean();
		tradeBillBean.setFlag("false");
		tradeBillBean.setSelected("true");
		tradeBillBean.setQuery_bill_form(queryBillForm);
		tradeBillBean.setCart_form(cartList);
		
		
		return new Object[][]{
				
				{description1, cartList, tradeBillBean},

		};
	}

}
