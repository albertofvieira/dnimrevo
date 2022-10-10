# Build
FROM maven:3.8.4-openjdk-17 AS build
COPY src /home/overmind-api/src
COPY pom.xml /home/overmind-api
RUN mvn -f /home/overmind-api/pom.xml clean package
#
# Package stage
#
FROM openjdk:17-alpine
COPY --from=build /home/overmind-api/target/overmind-api-0.0.1-SNAPSHOT.jar /usr/local/lib/overmind-api.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/overmind-api.jar"]