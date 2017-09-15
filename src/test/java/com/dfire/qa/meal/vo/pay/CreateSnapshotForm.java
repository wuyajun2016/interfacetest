/**
 * ==============================================================================
 * Copyright (c) 2016 by www.2dfire.com, All rights reserved.
 * ==============================================================================
 * This software is the confidential and proprietary information of
 * 2dfire.com, Inc. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with 2dfire.com, Inc.
 * ------------------------------------------------------------------------------
 * File name:  CreateSnapshotForm.java
 * Author: qiezi
 * Date: 2016/10/21 16:06
 * Description:
 * Nothing.
 * Function List:
 * 1. Nothing.
 * History:
 * 1. Nothing.
 * ==============================================================================
 */
package com.dfire.qa.meal.vo.pay;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * User: qiezi
 * Date: 2016/10/21
 * Time: 16:06
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateSnapshotForm extends QueryTradeBillForm {

	
    @JsonProperty("total_fee")
    private int total_fee;               //总金额，包含服务费
    
    @JsonProperty("origin_fee")
    private int origin_fee;              //原始金额，不包含服务费
    
    @JsonProperty("service_fee")
    private int service_fee;             //服务费
    
    @JsonProperty("discount_fee")
    private int discount_fee;             //优惠金额
    
    @JsonProperty("paid_fee")
    private int paid_fee;                //已付金额
    
    @JsonProperty("need_fee")
    private int need_fee;                //应付金额
    
    @JsonProperty("deduct_fee")
    private int deduct_fee;              //赞助抵扣金额
    
    @JsonProperty("promotion_fee")
    private int promotion_fee;           //优惠平台抵扣金额
    
    @JsonProperty("csrf_token")
    private String csrf_token;           //防重token
    
    // 父类已有该字段
//    private int source;                 //账单来源  1：普通下单 2：代付账单

    public int getTotalFee() {
        return total_fee;
    }

    public void setTotalFee(int totalFee) {
        this.total_fee = totalFee;
    }

    public int getOriginFee() {
        return origin_fee;
    }

    public void setOriginFee(int originFee) {
        this.origin_fee = originFee;
    }

    public int getServiceFee() {
        return service_fee;
    }

    public void setServiceFee(int serviceFee) {
        this.service_fee = serviceFee;
    }

    public int getDiscountFee() {
        return discount_fee;
    }

    public void setDiscountFee(int discountFee) {
        this.discount_fee = discountFee;
    }

    public int getPaidFee() {
        return paid_fee;
    }

    public void setPaidFee(int paidFee) {
        this.paid_fee = paidFee;
    }

    public int getNeedFee() {
        return need_fee;
    }

    public void setNeedFee(int needFee) {
        this.need_fee = needFee;
    }

    public int getDeductFee() {
        return deduct_fee;
    }

    public void setDeductFee(int deductFee) {
        this.deduct_fee = deductFee;
    }

    public int getPromotionFee() {
        return promotion_fee;
    }

    public void setPromotionFee(int promotionFee) {
        this.promotion_fee = promotionFee;
    }

    public String getCsrfToken() {
        return csrf_token;
    }

    public void setCsrfToken(String csrfToken) {
        this.csrf_token = csrfToken;
    }

}
