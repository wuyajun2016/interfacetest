package com.dfire.qa.meal.data;

import com.dfire.qa.meal.utils.CommonConstants;
import com.dfire.qa.meal.utils.MD5Utils;
import com.dfire.testcase.function.util.base.WechatBaseUtil;
import com.dfire.wechat.util.HttpRequestEx;
import com.google.gson.Gson;

public class BaseTestData {
	
	protected static String token;
	protected static String signKey;  // 该 signKey 由 entityID=99927792 和 seatCode = B2 生成
	
	protected static String unionId = CommonConstants.unionID;
	protected static String entityId = CommonConstants.entityId;	
	
	protected static String seatCode = CommonConstants.seatCode;	
	protected static boolean https = CommonConstants.HTTPS;
	
	protected static String uid = CommonConstants.UID;
	protected static String normal1MenuId = "";
	
	///////////////  boss-client style set     //////////////////////////
	protected static String backGroundStyle = CommonConstants.backGroundStyle;
	protected static String iconStyle = CommonConstants.iconStyle;
	
	
	///////////////  boss-member-privilege   //////////////////////////////
	protected static String privilegeId = "";
	protected static String productedId = "";
	protected static String couponName = CommonConstants.couponName;
	
	protected static Gson gson = new Gson();
	
	static{
		
		HttpRequestEx httpRequest = new HttpRequestEx(CommonConstants.DEFAULT_HOST, https);
		token = WechatBaseUtil.getToken(httpRequest, entityId, unionId);
		httpRequest.ShutDown();
		
		signKey =  MD5Utils.generatorKey(entityId + unionId);
		
	}

}
