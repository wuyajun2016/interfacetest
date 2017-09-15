package com.dfire.testcase.function.boss;

import java.io.Serializable;

public class ForceConfig implements Serializable {
	
	private static final long serialVersionUID = 7553984067152012978L;
    /**
     * ID
     */
    private String configId;

    /**
     * 实体ID
     */

    private String entityId;

    /**
     * 商品ID
     */
    private String menuId;

    /**
     * 商品类型（0:普通菜,1:套菜,2:加料菜）
     */
    private Integer menuType;

    /**
     * 强制类型（0:指定数量,1:与用餐人数相同）
     */
    private Integer forceType;

    /**
     * 强制点菜数量（当强制类型为"与用餐人数相同"时无视此数量）
     */
    private Integer forceNum;

    /**
     * 做法
     */
    private ForceMenuMakeVo make;

    /**
     * 规格
     */
    private ForceMenuSpecVo spec;

    public String getConfigId() {
        return configId;
    }

    public void setConfigId(String configId) {
        this.configId = configId;
    }

    public ForceMenuSpecVo getSpec() {
        return spec;
    }

    public void setSpec(ForceMenuSpecVo spec) {
        this.spec = spec;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public Integer getMenuType() {
        return menuType;
    }

    public void setMenuType(Integer menuType) {
        this.menuType = menuType;
    }

    public Integer getForceType() {
        return forceType;
    }

    public void setForceType(Integer forceType) {
        this.forceType = forceType;
    }

    public Integer getForceNum() {
        return forceNum;
    }

    public void setForceNum(Integer forceNum) {
        this.forceNum = forceNum;
    }

    public ForceMenuMakeVo getMake() {
        return make;
    }

    public void setMake(ForceMenuMakeVo make) {
        this.make = make;
    }

}
