package com.dfire.testcase.qjc.membersave;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.dfire.wechat.util.SignUtils;


/**
 * 权金城-红冲接口-数据提供
 * @author Administrator
 *
 */
public class qjc_CardRedTestData {
	
	//测试
//	private static final String APP_KEY = "b6bdc8dcddeacece09d5697145d373d9";
//	private static final String APP_SECRET = "cd5624742dc63d8a83adc25844344c34";
	
	//预发
	private static final String APP_KEY = "37390d23e2f716864b18690e8709ffd7";
	private static final String APP_SECRET = "5f5fe670fb0b944e8fc52d388b837eb7";
	
	private static SimpleDateFormat cardnum = new SimpleDateFormat("yyyyMMdd");  //当前日期
	private static String hcflow = String.valueOf(System.currentTimeMillis());
//	private static String cardcode = "58348";   //卡号
//	private static String shopid = "99932390";  //总编号
//	private static String enid = "99932390";   //单店编号
//	private static String phone = "15068129031";   //手机号
	
	private static String xfmoney = "3000";     //充值金额
	private static String cardcode = "0845";   //卡号
	private static String shopid = "00160541";   //总店编号
	private static String enid = "00160541";   //单店编号
	private static String phone = "15356718495";   //手机号

	
	/**
	 * 为红冲做数据准备,1 查询卡信息获取余额   2创建一条充值流水
	 * @return
	 */
	@DataProvider(name = "memberCardListTest")
	public static Object [][] memberCardListTest(){
		
		String description1 = "description: qjc setupdata_getbalance_getczflownum";
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
		query3.put("pay","1000");//充值金额(单位:分)
		query3.put("payMode","3");//支付方式  1, "现金",2, "银行卡"3, "第三方支付",4, "赠送",5, "其它",6, "微信",7, "支付宝",8, "QQ支付";
		query3.put("channelType","2");//充值方式 1:实体店 2:网上商店
		query3.put("operatorName","仙茅");// 操作者姓名
		query3.put("currDate", cardnum.format(new Date()));
		query3.put("thirdPayFlowId",hcflow);  //流水号取时间戳
		String sign3 = SignUtils.sign(query3,APP_SECRET);
		query3.put("sign", sign3);
		
		return new Object[][]{
			    {description1, query1, 200, 1, 100},  //查询会员卡信息
				{description3, query3, 200, 1, 200}, //为红冲准备流水号(如果只是查询卡信息，这里也会报流水号重复，不会有数据进去)
				
		};
	}
	
	
	/**
	 * 红冲：1 正常红冲  2 重复红冲只有第一次成功
	 * @return
	 */
	@DataProvider(name = "memberhcTest")
	public static Object [][] memberhcTest(){
        
		String description1 = "description: qjc memberhctest hongchong_normal";
		Map<String, Object> query1 = new HashMap<String, Object>();
		query1.put("method", "dfire.shop.member.card.cancel.charge");
		query1.put("appKey", APP_KEY);
		query1.put("v", "1.0");
		query1.put("locale", "zh_CN");
		query1.put("timestamp", String.valueOf(System.currentTimeMillis()));
		query1.put("entityId", enid);
		query1.put("code", cardcode);
		query1.put("mobile", phone);
		query1.put("thirdPayFlowId", hcflow);  //使用前面准备的流水号
		query1.put("operatorName", "仙茅");
		query1.put("currDate",cardnum.format(new Date()));  //获取当天日期并格式成20170804
		String sign = SignUtils.sign(query1,APP_SECRET);
		query1.put("sign", sign);
        
		
		String description2 = "description: qjc memberhctest hongchong_repeat";
		Map<String, Object> query2 = new HashMap<String, Object>();
		query2.put("method", "dfire.shop.member.card.cancel.charge");
		query2.put("appKey", APP_KEY);
		query2.put("v", "1.0");
		query2.put("locale", "zh_CN");
		query2.put("timestamp", String.valueOf(System.currentTimeMillis()));
		query2.put("entityId", enid);
		query2.put("code", cardcode);
		query2.put("mobile", phone);
		query2.put("thirdPayFlowId", hcflow);  //使用前面准备的流水号
		query2.put("operatorName", "仙茅");
		query2.put("currDate",cardnum.format(new Date()));  //获取当天日期并格式成20170804
		String sign2 = SignUtils.sign(query2,APP_SECRET);
		query2.put("sign", sign2);
		
        
		return new Object[][]{
			//描述 查询条件 响应状态 响应码 用例名
				{description1, query1, 200, 1, 100},  //正常红冲
				{description2, query2, 200, 1000, 200},  //红冲重复

		};
	}
	
	
	
	/**
	 * 红冲后，查询消费金额流水
	 * @return
	 */
	@DataProvider(name = "memberHcMoneyFlowTest")
	public static Object [][] memberHcMoneyFlowTest(){
		
		String description7 = "description: qjc member_hongchong_moneyflowtest";
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
		query7.put("action", "3"); //1/充值；2/支付；3/红冲; 4/支付回充
		String sign = SignUtils.sign(query7,APP_SECRET);
        //String sign = SignUtils.sign(query1.toSingleValueMap(), APP_SECRET);
		query7.put("sign", sign);
		query7.put("sessionId", "");
		
		return new Object[][]{
				{description7, query7, 200, 1, 200},

		};
	}

	/**
	 * 红冲后，查询消费后的会员卡信息
	 * @return
	 */
	@DataProvider(name = "memberHcCardListTest")
	public static Object [][] memberHcCardListTest(){
		
		String description1 = "description: examine membercardlist after hongchong";
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
		
		return new Object[][]{
				{description1, query1, 200, 1, 200},

		};
	}

}
