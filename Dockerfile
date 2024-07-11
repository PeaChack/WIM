FROM openjdk:17-jdk-alpine
LABEL authors="PeaChack"
MAINTAINER "PeaChack"
WORKDIR "/app"

ENTRYPOINT ["top", "-b"]