# 📱 Devices API

A RESTful API for managing devices, built with Java 21 and Spring Boot 3.2.  
This project was created as a backend challenge and demonstrates clean architecture, domain-driven validation, DTO mapping with MapStruct, and containerized deployment with Docker.

---

## 🚀 Tech Stack

- **Java 21**
- **Spring Boot 3.2**
- **Spring Data JPA**
- **MySQL / H2 (dev)**
- **MapStruct** – for DTO mapping
- **Lombok** – for boilerplate-free code
- **SpringDoc OpenAPI** – for Swagger UI
- **Docker / Docker Compose** – for local development

---

## 🧩 Features

- ✅ Create, update (full & partial), and delete devices
- ✅ Filter by brand or state
- ✅ Validation rules:
  - `creationTime` is immutable
  - `name` and `brand` are immutable when the device is `IN_USE`
  - Devices in use cannot be deleted
- ✅ Custom error handling with `@RestControllerAdvice`
- ✅ Swagger UI for easy testing
- ✅ Clean separation using DTOs + MapStruct

---

## 🔧 Getting Started

### 1. Clone the repo

```bash
git clone https://github.com/your-username/devices-api.git
cd devices-api
```

### 2. Start MySQL with Docker (optional)

```bash
docker-compose up -d
```

> The DB is accessible at `localhost:3306`, with user/pass `user:pass`.

### 3. Run the application

```bash
mvn spring-boot:run
```

Or run from your IDE using Java 21.

---

## ⚙️ Configuration

**`src/main/resources/application.yml`**

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/devices_db
    username: user
    password: pass

  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
```

---

## 📚 API Documentation

Once running, access Swagger UI at:

👉 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

---

## 🧪 Tests

Run all tests with:

```bash
mvn test
```

Includes:
- Unit tests for service logic and validation
- Integration tests for controllers (coming soon)

---

## 🐳 Docker

To build and run the app in a container:

```bash
docker build -t devices-api .
docker run -p 8080:8080 devices-api
```

Or use Docker Compose for DB + App together (see `docker-compose.yml`).

---

## 📁 Folder Structure

```bash
src/
├── controller       # REST Controllers
├── dto             # Request & Response DTOs
├── model           # Entity (JPA)
├── repository      # Spring Data interfaces
├── service         # Business logic
├── exception       # Custom error handlers
```

---

## 📌 Notes

- This project follows best practices for maintainability and scalability
- Suggestions for improvements are welcome!
- Prepared for production-readiness: can easily extend to include logging, metrics, etc.

---

## 🧑‍💻 Author

Bruno – [LinkedIn](https://linkedin.com/in/yourprofile)

---

## 📄 License

This project is for evaluation and educational purposes.