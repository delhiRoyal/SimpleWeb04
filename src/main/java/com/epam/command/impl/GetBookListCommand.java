package com.epam.command.impl;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.command.Command;
import com.epam.service.BookService;
import com.epam.service.exception.ServiceException;
import com.epam.service.factory.ServiceFactory;

public class GetBookListCommand implements Command {

	private static final Logger LOG = Logger.getLogger(GetBookListCommand.class);
	public static final String REDIRECT_TYPE_ATTRIBUTE = "redirectType";
	private static final String LANGUAGE_ATTRIBUTE = "language";
	private static final String CATEGORY_PARAMETER = "category";
	private static final String BOOKLIST_ATTRIBUTE = "bookList";
	private static final Object REDIRECT_TYPE_VALUE = "forward";
	private static final String BOOKLIST_FILE = "WEB-INF/jsp/bookList.jsp";
	private static final String ERROR_FILE = "WEB-INF/jsp/error.jsp";

	private static ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private static BookService bookService = serviceFactory.getBookService();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		String languageCode = getLanguageCode(request);
		String category = request.getParameter(CATEGORY_PARAMETER);
		try {
			request.setAttribute(BOOKLIST_ATTRIBUTE,
					bookService.getBooksAccordingToLanguageAndCategory(languageCode, category));
			request.setAttribute(REDIRECT_TYPE_ATTRIBUTE, REDIRECT_TYPE_VALUE);
			return BOOKLIST_FILE;
		} catch (ServiceException e) {
			LOG.error("can't get the list of books", e);
		}

		return ERROR_FILE;
	}

	private static String getLanguageCode(HttpServletRequest request) {
		String language = request.getParameter(LANGUAGE_ATTRIBUTE);
		if (language != null) {
			request.getSession().setAttribute(LANGUAGE_ATTRIBUTE, language);
		}
		Object locale = request.getSession().getAttribute(LANGUAGE_ATTRIBUTE);
		String languageCode = null;
		if (locale instanceof Locale) {
			languageCode = ((Locale) locale).getLanguage();
		} else {
			languageCode = locale.toString();
		}

		return languageCode;
	}

}
