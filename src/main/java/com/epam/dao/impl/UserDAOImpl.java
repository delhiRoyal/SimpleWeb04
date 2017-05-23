package com.epam.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.dao.UserDAO;
import com.epam.dao.dbutil.DBConnection;
import com.epam.dao.exception.DAOException;
import com.epam.domain.User;

public class UserDAOImpl implements UserDAO {

	private DBConnection dbConnection = DBConnection.getInstance();

	private static final int NAME_INDEX = 1;
	private static final int PASSWORD_INDEX = 2;
	private static final int SUBQUERY_NAME_INDEX = 3;
	private static final int OLD_NAME_INDEX = 2;
	private static final String COLUMN_NAME = "u_name";
	private static final String COLUMN_PASSWORD = "u_password";
	private static final String COLUMN_ROLE = "r_role";

	private static final String GET_DETAILS_OF_PERSON_QUERY = "SELECT u_name,u_password,r_role FROM user"
			+ " join role USING(r_id) WHERE u_name = ? ;";

	private static final String INSERT_NEW_USER_QUERY = "INSERT INTO user "
			+ "(u_name, u_password, r_id) SELECT * FROM (SELECT ? as u_name, ? as u_password, '2' as r_id)"
			+ " AS tmp WHERE NOT EXISTS ( SELECT u_name FROM user WHERE u_name = ? ) LIMIT 1;";

	private static final String SUCCESS_MESSAGE = "successfull";

	private static final String ALREADY_EXIST_MESSAGE = "alreadyexisting";

	private static final String GET_USER_LIST_QUERY = "SELECT u_name FROM user";

	private static final String UPDATE_USER_LIST_QUERY = "UPDATE user SET u_name = ? WHERE u_name = ? ";

	@Override
	public User getUserDetail(String name) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		User user = null;

		try {
			connection = dbConnection.getConnection();
			preparedStatement = connection.prepareStatement(GET_DETAILS_OF_PERSON_QUERY);
			preparedStatement.setString(NAME_INDEX, name);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				user = createUser(resultSet);
			}
			return user;
		} catch (SQLException e) {
			throw new DAOException("unable to execute statement", e);
		} finally {
			dbConnection.closeStatement(preparedStatement);
			dbConnection.closeConnection(connection);
		}

	}

	@Override
	public String addUser(User user) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = dbConnection.getConnection();
			preparedStatement = connection.prepareStatement(INSERT_NEW_USER_QUERY);
			preparedStatement.setString(NAME_INDEX, user.getName());
			preparedStatement.setString(PASSWORD_INDEX, user.getPassword());
			preparedStatement.setString(SUBQUERY_NAME_INDEX, user.getName());
			int result = preparedStatement.executeUpdate();
			if (result != 0) {
				return SUCCESS_MESSAGE;
			}
			return ALREADY_EXIST_MESSAGE;

		} catch (SQLException e) {
			throw new DAOException("unable to execute statement", e);
		} finally {
			dbConnection.closeStatement(preparedStatement);
			dbConnection.closeConnection(connection);
		}

	}

	@Override
	public List<String> getUsers() throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		List<String> users = new ArrayList<>();
		try {
			connection = dbConnection.getConnection();
			preparedStatement = connection.prepareStatement(GET_USER_LIST_QUERY);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				users.add(resultSet.getString(COLUMN_NAME));
			}
			return users;

		} catch (SQLException e) {
			throw new DAOException("unable to execute statement", e);
		} finally {
			dbConnection.closeStatement(preparedStatement);
			dbConnection.closeConnection(connection);
		}
	}

	@Override
	public boolean updateUser(String oldName, String newName) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dbConnection.getConnection();
			preparedStatement = connection.prepareStatement(UPDATE_USER_LIST_QUERY);
			preparedStatement.setString(NAME_INDEX, newName);
			preparedStatement.setString(OLD_NAME_INDEX, oldName);
			int result = preparedStatement.executeUpdate();
			if (result != 0)
				return true;

		} catch (SQLException e) {
			throw new DAOException("unable to execute statement", e);
		} finally {
			dbConnection.closeStatement(preparedStatement);
			dbConnection.closeConnection(connection);
		}
		return false;
	}

	private User createUser(ResultSet resultSet) throws SQLException {
		User user = new User();
		user.setName(resultSet.getString(COLUMN_NAME));
		user.setPassword(resultSet.getString(COLUMN_PASSWORD));
		user.setRole(resultSet.getString(COLUMN_ROLE));
		return user;
	}

}
