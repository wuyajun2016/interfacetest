package com.dfire.qa.meal.data;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.dfire.qa.meal.constant.BossContents;
import com.dfire.qa.meal.utils.CommonConstants;

public class PageSplitTestData extends BaseTestData{
	
	@DataProvider(name = "memberShipCard")
	public static Object [][] memberShipCard(){
		
		String description1 = "description: 本店会员卡";
		String customerRegisterId = uid;
		
		return new Object[][]{
				
				{description1, customerRegisterId, 200, 1},				

		};
	}
	
	
	@DataProvider(name = "shopCoupon")
	public static Object [][] shopCoupon(){
		
		String description1 = "description: 本店优惠";
		String customerRegisterId = uid;
		
		return new Object[][]{
				
				{description1, customerRegisterId, 200, 1},				

		};
	}
	
	
	@DataProvider(name = "myCardList")
	public static Object [][] myCardList(){
		
		String description1 = "description: 我的会员卡";
		String customerRegisterId = uid;
		
		return new Object[][]{
				
				{description1, customerRegisterId, 200, 1},				

		};
	}
	
	
	@DataProvider(name = "myCouponList")
	public static Object [][] myCouponList(){
		
		String description1 = "description: 我的优惠券";
		String customerRegisterId = uid;
		
		return new Object[][]{
				
				{description1, customerRegisterId, 200, 1},				

		};
	}
	
	
	
	@DataProvider(name = "takeOutMenuList")
	public static Object [][] takeOutMenuList(){
		
		String description1 = "description: 本店外卖菜单";
		String customerRegisterId = uid;
		
		return new Object[][]{
				
				{description1, customerRegisterId, 200, 1},				

		};
	}
	
	
	
	@DataProvider(name = "menuList")
	public static Object [][] menuList(){
		
		String description1 = "description: 本店堂食菜单";
		String customerRegisterId = uid;
		
		return new Object[][]{
				
				{description1, customerRegisterId, 200, 1},				

		};
	}
	
	
	
	@DataProvider(name = "orderInfo")
	public static Object [][] orderInfo(){
		
		String description1 = "description: 已下单的菜";
		
		return new Object[][]{
				
				{description1, 200, 1},				

		};
	}
	
	
	
	@DataProvider(name = "shopOrder")
	public static Object [][] shopOrder(){
		
		String description1 = "description: 本店订单";
		String customerRegisterId = uid;
		
		return new Object[][]{
				
				{description1, customerRegisterId, 200, 1},				

		};
	}
	
	
	
	@DataProvider(name = "brandSplit")
	public static Object [][] brandSplit(){
		
		String description1 = "description: 品牌拆分";
		String businessType1 = "plate_entity_id";
		String businessId1 = "99925788";  // 该 entityId 需要是连锁, 否则会没有数据
		
		
		String description2 = "description: 连锁拆分";
		String businessType2 = "brand_entity_id";
		String businessId2 = entityId;
		
		
		return new Object[][]{
				
				{description1, businessType1, businessId1},	
				{description2, businessType2, businessId2},	

		};
	}
	
	
	@DataProvider(name = "queenSplit")
	public static Object [][] queenSplit(){
		
		String description1 = "description: 排队拆分";
		String customerRegisterId = uid;
		
		return new Object[][]{
				
				{description1, customerRegisterId},				

		};
	}

	
	@DataProvider(name = "pointsSplit")
	public static Object [][] pointsSplit(){
		
		String description1 = "description: 积分商城拆分";
		String customerRegisterId = uid;
		int type = 1;  // 扫码类型,1:微信,2:支付宝
		
		return new Object[][]{
				
				{description1, customerRegisterId, type},				

		};
	}
	
	
	
	@DataProvider(name = "userCenterSplit")
	public static Object [][] userCenterSplit(){
		
		String description1 = "description: 个人中心拆分";
		String customerRegisterId = uid;

		
		return new Object[][]{
				
				{description1, customerRegisterId},				

		};
	}

	
	
	@DataProvider(name = "innerUserCenterSplit")
	public static Object [][] innerUserCenterSplit(){
		
		String description1 = "description: 二维火个人中心拆分";
		String customerRegisterId = uid;

		
		return new Object[][]{
				
				{description1, customerRegisterId},				

		};
	}

	
}
