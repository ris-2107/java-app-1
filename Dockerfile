# Use AdoptOpenJDK 11 base image
FROM adoptopenjdk/openjdk11:alpine-slim

# Set working directory inside the container
WORKDIR /app

# Copy the built JAR file from your local machine to the container
COPY build/libs/java-app-1-0.0.1-SNAPSHOT.jar /app/java-app-1.jar

# Define the command to run your application
ENTRYPOINT ["java", "-jar", "java-app-1.jar"]
