package com.dimbo.command.dispatcher;


import com.dimbo.command.Command;
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
        
        if (request.getParameter("role") == null) {
            CommandDispatcherGeneral cdg = new CommandDispatcherGeneral();
            return cdg.getCommand(request);
        }
        
        String roleParam = request.getParameter("role").trim().toUpperCase();
        Roles roleType = Roles.valueOf(roleParam);
        switch (roleType) {
            case ADMIN:
                LOGGER.info("ADMIN DISPATCHER");
                CommandDispatcherAdmin cda = new CommandDispatcherAdmin();
                command = cda.getCommand(request);
                break;
            
            case SUBSCRIBER:
                LOGGER.info("SUBSCRIBER DISPATCHER");
                CommandDispatcherSubscriber cds = new CommandDispatcherSubscriber();
                command = cds.getCommand(request);
                break;
            
            default:
                LOGGER.info("DEFAULT DISPATCHER");
                CommandDispatcherGeneral cdg = new CommandDispatcherGeneral();
                command = cdg.getCommand(request);
        }

//        switch (commandType) {
//            case LOGIN:
//                return CommandsGeneral.LOGIN.getCommand();
//
//            case REGISTRATION:
//                return CommandsGeneral.REGISTRATION.getCommand();
//
//            case LOGOUT:
//                return CommandsGeneral.LOGOUT.getCommand();
//
//            case SUBSCRIBER_LIST:
//                return CommandsGeneral.SUBSCRIBER_LIST.getCommand();
//
//            case SERVICE_LIST:
//                return CommandsGeneral.SERVICE_LIST.getCommand();
//
//            case ADD_SERVICE:
//                return CommandsGeneral.ADD_SERVICE.getCommand();
//
//            case ADD_TARIFF:
//                return CommandsGeneral.ADD_TARIFF.getCommand();
//
//            case NEW_TARIFF:
//                return CommandsGeneral.NEW_TARIFF.getCommand();
//
//            case SUBSCRIBER_PROFILE:
//                return CommandsGeneral.SUBSCRIBER_PROFILE.getCommand();
//
//            case CHANGE_LANGUAGE:
//                return CommandsGeneral.CHANGE_LANGUAGE.getCommand();
//
////		default:
////			LOGGER.warn("Unknown operation.");
////			command = new EmptyCommand();
//        }
        
        return command;
    }
}
