# Food Ordering System

## Part 4: Project Investigation

### 1. What is Spring Boot?
Spring Boot is a framework that simplifies building Java applications by providing auto-configuration and embedded servers, eliminating the need for complex setup.

### 2. What is Maven?
Maven is a build automation tool that manages project dependencies, compilation, testing, and packaging of Java applications.

### 3. What is the purpose of pom.xml?
The pom.xml file defines the project's dependencies, plugins, and build configuration for Maven.

### 4. What is the purpose of application.properties?
It stores configuration settings for the application such as database connection details, server port, and other environment-specific properties.

### 5. What does @SpringBootApplication do?
It is a combination of three annotations (@Configuration, @EnableAutoConfiguration, @ComponentScan) that marks the main class and enables Spring Boot's auto-configuration.

### 6. Why do developers use dependency management tools such as Maven?
Maven automates downloading and managing libraries, ensures consistent builds, and handles version conflicts between dependencies.

### 7. What is a REST API?
A REST API is a web service that uses HTTP methods (GET, POST, PUT, DELETE) to allow communication between client and server in a stateless manner.

### 8. What is JSON?
JSON is a lightweight data format used to exchange data between a server and a client, written as key-value pairs.

### 9. What is Dependency Injection?
Dependency Injection is a design pattern where objects receive their dependencies from an external source rather than creating them internally, making code more modular and testable.

## Part 5: Package Structure

### controller
Handles the incoming HTTP requests and returns responses.It acts as the entry point for the REST API.

### service
It contains the business logic. The controller calls the service layer to process data.

### repository
It handles the database operations using Spring Data JPA.It communicates directly with the database.

### entity
It contains Java classes that map to database tables using JPA annotations.

### dto
Data Transfer Objects, used to shape data sent between the client and server without exposing entities directly.

### config
Contains configuration classes such as security settings, CORS configuration, and bean definitions.

### exception
Handles custom exceptions and global error handling for the application.