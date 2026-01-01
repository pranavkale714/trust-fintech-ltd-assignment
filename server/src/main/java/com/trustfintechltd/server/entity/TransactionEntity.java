package com.trustfintechltd.server.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Entity
@Table(name = "transactions")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String trxId;

    private String bankId;
    private Long customerId;
    private String fromAccount;
    private String toAccount;
    private Double amount;
    private String currency;

    private OffsetDateTime timestamp;

    private String status;
    private String reason;

    private Long processingTimeMs;
}
