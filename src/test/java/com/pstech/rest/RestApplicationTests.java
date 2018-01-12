package com.pstech.rest;

import com.pstech.rest.data.PowerItemEntity;
import com.pstech.rest.data.PowerItemEntityRepository;
import com.pstech.rest.data.PowerReportEntity;
import com.pstech.rest.data.PowerReportEntityRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestApplicationTests {

    @Autowired
    PowerItemEntityRepository repository;

    @Autowired
    PowerReportEntityRepository repoReport;

    @Test
    public void contextLoads() {
    }

    @Test
    public void dataTest() {
        PowerItemEntity item = new PowerItemEntity();
        item.setTimestamp(LocalDateTime.now());
        item.setPc1_energy(0);
        item.setPc2_energy(1);
        item.setPc3_energy(2);
        item.setPo_energy(3);
        item.setPc1_power(4);
        item.setPc2_power(5);
        item.setPc3_power(6);
        item.setPo_power(7);
        repository.save(item);

        List<PowerItemEntity> items = repository.findAll();

    }

    @Test
    public void anotherDataTest() {
        List<PowerReportEntity> report = repoReport.generateReport(PowerReportEntity.DAILY_REPORT);
    }

}
