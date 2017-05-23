package com.epam.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.epam.command.Command;
import com.epam.domain.Book;
import com.epam.service.BookService;
import com.epam.service.exception.ServiceException;
import com.epam.service.factory.ServiceFactory;

public class AddBookCommand implements Command {

	private static final Logger LOG = Logger.getLogger(AddBookCommand.class);
	public static final String REDIRECT_TYPE_ATTRIBUTE = "redirectType";
	public static final String REDIRECT = "redirect";
	public static final String FORWARD = "forward";
	private static final String ADD_MESSAGE_ATTRIBUTE = "addMessage";
	public static final String FILE_ON_ADMINISTRATOR_LOGIN = "administratorhome";
	public static final String FILE_ON_ERROR = "error.jsp";
	private static final String NAME_PARAM = "name";
	private static final String DESCRIPTION_PARAM = "description";
	private static final String NUMBER_OF_PAGES_PARAM = "noOfPages";
	private static final String CATEGORY_PARAM = "category";

	private static ServiceFactory serviceFactory = ServiceFactory.getInstance();
	private static BookService bookService = serviceFactory.getBookService();

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		Book book = createBook(request);
		try {
			if (bookService.addBook(book)) {
				request.setAttribute(REDIRECT_TYPE_ATTRIBUTE, REDIRECT);
				request.getSession().setAttribute(ADD_MESSAGE_ATTRIBUTE, true);
				return FILE_ON_ADMINISTRATOR_LOGIN;
			}
		} catch (ServiceException e) {
			LOG.error("Can't add book", e);
		}
		return FILE_ON_ERROR;
	}

	private Book createBook(HttpServletRequest request) {
		Book book = new Book();
		book.setName(request.getParameter(NAME_PARAM));
		book.setDescription(request.getParameter(DESCRIPTION_PARAM));
		book.setNumberOfPages(request.getParameter(NUMBER_OF_PAGES_PARAM));
		book.setCategory(request.getParameter(CATEGORY_PARAM));
		return book;
	}

}
