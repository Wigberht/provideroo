package com.dimbo.helper.chat;

import com.dimbo.model.Message;
import com.dimbo.rest.JSONService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class MessageEncoder implements Encoder.Text<Message> {
    
    Logger LOGGER = LoggerFactory.getLogger(MessageEncoder.class);
    
    @Override
    public String encode(Message message) throws EncodeException {
        LOGGER.info("Encoding the message " + message);
        return JSONService.toJSON(message);
    }
    
    @Override
    public void init(EndpointConfig endpointConfig) {
    
    }
    
    @Override
    public void destroy() {
    
    }
}
