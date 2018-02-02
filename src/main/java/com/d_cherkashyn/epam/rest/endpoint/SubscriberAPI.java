package com.d_cherkashyn.epam.rest.endpoint;

import com.d_cherkashyn.epam.helper.service.SubscriberService;
import com.d_cherkashyn.epam.helper.validator.MainValidator;
import com.d_cherkashyn.epam.model.Subscriber;
import com.d_cherkashyn.epam.rest.JSONService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/subscriber")
public class SubscriberAPI extends HttpServlet {
    
    Logger LOGGER = LoggerFactory.getLogger(SubscriberAPI.class);
    
    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public String test() {
        return "test";
    }
    
    
    @GET
    @Path("/search")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response search(@QueryParam("query") String query) {
        String response;
        if (MainValidator.searchText(query)) {
            List<Subscriber> subscribers = SubscriberService.search(query);
            response = JSONService.toJSON(subscribers);
        } else {
            response = "";
        }
        return Response.status(200).entity(response).build();
    }
}