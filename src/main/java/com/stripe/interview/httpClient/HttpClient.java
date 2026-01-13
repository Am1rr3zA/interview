package com.stripe.interview.httpClient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HttpClient {
    private final CloseableHttpClient client;
    private final Map<String, String> defaultHeaders;

    public HttpClient() {
        this.client = HttpClients.createDefault();
        this.defaultHeaders = new HashMap<>();
    }

    public HttpClient withHeader(String name, String value) {
        defaultHeaders.put(name, value);
        return this;
    }

    public HttpClient withJwtAuth(String token) {
        return withHeader("Authorization", "Bearer " + token);
    }

    public HttpClient withBasicAuth(String username, String password) {
        String auth = java.util.Base64.getEncoder()
            .encodeToString((username + ":" + password).getBytes());
        return withHeader("Authorization", "Basic " + auth);
    }

    public String get(String url) throws IOException {
        return execute(new HttpGet(url));
    }

    public String post(String url, String body) throws IOException {
        HttpPost request = new HttpPost(url);
        if (body != null) {
            request.setEntity(new StringEntity(body));
        }
        return execute(request);
    }

    public String put(String url, String body) throws IOException {
        HttpPut request = new HttpPut(url);
        if (body != null) {
            request.setEntity(new StringEntity(body));
        }
        return execute(request);
    }

    public String delete(String url) throws IOException {
        return execute(new HttpDelete(url));
    }

    public String patch(String url, String body) throws IOException {
        HttpPatch request = new HttpPatch(url);
        if (body != null) {
            request.setEntity(new StringEntity(body));
        }
        return execute(request);
    }

    private String execute(HttpRequestBase request) throws IOException {
        // Add default headers
        for (Map.Entry<String, String> header : defaultHeaders.entrySet()) {
            request.setHeader(header.getKey(), header.getValue());
        }

        HttpResponse response = client.execute(request);
        HttpEntity entity = response.getEntity();
        return entity != null ? EntityUtils.toString(entity) : "";
    }

    public void close() throws IOException {
        client.close();
    }
}