package com.d_cherkashyn.epam.command.commands;

import com.d_cherkashyn.epam.command.Command;
import com.d_cherkashyn.epam.command.general.ChatListCommand;
import com.d_cherkashyn.epam.command.subscriber.ServiceListSubscriberCommand;
import com.d_cherkashyn.epam.command.subscriber.SubscriberProfileCommand;

/**
 * The Enum CommandsGeneral.
 */
public enum CommandsSubscriber {
    /**
     * Commands available to subscriber
     */
    SERVICE_LIST(new ServiceListSubscriberCommand()),
    PROFILE(new SubscriberProfileCommand()),
    CHAT_LIST(new ChatListCommand());
    
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
