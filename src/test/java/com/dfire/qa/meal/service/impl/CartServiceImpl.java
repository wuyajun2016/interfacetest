package com.dfire.qa.meal.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dfire.qa.meal.bean.BaseParamBean;
import com.dfire.qa.meal.bean.Response;
import com.dfire.qa.meal.constant.Constants;
import com.dfire.qa.meal.enums.Environment;
import com.dfire.qa.meal.enums.Module;
import com.dfire.qa.meal.httpclient.HTTPClientService;
import com.dfire.qa.meal.logger.LoggerMarkers;
import com.dfire.qa.meal.logger.MealLoggerFactory;
import com.dfire.qa.meal.props.HostProperties;
import com.dfire.qa.meal.service.ICartService;
import com.dfire.qa.meal.utils.BeanProvider;
import com.dfire.qa.meal.utils.HTTPRequestUtil;
import com.dfire.qa.meal.utils.PathForHTTP;
import com.dfire.qa.meal.vo.cart.CartIncludeSuitForm;
import com.dfire.qa.meal.vo.menu.MenuSpec;
import com.google.gson.Gson;



@Service
public class CartServiceImpl implements ICartService{

	@Resource
	private HostProperties hostProperties;
	
	@Resource
	private HTTPClientService httpClientService;
	
	private Gson gson = new Gson();
	
	@Override
	public Response createCart(BaseParamBean baseParamBean, Environment env) throws Exception {
		
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.CREATECART);
		
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
		
		Response response = null;
				
		Map<String, String> query = new HashMap<String, String>();
		query.put("xtoken", baseParamBean.getXtoken());
		query.put("entity_id", baseParamBean.getEntityId());
		query.put("seat_code", baseParamBean.getSeatCode());
		query.put("order_id", baseParamBean.getOrderId());

		int peopleCount = -1;
		String cartsCreateJson = BeanProvider.getCartsCreateJson(baseParamBean.getEntityId(),
				baseParamBean.getSeatCode(), baseParamBean.getOrderId(), peopleCount);
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("serverURL"), PathForHTTP.getPathForCreateOwnCart(), query, protocol);
		response = httpClientService.doPost(url, null, cartsCreateJson);
			
		
		return response;
	}

	
	@Override
	public Response listCartData(BaseParamBean baseParamBean, Environment env) throws Exception {
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.LISTCARTDATA);
		
//		if (Environment.publish == env){
//			httpClientService.setHttps();
//		}
		
		Response response = null;
		
		Map<String, String> query = new HashMap<String, String>();
		query.put("xtoken", baseParamBean.getXtoken());
		query.put("entity_id", baseParamBean.getEntityId());
		query.put("seat_code", baseParamBean.getSeatCode());
		query.put("order_id", baseParamBean.getOrderId());
		
		// 计算 URL 并发送 GET 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("serverURL"), PathForHTTP.getPathForGetCartData(), query, protocol);
		response = httpClientService.doGet(url, null);
					
		
		return response;
	}

	
	@Override
	@Deprecated
	public Response showPrivilegeTitle(BaseParamBean baseParamBean, Environment env) throws Exception {
		
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.PRIVILEGETITLE);
		
//		if (Environment.publish == env){
//			httpClientService.setHttps();
//		}
		
		Response response = null;		
	
		Map<String, String> query = new HashMap<String, String>();
		query.put("xtoken", baseParamBean.getXtoken());
		query.put("entity_id", baseParamBean.getEntityId());
		
		// 计算 URL 并发送  GET 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("serverURL"), PathForHTTP.getPathForShowTitle(), query, protocol);
		response = httpClientService.doGet(url, null);

	
		return response;
	
	}

	
	
	@Override
	public Response getNormalMenuSpec(BaseParamBean baseParamBean, String menuId, Environment env) throws Exception {
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.LISTNORMALSPEC);
		
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
		
		Response response = null;		
		
		Map<String, String> query = new HashMap<String, String>();
		query.put("xtoken", baseParamBean.getXtoken());
		query.put("entity_id", baseParamBean.getEntityId());
		query.put("menu_id", menuId);
		query.put("use_range", "1");
			
		
		// 计算 URL 并发送  GET 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("serverURL"), PathForHTTP.getPathForListNormalSpec(), query, protocol);
		response = httpClientService.doGet(url, null);
		
		return response;
		
	}
	
	
	
	@Override
	public Response getSuitMenuSpec(BaseParamBean baseParamBean, MenuSpec menuSpec, Environment env) throws Exception{
		
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.LISTMENUSPEC);
		
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
		
		Response response = null;		
		
		Map<String, String> query = new HashMap<String, String>();
		query.put("xtoken", baseParamBean.getXtoken());
		
		StringBuilder params = new StringBuilder();
		params.append("entity_id=").append(menuSpec.getEntity_id()).append("&menu_id=").append(menuSpec.getMenu_id()).
		append("&is_include=").append(menuSpec.getIs_include()).append("&data=").append(gson.toJson(menuSpec.getData()));
			
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, "the body is : " + params.toString());
		
		// 计算 URL 并发送  GET 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("serverURL"), PathForHTTP.getPathForListMenuSpec(), query, protocol);
		response = httpClientService.doPostWithForm(url, null, params.toString());
				
		return response;
	}
	
	
	
	
	@Override
	public Response listMenus(BaseParamBean baseParamBean, Map<String, String> otherParameter, Environment env) throws Exception {
		
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.LISTMENUS);
		
//		if (Environment.publish == env){
//			httpClientService.setHttps();
//		}
		
		Response response = null;		
		
		Map<String, String> query = new HashMap<String, String>();
		query.put("xtoken", baseParamBean.getXtoken());
		query.put("entity_id", baseParamBean.getEntityId());
		
		query.put("repeat", otherParameter.get("repeat"));
		query.put("recommend", otherParameter.get("recommend"));
			
		// 计算 URL 并发送  GET 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("serverURL"), PathForHTTP.getPathForListMenus(), query, protocol);
		response = httpClientService.doGet(url, null);
				
		return response;
		
	}

	@Override
	public Response popupMessage(BaseParamBean baseParamBean, Environment env) throws Exception {
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.POPUPMESSAGE);
		
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
		
		Response response = null;
		
		Map<String, String> query = new HashMap<String, String>();
		query.put("xtoken", baseParamBean.getXtoken());
		query.put("entity_id", baseParamBean.getEntityId());
		
		// 计算 URL 并发送  GET 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("serverURL"), PathForHTTP.getPathForGetPopupMessage(), query, protocol);
		response = httpClientService.doGet(url, null);
			
		return response;
	}


	@Override
	public Response cartPrivilege(BaseParamBean baseParamBean, List<String> menuIdList, Environment env) throws Exception {
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.CARTPRIVILEGE);
		
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
		
		Response response = null;
					
		Map<String, String> query = new HashMap<String, String>();
		query.put("xtoken", baseParamBean.getXtoken());
		query.put("entity_id", baseParamBean.getEntityId());
		query.put("menu_id_list", gson.toJson(menuIdList));
	
		// 计算 URL 并发送  GET 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("serverURL"), PathForHTTP.getPathForListCartsPrivilege(), query, protocol);
		response = httpClientService.doGet(url, null);			
		
		return response;
	}
	
	
		
	
	
	@Override
	public Response modifyPeople(BaseParamBean baseParamBean, Map<String, String> otherParameter, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.MODIFYPEOPLE);
		
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
		
		Response response = null;
			
		Map<String, String> query = new HashMap<String, String>();
		
		query.put("xtoken", baseParamBean.getXtoken());
		query.put("entity_id", baseParamBean.getEntityId());
		query.put("seat_code", baseParamBean.getSeatCode());
		query.put("order_id", baseParamBean.getOrderId());
		
		query.put("memo", otherParameter.get("memo"));
		query.put("people", otherParameter.get("people"));
		query.put("old_people", otherParameter.get("oldPeople"));
		query.put("memo_labels", otherParameter.get("memoLabels"));

		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("serverURL"), PathForHTTP.getPathForModifyInfo(), query, protocol);
		response = httpClientService.doPost(url, null, "");		
		
		return response;
	}

	
	
	@Override
	public Response getRecommendMenus(BaseParamBean baseParamBean, Map<String, String> otherParameter, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.MODIFYPEOPLE);
		
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
		
		Response response = null;
			
		Map<String, String> query = new HashMap<String, String>();

		query.put("xtoken", baseParamBean.getXtoken());
		query.put("entity_id", baseParamBean.getEntityId());
		query.put("seat_code", baseParamBean.getSeatCode());
		query.put("order_id", baseParamBean.getOrderId());
		
		query.put("page", otherParameter.get("page"));
		query.put("page_size", otherParameter.get("pageSize"));
		query.put("is_pre_cart", otherParameter.get("isPreCart")); // 是否是预点菜

		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("serverURL"), PathForHTTP.getPathForRecommendMenu(), query, protocol);
		response = httpClientService.doGet(url, null);		
		
		return response;
	}
	
	
	
	@Deprecated
	@Override
	public Response addRequiredItem(BaseParamBean baseParamBean, Map<String, String> otherParameter, Environment env) throws Exception {
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.ADDREQUIREDITEM);
		
//		if (Environment.publish == env){
//			httpClientService.setHttps();
//		}
		
		Response response = null;
		
		Map<String, String> query = new HashMap<String, String>();
		query.put("xtoken", baseParamBean.getXtoken());
		query.put("entity_id", baseParamBean.getEntityId());
		query.put("seat_code", baseParamBean.getSeatCode());

		query.put("order_id", baseParamBean.getOrderId());
		query.put("people", otherParameter.get("people"));
		
		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("serverURL"), PathForHTTP.getPathForForceMenu(), query, protocol);
		response = httpClientService.doPost(url, null, "");		
					
		return response;
	}

	
	@Override
	public Response addDishToCarts(BaseParamBean baseParamBean, String httpBodyJson, Environment env) throws Exception {
		
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.ADDDISHToCARTS);
		
//		if (Environment.publish == env){
//			httpClientService.setHttps();
//		}
		
		Response response = null;
		
		Map<String, String> query = new HashMap<String, String>();
		query.put("xtoken", baseParamBean.getXtoken());
		query.put("entity_id", baseParamBean.getEntityId());
		query.put("seat_code", baseParamBean.getSeatCode());
		query.put("order_id", baseParamBean.getOrderId());

		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("serverURL"), PathForHTTP.getPathForModifyCart(), query, protocol);
		response = httpClientService.doPost(url, null, httpBodyJson);		
					
		return response;
	}


	
	@Override
	public Response getMenuCount(BaseParamBean baseParamBean, Environment env) throws Exception{
	
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.ADDDISHToCARTS);
		
//		if (Environment.publish == env){
//			httpClientService.setHttps();
//		}
		
		Response response = null;
		
		Map<String, String> query = new HashMap<String, String>();
		query.put("xtoken", baseParamBean.getXtoken());
		query.put("entity_id", baseParamBean.getEntityId());
		query.put("seat_code", baseParamBean.getSeatCode());
		query.put("order_id", baseParamBean.getOrderId());

		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("serverURL"), PathForHTTP.getPathForGetCartCount(), query, protocol);
		response = httpClientService.doGet(url, null);		
					
		return response;
	}
	
	
	
	
	
	@Override
	public Response addDishtoCartWithShop(BaseParamBean baseParamBean, String httpBodyJson, Environment env) throws Exception{
	
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.ADDDISHToCARTS);
		
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
		
		Response response = null;
		
		Map<String, String> query = new HashMap<String, String>();
		query.put("xtoken", baseParamBean.getXtoken());
		query.put("entity_id", baseParamBean.getEntityId());
		

		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("serverURL"), PathForHTTP.getPathForPreCart(), query, protocol);
		response = httpClientService.doPost(url, null, httpBodyJson);		
					
		return response;
		
	}
	
	
	
	@Override
	public Response clearCart(BaseParamBean baseParamBean, Environment env) throws Exception {
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.CLEARCART);
		
//		if (Environment.publish == env){
//			httpClientService.setHttps();
//		}
		
		Response response = null;
		
		Map<String, String> query = new HashMap<String, String>();
		query.put("xtoken", baseParamBean.getXtoken());
		
		// 构造表单形式的 body 内容
		StringBuilder bodyBuilder = new StringBuilder();
		bodyBuilder.append("entity_id=").append(baseParamBean.getEntityId()).append("&seat_code=").
		append(baseParamBean.getSeatCode()).append("&order_id=").append(baseParamBean.getOrderId());

		// 计算 URL 并发送 POST 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("serverURL"), PathForHTTP.getPathForClearCart(), query, protocol);
		response = httpClientService.doPostWithForm(url, null, bodyBuilder.toString());
					
		return response;
		
	}
	
	
	@Override
	public Response getUserCart(BaseParamBean baseParamBean, Map<String, String> otherParameter, Environment env) throws Exception {

		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.GETUSERCART);
		
		if (Environment.publish == env){
			httpClientService.setHttps();
		}
		
		Response response = null;				

		Map<String, String> query = new HashMap<String, String>();
		
		query.put("xtoken", baseParamBean.getXtoken());
		query.put("entity_id", baseParamBean.getEntityId());
		query.put("queue_id", otherParameter.get("queueId"));
		
		// 计算 URL 并发送 GET 请求
		String protocol = (Environment.publish == env) ? "https" : "http";
		String url = HTTPRequestUtil.getCompleteURL(hostProperties.getHostProperties(env).get("serverURL"), PathForHTTP.getPathForGetUserCart(), query, protocol);
		response = httpClientService.doGet(url, null);
			
		return response;
		
	}
	
	
	
	
/////////////////////////////////////   封装方法           ////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public Map<String, Response> clickToEnter(BaseParamBean baseParamBean, Map<String, String> otherParameter, Environment env) throws Exception {
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.CLICKTOENTER);
		
		// prepare new cart
		Response createCart = createCart(baseParamBean, env);
				
		// get info of carts with list format
		Response cartList = listCartData(baseParamBean, env);
		
//		// show privilege title
//		Response priTitle = showPrivilegeTitle(baseParamBean, env);
		
		// list menus
		Response menuList = listMenus(baseParamBean, otherParameter, env);
		
		// pop up message
		Response popupMessage = popupMessage(baseParamBean, env);
		
//		// cart privilege
//		Response cartPrivilege = cartPrivilege(baseParamBean, otherParameter, env);
		
		Map<String, Response> response = new HashMap<String, Response>();
		response.put("createCart", createCart);
		response.put("cartList", cartList);
//		response.put("priTitle", priTitle);
		
		response.put("menuList", menuList);
		response.put("popUpMessage", popupMessage);
//		response.put("cartPrivilege", cartPrivilege);
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.CLICKTOENTERSUC);
		
		return response;
	}


	@Override
	public Map<String, Response> addMenuToCart(BaseParamBean baseParamBean, Map<String, String> otherParameter,
			List<CartIncludeSuitForm> cartSuitList, Environment env) throws Exception {

		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.ADDMENUTOCART);
		
		// 重新创建购物车, 以免之前创建的购物车失效
		Response createCart = createCart(baseParamBean, env);			
		
		// 修改人数和备注信息
		Response modifyPeople = modifyPeople(baseParamBean, otherParameter, env);
		
		// 添加必选商品
//		Response addForceMenu = addRequiredItem(baseParamBean, otherParameter, env);			
		
		//将菜加入购物车
		for(CartIncludeSuitForm cartSuitForm : cartSuitList){
			
			Response addDish = addDishToCarts(baseParamBean, gson.toJson(cartSuitForm), env);
//			Map<String, Response> addDishResult = new HashMap<String, Response>();
//			addDishResult.put("addDish", addDish);
//			HTTPRequestUtil.verifyResponse(addDishResult, "addDish");
			
			HTTPRequestUtil.verifyResponse(addDish);
			
		}
		
		
		Map<String, Response> response = new HashMap<String, Response>();
		response.put("createCart", createCart);
		response.put("modifyPeople", modifyPeople);			
//		response.put("addForceMenu", addForceMenu);	
		
		MealLoggerFactory.LOG_FILES_LOGGER.info(LoggerMarkers.LOGFILE, Constants.Message.ADDMENUTOCARTSUC);
		
		return response;
	}





	


	


	
	
	

}
