package com.trustfintechltd.server.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionResponse {
    private String trxId;
    private String status;
    private String reason;
    private long processingTimeMs;
}