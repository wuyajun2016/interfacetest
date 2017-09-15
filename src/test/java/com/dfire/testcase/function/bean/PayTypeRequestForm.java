/**
 * ==============================================================================
 * Copyright (c) 2016 by www.2dfire.com, All rights reserved.
 * ==============================================================================
 * This software is the confidential and proprietary information of
 * 2dfire.com, Inc. ("Confidential Information"). You shall not disclose
 * such Confidential Information and shall use it only in accordance
 * with the terms of the license agreement you entered into with 2dfire.com, Inc.
 * ------------------------------------------------------------------------------
 * File name:  PayTypE.java
 * Author: qiezi
 * Date: 2016/10/12 17:35
 * Description:
 * Nothing.
 * Function List:
 * 1. Nothing.
 * History:
 * 1. Nothing.
 * ==============================================================================
 */
package com.dfire.testcase.function.bean;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.dfire.soa.consumer.param.SupportGift;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * User: qiezi
 * Date: 2016/10/12
 * Time: 17:35
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PayTypeRequestForm implements Serializable {

    private static final long serialVersionUID = 2378526190374627477L;

    @JsonProperty("create_snapshot_form")
    private CreateSnapshotForm create_snapshot_form;  //接口传递参数
    
    @JsonProperty("gift_form")
    List<SupportGift> gift_form;                     //礼品数据

    public CreateSnapshotForm getCreateSnapshotForm() {
        return create_snapshot_form;
    }

    public void setCreateSnapshotForm(CreateSnapshotForm createSnapshotForm) {
        this.create_snapshot_form = createSnapshotForm;
    }

    public List<SupportGift> getGiftForm() {
        return gift_form;
    }

    public void setGiftForm(List<SupportGift> giftForm) {
        this.gift_form = giftForm;
    }


    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }
}
