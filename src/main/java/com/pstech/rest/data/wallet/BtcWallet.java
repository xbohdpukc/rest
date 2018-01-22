package com.pstech.rest.data.wallet;

import com.google.gson.annotations.SerializedName;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BtcWallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String address;

    @SerializedName(value = "total_sent")
    double totalSent;

    @SerializedName(value = "total_sent")
    double totalReceived;

    @SerializedName(value = "total_sent")
    double balance;

    @SerializedName(value = "total_sent")
    double unconfirmedBalance;

    @SerializedName(value = "total_sent")
    double finalBalance;

    @SerializedName(value = "total_sent")
    long numberOfTransactions;

    @SerializedName(value = "total_sent")
    long numberOfUnconfirmedTransactions;

    @SerializedName(value = "total_sent")
    long totalNumberOfTransactions;


}
