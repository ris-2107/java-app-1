# Stage 1: Build the Java application JAR file
FROM gradle:7.3.3-jdk11 AS builder

WORKDIR /app

# Copy only the build script and settings to cache dependencies downloading
COPY build.gradle .
COPY settings.gradle .
RUN gradle --no-daemon dependencies

# Copy the entire project and build it
COPY . .
RUN gradle --no-daemon build -x test

# Stage 2: Create a minimal runtime image and copy the JAR file from the previous stage
FROM adoptopenjdk/openjdk11:alpine-slim

WORKDIR /app

# Copy the built JAR file from the builder stage
COPY --from=builder /app/build/libs/java-app-1-0.0.1-SNAPSHOT.jar /app/java-app-1.jar

# Define the command to run your application
CMD ["java", "-jar", "java-app-1.jar"]
