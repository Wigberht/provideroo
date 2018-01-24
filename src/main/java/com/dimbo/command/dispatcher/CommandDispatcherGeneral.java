package com.dimbo.command.dispatcher;


import com.dimbo.command.Command;
import com.dimbo.command.commands.CommandsAdmin;
import com.dimbo.command.commands.CommandsGeneral;
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
        
        Command command;
        String commandParam = request.getParameter("command").trim().toUpperCase();
        
        CommandsGeneral commandType = CommandsGeneral.valueOf(commandParam);
        switch (commandType) {
            case LOGIN:
                command = CommandsGeneral.LOGIN.getCommand();
                break;
            
            case LOGOUT:
                command = CommandsGeneral.LOGOUT.getCommand();
                break;
            
            case CHANGE_LANGUAGE:
                command = CommandsGeneral.CHANGE_LANGUAGE.getCommand();
                break;
            
            default:
                command = CommandsGeneral.MISSING_COMMAND.getCommand();
        }
        
        return command;
    }
}
