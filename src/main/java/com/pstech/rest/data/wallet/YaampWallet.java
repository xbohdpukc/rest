package com.pstech.rest.data.wallet;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.logging.Logger;

@Data
@Entity
public class YaampWallet {

    private static Logger logger = Logger.getLogger("YaampWallet");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    LocalDateTime timestamp = LocalDateTime.now();

    String pool;

    String currency;

    @SerializedName(value = "unsold")
    double unsold;

    @SerializedName(value = "balance")
    double balance;

    @SerializedName(value = "unpaid", alternate = {"total_unpaid"})
    double unpaid;

    @SerializedName(value = "paid24h", alternate = {"total_paid"})
    double paid24h;

    @SerializedName(value = "total", alternate = {"total_earned"})
    double total;

    @SuppressWarnings("unchecked")
    public static YaampWallet parseWallet(String jsonString, String poolName) {
        final Gson gson = new Gson();
        YaampWallet result = new YaampWallet();
        try {
            result = gson.fromJson(jsonString, YaampWallet.class);
            result.setPool(poolName);
        } catch (Exception e) {
            logger.warning("Error occurred while parsing data for " + poolName + ": " + e.getMessage());
        }
        return result;
    }

}
