FROM openjdk:8-jre-alpine

ARG JAR_FILE=target/goose-game-1.0-SNAPSHOT.jar
ARG JAR_LIB_FILE=target/lib/

WORKDIR /home/gianfra1905/goose/

COPY ${JAR_FILE} app.jar

ADD ${JAR_LIB_FILE} lib/

ENTRYPOINT ["java","-jar","app.jar"]