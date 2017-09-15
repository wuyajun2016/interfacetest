package com.dfire.testcase.function.bean.cash;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CartCompareBean {

    /**
     * 如果是套餐，则会有这个子菜数组
     */
    private List<CartCompareBean> childCartVos;
    private String menuId;
    private String menuName;
    private String makeId;
    @JsonProperty("specDetailId")
    private String specDetailId;
    private Double num;
    private String kindMenuId;
    /**
     * 套菜子菜加价模式
     */
    private int addPriceMode;

    /**
     * 套菜子菜加价
     */
    private double addPrice;

    private int kindType;  // 1.普通菜  2.套菜  5.加料菜（只能在子child出现）


    public List<CartCompareBean> getChildCartVos() {
        return childCartVos;
    }


    public void setChildCartVos(List<CartCompareBean> childCartVos) {
        this.childCartVos = childCartVos;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMakeId() {
        return makeId;
    }

    public void setMakeId(String makeId) {
        this.makeId = makeId;
    }

    public String getSpecDetailId() {
		return specDetailId;
	}

	public void setSpecDetailId(String specDetailId) {
		this.specDetailId = specDetailId;
	}

	public Double getNum() {
        return num;
    }

    public void setNum(Double num) {
        this.num = num;
    }

    public String getKindMenuId() {
        return kindMenuId;
    }

    public void setKindMenuId(String kindMenuId) {
        this.kindMenuId = kindMenuId;
    }


    public int getAddPriceMode() {
        return addPriceMode;
    }

    public void setAddPriceMode(int addPriceMode) {
        this.addPriceMode = addPriceMode;
    }

    public double getAddPrice() {
        return addPrice;
    }

    public void setAddPrice(double addPrice) {
        this.addPrice = addPrice;
    }

    public int getKindType() {
        return kindType;
    }

    public void setKindType(int kindType) {
        this.kindType = kindType;
    }

    @Override
    public int hashCode() {
    	System.err.println("hashcode");
		return 92;
	}
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public boolean equals(Object o)   
    {   
        if (this == o)   
        {   
            return true;   
        }   
          
        if (o.getClass() == CartCompareBean.class)   
        {   
        	CartCompareBean obj = (CartCompareBean)o;   
			Class entityClass = CartCompareBean.class;
			Field[] fiels = entityClass.getDeclaredFields();
			for (Integer i = 0; i < fiels.length; i++) {
				Method getMethod = null;
				try {
					getMethod = entityClass.getDeclaredMethod("get" + fiels[i].getName().substring(0,1).toUpperCase()+fiels[i].getName().substring(1));
					Object param0 = getMethod.invoke(obj);
					Object param1 = getMethod.invoke(this);
					if (param0==param1||param0.equals(param1)) {
						continue;
					}
//					System.err.println("param0:"+param0);
//					System.err.println("param1:"+param1);
						
					return false;
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}
		return true;
    }   
}
