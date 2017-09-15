package com.dfire.qa.meal.service;

import java.util.List;
import java.util.Map;

import com.dfire.qa.meal.bean.BaseParamBean;
import com.dfire.qa.meal.bean.Response;
import com.dfire.qa.meal.enums.Environment;
import com.dfire.qa.meal.vo.cart.CartIncludeSuitForm;
import com.dfire.qa.meal.vo.menu.MenuSpec;



public interface ICartService {
	
	
	/**
	 * 创建购物车 create own cart , the data is in redis with keyword "order_cart" <br/>
	 * 需要参数：xtoken, entityId, seatCode, orderId <br/>
	 * 对应的 URL：/carts/v1/create
	 */
	public Response createCart(BaseParamBean baseParamBean, Environment env) throws Exception;
	
	
	/**
	 * 获取虚拟购物车数据及用餐人数 get info from cart with list format, the data is in redis <br/>
	 * with keyword "order_cart"  <br/>
	 * 对应的URL：/carts/v1/list  
	 */
	public Response listCartData(BaseParamBean baseParamBean, Environment env) throws Exception;
	
	
	/**
	 * 优惠载体文字显示栏标题 show privilege title <br/>
	 * 请求的 URL ：/privilege/v1/title <br/>
	 */
	@Deprecated
	public Response showPrivilegeTitle(BaseParamBean baseParamBean, Environment env) throws Exception; 
	
	
	
	/**
	 * 获取套菜 menu 详情 <br/>
	 * 请求 URL : /menus/v1/normal_detail
	 */
	public Response getNormalMenuSpec(BaseParamBean baseParamBean, String menuId, Environment env) throws Exception;
	
	
	
	/**
	 * 获取套菜 menu 详情 <br/>
	 * 请求 URL : /menus/v1/menu_detail
	 */
	public Response getSuitMenuSpec(BaseParamBean baseParamBean, MenuSpec menuSpec, Environment env) throws Exception;
	
	
	
	/**
	 * 获取菜单列表 list menus <br/>
	 * 请求 URL：/menus/v1/list
	 */
	public Response listMenus(BaseParamBean baseParamBean, Map<String, String> otherParameter, Environment env) throws Exception;
	
	
	
	/**
	 * 获取弹窗消息<br/>
	 * 请求 URL：/privilege/v1/popup_content
	 */
	public Response popupMessage(BaseParamBean baseParamBean, Environment env) throws Exception;
	
	
	
	/**
	 * 购物车页面展示可用优惠信息<br/>
	 * 请求的 URL ：/privilege/v1/cart/list
	 */
	public Response cartPrivilege(BaseParamBean baseParamBean, List<String> menuLIdist, Environment env) throws Exception;
	
	
	
	/**
	 * modify people and memo <br/>
	 * 修改人数和备注信息, 如果必选菜数目设置成与点餐人数相同, 该动作会触发添加必选菜动作  <br/>
	 * 需要参数：xtoken, entityId, seatCode, orderId, memo, people, old_people <br/>
	 * 请求 URL : /carts/v1/modify_people_memo
	 */
	public Response modifyPeople(BaseParamBean baseParamBean, Map<String, String> otherParameter, Environment env) throws Exception;
	
	
	
	
	/**
	 * 获取推荐菜<br/>
	 * 请求 URL : menus/v1/get_recommend_menus <br/>
	 */
	public Response getRecommendMenus(BaseParamBean baseParamBean, Map<String, String> otherParameter, Environment env) throws Exception;
	
	
	
	/**
	 * 添加必选商品 add required item
	 * 请求 URL ：/carts/v1/force_menu
	 */
	public Response addRequiredItem(BaseParamBean baseParamBean, Map<String, String> otherParameter, Environment env) throws Exception;
	
	
	/**
	 * 添加菜品到个人购物车(云购物车), 其中 httpBodyJson 是 CartIncludeSuitForm 的 Json 形式 <br/>
	 * 需要参数：xtoken, entityId, seatCode, orderId(默认为空) <br/>
	 * <span style="color: red;">请求的新 URL</span> ： /carts/v1/async_modify 或者    /carts/v1/modify ，二者是同一个接口，只是为了区分加的是普通菜还是套菜</br>
	 * <span style="color: blue;">请求的旧的URL</span>： /carts/v1/modify_own
	 */
	public Response addDishToCarts(BaseParamBean baseParamBean, String httpBodyJson, Environment env) throws Exception; 
	
	
	
	
	/**
	 * 获取购物车商品数量<br/>
	 */
	public Response getMenuCount(BaseParamBean baseParamBean, Environment env) throws Exception;
	
	
	/**
	 * 扫店码时添加菜品到个人购物车<br/>
	 */
	public Response addDishtoCartWithShop(BaseParamBean baseParamBean, String httpBodyJson, Environment env) throws Exception;
	
	
	
	
	/**
	 * 删除一起点购物车的所有菜, 该接口针对扫桌码 <br/>
	 * 需要参数：xtoken, entityId, seatCode, orderId
	 */
	public Response clearCart(BaseParamBean baseParamBean, Environment env) throws Exception;
	
	
	
	/**
	 * 获取用户购物车的数据接口（不含菜单列表）, 获取购物车内菜品数据 <br/>
	 * 扫店码<br/>
	 */
	public Response getUserCart(BaseParamBean baseParamBean, Map<String, String> otherParameter, Environment env) throws Exception;
		
	
	
	
	
	
	///////////////////////////////////////////   封装方法            ////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 封装方法 <br/>
	 * <b>点击进入</b>，进入菜单列表页 <br/>
	 * Map 中 的 key 为：newCart, cartList, priTitle, menuList <br/>
	 * <b>原始参数</b> ：entityId, seatCode, repeat, recommend  (orderID  :  "") <br/>
	 * <span style="color: red;"><B>该函数已更新<B></span>
	 */
	public Map<String, Response> clickToEnter(BaseParamBean baseParamBean, Map<String, String> otherParameter, Environment env) throws Exception;
	
	
	
	 /**
	  * 封装方法 <br/>
	  * 非预付款扫桌码加菜<br/>
	  * 包含请求: 创建购物车, 修改人数, 添加必选菜, 加菜(包括普通菜和套菜)
	  */
	 public Map<String, Response> addMenuToCart(BaseParamBean baseParamBean, Map<String, String> otherParameter, 
			 List<CartIncludeSuitForm> cartSuitList, Environment env) throws Exception;
	

}
