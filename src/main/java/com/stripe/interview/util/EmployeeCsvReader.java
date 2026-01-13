package com.stripe.interview.util;

import com.stripe.interview.model.Employee;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for reading CSV files and converting to Employee POJOs.
 */
public final class EmployeeCsvReader {
    
    private EmployeeCsvReader() {
        // Prevent instantiation
    }
    
    /**
     * Reads CSV file and returns list of Employee objects.
     */
    public static List<Employee> readEmployees(String filePath) throws IOException {
        try (Reader reader = new FileReader(filePath);
             CSVParser parser = CSVFormat.Builder.create().setHeader().setSkipHeaderRecord(true).build().parse(reader)) {
            
            List<Employee> employees = new ArrayList<>();
            for (CSVRecord record : parser) {
                Employee employee = new Employee();
                employee.setName(record.get("name"));
                employee.setAge(Integer.parseInt(record.get("age")));
                employee.setDepartment(record.get("department"));
                employee.setSalary(Double.parseDouble(record.get("salary")));
                employees.add(employee);
            }
            return employees;
        }
    }
}