# Changelog

All notable changes to this project will be documented in this file.

## [1.0.0] - initial log

### Added

* Initial release of **EShopBackend**
* Java 21 + Spring Boot 3.3.2
* REST API for Users, Products, Carts and Orders
* Interactive `ConsoleController`
* PostgreSQL + Spring Data JPA + Hibernate
* Flyway database migrations
* Spring Security + OAuth2 Resource Server
* Azure AD JWT authentication
* Spring Boot Actuator health monitoring
* Docker + Docker Compose support
* GitHub Actions CI/CD pipeline
* Docker image publishing to **Azure Container Registry (ACR)**
* Deployment to **Azure Container Apps (ACA)**
* Helm chart for Kubernetes
* SpotBugs + JaCoCo
* Environment-based configuration for application secrets

### Deployment

```text id="z8x7u2"
GitHub
  ↓
GitHub Actions
  ↓
Docker Image
  ↓
Azure Container Registry (ACR)
  ↓
Azure Container Apps (ACA)
  ↓
Spring Boot Application
```

### Version

**1.0.0 – Initial Release**
