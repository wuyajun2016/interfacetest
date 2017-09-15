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
import com.dfire.qa.meal.service.IPrivilegeService;
import com.dfire.qa.meal.utils.HTTPRequestUtil;
import com.dfire.qa.meal.utils.PathForHTTP;


@Service
public class PrivilegeServiceImpl implements IPrivilegeService{

	@Resource
	HostProperties hostProperties;
	
	@Resource
	HTTPClientService httpClientService;
	
	
	
	@Override
	public Response getPrivilegeList(BaseParamBean baseParamBean, Integer useRange, Environment env) throws Exception {
	
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.GETALLPRIVILEGE);
		
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
		
		Response response = null;
		
		Map<String, String> query = new HashMap<String, String>();
		query.put("xtoken", baseParamBean.getXtoken());
		query.put("entity_id", baseParamBean.getEntityId());
		query.put("use_range", Integer.toString(useRange));
		
		
		// 计算 URL 并发送 GET 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("serverURL"), PathForHTTP.getPathForGetPrivilegeList(), query, protocol);
		response = httpClientService.doGet(url, null);
					
		
		return response;
	}

}
