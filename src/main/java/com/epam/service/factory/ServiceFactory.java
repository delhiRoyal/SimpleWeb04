package com.epam.service.factory;

import com.epam.service.BookService;
import com.epam.service.UserService;
import com.epam.service.impl.BookServiceImpl;
import com.epam.service.impl.UserServiceImpl;

public class ServiceFactory {

	private UserService userService = new UserServiceImpl();
	private BookService bookService = new BookServiceImpl();

	private ServiceFactory() {

	}

	public static ServiceFactory getInstance() {
		return ServiceFactoryHelper.INSTANCE;
	}

	public UserService getUserService() {
		return userService;
	}

	public BookService getBookService() {
		return bookService;
	}

	private static class ServiceFactoryHelper {
		private static final ServiceFactory INSTANCE = new ServiceFactory();

		private ServiceFactoryHelper() {

		}
	}
}
