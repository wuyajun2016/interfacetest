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
 * 权金城-卡操作接口
 * @author Administrator
 *
 */
public class qjc_CardOperTest {
	
	private HttpRequestEx httpRequest;
	private static final Logger logger = Logger.getLogger(qjc_CardOperTest.class);
	
//	private String host = CommonConstants.DEFAULT_HOST;
//	private String host = "10.1.25.146:8080";  //测试
//	private String host = "open.2dfire-pre.com";  //预发
	private String host = "open.2dfire.com";  //线上
	private boolean https = CommonConstants.HTTPS;
	private float balance;  //余额
	private int sleeptime = 8000;    //等待时间,部分接口跑太快数据未落地
	
//	@BeforeClass(alwaysRun = true)
//	public void setup(){
//		
//	}
	/**
	 * 数据准备，不操作数据库只能这么干了。。。 操作前读取下余额
	 * @param description
	 * @param query
	 * @param responseStatus
	 * @param resultCode
	 * @param jobStatus
	 * @throws Exception
	 */
	@Test(priority = 1 , description = "qjc membercardlist_datasetup", dataProvider = "memberCardListTest",
			dataProviderClass = qjc_CardOperTestData.class, groups = {"smoke", "all"})
	public void getPayAndFee(String description, Map<String, String> query, 
			                   int responseStatus, int resultCode, int jobStatus)throws Exception{
		
		try{
			System.out.println("----------------------开始跑卡操作接口-----------------------------");
			httpRequest = new HttpRequestEx(host, https);
			Thread.sleep(sleeptime);//----xianshang
			Response response1 = httpRequest.post(PathForPost.getPathForqjc(),query);
			System.out.println(response1);
			JsonObject resp = new JsonParser().parse(response1.getResponseStr()).getAsJsonObject();//将Json字符串转换成JsonObject对象
					
			if(jobStatus==100){
				Assert.assertTrue(resp.get("success").getAsBoolean());	
				balance = resp.getAsJsonArray("model").get(0).getAsJsonObject().get("balance").getAsFloat();
				System.out.println("balance-----------------"+balance);
			}else if(jobStatus==200){
				Assert.assertTrue(resp.get("success").getAsBoolean());
				System.out.println("充值成功了吗？？？？？？？？？？"+resp.get("success").getAsBoolean());
				Thread.sleep(sleeptime); //保证充值落地，不然后面红冲查询不到流水
			}
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}

	/**
	 * 卡操作:1类型（type）不存在     2正常挂失成功   3挂失后充值  4挂失后消费  5挂失后红冲
	 * @param description
	 * @param query
	 * @param responseStatus
	 * @param resultCode
	 * @param jobStatus
	 * @throws Exception
	 */
	@Test(priority = 2,description = "qjc member card_operter", dataProvider = "membercardoperTest",
			dataProviderClass = qjc_CardOperTestData.class, groups = {"smoke", "all"})
	public void membercardoperTest(String description, Map<String, String> query, 
			                   int responseStatus, int resultCode, int jobStatus)throws Exception{
		
		try{
			httpRequest = new HttpRequestEx(host, https);
			           
			Response response1 = httpRequest.post(PathForPost.getPathForqjc(),query);
			JsonObject resp = new JsonParser().parse(response1.getResponseStr()).getAsJsonObject();//将Json字符串转换成JsonObject对象
			//Assert.assertEquals(resp.get("success"), true);
			if(jobStatus==100){  //挂失类型不存在
				Assert.assertFalse(resp.get("success").getAsBoolean());
				Assert.assertEquals(resp.get("message").getAsString(), "卡操作类型不正确");
			}else if(jobStatus==200){  //挂失正常
				Assert.assertTrue(resp.get("success").getAsBoolean());
			}
			else if(jobStatus==300){  //挂失充值
				Assert.assertFalse(resp.get("success").getAsBoolean());
				Assert.assertEquals(resp.get("resultCode").getAsInt(), resultCode);
				Assert.assertEquals(resp.get("message").getAsString(), "会员卡不是正常状态,不允许充值");
			}else if(jobStatus==400){  //挂失后消费
				Assert.assertFalse(resp.get("success").getAsBoolean());
				Assert.assertEquals(resp.get("resultCode").getAsString(), "SYSTEM_DEFAULT_ERROR");
				Assert.assertEquals(resp.get("message").getAsString(), "当前会员卡不是正常状态, 不能使用!");
				 
			}else if(jobStatus==500){  //挂失后红冲
				Assert.assertFalse(resp.get("success").getAsBoolean());
				Assert.assertEquals(resp.get("resultCode").getAsInt(), resultCode);
				Assert.assertEquals(resp.get("message").getAsString(), "当前会员卡不是正常状态, 不能使用!");
			}
						
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}
	
	/**
	 * 挂失后查询操作流水
	 * @param description
	 * @param query
	 * @param responseStatus
	 * @param resultCode
	 * @param jobStatus
	 * @throws Exception
	 */
	@Test(priority = 3 , description = "qjc member oper flow", dataProvider = "memberOperFlowTest",
			dataProviderClass = qjc_CardOperTestData.class, groups = {"smoke", "all"})
	public void memberOperFlowTest(String description, Map<String, String> query, 
			                   int responseStatus, int resultCode, int jobStatus)throws Exception{
		
		try{
			Thread.sleep(sleeptime); 
			httpRequest = new HttpRequestEx(host, https);
			
			Response response1 = httpRequest.post(PathForPost.getPathForqjc(),query);
			System.out.println(response1);
			JsonObject resp = new JsonParser().parse(response1.getResponseStr()).getAsJsonObject();//将Json字符串转换成JsonObject对象
			//Assert.assertEquals(resp.get("success"), true);
			Assert.assertTrue(resp.get("success").getAsBoolean());			
			if(resp.get("success").getAsBoolean() == true){
				//校验下action(操作方式为支付)
				Assert.assertEquals(resp.getAsJsonArray("model").get(0).getAsJsonObject().get("action").getAsString(), "2");
				//校验下卡状态为1（已领用）
				Assert.assertEquals(resp.getAsJsonArray("model").get(0).getAsJsonObject().get("status").getAsString(), "1");
			
			}
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}
	
	/**
	 * 挂失后查询卡信息，校验下里面卡的余额（balance）
	 * @param description
	 * @param query
	 * @param responseStatus
	 * @param resultCode
	 * @param jobStatus
	 * @throws Exception
	 */
	@Test(priority = 4 , description = "qjc membercardlist", dataProvider = "memberCardListTest",
			dataProviderClass = qjc_CardOperTestData.class, groups = {"smoke", "all"})
	public void memberCardListTest(String description, Map<String, String> query, 
			                   int responseStatus, int resultCode, int jobStatus)throws Exception{
		
		try{
			Thread.sleep(sleeptime);  //等待下，充值后查询太快，数据还没落地
			httpRequest = new HttpRequestEx(host, https);
			
			Response response1 = httpRequest.post(PathForPost.getPathForqjc(),query);
			System.out.println(response1);
			JsonObject resp = new JsonParser().parse(response1.getResponseStr()).getAsJsonObject();//将Json字符串转换成JsonObject对象
			//Assert.assertEquals(resp.get("success"), true);
			if(jobStatus==100){
				//判断是否返回成功
				Assert.assertTrue(resp.get("success").getAsBoolean());
				//校验下余额
				Assert.assertEquals(resp.getAsJsonArray("model").get(0).getAsJsonObject().get("balance").getAsFloat(), (float)(Math.round((balance+20.00f)*100))/100);
			}else if(jobStatus==200){
				//啥都不做，里面还有一条充值用例是为了创建初始数据.也不会创建成功，因为流水唯一了
				Assert.assertEquals(resp.get("resultCode").getAsString(), "046");
				Assert.assertEquals(resp.get("message").getAsString(), "该交易流水号已存在");
			}
		
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}
	
	/**
	 * 挂失后重新启用:1 启用 2查卡信息 3充值 4消费
	 * @param description
	 * @param query
	 * @param responseStatus
	 * @param resultCode
	 * @param jobStatus
	 * @throws Exception
	 */
	@Test(priority = 5 , description = "qjc card_guashi restart", dataProvider = "membercardoperrestartTest",
			dataProviderClass = qjc_CardOperTestData.class, groups = {"smoke", "all"})
	public void membercardoperrestartTest(String description, Map<String, String> query, 
			                   int responseStatus, int resultCode, int jobStatus)throws Exception{
		
		try{
			Thread.sleep(sleeptime);  //等待下
			httpRequest = new HttpRequestEx(host, https);
			
			Response response1 = httpRequest.post(PathForPost.getPathForqjc(),query);
			System.out.println(response1);
			JsonObject resp = new JsonParser().parse(response1.getResponseStr()).getAsJsonObject();//将Json字符串转换成JsonObject对象
			//Assert.assertEquals(resp.get("success"), true);
			Assert.assertTrue(resp.get("success").getAsBoolean());			
			if(jobStatus==100){  //挂失后重新启用
				Assert.assertTrue(resp.get("success").getAsBoolean());
				
			}else if(jobStatus==200){  //挂失后重新启用，查询卡信息,余额正确
				Assert.assertTrue(resp.get("success").getAsBoolean());
				//校验下余额
				Assert.assertEquals(resp.getAsJsonArray("model").get(0).getAsJsonObject().get("balance").getAsFloat(), (float)(Math.round((balance+20.00f)*100))/100);
	
			}else if(jobStatus==300){  //挂失后重新启用，充值30元
				Assert.assertTrue(resp.get("success").getAsBoolean());
			}
			else if(jobStatus==400){  //挂失后重新启用，消费30元
			
				Assert.assertTrue(resp.get("success").getAsBoolean());
			}
			
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}
}
