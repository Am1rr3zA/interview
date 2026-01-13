package com.stripe.interview.stringUtil;

public class ManualParseExample {
    public static void main(String[] args) {

        String data = "ffName:John;Age:25;City:NYC";

        // Using indexOf and substring
        int nameStart = data.indexOf("Name:") + 5;
        int nameEnd = data.indexOf(";", nameStart);
        String name = data.substring(nameStart, nameEnd);
        System.out.println("Name: " + name); // John

        // Extract between two characters
        String text = "Hello [World] Java";
        int start = text.indexOf("[") + 1;
        int end = text.indexOf("]");
        String extracted = text.substring(start, end);
        System.out.println("Extracted: " + extracted); // World

        // Using charAt for character-by-character parsing
        String number = "12345";
        int sum = 0;
        for (int i = 0; i < number.length(); i++) {
            sum += Character.getNumericValue(number.charAt(i));
        }
        System.out.println("Sum of digits: " + sum); // 15
    }
}