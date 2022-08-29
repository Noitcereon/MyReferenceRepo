FROM openjdk:17
LABEL APPLICATION="MyTunes"
WORKDIR /app
COPY ./target/my-tunes-assignment-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java","-jar","my-tunes-assignment-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080
