package com.epam.command.factory;

import java.util.HashMap;
import java.util.Map;

import com.epam.command.Command;
import com.epam.command.impl.GetBookListCommand;
import com.epam.command.impl.LoginCommand;

public class CommandFactory {

	private static final String LOGIN_COMMAND = "login";
	private static final String GET_BOOKS_COMMAND = "getBooks";

	private Map<String, Command> commandMap = new HashMap<>();

	private CommandFactory() {
		commandMap.put(LOGIN_COMMAND, new LoginCommand());
		commandMap.put(GET_BOOKS_COMMAND, new GetBookListCommand());

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
