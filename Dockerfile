# Define a imagem base
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml file to the container
COPY pom.xml .

# Download the dependencies and cache them in the container
RUN mvn dependency:go-offline

# Copy the project source code to the container
COPY src/ ./src/

# Build the application inside the container
RUN mvn package -DskipTests

# Use a lightweight Java 11 image as the base image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file from the builder stage to the container
COPY --from=builder /app/target/hub-0.0.1-SNAPSHOT.jar .

# Expose the port that the application listens on
EXPOSE 8080

# Set the command to run the application
CMD ["java", "-jar", "hub-0.0.1-SNAPSHOT.jar"]

