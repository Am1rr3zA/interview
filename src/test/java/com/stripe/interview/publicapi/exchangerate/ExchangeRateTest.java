package com.stripe.interview.publicapi.exchangerate;



import com.stripe.interview.util.HttpClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;


public class ExchangeRateTest {
    private HttpClientService mockHttpClient;
    private ExchangeRate exchangeRate;

    private static final String MOCK_JSON =
            "{\n" +
                    "  \"base_code\": \"USD\",\n" +
                    "  \"conversion_rates\": {\n" +
                    "    \"CAD\": 1.3956,\n" +
                    "    \"EUR\": 0.8581\n" +
                    "  }\n" +
                    "}";


    @BeforeEach
    void setup() {
        mockHttpClient = mock(HttpClientService.class);
        ApiConfig config = ApiConfig.builder()
                .url("https://fake-api.com")
                .connectTimeout(100)
                .method("GET")
                .build();

        exchangeRate = new ExchangeRate(config, mockHttpClient);
    }

    @Test
    void testGetExchangeRateJackson() throws Exception {
        // Arrange: mock HttpClientService
        when(mockHttpClient.get(anyString())).thenReturn(MOCK_JSON);

        // Act
        ExchangeRateResponse response = exchangeRate.getExchangeRate();

        // Assert
        assertNotNull(response);
        assertEquals("USD", response.getBase_code());
        assertEquals(1.3956, response.getConversion_rates().get("CAD"));
        assertEquals(0.8581, response.getConversion_rates().get("EUR"));

        // Verify the mock was called exactly once
        verify(mockHttpClient, times(1)).get(anyString());
    }

    @Test
    void testGetExchangeRateGson() throws Exception {
        // Arrange
        when(mockHttpClient.get(anyString())).thenReturn(MOCK_JSON);

        // Act
        ExchangeRateResponseGson response = exchangeRate.getExchangeRateGson();

        // Assert
        assertNotNull(response);
        assertEquals("USD", response.getBaseCode());
        assertEquals(1.3956, response.getConversionRates().get("CAD"));
        assertEquals(0.8581, response.getConversionRates().get("EUR"));

        verify(mockHttpClient, times(1)).get(anyString());
    }
}
