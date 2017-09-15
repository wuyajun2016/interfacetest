package com.dfire.qa.meal.data;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.dfire.qa.meal.bean.BaseParamBean;
import com.dfire.qa.meal.utils.CommonUtil;



public class ShopInfoModuleTestData extends BaseTestData{
	
	
	
	@DataProvider(name = "LoadStateTest")
	public static Object [][] LoadStateTest(){
		
		
		String description1 = "前置条件: 桌位码不存在";
		BaseParamBean baseParam1 = new BaseParamBean();
		baseParam1.setEntityId(entityId);
		baseParam1.setSeatCode("102YTX");
		baseParam1.setXtoken(token);
		

		String description2 = "前置条件: 桌位码存在";
		BaseParamBean baseParam2 = new BaseParamBean();
		baseParam2.setEntityId(entityId);
		baseParam2.setSeatCode(seatCode);
		baseParam2.setXtoken(token);
		
		
		String description3 = "前置条件: token 非法, 桌位码存在";
		BaseParamBean baseParam3 = new BaseParamBean();
		baseParam3.setEntityId(entityId);
		baseParam3.setSeatCode(seatCode);
		baseParam3.setXtoken("577fhfhjgkeurg");
		
		
		String description4 = "前置条件: token 字段不存在, 桌位码存在";
		BaseParamBean baseParam4 = new BaseParamBean();
		baseParam4.setEntityId(entityId);
		baseParam4.setSeatCode(seatCode);

		
		String description5 = "前置条件: token 正常, 桌位码字段不存在";
		BaseParamBean baseParam5 = new BaseParamBean();
		baseParam5.setEntityId(entityId);
		baseParam5.setXtoken(token);
		
		
		return new Object[][]{
				
				{description1, baseParam1, 200, 0, 400},
				{description2, baseParam2, 200, 1, 200},
				{description3, baseParam3, 200, -1, 400},
				
				{description4, baseParam4, 200, -1, 400},
				{description5, baseParam5, 200, 0, 400},


		};
	}

	@DataProvider(name = "baseInfoTest")
	public static Object [][] baseInfoTest(){
		
		String description1 = "前置条件: token 正常, entityId 非法";
		BaseParamBean baseParam1 = new BaseParamBean();
		baseParam1.setEntityId("entity_id");
		baseParam1.setXtoken(token);


		String description2 = "前置条件: token 正常, entityId 正常, 但是没有对应的shopImg";
		BaseParamBean baseParam2 = new BaseParamBean();
		baseParam2.setEntityId(entityId);
		baseParam2.setXtoken(token);
		
		
		String description3 = "前置条件: token 非法, entityId 正常, 但是没有对应的shopImg";
		BaseParamBean baseParam3 = new BaseParamBean();
		baseParam3.setEntityId(entityId);
		baseParam3.setXtoken("577fhfhjgkeurg");
		
		
		String description4 = "description: token 字段不存在, entityId 正常, 但是没有对应的shopImg";
		BaseParamBean baseParam4 = new BaseParamBean();
		baseParam4.setEntityId(entityId);

		
		String description5 = "前置条件: token 正常, entityId 字段为 null ";
		BaseParamBean baseParam5 = new BaseParamBean();
		baseParam5.setEntityId(null);
		baseParam5.setXtoken(token);

		
		String description6 = "前置条件: token 正常, entityId 正常, 存在对应的 shopImg ";
		BaseParamBean baseParam6 = new BaseParamBean();
		baseParam6.setEntityId(entityId);
		baseParam6.setXtoken(token);
		
		
		return new Object[][]{
				
				{description1, baseParam1, 200, 1, 400},
				{description2, baseParam2, 200, 1, 400},
				{description3, baseParam3, 200, -1, 400},
				
				{description4, baseParam4, 200, -1, 400},
				{description5, baseParam5, 200, 1, 400},
				{description6, baseParam6, 200, 1, 200},


		};
	}

	
	
	
	@DataProvider(name = "momentTest")
	public static Object [][] momentTest(){
		
		String description1 = "前置条件: token 正常, entityId 非法";
		BaseParamBean baseParam1 = new BaseParamBean();
		baseParam1.setEntityId("entity_id");
		baseParam1.setXtoken(token);


		String description2 = "前置条件: token 正常, entityId 正常, 但是没有对应的 notification";
		BaseParamBean baseParam2 = new BaseParamBean();
		baseParam2.setEntityId(entityId);
		baseParam2.setXtoken(token);
		
		String description3 = "前置条件: token 非法, entityId 正常, 但是没有对应的 notification";
		BaseParamBean baseParam3 = new BaseParamBean();
		baseParam3.setEntityId(entityId);
		baseParam3.setXtoken("577fhfhjgkeurg");
		
		String description4 = "前置条件: token 字段不存在, entityId 正常, 但是没有对应的 notification";
		BaseParamBean baseParam4 = new BaseParamBean();
		baseParam4.setEntityId(entityId);
		
		String description5 = "前置条件: token 正常, entityId 字段为 null ";
		BaseParamBean baseParam5 = new BaseParamBean();
		baseParam5.setEntityId(null);
		baseParam5.setXtoken(token);
		
		// 该用例预先数据还未准备好？？？
		String description6 = "前置条件: token 正常, entityId 正常, 存在对应的 notification ";
		Map<String, String> query6 = new HashMap<String, String>();
		query6.put("xtoken", token);
		String entityId6 = "";
		
		return new Object[][]{
				
				{description1, baseParam1, 200, 1, 400},
				{description2, baseParam2, 200, 1, 400},
				{description3, baseParam3, 200, -1, 400},
				
				{description4, baseParam4, 200, -1, 400},
				{description5, baseParam5, 200, 1, 400},
//				{description6, query6, entityId6, 200, 1, 200},


		};
	}
	
	
	@DataProvider(name = "allInfoTest")
	public static Object [][] allInfoTest(){
		
		String description1 = "前置条件: token 正常, entityId 非法";
		BaseParamBean baseParam1 = new BaseParamBean();
		baseParam1.setEntityId("entity_id");
		baseParam1.setXtoken(token);

		
		String description2 = "前置条件: token 正常, entityId 正常, 但是没有对应的 shopImg 以及 notification";
		BaseParamBean baseParam2 = new BaseParamBean();
		baseParam2.setEntityId(entityId);
		baseParam2.setXtoken(token);
		
		
		String description3 = "前置条件: token 非法, entityId 正常, 但是没有对应的 notification 以及  notification";
		BaseParamBean baseParam3 = new BaseParamBean();
		baseParam3.setEntityId(entityId);
		baseParam3.setXtoken("577fhfhjgkeurg");
		
		
		
		String description4 = "前置条件: token 字段不存在, entityId 正常, 但是没有对应的 notification";
		BaseParamBean baseParam4 = new BaseParamBean();
		baseParam4.setEntityId(entityId);
		
		
		
		String description5 = "前置条件: token 正常, entityId 字段为 null ";
		BaseParamBean baseParam5 = new BaseParamBean();
		baseParam5.setEntityId(null);
		baseParam5.setXtoken(token);
		
		// 该用例预先数据还未准备好？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？
		String description6 = "前置条件: token 正常, entityId 正常, 存在对应的 notification 并且存在 shopImg ";
		Map<String, String> query6 = new HashMap<String, String>();
		query6.put("xtoken", token);
		String entityId6 = "";
		
		// 该用例预先数据还未准备好？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？
		String description7 = "前置条件: token 正常, entityId 正常, 存在对应的 notification, 但是不存在 shopImg";
		Map<String, String> query7 = new HashMap<String, String>();
		query7.put("xtoken", token);
		String entityId7 = "";
		

		String description8 = "前置条件: token 正常, entityId 正常, 不存在对应的 notification, 但是存在 shopImg";
		Map<String, String> query8 = new HashMap<String, String>();
		query8.put("xtoken", token);
		String entityId8 = "99001331";
		
		return new Object[][]{
				
				{description1, baseParam1, 200, 1, 400},
				{description2, baseParam2, 200, 1, 400},
				{description3, baseParam3, 200, -1, 400},
				
				{description4, baseParam4, 200, -1, 400},
				{description5, baseParam5, 200, 1, 400},
//				{description6, query6, entityId6, 200, 1, 200},
				
//  			{description7, query7, entityId7, 200, 1, 200},
//				{description8, query8, entityId8, 200, 1, 200},


		};
	}
	
	
	@DataProvider(name = "shopShareTest")
	public static Object [][] shopShareTest(){
		
		
		String description1 = "前置条件: token 正常, entityId 非法";
		BaseParamBean baseParam1 = new BaseParamBean();
		baseParam1.setEntityId(CommonUtil.getUUID());
		baseParam1.setXtoken(token);


		String description2 = "前置条件: token 正常, entityId 正常, 但是没有对应的 shopImg 以及 notification";
		BaseParamBean baseParam2 = new BaseParamBean();
		baseParam2.setEntityId(entityId);
		baseParam2.setXtoken(token);
		
		
		
		String description3 = "前置条件: token 非法, entityId 正常, 但是没有对应的 notification 以及  notification";
		BaseParamBean baseParam3 = new BaseParamBean();
		baseParam3.setEntityId(entityId);
		baseParam3.setXtoken("577fhfhjgkeurg");
		
		
		
		String description4 = "前置条件: token 字段不存在, entityId 正常, 但是没有对应的 notification";
		BaseParamBean baseParam4 = new BaseParamBean();
		baseParam4.setEntityId(entityId);
		
		
		
		String description5 = "前置条件: token 正常, entityId 字段为 null ";
		BaseParamBean baseParam5 = new BaseParamBean();
		baseParam5.setEntityId(null);
		baseParam5.setXtoken(token);
		
		
		
		// 该用例预先数据还未准备好？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？
		String description6 = "前置条件: token 正常, entityId 正常, 存在对应的 notification 并且存在 shopImg ";
		Map<String, String> query6 = new HashMap<String, String>();
		query6.put("xtoken", token);
		String entityId6 = "";
		
		// 该用例预先数据还未准备好？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？
		String description7 = "前置条件: token 正常, entityId 正常, 存在对应的 notification, 但是不存在 shopImg";
		Map<String, String> query7 = new HashMap<String, String>();
		query7.put("xtoken", token);
		String entityId7 = "";
		

		String description8 = "前置条件: token 正常, entityId 正常, 不存在对应的 notification, 但是存在 shopImg";
		Map<String, String> query8 = new HashMap<String, String>();
		query8.put("xtoken", token);
		String entityId8 = "99001331";
		
		return new Object[][]{
				
				{description1, baseParam1, 200, 1, 400},
				{description2, baseParam2, 200, 1, 200},
				{description3, baseParam3, 200, -1, 400},
				
				{description4, baseParam4, 200, -1, 400},
				{description5, baseParam5, 200, 1, 400},
//				{description6, query6, entityId6, 200, 1, 200},
//				
//				{description7, query7, entityId7, 200, 1, 200},
//				{description8, query8, entityId8, 200, 1, 200},


		};
	}
}
