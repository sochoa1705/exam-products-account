FROM eclipse-temurin:17-jdk-focal
LABEL authors="SAOM"

EXPOSE 8081
COPY target/*.jar productsaccount-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/productsaccount-0.0.1-SNAPSHOT.jar