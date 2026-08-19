# EShopBackend

Modern **e-commerce backend** built with **Java 21** and **Spring Boot 3.3.2**.

The application provides both an interactive **ConsoleController** and a **REST API** for managing users, products, shopping carts, and orders.

The application is containerized with Docker and deployed to **Microsoft Azure** using **Azure Container Registry (ACR)** and **Azure Container Apps (ACA)**.

## Tech Stack

* Java 21
* Spring Boot 3.3.2
* Spring Data JPA + Hibernate
* PostgreSQL 16
* Flyway database migrations
* Spring Security
* OAuth2 Resource Server + Azure AD
* REST API + ConsoleController
* Docker + Docker Compose
* Azure Container Registry (ACR)
* Azure Container Apps (ACA)
* GitHub Actions CI/CD
* Helm / Kubernetes
* SpotBugs + JaCoCo
* Spring Boot Actuator

## Main Features

* User management with `USER` / `ADMIN` roles
* Product and stock management
* Shopping carts and cart items
* Order creation and checkout
* Order status management
* PostgreSQL persistence with Flyway migrations
* JWT authentication using Azure AD
* Interactive console menu for local testing
* REST API for external clients
* Application health monitoring with Spring Actuator

## Architecture

```text
REST API / Console
        │
        ▼
  Spring Boot
        │
   ┌────┴────┐
   ▼         ▼
Services   Security
   │       Azure AD
   ▼
Repositories
   │
   ▼
PostgreSQL
```

## Azure Deployment

The application is deployed as a Docker container:

```text
GitHub
   │
   ▼
GitHub Actions
   │
   ▼
Docker Image
   │
   ▼
Azure Container Registry (ACR)
   │
   ▼
Azure Container Apps (ACA)
   │
   ▼
Spring Boot Application
```

**Azure Container Registry** stores the Docker image, while **Azure Container Apps** runs the containerized Spring Boot application.

## Quick Start

### Docker Compose

```bash
docker-compose up -d
```

Application:

`http://localhost:8080`

Health:

`http://localhost:8080/actuator/health`


### Maven

```bash
mvn clean spring-boot:run
```

## Database

The application uses **PostgreSQL** with **Flyway** for database schema migrations.

Migration files:

```text
src/main/resources/db/migration/
```

## Security

* Stateless authentication
* OAuth2 Resource Server
* Azure AD JWT authentication
* CSRF disabled for the API
* Protected API endpoints require a valid JWT token

## CI/CD

GitHub Actions automatically:

```text
Build JAR
   ↓
Docker Build
   ↓
Login to ACR
   ↓
Push Image to ACR
```

The Docker image is published to:

```text
<ACR_LOGIN_SERVER>/eshopbackend:latest
```

Azure Container Apps uses the image stored in ACR to run the application.

The workflow is triggered by pushes to:

* `master`
* `main`

## Secrets

Sensitive values are stored as environment variables or GitHub Secrets:

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

**No passwords, tokens, or other credentials are committed to the repository.**
