package com.dfire.qa.meal.bean;

import java.util.HashMap;


public class ResultMap<T> extends HashMap{
	
	public static final String CODE = "code";               //接口调用是否成功，0：失败，1：成功
    public static final String ERROR_CODE = "errorCode";    //业务编码 接口调用错误业务编码
    public static final String DATA = "data";      		    //数据对象 成功需要返回消息，在data里加一个属性（比如：“showMsg”）
    public static final String RECORD = "record";   		//记录条数，当查询列表时接口返回的记录条数
    public static final String MESSAGE = "message"; 		//错误消息内容

    public static final int ERROR = 0;  //异常
    public static final int SUCCESS = 1;//成功


    public ResultMap(T t) {
    	
        put(CODE, SUCCESS);  //初始化默认 为 成功
        put(DATA, t);
       
    }
    
    
    public ResultMap(String errorCode, String message) {
        put(CODE, ERROR);
        put(ERROR_CODE, errorCode);
        put(MESSAGE, message);

    }


}
