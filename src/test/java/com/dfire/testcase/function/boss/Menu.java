package com.dfire.testcase.function.boss;

public class Menu {
	


	/**
     * <code>服务费收取值</code>.
     */
    private double serviceFee = 0;
    
    
    /**
     * <code>是否特色菜</code>.
     */
    private int isStyle = 0;


    /**
     * <code>退菜是否需要权限</code>.
     */
    private int isBackAuth = 1;
    
    
    /**
     * <code>主键</code>.
     */
    private String id;
    
    
    /**
     * <code>已发送的菜肴退菜时是否修改剩余数量</code>.
     */
    private int balanceMode = 0;
    
    
    /**
     * <code>标签来源对应的字段 1/系统标签、2/商户自定义标签</code>.
     */
    private int tagSource = 0;
    
    
    /**
     * I'dont know 
     */
    private int timePriceIsRatio = 0;
    
    
    /**
     * <code>版本号</code>.
     */
    private int lastVer = 0;
    
    
    /**
     * <code>预定价格</code>.
     */
    private double reservePrice = 0;
    
    
    /**
     * <code>提成比例</code>.
     */
    private double deduct = 0;
    
    
    /**
     * <code>是否可以赠菜</code>.
     */
    private int isGive = 0;
    
    
    /**
     * <code> I don't know
     */
    private String tagOfMenu;
    
    
    /**
     * <code>顺序码</code>.
     */
    private int sortCode = 0;
    
    
    /**
     * <code>是否可打折</code>.
     */
    private int isRatio = 1;
    
    
    /**
     * <code>结帐单位</code>.
     */
    private String account = "份";
    
    
    /**
     * <code>是否套菜</code>.
     */
    private int isInclude = 0;
    
    
    /**
     * <code>是否需要出单</code>.
     */
    private int isPrint = 0;
    
    
    /**
     * <code>是否双单位菜肴</code>.
     */
    private int isTwoAccount = 0;
    
    
    /**
     * <code> I don't know <code>
     */
    private int first = 0;
    
    
    /**
     * <code>加辣指数</code>.
     */
    private int acridLevel = 0;
    
    
    /**
     * <code>是否需要确认重量</code>.
     */
    private int isConfirm = 0;
    
    
    /**
     * <code> I don't know <code>
     */
    private int end = 0;
    
    
    /**
     * <code>是否支持多规格</code>.
     */
    private int isUseSpec = 0;
    
    
    
    /**
     * <code>提成方式</code>.
     */
    private int deductKind = 1;
    
    
    /**
     * <code>会员价</code>.
     */
    private double memberPrice;
    
    
    /**
     * <code> I don't know <code>
     */
    private int timePrice = 0;
    
    
    /**
     * <code>名称</code>.
     */
    private String name;
    
    
    /**
     * <code>编码</code>.
     */
    private String code="";
    
    
    /**
     * <code>特价</code>.
     */
    private double specialPrice = 0;
    
    
    /**
     * <code> I don't know <code>
     */
    private int warehouseCount = 0;
    
    
    /**
     * <code> 是不是必选菜 <code>
     */
    private int isForceMenu = 0;
    
    
    /**
     * <code>价格</code>.
     */
    private double price;
    
    
    /**
     * <code>点菜单位</code>.
     */
    private String buyAccount = "份";
    
    
    /**
     * <code>服务费收取方式</code>.
     */
    private int serviceFeeMode = 0;
    
    
    /**
     * <code>预定价是否可打折</code>.
     */
    private int isReserveRatio = 0;
    
    
    /**
     * <code>数据来源标记</code>.
     */
    private int ownerType = 0;
    
    
    
    /**
     * <code> I don't know <code>
     */
    private int maxCount = 0;
    
    
    /**
     * <code>是否有效</code>.
     */
    private int isValid = 1;
    
    
    
    /**
     * <code>推荐指数对应的字段 0/不设定、1/推荐、2/十分推荐、3/强烈推荐</code>.
     */
    private int recommendLevel = 0;
    
    
    /**
     * <code>点菜时是否允许修改单价</code>.
     */
    private int isChangePrice = 0;
    
    
    
    /**
     * <code>是否加料菜(配菜)</code>.
     */
    private int isAdditional = 0;
    
    
    /**
     * <code>菜类</code>.
     */
    private String kindMenuId;
    
    
    /**
     * <code>记录创建时间</code>.
     */
    private long createTime = 0;
    
    
    /**
     * <code>附件版本</code>.
     */
    private int attachmentVer = 0;
    
    
    /**
     * <code>操作时间</code>.
     */
    private long opTime = 0;
    
    
    /**
     * <code>烧菜耗时(分钟为单位)</code>.
     */
    private int consume = 0;
    
    
    /**
     * <code>是否可在电子屏上点</code>.
     */
    private int isSelf = 1;
    
    
    /**
     * <code>是否接受预定</code>.
     */
    private int isReserve = 1;
    
    
    /**
     * <code>备注</code>.
     */
    private String memo="";
    
    
    /**
     * <code>是否特价菜</code>.
     */
    private int isSpecial = 0;
    
    
    /**
     * <code> I don't know <code>
     */
    private int chain = 0;
    
    
    /**
     * <code>双单位菜肴的默认结账数量(每点菜单位)</code>.
     */
    private double defaultNum = 0;
    
    
    
	/**
	 * 构造函数, onSelf 字段为 true 表明该商品上架; 该字段为 false, 表明该商品下架
	 * @param id
	 * @param tagOfMenu
	 * @param memberPrice
	 * @param name
	 * @param price
	 * @param kindMenuId
	 */
	public Menu(String id, String tagOfMenu, double memberPrice, String name, double price, String kindMenuId, boolean onSelf){
		
		this.setId(id);
		this.setTagOfMenu(tagOfMenu);
		this.setMemberPrice(memberPrice);
		
		this.setName(name);
		this.setPrice(price);
		this.kindMenuId = kindMenuId;
		
		if(!onSelf){
			
			this.isSelf = 0;
			this.isReserve = 1;
		}
	}
	
	
	
	 public double getServiceFee() {
		return serviceFee;
	}


	public void setServiceFee(double serviceFee) {
		this.serviceFee = serviceFee;
	}

	public int getIsStyle() {
		return isStyle;
	}

	public void setIsStyle(int isStyle) {
		this.isStyle = isStyle;
	}

	public int getIsBackAuth() {
		return isBackAuth;
	}

	public void setIsBackAuth(int isBackAuth) {
		this.isBackAuth = isBackAuth;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getBalanceMode() {
		return balanceMode;
	}

	public void setBalanceMode(int balanceMode) {
		this.balanceMode = balanceMode;
	}

	public int getTagSource() {
		return tagSource;
	}

	public void setTagSource(int tagSource) {
		this.tagSource = tagSource;
	}

	public int getTimePriceIsRatio() {
		return timePriceIsRatio;
	}

	public void setTimePriceIsRatio(int timePriceIsRatio) {
		this.timePriceIsRatio = timePriceIsRatio;
	}

	public int getLastVer() {
		return lastVer;
	}

	public void setLastVer(int lastVer) {
		this.lastVer = lastVer;
	}

	public double getReservePrice() {
		return reservePrice;
	}

	public void setReservePrice(double reservePrice) {
		this.reservePrice = reservePrice;
	}

	public double getDeduct() {
		return deduct;
	}

	public void setDeduct(double deduct) {
		this.deduct = deduct;
	}

	public int getIsGive() {
		return isGive;
	}

	public void setIsGive(int isGive) {
		this.isGive = isGive;
	}

	public String getTagOfMenu() {
		return tagOfMenu;
	}

	public void setTagOfMenu(String tagOfMenu) {
		this.tagOfMenu = tagOfMenu;
	}

	public int getSortCode() {
		return sortCode;
	}

	public void setSortCode(int sortCode) {
		this.sortCode = sortCode;
	}

	public int getIsRatio() {
		return isRatio;
	}

	public void setIsRatio(int isRatio) {
		this.isRatio = isRatio;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public int getIsInclude() {
		return isInclude;
	}

	public void setIsInclude(int isInclude) {
		this.isInclude = isInclude;
	}

	public int getIsPrint() {
		return isPrint;
	}

	public void setIsPrint(int isPrint) {
		this.isPrint = isPrint;
	}

	public int getIsTwoAccount() {
		return isTwoAccount;
	}

	public void setIsTwoAccount(int isTwoAccount) {
		this.isTwoAccount = isTwoAccount;
	}

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getAcridLevel() {
		return acridLevel;
	}

	public void setAcridLevel(int acridLevel) {
		this.acridLevel = acridLevel;
	}

	public int getIsConfirm() {
		return isConfirm;
	}

	public void setIsConfirm(int isConfirm) {
		this.isConfirm = isConfirm;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public int getIsUseSpec() {
		return isUseSpec;
	}

	public void setIsUseSpec(int isUseSpec) {
		this.isUseSpec = isUseSpec;
	}

	public int getDeductKind() {
		return deductKind;
	}

	public void setDeductKind(int deductKind) {
		this.deductKind = deductKind;
	}

	public double getMemberPrice() {
		return memberPrice;
	}

	public void setMemberPrice(double memberPrice) {
		this.memberPrice = memberPrice;
	}

	public int getTimePrice() {
		return timePrice;
	}

	public void setTimePrice(int timePrice) {
		this.timePrice = timePrice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getSpecialPrice() {
		return specialPrice;
	}

	public void setSpecialPrice(double specialPrice) {
		this.specialPrice = specialPrice;
	}

	public int getWarehouseCount() {
		return warehouseCount;
	}

	public void setWarehouseCount(int warehouseCount) {
		this.warehouseCount = warehouseCount;
	}

	public int getIsForceMenu() {
		return isForceMenu;
	}

	public void setIsForceMenu(int isForceMenu) {
		this.isForceMenu = isForceMenu;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getBuyAccount() {
		return buyAccount;
	}

	public void setBuyAccount(String buyAccount) {
		this.buyAccount = buyAccount;
	}

	public int getServiceFeeMode() {
		return serviceFeeMode;
	}

	public void setServiceFeeMode(int serviceFeeMode) {
		this.serviceFeeMode = serviceFeeMode;
	}

	public int getIsReserveRatio() {
		return isReserveRatio;
	}

	public void setIsReserveRatio(int isReserveRatio) {
		this.isReserveRatio = isReserveRatio;
	}

	public int getOwnerType() {
		return ownerType;
	}

	public void setOwnerType(int ownerType) {
		this.ownerType = ownerType;
	}
    
    
}
