package com.epam.service.impl;

import com.epam.dao.UserDAO;
import com.epam.dao.exception.DAOException;
import com.epam.dao.factory.DAOFactory;
import com.epam.domain.User;
import com.epam.service.UserService;
import com.epam.service.exception.ServiceException;
import com.epam.service.validator.ServiceValidator;

public class UserServiceImpl implements UserService {

	private static DAOFactory daoFactory = DAOFactory.getInstance();
	private static UserDAO userDAO = daoFactory.getUserDAO();

	@Override
	public User getUserDetail(String name) throws ServiceException {

		ServiceValidator.validateName(name);
		try {
			return userDAO.getUserDetail(name);
		} catch (DAOException e) {
			throw new ServiceException("can't get user from DAO", e);
		}

	}

}
