package com.d_cherkashyn.epam.command.general;

import com.d_cherkashyn.epam.command.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;


/**
 * The Class LoginCommand.
 */
public class CreatePDFCommand implements Command {
    
    /**
     * The Constant LOGGER.
     */
    static final Logger LOGGER = LoggerFactory
        .getLogger(CreatePDFCommand.class);
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(HttpServletRequest request) {
        
        
        return "hehe";
    }
}
