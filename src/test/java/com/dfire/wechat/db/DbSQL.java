package com.dfire.wechat.db;

/**
 * @author Liao Jinwen 2016-8-8
 * 
 */
public class DbSQL {

	// suitmenu manipulation sql
	public static final String INSERT_MENU_CHANGE_SQL = "insert into suit_menu_change (suit_menu_change_id, menu_id, suit_menu_detail_id, entity_id, is_valid, last_ver)  "
			+ "values(?, ?, ?, ?, 1, 0)";	
	public static final String DELETE_MENU_CHANGE_SQL = "delete from suit_menu_change where suit_menu_change_id=?";
	
	// suitmenu manipulation sql
	public static final String INSERT_SUITMENU_SQL = "insert into suit_menu_detail (suit_menu_detail_id, suit_menu_id, entity_id, detail_menu_id, is_required, sort_code, is_valid, last_ver)  "
			+ "values(?, ?, ?, \"0\",  0, 0, 1, 0)";	
	public static final String DELETE_SUITMENU_SQL = "delete from suit_menu_detail where suit_menu_detail_id=?";
	
	// menu manipulation sql
	public static final String INSERT_MENU_SQL = "insert into menu (menu_id, entity_id, kind_menu_id, sort_code, name, buy_account, consume, balance_mode, is_valid, last_ver)  "
			+ "values(?, ?, ?, 3, \"test_for_menu\", \"份\", 0, 1, 1, 1)";	
	public static final String INSERT_MENU_WITHCHILD_SQL = "insert into menu (menu_id, entity_id, kind_menu_id, sort_code, name, buy_account, consume, balance_mode, is_valid, last_ver, is_include)  "
			+ "values(?, ?, ?, 3, \"test_for_menu\", \"份\", 0, 1, 1, 1, 1)";	
	
	// orderdetail manipulation sql
	public static final String INSERT_ORDERDETAIL_SQL = "insert into orderdetail (order_id, waitingorder_id, entity_id, load_time, status, is_valid, curr_date, people_count, open_time, memo, last_ver)  "
			+ "values(?, ?, ?, ?, 1, 1, \"2016-08-19\", 8, 1, \"test_for_order\", 0)";	
	public static final String INSERT_ORDERDETAIL2_SQL = "insert into orderdetail (order_id, waitingorder_id, entity_id, load_time, seat_code, customerregister_id, totalpay_id, status, is_valid, curr_date, people_count, open_time, memo, last_ver)  "
			+ "values(?, ?, ?, ?, ?, ?, ?, 1, 1, \"2016-08-19\", 8, 1, \"test_for_order\", 0)";	
	public static final String DELETE_ORDERDETAIL_SQL = "delete from orderdetail where order_id=?";
	
	// waitingorderdetail manipulation sql
	public static final String INSERT_WAITINGORDERDETAIL_SQL = "insert into waitingorderdetail (waitingorder_id, order_id, entity_id, is_valid, last_ver, kind)  "
			+ "values(?, ?, ?,  1, 0, 3)";	
	public static final String INSERT_WAITINGORDERDETAIL2_SQL = "insert into waitingorderdetail (waitingorder_id, order_id, entity_id, kind, pay_status, seat_code, status, customerregister_id, is_valid, last_ver )  "
			+ "values(?, ?, ?, ?, ?, ?, ?, ?, 1, 0)";	
	public static final String DELETE_WAITINGORDERDETAIL_SQL = "delete from waitingorderdetail where waitingorder_id=?";
	
	
	// waitingInstanceInfo manipulation sql
	public static final String INSERT_WAITINGINSTANCEINFO_SQL = "insert into waitinginstanceinfo (waitinginstance_id, waitingorder_id, menu_id, entity_id, name, make_id, spec_detail_id, spec_pricemode, num, account_num, price, fee, is_ratio, is_valid, last_ver)  "
			+ "values(?, ?, ?, ?, \"name\", \"makeId\", \"specId\", 0, 8, 0, 0, 0, 0, 1, 0)";	
	
	public static final String INSERT_WAITINGINSTANCEINFO2_SQL = "insert into waitinginstanceinfo (waitinginstance_id, waitingorder_id, menu_id, entity_id, status, kind, batch_msg, name, make_id, spec_detail_id, spec_pricemode, num, account_num, price, fee, is_ratio, is_valid, last_ver)  "
			+ "values(?, ?, ?, ?, ?, ?, ?, \"name\", \"makeId\", \"specId\", 0, 8, 0, 0, 0, 0, 1, 0)";	
	
	public static final String DELETE_WAITINGINSTANCEINFO_SQL = "delete from waitinginstanceinfo where waitinginstance_id=?";
	
	
	// pay manipulation sql
	public static final String INSERT_PAYINFO_SQL = "insert into payinfo (pay_id, totalpay_id, entity_id, pay)  "
			+ "values(?, ?, ?, ?)";	
	public static final String DELETE_PAYINFO_SQL =  "delete from payinfo where pay_id=? and totalpay_id=?";
	
	
	// servicebillinfo manipulation sql
	public static final String INSERT_SERVICEBILLINFO_SQL = "insert into servicebillinfo (servicebill_id, entity_id, agio_amount, agio_service_charge, final_amount)  "
			+ "values(?, ?, ?, ?, ?)";	
	public static final String DELETE_SERVICEBILLINFO_SQL =  "delete from servicebillinfo where servicebill_id=? and entity_id=?";
	
	// seat manipulation sql
	public static final String INSERT_SEAT_SQL = "insert into seat (seat_id, entity_id, code, name)  "
			+ "values(?, ?, ?, ?)";	
	public static final String DELETE_SEAT_SQL =  "delete from seat where seat_id=? and entity_id=?";
	
	public static final String DELETE_MENU_SQL = "delete from menu where menu_id=?";
	public static final String DELETE_ENTITYBYCODE_SQL = "delete from entity where entity_code=?";
	public static final String DELETE_SHOPMANAGERBYENTITYID_SQL = "delete from shop_manager where entity_id=?";
	
	public static final String INSERT_SHOP2_SQL = "insert into shop (id, entity_id) values(?, ?)";
	public static final String INSERT_SHOP3_SQL = "insert into shop (id, entity_id, brand_id, industry, name, code) values(?, ?, ?, ?, ?, ?)";
	
	// qrcode manipulation sql
	public static final String INSERT_QRCODE_SQL = "insert into qrcode (code) values(?)";
	public static final String DELETE_QRCODE_SQL = "delete from qrcode where code=?";
	
		
}
