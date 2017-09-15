package com.dfire.testcase.function.bean.cash;

import java.io.Serializable;

/**
 * 预付款折扣，会员卡信息等
 * Created by GuziZi on 2016/5/11.
 */
public class CardDto implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * kind的id
     */
    private String cardEntityId;
    /**
     * 会员卡id
     */
    private String cardId;
    /**
     * 会员卡卡号
     */
    private String code;

    /**
     * 卡类型的id
     */
    private String Id;

    /**
     * 卡类型的名字
     */
    private String name;

    /**
     * 卡类型折扣率
     */
    private Double ratio;

    /**
     * 卡类型优惠方式
     */
    private Integer mode;

    /**
     * 卡类型是否强制打折
     */
    private Short isForceRatio;

    public CardDto() {
    }


    public String getCardEntityId() {
        return cardEntityId;
    }

    public String getCardId() {
        return cardId;
    }

    public String getCode() {
        return code;
    }

    public String getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public Double getRatio() {
        return ratio;
    }

    public Integer getMode() {
        return mode;
    }

    public Short getIsForceRatio() {
        return isForceRatio;
    }

    public void setCardEntityId(String cardEntityId) {
        this.cardEntityId = cardEntityId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    public void setMode(Integer mode) {
        this.mode = mode;
    }

    public void setIsForceRatio(Short isForceRatio) {
        this.isForceRatio = isForceRatio;
    }
}
