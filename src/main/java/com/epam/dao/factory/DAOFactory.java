package com.epam.dao.factory;

import com.epam.dao.BookDAO;
import com.epam.dao.UserDAO;
import com.epam.dao.impl.BookDAOImpl;
import com.epam.dao.impl.UserDAOImpl;

public class DAOFactory {

	private UserDAO userDAO = new UserDAOImpl();
	private BookDAO bookDAO = new BookDAOImpl();

	private DAOFactory() {

	}

	public static DAOFactory getInstance() {
		return DAOFactoryHelper.INSTANCE;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public BookDAO getBookDAO() {
		return bookDAO;
	}

	private static class DAOFactoryHelper {
		private static final DAOFactory INSTANCE = new DAOFactory();

		private DAOFactoryHelper() {

		}
	}
}
