package com.stripe.interview.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Utility class for reading JSON files using Jackson.
 */
public final class JsonUtil {
    
    private static final ObjectMapper mapper = new ObjectMapper();
    
    private JsonUtil() {
        // Prevent instantiation
    }
    
    /**
     * Reads JSON file and returns as Map.
     */
    public static Map<String, Object> readJsonAsMap(String filePath) throws IOException {
        return mapper.readValue(new File(filePath), new TypeReference<Map<String, Object>>() {});
    }
    
    /**
     * Reads JSON array file and returns as List of Maps.
     */
    public static List<Map<String, Object>> readJsonArrayAsList(String filePath) throws IOException {
        return mapper.readValue(new File(filePath), new TypeReference<List<Map<String, Object>>>() {});
    }
    
    /**
     * Reads JSON file and converts to specified class.
     */
    public static <T> T readJson(String filePath, Class<T> clazz) throws IOException {
        return mapper.readValue(new File(filePath), clazz);
    }
}