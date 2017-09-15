package com.dfire.testcase.qjc.membersave;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.dfire.wechat.util.MD5Utils;
import com.dfire.wechat.util.SignUtils;;;

/**
 * 权金城-充值接口测试_数据提供
 * @author Administrator
 *
 */
public class qjc_CardReChargeTestData {
	
	//测试
//	private static final String APP_KEY = "b6bdc8dcddeacece09d5697145d373d9";
//	private static final String APP_SECRET = "cd5624742dc63d8a83adc25844344c34";
	
	//预发
	private static final String APP_KEY = "37390d23e2f716864b18690e8709ffd7";
	private static final String APP_SECRET = "5f5fe670fb0b944e8fc52d388b837eb7";
	
	private static SimpleDateFormat cardnum = new SimpleDateFormat("yyyyMMdd"); 
	
//	private static String czmoney = "3000";     //充值金额
//	private static String cardcode = "58348";   //卡号
//	private static String shopid = "99932390";   //总店编号
//	private static String enid = "99932390";   //单店编号
//	private static String phone = "15068129031";   //手机号
	
	private static String czmoney = "3000";     //充值金额
	private static String cardcode = "0845";   //卡号
	private static String shopid = "00160541";   //总店编号
	private static String enid = "00160541";   //单店编号
	private static String phone = "15356718495";   //手机号

	
	/**
	 * 卡充值正常 负数 0 流水号存在 最小1分
	 * @return
	 */
	@DataProvider(name = "memberczTest")
	public static Object [][] memberczTest(){
        
		String description2 = "description: qjc membercztest action cz_normal_payMode3";
		Map<String, Object> query2 = new HashMap<String, Object>();
		query2.put("method", "dfire.shop.member.card.charge");
		query2.put("appKey", APP_KEY);
		query2.put("v", "1.0");
		query2.put("locale", "zh_CN");
		query2.put("timestamp", String.valueOf(System.currentTimeMillis()));
		query2.put("entityId",enid);//实体ID
		query2.put("code",cardcode);//       会员卡ID
		query2.put("mobile",phone);//moneyflowAction、moneyflowStatus、moneyflowPay这三个字段
		query2.put("pay",czmoney);//充值金额(单位:分)
		query2.put("payMode","3");//支付方式  1, "现金",2, "银行卡"3, "第三方支付",4, "赠送",5, "其它",6, "微信",7, "支付宝",8, "QQ支付";
		query2.put("channelType","2");//充值方式 1:实体店 2:网上商店
		query2.put("operatorName","仙茅");// 操作者姓名
		query2.put("currDate", cardnum.format(new Date()));
		query2.put("thirdPayFlowId",String.valueOf(System.currentTimeMillis()));  //流水号取时间戳
		String sign = SignUtils.sign(query2,APP_SECRET);
        query2.put("sign", sign);
        
        String description3 = "description: qjc membercztest action cz-";
		Map<String, Object> query3 = new HashMap<String, Object>();
		query3.put("method", "dfire.shop.member.card.charge");
		query3.put("appKey", APP_KEY);
		query3.put("v", "1.0");
		query3.put("locale", "zh_CN");
		query3.put("timestamp", String.valueOf(System.currentTimeMillis()));
		query3.put("entityId",enid);//实体ID
		query3.put("code",cardcode);//       会员卡ID
		query3.put("mobile",phone);//moneyflowAction、moneyflowStatus、moneyflowPay这三个字段
		query3.put("pay","-4000");//充值金额(单位:分)
		query3.put("payMode","1");//支付方式  1, "现金",2, "银行卡"3, "第三方支付",4, "赠送",5, "其它",6, "微信",7, "支付宝",8, "QQ支付";
		query3.put("channelType","2");//充值方式 1:实体店 2:网上商店
		query3.put("operatorName","仙茅");// 操作者姓名
		query3.put("currDate", cardnum.format(new Date()));
		query3.put("thirdPayFlowId",String.valueOf(System.currentTimeMillis()));  //流水号取时间戳
		String sign2 = SignUtils.sign(query3,APP_SECRET);
        query3.put("sign", sign2);
		
        String description4 = "description: qjc membercztest action cz_flownumsame";
		Map<String, Object> query4 = new HashMap<String, Object>();
		query4.put("method", "dfire.shop.member.card.charge");
		query4.put("appKey", APP_KEY);
		query4.put("v", "1.0");
		query4.put("locale", "zh_CN");
		query4.put("timestamp", String.valueOf(System.currentTimeMillis()));
		query4.put("entityId",enid);//实体ID
		query4.put("code",cardcode);//       会员卡ID
		query4.put("mobile",phone);//moneyflowAction、moneyflowStatus、moneyflowPay这三个字段
		query4.put("pay","3000");//充值金额(单位:分)
		query4.put("payMode","3");//支付方式  1, "现金",2, "银行卡"3, "第三方支付",4, "赠送",5, "其它",6, "微信",7, "支付宝",8, "QQ支付";
		query4.put("channelType","2");//充值方式 1:实体店 2:网上商店
		query4.put("operatorName","仙茅");// 操作者姓名
		query4.put("currDate", cardnum.format(new Date()));
		query4.put("thirdPayFlowId","20170803001");  //流水号固定，为了测重复流水号
		String sign3 = SignUtils.sign(query4,APP_SECRET);
        query4.put("sign", sign3);
        
        String description5 = "description: qjc membercztest action cz_moneyis0";
		Map<String, Object> query5 = new HashMap<String, Object>();
		query5.put("method", "dfire.shop.member.card.charge");
		query5.put("appKey", APP_KEY);
		query5.put("v", "1.0");
		query5.put("locale", "zh_CN");
		query5.put("timestamp", String.valueOf(System.currentTimeMillis()));
		query5.put("entityId",enid);//实体ID
		query5.put("code",cardcode);//       会员卡ID
		query5.put("mobile",phone);//moneyflowAction、moneyflowStatus、moneyflowPay这三个字段
		query5.put("pay","0");//充值金额(单位:分)
		query5.put("payMode","3");//支付方式  1, "现金",2, "银行卡"3, "第三方支付",4, "赠送",5, "其它",6, "微信",7, "支付宝",8, "QQ支付";
		query5.put("channelType","2");//充值方式 1:实体店 2:网上商店
		query5.put("operatorName","仙茅");// 操作者姓名
		query5.put("currDate", cardnum.format(new Date()));
		query5.put("thirdPayFlowId",String.valueOf(System.currentTimeMillis()));  //流水号取时间戳
		String sign4 = SignUtils.sign(query5,APP_SECRET);
        query5.put("sign", sign4);
        
        String description6 = "description: qjc membercztest action cz_moneyis1min";
		Map<String, Object> query6 = new HashMap<String, Object>();
		query6.put("method", "dfire.shop.member.card.charge");
		query6.put("appKey", APP_KEY);
		query6.put("v", "1.0");
		query6.put("locale", "zh_CN");
		query6.put("timestamp", String.valueOf(System.currentTimeMillis()));
		query6.put("entityId",enid);//实体ID
		query6.put("code",cardcode);//       会员卡ID
		query6.put("mobile",phone);//moneyflowAction、moneyflowStatus、moneyflowPay这三个字段
		query6.put("pay","1");//充值金额(单位:分)
		query6.put("payMode","4");//支付方式  1, "现金",2, "银行卡"3, "第三方支付",4, "赠送",5, "其它",6, "微信",7, "支付宝",8, "QQ支付";
		query6.put("channelType","2");//充值方式 1:实体店 2:网上商店
		query6.put("operatorName","仙茅");// 操作者姓名
		query6.put("currDate", cardnum.format(new Date()));
		query6.put("thirdPayFlowId",System.currentTimeMillis()+"8");  //流水号取时间戳
		String sign5 = SignUtils.sign(query6,APP_SECRET);
        query6.put("sign", sign5);
        
        
		return new Object[][]{
			//描述 查询条件 响应状态 响应码 用例名
				{description2, query2, 200, 1, 200},  //充值金额30 类型3
				{description3, query3, 200, 33, 300},  //负数
				{description4, query4, 200, 46, 400},  //流水号唯一
				{description5, query5, 200, 33, 500},  //金额为0
				{description6, query6, 200, 1, 600},  //金额为1分,为赠送金额.看下总额会加上去

		};
	}
	
	
	
	/**
	 * 查询充值金额流水
	 * @return
	 */
	@DataProvider(name = "memberMoneyFlowTest")
	public static Object [][] memberMoneyFlowTest(){
		
		String description7 = "description: qjc membermoneyflowtest";
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
		query7.put("action", "1"); //1/充值；2/支付；3/红冲; 4/支付回充
		String sign = SignUtils.sign(query7,APP_SECRET);
        //String sign = SignUtils.sign(query1.toSingleValueMap(), APP_SECRET);
		query7.put("sign", sign);
		query7.put("sessionId", "");
		
		return new Object[][]{
				{description7, query7, 200, 1, 200},

		};
	}

	/**
	 * 查询充值后的会员卡信息
	 * @return
	 */
	@DataProvider(name = "memberCardListTest")
	public static Object [][] memberCardListTest(){
		
		String description1 = "description: qjc membercard_list test";
		Map<String, Object> query1 = new HashMap<String, Object>();
		//MultiValueMap<String, String> query1 = new LinkedMultiValueMap<String, String>();
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
				{description1, query1, 200, 1, 200},

		};
	}

}
