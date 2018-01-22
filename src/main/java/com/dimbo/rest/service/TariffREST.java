package com.dimbo.rest.service;

import com.dimbo.helper.service.TariffService;
import com.dimbo.helper.validator.MainValidator;
import com.dimbo.model.Tariff;
import com.dimbo.rest.JSONService;
import com.dimbo.rest.response.SimpleResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("/tariff")
public class TariffREST extends HttpServlet {
    
    Logger LOGGER = LoggerFactory.getLogger(TariffREST.class);
    
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
        JSONService jsonService = new JSONService();
        String title = jsonService.get(data, "title").asText();
        String description = jsonService.get(data, "description").asText();
        int numberOfDays = jsonService.get(data, "numberOfDays").asInt();
        double cost = jsonService.get(data, "cost").asDouble();
        String currency = jsonService.get(data, "currencyShortname").asText();
        
        if (!MainValidator.tariffValidator(title, description,
                                           String.valueOf(numberOfDays),
                                           String.valueOf(cost),
                                           currency)) {
            success = false;
        } else {
            TariffService tariffService = new TariffService();
            
            Tariff tariff = (Tariff) jsonService.toObject(data, Tariff.class);
            success = tariffService.updateTariff(tariff);
            
            tariffService.returnConnection();
        }
        
        String response = jsonService.toJSON(new SimpleResponse(success));
        
        return Response.status(200).entity(response).build();
    }
}
