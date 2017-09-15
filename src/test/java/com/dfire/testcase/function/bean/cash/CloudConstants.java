package com.dfire.testcase.function.bean.cash;
/**
 * 任务系统常量.
 * @author <a href="mailto:rain999@gmail.com">黄晓峰</a>.
 * @version $Revision$.
 */
public interface CloudConstants {

	/**
	 * 系统类型.
	 */
	public interface SystemType {
		/**
		 * 餐饮收银.
		 */
		public static final short CASH_DESK = 1;
		/**
		 * 点菜宝.
		 */
		public static final short ORDER_DESK = 2;
		/**
		 * 火小二/客户端.
		 */
		public static final short ORDER_PO_DESK = 3;

		/**
		 * 火排队.
		 */
		public static final short QUEUE_DESK = 11;
		/**
		 * 火叫号.
		 */
		public static final short QUEUE_CALL = 12;
		/**
		 * 火取号.
		 */
		public static final short QUEUE_GET = 13;
	}
	/**
	 * 业务子系统类型.
	 */
	public interface SubSystemType {
		/**
		 * 火收银HD.
		 */
		public static final int CASH_DESK_ANDROID_HD = 101;
		/**
		 * 火点菜宝HD.
		 */
		public static final int ORDER_DESK_ANDROID_HD = 201;
		/**
		 * 安卓火点菜宝.
		 */
		public static final int ORDER_DESK_ANDROID = 202;
		/**
		 * iphone火点菜宝.
		 */
		public static final int ORDER_DESK_IPHONE = 203;
		/**
		 * 安卓火小二.
		 */
		public static final int ORDER_PO_DESK_ANDROID = 301;
		/**
		 * iphone火小二.
		 */
		public static final int ORDER_PO_DESK_IPHONE = 302;
		/**
		 * 微信火小二.
		 */
		public static final int ORDER_PO_DESK_WEIXIN = 303;
		/**
		 * 安卓火排队HD.
		 */
		public static final int QUEUE_DESK_ANDROID_HD = 111;
		/**
		 * 安卓火叫号HD.
		 */
		public static final int QUEUE_CALL_ANDROID_HD = 121;
		/**
		 * 安卓火取号.
		 */
		public static final int QUEUE_GET_ANDROID_HD = 131;
	}
	/**
	 * 任务状态.
	 */
	public interface TaskStatus {
		/**
		 * 未处理.
		 */
		public static final Short UN_PROCESS = 1;

		/**
		 * 处理中.
		 */
		public static final Short IN_PROCESS = 2;

		/**
		 * 处理成功.
		 */
		public static final Short PROCESS_SUCCESS = 3;

		/**
		 * 处理失败.
		 */
		public static final Short PROCESS_FAIL = 4;

		/**
		 * 处理超时.
		 */
		public static final Short TIME_OUT = 5;
		/**
		 * 服务员处理完成，但是任务尚未终结，需要进一步处理.
		 */
		public static final Short PROCESSED_BY_WAITER = 6;

	}
	/**
	 * 业务类型.
	 */
	public interface Type {
		/**
		 *火小二扫单点菜.
		 */
		public static final int PO_ADD_INSTANCE_BY_SCAN_ORDER = 101;
		/**
		 *火小二扫桌点菜.
		 */
		public static final int PO_ADD_INSTANCE_BY_SCAN_SEAT = 102;
		/**
		 *火小二整单催菜.
		 */
		public static final int PO_URGE_ORDER = 103;
		/**
		 *火小二部分菜催菜.
		 */
		public static final int PO_URGE_INSTANCE = 104;
		/**
		 *火小二退菜申请.
		 */
		public static final int PO_CANCEL_INSTANCE = 105;
		/**
		 *火小二叫服务员，服务铃.
		 */
		public static final int PO_MESSAGE = 111;
		/**
		 *火小二叫服务员，人工结账.
		 */
		public static final int PO_ACCOUNT_BY_WAITER = 112;
		/**
		 *火小二支付申请，计算账单.
		 */
		public static final int PO_ACCOUNT = 121;
		/**
		 *火小二/淘点点网上支付.
		 */
		public static final int PO_NET_PAY = 122;
		/**
		 *火小二预订.
		 */
		public static final int PO_RESERVE = 131;
		/**
		 *火小二外卖.
		 */
		@Deprecated
		public static final int PO_TAKEOUT = 132;
		/**
		 *通知信息（发送给收银和点菜宝）.
		 */
		public static final int PO_NOTIFY_WAITER = 141;
		/**
		 *在线外卖下单
		 */
		public static final int PO_TAKEOUT_ONLINE = 401;
		/**
		 * 在线外卖取消
		 */
		public static final int PO_TAKEOUT_CANCEL_ONLINE = 403;
		/**
		 * 在线外卖配送更新
		 */
		public static final int PO_TAKEOUT_DELIVERY_ONLINE = 404;
		/**
		 * 在线外卖配送更新通知
		 */
		public static final int PO_TAKEOUT_DELIVER_SHOW = 405;
		 /**
		 * 在线外卖配送已送达
		 */
		public static final int PO_TAKEOUT_DELIVERIED = 406;
		/**
		 *本店小二外卖.
		 */
		public static final int PO_LOCAL_TAKEOUT = 501;
		/**
		 *点菜宝扫单加菜.
		 */
		public static final int WAITER_ADD_INSTANCE_BY_ORDER = 1001;
		/**
		 *点菜宝扫桌加菜.
		 */
		public static final int WAITER_ADD_INSTANCE_BY_SCAN_SEAT = 1002;
		/**
		 *整单催菜.
		 */
		public static final int WAITER_URGE_ORDER = 1003;
		/**
		 *催菜.
		 */
		public static final int WAITER_URGE_INSTANCE = 1004;
		/**
		 *改价.
		 */
		public static final int WAITER_EDIT_PRICE = 1005;
		/**
		 *退菜.
		 */
		public static final int WAITER_CANCEL_INSTANCE = 1006;
		/**
		 *改重量.
		 */
		public static final int WAITER_EDIT_WEIGHT = 1007;
		/**
		 *赠菜.
		 */
		public static final int WAITER_GIVE_INSTANCE = 1008;
		/**
		 *划菜.
		 */
		public static final int WAITER_DRAW_INSTANCE = 1009;
		/**
		 *开新单点菜.
		 */
		public static final int WAITER_NEW_ORDER = 1010;
		/**
		 *改单.
		 */
		public static final int WAITER_EDIT_ORDER = 1011;
		/**
		 *撤单.
		 */
		public static final int WAITER_CANCEL_ORDER = 1012;
		/**
		 *打折.
		 */
		public static final int WAITER_ACCOUNT = 1021;
		/**
		 *支付.
		 */
		public static final int WAITER_PAY = 1022;
		/**
		 *结账完毕.
		 */
		public static final int WAITER_END_PAY = 1023;
		/**
		 *打印客户联.
		 */
		public static final int WAITER_PRINT_CUSTOMER = 1031;
		/**
		 *打印点菜单.
		 */
		public static final int WAITER_PRINT_ORDER = 1032;
		/**
		 *打印财务联.
		 */
		public static final int WAITER_PRINT_FINANCE = 1033;
		/**
		 *打印测试.
		 */
		public static final int WAITER_PRINT_TEST = 1034;
		/**
		 *沽清.
		 */
		public static final int WAITER_BALANCE = 1041;
		/**
		 *点菜宝同意火小二点菜.
		 */
		public static final int WAITER_PO_ADD_INSTANCE_BY_ORDER = 1101;
		/**
		 *同意火小二扫桌点菜.
		 */
		public static final int WAITER_PO_ADD_INSTANCE_BY_SEAT = 1102;
		/**
		 *同意火小二整单催菜.
		 */
		public static final int WAITER_PO_URGE_ORDER = 1103;
		/**
		 *同意火小二部分菜催菜.
		 */
		public static final int WAITER_PO_URGE_INSTANCE = 1104;
		/**
		 *同意火小二退菜申请.
		 */
		public static final int WAITER_PO_CANCEL_INSTANCE = 1105;
		/**
		 * 付预款
		 */
		public static final int CUSTOMER_PAY_WAITING_ORDER = 123;
		/**
		 * 收银处理完预付款信息后发送给火服务生
		 */
		public static final int CUSTOMER_PREPAY_TO_WAITER = 142;
		/**
		 * 打印消息单
		 */
		public static final int WAITER_PRINT_MESSAGE_MENU = 1042;

//		/**
//		 * 小二微信需要开发票，已经废弃
//		 */
//		public static final int OPEN_INVOICE = 4001;
//		/**
//		 * 开发票结果，没使用
//		 */
//		public static final int RESULT_INVOICE = 4002;
		/**
		 * 小二微信需要开发票（电子发票和纸制发票）
		*/
		public static final int OPEN_E_INVOICE = 4003;

		public static final int INVOICE = 4004;
		/**
		 * 电子发票短信发送失败
		 */
		public static final int E_INVOICE_SM_FAIL =4005;
	}

}
