package com.dfire.testcase.qjc.membersave;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.dfire.wechat.util.CommonConstants;
import com.dfire.wechat.util.HttpRequestEx;
import com.dfire.wechat.util.MD5Utils;
import com.dfire.wechat.util.PathForPost;
import com.dfire.wechat.util.Response;
import com.dfire.wechat.util.SignUtils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.twodfire.util.JsonUtil;

import net.sf.json.JSONObject;

/**
 * 权金城-保存卡信息接口
 * @author Administrator
 *
 */
public class qjc_CreateCardTest {
	
	private HttpRequestEx httpRequest;
	private static final Logger logger = Logger.getLogger(qjc_CreateCardTest.class);
	
	private String host = "10.1.25.146:8080";  //测试
//	private String host = "open.2dfire-pre.com";  //预发
//	private String host = "open.2dfire.com";  //线上
	private boolean https = CommonConstants.HTTPS;
	Map<String, String> query2 = new HashMap<String, String>(); 
	private int sleeptime = 8000;    //等待时间,部分接口跑太快数据未落地
	
	/**
	 * 保存卡信息：1金额负数  2source不存在   3正常创建   4重复创建
	 * @param description
	 * @param query
	 * @param responseStatus
	 * @param resultCode
	 * @param jobStatus
	 * @throws Exception
	 */
	@Test(priority = 1,description = "qjc member cardsave", dataProvider = "membercardsaveTest",
			dataProviderClass = qjc_CreateCardTestData.class, groups = {"smoke", "all"})
	public void membercardsaveTest(String description, Map<String, String> query, 
			                   int responseStatus, int resultCode, int jobStatus)throws Exception{
		
		try{
			httpRequest = new HttpRequestEx(host, https);
			
			Response response1 = httpRequest.post_john(PathForPost.getPathForqjc(),query2,query);
			JsonObject resp = new JsonParser().parse(response1.getResponseStr()).getAsJsonObject();//将Json字符串转换成JsonObject对象
			if(jobStatus==100){  //金额为负数
				Assert.assertFalse(resp.get("success").getAsBoolean());
				Assert.assertEquals(resp.get("message").getAsString(), "输入参数为空或格式不正确!");
			}else if(jobStatus==200){  //source不存在
				Assert.assertFalse(resp.get("success").getAsBoolean());
				Assert.assertEquals(resp.get("message").getAsString(), "会员发放渠道权限未开通，请和管理员联系");
			}else if(jobStatus==300){  //正常保存卡信息
				Assert.assertTrue(resp.get("success").getAsBoolean());
			}else if(jobStatus==400){  //保存卡信息重复
				Assert.assertFalse(resp.get("success").getAsBoolean());
				Assert.assertEquals(resp.getAsJsonArray("model").get(0).getAsJsonObject().get("errMsg").getAsString().substring(0, 6), "此卡号已存在");
				 
			}
						
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}
	
	/**
	 * 保存卡信息后，查询操作流水，有一条激活的流水
	 * @param description
	 * @param query
	 * @param responseStatus
	 * @param resultCode
	 * @param jobStatus
	 * @throws Exception
	 */
	@Test(priority = 2 , description = "qjc member oper flow", dataProvider = "memberOperFlowTest",
			dataProviderClass = qjc_CreateCardTestData.class, groups = {"smoke", "all"})
	public void memberOperFlowTest(String description, Map<String, String> query, 
			                   int responseStatus, int resultCode, int jobStatus)throws Exception{
		
		try{
			Thread.sleep(sleeptime); 
			httpRequest = new HttpRequestEx(host, https);
			
			Response response1 = httpRequest.post(PathForPost.getPathForqjc(),query);
			System.out.println(response1);
			JsonObject resp = new JsonParser().parse(response1.getResponseStr()).getAsJsonObject();//将Json字符串转换成JsonObject对象
			Assert.assertTrue(resp.get("success").getAsBoolean());			
			if(resp.get("success").getAsBoolean() == true){
				//校验下action(操作方式为支付)
				Assert.assertEquals(resp.getAsJsonArray("model").get(0).getAsJsonObject().get("action").getAsString(), "7");
				//校验下卡状态为1（已领用）
				Assert.assertEquals(resp.getAsJsonArray("model").get(0).getAsJsonObject().get("status").getAsString(), "1");
				//卡号（还不知道怎么传入这个卡号）
//				Assert.assertEquals(resp.getAsJsonArray("model").get(0).getAsJsonObject().get("cardCode").getAsString(), "1");
			}
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}
	
	
	/**
	 * 保存卡信息后，查询金额流水，有一条充值流水（如果保存卡时候realBalance有传值）
	 * @param description
	 * @param query
	 * @param responseStatus
	 * @param resultCode
	 * @param jobStatus
	 * @throws Exception
	 */
	@Test(priority = 3 , description = "qjc member money flow", dataProvider = "memberMoneyFlowTest",
			dataProviderClass = qjc_CreateCardTestData.class, groups = {"smoke", "all"})
	public void memberMoneyFlowTest(String description, Map<String, String> query, 
			                   int responseStatus, int resultCode, int jobStatus)throws Exception{
		
		try{
//			Thread.sleep(sleeptime);
			httpRequest = new HttpRequestEx(host, https);
			
			Response response1 = httpRequest.post(PathForPost.getPathForqjc(),query);
			System.out.println(response1);
			JsonObject resp = new JsonParser().parse(response1.getResponseStr()).getAsJsonObject();//将Json字符串转换成JsonObject对象
			Assert.assertTrue(resp.get("success").getAsBoolean());			
			if(resp.get("success").getAsBoolean() == true){
				//实收额
				Assert.assertEquals(resp.getAsJsonArray("model").get(0).getAsJsonObject().get("pay").getAsString(), "20.00");
				//发生额
				Assert.assertEquals(resp.getAsJsonArray("model").get(0).getAsJsonObject().get("fee").getAsString(), "20.00");
			
			}
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}
	
	
	/**
	 * 保存卡信息后，查询会员卡信息：1 balance(余额)  2 giftBalance（赠送金额）  3 realBalance（实际充值金额） 4 degree（积分）
	 * 5 payAmount（支付累积金额） 6 consumeAmount（消费累积金额）  7 ratioAmount（折扣累积金额）8 getStatus（领用状态）
	 * @param description
	 * @param query
	 * @param responseStatus
	 * @param resultCode
	 * @param jobStatus
	 * @throws Exception
	 */
	@Test(priority = 4 , description = "qjc membercardlist", dataProvider = "memberCardListTest",
			dataProviderClass = qjc_CreateCardTestData.class, groups = {"smoke", "all"})
	public void memberCardListTest(String description, Map<String, String> query, 
			                   int responseStatus, int resultCode, int jobStatus)throws Exception{
		
		try{
			Thread.sleep(sleeptime);  //怕数据没落地
			httpRequest = new HttpRequestEx(host, https);
			
			Response response1 = httpRequest.post(PathForPost.getPathForqjc(),query);
			System.out.println(response1);
			JsonObject resp = new JsonParser().parse(response1.getResponseStr()).getAsJsonObject();//将Json字符串转换成JsonObject对象
			Assert.assertTrue(resp.get("success").getAsBoolean());
			if(resp.get("success").getAsBoolean()){
				//校验下1 balance(余额)  2 giftBalance（赠送金额）  3 realBalance（实际充值金额） 4 degree（积分）
				 //* 5 payAmount（支付累积金额） 6 consumeAmount（消费累积金额）  7 ratioAmount（折扣累积金额）8 getStatus（领用状态）
				Assert.assertEquals(resp.getAsJsonArray("model").get(0).getAsJsonObject().get("balance").getAsString(), "20.00");
				Assert.assertEquals(resp.getAsJsonArray("model").get(0).getAsJsonObject().get("giftBalance").getAsString(), "10.00");
				Assert.assertEquals(resp.getAsJsonArray("model").get(0).getAsJsonObject().get("realBalance").getAsString(), "10.00");
				Assert.assertEquals(resp.getAsJsonArray("model").get(0).getAsJsonObject().get("degree").getAsString(), "0.00");
				Assert.assertEquals(resp.getAsJsonArray("model").get(0).getAsJsonObject().get("payAmount").getAsString(), "10.00");
				Assert.assertEquals(resp.getAsJsonArray("model").get(0).getAsJsonObject().get("consumeAmount").getAsString(), "0.00");
				Assert.assertEquals(resp.getAsJsonArray("model").get(0).getAsJsonObject().get("ratioAmount").getAsString(), "10.00");
				Assert.assertEquals(resp.getAsJsonArray("model").get(0).getAsJsonObject().get("getStatus").getAsString(), "1");
	
			}
		
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}
	
	
}
