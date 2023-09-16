FROM maven:3.6.3-openjdk-17
WORKDIR /opt/MavenProject

# Copy source code and pom.xml
COPY src ./src
COPY pom.xml .

# Expose the port that the application listens on
EXPOSE 8080

# Build and run the application using Maven
CMD ["mvn", "spring-boot:run"]