# Docker Reference Files

The files in here are taken from a Java API project.

The `Dockerfile` is used to build an `image`, which in turn can be used to make a `container`

A `container` is an instance of whatever the image builds. The `Dockerfile` in this folder builds
an image with that can be used to start a Java API.

The `docker-compose.yml` is an alternative way to start containers. It can utilize a `Dockerfile` to build an image.
The `docker-compose.yml` provided here, builds the Dockerfile present in the same folder as the docker-compose itself and 
then starts 2 containers __in the same network__: one called practice_postgres_db (a Postgres Database) and one called
renewing-java-api-knowledge (a Java API).

By using the `docker-compose.yml` it automatically puts the containers it makes in the same network. This allows for communication between them.

This could also be done manually through the CLI commands. Something related to this documentation afaik: [see Docker documentation about network bridge](https://docs.docker.com/network/bridge/). 

