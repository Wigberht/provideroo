package com.dimbo.helper.pdf;

import com.dimbo.helper.service.ServiceService;
import com.dimbo.model.Service;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class TariffListPdfService {
    private List<Service> services;
    private PDDocument document;
    private PDPageContentStream contentStream;
    private ByteArrayOutputStream output;
    
    public TariffListPdfService() throws IOException {
        ServiceService serviceService = new ServiceService();
        services = serviceService.getAllServices();
        serviceService.returnConnection();
        
        initDocument();
    }
    
    private void initDocument() throws IOException {
        document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);
        
        contentStream = new PDPageContentStream(document, getPage());
        output = new ByteArrayOutputStream();
    }
    
    private PDPage getPage() {
        return document.getPage(document.getNumberOfPages() - 1);
    }
    
    private void writeText(float x, float y, String text) throws IOException {
        contentStream.beginText();
//        contentStream.setFont(PDType1Font.COURIER, 24);
        contentStream.newLineAtOffset(x, y);
        contentStream.showText(text);
        contentStream.endText();
    }
    
    private void drawLine(float x1, float y1, float x2, float y2) throws IOException {
        moveTo(x1, y1);
        lineTo(x2, y2);
        stroke();
    }
    
    private void moveTo(float x, float y) throws IOException {
        contentStream.moveTo(x, y);
    }
    
    private void lineTo(float x, float y) throws IOException {
        contentStream.lineTo(x, y);
    }
    
    private void stroke() throws IOException {
        contentStream.stroke();
    }
    
    private void lineWidth(int width) throws IOException {
        contentStream.setLineWidth(width);
    }
    
    private void font(PDFont font, int size) throws IOException {
        contentStream.setFont(font, size);
    }
    
    private void rect(float x, float y, float width, float height) throws IOException {
        moveTo(x, y);
        
        x += width;
        lineTo(x, y);
        y -= height;
        lineTo(x, y);
        x -= width;
        lineTo(x, y);
        y += height;
        lineTo(x, y);
        
        stroke();
    }
    
    public ByteArrayOutputStream getOutput() throws IOException {
        
        contentStream.setLineDashPattern(new float[]{3, 0}, 0);
        lineWidth(2);
        
        PDType1Font mainFont = PDType1Font.COURIER;
        font(mainFont, 24);
        
        
        float x = 100;
        float y = 740;
        writeText(x, y, "List of services and tariffs");
        
        rect(50, 700, 530, 200);
        font(mainFont, 14);
        
        float headerX = 60;
        float headery = 670;
        
        writeText(headerX, headery, "Title");
        headerX += 70;
        writeText(headerX, headery, "Description");
        headerX += 110;
        writeText(headerX, headery, "Days");
        headerX += 70;
        writeText(headerX, headery, "Cost");
        writeText(headerX, headery, "Cost");
        
        
        contentStream.close();
        
        document.save(output);
        document.close();
        
        return output;
    }
}
