package com.pstech.rest.data.wallet;

import com.pstech.rest.Utils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class YaampWalletApi {

    private final RestTemplate restTemplate = new RestTemplate();
    Logger logger = Logger.getLogger("YaampWallet");
    @Value("#{'${pools}'.split(',')}")
    List<String> poolNames;

    public List<YaampWallet> fetchWallets() {
        logger.info("fetching pools");
        Environment environment = Utils.getEnvironment();
        return poolNames.stream().map(poolName -> {
            String url = environment.getProperty(poolName + ".url") + environment.getProperty("pool.wallet.command." + poolName);
            try {
                HttpHeaders headers = new HttpHeaders();
                if (poolName.equalsIgnoreCase("hashrefinery")) {
                    headers.set("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
                    headers.set("Accept-Encoding", "gzip, deflate, br");
                    headers.set("Accept-Language", "en-US,en;q=0.9");
                    headers.set("Cache-Control", "max-age=0");
                    headers.set("Connection", "keep-alive");
                    headers.set("DNT", "1");
                    headers.set("Cookie", "__lnkrntdmcvrd=-1");
                    headers.set("Upgrade-Insecure-Requests", "1");
                }
                headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0, Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/237.36");

                HttpEntity<String> entity = new HttpEntity<>("parameters", headers);

                ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
                if (response.getStatusCode() == HttpStatus.OK) {
                    logger.info("Storing data for " + poolName);
                    return YaampWallet.parseWallet(response.getBody(), poolName);
                } else {
                    logger.warning("Failed to get poolalgo data from " + poolName + ", error " + response.getStatusCodeValue());
                    return null;
                }
            } catch (Exception e) {
                logger.warning("Exception while fetching " + poolName + ": " + e.getMessage());
                return null;
            }
        }).filter(Objects::nonNull).filter(w -> !w.getPool().isEmpty()).collect(Collectors.toList());
    }


}
