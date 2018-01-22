package com.pstech.rest;

import com.pstech.rest.data.power.PowerItemEntity;
import com.pstech.rest.data.power.PowerItemEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class MainController {

    Logger logger = Logger.getLogger("MainController");

    @Autowired
    PowerItemEntityRepository repository;

    @GetMapping("/addPowerDataItem")
    @Transactional
    public void addPowerDataItem(@RequestParam double pc1_energy, double pc2_energy, double pc3_energy, double po_energy,
                                 double pc1_power, double pc2_power, double pc3_power, double po_power) {
        PowerItemEntity item = new PowerItemEntity();
        item.setPc1_energy(pc1_energy);
        item.setPc2_energy(pc2_energy);
        item.setPc3_energy(pc3_energy);
        item.setPo_energy(po_energy);
        item.setPc1_power(pc1_power);
        item.setPc2_power(pc2_power);
        item.setPc3_power(pc3_power);
        item.setPo_power(po_power);
        repository.saveAndFlush(item);
        logger.info("Saved new power data item, id=" + item.getId());
    }

}
