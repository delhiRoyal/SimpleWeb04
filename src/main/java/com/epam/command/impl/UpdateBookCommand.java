package com.epam.command.impl;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.command.Command;
import com.epam.domain.Book;
import com.epam.domain.User;
import com.epam.service.BookService;
import com.epam.service.exception.ServiceException;
import com.epam.service.factory.ServiceFactory;

public class UpdateBookCommand implements Command {

	private static final Logger LOG = Logger.getLogger(UpdateBookCommand.class);

	private static final String LANGUAGE_ATTRIBUTE = "language";
	public static final String REDIRECT_TYPE_ATTRIBUTE = "redirectType";
	public static final String REDIRECT = "redirect";
	private static final String UPDATE_MESSAGE_ATTRIBUTE = "bookUpdateMessage";
	public static final String USER = "user";
	public static final String ADMINISTRATOR = "administrator";
	public static final String FILE_IF_USER = "userhome";
	public static final String FILE_IF_ADMINISTRATOR = "administratorhome";
	public static final String FILE_ON_ERROR = "error.jsp";
	private static final String ID_PARAM = "id";
	private static final String NAME_PARAM = "name";
	private static final String DESCRIPTION_PARAM = "description";

	private static ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private static BookService bookService = serviceFactory.getBookService();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		Book book = createBook(request);
		String languageCode = getLanguageCode(request);
		User user = (User) request.getSession().getAttribute(USER);
		try {
			if (bookService.updateBook(book, languageCode)) {
				request.setAttribute(REDIRECT_TYPE_ATTRIBUTE, REDIRECT);
				request.getSession().setAttribute(UPDATE_MESSAGE_ATTRIBUTE, true);
				return getRedirectFileAccordingToRole(user.getRole());
			}
		} catch (ServiceException e) {
			LOG.error("Can't add book", e);
		}
		return FILE_ON_ERROR;
	}

	private Book createBook(HttpServletRequest request) {
		Book book = new Book();
		book.setId(Integer.parseInt(request.getParameter(ID_PARAM)));
		book.setName(request.getParameter(NAME_PARAM));
		book.setDescription(request.getParameter(DESCRIPTION_PARAM));
		return book;
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

	private static String getRedirectFileAccordingToRole(String role) {
		if (USER.equals(role))
			return FILE_IF_USER;
		else if (ADMINISTRATOR.equals(role))
			return FILE_IF_ADMINISTRATOR;
		else
			return FILE_ON_ERROR;
	}

}
