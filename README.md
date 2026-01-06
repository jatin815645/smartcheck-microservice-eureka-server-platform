# 🚀 SmartCheck Microservices System

This repository contains a **Spring Boot microservices-based backend system** built using **Spring Cloud Netflix Eureka** and **Spring Cloud API Gateway**.

The system follows a **clean microservices architecture** where:

* Services are **loosely coupled**
* Communication happens via **service discovery**
* External clients interact **only through API Gateway**

---

## 🧩 Microservices Overview

The system consists of **5 services**:

| Service Name         | Type             | Description                                |
| -------------------- | ---------------- | ------------------------------------------ |
| **Eureka Server**    | Discovery Server | Registers and discovers all microservices  |
| **API Gateway**      | Gateway Service  | Single entry point for all client requests |
| **Auth Service**     | Microservice     | Authentication, registration, JWT, roles   |
| **Event Service**    | Microservice     | Event management                           |
| **Attendee Service** | Microservice     | Attendee management                        |

---

## 🏗️ Architecture Diagram (Conceptual)

```
                 ┌────────────────────┐
                 │   Eureka Server    │
                 │  (Service Registry)│
                 └─────────▲──────────┘
                           │
                  ┌────────┴────────┐
                  │   API Gateway   │
                  │ (Single Entry)  │
                  └───────▲─────────┘
                          │
        ┌─────────────────┼─────────────────┐
        │                 │                 │
┌────────────┐     ┌────────────┐     ┌────────────┐
│ Auth       │     │ Event      │     │ Attendee   │
│ Service    │◀──▶ │ Service    │◀──▶ │ Service    │
└────────────┘     └────────────┘     └────────────┘
```

✔ Clients never call services directly
✔ All requests go through **API Gateway**
✔ Gateway resolves services using **Eureka**

---

## 🧠 Key Concepts Used

* Spring Boot
* Spring Cloud Netflix Eureka
* Spring Cloud Gateway
* REST APIs
* JWT Authentication
* Role-Based Authorization
* Microservices Architecture
* Maven
* application.properties configuration

---

## 📌 Service Responsibilities

### 🌐 Eureka Server

* Acts as a **service registry**
* All microservices register here
* Helps in dynamic service discovery

**Dashboard**

```
http://localhost:8761
```

---

### 🚪 API Gateway

* Single entry point for all APIs
* Routes requests to correct microservice
* Integrates with Eureka
* Can handle:

  * Authentication filters
  * Logging
  * Rate limiting (future)

**Example Routes**

```
/auth/**       → Auth Service
/events/**     → Event Service
/attendees/**  → Attendee Service
```

---

### 🔐 Auth Service

* User registration
* User login
* JWT token generation
* Role-based access (ADMIN / USER)
* Token validation support

**Base Path**

```
/auth/api
```

---

### 📅 Event Service

* Create events
* Fetch event details
* Update and delete events
* Communicates with Attendee Service

**Base Path**

```
/events/api
```

---

### 🎟️ Attendee Service

* Register attendees for events
* Fetch attendee list
* Manage attendee status

**Base Path**

```
/attendees/api
```

---

## ⚙️ Technology Stack

| Technology           | Usage                |
| -------------------- | -------------------- |
| Java 17              | Programming language |
| Spring Boot          | Core framework       |
| Spring Cloud Eureka  | Service discovery    |
| Spring Cloud Gateway | API Gateway          |
| Maven                | Build tool           |
| MySQL / H2           | Database             |
| JWT                  | Authentication       |

---

## ⚙️ Configuration Details

### Eureka Server (`application.properties`)

```properties
server.port=8761
spring.application.name=eureka-server

eureka.client.register-with-eureka=false
eureka.client.fetch-registry=false
```

---

### API Gateway (`application.properties`)

```properties
server.port=8080
spring.application.name=api-gateway

eureka.client.service-url.defaultZone=http://localhost:8761/eureka
spring.cloud.gateway.discovery.locator.enabled=true
spring.cloud.gateway.discovery.locator.lower-case-service-id=true
```

---

### Microservice Configuration (Example: Auth Service)

```properties
server.port=8081
spring.application.name=auth-service

eureka.client.service-url.defaultZone=http://localhost:8761/eureka
```

✔ Same configuration pattern for Event & Attendee services
✔ Each service has:

* Unique port
* Unique service name

---

## 📦 Required Dependencies

### For Eureka Client (All Services + Gateway)

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
```

### Spring Cloud BOM (IMPORTANT)

```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-dependencies</artifactId>
            <version>2023.0.1</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```

---

## ▶️ How to Run the Project

### Step 1️⃣ Start Eureka Server

```bash
mvn spring-boot:run
```

Verify:

```
http://localhost:8761
```

---

### Step 2️⃣ Start Services

Start in any order:

* Auth Service
* Event Service
* Attendee Service
* API Gateway

All services will **auto-register** in Eureka.

---

## 🔍 Verify Services in Eureka

Open:

```
http://localhost:8761
```

You should see:

* AUTH-SERVICE
* EVENT-SERVICE
* ATTENDEE-SERVICE
* API-GATEWAY

---

## 🔄 API Flow Example

**Client Request**

```
POST http://localhost:8080/auth/api/register
```

Flow:

```
Client → API Gateway → Auth Service → Response
```

✔ No hardcoded URLs
✔ Gateway resolves service via Eureka

---

## 🔐 Security Notes

* JWT is generated by Auth Service
* API Gateway can validate JWT (future enhancement)
* 403 Forbidden means:

  * Token missing
  * Role mismatch
  * Security config blocking endpoint

---

## 🚀 Future Enhancements

* JWT validation filter in API Gateway
* Centralized Config Server
* Circuit Breaker (Resilience4j)
* Docker & Kubernetes
* Centralized Logging

---

## 👨‍💻 Author

**Jitendra Patil**
Backend & Android Developer
Learning Spring Boot Microservices 🚀
