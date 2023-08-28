FROM openjdk:17-jdk-slim
WORKDIR /app
EXPOSE 8080

ARG JAR_FILE=/target/*.jar

COPY ${JAR_FILE} /app/app.jar

ENV SPRING_PROFILES_ACTIVE=prod

CMD ["java", "-jar", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "app.jar"]