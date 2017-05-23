package com.epam.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.command.factory.CommandFactory;

public final class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger LOG = Logger.getLogger(Controller.class);
	public static final String REDIRECT_TYPE_ATTRIBUTE = "redirectType";
	private static final CommandFactory commandFactory = CommandFactory.getInstance();
	public static final String REQUEST_PARAM = "request";
	public static final String REDIRECT = "redirect";
	public static final String FORWARD = "forward";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		handleRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		handleRequest(request, response);
	}

	private static void handleRequest(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			LOG.error("can't set character encoding", e);
		}
		String requestType = request.getParameter(REQUEST_PARAM);
		String nextFile = commandFactory.getCommand(requestType).execute(request, response);
		String redirectType = (String) request.getAttribute(REDIRECT_TYPE_ATTRIBUTE);
		if (REDIRECT.equals(redirectType)) {
			redirect(response, nextFile);
		} else if (FORWARD.equals(redirectType)) {
			forward(request, response, nextFile);
		}
	}

	private static void redirect(HttpServletResponse response, String fileName) {
		try {
			response.sendRedirect(fileName);
		} catch (IOException e) {
			LOG.error("can't redirect", e);
		}
	}

	private static void forward(HttpServletRequest request, HttpServletResponse response, String fileName) {
		try {
			request.getRequestDispatcher(fileName).forward(request, response);
		} catch (ServletException | IOException e) {
			LOG.error("can't forward", e);
		}
	}

}
