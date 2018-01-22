package com.pstech.rest.data;

import lombok.Data;

import javax.persistence.Transient;

@Data
public class StatusInfo {

    String statusDisplay;

    @Transient
    String statusLine3;

}
