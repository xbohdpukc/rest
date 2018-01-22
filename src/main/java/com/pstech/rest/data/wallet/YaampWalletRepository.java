package com.pstech.rest.data.wallet;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component
public interface YaampWalletRepository extends JpaRepository<YaampWallet, Long> {
}
