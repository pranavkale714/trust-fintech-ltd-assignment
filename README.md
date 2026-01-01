# Concurrent Transaction Processing System

## Project Description

This project implements a **concurrent transaction processing system** where clients (Bank A & Bank B) send transaction requests in JSON format. Requests are converted to XML, processed by a central server, validated, logged in a database, and the server responds with transaction status and processing time.

---

## Core Features

### Server

* Accepts XML requests from Bank A & Bank B.
* Validates:

  * Unique transaction IDs (`trxId`)
  * Positive transaction amount
  * Correct account numbers
* Persists transaction logs in a database.
* Returns JSON responses with:

  * Status (`SUCCESS` / `FAILED`)
  * Reason for failure (if any)
* Handles multithreaded processing to support **high load (up to 8000 concurrent requests)**.

### Client (Bank A & Bank B)

* Receives JSON requests from users.
* Generates a **unique `trxId`** for each transaction.
* Converts JSON requests to XML.
* Sends XML requests to the server.
* Handles server responses.
* Supports **concurrent transaction execution**.

---

## Tech Stack

* **Backend:** Spring Boot 3.x, Java 17+ (tested on Java 20)
* **Database:** H2 / MySQL / PostgreSQL
* **JPA:** Spring Data JPA
* **XML Handling:** JAXB for marshalling/unmarshalling
* **Concurrency:** Java ExecutorService for multithreading
* **Testing:** Postman (manual) / JMeter (load testing)

---

## Prerequisites

* JDK 17+ / 20
* Maven
* MySQL / PostgreSQL / H2
* Postman (optional, for manual testing)
* JMeter (for load testing)

---

## Database Setup

### Create Database

```sql
CREATE DATABASE transactionsdb;
```

### Transactions Table

```sql
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
```

---

## Concurrency & Load Testing

* Server supports **up to 8000 concurrent requests** from clients.
* Multithreading implemented via **Java ExecutorService**.
* **JMeter** is used to simulate high concurrency:

  * Variables ensure unique `trxId` and timestamps for each request.
  * Summary reports capture:

    * Success vs failure counts
    * Average / peak processing times

---

## Testing & Verification

1. Send JSON requests to the **client APIs**.
2. Clients convert requests to XML and forward them to the server.
3. Server validates requests, persists them in the database, and returns JSON responses.
4. Use **JMeter** to simulate 8000 concurrent requests.
5. Capture **summary report logs and scr
