package com.dimbo.servlets;

import com.dimbo.ConnectionPool;
import com.dimbo.dao.factory.FactoryGenerator;
import com.google.gson.Gson;
import javafx.util.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

@WebServlet("/AjaxController")
public class AjaxController extends HttpServlet {
    
    Logger LOGGER = LoggerFactory.getLogger(AjaxController.class);
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("Method " + req.getParameter("method"));
        LOGGER.info("user id " + req.getParameter("userId"));
        process(req, resp);
    }
    
    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LOGGER.info("In process");
        String method = req.getParameter("method");
        LOGGER.info("Method: " + method);
        
        Gson jsonResponse = new Gson();
        String resultString = "";
        Pair<String, String> result;
        
        
        switch (method) {
            case "banUser":
//                int userId = Integer.parseInt(req.getParameter("userId"));
//                result = new Pair<>("banned", Boolean.toString(banUser(userId)));
//                resultString = jsonResponse.toJson(result);
                break;
        }
        
        resp.setContentType("json");
        resp.getWriter()
            .write(resultString);
    }
    
    private boolean banUser(int userId) {
//        Connection connection = ConnectionPool.conn();
//
//        boolean success = FactoryGenerator.getFactory()
//                                          .makeUserDAO(connection)
//                                          .setBanned(userId);
//        ConnectionPool.returnConn(connection);

//        return success;
        return true;
    }
    
    private boolean unbanUser(int userId) {
        return true;
    }
}
