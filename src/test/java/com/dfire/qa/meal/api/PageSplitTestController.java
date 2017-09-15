package com.dfire.qa.meal.api;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONPath;
import com.dfire.mc.promotion.domain.UseRangeType;
import com.dfire.mc.promotion.service.IPromotionClientService;
import com.dfire.qa.meal.bean.MenuGet;
import com.dfire.qa.meal.bean.Response;
import com.dfire.qa.meal.bean.ResultMap;
import com.dfire.qa.meal.constant.Constants;
import com.dfire.qa.meal.data.PageSplitTestData;
import com.dfire.qa.meal.httpclient.HTTPClientService;
import com.dfire.qa.meal.logger.LoggerMarkers;
import com.dfire.qa.meal.logger.MealLoggerFactory;
import com.dfire.qa.meal.service.IBossService;
import com.dfire.qa.meal.service.ICardService;
import com.dfire.qa.meal.service.ICartService;
import com.dfire.qa.meal.service.IIntegralMallService;
import com.dfire.qa.meal.service.IIntegrationService;
import com.dfire.qa.meal.service.IOrderService;
import com.dfire.qa.meal.service.IPrivilegeService;
import com.dfire.qa.meal.service.IShopService;
import com.dfire.qa.meal.service.ITakeoutMenuService;
import com.dfire.qa.meal.service.IUserCenterService;
import com.dfire.qa.meal.service.impl.TakeoutMenuServiceImpl;
import com.dfire.qa.meal.service.impl.UserCenterServiceImpl;
import com.dfire.qa.meal.vo.boss.TakeOutDeliveryMan;
import com.dfire.qa.meal.vo.cart.CartIncludeSuitForm;
import com.dfire.soa.consumer.integral.IIntergralMallService;
import com.dfire.soa.consumer.param.MenuListQueryRequest;
import com.dfire.soa.consumer.param.NearbyShop;
import com.dfire.soa.consumer.service.IConsumerPrivilegeService;
import com.dfire.soa.consumer.service.IMenuService;
import com.dfire.soa.consumer.service.IQueueService;
import com.dfire.soa.consumer.service.IShopConfigService;
import com.dfire.soa.consumer.vo.KindMenusAndTitleVo;
import com.dfire.soa.consumer.vo.KindMenusVo;
import com.dfire.soa.consumer.vo.MenusVo;
import com.dfire.soa.consumer.vo.NearbyShopVo;
import com.dfire.soa.consumer.vo.ShopMemberCardVo;
import com.dfire.soa.consumer.vo.integral.IntegralHomeDetailedVo;
import com.dfire.soa.consumer.vo.integral.IntegralHomePageVo;
import com.dfire.soa.consumer.vo.order.OrderVo;
import com.dfire.soa.consumer.vo.privilege.DiscountDogVo;
import com.dfire.soa.consumer.vo.privilege.PrivilegeVo;
import com.dfire.soa.consumer.vo.queue.EntitySeatInfo;
import com.dfire.soa.consumer.vo.queue.EntitySeatVo;
import com.dfire.soa.order.bo.Order;
import com.dfire.soa.order.service.IGetOrderService;
import com.dfire.soa.order.test.ITestOrderService;
import com.dfire.soa.shopmember.service.ICustomerRegisterCardService;
import com.dfire.soa.shopmember.vo.CardDataVo;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.twodfire.share.result.Result;


public class PageSplitTestController extends BaseTestController{
	
	
	
	@Resource
	IUserCenterService userCenterServiceImpl;
	
	
	@Resource
	IIntegralMallService integralMallService;
	
	@Resource
	IShopService shopService;
	
	@Resource
	IIntegrationService integrationService;
	
	@Resource
	IOrderService orderService;
	
	@Resource
	ICartService cartService;
	
	@Resource
	ICardService cardService;
	
	@Autowired
	IPrivilegeService privilegeService;
	
	
	@Resource
	ITakeoutMenuService takeoutMenuService;
	
	@Resource
	IBossService bossService;
	
	
	@Resource
	HTTPClientService httpClientService;

	
	
	@Resource
	ICustomerRegisterCardService customerRegisterCardService;
	
	
    @Resource
    IConsumerPrivilegeService consumerPrivilegeService;
	
    
    
    @Resource
    com.dfire.soa.consumer.service.IPrivilegeService concumerPrivilegeService;
    
    @Resource
    IMenuService consumerMenuService;
    
    
    @Autowired
    com.dfire.soa.consumer.service.order.IOrderService orderServiceImpl;
    
    
    @Resource
    IShopConfigService shopConfigService;
    
    
    @Resource
    private IQueueService queueService;
    
    
    @Resource
    private IIntergralMallService intergralMallService;
    
    
    @Autowired
    com.dfire.soa.consumer.service.IPrivilegeService privilegeServiceImpl;
    
    
    @Resource
    IPromotionClientService promotionClientService;
    
    
    @Resource
    ITestOrderService orderTestService;
    
    
    @Resource
    IGetOrderService getOrderService;
    
	@BeforeClass(alwaysRun = true)
	public void Setup() throws Exception{
		
		baseParamBean = commonService.getBaseParam(environment);
		
		
	}
	
	
	
	
	@AfterClass(alwaysRun = true)
	public void TearDown() throws Exception{
		
	}
	
	
	
	/////////////////////////////   页面拆分--一期             /////////////////////////////////////////
	@Test(description = "本店会员卡", dataProvider = "memberShipCard",
			dataProviderClass = PageSplitTestData.class, groups = {"smoke", "all"})
	public void memberShipCard(String description, String customerRegisterId, int responseStatus, int resultCode) throws Exception{
				
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.MEMBERSHIPCARD);
		
		Response cardListResponse = cardService.getCardsList(baseParamBean, environment);
		JsonObject cardListJsonObject = new JsonParser().parse(cardListResponse.getResponseStr()).getAsJsonObject();
		
		Map<String, JsonObject> cardMap = new HashMap<String, JsonObject>();
		for(JsonElement element : cardListJsonObject.get("data").getAsJsonArray()){
			
			String id = (element.getAsJsonObject().get("id").getAsString().isEmpty())?(element.getAsJsonObject().get("kindCardId").getAsString()) : (element.getAsJsonObject().get("id").getAsString());
			cardMap.put(id, element.getAsJsonObject());
			
		}
		
		Result<List<CardDataVo>> memberRs = customerRegisterCardService.queryCardAndEnableApplyCardList(customerRegisterId, baseParamBean.getEntityId());
		
		Assert.assertEquals(cardMap.size(), memberRs.getModel().size(), Constants.Error.MEMBERNOTEQUAL);
		
		for(CardDataVo cardDataVo : memberRs.getModel()){
		
			// 会员卡未申领
			if(1 == cardDataVo.getIsApply()){
				
				JsonObject matchCard = cardMap.get(cardDataVo.getKindCardId());
				Assert.assertTrue(cardDataVo.getEntityId().equalsIgnoreCase(matchCard.get("entityId").getAsString()), Constants.Error.ENTITYIDNOTEQUAL);								
				
			}else{
				
				// 会员卡已申领			
				JsonObject matchCard = cardMap.get(cardDataVo.getCardId());
				Assert.assertTrue(cardDataVo.getEntityId().equalsIgnoreCase(matchCard.get("entityId").getAsString()), Constants.Error.ENTITYIDNOTEQUAL);
				
				Assert.assertTrue(cardDataVo.getBalance().toString().equalsIgnoreCase(matchCard.get("balance").getAsString()), Constants.Error.BALANCENOTEQUAL);   // 余额
				Assert.assertTrue(cardDataVo.getDegree().toString().equalsIgnoreCase(matchCard.get("degree").getAsString()), Constants.Error.DEGREENOTEQUAL);
				
				Assert.assertTrue(cardDataVo.getInnerCode().toString().equalsIgnoreCase(matchCard.get("innerCode").getAsString()), Constants.Error.MEMBERNONOTEQUAL);   // 会员卡编号
			}
				
			
		}
		
		
	}
	
	
	
	@Test(description = "本店优惠", dataProvider = "shopCoupon",
			dataProviderClass = PageSplitTestData.class, groups = {"smoke", "all"})
	public void shopCoupon(String description, String customerRegisterId, int responseStatus, int resultCode) throws Exception{
		
		Integer useRange = 1;
		Response privilegeList = privilegeService.getPrivilegeList(baseParamBean, useRange, environment);
		
		JsonObject privilegeListJsonObject = new JsonParser().parse(privilegeList.getResponseStr()).getAsJsonObject().get("data").getAsJsonObject();
		
		Map<String, JsonObject> privilegeMap = new HashMap<String, JsonObject>();
		for(JsonElement element : privilegeListJsonObject.get("cardDataVoList").getAsJsonArray()){
			
			String id = element.getAsJsonObject().get("id").getAsString();
			privilegeMap.put(id, element.getAsJsonObject());
			
		}
		
		
		Result<PrivilegeVo> result = consumerPrivilegeService.getPrivilegeItermsList(baseParamBean.getEntityId(), customerRegisterId, useRange);
		
		Assert.assertEquals(privilegeMap.size(), result.getModel().getCardDataVoList().size(), Constants.Error.MEMBERNOTEQUAL);
		
		for(com.dfire.soa.consumer.vo.privilege.CardDataVo cardDataVo : result.getModel().getCardDataVoList()){
			
			JsonObject matchCard = privilegeMap.get(cardDataVo.getId());
			
			Assert.assertTrue(cardDataVo.getCardEntityId().equalsIgnoreCase(matchCard.get("cardEntityId").getAsString()), Constants.Error.ENTITYIDNOTEQUAL);
			Assert.assertTrue(cardDataVo.getKindCardId().equalsIgnoreCase(matchCard.get("kindCardId").getAsString()), Constants.Error.KINDCARDIDNOTEQUAL);
			Assert.assertTrue(cardDataVo.getCode().equalsIgnoreCase(matchCard.get("code").getAsString()), Constants.Error.MEMBERNONOTEQUAL);
			
		}
	}
	
	
	
	
	
	@Test(description = "我的会员卡", dataProvider = "myCardList",
			dataProviderClass = PageSplitTestData.class, groups = {"smoke", "all"})
	public void myCardList(String description, String customerRegisterId, int responseStatus, int resultCode) throws Exception{
		
		Integer page = 1;
		Integer pageSize = 10;
		Response allMyCardList = cardService.getAllMyCardList(baseParamBean, page, pageSize, environment);
		
		JsonObject cardListJsonObject = new JsonParser().parse(allMyCardList.getResponseStr()).getAsJsonObject();
		
		Map<String, JsonObject> cardMap = new HashMap<String, JsonObject>();
		for(JsonElement element : cardListJsonObject.get("data").getAsJsonArray()){
			
			String id = element.getAsJsonObject().get("id").getAsString();
			cardMap.put(id, element.getAsJsonObject());
			
		}
		
		Result<List<CardDataVo>> cardDataVoListResult = customerRegisterCardService.queryListByTmidForPage(customerRegisterId, page, pageSize);
		
		Assert.assertEquals(cardMap.size(), cardDataVoListResult.getModel().size(), Constants.Error.MEMBERNOTEQUAL);
		
		for(CardDataVo cardDataVo : cardDataVoListResult.getModel()){
			
			JsonObject matchCard = cardMap.get(cardDataVo.getCardId());
			Assert.assertTrue(cardDataVo.getEntityId().equalsIgnoreCase(matchCard.get("entityId").getAsString()), Constants.Error.ENTITYIDNOTEQUAL);
			
			MealLoggerFactory.LOG_FILES_LOGGER.debug(LoggerMarkers.LOGFILE, "dubbo value balance is : " + cardDataVo.getBalance().toString() +
					",  api balance value is : " + matchCard.get("balance").getAsLong());
			
			MealLoggerFactory.LOG_FILES_LOGGER.debug(LoggerMarkers.LOGFILE, "dubbo value balance is : " + cardDataVo.getDegree().toString() + 
					",  api balance value is : " + matchCard.get("degree").getAsDouble());
			
			Assert.assertTrue(cardDataVo.getBalance().toString().equalsIgnoreCase(Long.toString(matchCard.get("balance").getAsLong())), Constants.Error.BALANCENOTEQUAL);	
			
			Assert.assertTrue( 0 == cardDataVo.getDegree().compareTo(matchCard.get("degree").getAsInt()), Constants.Error.DEGREENOTEQUAL);
			
			Assert.assertTrue(cardDataVo.getKindCardId().equalsIgnoreCase(matchCard.get("kindCardId").getAsString()), Constants.Error.KINDCARDIDNOTEQUAL);
			Assert.assertTrue(cardDataVo.getCode().equalsIgnoreCase(matchCard.get("code").getAsString()), Constants.Error.MEMBERNONOTEQUAL);
			
		}
	}
	
	
	
	
	@Test(description = "我的优惠券", dataProvider = "myCouponList",
			dataProviderClass = PageSplitTestData.class, groups = {"smoke", "all"})
	public void myCouponList(String description, String customerRegisterId, int responseStatus, int resultCode) throws Exception{
		
		Integer page = 1;
		Integer pageSize = 10;
		Response allMyCouponList = cardService.getAllMyCouponList(baseParamBean, page, pageSize, environment);
		
		JsonObject couponListJsonObject = new JsonParser().parse(allMyCouponList.getResponseStr()).getAsJsonObject();
		
		Map<String, JsonObject> couponMap = new HashMap<String, JsonObject>();
		for(JsonElement element : couponListJsonObject.get("data").getAsJsonArray()){
			
			String id = element.getAsJsonObject().get("promotionCustomerId").getAsString();
			couponMap.put(id, element.getAsJsonObject());
			
		}
		
		Result result = concumerPrivilegeService.getMyPrivilege(customerRegisterId);
		
		@SuppressWarnings("unchecked")
		List<DiscountDogVo> DiscountDogVoList = (List<DiscountDogVo>)result.getModel();
		
		Assert.assertEquals(couponMap.size(), DiscountDogVoList.size(), Constants.Error.COUPONNOTEQUAL);
		
		for(DiscountDogVo cardDataVo : DiscountDogVoList){
			
			JsonObject matchCoupon = couponMap.get(cardDataVo.getPromotionCustomerId());
			Assert.assertTrue(cardDataVo.getEntityId().equalsIgnoreCase(matchCoupon.get("entityId").getAsString()), Constants.Error.ENTITYIDNOTEQUAL);			
			Assert.assertTrue(cardDataVo.getPromotionId().toString().equalsIgnoreCase(matchCoupon.get("promotionId").getAsString()), Constants.Error.COUPONTYPENOTEQUAL);				
			
			Assert.assertTrue(cardDataVo.getEntityName().equalsIgnoreCase(matchCoupon.get("entityName").getAsString()), Constants.Error.ENTITYNAMENOTEQUAL);			
			Assert.assertTrue(cardDataVo.getDiscount() == matchCoupon.get("discount").getAsInt(), Constants.Error.DISCOUNTNOTEQUAL);
			
			
		}
		
	}
	
	
	
	@Test(description = "本店外卖菜单", dataProvider = "takeOutMenuList",
			dataProviderClass = PageSplitTestData.class, groups = {"temp"})
	public void takeOutMenuList(String description, String customerRegisterId, int responseStatus, int resultCode) throws Exception{
		

		Response allMyMenuList = takeoutMenuService.getTakeoutMenuList(baseParamBean, environment);
		
		JsonObject menuListJsonObject = new JsonParser().parse(allMyMenuList.getResponseStr()).getAsJsonObject().get("data").getAsJsonObject();
		
		Map<String, JsonObject> menuMap = new HashMap<String, JsonObject>();
		for(JsonElement element : menuListJsonObject.get("kindMenusVos").getAsJsonArray()){
			
			JsonArray menuListArray = element.getAsJsonObject().get("menus").getAsJsonArray();
			
			for(JsonElement menuElement : menuListArray){
				
				String id = menuElement.getAsJsonObject().get("id").getAsString();
				menuMap.put(id, menuElement.getAsJsonObject());
			}
			
		}
		
		MenuListQueryRequest menuListQueryRequest = new MenuListQueryRequest(baseParamBean.getEntityId());
        menuListQueryRequest.setRepeat(false);
        menuListQueryRequest.setCustomerRegisterId(customerRegisterId);
        menuListQueryRequest.setUseRangeType(UseRangeType.TAKEOUT);
        menuListQueryRequest.setType(0);
	    Result<KindMenusAndTitleVo> result = consumerMenuService.queryMenuList(menuListQueryRequest);
		

	    KindMenusVo[] kindMenusVos = result.getModel().getKindMenusVos();
		
		int menuLength = 0;
		for(KindMenusVo kindMenusVo : kindMenusVos){
			
			menuLength += kindMenusVo.getMenus().size();
			
			for(MenusVo menusVo : kindMenusVo.getMenus()){
				
				JsonObject matchMenu = menuMap.get(menusVo.getId());
				Assert.assertTrue(menusVo.getEntityId().equalsIgnoreCase(matchMenu.get("entityId").getAsString()), Constants.Error.ENTITYIDNOTEQUAL);			
				Assert.assertTrue(menusVo.getName().toString().equalsIgnoreCase(matchMenu.get("name").getAsString()), Constants.Error.NAMENOTEQUAL);				
				
				Assert.assertTrue(menusVo.getKindMenuId().equalsIgnoreCase(matchMenu.get("kindMenuId").getAsString()), Constants.Error.KINDMENUIDNOTEQUAL);			
				Assert.assertTrue(0 == menusVo.getPrice().compareTo(matchMenu.get("price").getAsDouble()), Constants.Error.PRICENOTEQUAL);
			}
			
		}
		
		Assert.assertEquals(menuMap.size(), menuLength, Constants.Error.MENUNOTEQUAL);
		
	}
	
	
	
	
	@Test(description = "本店堂食菜单", dataProvider = "menuList",
			dataProviderClass = PageSplitTestData.class, groups = {"smoke", "all"})
	public void menuList(String description, String customerRegisterId, int responseStatus, int resultCode) throws Exception{
		

		Response menuList = cartService.listMenus(baseParamBean, otherParameter, environment);
		
		JsonObject menuListJsonObject = new JsonParser().parse(menuList.getResponseStr()).getAsJsonObject().get("data").getAsJsonObject();
		
		Map<String, JsonObject> menuMap = new HashMap<String, JsonObject>();
		for(JsonElement element : menuListJsonObject.get("kindMenusVos").getAsJsonArray()){
			
			JsonArray menuListArray = element.getAsJsonObject().get("menus").getAsJsonArray();
			
			for(JsonElement menuElement : menuListArray){
				
				String id = menuElement.getAsJsonObject().get("id").getAsString();
				menuMap.put(id, menuElement.getAsJsonObject());
			}
			
		}
		
		
		MenuListQueryRequest menuListQueryRequest = new MenuListQueryRequest(baseParamBean.getEntityId());
        menuListQueryRequest.setRepeat(false);
        menuListQueryRequest.setCustomerRegisterId(customerRegisterId);
        menuListQueryRequest.setUseRangeType(UseRangeType.TANGSHI);
        menuListQueryRequest.setType(0); // 如果这家店使用 支部包商家, 并且会有优惠才会是 1,,否则显示为 0
	    Result<KindMenusAndTitleVo> result = consumerMenuService.queryMenuList(menuListQueryRequest);
		

	    KindMenusVo[] kindMenusVos = result.getModel().getKindMenusVos();
		
		int menuLength = 0;
		for(KindMenusVo kindMenusVo : kindMenusVos){
			
			menuLength += kindMenusVo.getMenus().size();
			
			for(MenusVo menusVo : kindMenusVo.getMenus()){
				
				JsonObject matchMenu = menuMap.get(menusVo.getId());
				Assert.assertTrue(menusVo.getEntityId().equalsIgnoreCase(matchMenu.get("entityId").getAsString()), Constants.Error.ENTITYIDNOTEQUAL);			
				Assert.assertTrue(menusVo.getName().toString().equalsIgnoreCase(matchMenu.get("name").getAsString()), Constants.Error.NAMENOTEQUAL);				
				
				Assert.assertTrue(menusVo.getKindMenuId().equalsIgnoreCase(matchMenu.get("kindMenuId").getAsString()), Constants.Error.KINDMENUIDNOTEQUAL);			
				Assert.assertTrue(0 == menusVo.getPrice().compareTo(matchMenu.get("price").getAsDouble()), Constants.Error.PRICENOTEQUAL);
			}
			
		}
		
		Assert.assertEquals(menuMap.size(), menuLength, Constants.Error.MENUNOTEQUAL);
		
	}
	

	
	
	/////////////////////////////   页面拆分--二期             /////////////////////////////////////////
	@Test(description = "已下单的菜", dataProvider = "orderInfo",
			dataProviderClass = PageSplitTestData.class, groups = {"smoke", "all"})
	public void orderInfo(String description, int responseStatus, int resultCode) throws Exception{
		

		MenuGet waitingMenuGet = new MenuGet();
		waitingMenuGet.setNormalMenu(true);
		waitingMenuGet.setNormalMenuNo(1);
		
		MenuGet orderMenuGet = new MenuGet();
		orderMenuGet.setSuitMenu(true);
		orderMenuGet.setSuitMenuNo(1);
		
		
		// 下单(其中一个订单未支付, 另一个订单已支付)
//		ResultMap waitingOrderResult = integrationService.payNoPrePaySeat(baseParamBean, otherParameter, waitingMenuGet, environment, false);
		ResultMap orderResult = integrationService.payNoPrePaySeat(baseParamBean, otherParameter, orderMenuGet, environment, true);
		
//		Assert.assertTrue(waitingOrderResult.get("code").toString().equalsIgnoreCase("1"));
		Assert.assertTrue(orderResult.get("code").toString().equalsIgnoreCase("1"));
		
		
		
		Response orderResponse = orderService.getOrder(baseParamBean, environment);
		
		JsonObject menuListJsonObject = new JsonParser().parse(orderResponse.getResponseStr()).getAsJsonObject().get("data").getAsJsonObject();
		
		Map<String, JsonObject> waitingOrderMap = new HashMap<String, JsonObject>();
		for(JsonElement element : menuListJsonObject.get("waitingOrderVos").getAsJsonArray()){
			
			String waitingOrderId = element.getAsJsonObject().get("waitingOrderItems").getAsJsonObject().get("waitingOrderId").getAsString();

			waitingOrderMap.put(waitingOrderId, element.getAsJsonObject());
		
		}
		
		
		Map<String, JsonObject> orderMap = new HashMap<String, JsonObject>();
		for(JsonElement element : menuListJsonObject.get("orderVos").getAsJsonArray()){
			
			String orderId = element.getAsJsonObject().get("orderItems").getAsJsonObject().get("orderId").getAsString();

			orderMap.put(orderId, element.getAsJsonObject());
		
		}
		
		
		
		Map<String, JsonObject> historyMenuMap = new HashMap<String, JsonObject>();
		for(JsonElement element : menuListJsonObject.get("history4hOrderVos").getAsJsonArray()){
			
			String orderId = element.getAsJsonObject().get("orderId").getAsString();

			historyMenuMap.put(orderId, element.getAsJsonObject());
			
		}
		
		
		
//		@SuppressWarnings("unchecked")
//		List<CartIncludeSuitForm> expectedWaitingCartMenu = (List<CartIncludeSuitForm>)waitingOrderResult.get("data");
//		for(CartIncludeSuitForm element : expectedWaitingCartMenu){
//			
//			JsonObject matchOrder = waitingOrderMap.get(waitingOrderResult.get("id"));
//			
//			Assert.assertTrue(element.getMenuName().equalsIgnoreCase(matchOrder.get("name").getAsString()));
//			Assert.assertTrue(0 == element.getNum().compareTo(matchOrder.get("num").getAsDouble()));
//			
//	
//		}
		
		
		
		@SuppressWarnings("unchecked")
		List<CartIncludeSuitForm> expectedCartMenu = (List<CartIncludeSuitForm>)orderResult.get("data");
		for(CartIncludeSuitForm element : expectedCartMenu){
			
			JsonObject matchOrder = historyMenuMap.get(orderResult.get("id"));
//			JsonArray orderItems = matchOrder.get("orderItems").getAsJsonArray();
			
			@SuppressWarnings("unchecked")
			List<String> menuIds = (List<String>) JSONPath.read(matchOrder.toString(), "$.orderItems[0].orderMenus[0:-1].menuId"); 
			Assert.assertTrue(menuIds.contains(element.getMenuId()));

			
	
		}
		
		
	}
	
	
	
	
	
	
	
	@Test(description = "本店订单", dataProvider = "shopOrder",
			dataProviderClass = PageSplitTestData.class, groups = {"smoke", "all"})
	public void shopOrder(String description, String customerRegisterId, int responseStatus, int resultCode) throws Exception{
		

		Integer page = 1;
		Integer pageSize = 10;
		Response orderList = orderService.getShopOrderList(baseParamBean, page, pageSize, environment);
		
		JsonObject orderListJsonObject = new JsonParser().parse(orderList.getResponseStr()).getAsJsonObject();
		
		Map<String, JsonObject> orderMap = new HashMap<String, JsonObject>();
		for(JsonElement element : orderListJsonObject.get("data").getAsJsonArray()){
			
			String id = element.getAsJsonObject().get("orderId").getAsString();
	
			orderMap.put(id, element.getAsJsonObject());
			
			
		}
		
		
		Result<List<OrderVo>> result = orderServiceImpl.getShopOrdersList(customerRegisterId, baseParamBean.getEntityId(), page, pageSize);
		
		for(OrderVo orderVo : result.getModel()){
	
			JsonObject matchOrder = orderMap.get(orderVo.getOrderId());
			Assert.assertTrue(orderVo.getEntityId().equalsIgnoreCase(matchOrder.get("entityId").getAsString()), Constants.Error.ENTITYIDNOTEQUAL);			
			Assert.assertTrue(orderVo.getPayStatus() == matchOrder.get("payStatus").getAsInt(), Constants.Error.PAYSTATUSNOTEQUAL);				
			
			Assert.assertTrue(orderVo.getOrderStatus() == matchOrder.get("orderStatus").getAsInt(), Constants.Error.ORDERSTATUSNOTEQUAL);			
			Assert.assertTrue(orderVo.getTotalFee() == matchOrder.get("totalFee").getAsInt(), Constants.Error.TOTALFEENOTEQUAL);
			
		}
		
		
	}
	
	
	
	
	
	/////////////////////////////   页面拆分--三期             /////////////////////////////////////////
	/**
	 * bussinessId 即  entityId, 对应的店铺需要是连锁店铺, 否则会没有数据
	 */
	@Test(description = "品牌拆分相关", dataProvider = "brandSplit",
			dataProviderClass = PageSplitTestData.class, groups = {"smoke", "all"})
	public void brandSplit(String description, String businessType, String businessId) throws Exception{
		

		Response brandList = shopService.getNearbyShops(baseParamBean, businessType, businessId, environment);
		
		JsonObject brandListJsonObject = new JsonParser().parse(brandList.getResponseStr()).getAsJsonObject();
		
		Map<String, JsonObject> brandMap = new HashMap<String, JsonObject>();
		for(JsonElement element : brandListJsonObject.get("data").getAsJsonArray()){
			
			String id = element.getAsJsonObject().get("entityId").getAsString();
	
			brandMap.put(id, element.getAsJsonObject());
			
			
		}
		
		NearbyShop nearbyShop = new NearbyShop();
		nearbyShop.setLatitude("30.29824");
		nearbyShop.setLongitude("120.1298");
		nearbyShop.setPage(1);
		nearbyShop.setPageSize(20);
		
		Result<List<NearbyShopVo>> result = shopConfigService.getNearbyShopsByBusiness(nearbyShop, businessType, businessId);
		
		for(NearbyShopVo shopVo : result.getModel()){
	
			JsonObject matchBrand = brandMap.get(shopVo.getEntityId());
			Assert.assertTrue(shopVo.getName().equalsIgnoreCase(matchBrand.get("name").getAsString()), Constants.Error.BRANDNAMENOTEQUAL);			
			Assert.assertTrue(shopVo.getStatus() == matchBrand.get("status").getAsInt(), Constants.Error.BRANDSTATUSNOTEQUAL);				
			
			if( matchBrand.has("orderCount"))
				Assert.assertTrue(shopVo.getOrderCount() == matchBrand.get("orderCount").getAsInt(), Constants.Error.ORDERCOUNTNOTEQUAL);			
			Assert.assertTrue(shopVo.getSign().equalsIgnoreCase(matchBrand.get("sign").getAsString()), Constants.Error.SIGNNOTEQUAL);
			
		}
		
		
	}
	
	
	
	
	@Test(description = "排队拆分相关", dataProvider = "queenSplit",
			dataProviderClass = PageSplitTestData.class, groups = {"smoke", "all"})
	public void chainSplit(String description, String customerRegisterId) throws Exception{
		

		Response seatTypeList = shopService.getSeatType(baseParamBean, environment);
		
		JsonObject seatTypeListJsonObject = new JsonParser().parse(seatTypeList.getResponseStr()).getAsJsonObject().get("data").getAsJsonObject();
		
		Map<String, JsonObject> seatTypeMap = new HashMap<String, JsonObject>();
		for(JsonElement element : seatTypeListJsonObject.get("entitySeatInfoList").getAsJsonArray()){
			
			String id = element.getAsJsonObject().get("seatTypeName").getAsString();
	
			seatTypeMap.put(id, element.getAsJsonObject());		
			
		}
		

		
		Result<EntitySeatVo> queueStateResult = queueService.getEntityDeskInfo(baseParamBean.getEntityId(), customerRegisterId);
		
		for(EntitySeatInfo seatVo : queueStateResult.getModel().getEntitySeatInfoList()){
	
			JsonObject matchSeat = seatTypeMap.get(seatVo.getSeatTypeName());
			if(matchSeat.has("max"))
				Assert.assertTrue(seatVo.getMax() == matchSeat.get("max").getAsInt(), Constants.Error.MAXNOTEQUAL);			
			Assert.assertTrue(seatVo.getMin() == matchSeat.get("min").getAsInt(), Constants.Error.MINNOTEQUAL);				
			
			Assert.assertTrue(seatVo.getQueueLen() == matchSeat.get("queueLen").getAsInt(), Constants.Error.QUEUELENGTHNOTEQUAL);			
			
			
		}
		
		
	}
	
	
	

	@Test(description = "积分商城拆分相关", dataProvider = "pointsSplit",
			dataProviderClass = PageSplitTestData.class, groups = {"smoke", "all"})
	public void pointsSplit(String description, String customerRegisterId, int type) throws Exception{
		

		Response integralList = integralMallService.getIntegralHomeList(baseParamBean, environment);
		
		JsonObject integralListJsonObject = new JsonParser().parse(integralList.getResponseStr()).getAsJsonObject().get("data").getAsJsonObject();
		Result<IntegralHomePageVo> integralResult = intergralMallService.getIntegralHomeList(baseParamBean.getEntityId(), customerRegisterId, type);
		
		Map<String, JsonObject> integralMap = new HashMap<String, JsonObject>();
		
		if(integralListJsonObject.has("promotionExchangeList")){
			for(JsonElement element : integralListJsonObject.get("promotionExchangeList").getAsJsonArray()){
				
				String id = element.getAsJsonObject().get("integralId").getAsString();
		
				integralMap.put(id, element.getAsJsonObject());		
				
			}
			
			for(IntegralHomeDetailedVo integralVo : integralResult.getModel().getPromotionExchangeList()){
		
				JsonObject matchIntegral = integralMap.get(integralVo.getIntegralId());
				
				Assert.assertTrue(integralVo.getType() == matchIntegral.get("type").getAsInt(), Constants.Error.TYPENOTEQUAL);			
				Assert.assertTrue(integralVo.getNeedIntegral() == matchIntegral.get("needIntegral").getAsInt(), Constants.Error.NEEDINTEGRALNOTEQUAL);							
				Assert.assertTrue(integralVo.getIntegralEntityId().equalsIgnoreCase(matchIntegral.get("integralEntityId").getAsString()), Constants.Error.ENTITYIDNOTEQUAL);			
						
			}
		}
		
		
		
		if(integralListJsonObject.has("cardExchangeList")){
			
			for(JsonElement element : integralListJsonObject.get("cardExchangeList").getAsJsonArray()){
				
				String id = element.getAsJsonObject().get("integralId").getAsString();
		
				integralMap.put(id, element.getAsJsonObject());		
				
			}
	
	
			for(IntegralHomeDetailedVo integralVo : integralResult.getModel().getCardExchangeList()){
				
				JsonObject matchIntegral = integralMap.get(integralVo.getIntegralId());
				
				Assert.assertTrue(integralVo.getType() == matchIntegral.get("type").getAsInt(), Constants.Error.TYPENOTEQUAL);			
				Assert.assertTrue(integralVo.getNeedIntegral() == matchIntegral.get("needIntegral").getAsInt(), Constants.Error.NEEDINTEGRALNOTEQUAL);							
				Assert.assertTrue(integralVo.getIntegralEntityId().equalsIgnoreCase(matchIntegral.get("integralEntityId").getAsString()), Constants.Error.ENTITYIDNOTEQUAL);			
						
			}
		}
		
		
	}
	
	
	
	
	/**
	 * 目前只是对优惠卡进行了校验<br/>
	 * 目前该接口在日常环境还有问题<br/>
	 */
	@Test(description = "个人中心拆分相关", dataProvider = "userCenterSplit",
			dataProviderClass = PageSplitTestData.class, groups = {"smoke", "all"})
	public void userCenterSplit(String description, String customerRegisterId) throws Exception{
		

		Response userCenter = userCenterServiceImpl.getMyDashboard(baseParamBean, environment);
		
		JsonObject userCenterJsonObject = new JsonParser().parse(userCenter.getResponseStr()).getAsJsonObject().get("data").getAsJsonObject();
		
		JsonObject cardVoJsonObject = userCenterJsonObject.get("myCardVo").getAsJsonObject().get("cardVo").getAsJsonObject();

	
		Result<List<ShopMemberCardVo>> cardDataVoResult = privilegeServiceImpl.fetchShopMemberCard(baseParamBean.getEntityId(), customerRegisterId);
		ShopMemberCardVo shopMemberCardVo = cardDataVoResult.getModel().get(0);
		
		Assert.assertTrue(cardVoJsonObject.get("id").getAsString().equalsIgnoreCase(shopMemberCardVo.getCardVo().getId()));
		Assert.assertTrue( 0 == shopMemberCardVo.getCardVo().getBalance().compareTo(cardVoJsonObject.get("balance").getAsDouble()));
		Assert.assertTrue(cardVoJsonObject.get("degree").getAsInt() == shopMemberCardVo.getCardVo().getDegree().intValue());
		
	}
	
	
	
	
	@Test(description = "二维火个人中心拆分相关", dataProvider = "innerUserCenterSplit",
			dataProviderClass = PageSplitTestData.class, groups = {"smoke", "all"})
	public void innerUserCenterSplit(String description, String customerRegisterId) throws Exception{
		

		Response userCenter = userCenterServiceImpl.getMyUserCenter(baseParamBean, environment);
		
		JsonObject userCenterJsonObject = new JsonParser().parse(userCenter.getResponseStr()).getAsJsonObject().get("data").getAsJsonObject();

		Result<Integer> cardCountResult = customerRegisterCardService.queryCardCountByTmid(customerRegisterId);
		Result<Integer> cardCouponResult = promotionClientService.queryPromotionNumByCRegisterId(customerRegisterId);
		
		Assert.assertTrue(cardCountResult.getModel().intValue() == userCenterJsonObject.get("myCardCount").getAsInt());
		Assert.assertTrue(cardCouponResult.getModel().intValue() == userCenterJsonObject.get("myCouponCount").getAsInt());
		
		
	}
	
	
	
	
}
