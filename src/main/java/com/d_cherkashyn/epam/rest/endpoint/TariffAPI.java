package com.d_cherkashyn.epam.rest.endpoint;

import com.d_cherkashyn.epam.helper.service.TariffService;
import com.d_cherkashyn.epam.helper.validator.MainValidator;
import com.d_cherkashyn.epam.model.Tariff;
import com.d_cherkashyn.epam.rest.JSONService;
import com.d_cherkashyn.epam.rest.response.SimpleResponse;
import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/tariff")
public class TariffAPI extends HttpServlet {
    
    Logger LOGGER = LoggerFactory.getLogger(TariffAPI.class);
    
    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    public String test() {
        return "test";
    }
    
    @POST
    @Path("/update")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response update(String data) {
        boolean success;
        
        String title = JSONService.get(data, "title").asText();
        String description = JSONService.get(data, "description").asText();
        int numberOfDays = JSONService.get(data, "numberOfDays").asInt();
        double cost = JSONService.get(data, "cost").asDouble();
        String currency = JSONService.get(data, "currencyShortname").asText();
        
        
        if (!MainValidator.tariffValidator(title, description,
                                           String.valueOf(numberOfDays),
                                           String.valueOf(cost),
                                           currency)) {
            success = false;
        } else {
            Tariff tariff = (Tariff) JSONService.toObject(data, Tariff.class);
            success = TariffService.updateTariff(tariff);
        }
        
        String response = JSONService.toJSON(new SimpleResponse(success));
        
        return Response.status(200).entity(response).build();
    }
    
    @POST
    @Path("/delete")
    public Response delete(String data) {
        boolean success = false;
        
        JsonNode node = JSONService.get(data, "tariffId");
        if (node != null) {
            long id = node.asLong();
            success = TariffService.deleteTariff(id);
        }
        
        String response = JSONService.toJSON(new SimpleResponse(success));
        
        return Response.status(200).entity(response).build();
    }
    
    @GET
    @Path("/search")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response search(@QueryParam("query") String query) {
        String response;
        LOGGER.info("Q received: '" + query + "'");
        LOGGER.info("Validator ok: " + MainValidator.searchText(query));
        if (MainValidator.searchText(query)) {
            LOGGER.info("validator passed");
            List<Tariff> tariffs = TariffService.search(query);
            response = JSONService.toJSON(tariffs);
        } else {
            response = "";
        }
        return Response.status(200).entity(response).build();
    }
}
