package com.dfire.testcase.qjc.membersave;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nutz.json.Json;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.DataProvider;

import com.alibaba.fastjson.JSON;
import com.dfire.wechat.util.MD5Utils;
import com.dfire.wechat.util.SignUtils;
import com.twodfire.util.JsonUtil;

import net.sf.json.JSONObject;

/**
 * 权金城-保存卡信息数据提供
 * @author Administrator
 *
 */
public class qjc_CreateCardTestData {
	
	//测试
	private static final String APP_KEY = "b6bdc8dcddeacece09d5697145d373d9";
	private static final String APP_SECRET = "cd5624742dc63d8a83adc25844344c34";
	
	//预发、线上一样
//	private static final String APP_KEY = "37390d23e2f716864b18690e8709ffd7";
//	private static final String APP_SECRET = "5f5fe670fb0b944e8fc52d388b837eb7";
//	
	

	//测试
	private static String enid = "99932390";   //单店编号
	private static String shopid = "99932390";   //总店编号
	private static String phone = "15068129031";   //手机号
	
	//线上
//	private static String xfmoney = "3000";     //充值金额
//	private static String shopid = "00160541";   //总店编号
//	private static String enid = "00160541";   //单店编号
//	private static String phone = "15356718495";   //手机号
	
	private static String cardListJson;   //转换成string的javabean
	private static String cardcode = String.valueOf(System.currentTimeMillis()); //保存卡的时候每次自动生成卡号
	private static SimpleDateFormat cardnum = new SimpleDateFormat("yyyyMMdd");   //用作日期生成
	
	
	/**
	 * 保存卡信息：1金额负数  2source不存在   3正常创建   4重复创建
	 * @return
	 */
	@DataProvider(name = "membercardsaveTest")
	public static Object [][] membercardsaveTest(){
		
		List<String> applyShopEntityIdList = new ArrayList<>();
		String description1 = "description: qjc membercardsavetest money(basebalance) is -1000";
	 
		qjcCardListBean vo = new qjcCardListBean();
		List<qjcCardListBean> cardList1=new ArrayList<>();
		Map<String, Object> query1 = new HashMap<String, Object>();
		query1.put("method", "dfire.member.save");
		query1.put("appKey", APP_KEY);
		query1.put("v", "1.0");
		query1.put("locale", "zh_CN");
		query1.put("timestamp", String.valueOf(System.currentTimeMillis()));
		query1.put("entityId", enid);//连锁entityId,单店entityId
		vo.setSource(3);
		vo.setKindCardName("卡型"+cardcode);
		vo.setCode(cardcode);
		vo.setInnerCode(cardcode);
		vo.setBalance(1000L);
		vo.setBaseBalance(-1000L);  //金额为负
		vo.setGiveBalance(1000L);
		vo.setGiftBalance(1000L);
		vo.setRealBalance(1000L);
		vo.setDegree(800);
		vo.setPayAmount(1000L);
		vo.setRatioAmount(1000L);
		vo.setPay(1000L);
		vo.setMobile(phone);
		vo.setCountryCode("32551");
		cardList1.add(vo);
		try {
			cardListJson = JsonUtil.beanToJson(cardList1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		query1.put("cardList", cardListJson);
		String sign1 = SignUtils.sign(query1, APP_SECRET);
		query1.put("sign", sign1);
		Map<String,String> query1_new =new HashMap<String,String>();
		for (Map.Entry<String, Object> entry : query1.entrySet()) {
		       if(entry.getValue() instanceof String){
		    	   query1_new.put(entry.getKey(), (String) entry.getValue());
		          }
		 }
		
		
	    String description2 = "description: qjc membercardsavetest source is 4(not exist)";
	    qjcCardListBean vo2 = new qjcCardListBean();
		List<qjcCardListBean> cardList2=new ArrayList<>();
		Map<String, Object> query2 = new HashMap<String, Object>();
		query2.put("method", "dfire.member.save");
		query2.put("appKey", APP_KEY);
		query2.put("v", "1.0");
		query2.put("locale", "zh_CN");
		query2.put("timestamp", String.valueOf(System.currentTimeMillis()));
		query2.put("entityId", enid);//连锁entityId,单店entityId
		vo2.setSource(4);  //类型不存在
		vo2.setKindCardName("卡型"+cardcode);
		vo2.setCode(cardcode);
		vo2.setInnerCode(cardcode);
		vo2.setBalance(1000L);
		vo2.setBaseBalance(1000L);  
		vo2.setGiveBalance(1000L);
		vo2.setGiftBalance(1000L);
		vo2.setRealBalance(1000L);
		vo2.setDegree(800);
		vo2.setPayAmount(1000L);
		vo2.setRatioAmount(1000L);
		vo2.setPay(1000L);
		vo2.setMobile(phone);
		vo2.setCountryCode("32551");
		cardList2.add(vo2);
		try {
			cardListJson = JsonUtil.beanToJson(cardList2);
		} catch (Exception e) {
			e.printStackTrace();
		}
		query2.put("cardList", cardListJson);
		String sign2 = SignUtils.sign(query2, APP_SECRET);
		query2.put("sign", sign2);
		Map<String,String> query2_new =new HashMap<String,String>();
		for (Map.Entry<String, Object> entry : query2.entrySet()) {
		       if(entry.getValue() instanceof String){
		    	   query2_new.put(entry.getKey(), (String) entry.getValue());
		          }
		 }
	    
	    String description3 = "description: qjc membercardsavetest successful";
	    qjcCardListBean vo3 = new qjcCardListBean();
		List<qjcCardListBean> cardList3=new ArrayList<>();
		Map<String, Object> query3 = new HashMap<String, Object>();
		query3.put("method", "dfire.member.save");
		query3.put("appKey", APP_KEY);
		query3.put("v", "1.0");
		query3.put("locale", "zh_CN");
		query3.put("timestamp", String.valueOf(System.currentTimeMillis()));
		query3.put("entityId", enid);//连锁entityId,单店entityId
		vo3.setSource(3);  
		vo3.setKindCardName("卡型"+cardcode);
		vo3.setCode(cardcode);
		vo3.setInnerCode(cardcode);
		vo3.setBalance(1000L);
		vo3.setBaseBalance(1000L);  
		vo3.setGiveBalance(1000L);
		vo3.setGiftBalance(1000L);
		vo3.setRealBalance(1000L);
		vo3.setDegree(800);
		vo3.setPayAmount(1000L);
		vo3.setRatioAmount(1000L);
		vo3.setPay(1000L);
		vo3.setMobile(phone);
		vo3.setCountryCode("32551");
		//list
        applyShopEntityIdList.add(enid);
        vo.setApplyShopEntityIdList(applyShopEntityIdList);
		cardList3.add(vo3);
		try {
			cardListJson = JsonUtil.beanToJson(cardList3);
		} catch (Exception e) {
			e.printStackTrace();
		}
		query3.put("cardList", cardListJson);
		String sign3 = SignUtils.sign(query3, APP_SECRET);
		query3.put("sign", sign3);
		Map<String,String> query3_new =new HashMap<String,String>();
		for (Map.Entry<String, Object> entry : query3.entrySet()) {
		       if(entry.getValue() instanceof String){
		    	   query3_new.put(entry.getKey(), (String) entry.getValue());
		          }
		 }
	    
	    String description4 = "description: qjc membercardsavetest savecard duplicate";
	    qjcCardListBean vo4 = new qjcCardListBean();
		List<qjcCardListBean> cardList4=new ArrayList<>();
		Map<String, Object> query4 = new HashMap<String, Object>();
		query4.put("method", "dfire.member.save");
		query4.put("appKey", APP_KEY);
		query4.put("v", "1.0");
		query4.put("locale", "zh_CN");
		query4.put("timestamp", String.valueOf(System.currentTimeMillis()));
		query4.put("entityId", enid);//连锁entityId,单店entityId
		vo4.setSource(3);  
		vo4.setKindCardName("卡型"+cardcode);
		vo4.setCode(cardcode);
		vo4.setInnerCode(cardcode);
		vo4.setBalance(1000L);
		vo4.setBaseBalance(1000L);  
		vo4.setGiveBalance(1000L);
		vo4.setGiftBalance(1000L);
		vo4.setRealBalance(1000L);
		vo4.setDegree(800);
		vo4.setPayAmount(1000L);
		vo4.setRatioAmount(1000L);
		vo4.setPay(1000L);
		vo4.setMobile(phone);
		vo4.setCountryCode("32551");
		cardList4.add(vo4);
		try {
			cardListJson = JsonUtil.beanToJson(cardList4);
		} catch (Exception e) {
			e.printStackTrace();
		}
		query4.put("cardList", cardListJson);
		String sign4 = SignUtils.sign(query4, APP_SECRET);
		query4.put("sign", sign4);
		Map<String,String> query4_new =new HashMap<String,String>();
		for (Map.Entry<String, Object> entry : query4.entrySet()) {
		       if(entry.getValue() instanceof String){
		    	   query4_new.put(entry.getKey(), (String) entry.getValue());
		          }
		 }
		
		return new Object[][]{
			//描述 查询条件 响应状态 响应码 用例名
				{description1, query1_new, 200, 1, 100},  //金额basebalance为负数
				{description2, query2_new, 200, 1, 200},  //source不存在
				{description3, query3_new, 200, 0, 300},  //正常保存卡信息
				{description4, query4_new, 200, 1, 400},  //卡信息重复

		};
	}
	
	
	
	/**
	 * 保存卡信息后，查询操作流水，有一条激活的流水
	 * @return
	 */
	@DataProvider(name = "memberOperFlowTest")
	public static Object [][] memberOperFlowTest(){
		
		String description7 = "description: qjc membercardsavetest operflow";
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
		query7.put("action", "7");//1/制卡 2/挂失 3/解挂 4/注销 5/领卡 6/还卡 7/激活 8/换卡 9/关联 10/失联 11/修改密码(目前只有挂失2 激活7 解挂3)		
		String sign = SignUtils.sign(query7,APP_SECRET);
		query7.put("sign", sign);
		query7.put("sessionId", "");
		
		return new Object[][]{
				{description7, query7, 200, 1, 200},

		};
	}
	
	
	/**
	 * 保存卡信息后，查询金额流水，有一条充值流水（如果保存卡时候realBalance有传值）
	 * @return
	 */
	@DataProvider(name = "memberMoneyFlowTest")
	public static Object [][] memberMoneyFlowTest(){
		
		String description7 = "description: qjc membercardsavetest moneyflow";
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
	 * 保存卡信息后，查询会员卡信息：1 balance(余额)  2 giftBalance（赠送金额）  3 realBalance（实际充值金额） 4 degree（积分）
	 * 5 payAmount（支付累积金额） 6 consumeAmount（消费累积金额）  7 ratioAmount（折扣累积金额）8 getStatus（领用状态）
	 * @return
	 */
	@DataProvider(name = "memberCardListTest")
	public static Object [][] memberCardListTest(){
		
		String description1 = "description: qjc membercardsavetest query_card_information";
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
				{description1, query1, 200, 1, 100}, //查询会员卡信息

		};
	}
	
	
	
	
}
