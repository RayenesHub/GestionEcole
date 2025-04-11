FROM openjdk:17
EXPOSE 8761
ADD target/eurekaServer-0.0.1-SNAPSHOT.jar eurekaServer.jar
ENTRYPOINT ["java", "-jar", "eurekaServer.jar"]