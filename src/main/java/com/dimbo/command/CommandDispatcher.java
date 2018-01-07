package com.dimbo.command;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * The Class CommandDispatcher provides method for commands management.
 */
public class CommandDispatcher {

    /**
     * The Constant LOGGER.
     */
    static final Logger LOGGER = LoggerFactory.getLogger(CommandDispatcher.class);

    /**
     * Gets the command by name.
     *
     * @param request the request
     * @return the command
     */
    public Command getCommand(HttpServletRequest request) {

        Command command = null;
        String param = request.getParameter("command").trim().toUpperCase();
        Commands commandType = Commands.valueOf(param);

        switch (commandType) {
            case LOGIN:
                return Commands.LOGIN.getCommand();

            case REGISTRATION:
                return Commands.REGISTRATION.getCommand();

//		default:
//			LOGGER.warn("Unknown operation.");
//			command = new EmptyCommand();
        }

        return command;
    }
}
