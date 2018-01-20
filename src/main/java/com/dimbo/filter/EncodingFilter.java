package com.dimbo.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = {"*"})
public class EncodingFilter implements Filter {
    Logger LOGGER = LoggerFactory.getLogger(EncodingFilter.class);
    
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
        req.setCharacterEncoding("UTF8");
        res.setCharacterEncoding("UTF8");
        chain.doFilter(req, res);
    }
    
    @Override
    public void destroy() {
    }
}
