/*************************
 * 版权声明 **********************************
 * 版权所有：Copyright (c) 2011, ${year} 黄晓峰
 * <p/>
 * 工程名称：	钻木餐饮系统
 * 创建者： 黄晓峰 创建日期： ${date}
 * 创建记录：	创建类结构。
 **/

package com.dfire.testcase.function.bean.cash;

import com.dfire.test.util.StringUtil;
import com.dfire.testcase.function.bean.cash.base.BaseWaitingInstance;


/**
 * 点菜明细.
 *
 * @author <a href="mailto:xiaofenguser@163.com">黄晓峰</a>
 * @version $Revision$
 */
public class WaitingInstance extends BaseWaitingInstance {
    /**
     * <code>序列ID</code>.
     */
    private static final long serialVersionUID = 1L;
    /**
     * <code>已下单</code>.
     */
    public static final Short STATUS_WORK = (short) 9;
    /**
     * <code>失败</code>.
     */
    public static final Short STATUS_DELETE = (short) 3;
    /**
     * <code>超时</code>.
     */
    public static final Short STATUS_IN_PROCESS = (short) 2;
    /**
     * <code>待审核标记</code>.
     */
    public static final Short STATUS_PROCESS_SUCCESS = (short) 1;
    /**
     * <code>未处理标记</code>.
     */
    public static final Short STATUS_NO_OPERATION = (short) 0;

    /**
     * <code>普通菜</code>.
     */
    public static final Short KIND_NORMAL = (short) 1;

    /**
     * <code>套菜</code>.
     */
    public static final Short KIND_SUIT = (short) 2;

    /**
     * <code>自定义菜</code>.
     */
    public static final Short KIND_SELF = (short) 3;

    /**
     * <code>自定义套菜</code>.
     */
    public static final Short KIND_SELF_SUIT = (short) 4;

    /**
     * <code>加料菜</code>.
     */
    public static final Short KIND_ADDITION = (short) 5;

    public static final double ZERO = 0.005d;

    /**
     * <code>菜肴的显示价格</code>.
     */
    private Double showPrice;

    /**
     * <code>套菜父菜的id对应的字段</code>.
     */
    public static final String PARENTID = "PARENTID";

    /**
     * <code>套菜父菜的id对应的字段</code>.
     */
    private String parentId;

    /**
     * 加料菜名拼接字符串
     */
    private String additionMaterials;

    /**
     * 加料菜总价
     */
    private Double additionPrice;

    /**
     * 得到加料菜总价
     *
     * @return
     */
    public Double getAdditionPrice() {
        return additionPrice;
    }

    /**
     * 设置加料菜总价
     *
     * @param price
     * @return
     */
    public void setAdditionPrice(Double price) {
        additionPrice = price;
    }

    /**
     * 得到加料拼接字符串
     *
     * @return
     */
    public String getAdditionMaterials() {
        return additionMaterials;
    }

    /**
     * 设置加料拼接字符串
     *
     * @param addmaterials
     */
    public void setAdditionMaterials(String addmaterials) {
        additionMaterials = addmaterials;
    }

    /**
     * <code>待叫字段</code>
     */
    private Short isWait;

    /**
     * 得到套菜父菜的id
     *
     * @return parentId
     */

    public String getParentId() {
        return parentId;
    }

    /**
     * 设置套菜父菜的id
     *
     * @param parentId
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * 得到菜肴的显示价格.
     *
     * @return 菜肴的显示价格.
     */
    public Double getShowPrice() {
        return showPrice;
    }

    /**
     * 设置菜肴的显示价格.
     *
     * @param showPrice 菜肴的显示价格.
     */
    public void setShowPrice(Double showPrice) {
        this.showPrice = showPrice;
    }

    /**
     * 设置待叫字段
     *
     * @param isWait
     */
    public void setIsWait(Short isWait) {
        this.isWait = isWait;
    }

    /**
     * 得到待叫字段
     *
     * @return
     */
    public Short getIsWait() {
        return isWait;
    }

    /**
     * 撞餐的价格
     */
    private double hitPrice;

    public double getHitPrice() {
        return hitPrice;
    }

    public void setHitPrice(double hitPrice) {
        this.hitPrice = hitPrice;
    }

    /**
     * 更新费用.
     */
    public void updateFee() {
        Double fee = updateBaseFee(this.getPrice() + this.getHitPrice());
        if (fee == null || fee == 0) {
            this.setFee(NumberUtils.round(0.0));
        } else {
            this.setFee(NumberUtils.round(fee));
        }
    }

    /**
     * 根据基本价格更新
     *
     * @param price
     */
    private Double updateBaseFee(Double price) {
        double fee = 0;
        if (getOriginalPrice() == null) {
            setOriginalPrice(0d);
        }
        // 非赠菜
        if (!(getOriginalPrice().doubleValue() > ZERO && price.doubleValue() < ZERO)) {
            if (Math.abs(price.doubleValue()) > ZERO) {
                if (this.getNum() != null) {
                    if (this.getAccountNum() == null) {
                        this.setAccountNum(this.getNum());
                    }
                    fee = getAccountNum() * price;
                }
            }
            if (this.getMakePrice() != null && this.getMakeId() != null) {
                if (MenuMake.MAKEPRICE_PERBUYACCOUNT.equals(getMakePriceMode())) {
                    fee += getNum() * getMakePrice();
                } else if (MenuMake.MAKEPRICE_TOTAL.equals(getMakePriceMode())) {
                    fee += getMakePrice();
                } else if (MenuMake.MAKEPRICE_PERUNIT.equals(getMakePriceMode())) {
                    fee += getAccountNum() * getMakePrice();
                }
            }
        }
        if (this.getAdditionPrice() != null) {
            fee += getNum() * getAdditionPrice();
        }
        return fee;
    }

    /**
     * 更新价格.
     */
    public void updatePrice()  {
        if (getPrice() != null && getSpecDetailPrice() != null
                && getSpecPriceMode() != null) {
            if (this.isSuit()) {
                return;
            }
            double price = 0;
            if (getOriginalPrice() == null) {
                setOriginalPrice(0d);
            }
            // 非赠菜，计算规格
            if (!(getOriginalPrice().doubleValue() > ZERO && getPrice().doubleValue() < ZERO)) {
                if (SpecDetail.PRICE_MODE_ADD.equals(getSpecPriceMode())) {
                    price = getOriginalPrice() + getSpecDetailPrice();
                } else {
                    price = getOriginalPrice() * (1 + getSpecDetailPrice() / 100);
                }
                setShowPrice(NumberUtils.round(price));
            } else {
//                throw new DomainBizException("尚未初始化价格,或规格加价!");
            }
        }
    }

    /**
     * 是否双单位.
     *
     * @return true, 双单位.
     */
    public boolean isTwoAccount() {
        if (StringUtil.isEmpty(getAccountUnit())) {
            return false;
        }
        if (!getAccountUnit().equals(getUnit())) {
            return true;
        }
        return false;
    }

    public WaitingInstance copyNew() {
        WaitingInstance instance = new WaitingInstance();
        instance.setAccountNum(getAccountNum());
        instance.setAccountUnit(getAccountUnit());
        instance.setChildId(getChildId());
        instance.setCreateTime(getCreateTime());
        instance.setFee(getFee());
        instance.setId(getId());
        instance.setIsBackAuth(getIsBackAuth());
        instance.setIsRatio(getIsRatio());
        instance.setIsValid(getIsValid());
        instance.setKind(getKind());
        instance.setKindMenuId(getKindMenuId());
        instance.setLastVer(getLastVer());
        instance.setMakeId(getMakeId());
        instance.setMakeName(getMakeName());
        instance.setMakePrice(getMakePrice());
        instance.setMakePriceMode(getMakePriceMode());
        instance.setMemberPrice(getMemberPrice());
        instance.setMemo(getMemo());
        instance.setMenuId(getMenuId());
        instance.setName(getName());
        instance.setNum(getNum());
        instance.setOpTime(getOpTime());
        instance.setOrderId(getOrderId());
        instance.setOriginalPrice(getOriginalPrice());
        instance.setPrice(getPrice());
        instance.setPriceMode(getPriceMode());
        instance.setRatio(getRatio());
        instance.setRatioFee(getRatioFee());
        instance.setServiceFee(getServiceFee());
        instance.setServiceFeeMode(getServiceFeeMode());
        instance.setShowPrice(getShowPrice());
        instance.setSpecDetailId(getSpecDetailId());
        instance.setSpecDetailName(getSpecDetailName());
        instance.setSpecDetailPrice(getSpecDetailPrice());
        instance.setSpecPriceMode(getSpecPriceMode());
        instance.setTaste(getTaste());
        instance.setUnit(getUnit());
        instance.setStatus(getStatus());
        instance.setAdditionMaterials(getAdditionMaterials());
        instance.setAdditionPrice(getAdditionPrice());
        instance.setIsWait(getIsWait());
        return instance;
    }

    /**
     * 判断是否是子菜.
     *
     * @return
     */
    public boolean isChild() {
        return StringUtil.isNotEmpty(getParentId()) && !"0".equals(getParentId());
    }

    /**
     * 判断是否是加料菜.
     *
     * @return
     */
    public boolean isCharging() {
        return KIND_ADDITION.equals(getKind());
    }

    /**
     * 判断是否是套菜子菜.
     *
     * @return
     */
    public boolean isSuitChild() {
        return !getKind().equals(WaitingInstance.KIND_ADDITION) && StringUtil.isNotEmpty(getParentId()) && !"0".equals(getParentId());
    }

    /**
     * 判断是否是套菜
     *
     * @return
     */
    public boolean isSuit() {
        return WaitingInstance.KIND_SELF_SUIT.equals(getKind()) || WaitingInstance.KIND_SUIT.equals(getKind());
    }
}
