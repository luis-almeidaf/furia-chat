# Etapa de build
FROM maven:3.9.6-eclipse-temurin-21-alpine as build

WORKDIR /app

COPY . .

RUN mvn clean install -DskipTests

# Etapa de execução
FROM eclipse-temurin:21-jdk-jammy

WORKDIR /app

EXPOSE 8080

COPY --from=build /app/target/furia-chat-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
