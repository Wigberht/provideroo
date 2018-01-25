package com.dimbo.command.commands;

import com.dimbo.command.Command;
import com.dimbo.command.admin.*;
import com.dimbo.command.general.*;
import com.dimbo.command.subscriber.SubscriberProfileCommand;

/**
 * The Enum CommandsGeneral.
 */
public enum CommandsGeneral {
    /**
     * General commands that are available to everyone
     */
    MISSING_COMMAND(new MissingCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    CHANGE_LANGUAGE(new ChangeLanguageCommand()),
    CHAT_LIST(new ChatListCommand());
    
    private Command command;
    
    CommandsGeneral(Command command) {
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
