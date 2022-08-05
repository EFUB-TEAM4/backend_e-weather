FROM openjdk:17-jdk-slim

ADD ./build/libs/backend_e-weather-0.0.1-SNAPSHOT.jar app.jar

CMD java -jar -Dspring.profiles.active=${active} app.jar