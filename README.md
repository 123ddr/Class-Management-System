# 🎓 Class Management System

A modern and scalable **Class Management System** built with **Spring Boot**, **Firebase**, and **MySQL**, designed to streamline academic management processes such as course scheduling, department coordination, teacher assignments, and class administration.

---

## 🚀 Overview

The **Class Management System** provides a centralized backend for managing classes, departments, teachers, and students within an educational institution. It’s built using **Spring Boot 3**, adheres to clean architecture principles, and integrates **Firebase** for secure authentication and cloud connectivity.

This system exposes RESTful APIs that can be consumed by web or mobile frontends.

---

## 🧩 Key Features

- 🏫 Manage Departments, Courses, and Batches  
- 👩‍🏫 Assign Teachers and Coordinators  
- 📚 Link Courses to Teachers  
- 🧑‍🎓 Handle Batch Rosters (students)  
- 🕐 Create and manage Class Schedules  
- 🏠 Manage Classrooms with capacity & features  
- 🔒 Secure Firebase integration for authentication  
- 🧱 CRUD APIs for all entities  
- ⚡ Built with clean, layered architecture (Controller → Service → Repository → Entity)

---

## 🧱 Tech Stack

| Layer | Technology |
|--------|-------------|
| **Backend Framework** | Spring Boot 3 |
| **Database** | FireBase |
| **Cloud Integration** | Firebase Admin SDK |
| **ORM / Persistence** | Spring Data JPA + Hibernate |
| **Validation** | Jakarta Bean Validation |
| **Security (Optional)** | Spring Security + Firebase Auth |
| **Build Tool** | Maven |
| **Language** | Java 17+ |
| **API Documentation** | Swagger (Springdoc OpenAPI) |
| **Boilerplate Reduction** | Lombok |

---

## 📦 Dependencies

Below are the key dependencies defined in `pom.xml`:

```xml
<dependencies>
    <!-- Spring Boot Core -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- JPA + Hibernate -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- Validation -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>

    <!-- MySQL -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <scope>runtime</scope>
    </dependency>

    <!-- Firebase Admin SDK -->
    <dependency>
        <groupId>com.google.firebase</groupId>
        <artifactId>firebase-admin</artifactId>
        <version>9.2.0</version>
    </dependency>

    <!-- Lombok -->
    <dependency>
        <groupId>org.projectlombok</groupId>
        <artifactId>lombok</artifactId>
        <optional>true</optional>
    </dependency>

    <!-- Swagger / OpenAPI -->
    <dependency>
        <groupId>org.springdoc</groupId>
        <artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
        <version>2.5.0</version>
    </dependency>

    <!-- Testing -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>


## 🧩 System Architecture

