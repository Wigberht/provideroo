package com.d_cherkashyn.epam.helper.chat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import javax.ws.rs.Encoded;

@ClientEndpoint(
    encoders = {MessageEncoder.class},
    decoders = {MessageDecoder.class}
)
public class ChatClientEndpoint {
    Logger LOGGER = LoggerFactory.getLogger(ChatClientEndpoint.class);
    
    @OnOpen
    public void onOpen() {
        LOGGER.info("Chat client endpoint opened");
    }
    
    @OnClose
    public void onClose() {
        LOGGER.info("Chat client endpoint closed");
    }
    
    @OnMessage
    @Encoded
    public String onMessage(String message) {
        LOGGER.info("Message received by client: " + message);
        return message;
    }
    
    @OnError
    public void onError(Throwable t) {
        LOGGER.error("Chat endpoint encountered an error", t);
    }
    
}
