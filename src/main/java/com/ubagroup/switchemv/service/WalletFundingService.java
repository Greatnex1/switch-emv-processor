package com.ubagroup.switchemv.service;

import com.ubagroup.switchemv.model.Wallet;
import com.ubagroup.switchemv.repository.WalletRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class WalletFundingService {

    private final WalletRepository walletRepo;

    @Transactional
    public void fund(Long userId, BigDecimal amount) {

        Wallet wallet = walletRepo.lockByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        wallet.setBalance(wallet.getBalance().add(amount));
        log.info("Funding success.");
        log.info("Current balance: {}", wallet.getBalance());

    }

        @Transactional
    public void debit(Long userId, BigDecimal amount) {
        Wallet wallet = walletRepo.lockByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));
        if (wallet.getBalance().compareTo(amount) < 0)
            throw new RuntimeException("Insufficient funds");
        wallet.setBalance(wallet.getBalance().subtract(amount));
    }
}
