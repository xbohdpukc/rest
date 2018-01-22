package com.pstech.rest;

import com.pstech.rest.coin.QuoteApi;
import com.pstech.rest.coin.QuoteRepository;
import com.pstech.rest.data.poolalgo.PoolAlgoEntityRepository;
import com.pstech.rest.data.poolalgo.PoolApi;
import com.pstech.rest.data.wallet.YaampWalletApi;
import com.pstech.rest.data.wallet.YaampWalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Logger;

@Component
public class ScheduledJobs {

    @Value("#{'${coins.of.interest}'.split(',')}")
    List<String> coinsOfInterest;
    @Autowired
    PoolApi poolApi;
    @Autowired
    YaampWalletApi walletApi;
    @Autowired
    QuoteApi quoteApi;
    @Autowired
    PoolAlgoEntityRepository poolRepo;
    @Autowired
    YaampWalletRepository walletRepo;
    @Autowired
    QuoteRepository quoteRepo;
    @Autowired
    DataSource dataSource;
    private Logger logger = Logger.getLogger("ScheduledJobs");

    public void forceUpdateAll() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        updatePools();
        updateWallets();
        updateQuotes();
    }

    @Scheduled(fixedRateString = "${poll.rate.pools}")
    public void updatePools() {
        logger.info("job: updateMiners triggered");
        poolRepo.saveAll(poolApi.fetchPools());
    }

    @Scheduled(fixedRateString = "${poll.rate.wallets}")
    public void updateWallets() throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        logger.info("job: updateWallets");
        walletApi.fetchWallets().forEach(i -> {
            try {
                walletRepo.save(i);
            } catch (Exception e) {
                logger.warning("Error saving date for " + i.getPool() + ": " + e.getMessage());
            }
        });
    }

    @Scheduled(fixedRateString = "${poll.rate.quotes}")
    public void updateQuotes() {
        logger.info("job: updating quotes");
        quoteApi.fetchQuotes().stream()
                .filter(q -> coinsOfInterest.contains(q.getSymbol().toUpperCase()))
                .forEach(q -> {
                    try {
                        quoteRepo.save(q);
                    } catch (Exception e) {
                        logger.warning("Failed to store quote " + q.getSymbol() + ": " + e.getMessage());
                    }
                });
    }

    @Scheduled(cron = "0 0 1 * * ?")
    public void updateDailyResultsInDb() {
        new SimpleJdbcCall(dataSource).withProcedureName("update_all_daily_results").execute();
        new SimpleJdbcCall(dataSource).withProcedureName("update_pools_daily_results").execute();
    }


}
