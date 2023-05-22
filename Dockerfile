FROM FROM maven:3.6.0-jdk-11-slim 

WORKDIR /usr/src/app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN sed -i 's/\r$//' mvnw
RUN chmod +x mvnw

RUN apk add --no-cache tzdata
ENV TZ=America/Bahia

COPY src ./src

CMD ["./mvnw", "spring-boot:run"]

##
# Build stage
#
##FROM maven:3.6.0-jdk-11-slim AS build
##COPY src /home/app/src
##COPY pom.xml /home/app
##RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
##FROM openjdk:11-jre-slim
##COPY --from=build /home/app/target/getyourway-0.0.1-SNAPSHOT.jar /usr/local/lib/demo.jar
##EXPOSE 8080
##ENTRYPOINT ["java","-jar","/usr/local/lib/demo.jar"]
