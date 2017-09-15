package com.dfire.wechat.util;

import java.util.ArrayList;
import java.util.List;


/**
 * if service run in tomcat in real server use url perfix "weixin-meal/"
 * for example "weixin-meal/shop/v1/get_state"
 * @author ljw
 *
 */
public class PathForPost {


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
	private static List<String> pathForRecommendMenu;
	
	// for pay 
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
	
	//for qjc
	private static List<String> pathForqjc;
	//for qjc_pre
	private static List<String> pathForqjc_pre;
	//for test
	private static List<String> john_test_url;
	
	//appchange
	private static List<String> searchshop;
	private static List<String> hotsearch;
	private static List<String> shopbanner;
	private static List<String> searchcondition;
	private static List<String> timeline;
	private static List<String> fooddairy;
	private static List<String> firemember;
	
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
		pathForModifyCart.add("modify");
		
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
		
		
		// for confirm order 
		pathForConfirmOrder = new ArrayList<String>();
		pathForConfirmOrder.add("orders/v1/confirm");
		
		// for modify memo 
		pathForModifyMemo = new ArrayList<String>();
		pathForModifyMemo.add("orders/v1/modify_memo");
		
		// for get order
		pathForGetOrder = new ArrayList<String>();
		pathForGetOrder.add("orders/v1/get_order");
		
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
		
		pathForRecommendMenu = new ArrayList<String>();
		pathForRecommendMenu.add("menus/v1/get_recommend_menus");
		
//////////////////////////////////for pay  ////////////////////////////////////////////

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
		
		
/////////////////// for qjc test//////////////		

		pathForqjc = new ArrayList<String>();
		pathForqjc.add("open-api/router");
/////////////////// for qjc pre/pub//////////////		

		pathForqjc_pre = new ArrayList<String>();
		pathForqjc_pre.add("router");
////////////////for test//////////////
		john_test_url = new ArrayList<String>();
		john_test_url.add("query");
		
//////for appchange-searchshop////////
		
		searchshop = new ArrayList<String>();
		searchshop.add("consumer-api/search/v1/shop");
		
		hotsearch = new ArrayList<String>();
		hotsearch.add("consumer-api/popular/search/conf/keywords");
		
		shopbanner = new ArrayList<String>();
		shopbanner.add("consumer-api/search/v1/banners");
		
		searchcondition = new ArrayList<String>();
		searchcondition.add("consumer-api/search/v1/condition");
		
		timeline = new ArrayList<String>();
		timeline.add("consumer-api/food/dairy/v1/shop_track");
		
		fooddairy = new ArrayList<String>();
		fooddairy.add("consumer-api/food/dairy/v1/meal_statistic");
		
		firemember = new ArrayList<String>();
		firemember.add("consumer-api/food/dairy/v1/my_fire_member_info");
		
	}

	

	public static List<String> getPathForqjc() {
		return pathForqjc;
	}

	public static List<String> getPathForqjc_pre(){
		return pathForqjc_pre;
	}
	public static List<String> getPathFortest(){
		return john_test_url;
	}
	
	public static List<String> getSearchShop(){
		return searchshop;
	}
	
	public static List<String> getHotSearch(){
		return hotsearch;
	}
	
	public static List<String> getShopBanner(){
		return shopbanner;
	}
	
	public static List<String> getSearchConditon(){
		return searchcondition;
	}
	
	public static List<String> getTimeLine(){
		return timeline;
	}
	
	public static List<String> getFoodDairy(){
		return fooddairy;
	}
	
	public static List<String> getFireMember(){
		return firemember;
	}


	public static List<String> getPathForLoadState() {
		return pathForLoadState;
	}

	public static void setPathForLoadState(List<String> pathForLoadState) {
		PathForPost.pathForLoadState = pathForLoadState;
	}

	public static List<String> getPathForBaseInfo() {
		return pathForBaseInfo;
	}

	public static void setPathForBaseInfo(List<String> pathForBaseInfo) {
		PathForPost.pathForBaseInfo = pathForBaseInfo;
	}

	public static List<String> getPathForApplyCard() {
		return pathForApplyCard;
	}

	public static void setPathForApplyCard(List<String> pathForApplyCard) {
		PathForPost.pathForApplyCard = pathForApplyCard;
	}

	public static List<String> getPathForCardList() {
		return pathForCardList;
	}

	public static void setPathForCardList(List<String> pathForCardList) {
		PathForPost.pathForCardList = pathForCardList;
	}

	public static List<String> getPathForDeleteCard() {
		return pathForDeleteCard;
	}

	public static void setPathForDeleteCard(List<String> pathForDeleteCard) {
		PathForPost.pathForDeleteCard = pathForDeleteCard;
	}

	public static List<String> getPathForGetCartCount() {
		return pathForGetCartCount;
	}

	public static void setPathForGetCartCount(List<String> pathForGetCartCount) {
		PathForPost.pathForGetCartCount = pathForGetCartCount;
	}

	public static List<String> getPathForCreateOwnCart() {
		return pathForCreateOwnCart;
	}

	public static void setPathForCreateOwnCart(List<String> pathForCreateOwnCart) {
		PathForPost.pathForCreateOwnCart = pathForCreateOwnCart;
	}

	public static List<String> getPathForGetCartData() {
		return pathForGetCartData;
	}

	public static void setPathForGetCartData(List<String> pathForGetCartData) {
		PathForPost.pathForGetCartData = pathForGetCartData;
	}

	public static List<String> getPathForModifyCart() {
		return pathForModifyCart;
	}

	public static void setPathForModifyCart(List<String> pathForModifyCart) {
		PathForPost.pathForModifyCart = pathForModifyCart;
	}

	public static List<String> getPathForClearCart() {
		return pathForClearCart;
	}

	public static void setPathForClearCart(List<String> pathForClearCart) {
		PathForPost.pathForClearCart = pathForClearCart;
	}

	public static List<String> getPathForClearOwnCart() {
		return pathForClearOwnCart;
	}

	public static void setPathForClearOwnCart(List<String> pathForClearOwnCart) {
		PathForPost.pathForClearOwnCart = pathForClearOwnCart;
	}

	public static List<String> getPathForModifyInfo() {
		return pathForModifyInfo;
	}

	public static void setPathForModifyInfo(List<String> pathForModifyInfo) {
		PathForPost.pathForModifyInfo = pathForModifyInfo;
	}

	public static List<String> getPathForGetRecommendMenuList() {
		return pathForGetRecommendMenuList;
	}

	public static void setPathForGetRecommendMenuList(
			List<String> pathForGetRecommendMenuList) {
		PathForPost.pathForGetRecommendMenuList = pathForGetRecommendMenuList;
	}

	public static List<String> getPathForGetRecommendMenuById() {
		return pathForGetRecommendMenuById;
	}

	public static void setPathForGetRecommendMenuById(
			List<String> pathForGetRecommendMenuById) {
		PathForPost.pathForGetRecommendMenuById = pathForGetRecommendMenuById;
	}

	public static List<String> getPathForGetCouponList() {
		return pathForGetCouponList;
	}

	public static void setPathForGetCouponList(List<String> pathForGetCouponList) {
		PathForPost.pathForGetCouponList = pathForGetCouponList;
	}

	public static List<String> getPathForOAuth() {
		return pathForOAuth;
	}

	public static void setPathForOAuth(List<String> pathForOAuth) {
		PathForPost.pathForOAuth = pathForOAuth;
	}

	public static List<String> getPathForOAuthConsumerCode() {
		return pathForOAuthConsumerCode;
	}

	public static void setPathForOAuthConsumerCode(
			List<String> pathForOAuthConsumerCode) {
		PathForPost.pathForOAuthConsumerCode = pathForOAuthConsumerCode;
	}

	public static List<String> getPathForOAuthEntityId() {
		return pathForOAuthEntityId;
	}

	public static void setPathForOAuthEntityId(List<String> pathForOAuthEntityId) {
		PathForPost.pathForOAuthEntityId = pathForOAuthEntityId;
	}

	public static List<String> getPathForOAuthMenuCode() {
		return pathForOAuthMenuCode;
	}

	public static void setPathForOAuthMenuCode(List<String> pathForOAuthMenuCode) {
		PathForPost.pathForOAuthMenuCode = pathForOAuthMenuCode;
	}

	public static List<String> getPathForOAuthCallback() {
		return pathForOAuthCallback;
	}

	public static void setPathForOAuthCallback(List<String> pathForOAuthCallback) {
		PathForPost.pathForOAuthCallback = pathForOAuthCallback;
	}

	public static List<String> getPathForaliShop() {
		return pathForaliShop;
	}

	public static void setPathForaliShop(List<String> pathForaliShop) {
		PathForPost.pathForaliShop = pathForaliShop;
	}

	public static List<String> getPathForPreCart() {
		return pathForPreCart;
	}

	public static void setPathForPreCart(List<String> pathForPreCart) {
		PathForPost.pathForPreCart = pathForPreCart;
	}

	public static List<String> getPathForGetMenus() {
		return pathForGetMenus;
	}

	public static void setPathForGetMenus(List<String> pathForGetMenus) {
		PathForPost.pathForGetMenus = pathForGetMenus;
	}

	public static List<String> getPathForModifyPeopleAndMemo() {
		return pathForModifyPeopleAndMemo;
	}

	public static void setPathForModifyPeopleAndMemo(
			List<String> pathForModifyPeopleAndMemo) {
		PathForPost.pathForModifyPeopleAndMemo = pathForModifyPeopleAndMemo;
	}

	public static List<String> getPathForImportUserCart() {
		return pathForImportUserCart;
	}

	public static void setPathForImportUserCart(List<String> pathForImportUserCart) {
		PathForPost.pathForImportUserCart = pathForImportUserCart;
	}

	public static List<String> getPathForGetUserCart() {
		return pathForGetUserCart;
	}

	public static void setPathForGetUserCart(List<String> pathForGetUserCart) {
		PathForPost.pathForGetUserCart = pathForGetUserCart;
	}

	public static List<String> getPathForGetPrePayBill() {
		return pathForGetPrePayBill;
	}

	public static void setPathForGetPrePayBill(List<String> pathForGetPrePayBill) {
		PathForPost.pathForGetPrePayBill = pathForGetPrePayBill;
	}

	public static List<String> getPathForConfirmPrepayOrder() {
		return pathForConfirmPrepayOrder;
	}

	public static void setPathForConfirmPrepayOrder(
			List<String> pathForConfirmPrepayOrder) {
		PathForPost.pathForConfirmPrepayOrder = pathForConfirmPrepayOrder;
	}

	public static List<String> getPathForDeletePrepayOrder() {
		return pathForDeletePrepayOrder;
	}

	public static void setPathForDeletePrepayOrder(
			List<String> pathForDeletePrepayOrder) {
		PathForPost.pathForDeletePrepayOrder = pathForDeletePrepayOrder;
	}

	public static List<String> getPathForGetPrepayOrder() {
		return pathForGetPrepayOrder;
	}

	public static void setPathForGetPrepayOrder(List<String> pathForGetPrepayOrder) {
		PathForPost.pathForGetPrepayOrder = pathForGetPrepayOrder;
	}

	public static List<String> getPathForGetBillInfo() {
		return pathForGetBillInfo;
	}

	public static void setPathForGetBillInfo(List<String> pathForGetBillInfo) {
		PathForPost.pathForGetBillInfo = pathForGetBillInfo;
	}

	public static List<String> getPathForLockPayWaitingOrder() {
		return pathForLockPayWaitingOrder;
	}

	public static void setPathForLockPayWaitingOrder(
			List<String> pathForLockPayWaitingOrder) {
		PathForPost.pathForLockPayWaitingOrder = pathForLockPayWaitingOrder;
	}

	public static List<String> getPathForGetPrepayBill() {
		return pathForGetPrepayBill;
	}

	public static void setPathForGetPrepayBill(List<String> pathForGetPrepayBill) {
		PathForPost.pathForGetPrepayBill = pathForGetPrepayBill;
	}

	public static List<String> getPathForLockOrder() {
		return pathForLockOrder;
	}

	public static void setPathForLockOrder(List<String> pathForLockOrder) {
		PathForPost.pathForLockOrder = pathForLockOrder;
	}

	public static List<String> getPathForUnLockPayOrder() {
		return pathForUnLockPayOrder;
	}

	public static void setPathForUnLockPayOrder(List<String> pathForUnLockPayOrder) {
		PathForPost.pathForUnLockPayOrder = pathForUnLockPayOrder;
	}

	public static List<String> getPathForCheckOrder() {
		return pathForCheckOrder;
	}

	
	public static void setPathForCheckOrder(List<String> pathForCheckOrder) {
		PathForPost.pathForCheckOrder = pathForCheckOrder;
	}

	public static List<String> getPathForCheckOrderChange() {
		return pathForCheckOrderChange;
	}

	public static void setPathForCheckOrderChange(
			List<String> pathForCheckOrderChange) {
		PathForPost.pathForCheckOrderChange = pathForCheckOrderChange;
	}

	public static List<String> getPathForConfirmOrder() {
		return pathForConfirmOrder;
	}

	public static void setPathForConfirmOrder(List<String> pathForConfirmOrder) {
		PathForPost.pathForConfirmOrder = pathForConfirmOrder;
	}

	public static List<String> getPathForModifyMemo() {
		return pathForModifyMemo;
	}

	public static void setPathForModifyMemo(List<String> pathForModifyMemo) {
		PathForPost.pathForModifyMemo = pathForModifyMemo;
	}

	public static List<String> getPathForGetOrder() {
		return pathForGetOrder;
	}

	public static void setPathForGetOrder(List<String> pathForGetOrder) {
		PathForPost.pathForGetOrder = pathForGetOrder;
	}

	public static List<String> getPathForGetKoubeiOrder() {
		return pathForGetKoubeiOrder;
	}

	public static void setPathForGetKoubeiOrder(List<String> pathForGetKoubeiOrder) {
		PathForPost.pathForGetKoubeiOrder = pathForGetKoubeiOrder;
	}

	public static List<String> getPathForGetOrderHistory() {
		return pathForGetOrderHistory;
	}

	public static void setPathForGetOrderHistory(
			List<String> pathForGetOrderHistory) {
		PathForPost.pathForGetOrderHistory = pathForGetOrderHistory;
	}

	public static List<String> getPathForReIssuedPreOrder() {
		return pathForReIssuedPreOrder;
	}

	public static void setPathForReIssuedPreOrder(
			List<String> pathForReIssuedPreOrder) {
		PathForPost.pathForReIssuedPreOrder = pathForReIssuedPreOrder;
	}

	public static List<String> getPathForShowTitle() {
		return pathForShowTitle;
	}

	public static void setPathForShowTitle(List<String> pathForShowTitle) {
		PathForPost.pathForShowTitle = pathForShowTitle;
	}

	public static List<String> getPathForListMenus() {
		return pathForListMenus;
	}

	public static void setPathForListMenus(List<String> pathForListMenus) {
		PathForPost.pathForListMenus = pathForListMenus;
	}

	public static List<String> getPathForForceMenu() {
		return pathForForceMenu;
	}

	public static void setPathForForceMenu(List<String> pathForForceMenu) {
		PathForPost.pathForForceMenu = pathForForceMenu;
	}

	public static List<String> getPathForRecommendMenu() {
		return pathForRecommendMenu;
	}

	public static void setPathForRecommendMenu(List<String> pathForRecommendMenu) {
		PathForPost.pathForRecommendMenu = pathForRecommendMenu;
	}

	public static List<String> getPathForListCartsPrivilege() {
		return pathForListCartsPrivilege;
	}

	public static void setPathForListCartsPrivilege(
			List<String> pathForListCartsPrivilege) {
		PathForPost.pathForListCartsPrivilege = pathForListCartsPrivilege;
	}

	public static List<String> getPathForAddToCarts() {
		return pathForAddToCarts;
	}

	public static void setPathForAddToCarts(List<String> pathForAddToCarts) {
		PathForPost.pathForAddToCarts = pathForAddToCarts;
	}

	public static List<String> getPathForGetTradeBill() {
		return pathForGetTradeBill;
	}

	public static void setPathForGetTradeBill(List<String> pathForGetTradeBill) {
		PathForPost.pathForGetTradeBill = pathForGetTradeBill;
	}

	public static List<String> getPathForPlayComments() {
		return pathForPlayComments;
	}

	public static void setPathForPlayComments(List<String> pathForPlayComments) {
		PathForPost.pathForPlayComments = pathForPlayComments;
	}

	public static List<String> getPathForGetActivitySwitch() {
		return pathForGetActivitySwitch;
	}

	public static void setPathForGetActivitySwitch(
			List<String> pathForGetActivitySwitch) {
		PathForPost.pathForGetActivitySwitch = pathForGetActivitySwitch;
	}

	public static List<String> getPathForConfirmPrePay() {
		return pathForConfirmPrePay;
	}

	public static void setPathForConfirmPrePay(List<String> pathForConfirmPrePay) {
		PathForPost.pathForConfirmPrePay = pathForConfirmPrePay;
	}

	public static List<String> getPathForGetPayType() {
		return pathForGetPayType;
	}

	public static void setPathForGetPayType(List<String> pathForGetPayType) {
		PathForPost.pathForGetPayType = pathForGetPayType;
	}

	public static List<String> getPathForGetToken() {
		return pathForGetToken;
	}

	public static void setPathForGetToken(List<String> pathForGetToken) {
		PathForPost.pathForGetToken = pathForGetToken;
	}

	public static List<String> getPathForCardPay() {
		return pathForCardPay;
	}

	public static void setPathForCardPay(List<String> pathForCardPay) {
		PathForPost.pathForCardPay = pathForCardPay;
	}

	public static List<String> getPathForGetShopTax() {
		return pathForGetShopTax;
	}

	public static void setPathForGetShopTax(List<String> pathForGetShopTax) {
		PathForPost.pathForGetShopTax = pathForGetShopTax;
	}

	public static List<String> getPathForGetPersonInfo() {
		return pathForGetPersonInfo;
	}

	public static void setPathForGetPersonInfo(List<String> pathForGetPersonInfo) {
		PathForPost.pathForGetPersonInfo = pathForGetPersonInfo;
	}

	public static List<String> getPathForGetUserInfo() {
		return pathForGetUserInfo;
	}

	public static void setPathForGetUserInfo(List<String> pathForGetUserInfo) {
		PathForPost.pathForGetUserInfo = pathForGetUserInfo;
	}

	public static List<String> getPathForGetPopupMessage() {
		return pathForGetPopupMessage;
	}

	public static void setPathForGetPopupMessage(
			List<String> pathForGetPopupMessage) {
		PathForPost.pathForGetPopupMessage = pathForGetPopupMessage;
	}

	public static List<String> getPathForGetCartPrivilege() {
		return pathForGetCartPrivilege;
	}

	public static void setPathForGetCartPrivilege(
			List<String> pathForGetCartPrivilege) {
		PathForPost.pathForGetCartPrivilege = pathForGetCartPrivilege;
	}

	public static List<String> getPathForGetTradeBillV2() {
		return pathForGetTradeBillV2;
	}

	public static void setPathForGetTradeBillV2(List<String> pathForGetTradeBillV2) {
		PathForPost.pathForGetTradeBillV2 = pathForGetTradeBillV2;
	}

	public static List<String> getPathForGetGiveCoupon() {
		return pathForGetGiveCoupon;
	}

	public static void setPathForGetGiveCoupon(List<String> pathForGetGiveCoupon) {
		PathForPost.pathForGetGiveCoupon = pathForGetGiveCoupon;
	}

	public static List<String> getPathForGetOrderComments() {
		return pathForGetOrderComments;
	}

	public static void setPathForGetOrderComments(
			List<String> pathForGetOrderComments) {
		PathForPost.pathForGetOrderComments = pathForGetOrderComments;
	}





	
}
