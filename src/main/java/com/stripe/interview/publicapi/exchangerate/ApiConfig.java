package com.stripe.interview.publicapi.exchangerate;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiConfig {
    String url;
    String method;
    int connectTimeout;
    int readTimeout;
}
