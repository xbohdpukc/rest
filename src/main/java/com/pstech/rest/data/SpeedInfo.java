package com.pstech.rest.data;

import lombok.Data;

import javax.persistence.Transient;

@Data
public class SpeedInfo {

    long logInterval;

    String hashrate;

    String avgHashrate;

    @Transient
    String workUtility;

    @Transient
    String progressLine2;

    @Transient
    String progressLine3;

}
