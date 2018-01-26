package com.d_cherkashyn.epam.helper.chat;

import com.d_cherkashyn.epam.helper.service.ChatService;
import com.d_cherkashyn.epam.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

@ServerEndpoint(value = "/socket/chat/{chatId}",
    encoders = {MessageEncoder.class},
    decoders = {MessageDecoder.class}
)
public class ChatServerEndpoint {
    
    Logger LOGGER = LoggerFactory.getLogger(ChatServerEndpoint.class);
    
    private static final Set<String> USERS = new ConcurrentSkipListSet<>();
    private static final List<Session> sessions = new ArrayList<>();
    private String user;
    private Session endpointSession;
    private boolean dupUserDetected;
    private long chatId;
    
    @OnOpen
    public void onOpen(@PathParam("chatId") long chatId,
                       Session session) throws IOException, EncodeException {
        this.chatId = chatId;
        sessions.add(session);
        
        LOGGER.info("Chat endpoint opened");
    }
    
    @OnClose
    public void onClose(Session session) {
        for (Session s : sessions) {
            
            if (s.getId().equals(session.getId())) {
                synchronized (sessions) {
                    sessions.remove(session);
                }
            }
        }
        
        LOGGER.info("Chat endpoint closed");
    }
    
    @OnMessage
    public void onMessage(Message message,
                          Session session) throws IOException, EncodeException {
        LOGGER.info("Message received: " + message);
        
        ChatService cs = new ChatService();
        message = cs.pushMessage(message, chatId);
        cs.returnConnection();
        
        broadcast(message);
    }
    
    @OnError
    public void onError(Throwable t) {
        LOGGER.error("Chat endpoint encountered an error", t);
    }
    
    private static void broadcast(Message message) throws IOException, EncodeException {
        for (Session s : sessions) {
            s.getBasicRemote().sendObject(message);
        }
    }
}
