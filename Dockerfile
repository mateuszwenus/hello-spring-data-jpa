FROM maven:3.8.7-eclipse-temurin-17 as build
WORKDIR /build
COPY pom.xml ./pom.xml
RUN mvn dependency:resolve
COPY src ./src
RUN mvn package

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /build/target/hello-spring-data-jpa*.jar ./hello-spring-data-jpa.jar
EXPOSE 8080
CMD ["java", "-jar", "hello-spring-data-jpa.jar"]

