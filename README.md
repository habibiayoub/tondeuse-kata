# Project Name

Tondeuse

## Description

Faire avancer des tondeuses automatiquement

## Packaging

Docker est la solution de packaging proposé via un docker file.

## Steps to package and run the project

- Run ```mvn clean package``` to generate the jar file
- Run ```docker build . -t username/tondeuse:v1``` to create the docker image
- Run ```docker run -p 8080:8080 habibiayoub/tondeuse:v1``` to start the container

Below is link to a ready to use image pushed to docker hub
https://hub.docker.com/repository/docker/habibiayoub/tondeuse/general
