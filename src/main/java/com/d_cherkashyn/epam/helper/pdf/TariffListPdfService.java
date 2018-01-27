package com.d_cherkashyn.epam.helper.pdf;

import com.d_cherkashyn.epam.helper.service.ServiceService;
import com.d_cherkashyn.epam.model.Service;
import com.d_cherkashyn.epam.model.Tariff;
import org.apache.commons.lang3.text.WordUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class TariffListPdfService {
    
    Logger LOGGER = LoggerFactory.getLogger(TariffListPdfService.class);
    
    private List<Service> services;
    private PDDocument document;
    private PDPageContentStream contentStream;
    private ByteArrayOutputStream output;
    private PDType0Font titleFont;
    private PDType0Font mainFont;
    private PDImageXObject logoImage;
    
    private float maxRowY;
    private static final int WRAP_LENGTH = 25;
    private ResourceBundle resourceBundle;
    
    
    public TariffListPdfService(String locale) throws IOException {
        String[] localeParts = locale.split("_");
        resourceBundle = ResourceBundle.getBundle("messages", new Locale(localeParts[0]));
        
        ServiceService serviceService = new ServiceService();
        services = serviceService.getAllServices();
        serviceService.returnConnection();
        
        initDocument();
    }
    
    private PDType0Font getMainFont() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL fontResource = classLoader
            .getResource("/font/liberation-sans/LiberationSans-Regular.ttf");
        
        return PDType0Font.load(document, new File(fontResource.getFile()));
    }
    
    private PDType0Font getTitleFont() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL fontResource = classLoader
            .getResource("/font/liberation-sans/LiberationSans-Bold.ttf");
        
        return PDType0Font.load(document, new File(fontResource.getFile()));
    }
    
    private PDImageXObject getLogoImage() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        URL fontResource = classLoader
            .getResource("/images/internet-provider-small.jpg");
        
        return PDImageXObject.createFromFile(fontResource.getPath(), document);
    }
    
    private void initDocument() throws IOException {
        document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);
        
        titleFont = getTitleFont();
        mainFont = getMainFont();
        
        contentStream = new PDPageContentStream(document, getLastPage());
        output = new ByteArrayOutputStream();
        
        contentStream.setLineDashPattern(new float[]{3, 0}, 0);
        lineWidth(2);
        maxRowY = 1000;
        
    }
    
    private PDPage getLastPage() {
        return document.getPage(document.getNumberOfPages() - 1);
    }
    
    private void writeText(float x, float y, String text) throws IOException {
        contentStream.beginText();
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
    
    private void longLine(float startX, float startY, String text) throws IOException {
        int lineTopMargin = 15;
        String[] wrT = WordUtils.wrap(text, WRAP_LENGTH).split("\\r?\\n");
        for (int i = 0; i < wrT.length; i++) {
            startY -= lineTopMargin;
            
            contentStream.beginText();
            contentStream.newLineAtOffset(startX, startY);
            contentStream.showText(wrT[i]);
            contentStream.endText();
            
            maxRowY = (startY < maxRowY) ? startY : maxRowY;
        }
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
    
    private void writeRow(float rowX, float rowY, Tariff tariff) throws IOException {
        int columnStep = 110;
        String text = tariff.getTitle();
        longLine(rowX, rowY, text);
        
        text = tariff.getDescription();
        rowX += columnStep;
        longLine(rowX, rowY, text);
        
        text = String.valueOf(tariff.getNumberOfDays());
        rowX += columnStep + 50;
        longLine(rowX, rowY, text);
        
        text = tariff.getCost() + " " + tariff.getCurrencyShortname();
        rowX += columnStep;
        longLine(rowX, rowY, text);
        
    }
    
    private void drawTable(float tableX, float tableY,
                           float tableWidth, float tableHeight,
                           Service service) throws IOException {
        
        rect(tableX, tableY, tableWidth, tableHeight);
        font(mainFont, 14);
        float serviceTitleX = tableX + 10;
        float serviceTitleY = tableY + 20;
        
        writeText(serviceTitleX, serviceTitleY, service.getTitle());
        
        
        float headerX = tableX + 20;
        float headerY = tableY - 20;
        writeText(headerX, headerY, resourceBundle.getString("title"));
        
        headerX += 110;
        writeText(headerX, headerY, resourceBundle.getString("description"));
        
        headerX += 160;
        writeText(headerX, headerY, resourceBundle.getString("number_of_days"));
        
        headerX += 110;
        writeText(headerX, headerY, resourceBundle.getString("cost"));
        
        font(mainFont, 10);
        float rowX = tableX + 20;
        float rowY = headerY - 30;
        
        for (Tariff tariff : service.getTariffs()) {
            writeRow(rowX, rowY, tariff);
            rowY = maxRowY - 30;
        }
        
    }
    
    private void addPage() throws IOException {
        contentStream.close();
        PDPage page = new PDPage();
        document.addPage(page);
        contentStream = new PDPageContentStream(document, page);
    }
    
    public ByteArrayOutputStream getOutput() throws IOException {
        
        font(titleFont, 20);
        
        contentStream.drawImage(getLogoImage(), 20, 710);
        
        float x = 180;
        float y = 700;
        writeText(x, y, resourceBundle.getString("list_of_services_and_tariffs"));
        
        float tableX = 50;
        float tableY = 640;
        float tableWidth = 530;
        float tableHeight;
        for (Service service : services) {
            tableHeight = 50 * (service.getTariffs().size() + 1);
            if (tableHeight + 100 > maxRowY) {
                addPage();
                tableY = 740;
                maxRowY = 1000;
            }
            drawTable(tableX, tableY, tableWidth, tableHeight, service);
            
            tableY -= (tableHeight + 40);
        }
        
        
        contentStream.close();
        
        document.save(output);
        document.close();
        
        return output;
    }
}
