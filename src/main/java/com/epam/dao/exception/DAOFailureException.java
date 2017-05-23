package com.epam.dao.exception;

public class DAOFailureException extends RuntimeException {

	private static final long serialVersionUID = -1496593814773821319L;

	public DAOFailureException() {
		super();

	}

	public DAOFailureException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);

	}

	public DAOFailureException(String message, Throwable cause) {
		super(message, cause);

	}

	public DAOFailureException(String message) {
		super(message);

	}

	public DAOFailureException(Throwable cause) {
		super(cause);

	}

}
