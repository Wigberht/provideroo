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
        JSONService jsonService = new JSONService();
        
        
        long userId = jsonService.get(data, "userId").asLong();
        long tariffId = jsonService.get(data, "tariffId").asLong();
        LOGGER.info("Subscribe: userId: {}, tariffId: {}", userId, tariffId);
        
        
        SubscriptionService subscriptionService = new SubscriptionService();
        SubscriberService subscriberService = new SubscriberService();
        AccountService accountService = new AccountService();
        
        Subscriber subscriber = subscriberService
            .findSubscriberByUserId(userId);
        Subscription activeSubscription = subscriptionService
            .findSubscription(tariffId, subscriber.getId());
        LOGGER.info("I am here now");
        
        /*if there is such subscription - prolong it, create new otherwise*/
        // TODO: Refactor probably, or no
        if (activeSubscription != null) {
            subscriptionService.setSubscriptionProlong(activeSubscription, true);
            double debt = subscriberService.calculateDebt(subscriber.getId());
            LOGGER.info("Debt: " + debt);
            if (debt > subscriber.getAccount().getBalance()) {
                subscriptionService.setSubscriptionProlong(activeSubscription, false);
                success = false;
            } else {
                accountService.withdrawMoney(subscriber.getAccount(), debt);
                success = subscriptionService.prolongSubscriptions(subscriber.getId());
            }
        } else {
            success = subscriptionService.createSubscription(userId, tariffId);
        }
        
        
        subscriptionService.returnConnection();
        subscriberService.returnConnection();
        accountService.returnConnection();
        
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
        
        SubscriberService subscriberService = new SubscriberService();
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
            
            Subscriber subscriber = subscriberService.findSubscriberByUserId(userId);
            subscriber.setFirstName(firstName);
            subscriber.setLastName(lastName);
            subscriber.getUser().setLogin(login);
            
            success = subscriberService.updateSubscriberProfile(subscriber);
            subscriberService.returnConnection();
        }
        
        String response = JSONService.toJSON(new SimpleResponse(success));
        
        return Response.status(200).entity(response).build();
    }
    
    @POST
    @Path("/balance/replenish")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response replenishBalance(String data) {
        JSONService jsonService = new JSONService();
        
        SubscriberService subscriberService = new SubscriberService();
        AccountService accountService = new AccountService();
        SubscriptionService subscriptionService = new SubscriptionService();
        
        long userId = jsonService.get(data, "userId").asLong();
        double amount = jsonService.get(data, "amount").asDouble();
        
        LOGGER.info("Amount: " + amount);
        boolean success;
        if (!MainValidator.positiveNumber(String.valueOf(amount))) {
            LOGGER.error("invalid amount");
            success = false;
        } else {
            Subscriber subscriber = subscriberService.findSubscriberByUserId(userId);
            
            Account account = subscriber.getAccount();
            account.setBalance(account.getBalance() + amount);
            subscriberService.updateAccount(account);
            
            success = subscriberService.refreshSubscriptions(subscriber);
        }
        
        subscriberService.returnConnection();
        accountService.returnConnection();
        subscriptionService.returnConnection();
        
        String response = jsonService.toJSON(new SimpleResponse(success));
        
        return Response.status(200).entity(response).build();
    }
    
    @GET
    @Path("/all")
    public Response all() {
        UserService us = new UserService();
        List<User> userList = us.getAllUsers();
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
        
        ChatService cs = new ChatService();
        List<Chat> chats = cs.getUserChats(userId);
        cs.returnConnection();
        
        String response = JSONService.toJSON(chats);
        return Response.status(200).entity(response).build();
    }
    
}