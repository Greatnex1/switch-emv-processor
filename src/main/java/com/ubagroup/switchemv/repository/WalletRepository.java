package com.ubagroup.switchemv.repository;

import com.ubagroup.switchemv.model.Wallet;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select w from Wallet w where w.userId = :id")
    Optional<Wallet> lockByUserId(Long id);

}
