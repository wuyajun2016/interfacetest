package com.dfire.testcase.qjc.membersave;

import java.util.List;

/**
 * qjc保存会员信息
 * @author Administrator
 *
 */
public class qjcCardListBean {
	
	private  int  source;  //来源  必填
    private  String  kindCardName;  //会员卡类型名称  必填
    private  String  code;  //卡号  没有会创建,有的话会检验是否重复
    private  String  innerCode;  //内部ID号  没有会创建,有的话会检验是否重复
    private  String  pwd;  //密码  可不填
    private  String  customerName;  //会员名  可不填
    private  String  mobile;  //手机号码  可不填
    private  String  countryCode;  //手机区号  可不填
    private  int  sex;  //性别0-未知，1-男，2-女  可不填
    private  String  birth;  //生日  可不填 年/月/日
    private  long  balance;  //余额(单位:分)  可不填 但不能为负数
    private  long  baseBalance;  //本金余额(单位:分)  可不填 但不能为负数
    private  long  giveBalance;  //赠送部分余额(单位:分)  可不填 但不能为负数
    private  long  giftBalance;  //累计赠送金额(单位:分)  可不填 但不能为负数
    private  long  realBalance;  //累计充值实际金额(单位:分)  可不填 但不能为负数
    private  int  degree;  //积分  可不填 但不能为负数
    private  long  payAmount;  //支付累计(单位:分)  可不填 但不能为负数
    private  long  ratioAmount;  //折扣累计(单位:分)  可不填 但不能为负数
    private  int  status;  //0:未使用1:正常2:挂失3:注销4:换卡  不填 默认 1
    private  long  pay;  //应收金额(单位:分)  可不填
    private  long  consumeAmount;  //消费累计(单位:分)  可不填
    private  long  lastConsumeTime;  //卡最后消费时间  可不填
    private  int  consumeNum;  //卡消费次数  可不填
    private  int  kindCardType;  //会员卡类型(1:普通卡,2:特权卡)  可不填
    private  long  pledge;  //押金(单位:分)  可不填
    private  long  refundAmount;  //应退金额(单位:分)  可不填
    private  int  mode;  //优惠方式:1/使用会员价;2/使用打折方案;3/设置折扣率  可不填
    private  int  ratio;  //折扣率(按100为满单位)  可不填
    private  String  certificate;  //身份证号码  可不填
    private  String  company;  //公司  可不填
    private  String  memo;  //备注  可不填
    private  String  email;  //邮箱  可不填
    private  String  zipcode;  //邮编  可不填
    private  String  address;  //地址  可不填
    private  String  pos;  //职务  可不填
    private  String  job;  //职业  可不填
    private  String  weixin;  //微信号  可不填
    private  String  carNo;  //车牌  可不填
    private    List<String> applyShopEntityIdList;  //适用门店集合
	public int getSource() {
		return source;
	}
	public void setSource(int source) {
		this.source = source;
	}
	public String getKindCardName() {
		return kindCardName;
	}
	public void setKindCardName(String kindCardName) {
		this.kindCardName = kindCardName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getInnerCode() {
		return innerCode;
	}
	public void setInnerCode(String innerCode) {
		this.innerCode = innerCode;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public long getBalance() {
		return balance;
	}
	public void setBalance(long balance) {
		this.balance = balance;
	}
	public long getBaseBalance() {
		return baseBalance;
	}
	public void setBaseBalance(long baseBalance) {
		this.baseBalance = baseBalance;
	}
	public long getGiveBalance() {
		return giveBalance;
	}
	public void setGiveBalance(long giveBalance) {
		this.giveBalance = giveBalance;
	}
	public long getGiftBalance() {
		return giftBalance;
	}
	public void setGiftBalance(long giftBalance) {
		this.giftBalance = giftBalance;
	}
	public long getRealBalance() {
		return realBalance;
	}
	public void setRealBalance(long realBalance) {
		this.realBalance = realBalance;
	}
	public int getDegree() {
		return degree;
	}
	public void setDegree(int degree) {
		this.degree = degree;
	}
	public long getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(long payAmount) {
		this.payAmount = payAmount;
	}
	public long getRatioAmount() {
		return ratioAmount;
	}
	public void setRatioAmount(long ratioAmount) {
		this.ratioAmount = ratioAmount;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public long getPay() {
		return pay;
	}
	public void setPay(long pay) {
		this.pay = pay;
	}
	public long getConsumeAmount() {
		return consumeAmount;
	}
	public void setConsumeAmount(long consumeAmount) {
		this.consumeAmount = consumeAmount;
	}
	public long getLastConsumeTime() {
		return lastConsumeTime;
	}
	public void setLastConsumeTime(long lastConsumeTime) {
		this.lastConsumeTime = lastConsumeTime;
	}
	public int getConsumeNum() {
		return consumeNum;
	}
	public void setConsumeNum(int consumeNum) {
		this.consumeNum = consumeNum;
	}
	public int getKindCardType() {
		return kindCardType;
	}
	public void setKindCardType(int kindCardType) {
		this.kindCardType = kindCardType;
	}
	public long getPledge() {
		return pledge;
	}
	public void setPledge(long pledge) {
		this.pledge = pledge;
	}
	public long getRefundAmount() {
		return refundAmount;
	}
	public void setRefundAmount(long refundAmount) {
		this.refundAmount = refundAmount;
	}
	public int getMode() {
		return mode;
	}
	public void setMode(int mode) {
		this.mode = mode;
	}
	public int getRatio() {
		return ratio;
	}
	public void setRatio(int ratio) {
		this.ratio = ratio;
	}
	public String getCertificate() {
		return certificate;
	}
	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPos() {
		return pos;
	}
	public void setPos(String pos) {
		this.pos = pos;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getWeixin() {
		return weixin;
	}
	public void setWeixin(String weixin) {
		this.weixin = weixin;
	}
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public List<String> getApplyShopEntityIdList() {
		return applyShopEntityIdList;
	}
	public void setApplyShopEntityIdList(List<String> applyShopEntityIdList) {
		this.applyShopEntityIdList = applyShopEntityIdList;
	}
    
    
    
	
	
	
	
}
