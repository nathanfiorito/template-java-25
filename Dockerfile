# Build Stage
FROM maven:3.9-eclipse-temurin-25 AS build
WORKDIR /app
COPY app/pom.xml .
COPY app/src ./src
RUN mvn clean package -DskipTests

# Run Stage
FROM eclipse-temurin:25-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Create a non-root user
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
