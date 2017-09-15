package com.dfire.wechat.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.dfire.wechat.util.CommonConstants;


public class DDBUtils {
	    private static final Logger logger = Logger.getLogger(DDBUtils.class);
		protected Connection con = null;

		
		public DDBUtils() {
		}

		public Connection getConnection() throws SQLException, ClassNotFoundException {

			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(CommonConstants.JDBCURL);
			
		}
		
		
		public Connection getConnection(String path) throws SQLException, ClassNotFoundException {

			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(path);
			
		}
		
		public long queryRecordCount(String count_sql) throws ClassNotFoundException {
			Connection conn = null;
			PreparedStatement select = null;
			ResultSet rs = null;
			long count = -1;
			try {
				conn = getConnection();
				select = conn.prepareStatement(count_sql);	
				rs = select.executeQuery();
				while(rs.next()){
					return rs.getLong(1);
				}		
			} catch (SQLException e) {
				logger.error("unexpect SQLException:", e);
			} finally {
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {// ignore
					}
				}
				if (select != null) {
					try {
						select.close();
					} catch (SQLException e) {// ignore
					}
				}
			}
			return count;
		}		

}
