package com.dimbo.helper;

import com.dimbo.model.Role;
import com.dimbo.model.Roles;
import com.dimbo.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccessHelper {
    private static Logger LOGGER = LoggerFactory.getLogger(AccessHelper.class);
    
    private static final String regex = "(.*)/(.*)";
    private static final Pattern pattern = Pattern.compile(regex);
    
    public static boolean isAccessAllowed(HttpServletRequest request) {
        String url = request.getRequestURI();
        if (false) {
            LOGGER.info("Url: " + url);
            LOGGER.info("is user: " + isUser(request));
            LOGGER.info("is admin uri: " + getUriRole(request)
                .equalsIgnoreCase("admin"));
            
            if (getRequestUser(request) != null) {
                LOGGER.info("is request user admin: " +
                    getRequestUser(request).isAdmin());
            }
        }
        
        
        if (!isUser(request)) {
//            LOGGER.info("is not user " + url);
            return true;
        }

//        LOGGER.info("URI ROLE  : "+getUriRole(request));
//        LOGGER.info("ADMIN ROLE: "+Roles.ADMIN.name());
//        LOGGER.info("EQUALS    : "+getUriRole(request).equalsIgnoreCase(Roles.ADMIN.name()));
//        LOGGER.info("REQ IS ADM: "+getRequestUser(request).isAdmin());
        
        if (getRequestUser(request) == null) {
            return false;
        }
        
        if (getUriRole(request).equalsIgnoreCase(Roles.ADMIN.name()) &&
            !getRequestUser(request).isAdmin()) {
            LOGGER.info("First false");
            return false;
        }
        
        if (getUriRole(request).equalsIgnoreCase(Roles.SUBSCRIBER.name()) &&
            getRequestUser(request).isAdmin()) {
            LOGGER.info("Second false");
            return false;
        }
        
        return true;
    }
    
    private static String getUriRole(HttpServletRequest request) {
        Matcher matcher = pattern.matcher(request.getRequestURI());
        
        return matcher.find() ? matcher.group(1).replace("/", "") : "";
    }
    
    private static boolean isUser(HttpServletRequest request) {
        Matcher matcher = pattern.matcher(request.getRequestURI());
        boolean find = matcher.find();
        boolean isUser = false;
        if (find) {
            String requestRole = matcher.group(1).replace("/", "");
            for (Roles role : Roles.values()) {
                if (requestRole.equalsIgnoreCase(role.name())) {
                    isUser = true;
                }
            }
        }
        
        return isUser;
    }
    
    private static User getRequestUser(HttpServletRequest request) {
        User requestUser = null;
        if (null != request.getSession().getAttribute("user")) {
            requestUser = (User) request.getSession().getAttribute("user");
        }
        
        return requestUser;
    }
}
