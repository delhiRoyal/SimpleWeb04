package com.epam.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.command.Command;

public class GoToEditUserPageCommand implements Command {

	private static final String REDIRECT_TYPE_ATTRIBUTE = "redirectType";
	private static final Object REDIRECT_TYPE_VALUE = "forward";
	private static final String EDIT_USER_FILE = "WEB-INF/jsp/editUser.jsp";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(REDIRECT_TYPE_ATTRIBUTE, REDIRECT_TYPE_VALUE);
		return EDIT_USER_FILE;
	}

}
