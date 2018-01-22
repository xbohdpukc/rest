package com.pstech.rest.data.gpu;

import lombok.Data;

@Data
public class DeviceInfo {

    String deviceType;

    long gpuActivity;

    String intensity;

    String name;

    long gpuClock;

    long gpuMemoryClock;

    String gpuVoltage;

    long gpuPowertune;

    long fanSpeed;

    long fanPercent;

    long temperature;

}
