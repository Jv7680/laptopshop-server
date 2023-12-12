#
# Build stage
#
# FROM maven:3.8.6-eclipse-temurin-17-alpine AS build
# COPY src /home/app/src
# COPY pom.xml /home/app
# RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
# FROM openjdk:17-jdk-slim
# COPY --from=build /home/app/target/laptopshop-0.0.1-SNAPSHOT.jar /usr/local/lib/demo.jar
# EXPOSE 8080
# ENTRYPOINT ["java","-jar","/usr/local/lib/demo.jar"]

# FROM openjdk:17-jdk-slim
# ADD ./docker-spring-boot.jar docker-spring-boot.jar
# ENTRYPOINT ["java","-jar","docker-spring-boot.jar"]

FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build /target/laptopshop-0.0.1-SNAPSHOT.jar laptopshop.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","laptopshop.jar"]