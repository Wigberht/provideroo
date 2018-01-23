package com.dimbo.servlet;

import com.dimbo.command.Command;
import com.dimbo.command.CommandDispatcher;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/MainController")
public class MainController extends HttpServlet {

    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * {@inheritDoc}
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String page = processRequest(request);
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);

        dispatcher.forward(request, response);
    }

    /**
     * {@inheritDoc}
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String page = processRequest(request);

        response.sendRedirect(page);
    }

    /**
     * Process request.
     *
     * @param request the request
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
    private String processRequest(HttpServletRequest request) throws ServletException, IOException {
        CommandDispatcher commandDispatcher = new CommandDispatcher();
        Command command = commandDispatcher.getCommand(request);
//        String page = command.execute(request);
        return command.execute(request);
//        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
//
//        dispatcher.forward(request, response);

    }

}
