# Stage 1: Build the project
FROM maven:3.8.4-openjdk-17-slim 

WORKDIR /usr/src/app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN sed -i 's/\r$//' mvnw
RUN chmod +x mvnw


COPY src ./src

CMD ["./mvnw", "spring-boot:run"]

