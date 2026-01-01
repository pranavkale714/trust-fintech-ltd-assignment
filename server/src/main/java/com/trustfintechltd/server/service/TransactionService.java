package com.trustfintechltd.server.service;

import com.trustfintechltd.server.dto.TransactionRequest;
import com.trustfintechltd.server.dto.TransactionResponse;
import com.trustfintechltd.server.entity.TransactionEntity;
import com.trustfintechltd.server.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Async("transactionExecutor")
    public TransactionResponse processTransaction(TransactionRequest request) {
        long start = System.currentTimeMillis();

        String status;
        String reason;

        // Check for duplicate transaction
        Optional<TransactionEntity> existingTransaction = transactionRepository.findByTrxId(request.getTrxId());
        if (existingTransaction.isPresent()) {
            // Return the same response as before (idempotent)
            TransactionEntity trx = existingTransaction.get();
            return new TransactionResponse(trx.getTrxId(), trx.getStatus(), trx.getReason(), trx.getProcessingTimeMs());
        }

        // Validate amount
        if (request.getAmount() <= 0) {
            status = "FAILED";
            reason = "Invalid Amount";
        } else {
            status = "SUCCESS";
            reason = "Completed";
        }

        // Parse timestamp safely
        OffsetDateTime timestamp;
        try {
            timestamp = OffsetDateTime.parse(request.getTimestamp());
        } catch (DateTimeParseException e) {
            status = "FAILED";
            reason = "Invalid Timestamp";
            timestamp = OffsetDateTime.now();
        }

        long processingTime = System.currentTimeMillis() - start;

        // Persist transaction
        TransactionEntity entity = new TransactionEntity();
        entity.setTrxId(request.getTrxId());
        entity.setBankId(request.getBankId());
        entity.setCustomerId(request.getCustomerId());
        entity.setFromAccount(request.getFromAccount());
        entity.setToAccount(request.getToAccount());
        entity.setAmount(request.getAmount());
        entity.setCurrency(request.getCurrency());
        entity.setTimestamp(timestamp);
        entity.setStatus(status);
        entity.setReason(reason);
        entity.setProcessingTimeMs(processingTime);

        transactionRepository.save(entity);

        return new TransactionResponse(request.getTrxId(), status, reason, processingTime);
    }
}