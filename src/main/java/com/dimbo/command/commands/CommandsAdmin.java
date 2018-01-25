package com.dimbo.command.commands;

import com.dimbo.command.Command;
import com.dimbo.command.admin.*;
import com.dimbo.command.general.*;
import com.dimbo.command.subscriber.SubscriberProfileCommand;

/**
 * The Enum CommandsGeneral.
 */
public enum CommandsAdmin {
    /**
     * CommandsGeneral that are available
     */
    REGISTRATION(new RegistrationCommand()),
    SUBSCRIBER_LIST(new SubscriberListCommand()),
    SERVICE_LIST(new ServiceListAdminCommand()),
    ADD_SERVICE(new AddServiceCommand()),
    ADD_TARIFF(new AddTariffCommand()),
    NEW_TARIFF(new NewTariffCommand()),
    CHAT_LIST(new ChatListCommand());
    
    private Command command;
    
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
