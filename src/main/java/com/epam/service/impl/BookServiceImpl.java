package com.epam.service.impl;

import java.util.List;

import com.epam.dao.BookDAO;
import com.epam.dao.exception.DAOException;
import com.epam.dao.factory.DAOFactory;
import com.epam.domain.Book;
import com.epam.service.BookService;
import com.epam.service.exception.ServiceException;
import com.epam.service.validator.ServiceValidator;

public class BookServiceImpl implements BookService {

	private static DAOFactory daoFactory = DAOFactory.getInstance();
	private static BookDAO bookDAO = daoFactory.getBookDAO();

	@Override
	public List<Book> getBooksAccordingToLanguageAndCategory(String languageCode, String category)
			throws ServiceException {
		ServiceValidator.validateLanguageCode(languageCode);
		ServiceValidator.validateCategory(category);
		try {
			return bookDAO.getBooksAccordingToLanguageAndCategory(languageCode, category);
		} catch (DAOException e) {
			throw new ServiceException("can't get Books from DAO", e);
		}
	}

}
