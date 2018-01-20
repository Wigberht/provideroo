package com.dimbo.filter;

import com.dimbo.command.Commands;
import com.dimbo.model.Roles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.rmi.runtime.Log;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@WebFilter(urlPatterns = {"*"})
public class UrlRewriteFilter implements Filter {
    Logger LOGGER = LoggerFactory.getLogger(UrlRewriteFilter.class);
    
    String regex = "(.*)/(.*)";
    Pattern pattern = Pattern.compile(regex);
    
    @Override
    public void init(FilterConfig config) throws ServletException {
    }
    
    /**
     * This lovely filter helps in making URLs more user-friendly.
     * Converts 'MainController?command=subscriber_list' to 'admin/subscriber_list'
     * And '/jsp/admin/access_denied.jsp' to '/admin/control_panel'
     *
     * @param req
     * @param res
     * @param chain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
        LOGGER.info("Inr url rewrite filter");
        HttpServletRequest request = (HttpServletRequest) req;
        
        Matcher matcher = pattern.matcher(request.getRequestURI());
        boolean find = matcher.find();
        if (find) {
            boolean isUser = false;
            for (Roles role : Roles.values()) {
                if (matcher.group(1).contains(role.name().toLowerCase()))
                    isUser = true;
            }
            
            if (isUser) {
                String resultPage = getResultPage(matcher);
                RequestDispatcher requestDispatcher = request
                    .getRequestDispatcher(resultPage);
                requestDispatcher.forward(req, res);
            } else {
                chain.doFilter(req, res);
            }
        } else {
            chain.doFilter(req, res);
        }
    }
    
    private String getResultPage(Matcher matcher) {
        String page;
        boolean isCommand = false;
        String user = matcher.group(1);
        String matchPage = matcher.group(2);
        
        for (Commands command : Commands.values()) {
            if (command.name().equalsIgnoreCase(matchPage)) {
                isCommand = true;
                break;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        if (isCommand) {
            page = "/MainController?command=" + matchPage;
        } else {
            sb.append("/jsp")
              .append(user)
              .append("/")
              .append(matchPage)
              .append(".jsp");
            page = sb.toString();
        }
        
        LOGGER.info("Accessing page: " + page);
        
        return page;
    }
    
    @Override
    public void destroy() {
    }
}
