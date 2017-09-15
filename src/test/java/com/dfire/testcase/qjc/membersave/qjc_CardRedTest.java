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
 * 权金城-红冲接口
 * @author Administrator
 *
 */
public class qjc_CardRedTest {
	
	private HttpRequestEx httpRequest;
	private static final Logger logger = Logger.getLogger(qjc_CardRedTest.class);
	
//	private String host = CommonConstants.DEFAULT_HOST;
//	private String host = "10.1.25.146:8080";  //测试
//	private String host = "open.2dfire-pre.com";  //预发
	private String host = "open.2dfire.com";  //线上
	private boolean https = CommonConstants.HTTPS;
	private float balance;  //余额
	private float realBalance; //累积充值金额
	private float payamount; //支付累积
	private float consumeAmount; //消费累积
	private int sleeptime = 8000;    //等待时间,部分接口跑太快数据未落地
	
//	@BeforeClass(alwaysRun = true)
//	public void setup(){
//		
//	}
	/**
	 * 数据准备，不操作数据库只能这么干了。。。1 获取余额   2充值一笔，为了准备好红冲流水号
	 * @param description
	 * @param query
	 * @param responseStatus
	 * @param resultCode
	 * @param jobStatus
	 * @throws Exception
	 */
	@Test(priority = 1 , description = "qjc memberhccardlist_data_setup", dataProvider = "memberCardListTest",
			dataProviderClass = qjc_CardRedTestData.class, groups = {"smoke", "all"})
	public void getPayAndFee(String description, Map<String, String> query, 
			                   int responseStatus, int resultCode, int jobStatus)throws Exception{
		
		try{
			System.out.println("----------------------开始跑红冲接口-----------------------------");
			httpRequest = new HttpRequestEx(host, https);
			Thread.sleep(sleeptime);//----xianshang
			Response response1 = httpRequest.post(PathForPost.getPathForqjc(),query);
			System.out.println(response1);
			JsonObject resp = new JsonParser().parse(response1.getResponseStr()).getAsJsonObject();//将Json字符串转换成JsonObject对象
			if(jobStatus==100){   //数据准备，跑用例前先查询先卡信息保存下余额(先充值准备红冲流水号是为了后面校验确定红冲了金额变少了)
				Assert.assertTrue(resp.get("success").getAsBoolean());	
				balance = resp.getAsJsonArray("model").get(0).getAsJsonObject().get("balance").getAsFloat();
				//充值累积
				realBalance = resp.getAsJsonArray("model").get(0).getAsJsonObject().get("realBalance").getAsFloat();
				//支付累积
				payamount = resp.getAsJsonArray("model").get(0).getAsJsonObject().get("payAmount").getAsFloat();
				//消费累积
				consumeAmount = resp.getAsJsonArray("model").get(0).getAsJsonObject().get("consumeAmount").getAsFloat();
				System.out.println("balance------------------"+balance);
				
			}else if(jobStatus==200){  //准备红冲流水号
				Assert.assertTrue(resp.get("success").getAsBoolean());
				
			}
			
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}

	/**
	 * 红冲操作：1 正常红冲  2 重复红冲只有第一次成功   
	 * @param description
	 * @param query
	 * @param responseStatus
	 * @param resultCode
	 * @param jobStatus
	 * @throws Exception
	 */
	@Test(priority = 2,description = "qjc member hf", dataProvider = "memberhcTest",
			dataProviderClass = qjc_CardRedTestData.class, groups = {"smoke", "all"})
	public void memberhcTest(String description, Map<String, String> query, 
			                   int responseStatus, int resultCode, int jobStatus)throws Exception{
		
		try{
			httpRequest = new HttpRequestEx(host, https);
			Thread.sleep(sleeptime);  //等待前面充值数据落地           
			Response response1 = httpRequest.post(PathForPost.getPathForqjc(),query);

			JsonObject resp = new JsonParser().parse(response1.getResponseStr()).getAsJsonObject();//将Json字符串转换成JsonObject对象
			//Assert.assertEquals(resp.get("success"), true);
			if(jobStatus==100){  //正常红冲
				Assert.assertTrue(resp.get("success").getAsBoolean());
			}else if(jobStatus==200){  //红冲重复
				Assert.assertFalse(resp.get("success").getAsBoolean());
				Assert.assertEquals(resp.get("resultCode").getAsInt(), resultCode);
				Assert.assertEquals(resp.get("message").getAsString(), "该记录已被红冲!");
			}
					
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}
	
	/**
	 * 红冲后校验下金额流水
	 * @param description
	 * @param query
	 * @param responseStatus
	 * @param resultCode
	 * @param jobStatus
	 * @throws Exception
	 */
	@Test(priority = 3 , description = "qjc examine moneyflow after hongchong", dataProvider = "memberHcMoneyFlowTest",
			dataProviderClass = qjc_CardRedTestData.class, groups = {"smoke", "all"})
	public void memberHcMoneyFlowTest(String description, Map<String, String> query, 
			                   int responseStatus, int resultCode, int jobStatus)throws Exception{
		
		try{
			Thread.sleep(sleeptime); //调查询金额流水接口太快，可能上一步的红冲操作还没落地，所以这里等待下
			httpRequest = new HttpRequestEx(host, https);
			
			Response response1 = httpRequest.post(PathForPost.getPathForqjc(),query);
			System.out.println(response1);
			JsonObject resp = new JsonParser().parse(response1.getResponseStr()).getAsJsonObject();//将Json字符串转换成JsonObject对象
			//Assert.assertEquals(resp.get("success"), true);
			Assert.assertTrue(resp.get("success").getAsBoolean());			
			if(resp.get("success").getAsBoolean()){
				//校验下红冲后的pay（实收额）
				Assert.assertEquals(resp.getAsJsonArray("model").get(0).getAsJsonObject().get("pay").getAsString(), "10.00");
				//校验下红冲后的fee（发生额）
				Assert.assertEquals(resp.getAsJsonArray("model").get(0).getAsJsonObject().get("fee").getAsString(), "10.00");
				//校验下红冲后的action（操作：红冲，3）
				Assert.assertEquals(resp.getAsJsonArray("model").get(0).getAsJsonObject().get("action").getAsInt(), 3);
				//校验下红冲后的balance(余额)依然是最初setup中获取的余额值
				Assert.assertEquals(resp.getAsJsonArray("model").get(0).getAsJsonObject().get("balance").getAsFloat(), balance);
			
			}
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}
	
	/**
	 * 红冲后查询卡信息，校验下里面卡的余额（balance）、充值累积（realBalance）、支付累积（payAmount）、消费累积（consumeAmount）不变
	 * @param description
	 * @param query
	 * @param responseStatus
	 * @param resultCode
	 * @param jobStatus
	 * @throws Exception
	 */
	@Test(priority = 4 , description = "qjc membercardlist", dataProvider = "memberHcCardListTest",
			dataProviderClass = qjc_CardRedTestData.class, groups = {"smoke", "all"})
	public void memberHcCardListTest(String description, Map<String, String> query, 
			                   int responseStatus, int resultCode, int jobStatus)throws Exception{
		
		try{
//			Thread.sleep(sleeptime);  //等待下
			httpRequest = new HttpRequestEx(host, https);
			
			Response response1 = httpRequest.post(PathForPost.getPathForqjc(),query);
			System.out.println(response1);
			JsonObject resp = new JsonParser().parse(response1.getResponseStr()).getAsJsonObject();//将Json字符串转换成JsonObject对象
			Assert.assertTrue(resp.get("success").getAsBoolean());			
			if(resp.get("success").getAsBoolean()){
				//校验下balance（余额）
				Assert.assertEquals(resp.getAsJsonArray("model").get(0).getAsJsonObject().get("balance").getAsFloat(), balance);
				//校验payAmount（支付累积金额）
				Assert.assertEquals(resp.getAsJsonArray("model").get(0).getAsJsonObject().get("payAmount").getAsFloat(), payamount);
				//校验下realbalance（充值累积金额）不变
				Assert.assertEquals(resp.getAsJsonArray("model").get(0).getAsJsonObject().get("realBalance").getAsFloat(), realBalance);
				//校验下consumeAmount（消费累积金额）不变
				Assert.assertEquals(resp.getAsJsonArray("model").get(0).getAsJsonObject().get("consumeAmount").getAsFloat(), consumeAmount);
			}
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}
	
}
