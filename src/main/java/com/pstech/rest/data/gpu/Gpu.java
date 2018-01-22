package com.pstech.rest.data.gpu;

import com.pstech.rest.data.ProgressInfo;
import com.pstech.rest.data.SpeedInfo;
import com.pstech.rest.data.StatusInfo;
import lombok.Data;

@Data
public class Gpu {

    String name;

    StatusInfo statusInfo;

    DeviceInfo deviceInfo;

    ProgressInfo progressInfo;

    SpeedInfo speedInfo;

}
