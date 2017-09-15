package com.dfire.qa.meal.constant;

public class BossContents {
	
	
	public class InfraClient {

        // 定义返回信息
		public static final String COMMON = " 执行 掌柜端--顾客端设置--基础设置 开关： ";
		
		public static final String COMPOSINGTYPE = "微店菜单默认排版方式";
        public static final String SHOWPRICE = "二维火小二和微信点单页面上不显示合计价格";
        
        public static final String SHOWPEOPLE = "顾客点餐时需要选择用餐人数";
        public static final String SHOWSELLOUTMENU = "菜单中显示沽清的商品";
        public static final String SHOWCOMMENT = "店首页显示顾客评价";
        
        public static final String SHOWPOPWIN = "顾客点餐展示充值优惠弹窗";   
        public static final String SHOWCOUPONWIN = "顾客点餐展示优惠券弹窗";
        public static final String SHOWMSGNOUP = "手机下单可以选择暂不上菜";
        
        public static final String SHOWMSGCOLD = "手机下单可以选择冷上热待";
        public static final String SHOWMSGTAKEOUT = "手机下单可以选择打包带走";
        public static final String SHOWBUTTON = "顾客端显示扫菜码点菜按钮";
        
        public static final String SHOWPAPERINVOICE = "顾客付款后可以申请开纸质发票";
        public static final String SHOWELECINVOICE = "顾客付款后可以申请开电子发票";
        public static final String LEAVEEMPTY = "商品展示图片排版是否留空";
        
        public static final String JUDGELOCATION = "开启经纬度控制";
        
        
        
        public static final String BATCHSET = "掌柜端批量设置";
        public static final String SHOWMENUCODE = "顾客端显示扫菜码点菜按钮";
        public static final String TAKEOUT = "外卖设置";
        public static final String OPENTAKEOUT = "打开外卖设置";
        public static final String CLOSETAKEOUT = "关闭外卖设置";
        
        public static final String TAKEOUTTIME = "外卖配送时间设置";
        public static final String DELETEALLTAKEOUTCONFIG = "删除所有外卖时间设置";
        public static final String DELETETAKEOUTTIME = "删除外卖时间设置";
        public static final String GETTAKEOUTTIMECONFIG = "获取外卖时间设置";
        
        public static final String TAKEOUTDELIVERYMAN = "外卖配送人员设置";
        public static final String GETTAKEOUTDELIVERYMAN = "获取外卖配送人员信息";
        public static final String DELETETAKEOUTDELIVERYMAN = "删除外卖配送人员信息";
        public static final String DELETEALLTAKEOUTDELIVERYMAN = "删除所有外卖配送人员信息";
        
        
        public static final String GETALLFORCEMENULIST = "获取已有菜单列表";
        public static final String GETFORCEMENULIST = "获取必选菜列表";
        public static final String DELETEFORCEMENU = "删除必选菜";
        public static final String DELETEALLFORCEMENU = "删除所有必选菜";
        public static final String SAVEFORCEMENU = "保存必选菜";
        
        
        
        public static final String FILEUPLOAD = "文件上传";
        public static final String UPDATESTYLE = "个性化换肤--色调风格";
        
        public static final String CUSTOMBG = "个性化换肤--自定义背景";
        public static final String CUSTOMICON = "个性化换肤--自定义图标";
        
        public static final String MENUREPEATREMAIND = "顾客点餐重复提醒";
        public static final String MENUREPEATADDMENU = "顾客点餐重复提醒---加菜";
        public static final String MENUREPEATDELETEMENU = "顾客点餐重复提醒---删除菜";
        
        public static final String SAVECOUPON = "会员---保存优惠券";
        public static final String LISTCOUPON = "会员---优惠券列表";
        public static final String GETCOUPON = "获取优惠券";
        public static final String DELETECOUPON = "会员---删除优惠券";

        public static final String SAVEPROMOTION = "会员---保存促销";
        public static final String DELETEPROMOTION = "会员---删除促销";
        
        
        public static final String SAVEPRIVILEGE = "会员---保存会员等级特权";
        public static final String DELETEPRIVILEGE = "会员---删除会员等级特权";
        public static final String DELETECUSTOMPRIVILEGE = "会员---删除自定义会员等级特权";
        
        public static final String ADDCTUOMPRIVILEGE = "会员---添加自定义权限";
        public static final String DELETEALLCUSTOMPRIVILEGE = "会员---在自定义列表中删除自定义会员等级特权";
        
        
        public static final String GETALLPRIVILEGEDETAIL = "会员---获取所有会员特权详情";
        public static final String DELETEALLPRIVILEGEDETAIL = "会员---删除所有会员特权详情";
        
        
        public static final String GETALLCARDPRIVILEGEDETAIL = "会员---获取所有特权卡详情";
        public static final String DELETEALLCARDPRIVILEGEDETAIL = "会员---删除所有特权卡详情";
        
        public static final String ADDCARDPRIVILEGE = "会员---在特权库中添加特权卡设置";
        public static final String ADDPRIVILEGE = "会员---在用户特权中添加特权卡设置";
        
        
        public static final String QUERYKINDCARDS = "会员--卡充值优惠---查询优惠卡";
        public static final String RECHARGERULES = "会员--卡充值优惠---允许顾客端使用火小二自助充值";
        public static final String ADDRECHARGECARDS = "会员--卡充值优惠---添加卡充值优惠";
        public static final String DELETEKINDCARDS = "会员--卡充值优惠---删除所有充值优惠信息";
        
        
        public static final String SAVECARDGIFT = "会员---积分兑换设置---保存卡金额设置";
        public static final String SAVECOUPONGIFT = "会员---积分兑换设置---保存优惠券设置";
        public static final String GETGIFT = "会员---积分兑换设置---获取设置信息";
        public static final String DELETEALLGIFT = "会员---积分兑换设置---删除所有设置";
        
        
        public static final String SAVEGIFTCONVERT = "会员---积分抵现设置";
        public static final String GETGIFTCONVERT = "会员---积分抵现设置---获取";
        public static final String DELETEALLGIFTCONVERT = "会员---积分抵现设置---删除所有";
        
        
        public static final String UPDATEMENULABEL = "保存或更新菜品标签";
        public static final String MENURECOMMAND = "智能点餐";
        public static final String OPENMENURECOMMEND = "开启智能点餐";
        public static final String CLOSEMENURECOMMEND = "关闭智能点餐";
        
        
        public static final String MENUTEMPLATE = "智能点餐模板";
        public static final String UPDATEMENUTEMPLATE = "更新点餐模板";
        public static final String QUERYMENUTEMPLATE = "查询智能点餐模板";
        public static final String OPENMENUTEMPLATE = "开启智能模板点餐";
        public static final String CLOSEMENUTEMPLATE = "关闭智能模板点餐";
        
        
        
        public static final String GETACTIVITYDETAIL = "一元购---获取抽奖活动详情";
        public static final String GETACTIVITYLIST = "一元购---一元购活动列表页";
        public static final String GETMENULIST = "一元购---获取菜列表";
        
        public static final String MENUONSELF = "商品上下架";
        
	}
	
	
	public class Error {
		
		public static final String TAKEOUTDELIVERYMAN = "外卖联系人";
		public static final String TAKEOUTTIMECONFIG = "外卖时间";
		
	}

}
