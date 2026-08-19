FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:resolve

COPY src ./src

RUN mvn -B -DskipTests package

FROM eclipse-temurin:21-jre AS runtime

WORKDIR /app

RUN useradd -m appuser

COPY --from=build /app/target/*.jar app.jar

USER appuser

EXPOSE 8080

HEALTHCHECK --interval=30s --timeout=3s --start-period=10s --retries=3 \
  CMD curl -f http://localhost:8080/actuator/health || exit 1

ENTRYPOINT  ["java", "-jar", "app.jar"]
