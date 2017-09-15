package com.dfire.qa.meal.logger;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

/**
 * Created by ljw on 17/2/13.
 */
public class LoggerMarkers {

	
    //一般日志
    public static final Marker LOGFILE = MarkerFactory.getMarker("logFile");
    
    //收银日志
    public static final Marker CASHDESK = MarkerFactory.getMarker("CASHDESK");
    
    //整体流程
    public static final Marker INTEGRATION = MarkerFactory.getMarker("integration");
    

}
