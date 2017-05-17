package com.epam.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.dao.BookDAO;
import com.epam.dao.dbutil.DBConnectionPool;
import com.epam.dao.exception.DAOException;
import com.epam.domain.Book;

public class BookDAOImpl implements BookDAO {

	private static final String GET_BOOKS_ACCORDING_LANGUAGE_AND_CATEGORY = "select bl_name,bl_description,b_no_of_pages,"
			+ "cl_name from book inner join book_locale using(b_id) inner join category using(c_id)"
			+ "inner join category_locale using(c_id)"
			+ "inner join language on book_locale.l_id = language.l_id And category_locale.l_id = language.l_id "
			+ "where  l_code= ? And c_name = ? ;";
	private static final int LANGUAGE_CODE_INDEX = 1;
	private static final int CATEGORY_NAME_INDEX = 2;
	private static final String COLUMN_NAME = "bl_name";
	private static final String COLUMN_DESCRIPTION = "bl_description";
	private static final String COLUMN_NUMBER_OF_PAGES = "b_no_of_pages";
	private static final String COLUMN_CATEGORY = "cl_name";
	private DBConnectionPool dbConnection = DBConnectionPool.getInstance();

	@Override
	public List<Book> getBooksAccordingToLanguageAndCategory(String languageCode, String category) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		List<Book> books = new ArrayList<>();
		try {
			connection = dbConnection.getConnection();
			preparedStatement = connection.prepareStatement(GET_BOOKS_ACCORDING_LANGUAGE_AND_CATEGORY);
			preparedStatement.setString(LANGUAGE_CODE_INDEX, languageCode);
			preparedStatement.setString(CATEGORY_NAME_INDEX, category);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				books.add(createBook(resultSet));
			}
		} catch (InterruptedException e) {
			throw new DAOException("Can't get a connection from pool", e);
		} catch (SQLException e) {
			throw new DAOException("unable to execute statement", e);
		} finally {
			dbConnection.closeStatement(preparedStatement);
			dbConnection.returnConnectionToPool(connection);
		}
		return books;
	}

	private Book createBook(ResultSet resultSet) throws SQLException {
		Book book = new Book();
		book.setName(resultSet.getString(COLUMN_NAME));
		book.setDescription(resultSet.getString(COLUMN_DESCRIPTION));
		book.setNumberOfPages(resultSet.getString(COLUMN_NUMBER_OF_PAGES));
		book.setCategory(resultSet.getString(COLUMN_CATEGORY));
		return book;
	}

}