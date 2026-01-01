package com.trustfintechltd.client.banka.service;

import com.trustfintechltd.client.banka.dto.TransactionRequestDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TransactionService {

    private static final String SERVER_URL =
            "http://localhost:8080/server/transaction/process";

    private final RestTemplate restTemplate = new RestTemplate();

    public void sendTransaction(TransactionRequestDTO dto) {
        // logic here (XML conversion + call server)
        restTemplate.postForObject(SERVER_URL, "<xml/>", String.class);
    }
}