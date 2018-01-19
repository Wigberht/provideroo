package com.dimbo.command.general;

import com.dimbo.ConnectionPool;
import com.dimbo.command.Command;
import com.dimbo.dao.factory.FactoryGenerator;
import com.dimbo.helper.auth.Auth;
import com.dimbo.managers.PagesResourceManager;
import com.dimbo.model.Service;
import com.dimbo.model.User;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Connection;


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
