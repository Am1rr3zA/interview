package com.stripe.interview.publicapi.exchangerate;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVWriter;
import com.stripe.interview.Main;
import com.stripe.interview.util.HttpClientService;
import lombok.Data;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Properties;

@Data
public class ExchangeRateService {
    private final ExchangeRate exchangeRate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ExchangeRateService(ExchangeRate exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public ExchangeRateService()  throws Exception {
        Properties props = new Properties();
        try (InputStream input = getClass().getResourceAsStream("/exchange-api.properties")) {
            if (input == null) {
                throw new RuntimeException("exchange-api.properties not found on classpath");
            }
            props.load(input);
        }

        // Build URL from properties
        String endpoint = props.getProperty("exchange.api.endpoint");
        String baseCurrency = props.getProperty("exchange.api.baseCurrency");
        String fullUrl = endpoint + baseCurrency;

        // Create ExchangeRate instance with dynamic config
        ApiConfig config = ApiConfig.builder()
                .url(fullUrl)
                .method("GET")
                .connectTimeout(10000)
                .readTimeout(10000)
                .build();


        this.exchangeRate = new ExchangeRate(config, new HttpClientService());
    }

    public ExchangeRateResponse fetchRates() throws Exception {

        return exchangeRate.getExchangeRate();
    }

}
