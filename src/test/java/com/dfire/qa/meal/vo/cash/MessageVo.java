package com.dfire.qa.meal.vo.cash;

import java.io.Serializable;

public class MessageVo implements Serializable {
    /**
     * <code>消息id</code>.
     */
    public String id;

    /**
     * <code>消息内容</code>.
     */
    public String cot;
    /**
     * <code>消息类型type</code>.
     */
    public int ty;
    /**
     * <code>消息状态</code>.
     */
    public String su;
    /**
     * <code>title 标题</code>.
     */
    public String tl;
    /**
     * <code>座位编码seatCode</code>.
     */
    public String sc;
    /**
     * <code>过期时间expired_time</code>.
     */
    public int et;
    /**
     * <code>业务Id</code>.
     */
    public String b_id;
    /**
     * <code>用户Id</code>.
     */
    public String s_id;
    /**
     * <code>处理结果result_message</code>.
     */
    public String rm;
    /**
     * <code>处理者modify_user</code>.
     */
    public String mu;
    /**
     * <code>创建时间create_time</code>.
     */
    public long ct;
    /**
     * <code>修改时间modify_time</code>.
     */
    public long mt;

    /**
     * <code>订阅记录id</code>.
     */
    public String sb_id;

    /**
     * 得到消息id
     *
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * 设置消息id
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return cot;
    }

    public void setContent(String content) {
        this.cot = content;
    }

    public int getType() {
        return ty;
    }

    public void setType(int type) {
        this.ty = type;
    }

    public String getStatus() {
        return su;
    }

    public void setStatus(String status) {
        this.su = status;
    }

    public String getTl() {
        return tl;
    }

    public void setTl(String tl) {
        this.tl = tl;
    }

    public String getSeat_code() {
        return sc;
    }

    public void setSeat_code(String seat_code) {
        this.sc = seat_code;
    }

    public int getExpired_time() {
        return et;
    }

    public void setExpired_time(int expired_time) {
        this.et = expired_time;
    }

    public String getBusiness_id() {
        return b_id;
    }

    public void setBusiness_id(String business_id) {
        this.b_id = business_id;
    }

    public String getS_id() {
        return s_id;
    }

    public void setS_id(String s_id) {
        this.s_id = s_id;
    }

    public String getResult_message() {
        return rm;
    }

    public void setResult_message(String result_message) {
        this.rm = result_message;
    }

    public String getModify_user() {
        return mu;
    }

    public void setModify_user(String modify_user) {
        this.mu = modify_user;
    }

    public long getCreate_time() {
        return ct;
    }

    public void setCreate_time(long create_time) {
        this.ct = create_time;
    }

    public long getModify_time() {
        return mt;
    }

    public void setModify_time(long modify_time) {
        this.mt = modify_time;
    }

    public String getSb_id() {
        return sb_id;
    }

    public void setSb_id(String sb_id) {
        this.sb_id = sb_id;
    }

  
}
