package com.epam.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.util.Hashing;
import com.epam.command.Command;
import com.epam.domain.User;
import com.epam.service.UserService;
import com.epam.service.exception.ServiceException;
import com.epam.service.factory.ServiceFactory;

public class LoginCommand implements Command {

	private static final Logger LOG = Logger.getLogger(LoginCommand.class);

	public static final String NAME_PARAMETER = "name";
	public static final String PASSWORD_PARAMETER = "password";
	public static final String REDIRECT_TYPE_ATTRIBUTE = "redirectType";
	public static final String REDIRECT = "redirect";
	public static final String FORWARD = "forward";
	public static final String MESSAGE_ATTRIBUTE = "message";
	public static final String FILE_ON_AUTHENTICATION_FAILURE = "index.jsp";
	public static final String USER = "user";
	public static final String ADMINISTRATOR = "administrator";
	public static final String FILE_ON_USER_LOGIN = "userhome";
	public static final String FILE_ON_ADMINISTRATOR_LOGIN = "administratorhome";
	public static final String FILE_ON_ERROR = "error.jsp";
	public static final String EXCEPTION_MESSAGE = "can't find the user";

	private static ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private static UserService userService = serviceFactory.getUserService();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		User user = null;
		String name = request.getParameter(NAME_PARAMETER);
		String password = request.getParameter(PASSWORD_PARAMETER);
		if (name != null && password != null) {
			user = getUser(name);
			if (user != null && ifCorrectCredentials(password, user.getPassword())) {
				request.getSession().setAttribute(USER, user);
				request.setAttribute(REDIRECT_TYPE_ATTRIBUTE, REDIRECT);
				return getRedirectFileAccordingToRole(user.getRole());
			}
		}
		request.setAttribute(MESSAGE_ATTRIBUTE, true);
		request.setAttribute(REDIRECT_TYPE_ATTRIBUTE, FORWARD);
		return FILE_ON_AUTHENTICATION_FAILURE;

	}

	private static User getUser(String name) {
		User user = null;
		try {
			user = userService.getUserDetail(name);
		} catch (ServiceException e) {
			LOG.error(EXCEPTION_MESSAGE, e);
		}

		return user;
	}

	private static boolean ifCorrectCredentials(String passwordEntered, String userPassword) {

		return Hashing.generateHash(passwordEntered).equals(userPassword);

	}

	private static String getRedirectFileAccordingToRole(String role) {
		if (USER.equals(role))
			return FILE_ON_USER_LOGIN;
		else if (ADMINISTRATOR.equals(role))
			return FILE_ON_ADMINISTRATOR_LOGIN;
		else
			return FILE_ON_ERROR;
	}

}
