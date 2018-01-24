package com.dimbo.command;


import com.dimbo.model.Roles;
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
    static final Logger LOGGER = LoggerFactory
        .getLogger(CommandDispatcher.class);
    
    /**
     * Gets the command by name.
     *
     * @param request the request
     * @return the command
     */
    public Command getCommand(HttpServletRequest request) {
        
        Command command;
//        String commandParam = request.getParameter("command").trim().toUpperCase();
        
        if (request.getParameter("role") == null) {
            CommandDispatcherGeneral cdg = new CommandDispatcherGeneral();
            command = cdg.getCommand(request);
        } else {
            
            String roleParam = request.getParameter("role").trim().toUpperCase();

//        Commands commandType = Commands.valueOf(commandParam);
            Roles roleType = Roles.valueOf(roleParam);
            switch (roleType) {
                case ADMIN:
                    CommandDispatcherAdmin cda = new CommandDispatcherAdmin();
                    command = cda.getCommand(request);
                    break;
                
                case SUBSCRIBER:
                    CommandDispatcherSubscriber cds = new CommandDispatcherSubscriber();
                    command = cds.getCommand(request);
                    break;
                
                default:
                    CommandDispatcherGeneral cdg = new CommandDispatcherGeneral();
                    command = cdg.getCommand(request);
            }
        }
//        switch (commandType) {
//            case LOGIN:
//                return Commands.LOGIN.getCommand();
//
//            case REGISTRATION:
//                return Commands.REGISTRATION.getCommand();
//
//            case LOGOUT:
//                return Commands.LOGOUT.getCommand();
//
//            case SUBSCRIBER_LIST:
//                return Commands.SUBSCRIBER_LIST.getCommand();
//
//            case SERVICE_LIST:
//                return Commands.SERVICE_LIST.getCommand();
//
//            case ADD_SERVICE:
//                return Commands.ADD_SERVICE.getCommand();
//
//            case ADD_TARIFF:
//                return Commands.ADD_TARIFF.getCommand();
//
//            case NEW_TARIFF:
//                return Commands.NEW_TARIFF.getCommand();
//
//            case SUBSCRIBER_PROFILE:
//                return Commands.SUBSCRIBER_PROFILE.getCommand();
//
//            case CHANGE_LANGUAGE:
//                return Commands.CHANGE_LANGUAGE.getCommand();
//
////		default:
////			LOGGER.warn("Unknown operation.");
////			command = new EmptyCommand();
//        }
        
        return command;
    }
}
