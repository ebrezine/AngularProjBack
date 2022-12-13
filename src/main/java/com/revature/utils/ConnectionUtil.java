package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

public class ConnectionUtil {
	private static Connection connection;

	public static Connection getConnection() throws SQLException {
		if (connection != null && !connection.isClosed()) {
			return connection;
		} else {
			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			}
			Dotenv dotenv = Dotenv.load();

			String dbPort = dotenv.get("DB_PORT") != null ? dotenv.get("DB_PORT") : "5433";
			String dbName = dotenv.get("DB_NAME") != null ? dotenv.get("DB_NAME") : "postgres";

			String url = String.format("jdbc:postgresql://localhost:%s/%s", dbPort, dbName);

			String username = "postgres";
			String password = "password";

			connection = DriverManager.getConnection(url, username, password);

			return connection;
		}
	}
}
