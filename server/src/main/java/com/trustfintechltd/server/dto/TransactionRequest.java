package com.trustfintechltd.server.dto;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@Data
@XmlRootElement(name = "TransactionRequest")
@XmlAccessorType(XmlAccessType.FIELD)
public class TransactionRequest {

    @XmlElement(name = "TrxId")
    private String trxId;

    @XmlElement(name = "BankId")
    private String bankId;

    @XmlElement(name = "CustomerId")
    private Long customerId;

    @XmlElement(name = "FromAccount")
    private String fromAccount;

    @XmlElement(name = "ToAccount")
    private String toAccount;

    @XmlElement(name = "Amount")
    private Double amount;

    @XmlElement(name = "Currency")
    private String currency;

    @XmlElement(name = "Timestamp")
    private String timestamp;  // <-- changed from OffsetDateTime to String
}