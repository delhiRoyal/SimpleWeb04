package com.epam.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.dao.BookDAO;
import com.epam.dao.dbutil.DBConnection;
import com.epam.dao.exception.DAOException;
import com.epam.domain.Book;

public class BookDAOImpl implements BookDAO {

	private static final String GET_BOOKS_ACCORDING_LANGUAGE_AND_CATEGORY = "select b_id,bl_name,bl_description,b_no_of_pages,"
			+ "cl_name from Book inner join Book_Locale using(b_id) inner join Category using(c_id)"
			+ "inner join Category_Locale using(c_id)"
			+ "inner join language on Book_Locale.l_id = language.l_id And Category_Locale.l_id = language.l_id "
			+ "where  l_code= ? And c_name = ? ;";

	private static final String INSERT_COMMON_FIELDS_OF_BOOK_QUERY = "INSERT INTO Book ( `b_no_of_pages`, `c_id`)"
			+ " VALUES ( ? , ? );";

	private static final String INSERT_LANGUAGE_SPECIFIC_FIELDS_OF_BOOK_QUERY = "INSERT INTO `Book_Locale` ( `bl_name`,"
			+ " `bl_description`, `b_id`, `l_id`) VALUES " + "( ? , ? , (SELECT LAST_INSERT_ID() ), '1');";

	private static final String ROLLBACK_QUERY = "DELETE FROM Book WHERE b_id = (SELECT LAST_INSERT_ID()) ";

	private static final String GET_BOOK_WITH_ID_AND_LANGUAGE = "select b_id,bl_name,bl_description,b_no_of_pages,"
			+ "cl_name from Book inner join Book_Locale using(b_id) inner join Category using(c_id)"
			+ "inner join Category_Locale using(c_id)"
			+ "inner join language on Book_Locale.l_id = language.l_id And Category_Locale.l_id = language.l_id "
			+ "where  l_code= ? And c_name = ? And b_id = ?;";

	private static final String UPDATE_BOOK = " INSERT INTO Book_Locale (bl_name,bl_description,b_id,l_id) "
			+ " VALUES (?,?,?,(select l_id from language where l_code = ?))" + " ON DUPLICATE KEY UPDATE "
			+ " bl_name=VALUES(bl_name), bl_description = VALUES(bl_description); ";

	private static final int LANGUAGE_CODE_INDEX = 1;
	private static final int CATEGORY_NAME_INDEX = 2;
	private static final int NO_OF_PAGES_INDEX = 1;
	private static final int CATEGORY_INDEX = 2;
	private static final int NAME_INDEX = 1;
	private static final int DESCRIPTION_INDEX = 2;
	private static final int BOOK_ID_INDEX = 3;
	private static final int LANGUAGE_CODE_FOR_UPDATE_INDEX = 4;

	private static final String COLUMN_ID = "b_id";
	private static final String COLUMN_NAME = "bl_name";
	private static final String COLUMN_DESCRIPTION = "bl_description";
	private static final String COLUMN_NUMBER_OF_PAGES = "b_no_of_pages";
	private static final String COLUMN_CATEGORY = "cl_name";

	private static final String DEFAULT_LANGUAGE_CODE = "en";

	private DBConnection dbConnection = DBConnection.getInstance();

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
		} catch (SQLException e) {
			throw new DAOException("unable to execute statement", e);
		} finally {
			dbConnection.closeStatement(preparedStatement);
			dbConnection.closeConnection(connection);
		}
		return books;
	}

	@Override
	public boolean addBook(Book book) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		PreparedStatement preparedStatementForLocale = null;
		PreparedStatement rollBackPreparedStatement = null;
		try {
			connection = dbConnection.getConnection();
			preparedStatement = connection.prepareStatement(INSERT_COMMON_FIELDS_OF_BOOK_QUERY);
			preparedStatement.setString(NO_OF_PAGES_INDEX, book.getNumberOfPages());
			preparedStatement.setString(CATEGORY_INDEX, book.getCategory());
			int result = preparedStatement.executeUpdate();
			if (result != 0) {
				preparedStatementForLocale = connection.prepareStatement(INSERT_LANGUAGE_SPECIFIC_FIELDS_OF_BOOK_QUERY);
				preparedStatementForLocale.setString(NAME_INDEX, book.getName());
				preparedStatementForLocale.setString(DESCRIPTION_INDEX, book.getDescription());
				result = preparedStatementForLocale.executeUpdate();
			}
			if (result != 0)
				return true;

			rollBackPreparedStatement = connection.prepareStatement(ROLLBACK_QUERY);
			rollBackPreparedStatement.executeUpdate();

		} catch (SQLException e) {
			throw new DAOException("unable to execute statement", e);
		} finally {
			dbConnection.closeStatement(preparedStatement);
			dbConnection.closeStatement(preparedStatementForLocale);
			dbConnection.closeStatement(rollBackPreparedStatement);
			dbConnection.closeConnection(connection);
		}
		return false;
	}

	@Override
	public Book getBook(int id, String languageCode, String category) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Book book = null;
		try {
			connection = dbConnection.getConnection();
			preparedStatement = connection.prepareStatement(GET_BOOK_WITH_ID_AND_LANGUAGE);
			preparedStatement.setString(LANGUAGE_CODE_INDEX, languageCode);
			preparedStatement.setString(CATEGORY_NAME_INDEX, category);
			preparedStatement.setInt(BOOK_ID_INDEX, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (!resultSet.isBeforeFirst()) {
				preparedStatement.setString(LANGUAGE_CODE_INDEX, DEFAULT_LANGUAGE_CODE);
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					book = createBook(resultSet);
				}
			} else {
				while (resultSet.next()) {
					book = createBook(resultSet);
				}
			}

		} catch (SQLException e) {
			throw new DAOException("unable to execute statement", e);
		} finally {
			dbConnection.closeStatement(preparedStatement);
			dbConnection.closeConnection(connection);
		}
		return book;
	}

	@Override
	public boolean updateBook(Book book, String languageCode) throws DAOException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = dbConnection.getConnection();
			preparedStatement = connection.prepareStatement(UPDATE_BOOK);
			preparedStatement.setString(NAME_INDEX, book.getName());
			preparedStatement.setString(DESCRIPTION_INDEX, book.getDescription());
			preparedStatement.setInt(BOOK_ID_INDEX, book.getId());
			preparedStatement.setString(LANGUAGE_CODE_FOR_UPDATE_INDEX, languageCode);
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

	private Book createBook(ResultSet resultSet) throws SQLException {
		Book book = new Book();
		book.setId(resultSet.getInt(COLUMN_ID));
		book.setName(resultSet.getString(COLUMN_NAME));
		book.setDescription(resultSet.getString(COLUMN_DESCRIPTION));
		book.setNumberOfPages(resultSet.getString(COLUMN_NUMBER_OF_PAGES));
		book.setCategory(resultSet.getString(COLUMN_CATEGORY));
		return book;
	}

}
