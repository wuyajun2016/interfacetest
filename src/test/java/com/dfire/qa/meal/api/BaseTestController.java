package com.dfire.qa.meal.api;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.dfire.qa.meal.bean.BaseParamBean;
import com.dfire.qa.meal.enums.Environment;
import com.dfire.qa.meal.logger.LoggerMarkers;
import com.dfire.qa.meal.logger.MealLoggerFactory;
import com.dfire.qa.meal.service.ICommonService;
import com.dfire.qa.meal.utils.BeanProvider;
import com.dfire.qa.meal.utils.CommonConstants;



@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class BaseTestController extends  AbstractTestNGSpringContextTests{
	
	@Resource
	protected ICommonService commonService;
	
	protected static Environment environment = Environment.daily.getEnvironment(CommonConstants.currentEnv);
	
	protected BaseParamBean baseParamBean = null;
	
	protected Map<String, String> otherParameter = BeanProvider.getOtherParameter("6", "", "false");
	
//	@ExceptionHandler({Exception.class})
//	public JsonResult handleException(HttpServletRequest request, Exception exception){
//		
//		// 将异常打印到日志文件
//		MealLoggerFactory.LOG_FILES_LOGGER.error(LoggerMarkers.LOGFILE, "exception happen, the request is from : " + request.getRemoteHost());
//		MealLoggerFactory.LOG_FILES_LOGGER.error(LoggerMarkers.LOGFILE, CommonUtil.toString(exception));
//		
//		
//		// 构造返回参数		
//		return JsonResult.newInstance(JsonResult.FAIL_CODE, Constants.Exception.BASEEXCEPTION, 
//				new CaseResult(JsonResult.FAIL_CODE, null, exception.getMessage()));
//		
//	}

}
