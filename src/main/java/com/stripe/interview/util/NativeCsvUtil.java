package com.stripe.interview.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility class for reading CSV files using only Java standard library.
 */
public final class NativeCsvUtil {
    
    private NativeCsvUtil() {
        // Prevent instantiation
    }
    
    /**
     * Reads CSV file and returns list of records as maps (header -> value).
     */
    public static List<Map<String, String>> readCsvWithHeaders(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            List<Map<String, String>> records = new ArrayList<>();
            String headerLine = reader.readLine();
            if (headerLine == null) return records;
            
            String[] headers = parseCsvLine(headerLine);
            String line;
            
            while ((line = reader.readLine()) != null) {
                String[] values = parseCsvLine(line);
                Map<String, String> record = new HashMap<>();
                for (int i = 0; i < headers.length && i < values.length; i++) {
                    record.put(headers[i], values[i]);
                }
                records.add(record);
            }
            return records;
        }
    }
    
    /**
     * Reads CSV file and returns list of records as string arrays.
     */
    public static List<String[]> readCsv(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            List<String[]> records = new ArrayList<>();
            String line;
            
            while ((line = reader.readLine()) != null) {
                records.add(parseCsvLine(line));
            }
            return records;
        }
    }
    
    private static String[] parseCsvLine(String line) {
        List<String> fields = new ArrayList<>();
        StringBuilder field = new StringBuilder();
        boolean inQuotes = false;
        
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ',' && !inQuotes) {
                fields.add(field.toString());
                field.setLength(0);
            } else {
                field.append(c);
            }
        }
        fields.add(field.toString());
        
        return fields.toArray(new String[0]);
    }
}