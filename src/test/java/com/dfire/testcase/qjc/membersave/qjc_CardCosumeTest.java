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
 * 权金城-消费接口
 * @author Administrator
 *
 */
public class qjc_CardCosumeTest {
	
	private HttpRequestEx httpRequest;
	private static final Logger logger = Logger.getLogger(qjc_CardCosumeTest.class);
	
	private String host11111 = CommonConstants.DEFAULT_HOST;
//	private String host = "10.1.25.146:8080";  //测试
//	private String host = "open.2dfire-pre.com";  //预发
	private String host = "open.2dfire.com";  //线上
	private boolean https = CommonConstants.HTTPS;
	private float balance;  //余额
	private float realBalance; //累积充值金额
	private float payamount; //支付累积
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
	@Test(priority = 1 , description = "qjc membercosumcardlist_data_setup", dataProvider = "memberConsumCardListTest",
			dataProviderClass = qjc_CardCosumeTestData.class, groups = {"smoke", "all"})
	public void getPayAndFee(String description, Map<String, String> query, 
			                   int responseStatus, int resultCode, int jobStatus)throws Exception{
		
		try{
			System.out.println("----------------------开始跑消费接口-----------------------------");
			httpRequest = new HttpRequestEx(host, https);
			Thread.sleep(10000);//不加多个跑不行.而且需要10s之久，不然前面用例数据没落地
			Response response1 = httpRequest.post(PathForPost.getPathForqjc(),query);
			System.out.println(response1);
			JsonObject resp = new JsonParser().parse(response1.getResponseStr()).getAsJsonObject();//将Json字符串转换成JsonObject对象
			Assert.assertTrue(resp.get("success").getAsBoolean());			
			if(resp.get("success").getAsBoolean() == true){
				
				balance = resp.getAsJsonArray("model").get(0).getAsJsonObject().get("balance").getAsFloat();
				realBalance = resp.getAsJsonArray("model").get(0).getAsJsonObject().get("realBalance").getAsFloat();
				payamount = resp.getAsJsonArray("model").get(0).getAsJsonObject().get("payAmount").getAsFloat();
				System.out.println("balance------------"+balance+"realbalance-----------------"+realBalance);
			
			}
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}

	/**
	 * 消费操作：正常    小于1分   余额不足 负数   1分   
	 * @param description
	 * @param query
	 * @param responseStatus
	 * @param resultCode
	 * @param jobStatus
	 * @throws Exception
	 */
	@Test(priority = 2,description = "qjc member xf", dataProvider = "memberxfTest",
			dataProviderClass = qjc_CardCosumeTestData.class, groups = {"smoke", "all"})
	public void memberxfTest(String description, Map<String, String> query, 
			                   int responseStatus, int resultCode, int jobStatus)throws Exception{
		
		try{
			httpRequest = new HttpRequestEx(host, https);
			           
			Response response1 = httpRequest.post(PathForPost.getPathForqjc(),query);

			JsonObject resp = new JsonParser().parse(response1.getResponseStr()).getAsJsonObject();//将Json字符串转换成JsonObject对象
			//Assert.assertEquals(resp.get("success"), true);
			if(jobStatus==100){  //消费正常的
				Assert.assertTrue(resp.get("success").getAsBoolean());
			}else if(jobStatus==200){  //消费小于1
				Assert.assertFalse(resp.get("success").getAsBoolean());
				Assert.assertEquals(resp.get("resultCode").getAsInt(), resultCode);
				Assert.assertEquals(resp.get("message").getAsString(), "请查看根据服务接口对参数格式的要求");
			}else if(jobStatus==300){  //余额不足
				Assert.assertFalse(resp.get("success").getAsBoolean());
				Assert.assertEquals(resp.get("resultCode").getAsString(), "SYSTEM_DEFAULT_ERROR");
				Assert.assertEquals(resp.get("message").getAsString(), "您卡内的余额不足, 操作失败!");
			
			}else if(jobStatus==400){  //消费负数
				Assert.assertFalse(resp.get("success").getAsBoolean());
				Assert.assertEquals(resp.get("resultCode").getAsInt(), resultCode);
				Assert.assertEquals(resp.get("message").getAsString(), "请查看根据服务接口对参数格式的要求");
				Thread.sleep(sleeptime);   //调消费接口太快，可能会调不成功，所以这里等待下,再去消费1分钱
			}else if(jobStatus==400){  //消费1分
				Assert.assertFalse(resp.get("success").getAsBoolean());
				
			}
					
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}
	
	/**
	 * 消费后校验下金额流水返回的pay（实收额）、fee（发生额）
	 * @param description
	 * @param query
	 * @param responseStatus
	 * @param resultCode
	 * @param jobStatus
	 * @throws Exception
	 */
	@Test(priority = 3 , description = "qjc member money coumse flow", dataProvider = "memberCosumeFlowTest",
			dataProviderClass = qjc_CardCosumeTestData.class, groups = {"smoke", "all"})
	public void memberCosumeFlowTest(String description, Map<String, String> query, 
			                   int responseStatus, int resultCode, int jobStatus)throws Exception{
		
		try{
			Thread.sleep(sleeptime); //调查询金额流水接口太快，可能上一步的消费1分钱还没落地，所以这里等待下
			httpRequest = new HttpRequestEx(host, https);
			
			Response response1 = httpRequest.post(PathForPost.getPathForqjc(),query);
			System.out.println(response1);
			JsonObject resp = new JsonParser().parse(response1.getResponseStr()).getAsJsonObject();//将Json字符串转换成JsonObject对象
			//Assert.assertEquals(resp.get("success"), true);
			Assert.assertTrue(resp.get("success").getAsBoolean());			
			if(resp.get("success").getAsBoolean()){
				//校验下消费后的pay（实收额）
				Assert.assertEquals(resp.getAsJsonArray("model").get(0).getAsJsonObject().get("pay").getAsString(), "0.01");
				//校验下消费后的fee（发生额）
				Assert.assertEquals(resp.getAsJsonArray("model").get(0).getAsJsonObject().get("fee").getAsString(), "0.01");
				//校验下消费后的action（操作：支付 ，2）
				Assert.assertEquals(resp.getAsJsonArray("model").get(0).getAsJsonObject().get("action").getAsInt(), 2);
				//校验下消费30.01元后的balance(余额)
				Assert.assertEquals(resp.getAsJsonArray("model").get(0).getAsJsonObject().get("balance").getAsFloat(), (float)(Math.round((balance-30.01f)*100))/100);
			
			}
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}
	
	/**
	 * 消费后查询卡，校验下里面卡的余额（balance）、实际充值金额（realBalance）
	 * @param description
	 * @param query
	 * @param responseStatus
	 * @param resultCode
	 * @param jobStatus
	 * @throws Exception
	 */
	@Test(priority = 4 , description = "qjc membercardlist", dataProvider = "memberConsumCardListTest",
			dataProviderClass = qjc_CardCosumeTestData.class, groups = {"smoke", "all"})
	public void memberConsumCardListTest(String description, Map<String, String> query, 
			                   int responseStatus, int resultCode, int jobStatus)throws Exception{
		
		try{
			Thread.sleep(sleeptime);  //等待下，消费后查询太快，数据还没落地
			httpRequest = new HttpRequestEx(host, https);
			
			Response response1 = httpRequest.post(PathForPost.getPathForqjc(),query);
			System.out.println(response1);
			JsonObject resp = new JsonParser().parse(response1.getResponseStr()).getAsJsonObject();//将Json字符串转换成JsonObject对象
			//Assert.assertEquals(resp.get("success"), true);
			Assert.assertTrue(resp.get("success").getAsBoolean());			
			if(resp.get("success").getAsBoolean() == true){
				//校验下balance（余额）
				Assert.assertEquals(resp.getAsJsonArray("model").get(0).getAsJsonObject().get("balance").getAsFloat(), (float)(Math.round((balance-30.01f)*100))/100);
				//校验payAmount（支付累积金额）
				Assert.assertEquals(resp.getAsJsonArray("model").get(0).getAsJsonObject().get("payAmount").getAsFloat(), (float)(Math.round((payamount+30.01f)*100))/100);
				//校验下realbalance（充值累积金额）不变
				Assert.assertEquals(resp.getAsJsonArray("model").get(0).getAsJsonObject().get("realBalance").getAsFloat(), realBalance);
			System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxx"+resp.getAsJsonArray("model").get(0).getAsJsonObject().get("balance").getAsFloat());
			}
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}
	
}
