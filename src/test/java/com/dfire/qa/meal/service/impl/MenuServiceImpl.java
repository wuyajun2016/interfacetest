package com.dfire.qa.meal.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dfire.qa.meal.bean.BaseParamBean;
import com.dfire.qa.meal.bean.Response;
import com.dfire.qa.meal.constant.Constants;
import com.dfire.qa.meal.enums.Environment;
import com.dfire.qa.meal.httpclient.HTTPClientService;
import com.dfire.qa.meal.logger.LoggerMarkers;
import com.dfire.qa.meal.logger.MealLoggerFactory;
import com.dfire.qa.meal.props.HostProperties;
import com.dfire.qa.meal.service.IMenuService;
import com.dfire.qa.meal.utils.HTTPRequestUtil;
import com.dfire.qa.meal.utils.PathForHTTP;
import com.google.gson.Gson;

@Service
public class MenuServiceImpl implements IMenuService{
	
	@Resource
	private HostProperties hostProperties;
	
	@Resource
	private HTTPClientService httpClientService;
	
	private Gson gson = new Gson();
	
	
	@Override
	public Response getMenusByShopCode(BaseParamBean baseParamBean, Map<String, String> otherParameter, Environment env) throws Exception {
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Menu.MENUSSHOPCODE);	
		
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
		
		Response response = null;

		Map<String, String> query = new HashMap<String, String>();
		
		query.put("xtoken", baseParamBean.getXtoken());
		query.put("entity_id", baseParamBean.getEntityId());
		
		query.put("recommend", otherParameter.get("recommend"));
		query.put("repeat", otherParameter.get("repeat"));

		// 计算 URL 并发送 GET 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("serverURL"), PathForHTTP.getPathForGetMenus(), query, protocol);
		response = httpClientService.doGet(url, null);
	
		return response;
		
	}

}
