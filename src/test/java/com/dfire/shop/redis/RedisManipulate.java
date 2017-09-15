package com.dfire.shop.redis;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.alibaba.com.caucho.hessian.io.HessianInput;

import redis.clients.jedis.Jedis;

public class RedisManipulate {
	private static final Logger logger = Logger.getLogger(RedisManipulate.class);
	private static Jedis jedis=  null;
	
	public static void connectToRedis(){
		jedis = RedisUtils.createJedis();
	}
	
	public static void cleanCache(String key){
		RedisUtils.cleanCache(jedis, key);
		logger.info("clean key=" + key + " successus");
	}
	
	public static void setCache(String key, String value){
		RedisUtils.setCache(jedis, key, value);
		logger.info("set key=" + key + ", value=" + value);
	}
	
	public static void getCache(String key){
		String value = RedisUtils.getCache(jedis, key);
	}
	
	public static void deleteCache(String key){
		Long result = RedisUtils.deleteCache(jedis, key);
	}
	
	
	// hessian 反序列化
	public Object deserialize(byte[] by) throws IOException{  
	    if(by==null) throw new NullPointerException();  
	      
	    ByteArrayInputStream is = new ByteArrayInputStream(by);  
	    HessianInput hi = new HessianInput(is);  
	    return hi.readObject();  
	}  

}
