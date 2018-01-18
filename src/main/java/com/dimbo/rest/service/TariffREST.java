package com.dimbo.rest.service;

import com.dimbo.helper.service.TariffService;
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
        
        TariffService tariffService = new TariffService();
        ObjectMapper objectMapper = new ObjectMapper();
//        Tariff tariff = null;
//        try {
//            tariff = objectMapper.readValue(data, Tariff.class);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        
        Object tariffObj = jsonService.toObject(data, Tariff.class);

//        Tariff tariff1 = (Tariff) tariffObj;
//        LOGGER.info("Obj is null: " + (tariffObj == null));
//        LOGGER.info("Tariff is null: " + (tariff1 == null));
        Tariff tariff = (Tariff) jsonService.toObject(data, Tariff.class);

//        LOGGER.info("Json: " + data);
//        LOGGER.info("Cost: " + tariff1.getCost());

//        success = true;
        success = tariffService.updateTariff(tariff);
        
        tariffService.returnConnection();
        
        String response = jsonService.toJSON(new SimpleResponse(success));
        
        return Response.status(200).entity(response).build();
    }
}
