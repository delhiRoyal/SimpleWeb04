package com.epam.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.command.Command;
import com.epam.service.UserService;
import com.epam.service.exception.ServiceException;
import com.epam.service.factory.ServiceFactory;

public class GetUserCommand implements Command {

	private static final Logger LOG = Logger.getLogger(GetUserCommand.class);

	private static final String NAME_PARAMETER = "name";

	private static final String USER_DETAIL_FILE = "WEB-INF/jsp/user.jsp";
	private static final String ERROR_FILE = "WEB-INF/jsp/error.jsp";

	private static final String USER_ATTRIBUTE = "user";

	private static final String REDIRECT_TYPE_ATTRIBUTE = "redirectType";

	private static final Object REDIRECT_TYPE_VALUE = "forward";

	private static ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private static UserService userService = serviceFactory.getUserService();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter(NAME_PARAMETER);
		try {

			request.setAttribute(USER_ATTRIBUTE, userService.getUserDetail(name));
			request.setAttribute(REDIRECT_TYPE_ATTRIBUTE, REDIRECT_TYPE_VALUE);
			return USER_DETAIL_FILE;
		} catch (ServiceException e) {
			LOG.error("can't get details", e);
		}
		return ERROR_FILE;
	}

}
