package com.dfire.qa.meal.vo.boss;

public class MenuProp {
	
	
	/**
	 * 构造函数
	 * @param entityId
	 * @param id
	 */
	public MenuProp(String entityId, String id, boolean onSelf){
		
		this.entityId = entityId;
		this._id = id;
		this.id = id;
		
		if(!onSelf){
			this.isTakeout = 0;
			this.lastVer = 2;
			this.isReserve = 0;
		}
	}
	
	
    /**
     * <code>所属实体</code>.
     */
    private String entityId;
    
    
    /**
     * <code>外卖是否可点对应的字段</code>.
     */
    private int isTakeout = 1;
    
    
    /**
     * 是否指定：默认0
     * 可以根据值从大到小排序
     */
    private int showTop = 0;
    
    
    /**
     * 打包盒单价（此处必须为对象类型，当NULL时表示并没有被设值）
     */
    private Double packingBoxPrice = (double)0;
    
    
    /**
     * <code>起点份数对应的字段 </code>.
     */
    private int startNum = 1;
    
    
    /**
     * <code>操作时间</code>.
     */
    private long opTime = System.currentTimeMillis();
    
    
    /**
     * <code>此id只是为了object c中使用</code>.
     */
    private String _id;
    
    
    /**
     * <code>是否有效</code>.
     */
    private int isValid = 1;

    
    /**
     * <code> I don't know <code>
     */
    private int acridLevel = 0;
    
    
    /**
     * <code>推荐指数对应的字段 0/不设定、1/推荐、2/十分推荐、3/强烈推荐</code>.
     */
    private int recommendLevel = 0;
    
    
    /**
     * <code>主键</code>.
     */
    private String id;
    
    
    /**
     * 打包盒数量（此处必须为对象类型，当NULL时表示并没有被设值）
     */
    private Integer packingBoxNum = 0;
    
    
    
    /**
     * <code> I don't know <code>
     */
    private int memberPrice = 0;
    
    
    /**
     * <code> 是不是必选菜 <code>
     */
    private int isForceMenu = 0;
    
    
    /**
     * <code> 商品累加步长  <code>
     */
    private Integer stepLength = 1;
    
    
    /**
     * <code>版本号</code>.
     */
    private int lastVer = 3;
    
    
    /**
     * <code>记录创建时间</code>.
     */
    private long createTime = System.currentTimeMillis();
    
    
    /**
     * <code> 价格  <code>
     */
    private double price = 0;
    
    
    /**
     * <code>标签来源对应的字段 1/系统标签、2/商户自定义标签</code>.
     */
    private int tagSource = 1;
    
    
    /**
     * 此商品仅在套餐显示（此处必须为对象类型，当NULL时表示并没有被设值）
     */
    private Integer mealOnly = 0;
    
    
    /**
     *<code> 堂食可点此商品 <code>
     */
    private int isReserve = 1;
    
    
}
