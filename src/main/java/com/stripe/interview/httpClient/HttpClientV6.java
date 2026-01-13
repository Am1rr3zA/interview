package com.stripe.interview.httpClient;

import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HttpClientV6 {
    private static final OkHttpClient SHARED_CLIENT = new OkHttpClient();
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    
    private final HeaderInterceptor headerInterceptor;

    private HttpClientV6(Map<String, String> headers) {
        this.headerInterceptor = new HeaderInterceptor(headers);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final Map<String, String> headers = new HashMap<>();

        public Builder header(String name, String value) {
            headers.put(name, value);
            return this;
        }

        public Builder jwtAuth(String token) {
            return header("Authorization", "Bearer " + token);
        }

        public Builder basicAuth(String username, String password) {
            String auth = java.util.Base64.getEncoder()
                .encodeToString((username + ":" + password).getBytes());
            return header("Authorization", "Basic " + auth);
        }

        public HttpClientV6 build() {
            return new HttpClientV6(new HashMap<>(headers));
        }
    }



    private static class HeaderInterceptor implements Interceptor {
        private final Map<String, String> headers;

        HeaderInterceptor(Map<String, String> headers) {
            this.headers = new HashMap<>(headers);
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request.Builder builder = chain.request().newBuilder();
            for (Map.Entry<String, String> header : headers.entrySet()) {
                builder.addHeader(header.getKey(), header.getValue());
            }
            return chain.proceed(builder.build());
        }
    }

    public String get(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        return execute(request);
    }

    public String post(String url, String body) throws IOException {
        RequestBody requestBody = body != null ? RequestBody.create(body, JSON) : RequestBody.create("", JSON);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        return execute(request);
    }

    public String put(String url, String body) throws IOException {
        RequestBody requestBody = body != null ? RequestBody.create(body, JSON) : RequestBody.create("", JSON);
        Request request = new Request.Builder().url(url).put(requestBody).build();
        return execute(request);
    }

    public String delete(String url) throws IOException {
        Request request = new Request.Builder().url(url).delete().build();
        return execute(request);
    }

    private String execute(Request request) throws IOException {
        OkHttpClient clientWithHeaders = SHARED_CLIENT.newBuilder()
            .addInterceptor(headerInterceptor)
            .build();
        
        try (Response response = clientWithHeaders.newCall(request).execute()) {
            return response.body() != null ? response.body().string() : "";
        }
    }
}