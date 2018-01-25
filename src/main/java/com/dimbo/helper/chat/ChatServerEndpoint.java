package com.dimbo.helper.chat;

import com.dimbo.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import javax.ws.rs.Encoded;
import java.io.IOException;

@ServerEndpoint(value = "/socket/chat",
    encoders = {MessageEncoder.class},
    decoders = {MessageDecoder.class}
)
public class ChatServerEndpoint {
    Logger LOGGER = LoggerFactory.getLogger(ChatServerEndpoint.class);
    
    @OnOpen
    public void onOpen() {
        LOGGER.info("Chat endpoint opened");
    }
    
    @OnClose
    public void onClose() {
        LOGGER.info("Chat endpoint closed");
    }
    
    @OnMessage
    public void onMessage(Message message,
                          Session session) throws IOException, EncodeException {
        LOGGER.info("Message received: " + message);
    
        Message response = new Message();
        response.setMessage(message.getMessage());
        response.setId(message.getId());
        session.getBasicRemote().sendObject(response);
    }
    
    @OnError
    public void onError(Throwable t) {
        LOGGER.error("Chat endpoint encountered an error", t);
    }
    
}
