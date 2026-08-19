# EShopBackend

Modern **e-commerce backend** built with **Java 21** and **Spring Boot 3.3.2**.

The project demonstrates the development, containerization, CI/CD, and cloud deployment of a Spring Boot backend application.

The application provides two interfaces:

* **ConsoleController** – interactive console menu for local testing
* **REST API Controllers** – HTTP API for external clients

The backend manages users, products, shopping carts, and orders and uses **PostgreSQL** as its persistent database.

The application is containerized with **Docker**, the Docker image is stored in **Azure Container Registry (ACR)**, and the application runs on **Azure Container Apps (ACA)**.

---

## Features

### User Management

* User management
* `USER` and `ADMIN` roles
* Protected API endpoints
* Azure AD JWT authentication

### Product Management

* Product management
* Product descriptions
* Product prices
* Stock management
* Product categories

### Shopping Cart

* Cart creation
* Adding products to carts
* Removing cart items
* Updating quantities
* Cart retrieval
* Checkout functionality

### Order Management

* Creating orders from carts
* Order retrieval
* Order details
* Order status management

Supported order statuses:

```text
CREATED
PAID
SHIPPED
CANCELLED
```

---

# Technology Stack

| Category             | Technology               |
| -------------------- | ------------------------ |
| Language             | Java 21                  |
| Framework            | Spring Boot 3.3.2        |
| Web                  | Spring MVC / REST        |
| Persistence          | Spring Data JPA          |
| ORM                  | Hibernate                |
| Database             | PostgreSQL 16            |
| Database Migration   | Flyway                   |
| Security             | Spring Security          |
| Authentication       | OAuth2 Resource Server   |
| Identity Provider    | Microsoft Azure AD       |
| API Documentation    | Swagger / OpenAPI        |
| Build Tool           | Maven                    |
| Containerization     | Docker                   |
| Local Environment    | Docker Compose           |
| Container Registry   | Azure Container Registry |
| Cloud Runtime        | Azure Container Apps     |
| CI/CD                | GitHub Actions           |
| Kubernetes Packaging | Helm                     |
| Static Analysis      | SpotBugs                 |
| Test Coverage        | JaCoCo                   |
| Monitoring           | Spring Boot Actuator     |

---

# Application Architecture

The application follows a layered Spring Boot architecture:

```text
                 REST API
                    │
                    ▼
              Controllers
                    │
                    ▼
                Services
                    │
                    ▼
               Repositories
                    │
                    ▼
               PostgreSQL
```

Security is handled separately through Spring Security and Azure AD:

```text
Client
   │
   │ Bearer JWT
   ▼
Spring Security
   │
   ▼
Azure AD
   │
   ▼
Protected REST API
```

---

# ConsoleController

The application contains an interactive **ConsoleController** for local development and testing.

The console menu provides operations such as:

* Creating a new cart
* Listing carts
* Viewing cart details
* Listing orders
* Viewing order details
* Performing checkout
* Exiting the application

This provides a simple way to test the application's business logic without requiring an external REST client.

---

# REST API

The application also provides a REST API for external clients.

Main API areas include:

```text
/users
/products
/carts
/orders
```

The REST API uses the application's service and repository layers to process requests and persist data.

The main domain entities are:

```text
User
Product
Cart
CartItem
Order
OrderItem
```

---

# Database

The application uses **PostgreSQL** for persistent data storage.

The database contains the application's main e-commerce domain model:

```text
User
 │
 ├── Cart
 │     └── CartItem
 │           └── Product
 │
 └── Order
       └── OrderItem
             └── Product
```

JPA and Hibernate are used for object-relational mapping.

---

# Flyway Database Migrations

Database schema changes are managed with **Flyway**.

Migration files are stored in:

```text
src/main/resources/db/migration/
```

Example:

```text
V1__init.sql
V2__...
V3__...
```

Flyway automatically applies the required migrations when the application starts.

This makes the database schema reproducible across development and deployment environments.

---

# Security

The application uses **Spring Security** and **OAuth2 Resource Server**.

Authentication is based on **JWT access tokens issued by Microsoft Azure AD**.

Protected requests use:

```http
Authorization: Bearer <JWT_TOKEN>
```

The application uses stateless authentication.

Public endpoints include:

```text
/actuator/**
/swagger-ui/**
/v3/api-docs/**
```

Other protected API endpoints require a valid JWT token.

---

# Docker

The application is packaged as a Docker image.

The general build process is:

```text
Maven
  ↓
Spring Boot JAR
  ↓
Docker Image
```

Build the application:

```bash
mvn clean package
```

Build the Docker image:

```bash
docker build -t eshopbackend .
```

Run the container locally:

```bash
docker run -p 8080:8080 eshopbackend
```

---

# Docker Compose

Docker Compose can be used for local development.

Start the environment:

```bash
docker-compose up -d
```

The local environment can contain:

```text
Spring Boot Application
        │
        ▼
    PostgreSQL
```

The application is available at:

```text
http://localhost:8080
```

PostgreSQL:

```text
localhost:5432
```

---

# Azure Deployment

The application uses a container-based deployment architecture on Microsoft Azure.

## Azure Container Registry

The Docker image is stored in **Azure Container Registry (ACR)**.

The image is built and pushed by GitHub Actions.

Example image:

```text
<ACR_LOGIN_SERVER>/eshopbackend:latest
```

ACR acts as the private container registry for the application.

---

## Azure Container Apps

The application runs on **Azure Container Apps (ACA)**.

The deployment flow is:

```text
GitHub Repository
        │
        ▼
GitHub Actions
        │
        ▼
Maven Build
        │
        ▼
Docker Build
        │
        ▼
Azure Container Registry
        │
        │ Docker Image
        ▼
Azure Container Apps
        │
        ▼
Spring Boot Application
```

Azure Container Apps provides the managed container runtime and HTTP ingress for the application.

The application therefore runs in Azure as a containerized Spring Boot service.

---

# CI/CD

The project uses **GitHub Actions** for continuous integration and Docker image publishing.

The current workflow performs the following steps:

```text
Git Push
    ↓
Checkout Repository
    ↓
Set up JDK 21
    ↓
Maven Package
    ↓
Login to Azure Container Registry
    ↓
Docker Build
    ↓
Push Docker Image to ACR
```

The workflow is triggered by pushes to:

```text
master
main
```

The Maven build currently uses:

```bash
mvn clean package -DskipTests
```

The Docker image is published as:

```text
<ACR_LOGIN_SERVER>/eshopbackend:latest
```

Azure Container Apps then runs the image stored in ACR.

---

# GitHub Actions

The CI/CD workflow uses:

* `actions/checkout@v4`
* `actions/setup-java@v4`
* Java 21 Temurin
* Maven
* `azure/docker-login@v2`
* Docker

The workflow authenticates with Azure Container Registry using GitHub Secrets.

Registry credentials are never stored directly in the repository.

---

# Helm / Kubernetes

The project also contains a **Helm chart** for Kubernetes deployment.

The chart provides Kubernetes deployment configuration and can be used for Kubernetes-based environments.

Example structure:

```text
helm/
└── eshop-chart/
    ├── Chart.yaml
    ├── values.yaml
    └── templates/
```

The current Azure deployment, however, uses **Azure Container Apps** rather than a self-managed Kubernetes cluster.

---

# Monitoring

The application uses **Spring Boot Actuator** for application health monitoring.

Health endpoint:

```text
/actuator/health
```

Example:

```bash
curl http://localhost:8080/actuator/health
```

The health endpoint can also be used by container platforms to determine whether the application is running correctly.

---

# Code Quality

The project uses **SpotBugs** for static code analysis.

Run SpotBugs with:

```bash
mvn spotbugs:spotbugs
```

SpotBugs is also configured in the Maven build lifecycle.

---

# Test Coverage

The project uses **JaCoCo** for test coverage reporting.

Run the Maven verification lifecycle:

```bash
mvn clean verify
```

JaCoCo generates a coverage report based on the executed tests.

---

# Configuration

Database and Azure configuration is externalized using environment variables.

Examples:

```text
SPRING_DATASOURCE_USERNAME
SPRING_DATASOURCE_PASSWORD
SPRING_DATASOURCE_URL
AZURE_TENANT_ID
AZURE_CLIENT_ID
```

This allows different configuration to be used for local development and Azure deployment without changing the application code.

---

# Secrets

Sensitive credentials are stored outside the source code.

GitHub Actions uses repository secrets such as:

```text
POSTGRES_USER
POSTGRES_PASSWORD

AZURE_CLIENT_ID
AZURE_TENANT_ID

DOCKERHUB_USERNAME
DOCKERHUB_TOKEN

REGISTRY_LOGIN_SERVER
REGISTRY_USERNAME
REGISTRY_PASSWORD
```

Passwords, access tokens, client secrets, and registry credentials must not be committed to Git.

---

# Local Development

## Requirements

* Java 21
* Maven
* Docker
* Docker Compose
* PostgreSQL
* Git

Recommended development environment:

* IntelliJ IDEA
* Postman
* Docker Desktop

---

## Start with Docker Compose

```bash
docker-compose up -d
```

Check application health:

```bash
curl http://localhost:8080/actuator/health
```

---

## Start with Maven

```bash
mvn clean spring-boot:run
```

The application starts on:

```text
http://localhost:8080
```

---

# Useful Commands

### Build

```bash
mvn clean package
```

### Run tests

```bash
mvn test
```

### Full verification

```bash
mvn clean verify
```

### SpotBugs

```bash
mvn spotbugs:spotbugs
```

### Build Docker image

```bash
docker build -t eshopbackend .
```

### Start Docker Compose

```bash
docker-compose up -d
```

### Stop Docker Compose

```bash
docker-compose down
```

### Health check

```bash
curl http://localhost:8080/actuator/health
```

---

# Project Goals

This project demonstrates practical backend and DevOps skills:

* Java 21
* Spring Boot
* REST API development
* Layered architecture
* Spring Data JPA
* Hibernate
* PostgreSQL
* Flyway
* Spring Security
* OAuth2 Resource Server
* Azure AD authentication
* Docker
* Docker Compose
* GitHub Actions
* Azure Container Registry
* Azure Container Apps
* Helm
* Kubernetes concepts
* Static code analysis
* Test coverage
* Application monitoring

The project demonstrates the complete lifecycle of a containerized backend application:

```text
Development
     ↓
Maven Build
     ↓
Docker Image
     ↓
GitHub Actions
     ↓
Azure Container Registry
     ↓
Azure Container Apps
     ↓
Running Spring Boot Application
```
