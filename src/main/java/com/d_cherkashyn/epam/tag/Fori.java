package com.d_cherkashyn.epam.tag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

public class Fori extends SimpleTagSupport {
    
    Logger LOGGER = LoggerFactory.getLogger(Fori.class);
    
    int start;
    int end;
    
    @Override
    public void doTag() throws JspException, IOException {
        for (int i = start; i <= end; i++) {
            getJspBody().invoke(getJspContext().getOut());
        }
    }
    
    public int getStart() {
        return start;
    }
    
    public void setStart(int start) {
        this.start = start;
    }
    
    public int getEnd() {
        return end;
    }
    
    public void setEnd(int end) {
        this.end = end;
    }
}
