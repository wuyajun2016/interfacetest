package com.dfire.qa.meal.logger;


import org.slf4j.Logger;

/**
 * Created by ljw on 17/2/13.
 */
public class MealLoggerFactory {

	
    //日常逻辑日志, 模块方法日志
    public final static Logger LOG_FILES_LOGGER = org.slf4j.LoggerFactory.getLogger("LOGFILE");
    
    
    // 收银日志
    public final static Logger CASH_DESK_LOGGER = org.slf4j.LoggerFactory.getLogger("CASHDESK");
    
    
    
    //整个流程日志
    public final static Logger INTEGRATION = org.slf4j.LoggerFactory.getLogger("INTEGRATION");


}
