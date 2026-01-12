package com.stripe.interview.publicapi.exchangerate;

import lombok.Data;
import com.google.gson.annotations.SerializedName;
import java.util.Map;

@Data
public class ExchangeRateResponseGson {
    @SerializedName("time_last_update_unix")
    private long time_last_update_unix;

    @SerializedName("time_last_update_utc")
    private String time_last_update_utc;

    @SerializedName("time_next_update_unix")
    private long time_next_update_unix;

    @SerializedName("time_next_update_utc")
    private String time_next_update_utc;


    @SerializedName("base_code")
    private String baseCode;

    @SerializedName("conversion_rates")
    private Map<String, Double> conversionRates;
}
