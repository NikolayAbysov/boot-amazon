FROM openjdk:12-alpine

COPY target/amazon-0.0.1-SNAPSHOT.jar /amazon.jar

CMD ["java", "-jar", "/amazon.jar"]
