FORM maven:3.9.6-eclipse-temurin-21 AS build
COPY  .  .
RUN mvn  clean package -DskipTests

FORM openjdk:21-jdk-slim
COPY --from=build /target/hrms-0.0.1-SNAPSHOT.jar hrms.jar
EXPOSE 8080
ENTRYPOINT [ "java"."-jar", "hrms.jar" ]