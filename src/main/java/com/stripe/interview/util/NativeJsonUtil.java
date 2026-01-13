package com.stripe.interview.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility class for reading JSON files using only Java standard library.
 * Note: This is a basic parser for simple JSON structures.
 */
public final class NativeJsonUtil {
    
    private NativeJsonUtil() {
        // Prevent instantiation
    }
    
    /**
     * Reads JSON file and returns as Map (basic parsing).
     */
    public static Map<String, Object> readJsonAsMap(String filePath) throws IOException {
        String content = readFileContent(filePath);
        return parseJsonObject(content.trim());
    }
    
    /**
     * Reads JSON array file and returns as List of Maps (basic parsing).
     */
    public static List<Map<String, Object>> readJsonArrayAsList(String filePath) throws IOException {
        String content = readFileContent(filePath);
        return parseJsonArray(content.trim());
    }
    
    private static String readFileContent(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
            return content.toString();
        }
    }
    
    @SuppressWarnings("unchecked")
    private static Map<String, Object> parseJsonObject(String json) {
        Map<String, Object> map = new HashMap<>();
        json = json.trim();
        if (!json.startsWith("{") || !json.endsWith("}")) return map;
        
        json = json.substring(1, json.length() - 1).trim();
        if (json.isEmpty()) return map;
        
        String[] pairs = json.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        for (String pair : pairs) {
            String[] keyValue = pair.split(":", 2);
            if (keyValue.length == 2) {
                String key = keyValue[0].trim().replaceAll("^\"|\"$", "");
                String value = keyValue[1].trim();
                map.put(key, parseValue(value));
            }
        }
        return map;
    }
    
    @SuppressWarnings("unchecked")
    private static List<Map<String, Object>> parseJsonArray(String json) {
        List<Map<String, Object>> list = new ArrayList<>();
        json = json.trim();
        if (!json.startsWith("[") || !json.endsWith("]")) return list;
        
        json = json.substring(1, json.length() - 1).trim();
        if (json.isEmpty()) return list;
        
        String[] objects = json.split("},\\s*\\{");
        for (int i = 0; i < objects.length; i++) {
            String obj = objects[i];
            if (i == 0 && obj.startsWith("{") && !obj.endsWith("}")) obj = obj + "}";
            if (i == objects.length - 1 && !obj.startsWith("{") && obj.endsWith("}")) obj =  "{" + obj ;
            if (i > 0 && i < objects.length - 1) obj = "{" + obj + "}";
            
            list.add(parseJsonObject(obj));
        }
        return list;
    }
    
    private static Object parseValue(String value) {
        value = value.trim();
        if (value.startsWith("\"") && value.endsWith("\"")) {
            return value.substring(1, value.length() - 1);
        }
        if ("true".equals(value)) return true;
        if ("false".equals(value)) return false;
        if ("null".equals(value)) return null;
        
        try {
            if (value.contains(".")) return Double.parseDouble(value);
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return value;
        }
    }
}