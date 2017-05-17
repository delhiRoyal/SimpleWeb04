package com.epam.service.exception;

public class ServiceException extends Exception {

	private static final long serialVersionUID = 7499612788787936439L;

	public ServiceException() {

	}

	public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);

	}

	public ServiceException(String message) {
		super(message);

	}

	public ServiceException(Throwable cause) {
		super(cause);

	}

}
