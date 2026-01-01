package com.trustfintechltd.client.banka.service;

import com.trustfintechltd.client.banka.dto.TransactionRequestDTO;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
public class ConcurrentTransactionExecutor {

    private final TransactionService transactionService;

    public ConcurrentTransactionExecutor(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    public void executeConcurrentRequests(TransactionRequestDTO requestDTO, int requestCount) {

        int threadPoolSize = 100; // safe for local machine
        ExecutorService executorService = Executors.newFixedThreadPool(threadPoolSize);

        for (int i = 0; i < requestCount; i++) {
            executorService.submit(() -> {
                try {
                    transactionService.sendTransaction(requestDTO);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(10, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
