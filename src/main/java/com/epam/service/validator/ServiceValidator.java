package com.epam.service.validator;

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

	private static boolean isStringValid(String string) {
		return string != null && !string.isEmpty();
	}

}
