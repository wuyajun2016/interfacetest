package com.dfire.testcase.function.bean;

import java.util.List;

import com.dfire.wechat.util.CartIncludeSuitForm;
import com.google.gson.Gson;

public class TradeBillBean {
	
	
	private Gson gson = new Gson();
	
	/**
	 * "是否重新拉取账单"
	 */
	private String flag;
	
	
	/**
	 * I don't know
	 */
	private String selected;
	
	/**
	 * 查询形式的账单
	 */
	private QueryTradeBillForm query_bill_form;
	
	
	/**
	 * 该账单中包含的菜单
	 */
	private List<CartIncludeSuitForm> cart_form;
	
	
	/**
	 * 将 bean 装换成 json 形式的 string
	 * @return
	 */
	public String toJson(){
		
		String result = null;
		
		result = gson.toJson(this);
		
		return result;
	}

	
	

	public String getFlag() {
		return flag;
	}


	public void setFlag(String flag) {
		this.flag = flag;
	}


	public String getSelected() {
		return selected;
	}


	public void setSelected(String selected) {
		this.selected = selected;
	}


	public QueryTradeBillForm getQuery_bill_form() {
		return query_bill_form;
	}


	public void setQuery_bill_form(QueryTradeBillForm query_bill_form) {
		this.query_bill_form = query_bill_form;
	}


	public List<CartIncludeSuitForm> getCart_form() {
		return cart_form;
	}


	public void setCart_form(List<CartIncludeSuitForm> cart_form) {
		this.cart_form = cart_form;
	}

}
