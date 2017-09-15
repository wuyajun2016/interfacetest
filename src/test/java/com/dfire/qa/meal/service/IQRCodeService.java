package com.dfire.qa.meal.service;

import java.util.List;
import java.util.Map;

import com.dfire.qa.meal.bean.BaseParamBean;
import com.dfire.qa.meal.bean.Response;
import com.dfire.qa.meal.enums.Environment;



public interface IQRCodeService {
	
	
	/**
	 * 通用码扫码(包含桌码, 店码, 外卖码等)
	 */
	public Response scanQRCode(Map<String, String> header, List<String> URIPath, Environment env) throws Exception;
	
	
	/**
	 * 桌位码扫码
	 * 扫桌码：/s/{entityId}/{seatCode}/{signKey}  
	 */
	public Response oauthQRCodeForSeat(BaseParamBean baseParamBean, Map<String, String> header, Environment env) throws Exception;
	
	
	
	/**
	 * 店码扫码
	 * 扫店码：/s/{entityId}/{signKey}  
	 */
	public Response oauthQRCodeForShop(BaseParamBean baseParamBean, Environment env) throws Exception;
	
	
	/**
	 * 店铺初始化
	 * 对应 URL: GET /shop/v1/get_state
	 */
	public Response getInitDataForShop(BaseParamBean baseParamBean, Environment env) throws Exception;
	
	
	/**
	 * 首页会员等级信息详情
	 * 对应 URL: GET /shop_member/v1/get_person_info
	 */
	public Response getPersonInfo(BaseParamBean baseParamBean, Environment env) throws Exception;
	
	
	
	/**
	 * 查找用户个人信息
	 * 对应 URL: GET /users/v1/get_user_info
	 */
	public Response getUserInfo(BaseParamBean baseParamBean, Environment env) throws Exception;
	
	
	
	/**
	 * 店铺分享URL的信息（包括图片、文案）接口
	 * 对应 URL：GET /share/v1/info
	 */
	public Response shareForShop(BaseParamBean baseParamBean,  Environment env) throws Exception;
	
	
	
	/**
	 * 扫桌码, 需要验证店铺数据<br/>
	 * 需要参数：entityId, seatCode, signKey<br/>
	 */
	public Map<String, Response> scanCode(BaseParamBean baseParamBean, Environment env) throws Exception;

	
	
	/**
	 * 扫店码<br/>
	 * 需要参数：entityId, seatCode, signKey<br/>
	 */
	public Map<String, Response> scanShopCode(BaseParamBean baseParamBean, Environment env) throws Exception;

}
