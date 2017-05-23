package com.epam.command.factory;

import java.util.HashMap;
import java.util.Map;

import com.epam.command.Command;
import com.epam.command.impl.AddBookCommand;
import com.epam.command.impl.GetBookCommand;
import com.epam.command.impl.GetBookListCommand;
import com.epam.command.impl.GetUserCommand;
import com.epam.command.impl.GetUserListCommand;
import com.epam.command.impl.GoToAddBookPageCommand;
import com.epam.command.impl.GoToEditBookPageCommand;
import com.epam.command.impl.GoToEditUserPageCommand;
import com.epam.command.impl.LoginCommand;
import com.epam.command.impl.SignUpCommand;
import com.epam.command.impl.UpdateBookCommand;
import com.epam.command.impl.UpdateUserCommand;

public class CommandFactory {

	private static final String LOGIN_COMMAND = "login";
	private static final String SIGNUP_COMMAND = "signUp";
	private static final String GET_BOOKS_COMMAND = "getBooks";
	private static final String GET_USERS_COMMAND = "getUsers";
	private static final String GO_TO_ADD_BOOK_PAGE_COMMAND = "goToAddBookPage";
	private static final String GO_TO_EDIT_BOOK_PAGE_COMMAND = "goToEditBookPage";
	private static final String GO_TO_EDIT_USER_PAGE_COMMAND = "goToEditUserPage";
	private static final String ADD_BOOK_COMMAND = "addBook";
	private static final String GET_BOOK_COMMAND = "getBookFromId";
	private static final String UPDATE_BOOK_COMMAND = "updateBook";
	private static final String GET_USER_COMMAND = "getUser";
	private static final String UPDATE_USER_COMMAND = "updateUser";

	private Map<String, Command> commandMap = new HashMap<>();

	private CommandFactory() {
		commandMap.put(LOGIN_COMMAND, new LoginCommand());
		commandMap.put(GET_BOOKS_COMMAND, new GetBookListCommand());
		commandMap.put(SIGNUP_COMMAND, new SignUpCommand());
		commandMap.put(GET_USERS_COMMAND, new GetUserListCommand());
		commandMap.put(GO_TO_ADD_BOOK_PAGE_COMMAND, new GoToAddBookPageCommand());
		commandMap.put(ADD_BOOK_COMMAND, new AddBookCommand());
		commandMap.put(GET_BOOK_COMMAND, new GetBookCommand());
		commandMap.put(GO_TO_EDIT_BOOK_PAGE_COMMAND, new GoToEditBookPageCommand());
		commandMap.put(UPDATE_BOOK_COMMAND, new UpdateBookCommand());
		commandMap.put(GET_USER_COMMAND, new GetUserCommand());
		commandMap.put(GO_TO_EDIT_USER_PAGE_COMMAND, new GoToEditUserPageCommand());
		commandMap.put(UPDATE_USER_COMMAND, new UpdateUserCommand());

	}

	public static CommandFactory getInstance() {
		return CommandFactoryHelper.INSTANCE;
	}

	public Command getCommand(String command) {
		return commandMap.get(command);

	}

	private static class CommandFactoryHelper {

		private static final CommandFactory INSTANCE = new CommandFactory();

		private CommandFactoryHelper() {

		}
	}
}
