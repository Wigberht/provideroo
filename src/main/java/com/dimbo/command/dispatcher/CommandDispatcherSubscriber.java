package com.dimbo.command.dispatcher;


import com.dimbo.command.Command;
import com.dimbo.command.commands.CommandsAdmin;
import com.dimbo.command.commands.CommandsGeneral;
import com.dimbo.command.commands.CommandsSubscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * The Class CommandDispatcher provides method for commands management.
 */
public class CommandDispatcherSubscriber {
    
    /**
     * The Constant LOGGER.
     */
    static final Logger LOGGER = LoggerFactory
        .getLogger(CommandDispatcherSubscriber.class);
    
    /**
     * Gets the command by name.
     *
     * @param request the request
     * @return the command
     */
    public Command getCommand(HttpServletRequest request) {
        
        Command command;
        String commandParam = request.getParameter("command").trim().toUpperCase();
        
        CommandsSubscriber commandType = CommandsSubscriber.valueOf(commandParam);
        
        switch (commandType) {
            
            case SERVICE_LIST:
                return CommandsSubscriber.SERVICE_LIST.getCommand();
            
            case PROFILE:
                return CommandsSubscriber.PROFILE.getCommand();
    
            default:
                command = CommandsGeneral.MISSING_COMMAND.getCommand();
        }
        
        return command;
    }
}
