package com.dimbo.filter;

import com.dimbo.helper.AccessHelper;
import com.dimbo.manager.PagesResourceManager;
import com.dimbo.model.Roles;
import com.dimbo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@WebFilter(urlPatterns = {"*"})
public class AccessFilter implements Filter {
    Logger LOGGER = LoggerFactory.getLogger(AccessFilter.class);
    
    String regex = "(.*)/(.*)";
    Pattern pattern = Pattern.compile(regex);
    
    @Override
    public void init(FilterConfig config) throws ServletException {
    }
    
    /**
     * Encoding filter. Changes encoding of every request and response
     *
     * @param req
     * @param res
     * @param chain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
        LOGGER.info("In access filter");
        HttpServletRequest request = (HttpServletRequest) req;
        
        if (!AccessHelper.isAccessAllowed(request)) {
            String errorPage = PagesResourceManager
                .getPage("access_denied_jsp");
            
            RequestDispatcher rd = req.getRequestDispatcher(errorPage);
            
            rd.forward(req, res);
        } else {
            LOGGER.info("Access granted, doing next filter");
            chain.doFilter(req, res);
        }
    }
    
    
    @Override
    public void destroy() {
    }
}
