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
public class qjc_CardCosumeTestData {
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
        
		
		String description2 = "description: qjc memberxftest xf_less1";
		Map<String, Object> query2 = new HashMap<String, Object>();
		query2.put("method", "dfire.shop.member.card.pay");
		query2.put("appKey", APP_KEY);
		query2.put("v", "1.0");
		query2.put("locale", "zh_CN");
		query2.put("timestamp", String.valueOf(System.currentTimeMillis()));
		query2.put("entityId", enid);
		query2.put("code", cardcode);
		query2.put("orderId", String.valueOf(System.currentTimeMillis()));
		query2.put("needPayFee", "0.1");
		query2.put("originFee", "0.1");
		query2.put("title", "消费小于1分");
		query2.put("lastVer", "1");
		String sign2 = SignUtils.sign(query2,APP_SECRET);
		query2.put("sign", sign2);
		
		String description3 = "description: qjc memberxftest xf_moneynotenough";
		Map<String, Object> query3 = new HashMap<String, Object>();
		query3.put("method", "dfire.shop.member.card.pay");
		query3.put("appKey", APP_KEY);
		query3.put("v", "1.0");
		query3.put("locale", "zh_CN");
		query3.put("timestamp", String.valueOf(System.currentTimeMillis()));
		query3.put("entityId", enid);
		query3.put("code", cardcode);
		query3.put("orderId", String.valueOf(System.currentTimeMillis()));
		query3.put("needPayFee", "999999900");
		query3.put("originFee", "999999900");
		query3.put("title", "消费时余额不足");
		query3.put("lastVer", "1");
		String sign3 = SignUtils.sign(query3,APP_SECRET);
		query3.put("sign", sign3);
		
		String description5 = "description: qjc memberxftest xf_money-1000";
		Map<String, Object> query5 = new HashMap<String, Object>();
		query5.put("method", "dfire.shop.member.card.pay");
		query5.put("appKey", APP_KEY);
		query5.put("v", "1.0");
		query5.put("locale", "zh_CN");
		query5.put("timestamp", String.valueOf(System.currentTimeMillis()));
		query5.put("entityId", enid);
		query5.put("code", cardcode);
		query5.put("orderId", String.valueOf(System.currentTimeMillis()));
		query5.put("needPayFee", "-1000");
		query5.put("originFee", "-1000");
		query5.put("title", "消费负数");
		query5.put("lastVer", "1");
		String sign5 = SignUtils.sign(query5,APP_SECRET);
		query5.put("sign", sign5);
        
		String description4 = "description: qjc memberxftest xf_normal_1";
		Map<String, Object> query4 = new HashMap<String, Object>();
		query4.put("method", "dfire.shop.member.card.pay");
		query4.put("appKey", APP_KEY);
		query4.put("v", "1.0");
		query4.put("locale", "zh_CN");
		query4.put("timestamp", String.valueOf(System.currentTimeMillis()));
		query4.put("entityId", enid);
		query4.put("code", cardcode);
		query4.put("orderId", String.valueOf(System.currentTimeMillis()));
		query4.put("needPayFee", "1");
		query4.put("originFee", "1");
		query4.put("title", "消费1分");
		query4.put("lastVer", "1");
		String sign4 = SignUtils.sign(query4,APP_SECRET);
		query4.put("sign", sign4);
        
		return new Object[][]{
			//描述 查询条件 响应状态 响应码 用例名
				{description1, query1, 200, 1, 100},  //消费30
				{description2, query2, 200, 33, 200},  //消费小于1分
				{description3, query3, 200, 1, 300},  //消费余额不足
				{description5, query5, 200, 33, 400},  //消费负数
				{description4, query4, 200, 33, 500},  //消费1分

		};
	}
	
	
	
	/**
	 * 查询消费金额流水
	 * @return
	 */
	@DataProvider(name = "memberCosumeFlowTest")
	public static Object [][] memberCosumeFlowTest(){
		
		String description7 = "description: qjc membercousmeflowtest";
		Map<String, Object> query7 = new HashMap<String, Object>();
		query7.put("method", "dfire.shop.member.moneyflow.list");
		query7.put("appKey", APP_KEY);
		query7.put("v", "1.0");
		query7.put("locale", "zh_CN");
		query7.put("timestamp", String.valueOf(System.currentTimeMillis()));
		query7.put("entityId", enid);
		query7.put("shopEntityId", shopid);
		query7.put("currDate", cardnum.format(new Date()));
		query7.put("code", cardcode);
		query7.put("action", "2"); //1/充值；2/支付；3/红冲; 4/支付回充
		String sign = SignUtils.sign(query7,APP_SECRET);
        //String sign = SignUtils.sign(query1.toSingleValueMap(), APP_SECRET);
		query7.put("sign", sign);
		query7.put("sessionId", "");
		
		return new Object[][]{
				{description7, query7, 200, 1, 200},

		};
	}

	/**
	 * 查询消费后的会员卡信息
	 * @return
	 */
	@DataProvider(name = "memberConsumCardListTest")
	public static Object [][] memberConsumCardListTest(){
		
		String description1 = "description: qjc membercard_cosume_list test";
		Map<String, Object> query1 = new HashMap<String, Object>();
		query1.put("method", "dfire.shop.member.card.list");
		query1.put("appKey", APP_KEY);
		query1.put("v", "1.0");
		query1.put("locale", "zh_CN");
		query1.put("timestamp", String.valueOf(System.currentTimeMillis()));
		query1.put("entityId", enid);
		query1.put("mobile", phone);
		query1.put("code", cardcode);
		String sign = SignUtils.sign(query1,APP_SECRET);
        //String sign = SignUtils.sign(query1.toSingleValueMap(), APP_SECRET);
        query1.put("sign", sign);
        query1.put("sessionId", "");
		
		return new Object[][]{
				{description1, query1, 200, 1, 200,"abc"},

		};
	}

}
