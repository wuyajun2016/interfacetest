package com.dfire.testcase.function.service;

import org.apache.log4j.Logger;

import com.dfire.testcase.function.bean.CashInitBean;
import com.dfire.wechat.util.BeanProvider;
import com.dfire.wechat.util.PropertiesUtil;
import com.google.gson.Gson;

public class CashBeanProvider {
	
	private static final Logger logger = Logger.getLogger(BeanProvider.class);
	private Gson gson = new Gson();
	private PropertiesUtil propertyUtil = PropertiesUtil.getPropertiesUtil();
	
	/**
	 * 获取 CashInitBean 用于登陆收银机
	 * @return
	 */
	public  CashInitBean getCashInitBean(){
		
		CashInitBean cashInitBean = new CashInitBean();
		
		try{
			
			cashInitBean.setS_osv(propertyUtil.getValue("wjs_osv"));
			cashInitBean.setS_br(propertyUtil.getValue("wjs_br"));
			cashInitBean.setS_apv(propertyUtil.getValue("wjs_apv"));
			
			cashInitBean.setS_eid(propertyUtil.getValue("wjs_eid"));
			cashInitBean.setS_did(propertyUtil.getValue("wjs_did"));
			cashInitBean.setS_os(propertyUtil.getValue("wjs_os"));
			
			cashInitBean.setS_net(propertyUtil.getValue("wjs_net"));
			cashInitBean.setApp_key(propertyUtil.getValue("wjapp_key"));
			cashInitBean.setFormat(propertyUtil.getValue("wjformat"));
			
			cashInitBean.setS_sc(propertyUtil.getValue("wjs_sc"));
			cashInitBean.setAppKey(propertyUtil.getValue("wjappKey"));
			
		}catch(Exception e){
			
			logger.info(e.toString());
			return null;
			
		}
		
		return cashInitBean;
		
	}

}
