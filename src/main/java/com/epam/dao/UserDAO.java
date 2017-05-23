package com.epam.dao;

import java.util.List;

import com.epam.dao.exception.DAOException;
import com.epam.domain.User;

public interface UserDAO {

	User getUserDetail(String name) throws DAOException;

	String addUser(User user) throws DAOException;

	List<String> getUsers() throws DAOException;

	boolean updateUser(String oldName, String newName) throws DAOException;

}
