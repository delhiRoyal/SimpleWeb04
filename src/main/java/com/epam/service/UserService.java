package com.epam.service;

import java.util.List;

import com.epam.domain.User;
import com.epam.service.exception.ServiceException;

public interface UserService {

	User getUserDetail(String name) throws ServiceException;

	String addUser(User user) throws ServiceException;

	List<String> getUsers() throws ServiceException;
	
	boolean updateUser(String oldName, String newName) throws ServiceException;
}
