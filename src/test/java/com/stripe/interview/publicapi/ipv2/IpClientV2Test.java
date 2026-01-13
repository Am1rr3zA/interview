package com.stripe.interview.publicapi.ipv2;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.stripe.interview.publicapi.ip.IpResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IpClientV2Test {

    private MockWebServer server;
    private ObjectMapper mapper;

//    private final Gson gson = new Gson();

    @BeforeEach
    void setup() throws Exception {
        server = new MockWebServer();
        server.start();
        mapper = new ObjectMapper();
    }

    @AfterEach
    void tearDown() throws Exception {
        server.shutdown();
    }

//    @Test
//    public void testFetchPublicIp_success() throws Exception {
//        // Given: mock JSON response
//        String mockJson = "{\"ip\": \"123.45.67.89\"}";
//        server.enqueue(new MockResponse()
//                .setResponseCode(200)
//                .setBody(mockJson));
//
//        // Build client pointing to mock server
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url(server.url("/"))  // mock URL
//                .build();
//
//        IpClientV2 api = new IpClientV2(client, request, mapper);
//
//        // When
//        IpResponse response = api.fetchPublicIp();
//
//        // Then
//        assertNotNull(response);
//        assertEquals("123.45.67.89", response.getIp());
//    }

//    @Test
//    public void testFetchPublicIp_httpError() throws Exception {
//        server.enqueue(new MockResponse().setResponseCode(500));
//
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url(server.url("/"))
//                .build();
//
//        IpClientV2 api = new IpClientV2(client, request, mapper);
//
//        Exception ex = assertThrows(RuntimeException.class, api::fetchPublicIp);
//        assertTrue(ex.getMessage().contains("HTTP 500"));
//    }
}
