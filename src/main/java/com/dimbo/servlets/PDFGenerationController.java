package com.dimbo.servlets;

import com.dimbo.ConnectionPool;
import com.dimbo.dao.factory.FactoryGenerator;
import com.dimbo.helper.pdf.TariffListPdfService;
import com.dimbo.model.Service;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/ServicesPDF")
public class PDFGenerationController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TariffListPdfService tariffListPdfService = new TariffListPdfService();
        ByteArrayOutputStream output = tariffListPdfService.getOutput();
        
        resp.setContentType("application/pdf");
        
        LocalDate localDate = LocalDate.now();
        String date = DateTimeFormatter.ofPattern("yyy_MM_dd")
                                       .format(localDate);
        
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
