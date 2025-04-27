FROM openjdk:21-slim
WORKDIR /app
COPY build/libs/HelloWorldTest-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8090
ENV SPRING_PROFILES_ACTIVE=prod
ENTRYPOINT ["java", "-jar", "app.jar"]