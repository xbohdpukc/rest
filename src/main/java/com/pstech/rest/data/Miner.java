package com.pstech.rest.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.Gson;
import com.pstech.rest.data.gpu.Gpu;
import com.pstech.rest.data.pool.Pool;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
//@Entity
@Table(name = "miners")
public class Miner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long entityId;

    @JsonIgnore
    transient Logger logger = Logger.getLogger("Miner");

    int id;

    String name;

    String hostname;

    @Transient
    int groupId;

    String pool;

    String poolName;

    String algoName;

    String temperature;

    @Embedded
    StatusInfo statusInfo;

    @Embedded
    ProgressInfo progressInfo;

    @Embedded
    SpeedInfo speedInfo;

    @Embedded
    CoinInfo coinInfo;

    String updatedUtc;

    String updated;

    @ElementCollection
    List<Pool> poolList;

    @Transient
    List<Gpu> gpuList;

    boolean hasPool;

    @Transient
    boolean hasGpu;

    @Transient
    boolean hasPga;

    @Transient
    boolean hasAsic;

    boolean canReboot;

    boolean canStop;

    boolean canRestart;

    boolean canStart;

    boolean canPool;

    boolean hasValidStatus;

    @Transient
    MetaData metaData;


    public static Miner parseMiner(String minerData) {
        final Gson gson = new Gson();
        return gson.fromJson(minerData, Miner.class);
    }

    public void setPool(String pool) {
        this.pool = pool;
        Pattern pattern = Pattern.compile("(?<poolname>.+?)\\s\\[(?<algoname>.+?)\\]");
        Matcher matcher = pattern.matcher(pool);
        if (matcher.find() && matcher.groupCount() == 2) {
            this.poolName = matcher.group("poolname").toLowerCase();
            this.algoName = matcher.group("algoname").toLowerCase();
        } else {
            logger.warning("Miner " + id + " cannot parse poolalgo: " + pool);
        }
    }


}

