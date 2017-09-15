package com.dfire.qa.meal.constant;

/**
 * Created by ljw on 17/2/17.
 */
public class Constants {

	
	
	public class Common {

        // 定义返回信息
		public static final String CASEID = " 用例 ID ：";
		
        public static final String GETTOKEN = "获取用户 token";
        
        public static final String SHOPCODEVERIFIED = "店码数据验证";
        public static final String SHOPVERYSUC = "店码数据验证成功";
        public static final String SHOPVERYFAIL = "店码数据验证失败";
        
        public static final String SEATCODEVERIFIED = "桌码数据验证";   
        public static final String SEATVERYSUC = "桌码数据验证成功";
        public static final String SEATVERYFAIL = "桌码数据验证失败";
        
        public static final String MENUSVERIFIED = "菜单验证";
        public static final String MENUSVERYSUC = "菜单验证成功";
        public static final String MENUSVERYFAIL = "菜单验证失败";

	 
	}
	 
	
    public class QRCode {

        // 定义返回信息
    	public static final String MODULE = "扫码模块_";
        public static final String CODE_SCANSEAT = "1";
        public static final String MESSAGESUC_SCANSEAT = "扫桌码成功";
        public static final String MESSAGEERROR_SCANSEAT = "扫桌码失败";
        
        public static final String CODE_SCANSHOP = "1";
        public static final String SCANBEGIN = "扫码开始";
        public static final String CASESUC = "_200_成功";
        public static final String MESSAGE_SCANSHOP = "扫店码成功";
        public static final String MESSAGESUC_SCANCODE = "扫码成功";
        public static final String SCANFAIL = "扫码失败";
 
    }



    /**
     * controller 对应 message 
     */
    public class Cart {
    	
    	public static final String MODULE = "购物车模块_";
    	public static final String CASESUC = "_200_成功";
    	public static final String CASEFAIL = " 加菜失败, 原因是：";
    	
    	public static final String SOLDOUTBEGIN = "商品沽清用例开始执行";
    	public static final String SOLDOUTSUC = "商品沽清用例成功执行完毕";
    	public static final String SOLDOUTFAIL = "商品沽清用例执行失败";
    	
    	
    	public static final String ADDMENUBEGIN = "商品添加用例开始执行";
    	public static final String ADDMENUSUC = "商品添加用例成功执行完毕";
    	public static final String ADDMENUFAIL = "商品添加用例执行失败";
       
    }

    
    public class Order {   
    	
    	public static final String MODELE = "订单模块_";
    	public static final String CASESUC = "_200_成功";
    	public static final String CASEFAIL = "_失败, 详情：";
    	
    	public static final String PLACEORDER = "下单动作动作 ";
    	public static final String REFRESHORDER = "获取订单列表动作 ";
    	
    	public static final String ORDERNOTCHANGE = "订单未发生变化";
        public static final String ORDERCHANGE = "订单发生了变化";
         
        public static final String GETORDERLISTFAIL = "获取订单列表失败";
                  
        public static final String ORDERNOPAYBEGIN = "单用户下单（未支付）用例开始";
     	public static final String ORDERNOPAYSUC = "单用户下单（未支付）用例成功执行完毕";
     	public static final String ORDERNOPAYFAIL = "单用户下单（未支付）用例执行失败";
     	
     	public static final String WAITORDERINVALID = "等待之前的订单失效";
        public static final String WAITFINISH = "等待动作已完成";
     	
    }

    
    
    public class Pay {
    	
    	public static final String MODULE = "支付模块_";
    	public static final String CASESUC = "_200_成功";
    	public static final String CASEFAIL = " 支付失败, 原因是：";
    	
    	public static final String PAYACTION = "支付动作 ";
    	
    	public static final String REFRESHTOGETPAYDETAIL = "//////////////////  开始刷新订单页面        /////////////////////";
        public static final String REFRESHSUC = "//////////////  订单页面刷新成功      //////////////////////";
         
        public static final String PAYPROCEDURE = "/////////////////  开始支付流程      ////////////////////////";
        public static final String PAYPROCEDURESUC = "/////////////////  支付流程成功      ////////////////////////";
         
        public static final String PAYBEGIN = "单用户下单（包含支付）用例开始";
      	public static final String PAYSUC = "单用户下单（包含支付）用例成功执行完毕";
      	public static final String PAYFAIL = "单用户下单（包含支付）用例执行失败";
      	
      	
      	public static final String GETTRADEBILLERROR = "获取账单失败";
        public static final String GETPAYTYPEERROR = "获取支付类型失败";
       
       
    }


    public class Menu {
    	
    	// 定义返回信息
    	public static final String MENU_MODULE = "商品列表模块_";      

        public static final String MENULISTBEGIN = "商品列表验证开始";
        public static final String CASESUC = "_200_成功";
        public static final String MENULISTFAIL = "商品列表验证失败";
        public static final String MENULISTSUC = "商品列表用例成功执行完毕";
 
        
    	public static final String MODULE = "菜单验证模块_";
    	public static final String MENUVERIFY = "菜单验证动作 ";
    	
    	public static final String UIDVERIFY = "用户 ID (uid) 校验失败";
        public static final String PARENTMENU = "接口返回的套菜中父菜的 menuId 在预期的数据中不存在";
         
        public static final String PARENTMENUKIND = "父菜数据中的 kind 验证失败";
        public static final String PARENTMENUNUM = "父菜数据中的 num 验证失败";
         
        public static final String CHILDMENU = "接口返回的子菜的 menuId 在预期的数据中不存在";
        public static final String CHILDMENUKIND = "子菜数据中的 kind 验证失败";
      	public static final String CHILDMENUNUM = "子菜数据中的 num 验证失败";
      	
      	public static final String NORMALMENU = "接口返回的普通菜的 menuId 在预期数据中不存在";
      	public static final String NORMALMENUKIND = "普通菜的 kind 验证失败";
      	public static final String NORMALMENUNUM = "普通菜的 num 验证失败";
      	
      	
      	public static final String MENUSSHOPCODE = "店码获取菜单列表";
    	
    }
 
    
    
    public class Boss {
    	
    	public static final String MODULE = "掌柜模块_";
    	
    	public static final String OPEN = " 开启";
        public static final String CLOSE = " 关闭";
         
        public static final String SAVEFORCEMENU = "保存必选商品设置";
        public static final String REMOVEFORCEMENU = "删除必选商品设置";
         
        public static final String QUERYFORCEMENULIST = "获取必选商品列表";
        public static final String PREPAYSWITCH = "操作预付款配置开关";
        
        public static final String INFRASWITCH = "顾客端---基础设置开关";
      	
    	
    }
    
    
    
    
    public class CashDesk {
    	
    	public static final String MODULE = "收银模块_";
    	
    	public static final String LOGINCASHDESK = "登陆收银机";
        public static final String LOGINCASHDESKSUC = "登陆收银机成功";       
        public static final String LOGINCASHDESKFAIL = "登陆收银机失败";
        
        
        public static final String SENDHEARTBEAT = "收银机发送心跳";
        public static final String SENDHEARTBEATSUC = "收银机发送心跳成功";       
        public static final String SENDHEARTBEATFAIL = "收银机发送心跳失败";
              
        public static final String GETMESSAGELIST = "从 Dpush 获取消息列表";
        public static final String GETMESSAGELISTSUC = "从 Dpush 获取消息列表成功";       
        public static final String GETMESSAGELISTFAIL = "从 Dpush 获取消息列表失败";
        public static final String ERRORGETMESSAGE = "未获取到订单审核消息！";       
        public static final String SUCGETMESSAGE = "获取订单审核消息成功";
        
        
        public static final String WAITINGORDERVO = "构造 waitingOrderVo "; 
        public static final String WAITINGORDERVOSUC = "构造 waitingOrderVo 成功"; 
        public static final String WAITINGORDERVOFAIL = "构造 waitingOrderVo 失败"; 
        
        public static final String CHECKMSGEMPTY = "待审核消息 ID 为空 "; 
        public static final String CHECKMSGFORWAITING = "waitingOrderId 为空"; 
        
        
        public static final String TOTALPAYINFOVO = "上传订单信息 "; 
        public static final String TOTALPAYINFOVOSUC = "上传订单信息成功"; 
        public static final String TOTALPAYINFOVOFAIL = "上传订单信息 失败";
        
        
        public static final String UPDATEMESSAGE = "更新  Dpush 信息 "; 
        public static final String UPDATEMESSAGESUC = "更新  Dpush 信息成功"; 
        public static final String UPDATEMESSAGEFAIL = "更新  Dpush 信息 失败";
        
        
        public static final String  MENUBALANCE = "设置商品沽清 "; 
        public static final String  CLEANMENUBALANCE = "删除沽清商品"; 
        
        
        public static final String APPROVERORDER = "/////////////////  订单审核       /////////////////////"; 
        public static final String APPROVERORDERSUC = "///////////////  订单审核成功      ///////////////////"; 
        public static final String APPROVERORDERFAIL = "订单审核 失败";
        
        
        public static final String SETTLEORDER = "/////////////////   收银结账        //////////////////////"; 
        public static final String SETTLEORDERSUC = "////////////////   收银结账成功        ////////////////////"; 
        public static final String SETTLEORDERFAIL = "收银结账 失败";
        
        
        public static final String GETBILLINFO = "/////////////////  拉取账单 并更新       ///////////////////";
        public static final String GETBILLINFOSUC = "//////////////  拉取账单并更新成功  //////////////////";
        public static final String GETBILLINFOFAIL = "拉取账单失败";
        
        
        public static final String GETMESSAGELISTFOREXCEPTION = "从 Dpush 获取消息列表";
        public static final String GETMESSAGELISTSUCFOREXCEPTION = "从 Dpush 获取消息列表成功";       
        public static final String GETMESSAGELISTFAILFOREXCEPTION = "从 Dpush 获取消息列表失败";
       
    }
    
    
    /**
     * 用户信息
     */
    public class User{
    	
    	public static final String RECEIVEPROPERTYBEGIN = "接收配置文件开始";
    	public static final String RECEIVEPROPERTYSUC = "保存配置文件成功";
    	public static final String RECEIVEPROPERTYFAIL = "保存配置文件失败";
    }

    /**
     * 监控数据的参数
     */
    public class Monitor {
    	
        //设置服务请求超时监控
        public static final int SERVICE_TIMEOUT = 10000;
    }

    
    
    /**
     * 所有消息文本内容, 用于基本方法及模块方法的封装
     */
    public class Message {
    	
    	// QRCode
    	public static final String QRCODEUNIVERSAL = "扫码开始";
        public static final String QRCODESEAT = "扫桌码开始";
        public static final String QRCODESHOP = "扫店码开始";
        
        public static final String SHOPINIT = "店铺初始化";
        public static final String PERSONALINFO = "首页会员等级信息详情";
        
        public static final String USERINFO = "查找用户个人信息";
        public static final String SHAREFORSHOP = "店铺分享URL的信息（包括图片、文案）接口";
        
        // QRCode with integration
        public static final String SCANSEATCODE = "///////////////////   扫桌码流程开始            //////////////////////";
        public static final String SCANSEATCODESUC = "///////////////////   扫桌码流程成功            //////////////////////";
        
        public static final String SCANSHOPCODE = "///////////////////   扫桌码流程开始            //////////////////////";
        public static final String SCANSHOPCODESUC = "///////////////////   扫桌码流程成功            //////////////////////";
        
        public static final String SCANSEATCODEERROR = "扫桌码失败";
        public static final String SCANSHOPCODEERROR = "扫桌码失败";
        public static final String GETSHOPINITINFOERROR = "获取店铺初始化数据失败";
        
        public static final String GETMEMBERINFOERROR = "获取会员等级信息失败";
        public static final String GETUSERINFOERROR = "获取会员等级信息失败";
        
        public static final String GETSHAREOFSHOPINFOERROR = "获取店铺分享URL的信息失败";
        
        
        // Cart
        public static final String CREATECART = "创建购物车";
        public static final String LISTCARTDATA = "获取虚拟购物车数据及用餐人数";
        
        public static final String PRIVILEGETITLE = "优惠载体文字显示栏标题";
        public static final String LISTMENUS = "获取店铺菜单列表";
        public static final String LISTMENUSPEC = "获取菜品详情";
        public static final String LISTNORMALSPEC = "获取普通菜详情";
        
        public static final String POPUPMESSAGE = "获取弹窗消息";
        public static final String CARTPRIVILEGE = "购物车页面展示可用优惠信息";
        
        public static final String MODIFYPEOPLE = "修改人数和备注信息";
        public static final String ADDREQUIREDITEM = "添加必选商品";
        
        public static final String ADDDISHToCARTS = "添加菜品到个人购物车";
        public static final String CLEARCART = "删除一起点购物车的所有菜, 即清空购物车, 该接口针对扫桌码 ";
        
        public static final String GETUSERCART = "获取用户购物车的数据接口（不含菜单列表）, 获取购物车内菜品数据";
        
        
        // Cart with integration
        public static final String CLICKTOENTER = "//////////////    点击进入开始(创建购物车，显示菜单列表)   ////////////////////";
        public static final String CLICKTOENTERSUC = "//////////////    点击进入成功(创建购物车，显示菜单列表)   ////////////////////";
        
        public static final String ADDMENUTOCART = "//////////////    加菜到购物车(创建购物车，修改人数，添加必选菜，加普通菜或者套菜)   ////////////////////";
        public static final String ADDMENUTOCARTSUC = "//////////////    加菜到购物车成功成功(创建购物车，修改人数，添加必选菜，加普通菜或者套菜)   ////////////////////";
        
        
        // Order
        public static final String SUBMITORDER = "提交订单";
        public static final String GETORDER = "获取订单列表";
        
        public static final String CHECKORDERCHANGE = "检查订单变化  (可能多人提交订单)";    
        public static final String GETSHOPORDERLIST = "获取自己在本店的订单列表";   
        public static final String ACTIVITYSWITCH = "获取点赞活动开关";
        
        public static final String TRADEBILL = "获取优惠方案及支付订单信息";
        public static final String PAYTYPE = "获取支付类型并进行支付";
        public static final String CARDPAY = "会员卡支付";
        
        
        public static final String PLACEORDER = "//////////////    下订单开始(提交订单，获取订单，订单检查)   ////////////////////";
        public static final String PLACEORDERSUC = "//////////////    下订单成功(提交订单，获取订单，订单检查)   ////////////////////";
        
        public static final String GETCARTDATAERROR = "获取购物车数据失败";
        public static final String SUBMITORDERERROR = "提交订单失败";
        
        
        // Card
        public static final String GETCARDSLIST = "获取我在本店的会员卡列表";
        public static final String GETCARDDETAIL = "会员卡详情查询";
        public static final String GETCARDSPAYMENT = "会员卡支付";
        public static final String GETALLMYCARD = "获取包含我所有会员卡的列表";
        public static final String GETALLMYCOUPON = "获取包含我所有优惠券的列表";
        
        
        // Privilege
        public static final String GETALLPRIVILEGE = "获取店铺所有优惠方案列表";
        
        
        // Takeout menus
        public static final String GETTAKEOUTMENULIST = "获取店铺菜单列表";
        
        // HTTP 
        public static final String POSTBODY = "the httpbody is : ";
        
        
        // message
        public static final String CASESUC = "_200_成功";
    	public static final String CASEFAIL = "_失败, 详情：";
    	
    	
    	
    	// 页面拆分
    	public static final String MEMBERSHIPCARD = "页面拆分---本店会员卡";
    	public static final String NEARBYSHOP = "页面拆分---品牌拆分";
    	public static final String SEATTYPE = "页面拆分---排队拆分";
    	
    	
    	// 积分商城
    	public static final String INTEGRALHOME = "积分商城首页";
    	public static final String USERCENTER = "二维火会员中心";
    	
    	
    	
    	// 店铺相关信息
    	public static final String GETSTATE = "获取店铺相关信息";
    	public static final String BASEINFO = "获取店铺基本信息";

    }

    
    public class Error {
    	
    	// Common
    	public static final String GETTOKENERROR = "在该环境下对应的 entityId 不存在，请确认 entityId 是否与该环境相匹配";
    	
    	public static final String REQUESTERROR = "请求失败";
    	public static final String REQUESTSUC = "请求成功";
    	
    	// Cart
    	public static final String ADDMENUTOCARTERROR = "添加菜品到个人购物车接口返回 code 验证失败";

    	
    	// HTTP 
    	public static final String VERIFIEDERROR = "response 返回的 code 校验失败";
    	
    	
    	// verify
    	public static final String EXPECTED = "期望是 ";
    	public static final String ACTUAL = "实际是 ";
    	
    	
    	// 会员卡
    	public static final String MEMBERNOTEQUAL = "会员卡数目不一致";
    	public static final String ENTITYIDNOTEQUAL = "店铺 ID 不一致";
    	public static final String BALANCENOTEQUAL = "会员卡余额 不一致";
    	
    	public static final String DEGREENOTEQUAL = " degree 不一致";
    	public static final String MEMBERNONOTEQUAL = "会员卡编号 不一致";
    	public static final String KINDCARDIDNOTEQUAL = "kindCardId 不一致";
    	
    	public static final String COUPONNOTEQUAL = "优惠券数目不一致";
    	public static final String COUPONTYPENOTEQUAL = "优惠券类型不一致";
    	public static final String ENTITYNAMENOTEQUAL = "店铺名称不一致";
    	public static final String DISCOUNTNOTEQUAL = "折扣不一致";
    	
    	// takeout
    	public static final String MENUNOTEQUAL = "菜品数目不一致";
    	public static final String KINDMENUIDNOTEQUAL = "kindMenuId 不吻合";
    	public static final String NAMENOTEQUAL = "菜名不吻合";
    	public static final String PRICENOTEQUAL = "菜品价格不吻合";

    	// order
    	public static final String PAYSTATUSNOTEQUAL = "支付状态不吻合";
    	public static final String ORDERSTATUSNOTEQUAL = "订单状态不吻合";
    	public static final String TOTALFEENOTEQUAL = "支付费用不吻合";
    	
    	
    	// page split
    	public static final String BRANDNAMENOTEQUAL = "店名未匹配";
    	public static final String BRANDSTATUSNOTEQUAL = "店铺状态未匹配";
    	public static final String ORDERCOUNTNOTEQUAL = "订单数目未匹配";
    	public static final String SIGNNOTEQUAL = "签名未匹配";
    	
    	public static final String MAXNOTEQUAL = "最多人数 未匹配";
    	public static final String MINNOTEQUAL = " 最少人数 未匹配";
    	public static final String QUEUELENGTHNOTEQUAL = " 排队长度 未匹配";
    	
    	
    	public static final String TYPENOTEQUAL = "type 不吻合";
    	public static final String NEEDINTEGRALNOTEQUAL = "所需积分不吻合";
    }
    
    public class Exception {
        
    	//基础异常
        public static final String BASEEXCEPTION = "请求异常";

        //多线程执行异常
        public static final String THREADEXCEPTION = "请求异常";
        
    }
    
    
    /**
     * 默认属性名称
     */
    public class Default {

    }



}
