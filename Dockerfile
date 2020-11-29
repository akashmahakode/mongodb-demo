FROM docker.io/library/openjdk:8-jdk-alpine

ENV MAVEN_OPTS -Djava.net.preferIPv4Stack=true

ADD . /app

RUN mkdir -p /app; \
    cd /app; ./mvnw clean package -Dmaven.test.skip=true -Dgpg.skip;

WORKDIR /app

EXPOSE 8080

CMD ["java", "-jar", "/app/target/logs_app-0.0.1.jar"]
