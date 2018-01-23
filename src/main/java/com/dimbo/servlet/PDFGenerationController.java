package com.dimbo.servlet;

import com.dimbo.helper.pdf.TariffListPdfService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet("/ServicesPDF")
public class PDFGenerationController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TariffListPdfService tariffListPdfService = new TariffListPdfService(
            (String) req.getSession().getAttribute("locale"));
        
        ByteArrayOutputStream output = tariffListPdfService.getOutput();
        
        resp.setContentType("application/pdf");
        
        LocalDate localDate = LocalDate.now();
        String date = DateTimeFormatter.ofPattern("yyy_MM_dd").format(localDate);
        
        String filename = "tariff_list_" + date + ".pdf";
        
        //response.addHeader("Content-Type", "application/force-download"); //--< Use this if you want the file to download rather than display
        resp.addHeader("Content-Disposition", "inline; filename=" + filename);
        resp.getOutputStream().write(output.toByteArray());
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
