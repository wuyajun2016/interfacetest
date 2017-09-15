package com.dfire.testcase.appchange;

import java.util.List;
import java.util.Map;

/**
 * 查询条件
 * @author Administrator
 *
 */
public class nearbyShopConditionBean {

	private List<AdditionKeyBean> additionKey;
	
	private String  latitude;
	private String  longitude;
	private Number page;
	private Number pageSize;
	private String  searchContent;
	
	


	
	
	public List<AdditionKeyBean> getAdditionKey() {
		return additionKey;
	}
	public void setAdditionKey(List<AdditionKeyBean> additionKey) {
		this.additionKey = additionKey;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public Number getPage() {
		return page;
	}
	public void setPage(Number page) {
		this.page = page;
	}
	public Number getPageSize() {
		return pageSize;
	}
	public void setPageSize(Number pageSize) {
		this.pageSize = pageSize;
	}
	public String getSearchContent() {
		return searchContent;
	}
	public void setSearchContent(String searchContent) {
		this.searchContent = searchContent;
	}
	
	
}
