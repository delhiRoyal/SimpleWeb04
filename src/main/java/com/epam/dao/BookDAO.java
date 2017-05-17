package com.epam.dao;

import java.util.List;

import com.epam.dao.exception.DAOException;
import com.epam.domain.Book;

public interface BookDAO {

	List<Book> getBooksAccordingToLanguageAndCategory(String languageCode, String category) throws DAOException;

}
