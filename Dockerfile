FROM maven:3.8.4 AS maven
WORKDIR /usr/src/app
COPY . /usr/src/app
# Compile and package the application to an executable JAR
RUN mvn package



FROM openjdk:17-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ARG JAR_FILE=sin.jar
WORKDIR /opt/app
COPY --from=maven --chown=spring:spring /usr/src/app/target/${JAR_FILE} /opt/app/

ENTRYPOINT ["java","-jar","sin.jar"]