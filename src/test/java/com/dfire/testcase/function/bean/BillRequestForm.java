/**
 * ==============================================================================
 * Copyright (c) 2016 by www.2dfire.com, All rights reserved.
 * ==============================================================================
 * This software is the confidential and proprietary information of
 * 2dfire.com, Inc. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with 2dfire.com, Inc.
 * ------------------------------------------------------------------------------
 * File name:  BillRequestParam.java
 * Author: qiezi
 * Date: 2016/10/12 17:34
 * Description:
 * Nothing.
 * Function List:
 * 1. Nothing.
 * History:
 * 1. Nothing.
 * ==============================================================================
 */
package com.dfire.testcase.function.bean;


import com.dfire.qa.meal.vo.cart.CartIncludeSuitForm;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * User: qiezi
 * Date: 2016/10/12
 * Time: 17:34
 * 明天修改
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BillRequestForm implements Serializable {

	private static final long serialVersionUID = 5870155590085209261L;
	

    private QueryTradeBillForm query_bill_form;   //接口传递参数
    
    @JsonProperty("cart_form")
    private List<CartIncludeSuitForm> cartForms;    //购物车数据（预付款上传菜品信息）
    
    private Boolean flag;                           //是否重新拉取账单
    private Boolean selected;                       //是否选中赞助礼品
    
    private Long cart_time = 0L;
    
    private Integer from_cart = 0;



    public List<CartIncludeSuitForm> getCartForms() {
        return cartForms;
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public Boolean getSelected() {
        return selected;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public void setCartForms(List<CartIncludeSuitForm> cartForms) {
        this.cartForms = cartForms;
    }

	public Integer getFrom_cart() {
		return from_cart;
	}

	public void setFrom_cart(Integer from_cart) {
		this.from_cart = from_cart;
	}

	public Long getCart_time() {
		return cart_time;
	}

	public void setCart_time(Long cart_time) {
		this.cart_time = cart_time;
	}

	public QueryTradeBillForm getQuery_bill_form() {
		return query_bill_form;
	}

	public void setQuery_bill_form(QueryTradeBillForm query_bill_form) {
		this.query_bill_form = query_bill_form;
	}
}
