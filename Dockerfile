FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY DeltaSigma/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]