package com.dimbo.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class Localization {
    private static Logger LOGGER = LoggerFactory.getLogger(Localization.class);
    
    private static Map<String, String> bundleMap = new HashMap<>();
    
    static {
        bundleMap.put("ru", fillTheVoid(new Locale("ru")));
        bundleMap.put("en", fillTheVoid(new Locale("en")));
    }
    
    public static String getBundleString(String language) {
        if (language.contains("_")) {
            language = language.split("_")[0];
        }
        if (bundleMap.containsKey(language)) {
            return bundleMap.get(language);
        } else {
            bundleMap.put(language, fillTheVoid(new Locale(language)));
            return bundleMap.get(language);
        }
    }
    
    private static String fillTheVoid(Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle("messages", locale);
        
        Map<String, String> map = new HashMap<>();
        for (String key : bundle.keySet()) {
            map.put(key, bundle.getString(key));
        }
        
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final ObjectMapper mapper = new ObjectMapper();
        
        try {
            mapper.writeValue(out, map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        final byte[] data = out.toByteArray();
        
        return new String(data);
    }
}
