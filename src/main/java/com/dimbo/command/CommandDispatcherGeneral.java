package com.dimbo.command;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * The Class CommandDispatcher provides method for commands management.
 */
public class CommandDispatcherGeneral {
    
    /**
     * The Constant LOGGER.
     */
    static final Logger LOGGER = LoggerFactory
        .getLogger(CommandDispatcherGeneral.class);
    
    /**
     * Gets the command by name.
     *
     * @param request the request
     * @return the command
     */
    public Command getCommand(HttpServletRequest request) {
        
        Command command = null;
        String commandParam = request.getParameter("command").trim().toUpperCase();
        
        CommandsAdmin commandType = CommandsAdmin.valueOf(commandParam);
        
        switch (commandType) {
            case LOGIN:
                return Commands.LOGIN.getCommand();
            
            case REGISTRATION:
                return Commands.REGISTRATION.getCommand();
            
            case LOGOUT:
                return Commands.LOGOUT.getCommand();
            
            case SUBSCRIBER_LIST:
                return Commands.SUBSCRIBER_LIST.getCommand();
            
            case SERVICE_LIST:
                return Commands.SERVICE_LIST.getCommand();
            
            case ADD_SERVICE:
                return Commands.ADD_SERVICE.getCommand();
            
            case ADD_TARIFF:
                return Commands.ADD_TARIFF.getCommand();
            
            case NEW_TARIFF:
                return Commands.NEW_TARIFF.getCommand();
            
            case SUBSCRIBER_PROFILE:
                return Commands.SUBSCRIBER_PROFILE.getCommand();
            
            case CHANGE_LANGUAGE:
                return Commands.CHANGE_LANGUAGE.getCommand();

//		default:
//			LOGGER.warn("Unknown operation.");
//			command = new EmptyCommand();
        }
        
        return command;
    }
}
