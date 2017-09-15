package com.dfire.qa.meal.httpclient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.dfire.qa.meal.bean.Response;
import com.dfire.qa.meal.logger.LoggerMarkers;
import com.dfire.qa.meal.logger.MealLoggerFactory;

public class HttpRequestEx {
	
	private String host;
	private String protocol = "http";
    private HttpClient httpClient = new DefaultHttpClient();
    
    public void setHttps() throws Exception{
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
            SSLSocketFactory ssf = new SSLSocketFactory(ctx);  
            ClientConnectionManager ccm = httpClient.getConnectionManager();  
            SchemeRegistry sr = ccm.getSchemeRegistry();  
            //设置要使用的端口，默认是443  
            sr.register(new Scheme("https", 443, ssf));  
            
        } catch (Exception ex) {  
            throw new Exception(ex);  
        }    
    }
    
    
    /**
     * this constructor initialize with host and DefaulthttpClient
     */
	public HttpRequestEx(String host, boolean https) throws Exception{
		this.host = host;
		
		if(https){
			protocol = "https";
			setHttps();
		}
	}
	
	/**
	 * this constructor initialize with host and specified httpClient
	 * @param host
	 * @param httpClient
	 */
	public HttpRequestEx(String host, HttpClient httpClient){
		this.host = host;
		this.httpClient = httpClient;
	}
	
	
	public void ShutDown() {
		if (httpClient != null)
			httpClient.getConnectionManager().shutdown();
		else			
			MealLoggerFactory.LOG_FILES_LOGGER.error(LoggerMarkers.LOGFILE, "fail to shut down HTTP connection in HttpRequest");
	}
	
	/**
	 * get complete URL with htttp protocol
	 */
	private String getCompleteURL(List<String> path, Map<String, String> query){
		
		StringBuilder url = new StringBuilder( protocol + "://" + host);
		
		if(path == null || path.isEmpty() == true){
			MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, "the path is invalid");
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
	
	private String getCompleteURLWithHttps(List<String> path, Map<String, String> query){
		StringBuilder url = new StringBuilder("https://" + host);
		if(path == null || path.isEmpty() == true){
			MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, "the path is invalid");
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
	
	@SuppressWarnings("resource")
	private Response processResponse(HttpResponse httpResponse) {
		
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
			MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, "statusLine is : " +
			httpResponse.getStatusLine() + ", response is : " + responseStr );						
			
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
	 * HTTP body is null
	 */
	public Response post(List<String> path, Map<String, String> query) throws IOException {		

		HttpPost httpPost = new HttpPost(getCompleteURL(path, query));	

		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;application/json;charset=utf-8");
		
		httpPost.setEntity(new StringEntity("", "UTF-8"));
		
		Response response = processResponse(httpClient.execute(httpPost));	
		
		return response;
		
	}
	
	/**
	 * 构造带有 表单类型 header 的 post 请求
	 */
	public Response postWithHeader(List<String> path, Map<String, String> query, Map<String, String> param) throws IOException {		

		HttpPost httpPost = new HttpPost(getCompleteURL(path, query));	
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
		String encoding = "UTF-8";
		//装填参数  
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
        if(param!=null){  
            for (Entry<String, String> entry : param.entrySet()) {  
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));  
            }  
        }  
        
        MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, "请求方法为 ：post, 请求 body 为 ：" + nvps.toString());
		
        //设置参数到请求对象中  
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, encoding)); 
        Response response = processResponse(httpClient.execute(httpPost));	
        
		return response;
		
	}
	

	
	public Response postWithHeaders(List<String> path,Map<String, String> header, Map<String, String> query, Map<String, String> param) throws IOException {		

		HttpPost httpPost = new HttpPost(getCompleteURL(path, query));	
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
		if (header!=null) {
			for (Entry<String, String> entry  : header.entrySet()) {
				httpPost.addHeader(entry.getKey(), entry.getValue());
			}
		}
		String encoding = "UTF-8";
		//装填参数  
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
        if(param!=null){  
            for (Entry<String, String> entry : param.entrySet()) {  
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));  
            }  
        }  
        
        MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, "请求方法为 ：post, 请求 body 为 ：" + nvps.toString());
		
        //设置参数到请求对象中  
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, encoding)); 
        Response response = processResponse(httpClient.execute(httpPost));	
		return response;
		
	}
	
	
	
	public Response postWithHeaderandHttps(List<String> path, Map<String, String> query, Map<String, String> param) throws IOException {		

		HttpPost httpPost = new HttpPost(getCompleteURLWithHttps(path, query));	
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded");
		String encoding = "UTF-8";
		//装填参数  
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
        if(param!=null){  
            for (Entry<String, String> entry : param.entrySet()) {  
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));  
            }  
        }  
        
        
        MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, "请求方法为 ：post, 请求 body 为 ：" + nvps.toString());
		
        //设置参数到请求对象中  
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, encoding)); 
        Response response = processResponse(httpClient.execute(httpPost));	
		return response;
		
	}
	
	
	
	/**
	 * post 请求, 带有 HTTP header
	 */
	public Response post(List<String> path, Map<String, String> query, Map<String, String> httpHeader, String httpBody) throws IOException {		

		HttpPost httpPost = new HttpPost(getCompleteURL(path, query));	
		
		
		if(null != httpHeader && !httpHeader.isEmpty()){
			for(Map.Entry<String, String> entry:httpHeader.entrySet())
				httpPost.addHeader(entry.getKey(), entry.getValue());
		}
		
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;application/json;charset=utf-8");
		httpPost.setEntity(new StringEntity(httpBody, "UTF-8"));	
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, "请求 header 是：" + httpHeader + ", 请求 body 是 ： " + httpBody);
		
		Response response = processResponse(httpClient.execute(httpPost));	
		
		return response;
		
	}
	
	
	/**
	 * post 请求, 没有  HTTP header, content-type 为  json 形式
	 */
	public Response post(List<String> path, Map<String, String> query, String httpBody) throws IOException {		

		HttpPost httpPost = new HttpPost(getCompleteURL(path, query));	
		
		httpPost.addHeader("Content-Type", "application/json;charset=utf-8");
		httpPost.setEntity(new StringEntity(httpBody, "UTF-8"));
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, "请求方法是： post" + ", 请求 body 是 ： " + httpBody);

		Response response = processResponse(httpClient.execute(httpPost));	
		
		return response;
		
	}
	
	
	
	
	/**
	 * post 请求, 没有  HTTP header, content-type 为 form 形式
	 */
	public Response postWithFormType(List<String> path, Map<String, String> query, String httpBody) throws IOException {		

		HttpPost httpPost = new HttpPost(getCompleteURL(path, query));	
		
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;application/json;charset=utf-8");
		httpPost.setEntity(new StringEntity(httpBody, "UTF-8"));	
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, "请求 body 是 ： " + httpBody);
		
		Response response = processResponse(httpClient.execute(httpPost));	
		
		return response;
		
	}
	

	
	public Response get(List<String> path, Map<String, String> query) throws IOException {		

		HttpGet httpGet = new HttpGet(getCompleteURL(path, query));	
		httpGet.addHeader("Content-Type", "application/json;charset=utf-8");
		Response response = processResponse(httpClient.execute(httpGet));	
		
		return response;
		
	}

	
	/**
	 * add header, cookie e.g
	 */
	public Response get(List<String> path, Map<String, String> query, Map<String, String> header) throws IOException {		

		HttpGet httpGet = new HttpGet(getCompleteURL(path, query));	
		httpGet.addHeader("Content-Type", "application/json;charset=utf-8");
		
		for(Map.Entry<String, String> entry : header.entrySet()){
			httpGet.addHeader(entry.getKey(), entry.getValue());
		}
		
		Response response = processResponse(httpClient.execute(httpGet));	
		
		return response;
		
	}
	
	/**
	 * add user-agent to the http header
	 */
	public Response get(List<String> path, Map<String, String> query, String user_agent) throws IOException {		

		HttpGet httpGet = new HttpGet(getCompleteURL(path, query));	
		httpGet.addHeader("Content-Type", "application/json;charset=utf-8");
		httpGet.addHeader("User-Agent", user_agent);
		Response response = processResponse(httpClient.execute(httpGet));	
		
		return response;
		
	}
	



}
