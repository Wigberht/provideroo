package com.dimbo.rest;

import com.dimbo.rest.response.SimpleResponse;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class JSONService {
    Logger LOGGER = LoggerFactory.getLogger(JSONService.class);
    
    private ObjectMapper objectMapper;
    
    public JSONService() {
        objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
    }
    
    public JsonNode get(String json, String key) {
        try {
            return objectMapper.readTree(json).get(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    public String toJSON(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException jpe) {
            LOGGER.error("Cannot transform object to json", jpe);
        }
        
        return null;
    }
}
