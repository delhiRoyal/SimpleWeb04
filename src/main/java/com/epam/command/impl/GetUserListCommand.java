package com.epam.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.command.Command;
import com.epam.service.UserService;
import com.epam.service.exception.ServiceException;
import com.epam.service.factory.ServiceFactory;

public class GetUserListCommand implements Command {

	private static ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private static UserService userService = serviceFactory.getUserService();

	private static final Logger LOG = Logger.getLogger(GetUserListCommand.class);
	private static final String REDIRECT_TYPE_ATTRIBUTE = "redirectType";
	private static final Object REDIRECT_TYPE_VALUE = "forward";
	private static final String USERLIST_ATTRIBUTE = "users";
	private static final String USERLIST_FILE = "WEB-INF/jsp/userList.jsp";
	private static final String ERROR_FILE = "WEB-INF/jsp/error.jsp";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setAttribute(USERLIST_ATTRIBUTE, userService.getUsers());
			request.setAttribute(REDIRECT_TYPE_ATTRIBUTE, REDIRECT_TYPE_VALUE);
			return USERLIST_FILE;
		} catch (ServiceException e) {
			LOG.error("can't get list of users", e);
		}
		return ERROR_FILE;
	}

}
