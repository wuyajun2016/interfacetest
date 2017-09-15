/*************************
 * 版权声明 **********************************
 * 版权所有：Copyright (c) 2011, ${year} 黄晓峰
 * <p>
 * 工程名称：	钻木餐饮系统
 * 创建者： 黄晓峰 创建日期： ${date}
 * 创建记录：	创建类结构。
 **/

package com.dfire.qa.meal.vo.cash;


import java.util.List;

import com.dfire.qa.meal.vo.cash.base.Base;
import com.dfire.qa.meal.vo.cash.base.BaseInstance;
import com.dfire.test.util.StringUtil;



/**
 * 点菜明细.
 *
 * @author <a href="mailto:xiaofenguser@163.com">黄晓峰</a>
 * @version $Revision$
 */
public class Instance extends BaseInstance{
    /**
     * <code>序列ID</code>.
     */
    private static final long serialVersionUID = 1L;

    /**
     * <code>未确认菜</code>.
     */
    public static final Short STATUS_WAIT_SEND = (short) 1;
    /**
     * <code>正常</code>.
     */
    public static final Short STATUS_NORMAL = (short) 2;
    /**
     * <code>退菜标志</code>.
     */
    public static final Short STATUS_CANCEL = (short) 3;
    /**
     * <code>处理中（点菜设备使用）</code>.
     */
    public static final Short STATUS_IN_PROCESS = (short) 4;

    /**
     * <code>未上菜(火点菜宝)</code>.
     */
    public static final Short DRAW_STATUS_UNDRAWED = (short) 1;

    /**
     * <code>已上菜(火点菜宝)</code>.
     */
    public static final Short DRAW_STATUS_DRAWED = (short) 2;

    /**
     * <code>未成功(火点菜宝)</code>.
     */
    public static final Short STATUS_PROCESS_FAIL = (short) 7;

    /**
     * <code>分单状态(火点菜宝)</code>.
     */
//	public static final Short STATUS_SPLIT=(short)8;

	/** <code>普通菜</code>. */
	public static final Short KIND_NORMAL = (short) 1;

	/** <code>套菜</code>. */
	public static final Short KIND_SUIT = (short) 2;

	/** <code>自定义菜</code>. */
	public static final Short KIND_SELF = (short) 3;

	/** <code>自定义套菜</code>. */
	public static final Short KIND_SELF_SUIT = (short) 4;

	/** <code>加料菜</code>. */
	public static final Short KIND_ADDITION = (short) 5;

	/** <code>套菜计价模式:(1)固定价</code>. */
	public static final Short FIXED_PRICE_MODE = (short) 1;

	/** <code>套菜计价模式:(1)浮动价</code>. */
	public static final Short FLOAT_PRICE_MODE = (short) 2;

	public static final double ZERO = 0.005d;

	/**
	 * <code>服务费收取方式－不收取</code>.
	 */
	public static final Short SERVICEFEEMODE_NO = 0;

	/**
	 * <code>服务费收取方式-固定费用</code>.
	 */
	public static final Short SERVICEFEEMODE_FIX = 1;

	/**
	 * <code>服务费收取方式-菜价百分比</code>.
	 */
	public static final Short SERVICEFEEMODE_RATIO = 2;

	/** <code>餐盒</code>. */
	public static final Short TYPE_PACKING_BOX = (short) 1;

	/**
	 * 未确认菜数量. 用于套菜.
	 */
	private Integer notConfirmCount = 0;

	/**
	 * <code>加料</code>.
	 */
	private String addMaterials;

	/**
	 * 初始会员价,用于套餐会员价计算
	 */
	private double originalMemberPrice = -1d;

	/**
	 * <code>菜类名称</code>.
	 */
	private String kindMenuName;

	/**
	 * 原订单ID. 有菜肴转到其它单时，才需要填写.
	 */
	private String oldOrderId;

	private List<String> producePlanIds;

	/**
	 * <code>用于套餐子菜排序</code>
	 */
	private Integer sortCode;

	public Instance copyNew() {
		Instance instance = new Instance();
		instance.setId(getId());
		instance.setIsValid(getIsValid());
		instance.setLastVer(getLastVer());
		instance.setCreateTime(getCreateTime());
		instance.setOpTime(getOpTime());
		instance.setOrderId(getOrderId());
		instance.setKind(getKind());
		instance.setParentId(getParentId());
		instance.setPriceMode(getPriceMode());
		instance.setName(getName());
		instance.setMenuId(getMenuId());
		instance.setBookMenuId(getBookMenuId());
		instance.setMakeId(getMakeId());
		instance.setMakeName(getMakeName());
		instance.setMakePrice(getMakePrice());
		instance.setNum(getNum());
		instance.setAccountNum(getAccountNum());
		instance.setPrice(getPrice());
		instance.setIsRatio(getIsRatio());
		instance.setRatio(getRatio());
		instance.setFee(getFee());
		instance.setRatioFee(getRatioFee());
		instance.setProdPlanId(getProdPlanId());
		instance.setStatus(getStatus());
		instance.setIsWait(getIsWait());
		instance.setMemo(getMemo());
		instance.setSpecDetailId(getSpecDetailId());
		instance.setSpecDetailName(getSpecDetailName());
		instance.setSpecDetailPrice(getSpecDetailPrice());
		instance.setUnit(getUnit());
		instance.setAccountUnit(getAccountUnit());
		instance.setMakePriceMode(getMakePriceMode());
		instance.setKindMenuId(getKindMenuId());
		instance.setOriginalPrice(getOriginalPrice());
		instance.setTaste(getTaste());
		instance.setIsBuyNumberChanged(getIsBuyNumberChanged());
		instance.setRatioOperatorId(getRatioOperatorId());
		instance.setRatioCause(getRatioCause());
		instance.setChildId(getChildId());
		instance.setMemberPrice(getMemberPrice());
		instance.setKindBookMenuId(getKindBookMenuId());
		instance.setSpecPriceMode(getSpecPriceMode());
		instance.setWorkerId(getWorkerId());
		instance.setIsBackAuth(getIsBackAuth());
		instance.setOpUserId(getOpUserId());
		instance.setServiceFeeMode(getServiceFeeMode());
		instance.setServiceFee(getServiceFee());
		instance.setBatchMsg(getBatchMsg());
		instance.setWaitingInstanceId(getWaitingInstanceId());
		instance.setAdditionPrice(getAdditionPrice());
		instance.setDrawStatus(getDrawStatus());
		instance.setAddMaterials(getAddMaterials());
		instance.setHitPrice(getHitPrice());
		return instance;
	}

	/**
	 * 默认初始化方法.
	 */
	public void initDefault() {
		this.setMakePrice(0d);// 默认烧法加价为0
		this.setMakePriceMode(MenuMake.MAKEPRICE_TOTAL);// 默认烧法加价模式为一次性加价
		this.setIsRatio(Base.FALSE);
		this.setIsWait(Base.FALSE);
		this.setIsValid(Base.TRUE);
		this.setRatio((double) 100d);
		this.setSpecPriceMode(SpecDetail.PRICE_MODE_ADD);
		this.setSpecDetailPrice(0D);
		this.setRatioFee(0d);
		this.setIsBuyNumberChanged(Base.TRUE);
		this.setPriceMode(Short.valueOf((short) 1));
		this.setType((short) 0);
		setIsBackAuth(Base.TRUE);
	}

	/**
	 * 原订单ID.
	 * 
	 * @return 原订单ID.
	 */
	public String getOldOrderId() {
		return oldOrderId;
	}

	/**
	 * 设置原订单ID.
	 * 
	 * @param oldOrderId
	 *            原订单ID.
	 */
	public void setOldOrderId(String oldOrderId) {
		this.oldOrderId = oldOrderId;
	}

    /**
     * 更新费用.
     */
    public void updateFee() {
        Double fee = updateBaseFee(this.getPrice() + this.getHitPrice());
        if (fee == null || fee == 0) {
            this.setFee(NumberUtils.round(0.0));
            this.setRatioFee(0D);
        } else {
            this.setFee(NumberUtils.round(fee));
            updateRatioFee(this.getFee());
        }
    }

    /**
     * 更新费用(套餐).
     */
    public void updateSuitFee() {
        Double fee = updateBaseSuitFee(this.getPrice() + this.getHitPrice());
        if (fee == null || fee == 0) {
            this.setFee(NumberUtils.round(0.0));
            this.setRatioFee(0D);
            this.setMemberPrice(0D);
        } else {
            this.setFee(NumberUtils.round(fee));
            updateRatioFee(this.getFee());
//			updateMemberFee(this.getFee());
        }
    }

    /**
     * 更新费用.退部分菜时使用，
     */
    public void updateFeeOnCancel() {
        Double fee = updateBaseFeeOnCancel(this.getPrice());
        if (fee == null || fee == 0) {
            this.setFee(NumberUtils.round(0.0));
            this.setRatioFee(0D);
        } else {
            this.setFee(NumberUtils.round(fee));
            updateRatioFee(this.getFee());
        }
    }

    /**
     * 更新费用.
     */
    public Double updateMemberFee() {
        Double price = this.getMemberPrice();
//		if (price == null || price == 0) {
        if (price == null) {//解决会员价设置为0不起作用的bug
            price = this.getPrice();
        }
        return updateBaseFee(price);
    }

    /**
     * 更新费用(套餐使用).
     */
    public Double updateSuitMemberFee() {
        Double price = this.getMemberPrice();
//		if (price == null || price == 0) {
        if (price == null) {//解决会员价设置为0不起作用的bug
            price = this.getPrice();
        }
        return updateBaseSuitFee(price);
    }

    public void setOriginalMemberPrice(double price) {
        if (price >= 0)
            originalMemberPrice = price;
    }

    /**
     * 更新会员费用.套餐会员价应该是当前fee - Origprice + 会员price
     */
//	public void updateMemberFee(Double fee) {
//		if(originalMemberPrice < 0)
//			originalMemberPrice = getMemberPrice();
//		Double price = this.getOriginalPrice();
//		setMemberPrice(fee - price + originalMemberPrice);//这种写法错误，套餐的会员价通过menu获取，会员价本身固定，无需更新，fee中包含多份，各种规格做法加料等加价，也是不对的
//	}

	/**
	 * 判断是否是赠菜(价格为零的主菜).
	 * 
	 * @return
	 */
	public boolean isGive() {
		if (!isChild() && getOriginalPrice().doubleValue() > ZERO && getPrice().doubleValue() < ZERO) {
			return true;
		} else {
			return false;
		}
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
	 * 判断是否是套菜子菜.
	 * 
	 * @return
	 */
	public boolean isSuitChild() {
		return !getKind().equals(Instance.KIND_ADDITION) && StringUtil.isNotEmpty(getParentId()) && !"0".equals(getParentId());
	}

	/**
	 * 是否套菜.
	 * 
	 * @return 是否套菜.
	 */
	public boolean isSuit() {
		return KIND_SUIT.equals(getKind()) || KIND_SELF_SUIT.equals(getKind());
	}

	/**
	 * 是否自定义菜.
	 * 
	 * @return 自定义菜.
	 */
	public boolean isSelf() {
		return KIND_SELF.equals(getKind());
	}

	/**
	 * 更新打折金额.
	 */
	public void updateRatioFee(Double fee) {
		if (fee != null && this.getRatio() != null) {
			this.setRatioFee(NumberUtils.round((fee * this.getRatio()) / 100));
		} else if (this.getRatio() == null) {
			this.setRatioFee(fee);
		}
	}

	/**
	 * 不分类型，直接加价.
	 * 
	 * @param price
	 *            加价.
	 */
	public void addPriceWithoutMode(double price) {
		double oldPrice = this.getPrice();
		oldPrice += price;
		this.setPrice(oldPrice);
//		this.setOriginalPrice(oldPrice);
		updateFee();
	}

	/**
	 * 添加加料价格
	 * 
	 * @param price
	 */
	public void addPriceWithoutAddition(Double price) {
		double oldAdditionPrice = 0;
		if (this.getAdditionPrice() != null) {
			oldAdditionPrice = this.getAdditionPrice();
		}
		oldAdditionPrice += price;
		this.setAdditionPrice(oldAdditionPrice);
		if(isSuitChild()){
			updateSuitFee();
		}else{
			updateFee();
		}
	}

	/**
	 * 不分类型，直接加会员价
	 * 
	 * @param memberPrice
	 *            套菜加价.
	 */
	public void addMemberPriceWithoutMode(double memberPrice) {
		double oldMemberPrice = this.getMemberPrice();
		oldMemberPrice += memberPrice;
		this.setMemberPrice(oldMemberPrice);
	}

	/**
	 * 加价.
	 * 
	 * @param price
	 *            加价.
	 */
	public void addPrice(double price) {
		double oldPrice = this.getPrice();

		if ("2".equals(getPriceMode())) {
			oldPrice += price;
			this.setPrice(oldPrice);
//			this.setOriginalPrice(oldPrice);
			updateFee();
		}
	}

	/**
	 * 套菜加会员价
	 * 
	 * @param memberPrice
	 *            套菜加价.
	 */
	public void addMemberPrice(double memberPrice) {
		double oldMemberPrice = this.getMemberPrice();
		if ("2".equals(getPriceMode())) {
			oldMemberPrice += memberPrice;
			this.setMemberPrice(oldMemberPrice);
		}
	}

	/**
	 * 判断是否退菜.
	 *
	 * @return
	 */
	public boolean isBack() {
		return getStatus() != null && Instance.STATUS_CANCEL.equals(getStatus());
	}

	/**
	 * 更新价格.
	 */
	public void updatePrice() {
		if (getPrice() != null && getSpecDetailPrice() != null
				&& getSpecPriceMode() != null) {
			if(this.isSuit()){
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
				// 规格设置为0 ,或者规格计算后变为小于0.01的菜不应该计为赠菜,最小计为1分钱
//				if(getOriginalPrice().doubleValue() > ZERO)
//					price = Math.max(0.01d, price);
//				price = Math.max(0.01d, price);
            }
            setPrice(NumberUtils.round(price));
//			setPrice(price);
		} else {
//			throw new DomainBizException("尚未初始化价格,或规格加价!");
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
		if (price == null) {
			setPrice(0d);
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
	 * 根据基本价格更新(套餐使用)
	 *
	 * @param price
	 */
	private Double updateBaseSuitFee(Double price) {
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
					fee = getNum() * price;
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
	 * 根据基本价格更新，退部分菜时使用，当做法加价模式：一次性加价时，退菜时不退做法的价格
	 * 
	 * @param price
	 */
	private Double updateBaseFeeOnCancel(Double price) {
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
				}
				// else if (MenuMake.MAKEPRICE_TOTAL.equals(getMakePriceMode()))
				// {
				// fee += getMakePrice();
				// }
				else if (MenuMake.MAKEPRICE_PERUNIT.equals(getMakePriceMode())) {
					fee += getAccountNum() * getMakePrice();
				}
			}
			if (this.getAdditionPrice() != null) {
				fee += getNum() * getAdditionPrice();
			}
		}
		return fee;
	}

	/**
	 * 得到未确认子菜数量.
	 * 
	 * @return 未确认子菜数量.
	 */
	public Integer getNotConfirmCount() {
		return notConfirmCount;
	}

	/**
	 * 设置未确认子菜数量.
	 * 
	 * @param notConfirmCount
	 *            未确认子菜数量.
	 */
	public void setNotConfirmCount(Integer notConfirmCount) {
		this.notConfirmCount = notConfirmCount;
	}


	/**
	 * 是否双单位.
	 * 
	 * @return true,双单位.
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

	/**
	 * 得到加料.
	 * 
	 * @return 加料.
	 */
	public String getAddMaterials() {
		return addMaterials;
	}

	/**
	 * 设置加料.
	 * 
	 * @param addMaterials
	 *            加料.
	 */
	public void setAddMaterials(String addMaterials) {
		this.addMaterials = addMaterials;
	}

	/**
	 * 得到菜类名称.
	 * 
	 * @return 菜类名称.
	 */
	public String getKindMenuName() {
		return kindMenuName;
	}

	/**
	 * 设置菜类名称.
	 * 
	 * @param kindMenuName
	 *            菜类名称.
	 */
	public void setKindMenuName(String kindMenuName) {
		this.kindMenuName = kindMenuName;
	}

	/**
	 * 判断是否为双单位或者有规则、做法、加料和备注
	 * 
	 * @return
	 */
	public boolean isExtraInfo() {

		if (isTwoAccount()) {
			return true;
		}
		if (StringUtil.isNotEmpty(getSpecDetailId())) {
			return true;
		}
		if (StringUtil.isNotEmpty(getMakeId())) {
			return true;
		}
		if (Base.TRUE.equals(getHasAddition())) {
			return true;
		}
		if (StringUtil.isNotEmpty(getMemo())) {
			return true;
		}
		return false;
	}
	/**
	 * 获取配菜联打印几个，商品有规格时，单价显示为每种规格的单价 商品有加料时，单价上体现加料价格
	 * 商品做法根据结账单位加价时，单价上体现做法加价，其它加价类型则不用体现
	 * @return
	 */
	public Double getPrintPrice() {
		double fee = 0;
		if (getOriginalPrice() == null) {
			setOriginalPrice(0d);
		}
		// 非赠菜
        if (!(getOriginalPrice().doubleValue() > ZERO && getPrice().doubleValue() < ZERO)) {
			if (Math.abs(getPrice().doubleValue()) > ZERO) {
				if (this.getNum() != null) {
					if (this.getAccountNum() == null) {
						this.setAccountNum(this.getNum());
					}
					fee = getPrice();
				}
			}
			if (this.getMakePrice() != null && this.getMakeId() != null) {
				 if (MenuMake.MAKEPRICE_PERUNIT.equals(getMakePriceMode())) {
	//				fee += getAccountNum() * getMakePrice();
					 fee = FormatUtil.add(fee, getAccountNum() * getMakePrice());
				}
			}
			if (this.getAdditionPrice() != null) {
	//			fee += getNum() * getAdditionPrice();
				fee = FormatUtil.add(fee, getNum() * getAdditionPrice());
			}
		}
		return fee;
	}


	public List<String> getProducePlanIds() {
		return producePlanIds;
	}

	public void setProducePlanIds(List<String> producePlanIds) {
		this.producePlanIds = producePlanIds;
	}

	public Integer getSortCode() {
		return sortCode;
	}

	public void setSortCode(Integer sortCode) {
		this.sortCode = sortCode;
	}
	/**
	 * 判断是否是餐盒.
	 *
	 * @return
	 */
	public boolean isPackingBox() {
		return TYPE_PACKING_BOX.equals(getType());
	}
}
