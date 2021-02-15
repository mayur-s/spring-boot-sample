FROM docker.prod.walmart.com/com/sams/c7/ubuntu:latest

COPY /target/toxiproxy-0.0.1-SNAPSHOT.jar /app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]