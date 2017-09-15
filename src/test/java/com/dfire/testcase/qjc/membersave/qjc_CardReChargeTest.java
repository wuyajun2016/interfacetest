package com.dfire.testcase.qjc.membersave;

import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.dfire.wechat.util.CommonConstants;
import com.dfire.wechat.util.HttpRequestEx;
import com.dfire.wechat.util.PathForPost;
import com.dfire.wechat.util.Response;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * 权金城-充值接口测试
 * @author Administrator
 *
 */
public class qjc_CardReChargeTest {
	
	private HttpRequestEx httpRequest;
	private static final Logger logger = Logger.getLogger(qjc_CardReChargeTest.class);
	
//	private String host = CommonConstants.DEFAULT_HOST;
//	private String host = "10.1.25.146:8080";  //测试
//	private String host = "open.2dfire-pre.com";  //预发
	private String host = "open.2dfire.com";  //线上
	private boolean https = CommonConstants.HTTPS;
	private float balance;
	private float realBalance;
	private int sleeptime = 8000;    //等待时间,部分接口跑太快数据未落地
	
//	@BeforeClass(alwaysRun = true)
//	public void setup(){
//		
//	}
	/**
	 * 数据准备，不操作数据库只能这么干了。。。
	 * @param description
	 * @param query
	 * @param responseStatus
	 * @param resultCode
	 * @param jobStatus
	 * @throws Exception
	 */
	@Test(priority = 1 , description = "qjc membercardlist", dataProvider = "memberCardListTest",
			dataProviderClass = qjc_CardReChargeTestData.class, groups = {"smoke", "all"})
	public void getPayAndFee(String description, Map<String, String> query, 
			                   int responseStatus, int resultCode, int jobStatus)throws Exception{
		
		try{
			System.out.println("----------------------开始跑充值接口-----------------------------");
			httpRequest = new HttpRequestEx(host, https);
			Thread.sleep(sleeptime);//----xianshang
			Response response1 = httpRequest.post(PathForPost.getPathForqjc(),query);
			System.out.println(response1);
			JsonObject resp = new JsonParser().parse(response1.getResponseStr()).getAsJsonObject();//将Json字符串转换成JsonObject对象
			Assert.assertTrue(resp.get("success").getAsBoolean());			
			if(resp.get("success").getAsBoolean() == true){
				
				balance = resp.getAsJsonArray("model").get(0).getAsJsonObject().get("balance").getAsFloat();
				realBalance = resp.getAsJsonArray("model").get(0).getAsJsonObject().get("realBalance").getAsFloat();
				System.out.println(balance+realBalance);
			
			}
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}

	/**
	 * 充值操作：正常   负数    0    流水号存在     冲1分钱
	 * @param description
	 * @param query
	 * @param responseStatus
	 * @param resultCode
	 * @param jobStatus
	 * @throws Exception
	 */
	@Test(priority = 2,description = "qjc member chongzhi", dataProvider = "memberczTest",
			dataProviderClass = qjc_CardReChargeTestData.class, groups = {"smoke", "all"})
	public void memberczTest(String description, Map<String, String> query, 
			                   int responseStatus, int resultCode, int jobStatus)throws Exception{
		
		try{
			httpRequest = new HttpRequestEx(host, https);
			           
			Response response1 = httpRequest.post(PathForPost.getPathForqjc(),query);
			System.out.println("充值响应："+response1);
			JsonObject resp = new JsonParser().parse(response1.getResponseStr()).getAsJsonObject();//将Json字符串转换成JsonObject对象
			//Assert.assertEquals(resp.get("success"), true);
			if(jobStatus==200){  //充值正常的
				Assert.assertTrue(resp.get("success").getAsBoolean());
			}else if(jobStatus==300){  //充值金额为负数
				Assert.assertFalse(resp.get("success").getAsBoolean());
				Assert.assertEquals(resp.get("resultCode").getAsInt(), resultCode);
				Assert.assertEquals(resp.get("message").getAsString(), "请查看根据服务接口对参数格式的要求");
			}else if(jobStatus==400){  //充值流水号已存在
				Assert.assertFalse(resp.get("success").getAsBoolean());
				Assert.assertEquals(resp.get("resultCode").getAsInt(), resultCode);
				Assert.assertEquals(resp.get("message").getAsString(), "该交易流水号已存在");
			}else if(jobStatus==500){  //充值为0
				Assert.assertFalse(resp.get("success").getAsBoolean());
				Assert.assertEquals(resp.get("resultCode").getAsInt(), resultCode);
				Assert.assertEquals(resp.get("message").getAsString(), "请查看根据服务接口对参数格式的要求");
				Thread.sleep(sleeptime);   //调充值接口太快，可能会调不成功，所以这里等待下,再去充值1分钱
			}else if(jobStatus==600){  //充值1分
				
				Assert.assertTrue(resp.get("success").getAsBoolean());
			}
						
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}
	
	/**
	 * 充值后校验下金额流水返回的pay（实收额）、fee（发生额）
	 * @param description
	 * @param query
	 * @param responseStatus
	 * @param resultCode
	 * @param jobStatus
	 * @throws Exception
	 */
	@Test(priority = 3 , description = "qjc member money flow", dataProvider = "memberMoneyFlowTest",
			dataProviderClass = qjc_CardReChargeTestData.class, groups = {"smoke", "all"})
	public void memberMoneyFlowTest(String description, Map<String, String> query, 
			                   int responseStatus, int resultCode, int jobStatus)throws Exception{
		
		try{
			Thread.sleep(sleeptime); //调查询金额流水接口太快，可能上一步的充值1分钱还没落地，所以这里等待下
			httpRequest = new HttpRequestEx(host, https);
			
			Response response1 = httpRequest.post(PathForPost.getPathForqjc(),query);
			System.out.println(response1);
			JsonObject resp = new JsonParser().parse(response1.getResponseStr()).getAsJsonObject();//将Json字符串转换成JsonObject对象
			//Assert.assertEquals(resp.get("success"), true);
			Assert.assertTrue(resp.get("success").getAsBoolean());			
			if(resp.get("success").getAsBoolean() == true){
				
				Assert.assertEquals(resp.getAsJsonArray("model").get(0).getAsJsonObject().get("pay").getAsString(), "0.01");
				Assert.assertEquals(resp.getAsJsonArray("model").get(0).getAsJsonObject().get("fee").getAsString(), "0.01");
			
			}
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}
	
	/**
	 * 充值后查询卡，校验下里面卡的余额（balance）、实际充值金额（realBalance）
	 * @param description
	 * @param query
	 * @param responseStatus
	 * @param resultCode
	 * @param jobStatus
	 * @throws Exception
	 */
	@Test(priority = 4 , description = "qjc membercardlist", dataProvider = "memberCardListTest",
			dataProviderClass = qjc_CardReChargeTestData.class, groups = {"smoke", "all"})
	public void memberCardListTest(String description, Map<String, String> query, 
			                   int responseStatus, int resultCode, int jobStatus)throws Exception{
		
		try{
			
			Thread.sleep(sleeptime);  //等待下，充值后查询太快，数据还没落地
			httpRequest = new HttpRequestEx(host, https);
			
			Response response1 = httpRequest.post(PathForPost.getPathForqjc(),query);
			System.out.println(response1);
			JsonObject resp = new JsonParser().parse(response1.getResponseStr()).getAsJsonObject();//将Json字符串转换成JsonObject对象
			//Assert.assertEquals(resp.get("success"), true);
			Assert.assertTrue(resp.get("success").getAsBoolean());			
			if(resp.get("success").getAsBoolean() == true){
				//校验下余额、实际充值金额
				Assert.assertEquals(resp.getAsJsonArray("model").get(0).getAsJsonObject().get("balance").getAsFloat(), (float)(Math.round((balance+30.01f)*100))/100);
				Assert.assertEquals(resp.getAsJsonArray("model").get(0).getAsJsonObject().get("realBalance").getAsFloat(), (float)(Math.round((realBalance+30.01f)*100))/100);
			
			}
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}
	
}
