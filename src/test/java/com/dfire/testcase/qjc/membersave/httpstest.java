package com.dfire.testcase.qjc.membersave;

import static org.testng.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.dfire.wechat.db.WeChatUtils;
import com.dfire.wechat.util.CommonConstants;
import com.dfire.wechat.util.HttpRequestEx;
import com.dfire.wechat.util.PathForPost;
import com.dfire.wechat.util.Response;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * test
 * @author Administrator
 *
 */
public class httpstest {
	
	private HttpRequestEx httpRequest;
	private static final Logger logger = Logger.getLogger(httpstest.class);
	private boolean https = CommonConstants.HTTPS;
	private String host = "www.kuaidi100.com"; 

	
	@BeforeClass(alwaysRun = true)
	public void setup(){
//		WeChatUtils.insertMenuIntoDB("", "", "");  //操作数据库数据准备
		System.out.println("------------------初始化数据");
	}
	
	@Test(priority = 1 , description = "qjc membercosumcardlist_data_setup", dataProvider = "memberxfTest",
			dataProviderClass = httpstestdata.class, groups = {"smoke", "all"})
	public void getPayAndFee(String description, Map<String, String> query, 
			                   int responseStatus, int resultCode, int jobStatus,Map<String,String> abc)throws Exception{
		
		try{
			Map<String,String> query123 = new HashMap<String,String>(); 
			Map<String,String> testquery = new HashMap<String,String>();
			testquery.put("type", "shunfeng");
			testquery.put("postid", "615219134887");
			httpRequest = new HttpRequestEx(host, https);
			System.out.println("11111111111111111111"+abc.get("msg1"));
			Response response1 = httpRequest.postWithHeaderandHttps(PathForPost.getPathFortest(),query123,testquery);
			JsonObject resp = new JsonParser().parse(response1.getResponseStr()).getAsJsonObject();//将Json字符串转换成JsonObject对象
			System.out.println("返回为--------------------"+resp);
			System.out.println("返回为2--------------------"+resp.getAsJsonArray("data").get(0).getAsJsonObject().get("context"));
			System.out.println(abc);
			assertEquals(resp.getAsJsonArray("data").get(0).getAsJsonObject().get("context").getAsString(), "交易成功");
		}catch(Exception e){
			logger.info(e.toString());
		}
		
	}

	
}
