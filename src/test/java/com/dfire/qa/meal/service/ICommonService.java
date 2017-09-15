package com.dfire.qa.meal.service;

import java.util.List;
import java.util.Map;

import com.dfire.qa.meal.bean.BaseParamBean;
import com.dfire.qa.meal.bean.MenuGet;
import com.dfire.qa.meal.bean.Response;
import com.dfire.qa.meal.enums.Environment;
import com.dfire.qa.meal.vo.cart.CartIncludeSuitForm;


public interface ICommonService {
	

	
	/**
	 * 从配置文件中获取 entityId, 以及 seatCode
	 */
	public BaseParamBean getBaseParam(Environment env) throws Exception;
	
	
	
	/**
	 * 获取用户当前 token<br/>
	 * 调用获取 token 的函数才会不断刷新token 的更新日志<br/>
	 * 如果直接将 token 写入代码, token 在Redis 会失效<br/>
	 */
	public String getToken(String entityId, Environment env) throws Exception;
	
	
	/**
	 * 获取用户 token <br/>
	 * 不同环境的参数需要自己配置<br/>
	 */
	public String getToken(String entityId, String unionId, Environment env) throws Exception;
	
	
	/**
	 * 店码数据验证
	 */
	public boolean DataVerified(BaseParamBean baseParam, Map<String, String> otherParameter, 
			String people, List<CartIncludeSuitForm> httpJsonForCartSuitList, Environment env) throws Exception;
	
	
	
	/**
	 * 桌码数据验证
	 */
	public boolean DataVerified(BaseParamBean baseParam, String people, List<CartIncludeSuitForm> httpJsonForCartSuitList, Environment env) throws Exception;
	
	
	
	/**
	 * 进行菜单验证， 待验证菜单数据填写于 Map 容器中 <br/>
	 * Map<String, Map<String, CartIncludeSuitForm>>, 第一个参数表示 用户 ID, 一个用户对应一个购物车 <br/>
	 * 单个用户的购物车数据放置于   Map<String, CartIncludeSuitForm>  中，其中第一个参数表示菜单 ID, 一个菜单 ID 对应相应的菜单数据
	 */
    public boolean MenusValidate(Response response, String people, Map<String, Map<String, CartIncludeSuitForm>> expectedCartSuit, Environment env) throws Exception;
    

    
    /**
     * 根据需要通过接口获取 普通菜 或者 套菜<br/>
     */
    public List<CartIncludeSuitForm> getMenu(BaseParamBean baseParam, MenuGet menuGet, Map<String, String> otherParameter, Environment env) throws Exception;
	
    
    /**
     * 根据给定的条件获取菜品的组合<br/>
     */
    public List<CartIncludeSuitForm> chooseMenu(List<CartIncludeSuitForm> normalMenu, List<CartIncludeSuitForm> suitMenu) throws Exception;
    

    
    /**
     * 源菜单 与 购物车中的菜单 进行比较验证<br/>
     * stage 为  1 表示从购物车获取数据<br/>
     * stage 为  2 表示从 waitingOrder 处获取数据<br/>
     * stage 为  3 表示从 order 处获取数据<br/>
     */
    public  Boolean menuCompare(List<CartIncludeSuitForm> cartSuitList, BaseParamBean baseParam, Integer stage, Environment env) throws Exception;
    
    
    
    /**
     * 解析购物车中的菜品, 放入 List<br/>
     */
    public List<Object> getActualMenuList(List<Object> menuList) throws Exception;
    
    
    
    /**
     * 清理购物车数据<br/>
     * 必选菜目前只能通过下单方式消除<br/>
     * 购物车在12小时后失效<br/>
     */
    public Boolean clearCartData(BaseParamBean baseParamBean, Map<String, String> otherParameter, Environment env) throws Exception;
    
}