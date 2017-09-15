package com.dfire.qa.meal.utils;

import java.util.ArrayList;
import java.util.List;

import com.dfire.qa.meal.enums.Privilege;
import com.dfire.qa.meal.logger.LoggerMarkers;
import com.dfire.qa.meal.logger.MealLoggerFactory;
import com.mysql.fabric.xmlrpc.base.Array;


/**
 * if service run in tomcat in real server use url perfix "weixin-meal/"
 * for example "weixin-meal/shop/v1/get_state"
 * @author ljw
 *
 */
public class PathForHTTP {


	// for shop
	private static List<String> pathForLoadState;
	private static List<String> pathForBaseInfo;
	private static List<String> pathForGetPersonInfo;
	
	// for card
	private static List<String> pathForApplyCard;
	private static List<String> pathForCardList;
	private static List<String> pathForDeleteCard;
	
	// for cart
	private static List<String> pathForGetCartCount;
	private static List<String> pathForCreateOwnCart;
	private static List<String> pathForGetCartData;
	
	private static List<String> pathForModifyCart;
	private static List<String> pathForClearCart;
	private static List<String> pathForClearOwnCart;
	
	private static List<String> pathForModifyInfo;
	private static List<String> pathForGetRecommendMenuList;
	private static List<String> pathForGetRecommendMenuById;
	
	private static List<String> pathForForceMenu;
	private static List<String> pathForAddToCarts;
	
	// for coupon
	private static List<String> pathForGetCouponList;
	
	
	// for auth
	private static List<String> pathForOAuth;
	private static List<String> pathForOAuthConsumerCode;
	private static List<String> pathForOAuthEntityId;
	
	private static List<String> pathForOAuthMenuCode;
	private static List<String> pathForOAuthCallback;
	private static List<String> pathForaliShop;
	
	
	// for pre cart
	private static List<String> pathForPreCart;
	private static List<String> pathForGetMenus;
	private static List<String> pathForGetUserCart;
	
	private static List<String> pathForImportUserCart;	
	private static List<String> pathForModifyPeopleAndMemo;
	
	// for pre pay
	private static List<String> pathForGetPrePayBill;
	private static List<String> pathForConfirmPrepayOrder;
	private static List<String> pathForDeletePrepayOrder;
	
	private static List<String> pathForGetPrepayOrder;
	private static List<String> pathForGetBillInfo;
	private static List<String> pathForLockPayWaitingOrder;
	
	
	// get prepay bill
	private static List<String> pathForGetPrepayBill;
	private static List<String> pathForConfirmPrePay;
	
	// for order
	private static List<String> pathForLockOrder;
	private static List<String> pathForUnLockPayOrder;
	private static List<String> pathForCheckOrder;
	
	private static List<String> pathForCheckOrderChange;
	private static List<String> pathForGetShopOrderList;
	private static List<String> pathForConfirmOrder;
	private static List<String> pathForModifyMemo;
	
	private static List<String> pathForGetOrder;
	private static List<String> pathForGetKoubeiOrder;
	private static List<String> pathForGetOrderHistory;
	
	private static List<String> pathForReIssuedPreOrder;
	
	
	// for privilege
	private static List<String> pathForShowTitle;
	private static List<String> pathForListCartsPrivilege;
	
	private static List<String> pathForGetPopupMessage;
	private static List<String> pathForGetCartPrivilege;
	
	private static List<String> pathForGetGiveCoupon;
	
	// for menus
	private static List<String> pathForListMenus;
	private static List<String> pathForListMenuSpec;
	private static List<String> pathForListNormalSpec;
	private static List<String> pathForRecommendMenu;
	
	// for pay 
	private static List<String> pathForGetNormalTradeBill;
	private static List<String> pathForGetTradeBill;
	private static List<String> pathForGetTradeBillV2;
	private static List<String> pathForGetPayType;
	
	// for evaluation
	private static List<String> pathForPlayComments;
	
	// for activity 
	private static List<String> pathForGetActivitySwitch;
	private static List<String> pathForGetOrderComments;
	
	
	// for token 
	private static List<String> pathForGetToken;
	private static List<String> pathForCardPay;
	private static List<String> pathForGetShopTax;
	
	
	// for user
	private static List<String> pathForGetUserInfo;
	
	
	// for cash
	private static List<String> pathForLoginCashDesk;
	private static List<String> pathForSendHeartBeat;
	private static List<String> pathForGetMessageList;
	
	private static List<String> pathForGetWaitingOrder;
	private static List<String> pathForUploadTotalPay;
	private static List<String> pathForUpdateMessage;
	
	
	private static List<String> pathForMenuBalance;
	private static List<String> pathForCleanMenuBalance;
	
	
	// for boss
	private static List<String> pathForSaveForceMenu;
	private static List<String> pathForRemoveForceMenu;
	private static List<String> pathForGetForceMenuList;
	private static List<String> pathForGetAllForceMenuList;
	
	
	private static List<String> pathForSetEntityConfig;
	private static List<String> pathForSetAutoGetCoupon;
	
	private static List<String> pathForPrePaySwitch;
	private static List<String> pathForMenuConfigUpdate;
	
	
	private static List<String> pathForBaseWithClientSwitch;
	private static List<String> pathForListBossConfig;
	private static List<String> pathForBatchSetting;
	private static List<String> pathForTakeOutConfig;
	
	private static List<String> pathForTakeOutTime;
	private static List<String> pathForGetTakeOutConfig;
	private static List<String> pathForDeleteTakeOutConfig;
	private static List<String> pathForTakeOutDeliveryMan;
	
	private static List<String> pathForGetTakeOutDeliveryMan;
	private static List<String> pathForDeleteTakeOutDeliveryMan;
	
	
	
	private static List<String> pathForImageUpload;
	private static List<String> pathForAudioUpload;
	private static List<String> pathForImageSetConfig;
	private static List<String> pathForQueryImageSet;
	
	private static List<String> pathForColorStyleConfig;
	private static List<String> pathForMenuRepeatRemaindConfig;
	private static List<String> pathForMenuRepeatAddMenuConfig;
	
	private static List<String> pathForMenuRepeatDeleteMenuConfig;
	private static List<String> pathForSaveCouponConfig;
	private static List<String> pathForListCoupon;
	private static List<String> pathForDeleteCouponConfig;
	
	
	private static List<String> pathForSavePromotion;
	private static List<String> pathForGetPromotionList;
	private static List<String> pathForDeletePromotion;
	
	private static List<String> pathForBirthPrivilege;
	private static List<String> pathForMemoryPrivilege;
	private static List<String> pathForCouponPrivilege;
	private static List<String> pathForCustomPrivilege;
	private static List<String> pathForDeletePrivilege;
	private static List<String> pathForDeleteCustomPrivilege;
	
	private static List<String> pathForAddCustomPrivilege;
	private static List<String> pathForAllDeleteCustomPrivilege;
	
	private static List<String> pathForGetAllPrivilegeDetail;
	private static List<String> pathForGetAllCardPrivilegeDetail;
	
	private static List<String> pathForDeleteCardPrivilege;
	private static List<String> pathForAddCardPrivilege;
	
	private static List<String> pathForCardPrivilege;
	private static List<String> pathForGetCustomPrivilegeList;
	
	private static List<String> pathForQueryKindCards;
	private static List<String> pathForSaveRechargeRule;
	private static List<String> pathForDeleteAllRecharge;
	
	private static List<String> pathForSelfRecharge;
	private static List<String> pathForSaveGift;
	private static List<String> pathForGetGiftList;
	
	private static List<String> pathForDeleteAllGift;
	private static List<String> pathForSaveGiftConvert;
	private static List<String> pathForGetGiftConvertList;
	
	private static List<String> pathForUpdateMenuLabel;
	private static List<String> pathForSaveEntityConfig;
	private static List<String> pathForQueryAllConfig;
	
	private static List<String> pathForSavePlanConfig;
	private static List<String> pathForSaveTemplateConfig;
	private static List<String> pathForQueryMenuTemplate;
	
	private static List<String> pathForModifyMenuTemplate;
	
	
	// 一元购---刮刮乐
	private static List<String> pathForGetActivityDetail;
	private static List<String> pathForGetActivityList;
	private static List<String> pathForQueryCommonMenu;
	private static List<String> pathForSaveActivity;
	
	

	
	// 会员卡
	private static List<String> pathForGetCardsList;
	private static List<String> pathForGetCardDetail;
	private static List<String> pathForGetCardsPayment;
	private static List<String> pathForGetAllMyCard;
	private static List<String> pathForGetAllMyCoupon;
	
	
	// 本店优惠
	private static List<String> pathForGetPrivilegeList;
	
	
	// 外卖
	private static List<String> pathForGetTakeoutMenuList;
	
	
	// 品牌拆分
	private static List<String> pathForGetNearbyShop;
	private static List<String> pathForGetSeatType;

	
	// 积分商城
	private static List<String> pathForGetIntegralHome;
	
	
	// 个人中心
	private static List<String> pathForGetMyDashboard;
	private static List<String> pathForGetUserCenter;
	
	
	static{		
		
//////////////////////////////////// shop  ///////////////////////////////////////////
		// for load state
		pathForLoadState = new ArrayList<String>();
		pathForLoadState.add("shop/v1");
		pathForLoadState.add("get_state");
		
		// for base info
		pathForBaseInfo = new ArrayList<String>();
		pathForBaseInfo.add("shop/v1");
		
		
		// for get person info
		pathForGetPersonInfo = new ArrayList<String>();
		pathForGetPersonInfo.add("shop_member/v1/get_person_info");

/////////////////////////////////// card /////////////////////////////////////////////
		// for apply card
		pathForApplyCard = new ArrayList<String>();
		pathForApplyCard.add("cards/v1");
		pathForApplyCard.add("apply");

		// for card list
		pathForCardList = new ArrayList<String>();
		pathForCardList.add("cards/v1");
		pathForCardList.add("list");
		
		// for delete card
		pathForDeleteCard = new ArrayList<String>();
		pathForDeleteCard.add("cards/v1");
		pathForDeleteCard.add("delete");
		
////////////////////////////////// cart  //////////////////////////////////////////////	
		
		// for get cart count
		pathForGetCartCount = new ArrayList<String>();
		pathForGetCartCount.add("carts/v1");
		pathForGetCartCount.add("count");
		
		// for create own cart
		pathForCreateOwnCart = new ArrayList<String>();
		pathForCreateOwnCart.add("carts/v1");
		pathForCreateOwnCart.add("create");
		
		// for create own cart
		pathForGetCartData = new ArrayList<String>();
		pathForGetCartData.add("carts/v1");
		pathForGetCartData.add("list");
		
		// for modify cart
		pathForModifyCart = new ArrayList<String>();
		pathForModifyCart.add("carts/v1");
		pathForModifyCart.add("modify");  // async_modify  
		
		// for clear cart
		pathForClearCart = new ArrayList<String>();
		pathForClearCart.add("carts/v1");
		pathForClearCart.add("clear");
		
		// for clear cart
		pathForClearOwnCart = new ArrayList<String>();
		pathForClearOwnCart.add("carts/v1");
		pathForClearOwnCart.add("clear_by_id");
		
		// for modify memo and people
		pathForModifyInfo = new ArrayList<String>();
		pathForModifyInfo.add("carts/v1");
		pathForModifyInfo.add("modify_people_memo");
		
		// for get recommend menu list
		pathForGetRecommendMenuList = new ArrayList<String>();
		pathForGetRecommendMenuList.add("carts/v1");    
		pathForGetRecommendMenuList.add("get_recommend_menu_list");
				
		// for get recommend menu by id
		pathForGetRecommendMenuById = new ArrayList<String>();
		pathForGetRecommendMenuById.add("carts/v1");
		pathForGetRecommendMenuById.add("get_recommend_menu_by_id");
		
		
		// for get recommend menu by id
		pathForForceMenu = new ArrayList<String>();
		pathForForceMenu.add("carts/v1");
		pathForForceMenu.add("force_menu");
				
		
		// for get recommend menu by id
		pathForAddToCarts = new ArrayList<String>();
		pathForAddToCarts.add("carts/v1");
		pathForAddToCarts.add("modify_own");
		
/////////////////////////////////// coupon  ////////////////////////////////////////////
		
		// for get coupon list
		pathForGetCouponList = new ArrayList<String>();
		pathForGetCouponList.add("coupons/v1");
		pathForGetCouponList.add("list");
		
		
////////////////////////////////// OAuth   //////////////////////////////////////////////
		
		// for oauth
		pathForOAuth = new ArrayList<String>();
		pathForOAuth.add("s");
		
		// for oauth consumer code
		pathForOAuthConsumerCode = new ArrayList<String>();
		pathForOAuthConsumerCode.add("g");
		
		
		// for oauth entityId
		pathForOAuthEntityId = new ArrayList<String>();
		pathForOAuthEntityId.add("s");
		
		// for oauth menu code
		pathForOAuthMenuCode = new ArrayList<String>();
		pathForOAuthMenuCode.add("m");
		
		// for oauth call back
		pathForOAuthCallback = new ArrayList<String>();
		pathForOAuthCallback.add("oauth/callback");
		
		// for order dishes in aliShop
		pathForaliShop = new ArrayList<String>();
		pathForaliShop.add("koubeishop/callback");
		

////////////////////////////////// pre cart  //////////////////////////////////////////////
		
		// for order dishes in aliShop
		pathForPreCart = new ArrayList<String>();
		pathForPreCart.add("pre_carts/v1/modify");
		
		// for get menus
		pathForGetMenus = new ArrayList<String>();
		pathForGetMenus.add("pre_carts/v1/get_menus");
				
		
		// for import user cart
		pathForImportUserCart = new ArrayList<String>();
		pathForImportUserCart.add("pre_carts/v1/import_cart");
		
		
		// for modify people and memo
		pathForModifyPeopleAndMemo = new ArrayList<String>();
		pathForModifyPeopleAndMemo.add("pre_carts/v1/modify_people_memo");
		
		// for get user cart
		pathForGetUserCart = new ArrayList<String>();
		pathForGetUserCart.add("pre_carts/v1/get_user_cart");
		
//////////////////////////////////pre cart  //////////////////////////////////////////////
		
		// for get prePay bill
		pathForGetPrePayBill = new ArrayList<String>();
		pathForGetPrePayBill.add("prepay/v1/get_prepay_bill");
		
		// for confirm Prepay Order
		pathForConfirmPrepayOrder = new ArrayList<String>();
		pathForConfirmPrepayOrder.add("prepay/v1/get_prepay_bill");
				
		// for delete Prepay Order
		pathForDeletePrepayOrder = new ArrayList<String>();
		pathForDeletePrepayOrder.add("prepay/v1/delete/prepay_order");

		
		// for get Prepay Order
		pathForGetPrepayOrder = new ArrayList<String>();
		pathForGetPrepayOrder.add("prepay/v1/get_prepay_order");
		
		// for get bill info
		pathForGetBillInfo = new ArrayList<String>();
		pathForGetBillInfo.add("prepay/v1/get_bill_info");
		
		// for get bill info
		pathForLockPayWaitingOrder = new ArrayList<String>();
		pathForLockPayWaitingOrder.add("prepay/v1/lock_prepay_order");
		
////////////////////////////////// query pay  ////////////////////////////////////////////
		
		// for get prepay bill
		pathForGetPrepayBill = new ArrayList<String>();
		pathForGetPrepayBill.add("query_pay/bill");

		
		pathForGetShopTax = new ArrayList<String>();
		pathForGetShopTax.add("orders/v1/get_query_shop_tax");

//////////////////////////////////query pay  ////////////////////////////////////////////

		// for lock order
		pathForLockOrder = new ArrayList<String>();
		pathForLockOrder.add("orders/v1/lock_pay_order");
		
		
		// for unlock pay order
		pathForUnLockPayOrder = new ArrayList<String>();
		pathForUnLockPayOrder.add("orders/v1/unlock_pay_order");


		// for check order
		pathForCheckOrder = new ArrayList<String>();
		pathForCheckOrder.add("orders/v1/confirm_check");
		
		
		// for check order change
		pathForCheckOrderChange = new ArrayList<String>();
		pathForCheckOrderChange.add("orders/v1/check_order_change");
		
		
		// for check order change
		pathForGetShopOrderList = new ArrayList<String>();
		pathForGetShopOrderList.add("order/v1/get_shop_orders_list");
		
		
		
		// for confirm order 
		pathForConfirmOrder = new ArrayList<String>();
		pathForConfirmOrder.add("orders/v1/confirm");
		
		// for modify memo 
		pathForModifyMemo = new ArrayList<String>();
		pathForModifyMemo.add("orders/v1/modify_memo");
		
		/**
		 * old url : orders/v1/get_order
		 */
		pathForGetOrder = new ArrayList<String>();
		pathForGetOrder.add("order/get/v2/get_ordered");
		
		// for get koubei order
		pathForGetKoubeiOrder = new ArrayList<String>();
		pathForGetKoubeiOrder.add("orders/v1/get_koubei_order");
		
		
		// for get order history
		pathForGetOrderHistory = new ArrayList<String>();
		pathForGetOrderHistory.add("orders/v1/get_history_order");
		
		
		// for get order history
		pathForReIssuedPreOrder = new ArrayList<String>();
		pathForReIssuedPreOrder.add("orders/v1/re_accept_order");
		
		
////////////////////////////////// for privilege  ////////////////////////////////////////////

		// for show privilege title
		pathForShowTitle = new ArrayList<String>();
		pathForShowTitle.add("privilege/v1/title");
		
		// for list privilege of carts
		pathForListCartsPrivilege = new ArrayList<String>();
		pathForListCartsPrivilege.add("privilege/v1/cart/list");
		
		
		// for get popup message
		pathForGetPopupMessage = new ArrayList<String>();
		pathForGetPopupMessage.add("privilege/v1/popup_content");
		
		
		// for get Cart Privilege
		pathForGetCartPrivilege = new ArrayList<String>();
		pathForGetCartPrivilege.add("privilege/v1/cart/list");
		
		
		// for get give coupon
		pathForGetGiveCoupon = new ArrayList<String>();
		pathForGetGiveCoupon.add("privilege/v1/get_give_coupon");
		
//////////////////////////////////for menus  ////////////////////////////////////////////

		// for list menus
		pathForListMenus = new ArrayList<String>();
		pathForListMenus.add("menus/v1/list");
		
		
		// for list menu spec
		pathForListMenuSpec = new ArrayList<String>();
		pathForListMenuSpec.add("menus/v1/menu_detail");
		
		
		// for list normal menu spec
		pathForListNormalSpec = new ArrayList<String>();
		pathForListNormalSpec.add("menus/v1/normal_detail");
				
		
		pathForRecommendMenu = new ArrayList<String>();
		pathForRecommendMenu.add("menus/v1/get_recommend_menus");
		
//////////////////////////////////for pay  ////////////////////////////////////////////

		pathForGetNormalTradeBill = new ArrayList<String>();
		pathForGetNormalTradeBill.add("bill/v1/get_normal_trade_bill");
		
		// get trade bill for v1
		pathForGetTradeBill = new ArrayList<String>();
		pathForGetTradeBill.add("pay/v1/get_trade_bill");
		
		// get trade bill for v2
		pathForGetTradeBillV2 = new ArrayList<String>();
		pathForGetTradeBillV2.add("pay/v2/get_trade_bill");
		
		// get pay type
		pathForGetPayType = new ArrayList<String>();
		pathForGetPayType.add("pay/v1/get_pay_type");

//////////////////////////////////for evaluation  ////////////////////////////////////////////

		// judge the orders if to be evaluated
		pathForPlayComments = new ArrayList<String>();
		pathForPlayComments.add("evaluation/v1/play/comments");


//////////////////////////////////for activity  ////////////////////////////////////////////

		// judge the orders if to be evaluated
		pathForGetActivitySwitch = new ArrayList<String>();
		pathForGetActivitySwitch.add("activity/v1/activity_switch");
		
		// judge the orders if to be commented
		pathForGetOrderComments = new ArrayList<String>();
		pathForGetOrderComments.add("evaluation/v1/play/comments");

		
//////////////////////////////////for activity  ////////////////////////////////////////////

		// confirm pre pay
		pathForConfirmPrePay = new ArrayList<String>();
		pathForConfirmPrePay.add("prepay/v1/confirm_prepay");

		
		
//////////////////////////////////for token  ////////////////////////////////////////////

		// confirm pre pay
		pathForGetToken = new ArrayList<String>();
		pathForGetToken.add("dev/get_token");
		
		
		pathForCardPay = new ArrayList<String>();
		pathForCardPay.add("pay/v1/card_pay");
		
		
//////////////////////////////////  for user  ////////////////////////////////////////////
		
		// for get user info
		pathForGetUserInfo = new ArrayList<String>();
		pathForGetUserInfo.add("users/v1/get_user_info");
	
		
		
		
//////////////////////////////////  for cash  ////////////////////////////////////////////
		
		// for login cashDesk
		pathForLoginCashDesk = new ArrayList<String>();
		pathForLoginCashDesk.add("cash-api/auth/v1/loginWithEncryptedPassword");
		
		
		// for send heart beat
		pathForSendHeartBeat = new ArrayList<String>();
		pathForSendHeartBeat.add("heart/new");
		
		
		// for get message list
		pathForGetMessageList = new ArrayList<String>();
		pathForGetMessageList.add("dpush-api/dpush/v1/get_message_list");
		
		
		// for get waiting order
		pathForGetWaitingOrder = new ArrayList<String>();
		pathForGetWaitingOrder.add("cash-api/getOrder/v1/get_waiting_order_2_cash");
		
		
		// for upload totalpay
		pathForUploadTotalPay = new ArrayList<String>();
		pathForUploadTotalPay.add("cash-api/cashierorder/v2/uploadOrder");
		
		
		// for update message
		pathForUpdateMessage = new ArrayList<String>();
		pathForUpdateMessage.add("dpush-api/dpush/v1/update_message");
		
		
		pathForMenuBalance = new ArrayList<String>();
		pathForMenuBalance.add("cash-api/menu_balance/v1/upload_menu_balance");
		
		
		pathForCleanMenuBalance = new ArrayList<String>();
		pathForCleanMenuBalance.add("cash-api/menu_balance/v1/clear_menu_balance");

	
		
		
///////////////////////////////////   for boss    //////////////////////////////////////////////	
		
		// 添加必选菜
		pathForSaveForceMenu = new ArrayList<String>();
		pathForSaveForceMenu.add("menu/v1/save_force_menu");
		
		
		// 移除必选菜
		pathForRemoveForceMenu = new ArrayList<String>();
		pathForRemoveForceMenu.add("menu/v1/remove_force_menu");
		
		// 获取必选商品列表
		pathForGetForceMenuList = new ArrayList<String>();
		pathForGetForceMenuList.add("menu/v1/query_force_menu_list");
		
		
		// 获取必选商品列表
		pathForGetAllForceMenuList = new ArrayList<String>();
		pathForGetAllForceMenuList.add("menu/v1/query_force_menu_all_list");
				
				
		
		// 批量设置店铺维度的配置
		pathForSetEntityConfig = new ArrayList<String>();
		pathForSetEntityConfig.add("boss/v1/batch_set_entity_config");
		
		
		// 设置自动领券开关
		pathForSetAutoGetCoupon = new ArrayList<String>();
		pathForSetAutoGetCoupon.add("promotion/v1/set_auto_send_and_coupon");
		
		
		// 设置预付款开关
		pathForPrePaySwitch = new ArrayList<String>();
		pathForPrePaySwitch.add("config_set/v1/save");
		
		
		// 设置商品基本属性
		pathForMenuConfigUpdate = new ArrayList<String>();
		pathForMenuConfigUpdate.add("menu/v1/save_or_update_menu");
		
		
		
		// 设置预付款开关
		pathForBaseWithClientSwitch = new ArrayList<String>();
		pathForBaseWithClientSwitch.add("config_set/v1/save");
		
		
		// 设置预付款开关
		pathForListBossConfig = new ArrayList<String>();
		pathForListBossConfig.add("config_set/v1/list");
		
		// 顾客端--基础设置--顾客端显示扫菜码点菜按钮
		pathForBatchSetting = new ArrayList<String>();
		pathForBatchSetting.add("boss/v1/batch_set_entity_config");
		
		
		// 顾客端--外卖设置--主接口
		pathForTakeOutConfig = new ArrayList<String>();
		pathForTakeOutConfig.add("takeout/v1/save_settings");
		
		
		// 顾客端--外卖设置--外卖配送时间
		pathForTakeOutTime = new ArrayList<String>();
		pathForTakeOutTime.add("takeout/v1/save_time");
		
		// 顾客端--外卖设置--获取外卖配送时间
		pathForGetTakeOutConfig = new ArrayList<String>();
		pathForGetTakeOutConfig.add("takeout/v1/get_times");
				
		
		// 顾客端--外卖设置--删除外卖配送时间		
		pathForDeleteTakeOutConfig = new ArrayList<String>();
		pathForDeleteTakeOutConfig.add("takeout/v1/remove_time");
		
		
		// 顾客端--外卖设置--外卖配送人员设置
		pathForTakeOutDeliveryMan = new ArrayList<String>();
		pathForTakeOutDeliveryMan.add("takeout/v1/save_delivery_man");
		
		// 顾客端--外卖设置--获取外卖配送人员设置
		pathForGetTakeOutDeliveryMan = new ArrayList<String>();
		pathForGetTakeOutDeliveryMan.add("takeout/v1/get_delivery_mans");
		
		
		// 顾客端--外卖设置--删除外卖配送人员设置
		pathForDeleteTakeOutDeliveryMan = new ArrayList<String>();
		pathForDeleteTakeOutDeliveryMan.add("takeout/v1/remove_delivery_man");
				
				
		// 顾客端--商品页首与页尾设置--图片上传
		pathForImageUpload = new ArrayList<String>();
		pathForImageUpload.add("boss/v1/image_upload");
		
		
		// 顾客端--商品页首与页尾设置--图片上传后进行设置保存
		pathForImageSetConfig = new ArrayList<String>();
		pathForImageSetConfig.add("integral-api/item/v1/apply_setting");
		
		
		// 顾客端--商品页首与页尾设置--图片上传后进行设置保存
		pathForQueryImageSet = new ArrayList<String>();
		pathForQueryImageSet.add("integral-api/item/v1/query_items");
				
				
		
		// 顾客端--个性化换肤--包括色调风格设置, 自定义背景, 自定义图标
		pathForColorStyleConfig = new ArrayList<String>();
		pathForColorStyleConfig.add("integral-api/skin/v1/apply_skin_conf");
		
		
		// 顾客端--顾客点餐重复提醒
		pathForMenuRepeatRemaindConfig = new ArrayList<String>();
		pathForMenuRepeatRemaindConfig.add("menu_repeat_warn/v1/modify_switch");
		
		
		// 顾客端--顾客点餐重复提醒---加菜
		pathForMenuRepeatAddMenuConfig = new ArrayList<String>();
		pathForMenuRepeatAddMenuConfig.add("menu_repeat_warn/v1/save_menus");
		
		
		
		// 顾客端--顾客点餐重复提醒---删除菜
		pathForMenuRepeatDeleteMenuConfig = new ArrayList<String>();
		pathForMenuRepeatDeleteMenuConfig.add("menu_repeat_warn/v1/delete_menu");
		
		
		// 会员--保存优惠券
		pathForSaveCouponConfig = new ArrayList<String>();
		pathForSaveCouponConfig.add("promotion/v1/save_coupon_promotion");
		
		
		// 会员--列出优惠券
		pathForListCoupon = new ArrayList<String>();
		pathForListCoupon.add("promotion/v1/list_coupon_promotion");
		
		
		// 会员--删除优惠券
		pathForDeleteCouponConfig = new ArrayList<String>();
		pathForDeleteCouponConfig.add("promotion/v1/delete_coupon_promotion");
				
				
		// 会员--保存促销
		pathForSavePromotion = new ArrayList<String>();
		pathForSavePromotion.add("promotion/v1/save_sales_promotion");
		
		
		// 会员--获取促销列表
		pathForGetPromotionList = new ArrayList<String>();
		pathForGetPromotionList.add("promotion/v2/get_sales_promotion_list");
		
		// 会员--删除促销
		pathForDeletePromotion = new ArrayList<String>();
		pathForDeletePromotion.add("promotion/v1/delete_coupon_promotion");
		
		
		// 会员--添加生日祝福
		pathForBirthPrivilege = new ArrayList<String>();
		pathForBirthPrivilege.add("member_privilege/v1/add_birthday_privilege");
		
		
		// 会员--添加纪念日祝福
		pathForMemoryPrivilege = new ArrayList<String>();
		pathForMemoryPrivilege.add("member_privilege/v1/add_memory_privilege");
		
		
		// 会员--添加赠券
		pathForCouponPrivilege = new ArrayList<String>();
		pathForCouponPrivilege.add("member_privilege/v1/add_coupon_privilege");
		
		
		// 会员--添加自定义权限
		pathForCustomPrivilege = new ArrayList<String>();
		pathForCustomPrivilege.add("member_privilege/v1/save_custom_privilege");
		
		
		// 会员--删除一般权限
		pathForDeletePrivilege = new ArrayList<String>();
		pathForDeletePrivilege.add("member_privilege/v1/remove_privilege_by_customer_right_id");
		
		
		// 会员--删除自定义权限
		pathForDeleteCustomPrivilege = new ArrayList<String>();
		pathForDeleteCustomPrivilege.add("member_privilege/v1/remove_member_interest_right");
		
		
		// 会员--设置自定义权限
		pathForAddCustomPrivilege= new ArrayList<String>();
		pathForAddCustomPrivilege.add("member_privilege/v1/add_custom_privilege");
		
		
		// 会员--在自定义权限列表中删除自定义权限
		pathForAllDeleteCustomPrivilege = new ArrayList<String>();
		pathForAllDeleteCustomPrivilege.add("member_privilege/v1/custom_privilege_delete");
		
		
		// 会员--获取所有会员等级权限详情
		pathForGetAllPrivilegeDetail = new ArrayList<String>();
		pathForGetAllPrivilegeDetail.add("member_privilege/v1/member_privilege_detail");
		
		
		// 会员--获取所有特权卡详情
		pathForGetAllCardPrivilegeDetail = new ArrayList<String>();
		pathForGetAllCardPrivilegeDetail.add("member_privilege/v1/get_card_privilege_List");	
		
		
		// 会员--在特权卡管理中删除特权卡
		pathForDeleteCardPrivilege = new ArrayList<String>();
		pathForDeleteCardPrivilege.add("member_privilege/v1/remove_kind_card_privilege");	
		
		
		// 会员--在特权卡管理中添加特权卡
		pathForAddCardPrivilege = new ArrayList<String>();
		pathForAddCardPrivilege.add("member_privilege/v1/kind_card_privilege_save");	
				
		// 会员--在用户特权卡中添加特权卡
		pathForCardPrivilege = new ArrayList<String>();
		pathForCardPrivilege.add("member_privilege/v1/add_card_privilege");		
		
		
		// 会员--在用户特权卡中添加特权卡
		pathForGetCustomPrivilegeList = new ArrayList<String>();
		pathForGetCustomPrivilegeList.add("member_privilege/v1/get_custom_privilege_list");	
		
		
		// 会员--在用户特权卡中添加特权卡
		pathForQueryKindCards = new ArrayList<String>();
		pathForQueryKindCards.add("member/v2/query_kind_card_money_rule");	
		
		
		// 会员--在用户特权卡中添加特权卡
		pathForSaveRechargeRule = new ArrayList<String>();
		pathForSaveRechargeRule.add("member/v2/save_money_rule");	
		
		
		// 会员--在用户特权卡中添加特权卡
		pathForDeleteAllRecharge = new ArrayList<String>();
		pathForDeleteAllRecharge.add("member/v2/batch_remove_money_rule");
		
		
		// 会员--在用户特权卡中添加特权卡
		pathForSelfRecharge = new ArrayList<String>();
		pathForSelfRecharge.add("member/v1/save_self_recharge_list");
		
		
		// 会员--在用户特权卡中添加特权卡
		pathForSaveGift = new ArrayList<String>();
		pathForSaveGift.add("integral-api/gift/v1/save");
		
		
		// 会员--在用户特权卡中添加特权卡
		pathForGetGiftList = new ArrayList<String>();
		pathForGetGiftList.add("integral-api/gift/v1/list");
		
		
		// 会员--在用户特权卡中添加特权卡
		pathForDeleteAllGift = new ArrayList<String>();
		pathForDeleteAllGift.add("integral-api/gift/v1/remove");
		
		
		// 会员--积分抵现--保存
		pathForSaveGiftConvert = new ArrayList<String>();
		pathForSaveGiftConvert.add("gift/v2/convert/save");
			
		
		// 会员--积分抵现--获取
		pathForGetGiftConvertList = new ArrayList<String>();
		pathForGetGiftConvertList.add("gift/v2/convert/list");
		
		
		// 会员--积分抵现--获取
		pathForUpdateMenuLabel = new ArrayList<String>();
		pathForUpdateMenuLabel.add("intelligence/v1/save_or_update_menu_label");
		
		
		//  顾客点餐智能提醒与推荐---保存配置
		pathForSaveEntityConfig = new ArrayList<String>();
		pathForSaveEntityConfig.add("intelligence/v1/save_entity_config");
		
		
		// 顾客点餐智能提醒与推荐---获取所有配置
		pathForQueryAllConfig = new ArrayList<String>();
		pathForQueryAllConfig.add("intelligence/v2/query_all_configs");
		
		
		// 顾客点餐智能提醒与推荐---获取所有配置
		pathForSavePlanConfig = new ArrayList<String>();
		pathForSavePlanConfig.add("intelligence/v1/save_plan_config");
		
		
		// 智能点餐---一键智能点餐模版设置
		pathForSaveTemplateConfig = new ArrayList<String>();
		pathForSaveTemplateConfig.add("template/v1/modify_template_switch");
				
		// 智能点餐---查询智能点餐模板
		pathForQueryMenuTemplate = new ArrayList<String>();
		pathForQueryMenuTemplate.add("template/v1/template_index_data");
		
		
		// 智能点餐---修改智能点餐模板
		pathForModifyMenuTemplate = new ArrayList<String>();
		pathForModifyMenuTemplate.add("template/v2/modify_template");
		
		
	/////////////////////////////////////  一元购---刮刮乐      ////////////////////////////////////////
		
		// 一元购---获取抽奖活动详情
		pathForGetActivityDetail = new ArrayList<String>();
		pathForGetActivityDetail.add("integral-api/one_pay_activity/v1/get_activity_detail");
		
		// 一元购活动列表页
		pathForGetActivityList = new ArrayList<String>();
		pathForGetActivityList.add("integral-api/one_pay_activity/v1/get_activity_list");
		
		// 一元购---获取菜列表
		pathForQueryCommonMenu = new ArrayList<String>();
		pathForQueryCommonMenu.add("integral-api/one_pay_activity/v1/query_common_menu_list");
		
		
		// 一元购---抽奖活动保存/发布设置（根据活动状态）
		pathForSaveActivity = new ArrayList<String>();
		pathForSaveActivity.add("integral-api/one_pay_activity/v1/save_activity");
		
		
		// 会员卡---获取我在本店的会员卡列表
		pathForGetCardsList = new ArrayList<String>();
		pathForGetCardsList.add("cards/v1/list");
		
		
		// 会员卡---会员卡详情查询
		pathForGetCardDetail = new ArrayList<String>();
		pathForGetCardDetail.add("cards/v1/get_card_detail");
		
		
		// 会员卡---会员卡支付
		pathForGetCardsPayment = new ArrayList<String>();
		pathForGetCardsPayment.add("cards/v1/payment");
		
		
		// 会员卡---本店优惠
		pathForGetPrivilegeList = new ArrayList<String>();
		pathForGetPrivilegeList.add("privilege/v1/list");
		
		
		// 会员卡---获取所有的会员卡
		pathForGetAllMyCard = new ArrayList<String>();
		pathForGetAllMyCard.add("cards/v1/get_my_card_list");
		
		// 会员卡---获取所有的优惠券
		pathForGetAllMyCoupon = new ArrayList<String>();
		pathForGetAllMyCoupon.add("privilege/v1/get_my_coupon_list");
		
		
		// 外卖---获取店铺菜单列表
		pathForGetTakeoutMenuList = new ArrayList<String>();
		pathForGetTakeoutMenuList.add("takeout_menus/v1/list");
		
		
		// 品牌拆分---通过业务类型获取附近的店接口
		pathForGetNearbyShop = new ArrayList<String>();
		pathForGetNearbyShop.add("shop/v1/business/nearby_shops");
		
		
		// 排队拆分---获取排队桌位类型
		pathForGetSeatType = new ArrayList<String>();
		pathForGetSeatType.add("line_queue/v1/get_seat_type");
		
		
		// 积分商城拆分--积分商城首页
		pathForGetIntegralHome = new ArrayList<String>();
		pathForGetIntegralHome.add("integral/v1/integral_home_list");
		
		
		// 个人用户中心拆分---商户会员中心
		/**
		 * 这是一个神奇的 URL，CI 时由于出口 IP重复导致 URL 必须简写  user_center/v1/dash_board<br/>
		 * 宝马7系加长版：one_pay/api/user_center/v1/dash_board <br/>
		 */
		pathForGetMyDashboard = new ArrayList<String>();
		pathForGetMyDashboard.add("user_center/v1/dash_board");
		
		
		// 二维火个人用户中心拆分---二维火会员中心
		pathForGetUserCenter = new ArrayList<String>();
		pathForGetUserCenter.add("user_center/v1/dash_board_inner");
				
	}


	public static List<String> getPrivilegePath(Privilege privilege){
		
		if(privilege == Privilege.BIRTH)
			return getPathForBirthPrivilege();
		
		else if(privilege == Privilege.MEMORY)
			return getPathForMemoryPrivilege();
		
		else if(privilege == Privilege.COUPON)
			return getPathForCouponPrivilege();
		
		else if(privilege == Privilege.CUSTOMPRIVILEGE)
			return getPathForCustomPrivilege();
		
		else if(privilege == Privilege.CARDPRIVILEGE)
			return getPathForCardPrivilege();
		else {
			MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, "there is no match privilege path to get");
			return null;
		}
	}

	public static List<String> getPathForLoadState() {
		return pathForLoadState;
	}

	public static void setPathForLoadState(List<String> pathForLoadState) {
		PathForHTTP.pathForLoadState = pathForLoadState;
	}

	public static List<String> getPathForBaseInfo() {
		return pathForBaseInfo;
	}

	public static void setPathForBaseInfo(List<String> pathForBaseInfo) {
		PathForHTTP.pathForBaseInfo = pathForBaseInfo;
	}

	public static List<String> getPathForApplyCard() {
		return pathForApplyCard;
	}

	public static void setPathForApplyCard(List<String> pathForApplyCard) {
		PathForHTTP.pathForApplyCard = pathForApplyCard;
	}

	public static List<String> getPathForCardList() {
		return pathForCardList;
	}

	public static void setPathForCardList(List<String> pathForCardList) {
		PathForHTTP.pathForCardList = pathForCardList;
	}

	public static List<String> getPathForDeleteCard() {
		return pathForDeleteCard;
	}

	public static void setPathForDeleteCard(List<String> pathForDeleteCard) {
		PathForHTTP.pathForDeleteCard = pathForDeleteCard;
	}

	public static List<String> getPathForGetCartCount() {
		return pathForGetCartCount;
	}

	public static void setPathForGetCartCount(List<String> pathForGetCartCount) {
		PathForHTTP.pathForGetCartCount = pathForGetCartCount;
	}

	public static List<String> getPathForCreateOwnCart() {
		return pathForCreateOwnCart;
	}

	public static void setPathForCreateOwnCart(List<String> pathForCreateOwnCart) {
		PathForHTTP.pathForCreateOwnCart = pathForCreateOwnCart;
	}

	public static List<String> getPathForGetCartData() {
		return pathForGetCartData;
	}

	public static void setPathForGetCartData(List<String> pathForGetCartData) {
		PathForHTTP.pathForGetCartData = pathForGetCartData;
	}

	public static List<String> getPathForModifyCart() {
		return pathForModifyCart;
	}

	public static void setPathForModifyCart(List<String> pathForModifyCart) {
		PathForHTTP.pathForModifyCart = pathForModifyCart;
	}

	public static List<String> getPathForClearCart() {
		return pathForClearCart;
	}

	public static void setPathForClearCart(List<String> pathForClearCart) {
		PathForHTTP.pathForClearCart = pathForClearCart;
	}

	public static List<String> getPathForClearOwnCart() {
		return pathForClearOwnCart;
	}

	public static void setPathForClearOwnCart(List<String> pathForClearOwnCart) {
		PathForHTTP.pathForClearOwnCart = pathForClearOwnCart;
	}

	public static List<String> getPathForModifyInfo() {
		return pathForModifyInfo;
	}

	public static void setPathForModifyInfo(List<String> pathForModifyInfo) {
		PathForHTTP.pathForModifyInfo = pathForModifyInfo;
	}

	public static List<String> getPathForGetRecommendMenuList() {
		return pathForGetRecommendMenuList;
	}

	public static void setPathForGetRecommendMenuList(
			List<String> pathForGetRecommendMenuList) {
		PathForHTTP.pathForGetRecommendMenuList = pathForGetRecommendMenuList;
	}

	public static List<String> getPathForGetRecommendMenuById() {
		return pathForGetRecommendMenuById;
	}

	public static void setPathForGetRecommendMenuById(
			List<String> pathForGetRecommendMenuById) {
		PathForHTTP.pathForGetRecommendMenuById = pathForGetRecommendMenuById;
	}

	public static List<String> getPathForGetCouponList() {
		return pathForGetCouponList;
	}

	public static void setPathForGetCouponList(List<String> pathForGetCouponList) {
		PathForHTTP.pathForGetCouponList = pathForGetCouponList;
	}

	public static List<String> getPathForOAuth() {
		return pathForOAuth;
	}

	public static void setPathForOAuth(List<String> pathForOAuth) {
		PathForHTTP.pathForOAuth = pathForOAuth;
	}

	public static List<String> getPathForOAuthConsumerCode() {
		return pathForOAuthConsumerCode;
	}

	public static void setPathForOAuthConsumerCode(
			List<String> pathForOAuthConsumerCode) {
		PathForHTTP.pathForOAuthConsumerCode = pathForOAuthConsumerCode;
	}

	public static List<String> getPathForOAuthEntityId() {
		return pathForOAuthEntityId;
	}

	public static void setPathForOAuthEntityId(List<String> pathForOAuthEntityId) {
		PathForHTTP.pathForOAuthEntityId = pathForOAuthEntityId;
	}

	public static List<String> getPathForOAuthMenuCode() {
		return pathForOAuthMenuCode;
	}

	public static void setPathForOAuthMenuCode(List<String> pathForOAuthMenuCode) {
		PathForHTTP.pathForOAuthMenuCode = pathForOAuthMenuCode;
	}

	public static List<String> getPathForOAuthCallback() {
		return pathForOAuthCallback;
	}

	public static void setPathForOAuthCallback(List<String> pathForOAuthCallback) {
		PathForHTTP.pathForOAuthCallback = pathForOAuthCallback;
	}

	public static List<String> getPathForaliShop() {
		return pathForaliShop;
	}

	public static void setPathForaliShop(List<String> pathForaliShop) {
		PathForHTTP.pathForaliShop = pathForaliShop;
	}

	public static List<String> getPathForPreCart() {
		return pathForPreCart;
	}

	public static void setPathForPreCart(List<String> pathForPreCart) {
		PathForHTTP.pathForPreCart = pathForPreCart;
	}

	public static List<String> getPathForGetMenus() {
		return pathForGetMenus;
	}

	public static void setPathForGetMenus(List<String> pathForGetMenus) {
		PathForHTTP.pathForGetMenus = pathForGetMenus;
	}

	public static List<String> getPathForModifyPeopleAndMemo() {
		return pathForModifyPeopleAndMemo;
	}

	public static void setPathForModifyPeopleAndMemo(
			List<String> pathForModifyPeopleAndMemo) {
		PathForHTTP.pathForModifyPeopleAndMemo = pathForModifyPeopleAndMemo;
	}

	public static List<String> getPathForImportUserCart() {
		return pathForImportUserCart;
	}

	public static void setPathForImportUserCart(List<String> pathForImportUserCart) {
		PathForHTTP.pathForImportUserCart = pathForImportUserCart;
	}

	public static List<String> getPathForGetUserCart() {
		return pathForGetUserCart;
	}

	public static void setPathForGetUserCart(List<String> pathForGetUserCart) {
		PathForHTTP.pathForGetUserCart = pathForGetUserCart;
	}

	public static List<String> getPathForGetPrePayBill() {
		return pathForGetPrePayBill;
	}

	public static void setPathForGetPrePayBill(List<String> pathForGetPrePayBill) {
		PathForHTTP.pathForGetPrePayBill = pathForGetPrePayBill;
	}

	public static List<String> getPathForConfirmPrepayOrder() {
		return pathForConfirmPrepayOrder;
	}

	public static void setPathForConfirmPrepayOrder(
			List<String> pathForConfirmPrepayOrder) {
		PathForHTTP.pathForConfirmPrepayOrder = pathForConfirmPrepayOrder;
	}

	public static List<String> getPathForDeletePrepayOrder() {
		return pathForDeletePrepayOrder;
	}

	public static void setPathForDeletePrepayOrder(
			List<String> pathForDeletePrepayOrder) {
		PathForHTTP.pathForDeletePrepayOrder = pathForDeletePrepayOrder;
	}

	public static List<String> getPathForGetPrepayOrder() {
		return pathForGetPrepayOrder;
	}

	public static void setPathForGetPrepayOrder(List<String> pathForGetPrepayOrder) {
		PathForHTTP.pathForGetPrepayOrder = pathForGetPrepayOrder;
	}

	public static List<String> getPathForGetBillInfo() {
		return pathForGetBillInfo;
	}

	public static void setPathForGetBillInfo(List<String> pathForGetBillInfo) {
		PathForHTTP.pathForGetBillInfo = pathForGetBillInfo;
	}

	public static List<String> getPathForLockPayWaitingOrder() {
		return pathForLockPayWaitingOrder;
	}

	public static void setPathForLockPayWaitingOrder(
			List<String> pathForLockPayWaitingOrder) {
		PathForHTTP.pathForLockPayWaitingOrder = pathForLockPayWaitingOrder;
	}

	public static List<String> getPathForGetPrepayBill() {
		return pathForGetPrepayBill;
	}

	public static void setPathForGetPrepayBill(List<String> pathForGetPrepayBill) {
		PathForHTTP.pathForGetPrepayBill = pathForGetPrepayBill;
	}

	public static List<String> getPathForLockOrder() {
		return pathForLockOrder;
	}

	public static void setPathForLockOrder(List<String> pathForLockOrder) {
		PathForHTTP.pathForLockOrder = pathForLockOrder;
	}

	public static List<String> getPathForUnLockPayOrder() {
		return pathForUnLockPayOrder;
	}

	public static void setPathForUnLockPayOrder(List<String> pathForUnLockPayOrder) {
		PathForHTTP.pathForUnLockPayOrder = pathForUnLockPayOrder;
	}

	public static List<String> getPathForCheckOrder() {
		return pathForCheckOrder;
	}

	
	public static void setPathForCheckOrder(List<String> pathForCheckOrder) {
		PathForHTTP.pathForCheckOrder = pathForCheckOrder;
	}

	public static List<String> getPathForCheckOrderChange() {
		return pathForCheckOrderChange;
	}

	public static void setPathForCheckOrderChange(
			List<String> pathForCheckOrderChange) {
		PathForHTTP.pathForCheckOrderChange = pathForCheckOrderChange;
	}

	public static List<String> getPathForConfirmOrder() {
		return pathForConfirmOrder;
	}

	public static void setPathForConfirmOrder(List<String> pathForConfirmOrder) {
		PathForHTTP.pathForConfirmOrder = pathForConfirmOrder;
	}

	public static List<String> getPathForModifyMemo() {
		return pathForModifyMemo;
	}

	public static void setPathForModifyMemo(List<String> pathForModifyMemo) {
		PathForHTTP.pathForModifyMemo = pathForModifyMemo;
	}

	public static List<String> getPathForGetOrder() {
		return pathForGetOrder;
	}

	public static void setPathForGetOrder(List<String> pathForGetOrder) {
		PathForHTTP.pathForGetOrder = pathForGetOrder;
	}

	public static List<String> getPathForGetKoubeiOrder() {
		return pathForGetKoubeiOrder;
	}

	public static void setPathForGetKoubeiOrder(List<String> pathForGetKoubeiOrder) {
		PathForHTTP.pathForGetKoubeiOrder = pathForGetKoubeiOrder;
	}

	public static List<String> getPathForGetOrderHistory() {
		return pathForGetOrderHistory;
	}

	public static void setPathForGetOrderHistory(
			List<String> pathForGetOrderHistory) {
		PathForHTTP.pathForGetOrderHistory = pathForGetOrderHistory;
	}

	public static List<String> getPathForReIssuedPreOrder() {
		return pathForReIssuedPreOrder;
	}

	public static void setPathForReIssuedPreOrder(
			List<String> pathForReIssuedPreOrder) {
		PathForHTTP.pathForReIssuedPreOrder = pathForReIssuedPreOrder;
	}

	public static List<String> getPathForShowTitle() {
		return pathForShowTitle;
	}

	public static void setPathForShowTitle(List<String> pathForShowTitle) {
		PathForHTTP.pathForShowTitle = pathForShowTitle;
	}

	public static List<String> getPathForListMenus() {
		return pathForListMenus;
	}

	public static void setPathForListMenus(List<String> pathForListMenus) {
		PathForHTTP.pathForListMenus = pathForListMenus;
	}

	public static List<String> getPathForForceMenu() {
		return pathForForceMenu;
	}

	public static void setPathForForceMenu(List<String> pathForForceMenu) {
		PathForHTTP.pathForForceMenu = pathForForceMenu;
	}

	public static List<String> getPathForRecommendMenu() {
		return pathForRecommendMenu;
	}

	public static void setPathForRecommendMenu(List<String> pathForRecommendMenu) {
		PathForHTTP.pathForRecommendMenu = pathForRecommendMenu;
	}

	public static List<String> getPathForListCartsPrivilege() {
		return pathForListCartsPrivilege;
	}

	public static void setPathForListCartsPrivilege(
			List<String> pathForListCartsPrivilege) {
		PathForHTTP.pathForListCartsPrivilege = pathForListCartsPrivilege;
	}

	public static List<String> getPathForAddToCarts() {
		return pathForAddToCarts;
	}

	public static void setPathForAddToCarts(List<String> pathForAddToCarts) {
		PathForHTTP.pathForAddToCarts = pathForAddToCarts;
	}

	public static List<String> getPathForGetTradeBill() {
		return pathForGetTradeBill;
	}

	public static void setPathForGetTradeBill(List<String> pathForGetTradeBill) {
		PathForHTTP.pathForGetTradeBill = pathForGetTradeBill;
	}

	public static List<String> getPathForPlayComments() {
		return pathForPlayComments;
	}

	public static void setPathForPlayComments(List<String> pathForPlayComments) {
		PathForHTTP.pathForPlayComments = pathForPlayComments;
	}

	public static List<String> getPathForGetActivitySwitch() {
		return pathForGetActivitySwitch;
	}

	public static void setPathForGetActivitySwitch(
			List<String> pathForGetActivitySwitch) {
		PathForHTTP.pathForGetActivitySwitch = pathForGetActivitySwitch;
	}

	public static List<String> getPathForConfirmPrePay() {
		return pathForConfirmPrePay;
	}

	public static void setPathForConfirmPrePay(List<String> pathForConfirmPrePay) {
		PathForHTTP.pathForConfirmPrePay = pathForConfirmPrePay;
	}

	public static List<String> getPathForGetPayType() {
		return pathForGetPayType;
	}

	public static void setPathForGetPayType(List<String> pathForGetPayType) {
		PathForHTTP.pathForGetPayType = pathForGetPayType;
	}

	public static List<String> getPathForGetToken() {
		return pathForGetToken;
	}

	public static void setPathForGetToken(List<String> pathForGetToken) {
		PathForHTTP.pathForGetToken = pathForGetToken;
	}

	public static List<String> getPathForCardPay() {
		return pathForCardPay;
	}

	public static void setPathForCardPay(List<String> pathForCardPay) {
		PathForHTTP.pathForCardPay = pathForCardPay;
	}

	public static List<String> getPathForGetShopTax() {
		return pathForGetShopTax;
	}

	public static void setPathForGetShopTax(List<String> pathForGetShopTax) {
		PathForHTTP.pathForGetShopTax = pathForGetShopTax;
	}

	public static List<String> getPathForGetPersonInfo() {
		return pathForGetPersonInfo;
	}

	public static void setPathForGetPersonInfo(List<String> pathForGetPersonInfo) {
		PathForHTTP.pathForGetPersonInfo = pathForGetPersonInfo;
	}

	public static List<String> getPathForGetUserInfo() {
		return pathForGetUserInfo;
	}

	public static void setPathForGetUserInfo(List<String> pathForGetUserInfo) {
		PathForHTTP.pathForGetUserInfo = pathForGetUserInfo;
	}

	public static List<String> getPathForGetPopupMessage() {
		return pathForGetPopupMessage;
	}

	public static void setPathForGetPopupMessage(
			List<String> pathForGetPopupMessage) {
		PathForHTTP.pathForGetPopupMessage = pathForGetPopupMessage;
	}

	public static List<String> getPathForGetCartPrivilege() {
		return pathForGetCartPrivilege;
	}

	public static void setPathForGetCartPrivilege(
			List<String> pathForGetCartPrivilege) {
		PathForHTTP.pathForGetCartPrivilege = pathForGetCartPrivilege;
	}

	public static List<String> getPathForGetTradeBillV2() {
		return pathForGetTradeBillV2;
	}

	public static void setPathForGetTradeBillV2(List<String> pathForGetTradeBillV2) {
		PathForHTTP.pathForGetTradeBillV2 = pathForGetTradeBillV2;
	}

	public static List<String> getPathForGetGiveCoupon() {
		return pathForGetGiveCoupon;
	}

	public static void setPathForGetGiveCoupon(List<String> pathForGetGiveCoupon) {
		PathForHTTP.pathForGetGiveCoupon = pathForGetGiveCoupon;
	}

	public static List<String> getPathForGetOrderComments() {
		return pathForGetOrderComments;
	}

	public static void setPathForGetOrderComments(
			List<String> pathForGetOrderComments) {
		PathForHTTP.pathForGetOrderComments = pathForGetOrderComments;
	}

	public static List<String> getPathForLoginCashDesk() {
		return pathForLoginCashDesk;
	}

	public static void setPathForLoginCashDesk(List<String> pathForLoginCashDesk) {
		PathForHTTP.pathForLoginCashDesk = pathForLoginCashDesk;
	}

	public static List<String> getPathForSendHeartBeat() {
		return pathForSendHeartBeat;
	}

	public static void setPathForSendHeartBeat(List<String> pathForSendHeartBeat) {
		PathForHTTP.pathForSendHeartBeat = pathForSendHeartBeat;
	}

	public static List<String> getPathForGetMessageList() {
		return pathForGetMessageList;
	}

	public static void setPathForGetMessageList(List<String> pathForGetMessageList) {
		PathForHTTP.pathForGetMessageList = pathForGetMessageList;
	}

	public static List<String> getPathForGetWaitingOrder() {
		return pathForGetWaitingOrder;
	}

	public static void setPathForGetWaitingOrder(
			List<String> pathForGetWaitingOrder) {
		PathForHTTP.pathForGetWaitingOrder = pathForGetWaitingOrder;
	}

	public static List<String> getPathForUploadTotalPay() {
		return pathForUploadTotalPay;
	}

	public static void setPathForUploadTotalPay(List<String> pathForUploadTotalPay) {
		PathForHTTP.pathForUploadTotalPay = pathForUploadTotalPay;
	}

	public static List<String> getPathForUpdateMessage() {
		return pathForUpdateMessage;
	}

	public static void setPathForUpdateMessage(List<String> pathForUpdateMessage) {
		PathForHTTP.pathForUpdateMessage = pathForUpdateMessage;
	}

	public static List<String> getPathForSaveForceMenu() {
		return pathForSaveForceMenu;
	}

	public static void setPathForSaveForceMenu(List<String> pathForSaveForceMenu) {
		PathForHTTP.pathForSaveForceMenu = pathForSaveForceMenu;
	}

	public static List<String> getPathForRemoveForceMenu() {
		return pathForRemoveForceMenu;
	}

	public static void setPathForRemoveForceMenu(
			List<String> pathForRemoveForceMenu) {
		PathForHTTP.pathForRemoveForceMenu = pathForRemoveForceMenu;
	}

	public static List<String> getPathForGetForceMenuList() {
		return pathForGetForceMenuList;
	}

	public static void setPathForGetForceMenuList(
			List<String> pathForGetForceMenuList) {
		PathForHTTP.pathForGetForceMenuList = pathForGetForceMenuList;
	}

	public static List<String> getPathForSetEntityConfig() {
		return pathForSetEntityConfig;
	}

	public static void setPathForSetEntityConfig(
			List<String> pathForSetEntityConfig) {
		PathForHTTP.pathForSetEntityConfig = pathForSetEntityConfig;
	}

	public static List<String> getPathForSetAutoGetCoupon() {
		return pathForSetAutoGetCoupon;
	}

	public static void setPathForSetAutoGetCoupon(
			List<String> pathForSetAutoGetCoupon) {
		PathForHTTP.pathForSetAutoGetCoupon = pathForSetAutoGetCoupon;
	}

	public static List<String> getPathForPrePaySwitch() {
		return pathForPrePaySwitch;
	}

	public static void setPathForPrePaySwitch(List<String> pathForPrePaySwitch) {
		PathForHTTP.pathForPrePaySwitch = pathForPrePaySwitch;
	}

	public static List<String> getPathForMenuConfigUpdate() {
		return pathForMenuConfigUpdate;
	}

	public static void setPathForMenuConfigUpdate(
			List<String> pathForMenuConfigUpdate) {
		PathForHTTP.pathForMenuConfigUpdate = pathForMenuConfigUpdate;
	}

	public static List<String> getPathForBaseWithClientSwitch() {
		return pathForBaseWithClientSwitch;
	}

	public static void setPathForBaseWithClientSwitch(
			List<String> pathForBaseWithClientSwitch) {
		PathForHTTP.pathForBaseWithClientSwitch = pathForBaseWithClientSwitch;
	}

	public static List<String> getPathForBatchSetting() {
		return pathForBatchSetting;
	}

	public static void setPathForBatchSetting(List<String> pathForBatchSetting) {
		PathForHTTP.pathForBatchSetting = pathForBatchSetting;
	}

	public static List<String> getPathForTakeOutConfig() {
		return pathForTakeOutConfig;
	}

	public static void setPathForTakeOutConfig(List<String> pathForTakeOutConfig) {
		PathForHTTP.pathForTakeOutConfig = pathForTakeOutConfig;
	}

	public static List<String> getPathForTakeOutTime() {
		return pathForTakeOutTime;
	}

	public static void setPathForTakeOutTime(List<String> pathForTakeOutTime) {
		PathForHTTP.pathForTakeOutTime = pathForTakeOutTime;
	}

	public static List<String> getPathForTakeOutDeliveryMan() {
		return pathForTakeOutDeliveryMan;
	}

	public static void setPathForTakeOutDeliveryMan(
			List<String> pathForTakeOutDeliveryMan) {
		PathForHTTP.pathForTakeOutDeliveryMan = pathForTakeOutDeliveryMan;
	}

	public static List<String> getPathForImageUpload() {
		return pathForImageUpload;
	}

	public static void setPathForImageUpload(List<String> pathForImageUpload) {
		PathForHTTP.pathForImageUpload = pathForImageUpload;
	}

	public static List<String> getPathForAudioUpload() {
		return pathForAudioUpload;
	}

	public static void setPathForAudioUpload(List<String> pathForAudioUpload) {
		PathForHTTP.pathForAudioUpload = pathForAudioUpload;
	}

	public static List<String> getPathForImageSetConfig() {
		return pathForImageSetConfig;
	}

	public static void setPathForImageSetConfig(List<String> pathForImageSetConfig) {
		PathForHTTP.pathForImageSetConfig = pathForImageSetConfig;
	}

	public static List<String> getPathForColorStyleConfig() {
		return pathForColorStyleConfig;
	}

	public static void setPathForColorStyleConfig(
			List<String> pathForColorStyleConfig) {
		PathForHTTP.pathForColorStyleConfig = pathForColorStyleConfig;
	}

	public static List<String> getPathForMenuRepeatRemaindConfig() {
		return pathForMenuRepeatRemaindConfig;
	}

	public static void setPathForMenuRepeatRemaindConfig(
			List<String> pathForMenuRepeatRemaindConfig) {
		PathForHTTP.pathForMenuRepeatRemaindConfig = pathForMenuRepeatRemaindConfig;
	}

	public static List<String> getPathForMenuRepeatAddMenuConfig() {
		return pathForMenuRepeatAddMenuConfig;
	}

	public static void setPathForMenuRepeatAddMenuConfig(
			List<String> pathForMenuRepeatAddMenuConfig) {
		PathForHTTP.pathForMenuRepeatAddMenuConfig = pathForMenuRepeatAddMenuConfig;
	}

	public static List<String> getPathForMenuRepeatDeleteMenuConfig() {
		return pathForMenuRepeatDeleteMenuConfig;
	}

	public static void setPathForMenuRepeatDeleteMenuConfig(
			List<String> pathForMenuRepeatDeleteMenuConfig) {
		PathForHTTP.pathForMenuRepeatDeleteMenuConfig = pathForMenuRepeatDeleteMenuConfig;
	}

	public static List<String> getPathForSaveCouponConfig() {
		return pathForSaveCouponConfig;
	}

	public static void setPathForSaveCouponConfig(
			List<String> pathForSaveCouponConfig) {
		PathForHTTP.pathForSaveCouponConfig = pathForSaveCouponConfig;
	}

	public static List<String> getPathForDeleteCouponConfig() {
		return pathForDeleteCouponConfig;
	}

	public static void setPathForDeleteCouponConfig(
			List<String> pathForDeleteCouponConfig) {
		PathForHTTP.pathForDeleteCouponConfig = pathForDeleteCouponConfig;
	}

	public static List<String> getPathForSavePromotion() {
		return pathForSavePromotion;
	}

	public static void setPathForSavePromotion(List<String> pathForSavePromotion) {
		PathForHTTP.pathForSavePromotion = pathForSavePromotion;
	}

	public static List<String> getPathForDeletePromotion() {
		return pathForDeletePromotion;
	}

	public static void setPathForDeletePromotion(
			List<String> pathForDeletePromotion) {
		PathForHTTP.pathForDeletePromotion = pathForDeletePromotion;
	}


	public static List<String> getPathForBirthPrivilege() {
		return pathForBirthPrivilege;
	}

	public static void setPathForBirthPrivilege(List<String> pathForBirthPrivilege) {
		PathForHTTP.pathForBirthPrivilege = pathForBirthPrivilege;
	}

	public static List<String> getPathForMemoryPrivilege() {
		return pathForMemoryPrivilege;
	}

	public static void setPathForMemoryPrivilege(
			List<String> pathForMemoryPrivilege) {
		PathForHTTP.pathForMemoryPrivilege = pathForMemoryPrivilege;
	}

	public static List<String> getPathForCouponPrivilege() {
		return pathForCouponPrivilege;
	}

	public static void setPathForCouponPrivilege(
			List<String> pathForCouponPrivilege) {
		PathForHTTP.pathForCouponPrivilege = pathForCouponPrivilege;
	}

	public static List<String> getPathForCustomPrivilege() {
		return pathForCustomPrivilege;
	}

	public static void setPathForCustomPrivilege(
			List<String> pathForCustomPrivilege) {
		PathForHTTP.pathForCustomPrivilege = pathForCustomPrivilege;
	}

	public static List<String> getPathForDeletePrivilege() {
		return pathForDeletePrivilege;
	}

	public static void setPathForDeletePrivilege(
			List<String> pathForDeletePrivilege) {
		PathForHTTP.pathForDeletePrivilege = pathForDeletePrivilege;
	}

	public static List<String> getPathForDeleteCustomPrivilege() {
		return pathForDeleteCustomPrivilege;
	}

	public static void setPathForDeleteCustomPrivilege(
			List<String> pathForDeleteCustomPrivilege) {
		PathForHTTP.pathForDeleteCustomPrivilege = pathForDeleteCustomPrivilege;
	}

	public static List<String> getPathForAddCustomPrivilege() {
		return pathForAddCustomPrivilege;
	}

	public static void setPathForAddCustomPrivilege(
			List<String> pathForAddCustomPrivilege) {
		PathForHTTP.pathForAddCustomPrivilege = pathForAddCustomPrivilege;
	}

	public static List<String> getPathForAllDeleteCustomPrivilege() {
		return pathForAllDeleteCustomPrivilege;
	}

	public static void setPathForAllDeleteCustomPrivilege(
			List<String> pathForAllDeleteCustomPrivilege) {
		PathForHTTP.pathForAllDeleteCustomPrivilege = pathForAllDeleteCustomPrivilege;
	}

	public static List<String> getPathForGetAllPrivilegeDetail() {
		return pathForGetAllPrivilegeDetail;
	}

	public static void setPathForGetAllPrivilegeDetail(
			List<String> pathForGetAllPrivilegeDetail) {
		PathForHTTP.pathForGetAllPrivilegeDetail = pathForGetAllPrivilegeDetail;
	}

	public static List<String> getPathForGetAllCardPrivilegeDetail() {
		return pathForGetAllCardPrivilegeDetail;
	}

	public static void setPathForGetAllCardPrivilegeDetail(
			List<String> pathForGetAllCardPrivilegeDetail) {
		PathForHTTP.pathForGetAllCardPrivilegeDetail = pathForGetAllCardPrivilegeDetail;
	}

	public static List<String> getPathForDeleteCardPrivilege() {
		return pathForDeleteCardPrivilege;
	}

	public static void setPathForDeleteCardPrivilege(
			List<String> pathForDeleteCardPrivilege) {
		PathForHTTP.pathForDeleteCardPrivilege = pathForDeleteCardPrivilege;
	}

	public static List<String> getPathForAddCardPrivilege() {
		return pathForAddCardPrivilege;
	}

	public static void setPathForAddCardPrivilege(
			List<String> pathForAddCardPrivilege) {
		PathForHTTP.pathForAddCardPrivilege = pathForAddCardPrivilege;
	}

	public static List<String> getPathForCardPrivilege() {
		return pathForCardPrivilege;
	}

	public static void setPathForCardPrivilege(List<String> pathForCardPrivilege) {
		PathForHTTP.pathForCardPrivilege = pathForCardPrivilege;
	}

	public static List<String> getPathForGetCustomPrivilegeList() {
		return pathForGetCustomPrivilegeList;
	}

	public static void setPathForGetCustomPrivilegeList(
			List<String> pathForGetCustomPrivilegeList) {
		PathForHTTP.pathForGetCustomPrivilegeList = pathForGetCustomPrivilegeList;
	}

	public static List<String> getPathForQueryKindCards() {
		return pathForQueryKindCards;
	}

	public static void setPathForQueryKindCards(List<String> pathForQueryKindCards) {
		PathForHTTP.pathForQueryKindCards = pathForQueryKindCards;
	}

	public static List<String> getPathForSaveRechargeRule() {
		return pathForSaveRechargeRule;
	}

	public static void setPathForSaveRechargeRule(
			List<String> pathForSaveRechargeRule) {
		PathForHTTP.pathForSaveRechargeRule = pathForSaveRechargeRule;
	}

	public static List<String> getPathForDeleteAllRecharge() {
		return pathForDeleteAllRecharge;
	}

	public static void setPathForDeleteAllRecharge(
			List<String> pathForDeleteAllRecharge) {
		PathForHTTP.pathForDeleteAllRecharge = pathForDeleteAllRecharge;
	}

	public static List<String> getPathForSelfRecharge() {
		return pathForSelfRecharge;
	}

	public static void setPathForSelfRecharge(List<String> pathForSelfRecharge) {
		PathForHTTP.pathForSelfRecharge = pathForSelfRecharge;
	}

	public static List<String> getPathForSaveGift() {
		return pathForSaveGift;
	}

	public static void setPathForSaveGift(List<String> pathForSaveGift) {
		PathForHTTP.pathForSaveGift = pathForSaveGift;
	}

	public static List<String> getPathForGetGiftList() {
		return pathForGetGiftList;
	}

	public static void setPathForGetGiftList(List<String> pathForGetGiftList) {
		PathForHTTP.pathForGetGiftList = pathForGetGiftList;
	}

	public static List<String> getPathForDeleteAllGift() {
		return pathForDeleteAllGift;
	}

	public static void setPathForDeleteAllGift(List<String> pathForDeleteAllGift) {
		PathForHTTP.pathForDeleteAllGift = pathForDeleteAllGift;
	}

	public static List<String> getPathForSaveGiftConvert() {
		return pathForSaveGiftConvert;
	}

	public static void setPathForSaveGiftConvert(
			List<String> pathForSaveGiftConvert) {
		PathForHTTP.pathForSaveGiftConvert = pathForSaveGiftConvert;
	}

	public static List<String> getPathForGetGiftConvertList() {
		return pathForGetGiftConvertList;
	}

	public static void setPathForGetGiftConvertList(
			List<String> pathForGetGiftConvertList) {
		PathForHTTP.pathForGetGiftConvertList = pathForGetGiftConvertList;
	}

	public static List<String> getPathForUpdateMenuLabel() {
		return pathForUpdateMenuLabel;
	}

	public static void setPathForUpdateMenuLabel(
			List<String> pathForUpdateMenuLabel) {
		PathForHTTP.pathForUpdateMenuLabel = pathForUpdateMenuLabel;
	}

	public static List<String> getPathForSaveEntityConfig() {
		return pathForSaveEntityConfig;
	}

	public static void setPathForSaveEntityConfig(
			List<String> pathForSaveEntityConfig) {
		PathForHTTP.pathForSaveEntityConfig = pathForSaveEntityConfig;
	}

	public static List<String> getPathForQueryAllConfig() {
		return pathForQueryAllConfig;
	}

	public static void setPathForQueryAllConfig(List<String> pathForQueryAllConfig) {
		PathForHTTP.pathForQueryAllConfig = pathForQueryAllConfig;
	}

	public static List<String> getPathForSavePlanConfig() {
		return pathForSavePlanConfig;
	}

	public static void setPathForSavePlanConfig(List<String> pathforSavePlanConfig) {
		PathForHTTP.pathForSavePlanConfig = pathforSavePlanConfig;
	}

	public static List<String> getPathForSaveTemplateConfig() {
		return pathForSaveTemplateConfig;
	}

	public static void setPathForSaveTemplateConfig(
			List<String> pathForSaveTemplateConfig) {
		PathForHTTP.pathForSaveTemplateConfig = pathForSaveTemplateConfig;
	}

	public static List<String> getPathForQueryMenuTemplate() {
		return pathForQueryMenuTemplate;
	}

	public static void setPathForQueryMenuTemplate(
			List<String> pathForQueryMenuTemplate) {
		PathForHTTP.pathForQueryMenuTemplate = pathForQueryMenuTemplate;
	}

	public static List<String> getPathForModifyMenuTemplate() {
		return pathForModifyMenuTemplate;
	}

	public static void setPathForModifyMenuTemplate(
			List<String> pathForModifyMenuTemplate) {
		PathForHTTP.pathForModifyMenuTemplate = pathForModifyMenuTemplate;
	}

	public static List<String> getPathForGetTakeOutConfig() {
		return pathForGetTakeOutConfig;
	}

	public static void setPathForGetTakeOutConfig(
			List<String> pathForGetTakeOutConfig) {
		PathForHTTP.pathForGetTakeOutConfig = pathForGetTakeOutConfig;
	}

	public static List<String> getPathForDeleteTakeOutConfig() {
		return pathForDeleteTakeOutConfig;
	}

	public static void setPathForDeleteTakeOutConfig(
			List<String> pathForDeleteTakeOutConfig) {
		PathForHTTP.pathForDeleteTakeOutConfig = pathForDeleteTakeOutConfig;
	}

	public static List<String> getPathForGetTakeOutDeliveryMan() {
		return pathForGetTakeOutDeliveryMan;
	}

	public static void setPathForGetTakeOutDeliveryMan(
			List<String> pathForGetTakeOutDeliveryMan) {
		PathForHTTP.pathForGetTakeOutDeliveryMan = pathForGetTakeOutDeliveryMan;
	}

	public static List<String> getPathForDeleteTakeOutDeliveryMan() {
		return pathForDeleteTakeOutDeliveryMan;
	}

	public static void setPathForDeleteTakeOutDeliveryMan(
			List<String> pathForDeleteTakeOutDeliveryMan) {
		PathForHTTP.pathForDeleteTakeOutDeliveryMan = pathForDeleteTakeOutDeliveryMan;
	}

	public static List<String> getPathForQueryImageSet() {
		return pathForQueryImageSet;
	}

	public static void setPathForQueryImageSet(List<String> pathForQueryImageSet) {
		PathForHTTP.pathForQueryImageSet = pathForQueryImageSet;
	}

	public static List<String> getPathForGetAllForceMenuList() {
		return pathForGetAllForceMenuList;
	}

	public static void setPathForGetAllForceMenuList(
			List<String> pathForGetAllForceMenuList) {
		PathForHTTP.pathForGetAllForceMenuList = pathForGetAllForceMenuList;
	}

	public static List<String> getPathForListMenuSpec() {
		return pathForListMenuSpec;
	}

	public static void setPathForListMenuSpec(List<String> pathForListMenuSpec) {
		PathForHTTP.pathForListMenuSpec = pathForListMenuSpec;
	}

	public static List<String> getPathForListNormalSpec() {
		return pathForListNormalSpec;
	}

	public static void setPathForListNormalSpec(List<String> pathForListNormalSpec) {
		PathForHTTP.pathForListNormalSpec = pathForListNormalSpec;
	}

	public static List<String> getPathForListBossConfig() {
		return pathForListBossConfig;
	}

	public static void setPathForListBossConfig(List<String> pathForListBossConfig) {
		PathForHTTP.pathForListBossConfig = pathForListBossConfig;
	}

	public static List<String> getPathForGetActivityDetail() {
		return pathForGetActivityDetail;
	}

	public static void setPathForGetActivityDetail(
			List<String> pathForGetActivityDetail) {
		PathForHTTP.pathForGetActivityDetail = pathForGetActivityDetail;
	}

	public static List<String> getPathForGetActivityList() {
		return pathForGetActivityList;
	}

	public static void setPathForGetActivityList(
			List<String> pathForGetActivityList) {
		PathForHTTP.pathForGetActivityList = pathForGetActivityList;
	}

	public static List<String> getPathForQueryCommonMenu() {
		return pathForQueryCommonMenu;
	}

	public static void setPathForQueryCommonMenu(
			List<String> pathForQueryCommonMenu) {
		PathForHTTP.pathForQueryCommonMenu = pathForQueryCommonMenu;
	}

	public static List<String> getPathForSaveActivity() {
		return pathForSaveActivity;
	}

	public static void setPathForSaveActivity(List<String> pathForSaveActivity) {
		PathForHTTP.pathForSaveActivity = pathForSaveActivity;
	}

	public static List<String> getPathForGetCardsList() {
		return pathForGetCardsList;
	}

	public static void setPathForGetCardsList(List<String> pathForGetCardsList) {
		PathForHTTP.pathForGetCardsList = pathForGetCardsList;
	}

	public static List<String> getPathForGetCardDetail() {
		return pathForGetCardDetail;
	}

	public static void setPathForGetCardDetail(List<String> pathForGetCardDetail) {
		PathForHTTP.pathForGetCardDetail = pathForGetCardDetail;
	}

	public static List<String> getPathForGetCardsPayment() {
		return pathForGetCardsPayment;
	}

	public static void setPathForGetCardsPayment(
			List<String> pathForGetCardsPayment) {
		PathForHTTP.pathForGetCardsPayment = pathForGetCardsPayment;
	}

	public static List<String> getPathForGetPrivilegeList() {
		return pathForGetPrivilegeList;
	}

	public static void setPathForGetPrivilegeList(
			List<String> pathForGetPrivilegeList) {
		PathForHTTP.pathForGetPrivilegeList = pathForGetPrivilegeList;
	}

	public static List<String> getPathForGetAllMyCard() {
		return pathForGetAllMyCard;
	}

	public static void setPathForGetAllMyCard(List<String> pathForGetAllMyCard) {
		PathForHTTP.pathForGetAllMyCard = pathForGetAllMyCard;
	}

	public static List<String> getPathForGetAllMyCoupon() {
		return pathForGetAllMyCoupon;
	}

	public static void setPathForGetAllMyCoupon(List<String> pathForGetAllMyCoupon) {
		PathForHTTP.pathForGetAllMyCoupon = pathForGetAllMyCoupon;
	}

	public static List<String> getPathForGetTakeoutMenuList() {
		return pathForGetTakeoutMenuList;
	}

	public static void setPathForGetTakeoutMenuList(
			List<String> pathForGetTakeoutMenuList) {
		PathForHTTP.pathForGetTakeoutMenuList = pathForGetTakeoutMenuList;
	}

	public static List<String> getPathForGetShopOrderList() {
		return pathForGetShopOrderList;
	}

	public static void setPathForGetShopOrderList(
			List<String> pathForGetShopOrderList) {
		PathForHTTP.pathForGetShopOrderList = pathForGetShopOrderList;
	}

	public static List<String> getPathForGetNearbyShop() {
		return pathForGetNearbyShop;
	}

	public static void setPathForGetNearbyShop(List<String> pathForGetNearbyShop) {
		PathForHTTP.pathForGetNearbyShop = pathForGetNearbyShop;
	}

	public static List<String> getPathForGetSeatType() {
		return pathForGetSeatType;
	}

	public static void setPathForGetSeatType(List<String> pathForGetSeatType) {
		PathForHTTP.pathForGetSeatType = pathForGetSeatType;
	}

	public static List<String> getPathForGetIntegralHome() {
		return pathForGetIntegralHome;
	}

	public static void setPathForGetIntegralHome(
			List<String> pathForGetIntegralHome) {
		PathForHTTP.pathForGetIntegralHome = pathForGetIntegralHome;
	}

	public static List<String> getPathForGetMyDashboard() {
		return pathForGetMyDashboard;
	}

	public static void setPathForGetMyDashboard(List<String> pathForGetMyDashboard) {
		PathForHTTP.pathForGetMyDashboard = pathForGetMyDashboard;
	}

	public static List<String> getPathForGetUserCenter() {
		return pathForGetUserCenter;
	}

	public static void setPathForGetUserCenter(List<String> pathForGetUserCenter) {
		PathForHTTP.pathForGetUserCenter = pathForGetUserCenter;
	}

	public static List<String> getPathForListCoupon() {
		return pathForListCoupon;
	}

	public static void setPathForListCoupon(List<String> pathForListCoupon) {
		PathForHTTP.pathForListCoupon = pathForListCoupon;
	}

	public static List<String> getPathForGetPromotionList() {
		return pathForGetPromotionList;
	}

	public static void setPathForGetPromotionList(
			List<String> pathForGetPromotionList) {
		PathForHTTP.pathForGetPromotionList = pathForGetPromotionList;
	}

	public static List<String> getPathForGetNormalTradeBill() {
		return pathForGetNormalTradeBill;
	}

	public static void setPathForGetNormalTradeBill(
			List<String> pathForGetNormalTradeBill) {
		PathForHTTP.pathForGetNormalTradeBill = pathForGetNormalTradeBill;
	}

	public static List<String> getPathForMenuBalance() {
		return pathForMenuBalance;
	}

	public static void setPathForMenuBalance(List<String> pathForMenuBalance) {
		PathForHTTP.pathForMenuBalance = pathForMenuBalance;
	}

	public static List<String> getPathForCleanMenuBalance() {
		return pathForCleanMenuBalance;
	}

	public static void setPathForCleanMenuBalance(
			List<String> pathForCleanMenuBalance) {
		PathForHTTP.pathForCleanMenuBalance = pathForCleanMenuBalance;
	}









	
}
