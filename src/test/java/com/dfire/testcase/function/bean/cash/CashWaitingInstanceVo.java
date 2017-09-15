package com.dfire.testcase.function.bean.cash;

import com.dfire.soa.order.bo.WaitingInstance;

import java.io.Serializable;

/**
 * 收银机使用的waitinginstance对象
 * Created by <a href="liao670223382@163.com">shengchou</a> on 2016/5/28.
 */
public class CashWaitingInstanceVo implements Serializable {
    /**
     * 主键
     */
    private String id;
    /**
     * waitingorderid，才审核订单
     */
    private String orderId;
    /**
     * 菜单名称
     */
    private String name;
    /**
     * 菜id
     */
    private String menuId;
    /**
     * 做法id
     */
    private String makeId;
    /**
     * 做法名称
     */
    private String makeName;
    /**
     * 做法价格
     */
    private Double makePrice;
    /**
     * 做法加价模式
     */
    private Short makePriceMode;
    /**
     * 规格名称
     */
    private String specDetailName;
    /**
     * 规格名称id
     */
    private String specDetailId;
    /**
     * 规格加价模式
     */
    private Short specPriceMode;
    /**
     * 规格加价
     */
    private Double specDetailPrice;
    /**
     * 份数
     */
    private Double num;
    /**
     * 结账份数
     */
    private Double accountNum;
    /**
     * 单位
     */
    private String unit;
    /**
     * 结账单位
     */
    private String accountUnit;
    /**
     * 备注
     */
    private String memo;
    /**
     * 原始单价
     */
    private Double originalPrice;
    /**
     * 单价
     */
    private Double price;

    /**
     * 总价
     */
    private Double fee;
    /**
     * 折扣的费用
     */
    private Double ratioFee;
    /**
     * 折扣率
     */
    private Double ratio;
    /**
     * 是否打折
     */
    private Short isRatio;
    /**
     * 类型
     */
    private Short kind;
    /**
     * 计价模式
     */
    private Short priceMode;
    /**
     * 菜类id
     */
    private String kindMenuId;
    /**
     * <code>taste对应的字段</code>.
     */
    private String taste;
    /**
     * 口味
     */
    private Short status;

    /**
     * 套菜或者加料菜的父类id
     */
    private String parentId;
    /**
     * 套菜子菜id
     */
    private String childId;

    private int type = 0;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public CashWaitingInstanceVo(){

    }

    public CashWaitingInstanceVo(WaitingInstance waitingInstance) {
        if(null == waitingInstance) return;
        this.id = waitingInstance.getId();
        this.orderId = waitingInstance.getOrderId();
        this.name = waitingInstance.getName();
        this.menuId = waitingInstance.getMenuId();
        this.makeId = waitingInstance.getMakeId();
        this.makeName = waitingInstance.getMakeName();
        this.makePrice = waitingInstance.getMakePrice();
        this.makePriceMode = waitingInstance.getMakePriceMode();
        this.specDetailName = waitingInstance.getSpecDetailName();
        this.specDetailId = waitingInstance.getSpecDetailId();
        this.specPriceMode = waitingInstance.getSpecPriceMode();
        this.specDetailPrice = waitingInstance.getSpecDetailPrice();
        this.num = waitingInstance.getNum();
        this.accountNum = waitingInstance.getAccountNum();
        this.unit = waitingInstance.getUnit();
        this.accountUnit = waitingInstance.getAccountUnit();
        this.memo = waitingInstance.getMemo();
        this.originalPrice = waitingInstance.getOriginalPrice();
        this.price = waitingInstance.getPrice();
        this.fee = waitingInstance.getFee();
        this.ratioFee = waitingInstance.getRatioFee();
        this.ratio = waitingInstance.getRatio();
        this.isRatio = waitingInstance.getIsRatio();
        this.kind = waitingInstance.getKind();
        this.priceMode = waitingInstance.getPriceMode();
        this.kindMenuId = waitingInstance.getKindMenuId();
        this.taste = waitingInstance.getTaste();
        this.status = waitingInstance.getStatus();
        this.parentId = waitingInstance.getParentId();
        this.childId = waitingInstance.getChildId();
        this.type = waitingInstance.getType();
    }

    /**
     * 设置.
     *
     * @return orderId
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 得到.
     * orderId 要设置的 orderId
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * 设置.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 得到.
     * name 要设置的 name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 设置.
     *
     * @return menuId
     */
    public String getMenuId() {
        return menuId;
    }

    /**
     * 得到.
     * menuId 要设置的 menuId
     */
    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    /**
     * 设置.
     *
     * @return makeId
     */
    public String getMakeId() {
        return makeId;
    }

    /**
     * 得到.
     * makeId 要设置的 makeId
     */
    public void setMakeId(String makeId) {
        this.makeId = makeId;
    }

    /**
     * 设置.
     *
     * @return makeName
     */
    public String getMakeName() {
        return makeName;
    }

    /**
     * 得到.
     * makeName 要设置的 makeName
     */
    public void setMakeName(String makeName) {
        this.makeName = makeName;
    }

    /**
     * 设置.
     *
     * @return makePrice
     */
    public Double getMakePrice() {
        return makePrice;
    }

    /**
     * 得到.
     * makePrice 要设置的 makePrice
     */
    public void setMakePrice(Double makePrice) {
        this.makePrice = makePrice;
    }

    /**
     * 设置.
     *
     * @return makePriceMode
     */
    public Short getMakePriceMode() {
        return makePriceMode;
    }

    /**
     * 得到.
     * makePriceMode 要设置的 makePriceMode
     */
    public void setMakePriceMode(Short makePriceMode) {
        this.makePriceMode = makePriceMode;
    }

    /**
     * 设置.
     *
     * @return specDetailName
     */
    public String getSpecDetailName() {
        return specDetailName;
    }

    /**
     * 得到.
     * specDetailName 要设置的 specDetailName
     */
    public void setSpecDetailName(String specDetailName) {
        this.specDetailName = specDetailName;
    }

    /**
     * 设置.
     *
     * @return specDetailId
     */
    public String getSpecDetailId() {
        return specDetailId;
    }

    /**
     * 得到.
     * specDetailId 要设置的 specDetailId
     */
    public void setSpecDetailId(String specDetailId) {
        this.specDetailId = specDetailId;
    }

    /**
     * 设置.
     *
     * @return specPriceMode
     */
    public Short getSpecPriceMode() {
        return specPriceMode;
    }

    /**
     * 得到.
     * specPriceMode 要设置的 specPriceMode
     */
    public void setSpecPriceMode(Short specPriceMode) {
        this.specPriceMode = specPriceMode;
    }

    /**
     * 设置.
     *
     * @return specDetailPrice
     */
    public Double getSpecDetailPrice() {
        return specDetailPrice;
    }

    /**
     * 得到.
     * specDetailPrice 要设置的 specDetailPrice
     */
    public void setSpecDetailPrice(Double specDetailPrice) {
        this.specDetailPrice = specDetailPrice;
    }

    /**
     * 设置.
     *
     * @return num
     */
    public Double getNum() {
        return num;
    }

    /**
     * 得到.
     * num 要设置的 num
     */
    public void setNum(Double num) {
        this.num = num;
    }

    /**
     * 设置.
     *
     * @return accountNum
     */
    public Double getAccountNum() {
        return accountNum;
    }

    /**
     * 得到.
     * accountNum 要设置的 accountNum
     */
    public void setAccountNum(Double accountNum) {
        this.accountNum = accountNum;
    }

    /**
     * 设置.
     *
     * @return unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * 得到.
     * unit 要设置的 unit
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * 设置.
     *
     * @return accountUnit
     */
    public String getAccountUnit() {
        return accountUnit;
    }

    /**
     * 得到.
     * accountUnit 要设置的 accountUnit
     */
    public void setAccountUnit(String accountUnit) {
        this.accountUnit = accountUnit;
    }

    /**
     * 设置.
     *
     * @return memo
     */
    public String getMemo() {
        return memo;
    }

    /**
     * 得到.
     * memo 要设置的 memo
     */
    public void setMemo(String memo) {
        this.memo = memo;
    }

    /**
     * 设置.
     *
     * @return originalPrice
     */
    public Double getOriginalPrice() {
        return originalPrice;
    }

    /**
     * 得到.
     * originalPrice 要设置的 originalPrice
     */
    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    /**
     * 设置.
     *
     * @return price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 得到.
     * price 要设置的 price
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 设置.
     *
     * @return fee
     */
    public Double getFee() {
        return fee;
    }

    /**
     * 得到.
     * fee 要设置的 fee
     */
    public void setFee(Double fee) {
        this.fee = fee;
    }

    /**
     * 设置.
     *
     * @return ratioFee
     */
    public Double getRatioFee() {
        return ratioFee;
    }

    /**
     * 得到.
     * ratioFee 要设置的 ratioFee
     */
    public void setRatioFee(Double ratioFee) {
        this.ratioFee = ratioFee;
    }

    /**
     * 设置.
     *
     * @return ratio
     */
    public Double getRatio() {
        return ratio;
    }

    /**
     * 得到.
     * ratio 要设置的 ratio
     */
    public void setRatio(Double ratio) {
        this.ratio = ratio;
    }

    /**
     * 设置.
     *
     * @return isRatio
     */
    public Short getIsRatio() {
        return isRatio;
    }

    /**
     * 得到.
     * isRatio 要设置的 isRatio
     */
    public void setIsRatio(Short isRatio) {
        this.isRatio = isRatio;
    }

    /**
     * 设置.
     *
     * @return kind
     */
    public Short getKind() {
        return kind;
    }

    /**
     * 得到.
     * kind 要设置的 kind
     */
    public void setKind(Short kind) {
        this.kind = kind;
    }

    /**
     * 设置.
     *
     * @return priceMode
     */
    public Short getPriceMode() {
        return priceMode;
    }

    /**
     * 得到.
     * priceMode 要设置的 priceMode
     */
    public void setPriceMode(Short priceMode) {
        this.priceMode = priceMode;
    }

    /**
     * 设置.
     *
     * @return kindMenuId
     */
    public String getKindMenuId() {
        return kindMenuId;
    }

    /**
     * 得到.
     * kindMenuId 要设置的 kindMenuId
     */
    public void setKindMenuId(String kindMenuId) {
        this.kindMenuId = kindMenuId;
    }

    /**
     * 设置.
     *
     * @return taste
     */
    public String getTaste() {
        return taste;
    }

    /**
     * 得到.
     * taste 要设置的 taste
     */
    public void setTaste(String taste) {
        this.taste = taste;
    }

    /**
     * 设置.
     *
     * @return status
     */
    public Short getStatus() {
        return status;
    }

    /**
     * 得到.
     * status 要设置的 status
     */
    public void setStatus(Short status) {
        this.status = status;
    }

    /**
     * 设置.
     *
     * @return parentId
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * 得到.
     * parentId 要设置的 parentId
     */
    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * 设置.
     *
     * @return childId
     */
    public String getChildId() {
        return childId;
    }

    /**
     * 得到.
     * childId 要设置的 childId
     */
    public void setChildId(String childId) {
        this.childId = childId;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
