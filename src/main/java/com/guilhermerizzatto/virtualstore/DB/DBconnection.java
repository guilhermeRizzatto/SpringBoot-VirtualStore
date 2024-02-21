package com.guilhermerizzatto.virtualstore.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DBconnection {
	
	private static Connection conn = null;
	
	@Autowired
	private DBprops props;
	
	public Connection getConnection() throws SQLException {
		if(conn == null) {
			conn = DriverManager.getConnection(props.getUrl(), props.getUsername(), props.getPassword());
		}
		return conn;
	}

}
