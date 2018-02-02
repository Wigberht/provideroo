package com.d_cherkashyn.epam.rest.endpoint;

import com.d_cherkashyn.epam.helper.service.*;
import com.d_cherkashyn.epam.helper.validator.MainValidator;
import com.d_cherkashyn.epam.model.*;
import com.d_cherkashyn.epam.rest.JSONService;
import com.d_cherkashyn.epam.rest.response.SimpleResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServlet;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/user")
public class UserAPI extends HttpServlet {
    
    Logger LOGGER = LoggerFactory.getLogger(UserAPI.class);
    
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
        
        
        long userId = JSONService.get(data, "userId").asLong();
        long tariffId = JSONService.get(data, "tariffId").asLong();
        LOGGER.info("Subscribe: userId: {}, tariffId: {}", userId, tariffId);
        
        
        Subscriber subscriber = SubscriberService.findSubscriberByUserId(userId);
        Subscription activeSubscription = SubscriptionService
            .findSubscription(tariffId, subscriber.getId());
        LOGGER.info("I am here now");
        
        /*if there is such subscription - prolong it, create new otherwise*/
        // TODO: Refactor probably, or no
        if (activeSubscription != null) {
            SubscriptionService.setSubscriptionProlong(activeSubscription, true);
            double debt = SubscriberService.calculateDebt(subscriber.getId());
            LOGGER.info("Debt: " + debt);
            if (debt > subscriber.getAccount().getBalance()) {
                SubscriptionService.setSubscriptionProlong(activeSubscription, false);
                success = false;
            } else {
                AccountService.withdrawMoney(subscriber.getAccount(), debt);
                success = SubscriptionService.prolongSubscriptions(subscriber.getId());
            }
        } else {
            success = SubscriptionService.createSubscription(userId, tariffId);
        }
        
        String response = JSONService.toJSON(new SimpleResponse(success));
        
        return Response.status(200).entity(response).build();
    }
    
    @POST
    @Path("/unsubscribe")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response unsubscribe(String data) {
        boolean success;
        
        long userId = JSONService.get(data, "userId").asLong();
        long tariffId = JSONService.get(data, "tariffId").asLong();
        
        Subscriber subscriber = SubscriberService.findSubscriberByUserId(userId);
        Subscription subscription = SubscriptionService
            .findSubscription(tariffId, subscriber.getId());
        
        success = SubscriptionService.setSubscriptionProlong(subscription, false);
        
        String response = JSONService.toJSON(new SimpleResponse(success));
        
        return Response.status(200).entity(response).build();
    }
    
    
    @POST
    @Path("/ban")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response ban(String data) {
        long userId = JSONService.get(data, "userId").asLong();
        boolean banned = JSONService.get(data, "banned").asBoolean();
        
        boolean success = UserService.setBanned(userId, banned);
        
        String response = JSONService.toJSON(new SimpleResponse(success));
        
        return Response.status(success ? 200 : 400)
                       .entity(response)
                       .build();
    }
    
    @POST
    @Path("/profile/update")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateProfile(String data) {
        boolean success;
        
        long userId = JSONService.get(data, "userId").asLong();
        String firstName = JSONService.get(data, "firstName").asText();
        String lastName = JSONService.get(data, "lastName").asText();
        String login = JSONService.get(data, "login").asText();
        
        if (!MainValidator.simpleText(firstName)
            || !MainValidator.simpleText(lastName)
            || !MainValidator.login(login)) {
            success = false;
        } else {
            
            Subscriber subscriber = SubscriberService.findSubscriberByUserId(userId);
            subscriber.setFirstName(firstName);
            subscriber.setLastName(lastName);
            subscriber.getUser().setLogin(login);
            
            success = SubscriberService.updateSubscriberProfile(subscriber);
        }
        
        String response = JSONService.toJSON(new SimpleResponse(success));
        
        return Response.status(200).entity(response).build();
    }
    
    @POST
    @Path("/balance/replenish")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response replenishBalance(String data) {
        
        
        long userId = JSONService.get(data, "userId").asLong();
        double amount = JSONService.get(data, "amount").asDouble();
        
        LOGGER.info("Amount: " + amount);
        boolean success;
        if (!MainValidator.positiveNumber(String.valueOf(amount))) {
            LOGGER.error("invalid amount");
            success = false;
        } else {
            Subscriber subscriber = SubscriberService.findSubscriberByUserId(userId);
            
            Account account = subscriber.getAccount();
            account.setBalance(account.getBalance() + amount);
            SubscriberService.updateAccount(account);
            
            success = SubscriberService.refreshSubscriptions(subscriber);
        }
        
        String response = JSONService.toJSON(new SimpleResponse(success));
        
        return Response.status(200).entity(response).build();
    }
    
    @GET
    @Path("/all")
    public Response all() {
        List<User> userList = UserService.getAllUsers();
        userList = userList.stream().map((user) -> {
            user.setPassword(null);
            return user;
        }).collect(Collectors.toList());
        
        String response = JSONService.toJSON(userList);
        return Response.status(200).entity(response).build();
    }
    
    @GET
    @Path("/chats")
    public Response getChats(@QueryParam("userId") long userId) {
        LOGGER.info("user id received: " + userId);
        
        List<Chat> chats = ChatService.getUserChats(userId);
        
        String response = JSONService.toJSON(chats);
        return Response.status(200).entity(response).build();
    }
    
}