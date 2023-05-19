FROM openjdk:8-jdk-alpine

WORKDIR /usr/src/app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN sed -i 's/\r$//' mvnw
RUN chmod +x mvnw

RUN apk add --no-cache tzdata
ENV TZ=America/Bahia

COPY src ./src

CMD ["./mvnw", "spring-boot:run"]
