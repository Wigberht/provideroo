package com.d_cherkashyn.epam.command.commands;

import com.d_cherkashyn.epam.command.Command;
import com.d_cherkashyn.epam.command.admin.*;
import com.d_cherkashyn.epam.command.general.*;

/**
 * Enumeration that provides a list of all commands
 * that are available to user with role "ADMIN"
 */
public enum CommandsAdmin {
    /**
     * Commands that are available
     */
    REGISTRATION(new RegistrationCommand()),
    SUBSCRIBER_LIST(new SubscriberListCommand()),
    SERVICE_LIST(new ServiceListAdminCommand()),
    ADD_SERVICE(new AddServiceCommand()),
    ADD_TARIFF(new AddTariffCommand()),
    NEW_TARIFF(new NewTariffCommand()),
    CHAT_LIST(new ChatListCommand());
    
    private Command command;
    
    /**
     * Constructor that are used to initialize a object that enumeration element
     * represents
     *
     * @param command object to initialize element with
     */
    CommandsAdmin(Command command) {
        this.command = command;
    }
    
    /**
     * Return instance
     *
     * @return command's class instance
     */
    public Command getCommand() {
        return this.command;
    }
}
