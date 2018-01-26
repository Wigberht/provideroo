package com.d_cherkashyn.epam.command.dispatcher;


import com.d_cherkashyn.epam.command.Command;
import com.d_cherkashyn.epam.command.commands.CommandsAdmin;
import com.d_cherkashyn.epam.command.commands.CommandsGeneral;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * The Class CommandDispatcher provides method for commands management.
 */
public class CommandDispatcherAdmin {
    
    /**
     * The Constant LOGGER.
     */
    static final Logger LOGGER = LoggerFactory
        .getLogger(CommandDispatcherAdmin.class);
    
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
            case REGISTRATION:
                command = CommandsAdmin.REGISTRATION.getCommand();
                break;
            
            case SUBSCRIBER_LIST:
                command = CommandsAdmin.SUBSCRIBER_LIST.getCommand();
                break;
            
            case SERVICE_LIST:
                command = CommandsAdmin.SERVICE_LIST.getCommand();
                break;
            
            case ADD_SERVICE:
                command = CommandsAdmin.ADD_SERVICE.getCommand();
                break;
            
            case ADD_TARIFF:
                command = CommandsAdmin.ADD_TARIFF.getCommand();
                break;
            
            case NEW_TARIFF:
                command = CommandsAdmin.NEW_TARIFF.getCommand();
                break;
            
            case CHAT_LIST:
                command = CommandsAdmin.CHAT_LIST.getCommand();
                break;
            
            default:
                command = CommandsGeneral.MISSING_COMMAND.getCommand();
        }
        
        return command;
    }
}
