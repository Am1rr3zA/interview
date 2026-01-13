package com.stripe.interview.httpClient;

import java.io.IOException;

public class HttpExample {
    public static void main(String[] args) {
        HttpClient client = new HttpClient()
            .withHeader("Content-Type", "application/json")
            .withJwtAuth("your-jwt-token-here");

        try {
            // GET request
            String getResponse = client.get("https://jsonplaceholder.typicode.com/posts/1");
            System.out.println("GET Response: " + getResponse);

            // POST request
            String postBody = "{\"title\":\"foo\",\"body\":\"bar\",\"userId\":1}";
            String postResponse = client.post("https://jsonplaceholder.typicode.com/posts", postBody);
            System.out.println("POST Response: " + postResponse);

            // PUT request
            String putBody = "{\"id\":1,\"title\":\"updated\",\"body\":\"updated\",\"userId\":1}";
            String putResponse = client.put("https://jsonplaceholder.typicode.com/posts/1", putBody);
            System.out.println("PUT Response: " + putResponse);

            // DELETE request
            String deleteResponse = client.delete("https://jsonplaceholder.typicode.com/posts/1");
            System.out.println("DELETE Response: " + deleteResponse);

            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}