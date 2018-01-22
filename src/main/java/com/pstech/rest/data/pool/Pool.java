package com.pstech.rest.data.pool;

import com.google.gson.annotations.SerializedName;
import com.pstech.rest.data.ProgressInfo;
import com.pstech.rest.data.StatusInfo;
import lombok.Data;

import javax.persistence.Embedded;

@Data
public class Pool {

    int id;

    String name;

    @Embedded
    StatusInfo statusInfo;

    @Embedded
    AdditionalInfo additionalInfo;

    PriorityInfo priorityInfo;

    @Embedded
    ProgressInfo progressInfo;

    String coinName;

    @SerializedName("minerID")
    int minerId;

    String minerName;

    boolean canRemove;

    boolean canDisable;

    boolean canEnable;

    boolean canPrioritize;

}
