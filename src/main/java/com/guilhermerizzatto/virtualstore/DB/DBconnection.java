package com.guilhermerizzatto.virtualstore.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBconnection {
	
	private static Connection conn = null;
	

	public static Connection getConnection(){
		if(conn == null) {
			try {
			conn = DriverManager.getConnection(DBprops.dburl, DBprops.user, DBprops.password);
			}catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return conn;
	}
	
	public static void closeStatement(Statement st) {
		if(st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void closeResultSet(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	


}
