package com.pstech.rest.data.poolalgo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class PoolAlgo {

    static Logger logger = Logger.getLogger("PoolAlgo");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column
    String pool;

    @Column
    String name;

    @Column
    long port;

    @SerializedName("coins")
    @Column
    int numberOfCoins;

    @SerializedName("fees")
    @Column
    double feesPercent;

    @Column
    double hashrate;

    @Column
    long workers;

    @SerializedName("estimate_current")
    @Column
    double estimateCurrent;

    @SerializedName("estimate_last24h")
    @Column
    double estimateLast24h;

    @SerializedName("actual_last24h")
    @Column
    double actual24h;

    @SerializedName("hashrate_last24h")
    @Column
    double hashrateLast24h;

    @JsonIgnore
    @Column
    LocalDateTime lastUpdated;

    @SuppressWarnings("unchecked")
    public static List<PoolAlgo> parseAlgos(String jsonString, String poolName) {
        final Gson gson = new Gson();
        List<PoolAlgo> result = new ArrayList<>();
        Type hashMapType = new TypeToken<HashMap<String, PoolAlgo>>() {
        }.getType();
        try {
            HashMap<String, PoolAlgo> data = gson.fromJson(jsonString, hashMapType);
            result.addAll(data.values());
            String lcPoolName = poolName.toLowerCase();
            result.forEach(i -> {
                i.setPool(lcPoolName);
                i.setLastUpdated(LocalDateTime.now());
            });
        } catch (Exception e) {
            logger.warning("Error occurred while parsing data for " + poolName + ": " + e.getMessage());
        }
        return result;
    }

    public String toString() {
        return new Gson().toJson(this);
    }

}
