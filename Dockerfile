FROM openjdk:16-jdk-alpine
EXPOSE 8080
ADD build/libs/minestats-0.0.1-SNAPSHOT.jar minestats.jar
ENTRYPOINT ["java", "-jar", "minestats.jar"]