package com.stripe.interview.publicapi.ip;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class IpClient {


    private static final String API_URL = "https://api.ipify.org?format=json";

    private final CloseableHttpClient httpClient;
    private final ObjectMapper objectMapper;

    public IpClient() {
//        this.httpClient = HttpClients.createDefault();

        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(5000)      // connection timeout (ms)
                .setSocketTimeout(8000)       // read timeout (ms)
                .setConnectionRequestTimeout(3000) // timeout for getting a connection from pool
                .build();

        this.httpClient = HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .build();

        this.objectMapper = new ObjectMapper();
    }

    public IpResponse fetchPublicIp() throws Exception {
        HttpGet request = new HttpGet(API_URL);

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            String json = EntityUtils.toString(response.getEntity());
            return objectMapper.readValue(json, IpResponse.class);
        }
    }

    public IpResponse postExample() throws Exception {
        HttpPost post = new HttpPost("https://example.com/api");
        post.setHeader("Content-Type", "application/json");
        post.setEntity(new StringEntity("{\"hello\":\"world\"}", "UTF-8"));

        try (CloseableHttpResponse response = httpClient.execute(post)) {
            String json = EntityUtils.toString(response.getEntity());
            return objectMapper.readValue(json, IpResponse.class);

        }
    }
}
