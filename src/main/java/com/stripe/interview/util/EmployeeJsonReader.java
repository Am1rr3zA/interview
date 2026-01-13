package com.stripe.interview.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stripe.interview.model.Employee;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Utility class for reading JSON files and converting to Employee POJOs.
 */
public final class EmployeeJsonReader {
    
    private static final ObjectMapper mapper = new ObjectMapper();
    
    private EmployeeJsonReader() {
        // Prevent instantiation
    }
    
    /**
     * Reads JSON array file and returns list of Employee objects.
     */
    public static List<Employee> readEmployees(String filePath) throws IOException {
        return mapper.readValue(new File(filePath), new TypeReference<List<Employee>>() {});
    }
    
    /**
     * Reads single Employee from JSON file.
     */
    public static Employee readEmployee(String filePath) throws IOException {
        return mapper.readValue(new File(filePath), Employee.class);
    }
}