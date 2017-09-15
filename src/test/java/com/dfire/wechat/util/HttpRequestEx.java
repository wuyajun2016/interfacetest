package com.dfire.wechat.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
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
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.dfire.testcase.appchange.SearchShopTestData;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class HttpRequestEx {
	
	private static final Logger logger = Logger.getLogger(HttpRequestEx.class);
	private String host;
	private String protocol = "http";
//    private HttpClient httpClient = new DefaultHttpClient();   //---john之前是用这句，为了抓包改成了下面两句
	                                                             //如果走https的请求要改回去
    HttpHost proxy = new HttpHost("localhost", 8888);
    HttpClient httpClient = HttpClients.custom().setProxy(proxy).build();
     
    public void setHttps(){
        try {  
//        	System.setProperty("javax.net.ssl.trustStore", "D:\\jdk1.7.0_17\\jre\\lib\\security\\FiddlerKeystore");
//            System.setProperty("javax.net.ssl.trustStorePassword", "123456"); //如果是https，设置证书
            
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
            logger.error("", ex);  
        }    
    }
    
    
    /**
     * this constructor initialize with host and DefaulthttpClient
     */
	public HttpRequestEx(String host, boolean https){
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
	
	
	public void ShutDown(){
		if (httpClient != null)
			httpClient.getConnectionManager().shutdown();
		else
			logger.info("fail to shut down HTTP connection in HttpRequest");
	}
	
	/**
	 * get complete URL with htttp protocol
	 * @param path
	 * @param query
	 * @return
	 */
	private String getCompleteURL(List<String> path, Map<String, String> query){
		
		StringBuilder url = new StringBuilder( protocol + "://" + host);
		
		if(path == null || path.isEmpty() == true){
			logger.error("the path is invalid");
			return null;
		}
		for(String element:path)
			url.append("/" + element);
		
		if(query == null || query.isEmpty() == true){
			logger.info("the URL is : " + url.toString());
			return url.toString();
		}
		url.append("?");
		for(Map.Entry<String, String> entry:query.entrySet()){
			url.append(entry.getKey() + "=" + entry.getValue() + "&");
		}
		url.deleteCharAt(url.length() - 1);
		logger.info("the URL is : " + url.toString());
		return url.toString();
	}
	
	private String getCompleteURLWithHttps(List<String> path, Map<String, String> query){
		StringBuilder url = new StringBuilder("https://" + host);
		if(path == null || path.isEmpty() == true){
			logger.error("the path is invalid");
			return null;
		}
		for(String element:path)
			url.append("/" + element);
		
		if(query == null || query.isEmpty() == true){
			logger.info("the URL is : " + url.toString());
			return url.toString();
		}
		url.append("?");
		for(Map.Entry<String, String> entry:query.entrySet()){
			url.append(entry.getKey() + "=" + entry.getValue() + "&");
		}
		url.deleteCharAt(url.length() - 1);
		logger.info("the URL is : " + url.toString());
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
						
			logger.info(statusCode);
			logger.info(httpResponse.getStatusLine());
			logger.info("返回："+responseStr);	
			
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
				logger.error("fail to close input stream or output stream");
			}
		}
		
		return response;
	}	
	
	public Response put(List<String> path, Map<String, String> query, HttpContent httpContent) throws IOException {		

		HttpPut httpPut = new HttpPut(getCompleteURL(path, query));	
		
		if( !httpContent.httpHeaderIsValid() ){
			logger.error("http header is invalid");
			return null;
		}
		for(Map.Entry<String, String> entry:httpContent.getHttpHeader().entrySet())
			httpPut.addHeader(entry.getKey(), entry.getValue());
		
		httpPut.addHeader("Content-Type", "application/json;charset=utf-8");
		
		httpPut.setEntity(new StringEntity(httpContent.getHttpBody(), "UTF-8"));
		
		logger.info("http header is : " + httpContent.getHttpHeader());
		logger.info("http body is : " + httpContent.getHttpBody());
		
		Response response = processResponse(httpClient.execute(httpPut));	
		
		return response;
		
	}
	
	public Response post(List<String> path, Map<String, String> query, HttpContent httpContent) throws IOException {		

		HttpPost httpPost = new HttpPost(getCompleteURL(path, query));	
		
		if( !httpContent.httpHeaderIsValid() ){
			logger.error("http header is invalid");
			return null;
		}
		if(httpContent.getHttpBody() == null){
			logger.error("http body is null which is invalid");
			return null;
		}
		
		for(Map.Entry<String, String> entry:httpContent.getHttpHeader().entrySet())
			httpPost.addHeader(entry.getKey(), entry.getValue());

		httpPost.addHeader("Content-Type", "application/json;charset=utf-8");
		
		httpPost.setEntity(new StringEntity(httpContent.getHttpBody(), "UTF-8"));
		
		logger.info("http header is : " + httpContent.getHttpHeader());
		logger.info("http body is : " + httpContent.getHttpBody());
		
		Response response = processResponse(httpClient.execute(httpPost));	
		
		return response;
		
	}
	
	/**
	 * HTTP body is null
	 * @param path
	 * @param query
	 * @param httpContent
	 * @return
	 * @throws IOException
	 */
	public Response post(List<String> path, Map<String, String> query) throws IOException {		

		HttpPost httpPost = new HttpPost(getCompleteURL(path, query));	

		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;application/json;charset=utf-8");
		//httpPost.addHeader("Authorization", "APPCODE " + "3e760ce26db846fa8d3e38e45c9eee92");
		httpPost.setEntity(new StringEntity("", "UTF-8"));
		
		Response response = processResponse(httpClient.execute(httpPost));	
		
		return response;
		
	}
	
	/**
	 * 构造带有 表单类型 header 的 post 请求
	 * @param path
	 * @param query
	 * @param param
	 * @return
	 * @throws IOException
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
        logger.info("请求参数："+nvps.toString());
        logger.info("请求方法：post！");
		
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
        logger.info("请求参数："+nvps.toString());
		
        //设置参数到请求对象中  
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, encoding)); 
        Response response = processResponse(httpClient.execute(httpPost));	
		return response;
		
	}
	
	/**
	 * john-发送post，https请求
	 * @param path
	 * @param query
	 * @param param
	 * @return
	 * @throws IOException
	 */
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
        logger.info("请求参数："+nvps.toString());
		
        //设置参数到请求对象中  
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, encoding)); 
        Response response = processResponse(httpClient.execute(httpPost));	
        System.out.println(httpClient.execute(httpPost));
		return response;
		
	}
	
	
	
	/**
	 * post 请求, 带有 HTTP header
	 * @param path
	 * @param query
	 * @param httpHeader
	 * @param httpBody
	 * @return
	 * @throws IOException
	 */
	public Response post(List<String> path, Map<String, String> query, Map<String, String> httpHeader, String httpBody) throws IOException {		

		HttpPost httpPost = new HttpPost(getCompleteURL(path, query));	
		
		
		if(null != httpHeader && !httpHeader.isEmpty()){
			for(Map.Entry<String, String> entry:httpHeader.entrySet())
				httpPost.addHeader(entry.getKey(), entry.getValue());
		}
		
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;application/json;charset=utf-8");
		httpPost.setEntity(new StringEntity(httpBody, "UTF-8"));	
		
		logger.info("http header is : " + httpHeader);
		logger.info("http body is : " + httpBody);
		
		Response response = processResponse(httpClient.execute(httpPost));	
		
		return response;
		
	}
	
	
	/**
	 * post 请求, 没有  HTTP header, content-type 为  json 形式
	 * @param path
	 * @param query
	 * @param httpBody
	 * @return
	 * @throws IOException
	 */
	public Response post(List<String> path, Map<String, String> query, String httpBody) throws IOException {		

		HttpPost httpPost = new HttpPost(getCompleteURL(path, query));	
		logger.info("the http method: post");
		
		httpPost.addHeader("Content-Type", "application/json;charset=utf-8");
		httpPost.setEntity(new StringEntity(httpBody, "UTF-8"));
		
		logger.info("the http body is: " + httpBody);
		Response response = processResponse(httpClient.execute(httpPost));	
		
		return response;
		
	}
	
	
	
	
	/**
	 * post 请求, 没有  HTTP header, content-type 为 form 形式
	 * @param path
	 * @param query
	 * @param httpBody
	 * @return
	 * @throws IOException
	 */
	public Response postWithFormType(List<String> path, Map<String, String> query, String httpBody) throws IOException {		

		HttpPost httpPost = new HttpPost(getCompleteURL(path, query));	
		
		httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;application/json;charset=utf-8");
		httpPost.setEntity(new StringEntity(httpBody, "UTF-8"));	
		
		logger.info("the http body is: " + httpBody);
		
		Response response = processResponse(httpClient.execute(httpPost));	
		
		return response;
		
	}
	

	/**
	 * --john get http
	 * @param path
	 * @param query
	 * @return
	 * @throws IOException
	 */
	public Response get(List<String> path, Map<String, String> query) throws IOException {		

		HttpGet httpGet = new HttpGet(getCompleteURL(path, query));	
		httpGet.addHeader("Content-Type", "application/json;charset=utf-8");
		Response response = processResponse(httpClient.execute(httpGet));	
		
		return response;
		
	}

	
	/**
	 * add header, cookie e.g
	 * @param path
	 * @param query
	 * @param header
	 * @return
	 * @throws IOException
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
	 * @param path
	 * @param query
	 * @param user_agent
	 * @return
	 * @throws IOException
	 */
	public Response get(List<String> path, Map<String, String> query, String user_agent) throws IOException {		

		HttpGet httpGet = new HttpGet(getCompleteURL(path, query));	
		httpGet.addHeader("Content-Type", "application/json;charset=utf-8");
		httpGet.addHeader("User-Agent", user_agent);
		Response response = processResponse(httpClient.execute(httpGet));	
		
		return response;
		
	}
	
	public Response delete(List<String> path, Map<String, String> query, HttpContent httpContent) throws IOException {		

		HttpDelete httpDelete = new HttpDelete(getCompleteURL(path, query));	
		
		if( !httpContent.httpHeaderIsValid() ){
			logger.error("http header is invalid");
			return null;
		}
		for(Map.Entry<String, String> entry:httpContent.getHttpHeader().entrySet())
			httpDelete.addHeader(entry.getKey(), entry.getValue());
		
		httpDelete.addHeader("Content-Type", "application/json;charset=utf-8");
		
		Response response = processResponse(httpClient.execute(httpDelete));	
		
		return response;
		
	}
	
	
	/**
	 * john，发送post  http请求
	 * @param path    host
	 * @param query   查询条件,?后面的那段链接，一般都没有，直接拼在host后面了
	 * @param param   body数据
	 * @return
	 * @throws IOException
	 */
	public Response post_john(List<String> path, Map<String, String> query,Map<String, String> param) throws IOException {		

		
		// 目标地址
		HttpPost httpPost = new HttpPost(getCompleteURL(path, query));

	    System.out.println("请求: " + httpPost.getRequestLine());

	    // post 参数 传递

	    List<BasicNameValuePair> nvps = new ArrayList<BasicNameValuePair>();

//	    Map<String,String> params=new HashMap<>();

	    for(String key:param.keySet()){

	    nvps.add(new BasicNameValuePair(key, String.valueOf(param.get(key)))); // 参数

	    }
	    logger.info("请求参数："+nvps.toString());
	    UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(nvps, "UTF-8");

	    httpPost.setEntity(formEntity); // 设置参数给Post
		
	    // 执行

//	    HttpResponse response = httpClient.execute(httpPost);
        Response response1 = processResponse(httpClient.execute(httpPost));	
		return response1;
		
	}
	
	
	



}
