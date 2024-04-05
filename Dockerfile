FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY build/libs/java-app-1-0.0.1-SNAPSHOT.jar /app/java-app-1.jar
ENTRYPOINT ["java", "-jar", "java-app-1.jar"]
