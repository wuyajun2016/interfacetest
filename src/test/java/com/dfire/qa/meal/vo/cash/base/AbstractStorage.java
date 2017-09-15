package com.dfire.qa.meal.vo.cash.base;

import java.io.Serializable;


/**
 * 基类.
 * 可持久化的基类.
 *
 * @author <a href="mailto:chayou@2dfire.com">茶油</a>
 * @since 4.0
 */
public abstract class AbstractStorage implements Serializable {

    /**
     * <code>ID</code>.
     */
    private String id;
    /**
     * <code>版本号</code>.
     */
    private int lastVer;

    /**
     * <code>是否有效</code>.
     */
    private Short isValid;

    private Long createTime;

    private Long opTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getLastVer() {
        return lastVer;
    }

    public void setLastVer(int lastVer) {
        this.lastVer = lastVer;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getOpTime() {
        return opTime;
    }

    public void setOpTime(Long opTime) {
        this.opTime = opTime;
    }

    /**
     * 得到是否有效.
     *
     * @return 是否有效.
     */
    public Short getIsValid() {
        return isValid;
    }

    /**
     * 设置是否有效.
     *
     * @param isValid 是否有效.
     */
    public void setIsValid(Short isValid) {
        this.isValid = isValid;
    }

    /**
     * 增加版本号.
     */
    public void increaseVersion() {
        lastVer++;
    }

  
}
