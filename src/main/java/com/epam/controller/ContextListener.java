package com.epam.controller;

import javax.servlet.ServletContextEvent; 
import javax.servlet.ServletContextListener;

import com.epam.dao.dbutil.DBConnectionPool;

public class ContextListener implements ServletContextListener {

	private DBConnectionPool dbConnection;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		dbConnection.closeConnections();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		dbConnection = DBConnectionPool.getInstance();

	}

}
