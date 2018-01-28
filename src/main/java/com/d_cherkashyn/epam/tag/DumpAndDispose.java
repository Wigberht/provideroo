package com.d_cherkashyn.epam.tag;

import com.d_cherkashyn.epam.manager.MessagesResourceManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.ResourceBundle;

public class DumpAndDispose extends TagSupport {
    private static final Logger LOGGER = LoggerFactory.getLogger(DumpAndDispose.class);
    
    private String key;
    private String messageKey;
    
    @Override
    public int doStartTag() throws JspException {
        
        HttpSession s = pageContext.getSession();
        Object var = s.getAttribute(key);
        
        if (messageKey == null) {
            try {
                pageContext.getOut().write(String.valueOf(s.getAttribute(key)));
            } catch (IOException e) {
                LOGGER.info("Unable to write session variable as it is", e);
            }
        } else if (var != null && ((boolean) var)) {
            String locale = (String) s.getAttribute("locale");
            if (locale.contains("_")) {
                locale = locale.split("_")[0];
            }
            ResourceBundle rb = MessagesResourceManager.getBundle(locale);
            try {
                
                String message = (String) rb.getObject(messageKey);
                pageContext.getOut().write(message);
                
            } catch (IOException e) {
                LOGGER.info("Message key not found", e);
            }
        }
        
        pageContext.getSession().removeAttribute(key);
        
        return super.doStartTag();
    }
    
    public String getKey() {
        return key;
    }
    
    public void setKey(String key) {
        this.key = key;
    }
    
    public String getMessageKey() {
        return messageKey;
    }
    
    public void setMessageKey(String messageKey) {
        this.messageKey = messageKey;
    }
}
