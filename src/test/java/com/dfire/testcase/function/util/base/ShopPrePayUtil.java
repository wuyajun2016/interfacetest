package com.dfire.testcase.function.util.base;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.Assert;

import com.dfire.testcase.function.bean.BaseParamBean;
import com.dfire.wechat.util.HttpRequestEx;
import com.dfire.wechat.util.PathForPost;
import com.dfire.wechat.util.Response;

public class ShopPrePayUtil extends WechatBaseUtil{

	private static final Logger logger = Logger.getLogger(ShopPrePayUtil.class);
	
	public static Response preModifyPeopleAndMemo(HttpRequestEx httpRequest, BaseParamBean baseParamBean,Map<String, String> otherParameter) {

		Response response = null;
		try {
			logger.info("修改人数和备注");
			Map<String, String> query = new HashMap<String, String>();
			query.put("xtoken", baseParamBean.getXtoken());
			query.put("entity_id", baseParamBean.getEntityId());
			query.put("people", otherParameter.get("people"));
			query.put("memo", otherParameter.get("memo"));
			
			response = httpRequest.post(PathForPost.getPathForModifyPeopleAndMemo(), query);
			
			return response;
		} catch (Exception e) {
			logger.info(e.toString());
		}
		return response;
	}
	
	
	
////////////////////////////////     模块封装方法          //////////////////////////////////////////////////////////////////
	
	/**
	 * 扫店码, 需要验证店铺数据<br/>
	 * 该方法已被废弃，对应的方法可以参考：<br/>
	 * {@link com.dfire.testcase.function.util.base.ShopNoPrePayUtil#scanCode(HttpRequestEx, BaseParamBean, Map)} <br/>
	 * {@linkplain com.dfire.testcase.function.util.base.ShopNoPrePayUtil#scanCode(HttpRequestEx, BaseParamBean, Map)  非预付款扫店码}
	 *@see com.dfire.testcase.function.util.base.ShopNoPrePayUtil#scanCode(HttpRequestEx, BaseParamBean, Map)
	 */
	@Deprecated
	public static Response scanCode(HttpRequestEx httpRequest, String entityId, String seatCode, String signKey){
		Response response = null;
		try{
			
			// 构造参数
			BaseParamBean baseParamBean = new BaseParamBean();
			baseParamBean.setEntityId(entityId);
			baseParamBean.setSeatCode(seatCode);
			
			Map<String, String> otherParameter = new HashMap<String, String>();
			otherParameter.put("signKey", signKey);						
						
			// 二维码扫码入口
			Response responseTemp = oauthQRCodeForShop(httpRequest, baseParamBean, otherParameter);
			Assert.assertEquals(responseTemp.getStatus(), 200);
			
			// 获取初始化数据
			response = getInitDataForShop(httpRequest, baseParamBean);
			Assert.assertEquals(response.getStatus(), 200);
			
			// 店铺分享URL的信息（包括图片、文案）接口
			Response responseForShare = shareForShop(httpRequest, baseParamBean);
			Assert.assertEquals(responseForShare.getStatus(), 200);
			
		}catch(Exception e){
			logger.info(e.toString());
		}
		
		return response;
	}
	
}
