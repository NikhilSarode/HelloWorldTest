FROM openjdk:21-slim
WORKDIR /app
COPY build/libs/app.jar app.jar
EXPOSE 8090
ENV SPRING_PROFILES_ACTIVE=prod
ENTRYPOINT ["java", "-jar", "app.jar"]