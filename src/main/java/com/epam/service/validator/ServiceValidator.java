package com.epam.service.validator;

import com.epam.domain.Book;
import com.epam.domain.User;
import com.epam.service.exception.ServiceException;

public final class ServiceValidator {

	private ServiceValidator() {
	}

	public static void validateName(String name) throws ServiceException {
		if (!isStringValid(name)) {
			throw new ServiceException("Invalid Name String");
		}
	}

	public static void validateLanguageCode(String languageCode) throws ServiceException {
		if (!isStringValid(languageCode)) {
			throw new ServiceException("Invalid languageCode String");
		}
	}

	public static void validateCategory(String category) throws ServiceException {
		if (!isStringValid(category)) {
			throw new ServiceException("Invalid category String");
		}
	}

	public static void validateUser(User user) throws ServiceException {
		if (!isStringValid(user.getName())) {
			throw new ServiceException("Invalid User Name String");
		}
		if (!isStringValid(user.getPassword())) {
			throw new ServiceException("Invalid User Password String");
		}
	}

	public static void validateId(int id) throws ServiceException {
		if (id <= 0)
			throw new ServiceException("Invalid Id");
	}

	public static void validateBook(Book book) throws ServiceException {
		if (book == null)
			throw new ServiceException("Null Book Object");
	}

	private static boolean isStringValid(String string) {
		return string != null && !string.isEmpty();
	}

}
