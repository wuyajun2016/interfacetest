package com.dfire.wechat.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.dfire.sdk.util.StringUtil;
import com.dfire.testcase.function.boss.BaseBossBean;
import com.dfire.testcase.function.boss.BossControllerTest;
import com.twodfire.util.MD5Util;


public class MD5Utils {
	
	private static final Logger logger = Logger.getLogger(MD5Utils.class);
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
    		logger.error(e.toString());
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
    	logger.info("the result is : " + result);
    }
    
//    // Bean --> Map 1: 利用 Introspector 和   PropertyDescriptor 将Bean --> Map  
//    public static Map<String, String> transBossBean2Map(BaseBossBean obj) {  
//  
//        if(obj == null){  
//            return null;  
//        }          
//        
//        Map<String, String> map = new HashMap<String, String>();  
//        try {  
//            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());  
//            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
//            for (PropertyDescriptor property : propertyDescriptors) {  
//                String key = property.getName();  
//  
//                // 过滤class属性  
//                if (!key.equals("class")) {  
//                    // 得到property对应的getter方法  
//                    Method getter = property.getReadMethod();  
//                    Object value = getter.invoke(obj);  
//  
//                    map.put(key, value.toString());  
//                }  
//  
//            }  
//        } catch (Exception e) {  
//            logger.info("transform BaseBossBean to Map<String, String> object failed");
//            logger.info(e.toString());
//        }  
//  
//        return map;  
//  
//    }  

}
