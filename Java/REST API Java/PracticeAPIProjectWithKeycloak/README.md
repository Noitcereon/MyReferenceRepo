# Renewing Java API Knowledge
This repository holds a Java API, which I made to refresh my knowledge on the topic of Java REST APIs with Spring Boot, Hibernate, PostgresSql and Docker. It is purely practice.


## A few things to note:

- The Actor repository contains a custom JPQL to avoid a big performance issue using a JOIN FETCH (not implmeneted elsewhere)
- I've tried to implement basic DTOs (Data Transfer Objects) for Actors (more models, to make it easier for client to use and obfuscating internal structure from consumers)
- DatabaseConfig is in this case used to configure the connection string to the database for use with Heroku
- When you setup a new container of keycloak, you need to configure it's instance, such as adding a new realm, new clients and setup role/group mapping
- In Keycloak you can evaluate JWT claims for a client by going to: 'client you made' -> Client Scopes -> Evaluate (submenu)

## Installation
**Requires Docker**

1. Clone this repository
2. Create a `.env` file in root repository and fill it out (see `.env-example`)
3. Run `docker-compose up`. This should start 3 containers: practice_postgres_db, renewing-java-api-knowledge & practice_keycloak
4. You can now access the application (localhost:8082 by default) or run it using e.g. IntelliJ IDEA (localhost:8081 by default).
    4. (NOTE: the application container made with docker needs to be rebuild each time explicitly or it won't update, so using IDE is easier and faster)  
5. In addition, you can access Keycloak at localhost:8083 with username and password as 'admin' by default
## Usage
This project is mainly to be used as reference on how to make a Java API. Thus it contains explanation comments in the configuration files
such as the `application.properties`, `docker-compose.yml` and `Dockerfile`.

It should be able to run locally, where you can see it in action by doing the following:

1. You can run it using `docker-compose up` (starts containers as mentioned in Installation).
2. You can see the available API endpoints at `localhost:[your-port]/swagger` (default port is 8082) once you have run the application.
3. Once the database is running you can also run it using e.g. IntelliJ IDEA.

