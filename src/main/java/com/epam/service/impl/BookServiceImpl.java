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

	private static final DAOFactory DAOFACTORY = DAOFactory.getInstance();
	private static final BookDAO BOOKDAO = DAOFACTORY.getBookDAO();

	@Override
	public List<Book> getBooksAccordingToLanguageAndCategory(String languageCode, String category)
			throws ServiceException {
		ServiceValidator.validateLanguageCode(languageCode);
		ServiceValidator.validateCategory(category);
		try {
			return BOOKDAO.getBooksAccordingToLanguageAndCategory(languageCode, category);
		} catch (DAOException e) {
			throw new ServiceException("can't get Books from DAO", e);
		}
	}

	@Override
	public boolean addBook(Book book) throws ServiceException {
		ServiceValidator.validateBook(book);
		try {
			return BOOKDAO.addBook(book);
		} catch (DAOException e) {
			throw new ServiceException("can't add Book", e);
		}
	}

	@Override
	public Book getBook(int id, String languageCode, String category) throws ServiceException {
		ServiceValidator.validateId(id);
		ServiceValidator.validateLanguageCode(languageCode);
		ServiceValidator.validateCategory(category);
		try {
			return BOOKDAO.getBook(id, languageCode, category);
		} catch (DAOException e) {
			throw new ServiceException("can't get Book from DAO", e);
		}
	}

	@Override
	public boolean updateBook(Book book, String languageCode) throws ServiceException {
		ServiceValidator.validateBook(book);
		ServiceValidator.validateLanguageCode(languageCode);
		try {
			return BOOKDAO.updateBook(book, languageCode);
		} catch (DAOException e) {
			throw new ServiceException("can't update Books", e);
		}
	}

}
