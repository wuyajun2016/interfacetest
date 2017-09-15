package com.dfire.testcase.function.bean.cash;

import java.io.Serializable;

public class CloudMessage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * <code>未处理</code>
	 */
	public static final int MESSAGE_NEW_STATUS = 0;//"未处理状态"
	/**
	 * <code>处理中</code>
	 */
	public static final int MESSAGE_PROCESS_STATUS = 1;//"处理中"
	/**
	 * <code>处理成功</code>
	 */
	public static final int MESSAGE_SUCCESS_STATUS = 2;//"处理成功"
	/**
	 * <code>超时</code>
	 */
	public static final int MESSAGE_TIME_OUT_STATUS = 3;//"超时"
	/**
	 * <code>处理失败</code>
	 */
	public static final int MESSAGE_FAIL_STATUS= 4 ;//"处理失败"
	/**
	 * <code>拒绝</code>
	 */
	public static final int MESSAGE_REFUSE_STATUS= 5;//"拒绝"
	/**
	 * <code>撤销消息</code>
	 */
	public static final int MESSAGE_CANCEL_STATUS = -4;// "撤销消息"
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
	 * <code>消息时间</code>.
	 */
	private long t;
	
	/**
	 * 得到消息id
	 * @return
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置消息id
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}
	public String getCot() {
		return cot;
	}
	public void setCot(String cot) {
		this.cot = cot;
	}
	public int getTy() {
		return ty;
	}
	public void setTy(int ty) {
		this.ty = ty;
	}
	public String getSu() {
		return su;
	}
	public void setSu(String su) {
		this.su = su;
	}
	public String getTl() {
		return tl;
	}
	public void setTl(String tl) {
		this.tl = tl;
	}
	public String getSc() {
		return sc;
	}
	public void setSc(String sc) {
		this.sc = sc;
	}
	public int getEt() {
		return et;
	}
	public void setEt(int et) {
		this.et = et;
	}
	public String getB_id() {
		return b_id;
	}
	public void setB_id(String b_id) {
		this.b_id = b_id;
	}
	public String getS_id() {
		return s_id;
	}
	public void setS_id(String s_id) {
		this.s_id = s_id;
	}
	public String getRm() {
		return rm;
	}
	public void setRm(String rm) {
		this.rm = rm;
	}
	public String getMu() {
		return mu;
	}
	public void setMu(String mu) {
		this.mu = mu;
	}
	public long getCt() {
		return ct;
	}
	public void setCt(long ct) {
		this.ct = ct;
	}
	public long getT() {
		return t;
	}
	public void setT(long t) {
		this.t = t;
	}

	@Override
	public String toString() {
		return "CloudMessage{" +
				"id='" + id + '\'' +
				", cot='" + cot + '\'' +
				", ty=" + ty +
				", su='" + su + '\'' +
				", tl='" + tl + '\'' +
				", sc='" + sc + '\'' +
				", et=" + et +
				", b_id='" + b_id + '\'' +
				", s_id='" + s_id + '\'' +
				", rm='" + rm + '\'' +
				", mu='" + mu + '\'' +
				", ct=" + ct +
				", t=" + t +
				'}';
	}
}
