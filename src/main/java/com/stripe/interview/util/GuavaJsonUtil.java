package com.stripe.interview.util;

import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.ToNumberPolicy;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * Utility class for reading JSON files using Guava and Gson.
 */
public final class GuavaJsonUtil {

//    private static final Gson gson = new Gson();

    private static final Gson gson = new GsonBuilder()
            .setObjectToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE)
            .create();
    
    private GuavaJsonUtil() {
        // Prevent instantiation
    }
    
    /**
     * Reads JSON file and returns as Map.
     */
    public static Map<String, Object> readJsonAsMap(String filePath) throws IOException {
        String content = Files.toString(new File(filePath), StandardCharsets.UTF_8);
        Type type = new TypeToken<Map<String, Object>>(){}.getType();
        return gson.fromJson(content, type);
    }
    
    /**
     * Reads JSON array file and returns as List of Maps.
     */
    public static List<Map<String, Object>> readJsonArrayAsList(String filePath) throws IOException {
        String content = Files.toString(new File(filePath), StandardCharsets.UTF_8);
        Type type = new TypeToken<List<Map<String, Object>>>(){}.getType();
        return gson.fromJson(content, type);
    }
    
    /**
     * Reads JSON file and converts to specified class.
     */
    public static <T> T readJson(String filePath, Class<T> clazz) throws IOException {
        String content = Files.toString(new File(filePath), StandardCharsets.UTF_8);
        return gson.fromJson(content, clazz);
    }
}