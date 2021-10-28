#Image that we are using
FROM openjdk:11
COPY target/catninja-0.0.1-SNAPSHOT.jar catninja.jar
#EXPOSE 8080
ENTRYPOINT ["java", "-jar", "catninja.jar"]