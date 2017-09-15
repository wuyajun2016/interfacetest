package com.dfire.testcase.function.bean.cash;

import java.io.Serializable;
import java.util.List;

/**
 * @Description: .
 * @author <a href="johnnie_deng@126.com">邓国华</a>.
 */
public class TotalPayInfoVo implements Serializable{

	private static final long serialVersionUID = -3148207888913482798L;

	/**
	 * <code>totalPay对应的字段</code>.
	 */
	private TotalPay totalPay;

	/**
	 * <code>serviceBillVO对应的字段</code>.
	 */
	private ServiceBillVO serviceBillVO;

	/**
	 * <code>dicItems对应的字段</code>.
	 */
//	private List<DicItem> dicItems;

	/**
	 * <code>pays对应的字段</code>.
	 */
	private List<Pay> pays;

	/**
	 * <code>orderInfoVos对应的字段</code>.
	 */
	private List<OrderInfoVo> orderInfoVos;

	/**
	 * <code>shopAccounts对应的字段</code>.
	 */
//	private List<ShopAccount> shopAccounts;

	/**
	 * <code>seatName对应的字段</code>.
	 */
	private String seatName;
	
	/**
	 * <code>shopName对应的字段</code>.
	 */
	private String shopName;
	
	/**
	 * <code>付款额外信息</code>.
	 */
//	private List<PayDetail> payDetails;
	
	/**
	 * <code>额外款项</code>.
	 */
//	private List<SpecialFee> specialFees;

	public TotalPayInfoVo() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * 设置.
	 * 
	 * @return totalPay
	 */
	public TotalPay getTotalPay() {
		return totalPay;
	}

	/**
	 * 得到. totalPay 要设置的 totalPay
	 */
	public void setTotalPay(TotalPay totalPay) {
		this.totalPay = totalPay;
	}

	/**
	 * 设置.
	 * 
	 * @return serviceBillVO
	 */
	public ServiceBillVO getServiceBillVO() {
		return serviceBillVO;
	}

	/**
	 * 得到. serviceBillVO 要设置的 serviceBillVO
	 */
	public void setServiceBillVO(ServiceBillVO serviceBillVO) {
		this.serviceBillVO = serviceBillVO;
	}

//	/**
//	 * 设置.
//	 * 
//	 * @return dicItems
//	 */
//	public List<DicItem> getDicItems() {
//		return dicItems;
//	}
//
//	/**
//	 * 得到. dicItems 要设置的 dicItems
//	 */
//	public void setDicItems(List<DicItem> dicItems) {
//		this.dicItems = dicItems;
//	}

	/**
	 * 设置.
	 * 
	 * @return pays
	 */
	public List<Pay> getPays() {
		return pays;
	}

	/**
	 * 得到. pays 要设置的 pays
	 */
	public void setPays(List<Pay> pays) {
		this.pays = pays;
	}

	/**
	 * 设置.
	 * 
	 * @return orderInfoVos
	 */
	public List<OrderInfoVo> getOrderInfoVos() {
		return orderInfoVos;
	}

	/**
	 * 得到. orderInfoVos 要设置的 orderInfoVos
	 */
	public void setOrderInfoVos(List<OrderInfoVo> orderInfoVos) {
		this.orderInfoVos = orderInfoVos;
	}

//	/**
//	 * 设置.
//	 * 
//	 * @return shopAccounts
//	 */
//	public List<ShopAccount> getShopAccounts() {
//		return shopAccounts;
//	}
//
//	/**
//	 * 得到. shopAccounts 要设置的 shopAccounts
//	 */
//	public void setShopAccounts(List<ShopAccount> shopAccounts) {
//		this.shopAccounts = shopAccounts;
//	}

	/**
	 * 设置.
	 * 
	 * @return seatName
	 */
	public String getSeatName() {
		return seatName;
	}

	/**
	 * 得到. seatName 要设置的 seatName
	 */
	public void setSeatName(String seatName) {
		this.seatName = seatName;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
//	public List<PayDetail> getPayDetails() {
//		return payDetails;
//	}
//	public void setPayDetails(List<PayDetail> payDetails) {
//		this.payDetails = payDetails;
//	}
//	public List<SpecialFee> getSpecialFees() {
//		return specialFees;
//	}
//	public void setSpecialFees(List<SpecialFee> specialFees) {
//		this.specialFees = specialFees;
//	}
}
