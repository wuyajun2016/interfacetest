package com.dfire.testcase.qjc.membersave;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.DataProvider;

import com.dfire.wechat.util.MD5Utils;
import com.dfire.wechat.util.SignUtils;;;

/**
 * 权金城-卡操作数据提供
 * @author Administrator
 *
 */
public class qjc_CardOperTestData {
	
	//测试
//	private static final String APP_KEY = "b6bdc8dcddeacece09d5697145d373d9";
//	private static final String APP_SECRET = "cd5624742dc63d8a83adc25844344c34";
	
	//预发
	private static final String APP_KEY = "37390d23e2f716864b18690e8709ffd7";
	private static final String APP_SECRET = "5f5fe670fb0b944e8fc52d388b837eb7";
	
	private static SimpleDateFormat cardnum = new SimpleDateFormat("yyyyMMdd"); 
	
//	private static String cardcode = "58348";   //卡号
//	private static String enid = "99932390";   //单店编号
//	private static String shopid = "99932390";   //总店编号
//	private static String phone = "15068129031";   //手机号
	
	private static String xfmoney = "3000";     //充值金额
	private static String cardcode = "0845";   //卡号
	private static String shopid = "00160541";   //总店编号
	private static String enid = "00160541";   //单店编号
	private static String phone = "15356718495";   //手机号
	private static String hcflow = String.valueOf(System.currentTimeMillis());

	
	/**
	 * 卡操作:1类型（type）不存在     2正常挂失成功   3挂失后充值  4挂失后消费  5挂失后红冲
	 * @return
	 */
	@DataProvider(name = "membercardoperTest")
	public static Object [][] membercardoperTest(){
        
		String description1 = "description: qjc membercztest action cardoper_normal_gs2";
		Map<String, Object> query1 = new HashMap<String, Object>();
		query1.put("method", "dfire.shop.member.card.status.update");
		query1.put("appKey", APP_KEY);
		query1.put("v", "1.0");
		query1.put("locale", "zh_CN");
		query1.put("timestamp", String.valueOf(System.currentTimeMillis()));
		query1.put("entityId", enid);
		query1.put("code", cardcode);
		query1.put("mobile", phone);
		query1.put("type", "2");////状态 1-启用,2-挂失,3-退卡（暂时不支持）
		query1.put("operatorName", "仙茅");
	    String sign = SignUtils.sign(query1, APP_SECRET);
	    query1.put("sign", sign);
		
	    String description2 = "description: qjc membercztest action cardoper_type4_typenotexist";
		Map<String, Object> query2 = new HashMap<String, Object>();
		query2.put("method", "dfire.shop.member.card.status.update");
		query2.put("appKey", APP_KEY);
		query2.put("v", "1.0");
		query2.put("locale", "zh_CN");
		query2.put("timestamp", String.valueOf(System.currentTimeMillis()));
		query2.put("entityId", enid);
		query2.put("code", cardcode);
		query2.put("mobile", phone);
		query2.put("type", "4");////状态 1-启用,2-挂失,3-退卡（暂时不支持）
		query2.put("operatorName", "仙茅");
	    String sign2 = SignUtils.sign(query2, APP_SECRET);
	    query2.put("sign", sign2);
	    
	    
	    String description3 = "description: qjc membergstest action cz after gs";
		Map<String, Object> query3 = new HashMap<String, Object>();
		query3.put("method", "dfire.shop.member.card.charge");
		query3.put("appKey", APP_KEY);
		query3.put("v", "1.0");
		query3.put("locale", "zh_CN");
		query3.put("timestamp", String.valueOf(System.currentTimeMillis()));
		query3.put("entityId",enid);//实体ID
		query3.put("code",cardcode);//       会员卡ID
		query3.put("mobile",phone);//moneyflowAction、moneyflowStatus、moneyflowPay这三个字段
		query3.put("pay","3000");//充值金额(单位:分)
		query3.put("payMode","3");//支付方式  1, "现金",2, "银行卡"3, "第三方支付",4, "赠送",5, "其它",6, "微信",7, "支付宝",8, "QQ支付";
		query3.put("channelType","2");//充值方式 1:实体店 2:网上商店
		query3.put("operatorName","仙茅");// 操作者姓名
		query3.put("currDate", cardnum.format(new Date()));
		query3.put("thirdPayFlowId",String.valueOf(System.currentTimeMillis()));  //流水号取时间戳
		String sign3 = SignUtils.sign(query3,APP_SECRET);
		query3.put("sign", sign3);
		
		String description4 = "description: qjc membergstest action xf after gs";
		Map<String, Object> query4 = new HashMap<String, Object>();
		query4.put("method", "dfire.shop.member.card.pay");
		query4.put("appKey", APP_KEY);
		query4.put("v", "1.0");
		query4.put("locale", "zh_CN");
		query4.put("timestamp", String.valueOf(System.currentTimeMillis()));
		query4.put("entityId", enid);
		query4.put("code", cardcode);
		query4.put("orderId", String.valueOf(System.currentTimeMillis()));
		query4.put("needPayFee", "3000");
		query4.put("originFee", "3000");
		query4.put("title", "消费测试金额30.00");
		query4.put("lastVer", "1");
		String sign4 = SignUtils.sign(query4,APP_SECRET);
		query4.put("sign", sign4);
		
		String description5 = "description: qjc membergstest action hongchong after gs";
		Map<String, Object> query5 = new HashMap<String, Object>();
		query5.put("method", "dfire.shop.member.card.cancel.charge");
		query5.put("appKey", APP_KEY);
		query5.put("v", "1.0");
		query5.put("locale", "zh_CN");
		query5.put("timestamp", String.valueOf(System.currentTimeMillis()));
		query5.put("entityId", enid);
		query5.put("code", cardcode);       /////////////////////后续要改下
		query5.put("mobile", phone);
		query5.put("thirdPayFlowId", hcflow);//2017073103   /////////////////////后续要改下
		query5.put("operatorName", "仙茅"); 
	    query5.put("currDate",cardnum.format(new Date()));   /////////////////后续要改下，要拿个对应卡号、第三方流水、充值日期存在的，不然会报参数空
	    String sign5 = SignUtils.sign(query5, APP_SECRET);
	    query5.put("sign", sign5);
	    
		
		return new Object[][]{
			//描述 查询条件 响应状态 响应码 用例名
				{description2, query2, 200, 1, 100},  //挂失类型不存在
				{description1, query1, 200, 1, 200},  //挂失正常
				{description3, query3, 200, 0, 300},  //挂失后无法充值
				{description4, query4, 200, 1, 400},  //挂失后无法消费
				{description5, query5, 200, 1000, 500},  //挂失后无法红冲

		};
	}
	
	
	
	/**
	 * 挂失后，查询操作流水
	 * @return
	 */
	@DataProvider(name = "memberOperFlowTest")
	public static Object [][] memberOperFlowTest(){
		
		String description7 = "description: qjc memberoperflowtest";
		Map<String, Object> query7 = new HashMap<String, Object>();
		query7.put("method", "dfire.shop.member.operate.list");
		query7.put("appKey", APP_KEY);
		query7.put("v", "1.0");
		query7.put("locale", "zh_CN");
		query7.put("timestamp", String.valueOf(System.currentTimeMillis()));
		query7.put("entityId", enid);
		query7.put("shopEntityId", shopid);
		query7.put("currDate", cardnum.format(new Date()));
		query7.put("code", cardcode);
		query7.put("action", "2");//1/制卡 2/挂失 3/解挂 4/注销 5/领卡 6/还卡 7/激活 8/换卡 9/关联 10/失联 11/修改密码(目前只有挂失2 7 3)		
		String sign = SignUtils.sign(query7,APP_SECRET);
		query7.put("sign", sign);
		query7.put("sessionId", "");
		
		return new Object[][]{
				{description7, query7, 200, 1, 200},

		};
	}

	/**
	 * 挂失后，查询会员卡信息
	 * @return
	 */
	@DataProvider(name = "memberCardListTest")
	public static Object [][] memberCardListTest(){
		
		String description1 = "description: qjc quashi_membercard_list search test";
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
        query1.put("sign", sign);
        query1.put("sessionId", "");
        
        
        //为了红冲准备一个充值流水号
        String description3 = "description: qjc membergstest action setup_flownum_to_hongchong";
		Map<String, Object> query3 = new HashMap<String, Object>();
		query3.put("method", "dfire.shop.member.card.charge");
		query3.put("appKey", APP_KEY);
		query3.put("v", "1.0");
		query3.put("locale", "zh_CN");
		query3.put("timestamp", String.valueOf(System.currentTimeMillis()));
		query3.put("entityId",enid);//实体ID
		query3.put("code",cardcode);//       会员卡ID
		query3.put("mobile",phone);//moneyflowAction、moneyflowStatus、moneyflowPay这三个字段
		query3.put("pay","2000");//充值金额(单位:分)
		query3.put("payMode","3");//支付方式  1, "现金",2, "银行卡"3, "第三方支付",4, "赠送",5, "其它",6, "微信",7, "支付宝",8, "QQ支付";
		query3.put("channelType","2");//充值方式 1:实体店 2:网上商店
		query3.put("operatorName","仙茅");// 操作者姓名
		query3.put("currDate", cardnum.format(new Date()));
		query3.put("thirdPayFlowId",hcflow);  //流水号取时间戳
		String sign3 = SignUtils.sign(query3,APP_SECRET);
		query3.put("sign", sign3);
		
		return new Object[][]{
				{description1, query1, 200, 1, 100}, //查询会员卡信息
				{description3, query3, 200, 1, 200},  //为红冲准备流水号,如果只是查询卡信息，这里也会报流水号重复，不会有数据进去

		};
	}
	
	
	/**
	 * 卡操作:1挂失后重启又启用  2重新启用后查询卡信息状态为启用  3重新启用后充值正常  4重新启用后消费正常
	 * @return
	 */
	@DataProvider(name = "membercardoperrestartTest")
	public static Object [][] membercardoperrestartTest(){
        
		String description1 = "description: qjc membercztest action cardoper_gs_restart";
		Map<String, Object> query1 = new HashMap<String, Object>();
		query1.put("method", "dfire.shop.member.card.status.update");
		query1.put("appKey", APP_KEY);
		query1.put("v", "1.0");
		query1.put("locale", "zh_CN");
		query1.put("timestamp", String.valueOf(System.currentTimeMillis()));
		query1.put("entityId", enid);
		query1.put("code", cardcode);
		query1.put("mobile", phone);
		query1.put("type", "1");////状态 1-启用,2-挂失,3-退卡（暂时不支持）
		query1.put("operatorName", "仙茅");
	    String sign = SignUtils.sign(query1, APP_SECRET);
	    query1.put("sign", sign);
		
	    String description2 = "description: qjc restart_membercard_list_query test";
		Map<String, Object> query2 = new HashMap<String, Object>();
		query2.put("method", "dfire.shop.member.card.list");
		query2.put("appKey", APP_KEY);
		query2.put("v", "1.0");
		query2.put("locale", "zh_CN");
		query2.put("timestamp", String.valueOf(System.currentTimeMillis()));
		query2.put("entityId", enid);
		query2.put("mobile", phone);
		query2.put("code", cardcode);
		String sign2 = SignUtils.sign(query2,APP_SECRET);
		query2.put("sign", sign2);
		query2.put("sessionId", "");
		
		String description3 = "description: qjc membergstest action cz after card_restart";
		Map<String, Object> query3 = new HashMap<String, Object>();
		query3.put("method", "dfire.shop.member.card.charge");
		query3.put("appKey", APP_KEY);
		query3.put("v", "1.0");
		query3.put("locale", "zh_CN");
		query3.put("timestamp", String.valueOf(System.currentTimeMillis()));
		query3.put("entityId",enid);//实体ID
		query3.put("code",cardcode);//       会员卡ID
		query3.put("mobile",phone);//moneyflowAction、moneyflowStatus、moneyflowPay这三个字段
		query3.put("pay","3000");//充值金额(单位:分)
		query3.put("payMode","3");//支付方式  1, "现金",2, "银行卡"3, "第三方支付",4, "赠送",5, "其它",6, "微信",7, "支付宝",8, "QQ支付";
		query3.put("channelType","2");//充值方式 1:实体店 2:网上商店
		query3.put("operatorName","仙茅");// 操作者姓名
		query3.put("currDate", cardnum.format(new Date()));
		query3.put("thirdPayFlowId",String.valueOf(System.currentTimeMillis()));  //流水号取时间戳
		String sign3 = SignUtils.sign(query3,APP_SECRET);
		query3.put("sign", sign3);
			
		String description4 = "description: qjc membergstest action xf after card_restart";
		Map<String, Object> query4 = new HashMap<String, Object>();
		query4.put("method", "dfire.shop.member.card.pay");
		query4.put("appKey", APP_KEY);
		query4.put("v", "1.0");
		query4.put("locale", "zh_CN");
		query4.put("timestamp", String.valueOf(System.currentTimeMillis()));
		query4.put("entityId", enid);
		query4.put("code", cardcode);
		query4.put("orderId", String.valueOf(System.currentTimeMillis()));
		query4.put("needPayFee", "3000");
		query4.put("originFee", "3000");
		query4.put("title", "消费测试金额30.00");
		query4.put("lastVer", "1");
		String sign4 = SignUtils.sign(query4,APP_SECRET);
		query4.put("sign", sign4);
			
		return new Object[][]{
			//描述 查询条件 响应状态 响应码 用例名
				{description1, query1, 200, 1, 100},  //挂失后重新启用
				{description2, query2, 200, 1, 200},  //挂失重新启用后，查询会员卡信息
				{description3, query3, 200, 1, 300},  //挂失重新启用后，充值正常
				{description4, query4, 200, 1, 400},  //挂失重新启用后，消费正常

		};
	}


	
}
