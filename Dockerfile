FROM gradle:6.9.1-jdk11 AS Builder

WORKDIR /app

COPY --chown=gradle:gradle . .

RUN gradle clean build --no-daemon

# use openjdk:11-jre-slim if alpine does not work
FROM openjdk:11-jre-slim


ENV DOCKERIZE_VERSION v0.6.1

RUN apt-get update && apt-get install -y wget

RUN wget https://github.com/jwilder/dockerize/releases/download/$DOCKERIZE_VERSION/dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && tar -C /usr/local/bin -xzvf dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz \
    && rm dockerize-linux-amd64-$DOCKERIZE_VERSION.tar.gz

WORKDIR /app

COPY --from=Builder /app/build/libs/*.jar app.jar

CMD ["java", "-Dspring.profiles.active=prod", "-jar", "./app.jar"]