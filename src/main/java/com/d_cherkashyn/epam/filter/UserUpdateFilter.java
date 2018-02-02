package com.d_cherkashyn.epam.filter;

import com.d_cherkashyn.epam.helper.service.UserService;
import com.d_cherkashyn.epam.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"*"})
public class UserUpdateFilter implements Filter {
    Logger LOGGER = LoggerFactory.getLogger(UserUpdateFilter.class);
    
    @Override
    public void init(FilterConfig config) throws ServletException {
    }
    
    /**
     * User update filter. Checks if user was updated in db, and if it was - update it
     * to session so current user do not have to wait until next login
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
        
        if (s.getAttribute("user") != null) {
            User user = (User) s.getAttribute("user");
            String updateTime = UserService.getUserUpdatedTime(user.getId());
            
            if (!updateTime.equalsIgnoreCase(user.getUpdatedAt())) {
                s.setAttribute("user", UserService.getUser(user.getId()));
            }
        }
        
        chain.doFilter(req, res);
    }
    
    @Override
    public void destroy() {
    }
}
