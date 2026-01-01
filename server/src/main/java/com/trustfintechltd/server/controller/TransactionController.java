package com.trustfintechltd.server.controller;

import com.trustfintechltd.server.dto.TransactionRequest;
import com.trustfintechltd.server.dto.TransactionResponse;
import com.trustfintechltd.server.service.TransactionService;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.StringReader;

@RestController
@RequestMapping("/server/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping(path = "/process", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public TransactionResponse processTransaction(@RequestBody String xmlRequest) throws JAXBException {

        // Unmarshal XML to TransactionRequest object
        JAXBContext jaxbContext = JAXBContext.newInstance(TransactionRequest.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        TransactionRequest request = (TransactionRequest) unmarshaller.unmarshal(new StringReader(xmlRequest));

        // Pass to service for processing
        return transactionService.processTransaction(request);
    }
}