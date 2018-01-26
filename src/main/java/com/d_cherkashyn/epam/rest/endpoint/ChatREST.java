package com.d_cherkashyn.epam.rest.endpoint;

import com.d_cherkashyn.epam.helper.service.*;
import com.d_cherkashyn.epam.model.*;
import com.d_cherkashyn.epam.rest.JSONService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/chat")
public class ChatREST extends HttpServlet {
    
    Logger LOGGER = LoggerFactory.getLogger(ChatREST.class);
    
    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public String test() {
        return "test";
    }
    
    @GET
    @Path("/get")
    public Response getChat(@QueryParam("chatId") long chatId) {
        LOGGER.info("chat id received: " + chatId);
        
        ChatService cs = new ChatService();
        Chat chat = cs.getChat(chatId);
        cs.returnConnection();
        
        String response = JSONService.toJSON(chat);
        return Response.status(200).entity(response).build();
    }
    
    @POST
    @Path("/create")
    public Response createChat(String data) {
        long creatorId = JSONService.get(data, "creatorId").asLong();
        String creatorLogin = JSONService.get(data, "creatorLogin").asText();
        long receiverId = JSONService.get(data, "receiverId").asLong();
        String receiverLogin = JSONService.get(data, "receiverLogin").asText();
        
        Chat responseChat;
        ChatService cs = new ChatService();
        
        Chat commonChat = cs.findCommonChat(creatorId, receiverId);
        if (commonChat != null) {
            responseChat = commonChat;
        } else {
            responseChat = cs.createChat(creatorId, creatorLogin,
                                         receiverId, receiverLogin);
        }
        
        cs.returnConnection();
        
        if (responseChat != null) {
            return Response.status(200).entity(JSONService.toJSON(responseChat)).build();
        } else {
            return Response.status(400).build();
        }
    }
    
}