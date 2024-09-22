#Start with a base image containing Java runtime
FROM openjdk:17-jdk-slim

#Information around who maintains the image
MAINTAINER habibiayoub

# Add the application's jar to the image
COPY target/tondeuse-1.0.0.jar tondeuse-1.0.0.jar

# execute the application
ENTRYPOINT ["java", "-jar", "tondeuse-1.0.0.jar"]