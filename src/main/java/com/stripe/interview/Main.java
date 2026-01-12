package com.stripe.interview;

import com.stripe.interview.publicapi.exchangerate.*;
import com.stripe.interview.publicapi.ip.IpClient;
import com.stripe.interview.publicapi.ipv2.IpClientV2;
import com.stripe.interview.util.GenericExporter;

import java.io.InputStream;
import java.util.Properties;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.CSVWriter;
import com.stripe.interview.util.HttpClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.Map;

public class Main {
    private static final Logger log =
            LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            ExchangeRateService exchangeRateService = new ExchangeRateService();
            ExchangeRateResponse response = exchangeRateService.fetchRates();


            InteractiveExchangeRateApp app = new InteractiveExchangeRateApp(response);
            app.run();




//            log.info("Conversion rate for: " +  response.getConversion_rates().get("CAD"));
//            // Save conversion rates as CSV
//            try (CSVWriter csvWriter = new CSVWriter(new FileWriter("exchange-rates.csv"))) {
//                // Header
//                csvWriter.writeNext(new String[]{"Currency", "Rate"});
//                // Data
//                for (Map.Entry<String, Double> entry : response.getConversion_rates().entrySet()) {
//                    csvWriter.writeNext(new String[]{entry.getKey(), String.valueOf(entry.getValue())});
//                }
//            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


//public class Main {
//	public static void main(String... args) {
//		System.out.println("Hello world!");
//	}
//
//	public static String useGuavaForSomeReason(String input) {
//		return HtmlEscapers.htmlEscaper().escape(input);
//	}
//}

//public class Main {
//
//	public static void main(String[] args) {
//		IpClient client = new IpClient();
//		IpClientV2 clientv2 = new IpClientV2();
//        ExchangeRate exchangeRate = new ExchangeRate();
//
//		try {
//
//
////			IpResponse ip = client.fetchPublicIp();
////			System.out.println("Your public IP (v1): " + ip.getIp());
////			IpResponse ipv2 = clientv2.fetchPublicIp();
////			System.out.println("Your public IP (v2): " + ipv2.getIp());
////
////            ExchangeRateResponse err = exchangeRate.getExchangeRate();
////            ExchangeRateResponseGson errGson = exchangeRate.getExchangeRateGson();
////            System.out.println("------------------");
////            System.out.println(err);
////            System.out.println(errGson);
////            System.out.println("------------------");
////            System.out.println(err.getDocumentation());
////            System.out.println(err.getConversion_rates().get("CAD"));
////            System.out.println(err.getConversion_rates().get("IRN"));
////
////            GenericExporter.writeJson(err, "exchange_rates.json");
////            GenericExporter.writeCsv(errGson, "exchange_rates.csv");
////            GenericExporter.writeCsvFlatten(errGson, "exchange_rates_flatten.csv");
//
//
//
////            ApiConfig config = new ApiConfig("https://api.exchangerate.host/latest");
////            System.out.println(config.getUrl());
////
////
////            ApiConfig config2 = ApiConfig.builder()
////                    .url("https://apiV2.exchangerate.host/latest")
////                    .build();
////            System.out.println(config2.getUrl());
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//}

