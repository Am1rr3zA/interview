package com.stripe.interview.stringUtil;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.*;

public class ArrayPatternParser {

    public static void main(String[] args) {

        // ══════════════════════════════════════════════════════════
        // Example 1: Simple array string [a, b, c]
        // ══════════════════════════════════════════════════════════
        String arrayStr1 = "[apple, banana, cherry]";
        List<String> list1 = parseSimpleArray(arrayStr1);
        System.out.println("Parsed: " + list1);
        // Output: [apple, banana, cherry]


        // ══════════════════════════════════════════════════════════
        // Example 2: Numeric array [1, 2, 3, 4, 5]
        // ══════════════════════════════════════════════════════════
        String arrayStr2 = "[1, 2, 3, 4, 5]";
        List<Integer> list2 = parseIntArray(arrayStr2);
        System.out.println("Parsed integers: " + list2);
        // Output: [1, 2, 3, 4, 5]


        // ══════════════════════════════════════════════════════════
        // Example 3: Nested array pattern [[a,b], [c,d]]
        // ══════════════════════════════════════════════════════════
        String arrayStr3 = "[[1,2], [3,4], [5,6]]";
        List<List<Integer>> list3 = parseNestedArray(arrayStr3);
        System.out.println("Parsed nested: " + list3);
        // Output: [[1, 2], [3, 4], [5, 6]]


        // ══════════════════════════════════════════════════════════
        // Example 4: Mixed types array
        // ══════════════════════════════════════════════════════════
        String arrayStr4 = "[John, 25, true, 3.14]";
        List<Object> list4 = parseMixedArray(arrayStr4);
        System.out.println("Parsed mixed: " + list4);
    }

    // Parse simple string array
    public static List<String> parseSimpleArray(String input) {
        // Remove brackets and split
        String content = input.trim()
                .replaceAll("^\\[|\\]$", "");  // Remove [ and ]

        if (content.isEmpty()) {
            return new ArrayList<>();
        }

        return Arrays.stream(content.split(","))
                .map(String::trim)
                .collect(Collectors.toList());
    }

    // Parse integer array
    public static List<Integer> parseIntArray(String input) {
        String content = input.trim()
                .replaceAll("^\\[|\\]$", "");

        if (content.isEmpty()) {
            return new ArrayList<>();
        }

        return Arrays.stream(content.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    // Parse nested array [[1,2], [3,4]]
    public static List<List<Integer>> parseNestedArray(String input) {
        List<List<Integer>> result = new ArrayList<>();

        // Remove outer brackets
        String content = input.trim().substring(1, input.length() - 1);

        // Find inner arrays using regex
        Pattern pattern = Pattern.compile("\\[(.*?)\\]");
        Matcher matcher = pattern.matcher(content);

        while (matcher.find()) {
            String innerContent = matcher.group(1);
            List<Integer> innerList = Arrays.stream(innerContent.split(","))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            result.add(innerList);
        }

        return result;
    }

    // Parse mixed type array
    public static List<Object> parseMixedArray(String input) {
        String content = input.trim().replaceAll("^\\[|\\]$", "");

        return Arrays.stream(content.split(","))
                .map(String::trim)
                .map(ArrayPatternParser::parseValue)
                .collect(Collectors.toList());
    }

    // Detect and parse value type
    private static Object parseValue(String value) {
        // Try boolean
        if (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("false")) {
            return Boolean.parseBoolean(value);
        }

        // Try integer
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {}

        // Try double
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {}

        // Return as string
        return value;
    }
}