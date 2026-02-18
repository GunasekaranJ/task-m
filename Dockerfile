# Use Java 17 (stable for Spring Boot)
FROM eclipse-temurin:17-jdk

# Set working directory inside container
WORKDIR /app

# Copy the built jar into container
COPY target/Project1-0.0.1-SNAPSHOT.jar app.jar

# Expose Spring Boot port
EXPOSE 8080

ENV JAVA_OPTS="-Djava.net.preferIPv4Stack=true"

# Run the application
ENTRYPOINT ["java","-jar","/app/app.jar"]
