package com.stateinsurance.insurance;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Helper {
    
    public static String parseJsonResponse(String jsonResponse, String keyName) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(jsonResponse);

            // Extract the value associated with the specified key
            JsonNode keyValue = jsonNode.get(keyName);

            // Check if the key exists and return its value as a string
            if (keyValue != null) {
                return keyValue.asText();
            } else {
                return "Key not found";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error parsing JSON";
        }
    }
}
