package com.dimbo.servlets;

import com.google.gson.Gson;
import javafx.util.Pair;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/AjaxController")
public class AjaxController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        Gson jsonResponse = new Gson();
        Pair<String, Boolean> result;
        
        
        switch (method) {
            case "banUser":
                int userId = Integer.parseInt(req.getParameter("userId"));
                result = new Pair<>("banned", banUser(userId));
                resp.getWriter()
                    .append(jsonResponse.toJson(result));
                break;
        }
        
        super.doPost(req, resp);
    }
    
    private boolean banUser(int userId) {
        return true;
    }
    
    private boolean unbanUser(int userId) {
        return true;
    }
}
