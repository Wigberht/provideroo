package com.dimbo.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;


/**
 * The Class LoginCommand.
 */
public class LoginCommand implements Command {

	/** The Constant LOGGER. */
	static final Logger LOGGER = LoggerFactory.getLogger(LoginCommand.class);

	/**
	* {@inheritDoc}
	*/
	@Override
	public String execute(HttpServletRequest request) {

		String page = null;
//		Client client = null;
//
//		String login = (String) request.getParameter("login");
//		int pass = request.getParameter("password").hashCode();
//
//		try {
//			client = Authorization.logIn(login, pass);
//		} catch (TechnicalException e) {
//			LOGGER.error("Can not authorizate client", e);
//			return ResourceManager.getInstance().getParametr("error_page");
//
//		} catch (LogicException e) {
//			request.setAttribute("warn", true);
//			return ResourceManager.getInstance().getParametr("autorization_page");
//		}
//
//		request.getSession().setAttribute("clientId", client.getId());
//		request.getSession().setAttribute("name", client.getName());
//		request.getSession().setAttribute("login", client.getLogin());
//		request.getSession().setAttribute("access", client.getRole());
//
//		String locale = (String) request.getParameter("localeChooser");
//		request.getSession().setAttribute("locale", locale);
//
//		switch (client.getRole()) {
//
//		case READER:
//			page = ResourceManager.getInstance().getParametr("catalog_reader_page");
//			break;
//
//		case LIBRARIAN:
//			page = ResourceManager.getInstance().getParametr("clients");
//			break;
//		}

		return page;
	}
}
