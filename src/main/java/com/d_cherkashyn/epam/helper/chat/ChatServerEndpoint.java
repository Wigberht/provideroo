package com.d_cherkashyn.epam.helper.chat;

import com.d_cherkashyn.epam.helper.service.ChatService;
import com.d_cherkashyn.epam.model.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * Endpoint for chat sockets
 */
@ServerEndpoint(value = "/socket/chat/{chatId}",
    encoders = {MessageEncoder.class},
    decoders = {MessageDecoder.class}
)
public class ChatServerEndpoint {
    
    private static Logger LOGGER = LoggerFactory.getLogger(ChatServerEndpoint.class);
    
    private static final List<Session> sessions = new ArrayList<>();
    private long chatId;
    
    /**
     * Handler for OPEN event
     *
     * @param chatId
     * @param session
     * @throws IOException
     * @throws EncodeException
     */
    @OnOpen
    public void onOpen(@PathParam("chatId") long chatId,
                       Session session) throws IOException, EncodeException {
        this.chatId = chatId;
        
        sessions.add(session);
        
        LOGGER.info("Chat endpoint opened");
    }
    
    /**
     * Handler for CLOSE event
     *
     * @param session
     */
    @OnClose
    public void onClose(Session session) {
        LOGGER.info("Chat endpoint closed");
    }
    
    /**
     * Handler for MessageReceived event
     *
     * @param message
     * @param session
     * @throws IOException
     * @throws EncodeException
     */
    @OnMessage
    public void onMessage(Message message,
                          Session session) throws IOException, EncodeException {
        ChatService cs = new ChatService();
        message.setChatId(chatId);
        message = cs.pushMessage(message);
        cs.returnConnection();
        
        broadcast(message);
    }
    
    @OnError
    public void onError(Throwable t) {
        LOGGER.error("Chat endpoint encountered an error", t);
    }
    
    /**
     * Broadcasts the message to all chat users
     *
     * @param message
     * @throws IOException
     * @throws EncodeException
     */
    private static void broadcast(Message message) throws IOException, EncodeException {
        for (Session s : sessions) {
            if (s.isOpen()) {
                s.getBasicRemote().sendObject(message);
            }
        }
    }
}
