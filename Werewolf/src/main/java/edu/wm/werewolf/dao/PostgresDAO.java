package edu.wm.werewolf.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import edu.wm.werewolf.HomeController;

public class PostgresDAO {

	public static Connection establishConnection() {
		// TODO Auto-generated method stub
		 
		try {
 
			Class.forName("org.postgresql.Driver");
 
		} catch (ClassNotFoundException e) {
 
			HomeController.logger.info("Failed to load PostgreSQL JDBC Driver");
			e.printStackTrace();
 
		}
		
		Connection connection = null;
		 
		try {
 
			connection = DriverManager.getConnection(
					"jdbc:postgresql://127.0.0.1:5432/WEREWOLF", "postgres",
					"chipman");
 
		} catch (SQLException e) {
 
			HomeController.logger.info("Connection to database failed");
			e.printStackTrace();
 
		}
 
		if (connection != null) {
			HomeController.logger.info("Database connection successful");
		} else {
			HomeController.logger.info("Connection to database failed");
		}
		
		return connection;
	}
	
	public static ResultSet execQuery(Connection con, String query) {
		try {
			Statement stmt = con.createStatement();
			HomeController.logger.info(query);
			return stmt.executeQuery(query);
		}
		catch (SQLException e) {
			HomeController.logger.info("Query failed: " + e.getMessage());
			return null;
		}

		
	}

}
