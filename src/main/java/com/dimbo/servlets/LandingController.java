package com.dimbo.servlets;

import com.dimbo.ConnectionPool;
import com.dimbo.dao.factory.FactoryGenerator;
import com.dimbo.managers.PagesResourceManager;

import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/LandingController")
public class LandingController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
        throws ServletException, IOException {
        String landingPage = PagesResourceManager.getPage("index");
        Connection connection = ConnectionPool.conn();

        req.setAttribute("messages",
            FactoryGenerator.getFactory().makeMessageDAO(connection).getMessages(1));

        ConnectionPool.returnConn(connection);
        RequestDispatcher rd = req.getRequestDispatcher(landingPage);
        rd.forward(req, resp);
    }
}
