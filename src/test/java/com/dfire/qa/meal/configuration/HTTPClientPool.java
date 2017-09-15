package com.dfire.qa.meal.configuration;


import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;




@Configuration
public class HTTPClientPool {
	
	
	/**
	 * 定义连接管理器
	 * @return
	 */
	@Bean(destroyMethod = "close")
	public PoolingHttpClientConnectionManager getPoolingHttpClientConnectionManager(){
		
		
		PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
		
		// 设置最大连接数
		poolingHttpClientConnectionManager.setMaxTotal(200);
		
		// 设置每个主机地址的并发数
		poolingHttpClientConnectionManager.setDefaultMaxPerRoute(50);
		
		
		
		return poolingHttpClientConnectionManager;
		
	}
	
	
	
	/**
	 * httpclient 对象构建器
	 * @param connectionManager
	 * @return
	 */
	@Bean
	public HttpClientBuilder getHttpClientBuilder(){
		
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		
		httpClientBuilder.setConnectionManager(getPoolingHttpClientConnectionManager());
		
		return httpClientBuilder;
	}
	
	
	
	/**
	 * 定义Httpclient对象
	 * @return
	 */
	@Bean
	@Scope("prototype")
	public CloseableHttpClient getCloseableHttpClient(){
		
		CloseableHttpClient closeableHttpClient = getHttpClientBuilder().build();
		
		return closeableHttpClient;
		
	}
	
	
	
	
	/**
	 * 定义请求参数构建器
	 * @return
	 */
	@Bean
	public Builder getRequestConfigBuilder(){
		
		Builder builder = RequestConfig.custom();
		builder.setConnectTimeout(30000);
		builder.setConnectionRequestTimeout(60000);
		builder.setSocketTimeout(30000);
		builder.setStaleConnectionCheckEnabled(true);
		
		return builder;
	}
	
	
	/**
	 * 获取请求配置类
	 * @return
	 */
	@Bean
	public RequestConfig getRequestConfig(){
		
		RequestConfig requestConfig = getRequestConfigBuilder().build();
		
		return requestConfig;
		
	}
	
	
	
	
	

}
