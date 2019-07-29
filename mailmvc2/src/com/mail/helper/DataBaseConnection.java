package com.mail.helper;
import java.sql.*;
public class DataBaseConnection {
	private static Connection con;
	public static Connection getConnection(){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","rajesh");
			
		}
		catch(Exception ee){
			ee.printStackTrace();
		}
		return con;
	}
}
