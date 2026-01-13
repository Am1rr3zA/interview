package com.stripe.interview.httpClient;

import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HttpClientV4 {
    private static final OkHttpClient SHARED_CLIENT = new OkHttpClient();
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    
    private final Map<String, String> headers;

    private HttpClientV4(Map<String, String> headers) {
        this.headers = new HashMap<>(headers);
    }

    public static HttpClientV4 create() {
        return new HttpClientV4(new HashMap<>());
    }

    public HttpClientV4 withHeader(String name, String value) {
        Map<String, String> newHeaders = new HashMap<>(this.headers);
        newHeaders.put(name, value);
        return new HttpClientV4(newHeaders);
    }

    public HttpClientV4 withJwtAuth(String token) {
        return withHeader("Authorization", "Bearer " + token);
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

    private Request.Builder buildRequest(String url) {
        Request.Builder builder = new Request.Builder().url(url);
        for (Map.Entry<String, String> header : headers.entrySet()) {
            builder.addHeader(header.getKey(), header.getValue());
        }
        return builder;
    }

    private String execute(Request request) throws IOException {
        try (Response response = SHARED_CLIENT.newCall(request).execute()) {
            return response.body() != null ? response.body().string() : "";
        }
    }
}