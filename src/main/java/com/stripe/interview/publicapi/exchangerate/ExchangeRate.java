package com.stripe.interview.publicapi.exchangerate;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.stripe.interview.util.HttpClientService;
import lombok.Data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
public class ExchangeRate {

    private static final Logger log =
            LoggerFactory.getLogger(ExchangeRate.class);

    private final ApiConfig apiConfig;
    private final HttpClientService httpClientService;


    public ExchangeRate(ApiConfig apiConfig, HttpClientService httpClientService) {
        this.apiConfig = apiConfig;
        this.httpClientService = httpClientService;
    }


    public ExchangeRateResponse getExchangeRate() throws Exception {

        log.info("Fetching URL: {}", apiConfig.getUrl());


        String body = httpClientService.get(apiConfig.getUrl());
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(body, ExchangeRateResponse.class);

    }

    public ExchangeRateResponseGson getExchangeRateGson() throws Exception {
        String body = httpClientService.get(apiConfig.getUrl());
        Gson gson = new Gson();
        ExchangeRateResponseGson er = gson.fromJson(body, ExchangeRateResponseGson.class);
        return er;

    }

}
