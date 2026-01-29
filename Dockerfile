FROM eclipse-temurin:25-jdk

WORKDIR /app

# Copy the built JAR from target directory
COPY target/weather-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
