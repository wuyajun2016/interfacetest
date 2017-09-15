/************************* 版权声明 **********************************
 * 版权所有：Copyright (c) 2011, ${year} 黄晓峰
 *
 * 工程名称：	钻木餐饮系统
 * 创建者： 黄晓峰 创建日期： ${date}
 * 创建记录：	创建类结构。
 *
 **/

package com.dfire.testcase.function.bean.cash.base;

import java.awt.Cursor;

/**
 * 基础菜肴.
 * 
 * @author <a href="mailto:xiaofenguser@163.com">黄晓峰</a>
 * @version $Revision$
 */
public abstract class BaseMenu extends Base {
	/**
	 * <code>序列ID</code>.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * <code>表名</code>.
	 */
	public static final String TABLE_NAME = "MENU";
	/**
	 * <code>编码对应的字段</code>.
	 */
	public static final String CODE = "CODE";
	/**
	 * <code>名称对应的字段</code>.
	 */
	public static final String NAME = "NAME";
	/**
	 * <code>口味描述对应的字段</code>.
	 */
	public static final String TASTE = "TASTE";
	/**
	 * <code>拼写对应的字段</code>.
	 */
	public static final String SPELL = "SPELL";
	/**
	 * <code>点菜单位对应的字段</code>.
	 */
	public static final String BUYACCOUNT = "BUYACCOUNT";
	/**
	 * <code>结帐单位对应的字段</code>.
	 */
	public static final String ACCOUNT = "ACCOUNT";
	/**
	 * <code>是否双单位菜品对应的字段</code>.
	 */
	public static final String ISTWOACCOUNT = "ISTWOACCOUNT";
	/**
	 * <code>价格对应的字段</code>.
	 */
	public static final String PRICE = "PRICE";
	/**
	 * <code>备注对应的字段</code>.
	 */
	public static final String MEMO = "MEMO";
	/**
	 * <code>是否套菜对应的字段</code>.
	 */
	public static final String ISINCLUDE = "ISINCLUDE";
	/**
	 * <code>是否特色菜对应的字段</code>.
	 */
	public static final String ISSTYLE = "ISSTYLE";
	/**
	 * <code>是否可打折对应的字段</code>.
	 */
	public static final String ISRATIO = "ISRATIO";
	/**
	 * <code>是否需要确认重量对应的字段</code>.
	 */
	public static final String ISCONFIRM = "ISCONFIRM";
	/**
	 * <code>烧菜耗时(分钟为单位)对应的字段</code>.
	 */
	public static final String CONSUME = "CONSUME";
	/**
	 * <code>提成比例对应的字段</code>.
	 */
	public static final String DEDUCT = "DEDUCT";
	/**
	 * <code>已发送的菜品退菜时是否修改剩余数量对应的字段</code>.
	 */
	public static final String BALANCEMODE = "BALANCEMODE";
	/**
	 * <code>拼写2对应的字段</code>.
	 */
	public static final String SPELL2 = "SPELL2";
	/**
	 * <code>是否支持多规格对应的字段</code>.
	 */
	public static final String ISUSESPEC = "ISUSESPEC";
	/**
	 * <code>是否需要出单对应的字段</code>.
	 */
	public static final String ISPRINT = "ISPRINT";
	/**
	 * <code>提成方式对应的字段</code>.
	 */
	public static final String DEDUCTKIND = "DEDUCTKIND";
	/**
	 * <code>附件版本对应的字段</code>.
	 */
	public static final String ATTACHMENTVER = "ATTACHMENTVER";
	/**
	 * <code>是否接受预订对应的字段</code>.
	 */
	public static final String ISRESERVE = "ISRESERVE";
	/**
	 * <code>预定价格对应的字段</code>.
	 */
	public static final String RESERVEPRICE = "RESERVEPRICE";
	/**
	 * <code>双单位菜肴的默认结账数量(每点菜单位)对应的字段</code>.
	 */
	public static final String DEFAULTNUM = "DEFAULTNUM";
	/**
	 * <code>预定价是否可打折对应的字段</code>.
	 */
	public static final String ISRESERVERATIO = "ISRESERVERATIO";
	/**
	 * <code>顺序码对应的字段</code>.
	 */
	public static final String SORTCODE = "SORTCODE";
	/**
	 * <code>特价对应的字段</code>.
	 */
	public static final String SPECIALPRICE = "SPECIALPRICE";
	/**
	 * <code>照片附件对应的字段</code>.
	 */
	public static final String ATTACHMENTID = "ATTACHMENTID";
	/**
	 * <code>是否特价菜对应的字段</code>.
	 */
	public static final String ISSPECIAL = "ISSPECIAL";
	/**
	 * <code>是否可以赠菜对应的字段</code>.
	 */
	public static final String ISGIVE = "ISGIVE";
	/**
	 * <code>是否可在电子屏上点对应的字段</code>.
	 */
	public static final String ISSELF = "ISSELF";
	/**
	 * <code>点菜时是否允许修改单价对应的字段</code>.
	 */
	public static final String ISCHANGEPRICE = "ISCHANGEPRICE";
	/**
	 * <code>规格ID对应的字段</code>.
	 */
	public static final String SPECID = "SPECID";
	/**
	 * <code>菜类对应的字段</code>.
	 */
	public static final String KINDMENUID = "KINDMENUID";
	/**
	 * <code>退菜是否需要权限对应的字段</code>.
	 */
	public static final String ISBACKAUTH = "ISBACKAUTH";
	/**
	 * <code>会员价对应的字段</code>.
	 */
	public static final String MEMBERPRICE = "MEMBERPRICE";
	/**
	 * <code>是否为加料菜对应的字段</code>.
	 */
	public static final String ISADDITIONAL = "ISADDITIONAL";

	/**
	 * <code>服务费收取方式对应的字段</code>.
	 */
	public static final String SERVICEFEEMODE = "SERVICEFEEMODE";
	/**
	 * <code>服务费收取数值对应的字段</code>.
	 */
	public static final String SERVICEFEE = "SERVICEFEE";
	
	public static final String ACRIDLEVEL = "ACRIDLEVEL";

	// /**
	// * <code>服务器地址对应的字段</code>.
	// */
	// public static final String SERVER = "SERVER";
	// /**
	// * <code>路径对应的字段</code>.
	// */
	// public static final String PATH = "PATH";
	/**
	 * <code>编码</code>.
	 */
	private String code;
	/**
	 * <code>名称</code>.
	 */
	private String name;
	/**
	 * <code>口味描述</code>.
	 */
	private String taste;
	/**
	 * <code>拼写</code>.
	 */
	private String spell;
	/**
	 * <code>点菜单位</code>.
	 */
	private String buyAccount;
	/**
	 * <code>结帐单位</code>.
	 */
	private String account;
	/**
	 * <code>是否双单位菜品</code>.
	 */
	private Short isTwoAccount;
	/**
	 * <code>价格</code>.
	 */
	private Double price;
	/**
	 * <code>备注</code>.
	 */
	private String memo;
	/**
	 * <code>是否套菜</code>.
	 */
	private Short isInclude;
	/**
	 * <code>是否特色菜</code>.
	 */
	private Short isStyle;
	/**
	 * <code>是否可打折</code>.
	 */
	private Short isRatio;
	/**
	 * <code>是否需要确认重量</code>.
	 */
	private Short isConfirm;
	/**
	 * <code>烧菜耗时(分钟为单位)</code>.
	 */
	private Integer consume;
	/**
	 * <code>提成比例</code>.
	 */
	private Double deduct;
	/**
	 * <code>已发送的菜品退菜时是否修改剩余数量</code>.
	 */
	private Short balanceMode;
	/**
	 * <code>拼写2</code>.
	 */
	private String spell2;
	/**
	 * <code>是否支持多规格</code>.
	 */
	private Short isUseSpec;
	/**
	 * <code>是否需要出单</code>.
	 */
	private Short isPrint;
	/**
	 * <code>提成方式</code>.
	 */
	private Short deductKind;
	/**
	 * <code>附件版本</code>.
	 */
	private Integer attachmentVer;
	/**
	 * <code>是否接受预订</code>.
	 */
	private Short isReserve;
	/**
	 * <code>预定价格</code>.
	 */
	private Double reservePrice;
	/**
	 * <code>双单位菜肴的默认结账数量(每点菜单位)</code>.
	 */
	private Double defaultNum;
	/**
	 * <code>预定价是否可打折</code>.
	 */
	private Short isReserveRatio;
	/**
	 * <code>顺序码</code>.
	 */
	private Integer sortCode;
	/**
	 * <code>特价</code>.
	 */
	private Double specialPrice;
	/**
	 * <code>照片附件</code>.
	 */
	private String attachmentId;
	/**
	 * <code>是否特价菜</code>.
	 */
	private Short isSpecial;
	/**
	 * <code>是否可以赠菜</code>.
	 */
	private Short isGive;
	/**
	 * <code>是否可在电子屏上点</code>.
	 */
	private Short isSelf;
	/**
	 * <code>点菜时是否允许修改单价</code>.
	 */
	private Short isChangePrice;
	/**
	 * <code>规格ID</code>.
	 */
	private String specId;
	/**
	 * <code>菜类</code>.
	 */
	private String kindMenuId;
	/**
	 * <code>退菜是否需要权限</code>.
	 */
	private Short isBackAuth;
	/**
	 * <code>会员价</code>.
	 */
	private Double memberPrice;
	/**
	 * <code>是否为加料菜</code>.
	 */
	private Short IsAdditional;
	/**
	 * <code>服务费收取方式</code>.
	 */
	private Short serviceFeeMode;
	/**
	 * <code>服务费收取数值</code>.
	 */
	private Double serviceFee;

	/**
	 * <code>辣椒指数</code>
	 */
	private Integer acridLevel;

	// /**
	// * <code>服务器地址</code>.
	// */
	// private String server;
	// /**
	// * <code>路径</code>.
	// */
	// private String path;

	// public String getServer() {
	// return server;
	// }
	//
	// public void setServer(String server) {
	// this.server = server;
	// }
	//
	// public String getPath() {
	// return path;
	// }
	//
	// public void setPath(String path) {
	// this.path = path;
	// }

	/**
	 * 得到编码.
	 * 
	 * @return 编码.
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 设置编码.
	 * 
	 * @param code
	 *            编码.
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 得到名称.
	 * 
	 * @return 名称.
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置名称.
	 * 
	 * @param name
	 *            名称.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 得到口味描述.
	 * 
	 * @return 口味描述.
	 */
	public String getTaste() {
		return taste;
	}

	/**
	 * 设置口味描述.
	 * 
	 * @param taste
	 *            口味描述.
	 */
	public void setTaste(String taste) {
		this.taste = taste;
	}

	/**
	 * 得到拼写.
	 * 
	 * @return 拼写.
	 */
	public String getSpell() {
		return spell;
	}

	/**
	 * 设置拼写.
	 * 
	 * @param spell
	 *            拼写.
	 */
	public void setSpell(String spell) {
		this.spell = spell;
	}

	/**
	 * 得到点菜单位.
	 * 
	 * @return 点菜单位.
	 */
	public String getBuyAccount() {
		return buyAccount;
	}

	/**
	 * 设置点菜单位.
	 * 
	 * @param buyAccount
	 *            点菜单位.
	 */
	public void setBuyAccount(String buyAccount) {
		this.buyAccount = buyAccount;
	}

	/**
	 * 得到结帐单位.
	 * 
	 * @return 结帐单位.
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * 设置结帐单位.
	 * 
	 * @param account
	 *            结帐单位.
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * 得到是否双单位菜品.
	 * 
	 * @return 是否双单位菜品.
	 */
	public Short getIsTwoAccount() {
		return isTwoAccount;
	}

	/**
	 * 设置是否双单位菜品.
	 * 
	 * @param isTwoAccount
	 *            是否双单位菜品.
	 */
	public void setIsTwoAccount(Short isTwoAccount) {
		this.isTwoAccount = isTwoAccount;
	}

	/**
	 * 得到价格.
	 * 
	 * @return 价格.
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * 设置价格.
	 * 
	 * @param price
	 *            价格.
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * 得到备注.
	 * 
	 * @return 备注.
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * 设置备注.
	 * 
	 * @param memo
	 *            备注.
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * 得到是否套菜.
	 * 
	 * @return 是否套菜.
	 */
	public Short getIsInclude() {
		return isInclude;
	}

	/**
	 * 设置是否套菜.
	 * 
	 * @param isInclude
	 *            是否套菜.
	 */
	public void setIsInclude(Short isInclude) {
		this.isInclude = isInclude;
	}

	/**
	 * 得到是否特色菜.
	 * 
	 * @return 是否特色菜.
	 */
	public Short getIsStyle() {
		return isStyle;
	}

	/**
	 * 设置是否特色菜.
	 * 
	 * @param isStyle
	 *            是否特色菜.
	 */
	public void setIsStyle(Short isStyle) {
		this.isStyle = isStyle;
	}

	/**
	 * 得到是否可打折.
	 * 
	 * @return 是否可打折.
	 */
	public Short getIsRatio() {
		return isRatio;
	}

	/**
	 * 设置是否可打折.
	 * 
	 * @param isRatio
	 *            是否可打折.
	 */
	public void setIsRatio(Short isRatio) {
		this.isRatio = isRatio;
	}

	/**
	 * 得到是否需要确认重量.
	 * 
	 * @return 是否需要确认重量.
	 */
	public Short getIsConfirm() {
		return isConfirm;
	}

	/**
	 * 设置是否需要确认重量.
	 * 
	 * @param isConfirm
	 *            是否需要确认重量.
	 */
	public void setIsConfirm(Short isConfirm) {
		this.isConfirm = isConfirm;
	}

	/**
	 * 得到烧菜耗时(分钟为单位).
	 * 
	 * @return 烧菜耗时(分钟为单位).
	 */
	public Integer getConsume() {
		return consume;
	}

	/**
	 * 设置烧菜耗时(分钟为单位).
	 * 
	 * @param consume
	 *            烧菜耗时(分钟为单位).
	 */
	public void setConsume(Integer consume) {
		this.consume = consume;
	}

	/**
	 * 得到提成比例.
	 * 
	 * @return 提成比例.
	 */
	public Double getDeduct() {
		return deduct;
	}

	/**
	 * 设置提成比例.
	 * 
	 * @param deduct
	 *            提成比例.
	 */
	public void setDeduct(Double deduct) {
		this.deduct = deduct;
	}

	/**
	 * 得到已发送的菜品退菜时是否修改剩余数量.
	 * 
	 * @return 已发送的菜品退菜时是否修改剩余数量.
	 */
	public Short getBalanceMode() {
		return balanceMode;
	}

	/**
	 * 设置已发送的菜品退菜时是否修改剩余数量.
	 * 
	 * @param balanceMode
	 *            已发送的菜品退菜时是否修改剩余数量.
	 */
	public void setBalanceMode(Short balanceMode) {
		this.balanceMode = balanceMode;
	}

	/**
	 * 得到拼写2.
	 * 
	 * @return 拼写2.
	 */
	public String getSpell2() {
		return spell2;
	}

	/**
	 * 设置拼写2.
	 * 
	 * @param spell2
	 *            拼写2.
	 */
	public void setSpell2(String spell2) {
		this.spell2 = spell2;
	}

	/**
	 * 得到是否支持多规格.
	 * 
	 * @return 是否支持多规格.
	 */
	public Short getIsUseSpec() {
		return isUseSpec;
	}

	/**
	 * 设置是否支持多规格.
	 * 
	 * @param isUseSpec
	 *            是否支持多规格.
	 */
	public void setIsUseSpec(Short isUseSpec) {
		this.isUseSpec = isUseSpec;
	}

	/**
	 * 得到是否需要出单.
	 * 
	 * @return 是否需要出单.
	 */
	public Short getIsPrint() {
		return isPrint;
	}

	/**
	 * 设置是否需要出单.
	 * 
	 * @param isPrint
	 *            是否需要出单.
	 */
	public void setIsPrint(Short isPrint) {
		this.isPrint = isPrint;
	}

	/**
	 * 得到提成方式.
	 * 
	 * @return 提成方式.
	 */
	public Short getDeductKind() {
		return deductKind;
	}

	/**
	 * 设置提成方式.
	 * 
	 * @param deductKind
	 *            提成方式.
	 */
	public void setDeductKind(Short deductKind) {
		this.deductKind = deductKind;
	}

	/**
	 * 得到附件版本.
	 * 
	 * @return 附件版本.
	 */
	public Integer getAttachmentVer() {
		return attachmentVer;
	}

	/**
	 * 设置附件版本.
	 * 
	 * @param attachmentVer
	 *            附件版本.
	 */
	public void setAttachmentVer(Integer attachmentVer) {
		this.attachmentVer = attachmentVer;
	}

	/**
	 * 得到是否接受预订.
	 * 
	 * @return 是否接受预订.
	 */
	public Short getIsReserve() {
		return isReserve;
	}

	/**
	 * 设置是否接受预订.
	 * 
	 * @param isReserve
	 *            是否接受预订.
	 */
	public void setIsReserve(Short isReserve) {
		this.isReserve = isReserve;
	}

	/**
	 * 得到预定价格.
	 * 
	 * @return 预定价格.
	 */
	public Double getReservePrice() {
		return reservePrice;
	}

	/**
	 * 设置预定价格.
	 * 
	 * @param reservePrice
	 *            预定价格.
	 */
	public void setReservePrice(Double reservePrice) {
		this.reservePrice = reservePrice;
	}

	/**
	 * 得到双单位菜肴的默认结账数量(每点菜单位).
	 * 
	 * @return 双单位菜肴的默认结账数量(每点菜单位).
	 */
	public Double getDefaultNum() {
		return defaultNum;
	}

	/**
	 * 设置双单位菜肴的默认结账数量(每点菜单位).
	 * 
	 * @param defaultNum
	 *            双单位菜肴的默认结账数量(每点菜单位).
	 */
	public void setDefaultNum(Double defaultNum) {
		this.defaultNum = defaultNum;
	}

	/**
	 * 得到预定价是否可打折.
	 * 
	 * @return 预定价是否可打折.
	 */
	public Short getIsReserveRatio() {
		return isReserveRatio;
	}

	/**
	 * 设置预定价是否可打折.
	 * 
	 * @param isReserveRatio
	 *            预定价是否可打折.
	 */
	public void setIsReserveRatio(Short isReserveRatio) {
		this.isReserveRatio = isReserveRatio;
	}

	/**
	 * 得到顺序码.
	 * 
	 * @return 顺序码.
	 */
	public Integer getSortCode() {
		return sortCode;
	}

	/**
	 * 设置顺序码.
	 * 
	 * @param sortCode
	 *            顺序码.
	 */
	public void setSortCode(Integer sortCode) {
		this.sortCode = sortCode;
	}

	/**
	 * 得到特价.
	 * 
	 * @return 特价.
	 */
	public Double getSpecialPrice() {
		return specialPrice;
	}

	/**
	 * 设置特价.
	 * 
	 * @param specialPrice
	 *            特价.
	 */
	public void setSpecialPrice(Double specialPrice) {
		this.specialPrice = specialPrice;
	}

	/**
	 * 得到照片附件.
	 * 
	 * @return 照片附件.
	 */
	public String getAttachmentId() {
		return attachmentId;
	}

	/**
	 * 设置照片附件.
	 * 
	 * @param attachmentId
	 *            照片附件.
	 */
	public void setAttachmentId(String attachmentId) {
		this.attachmentId = attachmentId;
	}

	/**
	 * 得到是否特价菜.
	 * 
	 * @return 是否特价菜.
	 */
	public Short getIsSpecial() {
		return isSpecial;
	}

	/**
	 * 设置是否特价菜.
	 * 
	 * @param isSpecial
	 *            是否特价菜.
	 */
	public void setIsSpecial(Short isSpecial) {
		this.isSpecial = isSpecial;
	}

	/**
	 * 得到是否可以赠菜.
	 * 
	 * @return 是否可以赠菜.
	 */
	public Short getIsGive() {
		return isGive;
	}

	/**
	 * 设置是否可以赠菜.
	 * 
	 * @param isGive
	 *            是否可以赠菜.
	 */
	public void setIsGive(Short isGive) {
		this.isGive = isGive;
	}

	/**
	 * 得到是否可在电子屏上点.
	 * 
	 * @return 是否可在电子屏上点.
	 */
	public Short getIsSelf() {
		return isSelf;
	}

	/**
	 * 设置是否可在电子屏上点.
	 * 
	 * @param isSelf
	 *            是否可在电子屏上点.
	 */
	public void setIsSelf(Short isSelf) {
		this.isSelf = isSelf;
	}

	/**
	 * 得到点菜时是否允许修改单价.
	 * 
	 * @return 点菜时是否允许修改单价.
	 */
	public Short getIsChangePrice() {
		return isChangePrice;
	}

	/**
	 * 设置点菜时是否允许修改单价.
	 * 
	 * @param isChangePrice
	 *            点菜时是否允许修改单价.
	 */
	public void setIsChangePrice(Short isChangePrice) {
		this.isChangePrice = isChangePrice;
	}

	/**
	 * 得到规格ID.
	 * 
	 * @return 规格ID.
	 */
	public String getSpecId() {
		return specId;
	}

	/**
	 * 设置规格ID.
	 * 
	 * @param specId
	 *            规格ID.
	 */
	public void setSpecId(String specId) {
		this.specId = specId;
	}

	/**
	 * 得到菜类.
	 * 
	 * @return 菜类.
	 */
	public String getKindMenuId() {
		return kindMenuId;
	}

	/**
	 * 设置菜类.
	 * 
	 * @param kindMenuId
	 *            菜类.
	 */
	public void setKindMenuId(String kindMenuId) {
		this.kindMenuId = kindMenuId;
	}

	/**
	 * 得到退菜是否需要权限.
	 * 
	 * @return 退菜是否需要权限.
	 */
	public Short getIsBackAuth() {
		return isBackAuth;
	}

	/**
	 * 设置退菜是否需要权限.
	 * 
	 * @param isBackAuth
	 *            退菜是否需要权限.
	 */
	public void setIsBackAuth(Short isBackAuth) {
		this.isBackAuth = isBackAuth;
	}

	/**
	 * 得到会员价.
	 * 
	 * @return 会员价.
	 */
	public Double getMemberPrice() {
		return memberPrice;
	}

	/**
	 * 设置会员价.
	 * 
	 * @param memberPrice
	 *            会员价.
	 */
	public void setMemberPrice(Double memberPrice) {
		this.memberPrice = memberPrice;
	}

	/**
	 * 得到是否为加料菜.
	 * 
	 * @return 是否为加料菜.
	 */
	public Short getIsAdditional() {
		return IsAdditional;
	}

	/**
	 * 设置会员价.
	 * 
	 * @param isAdditional
	 *            是否为加料菜.
	 */
	public void setIsAdditional(Short isAdditional) {
		IsAdditional = isAdditional;
	}

	/**
	 * 得到服务费收取方式.
	 * 
	 * @return 服务费收取方式.
	 */
	public Short getServiceFeeMode() {
		return serviceFeeMode;
	}

	/**
	 * 设置服务费收取方式.
	 * 
	 * @param serviceFeeMode
	 *            服务费收取方式.
	 */
	public void setServiceFeeMode(Short serviceFeeMode) {
		this.serviceFeeMode = serviceFeeMode;
	}

	/**
	 * 得到服务费收取数值.
	 * 
	 * @return 服务费收取数值.
	 */
	public Double getServiceFee() {
		return serviceFee;
	}

	/**
	 * 设置服务费收取数值.
	 * 
	 * @param serviceFee
	 *            服务费收取数值.
	 */
	public void setServiceFee(Double serviceFee) {
		this.serviceFee = serviceFee;
	}

	/**
	 * 得到辣椒指数.
	 * 
	 * @return 辣椒指数.
	 */
	public Integer getAcridLevel() {
		return acridLevel;
	}

	/**
	 * 设置辣椒指数.
	 * 
	 * @param acridlevel
	 *            辣椒指数.
	 */
	public void setAcridlevel(Integer acridLevel) {
		this.acridLevel = acridLevel;
	}
}
