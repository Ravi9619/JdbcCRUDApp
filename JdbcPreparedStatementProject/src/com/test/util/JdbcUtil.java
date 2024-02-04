package com.test.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JdbcUtil {
	
	public static Connection getJdbConnection() throws IOException, SQLException {
		
		FileInputStream fis = new FileInputStream("C:\\Users\\VERMA_JI_KA_LAUNDA\\eclipse-workspace\\JdbcPreparedStatementProject\\src\\com\\test\\properties\\application.properties");
		Properties properties = new Properties();
		properties.load(fis);
		
		Connection connection = DriverManager.getConnection(properties.getProperty("url"),
				properties.getProperty("username"),properties.getProperty("password"));
		System.out.println("Connection object created ");
		return connection;
		
	}
	
	public static void cleanUp(Connection connection,Statement statement, ResultSet resultSet) throws SQLException {
		
		if(connection != null) {
			connection.close();
		}
		if(statement != null) {
			statement.close();
		}
		if(resultSet != null) {
			resultSet.close();
		}
	}

}
