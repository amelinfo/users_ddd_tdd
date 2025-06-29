# 🧪 Technical Test – User Management API in Java Spring Boot

## 📋 Description

This Spring Boot application provides a RESTful API for managing users.  
It follows a **Hexagonal Architecture (Ports and Adapters)**, applying best practices of **DDD** and **TDD**.

---

## ✅ Features

### Exercise 1: List all users
- `GET /users` → Returns the list of registered users.

### Exercise 2: Create a new user
- `POST /users` → Creates a new user, if the email is not already used.
- Email format is validated.
- Email uniqueness is enforced.

### Exercise 3: Activate a user
- `PATCH /users/{id}/activate` → Activates an existing user.
- Activation logic is separated within the domain service.

---

## 🧱 Architecture Overview

- `domain/model` → Domain entities (e.g., `User`)
- `domain/port/in` → Input ports (use cases)
- `domain/port/out` → Output ports (persistence interfaces)
- `application/service` → Core business logic
- `infra/persistence` → Data access layer (JPA/H2)
- `rest` → REST API controllers
- `exception` → Centralized exception handling
- `mapper` → Conversion between domain models and entities

---

### 🧭 Why Hexagonal Architecture?

> While a traditional **MVC (Model-View-Controller)** structure would be simpler and faster for a small application like this,  
> this project intentionally follows a **Hexagonal Architecture** to comply with the technical test requirements.  
>  
> This approach provides:
> - Better **separation of concerns**
> - High **testability**
> - Flexibility for scaling or swapping infrastructure (e.g., replacing the DB or UI)
>  
> It also aligns with **Domain-Driven Design (DDD)** principles.

---

## 🧪 Testing Strategy

- **Unit tests** for services, mappers, and ports
- **Integration tests** using `@SpringBootTest` and `MockMvc`
- Covers:
  - User creation with unique email
  - Email format validation
  - Handling duplicates
  - Activating users
  - HTTP error handling: `400`, `404`, `409`

Tests were developed using the **TDD** cycle: Red → Green → Refactor.

---

## 🛡️ Validation

- Input validation via `@NotBlank`, `@Email`, and `@Valid`
- Errors are handled by a global exception handler (`@RestControllerAdvice`)

Example error response:

```json
{
  "error": "Email already used: joan@mail.com"
}

![screencapture-localhost-8080-swagger-ui-index-html-2025-06-29-17_56_43](https://github.com/user-attachments/assets/273df058-8aa4-4fef-8dbd-2669e1f17541)



![question 1 postman](https://github.com/user-attachments/assets/12903ee3-0bce-4931-947e-26b3c8ea5a93)



![question 2 2](https://github.com/user-attachments/assets/344510b2-e544-42a1-a593-cabe28f5750d)



