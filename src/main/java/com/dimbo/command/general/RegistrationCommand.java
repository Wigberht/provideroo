package com.dimbo.command.general;

import com.dimbo.command.Command;
import com.dimbo.helper.auth.Registration;
import com.dimbo.helper.validator.MainValidator;
import com.dimbo.managers.PagesResourceManager;
import com.dimbo.model.Account;
import com.dimbo.model.Subscriber;
import com.dimbo.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.applet.Main;

public class RegistrationCommand implements Command {
    
    private static final Logger LOGGER = LoggerFactory
        .getLogger(RegistrationCommand.class);
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = PagesResourceManager.getPage("register_user");
        HttpSession s = request.getSession();
        
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String birthDate = request.getParameter("birth_date");
        
        if (!MainValidator.login(login)) {
            s.setAttribute("loginError", true);
        }
        
        if (!MainValidator.password(password)) {
            s.setAttribute("passwordError", true);
        }
        
        if (!MainValidator.simpleText(firstName)) {
            s.setAttribute("firstNameError", true);
        }
        
        if (!MainValidator.simpleText(lastName)) {
            s.setAttribute("lastNameError", true);
        }
        
        if (!MainValidator.birthday(birthDate)) {
            s.setAttribute("birthdayError", true);
        }
        
        if (!MainValidator
            .registrationValidator(login, password, firstName, lastName, birthDate)) {
            s.setAttribute("validationError", true);
            
            return page;
        }
        
        Registration registration = new Registration();
        
        User user = registration.registerUser(new User(login, password));
        if (user == null) {
            s.setAttribute("userError", true);
        }
        
        Account account = registration.registerAccount();
        if (account == null) {
            s.setAttribute("accountError", true);
        }
        
        if (account != null && user != null) {
            Subscriber subscriber = registration.registerSubscriber(
                new Subscriber(firstName, lastName, birthDate, user.getId(),
                               account.getId())
            );
            if (subscriber == null) {
                s.setAttribute("subscriberError", true);
            }
        }
        
        registration.closeConnection();
        
        return page;
    }
}
