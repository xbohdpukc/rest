package com.pstech.rest.data.poolalgo;

import com.pstech.rest.Utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public final class PoolApi {

    private final RestTemplate restTemplate = new RestTemplate();
    @Value("#{'${pools}'.split(',')}")
    List<String> poolNames;
    private Logger logger = Logger.getLogger("PoolApi");

    public List<PoolAlgo> fetchPools() {
        logger.info("fetching pools");
        Environment environment = Utils.getEnvironment();
        final String statusCommand = environment.getProperty("pool.status.command");
        return poolNames.stream().map(poolName -> {
            String url = environment.getProperty(poolName + ".url");
            try {
                HttpHeaders headers = new HttpHeaders();
                headers.set("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
                headers.set("Accept-Encoding", "gzip, deflate");
                headers.set("Accept-Language", "en-US,en;q=0.9");
                headers.set("Connection", "keep-alive");
                headers.set("DNT", "1");
                headers.set("Upgrade-Insecure-Requests", "1");
                headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0, Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/237.36");

                HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

                ResponseEntity<String> response = restTemplate.exchange(url + statusCommand, HttpMethod.GET, entity, String.class);
                if (response.getStatusCode() == HttpStatus.OK) {
                    logger.warning("Storing data for " + poolName);
                    return PoolAlgo.parseAlgos(response.getBody(), poolName);
                } else {
                    logger.warning("Failed to get poolalgo data from " + poolName + ", error " + response.getStatusCodeValue());
                    return new ArrayList<PoolAlgo>();
                }
            } catch (Exception e) {
                logger.warning("Exception while fetching " + poolName + ": " + e.getMessage());
                return new ArrayList<PoolAlgo>();
            }
        }).flatMap(Collection::stream).collect(Collectors.toList());
    }

}
