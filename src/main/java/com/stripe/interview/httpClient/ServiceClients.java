package com.stripe.interview.httpClient;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stripe.interview.model.Post;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ServiceClients {
    
    // Service-specific clients - RECOMMENDED APPROACH
    public static class JsonTestApiClient {
        private final HttpClientV5 client;
        
        public JsonTestApiClient(String apiKey) {
            this.client = HttpClientV5.builder()
                    .basicAuth("", apiKey)
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "application/json")
                .build();
        }

        public String getJson() throws IOException {
            return client.get("https://jsonplaceholder.typicode.com/posts/20");
        }
        public String createJson(String postBody) throws IOException {
            return client.post("https://jsonplaceholder.typicode.com/posts", postBody);
        }
    }
    
//    public static class GitHubApiClient {
//        private final HttpClientV3 client;
//
//        public GitHubApiClient(String token) {
//            this.client = HttpClientV3.create()
//                .withHeader("Authorization", "token " + token)
//                .withHeader("Accept", "application/vnd.github.v3+json");
//        }
//
//        public String getUser(String username) throws IOException {
//            return client.get("https://api.github.com/users/" + username);
//        }
//    }
    
    public static void main(String[] args) {
        // Each service gets its own configured client
        String postBody = "{\"title\":\"Java Test\",\"body\":\"TBD\",\"userId\":123}";
        JsonTestApiClient jsonTest = new JsonTestApiClient("sk_test_...");
        final ObjectMapper mapper = new ObjectMapper();

        
        try {
            String postResponse = jsonTest.createJson(postBody);
            System.out.println("JsonTest POST Response: " + postResponse);

            String getResponse = jsonTest.createJson(postBody);
            System.out.println("JsonTest GET Response: " + getResponse);

            Map<String, String> json = mapper.readValue(getResponse, new TypeReference<Map<String, String>>() {});

            System.out.println(json.get("body"));

            Post postV1 = mapper.readValue(getResponse, new TypeReference<Post>() {});
            System.out.println(postV1);

            Post postV2 = mapper.readValue(getResponse, Post.class);
            System.out.println(postV2);

            postV1.writePostsToCsv(postV1, "src/data/output/postV1.csv");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}