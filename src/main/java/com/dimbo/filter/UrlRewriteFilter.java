package com.dimbo.filter;

import com.dimbo.command.Commands;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebFilter(urlPatterns = {"*"})
public class UrlRewriteFilter implements Filter {
    Logger LOGGER = LoggerFactory.getLogger(UrlRewriteFilter.class);
    
    String regex = ".*?admin/(.*)";
    Pattern pattern = Pattern.compile(regex);
    
    @Override
    public void init(FilterConfig config) throws ServletException {
    }
    
    /**
     * This lovely filter helps in making URLs more user-friendly.
     * Converts 'MainController?command=subscriber_list' to 'admin/subscriber_list'
     * And '/jsp/admin/control_panel.jsp' to '/admin/control_panel'
     *
     * @param req
     * @param res
     * @param chain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
        LOGGER.info("In url filter");
        HttpServletRequest request = (HttpServletRequest) req;
        
        if (request.getRequestURI().contains("admin/")) {
            Matcher matcher = pattern.matcher(request.getRequestURI());
            if (matcher.find()) {
                String adminPage = getAdminPage(matcher.group(1));
                LOGGER.info("page: " + adminPage);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(adminPage);
                requestDispatcher.forward(req, res);
            } else {
                chain.doFilter(req, res);
            }
        } else {
            chain.doFilter(req, res);
        }
        
    }
    
    private String getAdminPage(String matchString) {
        String page;
        boolean isCommand = false;
        
        for (Commands command : Commands.values()) {
            if (command.name().equalsIgnoreCase(matchString)) {
                isCommand = true;
                break;
            }
        }
        
        if (isCommand) {
            page = "/MainController?command=" + matchString;
        } else {
            page = "/jsp/admin/" + matchString + ".jsp";
        }
        
        return page;
    }
    
    @Override
    public void destroy() {
    }
}
