FROM amazoncorretto:21-alpine

WORKDIR /app

COPY /build/libs/catcut-0.0.1.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]