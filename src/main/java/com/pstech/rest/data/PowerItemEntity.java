package com.pstech.rest.data;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "stpowerdata")
@NoArgsConstructor
public class PowerItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column()
    LocalDateTime timestamp = LocalDateTime.now();

    @Column
    double pc1_energy;

    @Column
    double pc2_energy;

    @Column
    double pc3_energy;

    @Column
    double po_energy;

    @Column
    double pc1_power;

    @Column
    double pc2_power;

    @Column
    double pc3_power;

    @Column
    double po_power;

}
