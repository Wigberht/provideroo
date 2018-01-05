package com.dimbo.servlets;

import com.dimbo.ConnectionPool;
import com.dimbo.dao.models.message.MysqlMessage;
import com.dimbo.model.Message;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;


public class LandingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection connection = ConnectionPool.getInstance().getConnection();
        Message[] messages = (new MysqlMessage(connection)).getMessages(1);
        req.setAttribute("messages", messages);

        RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
        rd.forward(req, resp);
    }
}
