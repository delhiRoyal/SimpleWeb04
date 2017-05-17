package com.epam.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.epam.dao.UserDAO;
import com.epam.dao.dbutil.DBConnectionPool;
import com.epam.dao.exception.DAOException;
import com.epam.domain.User;

public class UserDAOImpl implements UserDAO {

	private DBConnectionPool dbConnection = DBConnectionPool.getInstance();

	private static final int NAME_INDEX = 1;

	private static final String COLUMN_NAME = "u_name";
	private static final String COLUMN_PASSWORD = "u_password";
	private static final String COLUMN_ROLE = "r_role";

	private static final String GET_DETAILS_OF_PERSON = "SELECT u_name,u_password,r_role FROM user"
			+ " join role USING(r_id) WHERE u_name = ? ;";

	@Override
	public User getUserDetail(String name) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		User user = null;

		try {
			connection = dbConnection.getConnection();
			preparedStatement = connection.prepareStatement(GET_DETAILS_OF_PERSON);
			preparedStatement.setString(NAME_INDEX, name);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				user = createUser(resultSet);
			}
			return user;
		} catch (InterruptedException e) {
			throw new DAOException("Can't get a connection from pool", e);
		} catch (SQLException e) {
			throw new DAOException("unable to execute statement", e);
		} finally {
			dbConnection.closeStatement(preparedStatement);
			dbConnection.returnConnectionToPool(connection);
		}

	}

	private User createUser(ResultSet resultSet) throws SQLException {
		User user = new User();
		user.setName(resultSet.getString(COLUMN_NAME));
		user.setPassword(resultSet.getString(COLUMN_PASSWORD));
		user.setRole(resultSet.getString(COLUMN_ROLE));
		return user;
	}

}
