package com.pstech.rest.coin;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@Service
public class QuoteApi {

    @Value("${quotes.url}")
    String url;
    private Logger logger = Logger.getLogger("QuoteApi");
    private RestTemplate restTemplate = new RestTemplate();

    public List<Quote> fetchQuotes() {
        logger.info("fetching quotes");
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            return Quote.parseQuotes(response.getBody());
        } else {
            return Collections.emptyList();
        }
    }

}
