package com.epam.service;

import com.epam.domain.User;
import com.epam.service.exception.ServiceException;

public interface UserService {

	User getUserDetail(String name) throws ServiceException;

}
