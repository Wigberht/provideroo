package com.dimbo.rest.user;

import com.dimbo.ConnectionPool;
import com.dimbo.dao.factory.FactoryGenerator;
import com.dimbo.rest.response.SimpleResponse;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

@Path("/user")
public class UserREST extends HttpServlet {
    
    Logger LOGGER = LoggerFactory.getLogger(UserREST.class);
    
    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public String test() {
        return "test";
    }
    
    @POST
    @Path("/bane")
    public Response postText(@Context UriInfo uriInfo, String content) {
        MultivaluedMap<String, String> queryParams = uriInfo.getQueryParameters();
        String nameParam = queryParams.getFirst("name");
        for (Map.Entry<String, List<String>> entry : queryParams.entrySet()) {
            LOGGER.info(entry.getKey() + " : " + entry.getValue());
        }
        return Response.status(200)
                       .entity("YOLO")
                       .build();
    }
    
    @POST
    @Path("/ban")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response ban(String data) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        
        long userId = -1;
        boolean banned = false;
        try {
            userId = objectMapper.readTree(data)
                                 .get("userId")
                                 .asLong();
            banned = objectMapper.readTree(data)
                                 .get("banned")
                                 .asBoolean();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Connection connection = ConnectionPool.conn();
        boolean success = FactoryGenerator.getFactory()
                                          .makeUserDAO(connection)
                                          .setBanned(userId, banned);
        ConnectionPool.returnConn(connection);
        
        String responseString = "";
        
        try {
            responseString = objectMapper.writeValueAsString(new SimpleResponse(success));
        } catch (JsonProcessingException jpe) {
            LOGGER.error("json exception");
        }
        
        return Response.status(success ? 200 : 400)
                       .entity(responseString)
                       .build();
    }
    
}
