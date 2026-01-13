package com.stripe.interview.util;

import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.ToNumberPolicy;
import com.google.gson.reflect.TypeToken;
import com.stripe.interview.model.Employee;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Utility class for reading JSON files and converting to Employee POJOs using Guava and Gson.
 */
public final class EmployeeGuavaJsonReader {
    
    private static final Gson gson = new GsonBuilder()
            .setObjectToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE)
            .create();
    
    private EmployeeGuavaJsonReader() {
        // Prevent instantiation
    }
    
    /**
     * Reads JSON array file and returns list of Employee objects.
     */
    public static List<Employee> readEmployees(String filePath) throws IOException {
        String content = Files.toString(new File(filePath), StandardCharsets.UTF_8);
        Type type = new TypeToken<List<Employee>>(){}.getType();
        return gson.fromJson(content, type);
    }
    
    /**
     * Reads single Employee from JSON file.
     */
    public static Employee readEmployee(String filePath) throws IOException {
        String content = Files.toString(new File(filePath), StandardCharsets.UTF_8);
        return gson.fromJson(content, Employee.class);
    }
}