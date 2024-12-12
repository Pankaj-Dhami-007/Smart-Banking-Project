package com.bank.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider 
{
	private static Connection con;
	static {
		con=null;
	}
	public static Connection getMySqlConnection()
	{
		//1. load Driver 
		final String URL="jdbc:mysql://localhost:3306/";
		final String DB="mydb?createDatabaseIfNotExist=true";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con=DriverManager.getConnection(URL+DB,"root","1234");
			System.out.println("Connected to MySQL!");
		} 
		catch (ClassNotFoundException e) {
			System.out.println("unable  to load driver");
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}
