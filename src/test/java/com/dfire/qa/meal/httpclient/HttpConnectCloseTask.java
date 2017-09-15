/**
 * Copyright (C) 2009-2016 Hangzhou 2Dfire Technology Co., Ltd. All rights reserved
 */
package com.dfire.qa.meal.httpclient;

import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;




import com.dfire.qa.meal.logger.LoggerMarkers;
import com.dfire.qa.meal.logger.MealLoggerFactory;

import javax.annotation.Resource;

import java.util.concurrent.TimeUnit;

/**
 * Http 链接关闭任务
 *
 * @author <a href="mailto:sijidou@2dfire.com">xiongba</a>
 * @since 2016-04-02
 */
@Component
public class HttpConnectCloseTask {
   
    @Resource
    private PoolingHttpClientConnectionManager pollingConnectionManager;

    /**
     * 10秒执行一次清理失效链接
     */
    @Scheduled(fixedDelay = 10000)
    public void close() {
    	
        if (pollingConnectionManager != null) {
        	
            pollingConnectionManager.closeExpiredConnections();
            pollingConnectionManager.closeIdleConnections(10, TimeUnit.SECONDS);  
            
            MealLoggerFactory.LOG_FILES_LOGGER.debug(LoggerMarkers.LOGFILE,"当前HttpClient的链接:" + pollingConnectionManager.getTotalStats() + ",=" + pollingConnectionManager.getRoutes());
        
        } else {
        	MealLoggerFactory.LOG_FILES_LOGGER.error(LoggerMarkers.LOGFILE,"pollingConnectionManager 为空");
        }
    }
}
