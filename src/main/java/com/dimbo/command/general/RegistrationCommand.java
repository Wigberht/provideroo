package com.dimbo.command.general;

import com.dimbo.command.Command;
import com.dimbo.helper.auth.Registration;
import com.dimbo.managers.PagesResourceManager;
import com.dimbo.model.Account;
import com.dimbo.model.Subscriber;
import com.dimbo.model.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegistrationCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationCommand.class);

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(HttpServletRequest request) {
        String page = PagesResourceManager.getPage("register_user");

        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String birthDate = request.getParameter("birth_date");

        Registration registration = new Registration();
        HttpSession s = request.getSession();

        User user = registration.registerUser(new User(login, password));
        LOGGER.info("user created: ", user);
        if (user == null) {
            s.setAttribute("userError", true);
        }

        Account account = registration.registerAccount();
        if (account == null) {
            s.setAttribute("accountError", true);
        }

        if (account != null && user != null) {
            Subscriber subscriber = registration.registerSubscriber(
                new Subscriber(
                    firstName, lastName, birthDate, user.getId(), account.getId()
                )
            );
            if (subscriber == null) {
                s.setAttribute("subscriberError", true);
            }
        }

        registration.closeConnection();

        return page;
    }
}
