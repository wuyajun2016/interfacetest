package com.dfire.qa.meal.vo.boss;

import java.util.HashMap;
import java.util.Map;

import com.dfire.qa.meal.enums.Privilege;
import com.dfire.qa.meal.logger.LoggerMarkers;
import com.dfire.qa.meal.logger.MealLoggerFactory;
import com.google.gson.Gson;

public class BaseBossBean {
	
	// just for test
	private String entityId;
	
	
	// real parameter
	private ForceConfig forceConfig;
	private String configId;
	private String sign;
	
	private String appKey;
	private String appVersion;
	private String deviceId;
	
	private String format;
	private String sessionKey;	
	private String timeStamp;
	
	// config string for basic setting
	private String ids_str;
	
	// config map json for batch setting
	private String config_map_json;
	
	
	// config for take out
	private String settings_json;
		
	
	// config for take out time 
	private String time_json;
	
	
	// config for delivery man
	private String delivery_man_json;
	
	
	// config for upload file
	private String domain;
	
	// config for sava/delete upload image
	private String item_exts;
	private String del_item_ids;
	
	
	
	// config for style
	private String style;
	
	
	// config for menu repeat order
	private Integer status;
	
	
	// config for menu repeat delete menu
	private String menu_id;
	
	
	// config for save coupon
	private String coupon_promotion_str;
	
	
	// config for delete coupon 
	private String coupon_promotion_id;
	
	
	// config for save promotion
	private String sales_promotion_str;

	
	// config for entityType
	private String entityType;
	
	
	// config for member---privilege
	private String birth_privilege_str;
	private String memory_privilege_str;
	private String coupon_privilege_str;
	private String custom_privilege_str;
	
	private String customer_right_id;
	private String right_interest_Id;
	
	private Integer last_ver;
	private Integer last_version;
	
	// config for card pricilege 
	private String id;
	
	
	// config for add card privilege in the list
	private String kind_card_str;
	
	// config for member---privilege
	private Integer customer_level;
	
	// config for member---card privilege
	private String card_privilege_str;
	
	
	
	private String money_rule_str;
	
	
	private String self_recharge_str_list;
	
	
	private String gift_str;
	
	private String gift_convert_str;
	
	private String menu_label_str;
	
	private Integer is_turn_on;
	
	private String plan_config_json;
	
	private Integer is_template_on;
	
	// config for template
	private Integer sort_code;
	private Integer is_hidden;
	private Integer cpi;
	
	private String template_name;
	private String cover_path;
	private String shop_template_id;
	
	// config string for menu setting
	private String menu_str;
	private String menu_prop_str;
	

	/**
	 * 设置特权字段
	 * @param privilege
	 * @param privilegeStr
	 */
	public void setPrivilege(Privilege privilege, String privilegeStr){
		
		if(privilege == Privilege.BIRTH)
			setBirth_privilege_str(privilegeStr);
		
		else if(privilege == Privilege.MEMORY)
			setMemory_privilege_str(privilegeStr);
		
		else if(privilege == Privilege.COUPON)
			setCoupon_privilege_str(privilegeStr);
		
		else if(privilege == Privilege.CUSTOMPRIVILEGE)
			setCustom_privilege_str(privilegeStr);
		
		else if(privilege == Privilege.CARDPRIVILEGE)
			setCard_privilege_str(privilegeStr);
		
		else {
			MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, "there is no match privilege to set");
		}
	}
	
	
	/**
	 * 设置特权字段
	 * @param privilege
	 * @param privilegeStr
	 */
	public String getPrivilege(Privilege privilege){
		
		if(privilege == Privilege.BIRTH)
			return getBirth_privilege_str();
		
		else if(privilege == Privilege.MEMORY)
			return getMemory_privilege_str();
		
		else if(privilege == Privilege.COUPON)
			return getCoupon_privilege_str();
		
		else if(privilege == Privilege.CUSTOMPRIVILEGE)
			return getCustom_privilege_str();
		
		else if(privilege == Privilege.CARDPRIVILEGE)
			return getCard_privilege_str();
		
		else {
			MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, "there is no match privilege to get");
			return null;
		}
	}
	
	
	
	/**
	 * 用于计算 sign
	 * 针对设置必选菜
	 * @return
	 */
	public Map<String, String> getBodyForSaveForceMenu(){
		
		Gson gson = new Gson();
		Map<String, String> saveForMenuMap = new HashMap<String, String>();
		saveForMenuMap.put("app_key", getAppKey());
		saveForMenuMap.put("app_version", getAppVersion());
		saveForMenuMap.put("device_id", getDeviceId());
		
		saveForMenuMap.put("force_config", gson.toJson(getForceConfig()));
		saveForMenuMap.put("format", getFormat());
		saveForMenuMap.put("session_key", getSessionKey());
		
		saveForMenuMap.put("timestamp", getTimeStamp());
		saveForMenuMap.put("entityId", getEntityId());
		
		return saveForMenuMap;
	}
	
	/**
	 * 用于计算 sign
	 * 用于移除必选菜
	 * @return
	 */
	public Map<String, String> getBodyForRemoveForceMenu(){
		
		Map<String, String> saveForMenuMap = new HashMap<String, String>();
		saveForMenuMap.put("app_key", getAppKey());
		saveForMenuMap.put("app_version", getAppVersion());
		saveForMenuMap.put("device_id", getDeviceId());
		
		saveForMenuMap.put("config_id", getConfigId());
		saveForMenuMap.put("format", getFormat());
		saveForMenuMap.put("session_key", getSessionKey());
		
		saveForMenuMap.put("timestamp", getTimeStamp());
		saveForMenuMap.put("entityId", getEntityId());
		
		return saveForMenuMap;
	}
	
	
	/**
	 * 用于计算 sign
	 * 用于查询必选菜
	 * @return
	 */
	public Map<String, String> getBodyForQueryForceMenu(){
		
		Map<String, String> saveForMenuMap = new HashMap<String, String>();
		saveForMenuMap.put("app_key", getAppKey());
		saveForMenuMap.put("app_version", getAppVersion());
		saveForMenuMap.put("device_id", getDeviceId());
		
		saveForMenuMap.put("format", getFormat());
		saveForMenuMap.put("session_key", getSessionKey());
		
		saveForMenuMap.put("timestamp", getTimeStamp());
		saveForMenuMap.put("entityId", getEntityId());
		
		return saveForMenuMap;
	}
	
	
	/**
	 * 用于计算 sign
	 * 用于开启或者关闭扫桌码预付款开关
	 * @return
	 */
	public Map<String, String> getBodyForPrePaySwitchWithSeatCode(){
		
		Map<String, String> saveForMenuMap = new HashMap<String, String>();
		saveForMenuMap.put("app_key", getAppKey());
		saveForMenuMap.put("app_version", getAppVersion());
		saveForMenuMap.put("device_id", getDeviceId());
		
		saveForMenuMap.put("format", getFormat());
		saveForMenuMap.put("ids_str", getIds_str());
		
		saveForMenuMap.put("timestamp", getTimeStamp());
		saveForMenuMap.put("entityId", getEntityId());
		
		return saveForMenuMap;
	}
	
	
	
	/**
	 * 用于计算 sign
	 * 用于设置掌柜端商品的基本属性
	 * @return
	 */
	public Map<String, String> getBodyForMenuConfigUpdate(){
		
		Map<String, String> saveForMenuMap = new HashMap<String, String>();
		saveForMenuMap.put("app_key", getAppKey());
		saveForMenuMap.put("app_version", getAppVersion());
		
		saveForMenuMap.put("device_id", getDeviceId());		
		saveForMenuMap.put("format", getFormat());
		
		saveForMenuMap.put("menu_str", getMenu_str());
		saveForMenuMap.put("menu_prop_str", getMenu_prop_str());
		
		saveForMenuMap.put("timestamp", getTimeStamp());
		saveForMenuMap.put("entityId", getEntityId());
		
		return saveForMenuMap;
		
	}
	
	
	
	/**
	 * 用于计算 sign
	 * 用于批量设置开关
	 * @return
	 */
	public Map<String, String> getBodyForBatchSetConfig(){
		
		Map<String, String> saveForMenuMap = new HashMap<String, String>();
		saveForMenuMap.put("app_key", getAppKey());
		saveForMenuMap.put("app_version", getAppVersion());
		saveForMenuMap.put("device_id", getDeviceId());
		
		saveForMenuMap.put("format", getFormat());
		saveForMenuMap.put("config_map_json", getConfig_map_json());
		
		saveForMenuMap.put("timestamp", getTimeStamp());
		saveForMenuMap.put("entityId", getEntityId());
		
		return saveForMenuMap;
	}
	
	
	
	
	/**
	 * 用于计算 sign
	 * 用于外卖设置开关
	 * @return
	 */
	public Map<String, String> getBodyForTakeOutConfig(){
		
		Map<String, String> saveForMenuMap = new HashMap<String, String>();
		saveForMenuMap.put("app_key", getAppKey());
		saveForMenuMap.put("app_version", getAppVersion());
		saveForMenuMap.put("device_id", getDeviceId());
		
		saveForMenuMap.put("format", getFormat());
		saveForMenuMap.put("settings_json", getSettings_json());
		
		saveForMenuMap.put("timestamp", getTimeStamp());
		saveForMenuMap.put("entityId", getEntityId());
		
		return saveForMenuMap;
	}
	
	/**
	 * 用于计算 sign
	 * 用于外卖配送时间设置开关
	 * @return
	 */
	public Map<String, String> getBodyForTakeOutTimeConfig(){
		
		Map<String, String> saveForMenuMap = new HashMap<String, String>();
		saveForMenuMap.put("app_key", getAppKey());
		saveForMenuMap.put("app_version", getAppVersion());
		saveForMenuMap.put("device_id", getDeviceId());
		
		saveForMenuMap.put("format", getFormat());
		saveForMenuMap.put("time_json", getTime_json());
		
		saveForMenuMap.put("timestamp", getTimeStamp());
		saveForMenuMap.put("entityId", getEntityId());
		
		return saveForMenuMap;
	}
	
	
	
	/**
	 * 用于计算 sign
	 * 用于外卖配送人员设置开关
	 * @return
	 */
	public Map<String, String> getBodyForTakeOutDeliveryMan(){
		
		Map<String, String> saveForMenuMap = new HashMap<String, String>();
		saveForMenuMap.put("app_key", getAppKey());
		saveForMenuMap.put("app_version", getAppVersion());
		saveForMenuMap.put("device_id", getDeviceId());
		
		saveForMenuMap.put("format", getFormat());
		saveForMenuMap.put("delivery_man_json", getDelivery_man_json());
		
		saveForMenuMap.put("timestamp", getTimeStamp());
		saveForMenuMap.put("entityId", getEntityId());
		
		return saveForMenuMap;
	}
	
	
	
	/**
	 * 用于计算 sign
	 * 用于上传文件
	 * @return
	 */
	public Map<String, String> getBodyForUploadFile(){
		
		Map<String, String> saveForMenuMap = new HashMap<String, String>();
		saveForMenuMap.put("app_key", getAppKey());
		saveForMenuMap.put("app_version", getAppVersion());
		saveForMenuMap.put("device_id", getDeviceId());
		
		saveForMenuMap.put("format", getFormat());
		saveForMenuMap.put("domain", getDomain());
		
		saveForMenuMap.put("timestamp", getTimeStamp());
		saveForMenuMap.put("entityId", getEntityId());
		
		return saveForMenuMap;
	}
	
	
	/**
	 * 用于计算 sign
	 * 用于商品页首与页尾图片上传后的设置开关
	 * @return
	 */
	public Map<String, String> getBodyForImageSetConfig(){
		
		Map<String, String> saveForMenuMap = new HashMap<String, String>();
		saveForMenuMap.put("app_key", getAppKey());
		saveForMenuMap.put("app_version", getAppVersion());
		saveForMenuMap.put("device_id", getDeviceId());
		
		saveForMenuMap.put("del_item_ids", getDel_item_ids());
		saveForMenuMap.put("item_exts", getItem_exts());
		
		saveForMenuMap.put("timestamp", getTimeStamp());
		saveForMenuMap.put("entityId", getEntityId());
		saveForMenuMap.put("format", getFormat());
		
		return saveForMenuMap;
	}
	
	
	
	/**
	 * 用于计算 sign
	 * 用于个性化换肤--色调风格的设置开关
	 * @return
	 */
	public Map<String, String> getBodyForColorStyleConfig(){
		
		Map<String, String> saveForMenuMap = new HashMap<String, String>();
		saveForMenuMap.put("app_key", getAppKey());
		saveForMenuMap.put("app_version", getAppVersion());
		saveForMenuMap.put("device_id", getDeviceId());
		
		saveForMenuMap.put("style", getStyle());
		
		saveForMenuMap.put("timestamp", getTimeStamp());
		saveForMenuMap.put("entityId", getEntityId());
		saveForMenuMap.put("format", getFormat());
		
		return saveForMenuMap;
	}
	
	
	
	/**
	 * 用于计算 sign
	 * 用于顾客端设置--顾客点餐重复提醒
	 * @return
	 */
	public Map<String, String> getBodyForMenuRepeatRemaindConfig(){
		
		Map<String, String> saveForMenuMap = new HashMap<String, String>();
		saveForMenuMap.put("app_key", getAppKey());
		saveForMenuMap.put("app_version", getAppVersion());
		saveForMenuMap.put("device_id", getDeviceId());
		
		saveForMenuMap.put("status", Integer.toString(getStatus()));
		
		saveForMenuMap.put("timestamp", getTimeStamp());
		saveForMenuMap.put("entityId", getEntityId());
		saveForMenuMap.put("format", getFormat());
		
		return saveForMenuMap;
	}
	
	
	
	/**
	 * 用于计算 sign
	 * 用于顾客端设置--顾客点餐重复提醒--加菜
	 * @return
	 */
	public Map<String, String> getBodyForMenuRepeatAddMenuConfig(){
		
		Map<String, String> saveForMenuMap = new HashMap<String, String>();
		saveForMenuMap.put("app_key", getAppKey());
		saveForMenuMap.put("app_version", getAppVersion());
		saveForMenuMap.put("device_id", getDeviceId());
		
		saveForMenuMap.put("format", getFormat());
		saveForMenuMap.put("ids_str", getIds_str());
		
		saveForMenuMap.put("timestamp", getTimeStamp());
		saveForMenuMap.put("entityId", getEntityId());
		
		return saveForMenuMap;
	}
	
	
	
	/**
	 * 用于计算 sign
	 * 用于顾客端设置--顾客点餐重复提醒--删除菜
	 * @return
	 */
	public Map<String, String> getBodyForMenuRepeatDeleteMenuConfig(){
		
		Map<String, String> saveForMenuMap = new HashMap<String, String>();
		saveForMenuMap.put("app_key", getAppKey());
		saveForMenuMap.put("app_version", getAppVersion());
		saveForMenuMap.put("device_id", getDeviceId());
		
		saveForMenuMap.put("format", getFormat());
		saveForMenuMap.put("menu_id", getMenu_id());
		
		saveForMenuMap.put("timestamp", getTimeStamp());
		saveForMenuMap.put("entityId", getEntityId());
		
		return saveForMenuMap;
	}
	
	
	
	/**
	 * 用于计算 sign
	 * 用于会员设置--设置优惠券
	 * @return
	 */
	public Map<String, String> getBodyForSaveCouponConfig(){
		
		Map<String, String> saveForMenuMap = new HashMap<String, String>();
		saveForMenuMap.put("app_key", getAppKey());
		saveForMenuMap.put("app_version", getAppVersion());
		saveForMenuMap.put("device_id", getDeviceId());
		
		saveForMenuMap.put("format", getFormat());
		saveForMenuMap.put("coupon_promotion_str", getCoupon_promotion_str());
		
		saveForMenuMap.put("timestamp", getTimeStamp());
		saveForMenuMap.put("entityId", getEntityId());
		
		return saveForMenuMap;
	}
	
	
	
	
	/**
	 * 用于计算 sign
	 * 用于会员设置--删除优惠券
	 * @return
	 */
	public Map<String, String> getBodyForDeleteCouponConfig(){
		
		Map<String, String> saveForMenuMap = new HashMap<String, String>();
		saveForMenuMap.put("app_key", getAppKey());
		saveForMenuMap.put("app_version", getAppVersion());
		saveForMenuMap.put("device_id", getDeviceId());
		
		saveForMenuMap.put("format", getFormat());
		saveForMenuMap.put("coupon_promotion_id", getCoupon_promotion_id());
		
		saveForMenuMap.put("timestamp", getTimeStamp());
		saveForMenuMap.put("entityId", getEntityId());
		saveForMenuMap.put("entityType", getEntityType());
		
		return saveForMenuMap;
	}
	
	
	
	/**
	 * 用于计算 sign
	 * 用于会员设置--保存促销
	 * @return
	 */
	public Map<String, String> getBodyForSavePromotion(){
		
		Map<String, String> saveForMenuMap = new HashMap<String, String>();
		saveForMenuMap.put("app_key", getAppKey());
		saveForMenuMap.put("app_version", getAppVersion());
		saveForMenuMap.put("device_id", getDeviceId());
		
		saveForMenuMap.put("format", getFormat());
		saveForMenuMap.put("sales_promotion_str", getSales_promotion_str());
		
		saveForMenuMap.put("timestamp", getTimeStamp());
		saveForMenuMap.put("entityId", getEntityId());
		saveForMenuMap.put("entityType", getEntityType());
		
		return saveForMenuMap;
	}
	
	
	/**
	 * 用于计算 sign
	 * 用于会员设置--保存会员等级特权
	 * @return
	 */
	public Map<String, String> getBodyForSavePrivilege(Privilege privilege){
		
		Map<String, String> saveForMenuMap = new HashMap<String, String>();
		saveForMenuMap.put("app_key", getAppKey());
		saveForMenuMap.put("app_version", getAppVersion());
		saveForMenuMap.put("device_id", getDeviceId());
		
		saveForMenuMap.put("format", getFormat());
		saveForMenuMap.put(privilege.getField(), getPrivilege(privilege));
		saveForMenuMap.put("customer_level", Integer.toString(getCustomer_level()));
		
		saveForMenuMap.put("timestamp", getTimeStamp());
		saveForMenuMap.put("entityId", getEntityId());
		saveForMenuMap.put("entityType", getEntityType());
		
		return saveForMenuMap;
	}
	
	
	
	/**
	 * 用于计算 sign
	 * 用于会员设置--删除一般特权
	 * @return
	 */
	public Map<String, String> getBodyForDeletePrivilege(){
		
		Map<String, String> saveForMenuMap = new HashMap<String, String>();
		saveForMenuMap.put("app_key", getAppKey());
		saveForMenuMap.put("app_version", getAppVersion());
		saveForMenuMap.put("device_id", getDeviceId());
		
		saveForMenuMap.put("format", getFormat());
		saveForMenuMap.put("customer_right_id", getCustomer_right_id());
		
		saveForMenuMap.put("timestamp", getTimeStamp());
		saveForMenuMap.put("entityId", getEntityId());
//		saveForMenuMap.put("entityType", getEntityType());
		
		saveForMenuMap.put("last_ver", Integer.toString(getLast_ver()));
		
		return saveForMenuMap;
	}
	
	
	
	/**
	 * 用于计算 sign
	 * 用于会员设置--删除自定义特权
	 * @return
	 */
	public Map<String, String> getBodyForDeleteCustomPrivilege(){
		
		Map<String, String> saveForMenuMap = new HashMap<String, String>();
		saveForMenuMap.put("app_key", getAppKey());
		saveForMenuMap.put("app_version", getAppVersion());
		saveForMenuMap.put("device_id", getDeviceId());
		
		saveForMenuMap.put("format", getFormat());
		saveForMenuMap.put("customer_right_id", getCustomer_right_id());
		saveForMenuMap.put("right_interest_Id", getRight_interest_Id());
		
		saveForMenuMap.put("timestamp", getTimeStamp());
		saveForMenuMap.put("entityId", getEntityId());
//		saveForMenuMap.put("entityType", getEntityType());
		
		saveForMenuMap.put("last_version", Integer.toString(getLast_version()));
		
		return saveForMenuMap;
	}
	
	
	/**
	 * 用于计算 sign
	 * 用于会员设置--添加自定义会员等级特权
	 * @return
	 */
	public Map<String, String> getBodyForAddCustomPrivilege(){
		
		Map<String, String> saveForMenuMap = new HashMap<String, String>();
		saveForMenuMap.put("app_key", getAppKey());
		saveForMenuMap.put("app_version", getAppVersion());
		saveForMenuMap.put("device_id", getDeviceId());
		
		saveForMenuMap.put("format", getFormat());
		saveForMenuMap.put("custom_privilege_str", getCustom_privilege_str());
		
		saveForMenuMap.put("timestamp", getTimeStamp());
		saveForMenuMap.put("entityId", getEntityId());
		saveForMenuMap.put("entityType", getEntityType());
		
		return saveForMenuMap;
	}
	
	
	
	/**
	 * 用于计算 sign
	 * 用于会员设置--获取所有会员等级特权详情
	 * @return
	 */
	public Map<String, String> getBodyFoGetAllPrivilegeDetail(){
		
		Map<String, String> saveForMenuMap = new HashMap<String, String>();
		saveForMenuMap.put("app_key", getAppKey());
		saveForMenuMap.put("app_version", getAppVersion());
		saveForMenuMap.put("device_id", getDeviceId());
		
		saveForMenuMap.put("format", getFormat());		
		saveForMenuMap.put("timestamp", getTimeStamp());
		
		saveForMenuMap.put("entityId", getEntityId());

		
		return saveForMenuMap;
	}
	
	
	
	/**
	 * 用于计算 sign
	 * 用于会员设置--删除特权卡
	 * @return
	 */
	public Map<String, String> getBodyForDeleteCardPrivilege(){
		
		Map<String, String> saveForMenuMap = new HashMap<String, String>();
		saveForMenuMap.put("app_key", getAppKey());
		saveForMenuMap.put("app_version", getAppVersion());
		saveForMenuMap.put("device_id", getDeviceId());
		
		saveForMenuMap.put("format", getFormat());
		saveForMenuMap.put("id", getId());
		
		saveForMenuMap.put("timestamp", getTimeStamp());
		saveForMenuMap.put("entityId", getEntityId());
		
		return saveForMenuMap;
	}
	
	
	
	
	/**
	 * 用于计算 sign
	 * 用于会员设置--在特权库中添加特权
	 * @return
	 */
	public Map<String, String> getBodyForSaveCardPrivilege(){
		
		Map<String, String> saveForMenuMap = new HashMap<String, String>();
		saveForMenuMap.put("app_key", getAppKey());
		saveForMenuMap.put("app_version", getAppVersion());
		saveForMenuMap.put("device_id", getDeviceId());
		
		saveForMenuMap.put("format", getFormat());
		saveForMenuMap.put("kind_card_str", getKind_card_str());
		
		saveForMenuMap.put("timestamp", getTimeStamp());
		saveForMenuMap.put("entityId", getEntityId());
		saveForMenuMap.put("entityType", getEntityType());
		
		return saveForMenuMap;
	}
	
	
	
	/**
	 * 用于计算 sign
	 * 用于会员设置--获取所有自定义列表权限
	 * @return
	 */
	public Map<String, String> getBodyFoGetCustomPrivilegeList(){
		
		Map<String, String> saveForMenuMap = new HashMap<String, String>();
		saveForMenuMap.put("app_key", getAppKey());
		saveForMenuMap.put("app_version", getAppVersion());
		saveForMenuMap.put("device_id", getDeviceId());
		
		saveForMenuMap.put("format", getFormat());		
		saveForMenuMap.put("timestamp", getTimeStamp());
		
		saveForMenuMap.put("entityId", getEntityId());
		saveForMenuMap.put("customer_level", Integer.toString(getCustomer_level()));

		
		return saveForMenuMap;
	}
	
	

	/**
	 * 用于计算 sign
	 * 用于会员设置--卡充值---获取优惠券
	 * @return
	 */
	public Map<String, String> getBodyForQueryKindCards(){
		
		Map<String, String> saveForMenuMap = new HashMap<String, String>();
		saveForMenuMap.put("app_key", getAppKey());
		saveForMenuMap.put("app_version", getAppVersion());
		saveForMenuMap.put("device_id", getDeviceId());
		
		saveForMenuMap.put("format", getFormat());		
		saveForMenuMap.put("timestamp", getTimeStamp());
		
		saveForMenuMap.put("entityId", getEntityId());
		

		
		return saveForMenuMap;
	}
	
	
	/**
	 * 用于计算 sign
	 * 用于会员设置--卡充值---设置顾客端使用火小二自助充值
	 * @return
	 */
	public Map<String, String> getBodyForSaveRechargeRule(){
		
		Map<String, String> saveForMenuMap = new HashMap<String, String>();
		saveForMenuMap.put("app_key", getAppKey());
		saveForMenuMap.put("app_version", getAppVersion());
		saveForMenuMap.put("device_id", getDeviceId());
		
		saveForMenuMap.put("format", getFormat());		
		saveForMenuMap.put("timestamp", getTimeStamp());
		
		saveForMenuMap.put("entityId", getEntityId());
		saveForMenuMap.put("money_rule_str", getMoney_rule_str());

		
		return saveForMenuMap;
	}
	
	
	
	/**
	 * 用于计算 sign
	 * 用于会员设置--卡充值---删除所有充值优惠信息
	 * @return
	 */
	public Map<String, String> getBodyForDeleteAllRecharge(){
		
		Map<String, String> saveForMenuMap = new HashMap<String, String>();
		saveForMenuMap.put("app_key", getAppKey());
		saveForMenuMap.put("app_version", getAppVersion());
		saveForMenuMap.put("device_id", getDeviceId());
		
		saveForMenuMap.put("format", getFormat());		
		saveForMenuMap.put("timestamp", getTimeStamp());
		
		saveForMenuMap.put("entityId", getEntityId());
		saveForMenuMap.put("ids_str", getIds_str());

		
		return saveForMenuMap;
	}
	
	
	
	
	/**
	 * 用于计算 sign
	 * 用于会员设置--卡充值---设置顾客端使用火小二自助充值
	 * @return
	 */
	public Map<String, String> getBodyForSelfRecharge(){
		
		Map<String, String> saveForMenuMap = new HashMap<String, String>();
		saveForMenuMap.put("app_key", getAppKey());
		saveForMenuMap.put("app_version", getAppVersion());
		saveForMenuMap.put("device_id", getDeviceId());
		
		saveForMenuMap.put("format", getFormat());		
		saveForMenuMap.put("timestamp", getTimeStamp());
		
		saveForMenuMap.put("entityId", getEntityId());
		saveForMenuMap.put("self_recharge_str_list", getSelf_recharge_str_list());

		
		return saveForMenuMap;
	}
	
	
	
	/**
	 * 用于计算 sign
	 * 用于会员设置--积分兑换---添加卡金额/优惠券
	 * @return
	 */
	public Map<String, String> getBodyForSaveGift(){
		
		Map<String, String> saveForMenuMap = new HashMap<String, String>();
		saveForMenuMap.put("app_key", getAppKey());
		saveForMenuMap.put("app_version", getAppVersion());
		saveForMenuMap.put("device_id", getDeviceId());
		
		saveForMenuMap.put("format", getFormat());		
		saveForMenuMap.put("timestamp", getTimeStamp());
		
		saveForMenuMap.put("entityId", getEntityId());
		saveForMenuMap.put("gift_str", getGift_str());

		
		return saveForMenuMap;
	}
	
	
	
	/**
	 * 用于计算 sign
	 * 用于会员设置--积分兑换---添加卡金额/优惠券
	 * @return
	 */
	public Map<String, String> getBodyForSaveGiftConvert(){
		
		Map<String, String> saveForMenuMap = new HashMap<String, String>();
		saveForMenuMap.put("app_key", getAppKey());
		saveForMenuMap.put("app_version", getAppVersion());
		saveForMenuMap.put("device_id", getDeviceId());
		
		saveForMenuMap.put("format", getFormat());		
		saveForMenuMap.put("timestamp", getTimeStamp());
		
		saveForMenuMap.put("entityId", getEntityId());
		saveForMenuMap.put("gift_convert_str", getGift_convert_str());

		
		return saveForMenuMap;
	}
	
	
	
	/**
	 * 用于计算 sign
	 * 用于会员设置--积分兑换---添加卡金额/优惠券
	 * @return
	 */
	public Map<String, String> getBodyForUpdateMenuLabel(){
		
		Map<String, String> saveForMenuMap = new HashMap<String, String>();
		saveForMenuMap.put("app_key", getAppKey());
		saveForMenuMap.put("app_version", getAppVersion());
		saveForMenuMap.put("device_id", getDeviceId());
		
		saveForMenuMap.put("format", getFormat());		
		saveForMenuMap.put("timestamp", getTimeStamp());
		
		saveForMenuMap.put("entityId", getEntityId());
		saveForMenuMap.put("menu_label_str", getMenu_label_str());
		saveForMenuMap.put("menu_id", getMenu_id());

		
		return saveForMenuMap;
	}
	
	

	/**
	 * 用于计算 sign
	 * 用于智能点餐--顾客点餐智能提醒与推荐
	 * @return
	 */
	public Map<String, String> getBodyForSaveEntityConfig(){
		
		Map<String, String> saveForMenuMap = new HashMap<String, String>();
		saveForMenuMap.put("app_key", getAppKey());
		saveForMenuMap.put("app_version", getAppVersion());
		saveForMenuMap.put("device_id", getDeviceId());
		
		saveForMenuMap.put("format", getFormat());		
		saveForMenuMap.put("timestamp", getTimeStamp());
		
		saveForMenuMap.put("entityId", getEntityId());
		saveForMenuMap.put("is_turn_on", Integer.toString(getIs_turn_on()));

		
		return saveForMenuMap;
	}
	
	
	
	/**
	 * 用于计算 sign
	 * 用于智能点餐--顾客点餐智能提醒与推荐
	 * @return
	 */
	public Map<String, String> getBodyForSavePlanConfig(){
		
		Map<String, String> saveForMenuMap = new HashMap<String, String>();
		saveForMenuMap.put("app_key", getAppKey());
		saveForMenuMap.put("app_version", getAppVersion());
		saveForMenuMap.put("device_id", getDeviceId());
		
		saveForMenuMap.put("format", getFormat());		
		saveForMenuMap.put("timestamp", getTimeStamp());
		
		saveForMenuMap.put("entityId", getEntityId());
		saveForMenuMap.put("plan_config_json", getPlan_config_json());

		
		return saveForMenuMap;
	}
	
	
	
	/**
	 * 用于计算 sign
	 * 用于智能点餐--一键智能点餐模版设置
	 * @return
	 */
	public Map<String, String> getBodyForTemplateConfig(){
		
		Map<String, String> saveForMenuMap = new HashMap<String, String>();
		saveForMenuMap.put("app_key", getAppKey());
		saveForMenuMap.put("app_version", getAppVersion());
		saveForMenuMap.put("device_id", getDeviceId());
		
		saveForMenuMap.put("format", getFormat());		
		saveForMenuMap.put("timestamp", getTimeStamp());
		
		saveForMenuMap.put("entityId", getEntityId());
		saveForMenuMap.put("is_template_on", Integer.toString(getIs_template_on()));

		
		return saveForMenuMap;
	}
	
	
	public String getConfigId() {
		return configId;
	}
	
	public void setConfigId(String configId) {
		this.configId = configId;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getAppVersion() {
		return appVersion;
	}
	public void setAppVersion(String appVersion) {
		this.appVersion = appVersion;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getSessionKey() {
		return sessionKey;
	}
	public void setSessionKey(String sessionKey) {
		this.sessionKey = sessionKey;
	}
	public String getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public ForceConfig getForceConfig() {
		return forceConfig;
	}

	public void setForceConfig(ForceConfig forceConfig) {
		this.forceConfig = forceConfig;
	}

	public String getEntityId() {
		return entityId;
	}

	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}

	public String getIds_str() {
		return ids_str;
	}

	public void setIds_str(String ids_str) {
		this.ids_str = ids_str;
	}

	public String getMenu_str() {
		return menu_str;
	}

	public void setMenu_str(String menu_str) {
		this.menu_str = menu_str;
	}

	public String getMenu_prop_str() {
		return menu_prop_str;
	}

	public void setMenu_prop_str(String menu_prop_str) {
		this.menu_prop_str = menu_prop_str;
	}

	public String getConfig_map_json() {
		return config_map_json;
	}

	public void setConfig_map_json(String config_map_json) {
		this.config_map_json = config_map_json;
	}

	public String getSettings_json() {
		return settings_json;
	}

	public void setSettings_json(String settings_json) {
		this.settings_json = settings_json;
	}

	public String getTime_json() {
		return time_json;
	}

	public void setTime_json(String time_json) {
		this.time_json = time_json;
	}

	public String getDelivery_man_json() {
		return delivery_man_json;
	}

	public void setDelivery_man_json(String delivery_man_json) {
		this.delivery_man_json = delivery_man_json;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getItem_exts() {
		return item_exts;
	}

	public void setItem_exts(String item_exts) {
		this.item_exts = item_exts;
	}

	public String getDel_item_ids() {
		return del_item_ids;
	}

	public void setDel_item_ids(String del_item_ids) {
		this.del_item_ids = del_item_ids;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(String menu_id) {
		this.menu_id = menu_id;
	}

	public String getCoupon_promotion_str() {
		return coupon_promotion_str;
	}

	public void setCoupon_promotion_str(String coupon_promotion_str) {
		this.coupon_promotion_str = coupon_promotion_str;
	}

	public String getCoupon_promotion_id() {
		return coupon_promotion_id;
	}

	public void setCoupon_promotion_id(String coupon_promotion_id) {
		this.coupon_promotion_id = coupon_promotion_id;
	}

	public String getSales_promotion_str() {
		return sales_promotion_str;
	}

	public void setSales_promotion_str(String sales_promotion_str) {
		this.sales_promotion_str = sales_promotion_str;
	}

	public String getEntityType() {
		return entityType;
	}

	public void setEntityType(String entityType) {
		this.entityType = entityType;
	}

	public String getBirth_privilege_str() {
		return birth_privilege_str;
	}

	public void setBirth_privilege_str(String birth_privilege_str) {
		this.birth_privilege_str = birth_privilege_str;
	}

	public Integer getCustomer_level() {
		return customer_level;
	}

	public void setCustomer_level(Integer customer_level) {
		this.customer_level = customer_level;
	}

	public String getMemory_privilege_str() {
		return memory_privilege_str;
	}

	public void setMemory_privilege_str(String memory_privilege_str) {
		this.memory_privilege_str = memory_privilege_str;
	}

	public String getCoupon_privilege_str() {
		return coupon_privilege_str;
	}

	public void setCoupon_privilege_str(String coupon_privilege_str) {
		this.coupon_privilege_str = coupon_privilege_str;
	}

	public String getCustom_privilege_str() {
		return custom_privilege_str;
	}

	public void setCustom_privilege_str(String custom_privilege_str) {
		this.custom_privilege_str = custom_privilege_str;
	}

	public String getCustomer_right_id() {
		return customer_right_id;
	}

	public void setCustomer_right_id(String customer_right_id) {
		this.customer_right_id = customer_right_id;
	}

	public String getRight_interest_Id() {
		return right_interest_Id;
	}

	public void setRight_interest_Id(String right_interest_Id) {
		this.right_interest_Id = right_interest_Id;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getKind_card_str() {
		return kind_card_str;
	}


	public void setKind_card_str(String kind_card_str) {
		this.kind_card_str = kind_card_str;
	}


	public String getCard_privilege_str() {
		return card_privilege_str;
	}


	public void setCard_privilege_str(String card_privilege_str) {
		this.card_privilege_str = card_privilege_str;
	}


	public Integer getLast_ver() {
		return last_ver;
	}


	public void setLast_ver(Integer last_ver) {
		this.last_ver = last_ver;
	}


	public Integer getLast_version() {
		return last_version;
	}


	public void setLast_version(Integer last_version) {
		this.last_version = last_version;
	}


	public String getMoney_rule_str() {
		return money_rule_str;
	}


	public void setMoney_rule_str(String money_rule_str) {
		this.money_rule_str = money_rule_str;
	}


	public String getSelf_recharge_str_list() {
		return self_recharge_str_list;
	}


	public void setSelf_recharge_str_list(String self_recharge_str_list) {
		this.self_recharge_str_list = self_recharge_str_list;
	}


	public String getGift_str() {
		return gift_str;
	}


	public void setGift_str(String gift_str) {
		this.gift_str = gift_str;
	}


	public String getGift_convert_str() {
		return gift_convert_str;
	}


	public void setGift_convert_str(String gift_convert_str) {
		this.gift_convert_str = gift_convert_str;
	}


	public String getMenu_label_str() {
		return menu_label_str;
	}


	public void setMenu_label_str(String menu_label_str) {
		this.menu_label_str = menu_label_str;
	}


	public Integer getIs_turn_on() {
		return is_turn_on;
	}


	public void setIs_turn_on(Integer is_turn_on) {
		this.is_turn_on = is_turn_on;
	}


	public String getPlan_config_json() {
		return plan_config_json;
	}


	public void setPlan_config_json(String plan_config_json) {
		this.plan_config_json = plan_config_json;
	}


	public Integer getIs_template_on() {
		return is_template_on;
	}


	public void setIs_template_on(Integer is_template_on) {
		this.is_template_on = is_template_on;
	}


	public Integer getSort_code() {
		return sort_code;
	}


	public void setSort_code(Integer sort_code) {
		this.sort_code = sort_code;
	}


	public Integer getIs_hidden() {
		return is_hidden;
	}


	public void setIs_hidden(Integer is_hidden) {
		this.is_hidden = is_hidden;
	}


	public Integer getCpi() {
		return cpi;
	}


	public void setCpi(Integer cpi) {
		this.cpi = cpi;
	}


	public String getTemplate_name() {
		return template_name;
	}


	public void setTemplate_name(String template_name) {
		this.template_name = template_name;
	}


	public String getCover_path() {
		return cover_path;
	}


	public void setCover_path(String cover_path) {
		this.cover_path = cover_path;
	}


	public String getShop_template_id() {
		return shop_template_id;
	}


	public void setShop_template_id(String shop_template_id) {
		this.shop_template_id = shop_template_id;
	}






}
