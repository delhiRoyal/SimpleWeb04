package com.epam.dao;

import com.epam.dao.exception.DAOException;
import com.epam.domain.User;

public interface UserDAO {

	User getUserDetail(String name) throws DAOException;

}
