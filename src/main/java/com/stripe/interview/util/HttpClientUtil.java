package com.stripe.interview.util;


import lombok.Builder;
import lombok.Data;
import okhttp3.OkHttpClient;

import java.util.concurrent.TimeUnit;

@Data
@Builder
public class HttpClientUtil {
    private static volatile OkHttpClient instance;

    private HttpClientUtil() {

    }

    public static OkHttpClient getInstance() {
        if (instance == null) {
            synchronized (OkHttpClient.class) {
                if (instance == null) {
                    instance = new OkHttpClient.Builder()
                            .connectTimeout(5, TimeUnit.SECONDS)
                            .readTimeout(10, TimeUnit.SECONDS)
                            .writeTimeout(10, TimeUnit.SECONDS)
                            .callTimeout(15, TimeUnit.SECONDS)
                            .retryOnConnectionFailure(true)
                            .build();
                }
            }
        }
        return instance;
    }
}
