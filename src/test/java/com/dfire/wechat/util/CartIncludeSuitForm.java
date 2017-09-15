package com.dfire.wechat.util;

import java.util.List;

public class CartIncludeSuitForm {
	
	/**
     * 如果是套餐，则会有这个子菜数组
     */
    private List<CartIncludeSuitForm> childCartVos;
    private String uid;
    private String menuId;
    private String menuName;
    private String makeId;
    
//    @JsonProperty("specDetailId")
//    private String specId;
    
    private String specDetailId;
    private Double num;
    private String kindMenuId;
    
    private String kindMenuName;
    /**
     * 套菜子菜加价模式
     */
    private int addPriceMode;

    /**
     * 套菜子菜加价
     */
    private double addPrice;

    /**
     * 分组id
     */
    private String suitMenuDetailId;

    private int kindType;  // 1.普通菜  2.套菜  5.加料菜（只能在子child出现）
    
    /**
     * 索引 标识唯一的菜
     */
    private String index;

    public List<CartIncludeSuitForm> getChildCartVos() {
        return childCartVos;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setChildCartVos(List<CartIncludeSuitForm> childCartVos) {
        this.childCartVos = childCartVos;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMakeId() {
        return makeId;
    }

    public void setMakeId(String makeId) {
        this.makeId = makeId;
    }


    public Double getNum() {
        return num;
    }

    public void setNum(Double num) {
        this.num = num;
    }

    public String getKindMenuId() {
        return kindMenuId;
    }

    public void setKindMenuId(String kindMenuId) {
        this.kindMenuId = kindMenuId;
    }

    public String getSuitMenuDetailId() {
        return suitMenuDetailId;
    }

    public void setSuitMenuDetailId(String suitMenuDetailId) {
        this.suitMenuDetailId = suitMenuDetailId;
    }

    public int getAddPriceMode() {
        return addPriceMode;
    }

    public void setAddPriceMode(int addPriceMode) {
        this.addPriceMode = addPriceMode;
    }

    public double getAddPrice() {
        return addPrice;
    }

    public void setAddPrice(double addPrice) {
        this.addPrice = addPrice;
    }

    public int getKindType() {
        return kindType;
    }

    public void setKindType(int kindType) {
        this.kindType = kindType;
    }

	public String getSpecDetailId() {
		return specDetailId;
	}

	public void setSpecDetailId(String specDetailId) {
		this.specDetailId = specDetailId;
	}

	public String getKindMenuName() {
		return kindMenuName;
	}

	public void setKindMenuName(String kindMenuName) {
		this.kindMenuName = kindMenuName;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

}
