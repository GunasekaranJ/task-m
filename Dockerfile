# Use official OpenJDK 17 image
FROM openjdk:17-jdk-alpine

# Set working directory inside container
WORKDIR /app

# Copy all project files
COPY . .

# Build the Spring Boot project
RUN ./mvnw clean package -DskipTests

# Expose port (Render assigns dynamic PORT)
ENV PORT 8080
EXPOSE $PORT

# Run the Spring Boot application
CMD ["sh", "-c", "java -jar target/*.jar --server.port=$PORT"]
