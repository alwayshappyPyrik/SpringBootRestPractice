FROM openjdk:17-oracle
LABEL authors="Yaroslav"

EXPOSE 8080
COPY target/SpringBootRestPractice-0.0.1-SNAPSHOT.jar authorizationservice.jar
CMD ["java", "-jar", "authorizationservice.jar"]
