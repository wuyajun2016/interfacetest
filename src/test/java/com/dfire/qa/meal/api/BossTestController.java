package com.dfire.qa.meal.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javassist.expr.NewArray;

import javax.annotation.Resource;







































import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.dfire.qa.meal.bean.DingdingCall;
import com.dfire.qa.meal.bean.DingdingMsg;
import com.dfire.qa.meal.bean.DingdingText;
import com.dfire.qa.meal.bean.MenuGet;
import com.dfire.qa.meal.bean.Response;
import com.dfire.qa.meal.constant.BossContents;
import com.dfire.qa.meal.data.BossTestData;
import com.dfire.qa.meal.enums.FileType;
import com.dfire.qa.meal.enums.Privilege;
import com.dfire.qa.meal.httpclient.HTTPClientService;
import com.dfire.qa.meal.logger.LoggerMarkers;
import com.dfire.qa.meal.logger.MealLoggerFactory;
import com.dfire.qa.meal.service.IBossService;
import com.dfire.qa.meal.service.IMessagePush;
import com.dfire.qa.meal.utils.CommonUtil;
import com.dfire.qa.meal.vo.boss.Coupon;
import com.dfire.qa.meal.vo.boss.CouponGift;
import com.dfire.qa.meal.vo.boss.CustomPrivilege;
import com.dfire.qa.meal.vo.boss.CustomPrivilegeVos;
import com.dfire.qa.meal.vo.boss.ForceConfig;
import com.dfire.qa.meal.vo.boss.GiftConvert;
import com.dfire.qa.meal.vo.boss.ImagePara;
import com.dfire.qa.meal.vo.boss.KindCard;
import com.dfire.qa.meal.vo.boss.PlanConfig;
import com.dfire.qa.meal.vo.boss.PrivilegeVo;
import com.dfire.qa.meal.vo.boss.Promotion;
import com.dfire.qa.meal.vo.boss.RechargeRule;
import com.dfire.qa.meal.vo.boss.SelfRecharge;
import com.dfire.qa.meal.vo.boss.TakeOut;
import com.dfire.qa.meal.vo.boss.TakeOutDeliveryMan;
import com.dfire.qa.meal.vo.boss.TakeOutTime;
import com.dfire.qa.meal.vo.cart.CartIncludeSuitForm;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mysql.fabric.xmlrpc.base.Array;


public class BossTestController extends BaseTestController{
	
	
	@Resource
	IBossService bossService;
	
	
	@Resource
	private HTTPClientService httpClientService;
	
	
	@Resource
	IMessagePush messagePush;
	
	private Map<String, String> infraMap = new HashMap<String, String>();
	
	private List<CartIncludeSuitForm> targetMenu = new ArrayList<CartIncludeSuitForm>();
	
	private Gson gson = new Gson();
	
	
	
	@BeforeClass(alwaysRun = true)
	public void Setup() throws Exception{
		
		baseParamBean = commonService.getBaseParam(environment);
		
		// 获取掌柜端--顾客端---基础设置
		Response configList = bossService.listConfigInfraSwitch(baseParamBean.getEntityId(), environment);
		
		infraMap = CommonUtil.bossInfraConfigConvert(configList);
		
		
		// 获取店铺普通菜
		MenuGet menuGet = new MenuGet();
		menuGet.setNormalMenu(true);
		menuGet.setNormalMenuNo(3);
		
		targetMenu = commonService.getMenu(baseParamBean, menuGet, otherParameter, environment);
		
	}
	
	
	
	
	@AfterClass(alwaysRun = true)
	public void TearDown() throws Exception{
		
	}
	
	@Test(description = "钉钉---消息推送")
	public void dingdingPush() throws Exception{
			
		
		String URL = "https://oapi.dingtalk.com/robot/send?access_token=6bcea9bb63631286d4cbcffeb1f6ec54509a3bea9551f828eff7bbe0afd12988";
		List<String> mobilesList = new ArrayList<String>();
		mobilesList.add("15158112345");
		DingdingText dindinText = new DingdingText("test~~");
		DingdingCall dindinCall = new DingdingCall(mobilesList, false);
		
		DingdingMsg dingdingMsg = new DingdingMsg("text", dindinText, dindinCall);
		// 向钉钉发送消息
		Boolean openSwitch = messagePush.pushMsgToDingding(URL, dingdingMsg);
						
		
	}
	
	
	///////////////////////////////   boss-client set    //////////////////////////////////////////////////////////////////////////////
	
	@Test(description = "顾客端设置---基础设置", dataProvider = "infraSwitch",
			dataProviderClass = BossTestData.class, groups = {"smoke", "all"})
	public void infraSwitch(String description, String value, int responseStatus, int resultCode) throws Exception{
			
		String config = CommonUtil.getKeyFromMap(infraMap, value);
		
		// 开启开关
		Response openSwitch = bossService.infraSwitchWithClient(baseParamBean.getEntityId(), config, true, value, environment);
		CommonUtil.verifyResponse(openSwitch, responseStatus, resultCode, value);
						
		
		// 关闭开关
		Response closeSwitch = bossService.infraSwitchWithClient(baseParamBean.getEntityId(), config, false, value, environment);
		CommonUtil.verifyResponse(closeSwitch, responseStatus, resultCode, value);
		
	}
	
	
	
	
	@Test(description = "顾客端设置---基础设置---顾客端显示扫菜码点菜按钮", dataProvider = "batchSet",
			dataProviderClass = BossTestData.class, groups = {"smoke", "all"})
	public void batchSet(String description, Map<String, String> configMap1, Map<String, String> configMap2, int responseStatus, int resultCode) throws Exception{
			
		
		
		// 开启开关
		Response openSwitch = bossService.batchSetConfig(baseParamBean.getEntityId(), configMap1, environment);
		CommonUtil.verifyResponse(openSwitch, responseStatus, resultCode, BossContents.InfraClient.SHOWPRICE);
						
		
		
		// 关闭开关
		Response closeSwitch = bossService.batchSetConfig(baseParamBean.getEntityId(), configMap2, environment);
		CommonUtil.verifyResponse(closeSwitch, responseStatus, resultCode, BossContents.InfraClient.SHOWPRICE);
		
	}
	
	
	
	@Test(description = "顾客端设置---外卖设置--设置外卖人员", dataProvider = "takeOutDeliveryMan",
			dataProviderClass = BossTestData.class, groups = {"smoke", "all"})
	public void takeOutDeliveryMan(String description, TakeOutDeliveryMan takeoutDeliveryManBean, int responseStatus, int resultCode) throws Exception{
			
		// 删除外卖人员
		Boolean flag = bossService.deleteAllTakeOutDeliveryMan(baseParamBean.getEntityId(), environment);
		Assert.assertEquals(flag.booleanValue(), true);
		
		
		// 添加设置外卖人员
		Response openSwitch = bossService.takeOutDeliveryMan(baseParamBean.getEntityId(), gson.toJson(takeoutDeliveryManBean), environment);
		CommonUtil.verifyResponse(openSwitch, responseStatus, resultCode, BossContents.InfraClient.TAKEOUTDELIVERYMAN);

		Response timesConfigCheck = bossService.getTakeOutDeliveryMan(baseParamBean.getEntityId(), environment);
		JsonObject respCheck = new JsonParser().parse(timesConfigCheck.getResponseStr()).getAsJsonObject().
				get("data").getAsJsonArray().get(0).getAsJsonObject();
		Assert.assertTrue(takeoutDeliveryManBean.getPhone().equalsIgnoreCase(respCheck.get("phone").getAsString()));
		Assert.assertTrue(takeoutDeliveryManBean.getIdCard().equalsIgnoreCase(respCheck.get("idCard").getAsString()));
		
		// 删除外卖人员
		Boolean flag2 = bossService.deleteAllTakeOutDeliveryMan(baseParamBean.getEntityId(), environment);
		Assert.assertEquals(flag2.booleanValue(), true);
		
	}
	
	
	
	@Test(description = "顾客端设置---外卖设置--设置外卖时间", dataProvider = "takeOutTime",
			dataProviderClass = BossTestData.class, groups = {"smoke", "all"})
	public void takeOutTime(String description, TakeOutTime takeoutTimeBean, int responseStatus, int resultCode) throws Exception{
			
		// 删除外卖时间设置
		Boolean flag = bossService.deleteAllTakeOutTimeConfig(baseParamBean.getEntityId(), environment);
		Assert.assertEquals(flag.booleanValue(), true);

		
		// 设置外卖时间
		Response openSwitch = bossService.takeOutTimeConfig(baseParamBean.getEntityId(), gson.toJson(takeoutTimeBean), environment);
		CommonUtil.verifyResponse(openSwitch, responseStatus, resultCode, BossContents.InfraClient.TAKEOUTTIME);
		
		
		Response timesConfigCheck = bossService.getTakeOutTimeConfig(baseParamBean.getEntityId(), environment);
		JsonObject respCheck = new JsonParser().parse(timesConfigCheck.getResponseStr()).getAsJsonObject().
				get("data").getAsJsonArray().get(0).getAsJsonObject();
		Assert.assertTrue(takeoutTimeBean.getBeginTime().equals(respCheck.get("beginTime").getAsLong()));
		Assert.assertTrue(takeoutTimeBean.getNum().equals(respCheck.get("num").getAsInt()));
		
		
		
		// 删除外卖时间设置
		Boolean flag2 = bossService.deleteAllTakeOutTimeConfig(baseParamBean.getEntityId(), environment);
		Assert.assertEquals(flag2.booleanValue(), true);

		
	}
	
	
	@Test(description = "顾客端设置---外卖设置--主接口", dataProvider = "takeOut",
			dataProviderClass = BossTestData.class, groups = {"smoke", "all"})
	public void takeOut(String description, TakeOut takeoutSet, int responseStatus, int resultCode) throws Exception{
			
		// 关闭外卖设置
		takeoutSet.setIsOut(0);
		Response closeSwitch = bossService.takeOutSetConfig(baseParamBean.getEntityId(), gson.toJson(takeoutSet), environment);
		CommonUtil.verifyResponse(closeSwitch, responseStatus, resultCode, BossContents.InfraClient.CLOSETAKEOUT);
				
				
		// 开启外卖设置
		takeoutSet.setIsOut(1);
		Response openSwitch = bossService.takeOutSetConfig(baseParamBean.getEntityId(), gson.toJson(takeoutSet), environment);
		CommonUtil.verifyResponse(openSwitch, responseStatus, resultCode, BossContents.InfraClient.OPENTAKEOUT);
		

		
	}
	
	
	
	@Test(description = "顾客端设置---必选商品", dataProvider = "forceMenu",
			dataProviderClass = BossTestData.class, groups = {"smoke", "all"})
	public void forceMenu(String description, ForceConfig forceConfig, int responseStatus, int resultCode) throws Exception{
		
		try{
			Response menuList = bossService.getAllForceMenuList(baseParamBean.getEntityId(), environment);
			JsonArray forceMenuArray = new JsonParser().parse(menuList.getResponseStr()).getAsJsonObject().get("data").getAsJsonArray();
			
			for(JsonElement element : forceMenuArray){
				
				if(!element.getAsJsonObject().has("forceMenuVoList") || (element.getAsJsonObject().get("forceMenuVoList").getAsJsonArray().size() < 1))
					continue;
				
				String menuId = element.getAsJsonObject().get("forceMenuVoList").getAsJsonArray().get(0).getAsJsonObject().get("menuId").getAsString();
				Integer menuType = element.getAsJsonObject().get("forceMenuVoList").getAsJsonArray().get(0).getAsJsonObject().get("menuType").getAsInt();
				
				forceConfig.setMenuId(menuId);
				forceConfig.setMenuType(menuType);
				forceConfig.setForceType(0);
				forceConfig.setForceNum(1);
				
				// 删除所有必选菜
				Boolean deleteSwitch = bossService.deleteAllForceMenu(baseParamBean.getEntityId(), environment);
				Assert.assertEquals(deleteSwitch.booleanValue(), true, BossContents.InfraClient.DELETEALLFORCEMENU);
		
				
				// 设置必选菜
				Response saveSwitch = bossService.saveForceMenu(baseParamBean.getEntityId(), forceConfig, environment);
				if((new JsonParser().parse(saveSwitch.getResponseStr()).getAsJsonObject().has("message")))
					continue;
				CommonUtil.verifyResponse(saveSwitch, responseStatus, resultCode, BossContents.InfraClient.SAVEFORCEMENU);
			}
			
		}catch(Exception exception){
			
			MealLoggerFactory.LOG_FILES_LOGGER.error(LoggerMarkers.LOGFILE, "error happen, msg :" + exception.toString());
			
		}finally{
				
			// 删除所有必选菜
			Boolean deleteSwitch2 = bossService.deleteAllForceMenu(baseParamBean.getEntityId(), environment);
			Assert.assertEquals(deleteSwitch2.booleanValue(), true, BossContents.InfraClient.DELETEALLFORCEMENU);
		}
		

		
	}
	
	
	
	
	@Test(description = "顾客端设置---商品页首与页尾设--包含文件上传和配置设置接口", dataProvider = "upLoadFile",
			dataProviderClass = BossTestData.class, groups = {"smoke", "all"})
	public void upLoadFile(String description, FileType fileType, List<String> fileNameList, int responseStatus, int resultCode) throws Exception{
			
		
		List<ImagePara> imageParaList = new ArrayList<ImagePara>();
		int count = 0;
		

		// 上传图片
		for(String fileName : fileNameList){
			
			count++;
			Response openSwitch = bossService.uploadFile(baseParamBean.getEntityId(), fileType, fileName, environment);
			CommonUtil.verifyResponse(openSwitch, responseStatus, resultCode, BossContents.InfraClient.FILEUPLOAD);
			
			JsonObject resp = new JsonParser().parse(openSwitch.getResponseStr()).getAsJsonObject();
			ImagePara imagePara = new ImagePara();
			imagePara.setEntityID(baseParamBean.getEntityId());
			imagePara.setType(count);
			imagePara.setUrl(resp.get("data").getAsString());
			
			imageParaList.add(imagePara);
		}

		
		// 保存设置
		Response openSwitch2 = bossService.imageSetConfig(baseParamBean.getEntityId(), gson.toJson(imageParaList), gson.toJson(new ArrayList<String>()), environment);
		CommonUtil.verifyResponse(openSwitch2, responseStatus, resultCode, BossContents.InfraClient.FILEUPLOAD);
		
		
		
		// 获取所有已经上传的图片信息
		Response imageSet = bossService.queryImageSet(baseParamBean.getEntityId(), environment);
		JsonObject imageSetResp = new JsonParser().parse(imageSet.getResponseStr()).getAsJsonObject();
		
		List<String> deleteImageParaList = new ArrayList<String>();
		for(JsonElement element : imageSetResp.get("data").getAsJsonObject().get("itemExtVos").getAsJsonArray()){
			
			deleteImageParaList.add(element.getAsJsonObject().get("id").getAsString());
			
		}
		
		// 删除所有图片
		Response deleteAllSwitch = bossService.imageSetConfig(baseParamBean.getEntityId(), gson.toJson(new ArrayList<String>()), gson.toJson(deleteImageParaList), environment);
		CommonUtil.verifyResponse(deleteAllSwitch, responseStatus, resultCode, BossContents.InfraClient.FILEUPLOAD);
		
	}
	
	
	
	
	
	@Test(description = "顾客端设置---个性化换肤", dataProvider = "updateStyle",
			dataProviderClass = BossTestData.class, groups = {"smoke", "all"})
	public void updateStyle(String description,String styleJson, String backGroundJson, String iconJson, int responseStatus, int resultCode) throws Exception{
		
		
		// 更改色调风格
		Response openSwitch = bossService.colorStyleConfig(baseParamBean.getEntityId(), styleJson, environment);
		CommonUtil.verifyResponse(openSwitch, responseStatus, resultCode, BossContents.InfraClient.UPDATESTYLE);
		

		// 自定义背景
		Response openSwitch2 = bossService.colorStyleConfig(baseParamBean.getEntityId(), backGroundJson, environment);
		CommonUtil.verifyResponse(openSwitch2, responseStatus, resultCode, BossContents.InfraClient.CUSTOMBG);
		
		
		// 自定义图标
		Response openSwitch3 = bossService.colorStyleConfig(baseParamBean.getEntityId(), iconJson, environment);
		CommonUtil.verifyResponse(openSwitch3, responseStatus, resultCode, BossContents.InfraClient.CUSTOMICON);
				
		
	}

	
	
	@Test(description = "顾客端设置---顾客点餐重复提醒", dataProvider = "menuRepeatRemaind",
			dataProviderClass = BossTestData.class, groups = {"smoke", "all"})
	public void menuRepeatRemaind(String description, Integer status, int responseStatus, int resultCode) throws Exception{
		
		String menuId = targetMenu.get(0).getMenuId();
		List<String> menuIdList = new ArrayList<String>();
		menuIdList.add(menuId);
		
		// 设置顾客点餐重复提醒
		Response openSwitch = bossService.menuRepeatConfig(baseParamBean.getEntityId(), status, environment);
		CommonUtil.verifyResponse(openSwitch, responseStatus, resultCode, BossContents.InfraClient.MENUREPEATREMAIND);
		

		// 添加重复提醒菜品
		Response openSwitch2 = bossService.menuRepeatAddMenuConfig(baseParamBean.getEntityId(), gson.toJson(menuIdList), environment);
		CommonUtil.verifyResponse(openSwitch2, responseStatus, resultCode, BossContents.InfraClient.MENUREPEATADDMENU);
		
		
		// 删除重复提醒菜品
		Response openSwitch3 = bossService.menuRepeatDeleteMenuConfig(baseParamBean.getEntityId(), menuId, environment);
		CommonUtil.verifyResponse(openSwitch3, responseStatus, resultCode, BossContents.InfraClient.MENUREPEATDELETEMENU);
		
		
				
		
	}
	
	
	
	
	
	
	///////////////////////////   boss-member    ///////////////////////////////////////////////////////////////////  
	
	@Test(description = "会员设置---保存与删除优惠券", dataProvider = "saveCoupon",
			dataProviderClass = BossTestData.class, groups = {"smoke", "all"})
	public void saveCoupon(String description, Coupon coupon, int responseStatus, int resultCode) throws Exception{
			
		
		// 添加优惠券
		Response openSwitch = bossService.saveCouponConfig(baseParamBean.getEntityId(), gson.toJson(coupon), environment);
		CommonUtil.verifyResponse(openSwitch, responseStatus, resultCode, BossContents.InfraClient.SAVECOUPON);

		JsonObject resp = new JsonParser().parse(openSwitch.getResponseStr()).getAsJsonObject();
		String couponId = resp.get("data").getAsJsonArray().get(0).getAsString();
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, "the coupon id is : " + couponId);
		
		
		JsonObject couponCheck = bossService.getCoupon(baseParamBean.getEntityId(), couponId, environment);
		Assert.assertTrue(coupon.getCouponType().equals(couponCheck.get("couponType").getAsInt()));
		Assert.assertTrue(coupon.getAmount().equals(couponCheck.get("amount").getAsInt()));
		
		
		// 删除优惠券
		Response openSwitch2 = bossService.deleteCouponConfig(baseParamBean.getEntityId(), couponId, environment);
		CommonUtil.verifyResponse(openSwitch2, responseStatus, resultCode, BossContents.InfraClient.DELETECOUPON);
				
				
		
	}
	
	
	
	@Test(description = "会员设置---保存与删除促销", dataProvider = "savePromotion",
			dataProviderClass = BossTestData.class, groups = {"smoke", "all"})
	public void savePromotion(String description, Promotion promotion, int responseStatus, int resultCode) throws Exception{
			
		
		// 添加促销
		Response openSwitch = bossService.savePromotion(baseParamBean.getEntityId(), gson.toJson(promotion), environment);
		CommonUtil.verifyResponse(openSwitch, responseStatus, resultCode, BossContents.InfraClient.SAVEPROMOTION);

		JsonObject resp = new JsonParser().parse(openSwitch.getResponseStr()).getAsJsonObject();
		String promotionId = resp.get("data").getAsString();
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, "the promotion id is : " + promotionId);
		
		
		JsonObject promotionCheck = bossService.getPromotion(baseParamBean.getEntityId(), promotionId, environment);
		Assert.assertTrue(promotion.getTitle().equalsIgnoreCase(promotionCheck.get("title").getAsString()));
		Assert.assertTrue(promotion.getDiscountRate().equalsIgnoreCase(promotionCheck.get("discountRate").getAsString()));
		
		// 删除促销
		Response openSwitch2 = bossService.deleteCouponConfig(baseParamBean.getEntityId(), promotionId, environment);
		CommonUtil.verifyResponse(openSwitch2, responseStatus, resultCode, BossContents.InfraClient.DELETEPROMOTION);
				
				
		
	}
	
	
	

	@Test(description = "会员设置---保存与删除会员特权等级", dataProvider = "memberPrivilege",
			dataProviderClass = BossTestData.class, groups = {"smoke", "all"})
	public void memberPrivilege(String description, String couponJson, Privilege privilege, PrivilegeVo privilegeVo, 
			CustomPrivilege custoPrivilege, KindCard kindCard, int responseStatus, int resultCode) throws Exception{
			
		
		////////////////  设置优惠券, 会员等级特权专用          ///////////////////////////////////////
		// 添加优惠券
		Response couponSwitch = bossService.saveCouponConfig(baseParamBean.getEntityId(), couponJson, environment);
		CommonUtil.verifyResponse(couponSwitch, responseStatus, resultCode, BossContents.InfraClient.SAVECOUPON);

		JsonObject resp = new JsonParser().parse(couponSwitch.getResponseStr()).getAsJsonObject();
		String couponId = resp.get("data").getAsJsonArray().get(0).getAsString();
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, "the coupon id is : " + couponId);
		
		privilegeVo.getPrivilegePromotionVos().get(0).getRespShopCouponPromotionVo().setId(couponId);
		
		
		
		try{
			
			////////////////////////////  添加一般会员等级特权           /////////////////////////////////
			// 添加会员等级特权
			Response normalSwitch = bossService.savePrivilege(baseParamBean.getEntityId(), privilege, gson.toJson(privilegeVo), environment);
			CommonUtil.verifyResponse(normalSwitch, responseStatus, resultCode, BossContents.InfraClient.SAVEPRIVILEGE);
			
			
			
			////////////////////////////   添加自定义会员特权              /////////////////////////////////
			// 在自定义列表中添加自定义会员等级特权
			Response customSwitch = bossService.addCustomPrivilege(baseParamBean.getEntityId(), gson.toJson(custoPrivilege), environment);
			CommonUtil.verifyResponse(customSwitch, responseStatus, resultCode, BossContents.InfraClient.ADDCTUOMPRIVILEGE);	
			
			Response customDetail = bossService.getCustomPrivilegeList(baseParamBean.getEntityId(), environment);
			JsonObject customDetailResp = new JsonParser().parse(customDetail.getResponseStr()).getAsJsonObject();
			String customPrivilegeId = customDetailResp.get("data").getAsJsonArray().get(0).getAsJsonObject().get("customPrivilegeId").getAsString();
			CustomPrivilegeVos customPrivilegeVos = new CustomPrivilegeVos(customPrivilegeId);
			
			
			// --------------------------------------------------------------------------------------------------------------
			// 添加自定义会员等级特权
			Response addCustomSwitch = bossService.savePrivilege(baseParamBean.getEntityId(), Privilege.CUSTOMPRIVILEGE, gson.toJson(customPrivilegeVos), environment);
			CommonUtil.verifyResponse(addCustomSwitch, responseStatus, resultCode, BossContents.InfraClient.SAVEPRIVILEGE);
			
			// ---------------------------------------------------------------------------------------------------------------
			
			
	
			///////////////////////////   添加特权卡会员特权           /////////////////////////////////////
			// 在特权库中添加特权卡设置
			Response saveCardPrivilege = bossService.addCardPrivilege(baseParamBean.getEntityId(), gson.toJson(kindCard), environment);
			CommonUtil.verifyResponse(saveCardPrivilege, responseStatus, resultCode, BossContents.InfraClient.ADDCARDPRIVILEGE);
			
			
			Response cardPricilege = bossService.getAllCardPrivilegeDetail(baseParamBean.getEntityId(), environment);
			JsonObject cardPrivilegeResp = new JsonParser().parse(cardPricilege.getResponseStr()).getAsJsonObject();
			
			String id = cardPrivilegeResp.get("data").getAsJsonArray().get(0).getAsJsonObject().get("id").getAsString();
			String lastVersion = cardPrivilegeResp.get("data").getAsJsonArray().get(0).getAsJsonObject().get("lastVersion").getAsString();
			String entityName = cardPrivilegeResp.get("data").getAsJsonArray().get(0).getAsJsonObject().get("entityName").getAsString();
			
			kindCard.setId(id);
			kindCard.setLastVersion(lastVersion);
			kindCard.setEntityName(entityName);
			
			// 添加会员特权中添加特权卡
			Response addCardPrivilegewitch = bossService.savePrivilege(baseParamBean.getEntityId(), Privilege.CARDPRIVILEGE, gson.toJson(kindCard), environment);
			CommonUtil.verifyResponse(addCardPrivilegewitch, responseStatus, resultCode, BossContents.InfraClient.ADDPRIVILEGE);
			
				

		}catch(Exception e){
			
			MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, "exception msg is : " + e.toString());
			
		}finally{
			
			///////////////////////  删除所有会员等级特权               //////////////////////////////////////////
			bossService.deleteAllPrivilege(baseParamBean.getEntityId(), custoPrivilege, environment);
			
			
			///////////////////////  删除会员特权等级专用优惠券         ///////////////////////////////////////
			// 删除优惠券
			Response couponSwitch2 = bossService.deleteCouponConfig(baseParamBean.getEntityId(), couponId, environment);
			CommonUtil.verifyResponse(couponSwitch2, responseStatus, resultCode, BossContents.InfraClient.DELETECOUPON);
		}
		
		
	}
	
	
	
	@Test(description = "会员设置---卡充值设置", dataProvider = "cardRecharge",
			dataProviderClass = BossTestData.class, groups = {"smoke", "all"})
	public void cardRecharge(String description, Integer order, int responseStatus, int resultCode) throws Exception{
			
		
		try{
			
			// 查询获取第一个测试优惠卡
			Response querySwitch = bossService.queryKindCards(baseParamBean.getEntityId(), environment);
			CommonUtil.verifyResponse(querySwitch, responseStatus, resultCode, BossContents.InfraClient.QUERYKINDCARDS);
			
			JsonObject queryResp = new JsonParser().parse(querySwitch.getResponseStr()).getAsJsonObject();
			String kindCardId = queryResp.get("data").getAsJsonArray().get(order).getAsJsonObject().get("kindCardId").getAsString();
			String kindCardName = queryResp.get("data").getAsJsonArray().get(order).getAsJsonObject().get("kindCardName").getAsString();
			
			List<SelfRecharge> selfRechargeList = new ArrayList<SelfRecharge>();
			selfRechargeList.add(new SelfRecharge(kindCardId, 1));
			
			// 设置 该测试优惠卡 允许顾客端使用火小二自助充值
			Response selfRechargeSwitch = bossService.selfRecharge(baseParamBean.getEntityId(), gson.toJson(selfRechargeList), environment);
			CommonUtil.verifyResponse(selfRechargeSwitch, responseStatus, resultCode, BossContents.InfraClient.RECHARGERULES);
			
			
			RechargeRule rechargeRule = new RechargeRule(kindCardId, kindCardName);
			// 向 上述  测试优惠卡  添加卡充值优惠
			Response rechargeSwitch = bossService.saveRechargeRule(baseParamBean.getEntityId(), gson.toJson(rechargeRule), environment);
			CommonUtil.verifyResponse(rechargeSwitch, responseStatus, resultCode, BossContents.InfraClient.ADDRECHARGECARDS);
		
			
		}catch(Exception e){
			
			MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, "the exception is : " + e.toString());
			
		}finally{
			
			// 删除所有测试优惠卡上的充值优惠
			Response deleteAllRechargeSwitch = bossService.deleteAllRechargeRule(baseParamBean.getEntityId(), environment);
			CommonUtil.verifyResponse(deleteAllRechargeSwitch, responseStatus, resultCode, BossContents.InfraClient.DELETEKINDCARDS);
			
		}
				
				
		
	}
	
	
	
	@Test(description = "会员设置---积分兑换设置", dataProvider = "giftSet",
			dataProviderClass = BossTestData.class, groups = {"smoke", "all"})
	public void giftSet(String description, String cardJson, CouponGift couponGift, int responseStatus, int resultCode) throws Exception{
			
		// 添加 单品现金 优惠券
		Response addCoupon = bossService.saveCouponConfig(baseParamBean.getEntityId(), gson.toJson(new Coupon(3)), environment);
		CommonUtil.verifyResponse(addCoupon, responseStatus, resultCode, BossContents.InfraClient.SAVECOUPON);

		JsonObject resp = new JsonParser().parse(addCoupon.getResponseStr()).getAsJsonObject();
		String couponId = resp.get("data").getAsJsonArray().get(0).getAsString();
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, "the coupon id is : " + couponId);
		
		couponGift.setProductId(couponId);

		
		try{
				
			
			// 添加卡金额
			Response cardSwitch = bossService.saveGift(baseParamBean.getEntityId(), cardJson, environment);
			CommonUtil.verifyResponse(cardSwitch, responseStatus, resultCode, BossContents.InfraClient.SAVECARDGIFT);
	
			// 添加优惠券
			Response couponSwitch = bossService.saveGift(baseParamBean.getEntityId(), gson.toJson(couponGift), environment);
			CommonUtil.verifyResponse(couponSwitch, responseStatus, resultCode, BossContents.InfraClient.SAVECOUPONGIFT);
			
		}catch(Exception e){
			
			MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, "the exception is : " + e.toString());
			
		}finally{
				
			// 删除所有积分兑换设置
			Response deleteSwitch = bossService.deleteAllGift(baseParamBean.getEntityId(), environment);
			CommonUtil.verifyResponse(deleteSwitch, responseStatus, resultCode, BossContents.InfraClient.DELETEALLGIFT);
			
			
			// 删除优惠券
			Response couponSwitch2 = bossService.deleteCouponConfig(baseParamBean.getEntityId(), couponId, environment);
			CommonUtil.verifyResponse(couponSwitch2, responseStatus, resultCode, BossContents.InfraClient.DELETECOUPON);
			
		}
				
				
		
	}
	
	
	
	/**
	 * 会员设置---积分抵现设置<br/>
	 */
	@Test(description = "会员设置---积分抵现设置", dataProvider = "giftConvertSet",
			dataProviderClass = BossTestData.class, groups = {"smoke", "all"})
	public void giftConvertSet(String description, GiftConvert giftConvert, int responseStatus, int resultCode) throws Exception{
			
		
		try{
			
			// 设置积分抵现规则
			Response giftConvertSwitch = bossService.saveGiftConvert(baseParamBean.getEntityId(), gson.toJson(giftConvert), environment);
			CommonUtil.verifyResponse(giftConvertSwitch, responseStatus, resultCode, BossContents.InfraClient.SAVEGIFTCONVERT);
				
			
			
		}catch(Exception e){
			
			MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, "the exception is : " + e.toString());
			
		}finally{
			
			
			// 获取积分抵现信息
			Response giftSwitch = bossService.getGiftConvertList(baseParamBean.getEntityId(), environment);
			CommonUtil.verifyResponse(giftSwitch, responseStatus, resultCode, BossContents.InfraClient.GETGIFTCONVERT);
			
			giftConvert.reset();
			// 删除所有积分抵现设置
			Response deleteSwitch = bossService.saveGiftConvert(baseParamBean.getEntityId(), gson.toJson(giftConvert), environment);
			CommonUtil.verifyResponse(deleteSwitch, responseStatus, resultCode, BossContents.InfraClient.DELETEALLGIFTCONVERT);
		}
				
				
		
	}
	
	
	
	
	@Test(description = "智能点餐---商品标签设置", dataProvider = "menuLabelSet",
			dataProviderClass = BossTestData.class, groups = {"smoke", "all"})
	public void menuLabelSet(String description, String menuLabelJson, int responseStatus, int resultCode) throws Exception{
			
		String menuId = targetMenu.get(1).getMenuId();
		
		// 更新商品标签设置
		Response openSwitch = bossService.updateMenuLabel(baseParamBean.getEntityId(), menuLabelJson, menuId, environment);
		CommonUtil.verifyResponse(openSwitch, responseStatus, resultCode, BossContents.InfraClient.UPDATEMENULABEL);
				
				
		
	}
	
	
	
	@Test(description = "智能点餐---顾客点餐智能提醒与推荐", dataProvider = "menuRecommendSet",
			dataProviderClass = BossTestData.class, groups = {"smoke", "all"})
	public void menuRecommendSet(String description, PlanConfig planConfig, int responseStatus, int resultCode) throws Exception{
			
		
		// 开启智能点餐
		Response openEntitySwitch = bossService.saveEntityConfig(baseParamBean.getEntityId(), 1, environment);
		CommonUtil.verifyResponse(openEntitySwitch, responseStatus, resultCode, BossContents.InfraClient.OPENMENURECOMMEND);
		
		// 关闭智能点餐
		Response closeEntitySwitch = bossService.saveEntityConfig(baseParamBean.getEntityId(), 0, environment);
		CommonUtil.verifyResponse(closeEntitySwitch, responseStatus, resultCode, BossContents.InfraClient.CLOSEMENURECOMMEND);
		
		// 获取智能点餐设置
		Response queryPlanSwitch = bossService.queryPlanConfig(baseParamBean.getEntityId(), environment);
		CommonUtil.verifyResponse(queryPlanSwitch, responseStatus, resultCode, BossContents.InfraClient.UPDATEMENULABEL);

		JsonObject queryPlanResp = new JsonParser().parse(queryPlanSwitch.getResponseStr()).getAsJsonObject().get("data").getAsJsonObject();
		String planId = queryPlanResp.get("planConfigTypeVos").getAsJsonArray().get(0).getAsJsonObject().get("planId").getAsString();
		planConfig.setPlanId(planId);
		
		// 进行智能点餐设置
		Response savePlanSwitch = bossService.savePlanConfig(baseParamBean.getEntityId(), gson.toJson(planConfig), environment);
		CommonUtil.verifyResponse(savePlanSwitch, responseStatus, resultCode, BossContents.InfraClient.UPDATEMENULABEL);
				
				
		
	}
	
	
	
	@Test(description = "智能点餐---一键智能点餐模版设置", dataProvider = "menuTemplateSet",
			dataProviderClass = BossTestData.class, groups = {"smoke", "all"})
	public void menuTemplateSet(String description, int responseStatus, int resultCode) throws Exception{
			
		
		// 开启智能模板点餐
		Response openEntitySwitch = bossService.saveTemplateConfig(baseParamBean.getEntityId(), 1, environment);
		CommonUtil.verifyResponse(openEntitySwitch, responseStatus, resultCode, BossContents.InfraClient.OPENMENUTEMPLATE);
		
		// 关闭智能模板点餐
		Response closeEntitySwitch = bossService.saveTemplateConfig(baseParamBean.getEntityId(), 0, environment);
		CommonUtil.verifyResponse(closeEntitySwitch, responseStatus, resultCode, BossContents.InfraClient.CLOSEMENUTEMPLATE);
		
		// 查询智能点餐模板
		Response queryTemplateSwitch = bossService.queryMenuTemplate(baseParamBean.getEntityId(), environment);
		CommonUtil.verifyResponse(queryTemplateSwitch, responseStatus, resultCode, BossContents.InfraClient.QUERYMENUTEMPLATE);

		JsonObject queryPlanResp = new JsonParser().parse(queryTemplateSwitch.getResponseStr()).getAsJsonObject().get("data").getAsJsonObject();
		String coverPath = queryPlanResp.get("shopTemplateList").getAsJsonArray().get(2).getAsJsonObject().get("coverPath").getAsString();
		String shopTemplateId = queryPlanResp.get("shopTemplateList").getAsJsonArray().get(2).getAsJsonObject().get("shopTemplateId").getAsString();
		String templateName = queryPlanResp.get("shopTemplateList").getAsJsonArray().get(2).getAsJsonObject().get("templateName").getAsString();
		
		// 修改模板设置
		Response savePlanSwitch = bossService.modifyTemplateConfig(baseParamBean.getEntityId(), templateName, coverPath, shopTemplateId, environment);
		CommonUtil.verifyResponse(savePlanSwitch, responseStatus, resultCode, BossContents.InfraClient.UPDATEMENUTEMPLATE);
				
				
		
	}
	
}
