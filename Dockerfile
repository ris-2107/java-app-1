FROM adoptopenjdk/openjdk11:alpine-jre
WORKDIR /app
COPY build/libs/java-app-1-0.0.1-SNAPSHOT.jar /app/java-app-1.jar
ENTRYPOINT ["java", "-jar", "java-app-1.jar"]