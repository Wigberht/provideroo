package com.d_cherkashyn.epam.helper.chat;

import com.d_cherkashyn.epam.model.Message;
import com.d_cherkashyn.epam.rest.JSONService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 * Decoder for messages that websocket acquires
 */
public class MessageDecoder implements Decoder.Text<Message> {
    Logger LOGGER = LoggerFactory.getLogger(MessageDecoder.class);
    
    @Override
    public Message decode(String s) throws DecodeException {
        LOGGER.info("Decoding the message: " + s);
        return (Message) JSONService.toObject(s, Message.class);
    }
    
    @Override
    public boolean willDecode(String s) {
        return true;
    }
    
    @Override
    public void init(EndpointConfig endpointConfig) {
    
    }
    
    @Override
    public void destroy() {
    
    }
}
