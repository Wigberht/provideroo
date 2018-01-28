package com.d_cherkashyn.epam.command.dispatcher;


import com.d_cherkashyn.epam.command.Command;
import com.d_cherkashyn.epam.command.commands.CommandsAdmin;
import com.d_cherkashyn.epam.command.commands.CommandsGeneral;
import com.d_cherkashyn.epam.command.commands.CommandsSubscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * The Class CommandDispatcherAdmin provides functionality to dispatch subscriber's
 * commands.
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
                command = CommandsSubscriber.SERVICE_LIST.getCommand();
                break;
            
            case PROFILE:
                command = CommandsSubscriber.PROFILE.getCommand();
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
