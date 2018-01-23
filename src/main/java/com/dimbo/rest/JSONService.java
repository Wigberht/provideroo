package com.dimbo.rest;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class JSONService {
    private static Logger LOGGER = LoggerFactory.getLogger(JSONService.class);
    
    private static ObjectMapper objectMapper = new ObjectMapper();
    
    static {
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    }
    
    public JSONService() {
    }
    
    public static JsonNode get(String json, String key) {
        try {
            return objectMapper.readTree(json).get(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public static Object toObject(String json, Class<?> clasz) {
        try {
            return objectMapper.readValue(json, clasz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static String toJSON(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException jpe) {
            LOGGER.error("Cannot transform object to json", jpe);
        }
        
        return null;
    }
}
