FROM eclipse-temurin:21-jdk

WORKDIR /app
COPY . .

# Add this line to fix the permission issue
RUN chmod +x mvnw

RUN ./mvnw clean package -DskipTests

CMD ["java", "-jar", "$(ls target/*.jar | grep -v 'plain.jar')"]
