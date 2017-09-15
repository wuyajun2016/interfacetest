package com.dfire.wechat.util;

import com.dfire.sdk.util.MD5Util;
import com.dfire.sdk.util.StringUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class SignUtilForApp {
	private static Logger logger = LoggerFactory.getLogger(SignUtilForApp.class);
    private static Map<String, String> keySecretMap = new HashMap();
    private static final List<String> UN_PARTICIPATE_PARAMS;

    public SignUtilForApp() {
    }

    public static String getSign(Map<String, String[]> paramsMap, String token) {
        Map<String, String> params = parseParams(paramsMap);
        String appKey = (String)params.get("appKey");
        if (keySecretMap.containsKey(params.get("appKey"))) {
            params.put("secretKey", keySecretMap.get(appKey));
        }

        logger.info("request params: {}", params.toString());
        params.put("token", token);
        return generateSign(params);
    }

    private static Map<String, String> parseParams(Map<String, String[]> paramIn) {
        Map<String, String> paramOut = new HashMap();
        Iterator i$ = paramIn.entrySet().iterator();

        while(true) {
            while(i$.hasNext()) {
                Entry<String, String[]> entry = (Entry)i$.next();
                String key = (String)entry.getKey();
                String[] value = (String[])entry.getValue();
                if (null != value && value.length > 0) {
                    paramOut.put(key, value[0]);
                } else {
                    paramOut.put(key, "");
                }
            }

            return paramOut;
        }
    }

    public static Map<String, String> getKeyMap() {
        return keySecretMap;
    }

    private static String generateSign(Map<String, String> params) {
        String[] keys = (String[])params.keySet().toArray(new String[0]);
        Arrays.sort(keys);
        StringBuilder query = new StringBuilder();
        String[] arr$ = keys;
        int len$ = keys.length;
        boolean first = true;

        for(int i$ = 0; i$ < len$; ++i$) {
            String key = arr$[i$];
            if (!UN_PARTICIPATE_PARAMS.contains(key)) {
                String value = ((String)params.get(key)).toString();
                if (!StringUtil.isEmpty(key)) {
                    if (first) {
                        first = false;
                    } else {
                        query.append("&");
                    }

                    query.append(key).append("=").append(value);
                }
            }
        }

        logger.info("request query is:" + query.toString());
        return MD5Util.encode(query.toString());
    }

    public static void main(String[] args) {
        String[] appKey = new String[]{"10001"};
        String[] uid = new String[]{"15157481507"};
        String[] equipmentId = new String[]{"e10001"};
        String[] timestamp = new String[]{"1446801341607"};
        String[] sign = new String[]{"7F2EEB98A37C7F8CEF7AD715F6AE8562"};
        Map<String, String[]> paramsMap = new HashMap();
        paramsMap.put("appKey", appKey);
        paramsMap.put("uid", uid);
        paramsMap.put("equipmentId", equipmentId);
        paramsMap.put("timestamp", timestamp);
        paramsMap.put("sign", sign);
        System.out.println(getSign(paramsMap, "MzdmZTgzOGFhYTI3NWZhNjMyMDQ4MDJlOTI2MWU3YzI="));
    }

    static {
        keySecretMap.put("100010", "06fd3e1fa8a34f94ac68c0062f5ec3e0");
        keySecretMap.put("100011", "8a56de338a8049d98ed2007924996c00");
        UN_PARTICIPATE_PARAMS = new ArrayList<String>() {
            {
                this.add("sign");
            }
        };
    }
}
