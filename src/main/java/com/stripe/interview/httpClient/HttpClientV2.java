package com.stripe.interview.httpClient;

import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HttpClientV2 {
    private final OkHttpClient client;
    private final Map<String, String> defaultHeaders;
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    public HttpClientV2() {
        this.client = new OkHttpClient();
        this.defaultHeaders = new HashMap<>();
    }

    public HttpClientV2 withHeader(String name, String value) {
        defaultHeaders.put(name, value);
        return this;
    }

    public HttpClientV2 withJwtAuth(String token) {
        return withHeader("Authorization", "Bearer " + token);
    }

    public HttpClientV2 withBasicAuth(String username, String password) {
        String auth = java.util.Base64.getEncoder()
            .encodeToString((username + ":" + password).getBytes());
        return withHeader("Authorization", "Basic " + auth);
    }

    public String get(String url) throws IOException {
        Request request = buildRequest(url).build();
        return execute(request);
    }

    public String post(String url, String body) throws IOException {
        RequestBody requestBody = body != null ? RequestBody.create(body, JSON) : RequestBody.create("", JSON);
        Request request = buildRequest(url).post(requestBody).build();
        return execute(request);
    }

    public String put(String url, String body) throws IOException {
        RequestBody requestBody = body != null ? RequestBody.create(body, JSON) : RequestBody.create("", JSON);
        Request request = buildRequest(url).put(requestBody).build();
        return execute(request);
    }

    public String delete(String url) throws IOException {
        Request request = buildRequest(url).delete().build();
        return execute(request);
    }

    public String patch(String url, String body) throws IOException {
        RequestBody requestBody = body != null ? RequestBody.create(body, JSON) : RequestBody.create("", JSON);
        Request request = buildRequest(url).patch(requestBody).build();
        return execute(request);
    }

    private Request.Builder buildRequest(String url) {
        Request.Builder builder = new Request.Builder().url(url);
        for (Map.Entry<String, String> header : defaultHeaders.entrySet()) {
            builder.addHeader(header.getKey(), header.getValue());
        }
        return builder;
    }

    private String execute(Request request) throws IOException {
        try (Response response = client.newCall(request).execute()) {
            return response.body() != null ? response.body().string() : "";
        }
    }
}