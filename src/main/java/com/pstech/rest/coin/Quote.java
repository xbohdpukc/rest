package com.pstech.rest.coin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import lombok.Data;

import javax.persistence.*;
import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "quotes")
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long quoteId;

    String id; // bitcoin

    String name; // Bitcoin

    String symbol; // BTC

    int rank; // 1

    @SerializedName("price_usd")
    double priceUsd;

    @SerializedName("price_btc")
    double priceBtc;

    @SerializedName("24h_volume_usd")
    double volumeUsd24h;

    @SerializedName("market_cap_usd")
    double marketCapUsd;

    @SerializedName("available_supply")
    double availableSupply;

    @SerializedName("total_supply")
    double totalSupply;

    @SerializedName("percent_change_1h")
    double percentChange1h;

    @SerializedName("percent_change_24h")
    double percentChange24h;

    @SerializedName("percent_change_7d")
    double percentChange7d;

    @SerializedName("last_updated")
    long lastUpdated;

    @SuppressWarnings("unchecked")
    public static List<Quote> parseQuotes(String quoteData) {
        final Gson gson = new Gson();
        Type listType = new TypeToken<ArrayList<Quote>>() {
        }.getType();
        return gson.fromJson(quoteData, listType);
    }

    @JsonIgnore
    public LocalDateTime getLastUpdatedDateTime() {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(lastUpdated), TimeZone.getDefault().toZoneId());
    }

}
