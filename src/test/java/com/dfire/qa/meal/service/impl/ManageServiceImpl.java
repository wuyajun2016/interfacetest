package com.dfire.qa.meal.service.impl;

import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Service;

import com.dfire.qa.meal.logger.LoggerMarkers;
import com.dfire.qa.meal.logger.MealLoggerFactory;
import com.dfire.qa.meal.service.IManageService;

@Service
public class ManageServiceImpl implements IManageService, ApplicationContextAware, ApplicationListener<ContextRefreshedEvent> {

	private static ApplicationContext applicationContext;
	
	
	/**
	 * Spring 会将 applicationContext 传递到这里<br/>
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ManageServiceImpl.applicationContext = applicationContext;
		
	}

	
	
	@Override
	public Boolean getAllMethods() throws Exception {
		
		Map<String, Object> beansWithAnnotationMap = ManageServiceImpl.applicationContext.getBeansWithAnnotation(org.springframework.stereotype.Controller.class);
		Class<? extends Object> clazz = null;  
		
	    for(Map.Entry<String, Object> entry : beansWithAnnotationMap.entrySet()){  
		   
	    	clazz = entry.getValue().getClass();//获取到实例对象的class信息  
	    	String className = clazz.getName();
	    	MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, "class name is : " + className);
	    	
		    Class<? extends Object>  [] interfaces = clazz.getInterfaces(); 
		    
		    for(Class<? extends Object>  element : interfaces){  
		        
		    	String interfaceName = element.getName(); //接口的完整名
		    	MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, "the interface name is : " + interfaceName );
		    	
		    	Object [] signStrings = element.getSigners();
		    	for(Object signObject : signStrings){
		    		String signString = signObject.toString();
		    		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, "the sign is : " + signString);
		    	}
		    }  
	    }  
	    
		return true;
	}



	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		// 根容器为Spring容器  
        if(event.getApplicationContext().getParent()==null){  
        	
            Map<String,Object> beans = event.getApplicationContext().getBeansWithAnnotation(org.springframework.stereotype.Controller.class);  
            for(Object bean:beans.values()){  
            	
                System.err.println(bean==null?"null":bean.getClass().getName());  
            }  
            
            System.err.println("=====ContextRefreshedEvent=====" + event.getSource().getClass().getName());  
        }  
  
		
	}
	
	

}
