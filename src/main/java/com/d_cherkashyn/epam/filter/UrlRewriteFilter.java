package com.d_cherkashyn.epam.filter;

import com.d_cherkashyn.epam.command.commands.CommandsAdmin;
import com.d_cherkashyn.epam.command.commands.CommandsGeneral;
import com.d_cherkashyn.epam.command.commands.CommandsSubscriber;
import com.d_cherkashyn.epam.model.Roles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Filter that makes urls more user-friendly
 */
public class UrlRewriteFilter implements Filter {
    Logger LOGGER = LoggerFactory.getLogger(UrlRewriteFilter.class);
    
    String regex = "/(.*)/(.*)";
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
    public void doFilter(ServletRequest req, ServletResponse res,
                         FilterChain chain) throws ServletException, IOException {
        
        HttpServletRequest request = (HttpServletRequest) req;
        Matcher matcher = pattern.matcher(request.getRequestURI());
        if (matcher.find()) {
            String roleName = matcher.group(1).toUpperCase();
            
            boolean isUser = false;
            for (Roles role : Roles.values()) {
                if (roleName.equals(role.name())) {
                    isUser = true;
                }
            }
            
            if (isUser) {
                String resultPage = getResultPage(matcher);
                RequestDispatcher rd = request.getRequestDispatcher(resultPage);
                rd.forward(req, res);
            } else {
                chain.doFilter(req, res);
            }
        } else {
            chain.doFilter(req, res);
        }
    }
    
    private String getResultPage(Matcher matcher) {
        String page;
        
        String user = matcher.group(1);
        String matchPage = matcher.group(2);
        
        boolean isCommand = isCommand(matchPage);
        StringBuilder sb = new StringBuilder();
        if (isCommand) {
            sb.append("/MainController?command=")
              .append(matchPage)
              .append("&role=")
              .append(user);
        } else {
            sb.append("/jsp/")
              .append(user)
              .append("/")
              .append(matchPage)
              .append(".jsp");
        }
        
        page = sb.toString();
        
        LOGGER.info("Accessing page: " + page);
        
        return page;
    }
    
    private boolean isCommand(String page) {
        for (CommandsGeneral command : CommandsGeneral.values()) {
            if (command.name().equalsIgnoreCase(page)) {
                return true;
            }
        }
        
        for (CommandsAdmin command : CommandsAdmin.values()) {
            if (command.name().equalsIgnoreCase(page)) {
                return true;
            }
        }
        
        for (CommandsSubscriber command : CommandsSubscriber.values()) {
            if (command.name().equalsIgnoreCase(page)) {
                return true;
            }
        }
        
        return false;
    }
    
    @Override
    public void destroy() {
    }
}
