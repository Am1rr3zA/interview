package com.stripe.interview.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.*;

public class GenericExporter {

    // ---------------- JSON ----------------
    public static <T> void writeJson(T object, String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), object);
    }

    public static <T> void writeCsvFlatten(T object, String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.convertValue(object, Map.class);

        Map<String, String> flatMap = new LinkedHashMap<>();
        flattenMap("", map, flatMap);

        try (FileWriter writer = new FileWriter(filePath)) {
            // Write header
            writer.append(String.join(",", flatMap.keySet()));
            writer.append("\n");

            // Write values
            writer.append(String.join(",", flatMap.values()));
            writer.append("\n");
        }
    }

    private static void flattenMap(String prefix, Map<String, Object> map, Map<String, String> flatMap) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = prefix.isEmpty() ? entry.getKey() : prefix + "." + entry.getKey();
            Object value = entry.getValue();

            if (value instanceof Map) {
                flattenMap(key, (Map<String, Object>) value, flatMap);
            } else if (value instanceof List) {
                List<?> list = (List<?>) value;
                for (int i = 0; i < list.size(); i++) {
                    Object item = list.get(i);
                    if (item instanceof Map) {
                        flattenMap(key + "[" + i + "]", (Map<String, Object>) item, flatMap);
                    } else {
                        flatMap.put(key + "[" + i + "]", item != null ? item.toString() : "");
                    }
                }
            } else {
                flatMap.put(key, value != null ? value.toString() : "");
            }
        }
    }

    // ---------------- CSV ----------------
    public static <T> void writeCsv(T object, String filePath) throws IOException, IllegalAccessException {
        try (FileWriter writer = new FileWriter(filePath)) {
            Class<?> clazz = object.getClass();
            Field[] fields = clazz.getDeclaredFields();

            // Write CSV header
            for (int i = 0; i < fields.length; i++) {
                fields[i].setAccessible(true);
                writer.append(fields[i].getName());
                if (i < fields.length - 1) writer.append(",");
            }
            writer.append("\n");

            // Write CSV values
            for (int i = 0; i < fields.length; i++) {
                Object value = fields[i].get(object);

                // Special handling if the field is a Map (like conversion_rates)
                if (value instanceof Map) {
                    Map<?, ?> map = (Map<?, ?>) value;
                    for (Map.Entry<?, ?> entry : map.entrySet()) {
                        writer.append(fields[i].getName())
                                .append("_key=").append(entry.getKey().toString())
                                .append("_value=").append(entry.getValue().toString())
                                .append("\n");
                    }
                } else {
                    writer.append(value != null ? value.toString() : "");
                    if (i < fields.length - 1) writer.append(",");
                }
            }
            writer.append("\n");
        }
    }
}