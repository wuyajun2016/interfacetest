package com.dfire.wechat.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.dfire.wechat.util.CommonConstants;


public class WeChatUtils {
	
private static final Logger logger = Logger.getLogger(WeChatUtils.class);
	

		//seat manipulation
		public static int insertSeatIntoDB(String seatId, String entityId, String code, String name)
		{
			DDBUtils ddbUtils = new DDBUtils();
			PreparedStatement stateMent = null;
			Connection conn = null;
			try {
				conn = ddbUtils.getConnection(CommonConstants.JDBCURL_SEAT);
				stateMent = conn.prepareStatement(DbSQL.INSERT_SEAT_SQL);
				stateMent.setString(1, seatId);
				stateMent.setString(2, entityId);
				stateMent.setString(3, code);
				stateMent.setString(4, name);
				int count = stateMent.executeUpdate();
				logger.info("insert seat into DB success");
				return count;
			} catch (SQLException e) {
				logger.error(e.getMessage());
				return -1;
			} catch (ClassNotFoundException e) {
				logger.error(e.getMessage());
				return -1;
			} finally {
				if (stateMent != null) {
					try {
						stateMent.close();
					} catch (SQLException e) {
						// ignore
					}
				}
				if(conn != null){
					try{
						conn.close();
					}catch(SQLException e){
						logger.error("connection close failed");
					}
				}
			}
		}
		
		
		public static int deleteSeatFromDB(String seatId, String entityId)
		{
			DDBUtils ddbUtils = new DDBUtils();
			PreparedStatement deleteShopRecord = null;
			Connection conn = null;
			try {
				conn = ddbUtils.getConnection(CommonConstants.JDBCURL_SEAT);
				deleteShopRecord = conn.prepareStatement(DbSQL.DELETE_SEAT_SQL);
				deleteShopRecord.setString(1, seatId);
				deleteShopRecord.setString(2, entityId);
				int count = deleteShopRecord.executeUpdate();
				return count;
			} catch (SQLException e) {
				logger.error(e.getMessage());
				return -1;
			} catch (ClassNotFoundException e) {
				logger.error(e.getMessage());
				return -1;
			} finally {
				if (deleteShopRecord != null) {
					try {
						deleteShopRecord.close();
					} catch (SQLException e) {
						// ignore
					}
				}
				if(conn != null){
					try{
						conn.close();
					}catch(SQLException e){
						logger.error("connection close failed");
					}
				}
			}
		}
		
		
		
		
		//service bill info manipulation
		public static int insertServiceBillInfoIntoDB(String serviceBillId, String entityId, 
				double agio_amount, double agio_service_charge, double final_amount)
		{
			DDBUtils ddbUtils = new DDBUtils();
			PreparedStatement stateMent = null;
			Connection conn = null;
			try {
				conn = ddbUtils.getConnection(CommonConstants.JDBCURL_ORDER);
				stateMent = conn.prepareStatement(DbSQL.INSERT_SERVICEBILLINFO_SQL);
				stateMent.setString(1, serviceBillId);
				stateMent.setString(2, entityId);
				stateMent.setDouble(3, agio_amount);
				stateMent.setDouble(4, agio_service_charge);
				stateMent.setDouble(5, final_amount);
				int count = stateMent.executeUpdate();
				logger.info("insert service bill info into DB success");
				return count;
			} catch (SQLException e) {
				logger.error(e.getMessage());
				return -1;
			} catch (ClassNotFoundException e) {
				logger.error(e.getMessage());
				return -1;
			} finally {
				if (stateMent != null) {
					try {
						stateMent.close();
					} catch (SQLException e) {
						// ignore
					}
				}
				if(conn != null){
					try{
						conn.close();
					}catch(SQLException e){
						logger.error("connection close failed");
					}
				}
			}
		}


		
		public static int deleteServiceBillInfoFromDB(String serviceBillId, String entityId)
		{
			DDBUtils ddbUtils = new DDBUtils();
			PreparedStatement deleteShopRecord = null;
			Connection conn = null;
			try {
				conn = ddbUtils.getConnection(CommonConstants.JDBCURL_ORDER);
				deleteShopRecord = conn.prepareStatement(DbSQL.DELETE_SERVICEBILLINFO_SQL);
				deleteShopRecord.setString(1, serviceBillId);
				deleteShopRecord.setString(2, entityId);
				int count = deleteShopRecord.executeUpdate();
				return count;
			} catch (SQLException e) {
				logger.error(e.getMessage());
				return -1;
			} catch (ClassNotFoundException e) {
				logger.error(e.getMessage());
				return -1;
			} finally {
				if (deleteShopRecord != null) {
					try {
						deleteShopRecord.close();
					} catch (SQLException e) {
						// ignore
					}
				}
				if(conn != null){
					try{
						conn.close();
					}catch(SQLException e){
						logger.error("connection close failed");
					}
				}
			}
		}
		
		
		// pay manipulation
		public static int insertPayInfoIntoDB(String payId, String totalPayId, String entityId, int pay)
		{
			DDBUtils ddbUtils = new DDBUtils();
			PreparedStatement stateMent = null;
			Connection conn = null;
			try {
				conn = ddbUtils.getConnection(CommonConstants.JDBCURL_ORDER);
				stateMent = conn.prepareStatement(DbSQL.INSERT_PAYINFO_SQL);
				stateMent.setString(1, payId);
				stateMent.setString(2, totalPayId);
				stateMent.setString(3, entityId);
				stateMent.setInt(4, pay);
				int count = stateMent.executeUpdate();
				logger.info("insert pay into DB success");
				return count;
			} catch (SQLException e) {
				logger.error(e.getMessage());
				return -1;
			} catch (ClassNotFoundException e) {
				logger.error(e.getMessage());
				return -1;
			} finally {
				if (stateMent != null) {
					try {
						stateMent.close();
					} catch (SQLException e) {
						// ignore
					}
				}
				if(conn != null){
					try{
						conn.close();
					}catch(SQLException e){
						logger.error("connection close failed");
					}
				}
			}
		}


		
		public static int deletePayInfoFromDB(String payId, String totalPayId)
		{
			DDBUtils ddbUtils = new DDBUtils();
			PreparedStatement deleteShopRecord = null;
			Connection conn = null;
			try {
				conn = ddbUtils.getConnection(CommonConstants.JDBCURL_ORDER);
				deleteShopRecord = conn.prepareStatement(DbSQL.DELETE_PAYINFO_SQL);
				deleteShopRecord.setString(1, payId);
				deleteShopRecord.setString(2, totalPayId);
				int count = deleteShopRecord.executeUpdate();
				return count;
			} catch (SQLException e) {
				logger.error(e.getMessage());
				return -1;
			} catch (ClassNotFoundException e) {
				logger.error(e.getMessage());
				return -1;
			} finally {
				if (deleteShopRecord != null) {
					try {
						deleteShopRecord.close();
					} catch (SQLException e) {
						// ignore
					}
				}
				if(conn != null){
					try{
						conn.close();
					}catch(SQLException e){
						logger.error("connection close failed");
					}
				}
			}
		}
		
		

		//waitingInstanceInfo manipulation
		public static int insertWaitingInstanceInfoIntoDB(String waitingInstanceId, String waitingOrderId, String menuId, String entityId)
		{
			DDBUtils ddbUtils = new DDBUtils();
			PreparedStatement stateMent = null;
			Connection conn = null;
			try {
				conn = ddbUtils.getConnection(CommonConstants.JDBCURL_ORDER);
				stateMent = conn.prepareStatement(DbSQL.INSERT_WAITINGINSTANCEINFO_SQL);
				stateMent.setString(1, waitingInstanceId);
				stateMent.setString(2, waitingOrderId);
				stateMent.setString(3, menuId);
				stateMent.setString(4, entityId);
				int count = stateMent.executeUpdate();
				logger.info("insert waitingInstanceInfo into DB success");
				return count;
			} catch (SQLException e) {
				logger.error(e.getMessage());
				return -1;
			} catch (ClassNotFoundException e) {
				logger.error(e.getMessage());
				return -1;
			} finally {
				if (stateMent != null) {
					try {
						stateMent.close();
					} catch (SQLException e) {
						// ignore
					}
				}
				if(conn != null){
					try{
						conn.close();
					}catch(SQLException e){
						logger.error("connection close failed");
					}
				}
			}
		}
		
		public static int insertWaitingInstanceInfoIntoDB(String waitingInstanceId, String waitingOrderId, String menuId, 
				String entityId, int orderStatus, int kind, String batchMsg)
		{
			DDBUtils ddbUtils = new DDBUtils();
			PreparedStatement stateMent = null;
			Connection conn = null;
			try {
				conn = ddbUtils.getConnection(CommonConstants.JDBCURL_ORDER);
				stateMent = conn.prepareStatement(DbSQL.INSERT_WAITINGINSTANCEINFO2_SQL);
				stateMent.setString(1, waitingInstanceId);
				stateMent.setString(2, waitingOrderId);
				stateMent.setString(3, menuId);
				stateMent.setString(4, entityId);
				stateMent.setInt(5, orderStatus);
				stateMent.setInt(6, kind);
				stateMent.setString(7, batchMsg);
				int count = stateMent.executeUpdate();
				logger.info("insert waitingInstanceInfo into DB success");
				return count;
			} catch (SQLException e) {
				logger.error(e.getMessage());
				return -1;
			} catch (ClassNotFoundException e) {
				logger.error(e.getMessage());
				return -1;
			} finally {
				if (stateMent != null) {
					try {
						stateMent.close();
					} catch (SQLException e) {
						// ignore
					}
				}
				if(conn != null){
					try{
						conn.close();
					}catch(SQLException e){
						logger.error("connection close failed");
					}
				}
			}
		}
		
		
		public static int deleteWaitingInstanceFromDB(String waitingInstanceId)
		{
			DDBUtils ddbUtils = new DDBUtils();
			PreparedStatement deleteShopRecord = null;
			Connection conn = null;
			try {
				conn = ddbUtils.getConnection(CommonConstants.JDBCURL_ORDER);
				deleteShopRecord = conn.prepareStatement(DbSQL.DELETE_WAITINGINSTANCEINFO_SQL);
				deleteShopRecord.setString(1, waitingInstanceId);
				int count = deleteShopRecord.executeUpdate();
				return count;
			} catch (SQLException e) {
				logger.error(e.getMessage());
				return -1;
			} catch (ClassNotFoundException e) {
				logger.error(e.getMessage());
				return -1;
			} finally {
				if (deleteShopRecord != null) {
					try {
						deleteShopRecord.close();
					} catch (SQLException e) {
						// ignore
					}
				}
				if(conn != null){
					try{
						conn.close();
					}catch(SQLException e){
						logger.error("connection close failed");
					}
				}
			}
		}
		
		

		//waitingorderdetail manipulation
		public static int insertWaitingOrderDetailIntoDB(String waitingOrderId, String orderId, String entityId)
		{
			DDBUtils ddbUtils = new DDBUtils();
			PreparedStatement stateMent = null;
			Connection conn = null;
			try {
				conn = ddbUtils.getConnection(CommonConstants.JDBCURL_ORDER);
				stateMent = conn.prepareStatement(DbSQL.INSERT_WAITINGORDERDETAIL_SQL);
				stateMent.setString(1, waitingOrderId);
				stateMent.setString(2, orderId);
				stateMent.setString(3, entityId);
				int count = stateMent.executeUpdate();
				logger.info("insert waitingorderdetail into DB success");
				return count;
			} catch (SQLException e) {
				logger.error(e.getMessage());
				return -1;
			} catch (ClassNotFoundException e) {
				logger.error(e.getMessage());
				return -1;
			} finally {
				if (stateMent != null) {
					try {
						stateMent.close();
					} catch (SQLException e) {
						// ignore
					}
				}
				if(conn != null){
					try{
						conn.close();
					}catch(SQLException e){
						logger.error("connection close failed");
					}
				}
			}
		}
		
		
		public static int insertWaitingOrderDetailIntoDB(String waitingOrderId, String orderId, String entityId, 
				int kind, int pay_status, String seat_code, int status, String customerregister_id)
		{
			DDBUtils ddbUtils = new DDBUtils();
			PreparedStatement stateMent = null;
			Connection conn = null;
			try {
				conn = ddbUtils.getConnection(CommonConstants.JDBCURL_ORDER);
				stateMent = conn.prepareStatement(DbSQL.INSERT_WAITINGORDERDETAIL2_SQL);
				stateMent.setString(1, waitingOrderId);
				stateMent.setString(2, orderId);
				stateMent.setString(3, entityId);
				stateMent.setInt(4, kind);
				stateMent.setInt(5, pay_status);
				stateMent.setString(6, seat_code);
				stateMent.setInt(7, status);
				stateMent.setString(8, customerregister_id);
				int count = stateMent.executeUpdate();
				logger.info("insert waitingorderdetail into DB success");
				return count;
			} catch (SQLException e) {
				logger.error(e.getMessage());
				return -1;
			} catch (ClassNotFoundException e) {
				logger.error(e.getMessage());
				return -1;
			} finally {
				if (stateMent != null) {
					try {
						stateMent.close();
					} catch (SQLException e) {
						// ignore
					}
				}
				if(conn != null){
					try{
						conn.close();
					}catch(SQLException e){
						logger.error("connection close failed");
					}
				}
			}
		}
		
		
		public static int deleteWaitingOrderDetailFromDB(String waitingOrderId)
		{
			DDBUtils ddbUtils = new DDBUtils();
			PreparedStatement deleteShopRecord = null;
			Connection conn = null;
			try {
				conn = ddbUtils.getConnection(CommonConstants.JDBCURL_ORDER);
				deleteShopRecord = conn.prepareStatement(DbSQL.DELETE_WAITINGORDERDETAIL_SQL);
				deleteShopRecord.setString(1, waitingOrderId);
				int count = deleteShopRecord.executeUpdate();
				return count;
			} catch (SQLException e) {
				logger.error(e.getMessage());
				return -1;
			} catch (ClassNotFoundException e) {
				logger.error(e.getMessage());
				return -1;
			} finally {
				if (deleteShopRecord != null) {
					try {
						deleteShopRecord.close();
					} catch (SQLException e) {
						// ignore
					}
				}
				if(conn != null){
					try{
						conn.close();
					}catch(SQLException e){
						logger.error("connection close failed");
					}
				}
			}
		}
		
		
		
		//orderdetail manipulation
		public static int insertOrderDetailIntoDB(String orderId, String waitingOrderId, String entityId, int loadTime)
		{
			DDBUtils ddbUtils = new DDBUtils();
			PreparedStatement stateMent = null;
			Connection conn = null;
			try {
				conn = ddbUtils.getConnection(CommonConstants.JDBCURL_ORDER);
				stateMent = conn.prepareStatement(DbSQL.INSERT_ORDERDETAIL_SQL);
				stateMent.setString(1, orderId);
				stateMent.setString(2, waitingOrderId);
				stateMent.setString(3, entityId);
				stateMent.setInt(4, loadTime);
				int count = stateMent.executeUpdate();
				logger.info("insert orderdetail into DB success");
				return count;
			} catch (SQLException e) {
				logger.error(e.getMessage());
				return -1;
			} catch (ClassNotFoundException e) {
				logger.error(e.getMessage());
				return -1;
			} finally {
				if (stateMent != null) {
					try {
						stateMent.close();
					} catch (SQLException e) {
						// ignore
					}
				}
				if(conn != null){
					try{
						conn.close();
					}catch(SQLException e){
						logger.error("connection close failed");
					}
				}
			}
		}
	
	
		
		public static int insertOrderDetailIntoDB(String orderId, String waitingOrderId, String entityId, int loadTime, 
				String seatCode, String customerregister_id, String totalpay_id)
		{
			DDBUtils ddbUtils = new DDBUtils();
			PreparedStatement stateMent = null;
			Connection conn = null;
			try {
				conn = ddbUtils.getConnection(CommonConstants.JDBCURL_ORDER);
				stateMent = conn.prepareStatement(DbSQL.INSERT_ORDERDETAIL2_SQL);
				stateMent.setString(1, orderId);
				stateMent.setString(2, waitingOrderId);
				stateMent.setString(3, entityId);
				stateMent.setInt(4, loadTime);
				stateMent.setString(5, seatCode);
				stateMent.setString(6, customerregister_id);
				stateMent.setString(7, totalpay_id);
				int count = stateMent.executeUpdate();
				logger.info("insert orderdetail into DB success");
				return count;
			} catch (SQLException e) {
				logger.error(e.getMessage());
				return -1;
			} catch (ClassNotFoundException e) {
				logger.error(e.getMessage());
				return -1;
			} finally {
				if (stateMent != null) {
					try {
						stateMent.close();
					} catch (SQLException e) {
						// ignore
					}
				}
				if(conn != null){
					try{
						conn.close();
					}catch(SQLException e){
						logger.error("connection close failed");
					}
				}
			}
		}
		
		
		public static int deleteOrderDetailFromDB(String orderId)
		{
			DDBUtils ddbUtils = new DDBUtils();
			PreparedStatement deleteShopRecord = null;
			Connection conn = null;
			try {
				conn = ddbUtils.getConnection(CommonConstants.JDBCURL_ORDER);
				deleteShopRecord = conn.prepareStatement(DbSQL.DELETE_ORDERDETAIL_SQL);
				deleteShopRecord.setString(1, orderId);
				int count = deleteShopRecord.executeUpdate();
				return count;
			} catch (SQLException e) {
				logger.error(e.getMessage());
				return -1;
			} catch (ClassNotFoundException e) {
				logger.error(e.getMessage());
				return -1;
			} finally {
				if (deleteShopRecord != null) {
					try {
						deleteShopRecord.close();
					} catch (SQLException e) {
						// ignore
					}
				}
				if(conn != null){
					try{
						conn.close();
					}catch(SQLException e){
						logger.error("connection close failed");
					}
				}
			}
		}
		
		
		//menu manipulation
		public static int insertChildMenuIntoDB(String priId, String menuId, String suitMenuPri, String entityId)
		{
			DDBUtils ddbUtils = new DDBUtils();
			PreparedStatement stateMent = null;
			Connection conn = null;
			try {
				conn = ddbUtils.getConnection();
				stateMent = conn.prepareStatement(DbSQL.INSERT_MENU_CHANGE_SQL);
				stateMent.setString(1, priId);
				stateMent.setString(2, menuId);
				stateMent.setString(3, suitMenuPri);
				stateMent.setString(4, entityId);
				int count = stateMent.executeUpdate();
				logger.info("insert suitMenu into DB success");
				return count;
			} catch (SQLException e) {
				logger.error(e.getMessage());
				return -1;
			} catch (ClassNotFoundException e) {
				logger.error(e.getMessage());
				return -1;
			} finally {
				if (stateMent != null) {
					try {
						stateMent.close();
					} catch (SQLException e) {
						// ignore
					}
				}
				if(conn != null){
					try{
						conn.close();
					}catch(SQLException e){
						logger.error("connection close failed");
					}
				}
			}
		}
		
		
		
		// menu manipulation
		public static int insertSuitMenuIntoDB(String suitMenuId, String menuId, String entityId)
		{
			DDBUtils ddbUtils = new DDBUtils();
			PreparedStatement stateMent = null;
			Connection conn = null;
			try {
				conn = ddbUtils.getConnection();
				stateMent = conn.prepareStatement(DbSQL.INSERT_SUITMENU_SQL);
				stateMent.setString(1, suitMenuId);
				stateMent.setString(2, menuId);
				stateMent.setString(3, entityId);
				int count = stateMent.executeUpdate();
				logger.info("insert suitMenu into DB success");
				return count;
			} catch (SQLException e) {
				logger.error(e.getMessage());
				return -1;
			} catch (ClassNotFoundException e) {
				logger.error(e.getMessage());
				return -1;
			} finally {
				if (stateMent != null) {
					try {
						stateMent.close();
					} catch (SQLException e) {
						// ignore
					}
				}
				if(conn != null){
					try{
						conn.close();
					}catch(SQLException e){
						logger.error("connection close failed");
					}
				}
			}
		}


	// menu manipulation
	public static int insertMenuIntoDB(String menuId, String entityId, String kindMenuId)
	{
		DDBUtils ddbUtils = new DDBUtils();
		PreparedStatement stateMent = null;
		Connection conn = null;
		try {
			conn = ddbUtils.getConnection();
			stateMent = conn.prepareStatement(DbSQL.INSERT_MENU_SQL);
			stateMent.setString(1, menuId);
			stateMent.setString(2, entityId);
			stateMent.setString(3, kindMenuId);
			int count = stateMent.executeUpdate();
			logger.info("insert menu into DB success");
			return count;
		} catch (SQLException e) {
			logger.error(e.getMessage());
			return -1;
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage());
			return -1;
		} finally {
			if (stateMent != null) {
				try {
					stateMent.close();
				} catch (SQLException e) {
					// ignore
				}
			}
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					logger.error("connection close failed");
				}
			}
		}
	}
	
	public static int insertMenuWithChildIntoDB(String menuId, String entityId, String kindMenuId)
	{
		DDBUtils ddbUtils = new DDBUtils();
		PreparedStatement stateMent = null;
		Connection conn = null;
		try {
			conn = ddbUtils.getConnection();
			stateMent = conn.prepareStatement(DbSQL.INSERT_MENU_WITHCHILD_SQL);
			stateMent.setString(1, menuId);
			stateMent.setString(2, entityId);
			stateMent.setString(3, kindMenuId);
			int count = stateMent.executeUpdate();
			logger.info("insert menu into DB success");
			return count;
		} catch (SQLException e) {
			logger.error(e.getMessage());
			return -1;
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage());
			return -1;
		} finally {
			if (stateMent != null) {
				try {
					stateMent.close();
				} catch (SQLException e) {
					// ignore
				}
			}
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					logger.error("connection close failed");
				}
			}
		}
	}
	
	
	public static int deleteSuitMenuFromDB(String suitMenuId)
	{
		DDBUtils ddbUtils = new DDBUtils();
		PreparedStatement deleteShopRecord = null;
		Connection conn = null;
		try {
			conn = ddbUtils.getConnection();
			deleteShopRecord = conn.prepareStatement(DbSQL.DELETE_MENU_SQL);
			deleteShopRecord.setString(1, suitMenuId);
			int count = deleteShopRecord.executeUpdate();
			return count;
		} catch (SQLException e) {
			logger.error(e.getMessage());
			return -1;
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage());
			return -1;
		} finally {
			if (deleteShopRecord != null) {
				try {
					deleteShopRecord.close();
				} catch (SQLException e) {
					// ignore
				}
			}
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					logger.error("connection close failed");
				}
			}
		}
	}
	
	
	
	public static int deleteMenuFromDB(String menuId)
	{
		DDBUtils ddbUtils = new DDBUtils();
		PreparedStatement deleteShopRecord = null;
		Connection conn = null;
		try {
			conn = ddbUtils.getConnection();
			deleteShopRecord = conn.prepareStatement(DbSQL.DELETE_MENU_SQL);
			deleteShopRecord.setString(1, menuId);
			int count = deleteShopRecord.executeUpdate();
			return count;
		} catch (SQLException e) {
			logger.error(e.getMessage());
			return -1;
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage());
			return -1;
		} finally {
			if (deleteShopRecord != null) {
				try {
					deleteShopRecord.close();
				} catch (SQLException e) {
					// ignore
				}
			}
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					logger.error("connection close failed");
				}
			}
		}
	}
	
	
	public static int deleteChildMenuFromDB(String menuPriId)
	{
		DDBUtils ddbUtils = new DDBUtils();
		PreparedStatement deleteShopRecord = null;
		Connection conn = null;
		try {
			conn = ddbUtils.getConnection();
			deleteShopRecord = conn.prepareStatement(DbSQL.DELETE_MENU_CHANGE_SQL);
			deleteShopRecord.setString(1, menuPriId);
			int count = deleteShopRecord.executeUpdate();
			return count;
		} catch (SQLException e) {
			logger.error(e.getMessage());
			return -1;
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage());
			return -1;
		} finally {
			if (deleteShopRecord != null) {
				try {
					deleteShopRecord.close();
				} catch (SQLException e) {
					// ignore
				}
			}
			if(conn != null){
				try{
					conn.close();
				}catch(SQLException e){
					logger.error("connection close failed");
				}
			}
		}
	}
	
	
	
	// qrcode manipulation
		public static int insertQRcodeIntoDB(String code)
		{
			DDBUtils ddbUtils = new DDBUtils();
			PreparedStatement stateMent = null;
			Connection conn = null;
			try {
				conn = ddbUtils.getConnection(CommonConstants.JDBCURL_QRCODE);
				stateMent = conn.prepareStatement(DbSQL.INSERT_QRCODE_SQL);
				stateMent.setString(1, code);
				int count = stateMent.executeUpdate();
				logger.info("insert menu into DB success");
				return count;
			} catch (SQLException e) {
				logger.error(e.getMessage());
				return -1;
			} catch (ClassNotFoundException e) {
				logger.error(e.getMessage());
				return -1;
			} finally {
				if (stateMent != null) {
					try {
						stateMent.close();
					} catch (SQLException e) {
						// ignore
					}
				}
				if(conn != null){
					try{
						conn.close();
					}catch(SQLException e){
						logger.error("connection close failed");
					}
				}
			}
		}
		
		
		public static int deleteQRcodeFromDB(String code)
		{
			DDBUtils ddbUtils = new DDBUtils();
			PreparedStatement deleteShopRecord = null;
			Connection conn = null;
			try {
				conn = ddbUtils.getConnection(CommonConstants.JDBCURL_QRCODE);
				deleteShopRecord = conn.prepareStatement(DbSQL.DELETE_QRCODE_SQL);
				deleteShopRecord.setString(1, code);
				int count = deleteShopRecord.executeUpdate();
				return count;
			} catch (SQLException e) {
				logger.error(e.getMessage());
				return -1;
			} catch (ClassNotFoundException e) {
				logger.error(e.getMessage());
				return -1;
			} finally {
				if (deleteShopRecord != null) {
					try {
						deleteShopRecord.close();
					} catch (SQLException e) {
						// ignore
					}
				}
				if(conn != null){
					try{
						conn.close();
					}catch(SQLException e){
						logger.error("connection close failed");
					}
				}
			}
		}

}
