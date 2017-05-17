package com.epam.service;

import java.util.List;

import com.epam.domain.Book;
import com.epam.service.exception.ServiceException;

public interface BookService {

	List<Book> getBooksAccordingToLanguageAndCategory(String languageCode, String category) throws ServiceException;
}
