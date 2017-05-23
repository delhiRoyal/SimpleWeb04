package com.epam.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.command.Command;
import com.epam.service.UserService;
import com.epam.service.exception.ServiceException;
import com.epam.service.factory.ServiceFactory;

public class UpdateUserCommand implements Command {

	private static final Logger LOG = Logger.getLogger(UpdateUserCommand.class);

	private static final String NAME_PARAM = "name";
	private static final String NEW_NAME_PARAM = "newName";
	public static final String REDIRECT_TYPE_ATTRIBUTE = "redirectType";
	public static final String REDIRECT = "redirect";
	private static final String UPDATE_MESSAGE_ATTRIBUTE = "userUpdateMessage";
	public static final String FILE_ON_ERROR = "error.jsp";

	private static final String FILE_ON_SUCCESS = "administratorhome";
	private static ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private static UserService userService = serviceFactory.getUserService();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String oldName = request.getParameter(NAME_PARAM);
		String newName = request.getParameter(NEW_NAME_PARAM);
		try {
			if (userService.updateUser(oldName, newName)) {
				request.setAttribute(REDIRECT_TYPE_ATTRIBUTE, REDIRECT);
				request.getSession().setAttribute(UPDATE_MESSAGE_ATTRIBUTE, true);
				return FILE_ON_SUCCESS;
			}

		} catch (ServiceException e) {
			LOG.error("can't update the user", e);
		}
		return FILE_ON_ERROR;
	}

}
