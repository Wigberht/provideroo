package com.dimbo.rest.user;

import com.dimbo.ConnectionPool;
import com.dimbo.dao.factory.FactoryGenerator;

import javax.servlet.http.HttpServlet;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Connection;

@Path("/user")
public class UserREST extends HttpServlet {
    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public String test() {
        return "test";
    }
    
    @POST
    @Path("/ban")
    @Produces(MediaType.APPLICATION_JSON)
    public boolean banUser(@FormParam("userId") int userId) {
        Connection connection = ConnectionPool.conn();
//        String response = "success:";
        boolean success = FactoryGenerator.getFactory()
                                          .makeUserDAO(connection)
                                          .ban(userId);
        ConnectionPool.returnConn(connection);
        
        return success;
//        Pair<String, Boolean> resultPair = new Pair<>("success", success);
//        return new Gson().toJson(resultPair);
        
    }
    
}
