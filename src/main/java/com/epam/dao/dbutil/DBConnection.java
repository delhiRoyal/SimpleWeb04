package com.epam.dao.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import com.epam.dao.exception.DAOFailureException;

public class DBConnection {

	private ResourceBundle resourceBundle = ResourceBundle.getBundle("com.epam.database.database");

	private static final String DRIVER_KEY = "jdbc.driver";
	private static final String URL_KEY = "db.url";
	private static final String USER_KEY = "user";
	private static final String PASS_KEY = "pass";
	private static final Logger LOG = Logger.getLogger(DBConnection.class);

	private final String driver = resourceBundle.getString(DRIVER_KEY);
	private final String dbURL = resourceBundle.getString(URL_KEY);
	private final String user = resourceBundle.getString(USER_KEY);
	private final String pass = resourceBundle.getString(PASS_KEY);

	private DBConnection() {

		setDriver();

	}

	public static DBConnection getInstance() {
		return DBConnectionHelper.dbConnection;
	}

	private void setDriver() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			LOG.fatal("Can't find the Driver", e);
			throw new DAOFailureException("Error finding the driver");
		}
	}

	public Connection getConnection() {
		Connection connection;
		try {
			connection = DriverManager.getConnection(dbURL, user, pass);
		} catch (SQLException e) {
			LOG.fatal("Unable to open a connection", e);
			throw new RuntimeException("Error opening the connection");
		}
		return connection;
	}

	public void closeStatement(PreparedStatement statement) {
		try {
			if (statement != null)
				statement.close();

		} catch (SQLException e) {
			LOG.error("Unable to close statement", e);
		}
	}

	public void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException e) {
			LOG.error("Unable to close connections", e);
		}
	}

	private static class DBConnectionHelper {
		private static DBConnection dbConnection = new DBConnection();

		private DBConnectionHelper() {

		}
	}

}
