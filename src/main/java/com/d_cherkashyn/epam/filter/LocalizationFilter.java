package com.d_cherkashyn.epam.filter;

import com.d_cherkashyn.epam.helper.Localization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"*"})
public class LocalizationFilter implements Filter {
    Logger LOGGER = LoggerFactory.getLogger(LocalizationFilter.class);
    
    @Override
    public void init(FilterConfig config) throws ServletException {
    }
    
    /**
     * Localization filter. adds JSON with localization strings to every request
     * (so front-end is able to obtain any string it wants)
     *
     * @param req
     * @param res
     * @param chain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws ServletException, IOException {
        
        HttpSession s = ((HttpServletRequest) req).getSession();
        String locale = (String) s.getAttribute("locale");
        req.setAttribute("JSONMessages", Localization.getBundleString(locale));
        
        chain.doFilter(req, res);
    }
    
    @Override
    public void destroy() {
    }
}
