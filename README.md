Project Description

Build a concurrent transaction processing system where clients (Bank A & Bank B) send transaction requests in JSON, which are converted to XML and processed by a central server.

The server validates transactions, stores logs in a database, and responds with transaction status and processing time.

Core Features:
Server
Accepts XML requests from Bank A & Bank B

Validates:
Unique transaction IDs (trxId)
Positive amount
Correct account numbers
Persists transaction logs in a database
Returns JSON responses with status (SUCCESS / FAILED) and reason
Handles multithreaded processing for high load (8000 concurrent requests)
Client (Bank A & Bank B)
Receives JSON requests
Generates unique trxId
Converts request to XML
Sends XML to the server
Handles server responses
Supports concurrent transaction execution

Tech Stack:
Spring Boot 3.x
Java 17+ (tested on Java 20)
Spring Data JPA
H2 / PostgreSQL / MySQL database
JAXB for XML marshalling/unmarshalling
Java Concurrency utilities for multithreaded request handling
Postman / JMeter for testing 8000 concurrent requests


Prerequisites:
JDK 17+ / 20
Maven
MySQL / PostgreSQL / H2
Postman (optional for manual testing)
JMeter (for load testing)



Create Database:
CREATE DATABASE transactionsdb;


Transactions Table:
CREATE TABLE transactions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    trx_id VARCHAR(255) UNIQUE NOT NULL,
    bank_id VARCHAR(255) NOT NULL,
    customer_id BIGINT NOT NULL,
    from_account VARCHAR(255) NOT NULL,
    to_account VARCHAR(255) NOT NULL,
    amount DOUBLE NOT NULL,
    currency VARCHAR(255) NOT NULL,
    timestamp TIMESTAMP(6) WITH TIME ZONE NOT NULL,
    status VARCHAR(255) NOT NULL,
    reason VARCHAR(255) NOT NULL,
    processing_time_ms BIGINT
);


Concurrency & Load Testing
Server supports 8000 concurrent requests from both clients
Multithreading implemented via Java ExecutorService
JMeter is used for load testing
Variables in JMeter ensure unique trxId and timestamp per request
Summary reports capture:
Success vs failure counts
Average / peak processing times
Testing & Verification
Send JSON requests to client APIs
Clients convert requests to XML and forward to server
Server validates, persists, and returns JSON response
Use JMeter to simulate 8000 concurrent requests
Capture Summary Report Listener logs and screenshots
