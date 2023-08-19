FROM openjdk:17-jdk-slim-buster
add target/backend.jar backend.jar
ENTRYPOINT ["java","-jar","backend.jar"]
