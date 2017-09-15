package com.dfire.wechat.util;

import java.util.Locale;

import org.apache.log4j.Logger;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class ClientSignatrueUtil {
	
	private static final Logger logger = Logger.getLogger(ClientSignatrueUtil.class);
	// RFC1123 日期格式
    private static final DateTimeFormatter RFCDateFormate = DateTimeFormat.forPattern("EEE, dd MMM yyyy HH:mm:ss ZZZ").withLocale(Locale.US);
    
    public static String getRFCDateFromLong(long date) {
		try {
			return RFCDateFormate.print(date);
		} catch (Exception e) {
			return null;
		}

	}
}
