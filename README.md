# 🚛 Load Booking System

A Spring Boot-based RESTful application to manage load and booking operations with role-based access control for `USER` and `ADMIN` roles.

---

## 📦 Features

- ✅ User Registration & Login
- ✅ Role-based Authorization (`USER`, `ADMIN`)
- ✅ Load Management (USER)
- ✅ Booking Management (ADMIN)
- ✅ Secure password encryption using BCrypt
- ✅ Clean REST APIs

---

## 🛠️ Setup Instructions

### ✅ Prerequisites

- Java 17+
- Maven
- Git
- PostgreSQL

---
⚙️ Configure the Application
The application uses PostgreSQL as the database.

🧩 PostgreSQL Configuration
In src/main/resources/application.properties, update the following:

### 📁 Clone the Repository

```bash
git clone https://github.com/your-username/load-booking.git
cd load-booking
```
⚙️ Configure the Application
The application uses PostgreSQL as the database.

🧩 PostgreSQL Configuration
In src/main/resources/application.properties, update the following:
```bash
# PostgreSQL Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/load_booking
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

▶️ Run the Application
```bash
mvn spring-boot:run

```
The application will be running at:

📍 http://localhost:8080
## 🔐 Authentication & Authorization

| Endpoint                       | Method | Access Role          |
|-------------------------------|--------|-----------------------|
| `/register`                   | POST   | Public                |
| `/load/`                       | GET    | USER                  |
| `/booking/{bookingId}/accept`| POST   | USER                  |
| `/booking/`                 | Any    | ADMIN                 |
| Other routes                  | Any    | Authenticated Users   |


👤 User JSON Data


🔸 Register a New User

POST /register
```bash
{
  "userName": "john_doe",
  "email": "john.doe@example.com",
  "password": "password123",
  "role": "USER"
}
```
🧑 Example Admin User:
```bash
{
  "userName": "admin_master",
  "email": "admin@example.com",
  "password": "adminpass",
  "role": "ADMIN"
}
```

Load Management APIs
1. POST /load → Create a new load
```bash
   {
  "shipperId": 101,
  "truckType": "Flatbed",
  "origin": "New York",
  "destination": "Los Angeles",
  "weight": 10000,
  "dimensions": {
    "length": 10,
    "width": 5,
    "height": 3
  },
  "price": 1200.50
}
```
2. GET /load → Fetch loads (filter by shipperId, truckType)
```bash
GET /load?shipperId=101&truckType=Flatbed
```
3. GET /load/{loadId}
```bash
GET /load/{loadId}
```
4. PUT /load/{loadId}
```bash
{
  "truckType": "Refrigerated",
  "origin": "New York",
  "destination": "San Francisco",
  "weight": 9500,
  "dimensions": {
    "length": 9,
    "width": 5,
    "height": 3
  },
  "price": 1500.75
}
```
5. DELETE /load/{loadId}
```bash
DELETE /load/{loadId}
```
Booking Management APIs
1. POST /booking
```bash
{
  "load": {
        "id": "loadId"
  },
  "transporterId": "TRANS789",
  "proposedRate": 25000,
  "comment": "Can pick up early",
  "requestedAt": "2025-04-14T12:00:00Z"
}
```
2. GET /booking → Fetch bookings (filter by shipperId, transporterId)
```bash
GET /booking?shipperId=101&transporterId=201
```
3. GET /booking/{bookingId}
```bash
GET /booking/{bookingId}
 ```
4. PUT /booking/{bookingId}

5.DELETE /booking/{bookingId}
```bash
DELETE /booking/{bookingId}
```

