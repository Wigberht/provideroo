package com.d_cherkashyn.epam.tag;


import com.d_cherkashyn.epam.rest.JSONService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class Jsonify extends TagSupport {
    Logger LOGGER = LoggerFactory.getLogger(Jsonify.class);
    
    private Object obj;
    
    
    @Override
    public int doStartTag() throws JspException {
        
        try {
            pageContext.getOut().write(JSONService.toJSON(obj));
        } catch (IOException e) {
            LOGGER.error("start tag was unable to convert", e);
        }
        
        return SKIP_BODY;
    }
    
    public Object getObj() {
        return obj;
    }
    
    public void setObj(Object obj) {
        this.obj = obj;
    }
}
