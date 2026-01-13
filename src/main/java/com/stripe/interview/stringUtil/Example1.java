package com.stripe.interview.stringUtil;

import java.util.Arrays;

public class Example1 {

    public static void main(String[] args) {

        // ══════════════════════════════════════════════════════════
        // Example 1: Ampersand delimiter (xx&yy&cc)
        // ══════════════════════════════════════════════════════════
        String input1 = "apple&banana&cherry";
        String[] fruits = input1.split("&");

        System.out.println("Input: " + input1);
        for (int i = 0; i < fruits.length; i++) {
            System.out.println("  [" + i + "] = " + fruits[i]);
        }
        // Output:
        // Input: apple&banana&cherry
        //   [0] = apple
        //   [1] = banana
        //   [2] = cherry


        // ══════════════════════════════════════════════════════════
        // Example 2: Comma-separated values
        // ══════════════════════════════════════════════════════════
        String input2 = "John,25,Engineer,New York";
        String[] parts = input2.split(",");

        String name = parts[0];      // John
        int age = Integer.parseInt(parts[1]);  // 25
        String job = parts[2];       // Engineer
        String city = parts[3];      // New York

        System.out.println("Name: " + name + ", Age: " + age);


        // ══════════════════════════════════════════════════════════
        // Example 3: Multiple delimiters (| or & or ,)
        // ══════════════════════════════════════════════════════════
        String input3 = "red|green&blue,yellow";
        String[] colors = input3.split("[|&,]");  // Regex character class

        // Output: [red, green, blue, yellow]
        System.out.println(Arrays.toString(colors));


        // ══════════════════════════════════════════════════════════
        // Example 4: Limit the number of splits
        // ══════════════════════════════════════════════════════════
        String input4 = "one&two&three&four&five";
        String[] limited = input4.split("&", 3);  // Max 3 parts

        // Output: [one, two, three&four&five]
        System.out.println(Arrays.toString(limited));
    }
}