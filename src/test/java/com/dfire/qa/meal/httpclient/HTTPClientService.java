package com.dfire.qa.meal.httpclient;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;











import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dfire.qa.meal.bean.Response;
import com.dfire.qa.meal.logger.LoggerMarkers;
import com.dfire.qa.meal.logger.MealLoggerFactory;
import com.dfire.qa.meal.utils.HTTPRequestUtil;







@Service
public class HTTPClientService {
	
	@Autowired
	private CloseableHttpClient httpClient;
	
	@Autowired
	private RequestConfig requestConfig;

	
	/**
	 * 绕过证书检查
	 */
	public void setHttps(){
		
        try {  
            SSLContext ctx = SSLContext.getInstance("TLS");  
            
            X509TrustManager tm = new X509TrustManager() {  
            	
                public void checkClientTrusted(  
                        java.security.cert.X509Certificate[] chain,  
                        String authType)  
                        throws java.security.cert.CertificateException {  
                }  
  
                public void checkServerTrusted(  
                        java.security.cert.X509Certificate[] chain,  
                        String authType)  
                        throws java.security.cert.CertificateException {  
                }  
  
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {  
                    return null;  
                }  
                  
            };  
            
            ctx.init(null, new TrustManager[] { (TrustManager) tm }, null);  
            
            LayeredConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(ctx);
            
            CloseableHttpClient httpClientWithHttps = HttpClients.custom().setSSLSocketFactory(sslSocketFactory).build();
            
            httpClient = httpClientWithHttps;
            
        } catch (Exception ex) {  
        	
            ex.printStackTrace();  
        }    
    }

	
	
	/**
	 * 执行GET请求
	 */
	public Response doGet(String url, Map<String, String> header) throws Exception {
		
		// 创建http GET请求
		HttpGet httpGet = new HttpGet(url);
		
		// 设置请求头
		httpGet.addHeader("Content-Type", "application/json;charset=utf-8");
		
		if(header != null && !header.isEmpty()){
			for(Map.Entry<String, String> entry : header.entrySet()){
				httpGet.addHeader(entry.getKey(), entry.getValue());
			}
		}
		
		httpGet.setConfig(this.requestConfig);

		CloseableHttpResponse response = null;
		Response responseResult = null;
		
		try {
			
			// 执行请求
			response = httpClient.execute(httpGet);
			
			responseResult = HTTPRequestUtil.processResponse(response);
			
		} finally {
			if (response != null) {
				response.close();
			}
			
			httpGet.releaseConnection();
		}
		
		return responseResult;
	}
	
	
	
	/**
	 * 以表单形式执行 POST 请求
	 */
	public Response doPost(String url, Map<String, String> header, String httpBody) throws Exception {
		
		// 创建http GET请求
		HttpPost httpPost = new HttpPost(url);
		
		// 设置请求头
		httpPost.addHeader("Content-Type", "application/json;charset=utf-8");
		
		if(header != null && !header.isEmpty()){
			for(Map.Entry<String, String> entry : header.entrySet()){
				httpPost.addHeader(entry.getKey(), entry.getValue());
			}
		}
		
		httpPost.setConfig(this.requestConfig);
//		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, "request config is : " + requestConfig.getConnectionRequestTimeout() + ", " 
//		+ requestConfig.getConnectTimeout()  + ", " + requestConfig.getSocketTimeout() );
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, "the httpBody is : " + httpBody);
		
		httpPost.setEntity(new StringEntity(httpBody, "UTF-8"));	
		
		CloseableHttpResponse response = null;
		Response responseResult = null;
		
		try {
			// 执行请求
			response = httpClient.execute(httpPost);
			
			responseResult = HTTPRequestUtil.processResponse(response);
			
		} finally {
			if (response != null) {
				response.close();
			}
			
			httpPost.releaseConnection();
		}
		
		return responseResult;
	}
	


	/**
	 * 以 表单形式执行 POST 请求
	 */
	public Response doPostWithForm(String url, Map<String, String> header, String httpBody) throws Exception {
		
		// 创建http GET请求
		HttpPost httpPost = new HttpPost(url);
		
		// 设置请求头
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;application/json;charset=utf-8");
		
		if(header != null && !header.isEmpty()){
			for(Map.Entry<String, String> entry : header.entrySet()){
				httpPost.addHeader(entry.getKey(), entry.getValue());
			}
		}
		
		httpPost.setConfig(this.requestConfig);
		httpPost.setEntity(new StringEntity(httpBody, "UTF-8"));	

		
		CloseableHttpResponse response = null;
		Response responseResult = null;
		
		try {
			
			// 执行请求
			response = httpClient.execute(httpPost);
			
			responseResult = HTTPRequestUtil.processResponse(response);
			
		} finally {
			if (response != null) {
				response.close();
			}
			
			httpPost.releaseConnection();
		}
		
		return responseResult;
	}
	
	
	
	/**
	 * 以 表单形式执行 POST 请求
	 */
	public Response doPostForCashWithForm(String url, Map<String, String> header, Map<String, String> paramOfBody) throws Exception {
		
		// 创建http GET请求
		HttpPost httpPost = new HttpPost(url);
		
		// 设置请求头
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;application/json;charset=utf-8");
		
		if(header != null && !header.isEmpty()){
			for(Map.Entry<String, String> entry : header.entrySet()){
				httpPost.addHeader(entry.getKey(), entry.getValue());
			}
		}
		
		
		//装填参数  
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
        if(paramOfBody != null){  
            for (Entry<String, String> entry : paramOfBody.entrySet()) {  
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));  
            }  
        }  
        
        
		httpPost.setConfig(this.requestConfig);
		httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));	

		
		CloseableHttpResponse response = null;
		Response responseResult = null;
		
		try {
			
			// 执行请求
			response = httpClient.execute(httpPost);
			
			responseResult = HTTPRequestUtil.processResponse(response);
			
		} finally {
			if (response != null) {
				response.close();
			}
			
			httpPost.releaseConnection();
		}
		
		return responseResult;
	}
	
	
	
	/**
	 * 通过  POST 请求上传文件
	 */
	public Response doPostFile(String url, Map<String, String> header, HttpEntity httpEntity, String boundary) throws Exception {
		
		// 创建http GET请求
		HttpPost httpPost = new HttpPost(url);
		
		// 设置请求头
		httpPost.addHeader("Content-Type", "multipart/form-data;charset=utf-8; boundary=" + boundary);
		
		if(header != null && !header.isEmpty()){
			for(Map.Entry<String, String> entry : header.entrySet()){
				httpPost.addHeader(entry.getKey(), entry.getValue());
			}
		}
		
		httpPost.setConfig(this.requestConfig);
//		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, "request config is : " + requestConfig.getConnectionRequestTimeout() + ", " 
//		+ requestConfig.getConnectTimeout()  + ", " + requestConfig.getSocketTimeout() );
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, "the httpBody is : ");
		
		httpPost.setEntity(httpEntity);	
		
		CloseableHttpResponse response = null;
		Response responseResult = null;
		
		try {
			// 执行请求
			response = httpClient.execute(httpPost);
			
			responseResult = HTTPRequestUtil.processResponse(response);
			
		} finally {
			if (response != null) {
				response.close();
			}
			
			httpPost.releaseConnection();
		}
		
		return responseResult;
	}
}
