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
package com.dfire.qa.meal.vo.pay;

import bsh.This;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.dfire.soa.consumer.vo.CardPayVo;
import com.dfire.soa.consumer.vo.privilege.TradePromotionParam;

import java.io.Serializable;
import java.util.List;

/**
 * User: qiezi
 * Date: 2016/10/13
 * Time: 11:11
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueryTradeBillForm implements Serializable {

    private static final long serialVersionUID = -9099162160384529863L;
    
    private String entityId;            //店铺ID

    private String seatCode;            //座位号

    private String customerRegisterId;  //用户ID

    @JsonProperty("order_id")
    private String orderId;             //订单Id

    @JsonProperty("waiting_order_id")
    private String waitingOrderId;      //预付款订单ID
    
    @JsonProperty("card_id")
    private String cardId;              //会员卡Id
    
    @JsonProperty("coupon_id")
    private int couponId;               //红包ID
    
    @JsonProperty("code_id")
    private int codeId;                 //红包code
    
    private String sign;                //红包签名
    
    @JsonProperty("shop_id")
    private String shopId;              //shopId
    
    private short orderFrom;            //订单来源
    
    @JsonProperty("promotion_params_string")
    private List<TradePromotionParam> tradePromotionParamList; //优惠平台优惠信息
    
    private int source;                 //账单来源  1：普通下单 2：代付账单
    
    private List<CardPayVo> cardPay; //会员卡支付信息
    
    
    
  
    
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

	public short getOrderFrom() {
		return orderFrom;
	}

	public void setOrderFrom(short orderFrom) {
		this.orderFrom = orderFrom;
	}

	public List<CardPayVo> getCardPay() {
		return cardPay;
	}

	public void setCardPay(List<CardPayVo> cardPay) {
		this.cardPay = cardPay;
	}

	
	
}
