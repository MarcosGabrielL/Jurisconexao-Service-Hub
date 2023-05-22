# Stage 1: Build the project
FROM maven:3.8.4-openjdk-17-slim AS builder

WORKDIR /app

# Copy the pom.xml file
COPY pom.xml .

# Download the project dependencies
RUN mvn dependency:go-offline

# Copy the source code
COPY src/ ./src/

# Build the project
RUN mvn package -DskipTests

# Stage 2: Create the final container
FROM openjdk:17-jdk-slim


WORKDIR /app

# Copy the built JAR file from the builder stage to the container
COPY --from=builder /app/target/hub-0.0.1-SNAPSHOT.jar .

# Expose the port that the application listens on
EXPOSE 8080

# Define the command to run the application
CMD ["java", "-jar", "hub-0.0.1-SNAPSHOT.jar"]
