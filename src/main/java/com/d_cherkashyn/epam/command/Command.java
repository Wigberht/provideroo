package com.d_cherkashyn.epam.command;

import javax.servlet.http.HttpServletRequest;

/**
 * Command interface.
 * basics for implementation command pattern
 */
public interface Command {
    
    /**
     * Execute command.
     *
     * @param request the request
     * @return name of jsp
     */
    String execute(HttpServletRequest request);
    
}
