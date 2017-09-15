/*************************
 * 版权声明 **********************************
 * 版权所有：Copyright (c) 2011, ${year} 黄晓峰
 * <p>
 * 工程名称：	钻木餐饮系统
 * 创建者： 黄晓峰 创建日期： ${date}
 * 创建记录：	创建类结构。
 **/

package com.dfire.testcase.function.bean.cash.base;


/**
 * 基础点菜明细.
 *
 * @author <a href="mailto:xiaofenguser@163.com">黄晓峰</a>
 * @version $Revision$
 */
public abstract class BaseInstance extends Base{
	/**
	 * <code>序列ID</code>.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * <code>表名</code>.
	 */
	public static final String TABLE_NAME = "INSTANCE";
	/**
	 * <code>订单ID对应的字段</code>.
	 */
	public static final String ORDERID = "ORDERID";
	/**
	 * <code>点菜类型对应的字段</code>.
	 */
	public static final String KIND = "KIND";
	/**
	 * <code>套菜父菜ID对应的字段</code>.
	 */
	public static final String PARENTID = "PARENTID";
	/**
	 * <code>套菜计价模式对应的字段</code>.
	 */
	public static final String PRICEMODE = "PRICEMODE";
	/**
	 * <code>菜名对应的字段</code>.
	 */
	public static final String NAME = "NAME";
	/**
	 * <code>菜品ID对应的字段</code>.
	 */
	public static final String MENUID = "MENUID";
	/**
	 * <code>菜谱菜肴ID对应的字段</code>.
	 */
	public static final String BOOKMENUID = "BOOKMENUID";
	/**
	 * <code>做法ID对应的字段</code>.
	 */
	public static final String MAKEID = "MAKEID";
	/**
	 * <code>做法对应的字段</code>.
	 */
	public static final String MAKENAME = "MAKENAME";
	/**
	 * <code>做法调价对应的字段</code>.
	 */
	public static final String MAKEPRICE = "MAKEPRICE";
	/**
	 * <code>点菜数量对应的字段</code>.
	 */
	public static final String NUM = "NUM";
	/**
	 * <code>结账数量对应的字段</code>.
	 */
	public static final String ACCOUNTNUM = "ACCOUNTNUM";
	/**
	 * <code>单价对应的字段</code>.
	 */
	public static final String PRICE = "PRICE";
	/**
	 * <code>可否打折对应的字段</code>.
	 */
	public static final String ISRATIO = "ISRATIO";
	/**
	 * <code>折扣率对应的字段</code>.
	 */
	public static final String RATIO = "RATIO";
	/**
	 * <code>金额对应的字段</code>.
	 */
	public static final String FEE = "FEE";
	/**
	 * <code>折后金额对应的字段</code>.
	 */
	public static final String RATIOFEE = "RATIOFEE";
	/**
	 * <code>出品方案ID对应的字段</code>.
	 */
	public static final String PRODPLANID = "PRODPLANID";
	/**
	 * <code>状态对应的字段</code>.
	 */
	public static final String STATUS = "STATUS";
	/**
	 * <code>是否待菜对应的字段</code>.
	 */
	public static final String ISWAIT = "ISWAIT";
	/**
	 * <code>说明对应的字段</code>.
	 */
	public static final String MEMO = "MEMO";
	/**
	 * <code>规格明细ID对应的字段</code>.
	 */
	public static final String SPECDETAILID = "SPECDETAILID";
	/**
	 * <code>规格名对应的字段</code>.
	 */
	public static final String SPECDETAILNAME = "SPECDETAILNAME";
	/**
	 * <code>规格调价对应的字段</code>.
	 */
	public static final String SPECDETAILPRICE = "SPECDETAILPRICE";
	/**
	 * <code>点菜单位对应的字段</code>.
	 */
	public static final String UNIT = "UNIT";
	/**
	 * <code>结账单位对应的字段</code>.
	 */
	public static final String ACCOUNTUNIT = "ACCOUNTUNIT";
	/**
	 * <code>做法调价模式对应的字段</code>.
	 */
	public static final String MAKEPRICEMODE = "MAKEPRICEMODE";
	/**
	 * <code>菜类ID对应的字段</code>.
	 */
	public static final String KINDMENUID = "KINDMENUID";
	/**
	 * <code>原始单价对应的字段</code>.
	 */
	public static final String ORIGINALPRICE = "ORIGINALPRICE";
	/**
	 * <code>口味说明对应的字段</code>.
	 */
	public static final String TASTE = "TASTE";
	/**
	 * <code>是否修改过点菜数量对应的字段</code>.
	 */
	public static final String ISBUYNUMBERCHANGED = "ISBUYNUMBERCHANGED";
	/**
	 * <code>打折操作人对应的字段</code>.
	 */
	public static final String RATIOOPERATORID = "RATIOOPERATORID";
	/**
	 * <code>打折原因对应的字段</code>.
	 */
	public static final String RATIOCAUSE = "RATIOCAUSE";
	/**
	 * <code>套菜可换菜原始ID对应的字段</code>.
	 */
	public static final String CHILDID = "CHILDID";
	/**
	 * <code>会员价对应的字段</code>.
	 */
	public static final String MEMBERPRICE = "MEMBERPRICE";
	/**
	 * <code>菜谱菜类ID对应的字段</code>.
	 */
	public static final String KINDBOOKMENUID = "KINDBOOKMENUID";
	/**
	 * <code>规格调价模式对应的字段</code>.
	 */
	public static final String SPECPRICEMODE = "SPECPRICEMODE";
	/**
	 * <code>点菜人对应的字段</code>.
	 */
	public static final String WORKERID = "WORKERID";
	/**
	 * <code>退菜是否需要权限对应的字段</code>.
	 */
	public static final String ISBACKAUTH = "ISBACKAUTH";
	/**
	 * <code>操作人对应的字段</code>.
	 */
	public static final String OPUSERID = "OPUSERID";
	/**
	 * <code>服务费收取方式对应的字段</code>.
	 */
	public static final String SERVICEFEEMODE = "SERVICEFEEMODE";
	/**
	 * <code>服务费收取数值对应的字段</code>.
	 */
	public static final String SERVICEFEE = "SERVICEFEE";
	/**
	 * <code>批次信息对应的字段</code>.
	 */
	public static final String BATCHMSG = "BATCHMSG";
	/**
	 * <code>waitingInstanceId对应的字段</code>.
	 */
	public static final String WAITINGINSTANCEID = "WAITINGINSTANCEID";
	/**
	 * <code>分单时候原菜肴id对应的字段</code>.
	 */
	public static final String ORIGNID = "ORIGNID";
	/**
	 * <code>加料菜总价</code>.
	 */
	public static final String ADDITIONPRICE = "ADDITIONPRICE";
	/**
	 * <code>是否有加料</code>.
	 */
	public static final String HASADDITION = "HASADDITION";
	
	/**
	 * <code>桌位Id</code>.
	 */
	public static final String SEATID = "SEATID";
	
	/**
	 * 划菜状态： 1/未划菜   2/已划菜
	 */
	public static final String DRAWSTATUS = "DRAWSTATUS";

	/**
	 *菜肴类型，外卖区分餐盒
	*/
	public static final String TYPE = "TYPE";
	/**
	 *扩展字段
	*/
	public static final String EXT="EXT";
	
	/**
     * <code>撞餐</code>.
     */
    public static final String HITPRICE = "HITPRICE";
	
	/**
	 * <code>订单ID</code>.
	 */
	private String orderId;
	/**
	 * <code>点菜类型</code>.
	 */
	private Short kind;
	/**
	 * <code>套菜父菜ID</code>.
	 */
	private String parentId;
	/**
	 * <code>套菜计价模式</code>.
	 */
	private Short priceMode;
	/**
	 * <code>菜名</code>.
	 */
	private String name;
	/**
	 * <code>菜品ID</code>.
	 */
	private String menuId;
	/**
	 * <code>菜谱菜肴ID</code>.
	 */
	private String bookMenuId;
	/**
	 * <code>做法ID</code>.
	 */
	private String makeId;
	/**
	 * <code>做法</code>.
	 */
	private String makeName;
	/**
	 * <code>做法调价</code>.
	 */
	private Double makePrice;
	/**
	 * <code>点菜数量</code>.
	 */
	private Double num;
	/**
	 * <code>结账数量</code>.
	 */
	private Double accountNum;
	/**
	 * <code>单价</code>.
	 */
	private Double price;
	/**
	 * <code>可否打折</code>.
	 */
	private Short isRatio;
	/**
	 * <code>折扣率</code>.
	 */
	private Double ratio;
	/**
	 * <code>金额</code>.
	 */
	private Double fee;
	/**
	 * <code>折后金额</code>.
	 */
	private Double ratioFee;
	/**
	 * <code>出品方案ID</code>.
	 */
	private String prodPlanId;
	/**
	 * <code>状态</code>.
	 */
	private Short status;
	/**
	 * <code>是否待菜</code>.
	 */
	private Short isWait;
	/**
	 * <code>说明</code>.
	 */
	private String memo;
	/**
	 * <code>规格明细ID</code>.
	 */
	private String specDetailId;
	/**
	 * <code>规格名</code>.
	 */
	private String specDetailName;
	/**
	 * <code>规格调价</code>.
	 */
	private Double specDetailPrice;
	/**
	 * <code>点菜单位</code>.
	 */
	private String unit;
	/**
	 * <code>结账单位</code>.
	 */
	private String accountUnit;
	/**
	 * <code>做法调价模式</code>.
	 */
	private Short makePriceMode;
	/**
	 * <code>菜类ID</code>.
	 */
	private String kindMenuId;
	/**
	 * <code>原始单价</code>.
	 */
	private Double originalPrice;
	/**
	 * <code>口味说明</code>.
	 */
	private String taste;
	/**
	 * <code>是否修改过点菜数量</code>.
	 */
	private Short isBuyNumberChanged;
	/**
	 * <code>打折操作人</code>.
	 */
	private String ratioOperatorId;
	/**
	 * <code>打折原因</code>.
	 */
	private String ratioCause;
	/**
	 * <code>套菜可换菜原始ID</code>.
	 */
	private String childId;
	/**
	 * <code>会员价</code>.
	 */
	private Double memberPrice;
	/**
	 * <code>菜谱菜类ID</code>.
	 */
	private String kindBookMenuId;
	/**
	 * <code>规格调价模式</code>.
	 */
	private Short specPriceMode;
	/**
	 * <code>点菜人</code>.
	 */
	private String workerId;
	/**
	 * <code>退菜是否需要权限</code>.
	 */
	private Short isBackAuth;
	/**
	 * <code>操作人</code>.
	 */
	private String opUserId;
	
	/**
	 * <code>服务费收取方式</code>.
	 */
	private Short serviceFeeMode;
	
	/**
	 * <code>服务费收取值</code>.
	 */
	private Double serviceFee;
	/**
	 * <code>批次信息</code>.
	 */
	private String batchMsg;
	/**
	 * <code>waitingInstanceId</code>.
	 */
	private String waitingInstanceId;
	/**
	 * <code>分单时原菜肴ID对应的字段</code>.
	 */
	private String orignId;
	
	private Double additionPrice;
	
	private Short hasAddition;
	
	private String seatId;

	/**
	 * 用二进制位标识开关状态
	 * 已使用的位有：从右到左
	 * 第0位：服务生划菜状态
	 * 第1位：外卖预定单未下单菜删除状态
	 * 第2位：取餐呼叫状态
	 * 第3位：已取餐状态
	 * 第4位：重复取餐翻转状态
	 */
	private Short drawStatus;

	/**
	 * <code>菜肴类型</code>.
	 */
	private Short type;
	/**
	 * <code>扩展</code>.
	 */
	private String ext;
	
	 /**
     * 撞餐价格
     */
    private double hitPrice;
	
	/**
	 * 得到订单ID.
	 * @return 订单ID.
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * 设置订单ID.
	 * @param orderId 订单ID.
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	/**
	 * 得到点菜类型.
	 * @return 点菜类型.
	 */
	public Short getKind() {
		return kind;
	}

	/**
	 * 设置点菜类型.
	 * @param kind 点菜类型.
	 */
	public void setKind(Short kind) {
		this.kind = kind;
	}
	/**
	 * 得到套菜父菜ID.
	 * @return 套菜父菜ID.
	 */
	public String getParentId() {
		return parentId;
	}

	/**
	 * 设置套菜父菜ID.
	 * @param parentId 套菜父菜ID.
	 */
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	/**
	 * 得到套菜计价模式.
	 * @return 套菜计价模式.
	 */
	public Short getPriceMode() {
		return priceMode;
	}

	/**
	 * 设置套菜计价模式.
	 * @param priceMode 套菜计价模式.
	 */
	public void setPriceMode(Short priceMode) {
		this.priceMode = priceMode;
	}
	/**
	 * 得到菜名.
	 * @return 菜名.
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置菜名.
	 * @param name 菜名.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 得到菜品ID.
	 * @return 菜品ID.
	 */
	public String getMenuId() {
		return menuId;
	}

	/**
	 * 设置菜品ID.
	 * @param menuId 菜品ID.
	 */
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
	/**
	 * 得到菜谱菜肴ID.
	 * @return 菜谱菜肴ID.
	 */
	public String getBookMenuId() {
		return bookMenuId;
	}

	/**
	 * 设置菜谱菜肴ID.
	 * @param bookMenuId 菜谱菜肴ID.
	 */
	public void setBookMenuId(String bookMenuId) {
		this.bookMenuId = bookMenuId;
	}
	/**
	 * 得到做法ID.
	 * @return 做法ID.
	 */
	public String getMakeId() {
		return makeId;
	}

	/**
	 * 设置做法ID.
	 * @param makeId 做法ID.
	 */
	public void setMakeId(String makeId) {
		this.makeId = makeId;
	}
	/**
	 * 得到做法.
	 * @return 做法.
	 */
	public String getMakeName() {
		return makeName;
	}

	/**
	 * 设置做法.
	 * @param makeName 做法.
	 */
	public void setMakeName(String makeName) {
		this.makeName = makeName;
	}
	/**
	 * 得到做法调价.
	 * @return 做法调价.
	 */
	public Double getMakePrice() {
		return makePrice;
	}

	/**
	 * 设置做法调价.
	 * @param makePrice 做法调价.
	 */
	public void setMakePrice(Double makePrice) {
		this.makePrice = makePrice;
	}
	/**
	 * 得到点菜数量.
	 * @return 点菜数量.
	 */
	public Double getNum() {
		return num;
	}

	/**
	 * 设置点菜数量.
	 * @param num 点菜数量.
	 */
	public void setNum(Double num) {
		this.num = num;
	}
	/**
	 * 得到结账数量.
	 * @return 结账数量.
	 */
	public Double getAccountNum() {
		return accountNum;
	}

	/**
	 * 设置结账数量.
	 * @param accountNum 结账数量.
	 */
	public void setAccountNum(Double accountNum) {
		this.accountNum = accountNum;
	}
	/**
	 * 得到单价.
	 * @return 单价.
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * 设置单价.
	 * @param price 单价.
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
	/**
	 * 得到可否打折.
	 * @return 可否打折.
	 */
	public Short getIsRatio() {
		return isRatio;
	}

	/**
	 * 设置可否打折.
	 * @param isRatio 可否打折.
	 */
	public void setIsRatio(Short isRatio) {
		this.isRatio = isRatio;
	}
	/**
	 * 得到折扣率.
	 * @return 折扣率.
	 */
	public Double getRatio() {
		return ratio;
	}

	/**
	 * 设置折扣率.
	 * @param ratio 折扣率.
	 */
	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}
	/**
	 * 得到金额.
	 * @return 金额.
	 */
	public Double getFee() {
		return fee;
	}

	/**
	 * 设置金额.
	 * @param fee 金额.
	 */
	public void setFee(Double fee) {
		this.fee = fee;
	}
	/**
	 * 得到折后金额.
	 * @return 折后金额.
	 */
	public Double getRatioFee() {
		return ratioFee;
	}

	/**
	 * 设置折后金额.
	 * @param ratioFee 折后金额.
	 */
	public void setRatioFee(Double ratioFee) {
		this.ratioFee = ratioFee;
	}
	/**
	 * 得到出品方案ID.
	 * @return 出品方案ID.
	 */
	public String getProdPlanId() {
		return prodPlanId;
	}

	/**
	 * 设置出品方案ID.
	 * @param prodPlanId 出品方案ID.
	 */
	public void setProdPlanId(String prodPlanId) {
		this.prodPlanId = prodPlanId;
	}
	/**
	 * 得到状态.
	 * @return 状态.
	 */
	public Short getStatus() {
		return status;
	}

	/**
	 * 设置状态.
	 * @param status 状态.
	 */
	public void setStatus(Short status) {
		this.status = status;
	}
	/**
	 * 得到是否待菜.
	 * @return 是否待菜.
	 */
	public Short getIsWait() {
		return isWait;
	}

	/**
	 * 设置是否待菜.
	 * @param isWait 是否待菜.
	 */
	public void setIsWait(Short isWait) {
		this.isWait = isWait;
	}
	/**
	 * 得到说明.
	 * @return 说明.
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * 设置说明.
	 * @param memo 说明.
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}
	/**
	 * 得到规格明细ID.
	 * @return 规格明细ID.
	 */
	public String getSpecDetailId() {
		return specDetailId;
	}

	/**
	 * 设置规格明细ID.
	 * @param specDetailId 规格明细ID.
	 */
	public void setSpecDetailId(String specDetailId) {
		this.specDetailId = specDetailId;
	}
	/**
	 * 得到规格名.
	 * @return 规格名.
	 */
	public String getSpecDetailName() {
		return specDetailName;
	}

	/**
	 * 设置规格名.
	 * @param specDetailName 规格名.
	 */
	public void setSpecDetailName(String specDetailName) {
		this.specDetailName = specDetailName;
	}
	/**
	 * 得到规格调价.
	 * @return 规格调价.
	 */
	public Double getSpecDetailPrice() {
		return specDetailPrice;
	}

	/**
	 * 设置规格调价.
	 * @param specDetailPrice 规格调价.
	 */
	public void setSpecDetailPrice(Double specDetailPrice) {
		this.specDetailPrice = specDetailPrice;
	}
	/**
	 * 得到点菜单位.
	 * @return 点菜单位.
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * 设置点菜单位.
	 * @param unit 点菜单位.
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}
	/**
	 * 得到结账单位.
	 * @return 结账单位.
	 */
	public String getAccountUnit() {
		return accountUnit;
	}

	/**
	 * 设置结账单位.
	 * @param accountUnit 结账单位.
	 */
	public void setAccountUnit(String accountUnit) {
		this.accountUnit = accountUnit;
	}
	/**
	 * 得到做法调价模式.
	 * @return 做法调价模式.
	 */
	public Short getMakePriceMode() {
		return makePriceMode;
	}

	/**
	 * 设置做法调价模式.
	 * @param makePriceMode 做法调价模式.
	 */
	public void setMakePriceMode(Short makePriceMode) {
		this.makePriceMode = makePriceMode;
	}
	/**
	 * 得到菜类ID.
	 * @return 菜类ID.
	 */
	public String getKindMenuId() {
		return kindMenuId;
	}

	/**
	 * 设置菜类ID.
	 * @param kindMenuId 菜类ID.
	 */
	public void setKindMenuId(String kindMenuId) {
		this.kindMenuId = kindMenuId;
	}
	/**
	 * 得到原始单价.
	 * @return 原始单价.
	 */
	public Double getOriginalPrice() {
		return originalPrice;
	}

	/**
	 * 设置原始单价.
	 * @param originalPrice 原始单价.
	 */
	public void setOriginalPrice(Double originalPrice) {
		this.originalPrice = originalPrice;
	}
	/**
	 * 得到口味说明.
	 * @return 口味说明.
	 */
	public String getTaste() {
		return taste;
	}

	/**
	 * 设置口味说明.
	 * @param taste 口味说明.
	 */
	public void setTaste(String taste) {
		this.taste = taste;
	}
	/**
	 * 得到是否修改过点菜数量.
	 * @return 是否修改过点菜数量.
	 */
	public Short getIsBuyNumberChanged() {
		return isBuyNumberChanged;
	}

	/**
	 * 设置是否修改过点菜数量.
	 * @param isBuyNumberChanged 是否修改过点菜数量.
	 */
	public void setIsBuyNumberChanged(Short isBuyNumberChanged) {
		this.isBuyNumberChanged = isBuyNumberChanged;
	}
	/**
	 * 得到打折操作人.
	 * @return 打折操作人.
	 */
	public String getRatioOperatorId() {
		return ratioOperatorId;
	}

	/**
	 * 设置打折操作人.
	 * @param ratioOperatorId 打折操作人.
	 */
	public void setRatioOperatorId(String ratioOperatorId) {
		this.ratioOperatorId = ratioOperatorId;
	}
	/**
	 * 得到打折原因.
	 * @return 打折原因.
	 */
	public String getRatioCause() {
		return ratioCause;
	}

	/**
	 * 设置打折原因.
	 * @param ratioCause 打折原因.
	 */
	public void setRatioCause(String ratioCause) {
		this.ratioCause = ratioCause;
	}
	/**
	 * 得到套菜可换菜原始ID.
	 * @return 套菜可换菜原始ID.
	 */
	public String getChildId() {
		return childId;
	}

	/**
	 * 设置套菜可换菜原始ID.
	 * @param childId 套菜可换菜原始ID.
	 */
	public void setChildId(String childId) {
		this.childId = childId;
	}
	/**
	 * 得到会员价.
	 * @return 会员价.
	 */
	public Double getMemberPrice() {
		return memberPrice;
	}

	/**
	 * 设置会员价.
	 * @param memberPrice 会员价.
	 */
	public void setMemberPrice(Double memberPrice) {
		this.memberPrice = memberPrice;
	}
	/**
	 * 得到菜谱菜类ID.
	 * @return 菜谱菜类ID.
	 */
	public String getKindBookMenuId() {
		return kindBookMenuId;
	}

	/**
	 * 设置菜谱菜类ID.
	 * @param kindBookMenuId 菜谱菜类ID.
	 */
	public void setKindBookMenuId(String kindBookMenuId) {
		this.kindBookMenuId = kindBookMenuId;
	}
	/**
	 * 得到规格调价模式.
	 * @return 规格调价模式.
	 */
	public Short getSpecPriceMode() {
		return specPriceMode;
	}

	/**
	 * 设置规格调价模式.
	 * @param specPriceMode 规格调价模式.
	 */
	public void setSpecPriceMode(Short specPriceMode) {
		this.specPriceMode = specPriceMode;
	}
	/**
	 * 得到点菜人.
	 * @return 点菜人.
	 */
	public String getWorkerId() {
		return workerId;
	}

	/**
	 * 设置点菜人.
	 * @param workerId 点菜人.
	 */
	public void setWorkerId(String workerId) {
		this.workerId = workerId;
	}
	/**
	 * 得到退菜是否需要权限.
	 * @return 退菜是否需要权限.
	 */
	public Short getIsBackAuth() {
		return isBackAuth;
	}

	/**
	 * 设置退菜是否需要权限.
	 * @param isBackAuth 退菜是否需要权限.
	 */
	public void setIsBackAuth(Short isBackAuth) {
		this.isBackAuth = isBackAuth;
	}
	/**
	 * 得到操作人.
	 * @return 操作人.
	 */
	public String getOpUserId() {
		return opUserId;
	}

	/**
	 * 设置操作人.
	 * @param opUserId 操作人.
	 */
	public void setOpUserId(String opUserId) {
		this.opUserId = opUserId;
	}
	
	/**
	 * 得到服务费收取方式.
	 * @return 服务费收取方式.
	 */
	public Short getServiceFeeMode() {
		return serviceFeeMode;
	}

	/**
	 * 设置服务费收取方式.
	 * @param serviceFeeMode 服务费收取方式.
	 */
	public void setServiceFeeMode(Short serviceFeeMode) {
		this.serviceFeeMode = serviceFeeMode;
	}

	/**
	 * 得到服务费收取值.
	 * @return 服务费收取值.
	 */
	public Double getServiceFee() {
		return serviceFee;
	}

	/**
	 * 设置服务费收取值.
	 * @param serviceFee 服务费收取值.
	 */
	public void setServiceFee(Double serviceFee) {
		this.serviceFee = serviceFee;
	}
	/**
	 * 得到批次信息.
	 * @return 批次信息.
	 */
	public String getBatchMsg() {
		return batchMsg;
	}

	/**
	 * 设置批次信息.
	 * @param batchMsg 批次信息.
	 */
	public void setBatchMsg(String batchMsg) {
		this.batchMsg = batchMsg;
	}
	/**
	 * 得到waitingInstanceId.
	 * @return waitingInstanceId.
	 */
	public String getWaitingInstanceId() {
		return waitingInstanceId;
	}
	
	/**
	 * 设置waitingInstanceId
	 * @param waitingInstanceId 
	 */
	public void setWaitingInstanceId(String waitingInstanceId) {
		this.waitingInstanceId = waitingInstanceId;
	}

	/**
	 * 得到分单时原菜肴ID.
	 * @return orignId 原菜肴ID.
	 */
	public String getOrignId() {
		return orignId;
	}

	/**
	 * 设置分单时原菜肴ID.
	 * @param orignId 原菜肴ID.
	 */
	public void setOrignId(String orignId) {
		this.orignId = orignId;
	}
	
	/**
	 * 得到加料菜总价
	 * @return
	 */
	public Double getAdditionPrice() {
		return additionPrice;
	}

	/**
	 * 设置加料菜总价
	 * @return
	 */
	public void setAdditionPrice(Double additionPrice) {
		this.additionPrice = additionPrice;
	}

	/**
	 * 得到是否有加料
	 * @return
	 */
	public void setHasAddition(Short hasAddition) {
		this.hasAddition = hasAddition;
	}
	
	/**
	 * 设置是否有加料
	 * @return
	 */
	public Short getHasAddition() {
		return hasAddition;
	}
	
	public String getSeatId() {
		return seatId;
	}
	
	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}
	
	public Short getDrawStatus() {
		return drawStatus != null ? drawStatus : 0;
	}

	public void setDrawStatus(Short drawStatus) {
		this.drawStatus = drawStatus;
	}
	/**
	 * 得到菜肴类型.
	 * @return 菜肴类型.
	 */
	public Short getType() {
		return type;
	}
	/**
	 * 设置菜肴类型.
	 * @param type 菜肴类型.
	 */
	public void setType(Short type) {
		this.type = type;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	 public double getHitPrice() {
        return hitPrice;
    }

    public void setHitPrice(double hitPrice) {
        this.hitPrice = hitPrice;
    }
	

}
