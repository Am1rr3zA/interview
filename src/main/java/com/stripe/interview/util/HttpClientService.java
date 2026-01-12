package com.stripe.interview.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class HttpClientService {
    private final OkHttpClient client;

    public HttpClientService() {
        this.client = HttpClientUtil.getInstance();
    }

    public String get(String url) throws IOException {
        Request request = new Request.Builder().url(url).get().build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("HTTP error: " + response.code());
            }
            return response.body().string();
        }
    }
}
