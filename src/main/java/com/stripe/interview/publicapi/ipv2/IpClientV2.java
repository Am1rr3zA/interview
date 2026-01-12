package com.stripe.interview.publicapi.ipv2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stripe.interview.publicapi.ip.IpResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class IpClientV2 {

    private final OkHttpClient client;
    private final Request request;
    private final ObjectMapper mapper;


    // Dependency-injectable constructor (for unit tests)
    public IpClientV2(OkHttpClient client, Request request, ObjectMapper mapper) {
        this.client = client;
        this.request = request;
        this.mapper = mapper;
    }

    public IpClientV2() {
        this(
                new OkHttpClient(),
                new Request.Builder()
                        .url("https://api.ipify.org?format=json")
                        .get()
                        .build(),
                new ObjectMapper()
        );
    }

    public IpResponse fetchPublicIp() throws Exception {
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("Failed: HTTP " + response.code());
            }
            String body = response.body().string();
            return mapper.readValue(body, IpResponse.class);

        }
    }
}
