package com.stripe.interview.util;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utility class for reading CSV files using OpenCSV.
 */
public final class OpenCsvUtil {
    
    private OpenCsvUtil() {
        // Prevent instantiation
    }
    
    /**
     * Reads CSV file and returns list of records as maps (header -> value).
     */
    public static List<Map<String, String>> readCsvWithHeaders(String filePath) throws IOException, CsvException {
        try (FileReader fileReader = new FileReader(filePath);
             CSVReader csvReader = new CSVReaderBuilder(fileReader).withSkipLines(0).build()) {
            
            List<String[]> allRows = csvReader.readAll();
            if (allRows.isEmpty()) return new ArrayList<>();
            
            String[] headers = allRows.get(0);
            List<Map<String, String>> records = new ArrayList<>();
            
            for (int i = 1; i < allRows.size(); i++) {
                String[] row = allRows.get(i);
                Map<String, String> record = new HashMap<>();
                for (int j = 0; j < headers.length && j < row.length; j++) {
                    record.put(headers[j], row[j]);
                }
                records.add(record);
            }
            return records;
        }
    }
    
    /**
     * Reads CSV file and returns list of records as string arrays.
     */
    public static List<String[]> readCsv(String filePath) throws IOException, CsvException {
        try (FileReader fileReader = new FileReader(filePath);
             CSVReader csvReader = new CSVReaderBuilder(fileReader).build()) {
            
            return csvReader.readAll();
        }
    }
}