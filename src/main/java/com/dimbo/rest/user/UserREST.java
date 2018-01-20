package com.dimbo.rest.user;

import com.dimbo.helper.service.SubscriberService;
import com.dimbo.helper.service.SubscriptionService;
import com.dimbo.helper.service.UserService;
import com.dimbo.model.Account;
import com.dimbo.model.Subscriber;
import com.dimbo.model.Subscription;
import com.dimbo.rest.JSONService;
import com.dimbo.rest.response.SimpleResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
    @Path("/subscribe")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response subscribe(String data) {
        boolean success;
        JSONService jsonService = new JSONService();
        
        long userId = jsonService.get(data, "userId").asLong();
        long tariffId = jsonService.get(data, "tariffId").asLong();
        
        SubscriptionService subscriptionService = new SubscriptionService();
        SubscriberService subscriberService = new SubscriberService();
        
        Subscriber subscriber = subscriberService
            .findSubscriberByUserId(userId);
        Subscription activeSubscription = subscriptionService
            .findSubscription(tariffId, subscriber.getId());
        
        /*if there is such subscription - prolong it, create new otherwise*/
        if (activeSubscription != null) {
            success = subscriptionService
                .setSubscriptionProlong(activeSubscription, true);
        } else {
            success = subscriptionService.createSubscription(userId, tariffId);
        }
        
        subscriptionService.returnConnection();
        subscriberService.returnConnection();
        
        String response = jsonService.toJSON(new SimpleResponse(success));
        
        return Response.status(200).entity(response).build();
    }
    
    @POST
    @Path("/unsubscribe")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response unsubscribe(String data) {
        boolean success;
        JSONService jsonService = new JSONService();
        
        long userId = jsonService.get(data, "userId").asLong();
        long tariffId = jsonService.get(data, "tariffId").asLong();
        
        SubscriptionService subscriptionService = new SubscriptionService();
        SubscriberService subscriberService = new SubscriberService();
        
        Subscriber subscriber = subscriberService
            .findSubscriberByUserId(userId);
        
        Subscription subscription = subscriptionService
            .findSubscription(tariffId, subscriber.getId());
        
        success = subscriptionService
            .setSubscriptionProlong(subscription, false);
        
        subscriptionService.returnConnection();
        subscriberService.returnConnection();
        
        String response = jsonService.toJSON(new SimpleResponse(success));
        
        return Response.status(200).entity(response).build();
    }
    
    
    @POST
    @Path("/ban")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response ban(String data) {
        JSONService jsonService = new JSONService();
        
        long userId = jsonService.get(data, "userId").asLong();
        boolean banned = jsonService.get(data, "banned").asBoolean();
        
        UserService userService = new UserService();
        boolean success = userService.setBanned(userId, banned);
        userService.returnConnection();
        
        String response = jsonService.toJSON(new SimpleResponse(success));
        
        return Response.status(success ? 200 : 400)
                       .entity(response)
                       .build();
    }
    
    @POST
    @Path("/profile/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProfile(String data) {
        JSONService jsonService = new JSONService();
        SubscriberService subscriberService = new SubscriberService();
        
        long userId = jsonService.get(data, "userId").asLong();
        String firstName = jsonService.get(data, "firstName").asText();
        String lastName = jsonService.get(data, "lastName").asText();
        String login = jsonService.get(data, "login").asText();
        
        Subscriber subscriber = subscriberService.findSubscriberByUserId(userId);
        subscriber.setFirstName(firstName);
        subscriber.setLastName(lastName);
        subscriber.getUser().setLogin(login);
        
        boolean success = subscriberService.updateSubscriberProfile(subscriber);
        subscriberService.returnConnection();
        
        String response = jsonService.toJSON(new SimpleResponse(success));
        
        return Response.status(200).entity(response).build();
    }
    
    @POST
    @Path("/balance/replenish")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response replenishBalance(String data) {
        JSONService jsonService = new JSONService();
        
        SubscriberService subscriberService = new SubscriberService();
        
        LOGGER.info("amount: " + jsonService.get(data, "amount"));
        long userId = jsonService.get(data, "userId").asLong();
        double amount = jsonService.get(data, "amount").asDouble();
        LOGGER.info("double amount " + amount);
        amount = amount >= 0 ? amount : 0;
        
        boolean success;
        if (amount == 0) {
            success = false;
        } else {
            LOGGER.info("Updatin");
            Account account = subscriberService.findSubscriberByUserId(userId)
                                               .getAccount();
            account.setBalance(account.getBalance() + amount);
            LOGGER.info("account balance " + account.getBalance());
            success = subscriberService.updateAccount(account);
        }
        
        subscriberService.returnConnection();
        
        String response = jsonService.toJSON(new SimpleResponse(success));
        
        return Response.status(200).entity(response).build();
    }
    
    
}
