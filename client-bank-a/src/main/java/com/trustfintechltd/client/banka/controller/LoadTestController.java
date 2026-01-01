package com.trustfintechltd.client.banka.controller;

import com.trustfintechltd.client.banka.dto.TransactionRequestDTO;
import com.trustfintechltd.client.banka.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.*;

@RestController
@RequestMapping("/bank/load-test")
public class LoadTestController {

    private final TransactionService transactionService;

    public LoadTestController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public String runLoadTest(@RequestParam(defaultValue = "1000") int requests)
            throws InterruptedException {

        int threadPoolSize = 100; // adjustable
        ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);
        CountDownLatch latch = new CountDownLatch(requests);

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < requests; i++) {
            executor.submit(() -> {
                try {
                    TransactionRequestDTO dto = new TransactionRequestDTO();
                    dto.setCustomerId(892345L);
                    dto.setFromAccount("1234567890");
                    dto.setToAccount("9876543210");
                    dto.setAmount(100.0);
                    dto.setCurrency("INR");

                    transactionService.sendTransaction(dto);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executor.shutdown();

        long totalTime = System.currentTimeMillis() - startTime;

        return "Sent " + requests + " concurrent requests in " + totalTime + " ms";
    }
}
