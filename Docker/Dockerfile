FROM maven:3.8.5-openjdk-17 AS maven
WORKDIR /app
# Copies all files in the build directiory (docker build [directory]) into the Docker context at /app/[files]
COPY . .
# Runs the mvn clean install without tests in the Docker context
RUN ["mvn", "clean", "install", "-DskipTests"]

FROM openjdk:17 as runtime
WORKDIR /app
ENV SPRING_PROFILE production
#ARG JAR_FILE=./app/target/*.jar
# Copy compiled jar file (from 'maven' image context) into the docker image at /app/app.jar
COPY --from=maven app/target/*.jar app.jar
# Change ownership of app folder to user with id 1000 and group with id 1000
RUN chown -R 1000:1000 /app
# Change the user (and group) to not be root user in the image
USER 1000:1000
ENTRYPOINT ["java","-jar","app.jar"]