package com.epam.service.impl;

import java.util.List;

import com.epam.dao.UserDAO;
import com.epam.dao.exception.DAOException;
import com.epam.dao.factory.DAOFactory;
import com.epam.domain.User;
import com.epam.service.UserService;
import com.epam.service.exception.ServiceException;
import com.epam.service.validator.ServiceValidator;

public class UserServiceImpl implements UserService {

	private static final DAOFactory DAOFACTORY = DAOFactory.getInstance();
	private static final UserDAO USERDAO = DAOFACTORY.getUserDAO();

	@Override
	public User getUserDetail(String name) throws ServiceException {

		ServiceValidator.validateName(name);
		try {
			return USERDAO.getUserDetail(name);
		} catch (DAOException e) {
			throw new ServiceException("can't get user from DAO", e);
		}

	}

	@Override
	public String addUser(User user) throws ServiceException {
		ServiceValidator.validateUser(user);
		try {
			return USERDAO.addUser(user);
		} catch (DAOException e) {
			throw new ServiceException("can't add user from DAO", e);
		}
	}

	@Override
	public List<String> getUsers() throws ServiceException {
		try {
			return USERDAO.getUsers();
		} catch (DAOException e) {
			throw new ServiceException("can't get users from DAO", e);
		}
	}

	@Override
	public boolean updateUser(String oldName, String newName) throws ServiceException {
		ServiceValidator.validateName(oldName);
		ServiceValidator.validateName(newName);
		try {
			return USERDAO.updateUser(oldName, newName);
		} catch (DAOException e) {
			throw new ServiceException("can't update user from DAO", e);
		}
	}

}
