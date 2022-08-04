FROM openjdk:17-jdk-slim

ADD ./build/libs/*.jar app.jar

CMD ["java", "-jar", "app.jar"]