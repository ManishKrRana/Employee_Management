package com.ems;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/employee_management";

	static Connection conn;

	// Database Credentials
	static final String user = "root";
	static final String password = "Manish@6710";

	public static Connection createConnection() {

		try {
			// load the driver
			Class.forName(JDBC_DRIVER);

			// create the connection
			conn = DriverManager.getConnection(DB_URL, user, password);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return conn;
	}
}
