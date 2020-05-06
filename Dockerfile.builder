FROM openjdk:8-jdk-alpine AS builder
# ----
# Install Maven
RUN apk add --no-cache curl tar bash
ARG MAVEN_VERSION=3.3.9
ARG USER_HOME_DIR="/root"
RUN mkdir -p /usr/share/maven && \
curl -fsSL http://apache.osuosl.org/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz | tar -xzC /usr/share/maven --strip-components=1 && \
ln -s /usr/share/maven/bin/mvn /usr/bin/mvn
ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"
# speed up Maven JVM a bit
ENV MAVEN_OPTS="-XX:+TieredCompilation -XX:TieredStopAtLevel=1"
ENTRYPOINT ["/usr/bin/mvn"]
# ----
# Install project dependencies and keep sources
# make source folder
RUN mkdir -p /usr/src/app
WORKDIR /usr/src/app
# install maven dependency packages (keep in image)
COPY pom.xml /usr/src/app
# RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)
# RUN mvn -T 1C install && rm -rf target
# copy other source files (keep in image)
COPY src /usr/src/app/src

# Production Stage for Spring boot application image
FROM openjdk:8-jre-alpine AS production
ARG DEPENDENCY=/usr/src/app/src/target/dependency

# Copy the dependency application file from build stage artifact
# COPY --from=builder ${DEPENDENCY}/BOOT-INF/lib /app/lib
# COPY --from=builder ${DEPENDENCY}/META-INF /app/META-INF
# COPY --from=builder ${DEPENDENCY}/BOOT-INF/classes /app

# Run the Spring boot application
ENTRYPOINT ["java", "-jar", "goose-game-1.0-SNAPSHOT"]
