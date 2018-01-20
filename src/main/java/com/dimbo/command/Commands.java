package com.dimbo.command;

import com.dimbo.command.admin.*;
import com.dimbo.command.general.LoginCommand;
import com.dimbo.command.general.LogoutCommand;
import com.dimbo.command.general.RegistrationCommand;
import com.dimbo.command.subscriber.ShowSubscriberProfileCommand;

/**
 * The Enum Commands.
 */
public enum Commands {
    /**
     * Commands that are available
     */
    REGISTRATION(new RegistrationCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    SUBSCRIBER_LIST(new SubscriberListCommand()),
    SERVICE_LIST(new ServiceListCommand()),
    ADD_SERVICE(new AddServiceCommand()),
    ADD_TARIFF(new AddTariffCommand()),
    NEW_TARIFF(new NewTariffCommand()),
    SHOW_SUBSCRIBER_PROFILE(new ShowSubscriberProfileCommand());
    
    private Command command;
    
    Commands(Command command) {
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
