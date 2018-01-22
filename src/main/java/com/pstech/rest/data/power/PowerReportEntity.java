package com.pstech.rest.data.power;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PowerReportEntity {

    public final static String HOURLY_REPORT = "%Y-%m-%d %H:00";

    public final static String DAILY_REPORT = "%Y-%m-%d";

    @Id
    @Column(name = "dt")
    LocalDateTime localDateTime;

    @Column(name = "total_energy")
    double totalEnergy;

    @Column(name = "avgpower")
    double avgPower;

    @Column(name = "pc_energy")
    double pc_energy;

    @Column(name = "po_energy")
    double po_energy;

    @Column(name = "pc1_energy")
    double pc1_energy;

    @Column(name = "pc2_energy")
    double pc2_energy;

    @Column(name = "pc3_energy")
    double pc3_energy;

}
