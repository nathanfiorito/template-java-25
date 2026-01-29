# Template Java 25 - Spring Boot & Hexagonal Architecture

Welcome to the standard template for Java 25 applications. This project is built on strict architectural principles to ensure maintainability, scalability, and clean code.

## 🚀 Tech Stack

- **Language:** Java 25 (Preview Features Enabled)
- **Framework:** Spring Boot 3.x
- **Architecture:** Hexagonal (Ports & Adapters)
- **Quality:** ArchUnit, Checkstyle
- **Build:** Maven
- **Containerization:** Docker

## 🏗️ Architecture

This project strictly follows **Hexagonal Architecture**. The codebase is organized into three distinct layers with a strict dependency rule: **Dependencies point INWARDS.**

### 1. Domain (Center)
*   **Purpose:** Core business logic and rules.
*   **Rules:**
    *   **No Frameworks:** Pure Java only. No Spring, JPA, or Jackson annotations.
    *   **Immutability:** Usage of `record` for entities and value objects.
    *   **Sealed Interfaces:** For modeling domain states and errors.

### 2. Application (Use Cases)
*   **Purpose:** Orchestration of domain logic.
*   **Components:**
    *   **Input Ports:** Interfaces defining the use cases.
    *   **Use Cases:** Implementations (`Service` classes) that implement Input Ports.
    *   **Output Ports:** Interfaces for external communication (Repositories, Clients).

### 3. Infrastructure (Adapters)
*   **Purpose:** Implementation of technical details.
*   **Components:**
    *   **Input Adapters:** REST Controllers, Message Consumers.
    *   **Output Adapters:** JPA Repositories, Feign Clients, Redis Adapters.
    *   **Configuration:** Spring Boot config classes.

## 📏 Clean Code Standards

We enforce strict coding standards:
- **Naming:** Semantic and descriptive.
    - Domain: `Order`, `Product`
    - UseCase: `CreateOrderUseCase`
    - Service: `OrderService`
    - Repository Port: `OrderRepository`
    - Controller: `OrderController`
    - JPA Adapter: `OrderPersistenceAdapter`
- **Immutability:** Prefer `final`, `records`, and immutable collections.
- **No Empty Catches:** All exceptions must be handled or propagated meaningful context.
- **Early Return:** Avoid deep nesting.

## 🛠️ How to Run

### Local Development (Maven)
```bash
cd app
mvn spring-boot:run
```

### Docker
```bash
docker build -t template-java-25 .
docker run -p 8080:8080 template-java-25
```

## ✅ Testing & Quality

- **ArchUnit:** Ensures architectural boundaries are respected.
- **Unit Tests:** Focus on Domain and Application layers.
- **Integration Tests:** Focus on Infrastructure adapters.

---
**Maintained by Nathan Fiorito**