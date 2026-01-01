package com.trustfintechltd.server.repository;

import com.trustfintechltd.server.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransactionRepository extends JpaRepository<TransactionEntity, Long> {
    Optional<TransactionEntity> findByTrxId(String trxId);
}