package com.dimbo.command.commands;

import com.dimbo.command.Command;
import com.dimbo.command.subscriber.ServiceListSubscriberCommand;
import com.dimbo.command.subscriber.SubscriberProfileCommand;

/**
 * The Enum CommandsGeneral.
 */
public enum CommandsSubscriber {
    /**
     * Commands available to subscriber
     */
    SERVICE_LIST(new ServiceListSubscriberCommand()),
    PROFILE(new SubscriberProfileCommand());
    
    private Command command;
    
    CommandsSubscriber(Command command) {
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
