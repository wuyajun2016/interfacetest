package com.dfire.qa.meal.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;









import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;

import com.dfire.qa.meal.bean.Response;
import com.dfire.qa.meal.constant.Constants;
import com.dfire.qa.meal.enums.Environment;
import com.dfire.qa.meal.enums.Module;
import com.dfire.qa.meal.logger.LoggerMarkers;
import com.dfire.qa.meal.logger.MealLoggerFactory;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class HTTPRequestUtil {
	
	private static Gson gson = new Gson();
	
	/**
	 * get complete URL
	 */
	public static String getCompleteURL(String host, List<String> path, Map<String, String> query, String protocol){
				
		StringBuilder url = new StringBuilder( protocol + "://" + host);
		
		if(path == null || path.isEmpty() == true){
			MealLoggerFactory.LOG_FILES_LOGGER.error(LoggerMarkers.LOGFILE, "the path is invalid");
			return null;
		}
		
		for(String element:path)
			url.append("/" + element);
		
		if(query == null || query.isEmpty() == true){
			MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, "the URL is : " + url.toString());
			return url.toString();
		}
		
		url.append("?");
		for(Map.Entry<String, String> entry:query.entrySet()){
			url.append(entry.getKey() + "=" + entry.getValue() + "&");
		}
		
		url.deleteCharAt(url.length() - 1);
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, "the URL is : " + url.toString());
		
		return url.toString();
		
	}
	
	
	/**
	 * 处理 HTTP 请求
	 */
	public static Response processResponse(HttpResponse httpResponse) throws Exception{
		
		Response response = null;
		ByteArrayOutputStream arrayStream = new ByteArrayOutputStream();
		InputStream is = new InputStream() {
			
			@Override
			public int read() throws IOException {
				// TODO Auto-generated method stub
				return 0;
			}
		};
		
		try{
			
			int statusCode = httpResponse.getStatusLine().getStatusCode();			
			HttpEntity entity = httpResponse.getEntity();
			
			byte[] buffer = new byte[1024];
			is = entity.getContent();
			
			int len;
			while ((len = is.read(buffer)) > 0) {
				arrayStream.write(buffer, 0, len);
			}
			
			String responseStr = new String(arrayStream.toByteArray(), "UTF-8");
			
			EntityUtils.consume(entity);
			
			MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, "statuLine is : " +
			httpResponse.getStatusLine() + ", response body is : " + responseStr );
			
			response = new Response(statusCode, responseStr);
			
		}catch(Exception e){
			
			e.printStackTrace();
			
		}finally{
			
			try{
				is.close();
	//			arrayStream.reset();
				arrayStream.close();
			}
			catch(Exception e){
				
				MealLoggerFactory.LOG_FILES_LOGGER.error(LoggerMarkers.LOGFILE, "fail to close input stream or output stream");				
			}
		}
		
		return response;
	}	
	
	
	/**
	 * 校验 HTTP 返回的 response <br/>
	 * verifyKey 是校验的模块
	 */
	public static Boolean verifyResponse(Map<String, Response> response, String verifyKey) throws Exception{

		// 确认 response 非空
		if(null == response || response.isEmpty())
			return true;
		
		
		// 确认响应状态及具体内容
		for(Map.Entry<String, Response> entry : response.entrySet()){
						
			if(entry.getValue().getStatus() != 200){
				return false;
			}
			
			if( null != verifyKey && !verifyKey.isEmpty()  && entry.getKey().equalsIgnoreCase(verifyKey)){
				
				JsonObject resp = new JsonParser().parse(entry.getValue().getResponseStr()).getAsJsonObject();
				
				// 断言校验不通过会直接抛出异常, 并且无法被 try catch 语句捕获, 应该采取其他比较方式,并通过抛出异常的方法解决
				if(1 != resp.get("code").getAsInt())
					throw new Exception(Constants.Error.EXPECTED + "1, " + Constants.Error.ACTUAL + 
							resp.get("code").getAsInt() + ", " + Module.valueOf(verifyKey).getDescription() + " " + Constants.Error.VERIFIEDERROR);
				
			}
		}
		
		return true;
		
	}
	
	
	/**
	 * 校验 HTTP 返回的 response <br/>
	 * 对  response 返回的错误码进行校验, 包含 404, 503, 200 的情况 
	 */
	public static Boolean verifyResponse(Response response) throws Exception{

		// 确认 response 非空
		if(null == response)
			return true;
		
		
		// 确认响应状态及具体内容
								
		if(response.getStatus() != 200){
			
			throw new Exception(gson.toJson(response));
			
		}
		
			
		JsonObject resp = new JsonParser().parse(response.getResponseStr()).getAsJsonObject();
			
		// 断言校验不通过会直接抛出异常, 并且无法被 try catch 语句捕获, 应该采取其他比较方式,并通过抛出异常的方法解决
		if(1 != resp.get("code").getAsInt()){
			
			throw new Exception(gson.toJson(response));
			
		}				
		
		return true;
		
	}


	
	/**
	 * 增加 path 中的变量部分，构造完整的 path
	 */
	public static List<String> addPathOfVarible(List<String> oldPath, String varible) throws Exception{
		
		List<String> path = new ArrayList<String>();
		
		for(String element : oldPath){
			path.add(element);
		}
				
//		path.addAll(oldPath);
		path.add(varible);
		
		return path;
	}
	

}
