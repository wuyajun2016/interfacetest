package com.dfire.qa.meal.data;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.dfire.qa.meal.constant.BossContents;
import com.dfire.qa.meal.enums.FileType;
import com.dfire.qa.meal.enums.Privilege;
import com.dfire.qa.meal.utils.BeanProvider;
import com.dfire.qa.meal.utils.CommonConstants;
import com.dfire.qa.meal.vo.boss.CardGift;
import com.dfire.qa.meal.vo.boss.Coupon;
import com.dfire.qa.meal.vo.boss.CouponGift;
import com.dfire.qa.meal.vo.boss.CustomPrivilege;
import com.dfire.qa.meal.vo.boss.CustomPrivilegeVos;
import com.dfire.qa.meal.vo.boss.ForceConfig;
import com.dfire.qa.meal.vo.boss.GiftConvert;
import com.dfire.qa.meal.vo.boss.ImagePara;
import com.dfire.qa.meal.vo.boss.KindCard;
import com.dfire.qa.meal.vo.boss.MenuLabel;
import com.dfire.qa.meal.vo.boss.PlanConfig;
import com.dfire.qa.meal.vo.boss.PrivilegePromotionVo;
import com.dfire.qa.meal.vo.boss.PrivilegeVo;
import com.dfire.qa.meal.vo.boss.Promotion;
import com.dfire.qa.meal.vo.boss.RespShopCouponPromotionVo;
import com.dfire.qa.meal.vo.boss.Style;
import com.dfire.qa.meal.vo.boss.TakeOut;
import com.dfire.qa.meal.vo.boss.TakeOutDeliveryMan;
import com.dfire.qa.meal.vo.boss.TakeOutTime;


public class BossTestData extends BaseTestData{
	
	
	@DataProvider(name = "infraSwitch")
	public static Object [][] infraSwitch(){
				

		String description1 = "description: " + BossContents.InfraClient.COMPOSINGTYPE;
		String config1 = BossContents.InfraClient.COMPOSINGTYPE;
		
		
		String description2 = "description: " + BossContents.InfraClient.SHOWPRICE;
		String config2 = BossContents.InfraClient.SHOWPRICE;
		
		
		String description3 = "description: " + BossContents.InfraClient.SHOWPEOPLE;
		String config3 = BossContents.InfraClient.SHOWPEOPLE;
		
		
		String description4 = "description: " + BossContents.InfraClient.SHOWSELLOUTMENU;
		String config4 = BossContents.InfraClient.SHOWSELLOUTMENU;
		
		
		String description5 = "description: " + BossContents.InfraClient.SHOWCOMMENT;
		String config5 = BossContents.InfraClient.SHOWCOMMENT;
		
		
		String description6 = "description: " + BossContents.InfraClient.SHOWPOPWIN;
		String config6 = BossContents.InfraClient.SHOWPOPWIN;
		
		
		String description7 = "description: " + BossContents.InfraClient.SHOWCOUPONWIN;
		String config7 = BossContents.InfraClient.SHOWCOUPONWIN;
		
		
		String description8 = "description: " + BossContents.InfraClient.SHOWMSGCOLD;
		String config8 = BossContents.InfraClient.SHOWMSGCOLD;
		
		
		String description9 = "description: " + BossContents.InfraClient.SHOWMSGNOUP;
		String config9 = BossContents.InfraClient.SHOWMSGNOUP;
		
		
		String description10 = "description: " + BossContents.InfraClient.SHOWMSGTAKEOUT;
		String config10 = BossContents.InfraClient.SHOWMSGTAKEOUT;
		
		
		String description11 = "description: " + BossContents.InfraClient.LEAVEEMPTY;
		String config11 = BossContents.InfraClient.LEAVEEMPTY;
		
		
		String description12 = "description: " + BossContents.InfraClient.JUDGELOCATION;
		String config12 = BossContents.InfraClient.JUDGELOCATION;
		
		return new Object[][]{
				
				{description1, config1, 200, 1},
				{description2, config2, 200, 1},
				{description3, config3, 200, 1},
				
				{description4, config4, 200, 1},
				{description5, config5, 200, 1},
				{description6, config6, 200, 1},
				
				{description7, config7, 200, 1},
				{description8, config8, 200, 1},
				{description9, config9, 200, 1},
				
				{description10, config10, 200, 1},
				{description11, config11, 200, 1},
				{description12, config12, 200, 1},

		};
	}
	
	
	
	
	
	@DataProvider(name = "batchSet")
	public static Object [][] batchSet(){
		
		String description1 = "description: " + BossContents.InfraClient.SHOWMENUCODE;
		Map<String, String> configMap1 = new HashMap<String, String>();
		configMap1.put(CommonConstants.menuCodeButtonFlag, "1");
		
		
		Map<String, String> configMap2 = new HashMap<String, String>();
		configMap2.put(CommonConstants.menuCodeButtonFlag, "0");
		
		return new Object[][]{
				
				{description1, configMap1, configMap2, 200, 1},				

		};
	}
	
	
	
	@DataProvider(name = "takeOutDeliveryMan")
	public static Object [][] takeOutDeliveryMan(){
		
		String description1 = "description: " + BossContents.InfraClient.TAKEOUTDELIVERYMAN;
		TakeOutDeliveryMan takeoutSet1 = BeanProvider.getTakeOutDeliveryManBean();
		
		return new Object[][]{
				
				{description1, takeoutSet1, 200, 1},				

		};
	}
	
	
	
	
	@DataProvider(name = "takeOutTime")
	public static Object [][] takeOutTime(){
		
		String description1 = "description: " + BossContents.InfraClient.TAKEOUTTIME;
		TakeOutTime takeoutSetBean1 = BeanProvider.getTakeOutTimeBean();
		
		return new Object[][]{
				
				{description1, takeoutSetBean1, 200, 1},				

		};
	}
	
	
	
	@DataProvider(name = "takeOut")
	public static Object [][] takeOut(){
		
		String description1 = "description: " + BossContents.InfraClient.OPENTAKEOUT;
		TakeOut takeoutSet1 = BeanProvider.getTakeOutSet();
		
		return new Object[][]{
				
				{description1, takeoutSet1, 200, 1},				

		};
	}
	
	
	
	@DataProvider(name = "forceMenu")
	public static Object [][] forceMenu(){
		
		String description1 = "description: " + BossContents.InfraClient.SAVEFORCEMENU;
		ForceConfig forceConfig1 = new ForceConfig();
		
		return new Object[][]{
				
				{description1, forceConfig1, 200, 1},				

		};
	}
	
	
	
	
	@DataProvider(name = "upLoadFile")
	public static Object [][] upLoadFile(){
		
		String description1 = "description: " + BossContents.InfraClient.FILEUPLOAD;
		FileType fileType1 = FileType.image;
		List<String> fileNameList1 = new ArrayList<String>();
		fileNameList1.add("scenery.jpg");
		fileNameList1.add("sky.jpg");
		
		
		
		return new Object[][]{
				
				{description1, fileType1, fileNameList1, 200, 1},				

		};
	}
	
	
	
	
	@DataProvider(name = "updateStyle")
	public static Object [][] updateStyle(){
		
		String description1 = "description: " + BossContents.InfraClient.UPDATESTYLE;
		Style style11 = new Style();
		style11.setBag("");
		style11.setTone("2");
		style11.setIcon("");
		style11.setEntityId(entityId);
		
		Style style12 = new Style();
		style12.setBag(backGroundStyle);
		style12.setTone("2");
		style12.setIcon("1");
		style12.setEntityId(entityId);
		
		
		Style style13 = new Style();
		style13.setBag(iconStyle);
		style13.setTone("2");
		style13.setIcon("1");
		style13.setEntityId(entityId);
		
		
		return new Object[][]{
				
				{description1, gson.toJson(style11), gson.toJson(style12), gson.toJson(style13), 200, 1},				

		};
	}
	
	
	
	@DataProvider(name = "menuRepeatRemaind")
	public static Object [][] menuRepeatRemaind(){
		
		String description1 = "description: " + BossContents.InfraClient.MENUREPEATREMAIND;		
		Integer status1 = 1;


		return new Object[][]{
				
				{description1, status1, 200, 1},				

		};
	}
	
	
	
	@DataProvider(name = "saveCoupon")
	public static Object [][] saveCoupon(){
		
		String description1 = "description: 添加全场现金券";		
		Coupon coupon1 = new Coupon(1);
		
		String description2 = "description: 添加全场折扣券";		
		Coupon coupon2 = new Coupon(2);
		
		String description3 = "description: 添加单品现金券";		
		Coupon coupon3 = new Coupon(3);
		
		String description4 = "description: 添加单品折扣券";		
		Coupon coupon4 = new Coupon(4);
		
		String description5 = "description: 添加单品特价券";		
		Coupon coupon5 = new Coupon(5);
		
		String description6 = "description: 添加单品兑换券";		
		Coupon coupon6 = new Coupon(6);
		
		
		return new Object[][]{
				
				{description1, coupon1, 200, 1},		
				{description2, coupon2, 200, 1},	
				
				{description3, coupon3, 200, 1},		
				{description4, coupon4, 200, 1},	
				
				{description5, coupon5, 200, 1},		
				{description6, coupon6, 200, 1},	
				

		};
	}
	
	
	@DataProvider(name = "savePromotion")
	public static Object [][] savePromotion(){
		
		
		String description1 = "description: " + BossContents.InfraClient.SAVEPROMOTION;		
		Promotion promotion = new Promotion();

		
		return new Object[][]{
				
				{description1, promotion, 200, 1},				

		};
	}
	
	
	
	@DataProvider(name = "memberPrivilege")
	public static Object [][] memberPrivilege(){
		
		
		String description1 = "description: " + BossContents.InfraClient.SAVECOUPON;		
		Coupon coupon = new Coupon(2);
		
		
		Privilege privilege1 = Privilege.BIRTH;
		RespShopCouponPromotionVo respShopCouponPromotionVo = new RespShopCouponPromotionVo(privilegeId ,entityId);
		PrivilegePromotionVo privilegePromotionVo = new PrivilegePromotionVo(respShopCouponPromotionVo);
		
		List<PrivilegePromotionVo> privilegePromotionVos = new ArrayList<PrivilegePromotionVo>();
		privilegePromotionVos.add(privilegePromotionVo);
		PrivilegeVo privilegeVo = new PrivilegeVo(privilegePromotionVos);
		
		
		CustomPrivilege customPrivilege = new CustomPrivilege();
		
		
		
		KindCard kindCard = new KindCard();
		
		return new Object[][]{
				
				{description1, gson.toJson(coupon), privilege1, privilegeVo, customPrivilege, kindCard, 200, 1},				

		};
	}
	
	
	
	
	@DataProvider(name = "cardRecharge")
	public static Object [][] cardRecharge(){
		
		
		String description1 = "description: " + BossContents.InfraClient.SAVEPROMOTION;		
		Integer order1 = 0;  // 选择第一个测试卡

		
		return new Object[][]{
				
				{description1, order1, 200, 1},				

		};
	}
	
	
	
	@DataProvider(name = "giftSet")
	public static Object [][] giftSet(){
		
		
		String description1 = "description: " + BossContents.InfraClient.SAVEPROMOTION;		
		
		CardGift cardGift = new CardGift();
		CouponGift couponGift = new CouponGift(productedId, couponName);
		
		return new Object[][]{
				
				{description1, gson.toJson(cardGift), couponGift, 200, 1},				

		};
	}
	
	
	@DataProvider(name = "giftConvertSet")
	public static Object [][] giftConvertSet(){
		
		
		String description1 = "description: " + BossContents.InfraClient.SAVEPROMOTION;		
		GiftConvert giftConvert = new GiftConvert();
		
		
		return new Object[][]{
				
				{description1, giftConvert, 200, 1},				

		};
	}
	
	
	
	@DataProvider(name = "menuLabelSet")
	public static Object [][] menuLabelSet(){
		
		
		String description1 = "description: " + BossContents.InfraClient.UPDATEMENULABEL;		
		MenuLabel menuLabel = new MenuLabel();
		
		return new Object[][]{
				
				{description1, gson.toJson(menuLabel), 200, 1},				

		};
	}
	
	
	
	@DataProvider(name = "menuRecommendSet")
	public static Object [][] menuRecommendSet(){
		
		
		String description1 = "description: " + BossContents.InfraClient.UPDATEMENULABEL;		
		List<String> labelIdList = new ArrayList<String>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "9", "10", "11", "12", "14", "15", "19", "29", "30"));
		
		PlanConfig planConfig = new PlanConfig("", labelIdList);
		
		return new Object[][]{
				
				{description1, planConfig, 200, 1},				

		};
	}
	
	
	
	@DataProvider(name = "menuTemplateSet")
	public static Object [][] menuTemplateSet(){
		
		
		String description1 = "description: " + BossContents.InfraClient.MENUTEMPLATE;		
		
		
		return new Object[][]{
				
				{description1, 200, 1},				

		};
	}
	
	
	
	
	
	
}
