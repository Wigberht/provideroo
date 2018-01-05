package com.dimbo.command;

import javax.servlet.http.HttpServletRequest;

/**
 * The Interface Command.
 */
public interface Command {

	/**
	 * Execute command.
	 *
	 * @param request the request
	 * @return name of jsp
	 */
	String execute(HttpServletRequest request);

}
