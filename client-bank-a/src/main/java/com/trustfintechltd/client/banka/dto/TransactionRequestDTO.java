package com.trustfintechltd.client.banka.dto;

import lombok.Data;

@Data
public class TransactionRequestDTO {
    private Long customerId;
    private String fromAccount;
    private String toAccount;
    private Double amount;
    private String currency;
}