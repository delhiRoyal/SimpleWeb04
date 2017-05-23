package com.epam.dao;

import java.util.List;

import com.epam.dao.exception.DAOException;
import com.epam.domain.Book;

public interface BookDAO {

	List<Book> getBooksAccordingToLanguageAndCategory(String languageCode, String category) throws DAOException;

	boolean addBook(Book book) throws DAOException;

	Book getBook(int id, String languageCode, String category) throws DAOException;

	boolean updateBook(Book book, String languageCode) throws DAOException;
}
