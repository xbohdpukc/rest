package com.pstech.rest.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface PowerReportEntityRepository extends JpaRepository<PowerReportEntity, LocalDateTime> {


    @Query(nativeQuery = true,
            value =
                    "SELECT \n" +
                            "    dt,\n" +
                            "    pc1_energy + pc2_energy + pc3_energy + po_energy AS total_energy,\n" +
                            "    avgpower,\n" +
                            "    pc1_energy + pc2_energy + pc3_energy AS pc_energy,\n" +
                            "    po_energy,\n" +
                            "    pc1_energy,\n" +
                            "    pc2_energy,\n" +
                            "    pc3_energy\n" +
                            "FROM\n" +
                            "    stpowerdata stp,\n" +
                            "    (SELECT \n" +
                            "        DATE_FORMAT(timestamp, ?1) dt,\n" +
                            "            MAX(timestamp) mxts,\n" +
                            "            AVG(pc1_power + pc2_power + pc3_power + po_power) avgpower\n" +
                            "    FROM\n" +
                            "        stpowerdata\n" +
                            "    GROUP BY 1) dt\n" +
                            "WHERE\n" +
                            "    stp.timestamp = dt.mxts\n" +
                            "ORDER BY dt DESC")
    List<PowerReportEntity> generateReport(String reportType);

}
