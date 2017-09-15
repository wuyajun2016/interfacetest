/**
 * ==============================================================================
 * Copyright (c) 2016 by www.2dfire.com, All rights reserved.
 * ==============================================================================
 * This software is the confidential and proprietary information of
 * 2dfire.com, Inc. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with 2dfire.com, Inc.
 * ------------------------------------------------------------------------------
 * File name:  QueryTradeBillDTO.java
 * Author: qiezi
 * Date: 2016/10/13 11:11
 * Description:
 * Nothing.
 * Function List:
 * 1. Nothing.
 * History:
 * 1. Nothing.
 * ==============================================================================
 */
package com.dfire.testcase.function.bean;

import bsh.This;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.Serializable;

/**
 * User: qiezi
 * Date: 2016/10/13
 * Time: 11:11
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryTradeBillForm implements Serializable {

    private static final long serialVersionUID = -9099162160384529863L;
    
    @JsonProperty("entity_id")
    private String entityId;            //店铺ID
    
    @JsonProperty("seat_code")
    private String seatCode;     
    
    //座位号
    @JsonInclude(Include.NON_NULL) 
    private String customerRegisterId;  //用户ID
    
    @JsonProperty("order_id")
    private String orderId;             //订单Id
    
    @JsonInclude(Include.NON_NULL) 
    private String waiting_order_id;      //预付款订单ID
    
    @JsonProperty("card_id")
    private String cardId;    
    
    //会员卡Id
    @JsonInclude(Include.NON_NULL) 
    @JsonProperty("coupon_id")
    private int couponId;               //红包ID
    
    @JsonInclude(Include.NON_NULL) 
    @JsonProperty("code_id")   
    private int codeId;                 //红包code
    
    private String sign;                //红包签名
    
    @JsonInclude(Include.NON_NULL) 
    @JsonProperty("shop_id")
    private String shopId;              //shopId
    
    @JsonProperty("promotion_id")
    private String promotionId;         //优惠平台promotionId
    
    @JsonProperty("promotion_customer_id")
    private String promotionCustomerId; //优惠平台 promotionCustomerId;
    
    private int source;                 //账单来源  1：普通下单 2：代付账单

    
    private String cart_forms_string;  // 该账单内所点的菜
    private boolean gift_forms_string; // 是否选中赞助礼品
    
    
    private Integer from_cart = 0;   // 此次新增
    
    
    
    public String getWaitingOrderId(){
    	return waiting_order_id;
    }
    
    public void setWaitingOrderId(String waitingOrderId){
    	this.waiting_order_id = waitingOrderId;
    }
    
    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getSeatCode() {
        return seatCode;
    }

    public void setSeatCode(String seatCode) {
        this.seatCode = seatCode;
    }

    public String getCustomerRegisterId() {
        return customerRegisterId;
    }

    public void setCustomerRegisterId(String customerRegisterId) {
        this.customerRegisterId = customerRegisterId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }



    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public int getCouponId() {
        return couponId;
    }

    public void setCouponId(int couponId) {
        this.couponId = couponId;
    }

    public int getCodeId() {
        return codeId;
    }

    public void setCodeId(int codeId) {
        this.codeId = codeId;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }


    public String getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(String promotionId) {
        this.promotionId = promotionId;
    }

    public String getPromotionCustomerId() {
        return promotionCustomerId;
    }

    public void setPromotionCustomerId(String promotionCustomerId) {
        this.promotionCustomerId = promotionCustomerId;
    }

    public int getSource() {
        return source;
    }

    public void setSource(int source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }

	public String getCart_forms_string() {
		return cart_forms_string;
	}

	public void setCart_forms_string(String cart_forms_string) {
		this.cart_forms_string = cart_forms_string;
	}

	public boolean isGift_forms_string() {
		return gift_forms_string;
	}

	public void setGift_forms_string(boolean gift_forms_string) {
		this.gift_forms_string = gift_forms_string;
	}

	public Integer getFrom_cart() {
		return from_cart;
	}

	public void setFrom_cart(Integer from_cart) {
		this.from_cart = from_cart;
	}
}
