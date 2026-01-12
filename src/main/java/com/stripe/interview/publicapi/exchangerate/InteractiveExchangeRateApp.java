package com.stripe.interview.publicapi.exchangerate;

import com.stripe.interview.Main;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class InteractiveExchangeRateApp {
    private static final Logger log =
            LoggerFactory.getLogger(InteractiveExchangeRateApp.class);

    private final ExchangeRateResponse response;
    private final Scanner scanner = new Scanner(System.in);

    public InteractiveExchangeRateApp(ExchangeRateResponse response) {
        this.response = response;
    }

    public void run() {
        log.info("=== Exchange Rate Converter ===");
        log.info("Enter currency code (USD, CAD, EUR, GBP, etc.) or 'quit' to exit:");

        while (true) {
            log.info("Enter the 3 Character Current: \nCurrency Input: ");
            String currency = scanner.nextLine().trim().toUpperCase();
            if (currency.equalsIgnoreCase("quit") || currency.equalsIgnoreCase("exit")) {
                log.info("Goodbye!");
                break;
            }

            if (currency.length() != 3) {
                log.info("Please enter a valid 3-letter currency code (e.g., USD, CAD)");
                continue;
            }

            try {

                // Dynamic logging - uses actual base currency from API response
                log.info("The currency conversion for {} is: {}",
                        currency,
                        response.getConversion_rates().get(currency));

            } catch (Exception e) {
                log.error("Error fetching rates: " + e.getMessage());
            }
        }
        scanner.close();
    }
}
