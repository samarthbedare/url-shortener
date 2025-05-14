FROM eclipse-temurin:21-jdk

WORKDIR /app
COPY . .

# Add execute permission to mvnw
RUN chmod +x mvnw

RUN ./mvnw clean package -DskipTests

# Find the jar file in the target folder and run it
CMD ["java", "-jar", "target/UrlShortener-0.0.1-SNAPSHOT.jar"]
