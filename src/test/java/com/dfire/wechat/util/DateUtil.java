package com.dfire.wechat.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	/* 
     * 将时间转换为时间戳
     */    
    public static String dateToStamp(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
		try {
			date = simpleDateFormat.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }
    
    
    /* 
     * 将时间戳转换为时间
     */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }
    
    
    public static void main(String arg[]) {
    	String stamp = DateUtil.dateToStamp("2016-11-11 00:00:00");
    	System.out.println(stamp);
		String date = DateUtil.stampToDate("1478793600");
		System.out.println(date);
	}
}
