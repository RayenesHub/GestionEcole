FROM openjdk:17
# Le port doit correspondre à celui défini dans application.properties (ex: 8020 ou 8045)
EXPOSE 8045
ADD target/Student-0.0.1-SNAPSHOT.jar student.jar
ENTRYPOINT ["java", "-jar", "student.jar"]
