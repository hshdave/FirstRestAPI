package com.api.mad318.server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {

	private String dbName = "classicmodels";
	private String userName = "admin";
	private String userPass = "harSH3938";
	private String hostName = "database-1.cjeubsgsfckd.us-east-2.rds.amazonaws.com";
	private String port = "3306";
	private String jdbcurl = "jdbc:mysql://"+hostName+":"+port+"/"+dbName+"?user="+userName+"&password="+userPass;
	
	private Connection con=null;
	
	
	public Connection getConnection()
	{
		
		try {
			
			System.out.println("Load Driver");
			//JDBC Driver for MySQL
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			System.out.println("Make Connection To Mysql");
			con = DriverManager.getConnection(jdbcurl);
			
			return con;
			
		} catch (ClassNotFoundException e) {
			System.out.println("Class Not found Error :"+e.getMessage());
			
		} catch (SQLException e) {

			System.out.println("SQL Exception :"+e.getMessage());
		}
		
		return con;
	}
	
}
