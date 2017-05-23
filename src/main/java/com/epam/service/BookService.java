package com.epam.service;

import java.util.List;

import com.epam.domain.Book;
import com.epam.service.exception.ServiceException;

public interface BookService {

	List<Book> getBooksAccordingToLanguageAndCategory(String languageCode, String category) throws ServiceException;

	boolean addBook(Book book) throws ServiceException;

	public Book getBook(int id, String languageCode, String category) throws ServiceException;

	boolean updateBook(Book book, String languageCode) throws ServiceException;
}
