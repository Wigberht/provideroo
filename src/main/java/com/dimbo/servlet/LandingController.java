package com.dimbo.servlet;

import com.dimbo.manager.PagesResourceManager;

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

        RequestDispatcher rd = req.getRequestDispatcher(landingPage);
        rd.forward(req, resp);
    }
}
