package com.dimbo.servlets;

import com.dimbo.command.Command;
import com.dimbo.command.CommandDispatcher;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainController {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * {@inheritDoc}
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    /**
     * {@inheritDoc}
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

    }

    /**
     * Process request.
     *
     * @param request  the request
     * @param response the response
     * @throws ServletException the servlet exception
     * @throws IOException      Signals that an I/O exception has occurred.
     */
    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response) throws ServletException, IOException {
        CommandDispatcher commandDispatcher = new CommandDispatcher();
        Command command = commandDispatcher.getCommand(request);
        String page = command.execute(request);
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);

        dispatcher.forward(request, response);
    }
}
