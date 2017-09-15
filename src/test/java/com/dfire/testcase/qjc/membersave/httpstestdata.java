package com.dfire.testcase.qjc.membersave;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.dfire.wechat.util.SignUtils;


/**
 * 权金城-消费接口-数据提供
 * @author Administrator
 *
 */
public class httpstestdata {
	//测试
//	private static final String APP_KEY = "b6bdc8dcddeacece09d5697145d373d9";
//	private static final String APP_SECRET = "cd5624742dc63d8a83adc25844344c34";
	
	//预发
	private static final String APP_KEY = "37390d23e2f716864b18690e8709ffd7";
	private static final String APP_SECRET = "5f5fe670fb0b944e8fc52d388b837eb7";
	
	private static SimpleDateFormat cardnum = new SimpleDateFormat("yyyyMMdd");  //当前日期
	
//	private static String xfmoney = "3000";     //充值金额
//	private static String cardcode = "58348";   //卡号
//	private static String shopid = "99932390";   //总店编号
//	private static String enid = "99932390";   //单店编号
//	private static String phone = "15068129031";   //手机号
	
	private static String xfmoney = "3000";     //充值金额
	private static String cardcode = "0845";   //卡号
	private static String shopid = "00160541";   //总店编号
	private static String enid = "00160541";   //单店编号
	private static String phone = "15356718495";   //手机号

	
	/**
	 * 消费：正常    小于1分   余额不足   负数   1分   
	 * @return
	 */
	@DataProvider(name = "memberxfTest")
	public static Object [][] memberxfTest(){
        
		String description1 = "description: qjc memberxftest xf_normal_3000";
		Map<String, Object> query1 = new HashMap<String, Object>();
		Map<String,String> data111 = new HashMap<String,String>();
		query1.put("method", "dfire.shop.member.card.pay");
		query1.put("appKey", APP_KEY);
		query1.put("v", "1.0");
		query1.put("locale", "zh_CN");
		query1.put("timestamp", String.valueOf(System.currentTimeMillis()));
		query1.put("entityId", enid);
		query1.put("code", cardcode);
		query1.put("orderId", String.valueOf(System.currentTimeMillis()));
		query1.put("needPayFee", xfmoney);
		query1.put("originFee", xfmoney);
		query1.put("title", "消费测试金额30.00");
		query1.put("lastVer", "1");
		String sign = SignUtils.sign(query1,APP_SECRET);
		query1.put("sign", sign);
      
		data111.put("msg1", "交易成功");
		data111.put("msg2", "交易失败");
		return new Object[][]{
			//描述 查询条件 响应状态 响应码 用例名(不同入参不同的数字) 响应文案
				{description1, query1, 200, 1, 100,data111},  //消费30

		};
	}
	
	
	

}
