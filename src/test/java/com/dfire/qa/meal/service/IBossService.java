package com.dfire.qa.meal.service;


import java.util.Map;

import com.dfire.qa.meal.bean.BaseParamBean;
import com.dfire.qa.meal.bean.Response;
import com.dfire.qa.meal.enums.Environment;
import com.dfire.qa.meal.enums.FileType;
import com.dfire.qa.meal.enums.Privilege;
import com.dfire.qa.meal.enums.ScanCodeProcedure;
import com.dfire.qa.meal.vo.boss.CustomPrivilege;
import com.dfire.qa.meal.vo.boss.ForceConfig;
import com.dfire.qa.meal.vo.cart.CartIncludeSuitForm;
import com.google.gson.JsonObject;




public interface IBossService {
	
	
	 /**
     * 保存必选商品设置, 此处默认为 普通菜 <br/>
	 * 设置的 forceType (强制类型（0:指定数量,1:与用餐人数相同）); <br/>
	 * 设置的 forceNum ( 强制点菜数量（当强制类型为"与用餐人数相同"时无视此数量）)
     */
	public Response saveForceMenu(ForceConfig forceConfig, Environment env) throws Exception;
	
	
	 /**
     * 保存必选商品设置, 此处默认为 普通菜 <br/>
	 * 设置的 forceType (强制类型（0:指定数量,1:与用餐人数相同）); <br/>
	 * 设置的 forceNum ( 强制点菜数量（当强制类型为"与用餐人数相同"时无视此数量）)
     */
	public Response saveForceMenu(String entityId,  ForceConfig forceConfig, Environment env) throws Exception;
	
	
	
	/**
	 * 删除必选商品设置 <br/>
	 * 删除必选商品设置：/menu/v1/remove_force_menu  
	 */
	public Response removeForceMenu(String entityId, String configId, Environment env) throws Exception;
	
	
	
	/**
	 * 获取必选商品列表 <br/>
	 * 获取必选商品列表：/menu/v1/query_force_menu_list  
	 */
	public Response queryForceMenuList(String entityId, Environment env) throws Exception;
		
	
	
	
	/**
	 * 操作预付款配置开关 <br/>
	 * scanCodeProcedure 表示扫码类型,比如 非预付款扫桌码, 非预付款扫店码等 <br/>
	 * configId 表示具体要操作的配置项 id, 该配置项可以从与 entityId 相关的信息中进行获取; switchConfig 为 true 表示打开该配置, 为 false 表示关闭该配置项 <br/>
	 */
	public Response prePaySwitch(String entityId, ScanCodeProcedure scanCodeProcedure, boolean switchConfig, Environment env) throws Exception;
	
	
	/**
	 * 设置外卖配送人员<br/>
	 */
	public Response takeOutDeliveryMan(String entityId, String takeoutDeliveryManJson, Environment env) throws Exception;
	
	
	
	/**
	 * 顾客端---外卖设置---获取外卖人员信息<br/>
	 */
	public Response getTakeOutDeliveryMan(String entityId, Environment env) throws Exception;
	
	
	
	/**
	 * 顾客端---外卖设置---删除外卖人员信息<br/>
	 */
	public Response deleteTakeOutDeliveryMan(String entityId, String id, Environment env) throws Exception;
	
	
	/**
	 *  顾客端---外卖设置---删除所有外卖人员信息<br/>
	 */
	public Boolean deleteAllTakeOutDeliveryMan(String entityId, Environment env) throws Exception;
	
	

	/**
	 * 设置外卖配送时间<br/>
	 */
	public Response takeOutTimeConfig(String entityId, String takeoutTimeJson, Environment env) throws Exception;
	
	
	
	/**
	 * 顾客端--外卖设置---获取外卖配送时间<br/>
	 */
	public Response getTakeOutTimeConfig(String entityId, Environment env) throws Exception;
	
	
	
	/**
	 * 顾客端--外卖设置---删除外卖配送时间<br/>
	 */
	public Response deleteTakeOutTimeConfig(String entityId, String id, Environment env) throws Exception;
	
	
	/**
	 * 顾客端--外卖设置---删除所有外卖配送时间<br/>
	 */
	public Boolean deleteAllTakeOutTimeConfig(String entityId, Environment env) throws Exception;
	
	
	
	/**
	 * 外卖设置开关<br/>
	 */
	public Response takeOutSetConfig(String entityId, String takeoutSetJson, Environment env) throws Exception;
	
	
	/**
	 * 顾客端---必选商品---获取所有菜单列表(以必选菜的形式)<br/>
	 */
	public Response getAllForceMenuList(String entityId, Environment env) throws Exception;
	
	
	
	/**
	 * 顾客端---必选商品---获取必选菜列表<br/>
	 */
	public Response getForceMenuList(String entityId, Environment env) throws Exception;
	
	
	
	/**
	 * 顾客端---必选商品---删除必选菜<br/>
	 */
	public Response deleteForceMenu(String entityId, String configId, Environment env) throws Exception;
	
	
	
	/**
	 * 顾客端---必选商品---删除所有必选菜<br/>
	 */
	public Boolean deleteAllForceMenu(String entityId, Environment env) throws Exception;
	
	
	
	/**
	 * 批量设置开关<br/>
	 */
	public Response batchSetConfig(String entityId, Map<String, String> configMap, Environment env) throws Exception;
	
	
	
	/**
	 * 客户端设置---获取系统配置<br/>
	 */
	public Response listConfigInfraSwitch(String entityId, Environment env) throws Exception;
	
	
	
	/**
	 * 客户端设置---基础设置<br/>     
	 */
	public Response infraSwitchWithClient(String entityId, String config, boolean switchConfig, String description, Environment env) throws Exception;

	
	
	/**
	 * 客户端设置--文件上传<br/>
	 * fileName 包含文件后缀<br/>
	 */
	public Response uploadFile(String entityId, FileType fileType, String fileName, Environment env) throws Exception;
	
	
	
	/**
	 * 上传图片后点击保存进行图片设置<br/>
	 */
	public Response imageSetConfig(String entityId, String addImageSetJson, String deleteImageJson, Environment env) throws Exception;
	
	
	/**
	 * 顾客端---页首与页尾---获取已上传图片信息<br/>
	 */
	public Response queryImageSet(String entityId, Environment env) throws Exception;
	
	
	/**
	 * 个性化换肤, 包括色调风格设置, 自定义背景, 自定义图标<br/>
	 */
	public Response colorStyleConfig(String entityId, String styleJson, Environment env) throws Exception;
	
	
	
	/**
	 * 客户端设置---顾客点餐重复提醒<br/>
	 */
	public Response menuRepeatConfig(String entityId, Integer status, Environment env) throws Exception;
	
	
	/**
	 *  顾客端设置---顾客点餐重复提醒--添加菜品<br/>
	 */
	public Response menuRepeatAddMenuConfig(String entityId, String menuListJson, Environment env) throws Exception;
	
	
	
	/**
	 *  顾客端设置---顾客点餐重复提醒--删除菜品<br/>
	 */
	public Response menuRepeatDeleteMenuConfig(String entityId, String menuId, Environment env) throws Exception;
	
	
	
	/**
	 * 会员--保存优惠券<br/>
	 */
	public Response saveCouponConfig(String entityId, String couponJson, Environment env) throws Exception;

	
	
	/**
	 * 优惠券列表<br/>
	 */
	public Response listCoupon(String entityId, Environment env) throws Exception;
	
	
	/**
	 * 获取优惠券<br/>
	 */
	public JsonObject getCoupon(String entityId, String couponId, Environment env) throws Exception;
	
	
	
	/**
	 * 会员--删除优惠券<br/>
	 */	
	public Response deleteCouponConfig(String entityId, String couponId, Environment env) throws Exception;
	
	
	
	/**
	 * 会员--保存促销<br/>
	 * 该接口的 HTTP 请求参数中需要添加  entityType 参数, "0" 表示单店, 具体详见 com.dfire.qa.meal.vo.boss.EntityType <br/>
	 */	
	public Response savePromotion(String entityId, String promotionJson, Environment env) throws Exception;
	
	
	/**
	 * 获取促销活动列表(连锁+门店)<br/>
	 */
	public Response getPromotionList(String entityId, Environment env) throws Exception;
	
	
	/**
	 * 获取特定促销<br/>
	 */
	public JsonObject getPromotion(String entityId, String promotionId, Environment env) throws Exception;
	
	
	/**
	 * 会员--删除促销<br/>
	 */	
	public Response deletePromotion(String entityId, String promotionId, Environment env) throws Exception;

	
	
	/**
	 * 增加会员等级特权<br/>
	 */
	public Response savePrivilege(String entityId, Privilege privilege, String privilegeJson, Environment env) throws Exception;
	
	
	
	/**
	 * 删除会员一般特权等级<br/>
	 */
	public Response deletePrivilege(String entityId, String privilegeId, Environment env) throws Exception;
	
	
	/**
	 * 删除会员特权<br/>
	 */
	public Response deleteCardPrivilege(String entityId, String cardPrivilegeId, Environment env) throws Exception;
	
	
	/**
	 * 删除会员自定义特权等级<br/>
	 */
	public Response deleteCustomPrivilege(String entityId, String privilegeId, String interestId, Environment env) throws Exception;
	

	/**
	 * 添加会员自定义特权等级<br/>
	 */
	public Response addCustomPrivilege(String entityId, String customPrivilegeJson, Environment env) throws Exception;
	
	
	
	/**
	 * 在自定义特权列表中删除自定义特权等级<br/>
	 */
	public Response deleteAllCustomPrivilege(String entityId, String customPrivilegeJson, Environment env) throws Exception;
		
	
	/**
	 * 获取用户所有会员等级特权详情<br/>
	 */
	public Response getAllPrivilegeDetail(String entityId, Environment env) throws Exception;

	
	
	/**
	 * 删除用户所有会员等级特权<br/>
	 */
	public Boolean deleteAllPrivilege(String entityId, CustomPrivilege customPrivilege, Environment env) throws Exception;
	
	
	
	/**
	 * 获取用户特权卡详情<br/>
	 */
	public Response getAllCardPrivilegeDetail(String entityId, Environment env) throws Exception;
	
	
	
	/**
	 * 在特权卡管理上添加特权卡<br/>
	 */
	public Response addCardPrivilege(String entityId, String cardPrivilegeJson, Environment env) throws Exception;
	
	
	/**
	 * 获取自定义列表中所有的权限<br/>
	 */
	public Response getCustomPrivilegeList(String entityId, Environment env) throws Exception;
	
	
	
	/**
	 * 会员---卡充值优惠--获取优惠卡<br/>
	 */
	public Response queryKindCards(String entityId, Environment env) throws Exception;
	
	
	
	/**
	 * 会员---卡充值优惠--增加卡充值优惠<br/>允许顾客端使用火小二自助充值
	 */
	public Response saveRechargeRule(String entityId, String rechargeJson, Environment env) throws Exception;
	
	
	
	/**
	 *会员---卡充值优惠--删除所有卡充值优惠<br/>
	 */
	public Response deleteAllRechargeRule(String entityId, Environment env) throws Exception;
	
	
	
	/**
	 * 会员---卡充值优惠--允许顾客端使用火小二自助充值<br/>
	 */
	public Response selfRecharge(String entityId, String selfRechargeJson, Environment env) throws Exception;
	
	
	/**
	 * 会员---积分兑换设置---添加卡金额/优惠券<br/>
	 */
	public Response saveGift(String entityId, String giftJson, Environment env) throws Exception;
	
	
	
	/**
	 * 会员---积分兑换设置---获取设置信息<br/>
	 */
	public Response getGiftList(String entityId, Environment env) throws Exception;
	
	
	
	/**
	 * 会员---积分兑换设置---删除所有设置信息<br/>
	 */
	public Response deleteAllGift(String entityId, Environment env) throws Exception;
	
	
	
	/**
	 * 会员---积分抵现设置---保存设置信息<br/>
	 */
	public Response saveGiftConvert(String entityId, String giftConvertJson, Environment env) throws Exception;
	
	
	/**
	 * 会员---积分抵现设置---获取设置信息<br/>
	 */
	public Response getGiftConvertList(String entityId, Environment env) throws Exception;
	
	
	
	/**
	 * 智能点餐---商品标签设置<br/>
	 */
	public Response updateMenuLabel(String entityId, String menuLabelJson, String menuId, Environment env) throws Exception;
	
	
	
	/**
	 * 智能点餐---顾客点餐智能提醒与推荐<br/>
	 */
	public Response saveEntityConfig(String entityId, Integer trunOn, Environment env) throws Exception;
	
	
	
	/**
	 * 智能点餐---获取所有配置<br/>
	 */
	public Response queryPlanConfig(String entityId, Environment env) throws Exception;
	
	
	/**
	 * 智能点餐---顾客点餐智能提醒与推荐---保存配置<br/>
	 */
	public Response savePlanConfig(String entityId, String planConfigJson, Environment env) throws Exception;
	
	
	
	/**
	 * 智能点餐---保存智能点餐模版设置<br/>
	 */
	public Response saveTemplateConfig(String entityId, Integer flag, Environment env) throws Exception;
	
	
	
	/**
	 * 智能点餐---查询智能点餐模板<br/>
	 */
	public Response queryMenuTemplate(String entityId, Environment env) throws Exception;
	
	
	
	/**
	 * 智能点餐---修改智能点餐模板<br/>
	 */
	public Response modifyTemplateConfig(String entityId, String templateName, String coverPath, String shopTemplateId, Environment env) throws Exception;
	
	
	
	/**
	 * 商品上下架<br/>
	 * onSelf 为 true 表示上架, 为 false 表示下架<br/>
	 */
	public Response menuConfigUpdate(BaseParamBean baseParamBean, Object menuObject, Boolean onSelf, Environment env) throws Exception;
	
	
	
}
