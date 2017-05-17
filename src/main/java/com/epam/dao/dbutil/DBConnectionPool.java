package com.epam.dao.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.apache.log4j.Logger;

public class DBConnectionPool {

	private ResourceBundle resourceBundle = ResourceBundle.getBundle("com.epam.database.database");

	private static final String DRIVER_KEY = "jdbc.driver";
	private static final String URL_KEY = "db.url";
	private static final String USER_KEY = "user";
	private static final String PASS_KEY = "pass";
	private static final int CONNECTION_COUNT = 5;

	private static final Logger LOG = Logger.getLogger(DBConnectionPool.class);

	private final String driver = resourceBundle.getString(DRIVER_KEY);
	private final String dbURL = resourceBundle.getString(URL_KEY);
	private final String user = resourceBundle.getString(USER_KEY);
	private final String pass = resourceBundle.getString(PASS_KEY);

	private BlockingQueue<Connection> availableConnections = new ArrayBlockingQueue<>(CONNECTION_COUNT);
	private BlockingQueue<Connection> usedConnections = new ArrayBlockingQueue<>(CONNECTION_COUNT);

	private DBConnectionPool() {

		setDriver();

		for (int count = 0; count < CONNECTION_COUNT; count++)
			try {
				availableConnections.put(openConnection());
			} catch (InterruptedException e) {
				LOG.error("Can't add into the blockingQueue", e);
			}
	}

	public static DBConnectionPool getInstance() {
		return DBConnectionHelper.dbConnection;
	}

	private void setDriver() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			LOG.fatal("Can't find the Driver", e);
			throw new RuntimeException("Error finding the driver");
		}
	}

	private Connection openConnection() {
		Connection connection;
		try {
			connection = DriverManager.getConnection(dbURL, user, pass);
		} catch (SQLException e) {
			LOG.fatal("Unable to open a connection", e);
			throw new RuntimeException("Error opening the connection");
		}
		return connection;
	}

	public Connection getConnection() throws InterruptedException {
		Connection connection = availableConnections.take();
		usedConnections.put(connection);
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

	public void returnConnectionToPool(Connection connection) {
		try {
			if (connection != null)
				usedConnections.remove(connection);
			availableConnections.put(connection);
		} catch (InterruptedException e) {
			LOG.error("Unable to return connection to the availableConnections queue", e);
		}

	}

	public void closeConnections() {
		Iterator<Connection> iterator = availableConnections.iterator();
		while (iterator.hasNext()) {
			try {
				Connection connection = availableConnections.take();
				connection.close();
			} catch (SQLException | InterruptedException e) {
				LOG.error("Unable to close connections", e);
			}
		}
	}

	private static class DBConnectionHelper {
		private static DBConnectionPool dbConnection = new DBConnectionPool();

		private DBConnectionHelper() {

		}
	}

}
