package com.solusi247.reportManagementApi.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
	
	public static Connection getConnection() {
		try {
//			String connectionURL = "jdbc:mysql://localhost:3306/report_management"; //Laptop Edityo
			String connectionURL = "jdbc:mysql://192.168.1.228:3306/report_management"; //Server Solusi
			Connection connection = null;
			Class.forName("com.mysql.jdbc.Driver");
//			connection = DriverManager.getConnection(connectionURL, "root", ""); //Laptop Edityo
			connection = DriverManager.getConnection(connectionURL, "reportManagement", "1234"); //Server Solusi
			return connection;
		} catch (Exception e) {
//			throw e;
			e.printStackTrace();
			return null;
		}
	}

}
