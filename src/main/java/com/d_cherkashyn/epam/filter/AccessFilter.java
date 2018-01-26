package com.d_cherkashyn.epam.filter;

import com.d_cherkashyn.epam.helper.AccessHelper;
import com.d_cherkashyn.epam.manager.PagesResourceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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
        HttpServletRequest request = (HttpServletRequest) req;
        
        if (!AccessHelper.isAccessAllowed(request)) {
            String errorPage = PagesResourceManager
                .getPage("access_denied_jsp");
            
            RequestDispatcher rd = req.getRequestDispatcher(errorPage);
            
            rd.forward(req, res);
        } else {
            chain.doFilter(req, res);
        }
    }
    
    
    @Override
    public void destroy() {
    }
}
