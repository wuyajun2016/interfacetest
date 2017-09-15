package com.dfire.testcase.function.boss;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.collections.map.MultiValueMap;
import org.apache.log4j.Logger;
import org.testng.Assert;

import com.dfire.testcase.bean.BasicSetting;
import com.dfire.wechat.util.CommonConstants;
import com.dfire.wechat.util.HttpRequestEx;
import com.dfire.wechat.util.MD5Utils;
import com.dfire.wechat.util.Response;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class BossController {
	
	private static final Logger logger = Logger.getLogger(BossController.class);
	
	private String entityId = CommonConstants.entityId;
	private String configId = "";
	private String sign = "";	
	
	private String appKey = "200006";  // 火掌柜 appKey
	private String appVersion = "5.5.52";
	private String deviceId = "1E8543AEED1E43BEBAB6AE46A2BAC605";
	
	private String format = "json";
	// 在 HTTP 请求的 header 中 设置为 test时 session 相关参数可忽略
	private String sessionKey = "100008999292499992924957e6050a0157f53eeb9f0013";  
	private String timeStamp = Long.toString(System.currentTimeMillis());

	// forceConfig 必填四项内容
	private String menuId = CommonConstants.cokeMenuId;
	private Integer menuType = 0;
	private Integer forceType = 0;
	private Integer forceNum = 1;
	
	private String secret = "BoivJgAlmBUO05yoxD6RU/SZ/nhLvpXT40v2ceqKJ1s=";
	private BaseBossBean baseParam = new BaseBossBean();
	private Map<String, String> httpHeader = new HashMap<String, String>();

	/**
	 * 初始化设置
	 */
	private void setup(){
				
		logger.info("setup in BossController");
		
	
		// 设置 HTTP 请求 body
		
		baseParam.setConfigId(configId);		
		baseParam.setAppKey(appKey);
		baseParam.setAppVersion(appVersion);
		
		baseParam.setDeviceId(deviceId);		
		baseParam.setFormat(format);
		baseParam.setSessionKey(sessionKey);
		
		baseParam.setTimeStamp(timeStamp);
		baseParam.setEntityId(entityId);
        
		
		
		// 设置 HTTP 请求  header
		httpHeader.put("version", "sso");
		httpHeader.put("sessionId", "123");
		httpHeader.put("isTest", "true");

	}
	
	
	
    /**
     * 保存必选商品设置, 此处默认为 普通菜
	 * 设置的 forceType (强制类型（0:指定数量,1:与用餐人数相同）); 
	 * 设置的 forceNum ( 强制点菜数量（当强制类型为"与用餐人数相同"时无视此数量）)
     * @param httpRequest
     * @param entityId
     * @param menuId
     * @param forceType
     * @param forceNum
     * @return
     */
	public Boolean saveForceMenu(HttpRequestEx httpRequest, String entityId, ForceConfig forceConfig){
		
		Boolean flag = false;
		
		try{
			
			logger.info("掌柜端设置：保存必选商品设置");
			
			setup(); // 初始设置, 包含清空个性化设置功能
			baseParam.setForceConfig(forceConfig);
			baseParam.setEntityId(entityId);
			
			sign = MD5Utils.generateSignForBossAPI(secret, baseParam.getBodyForSaveForceMenu());
			baseParam.setSign(sign);
			
			Response response = RequestForBoss.saveForceMenu(httpRequest, httpHeader, baseParam); 

			flag = true;
			
		}catch(Exception e){
			logger.info(e.toString());
		}
		
		return flag;
		
	}


	/**
	 * 删除必选商品设置
	 * 设置的 configId 是 menuId 对应的 移除配置 Id 
	 * @param httpRequest
	 * @param entityId
	 * @param configId
	 */
	public Boolean removeForceMenu(HttpRequestEx httpRequest, String entityId, String menuId){
		
		Boolean flag = false;
		try{
			
			logger.info("掌柜端设置：删除必选商品设置");
			
			setup(); // 初始设置, 包含清空个性化设置功能
			baseParam.setEntityId(entityId);
			
			MultiValueMap menuMultiValueMap = queryForceMenu(httpRequest, entityId);
			if(null == menuMultiValueMap)
				return true;
			Collection configCollection = menuMultiValueMap.getCollection(menuId);  
						
			
			Iterator configIterator = configCollection.iterator();
			while(configIterator.hasNext()){
				baseParam.setConfigId(configIterator.next().toString());						
				sign = MD5Utils.generateSignForBossAPI(secret, baseParam.getBodyForRemoveForceMenu());
				baseParam.setSign(sign);
				
				Response response = RequestForBoss.removeForceMenu(httpRequest, httpHeader, baseParam); 
				
				Assert.assertEquals(response.getStatus(), 200);
				JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
				Assert.assertEquals(resp.get("code").getAsInt(), 1);
			}
			
			flag = true;
			
		}catch(Exception e){
			logger.info(e.toString());
			
		}
		
		return flag;
		
	}
	
	
	/**
	 * 查询必选商品设置
	 * @param httpRequest
	 * @param entityId
	 * @return
	 */
	public MultiValueMap queryForceMenu(HttpRequestEx httpRequest, String entityId){
		
		MultiValueMap forceMenuList = new MultiValueMap();
		
		try{
			
			logger.info("掌柜端设置：查询必选商品设置");
			
			setup(); // 初始设置, 包含清空个性化设置功能
			baseParam.setEntityId(entityId);
			sign = MD5Utils.generateSignForBossAPI(secret, baseParam.getBodyForQueryForceMenu());
			baseParam.setSign(sign);
			
			// 查询必选菜单列表
			Response response = RequestForBoss.queryForceMenuList(httpRequest, httpHeader, baseParam); 
			
			// 获取必选菜单数据
			JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();	
			JsonArray forceMenuArray = null;
			if(resp.has("data"))
				forceMenuArray = resp.get("data").getAsJsonArray();
			else {
				return null;
			}
			
			// 将必选菜单数据放入  MultiValueMap  
			for(JsonElement menuElement : forceMenuArray){
				JsonObject menuItem = menuElement.getAsJsonObject();
				forceMenuList.put(menuItem.get("menuId").getAsString(), menuItem.get("forceConfigVo").getAsJsonObject().get("configId").getAsString());
			}
			
		}catch(Exception e){
			logger.info(e.toString());
		}
		
		return forceMenuList;
	}
	
	
	
	/**
	 * idStr 表示掌柜端基础设置中的配置项列表字符串
	 * open 为 true 表示开启预付款开关, 为 false 表示关闭预付款开关
	 * @param httpRequest
	 * @param idsStr
	 * @param open
	 * @return
	 */
	public boolean prePaySwitch(HttpRequestEx httpRequest, String idsStr, String entityId){
			
		boolean flag = false;
		
		try{
										
			setup(); // 初始设置, 包含清空个性化设置功能
			baseParam.setEntityId(entityId);
			baseParam.setIds_str(idsStr);
			
			sign = MD5Utils.generateSignForBossAPI(secret, baseParam.getBodyForPrePaySwitchWithSeatCode());
			baseParam.setSign(sign);			
			
			// 设置掌柜端扫桌码时的预付款开关
			Response response = RequestForBoss.prePaySwitch(httpRequest, httpHeader, baseParam); 
			
			Assert.assertEquals(response.getStatus(), 200);
			JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(resp.get("code").getAsInt(), 1);
			
			flag = true;
			
		}catch(Exception e){
			logger.info(e.toString());
		}
		
		return flag;
	}
	
//	/**
//	 * 该接口对应于掌柜端的基础设置，用于对预付款开关等进行设置 <br/>
//	 * config 表示具体要操作的配置项的 id, switchConfig 为 true 表示打开该配置, 为 false 表示关闭该配置项 <br/>
//	 * basicSetting 表示基础设置类型,比如 非预付款扫桌码, 非预付款扫店码等
//	 * @param httpRequestForBossAPI
//	 * @param bossController
//	 * @param configId
//	 * @param switchForConfig
//	 * @return
//	 */
//	public boolean basicConfigForBoss(HttpRequestEx httpRequestForBossAPI, BossController bossController, 
//			BasicSetting basicSetting, String configId, boolean switchForConfig){
//		
//		boolean flag = false;
//		
//		try{
//			
//			if(switchForConfig){
//				logger.info("打开" + basicSetting.showDescription() + "开关");
//			}else{
//				logger.info("关闭" + basicSetting.showDescription() + "开关");
//			}
//			
//			Map<String, String> targetConfigMap = new HashMap<String, String>();
//			String value = switchForConfig ? ("1") : ("0");
//			targetConfigMap.put(configId, value);
//			
//			BossConfig bossConfig = new BossConfig();
//			String configSettingString = bossConfig.BasicSet(targetConfigMap);
//			
//			flag = bossController.prePaySwitch(httpRequestForBossAPI, configSettingString, entityId);
//
//		}catch(Exception e){
//			logger.error(e.toString());
//		}
//		
//		return flag;
//	}
	
	
	/**
	 * 进行掌柜端的基础设置，用于对预付款开关等进行设置 <br/>
	 * config 表示具体要操作的配置项的 id, switchConfig 为 true 表示打开该配置, 为 false 表示关闭该配置项 <br/>
	 * basicSetting 表示基础设置类型,比如 非预付款扫桌码, 非预付款扫店码等
	 */
	public boolean basicConfigForBoss(HttpRequestEx httpRequestForBossAPI, String entityId,
			BasicSetting basicSetting, String configId, boolean switchForConfig){
		
		boolean flag = false;
		
		try{
			
			if(switchForConfig){
				logger.info("打开" + basicSetting.showDescription() + "开关");
			}else{
				logger.info("关闭" + basicSetting.showDescription() + "开关");
			}
			
			Map<String, String> targetConfigMap = new HashMap<String, String>();
			String value = switchForConfig ? ("1") : ("0");
			targetConfigMap.put(configId, value);
			
			BossConfig bossConfig = new BossConfig();
			String configSettingString = bossConfig.BasicSet(targetConfigMap);
			
			flag = prePaySwitch(httpRequestForBossAPI, configSettingString, entityId);

		}catch(Exception e){
			logger.error(e.toString());
		}
		
		return flag;
	}
	
	
	/**
	 * menu_str 以及  menu_prop_str 是掌柜端对商品进行属性设置时传送的字段
	 * 该接口主要通过掌柜端接口对商品属性进行设置
	 * @param httpRequest
	 * @param entityId
	 * @param menu_str
	 * @param menu_prop_str
	 * @return
	 */
	public boolean menuConfigUpdate(HttpRequestEx httpRequest, String entityId, String menu_str, String menu_prop_str ){
		
		boolean flag = false;
		
		try{
			setup(); // 初始设置, 包含清空个性化设置功能
			baseParam.setEntityId(entityId);
			baseParam.setMenu_str(menu_str);
			baseParam.setMenu_prop_str(menu_prop_str);
			
			sign = MD5Utils.generateSignForBossAPI(secret, baseParam.getBodyForMenuConfigUpdate());
			baseParam.setSign(sign);			
			
			// 设置掌柜端扫桌码时的预付款开关
			Response response = RequestForBoss.menuConfigUpdate(httpRequest, httpHeader, baseParam); 
			
			Assert.assertEquals(response.getStatus(), 200);
			JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();			
			Assert.assertEquals(resp.get("code").getAsInt(), 1);
			
			flag = true;	
			
		}catch(Exception e){
			logger.error(e.toString());
		}
		
		return flag;
		
	}

}
