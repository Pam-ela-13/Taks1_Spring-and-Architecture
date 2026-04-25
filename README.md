# Book Management API - Task1

**Author:** Pamela Tapia

---

## Overview

This is a **Spring Boot 4.0.6** application that demonstrates advanced programming concepts through a Book Management REST API. The project showcases essential features such as dependency injection, exception handling, interceptors, and notification systems.

---

## Project Description

The application provides a complete REST API for managing a library of books. Users can:
- Retrieve all books or search by ID
- Create, update, and delete books
- Handle custom exceptions gracefully
- Track requests through logging and API key validation
- Send notifications (Email and SMS)

---

## Technology Stack

- **Java Version:** 21
- **Framework:** Spring Boot 4.0.6
- **Build Tool:** Maven
- **Database:** H2 (In-memory)
- **API Type:** RESTful Web Services

---

## Project Structure & Key Components

### 📚 **Book Management** (`src/main/java/com/example/Task1/book/`)
- **BookModel.java** - Represents a book entity with properties (ID, title, author, ISBN, pages, genre)
- **BookService.java** - Core business logic for book operations (CRUD operations, data management)
- **BookNotFoundException.java** - Exception thrown when a book is not found
- **BookNotAvailableException.java** - Exception thrown when a book is not available
- **InvalidIsbnException.java** - Exception thrown for invalid ISBN format

### 🎮 **REST Controller** (`src/main/java/com/example/Task1/controller/`)
- **BookController.java** - REST endpoints for managing books
  - `GET /api/libros` - Retrieve all books
  - `GET /api/libros/{id}` - Get book by ID
  - `POST /api/libros` - Create a new book
  - `PUT /api/libros/{id}` - Update a book
  - `DELETE /api/libros/{id}` - Delete a book

### ⚙️ **Configuration** (`src/main/java/com/example/Task1/config/`)
- **WebConfig.java** - Spring Web configuration, interceptor registration

### 🚨 **Exception Handling** (`src/main/java/com/example/Task1/exception/`)
- **GlobalExceptionHandler.java** - Centralized exception handling for the entire application
- **ErrorResponse.java** - Structured error response format
- **ApiKeyInvalidaException.java** - Exception for invalid API keys
- **TareaNoEncontradaException.java** - Custom task not found exception

### 🔍 **Interceptors** (`src/main/java/com/example/Task1/interceptor/`)
- **ApiKeyInterceptor.java** - Validates API keys for incoming requests
- **LoggingInterceptor.java** - Logs HTTP requests and responses for debugging

### 📬 **Notification System** (`src/main/java/com/example/Task1/notification/`)
- **NotificationInterface.java** - Interface defining notification behavior
- **EmailSenderNotificationImpl.java** - Implementation for sending email notifications
- **SmsSenderNotificationImpl.java** - Implementation for sending SMS notifications

### 🏠 **Main Application** (`src/main/java/com/example/Task1/`)
- **Task1Application.java** - Spring Boot application entry point

---

## Running the Application

```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run

# Access the H2 Database Console
http://localhost:8090/h2-console
```

---

## Key Concepts Demonstrated

✅ **Dependency Injection** - Constructor-based dependency injection in controllers  
✅ **Service Layer Pattern** - Business logic separation  
✅ **Exception Handling** - Global exception handling with custom exceptions  
✅ **Interceptors** - Request/response interception for cross-cutting concerns  
✅ **REST API Design** - Proper HTTP methods and response codes  
✅ **Design Patterns** - Factory pattern in notification system  

---

## Properties Files

- **application.properties** - Default application configuration
- **application-dev.properties** - Development-specific configuration

---

**Project Status:** ✅ Active Development

