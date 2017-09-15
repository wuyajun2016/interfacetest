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
package com.dfire.qa.meal.vo.pay;


import com.dfire.qa.meal.vo.cart.CartIncludeSuitForm;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * User: qiezi
 * Date: 2016/10/12
 * Time: 17:34
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BillRequestForm implements Serializable {

    private static final long serialVersionUID = 5870155590085209261L;
    
    @JsonProperty("query_bill_form")
    private QueryTradeBillForm queryTradeBillForm;   //接口传递参数
    
    @JsonProperty("cart_form")
    private List<CartIncludeSuitForm> cartForms;    //购物车数据（预付款上传菜品信息）
    
    private Boolean flag;                           //是否重新拉取账单
    private Boolean selected;                       //是否选中赞助礼品
    
    private Boolean cardSelected;                       //是否选中会员卡支付
    
    @JsonProperty("cart_time")
    private Long cartTime = 0L;
    
    @JsonProperty("from_cart")
    private Integer fromCart = 0;
    
    private Integer firstLoading ;   //1: 第一次进入账单   2：非第一次进入账单

    public QueryTradeBillForm getQueryTradeBillForm() {
        return queryTradeBillForm;
    }

    public void setQueryTradeBillForm(QueryTradeBillForm queryTradeBillForm) {
        this.queryTradeBillForm = queryTradeBillForm;
    }

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

    public Long getCartTime() {
        return cartTime;
    }

    public void setCartTime(Long cartTime) {
        this.cartTime = cartTime;
    }

    public Integer getFromCart() {
        return fromCart;
    }

    public void setFromCart(Integer fromCart) {
        this.fromCart = fromCart;
    }

    public Boolean getCardSelected() {
        return cardSelected;
    }

    public void setCardSelected(Boolean cardSelected) {
        this.cardSelected = cardSelected;
    }

    public Integer getFirstLoading() {
        return firstLoading;
    }

    public void setFirstLoading(Integer firstLoading) {
        this.firstLoading = firstLoading;
    }
}
