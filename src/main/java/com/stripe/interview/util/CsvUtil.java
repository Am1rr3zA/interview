package com.stripe.interview.util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Utility class for reading CSV files using Apache Commons CSV.
 */
public final class CsvUtil {
    
    private CsvUtil() {
        // Prevent instantiation
    }
    
    /**
     * Reads CSV file and returns list of records as maps (header -> value).
     */
    public static List<Map<String, String>> readCsvWithHeaders(String filePath) throws IOException {
        try (Reader reader = new FileReader(filePath);
             CSVParser parser = CSVFormat.Builder.create().setHeader().setSkipHeaderRecord(true).build().parse(reader)) {
            
            List<Map<String, String>> records = new ArrayList<>();
            for (CSVRecord record : parser) {
                records.add(record.toMap());
            }
            return records;
        }
    }
    
    /**
     * Reads CSV file and returns list of records as string arrays.
     */
    public static List<String[]> readCsv(String filePath) throws IOException {
        try (Reader reader = new FileReader(filePath);
             CSVParser parser = CSVFormat.DEFAULT.parse(reader)) {
            
            List<String[]> records = new ArrayList<>();
            for (CSVRecord record : parser) {
                String[] values = new String[record.size()];
                for (int i = 0; i < record.size(); i++) {
                    values[i] = record.get(i);
                }
                records.add(values);
            }
            return records;
        }
    }
}