# Load Booking System

A Spring Boot application for managing loads and bookings in a logistics system, using PostgreSQL and Spring Data JPA. Supports load posting, transporter bookings, and status transitions based on business rules.

---

## Technologies Used

- Java 17+
- Spring Boot 3+
- Spring Data JPA
- PostgreSQL
- Swagger / OpenAPI 3
- Gradle

---

## API Overview

### Load Management

| Method | Endpoint             | Description                 |
|--------|----------------------|-----------------------------|
| POST   | `/load`              | Create a new load           |
| GET    | `/load`              | Filter loads by shipperId, truckType, status, page, size |
| GET    | `/load/{loadId}`     | Get load details by ID      |
| PUT    | `/load/{loadId}`     | Update load details         |
| DELETE | `/load/{loadId}`     | Delete a load               |

#### Rules:
- New loads default to status: `POSTED`
- When a booking is made → `status` changes to `BOOKED`
- If all bookings are deleted → `status` reverts to `POSTED`
- If any booking exists but is deleted → `status` becomes `CANCELLED`

---

### Booking Management

| Method | Endpoint                 | Description                     |
|--------|--------------------------|---------------------------------|
| POST   | `/booking`               | Create a booking for a load     |
| GET    | `/booking`               | Filter by loadId, transporterId, status |
| GET    | `/booking/{bookingId}`   | Get booking details             |
| PUT    | `/booking/{bookingId}`   | Update a booking                |
| DELETE | `/booking/{bookingId}`   | Delete a booking                |

#### Rules:
- Booking not allowed if load is `CANCELLED`
- Accepted bookings → `status = ACCEPTED`
- Rejected bookings → `status = REJECTED`
- If all bookings are deleted or rejected → Load `status = POSTED`

---

## Entities

### LoadEntity
```json
{
  "id": "UUID",
  "shipperId": "String",
  "facility": {
    "loadingPoint": "String",
    "unloadingPoint": "String",
    "loadingDate": "Timestamp",
    "unloadingDate": "Timestamp"
  },
  "productType": "String",
  "truckType": "String",
  "noOfTrucks": "int",
  "weight": "double",
  "comment": "String",
  "datePosted": "Timestamp",
  "status": "POSTED | BOOKED | CANCELLED"
}
```

###  BookingEntity
```json
{
  "id": "UUID",
  "loadId": "UUID",
  "transporterId": "String",
  "proposedRate": "double",
  "comment": "String",
  "status": "PENDING | ACCEPTED | REJECTED",
  "requestedAt": "Timestamp"
}
```

##  Project Structure
<img width="376" height="285" alt="image" src="https://github.com/user-attachments/assets/55406863-c1ca-4e51-bdeb-bec36904eebb" />

## Exception Handling
All exceptions are handled using @ControllerAdvice via:
```bash 
com.loadbookingsystem.exception.GlobalExceptionHandler
```

## API Documentation
Swagger UI is available at:
```bash
http://localhost:8080/swagger-ui/index.html
```
## Running Locally
### Prerequisites
1. Java 17+
2. PostgreSQL running locally
3. Gradle

### Configuration
Update your application.properties with refernce to sample application.properties

Yay, then run your application!

### Author:
Aayushi
