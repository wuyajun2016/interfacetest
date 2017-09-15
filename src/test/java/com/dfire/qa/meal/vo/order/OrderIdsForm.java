package com.dfire.qa.meal.vo.order;

import java.util.List;

import com.dfire.qa.meal.vo.menu.MenuForm;

public class OrderIdsForm {
	
	private String id;
    private List<MenuForm> order;
    
    private String foodStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<MenuForm> getOrder() {
        return order;
    }

    public void setOrder(List<MenuForm> order) {
        this.order = order;
    }

	public String getFoodStatus() {
		return foodStatus;
	}

	public void setFoodStatus(String foodStatus) {
		this.foodStatus = foodStatus;
	}

}
