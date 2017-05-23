package com.epam.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.command.Command;
import com.epam.domain.User;
import com.epam.service.UserService;
import com.epam.service.exception.ServiceException;
import com.epam.service.factory.ServiceFactory;
import com.epam.util.Hashing;

public class SignUpCommand implements Command {

	private static final Logger LOG = Logger.getLogger(SignUpCommand.class);

	private static final String NAME_PARAMETER = "name";

	private static final String PASSWORD_PARAMETER = "password";

	private static final String REPEATED_PASSWORD_PARAMETER = "repeatPassword";

	private static final String EXCEPTION_MESSAGE = "can't add the user";

	private static final String REDIRECT_TYPE_ATTRIBUTE = "redirectType";

	private static final String REDIRECT = "redirect";

	private static final String FILE_ON_SUCCESS = "index.jsp";

	private static final String SUCCESS_MESSAGE = "successfull";

	private static final String ALREADY_EXIST_MESSAGE = "alreadyexisting";

	public static final String SIGN_UP_MESSAGE_ATTRIBUTE = "signUpMessage";

	public static final String FILE_IF_NAME_ALREADY_EXIST = "signup";

	private static final String FILE_ON_ERROR = "error.jsp";

	private static ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private static UserService userService = serviceFactory.getUserService();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		User user = new User();
		String name = request.getParameter(NAME_PARAMETER);
		String password = request.getParameter(PASSWORD_PARAMETER);
		String repeatedPassword = request.getParameter(REPEATED_PASSWORD_PARAMETER);
		System.out.println(name + password + repeatedPassword);
		if (name != null && password != null && password.equals(repeatedPassword)) {
			user.setName(name);
			user.setPassword(Hashing.generateHash(password));
			String success = addUser(user);
			request.setAttribute(REDIRECT_TYPE_ATTRIBUTE, REDIRECT);
			if (success.equals(SUCCESS_MESSAGE)) {
				request.getSession().setAttribute(SIGN_UP_MESSAGE_ATTRIBUTE, true);
				return FILE_ON_SUCCESS;
			}
			if (success.equals(ALREADY_EXIST_MESSAGE)) {
				request.getSession().setAttribute(SIGN_UP_MESSAGE_ATTRIBUTE, true);
				return FILE_IF_NAME_ALREADY_EXIST;
			}

		}
		return FILE_ON_ERROR;
	}

	private static String addUser(User user) {
		String response = null;
		try {
			response = userService.addUser(user);
		} catch (ServiceException e) {
			response = "exception";
			LOG.error(EXCEPTION_MESSAGE, e);
		}
		return response;
	}

}
