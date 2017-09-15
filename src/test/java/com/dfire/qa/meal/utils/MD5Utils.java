package com.dfire.qa.meal.utils;


import java.util.Arrays;
import java.util.Map;

import com.dfire.qa.meal.logger.LoggerMarkers;
import com.dfire.qa.meal.logger.MealLoggerFactory;
import com.dfire.sdk.util.StringUtil;
import com.twodfire.util.MD5Util;


public class MD5Utils {
	
    private static final String SIGN_KEY = ",.xcvlasdiqpoikm,. xvz";

    public static String generatorKey(String source) {
        StringBuilder sb = new StringBuilder();
        sb.append(source).append(SIGN_KEY);
        return MD5Util.MD5(sb.toString());
    }
    
    
    // boss-api 加密函数
    public static String generateSignForBossAPI(String secret, Map<String, String> params){
    	
    	String sign = null;
    	
    	try{
    		
    		sign = server(secret, params);
    		
    	}catch(Exception e){

    		MealLoggerFactory.LOG_FILES_LOGGER.error(LoggerMarkers.LOGFILE, e.toString());
    	}
    	
    	return sign;
    }
    
    
    // boss-api 加密子函数
    public static String server(String secrect, Map<String, String> params) {
        String[] keys = (String[])params.keySet().toArray(new String[0]);
        Arrays.sort(keys);
        StringBuilder query = new StringBuilder();
        String[] arr$ = keys;
        int len$ = keys.length;

        for(int i$ = 0; i$ < len$; ++i$) {
            String key = arr$[i$];
            if(!key.equals("sign") && !key.equals("method") && !key.equals("appKey") && !key.equals("v") && !key.equals("format") && !key.equals("timestamp")) {
                String value = (String)params.get(key);
                if(!StringUtil.isEmpty(key) && !StringUtil.isEmpty(value)) {
                    query.append(key).append(value);
                }
            }
        }

        return MD5Util.MD5(query.toString() + secrect);
    }
    
    
    public static void main(String [] argu){
    	
    	String source = "00067412" + "104";
    	String result = generatorKey(source);
    	MealLoggerFactory.LOG_FILES_LOGGER.error(LoggerMarkers.LOGFILE, "the result is : " + result);

    }
    

}
