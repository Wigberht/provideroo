package com.d_cherkashyn.epam.command.commands;

import com.d_cherkashyn.epam.command.Command;
import com.d_cherkashyn.epam.command.general.*;

/**
 * Enumeration that provides a list of all commands
 * that are available to any user
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
    
    /**
     * Constructor that are used to initialize a object that enumeration element
     * represents
     *
     * @param command object to initialize element with
     */
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
