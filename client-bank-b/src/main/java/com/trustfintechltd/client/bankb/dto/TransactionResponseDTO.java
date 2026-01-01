package com.trustfintechltd.client.bankb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TransactionResponseDTO {
    private String trxId;
    private String status;
    private String message;
}